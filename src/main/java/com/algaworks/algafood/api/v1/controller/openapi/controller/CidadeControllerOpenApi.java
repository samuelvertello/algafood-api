package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.CidadeModel;
import com.algaworks.algafood.api.v1.model.input.CidadeInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {  

   @ApiOperation("Lista as cidades")   
   public CollectionModel<CidadeModel> listar();

   @ApiOperation("busca uma cidade por ID")
   @ApiResponses({
      @ApiResponse(code = 400, message = "ID da cidade inválido", response = Problem.class),
      @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)})   
   public CidadeModel buscar(@ApiParam(value = "ID de uma cidade") Long cidadeId);

   @ApiOperation("Cadastra uma cidade")
   @ApiResponses({@ApiResponse(code = 201, message = "Cidade cadastrada")})   
   public CidadeModel adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade") 
         CidadeInput cidadeInput);

   @ApiOperation("Atualiza uma cidade por ID")
   @ApiResponses({@ApiResponse(code = 200, message = "Cidade atualizada")})   
   public CidadeModel atualizar(@ApiParam("ID de uma cidade") Long cidadeId,
         @ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados") 
          CidadeInput cidadeInput);

      
   @ApiOperation("Exclui uma cidade por ID")
   @ApiResponses({
      @ApiResponse(code = 204, message = "Cidade excluida"),
      @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)})      
   public void excluir(@ApiParam(value = "ID de uma cidade") Long cidadeId);
   
}
