package com.algaworks.algafood.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.ApiLinks;
import com.algaworks.algafood.api.v1.controller.openapi.controller.EstatisticasControllerOpenApi;
import com.algaworks.algafood.api.v1.model.EstatisticasModel;
import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.service.VendaQueryService;
import com.algaworks.algafood.domain.service.VendaReportService;


@RestController
@RequestMapping(path = "/estatisticas")
public class EstatisticasController implements EstatisticasControllerOpenApi {

   @Autowired
   private VendaQueryService VendaQueryService;

   @Autowired
   private VendaReportService VendaReportService;

   @Autowired
   private ApiLinks apiLink;

   @Override
   @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
   public EstatisticasModel estatisticas() {
         
      var estatisticasModel = new EstatisticasModel();

      estatisticasModel.add(apiLink.linkToEstatisticasVendaDiarias("vendas-diarias"));

      return estatisticasModel;
   }

   @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
   public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro,
               @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet) {
      return VendaQueryService.consultarVendasDiarias(filtro, timeOffSet);
   }

   @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
   public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro,
               @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet) {

         byte[] bytesPdf = VendaReportService.emitirVendasDiarias(filtro, timeOffSet);

         var headers = new HttpHeaders();
         headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");

      return ResponseEntity.ok()
               .contentType(MediaType.APPLICATION_PDF)
               .headers(headers)
               .body(bytesPdf);
   }
   
   
}
