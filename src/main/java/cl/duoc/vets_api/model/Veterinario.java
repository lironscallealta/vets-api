/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vets_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
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

    @Column(nullable = false, name = "pnombre")
    private String nombre;

    @Column(name = "snombre")
    private String segundoNombre;

    @Column(name = "appaterno")
    private String apellido;

    @Column(name = "apmaterno")
    private String segundoApellido;

    @Column(length = 22)
    private String rut;

    @Column(length = 1)
    private String dv;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 20)
    private String telefonoCelular;

    @Column(name = "fecha_nacimiento_vet", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(length = 12, nullable = false, unique = true, name = "registro_profesional")
    private String numeroRegistroProfesional;

    @Column(name = "egreso_profesional")
    private LocalDate egresoProfesional;

    @Column(name = "opera")
    private Boolean puedeOperar;

    @OneToMany
    @JoinColumn(name = "veterinario_id")
    private List<Horario> horarioVeterinario;
}
