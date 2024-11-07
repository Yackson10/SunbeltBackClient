package com.sunbelt.backclient.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class ClienteController {

    @Operation(summary = "Valida la salud de la aplicación")
    @GetMapping("/health")
    public ResponseEntity<String> metodoGet(){
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}

