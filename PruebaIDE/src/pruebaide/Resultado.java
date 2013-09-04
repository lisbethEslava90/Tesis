/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pruebaide;

import com.google.common.collect.Table;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Julio
 */
public class Resultado extends JPanel{

    private String name;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JScrollPane scroll;

    public Resultado(String nombre) {
        this.name = nombre;
        tabla = new JTable();
        scroll = new JScrollPane(tabla);
        this.add(scroll);
    }
    public String getNombre(){
        return name;
    }
    public void SetAreaResultado(Table datos){
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