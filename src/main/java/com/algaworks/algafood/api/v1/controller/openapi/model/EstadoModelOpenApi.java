package com.algaworks.algafood.api.v1.controller.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v1.model.EstadoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel("EstadosModel")
@Setter
@Getter
public class EstadoModelOpenApi {
   private EstadoEmbeddedModelOpenApi _embedded;
   private Links _links;


   
@ApiModel("EstadoEmbeddedModel")
@Data
public class EstadoEmbeddedModelOpenApi {

   private List<EstadoModel> estado;
}
   
}
