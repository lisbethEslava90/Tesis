/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pruebaide;

import com.google.common.collect.Table;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Julio
 */
public class Resultado extends JPanel{

    private String name;
    private JTable tabla;
    private DefaultTableModel modelo;

    public Resultado(String nombre) {
        this.name = nombre;
        tabla = new JTable();
        tabla.setBounds(30, 10, 500, 210);
        this.add(tabla);
        this.setLayout(null);

    }
    public String getNombre(){
        return name;
    }
    public void SetAreaResultado(Table datos){
        modelo = new DefaultTableModel(datos.rowKeySet().size(),datos.columnKeySet().size());
        
        for(int i=0; i<datos.rowKeySet().size(); i++){
            for(int j=0; j<datos.columnKeySet().size(); j++){
                modelo.setValueAt(datos.get(i, j), i, j);                
            }
        }
        
        tabla.setModel(modelo);
    }
}
