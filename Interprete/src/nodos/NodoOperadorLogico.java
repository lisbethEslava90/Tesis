/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nodos;

/**
 *
 * @author Julio
 */
public class NodoOperadorLogico extends NodoBase{

    private tipoOp operacion;
    private NodoBase opeI, opeD;

    public NodoOperadorLogico(tipoOp operacion, NodoBase opeI, NodoBase opeD) {
        this.operacion = operacion;
        this.opeI = opeI;
        this.opeD = opeD;
    }

    public tipoOp getOperacion() {
        return operacion;
    }

    public NodoBase getOpeD() {
        return opeD;
    }

    public NodoBase getOpeI() {
        return opeI;
    }

}
