# Campanha sócio-torcedor
API REST campanhas de sócio-torcedor
Que Fornece macanismo para cadastro de campanhas para sócio-torcedores.

Ao cadastrar uma campanha o sistema verifica se existem campanhas dentro do mesmo período caso positivo soma um dias na data de termino. O sistema também verifica se há campanha com o mesmo termino e caso positivo adiciona mais um dia.
 Para melhor endendimento segue o cénario de teste implementado:
 [imagem do cenário](https://drive.google.com/file/d/1HAUX-bo-k1BcbI3JM4FTy5QTAnfh4orZ/view?usp=sharing)
 ```
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
 ```

O sistema também fornece o serviço para que o usuario se associe a uma campanha do seu time
[imagem do cenário](https://drive.google.com/file/d/1bTvf0e-c5F6Qah98Y81CuaTlf0jZMgBq/view?usp=sharing)
```
    /**
     * Cenário: Eu quero associar novas campanhas a um usuário, já cadastrado, acordo com as regras de négocio 
     * 
     * Dado que tenha um usuários cadastrado com o seu time de coração
     * Então associo o usuario em todas as campanhas do seu respectivo time.
     * E verifico que as campanha cadastradas foram retornadas
     */
	@Test
	public void testaAssociacaoDeCampanhasPorUsuario() {
		
		//Dada a requisição de associacao do usuario de identificador 1 (primero parametro), cujo time do coração possui identificador 1 (segundo parametro).
		//Então realizo o cadastramento com sucesso
		given()
        .when().post("/associar/1/1")
        .then().body("nome", hasItems("x", "y", "z")).statusCode(200);
    }
```

### GET /campanhas
[imagem da lista de campanhas](https://drive.google.com/file/d/1aUQ__EljETYh3r1nggIq6Cy3vxZ6GMmW/view?usp=sharing)


### GET /campanha/:id
[image do detalhe de uma campanha](https://drive.google.com/file/d/1dM_VmtGEtnQU_e9sMvVF_7NsiesDlBU1/view?usp=sharing)


### POST /campanha
[imagem da criação de uma campanha](https://drive.google.com/file/d/1qeJLD1nE4fEjVL9qhLVt2jr0t1Aiv2fQ/view?usp=sharing)


### DELETE /campanha/:id
```
[imagem da remoção de uma campanha](https://drive.google.com/file/d/1b8nSAeOqhwaYEkdoL4u6_2zshsEUjGSx/view?usp=sharing)

```
