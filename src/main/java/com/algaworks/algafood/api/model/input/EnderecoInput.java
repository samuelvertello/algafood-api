package com.algaworks.algafood.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoInput {

   @ApiModelProperty(example = "38400-000", required = true)
   @NotBlank
   private String cep;

   @ApiModelProperty(example = "Rua Floriano Peixoto", required = true)
   @NotBlank
   private String logradouro;

   @ApiModelProperty(example = "1336", required = true)
   @NotBlank
   private String numero;

   @ApiModelProperty(example = "Casa")
   private String complemento;

   @ApiModelProperty(example = "Pampulha", required = true)
   @NotBlank
   private String bairro;    

   @Valid
   @NotNull
   private CidadeIdInput cidade;
   
}
