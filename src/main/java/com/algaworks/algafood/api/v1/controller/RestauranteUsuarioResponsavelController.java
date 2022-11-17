package com.algaworks.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.ApiLinks;
import com.algaworks.algafood.api.v1.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.api.v1.controller.openapi.controller.RestauranteUsuarioResponsavelControllerOpenApi;
import com.algaworks.algafood.api.v1.model.UsuarioModel;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/responsavel", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioResponsavelController implements RestauranteUsuarioResponsavelControllerOpenApi {

   @Autowired
   private CadastroRestauranteService restauranteService;

   @Autowired
   private UsuarioModelAssembler usuarioModelAssembler;

   @Autowired
   private ApiLinks apiLink;

   @GetMapping
   public CollectionModel<UsuarioModel> listar(@PathVariable Long restauranteId) {
      
      Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

      CollectionModel<UsuarioModel> usuariosResponsaveisModel = usuarioModelAssembler.toCollectionModel(restaurante.getUsuarioResposavel())
            .removeLinks()
            .add(apiLink.linkToResponsaveisRestaurante(restauranteId))
            .add(apiLink.linkToResponsaveisRestauranteAssociacao(
                  restauranteId, restauranteId, "associar"));

      usuariosResponsaveisModel.getContent().forEach(usuarioResponsavel -> {
         usuarioResponsavel.add(apiLink.linkToResponsaveisRestauranteDesassociacao(
               restauranteId, restauranteId, "desassociar"));
      });

      return usuariosResponsaveisModel;
   }

   @PutMapping("/{usuarioId}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId){
      restauranteService.associarUsuarioResponsavel(restauranteId, usuarioId);

      return ResponseEntity.noContent().build();
   }

   @DeleteMapping("/{usuarioId}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
      restauranteService.desassociarUsuarioResponsavel(restauranteId, usuarioId);

      return ResponseEntity.noContent().build();
   }
}
