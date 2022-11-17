package com.algaworks.algafood.api.v1.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.ApiLinks;
import com.algaworks.algafood.api.v1.controller.PedidoController;
import com.algaworks.algafood.api.v1.model.PedidoModel;
import com.algaworks.algafood.domain.model.Pedido;

@Component
public class PedidoModelAssembler extends
      RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

   @Autowired
   private ModelMapper modelMapper;

   @Autowired
   private ApiLinks apiLink;

   public PedidoModelAssembler() {
      super(PedidoController.class, PedidoModel.class);

   }

   @Override
   public PedidoModel toModel(Pedido pedido) {
      PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
      modelMapper.map(pedido, pedidoModel);

      pedidoModel.add(apiLink.linkToPedidos("pedidos"));

      if (pedido.podeSerConfirmado()) {
         pedidoModel.add(apiLink.linkToConfirmacaoPedido(pedido.getCodigo(), "confirmar"));

      }
      if (pedido.podeSerEntregue()) {
         pedidoModel.add(apiLink.linkToEntregarPedido(pedido.getCodigo(), "Entregar"));
      }
      if (pedido.podeSerCancelado()) {
         pedidoModel.add(apiLink.linkToCancelarPedido(pedido.getCodigo(), "cancelar"));
      }

      pedidoModel.getRestaurante().add(apiLink.linkToRestaurante(
            pedido.getRestaurante().getId()));

      pedidoModel.getCliente().add(apiLink.linkToUsuario(
            pedido.getCliente().getId()));

      pedidoModel.getFormaPagamento().add(apiLink.linkToFormaPagamento(
            pedido.getFormaPagamento().getId()));

      pedidoModel.getEnderecoEntrega().getCidade().add(apiLink.linkToCidade(
            pedido.getEnderecoEntrega().getCidade().getId(), "cidade"));

      pedidoModel.getItens().forEach(itens -> itens.add(apiLink.linkToRestauranteProduto(
            pedido.getRestaurante().getId(), itens.getProdutoId(), "produto")));

      return pedidoModel;

   }

   @Override
   public CollectionModel<PedidoModel> toCollectionModel(Iterable<? extends Pedido> entities) {
      return super.toCollectionModel(entities)
            .add(linkTo(PedidoController.class).withSelfRel());
   }

}
