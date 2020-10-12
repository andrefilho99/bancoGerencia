package bancoGerencia.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bancoGerencia.model.Conta;
import bancoGerencia.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;
	
	//Caso não ache a conta lançar exceção
	public Conta getConta(String numConta) {
		return contaRepository.findOne(numConta);
	}
	
	public String createConta() {
		
		Conta conta = new Conta();
		
		conta.setNumConta(generateNumConta());	
		contaRepository.save(conta);
		
		return conta.getNumConta();
	}
	
	public void updateConta(String numConta, Double saldo) {
		
		Conta conta = getConta(numConta);
		
		conta.setSaldo(saldo);
		contaRepository.save(conta);
	}
	
	public void deleteConta(String numConta) {
		
		Conta conta = getConta(numConta);
		
		conta.setAtivo(false);;
		contaRepository.save(conta);
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
}
