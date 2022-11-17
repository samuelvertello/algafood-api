package com.algaworks.algafood.api.v1.controller.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v1.model.PedidoResumoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;


@ApiModel("PedidosModel")
@Data
public class PedidosModelOpenApi {

   private PedidosResumoModelEmbeddedOpenApi _embedded;
   private Links _links;
   private PageModelOpenApi page;


   @ApiModel("PedidosMResumoModelEmbedded")
   @Data
   public class PedidosResumoModelEmbeddedOpenApi {

     private List<PedidoResumoModel> pedidos;
   }
   
}
   

