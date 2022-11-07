package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PermissaoInput {

   @ApiModelProperty(example = "Controle total", required = true)
   @NotBlank
   private String nome;

   @ApiModelProperty(example = "da acesso total a plataforma para leitura, inserção e exclusao", required = true)
   @NotBlank
   private String descricao;
   
}
