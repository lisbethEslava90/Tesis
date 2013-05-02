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
    private int TIPO_OPE;
    private NodoBase opIzq,opDer;

    public NodoOperacion(int TIPO_OPE, NodoBase opIzq, NodoBase opDer) {
        this.TIPO_OPE = TIPO_OPE;
        this.opIzq = opIzq;
        this.opDer = opDer;
    }

    public int getTIPO_OPE() {
        return TIPO_OPE;
    }

    public NodoBase getOpDer() {
        return opDer;
    }

    public NodoBase getOpIzq() {
        return opIzq;
    }
    
}
