package com.algaworks.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.algaworks.algafood.domain.model.StatusPedido;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoModel {

   @ApiModelProperty(example = "367b1678-e48b-480a-810f-6b8caad34a0a")
   private String codigo;

   @ApiModelProperty(example = "278.90")
   private BigDecimal subTotal;

   @ApiModelProperty(example = "12.90")
   private BigDecimal taxaFrete;

   @ApiModelProperty(example = "288.90")
   private BigDecimal valorTotal;

   @ApiModelProperty(example = "ENTREGUE")
   private StatusPedido status; 

   @ApiModelProperty(example = "2022-09-27T12:11:58Z")
   private OffsetDateTime dataCriacao;   

   @ApiModelProperty(example = "2022-09-27T12:11:58Z")
   private OffsetDateTime dataConfirmacao;

   @ApiModelProperty(example = "2022-09-27T12:11:58Z")
   private OffsetDateTime dataEntrega;

   @ApiModelProperty(example = "2022-09-27T12:11:58Z")
   private OffsetDateTime dataCancelamento;
   
   private FormaPagamentoModel formaPagamento;

   private RestauranteResumoModel restaurante;

   private EnderecoModel enderecoEntrega;  

   private UsuarioModel cliente;   
   
   private List<ItemPedidoModel> itens;
   
}
