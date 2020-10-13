package bancoGerencia.service;

import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bancoGerencia.exceptions.ContaException;
import bancoGerencia.exceptions.SaldoException;
import bancoGerencia.exceptions.ValorException;
import bancoGerencia.model.Conta;
import bancoGerencia.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;
	
	private Operacao operacao;
	
	//Caso não ache a conta lançar exceção
	public Conta getConta(String numConta) throws ContaException{
		
		Conta conta = contaRepository.findOne(numConta);
		
		if(conta == null) {
			throw new ContaException("Conta " + numConta + " não existe.");
		}
		
		return conta;
	}
	
	public String createConta() {
		
		Conta conta = new Conta();
		
		conta.setNumConta(generateNumConta());	
		contaRepository.save(conta);
		
		return conta.getNumConta();
	}
	
	public void updateConta(String numConta, Double saldo) throws ContaException {
		
		Conta conta = getConta(numConta);
		
		conta.setSaldo(saldo);
		contaRepository.save(conta);
	}
	
	public void deleteConta(String numConta) throws ContaException {
		
		Conta conta = getConta(numConta);
		
		conta.setAtivo(false);;
		contaRepository.save(conta);
	}
	
	public Double getSaldo(String numConta) throws ContaException {
		
		Conta conta = getConta(numConta);	
		operacao = new OperacaoGetSaldo(conta);
		return operacao.execute();
	}
	
	@Transactional
	public void creditar(String numConta, Double valor) throws ContaException, ValorException {
		
		Conta conta = getConta(numConta);
		
		if(valor <= 0) {
			throw new ValorException("Valor inválido.");
		}
		
		operacao = new OperacaoCreditar(conta, valor);
		operacao.execute();
		
		contaRepository.save(conta);
	}
	
	@Transactional
	public void debitar(String numConta, Double valor) throws ContaException, ValorException, SaldoException {
		
		Conta conta = getConta(numConta);
		
		verificarValor(valor);
		verificarDebitoConta(conta, valor, "Saldo menor que o valor do débito.");
		
		operacao = new OperacaoDebitar(conta, valor);
		operacao.execute();
		
		contaRepository.save(conta);
	}
	
	@Transactional
	public void transferir(String numContaOrigem, String numContaDestino, Double valor) throws ContaException, ValorException, SaldoException {
		
		Conta contaOrigem = getConta(numContaOrigem);
		Conta contaDestino = getConta(numContaDestino);
		
		verificarValor(valor);
		verificarDebitoConta(contaOrigem, valor, "Saldo menor que o valor da transferência.");
		
		operacao = new OperacaoTransferir(contaOrigem, contaDestino, valor);
		operacao.execute();
		
		contaRepository.save(contaOrigem);
		contaRepository.save(contaDestino);
	}
	
	private String generateNumConta() {
		
		int numConta;
		
		while(true) {
			
			numConta = 10000 + new Random().nextInt(900000);
			
			if(contaRepository.findOne(Integer.toString(numConta)) == null) {
				break;
			}
		}
		
		return Integer.toString(numConta);
	}
	
	private void verificarValor(Double valor) throws ValorException {
		
		if(valor <= 0) {
			throw new ValorException("Valor inválido.");
		}
	}
	
	private void verificarDebitoConta(Conta conta, Double valor, String message) throws SaldoException {
		
		if(conta.getSaldo() < valor) {
			throw new SaldoException(message);
		}
	}
}
