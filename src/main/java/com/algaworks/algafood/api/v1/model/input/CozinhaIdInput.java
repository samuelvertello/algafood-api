package com.algaworks.algafood.api.v1.model.input;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaIdInput {

   @ApiModelProperty(example = "5", required = true)
   @NotNull
   private Long id;
   
}
