/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vets_api.dataloader;

import cl.duoc.vets_api.model.DiasSemana;
import cl.duoc.vets_api.model.Horario;
import cl.duoc.vets_api.model.Veterinario;
import cl.duoc.vets_api.repository.VeterinarioRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private static final int VETERINARIOS_FLYWAY = 5;
    private static final int VETERINARIOS_FAKE = 10;

    private final VeterinarioRepository veterinarioRepository;

    @Override
    public void run(String... args) {
        if (veterinarioRepository.count() > VETERINARIOS_FLYWAY) {
            return;
        }

        Faker faker = new Faker();

        for (int i = 0; i < VETERINARIOS_FAKE; i++) {
            Veterinario veterinario = new Veterinario();
            veterinario.setNombre(faker.name().firstName());
            veterinario.setSegundoNombre(faker.name().firstName());
            veterinario.setApellido(faker.name().lastName());
            veterinario.setSegundoApellido(faker.name().lastName());
            veterinario.setRut(String.valueOf(faker.number().numberBetween(10000000, 25000000)));
            veterinario.setDv(faker.options().option("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "K"));
            veterinario.setEmail(faker.internet().emailAddress());
            veterinario.setTelefonoCelular("+569" + faker.number().digits(8));
            veterinario.setFechaNacimiento(faker.timeAndDate().birthday(25, 55));
            veterinario.setNumeroRegistroProfesional("REG-FAKE-" + (2000 + i));
            veterinario.setEgresoProfesional(
                    LocalDate.now().minusYears(ThreadLocalRandom.current().nextInt(1, 10)));
            veterinario.setPuedeOperar(faker.bool().bool());
            veterinario.setHorarioVeterinario(crearHorarios());
            veterinarioRepository.save(veterinario);
        }
    }

    private List<Horario> crearHorarios() {
        List<Horario> horarios = new ArrayList<>();
        DiasSemana[] dias = DiasSemana.values();

        for (int i = 0; i < 2; i++) {
            Horario horario = new Horario();
            horario.setDia(dias[ThreadLocalRandom.current().nextInt(dias.length)]);
            horario.setHoraInicio(LocalTime.of(8 + (i * 6), 0));
            horario.setHoraFin(LocalTime.of(13 + (i * 6), 0));
            horarios.add(horario);
        }

        return horarios;
    }
}
