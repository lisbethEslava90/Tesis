/*
 * Generar una nueva pestaña cada vez que el usuario desee crear una nueva consulta
 *
 */

package pruebaide;

import java.awt.Font;
import javax.swing.*;
/**
 *
 * @author Julio
 */
public class NuevaConsulta extends  JPanel{

    //DARLE MEMORIA EN EL CONSTRUCTO  A TOOOODOOO!!!!

    private String consulta = "";
    private JTextArea areaConsulta;
    private JLabel nombre;
    private JTextField nombreConsulta;
    public JButton ejecutar;
    private Resultado resultado;
    
    public NuevaConsulta(int pestana) {

        areaConsulta = new JTextArea();
        nombre = new JLabel("Nombre:");
        nombreConsulta = new JTextField("Consulta 1");
        ejecutar = new JButton("Ejecutar");

        resultado = new Resultado("Resultado "+pestana);
        this.setLayout(null);

        areaConsulta.setBounds(30,10,500,100);

        nombre.setBounds(110, 130, 70, 30);
        nombre.setFont(new Font("Tahoma", Font.BOLD, 12));

        nombreConsulta.setBounds(170, 130, 150, 30);
        nombreConsulta.setText("Consulta "+pestana);

        ejecutar.setBounds(330, 130, 100, 30);

        this.add(ejecutar);
        this.add(nombre);
        this.add(nombreConsulta);
        this.add(areaConsulta);
        
    }

    public void maximizar(){

    }

    //recibe el simbolo o tipo de consulta seleccionada por el usuario
    public void recibirSimbolos (String simbolo){
        
        consulta = areaConsulta.getText()+ " " + simbolo;
        areaConsulta.setText(consulta);
    }

    public Resultado getResultado() {
        return resultado;
    }

}