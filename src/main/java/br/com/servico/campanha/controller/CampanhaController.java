package br.com.servico.campanha.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import br.com.servico.campanha.model.CampanhaUsuario;
import br.com.servico.campanha.repository.CampanhaRepository;
import br.com.servico.campanha.repository.CampanhaUsuarioRepository;

/**
 * Rest Controller para API de Campanha.
 *
 * @author Lucas Bandeira
 */
@RestController
public class CampanhaController {

	private static final int QUANTIDADE_DIAS = 1;

	@Autowired
	private CampanhaRepository campanhaRepository;
	
	@Autowired
	private CampanhaUsuarioRepository campanhaUsuarioRepository;
	
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
				campanhaDoPeriodo.setDataFinal(campanhaDoPeriodo.getDataFinal()
						.plusDays(QUANTIDADE_DIAS));
				campanhaRepository.save(campanhaDoPeriodo);
			}
			
			campanhaRepository.save(novaCampanha);
			
			List<Campanha> listaCampanhaMesmaDataFinal = campanhaRepository
					.findByDataFinal(novaCampanha.getDataFinal());
			if(!listaCampanhaMesmaDataFinal.isEmpty()) {
				somarUmaDiaNasCampanhasComMesmaData(listaCampanhaMesmaDataFinal);
			}
			
			return ResponseEntity.ok().body(novaCampanha);
		}
		
		return ResponseEntity.badRequest().body(null);
		
	}

	/**
	 * Método auxiliar para somar um dia nas campanha com mesma data de vencimento
	 * 
	 * @param listaCampanhaMesmaDataFinal
	 */
	private void somarUmaDiaNasCampanhasComMesmaData(List<Campanha> listaCampanhaMesmaDataFinal) {
		int quantidade = listaCampanhaMesmaDataFinal.size();
		for (Campanha campanha : listaCampanhaMesmaDataFinal) {
			while(quantidade > 1) {
				campanha.setDataFinal(campanha.getDataFinal().plusDays(quantidade));
				campanhaRepository.save(campanha);
				quantidade--;
			}
		}
	}
	
	/**
	 * Método POST para associar os usuários a campanhas não cadastradas
	 * 
	 * @return lista de campanhas há serem cadastradas
	 */
	@PostMapping("/associar/{idUsuario}/{idTime}")
	List<Campanha> associarCampanhasUsuario (@PathVariable Long idUsuario, @PathVariable Long idTime) {
		List<Campanha> listaCampanhasPorTime = campanhaBO
				.listarCampanhasAtivas(campanhaRepository.findByTimeId(idTime));
		List<CampanhaUsuario> listaAssociacoesDoUsuario = campanhaUsuarioRepository
				.findByIdUsuario(idUsuario);

		List<Campanha> campanhasDoUsuario = new ArrayList<Campanha>();
		for (CampanhaUsuario campanhaUsuario : listaAssociacoesDoUsuario) {
			campanhasDoUsuario.add(campanhaRepository.findById(campanhaUsuario.getIdCampanha()));
		}
		
		List<Campanha> listaCampanhasHaSeremCadstradas = listaCampanhasPorTime.stream()
				.filter(campanhaPorTime -> !campanhasDoUsuario.contains(campanhaPorTime))
				.collect(Collectors.toList());
		
		if(!listaCampanhasHaSeremCadstradas.isEmpty()) {
			for (Campanha campanhaHaSerCadstrada : listaCampanhasHaSeremCadstradas) {
				CampanhaUsuario camapanhaUsuario = 
						new CampanhaUsuario(idUsuario, campanhaHaSerCadstrada.getId());
				campanhaUsuarioRepository.save(camapanhaUsuario);
			}
		}
		
		return listaCampanhasHaSeremCadstradas;
	}
	
	/**
	 * Método DELETE para remover uma campanha pelo id
	 */
	@DeleteMapping("/campanha/{id}")
	Campanha removerCampanha(@PathVariable Long id) {
		return campanhaRepository.deleteById(id);
	}
}
