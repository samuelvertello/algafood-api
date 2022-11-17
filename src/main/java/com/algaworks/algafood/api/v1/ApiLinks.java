package com.algaworks.algafood.api.v1;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.controller.CidadeController;
import com.algaworks.algafood.api.v1.controller.CozinhaController;
import com.algaworks.algafood.api.v1.controller.EstadoController;
import com.algaworks.algafood.api.v1.controller.EstatisticasController;
import com.algaworks.algafood.api.v1.controller.FluxoPedidoController;
import com.algaworks.algafood.api.v1.controller.FormaPagamentoController;
import com.algaworks.algafood.api.v1.controller.GrupoController;
import com.algaworks.algafood.api.v1.controller.GrupoPermissaoController;
import com.algaworks.algafood.api.v1.controller.PedidoController;
import com.algaworks.algafood.api.v1.controller.PermissaoController;
import com.algaworks.algafood.api.v1.controller.RestauranteController;
import com.algaworks.algafood.api.v1.controller.RestauranteFormaPagamentoController;
import com.algaworks.algafood.api.v1.controller.RestauranteProdutoController;
import com.algaworks.algafood.api.v1.controller.RestauranteProdutoFotoController;
import com.algaworks.algafood.api.v1.controller.RestauranteUsuarioResponsavelController;
import com.algaworks.algafood.api.v1.controller.UsuarioController;
import com.algaworks.algafood.api.v1.controller.UsuarioGrupoController;

@Component
public class ApiLinks {

   private static final TemplateVariables PAGINACAO_VARIABLES = new TemplateVariables(
         new TemplateVariable("page", VariableType.REQUEST_PARAM),
         new TemplateVariable("size", VariableType.REQUEST_PARAM),
         new TemplateVariable("sort", VariableType.REQUEST_PARAM));

  
   public Link linkToPedidos(String rel) {

      TemplateVariables filtroVariables = new TemplateVariables(
            new TemplateVariable("clienteId", VariableType.REQUEST_PARAM),
            new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
            new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
            new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM));

      String pedidosUrl = linkTo(PedidoController.class).toUri().toString();

