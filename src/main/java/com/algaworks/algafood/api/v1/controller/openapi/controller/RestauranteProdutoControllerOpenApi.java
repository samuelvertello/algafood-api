package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.ProdutoModel;
import com.algaworks.algafood.api.v1.model.input.ProdutoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Produtos")
public interface RestauranteProdutoControllerOpenApi {

   @ApiOperation("Lista produtos de um restaurante")
   public CollectionModel<ProdutoModel> listar(
         @ApiParam(value = "ID de um restaurante", example = "1") Long restauranteId,

         @ApiParam(value = "Informa se deve ou não Incluir produtos inativos",
               example = "true", defaultValue = "false") 
            Boolean incluirInativos);

   @ApiOperation("Busca um produto especifico de um restaurante")
   @ApiResponses({
         @ApiResponse(code = 400, message = "Produto e/ou restaurante inválido", response = Problem.class),
         @ApiResponse(code = 404, message = "Produto e/ou restaurante não encontrado", response = Problem.class) })
   public ProdutoModel buscar(@ApiParam(value = "ID de um restaurante", example = "1") Long restauranteId,
         @ApiParam(value = "ID de um produto", example = "1") Long produtoId);

   @ApiOperation("Adicionar um produto a um restaurante")
   @ApiResponses({
         @ApiResponse(code = 201, message = "Produto adicionado ao restaurante"),
         @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class) })
   public ProdutoModel adicionar(@ApiParam(value = "ID de um restaurante", example = "1") Long restauranteId,
         @ApiParam(value = "ID de um produto", example = "1") ProdutoInput produtoInput);

   @ApiOperation("Atualiza um produto de um restaurante")
   @ApiResponses({
         @ApiResponse(code = 200, message = "Produto atualizado"),
         @ApiResponse(code = 404, message = "Produto e/ou restaurante não encontrado", response = Problem.class) })
   public ProdutoModel atualizar(@ApiParam(value = "ID de um restaurante", example = "1") Long restauranteId,
         @ApiParam(value = "ID de um produto", example = "1") Long produtoId,
         @ApiParam(value = "Representação para atualização de um produto") ProdutoInput produtoInput);

}
