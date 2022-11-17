package com.algaworks.algafood.api.v1.controller.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("formasPagamentoModel")
@Data
public class FormasPagamentoModelOpenApi {

   private FormasPagamentoEmbeddedModelOpenApi _embedded;
   private Links _links;



   @ApiModel("formasPagamentoEmbeddedModel")
   @Data
   public class FormasPagamentoEmbeddedModelOpenApi {
      
      private List<FormaPagamentoModel> formasPagamento;
   }
   
}
