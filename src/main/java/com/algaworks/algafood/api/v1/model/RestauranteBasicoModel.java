package com.algaworks.algafood.api.v1.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurantes")
@Setter
@Getter
public class RestauranteBasicoModel extends RepresentationModel<RestauranteBasicoModel>{

   @ApiModelProperty(example = "1")
   private Long id;
   
   @ApiModelProperty(example = "thai Delivery")
   private String nome;
   
   @ApiModelProperty(example = "9.50")
   private BigDecimal taxaFrete;

   private CozinhaModel cozinha;
   
}
