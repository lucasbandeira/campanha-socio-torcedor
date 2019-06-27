package br.com.campanha.servico.campanha;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.jayway.restassured.RestAssured;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CampanhaApplicationTests {

	@BeforeClass
    public static void setup() {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = Integer.valueOf(8080);
        }
        else{
            RestAssured.port = Integer.valueOf(port);
        }

        String basePath = System.getProperty("server.base");
        if(basePath==null){
            basePath = "";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;
    }

    @Test
    public void testa1RetornoDaListaDeCampanhas() {
        given()
        .when().get("/campanhas").then().statusCode(200);
    }

    /**
     * Cenário: Eu quero cadastrar novas campanhas de acordo com as regras de négocio 
     * 
     * Dado que sejam cadastradas 3 campanhas com as seguintes datas de vencimento: 
     * 03/07/2019, 02/07/2019 e 03/07/2019
     * Então verifico se as mesmas foram cadastradas
     * E verifico se as campanhas com datas do mesmo período de virgencia foram incrementadas 
     * e as com mesma data de validade também foram incrementadas.
     */
    @SuppressWarnings("serial")
    @Test
    public void testa2CenarioDeCriacaoDeCampanha() {
    	
    	Map<String,Object> campanha1 = new HashMap<>();
    	campanha1.put("nome", "x");
    	campanha1.put("dataInicial", "2019-07-01");
        campanha1.put("dataFinal", "2019-07-03");
        campanha1.put("time", new HashMap<String, Object>() {{
            put("id", "1");
            put("nome", "Ceara");
        }});
    	given()
    	.contentType("application/json;text/plain")
    	.body(campanha1)
    	.when().post("/campanha").then()
    	.statusCode(200);
    	
    	Map<String,Object> cmapanha2 = new HashMap<>();
    	cmapanha2.put("nome", "y");
    	cmapanha2.put("dataInicial", "2019-07-01");
        cmapanha2.put("dataFinal", "2019-07-02");
        cmapanha2.put("time", new HashMap<String, Object>() {{
            put("id", "1");
            put("nome", "Ceara");
        }});
    	given()
    	.contentType("application/json;text/plain")
    	.body(cmapanha2)
    	.when().post("/campanha").then()
    	.statusCode(200);
    	
    	Map<String,Object> campanha3 = new HashMap<>();
    	campanha3.put("nome", "z");
    	campanha3.put("dataInicial", "2019-07-01");
        campanha3.put("dataFinal", "2019-07-03");
        campanha3.put("time", new HashMap<String, Object>() {{
            put("id", "1");
            put("nome", "Ceara");
        }});
    	given()
    	.contentType("application/json;text/plain")
    	.body(campanha3)
    	.when().post("/campanha").then()
    	.statusCode(200);
    	
    	given().when().get("/campanhas").then()
    	.body("nome", hasItems("x", "y", "z"));
    	
    	given().when().get("/campanhas").then()
    	.body("dataFinal", hasItems("2019-07-04","2019-07-05","2019-07-03"));
    }
    
    /**
     * Cenário: Eu quero associar novas campanhas a um usuário, já cadastrado, de acordo com as regras de négocio 
     * 
     * Dado que tenha um usuários cadastrado com o seu time de coração
     * Então associo o usuario em todas as campanhas do seu respectivo time.
     * E verifico que as campanha cadastradas foram retornadas
     */
	@Test
	public void testa3AssociacaoDeCampanhasPorUsuario() {
		
		//Dada a requisição de associacao do usuario de identificador 1 (primero parametro), cujo time do coração possui identificador 1 (segundo parametro).
		//Então realizo o cadastramento com sucesso
		given()
        .when().post("/associar/1/1")
        .then().body("nome", hasItems("x", "y", "z")).statusCode(200);
    }

}
