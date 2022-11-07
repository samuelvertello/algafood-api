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

import com.algaworks.algafood.api.assembler.RestauranteInputDisassembler;
import com.algaworks.algafood.api.assembler.RestauranteModelAssembler;
import com.algaworks.algafood.api.controller.openapi.controller.RestauranteControllerOpenApi;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.api.model.view.RestauranteView;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController implements RestauranteControllerOpenApi{

   @Autowired
   private RestauranteRepository restauranteRepository;

   @Autowired
   private CadastroRestauranteService restauranteService;

   @Autowired
   private RestauranteModelAssembler restauranteModelAssembler;

   @Autowired
   private RestauranteInputDisassembler restauranteInputDisassembler;

   @ApiOperation(value = "Lista restaurantes")
   @ApiImplicitParams({@ApiImplicitParam(value = "apenas-nome",
         name = "projecao", paramType = "query", type = "string")})
   @JsonView(RestauranteView.Resumo.class)
   @GetMapping
   public List<RestauranteModel> listar() {
      return restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
      
   }  

   @ApiOperation(value = "Lista restaurantes", hidden = true)
   @JsonView(RestauranteView.ApenasNome.class)
   @GetMapping(params = "projecao=apenas-nome")
   public List<RestauranteModel> listarApenasNome() {
      return listar();
   }   

   @GetMapping("/{restauranteId}")
   public RestauranteModel buscar(@PathVariable Long restauranteId) {
      Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

      return restauranteModelAssembler.toModel(restaurante);

   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
      try {

         Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);

         return restauranteModelAssembler.toModel(restauranteService.salvar(restaurante));

      } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
         throw new NegocioException(e.getMessage(), e);

      }
   }

   @PutMapping("/{restauranteId}")
   public RestauranteModel atualizar(@PathVariable Long restauranteId,
         @RequestBody @Valid RestauranteInput restauranteInput) {

      try {

         Restaurante restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);

         restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);

         return restauranteModelAssembler.toModel(restauranteService.salvar(restauranteAtual));
      } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
         throw new NegocioException(e.getMessage(), e);
      }

   }

   @PutMapping("/{restauranteId}/ativo")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void ativar(@PathVariable Long restauranteId) {
      restauranteService.ativar(restauranteId);

   }

   
   @DeleteMapping("/{restauranteId}/ativo")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void inativar(@PathVariable Long restauranteId) {
      restauranteService.inativar(restauranteId);

   }

   @PutMapping("/ativacoes")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {

      try {
         restauranteService.ativarVariosRestaurantes(restauranteIds);
      } catch (RestauranteNaoEncontradoException e) {
         throw new NegocioException(e.getMessage(), e);
      }
   }

   @DeleteMapping("/ativacoes")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {

      try {
         restauranteService.inativarVariosRestaurantes(restauranteIds);
      } catch (RestauranteNaoEncontradoException e) {
         throw new NegocioException(e.getMessage(), e);
      }
   }

   @PutMapping("/{restauranteId}/abertura")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void abrir(@PathVariable Long restauranteId) {
      restauranteService.abrir(restauranteId);
   }

   @PutMapping("/{restauranteId}/fechamento")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void fechar(@PathVariable Long restauranteId) {
      restauranteService.fechar(restauranteId);
   }


   
}