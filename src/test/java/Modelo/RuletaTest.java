package Modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RuletaTest {

    @Test
    void jugarDebeRetornarResultadoValido() {

        IRepositorioResultados repo =
                new RepositorioEnMemoria();

        Ruleta ruleta =
                new Ruleta(1000, repo);

        Resultado resultado =
                ruleta.jugar(
                        new ApuestaPar(100)
                );

        assertNotNull(resultado);

        assertTrue(
                resultado.getNumero() >= 0 &&
                        resultado.getNumero() <= 36
        );
    }

    @Test
    void apostarMasQueSaldoDebeLanzarExcepcion() {

        IRepositorioResultados repo =
                new RepositorioEnMemoria();

        Ruleta ruleta =
                new Ruleta(10, repo);

        IllegalArgumentException ex =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> ruleta.jugar(
                                new ApuestaRojo(100)
                        )
                );

        assertEquals(
                "Saldo insuficiente",
                ex.getMessage()
        );
    }

    @Test
    void saldoNuncaDebeSerNegativo() {

        IRepositorioResultados repo =
                new RepositorioEnMemoria();

        Ruleta ruleta =
                new Ruleta(1000, repo);

        for(int i = 0; i < 1000; i++){

            ruleta.jugar(
                    new ApuestaPar(1)
            );
        }

        assertTrue(
                ruleta.getSaldo() >= 0
        );
    }

    @Test
    void stressTest100000Apuestas() {

        IRepositorioResultados repo =
                new RepositorioEnMemoria();

        Ruleta ruleta =
                new Ruleta(500000, repo);

        for(int i = 0; i < 100000; i++){

            Resultado r =
                    ruleta.jugar(
                            new ApuestaPar(1)
                    );

            assertNotNull(r);
        }

        assertEquals(
                100000,
                repo.obtenerResultados().size()
        );
    }
}