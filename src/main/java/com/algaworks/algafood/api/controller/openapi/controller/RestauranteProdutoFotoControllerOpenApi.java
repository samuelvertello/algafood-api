package com.algaworks.algafood.api.controller.openapi.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.FotoProdutoModel;
import com.algaworks.algafood.api.model.input.FotoProdutoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Produtos")
public interface RestauranteProdutoFotoControllerOpenApi {

      
   @ApiOperation(value = "Representação de uma foto", produces = "application/json, image/jpeg, image/png")   
   @ApiResponses({
      @ApiResponse(code = 400, message = "Restaurante e/ou Produto inválido", response = Problem.class),
      @ApiResponse(code = 404, message = "Foto do produto não encontrado", response = Problem.class)})
   public FotoProdutoModel buscar(@ApiParam(value = "ID de um restaurante",
         example = "1") Long restauranteId, 
         @ApiParam(value = "ID de um produto", example = "1")Long produtoId);


   @ApiOperation(value = "Representação de uma foto", hidden = true)   
   public ResponseEntity<?> servirFoto(Long restauranteId, Long produtoId, String acceptHeader) 
         throws HttpMediaTypeNotAcceptableException;



   @ApiOperation(value = "Atualiza uma foto de um produto"/* , produces = "multipart/form-data" */)
   @ApiResponses({
      @ApiResponse(code = 200, message = "Foto do produto atualizada"),
      @ApiResponse(code = 400, message = "Restaraurante e/ou Produto não encontrado", response = Problem.class),
      @ApiResponse(code = 404, message = "Foto do produto não encontrado", response = Problem.class)})
   public FotoProdutoModel atualizarFoto(
         @ApiParam(value = "ID do restaurante", example = "1") Long restauranteId,
         @ApiParam(value = "ID do produto", example = "1") long produtoId, 

         FotoProdutoInput fotoProdutoInput,
         
         @ApiParam(value = "Arquivo da foto do produto (máximo 1000kb, apenas JPG e PNG)", 
         required = true) MultipartFile arquivo) throws IOException;



   @ApiOperation("Exclui uma foto de um produto")
   @ApiResponses({
      @ApiResponse(code = 204, message = "Foto do produto excluida"),
      @ApiResponse(code = 400, message = "Restaurante e/ou Produto inválido"),
      @ApiResponse(code = 404, message = "Foto não encontrada", response = Problem.class)})
   public void excluirFoto(@ApiParam(value = "ID de um restaurante",
         example = "1") Long restauranteId,
         @ApiParam(value = "ID de um produto", example = "1") Long produtoId);
   

}
