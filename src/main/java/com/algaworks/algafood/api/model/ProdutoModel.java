package com.algaworks.algafood.api.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProdutoModel {

   @ApiModelProperty(example = "1")
   private Long id;

   @ApiModelProperty(example = "Macarrão tailandês")
   private String nome;

   @ApiModelProperty(example = "apimentado")
   private String descricao;

   @ApiModelProperty(example = "35.90")
   private BigDecimal preco;
   
   @ApiModelProperty(example = "true")
   private Boolean ativo;

   
}
