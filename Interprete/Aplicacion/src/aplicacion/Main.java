/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;

/**
 *
 * @author Julio
 */

import Vista.Vista;
import java.awt.*;
import javax.swing.*;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Vista vista = new Vista(new Dimension(1000, 800));
       // vista.setDefaultLookAndFeelDecorated(true);
        vista.setDefaultCloseOperation(vista.EXIT_ON_CLOSE);
        vista.setVisible(true);
    }

}
