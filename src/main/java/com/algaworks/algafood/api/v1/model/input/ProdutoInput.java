package com.algaworks.algafood.api.v1.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProdutoInput {

   @ApiModelProperty(example = "Camarão tailandês", required = true)
   @NotBlank
   private String nome;

   @ApiModelProperty(example = "Camarão ao molho shoyu", required = true)
   @NotBlank
   private String descricao;

   @ApiModelProperty(example = "87.00", required = true)
   @NotNull
   private BigDecimal preco;

   @ApiModelProperty(example = "true")
   @NotNull
   private Boolean ativo;


   
}
