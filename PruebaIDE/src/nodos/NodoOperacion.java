/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nodos;

/**
 *
 * @author Julio
 */
public class NodoOperacion extends NodoBase {
    private String TIPO_OPE;
    private NodoBase opIzq,opDer;

    public NodoOperacion(String TIPO_OPE, NodoBase opIzq, NodoBase opDer) {
        this.TIPO_OPE = TIPO_OPE;
        this.opIzq = opIzq;
        this.opDer = opDer;
    }

    public String getTIPO_OPE() {
        return TIPO_OPE;
    }

    public NodoBase getOpDer() {
        return opDer;
    }

    public NodoBase getOpIzq() {
        return opIzq;
    }
    
}
