package com.algaworks.algafood.api.v1.controller.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import com.algaworks.algafood.api.v1.model.EstatisticasModel;
import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Estatisticas")
public interface EstatisticasControllerOpenApi {

   @ApiOperation(value = "Estatisticas", hidden = true)
   EstatisticasModel estatisticas();

   @ApiOperation("Consulta estatisticas de vendas diárias")
   @ApiImplicitParams({
      @ApiImplicitParam(name = "restauranteId", value = "ID do restaurante", dataType = "Long", example = "1"),
      @ApiImplicitParam(name = "dataCriacaoInicio", value = "Define data inicial do relátorio",
            dataType = "date-time", example = "2022-09-27T12:11:58Z"),
      @ApiImplicitParam(name = "dataCriacaoFim", value = "Define data final do relátorio", 
            dataType = "date-time", example = "2022-10-27T12:11:58Z")})

   public List<VendaDiaria> consultarVendasDiarias(@ApiParam(name = "corpo",
         value = "Representação dos parametros que deve conter no relátorio") VendaDiariaFilter filtro,
            @ApiParam(value = "Define fuso horário em que o relátorio deve ser criado", 
               example = "-03:00", defaultValue = "+00:00") String timeOffSet);


   public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro,
   @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet);
   
}
