/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VentanaSplit.java
 *
 * Created on 19-abr-2013, 13:28:49
 */

package pruebaide;

import java.awt.Cursor;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import interprete.Funciones;

/**
 *
 * @author Julio
 */
public class Aplicacion extends javax.swing.JFrame {

    private int contPestanas = 1;
    //private NuevaConsulta pestana = new NuevaConsulta(contPestanas);
    private ArrayList<NuevaConsulta> ListaPestana;// = new ArrayList<NuevaConsulta>();
    private ArrayList<Resultado> ListaResultado;
    final NuevaConsulta pestana;
    private Vector<String> consultas;
    private JFileChooser archivo;
    private Funciones funciones;
    private Resultado resultado;

    /** Creates new form VentanaSplit */
    public Aplicacion() {
        initComponents();

        funciones = new Funciones();
        resultado = new Resultado(null);
        archivo = new JFileChooser("relaciones/");
        consultas = new Vector<String>();
        this.setLocationRelativeTo(null);
        pestana = new NuevaConsulta(contPestanas, this);
        ListaPestana = new ArrayList<NuevaConsulta>();
        ListaPestana.add(new NuevaConsulta(contPestanas,this));
        ListaResultado = new ArrayList<Resultado>();
        this.ZonaConsulta.addTab("Consulta 1", ListaPestana.get(0)); //add(pestana);
        Resultado auxiliar = ListaPestana.get(0).getResultado();
        ListaResultado.add(auxiliar);
        ZonaResultado.addTab(auxiliar.getNombre(),auxiliar);
        this.jSplitPane1.setDividerLocation(0.25);
     //   this.jSplitPane2.setDividerLocation(0.4);
        this.jSplitPane3.setDividerLocation(0.7);
        consultas.add("Consulta 1");
        historialConsultas.setListData(consultas);

        archivo.addChoosableFileFilter(new FileFilter() {

            @Override
            public boolean accept(File f) {
                if(f.isDirectory()){
                    return true;
                }else{
                    return f.getName().toLowerCase().endsWith(".csv");
                }
            }
            @Override
            public String getDescription() {
                return "Archivos .csv";
            }
        });
    }

    public void CambiarNombre(int indice, String texto){
        ZonaConsulta.setTitleAt(indice, texto);
        ZonaResultado.setTitleAt(indice, "Resultado de: "+texto);
        consultas.set(indice, texto);
        historialConsultas.setListData(consultas);
    }

    public ArrayList<NuevaConsulta> getListaPestana() {
        return ListaPestana;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        ConsultaNueva = new javax.swing.JMenuItem();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        PanelIconos = new javax.swing.JPanel();
        SeleccionIcon = new javax.swing.JLabel();
        ProyeccionIcon = new javax.swing.JLabel();
        UnionIcon = new javax.swing.JLabel();
        InterseccionIcon = new javax.swing.JLabel();
        ProductoIcon = new javax.swing.JLabel();
        DiferenciaIcon = new javax.swing.JLabel();
        DivisionIcon = new javax.swing.JLabel();
        ReunionIcon = new javax.swing.JLabel();
        jSplitPane3 = new javax.swing.JSplitPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        ZonaConsulta = new javax.swing.JTabbedPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        ZonaResultado = new javax.swing.JTabbedPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        Carpetas = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        historialConsultas = new javax.swing.JList();
        jMenuBar1 = new javax.swing.JMenuBar();
        Archivo = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        ConsultaNueva.setText("Nueva Consulta");
        ConsultaNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultaNuevaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ConsultaNueva);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jSplitPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSplitPane1.setDividerSize(2);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jSplitPane1.setLeftComponent(jPanel1);

