package bancoGerencia.service;

import bancoGerencia.model.Conta;

public class OperacaoGetSaldo implements Operacao {
	
	private Conta conta;
	
	public OperacaoGetSaldo(Conta conta) {
		this.conta = conta;
	}
	
	@Override
	public Double execute() {
		return conta.getSaldo();
	}
}
