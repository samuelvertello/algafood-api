package com.algaworks.algafood.domain.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException {

   public PermissaoNaoEncontradaException(String mensagem) {
      super(mensagem);
      
   }

   public PermissaoNaoEncontradaException(Long permissaoId) {
      this(String.format(
         "Não existe permissao de código %d cadastrado nesse restaurante.", permissaoId));
   }
   
}
