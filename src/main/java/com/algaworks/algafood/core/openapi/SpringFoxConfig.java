package com.algaworks.algafood.core.openapi;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.apache.http.HttpStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.ServletWebRequest;

import com.algaworks.algafood.api.controller.openapi.model.CozinhasModelOpenApi;
import com.algaworks.algafood.api.controller.openapi.model.PageableModelOpenApi;
import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RepresentationBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

   @Bean
   public Docket apiDocket() {

      var typeResolver = new TypeResolver();

      return new Docket(DocumentationType.SWAGGER_2)
            .select()               
               .apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
               .paths(PathSelectors.any())
            // .path(PathSelectors.ant("/restaurantes/*"))
               .build()
               .useDefaultResponseMessages(false)
               .globalResponses(HttpMethod.GET, globalGetResponseMessages())
               .globalResponses(HttpMethod.POST, globalPostResponseMessages())
               .globalResponses(HttpMethod.PUT, globalPutResponseMessages())
               .globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
               .additionalModels(typeResolver.resolve(Problem.class))
               .directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
               .ignoredParameterTypes(ServletWebRequest.class)               
               .alternateTypeRules(AlternateTypeRules.newRule(
                     typeResolver.resolve(Page.class, CozinhaModel.class),
                     CozinhasModelOpenApi.class))
               .apiInfo(apiInfo())
               .tags(new Tag("Cidades", "Gerencia as cidades"),
                     new Tag("Grupos", "Gerencia os grupos de Usuários"),
                     new Tag("Cozinhas", "Gerencia as cozinhas"),
                     new Tag("Formas Pagamento", "Gerencia as formas de pagamento"),
                     new Tag("Pedidos", "Gerencia os pedidos"),
                     new Tag("Restaurantes", "Gerencia os restaurantes"),
                     new Tag("Estados", "Gerencia os estados"));
   }

   private List<Response> globalGetResponseMessages() {
      return Arrays.asList(     
            new ResponseBuilder()
               .code(String.valueOf(HttpStatus.SC_BAD_REQUEST))
               .description("Requisição inválida (erro do consumidor da API)")   
               .representation(MediaType.APPLICATION_JSON)
               .apply(getProblemaModelReference())            
               .build(),      
            new ResponseBuilder()
               .code(String.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR))
               .description("Erro interno do servidor")
               .representation(MediaType.APPLICATION_JSON)
               .apply(getProblemaModelReference())
               .build(),
            new ResponseBuilder()
               .code(String.valueOf(HttpStatus.SC_NOT_ACCEPTABLE))
               .description("Recurso não possui representação que poderia ser aceita pelo consumidor")
               .build(),            
            new ResponseBuilder()
               .code(String.valueOf(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE))
               .description("Requisição recusada pois está em formato nao suportado")
               .representation(MediaType.APPLICATION_JSON)
               .apply(getProblemaModelReference())
               .build());
   } 

   private List<Response> globalPostResponseMessages() {
      return Arrays.asList(           
            new ResponseBuilder()
               .code(String.valueOf(HttpStatus.SC_BAD_REQUEST))
               .description("Requisição inválida (erro do consumidor da API)")
               .representation(MediaType.APPLICATION_JSON)
               .apply(getProblemaModelReference())
               .build(),
            new ResponseBuilder()
               .code(String.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR))
               .description("Requisição recusada, erro interno no servidor")
               .representation(MediaType.APPLICATION_JSON)
               .apply(getProblemaModelReference())
               .build(),
            new ResponseBuilder()
               .code(String.valueOf(HttpStatus.SC_NOT_ACCEPTABLE))
               .description("Recurso não possui representação que poderia ser aceita pelo consumidor")
               .build(),
            new ResponseBuilder()
               .code(String.valueOf(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE))
               .description("Requisição recusada, pois está em formato nao suportado")
               .representation(MediaType.APPLICATION_JSON)
               .apply(getProblemaModelReference())
               .build());
   } 

   private List<Response> globalPutResponseMessages() {
      return Arrays.asList(           
            new ResponseBuilder()
               .code(String.valueOf(HttpStatus.SC_BAD_REQUEST))
               .description("Requisição inválida (erro do consumidor da API)")
               .representation(MediaType.APPLICATION_JSON)
               .apply(getProblemaModelReference())
               .build(),
            new ResponseBuilder()
               .code(String.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR))
               .description("Requisição recusada, erro interno no servidor")
               .representation(MediaType.APPLICATION_JSON)
               .apply(getProblemaModelReference())
               .build(),
            new ResponseBuilder()
               .code(String.valueOf(HttpStatus.SC_NOT_ACCEPTABLE))
               .description("Recurso não possui representação que poderia ser aceita pelo consumidor")
               .build(),
            new ResponseBuilder()
               .code(String.valueOf(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE))
               .description("Requisição recusada, pois está em formato nao suportado")
               .representation(MediaType.APPLICATION_JSON)
               .apply(getProblemaModelReference())
               .build());
   } 

   private List<Response> globalDeleteResponseMessages() {
      return Arrays.asList(           
            new ResponseBuilder()
               .code(String.valueOf(HttpStatus.SC_BAD_REQUEST))
               .description("Requisição inválida (erro do consumidor da API)")
               .representation(MediaType.APPLICATION_JSON)
               .apply(getProblemaModelReference())
               .build(),
            new ResponseBuilder()
               .code(String.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR))
               .description("Requisição recusada, erro interno no servidor")
               .representation(MediaType.APPLICATION_JSON)
               .apply(getProblemaModelReference())
               .build());
   }    

   private Consumer<RepresentationBuilder> getProblemaModelReference() {
      return r -> r.model(m -> m.name("Problema")
            .referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
                  q -> q.name("Problema").namespace("com.algaworks.algafood.api.exceptionhandler")))));
   }

   private ApiInfo apiInfo() {
      return new ApiInfoBuilder()
            .title("Algafood API")
            .description("API aberta para clientes e restaurantes")
            .version("1.0.0")
            .contact(new Contact("Algafood", "https://www.algafood.com", "samuell.vertello@gmail.com"))
            .build();
   }
   
   
}
