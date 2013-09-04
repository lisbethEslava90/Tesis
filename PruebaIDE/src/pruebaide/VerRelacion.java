/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pruebaide;

import com.google.common.collect.Table;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Julio
 */
public class VerRelacion extends JDialog{

    private JPanel panel;
    private JScrollPane scroll;
    private JTable tabla;
    private DefaultTableModel modelo;

    public VerRelacion(JFrame parent, String nombre) {
        panel = new JPanel();
        tabla = new JTable();
        scroll = new JScrollPane(tabla);

        this.setTitle(nombre);
        this.setSize(800, 300);
        this.add(scroll);
        this.setLocationRelativeTo(parent);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void SetRelacion(Table datos){
        String NombreColumnas [] = new String[datos.columnKeySet().size()];
        String data[][] = new String[datos.rowKeySet().size()-1][datos.columnKeySet().size()];
        //asigno a un vector los nombres de las columnas
        for(int i=0; i<datos.columnKeySet().size(); i++){
            NombreColumnas[i] = datos.get(0, i).toString();
        }
        //asigno a una matriz la data
        for(int i=0; i<datos.rowKeySet().size()-1; i++){
            for(int j=0; j<datos.columnKeySet().size(); j++){
                data[i][j] = datos.get(i+1, j).toString();
            }
        }
        modelo = new DefaultTableModel(data, NombreColumnas);
        tabla.setModel(modelo);
        tabla.setEnabled(false);
    }
}