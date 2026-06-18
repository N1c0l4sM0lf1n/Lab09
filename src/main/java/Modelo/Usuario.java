package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String username;
    private String password;
    private String nombre;
    private List<Resultado> historial = new ArrayList<>();

    public Usuario(String username,
                   String password,
                   String nombre) {

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException(
                    "El usuario es obligatorio."
            );
        }

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException(
                    "La contraseña es obligatoria."
            );
        }

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre es obligatorio."
            );
        }

        this.username = username;
        this.password = password;
        this.nombre = nombre;
    }

    public boolean validarCredenciales(String u, String p) {

        if (u == null || p == null) {
            return false;
        }

        return username.equals(u) &&
                password.equals(p);
    }

    public void agregarResultado(Resultado r) {

        if (r == null) {
            throw new IllegalArgumentException(
                    "El resultado no puede ser null."
            );
        }

        historial.add(r);
    }

    public List<Resultado> getHistorial() {
        return historial;
    }

    public String getUsername() {
        return username;
    }

    public String getNombre() {
        return nombre;
    }
}