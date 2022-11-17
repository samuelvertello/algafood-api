package com.algaworks.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.ApiLinks;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

   @Autowired
   private ApiLinks apiLink;

   @GetMapping
   public RootrEntryPointModel root() {
      var rootrEntryPointModel = new RootrEntryPointModel();

      rootrEntryPointModel.add(apiLink.linkToCozinhas("cozinhas"));
      rootrEntryPointModel.add(apiLink.linkToPedidos("pedidos"));
      rootrEntryPointModel.add(apiLink.linkToRestaurante("restaurantes"));
      rootrEntryPointModel.add(apiLink.linkToGrupo("grupos"));
      rootrEntryPointModel.add(apiLink.linkToUsuario("usuarios"));
      rootrEntryPointModel.add(apiLink.linkToPermissoes("permissoes"));
      rootrEntryPointModel.add(apiLink.linkToFormaPagamento("formas-pagamento"));
      rootrEntryPointModel.add(apiLink.linkToEstado("estados"));
      rootrEntryPointModel.add(apiLink.linkToCidade("cidades"));
      rootrEntryPointModel.add(apiLink.linkToEstatisticas("Estatisticas-vendas"));


      return rootrEntryPointModel;

   }
   
   
   private static class RootrEntryPointModel extends RepresentationModel<RootrEntryPointModel> {

   }
}
