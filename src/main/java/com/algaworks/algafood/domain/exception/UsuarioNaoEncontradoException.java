package com.algaworks.algafood.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException{

   public UsuarioNaoEncontradoException(String mensagem) {
      super(mensagem);
      
   }

   public UsuarioNaoEncontradoException(Long usuarioId) {
      this(String.format("Usuario de código %d não encontrado", usuarioId));
   }
   
}