        PanelIconos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        SeleccionIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SeleccionIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Seleccion/sigma4.png"))); // NOI18N
        SeleccionIcon.setToolTipText("Selección");
        SeleccionIcon.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        SeleccionIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SeleccionIconMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SeleccionIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SeleccionIconMouseExited(evt);
            }
        });

        ProyeccionIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ProyeccionIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Proyeccion/pi4.png"))); // NOI18N
        ProyeccionIcon.setToolTipText("Proyección");
        ProyeccionIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProyeccionIconMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ProyeccionIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ProyeccionIconMouseExited(evt);
            }
        });

        UnionIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        UnionIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Union/u4.png"))); // NOI18N
        UnionIcon.setToolTipText("Unión");
        UnionIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UnionIconMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                UnionIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                UnionIconMouseExited(evt);
            }
        });

        InterseccionIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        InterseccionIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Interseccion/inter4.png"))); // NOI18N
        InterseccionIcon.setToolTipText("Intersección");
        InterseccionIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InterseccionIconMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                InterseccionIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                InterseccionIconMouseExited(evt);
            }
        });

        ProductoIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ProductoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Producto/xs4.png"))); // NOI18N
        ProductoIcon.setToolTipText("Producto Cartesiano");
        ProductoIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProductoIconMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ProductoIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ProductoIconMouseExited(evt);
            }
        });

        DiferenciaIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DiferenciaIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Diferencia/menos3.png"))); // NOI18N
        DiferenciaIcon.setToolTipText("Diferencia");
        DiferenciaIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DiferenciaIconMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DiferenciaIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DiferenciaIconMouseExited(evt);
            }
        });

        DivisionIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DivisionIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Division/division4.png"))); // NOI18N
        DivisionIcon.setToolTipText("División");
        DivisionIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DivisionIconMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DivisionIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DivisionIconMouseExited(evt);
            }
        });

        ReunionIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ReunionIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Reunion/join4.png"))); // NOI18N
        ReunionIcon.setToolTipText("Reunión Natural");
        ReunionIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReunionIconMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ReunionIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ReunionIconMouseExited(evt);
            }
        });

        javax.swing.GroupLayout PanelIconosLayout = new javax.swing.GroupLayout(PanelIconos);
        PanelIconos.setLayout(PanelIconosLayout);
        PanelIconosLayout.setHorizontalGroup(
            PanelIconosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelIconosLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(SeleccionIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ProyeccionIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UnionIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ProductoIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(InterseccionIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(DiferenciaIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(DivisionIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ReunionIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(261, Short.MAX_VALUE))
        );
        PanelIconosLayout.setVerticalGroup(
            PanelIconosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelIconosLayout.createSequentialGroup()
                .addGroup(PanelIconosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DivisionIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ReunionIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DiferenciaIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InterseccionIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProductoIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UnionIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProyeccionIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SeleccionIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jSplitPane3.setDividerLocation(200);
        jSplitPane3.setDividerSize(2);
        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        ZonaConsulta.setBackground(new java.awt.Color(255, 255, 255));
        ZonaConsulta.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ZonaConsultaStateChanged(evt);
            }
        });
        jScrollPane3.setViewportView(ZonaConsulta);

        jSplitPane3.setTopComponent(jScrollPane3);

        ZonaResultado.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ZonaResultadoStateChanged(evt);
            }
        });
        jScrollPane4.setViewportView(ZonaResultado);

        jSplitPane3.setRightComponent(jScrollPane4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
            .addComponent(PanelIconos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(PanelIconos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(jPanel2);

        jSplitPane2.setDividerLocation(80);
        jSplitPane2.setDividerSize(2);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jScrollPane1.setViewportView(Carpetas);

        jSplitPane2.setTopComponent(jScrollPane1);

        historialConsultas.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        historialConsultas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                historialConsultasMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(historialConsultas);

        jSplitPane2.setRightComponent(jScrollPane2);

        jSplitPane1.setLeftComponent(jSplitPane2);

        Archivo.setText("Archivo");
        Archivo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jMenuItem1.setText("Nueva Relación");
        jMenuItem1.setName("NuevaRelacion"); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        Archivo.add(jMenuItem1);

        jMenuItem2.setText("Cargar Relación");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        Archivo.add(jMenuItem2);

        jMenuBar1.add(Archivo);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:
        this.jSplitPane2.setDividerLocation(0.53);
        this.jSplitPane3.setDividerLocation(0.45);
    }//GEN-LAST:event_formComponentResized

    private void SeleccionIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SeleccionIconMouseEntered
        // TODO add your handling code here:
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_SeleccionIconMouseEntered

    private void SeleccionIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SeleccionIconMouseExited
        // TODO add your handling code here:
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_SeleccionIconMouseExited

    private void ProyeccionIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProyeccionIconMouseEntered
        // TODO add your handling code here:
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_ProyeccionIconMouseEntered

    private void ProyeccionIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProyeccionIconMouseExited
        // TODO add your handling code here:
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_ProyeccionIconMouseExited

    private void UnionIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UnionIconMouseEntered
        // TODO add your handling code here:
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_UnionIconMouseEntered

    private void UnionIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UnionIconMouseExited
        // TODO add your handling code here:
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_UnionIconMouseExited

    private void ProductoIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProductoIconMouseEntered
        // TODO add your handling code here:
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_ProductoIconMouseEntered

    private void ProductoIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProductoIconMouseExited
        // TODO add your handling code here:
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_ProductoIconMouseExited

    private void InterseccionIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InterseccionIconMouseEntered
        // TODO add your handling code here:
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_InterseccionIconMouseEntered

    private void InterseccionIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InterseccionIconMouseExited
        // TODO add your handling code here:
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_InterseccionIconMouseExited

    private void DiferenciaIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DiferenciaIconMouseEntered
        // TODO add your handling code here:
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_DiferenciaIconMouseEntered

    private void DiferenciaIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DiferenciaIconMouseExited
        // TODO add your handling code here:
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_DiferenciaIconMouseExited

    private void DivisionIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DivisionIconMouseEntered
        // TODO add your handling code here:
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_DivisionIconMouseEntered

    private void DivisionIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DivisionIconMouseExited
        // TODO add your handling code here:
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_DivisionIconMouseExited

    private void ReunionIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReunionIconMouseEntered
        // TODO add your handling code here:
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_ReunionIconMouseEntered

    private void ReunionIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReunionIconMouseExited
        // TODO add your handling code here:
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_ReunionIconMouseExited

    private void SeleccionIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SeleccionIconMouseClicked
        // TODO add your handling code here:
        agregarSimbolo("SEL");
    }//GEN-LAST:event_SeleccionIconMouseClicked

    private void ProyeccionIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProyeccionIconMouseClicked
        // TODO add your handling code here:
        agregarSimbolo("PRO");
    }//GEN-LAST:event_ProyeccionIconMouseClicked

    private void UnionIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UnionIconMouseClicked
        // TODO add your handling code here:
        agregarSimbolo("UNI");
    }//GEN-LAST:event_UnionIconMouseClicked

    private void ProductoIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProductoIconMouseClicked
        // TODO add your handling code here:
        agregarSimbolo("PROC");
    }//GEN-LAST:event_ProductoIconMouseClicked

    private void InterseccionIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InterseccionIconMouseClicked
        // TODO add your handling code here:
        agregarSimbolo("INT");
    }//GEN-LAST:event_InterseccionIconMouseClicked

    private void DiferenciaIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DiferenciaIconMouseClicked
        // TODO add your handling code here:
        agregarSimbolo("DIF");
    }//GEN-LAST:event_DiferenciaIconMouseClicked

    private void DivisionIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DivisionIconMouseClicked
        // TODO add your handling code here:
        agregarSimbolo("DIV");
    }//GEN-LAST:event_DivisionIconMouseClicked

    private void ReunionIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReunionIconMouseClicked
        // TODO add your handling code here:
        agregarSimbolo("REUN");
    }//GEN-LAST:event_ReunionIconMouseClicked

    private void historialConsultasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_historialConsultasMousePressed
        // TODO add your handling code here:
        if(SwingUtilities.isRightMouseButton(evt)){
            jPopupMenu1.show(evt.getComponent(),evt.getX(),evt.getY());

        }
    }//GEN-LAST:event_historialConsultasMousePressed

    private void ConsultaNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultaNuevaActionPerformed
        // TODO add your handling code here:
        contPestanas++;
        ListaPestana.add(new NuevaConsulta(contPestanas,this));
        this.ZonaConsulta.addTab("Consulta "+contPestanas, ListaPestana.get(contPestanas-1));
        Resultado auxiliar = ListaPestana.get(contPestanas-1).getResultado();
        ListaResultado.add(auxiliar);
        ZonaResultado.addTab(auxiliar.getNombre(),auxiliar);
        consultas.add("Consulta "+contPestanas);
        historialConsultas.setListData(consultas);
    }//GEN-LAST:event_ConsultaNuevaActionPerformed

    private void ZonaConsultaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ZonaConsultaStateChanged
        // TODO add your handling code here:
        if(ZonaResultado.getTabCount()==0)return;
        for(int i=0; i<ListaPestana.size(); i++)
        {
          if(ListaPestana.get(i).isVisible())
            {
                ZonaResultado.setSelectedIndex(i);
                break;
            }
        }
    }//GEN-LAST:event_ZonaConsultaStateChanged

    private void ZonaResultadoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ZonaResultadoStateChanged
        // TODO add your handling code here:
        if(ZonaConsulta.getTabCount()==0) return;;
        for(int i=0; i<ListaResultado.size(); i++){
            if(ListaResultado.get(i).isVisible()){
                ZonaConsulta.setSelectedIndex(i);
                break;
            }
        }
    }//GEN-LAST:event_ZonaResultadoStateChanged

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        
        int seleccion = archivo.showOpenDialog(null);

        switch(seleccion){
            case JFileChooser.CANCEL_OPTION:
                break;
            case JFileChooser.APPROVE_OPTION:
                funciones.cargarArchivo(archivo.getSelectedFile());
                break;
            case JFileChooser.ERROR_OPTION:
                break;
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void agregarSimbolo(String simbolo){

        for(int i=0; i<ListaPestana.size(); i++){
            //System.out.println("i: "+ i +" - "+ ListaPestana.get(i).isVisible()  );
            if(ListaPestana.get(i).isVisible())
            {
                ListaPestana.get(i).recibirSimbolos(simbolo);
                break;
            }
        }
    }

    /**
     *
    * @param args the command line arguments
    */
    public static void main(String args[]) {


        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
            javax.swing.UIManager.setLookAndFeel(info.getClassName());
            break;
            }
            }
            } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Aplicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Aplicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Aplicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Aplicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Aplicacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Archivo;
    private javax.swing.JTree Carpetas;
    private javax.swing.JMenuItem ConsultaNueva;
    private javax.swing.JLabel DiferenciaIcon;
    private javax.swing.JLabel DivisionIcon;
    private javax.swing.JLabel InterseccionIcon;
    private javax.swing.JPanel PanelIconos;
    private javax.swing.JLabel ProductoIcon;
    private javax.swing.JLabel ProyeccionIcon;
    private javax.swing.JLabel ReunionIcon;
    private javax.swing.JLabel SeleccionIcon;
    private javax.swing.JLabel UnionIcon;
    private javax.swing.JTabbedPane ZonaConsulta;
    private javax.swing.JTabbedPane ZonaResultado;
    private javax.swing.JList historialConsultas;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    // End of variables declaration//GEN-END:variables

}