      return Link.of(UriTemplate.of(pedidosUrl,
            PAGINACAO_VARIABLES.concat(filtroVariables)), rel);

   }

   public Link linkToEstatisticasVendaDiarias(String rel) {
      TemplateVariables filtroEstatisticas = new TemplateVariables(
         new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
         new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
         new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM));

         String estatisticasUrl = linkTo(methodOn(EstatisticasController.class)
               .consultarVendasDiarias(null, null)).withRel(rel).toUri().toString();

         return Link.of(UriTemplate.of(estatisticasUrl, filtroEstatisticas), rel);
   }

   public Link linkToEstatisticas(String rel) {
      return linkTo(EstatisticasController.class).withRel(rel);
   }
   
   public Link linkToRestaurante(String rel) {

      TemplateVariables filtroRestaurantes = new TemplateVariables(
            new TemplateVariable("projecao=apenas-nome", VariableType.REQUEST_PARAM));

      String restauranteUrl = linkTo(RestauranteController.class).toUri().toString();

      return Link.of(UriTemplate.of(restauranteUrl, filtroRestaurantes), rel);
   }
      
   public Link linkToRestaurante(Long restauranteId, String rel) {
      return linkTo(methodOn(RestauranteController.class).buscar(restauranteId)).withRel(rel);
   }

   public Link linkToRestaurante(Long restauranteId) {
      return linkToRestaurante(restauranteId, IanaLinkRelations.SELF_VALUE);   
   }  

   public Link linkToRestaurante() {
      return linkToRestaurante(IanaLinkRelations.SELF_VALUE);
   }

   public Link linkToAbrirRestaurante(Long restauranteId, String rel) {
      return linkTo(methodOn(RestauranteController.class)
            .abrir(restauranteId)).withRel(rel);
   }
   public Link linkToFecharRestaurante(Long restauranteId, String rel) {
      return linkTo(methodOn(RestauranteController.class)
            .fechar(restauranteId)).withRel(rel);
   }
   public Link linkToAtivarRestaurante(Long restauranteId, String rel) {
      return linkTo(methodOn(RestauranteController.class)
            .ativar(restauranteId)).withRel(rel);
   }
   public Link linkToInativarRestaurante(Long restauranteId, String rel) {
      return linkTo(methodOn(RestauranteController.class)
            .inativar(restauranteId)).withRel(rel);
   }
   
   public Link linkToResponsaveisRestaurante(Long restauranteId, String rel) {
      return linkTo(methodOn(RestauranteUsuarioResponsavelController.class)
            .listar(restauranteId)).withRel(rel);
   }

   public Link linkToResponsaveisRestaurante(Long restauranteId) {
      return linkToResponsaveisRestaurante(restauranteId, IanaLinkRelations.SELF_VALUE);
   }

   public Link linkToResponsaveisRestauranteDesassociacao(
            Long restauranteId, Long usuarioId, String rel) {
      return linkTo(methodOn(RestauranteUsuarioResponsavelController.class)
            .desassociar(restauranteId, usuarioId)).withRel(rel);
   }

   public Link linkToResponsaveisRestauranteAssociacao(
            Long restauranteId, Long usuarioId, String rel) {
      return linkTo(methodOn(RestauranteUsuarioResponsavelController.class)
            .associar(restauranteId, null)).withRel(rel);
   }

   public Link linkToRestauranteFormaPagamento(Long formaPagamentoId, String rel) {
      return linkTo(methodOn(RestauranteFormaPagamentoController.class)
         .listar(formaPagamentoId)).withRel(rel);
   }

   public Link linkToRestauranteFormaPagamento(Long restauranteFormaPagamentoId) {
      return linkToRestauranteFormaPagamento(
            restauranteFormaPagamentoId, IanaLinkRelations.SELF_VALUE);
   }
   public Link linkToRestauranteFormaPagamentoDesassociacao(Long restauranteId,
         Long formaPagamentoId, String rel){
      
      return linkTo(methodOn(RestauranteFormaPagamentoController.class)
            .desassociar(restauranteId, formaPagamentoId)).withRel(rel);
   }

   public Link linkToRestauranteFormaPagamentoAssociacao(Long restauranteId, String rel){
      
      return linkTo(methodOn(RestauranteFormaPagamentoController.class)
            .associar(restauranteId, null)).withRel(rel);
   }   

   public Link linkToRestauranteProduto(Long restauranteId, String rel) {
      return linkTo(methodOn(RestauranteProdutoController.class)
            .listar(restauranteId, null)).withRel(rel);
   }

   public Link linkToRestauranteProduto(Long restauranteId) {
      return linkToRestauranteProduto(restauranteId, IanaLinkRelations.SELF_VALUE);
   }

   public Link linkToRestauranteProduto(Long restauranteId, Long produtoId, String rel) {
      return linkTo(methodOn(RestauranteProdutoController.class)
            .buscar(restauranteId, produtoId)).withRel(rel);
   }

   public Link linkToFotoProduto(Long restauranteId, Long produtoId, String rel) {
      return linkTo(methodOn(RestauranteProdutoFotoController.class)
            .buscar(restauranteId, produtoId)).withRel(rel);
   }  

   public Link linkToFotoProduto(Long restauranteId, Long produtoId) {
      return linkToFotoProduto(restauranteId, produtoId, IanaLinkRelations.SELF_VALUE);
   }

   
  
   public Link linkToConfirmacaoPedido(String codigoPedido, String rel) {
      return linkTo(methodOn(FluxoPedidoController.class).confirmar(codigoPedido)).withRel(rel);
   }
   public Link linkToEntregarPedido(String codigoPedido, String rel) {
      return linkTo(methodOn(FluxoPedidoController.class).entregar(codigoPedido)).withRel(rel);
   }
   public Link linkToCancelarPedido(String codigoPedido, String rel) {
      return linkTo(methodOn(FluxoPedidoController.class).cancelamento(codigoPedido)).withRel(rel);
   }

   public Link linkToUsuario(Long usuarioId, String rel) {
      return linkTo(methodOn(UsuarioController.class).buscar(usuarioId)).withRel(rel);
   }

   public Link linkToUsuario(Long usuarioId) {
      return linkToUsuario(usuarioId, IanaLinkRelations.SELF_VALUE);
   }

   public Link linkToUsuario(String rel) {
      return linkTo(UsuarioController.class).withRel(rel);
   }

   public Link linkToUsuario() {
      return linkTo(UsuarioController.class).withSelfRel();
   }

   public Link linkToGruposUsuario(Long usuarioId, String rel) {
      return linkTo(methodOn(UsuarioGrupoController.class).listar(usuarioId)).withRel(rel);
   }

   public Link linkToGruposUsuario(Long usuarioId) {
      return linkToGruposUsuario(usuarioId, IanaLinkRelations.SELF_VALUE);
   }

   public Link linkToAssociarUsuarioGrupo() {
      return linkTo(UsuarioGrupoController.class).withSelfRel();
   }

   public Link linkToAssociarUsuarioGrupo(Long usuarioId, String rel) {
      return linkTo(methodOn(UsuarioGrupoController.class)
            .associar(usuarioId, null)).withRel(rel);
   }

   public Link linkToDesassociarUsuarioGrupo(Long usuarioId, Long grupoId, String rel) {
      return linkTo(methodOn(UsuarioGrupoController.class)
            .desassociar(usuarioId, grupoId)).withRel(rel);
   }

   public Link linkToGrupo(String rel) {
      return linkTo(GrupoController.class).withRel(rel);
   }
   public Link linkToGrupo(Long grupoId) {
      return linkTo(methodOn(GrupoController.class).buscar(grupoId)).withSelfRel();
   }

   public Link linkToPermissaoGrupo(Long grupoId, String rel) {
      return linkTo(methodOn(GrupoPermissaoController.class)
            .listar(grupoId)).withRel(rel);
   }

   public Link linkToPermissaoGrupo(Long grupoId) {
      return linkToPermissaoGrupo(grupoId, IanaLinkRelations.SELF_VALUE);
   }

   public Link linkToPermissoes(String rel) {
      return linkTo(PermissaoController.class).withRel(rel);
   }

   public Link linkToAssociarPermissoes(Long grupoId, String rel) {
      return linkTo(methodOn(GrupoPermissaoController.class)
            .associar(grupoId, null)).withRel(rel);
   }

   public Link linkToDesassociarPermissoes(Long grupoId, Long permissaoId, String rel) {
      return linkTo(methodOn(GrupoPermissaoController.class)
            .desassociar(grupoId, permissaoId)).withRel(rel);
   }

   
   public Link linkToFormaPagamento(String rel) {
      return linkTo(FormaPagamentoController.class).withRel(rel);
   }

   public Link linkToFormaPagamento() {
      return linkToFormaPagamento(IanaLinkRelations.SELF_VALUE);
   }

   public Link linkToFormaPagamento(Long restauranteId) {
      return linkTo(methodOn(FormaPagamentoController.class)
            .buscar(restauranteId, null)).withSelfRel();
   }

   public Link linkToCidade(Long cidadeId, String rel) {
      return linkTo(methodOn(CidadeController.class).buscar(cidadeId)).withRel(rel);
   }
   public Link linkToCidade(Long cidadeId) {
      return linkToCidade(cidadeId, IanaLinkRelations.SELF_VALUE);
   }
   public Link linkToCidade(String rel) {
      return linkTo(CidadeController.class).withRel(rel);
   }

   public Link linkToCidade() {
      return linkToCidade(IanaLinkRelations.SELF_VALUE);
   }

   public Link linkToEstado(String rel) {
      return linkTo(EstadoController.class).withRel(rel);
   }

   public Link linkToEstado(Long estadoId, String rel) {
      return linkTo(methodOn(EstadoController.class).buscar(estadoId)).withRel(rel);
   }

   public Link linkToEstado(Long estadoId) {
      return linkToEstado(estadoId, IanaLinkRelations.SELF_VALUE);
   }


   public Link linkToCozinhas(String rel) {
      return linkTo(CozinhaController.class).withRel(rel);
   }
   
   
   public Link linkToCozinhas(Long cozinhaId, String rel) {
      return linkTo(methodOn(CozinhaController.class).buscar(cozinhaId)).withRel(rel);
   }

   public Link linkToCozinhas(Long cozinhaId) {
      return linkToCozinhas(cozinhaId, IanaLinkRelations.SELF_VALUE);
   }
      

}
