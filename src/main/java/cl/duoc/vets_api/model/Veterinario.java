/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vets_api.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "veterinarios")
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(length = 50, name = "segundo_nombre")
    private String segundoNombre;

    @Column(length = 50, name = "appaterno")
    private String apellido;

    @Column(length = 50, name = "apmaterno")
    private String segundoApellido;

    @Column(length = 22)
    private String rut;

    @Column(length = 1)
    private String dv;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 20)
    private String telefonoCelular;

    @Column(name = "fecha_nacimiento_vet", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(length = 12, nullable = false, unique = true, name = "registro_veterinario")
    private String numeroRegistroProfesional;

    @Column(name = "fecha_egreso_vet")
    private LocalDate egresoProfesional;

    @Column(name = "es_cirujano")
    private Boolean esCirujano;

}