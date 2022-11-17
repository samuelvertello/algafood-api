package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.EstadoModel;
import com.algaworks.algafood.api.v1.model.input.EstadoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {

   @ApiOperation("Lista os estado")
   public CollectionModel<EstadoModel> listar();


   @ApiOperation("Buscar um estado por ID")
   @ApiResponses({
         @ApiResponse(code = 400, message = "Estado inválido", response = Problem.class),
         @ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class) })
   public EstadoModel buscar(@ApiParam(
         value = "ID de um estado", example = "1") Long estadoId);


   @ApiOperation("Buscar um estado por ID")
   @ApiResponses({@ApiResponse(code = 201, message = "Estado salvo")})
   public EstadoModel salvar(@ApiParam(
         value = "Representação de um estado", name = "corpo") EstadoInput estadoInput);

   @ApiOperation("Atualiza um estado por ID")
   @ApiResponses({
      @ApiResponse(code = 400, message = "Estado inválido", response = Problem.class),
      @ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)})
   public EstadoModel atualizar(@ApiParam(
      value = "ID de um estado", example = "1") Long estadoId,
      @ApiParam(value = "Representação de um estado", name = "corpo") EstadoInput estadoInput);


   @ApiOperation("Excluir um estado por ID")
   @ApiResponses({
      @ApiResponse(code = 204, message = "Estado excluido"),
      @ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)})
   public void remover(@ApiParam(value = "ID de um estado", example = "7") Long estadoId);
}
