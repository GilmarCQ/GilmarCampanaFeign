package com.codigo.feign.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaRequest {
    private String razonSocial;
    private String numDocu;
    private String nomComercial;
}
