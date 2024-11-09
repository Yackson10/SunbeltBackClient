package com.sunbelt.backclient.service;

import com.sunbelt.backclient.dto.request.ClienteRequestDTO;
import com.sunbelt.backclient.dto.response.ClienteResponseDTO;

public interface IClienteService {

    ClienteResponseDTO consultar(ClienteRequestDTO clienteResponseDTO);

}
