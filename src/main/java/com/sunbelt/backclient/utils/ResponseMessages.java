package com.sunbelt.backclient.utils;

import lombok.Data;
import lombok.Getter;

@Getter
public enum ResponseMessages {

    TIPO_DOCUMENTO_OBLIGATORIO("El tipo es obligatorio"),
    NUMERO_DOCUMENTO_OBLIGATORIO("El numero de documento es obligatorio"),
    TIPO_DOCUMENTO_INVALIDO("Tipo de documento inválido. Debe ser 'C' (cédula de ciudadanía) o 'P' (Pasaporte)."),
    CLIENTE_NO_EXISTE("Cliente no existe");
    private final String message;

    ResponseMessages(String message) {
        this.message = message;
    }

}
