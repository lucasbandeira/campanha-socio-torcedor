/**
 * 
 */
package br.com.servico.campanha.business;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.servico.campanha.model.Campanha;

/**
 * Business Object para auxiliar o RestController
 * 
 * @author Lucas Bandeira
 */
@Service
public class CampanhaBO {
	
	/**
	 * Método que lista as campanhas com data virgente
	 * 
	 * @param listaCampanhas - lista com todas as campanhas da base
	 * @return lista das campanhas virgentes
	 */
	public List<Campanha> listarCampanhasAtivas(final List<Campanha> listaCampanhas) {
 		return listaCampanhas.stream()
 				.filter(campanha -> !campanha.getDataFinal().isBefore(LocalDate.now()))
 				.collect(Collectors.toList());
	}
	
	/**
	 * Método que lista as campanhas virgentes dentro de um período
	 * 
	 * @param listaCampanhas - lista com de campanhas
	 * @param dataFinal - data final da campanha
	 * @return lista das campanhas virgentes dentro de um período
	 */
	public List<Campanha> listarCampanhasDoPeriodo(final List<Campanha> listaCampanhas, LocalDate dataFinal) {
		return listaCampanhas.stream()
				.filter(campanha -> !campanha.getDataFinal().isAfter(dataFinal))
				.collect(Collectors.toList());
	}
	
	/**
	 * Método que valida se a data final de uma campanha é menor que a data inicial
	 * 
	 * @return Boolean true caso data final maior que data inicial ou false caso data final menor que data inicial
	 */
	public Boolean validarPeriodo(final Campanha campanha) {
		return campanha.getDataFinal().isAfter(campanha.getDataInicial());
	}
	
	
}
