package com.algaworks.algafood.api.model;

import java.math.BigDecimal;

import com.algaworks.algafood.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteModel {

   @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
   @ApiModelProperty(example = "1")
   private Long id;

   @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
   @ApiModelProperty(example = "Thai Gourmet")
   private String nome;

   @JsonView(RestauranteView.Resumo.class)
   @ApiModelProperty(example = "12.50")
   private BigDecimal taxaFrete;

   @JsonView(RestauranteView.Resumo.class)
   private CozinhaModel cozinha;

   @ApiModelProperty(example = "true")
   private Boolean ativo;

   @ApiModelProperty(example = "true")
   private Boolean aberto;

   private EnderecoModel endereco;

}
