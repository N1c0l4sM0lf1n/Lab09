package Modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApuestaTest {

    @Test
    void crearApuestaRojoValida() {

        ApuestaRojo apuesta = new ApuestaRojo(100);

        assertEquals(100, apuesta.getMonto());
        assertEquals("Rojo", apuesta.getEtiqueta());
    }

    @Test
    void montoNegativoDebeFallar() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new ApuestaRojo(-100)
        );
    }

    @Test
    void montoCeroDebeFallar() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new ApuestaRojo(0)
        );
    }

    @Test
    void todasLasApuestasDebenSerPolimorficas() {

        ApuestaBase[] apuestas = {

                new ApuestaRojo(10),
                new ApuestaNegro(10),
                new ApuestaPar(10),
                new ApuestaImpar(10)
        };

        for (ApuestaBase apuesta : apuestas) {

            assertNotNull(apuesta);
            assertNotNull(apuesta.getEtiqueta());
            assertTrue(apuesta.getMonto() > 0);
        }
    }
}