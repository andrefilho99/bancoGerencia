package bancoGerencia.service;

import bancoGerencia.model.Conta;

public class OperacaoTransferir implements Operacao {
	
	private Conta c1;
	
	private Conta c2;
	
	private Double valor;
	
	public OperacaoTransferir(Conta c1, Conta c2, Double valor) {
		this.c1 = c1;
		this.c2 = c2;
		this.valor = valor;
	}
	
	@Override
	public Double execute() {
		Double novoBalancoC1 = c1.getSaldo() - valor;
		Double novoBalancoC2 = c2.getSaldo() + valor;
		
		c1.setSaldo(novoBalancoC1);
		c2.setSaldo(novoBalancoC2);
		return novoBalancoC1;
	}
}
