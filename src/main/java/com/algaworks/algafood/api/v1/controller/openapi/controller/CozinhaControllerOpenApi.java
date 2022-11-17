package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.CozinhaModel;
import com.algaworks.algafood.api.v1.model.input.CozinhaInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {

   @ApiOperation("Lista as cozinhas")
   public PagedModel<CozinhaModel> listar(Pageable pageable);
    
   @ApiOperation("Busca uma cozinha por ID")
   @ApiResponses({
      @ApiResponse(code = 400, message = "ID de cozinha inválido", response = Problem.class),
      @ApiResponse(code = 404, message = "ID de cozinha não encontrado", response = Problem.class)})      
   public CozinhaModel buscar(@ApiParam(value = "ID de uma cozinha") Long cozinhaId);
  
   @ApiOperation("Cadastra uma cozinha")
   @ApiResponses({
      @ApiResponse(code = 201, message = "Cozinha cadastrada")})
   public CozinhaModel cadastrar(@ApiParam(
            value = "Representação de uma nova cozinha", name = "corpo") CozinhaInput cozinhaInput);
  
   @ApiOperation("Atualiza uma cozinha por ID")
   @ApiResponses({
      @ApiResponse(code = 200, message = "Atualiza uma Cozinha")})
   public CozinhaModel atualizar(@ApiParam(value = "ID de uma cozinha") Long cozinhaId,
         @ApiParam(value = "Representação de uma cozinha com dados atualizados")
         CozinhaInput cozinhaInput);
   
   @ApiOperation("Exclui uma cozinha por ID")
   @ApiResponses({
      @ApiResponse(code = 204, message = "Cozinha excluida"),
      @ApiResponse(code = 404, message = "Cozinha nao encontrado", response = Problem.class)})
   public void remover(@ApiParam(value = "ID de uma cozinha") Long cozinhaId);

   
}
