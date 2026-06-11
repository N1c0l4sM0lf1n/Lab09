package Controlador;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionControllerTest {

    @Test
    void loginCorrecto() {

        SessionController session =
                new SessionController();

        session.registrarUsuario(
                "nico",
                "1234",
                "Nicolas"
        );

        assertTrue(
                session.iniciarSesion(
                        "nico",
                        "1234"
                )
        );
    }

    @Test
    void loginUsuarioInexistente() {

        SessionController session =
                new SessionController();

        assertFalse(
                session.iniciarSesion(
                        "fantasma",
                        "1234"
                )
        );
    }

    @Test
    void loginUsernameNull() {

        SessionController session =
                new SessionController();

        session.registrarUsuario(
                "nico",
                "1234",
                "Nicolas"
        );

        assertFalse(
                session.iniciarSesion(
                        null,
                        "1234"
                )
        );
    }

    @Test
    void cerrarSesionDebeEliminarUsuario() {

        SessionController session =
                new SessionController();

        session.registrarUsuario(
                "nico",
                "1234",
                "Nicolas"
        );

        session.cerrarSesion();

        assertNull(
                session.getUsuario()
        );
    }
}