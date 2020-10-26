package bancoGerencia.service;

import bancoGerencia.model.Conta;

public class OperacaoDebitarBonus implements Operacao{
	
	private Conta conta;
	
	private Double valorBonus;
	
	public OperacaoDebitarBonus(Conta conta, Double valorBonus) {
		this.conta = conta;
		this.valorBonus = valorBonus;
	}
	
	@Override
	public Double execute() {
		Double novoBonus = conta.getBonus() - valorBonus;
		conta.setSaldo(novoBonus);
		return novoBonus;
	}
}
