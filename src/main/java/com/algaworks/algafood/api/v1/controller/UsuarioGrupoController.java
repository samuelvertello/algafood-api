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
import com.algaworks.algafood.api.v1.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.v1.controller.openapi.controller.UsuarioGrupoControllerOpenApi;
import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(value = "/usuarios/{usuarioId}/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {

   @Autowired
   private CadastroUsuarioService usuarioService;

   @Autowired
   private GrupoModelAssembler grupoModelAssembler;

   @Autowired
   private ApiLinks apiLink;

   @Override
   @GetMapping
   public CollectionModel<GrupoModel> listar(@PathVariable Long usuarioId) {
      Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);

      CollectionModel<GrupoModel> usuariogruposModel = grupoModelAssembler
            .toCollectionModel(usuario.getGrupos())
            .add(apiLink.linkToDesassociarUsuarioGrupo(
                  usuarioId, null, "associar"));

      usuariogruposModel.getContent().forEach(usuarioGrupo -> {
         usuarioGrupo.add(apiLink.linkToDesassociarUsuarioGrupo(
               usuarioId, usuarioGrupo.getId(), "desassociar"));
      });

      return usuariogruposModel;

   }

   @Override
   @PutMapping("/{grupoId}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public ResponseEntity<Void> associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
      usuarioService.associarGrupo(usuarioId, grupoId);

      return ResponseEntity.noContent().build();
   }

   @Override
   @DeleteMapping("/{grupoId}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public ResponseEntity<Void> desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
      usuarioService.desassociarGrupo(usuarioId, grupoId);

      return ResponseEntity.noContent().build();
   }

}
