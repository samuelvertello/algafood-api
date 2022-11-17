package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.ApiLinks;
import com.algaworks.algafood.api.v1.controller.GrupoController;
import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.domain.model.Grupo;

@Component
public class GrupoModelAssembler
      extends RepresentationModelAssemblerSupport<Grupo, GrupoModel> {

   @Autowired
   private ModelMapper modelMapper;

   @Autowired
   private ApiLinks apiLink;

   public GrupoModelAssembler() {
      super(GrupoController.class, GrupoModel.class);
      
   }

   @Override
   public GrupoModel toModel(Grupo grupo) {
      GrupoModel grupoModel = createModelWithId(grupo.getId(), grupo);

      grupoModel = modelMapper.map(grupo, GrupoModel.class);

      grupoModel.add(apiLink.linkToGrupo(grupo.getId()));

      grupoModel.add(apiLink.linkToGrupo("grupos"));
      
      grupoModel.add(apiLink.linkToPermissaoGrupo(grupo.getId(), "permissoes"));  
            
      
      return grupoModel;

   }

   @Override
   public CollectionModel<GrupoModel> toCollectionModel(Iterable<? extends Grupo> entities) {      
      return super.toCollectionModel(entities)
            .add(apiLink.linkToGrupo("grupos"));  
            
   }

   

}
