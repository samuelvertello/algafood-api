package com.algaworks.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.algaworks.algafood.domain.model.StatusPedido;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoModel {

   private String codigo;
   private BigDecimal subTotal;
   private BigDecimal taxaFrete;
   private BigDecimal valorTotal;
   private StatusPedido status; 
   private OffsetDateTime dataCriacao;   
   private OffsetDateTime dataConfirmacao;
   private OffsetDateTime dataEntrega;
   private OffsetDateTime dataCancelamento;
   private FormaPagamentoModel formaPagamento;
   private RestauranteResumoModel restaurante;
   private EnderecoModel enderecoEntrega;  
   private UsuarioModel cliente;   
   private List<ItemPedidoModel> itens;
   
}
