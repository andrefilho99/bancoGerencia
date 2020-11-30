package bancoGerencia.service;

import bancoGerencia.model.Conta;

public class OperacaoGetBonus implements Operacao {
	
	private Conta conta;
	
	public OperacaoGetBonus(Conta conta) {
		this.conta = conta;
	}
	
	@Override
	public Double execute() {
		return conta.getBonus();
	}
}
