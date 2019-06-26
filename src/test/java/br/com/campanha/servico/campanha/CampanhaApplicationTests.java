package br.com.campanha.servico.campanha;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.RestAssured;

import static org.hamcrest.Matchers.hasItems;

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
    public void testaRetornoDaListaDeCampanhas() {
        given()
        .when().get("/campanhas").then().statusCode(200);
    }

    /**
     * 
     * Cenário de teste para validar lógica de criação de uma campanha
     * 
     */
    @SuppressWarnings("serial")
	@Test
	public void testaCriacaoDeCampanha() {
    	
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


}
