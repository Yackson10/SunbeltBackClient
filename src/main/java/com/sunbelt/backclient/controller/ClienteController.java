package com.sunbelt.backclient.controller;

import com.sunbelt.backclient.dto.request.ClienteRequestDTO;
import com.sunbelt.backclient.dto.response.ClienteResponseDTO;
import com.sunbelt.backclient.service.IClienteService;
import com.sunbelt.backclient.service.impl.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class ClienteController {

    private final IClienteService clienteService;

    @Operation(summary = "Valida la salud de la aplicación")
    @GetMapping("/health")
    public ResponseEntity<String> health(){
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping("/consultar")
    public ResponseEntity<ClienteResponseDTO> consultar(@RequestBody ClienteRequestDTO clienteRequestDTO){
        return new ResponseEntity<>(clienteService.consultar(clienteRequestDTO), HttpStatus.OK);
    }




}

