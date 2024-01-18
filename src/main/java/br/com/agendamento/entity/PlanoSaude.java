package br.com.agendamento.entity;


import java.io.Serializable;

import javax.persistence.*;

;

/**
 * 
 */
@Entity
@Table(name="planoasaude")
public class PlanoSaude implements Serializable {

  
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPlanoSaude")
	private int idPlanoSaude;
	private String carterinha;
	private String plano;
	private String produto;
	@JoinColumn(name = "idCliente", nullable = true)
    @ManyToOne
	private Cliente cliente;

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public int getIdPlanoSaude() {
		return idPlanoSaude;
	}
	public void setIdPlanoSaude(int idPlanoSaude) {
		this.idPlanoSaude = idPlanoSaude;
	}
	public String getCarterinha() {
		return carterinha;
	}
	public void setCarterinha(String carterinha) {
		this.carterinha = carterinha;
	}
	public String getPlano() {
		return plano;
	}
	public void setPlano(String plano) {
		this.plano = plano;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((carterinha == null) ? 0 : carterinha.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + idPlanoSaude;
		result = prime * result + ((plano == null) ? 0 : plano.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlanoSaude other = (PlanoSaude) obj;
		if (carterinha == null) {
			if (other.carterinha != null)
				return false;
		} else if (!carterinha.equals(other.carterinha))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (idPlanoSaude != other.idPlanoSaude)
			return false;
		if (plano == null) {
			if (other.plano != null)
				return false;
		} else if (!plano.equals(other.plano))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}
	
	
}
