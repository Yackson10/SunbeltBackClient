package com.sunbelt.backclient.service.impl;

import com.sunbelt.backclient.dto.request.ClienteRequestDTO;
import com.sunbelt.backclient.dto.response.ClienteResponseDTO;
import com.sunbelt.backclient.entity.ClienteEntity;
import com.sunbelt.backclient.repository.ClienteRepository;
import com.sunbelt.backclient.utils.ResponseMessages;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TestClienteService {

    @InjectMocks
    ClienteService clienteService;

    @Mock
    ClienteRepository clienteRepository;

    private ClienteRequestDTO clienteRequestDTO;
    private ClienteEntity clienteEntity;

    @Before
    public void setUp() {

        clienteRequestDTO = ClienteRequestDTO.builder()
                .numeroDocumento("10121314")
                .tipoDocumento("C")
                .build();

        clienteEntity = ClienteEntity.builder()
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
    }

    @Test
    public void testConsultarClienteExistente() {
        when(clienteRepository.findByNumeroDocumentoAndTipoIdentificacion(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Optional.of(clienteEntity));

        ClienteResponseDTO response = clienteService.consultar(clienteRequestDTO);

        assertEquals(clienteEntity.getPrimerNombre(), response.getPrimerNombre());
        assertEquals(clienteEntity.getPrimerApellido(), response.getPrimerApellido());
        assertEquals(clienteEntity.getTelefono(), response.getTelefono());
        assertEquals(clienteEntity.getDireccion(), response.getDireccion());
        assertEquals(clienteEntity.getCiudadResidencia(), response.getCiudadResidencia());

        Mockito.verify(clienteRepository, Mockito.times(1))
                .findByNumeroDocumentoAndTipoIdentificacion("10121314", "C");

    }

    @Test
    public void testConsultarClienteNoExistente() {
        when(clienteRepository.findByNumeroDocumentoAndTipoIdentificacion("10121314", "C"))
                .thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            clienteService.consultar(clienteRequestDTO);
        });

        assertEquals(ResponseMessages.CLIENTE_NO_EXISTE.getMessage(), exception.getReason());
    }

    @Test
    public void testValidarClienteTipoDocumentoVacio() {

        ClienteRequestDTO invalidRequest = ClienteRequestDTO.builder()
                .numeroDocumento("12345678")
                .tipoDocumento("")
                .build();

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            clienteService.consultar(invalidRequest);
        });

        assertEquals(ResponseMessages.TIPO_DOCUMENTO_OBLIGATORIO.getMessage(), exception.getReason());
    }

    @Test
    public void testValidarClienteNumeroDocumentoVacio() {

        ClienteRequestDTO invalidRequest = ClienteRequestDTO.builder()
                .numeroDocumento("")
                .tipoDocumento("C")
                .build();

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            clienteService.consultar(invalidRequest);
        });

        assertEquals(ResponseMessages.NUMERO_DOCUMENTO_OBLIGATORIO.getMessage(), exception.getReason());
    }

    @Test
    public void testValidarClienteTipoDocumentoInvalido() {
        ClienteRequestDTO invalidRequest = ClienteRequestDTO.builder()
                .numeroDocumento("12345678")
                .tipoDocumento("B")
                .build();

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            clienteService.consultar(invalidRequest);
        });

        assertEquals(ResponseMessages.TIPO_DOCUMENTO_INVALIDO.getMessage(), exception.getReason());
    }




}
