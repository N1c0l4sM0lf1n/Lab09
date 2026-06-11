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
                resultado.getNumero() >= 0
                        && resultado.getNumero() <= 36
        );
    }

    @Test
    void depositarDebeAumentarSaldo() {

        Ruleta ruleta =
                new Ruleta(
                        100,
                        new RepositorioEnMemoria()
                );

        ruleta.depositar(50);

        assertEquals(
                150,
                ruleta.getSaldo()
        );
    }

    @Test
    void depositoNegativoDebeFallar() {

        Ruleta ruleta =
                new Ruleta(
                        100,
                        new RepositorioEnMemoria()
                );

        assertThrows(
                IllegalArgumentException.class,
                () -> ruleta.depositar(-50)
        );
    }

    @Test
    void depositoCeroDebeFallar() {

        Ruleta ruleta =
                new Ruleta(
                        100,
                        new RepositorioEnMemoria()
                );

        assertThrows(
                IllegalArgumentException.class,
                () -> ruleta.depositar(0)
        );
    }

    @Test
    void apostarTodoElSaldoDebePermitirse() {

        Ruleta ruleta =
                new Ruleta(
                        100,
                        new RepositorioEnMemoria()
                );

        assertDoesNotThrow(
                () -> ruleta.jugar(
                        new ApuestaPar(100)
                )
        );
    }

    @Test
    void apostarMasQueSaldoDebeFallar() {

        Ruleta ruleta =
                new Ruleta(
                        100,
                        new RepositorioEnMemoria()
                );

        IllegalArgumentException ex =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> ruleta.jugar(
                                new ApuestaPar(101)
                        )
                );

        assertEquals(
                "Saldo insuficiente",
                ex.getMessage()
        );
    }

    @Test
    void noDebePermitirApostarConSaldoCero() {

        Ruleta ruleta =
                new Ruleta(
                        0,
                        new RepositorioEnMemoria()
                );

        assertThrows(
                IllegalArgumentException.class,
                () -> ruleta.jugar(
                        new ApuestaPar(1)
                )
        );
    }

    @Test
    void retirarDineroCorrectamente() {

        Ruleta ruleta =
                new Ruleta(
                        100,
                        new RepositorioEnMemoria()
                );

        ruleta.retirar(40);

        assertEquals(
                60,
                ruleta.getSaldo()
        );
    }

    @Test
    void retirarMasQueSaldoDebeFallar() {

        Ruleta ruleta =
                new Ruleta(
                        100,
                        new RepositorioEnMemoria()
                );

        assertThrows(
                IllegalArgumentException.class,
                () -> ruleta.retirar(101)
        );
    }

    @Test
    void retirarMontoNegativoDebeFallar() {

        Ruleta ruleta =
                new Ruleta(
                        100,
                        new RepositorioEnMemoria()
                );

        assertThrows(
                IllegalArgumentException.class,
                () -> ruleta.retirar(-1)
        );
    }

    @Test
    void stressTest100000Apuestas() {

        IRepositorioResultados repo =
                new RepositorioEnMemoria();

        Ruleta ruleta =
                new Ruleta(
                        500000,
                        repo
                );

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