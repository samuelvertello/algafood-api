package com.algaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PermissaoModel {

   @ApiModelProperty(example = "1")
   private Long id;

   @ApiModelProperty(example = "leitura")
   private String nome;

   @ApiModelProperty(example = "Usuários com essa permissão podem apenas fazer leitura dos dados")
   private String descricao;
   
}
