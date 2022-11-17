package com.algaworks.algafood.api.v1.controller.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v1.model.GrupoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("GruposModel")
@Data
public class GruposModelOpenApi {
   
   private GruposModelEmbeddedOpenApi _embedded;
   private Links _links;


   @ApiModel("gruposEmbeddedModel")
   @Data
   public class GruposModelEmbeddedOpenApi {

      private List<GrupoModel> grupos;
   }
   
}
