package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.ApiLinks;
import com.algaworks.algafood.api.v1.controller.CozinhaController;
import com.algaworks.algafood.api.v1.model.CozinhaModel;
import com.algaworks.algafood.domain.model.Cozinha;

@Component
public class CozinhaModelAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModel> {
  
   @Autowired
   private ModelMapper modelMapper;

   @Autowired
   private ApiLinks apiLink;

   public CozinhaModelAssembler() {
      super(CozinhaController.class, CozinhaModel.class);
   }
   

   @Override
   public CozinhaModel toModel(Cozinha cozinha) {
      CozinhaModel cozinhaModel = createModelWithId(cozinha.getId(), cozinha);
      modelMapper.map(cozinha, cozinhaModel);

      cozinhaModel.add(apiLink.linkToCozinhas("cozinhas"));

      return cozinhaModel;
   }


   @Override
   public CollectionModel<CozinhaModel> toCollectionModel(Iterable<? extends Cozinha> entities) {
      
      return super.toCollectionModel(entities);
   }

   

  
   

   
}
