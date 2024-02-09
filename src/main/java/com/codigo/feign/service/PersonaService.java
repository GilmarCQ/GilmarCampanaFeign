package com.codigo.feign.service;

import com.codigo.feign.aggregates.request.PersonaRequest;
import com.codigo.feign.aggregates.response.BaseResponse;

public interface PersonaService {
    BaseResponse crearPersona(PersonaRequest persona);
    BaseResponse getInfoReniec(String numero);
}
