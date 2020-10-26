package bancoGerencia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Conta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTA")
	@SequenceGenerator(name = "SEQ_CONTA", sequenceName = "public.id_seq_conta", allocationSize = 1, initialValue = 2)
	@Column(name = "id")
	private Integer id;
	
	@Column
	private Boolean ativo = true;
	
	@Column(name = "num_conta", updatable = false)
	private String numConta;
	
	@Column
	private Double saldo = 0.0;
	
	@Column
	private Double bonus = 0.0;
	
	public Conta() {
		this.ativo = true;
		this.saldo = 0.0;
		this.bonus = 0.0;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getNumConta() {
		return numConta;
	}

	public void setNumConta(String numConta) {
		this.numConta = numConta;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}
}
