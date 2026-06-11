package Modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstadisticasTest {

    @Test
    void estadisticasConHistorialVacio() {

        IRepositorioResultados repo =
                new RepositorioEnMemoria();

        Estadisticas e =
                new Estadisticas(repo);

        assertEquals(
                0,
                e.getTotalJugadas()
        );

        assertEquals(
                0,
                e.getVictorias()
        );

        assertEquals(
                0.0,
                e.getPorcentajeVictorias()
        );
    }

    @Test
    void calcularEstadisticasCorrectamente() {

        IRepositorioResultados repo =
                new RepositorioEnMemoria();

        repo.guardarResultado(
                new Resultado(
                        10,
                        "Par",
                        100,
                        true
                )
        );

        repo.guardarResultado(
                new Resultado(
                        7,
                        "Rojo",
                        100,
                        true
                )
        );

        repo.guardarResultado(
                new Resultado(
                        15,
                        "Impar",
                        100,
                        false
                )
        );

        repo.guardarResultado(
                new Resultado(
                        22,
                        "Par",
                        100,
                        true
                )
        );

        Estadisticas e =
                new Estadisticas(repo);

        assertEquals(
                4,
                e.getTotalJugadas()
        );

        assertEquals(
                3,
                e.getVictorias()
        );

        assertEquals(
                75.0,
                e.getPorcentajeVictorias(),
                0.01
        );
    }
}