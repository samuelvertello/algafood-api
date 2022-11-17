package com.algaworks.algafood.api.v1.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.assembler.PedidoInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.PedidoModelAssembler;
import com.algaworks.algafood.api.v1.assembler.PedidoResumoModelAssembler;
import com.algaworks.algafood.api.v1.controller.openapi.controller.PedidoControllerOpenApi;
import com.algaworks.algafood.api.v1.model.PedidoModel;
import com.algaworks.algafood.api.v1.model.PedidoResumoModel;
import com.algaworks.algafood.api.v1.model.input.PedidoInput;
import com.algaworks.algafood.core.data.PageWrapper;
import com.algaworks.algafood.core.data.PageableTranslator;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.service.EmissaoPedidoService;
import com.algaworks.algafood.infrastructure.repository.spec.PedidoSpecs;


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

   @Autowired
   private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;

   
   
   @GetMapping
   public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro,@PageableDefault(size = 10) Pageable pageable) {   
      
      Pageable pageableTraduzido = traduzirPageagle(pageable);
      
      Page<Pedido> pedidosPage = pedidoRepository.findAll(
               PedidoSpecs.usandoFiltro(filtro), pageableTraduzido);

      pedidosPage = new PageWrapper<>(pedidosPage, pageable);

      return pagedResourcesAssembler.toModel(pedidosPage, pedidoResumoModelAssembler);          
       
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
      var mapeamento = Map.of(
              /*  "codigo", "codigo",
               "restaurante.nome", "restaurante.nome",
               "nomeCliente", "cliente.nome",
               "valorTotal", "valorTotal" */
               "codigo", "codigo",
               "subtotal", "subtotal",
               "taxaFrete", "taxaFrete",
               "valorTotal", "valorTotal",
               "dataCriacao", "dataCriacao",
               "restaurante.nome", "restaurante.nome",
               "restaurante.id", "restaurante.id",
               "cliente.id", "cliente.id",
               "cliente.nome", "cliente.nome"
      );

      return  PageableTranslator.translate(apiPageable, mapeamento);
   }
}
