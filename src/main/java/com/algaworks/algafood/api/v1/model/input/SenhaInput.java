package com.algaworks.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SenhaInput {

   @ApiModelProperty(example = "joao12345", required = true)
   @NotBlank
   private String senhaAtual;

   @ApiModelProperty(example = "joao23564", required = true)
   @NotBlank
   private String novaSenha;
   
}
