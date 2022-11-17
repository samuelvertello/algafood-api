package com.algaworks.algafood.api.v1.controller.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v1.model.UsuarioModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;


@ApiModel("UsuariosModel")
@Data
public class UsuarioModelOpenApi {

   private UsuarioModelEmbeddedOpenApi _embedded;
   private Links _links;



   @ApiModel("UsuariosEmbeddedModel")
   @Data
   public class UsuarioModelEmbeddedOpenApi {

      private List<UsuarioModel> usuarios;
   }
   
}
