package com.algaworks.algafood.core.openapi;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Resource;

import org.apache.http.HttpStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.ServletWebRequest;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.controller.openapi.model.CidadesModelOpenApi;
import com.algaworks.algafood.api.v1.controller.openapi.model.CozinhasModelOpenApi;
import com.algaworks.algafood.api.v1.controller.openapi.model.EstadoModelOpenApi;
import com.algaworks.algafood.api.v1.controller.openapi.model.FormasPagamentoModelOpenApi;
import com.algaworks.algafood.api.v1.controller.openapi.model.GruposModelOpenApi;
import com.algaworks.algafood.api.v1.controller.openapi.model.LinksModelOpenApi;
import com.algaworks.algafood.api.v1.controller.openapi.model.PageableModelOpenApi;
import com.algaworks.algafood.api.v1.controller.openapi.model.PedidosModelOpenApi;
import com.algaworks.algafood.api.v1.controller.openapi.model.ProdutosModelOpenApi;
import com.algaworks.algafood.api.v1.controller.openapi.model.RestaurantesModelOpenApi;
import com.algaworks.algafood.api.v1.controller.openapi.model.UsuarioModelOpenApi;
import com.algaworks.algafood.api.v1.model.CidadeModel;
import com.algaworks.algafood.api.v1.model.CozinhaModel;
import com.algaworks.algafood.api.v1.model.EstadoModel;
import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;
import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.api.v1.model.PedidoResumoModel;
import com.algaworks.algafood.api.v1.model.ProdutoModel;
import com.algaworks.algafood.api.v1.model.RestauranteBasicoModel;
import com.algaworks.algafood.api.v1.model.UsuarioModel;
import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

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
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
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
                  .build()
               .useDefaultResponseMessages(false)
               .globalResponses(HttpMethod.GET, globalGetResponseMessages())
               .globalResponses(HttpMethod.POST, globalPostResponseMessages())
               .globalResponses(HttpMethod.PUT, globalPutResponseMessages())
               .globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
               .additionalModels(typeResolver.resolve(Problem.class))
               .ignoredParameterTypes(ServletWebRequest.class, URL.class,
                  URI.class, URLStreamHandler.class, Resource.class,
                  File.class, InputStream.class) 
               .directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
               .directModelSubstitute(Links.class, LinksModelOpenApi.class)                            
               .alternateTypeRules(AlternateTypeRules.newRule(
                     typeResolver.resolve(PagedModel.class, CozinhaModel.class),
                     CozinhasModelOpenApi.class),
               
                  AlternateTypeRules.newRule(typeResolver.resolve(
                        CollectionModel.class, CidadeModel.class),
                        CidadesModelOpenApi.class),

                  AlternateTypeRules.newRule(typeResolver.resolve(
                        CollectionModel.class, EstadoModel.class),
                        EstadoModelOpenApi.class),
                        
                  AlternateTypeRules.newRule(typeResolver.resolve(
                        CollectionModel.class, FormaPagamentoModel.class),
                        FormasPagamentoModelOpenApi.class),
                        
                  AlternateTypeRules.newRule(typeResolver.resolve(
                        CollectionModel.class, GrupoModel.class),
                        GruposModelOpenApi.class),
                     
                  AlternateTypeRules.newRule(typeResolver.resolve(
                        PagedModel.class, PedidoResumoModel.class),
                        PedidosModelOpenApi.class),
                  
                  AlternateTypeRules.newRule(typeResolver.resolve(
                        CollectionModel.class, ProdutoModel.class),
                        ProdutosModelOpenApi.class),
                        
                  AlternateTypeRules.newRule(typeResolver.resolve(
                        CollectionModel.class, UsuarioModel.class),
                        UsuarioModelOpenApi.class),
                        
                  AlternateTypeRules.newRule(typeResolver.resolve(
                        CollectionModel.class, RestauranteBasicoModel.class),
                        RestaurantesModelOpenApi.class))

               .apiInfo(apiInfo())
               .tags(new Tag("Cidades", "Gerencia as cidades"),
                     new Tag("Grupos", "Gerencia os grupos de Usuários"),
                     new Tag("Cozinhas", "Gerencia as cozinhas"),
                     new Tag("Formas Pagamento", "Gerencia as formas de pagamento"),
                     new Tag("Pedidos", "Gerencia os pedidos"),
                     new Tag("Restaurantes", "Gerencia os restaurantes"),
                     new Tag("Estados", "Gerencia os estados"),
                     new Tag("Usuarios", "Gerencia os usuários"),
                     new Tag("Estatisticas", "Gerencia os relátorios de estatisticas de vendas"),
                     new Tag("Permissões", "Gerencia as permissões de um grupo"));
   }

   @Bean
   public JacksonModuleRegistrar springFoxJacksonConfig() {
      return objectMapper -> objectMapper.registerModule(new JavaTimeModule());
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
