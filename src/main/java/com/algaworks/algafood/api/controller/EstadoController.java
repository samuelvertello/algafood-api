package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.algaworks.algafood.api.assembler.EstadoInputDisassembler;
import com.algaworks.algafood.api.assembler.EstadoModelAssembler;
import com.algaworks.algafood.api.controller.openapi.controller.EstadoControllerOpenApi;
import com.algaworks.algafood.api.model.EstadoModel;
import com.algaworks.algafood.api.model.input.EstadoInput;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping(path = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController implements EstadoControllerOpenApi {

   @Autowired
   private EstadoRepository estadoRepository;

   @Autowired
   private CadastroEstadoService estadoService;

   @Autowired
   private EstadoModelAssembler estadoModelAssembler;

   @Autowired
   private EstadoInputDisassembler estadoInputDisassembler;

   @GetMapping
   public List<EstadoModel> listar() {
      List<Estado> todosEstados = estadoRepository.findAll();

      return estadoModelAssembler.toCollectionModel(todosEstados);
   }

   @GetMapping("/{estadoId}")
   public EstadoModel buscar(@PathVariable Long estadoId) {
      Estado estado = estadoService.buscarOuFalhar(estadoId);

      return estadoModelAssembler.toModel(estado);

   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public EstadoModel salvar(@RequestBody @Valid EstadoInput estadoInput) {
      Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);

      estado = estadoService.salvar(estado);

      return estadoModelAssembler.toModel(estado);

   }

   @PutMapping("/{estadoId}")
   public EstadoModel atualizar(@PathVariable Long estadoId,
         @RequestBody @Valid EstadoInput estadoInput) {

      Estado estadoAtual = estadoService.buscarOuFalhar(estadoId);

      estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);

      estadoAtual = estadoService.salvar(estadoAtual);

      return estadoModelAssembler.toModel(estadoAtual);

   }

   @DeleteMapping("/{estadoId}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void remover(@PathVariable Long estadoId) {
      estadoService.excluir(estadoId);

   }

}
