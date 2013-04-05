/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

/**
 *
 * @author Julio
 */
import java.awt.*;
import  javax.swing.*;

public class Vista extends JFrame{

    JPanel panelIzquierdo = new JPanel();
    JPanel panelDerecho = new JPanel();

    public Vista(Dimension tamano){

        Container contenedor = getContentPane();
        JTextArea areaIzq = new JTextArea(" ");

        Dimension fullscreen = Toolkit.getDefaultToolkit().getScreenSize();
	fullscreen.width=fullscreen.width-400;
	fullscreen.height=fullscreen.height-400;
	setBounds(50,50,fullscreen.width,fullscreen.height);

        areaIzq.setBounds(0, 0, 100, 600);
        areaIzq.setEditable(false);
        areaIzq.setColumns(20);
        areaIzq.setRows(40);

       // panelIzquierdo.setBackground(Color.red);
        //panelDerecho.setBackground(Color.blue);
        panelIzquierdo.add(new JSeparator(JSeparator.VERTICAL));
        contenedor.add(areaIzq);
        contenedor.add(panelIzquierdo, BorderLayout.WEST);
        contenedor.add(panelDerecho, BorderLayout.CENTER);

        setPreferredSize(tamano);
        setFocusable(true);
        setTitle("Álgebra");


    }

    public void inicio(){

        
        
        JPanel panelDerecho = new JPanel();

        

        
        //getContentPane().add(panelIzquierdo,BorderLayout.WEST);
    }
}
