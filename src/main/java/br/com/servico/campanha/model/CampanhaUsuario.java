package br.com.servico.campanha.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Model para Entidade CampanhaUsuario.
 *
 * @author Lucas Bandeira
 */
@Entity
public class CampanhaUsuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long idUsuario;
	
	private Long idCampanha;
	
	
	
	public CampanhaUsuario() {}

	public CampanhaUsuario(Long idUsuario, Long idCadastro) {
		this.idUsuario = idUsuario;
		this.idCampanha = idCadastro;
	}

	/**
	 * Método get do atributo id
	 * 
	 * @return id Valor do atributo
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Método get do atributo idUsuario
	 * 
	 * @return idUsuario Valor do atributo
	 */
	public Long getIdUsuario() {
		return idUsuario;
	}
	
	/**
	 * Método get do atributo idCampanha
	 * 
	 * @return idCampanha Valor do atributo
	 */
	public Long getIdCampanha() {
		return idCampanha;
	}

}
