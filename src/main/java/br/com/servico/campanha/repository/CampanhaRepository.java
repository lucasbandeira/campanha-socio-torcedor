package br.com.servico.campanha.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.servico.campanha.model.Campanha;

/**
 * Repository para Entidade Campanha.
 *
 * @author Lucas Bandeira
 */
public interface CampanhaRepository extends Repository <Campanha, Long> {
	
	/**
	 * Método que retorna uma Campanha pelo id.
	 * 
	 * @param id
	 * @return Campanha pelo id
	 */
	Campanha findById(Long id);
	
	/**
	 * Método que retorna uma lista de Campanhas pela dataFinal.
	 * 
	 * @param dataFinal
	 * @return lista de Campanhas pela dataFinal
	 */
	List<Campanha> findByDataFinal(LocalDate dataFinal);
	
	/**
	 * Método que retorna uma lista de Campanhas pelo idTime.
	 * 
	 * @param idTime
	 * @return lista de Campanhas pelo idTime
	 */
	List<Campanha> findByTimeId(Long idTime);
	
	/**
	 * Método que retorna uma todas as Campanhas
	 * 
	 * @return lista de Campanhas
	 */
	List<Campanha> findAll();
	
	/**
	 * Método que salva uma Campanha.
	 * 
	 * @param campanha
	 * @return Campanha salva
	 */
	Campanha save(Campanha campanha);
	
	/**
	 * Método que remove uma Campanha por id.
	 * 
	 * @param id
	 * @return Campanha removida
	 */
	Campanha deleteById(Long id);
	
	
}
