package bancoGerencia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bancoGerencia.model.Conta;
import bancoGerencia.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;
	
	public Conta getConta(Integer id) {
		return contaRepository.findOne(id);
	}
}
