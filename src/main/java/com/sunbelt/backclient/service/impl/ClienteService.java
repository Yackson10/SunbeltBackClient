package com.sunbelt.backclient.service.impl;

import com.sunbelt.backclient.dto.request.ClienteRequestDTO;
import com.sunbelt.backclient.dto.response.ClienteResponseDTO;
import com.sunbelt.backclient.entity.ClienteEntity;
import com.sunbelt.backclient.repository.ClienteRepository;
import com.sunbelt.backclient.service.IClienteService;
import com.sunbelt.backclient.utils.Constants;
import com.sunbelt.backclient.utils.ResponseMessages;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClienteService implements IClienteService {

    private final ClienteRepository clienteRepository;

    @PostConstruct
    public void init() {
        ClienteEntity clienteEntity = ClienteEntity.builder()
                .numeroDocumento("10121314")
                .tipoIdentificacion("C")
                .primerNombre("Juan")
                .segundoNombre("Andres")
                .primerApellido("Moreno")
                .segundoApellido("Jaramillo")
                .telefono("3117897898")
                .direccion("Calle 82 # 43 - 31")
                .ciudadResidencia("medellin")
                .build();

        clienteRepository.save(clienteEntity);
    }

    @Override
    public ClienteResponseDTO consultar(ClienteRequestDTO clienteRequestDTO) {
        validarCliente(clienteRequestDTO);
        Optional<ClienteEntity> clienteOptional = clienteRepository.findByNumeroDocumentoAndTipoIdentificacion(clienteRequestDTO.getNumeroDocumento(), clienteRequestDTO.getTipoDocumento().toUpperCase());
        if(clienteOptional.isPresent()){

            return ClienteResponseDTO.builder()
                    .primerNombre(clienteOptional.get().getPrimerNombre())
                    .segundoNombre(clienteOptional.get().getSegundoNombre())
                    .primerApellido(clienteOptional.get().getPrimerApellido())
                    .segundoApellido(clienteOptional.get().getSegundoApellido())
                    .telefono(clienteOptional.get().getTelefono())
                    .direccion(clienteOptional.get().getDireccion())
                    .ciudadResidencia(clienteOptional.get().getCiudadResidencia())
                    .build();

        }else{
            log.error(ResponseMessages.CLIENTE_NO_EXISTE.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessages.CLIENTE_NO_EXISTE.getMessage());
        }
    }

    private void validarCliente(ClienteRequestDTO clienteRequestDTO) {

        if (StringUtils.isBlank(clienteRequestDTO.getTipoDocumento())) {
            log.error(ResponseMessages.TIPO_DOCUMENTO_OBLIGATORIO.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ResponseMessages.TIPO_DOCUMENTO_OBLIGATORIO.getMessage());
        }

        if (StringUtils.isBlank(clienteRequestDTO.getNumeroDocumento())) {
            log.error(ResponseMessages.NUMERO_DOCUMENTO_OBLIGATORIO.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ResponseMessages.NUMERO_DOCUMENTO_OBLIGATORIO.getMessage());
        }

        if (!Constants.DOCUMENT_TYPE_CEDULA.equalsIgnoreCase(clienteRequestDTO.getTipoDocumento()) && !Constants.DOCUMENT_TYPE_PASAPORTE.equalsIgnoreCase(clienteRequestDTO.getTipoDocumento())) {
            log.error(ResponseMessages.TIPO_DOCUMENTO_INVALIDO.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ResponseMessages.TIPO_DOCUMENTO_INVALIDO.getMessage());
        }

    }
}



