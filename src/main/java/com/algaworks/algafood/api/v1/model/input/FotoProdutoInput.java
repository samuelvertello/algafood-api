package com.algaworks.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.core.validation.FileContentType;
import com.algaworks.algafood.core.validation.FileSize;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FotoProdutoInput {
   
   @ApiModelProperty(/* value = "Arquivo da foto do produto (máximo 1000kb, apenas JPG e PNG)", 
   required = true, */ hidden = true)
   @NotNull
   @FileSize(max = "500KB")
   @FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})   
   private MultipartFile arquivo;

   
   @ApiModelProperty(value = "Descrição da foto do produto", required = true)
   @NotBlank
   private String descricao;
}
