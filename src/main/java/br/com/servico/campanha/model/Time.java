package br.com.servico.campanha.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Model para Entidade Campanha.
 *
 * @author Lucas Bandeira
 */
@Entity
public class Time{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	/**
	 * Método get do atributo nome
	 * 
	 * @return nome Valor do atributo
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Método set do atributo nome
	 * 
	 * @param nome Valor para setar no atributo
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Método get do atributo id
	 * 
	 * @return id Valor do atributo
	 */
	public Long getId() {
		return id;
	}
	
}
