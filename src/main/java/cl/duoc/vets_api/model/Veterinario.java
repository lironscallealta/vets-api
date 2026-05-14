/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vets_api.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

    @Column(length = 12, nullable = false, unique = true, name = "numero_profesional")
    private String numeroRegistroProfesional;

    @Column(name = "egreso_profesional")
    private LocalDate egresoProfesional;

    @Column(name = "es_cirujano")
    private Boolean esCirujano;

    @ManyToMany
    @JoinTable(name = "veterinarios_horarios", joinColumns = @JoinColumn(name = "veterinario_id"), inverseJoinColumns = @JoinColumn(name = "horario_id"))
    private List<Horario> horarios; // cuando trabaja cada veterinario

    // esa fue la forma que encontre para hacer la tabla intermedia cuando es muchos
    // a muchos
}