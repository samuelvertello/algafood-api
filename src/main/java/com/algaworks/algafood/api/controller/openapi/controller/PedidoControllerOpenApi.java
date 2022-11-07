package com.algaworks.algafood.api.controller.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.domain.filter.PedidoFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {
   
   @ApiImplicitParams({
   @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por virgula",
         name = "campos", paramType = "query", type = "string")})  
   @ApiOperation("Exibe uma lista de pedidos pesquisados") 
   @ApiResponses({
      @ApiResponse(code = 400, message = "Pedido inválido", response = Problem.class),
      @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)})  
   public Page<PedidoResumoModel> pesquisar(PedidoFilter filtro,@PageableDefault(size = 10) Pageable pageable);


   @ApiOperation("Busca um pedido por código")
   @ApiResponses({
      @ApiResponse(code = 400, message = "Código pedido inválido", response = Problem.class),
      @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)})
   public PedidoModel buscar(@ApiParam(
         value = "código de um pedido", example = "8850e1d7-1039-4506-9bc9-7d219428e175") String codigoPedido);

  
   @ApiOperation("Cadastra um pedido")
   @ApiResponses({@ApiResponse(code = 201, message = "Pedido cadastrado")})
   public PedidoModel adicionar(@ApiParam(
         value = "Representação de um novo pedido", name = "corpo") PedidoInput pedidoInput); 
}
