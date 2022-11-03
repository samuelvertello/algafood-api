package com.algaworks.algafood.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException{

   public ProdutoNaoEncontradoException(String mensagem) {
      super(mensagem);
      
   }

   public ProdutoNaoEncontradoException(Long restauranteId, Long produtoId) {
      this(String.format(
         "Produto de código %d não encontrado ou nao existe no restaurante de id %d.", produtoId, restauranteId));
      
   }
   
}
