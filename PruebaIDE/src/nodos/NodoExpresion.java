/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nodos;

/**
 *
 * @author Julio
 */
public class NodoExpresion extends  NodoBase{
    private String TIPO_EXP; //PRO o SEL
    private NodoBase predicado,relacion;

    public NodoExpresion(String TIPO_EXP, NodoBase predicado, NodoBase relacion) {
        this.TIPO_EXP = TIPO_EXP;
        this.predicado = predicado;
        this.relacion = relacion;
    }

    public String getTIPO_EXP() {
        return TIPO_EXP;
    }

    public NodoBase getPredicado() {
        return predicado;
    }

    public NodoBase getRelacion() {
        return relacion;
    }
    
}
