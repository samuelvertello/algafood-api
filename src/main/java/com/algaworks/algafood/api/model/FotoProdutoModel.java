package com.algaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FotoProdutoModel {

   @ApiModelProperty(example = "b8bbd21a-4dd3-4954-835c-3493af2ba6a0_khaosoi-tailandia.jpg")
   private String nomeArquivo;

   @ApiModelProperty(example = "khaosoi super saboroso")
   private String descricao;

   @ApiModelProperty(example = "image/jpeg")
   private String contentType;

   @ApiModelProperty(example = "50975")
   private Long tamanho;
   
}
