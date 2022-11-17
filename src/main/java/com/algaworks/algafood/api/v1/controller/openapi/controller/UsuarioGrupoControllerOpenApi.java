package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.GrupoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuarios")
public interface UsuarioGrupoControllerOpenApi {


   @ApiOperation("Lista os grupo de um usuário")
   @ApiResponses({
      @ApiResponse(code = 400, message = "ID do usuário inválido", response = Problem.class),
      @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)})
   public CollectionModel<GrupoModel> listar(@ApiParam(example = "1") Long usuarioId);
   

   @ApiOperation("Associa grupo a um usuário")
   @ApiResponses({
      @ApiResponse(code = 204, message = "Usuário associado ao grupo"),
      @ApiResponse(code = 400, message = "ID de um usuário inválido", response = Problem.class),
      @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)})
   public ResponseEntity<Void> associar(@ApiParam(example = "1") Long usuarioId, @ApiParam(example = "1") Long grupoId);

   
   @ApiOperation("Desassocia usuário de um grupo")
   @ApiResponses({
      @ApiResponse(code = 204, message = "Usuário desassociado de um grupo"),
      @ApiResponse(code = 400, message = "ID de usuário inválido", response = Problem.class),
      @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)})
   public ResponseEntity<Void> desassociar(@ApiParam(example = "1") Long usuarioId, @ApiParam(example = "1") Long grupoId);

   

}