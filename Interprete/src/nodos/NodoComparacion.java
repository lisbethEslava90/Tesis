/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nodos;

/**
 *
 * @author Julio
 */
public class NodoComparacion extends NodoBase{

    private NodoBase opIzquierdo;
    private NodoBase opDerecho;
    private tipoOp operacion;

    public NodoComparacion(NodoBase opIzquierdo, NodoBase opDerecho, tipoOp operacion) {
        this.opIzquierdo = opIzquierdo;
        this.opDerecho = opDerecho;
        this.operacion = operacion;
    }

    public NodoComparacion(tipoOp operacion) {
        this.opIzquierdo = null;
        this.opDerecho = null;
        this.operacion = operacion;
    }

    public NodoBase getOpDerecho() {
        return opDerecho;
    }

    public void setOpDerecho(NodoBase opDerecho) {
        this.opDerecho = opDerecho;
    }

    public NodoBase getOpIzquierdo() {
        return opIzquierdo;
    }

    public void setOpIzquierdo(NodoBase opIzquierdo) {
        this.opIzquierdo = opIzquierdo;
    }

    public tipoOp getOperacion(){
        return operacion;
    }

}
