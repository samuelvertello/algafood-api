package com.algaworks.algafood.api.model.input;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.algaworks.algafood.domain.model.Permissao;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GrupoInput {

   @NotBlank
   private String nome;

   private List<Permissao> permissoes = new ArrayList<>();
   
}
