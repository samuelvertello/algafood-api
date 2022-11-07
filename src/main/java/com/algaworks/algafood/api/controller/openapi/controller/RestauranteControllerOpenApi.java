package com.algaworks.algafood.api.controller.openapi.controller;

import java.util.List;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {

   
   @ApiOperation(value = "Lista restaurantes")
   @ApiImplicitParams({@ApiImplicitParam(value = "apenas-nome",
         name = "projecao", paramType = "query", type = "string")})   
   public List<RestauranteModel> listar();


   @ApiOperation(value = "Lista nome dos restaurantes", hidden = true)   
   public List<RestauranteModel> listarApenasNome();


   @ApiOperation("Busca um restaurante por ID")
   @ApiResponses({
      @ApiResponse(code = 400, message = "Restaurante inválido", response = Problem.class),
      @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)}) 
   public RestauranteModel buscar(@ApiParam(value = "ID de um restaurante")Long restauranteId);


   @ApiOperation("adiciona um novo restaurante")
   @ApiResponses({@ApiResponse(code = 201, message = "Restaurante adicionado")})
   public RestauranteModel adicionar(@ApiParam(value = "Representação de um novo restaurante",
         name = "corpo") RestauranteInput restauranteInput);


   @ApiOperation("Atualiza um restaurante por ID")
   @ApiResponses({
      @ApiResponse(code = 200, message = "Restaurante atualizado"),
      @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)})
   public RestauranteModel atualizar(@ApiParam(
            value = "ID de um restaurante", example = "1") Long restauranteId,
            @ApiParam(name = "corpo") RestauranteInput restauranteInput);


   @ApiOperation("Ativa um restaurante por ID")
   @ApiResponses({
      @ApiResponse(code = 400, message = "Restaurante inválido", response = Problem.class),
      @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)})
   public void ativar(@ApiParam(value = "ID de um restaurante", example = "1") Long restauranteId);

   
   @ApiOperation("Inativa um restaurante por ID")
   @ApiResponses({
      @ApiResponse(code = 400, message = "Restaurante inválido", response = Problem.class),
      @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)})
   public void inativar(@ApiParam(value = "ID de um restaurante", example = "1") Long restauranteId);


   @ApiOperation("Ativar varios restaurantes de uma vez")
   @ApiResponses({
      @ApiResponse(code = 400, message = "Restaurante inválido"),
      @ApiResponse(code = 404, message = "Restaurante não encontrado")})
   public void ativarMultiplos(List<Long> restauranteIds);


   @ApiOperation("Inativa varios restaurantes de uma vez")
   @ApiResponses({
      @ApiResponse(code = 400, message = "Restaurante inválido"),
      @ApiResponse(code = 404, message = "Restaurante não encontrado")})
   public void inativarMultiplos(@ApiParam(
         name = "corpo", value = "IDs de restaurantes") List<Long> restauranteIds);


   @ApiOperation("Abre um restaurante por ID")
   @ApiResponses({
      @ApiResponse(code = 400, message = "Restaurante inválido"),
      @ApiResponse(code = 404, message = "Restaurante não encontrado")})
   public void abrir(@ApiParam(
         value = "ID de um restaurante", example = "1") Long restauranteId);


   @ApiOperation("Fecha um restaurante por ID")
   @ApiResponses({
      @ApiResponse(code = 400, message = "Restaurante inválido"),
      @ApiResponse(code = 404, message = "Restaurante não encontrado")})
   public void fechar(@ApiParam(
         value = "ID de um restaurante", example = "1") Long restauranteId);
}
