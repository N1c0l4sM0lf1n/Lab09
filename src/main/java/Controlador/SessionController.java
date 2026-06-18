package Controlador;

import Modelo.*;

public class SessionController {

    private Usuario usuarioActual;
    private RuletaController ruletaController;
    private ResultadoController resultadoController;

    public void registrarUsuario(String u,
                                 String p,
                                 String n) {

        if (u == null || u.isBlank()) {
            throw new IllegalArgumentException(
                    "Debe ingresar un nombre de usuario."
            );
        }

        if (p == null || p.isBlank()) {
            throw new IllegalArgumentException(
                    "Debe ingresar una contraseña."
            );
        }

        if (n == null || n.isBlank()) {
            throw new IllegalArgumentException(
                    "Debe ingresar un nombre."
            );
        }

        usuarioActual = new Usuario(u, p, n);

        ArchivoUsuario.guardarUsuario(usuarioActual);

        IRepositorioResultados repo =
                new RepositorioEnMemoria();

        Ruleta ruleta =
                new Ruleta(1000, repo);

        ruletaController =
                new RuletaController(ruleta, this);

        resultadoController =
                new ResultadoController(this, repo);
    }

    public boolean iniciarSesion(String u,
                                 String p) {

        if (u == null || p == null) {
            return false;
        }

        return usuarioActual != null &&
                usuarioActual.validarCredenciales(u, p);
    }

    public Usuario getUsuario(){
        return usuarioActual;
    }

    public RuletaController getRuletaController(){
        return ruletaController;
    }

    public ResultadoController getResultadoController(){
        return resultadoController;
    }

    public void cerrarSesion() {
        usuarioActual = null;
    }


}