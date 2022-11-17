package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.UsuarioModel;
import com.algaworks.algafood.api.v1.model.input.SenhaInput;
import com.algaworks.algafood.api.v1.model.input.UsuarioComSenhaInput;
import com.algaworks.algafood.api.v1.model.input.UsuarioInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuarios")
public interface UsuarioControllerOpenApi {

   @ApiOperation("Lista todos os usuários")
   public CollectionModel<UsuarioModel> listar();

   @ApiOperation("Busca um usuário por ID")
   @ApiResponses({
      @ApiResponse(code = 400, message = "ID do usuário inválido", response = Problem.class),
      @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)})
   public UsuarioModel buscar(@ApiParam(example = "1") Long usuarioId);

   
   @ApiOperation("Salva um usuário")
   @ApiResponses({
      @ApiResponse(code = 200, message = "Usuário salvo com sucesso")})
   public UsuarioModel salvar(@ApiParam(name = "corpo", value = "Representação de um novo usuário") 
         UsuarioComSenhaInput usuarioInput);
  

   @ApiOperation("Atualiza um usuario por ID")
   @ApiResponses({
      @ApiResponse(code = 204, message = "Usuário atualizado com sucesso"),
      @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)})
   public UsuarioModel atualizar(@ApiParam(example = "1") Long usuarioId, 
         @ApiParam(name = "corpo", value = "Representação de um usuário com os novos dados") 
            UsuarioInput usuarioInput);

  
   @ApiOperation("Atualizar senha de um usuário")
   @ApiResponses({
      @ApiResponse(code = 204, message = "Senha atualizada com sucesso"),
      @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)})
   public void alterarSenha(@ApiParam(example = "1") Long usuarioId,
         @ApiParam(example = "1") SenhaInput senha);

   
   @ApiOperation("Remove um usuário por ID")
   @ApiResponses({
      @ApiResponse(code = 204, message = "Usuário removido com sucesso"),
      @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)})
   public void remover(@ApiParam(example = "1") Long usuarioId);
   
}
