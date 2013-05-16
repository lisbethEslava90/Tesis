/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pruebaide;

import javax.swing.JPanel;

/**
 *
 * @author Julio
 */
public class Resultado extends JPanel{

    private String name;

    public Resultado(String nombre) {
        this.name = nombre;

    }
    public String getNombre(){

        return name;
    }
}
