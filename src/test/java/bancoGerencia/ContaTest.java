package bancoGerencia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import bancoGerencia.exceptions.ContaException;
import bancoGerencia.exceptions.SaldoException;
import bancoGerencia.exceptions.ValorException;
import bancoGerencia.service.ContaService;

@RunWith(SpringRunner.class )
@SpringBootTest
public class ContaTest {
	
	@Autowired
	private ContaService contaService;
	
	@Test
	public void getSaldoContaCriacao() throws ContaException {
		String numConta = contaService.createConta();	
		assertEquals(0.0, contaService.getSaldo(numConta), 0.0001);
	}
	
	@Test
	public void getSaldoConta() throws ContaException {
		String numConta = contaService.createConta();
		contaService.updateConta(numConta, 55.0);
		assertEquals(55.0, contaService.getSaldo(numConta), 0.0001);
	}
	
	@Test(expected = ContaException.class)
	public void getSaldoContaNaoExiste() throws ContaException {
		String numConta = "naocriada";
		contaService.getSaldo(numConta);
	}
	
	@Test
	public void creditarConta() throws ContaException, ValorException {
		String numConta = contaService.createConta();
		contaService.creditar(numConta, 12.0);
		assertEquals(12.0, contaService.getSaldo(numConta), 0.0001);
	}
	
	@Test(expected = ValorException.class)
	public void creditarContaZero() throws ContaException, ValorException {
		String numConta = contaService.createConta();
		contaService.creditar(numConta, 0.0);
	}
	
	@Test(expected = ValorException.class)
	public void creditarContaNegativo() throws ContaException, ValorException {
		String numConta = contaService.createConta();
		contaService.creditar(numConta, -10.0);
	}
	
	@Test
	public void debitarConta() throws ContaException, ValorException, SaldoException {
		String numConta = contaService.createConta();
		contaService.updateConta(numConta, 55.0);
		contaService.debitar(numConta, 5.0);
		assertEquals(50.0, contaService.getSaldo(numConta), 0.0001);
	}
	
	@Test(expected = SaldoException.class)
	public void debitarContaSemSaldo() throws ContaException, ValorException, SaldoException {
		String numConta = contaService.createConta();
		contaService.updateConta(numConta, 55.0);
		contaService.debitar(numConta, 56.0);
	}
	
	@Test(expected = ValorException.class)
	public void debitarContaZero() throws ContaException, ValorException, SaldoException {
		String numConta = contaService.createConta();
		contaService.debitar(numConta, 0.0);
	}
	
	@Test(expected = ValorException.class)
	public void debitarContaNegativo() throws ContaException, ValorException, SaldoException {
		String numConta = contaService.createConta();
		contaService.debitar(numConta, -10.0);
	}
	
	@Test
	public void transferirConta() throws ContaException, ValorException, SaldoException {
		String numContaOrigem = contaService.createConta();
		String numContaDestino = contaService.createConta();
		contaService.updateConta(numContaOrigem, 10.0);
		contaService.transferir(numContaOrigem, numContaDestino, 10.0);
		assertEquals(0.0, contaService.getSaldo(numContaOrigem), 0.0001);
		assertEquals(10.0, contaService.getSaldo(numContaDestino), 0.0001);
	}
	
	@Test(expected = ContaException.class)
	public void transferirContaOrigemNaoExiste() throws ContaException, ValorException, SaldoException {
		String numContaOrigem = "naoCriada";
		String numContaDestino = contaService.createConta();
		contaService.updateConta(numContaOrigem, 10.0);
		contaService.transferir(numContaOrigem, numContaDestino, 10.0);
	}
	
	@Test(expected = ContaException.class)
	public void transferirContaDestinoNaoExiste() throws ContaException, ValorException, SaldoException {
		String numContaOrigem = contaService.createConta();
		String numContaDestino = "naoCriada";
		contaService.updateConta(numContaOrigem, 10.0);
		contaService.transferir(numContaOrigem, numContaDestino, 10.0);
	}
	
	@Test(expected = SaldoException.class)
	public void transferirContaSemSaldo() throws ContaException, ValorException, SaldoException {
		String numContaOrigem = contaService.createConta();
		String numContaDestino = contaService.createConta();
		contaService.updateConta(numContaOrigem, 10.0);
		contaService.transferir(numContaOrigem, numContaDestino, 20.0);
	}
	
	@Test(expected = ValorException.class)
	public void transferirContaZero() throws ContaException, ValorException, SaldoException {
		String numContaOrigem = contaService.createConta();
		String numContaDestino = contaService.createConta();
		contaService.transferir(numContaOrigem, numContaDestino, 0.0);
	}
	
	@Test(expected = ValorException.class)
	public void transferirContaNegativo() throws ContaException, ValorException, SaldoException {
		String numContaOrigem = contaService.createConta();
		String numContaDestino = contaService.createConta();
		contaService.transferir(numContaOrigem, numContaDestino, -10.0);
	}
	
	@Test
	public void creditarBonus() throws ContaException, ValorException {
		String numConta = contaService.createConta();
		contaService.creditar(numConta, 100.0);
		assertEquals(1.0, contaService.getBonus(numConta), 0.0001);
	}
	
	@Test
	public void debitarBonus() throws ContaException, ValorException, SaldoException {
		String numConta = contaService.createConta();
		contaService.creditar(numConta, 100.0);
		contaService.debitarBonus(numConta, 1.0);
		assertEquals(0.0, contaService.getBonus(numConta), 0.0001);
	}
	
	@Test(expected = SaldoException.class)
	public void debitarBonusSemSaldo() throws ContaException, ValorException, SaldoException {
		String numConta = contaService.createConta();
		contaService.creditar(numConta, 100.0);
		contaService.debitarBonus(numConta, 10.0);
	}
}

