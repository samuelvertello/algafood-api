package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.model.input.FormaPagamentoInput;
import com.algaworks.algafood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoInputDisassembler {


   @Autowired
   private ModelMapper modelMapper;
   

   public FormaPagamento toDomainOject(FormaPagamentoInput FormaPagamentoInput) {
      return modelMapper.map(FormaPagamentoInput, FormaPagamento.class);
   }

   public void copyToDomainObject(FormaPagamentoInput formaPagamentoInput, FormaPagamento formaPagamento){
      modelMapper.map(formaPagamentoInput, formaPagamento);
   }

}
