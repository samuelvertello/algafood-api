package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
   
   ERRO_DADO_INVALIDO("dados-invalido-ou-nulo", "Dados inválidos ou nulo"),
   ERRO_DE_SISTEMA("erro-de-sistema", "Erro de sistema"),
   MESSAGE_IMCOMPRESSIVE("mensagem-imcompreesivel", "Mensagem incompreensivél"),
   RECURSO_NAO_ENCONTRADO("entidade-nao-encontrado", "Recurso não encontrado"),
   ENTIDADE_EM_USO("entidade-em-uso", "Entidade em uso"),
   ERRO_NEGOCIO("erro-negocio", "Violação de regra de negócio"),
   PARAMETRO_INVALIDO("parametro-invalido", "Parametro inválido");
   

   private String title;
   private String uri;

   ProblemType(String path, String title) {
      this.uri = "https://algafood.com.br/" + path;
      this.title = title;
   }
}
