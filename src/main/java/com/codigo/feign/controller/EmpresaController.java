package com.codigo.feign.controller;

import com.codigo.feign.aggregates.request.EmpresaRequest;
import com.codigo.feign.aggregates.response.BaseResponse;
import com.codigo.feign.service.EmpresaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/empresa")
public class EmpresaController {
    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/{numero}")
    public BaseResponse getInfoSunat(@PathVariable String numero)
    {
        return empresaService.getInfoSunat(numero);
    }

    @PostMapping("/agregar-sunat")
    public BaseResponse crearEmpresaSunat(@RequestBody EmpresaRequest empresa)
    {
        return empresaService.crearEmpresaSunat(empresa.getNumDocu());
    }
}
