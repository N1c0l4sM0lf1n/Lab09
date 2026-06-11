package Modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositorioEnMemoriaTest {

    @Test
    void guardarResultadoDebePersistir() {

        RepositorioEnMemoria repo =
                new RepositorioEnMemoria();

        Resultado resultado =
                new Resultado(
                        10,
                        "Par",
                        100,
                        true
                );

        repo.guardarResultado(resultado);

        assertEquals(
                1,
                repo.obtenerResultados().size()
        );
    }

    @Test
    void guardarMuchosResultados() {

        RepositorioEnMemoria repo =
                new RepositorioEnMemoria();

        for(int i=0;i<10000;i++){

            repo.guardarResultado(
                    new Resultado(
                            i % 37,
                            "Par",
                            1,
                            true
                    )
            );
        }

        assertEquals(
                10000,
                repo.obtenerResultados().size()
        );
    }
}