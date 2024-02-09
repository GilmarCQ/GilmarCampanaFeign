package com.codigo.feign.service.impl;

import com.codigo.feign.aggregates.constants.Constants;
import com.codigo.feign.aggregates.request.EmpresaRequest;
import com.codigo.feign.aggregates.response.BaseResponse;
import com.codigo.feign.aggregates.response.SunatResponse;
import com.codigo.feign.entity.EmpresaEntity;
import com.codigo.feign.entity.TipoDocumentoEntity;
import com.codigo.feign.feignclient.SunatClient;
import com.codigo.feign.repository.EmpresaRepository;
import com.codigo.feign.repository.TipoDocumentoRepository;
import com.codigo.feign.service.EmpresaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpresaServiceImpl implements EmpresaService {
    private final EmpresaRepository empresaRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final SunatClient sunatClient;

    public EmpresaServiceImpl(EmpresaRepository empresaRepository, TipoDocumentoRepository tipoDocumentoRepository, SunatClient sunatClient) {
        this.empresaRepository = empresaRepository;
        this.tipoDocumentoRepository = tipoDocumentoRepository;
        this.sunatClient = sunatClient;
    }

    @Value("${token.api}")
    private String tokenApi;

    @Override
    public BaseResponse crearEmpresa(EmpresaRequest empresa) {
        return null;
    }

    @Override
    public BaseResponse crearEmpresaSunat(String numero) {
        SunatResponse response = getExecution(numero);
        if(response != null)
        {
            Optional<TipoDocumentoEntity> tipoDocumentoBd = tipoDocumentoRepository.findByCodTipo(response.getTipoDocumento());
            if (tipoDocumentoBd.isEmpty()) {
                return new BaseResponse(Constants.CODE_ERROR, Constants.MESS_ERROR, Optional.empty());
            }
            //  Validar que no haya sido agregada previamente la empresa
            Optional<EmpresaEntity> empresaBdOpt = empresaRepository.findByNumDocu(response.getNumeroDocumento());
            if(!empresaBdOpt.isEmpty()){
                return new BaseResponse(Constants.CODE_ERROR, Constants.MESS_ERROR + " RUC registrado.", Optional.empty());
            }

            EmpresaEntity empresa = new EmpresaEntity();
            empresa.setNumDocu(response.getNumeroDocumento());
            empresa.setRazonSocial(response.getRazonSocial());
            empresa.setNomComercial(response.getRazonSocial());
            empresa.setTipoDocumento(tipoDocumentoBd.get());
            empresa.setEstado(1);
            EmpresaEntity empresaBd = empresaRepository.save(empresa);
            return new BaseResponse(Constants.CODE_SUCCESS, Constants.MESS_SUCCESS, Optional.of(empresaBd));
        } else {
            return new BaseResponse(Constants.CODE_ERROR, Constants.MESS_ERROR, Optional.empty());
        }
    }

    @Override
    public BaseResponse getInfoSunat(String numero)
    {
        SunatResponse response = getExecution(numero);
        if(response != null)
        {
            return new BaseResponse(Constants.CODE_SUCCESS, Constants.MESS_SUCCESS, Optional.of(response));
        } else {
            return new BaseResponse(Constants.CODE_ERROR, Constants.MESS_ERROR, Optional.empty());
        }
    }

    private SunatResponse getExecution(String numero)
    {
        String authorization = "Bearer " + tokenApi;
        return sunatClient.getInfo(numero, authorization);
    }
}
