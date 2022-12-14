package com.algaworks.algafood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.assembler.UsuarioInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.api.v1.controller.openapi.controller.UsuarioControllerOpenApi;
import com.algaworks.algafood.api.v1.model.UsuarioModel;
import com.algaworks.algafood.api.v1.model.input.SenhaInput;
import com.algaworks.algafood.api.v1.model.input.UsuarioComSenhaInput;
import com.algaworks.algafood.api.v1.model.input.UsuarioInput;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;
import com.algaworks.algafood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi {

   @Autowired
   private UsuarioRepository usuarioRepository;

   @Autowired
   private UsuarioModelAssembler usuarioModelAssembler;

   @Autowired
   private UsuarioInputDisassembler usuarioInputDisassembler;

   @Autowired
   private CadastroUsuarioService usuarioService;

   @GetMapping
   public CollectionModel<UsuarioModel> listar() {

      List<Usuario> todosUsuarios = usuarioRepository.findAll();

      return usuarioModelAssembler.toCollectionModel(todosUsuarios);
   }

   @GetMapping("/{usuarioId}")
   public UsuarioModel buscar(@PathVariable Long usuarioId) {
      Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);

      return usuarioModelAssembler.toModel(usuario);
   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public UsuarioModel salvar(@RequestBody @Valid UsuarioComSenhaInput usuarioInput) {

      Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);

      usuario = usuarioService.salvar(usuario);

      return usuarioModelAssembler.toModel(usuario);

   }

   @PutMapping("/{usuarioId}")
   public UsuarioModel atualizar(@PathVariable Long usuarioId, 
         @RequestBody @Valid UsuarioInput usuarioInput) {

      Usuario usuarioAtual = usuarioService.buscarOuFalhar(usuarioId);

      usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);

      usuarioAtual = usuarioService.salvar(usuarioAtual);

      return usuarioModelAssembler.toModel(usuarioAtual);

   }

   @PutMapping("/{usuarioId}/senha")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
      usuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
   }

   @DeleteMapping("/{usuarioId}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void remover(@PathVariable Long usuarioId) {
      usuarioService.excluir(usuarioId);

   }


   
}
