package Vista;

import Controlador.SessionController;
import Modelo.*;

import javax.swing.*;

public class VentanaRuleta {

    private SessionController session;

    public VentanaRuleta(SessionController session){
        this.session = session;
    }

    public void mostrar(){
        jugar();
    }

    public void jugar(){

        try {

            String opcion =
                    JOptionPane.showInputDialog(
                            "ROJO / NEGRO / PAR / IMPAR"
                    );

            if(opcion == null){
                return;
            }

            int monto =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    "Monto:"
                            )
                    );

            ApuestaBase apuesta;

            switch (opcion.toUpperCase()) {

                case "ROJO":
                    apuesta = new ApuestaRojo(monto);
                    break;

                case "NEGRO":
                    apuesta = new ApuestaNegro(monto);
                    break;

                case "PAR":
                    apuesta = new ApuestaPar(monto);
                    break;

                case "IMPAR":
                    apuesta = new ApuestaImpar(monto);
                    break;

                default:
                    JOptionPane.showMessageDialog(
                            null,
                            "Tipo de apuesta inválido"
                    );
                    return;
            }

            Resultado r =
                    session.getRuletaController()
                            .jugar(apuesta);

            JOptionPane.showMessageDialog(
                    null,
                    "Número: " + r.getNumero()
                            + "\nTipo: " + r.getTipo()
                            + "\nResultado: "
                            + (r.isGano()
                            ? "Ganaste"
                            : "Perdiste")
                            + "\nSaldo actual: $"
                            + session
                            .getRuletaController()
                            .getSaldo()
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    null,
                    "Debe ingresar un monto numérico.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}