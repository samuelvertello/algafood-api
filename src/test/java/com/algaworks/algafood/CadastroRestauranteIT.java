package com.algaworks.algafood;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroRestauranteIT {

   private static final String VIOLAO_DE_REGRA_DE_NEGOCIO_PROBLEM_TYPE = "Violação de regra de negócio";

   private static final String DADOS_INVALIDOS_PROBLEM_TITLE = "Dados inválidos ou nulo";

   private static final int RESTAURANTE_ID_INEXISTENTE = 100;

   @LocalServerPort
   private int port;

   @Autowired
   private DatabaseCleaner databaseCleaner;

   @Autowired
   private CozinhaRepository cozinhaRepository;

   @Autowired
   private RestauranteRepository restauranteRepository;

   private String jsonRestauranteCorreto;
   private String jsonRestauranteSemFrete;
   private String jsonRestauranteSemCozinha;
   private String jsonRestauranteComCozinhaInexistente;
   
   
   private Restaurante burguerTopRestaurante;

   @BeforeEach
   public void setUp() {
      RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
      RestAssured.port = port;
      RestAssured.basePath = "/restaurantes";

      jsonRestauranteCorreto = ContentFromResource("/json/correto/restaurante-new-york-barbecue.json");
      jsonRestauranteSemFrete = ContentFromResource("/json/incorreto/restaurante-new-york-barbecue-sem-frete.json");
      jsonRestauranteSemCozinha = ContentFromResource("/json/incorreto/restaurante-new-york-barbecue-sem-cozinha.json");
      jsonRestauranteComCozinhaInexistente = ContentFromResource("/json/incorreto/restaurante-new-york-barbecue-com-cozinha-inexistente.json");
      
      databaseCleaner.clearTables();
      prepararDados();
   }

   @Test
   public void deveRetornarStatus200_QuandoConsultarRestaurante() {
      RestAssured.given()
            .accept(ContentType.JSON)
         .when()
            .get()
         .then()
            .statusCode(HttpStatus.OK.value());
   }

   @Test
   public void deveRetornarStatus201_QuandoCadastrarRestaurante() {
      RestAssured.given()
               .body(jsonRestauranteCorreto)
               .contentType(ContentType.JSON)
               .accept(ContentType.JSON)
            .when()
               .post()
            .then()
               .statusCode(HttpStatus.CREATED.value());
   }

   @Test
   public void deveRetornarStatus400_QuandoCadastrarRestauranteSemTaxa() {
      RestAssured.given()
               .body(jsonRestauranteSemFrete)
               .contentType(ContentType.JSON)
               .accept(ContentType.JSON)
            .when()
               .post()
            .then()
               .statusCode(HttpStatus.BAD_REQUEST.value())
               .body("title", Matchers.equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));
               
   }

   @Test
   public void deveRetornarStatus400_QuandoCadastrarRestauranteSemCozinha() {
      RestAssured.given()
               .body(jsonRestauranteSemCozinha)
               .contentType(ContentType.JSON)
               .accept(ContentType.JSON)
            .when()
               .post()
            .then()
               .statusCode(HttpStatus.BAD_REQUEST.value())
               .body("title", Matchers.equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));
   }

   @Test
   public void deveRetornarStatus400_QuandoCadastrarRestauranteComCozinhaInexistente() {
      RestAssured.given()
               .body(jsonRestauranteComCozinhaInexistente)
               .contentType(ContentType.JSON)
               .accept(ContentType.JSON)
            .when()
               .post()
            .then()
               .statusCode(HttpStatus.BAD_REQUEST.value())
               .body("title", Matchers.equalTo(VIOLAO_DE_REGRA_DE_NEGOCIO_PROBLEM_TYPE));
   }

   @Test
   public void deveRetornarRespostaEStatusCorretos_QuandoConsultarRestauranteExistente() {
      RestAssured.given()
               .pathParam("restauranteId", burguerTopRestaurante.getId())
               .accept(ContentType.JSON)
            .when()
               .get("/{restauranteId}")
            .then()
               .statusCode(HttpStatus.OK.value())
               .body("nome", Matchers.equalTo(burguerTopRestaurante.getNome()));
   }

   @Test
   public void deveRetornarStatus404_QuandoConsultarRestauranteInexistente() {
      RestAssured.given()
               .pathParam("restauranteId", RESTAURANTE_ID_INEXISTENTE)
               .accept(ContentType.JSON)
            .when()
               .get("/{restauranteId}")
            .then()
               .statusCode(HttpStatus.NOT_FOUND.value());
   }

   public static String ContentFromResource(String resourceName) {
		try {
			InputStream stream = ResourceUtils.class.getResourceAsStream(resourceName);
			return StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

   private void prepararDados() {
      Cozinha cozinhaBrasileira = new Cozinha();
      cozinhaBrasileira.setNome("Brasileira");
      cozinhaRepository.save(cozinhaBrasileira);

      Cozinha cozinhaAmericana = new Cozinha();
      cozinhaAmericana.setNome("Americana");
      cozinhaRepository.save(cozinhaAmericana);

      burguerTopRestaurante = new Restaurante();
      burguerTopRestaurante.setNome("Burguer Top");
      burguerTopRestaurante.setTaxaFrete(new BigDecimal(10));
      burguerTopRestaurante.setCozinha(cozinhaAmericana);
      restauranteRepository.save(burguerTopRestaurante);

      Restaurante comidaMineirRestaurante = new Restaurante();
      comidaMineirRestaurante.setNome("Comida Mineira");
      comidaMineirRestaurante.setTaxaFrete(new BigDecimal(10));
      comidaMineirRestaurante.setCozinha(cozinhaBrasileira);
      restauranteRepository.save(comidaMineirRestaurante);
   }
   
}
