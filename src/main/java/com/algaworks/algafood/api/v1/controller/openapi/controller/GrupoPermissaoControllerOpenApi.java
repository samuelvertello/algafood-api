package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.PermissaoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoPermissaoControllerOpenApi {


   @ApiOperation("Lista as permissões de um grupo")
   @ApiResponses({
      @ApiResponse(code = 400, message = "Grupo inválido", response = Problem.class),
      @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)})
   public CollectionModel<PermissaoModel> listar(@ApiParam(example = "1") Long grupoId);


   @ApiOperation("Associa uma permissão a um grupo por ID")
   @ApiResponses({
      @ApiResponse(code = 204, message = "Permissão associada ao grupo"),
      @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)})
   public ResponseEntity<Void> associar(@ApiParam(example = "1") Long grupoId, @ApiParam(example = "3") Long permissaoId);


   @ApiOperation("Desassocia uma permissão de um grupo")
   @ApiResponses({
      @ApiResponse(code = 204, message = "Permissão desassociada com sucesso"),
      @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)})
   public ResponseEntity<Void> desassociar(@ApiParam(example = "1") Long grupoId, @ApiParam(example = "3") Long permissaoId);
      
   
}
