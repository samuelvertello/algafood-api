package com.algaworks.algafood.api.v1.model.input;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.algaworks.algafood.domain.model.Permissao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GrupoInput {

   @ApiModelProperty(example = "Gerente", required = true)
   @NotBlank
   private String nome;
   
   private List<Permissao> permissoes = new ArrayList<>();
   
}
