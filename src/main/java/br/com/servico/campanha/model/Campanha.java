package br.com.servico.campanha.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Model para Entidade Campanha.
 *
 * @author Lucas Bandeira
 */
@Entity
public class Campanha{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private LocalDate dataInicial;
	
	private LocalDate dataFinal;
	
	@OneToOne
	@JoinColumn(name = "id_time")
	private Time time;
	
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
	 * Método get do atributo dataInicial
	 * 
	 * @return dataInicial Valor do atributo
	 */
	public LocalDate getDataInicial() {
		return dataInicial;
	}
	
	/**
	 * Método set do atributo dataInicial
	 * 
	 * @param dataInicial Valor para setar no atributo
	 */
	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}
	
	/**
	 * Método get do atributo dataFinal
	 * 
	 * @return dataFinal Valor do atributo
	 */
	public LocalDate getDataFinal() {
		return dataFinal;
	}
	
	/**
	 * Método set do atributo dataFinal
	 * 
	 * @param dataFinal Valor para setar no atributo
	 */
	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
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
	 *  Método get do atributo time
	 * 
	 * @return time Valor do atributo
	 */
	public Time getTime() {
		return time;
	}

	/**
	 * Método set do atributo time
	 * 
	 * @param time Valor para setar no atributo
	 */
	public void setTime(Time time) {
		this.time = time;
	}
	
}
