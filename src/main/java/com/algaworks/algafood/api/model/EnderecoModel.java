package com.algaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoModel {
   

  @ApiModelProperty(example = "38400-000")
  private String cep;

  @ApiModelProperty(example = "Rua Floriano Peixoto")
  private String logradouro;

  @ApiModelProperty(example = "1325")
  private String numero;

  @ApiModelProperty(example = "Casa")
  private String complemento;

  @ApiModelProperty(example = "Pampulha")
  private String bairro;    

  private CidadeResumoModel cidade;
   
}
