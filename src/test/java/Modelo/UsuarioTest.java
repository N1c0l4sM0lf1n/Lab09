package Modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void credencialesCorrectas() {

        Usuario usuario =
                new Usuario(
                        "nico",
                        "1234",
                        "Nicolas"
                );

        assertTrue(
                usuario.validarCredenciales(
                        "nico",
                        "1234"
                )
        );
    }

    @Test
    void passwordIncorrecta() {

        Usuario usuario =
                new Usuario(
                        "nico",
                        "1234",
                        "Nicolas"
                );

        assertFalse(
                usuario.validarCredenciales(
                        "nico",
                        "xxxx"
                )
        );
    }

    @Test
    void usernameIncorrecto() {

        Usuario usuario =
                new Usuario(
                        "nico",
                        "1234",
                        "Nicolas"
                );

        assertFalse(
                usuario.validarCredenciales(
                        "otro",
                        "1234"
                )
        );
    }
}