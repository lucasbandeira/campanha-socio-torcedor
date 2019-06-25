package br.com.campanha.servico.campanha;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.RestAssured;

//Para rodar o teste é necessario subir a aplicação e remover o @Ignore
@Ignore
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
            basePath = "/";
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

    @Test
	public void testaCriacaoDeCampanha() {
		Map<String,Object> map = new HashMap<>();
		map.put("nome", "xyx1111");
		map.put("dataInicial", "audi");
        map.put("dataFinal", "red");
        map.put("time", Arrays.asList(new HashMap<String, Object>() {{
            put("id", "1");
            put("nome", "Ceara");
        }}));

		given()
		.contentType("application/json")
		.body(map)
		.when().post("/campanha").then()
		.statusCode(200);
	}


}
