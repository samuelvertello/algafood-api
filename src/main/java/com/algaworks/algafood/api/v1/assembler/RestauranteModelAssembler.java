package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.ApiLinks;
import com.algaworks.algafood.api.v1.controller.RestauranteController;
import com.algaworks.algafood.api.v1.model.RestauranteModel;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel> {

   @Autowired
   private ApiLinks apiLink;

   public RestauranteModelAssembler() {
      super(RestauranteController.class, RestauranteModel.class);

   }

   @Autowired
   private ModelMapper modelMapper;

   public RestauranteModel toModel(Restaurante restaurante) {
      
      RestauranteModel restauranteModel = createModelWithId(restaurante.getId(), restaurante);
      modelMapper.map(restaurante, restauranteModel);

      restauranteModel.add(apiLink.linkToRestaurante("restaurantes"));


      restauranteModel.getCozinha().add(apiLink.linkToCozinhas(
            restaurante.getCozinha().getId()));

      if(restauranteModel.getEndereco() != null && 
            restaurante.getEndereco().getCidade() != null){
               
         restauranteModel.getEndereco().getCidade().add(apiLink
            .linkToCidade(restaurante.getEndereco().getCidade().getId()));
      }
      restauranteModel.add(apiLink.linkToRestauranteProduto(
            restaurante.getId(), "produtos"));

      restauranteModel.add(apiLink.linkToRestauranteFormaPagamento(
            restaurante.getId(), "formas-pagamento"));

      restauranteModel.add(apiLink.linkToResponsaveisRestaurante(
            restaurante.getId(), "responsaveis"));

      if (restaurante.permitidoAtivacao()) {
         restauranteModel.add(apiLink
            .linkToAtivarRestaurante(restaurante.getId(), "ativar"));
      }
      if(restaurante.permitidoInativacao()) {
         restauranteModel.add(apiLink
            .linkToInativarRestaurante(restaurante.getId(), "inativar"));
      }
      if(restaurante.permitidoAbertura()) {
         restauranteModel.add(apiLink
            .linkToAbrirRestaurante(restaurante.getId(), "abrir restaurante"));
      }
      if(restaurante.permitidoFechamento()) {
         restauranteModel.add(apiLink
            .linkToFecharRestaurante(restaurante.getId(), "fechar restaurante"));
      }

      return restauranteModel;

   }

   @Override
   public CollectionModel<RestauranteModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
      return super.toCollectionModel(entities)
            .add(apiLink.linkToRestaurante());
   }

}
