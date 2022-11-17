package com.algaworks.algafood.api.v1.controller.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v1.model.RestauranteBasicoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("RestaurantesModel")
@Data
public class RestaurantesModelOpenApi {

   private RestaurantesModelEmbeddedOpenApi _embedded;
   private Links _links;


   @ApiModel("RestaurantesEmbeddedModel")
   @Data
   private class RestaurantesModelEmbeddedOpenApi {

     private List<RestauranteBasicoModel> restaurantes;
   }
   
}
