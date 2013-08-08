/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nodos;

/**
 *
 * @author Julio
 */
public class NodoNumero extends NodoBase {

    private Integer numero;

    public NodoNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getNumero() {
        return numero;
    }
}