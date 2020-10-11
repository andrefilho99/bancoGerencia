package bancoGerencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bancoGerencia.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer>{
	
	@Query(value = "select * from conta where num_conta = ?1 and ativo = true", nativeQuery=true)
	public Conta findOne(String numConta);
}
