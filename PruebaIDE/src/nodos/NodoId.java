/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nodos;

/**
 *
 * @author Julio
 */
public class NodoId extends NodoBase{

    private String id, sinonimo=null;

    public NodoId(String id) {
        this.id = id;
    }

    public NodoId(String id, String sinonimo) {
        this.id = id;
        this.sinonimo = sinonimo;
    }

    public String getId() {
        return id;
    }

    public String getSinonimo() {
        return sinonimo;
    }

}
