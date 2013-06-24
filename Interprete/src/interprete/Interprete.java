/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interprete;
import nodos.*;

/**
 *
 * @author Julio
 */
public class Interprete {
    
    private NodoBase raiz;
    Funciones funcion = new Funciones();

    public Interprete(NodoBase root) {
        this.raiz = root;
    }

    public void inicio(){
        interpretarNodo(raiz);
    }

    public void interpretarNodo(NodoBase nodo){
        NodoBase nodoActual = nodo;
        while (nodoActual != null){

            if(nodoActual instanceof NodoComparacion){
                System.out.println("NodoComparacion");
                //imprimirNodo( (NodoComparacion) raiz);
            }else if(nodoActual instanceof NodoExpresion){
                System.out.println("NodoExpresion");
                nodoExpresion((NodoExpresion)raiz);
            }else if(nodoActual instanceof NodoFecha){
                System.out.println("NodoFecha");
                //System.out.println("Fecha: "+((NodoFecha)raiz).getFecha());
            }else if(nodoActual instanceof NodoId){
                System.out.println("NodoId");
                //imprimirNodo((NodoId)raiz);
            }else if(nodoActual instanceof  NodoNumero){
                System.out.println("NodoNumero");
                //imprimirNodo((NodoNumero)raiz);
            }else if(nodoActual instanceof NodoOperacion){
                System.out.println("NodoOperacion");
                nodoOperacion((NodoOperacion)raiz);
            }else if(nodoActual instanceof NodoOperadorLogico){
                System.out.println("NodoOperadorLogico");
                //imprimirNodo((NodoOperadorLogico)raiz);
            }else System.out.println("Nodo Desconocido");

            if(nodoActual instanceof NodoComparacion){
//               System.out.println("**Expr izq opeCompara**");
//               imprimirArbol(((NodoComparacion)raiz).getOpIzquierdo());
//               System.out.println("**Expr der opeCompara**");
//               imprimirArbol(((NodoComparacion)raiz).getOpDerecho());
            }
            else if(nodoActual instanceof NodoExpresion){
                //System.out.println("**Relacion**");
                //Expresion(((NodoExpresion)raiz).getRelacion());
                //System.out.println("**Predicado**");
//                imprimirArbol(((NodoExpresion)raiz).getPredicado());
            }
            else if(nodoActual instanceof NodoOperacion){
//                System.out.println("**Operacion**");
//                imprimirArbol(((NodoOperacion)raiz).getOpIzq());
//                imprimirArbol(((NodoOperacion)raiz).getOpDer());
            }
            else if(nodoActual instanceof NodoOperadorLogico){
//                System.out.println("*exp izq opeLogico*");
//                imprimirArbol(((NodoOperadorLogico)raiz).getOpeI());
//                System.out.println("*exp der opeLogico");
//                imprimirArbol(((NodoOperadorLogico)raiz).getOpeD());
            }
            nodoActual = nodoActual.getHermanoDerecha();
        }
    }

    private void nodoExpresion(NodoExpresion nodoExpresion){
        if(nodoExpresion.getTIPO_EXP().equals("SEL")){
            System.out.println("SEL");
        }
        else if(nodoExpresion.getTIPO_EXP().equals("PRO")){
            System.out.println("PRO");
            
        }
    }

    private void nodoOperacion(NodoOperacion nodoOperacion){
        String sel = nodoOperacion.getTIPO_OPE();

           if(sel.equals("UNI")){
                System.out.println("UNI");
           }
           if(sel.equals("INT"))
                System.out.println("INT");
           if(sel.equals("PROC"))
                System.out.println("PROC");
           if(sel.equals("DIV"))
                System.out.println("DIV");
           if(sel.equals("DIF"))
                System.out.println("DIF");
           if(sel.equals("REUN"))
                System.out.println("REUN");
    }


}
