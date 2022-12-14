package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.ApiLinks;
import com.algaworks.algafood.api.v1.controller.RestauranteController;
import com.algaworks.algafood.api.v1.model.RestauranteBasicoModel;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteBasicoModelAssembler extends
      RepresentationModelAssemblerSupport<Restaurante, RestauranteBasicoModel> {


   @Autowired
   private ModelMapper modelMapper;

   @Autowired
   private ApiLinks apiLink;

   public RestauranteBasicoModelAssembler() {
      super(RestauranteController.class, RestauranteBasicoModel.class);

   }

   @Override
   public RestauranteBasicoModel toModel(Restaurante restaurante) {
      RestauranteBasicoModel restauranteBasicoModel = createModelWithId(restaurante.getId(), restaurante);
      modelMapper.map(restaurante, restauranteBasicoModel);

      restauranteBasicoModel.add(apiLink.linkToRestaurante("restaurantes"));

      restauranteBasicoModel.getCozinha().add(apiLink.linkToCozinhas(restaurante.getCozinha().getId()));

      return restauranteBasicoModel;

   }

   @Override
   public CollectionModel<RestauranteBasicoModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
      return super.toCollectionModel(entities)
            .add(apiLink.linkToRestaurante());
   }

}
