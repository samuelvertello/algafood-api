package com.algaworks.algafood.api.v1.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.algaworks.algafood.domain.model.StatusPedido;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidos")
@Setter
@Getter
public class PedidoResumoModel extends RepresentationModel<PedidoResumoModel>{

   private String codigo;
   private BigDecimal subTotal;
   private BigDecimal taxaFrete;
   private BigDecimal valorTotal;
   private StatusPedido status; 
   private OffsetDateTime dataCriacao; 
   private RestauranteApenasNomeModel restaurante;
   private UsuarioModel cliente; 
   
   
}
