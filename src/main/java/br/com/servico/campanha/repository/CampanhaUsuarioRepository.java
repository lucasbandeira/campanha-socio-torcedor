package br.com.servico.campanha.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.servico.campanha.model.CampanhaUsuario;

public interface CampanhaUsuarioRepository extends Repository <CampanhaUsuario, Long> {
	
	/**
	 * Método que salva uma CampanhaUsuario.
	 * 
	 * @param campanhaUsuario
	 * @return CampanhaUsuario salva
	 */
	CampanhaUsuario save(CampanhaUsuario campanhaUsuario);
	
	/**
	 * Método que retorna uma Campanha pelo idUsuario.
	 * 
	 * @param idUsuario
	 * @return Campanha pelo idUsuario
	 */
	List<CampanhaUsuario> findByIdUsuario(Long idUsuario);
}
