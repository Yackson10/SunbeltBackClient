package com.sunbelt.backclient.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_identificacion", nullable = false)
    private String tipoIdentificacion;

    @Column(name = "numero_documento", nullable = false, unique = true)
    private String numeroDocumento;

    @Column(name = "primer_nombre", nullable = false)
    private String primerNombre;

    @Column(name = "segundo_nombre")
    private String segundoNombre;

    @Column(name = "primer_apellido", nullable = false)
    private String primerApellido;

    @Column(name = "segundo_apellido")
    private String segundoApellido;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "ciudad_residencia")
    private String ciudadResidencia;

}
