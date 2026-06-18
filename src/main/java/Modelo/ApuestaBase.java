package Modelo;

public abstract class ApuestaBase {

    protected int monto;
    protected String etiqueta;

    public ApuestaBase(int monto, String etiqueta) {

        if (monto <= 0) {
            throw new IllegalArgumentException(
                    "El monto debe ser mayor que cero."
            );
        }

        if (etiqueta == null || etiqueta.isBlank()) {
            throw new IllegalArgumentException(
                    "La etiqueta no puede estar vacía."
            );
        }

        this.monto = monto;
        this.etiqueta = etiqueta;
    }

    public int getMonto() {
        return monto;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public abstract boolean acierta(int numero, String color);
}