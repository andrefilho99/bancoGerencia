package bancoGerencia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bancoGerencia.model.Conta;
import bancoGerencia.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	private ContaService contaService;
	
	@GetMapping("/{numConta}")
	public Conta one(@PathVariable String numConta) {
		return contaService.getConta(numConta);
	}
	
	@GetMapping("/create")
	public String create() {
		return contaService.createConta();
	}
}