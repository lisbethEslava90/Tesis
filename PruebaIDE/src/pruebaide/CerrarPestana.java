/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pruebaide;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Julio
 */
public class CerrarPestana extends JPanel{

    private int pos;
    private JLabel lblTitle;
    private JButton btnClose;
    private JTabbedPane pestana;

    public CerrarPestana(JTabbedPane tabbedPane, final JComponent c, String title, Icon icon, int contador, final JTabbedPane resultado){
        pestana = tabbedPane;
        //tabbedPane.addTab(null,c);
        pestana.addTab(null, c);
        //pos = tabbedPane.indexOfTabComponent(c);
        System.out.println("Contador: "+contador);
        FlowLayout f = new FlowLayout(FlowLayout.CENTER,5,0);
        this.setLayout(f);
        this.setOpaque(false);

        lblTitle = new JLabel(title);

        btnClose = new JButton();
        btnClose.setOpaque(false);
        btnClose.setRolloverIcon(icon);
        btnClose.setRolloverEnabled(true);
        btnClose.setIcon(icon);
        btnClose.setBorder(null);
        btnClose.setFocusable(false);

        this.add(lblTitle);
        this.add(btnClose);
        this.setBorder(BorderFactory.createEmptyBorder(2,0,0,0));

        pestana.setTabComponentAt(contador-1, this);
        
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pos = pestana.getSelectedIndex();
                pestana.remove(c);
                resultado.remove(pos);
            }
        });

    }
}
