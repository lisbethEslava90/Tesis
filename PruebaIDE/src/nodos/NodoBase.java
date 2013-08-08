
package nodos;

/**
 *
 * @author Julio
 */
public class NodoBase {

    private NodoBase HermanoDerecha;

    public NodoBase() {
    }

    public NodoBase(NodoBase HermanoDerecha) {
        this.HermanoDerecha = HermanoDerecha;
    }

    public NodoBase getHermanoDerecha() {
        return HermanoDerecha;
    }

    public void setHermanoDerecha(NodoBase HermanoDerecha) {
        this.HermanoDerecha = HermanoDerecha;
    }
    
     public boolean tieneHermano() {
        return (HermanoDerecha != null);
    }

}
