package com.algaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeResumoModel {

   @ApiModelProperty(example = "3")
   private Long id;

   @ApiModelProperty(example = "Belo Horizonte")
   private String nome;

   @ApiModelProperty(example = "Minas Gerais")
   private String Estado;
   
}
