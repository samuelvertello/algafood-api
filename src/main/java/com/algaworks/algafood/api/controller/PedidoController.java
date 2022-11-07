package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.PedidoInputDisassembler;
import com.algaworks.algafood.api.assembler.PedidoModelAssembler;
import com.algaworks.algafood.api.assembler.PedidoResumoModelAssembler;
import com.algaworks.algafood.api.controller.openapi.controller.PedidoControllerOpenApi;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.core.data.PageableTranslator;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.service.EmissaoPedidoService;
import com.algaworks.algafood.infrastructure.repository.spec.PedidoSpecs;
import com.google.common.collect.ImmutableMap;

@RestController
@RequestMapping(value = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenApi {

   @Autowired
   private EmissaoPedidoService pedidoService;
      
   @Autowired
   private PedidoRepository pedidoRepository;

   
   @Autowired
   private PedidoResumoModelAssembler pedidoResumoModelAssembler;

   @Autowired
   private PedidoModelAssembler pedidoModelAssembler;

   @Autowired
   private PedidoInputDisassembler pedidoInputDisassembler;

   
   
   @GetMapping
   public Page<PedidoResumoModel> pesquisar(PedidoFilter filtro,@PageableDefault(size = 10) Pageable pageable) {   
      
      pageable = traduzirPageagle(pageable);
      
      Page<Pedido> pedidosPage = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageable);

      List<PedidoResumoModel> pedidoModel = pedidoResumoModelAssembler.toCollectionModel(pedidosPage.getContent());

      Page<PedidoResumoModel> pedidoModelPage = new PageImpl<>(pedidoModel, pageable, pedidosPage.getTotalElements());

      return pedidoModelPage;
       
   }
   
  
   @GetMapping("/{codigoPedido}")
   public PedidoModel buscar(@PathVariable String codigoPedido) {
      Pedido pedido = pedidoService.buscarOuFalhar(codigoPedido);

      return pedidoModelAssembler.toModel(pedido);
   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public PedidoModel adicionar(@RequestBody @Valid PedidoInput pedidoInput) {
      try {  
                  
         Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);  
         
         novoPedido.setCliente(new Usuario());
         novoPedido.getCliente().setId(1L);

         novoPedido = pedidoService.emitir(novoPedido);
         
         return pedidoModelAssembler.toModel(novoPedido);

      } catch (EntidadeNaoEncontradaException e) {
         throw new NegocioException(e.getMessage(), e);
         
      }
      
   }  
   
   private Pageable traduzirPageagle(Pageable apiPageable) {
      var mapeamento = ImmutableMap.of(
               "codigo", "codigo",
               "restaurante.nome", "restaurante.nome",
               "nomeCliente", "cliente.nome",
               "valorTotal", "valorTotal"
      );

      return  PageableTranslator.translate(apiPageable, mapeamento);
   }
}
