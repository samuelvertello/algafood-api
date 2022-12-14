package com.algaworks.algafood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
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

import com.algaworks.algafood.api.v1.assembler.CozinhaInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.CozinhaModelAssembler;
import com.algaworks.algafood.api.v1.controller.openapi.controller.CozinhaControllerOpenApi;
import com.algaworks.algafood.api.v1.model.CozinhaModel;
import com.algaworks.algafood.api.v1.model.input.CozinhaInput;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping(path = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController implements CozinhaControllerOpenApi {

   @Autowired
   private CozinhaRepository CozinhaRepository;

   @Autowired
   private CadastroCozinhaService cozinhaService;

   @Autowired
   private CozinhaModelAssembler cozinhaModelAssembler;

   @Autowired
   private CozinhaInputDisassembler cozinhaInputDisassembler;

   @Autowired
   private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;

   @GetMapping
   public PagedModel<CozinhaModel> listar(@PageableDefault(size = 10) Pageable pageable) {
      Page<Cozinha> cozinhasPage = CozinhaRepository.findAll(pageable);

      PagedModel<CozinhaModel> cozinhasPagedModel = pagedResourcesAssembler
            .toModel(cozinhasPage, cozinhaModelAssembler);

      return cozinhasPagedModel;

   }

   @GetMapping("/{cozinhaId}")
   public CozinhaModel buscar(@PathVariable Long cozinhaId) {
      Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);

      return cozinhaModelAssembler.toModel(cozinha);

   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public CozinhaModel cadastrar(@RequestBody @Valid CozinhaInput cozinhaInput) {
      Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
      cozinha = cozinhaService.salvar(cozinha);

      return cozinhaModelAssembler.toModel(cozinha);
   }

   @PutMapping("/{cozinhaId}")
   public CozinhaModel atualizar(@PathVariable Long cozinhaId,
         @RequestBody @Valid CozinhaInput cozinhaInput) {

      Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(cozinhaId);
      cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
      cozinhaAtual = cozinhaService.salvar(cozinhaAtual);

      return cozinhaModelAssembler.toModel(cozinhaAtual);

   }

   @DeleteMapping("/{cozinhaId}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void remover(@PathVariable Long cozinhaId) {
      cozinhaService.excluir(cozinhaId);

   }
}