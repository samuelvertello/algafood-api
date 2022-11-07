package com.algaworks.algafood.api.controller.openapi.controller;

import java.util.List;

import com.algaworks.algafood.api.model.UsuarioModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(tags = "Restaurantes")
public interface RestauranteUsuarioResponsavelControllerOpenApi {

   @ApiOperation("Lista usuários responsáveis de um restaurante")
   public List<UsuarioModel> listar(@ApiParam(value = "ID de um restaurante",
         example = "1") Long restauranteId);


   @ApiOperation("Associa um usuário responsável a um restaurante")
   @ApiResponses({
      @ApiResponse(code = 204, message = "Usuário responsável associado a um restaurante"),
      @ApiResponse(code = 404, message = "Usuário e/ou restaurante não encontrado")})
   public void associar(@ApiParam(value = "ID de um restaurante", example = "1") Long restauranteId,
         @ApiParam(value = "ID de um usuário", example = "3")  Long usuarioId);

   @ApiOperation("Desassocia um usuário responsavel de um restaurante")
   @ApiResponses({
      @ApiResponse(code = 204, message = "Usuário responsável desassociado de um restaurante"),
      @ApiResponse(code = 404, message = "Usuário e/ou restaurante não encontrado")})
   public void desassociar(@ApiParam(value = "ID de um restaurante", example = "1") Long restauranteId,
         @ApiParam(value = "ID de um usuário", example = "1") Long usuarioId);
   
}
