/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pruebaide;

import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Julio
 */
public class Resultado extends JPanel{

    private String name;
    private JTextArea areaResultado;

    public Resultado(String nombre) {
        this.name = nombre;

        areaResultado = new JTextArea();
        areaResultado.setBounds(30, 10, 500, 210);
        this.setLayout(null);
        this.add(areaResultado);
    }
    public String getNombre(){
        return name;
    }
    public void SetAreaResultado(String texto){
        areaResultado.setText(texto);
    }
}
