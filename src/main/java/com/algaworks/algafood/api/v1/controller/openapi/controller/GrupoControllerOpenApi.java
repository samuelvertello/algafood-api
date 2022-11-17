package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.api.v1.model.input.GrupoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

   @ApiOperation("Lista os grupos")
   public CollectionModel<GrupoModel> listar();
   
   @ApiOperation("Busca um grupo por ID")
   @ApiResponses({
      @ApiResponse(code = 400, message = "ID informado inválido", response = Problem.class),
      @ApiResponse(code = 404, message = "Grupo informado não encontrado", response = Problem.class)})
   public GrupoModel buscar(@ApiParam(
            value = "ID de um grupo", example = "1") Long grupoId);
   
   @ApiOperation("cadastra um novo grupo")
   @ApiResponses({   
      @ApiResponse(code = 201, message = "Grupo cadastrado")})
   public GrupoModel salvar(
         @ApiParam(name = "corpo", value = "Representação de um novo grupo")
         GrupoInput grupoInput);
   
   @ApiOperation("Atualiza um grupo por ID")
   @ApiResponses({
      @ApiResponse(code = 200, message = "Grupo atualizado")})
   public GrupoModel atualizar(
            @ApiParam(value = "ID de um grupo", example = "1") Long grupoId,
            @ApiParam(name = "corpo", value = "Representação de um grupo com os novos dados")
             GrupoInput grupoInput);
  
   @ApiOperation("Exclui um grupo por ID")
   @ApiResponses({
      @ApiResponse(code = 204, message = "Grupo excluido", response = Problem.class),
      @ApiResponse(code = 404, message = "Grupo informado não encontrado", response = Problem.class)})
   public void remover(@ApiParam(value = "ID de um grupo", example = "1") Long grupoId);
   
}
