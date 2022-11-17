package com.algaworks.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Relation(collectionRelation = "permissoes")
@Setter
@Getter
public class PermissaoModel extends RepresentationModel<PermissaoModel>  {

   @ApiModelProperty(example = "1")
   private Long id;

   @ApiModelProperty(example = "leitura")
   private String nome;

   @ApiModelProperty(example = "Usuários com essa permissão podem apenas fazer leitura dos dados")
   private String descricao;
   
}
