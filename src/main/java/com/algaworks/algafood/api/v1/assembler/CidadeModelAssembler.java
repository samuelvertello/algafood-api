package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.ApiLinks;
import com.algaworks.algafood.api.v1.controller.CidadeController;
import com.algaworks.algafood.api.v1.model.CidadeModel;
import com.algaworks.algafood.domain.model.Cidade;


@Component
public class CidadeModelAssembler 
   extends RepresentationModelAssemblerSupport<Cidade, CidadeModel>{

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private ApiLinks apiLink;


  public CidadeModelAssembler() {
    super(CidadeController.class, CidadeModel.class);
    
  }
  
   @Override
   public CidadeModel toModel(Cidade cidade) {
      CidadeModel cidadeModel = createModelWithId(cidade.getId(), cidade);

      modelMapper.map(cidade, cidadeModel);           

      cidadeModel.add(apiLink.linkToCidade("cidades"));      

      cidadeModel.getEstado().add(apiLink.linkToEstado(cidadeModel.getEstado().getId()));   
      
      return cidadeModel;

   }

   @Override
   public CollectionModel<CidadeModel> toCollectionModel(Iterable<? extends Cidade> entities) {      
      return super.toCollectionModel(entities)
            .add(apiLink.linkToCidade());
   }  
   
}
