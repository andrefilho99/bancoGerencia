package bancoGerencia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bancoGerencia.exceptions.ContaException;
import bancoGerencia.exceptions.SaldoException;
import bancoGerencia.exceptions.ValorException;
import bancoGerencia.model.Conta;
import bancoGerencia.service.ContaService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	private ContaService contaService;
	
	@GetMapping("/")
	public List<Conta> all() throws ContaException {
		return contaService.getAll();
	}
	
	@GetMapping("/{numConta}")
	public Conta one(@PathVariable String numConta) {
		try {
			return contaService.getConta(numConta);
		} catch (ContaException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/create")
	public String create() {
		return contaService.createConta();
	}
	
	@GetMapping("/saldo/{numConta}")
	public Double getSaldo(@PathVariable String numConta) throws ContaException {
		return contaService.getSaldo(numConta);
	}
	
	@GetMapping("/creditar/{numConta}/{valor}")
	public void creditar(@PathVariable String numConta, @PathVariable Double valor) throws ContaException, ValorException {
		contaService.creditar(numConta, valor);
	}
	
	@GetMapping("/debitar/{numConta}/{valor}")
	public void debitar(@PathVariable String numConta, @PathVariable Double valor) throws ContaException, ValorException, SaldoException {
		contaService.debitar(numConta, valor);
	}
	
	@GetMapping("/debitar-bonus/{numConta}/{valor}")
	public void debitarBonus(@PathVariable String numConta, @PathVariable Double valor) throws ContaException, ValorException, SaldoException {
		contaService.debitarBonus(numConta, valor);
	}
	
	@GetMapping("/transferir/{contaOrig}/{contaDest}/{valor}")
	public void transferir(@PathVariable String contaOrig, @PathVariable String contaDest, @PathVariable Double valor) throws ContaException, ValorException, SaldoException {
		contaService.transferir(contaOrig, contaDest, valor);
	}
	
}
