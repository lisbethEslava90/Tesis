/*
 * Generar una nueva pestaña cada vez que el usuario desee crear una nueva consulta
 *
 */
package pruebaide;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;

/**
 *
 * @author Julio
 */
public class NuevaConsulta extends JPanel implements FocusListener {

    private String consulta = "", nom = "";
    private JTextArea areaConsulta;
    private JLabel nombre;
    private JTextField nombreConsulta;
    public JButton ejecutar, cambiarNombre;
    private Resultado resultado;
    final private Aplicacion aplicacion;

    public NuevaConsulta(int pestana, final Aplicacion aplicacion) {

        this.aplicacion = aplicacion;
        areaConsulta = new JTextArea();
        nombre = new JLabel("Nombre:");
        nombreConsulta = new JTextField("Consulta 1");
        ejecutar = new JButton("Ejecutar");
        cambiarNombre = new JButton("Cambiar Nombre");

        resultado = new Resultado("Resultado " + pestana);
        this.setLayout(null);

        areaConsulta.setBounds(30, 10, 500, 100);

        nombre.setBounds(65, 130, 70, 30);
        nombre.setFont(new Font("Tahoma", Font.BOLD, 12));

        nombreConsulta.setBounds(120, 130, 150, 30);
        nombreConsulta.setText("Consulta " + pestana);

        ejecutar.setBounds(400, 130, 100, 30);
        cambiarNombre.setBounds(270, 130, 130, 30);

        this.add(ejecutar);
        this.add(cambiarNombre);
        this.add(nombre);
        this.add(nombreConsulta);
        this.add(areaConsulta);

        nombreConsulta.addFocusListener(this);

        cambiarNombre.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < aplicacion.getListaPestana().size(); i++) {
                    if (aplicacion.getListaPestana().get(i).isVisible()) {
                        aplicacion.CambiarNombre(i, nombreConsulta.getText());

                        break;
                    }
                }
            }
        });

        ejecutar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //System.out.println(""+recibirSimbolos(consulta));
            }
        });
    }

    //recibe el simbolo o tipo de consulta seleccionada por el usuario
    public void recibirSimbolos(String simbolo) {

        consulta = areaConsulta.getText() + " " + simbolo;
        areaConsulta.setText(consulta);
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void focusGained(FocusEvent e) {
        for (int i = 0; i < aplicacion.getListaPestana().size(); i++) {
            if (aplicacion.getListaPestana().get(i).isVisible()) {
                if (nombreConsulta.getText().equals("Consulta " + (i + 1))) {
                    nombreConsulta.setText("");
                }
            }
        }
    }

    public void focusLost(FocusEvent e) {
        for (int i = 0; i < aplicacion.getListaPestana().size(); i++) {
            if (aplicacion.getListaPestana().get(i).isVisible()) {
                if (nombreConsulta.getText().equals("")) {
                    nombreConsulta.setText("Consulta "+(i+1));
                }
            }
        }
    }
}
