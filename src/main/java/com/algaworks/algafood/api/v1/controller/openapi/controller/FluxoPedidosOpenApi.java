package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface FluxoPedidosOpenApi {

   @ApiOperation("Confirma um pedido por código")
   @ApiResponses({
         @ApiResponse(code = 400, message = "Pedido inválido", response = Problem.class),
         @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class) })
   ResponseEntity<Void> confirmar(@ApiParam(value = "ID de um pedido", example = "5") String codigoPedido);

   @ApiOperation("Entrega um pedido por código")
   @ApiResponses({
         @ApiResponse(code = 400, message = "Pedido inválido", response = Problem.class),
         @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class) })
   ResponseEntity<Void> entregar(@ApiParam(value = "ID de um pedido", example = "10") String codigoPedido);

   @ApiOperation("Cancela um pedido por código")
   @ApiResponses({
         @ApiResponse(code = 400, message = "Pedido inválido", response = Problem.class),
         @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class) })
   ResponseEntity<Void> cancelamento(@ApiParam(value = "ID de um pedido", example = "15") String codigoPedido);

}