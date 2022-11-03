package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(value = "/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

   @Autowired
   private CadastroUsuarioService usuarioService;

   @Autowired
   private GrupoModelAssembler grupoModelAssembler;

   @GetMapping
   public List<GrupoModel> listar(@PathVariable Long usuarioId) {
      Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);

      return grupoModelAssembler.toCollectionModel(usuario.getGrupos());
   }

   @PutMapping("/{grupoId}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
      usuarioService.associarGrupo(usuarioId, grupoId);
   }

   @DeleteMapping("/{grupoId}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
      usuarioService.desassociarGrupo(usuarioId, grupoId);
   }

   
}