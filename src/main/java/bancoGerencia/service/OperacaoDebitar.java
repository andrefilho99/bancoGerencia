package bancoGerencia.service;

import bancoGerencia.model.Conta;

public class OperacaoDebitar implements Operacao{
	
	private Conta conta;
	
	private Double valor;
	
	public OperacaoDebitar(Conta conta, Double valor) {
		this.conta = conta;
		this.valor = valor;
	}
	
	@Override
	public Double execute() {
		Double novoBalanco = conta.getSaldo() - valor;
		conta.setSaldo(novoBalanco);
		return novoBalanco;
	}
}
