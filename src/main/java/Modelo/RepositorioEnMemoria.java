package Modelo;

import java.util.ArrayList;
import java.util.List;

public class RepositorioEnMemoria
        implements IRepositorioResultados {

    private final List<Resultado> resultados =
            new ArrayList<>();

    @Override
    public void guardarResultado(Resultado resultado) {

        if (resultado == null) {
            throw new IllegalArgumentException(
                    "No se puede guardar un resultado null."
            );
        }

        resultados.add(resultado);
    }
    @Override
    public List<Resultado> obtenerResultados() {
        return resultados;
    }
}