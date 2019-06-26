package br.com.servico.campanha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.servico.campanha.business.CampanhaBO;
import br.com.servico.campanha.model.Campanha;
import br.com.servico.campanha.repository.CampanhaRepository;

/**
 * Rest Controller para API de Campanha.
 *
 * @author Lucas Bandeira
 */
@RestController
public class CampanhaController {

	@Autowired
	private CampanhaRepository campanhaRepository;
	
	@Autowired
	private CampanhaBO campanhaBO;
	
	/**
	 * Método GET para listar as campanhas ativas
	 * 
	 * @return lista de campanhas ativas
	 */
	@GetMapping("/campanhas")
	List<Campanha> listarCampanhas() {
		return campanhaBO.listarCampanhasAtivas(campanhaRepository.findAll());
	}
	
	/**
	 * Método GET para visualizar uma campanha pelo id
	 * 
	 * @return Campanha
	 */
	@GetMapping("/campanha/{id}")
	Campanha visualizarCampanha(@PathVariable Long id) {
		return campanhaRepository.findById(id);
	}
	
	/**
	 * Método POST para criar uma campanha
	 * 
	 * @return Campanha
	 */
	@PostMapping("/campanha")
	ResponseEntity<Campanha> criarCampanha (@RequestBody Campanha novaCampanha) {
		if(campanhaBO.validarPeriodo(novaCampanha)) {
			
			List<Campanha> listaCampanhasDoPeriodo = campanhaBO
					.listarCampanhasDoPeriodo(campanhaBO.listarCampanhasAtivas(campanhaRepository.findAll()), 
							novaCampanha.getDataFinal());
			
			for (Campanha campanhaDoPeriodo : listaCampanhasDoPeriodo) {
				campanhaDoPeriodo.setDataFinal(campanhaDoPeriodo.getDataFinal().plusDays(1));
				campanhaRepository.save(campanhaDoPeriodo);
			}
			
			List<Campanha> listaCampanhaMesmaDataFinal = campanhaRepository.findByDataFinal(novaCampanha.getDataFinal());
			if(!listaCampanhaMesmaDataFinal.isEmpty()) {
				somarUmaDiaNasCampanhasComMesmaData(listaCampanhaMesmaDataFinal);
			}
			
			return ResponseEntity.ok().body(campanhaRepository.save(novaCampanha));
		}
		
		return ResponseEntity.badRequest().body(null);
		
	}

	private void somarUmaDiaNasCampanhasComMesmaData(List<Campanha> listaCampanhaMesmoDataFinal) {
		
		int quantidade = listaCampanhaMesmoDataFinal.size();
		for (Campanha campanha : listaCampanhaMesmoDataFinal) {
			while(quantidade > 1) {
				campanha.setDataFinal(campanha.getDataFinal().plusDays(quantidade-1));
				campanhaRepository.save(campanha);
				quantidade--;
			}
		}
	}
	
	/**
	 * Método DELETE para remover uma campanha pelo id
	 */
	@DeleteMapping("/campanha/{id}")
	void removerCampanha(@PathVariable Long id) {
		campanhaRepository.deleteById(id);
	}	
}
