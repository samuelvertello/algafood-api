package com.algaworks.algafood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.ApiLinks;
import com.algaworks.algafood.api.v1.assembler.ProdutoInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.ProdutoModelAssembler;
import com.algaworks.algafood.api.v1.controller.openapi.controller.RestauranteProdutoControllerOpenApi;
import com.algaworks.algafood.api.v1.model.ProdutoModel;
import com.algaworks.algafood.api.v1.model.input.ProdutoInput;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.service.CadastroProdutoService;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/produtos", 
      produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoController implements RestauranteProdutoControllerOpenApi{

   @Autowired
   private ProdutoRepository produtoRepository;

   @Autowired
   private CadastroRestauranteService restauranteService;

   @Autowired
   private CadastroProdutoService produtoService;

   @Autowired
   private ProdutoModelAssembler produtoModelAssembler;

   @Autowired
   private ProdutoInputDisassembler produtoInputDisassembler;

   @Autowired
   private ApiLinks apiLink;


   @GetMapping
   public CollectionModel<ProdutoModel> listar(@PathVariable Long restauranteId,
            @RequestParam(required = false, defaultValue = "false") Boolean incluirInativos) {

      Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

      List<Produto> todosProdutos = null;

      if (incluirInativos) {
         todosProdutos = produtoRepository.findTodosByRestaurante(restaurante);
      } else {
         todosProdutos = produtoRepository.findAtivosByRestaurante(restaurante);
      }

        return produtoModelAssembler.toCollectionModel(todosProdutos)
            .add(apiLink.linkToRestauranteProduto(restauranteId));
     
            
   }
                  
   @GetMapping("/{produtoId}")
   public ProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
      Produto produto = produtoService.buscarOuFalhar(restauranteId, produtoId);

      return produtoModelAssembler.toModel(produto);
   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public ProdutoModel adicionar(@PathVariable Long restauranteId,
             @RequestBody @Valid ProdutoInput produtoInput) {
      try {

         Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
         Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
         produto.setRestaurante(restaurante);

         produto = produtoService.salvar(produto);

         return produtoModelAssembler.toModel(produto);

      } catch (Exception e) {
         throw new NegocioException(e.getMessage());
      }
   }

   @PutMapping("/{produtoId}")
   public ProdutoModel atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
            @RequestBody @Valid ProdutoInput produtoInput) {

         try {
            Produto produtoAtual = produtoService.buscarOuFalhar(restauranteId, produtoId);

            produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);

            produtoAtual = produtoService.salvar(produtoAtual);

            return produtoModelAssembler.toModel(produtoAtual);
            
         } catch (Exception e) {
            throw new NegocioException(e.getMessage());
         }
      }

   
      
     
}
