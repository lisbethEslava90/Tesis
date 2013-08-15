/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pruebaide;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Julio
 */
public class VentanaRelacion extends JDialog implements FocusListener {

    private JPanel panel, panelIzq, panelDer, panelInferior;
    private JButton AgregarColumna, EliminarColumna, InsertarFila, EliminarFila, CrearRelacion, cancelar;
    private JLabel etiquetaNombre;
    private JTextField nombreRelacion;
    private JScrollPane scroll;
    private JTable tabla;
    private DefaultTableModel modelo;
    private File archivo;
    private int fila = -1, columna = -1;

    public VentanaRelacion(JFrame parent) {
        super(parent, true);

        String [] NombreColumnas = {"Columna1", "Columna 2"};
        final String [][] data = {{"Valor 1", "Valor 2"}};
        modelo = new DefaultTableModel(data,NombreColumnas);
        tabla = new JTable(modelo);
        tabla.setModel(modelo);

        panel = new JPanel();
        panelIzq = new JPanel();
        panelDer = new JPanel();
        panelInferior = new JPanel();
        scroll = new JScrollPane();      
        AgregarColumna = new JButton("Agregar Columna");
        EliminarColumna = new JButton("Eliminar Columna");
        InsertarFila = new JButton("Insertar Fila");
        EliminarFila = new JButton("Eliminar Fila");
        etiquetaNombre = new JLabel("Relación: ");
        nombreRelacion = new JTextField("Relacion");
        CrearRelacion = new JButton("Crear Relación");
        cancelar = new JButton("Cancelar");
        etiquetaNombre.setBounds(55, 10, 55, 30);
        nombreRelacion.setBounds(115, 10, 120, 30);
        CrearRelacion.setBounds(240, 10, 120, 30);
        cancelar.setBounds(365, 10, 90, 30);

        nombreRelacion.addFocusListener(this);
        scroll.setViewportView(tabla);

        panel.setLayout(null);
        panelIzq.setLayout(null);
        panelDer.setLayout(null);
        panelInferior.setLayout(null);
        panelIzq.setBounds(5, 5, 150, 310);
        panelIzq.setBorder(BorderFactory.createEtchedBorder());

        panelInferior.setBounds(5, 320, 485, 50);
        panelInferior.setBorder(BorderFactory.createEtchedBorder());
        panelInferior.add(etiquetaNombre);
        panelInferior.add(nombreRelacion);
        panelInferior.add(CrearRelacion);
        panelInferior.add(cancelar);
        
        AgregarColumna.setBounds(10, 30, 130, 30);
        EliminarColumna.setBounds(10, 100, 130, 30);
        InsertarFila.setBounds(10, 170, 130, 30);
        EliminarFila.setBounds(10, 240, 130, 30);


        AgregarColumna.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modelo.addColumn("New Column");
                tabla.repaint();
            }
        });

        InsertarFila.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modelo.addRow(new Object[modelo.getRowCount()+1]);
                tabla.repaint();
            }
        });

        //En que posicion de la tabla hace click
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fila = tabla.rowAtPoint(e.getPoint());
                columna = tabla.columnAtPoint(e.getPoint());
            }
        });

        EliminarFila.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(modelo.getRowCount() > 1){
                    //Borrar siempre la ultima
                    modelo.removeRow(modelo.getRowCount()-1);
                    //Borrar la que haya seleccionado o la última
//                    if(fila > -1)
//                        modelo.removeRow(fila);
//                    else
//                        modelo.removeRow(modelo.getRowCount()-1);
                }
            }
        });

        EliminarColumna.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(modelo.getColumnCount() > 1){
                    //Borrar siempre la ultima
                    modelo = removeCol(modelo.getColumnCount()-1);
                    tabla.setModel(modelo);
                    //Borrar la que haya seleccionado o la ultima
//                    if(columna > -1){
//                        modelo = removeCol(columna);
//                        tabla.setModel(modelo);
//                        columna = -1;
//                    }else{
//                        modelo = removeCol(modelo.getColumnCount()-1);
//                        tabla.setModel(modelo);
//                    }
                }
            }
        });

        CrearRelacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                archivo = new File("relaciones/",nombreRelacion.getText()+".csv");
                FileWriter writer;
                PrintWriter salida = null;
                String texto = "";

                try {
                    writer = new FileWriter(archivo);
                    salida = new PrintWriter(writer);
                } catch (IOException ex) {
                    Logger.getLogger(VentanaRelacion.class.getName()).log(Level.SEVERE, null, ex);
                }

                for(int i=0; i < modelo.getRowCount(); i++){
                    for(int j=0; j < modelo.getColumnCount(); j++){
                        System.out.println(""+modelo.getValueAt(i, j));
                        if(modelo.getValueAt(i, j)==null)
                            modelo.setValueAt("", i, j);
                        if(j != modelo.getColumnCount()-1)
                            texto = "\""+modelo.getValueAt(i, j).toString()+"\""+",";
                        else
                            texto = "\""+modelo.getValueAt(i, j).toString()+"\"";
                        salida.write(texto);
                    }
                    texto = "\n";
                    salida.write(texto);
                }
                try {
                    archivo.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(VentanaRelacion.class.getName()).log(Level.SEVERE, null, ex);
                }
                salida.close();
                VentanaRelacion.this.dispose();
            }
        });

        cancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaRelacion.this.dispose();
            }
        });
        
        panelIzq.add(AgregarColumna);
        panelIzq.add(EliminarColumna);
        panelIzq.add(InsertarFila);
        panelIzq.add(EliminarFila);

        panelDer.setBounds(160, 5, 330, 310);
        panelDer.setBorder(BorderFactory.createEtchedBorder());

        tabla.setBounds(5, 5, 320, 300);
        tabla.setBorder(BorderFactory.createLineBorder(Color.BLACK));        
        panelDer.add(tabla);
        //panelDer.add(scroll);

        panel.add(panelIzq);
        panel.add(panelDer);
        panel.add(panelInferior);

        this.setResizable(false);
        this.setSize(500, 400);
        this.add(panel);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);

    }

     public void focusGained(FocusEvent e) {
         if(nombreRelacion.getText().equals("Relacion"))
             nombreRelacion.setText("");
    }

    public void focusLost(FocusEvent e) {
        if(nombreRelacion.getText().equals(""))
            nombreRelacion.setText("Relacion");
    }

    private DefaultTableModel removeCol(int id){
        DefaultTableModel tmp = new DefaultTableModel();
        int columnas = modelo.getColumnCount();
        for(int i=0;i<columnas;i++){
            if(i!=id)
                tmp.addColumn(modelo.getColumnName(i));
        }
        int rows = modelo.getRowCount();
        String datos[] = new String[columnas-1];
        for(int row=0;row<rows;row++){
            for(int col=0,sel=0;col<columnas;col++,sel++){
                if(col!=id)
                    datos[sel] = (String) modelo.getValueAt(row, col);
                else
                    sel--;
            }
            tmp.addRow(datos);
        }
        return tmp;
    }
}