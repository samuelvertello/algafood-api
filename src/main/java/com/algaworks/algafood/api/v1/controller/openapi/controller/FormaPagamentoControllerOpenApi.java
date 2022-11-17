package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.controller.openapi.model.FormasPagamentoModelOpenApi;
import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;
import com.algaworks.algafood.api.v1.model.input.FormaPagamentoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Formas Pagamento")
public interface FormaPagamentoControllerOpenApi {

   @ApiOperation(value = "Lista as formas de pagamento")
   @ApiResponses({
      @ApiResponse(code = 200, message = "ok", response = FormasPagamentoModelOpenApi.class)
   })
   public ResponseEntity<CollectionModel<FormaPagamentoModel>> listar(ServletWebRequest request);
  
   @ApiOperation("Busca uma forma de pagamento por ID")
   @ApiResponses({
      @ApiResponse(code = 400, message = "Forma de pagamento inválido", response = Problem.class),
      @ApiResponse(code = 404, message = "Forma de pagamento não encontrado", response = Problem.class)})
   public ResponseEntity<FormaPagamentoModel> buscar(
         @ApiParam(value = "ID de uma forma de pagamento", example = "1")
               Long formaPagamentoId, ServletWebRequest request);
               

   @ApiOperation("Salva uma forma de pagamento")
   @ApiResponses({
      @ApiResponse(code = 201, message = "Forma de pagamento cadastrada")})
   public FormaPagamentoModel adicionar(@ApiParam(
         value = "Representação de uma forma de pagamento", name = "corpo")
         FormaPagamentoInput formaPagamentoInput);

   @ApiOperation("Atualiza uma forma de pagamento por ID")
   @ApiResponses({
      @ApiResponse(code = 400, message = "Forma de pagamento inválido", response = Problem.class),
      @ApiResponse(code = 404, message = "Forma de pagamento não encontrado", response = Problem.class)})
   public FormaPagamentoModel atualizar(
         @ApiParam(value = "ID de uma forma de pagamento") Long formaPagamentoId,
         @ApiParam(value = "Representação de uma forma de pagamento", name = "corpo") 
         FormaPagamentoInput formaPagamentoInput);
         
   @ApiOperation("Exclui uma forma de pagamento por ID")
   @ApiResponses({
      @ApiResponse(code = 204, message = "Forma de pagamento excluida"),
      @ApiResponse(code = 404, message = "Forma de pagamento não encontrado", response = Problem.class)})
   public void remover(@ApiParam(
            value = "ID de uma forma de pagamento") Long formaPagamentoId);
   
}
