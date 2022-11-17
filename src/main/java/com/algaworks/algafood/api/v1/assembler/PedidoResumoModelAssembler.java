package com.algaworks.algafood.api.v1.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.ApiLinks;
import com.algaworks.algafood.api.v1.controller.PedidoController;
import com.algaworks.algafood.api.v1.model.PedidoResumoModel;
import com.algaworks.algafood.domain.model.Pedido;

@Component
public class PedidoResumoModelAssembler 
      extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel> {

   @Autowired
   private ModelMapper modelMapper;

   @Autowired
   private ApiLinks apiLink;

   public PedidoResumoModelAssembler() {
      super(PedidoController.class, PedidoResumoModel.class);

   }

   public PedidoResumoModel toModel(Pedido pedido) {
      PedidoResumoModel pedidoResumoModel = createModelWithId(pedido.getCodigo(), pedido);
      modelMapper.map(pedido, pedidoResumoModel);

      pedidoResumoModel.add(apiLink.linkToPedidos("pedidos"));

      pedidoResumoModel.getRestaurante().add(apiLink.linkToRestaurante(pedido.getRestaurante().getId()));

      pedidoResumoModel.getCliente().add(apiLink.linkToUsuario(pedido.getCliente().getId()));


      return pedidoResumoModel;

   }

   @Override
   public CollectionModel<PedidoResumoModel> toCollectionModel(Iterable<? extends Pedido> entities) {      
      return super.toCollectionModel(entities)
            .add(linkTo(PedidoController.class).withSelfRel());
   }


}
