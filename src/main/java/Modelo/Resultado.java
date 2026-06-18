package Modelo;

public class Resultado {

    private int numero;
    private String tipo;
    private int monto;
    private boolean gano;

    public Resultado(int numero, String tipo, int monto, boolean gano) {

        if (numero < 0 || numero > 36) {
            throw new IllegalArgumentException(
                    "El número debe estar entre 0 y 36."
            );
        }

        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException(
                    "El tipo de apuesta es obligatorio."
            );
        }

        if (monto <= 0) {
            throw new IllegalArgumentException(
                    "El monto debe ser mayor que cero."
            );
        }

        this.numero = numero;
        this.tipo = tipo;
        this.monto = monto;
        this.gano = gano;
    }

    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public int getMonto() {
        return monto;
    }

    public boolean isGano() {
        return gano;
    }
}