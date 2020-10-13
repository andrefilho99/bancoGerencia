package bancoGerencia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bancoGerencia.exceptions.ContaException;
import bancoGerencia.model.Conta;
import bancoGerencia.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	private ContaService contaService;
	
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
}
