package com.algaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioModel {
   
   @ApiModelProperty(example = "15")
   private Long id;

   @ApiModelProperty(example = "João da Silva")
   private String nome;

   @ApiModelProperty(example = "joao.silva@algafood.com")
   private String email;
      
   
}
