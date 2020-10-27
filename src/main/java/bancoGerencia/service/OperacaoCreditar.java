package bancoGerencia.service;

import bancoGerencia.model.Conta;

public class OperacaoCreditar implements Operacao {
	
	private Conta conta;
	
	private Double valor;
	
	public OperacaoCreditar(Conta conta, Double valor) {
		this.conta = conta;
		this.valor = valor;
	}
	
	@Override
	public Double execute() {
		Double novoBalanco = conta.getSaldo() + valor;
		Double novoBonus = conta.getBonus() + (valor*0.01);
		conta.setSaldo(novoBalanco);
		conta.setBonus(novoBonus);
		return novoBalanco;
		
	}
}
