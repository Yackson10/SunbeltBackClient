package com.sunbelt.backclient.service.impl;

import com.sunbelt.backclient.dto.request.ClienteRequestDTO;
import com.sunbelt.backclient.dto.response.ClienteResponseDTO;
import com.sunbelt.backclient.service.IClienteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteService implements IClienteService {

    @Override
    public ClienteResponseDTO consultar(ClienteRequestDTO clienteRequestDTO) {
        validar(clienteRequestDTO);
        return new ClienteResponseDTO();
    }

    private void validar(ClienteRequestDTO clienteRequestDTO) {

        if (StringUtils.isBlank(clienteRequestDTO.getTipoDocumento())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El tipo es obligatorio");
        }

        if (StringUtils.isBlank(clienteRequestDTO.getNumeroDocumento())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El numero de documento es obligatorio");
        }

        if (!"C".equals(clienteRequestDTO.getTipoDocumento()) && !"P".equals(clienteRequestDTO.getTipoDocumento())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de documento inválido. Debe ser 'C' (cédula de ciudadanía) o 'P' (Pasaporte).");
        }
    }
}


