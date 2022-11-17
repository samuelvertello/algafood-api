package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteFormaPagamentoControllerOpenApi {

   @ApiOperation("Lista forma de pagamento por ID de um restaurante")
   public CollectionModel<FormaPagamentoModel> listar(@ApiParam(
         value = "ID de um restaurante", example = "1") Long restauranteId);
         

   @ApiOperation("Desassocia uma forma de pagamento de um restaurante")
   @ApiResponses({
      @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
      @ApiResponse(code = 404, message = "Forma de pagamento ou restaurante não encontrado",
            response = Problem.class)})
   public ResponseEntity<Void> desassociar(@ApiParam(value = "ID de um restaurante", example = "1")  Long restauranteId,
         @ApiParam(value = "ID de uma forma de pagamento", example = "1") Long formaPagamentoId);

   
   @ApiOperation("Associa uma forma de pagamento a um restaurante")
   @ApiResponses({
      @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
      @ApiResponse(code = 404, message = "Forma de pagamento ou restaurante não encontrado",
            response = Problem.class)})
   public ResponseEntity<Void> associar(@ApiParam(value = "ID de um restaurante", example = "1")  Long restauranteId, 
         @ApiParam(value = "ID de uma forma de pagamento", example = "1") Long formaPagamentoId);
   
}
