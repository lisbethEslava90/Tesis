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
public class Util {

    static int sangria = 0;
    static int cont =0;
    public static void imprimirArbol(NodoBase raiz){

        sangria+=2;
        while (raiz != null){
            imprimirEspacios();

            if(raiz instanceof NodoComparacion){
               imprimirNodo( (NodoComparacion) raiz);
            }else if(raiz instanceof NodoExpresion){
                imprimirNodo((NodoExpresion)raiz);
            }else if(raiz instanceof NodoFecha)
                System.out.println("Fecha: "+((NodoFecha)raiz).getFecha());
            else if(raiz instanceof NodoId){
                imprimirNodo((NodoId)raiz);
            }else if(raiz instanceof  NodoNumero)
                imprimirNodo((NodoNumero)raiz);
            else if(raiz instanceof NodoOperacion)
                imprimirNodo((NodoOperacion)raiz);
            else if(raiz instanceof NodoOperadorLogico)
                imprimirNodo((NodoOperadorLogico)raiz);
            else System.out.println("Nodo Desconocido");

            if(raiz instanceof NodoComparacion){

               imprimirEspacios();
               System.out.println("**Expr izq opeCompara**");
               imprimirArbol(((NodoComparacion)raiz).getOpIzquierdo());
               imprimirEspacios();
               System.out.println("**Expr der opeCompara**");
               imprimirArbol(((NodoComparacion)raiz).getOpDerecho());
            }

            else if(raiz instanceof NodoExpresion){
                imprimirEspacios();
                System.out.println("**Relacion**");
                imprimirArbol(((NodoExpresion)raiz).getRelacion()); 
                imprimirEspacios();
                System.out.println("**Predicado**");
                imprimirArbol(((NodoExpresion)raiz).getPredicado());

            }

            else if(raiz instanceof NodoOperacion){
                imprimirEspacios();
                System.out.println("**Operacion**");
                imprimirArbol(((NodoOperacion)raiz).getOpIzq());
                imprimirEspacios();
                imprimirArbol(((NodoOperacion)raiz).getOpDer());
            }

            else if(raiz instanceof NodoOperadorLogico){
                imprimirEspacios();
                System.out.println("*exp izq opeLogico*");
                imprimirArbol(((NodoOperadorLogico)raiz).getOpeI());
                imprimirEspacios();
                System.out.println("*exp der opeLogico");
                imprimirArbol(((NodoOperadorLogico)raiz).getOpeD());
            }

            raiz = raiz.getHermanoDerecha();
        }
        sangria-=2;
    }
    
    public static void imprimirEspacios(){
        
        for(int i=0; i<sangria; i++)
            System.out.print(" ");
    }

    public static void imprimirNodo(NodoBase raiz){
        //System.out.println("SANGRIA: "+sangria);
        if(raiz instanceof NodoComparacion){

            tipoOp sel = ((NodoComparacion) raiz).getOperacion();

                if (sel == tipoOp.and)
                    System.out.println("AND");
                if (sel == tipoOp.or)
                    System.out.println("OR");
                if (sel == tipoOp.not)
                    System.out.println("NOT");
                if (sel == tipoOp.diferente)
                    System.out.println("!=");
                if (sel == tipoOp.divi)
                    System.out.println("/");
                if (sel == tipoOp.igual)
                    System.out.println("=");
                if (sel == tipoOp.mayor)
                    System.out.println(">");
                if (sel == tipoOp.mayorIgual)
                    System.out.println(">=");
                if (sel == tipoOp.menor)
                    System.out.println("<");
                if (sel == tipoOp.menorIgual)
                    System.out.println("<=");
                if (sel == tipoOp.multi)
                    System.out.println("*");
                if (sel == tipoOp.resta)
                    System.out.println("-");
                if (sel == tipoOp.suma)
                    System.out.println("+");
        }

        if(raiz instanceof NodoExpresion){

           String sel = ((NodoExpresion) raiz).getTIPO_EXP();

           if(sel.equals("SEL"))
                System.out.println("SEL");
           if(sel.equals("PRO"))
                System.out.println("PRO");
        }
            
        if(raiz instanceof NodoId){
               String sino = (((NodoId) raiz).getSinonimo()!=null)?((NodoId) raiz).getSinonimo():"";
               System.out.println("ID:"+((NodoId) raiz).getId()+""+sino);
        }

        if(raiz instanceof  NodoNumero)
            System.out.println("NUM = "+ ((NodoNumero) raiz).getNumero());

        if(raiz instanceof NodoOperacion){
            String sel = ((NodoOperacion) raiz).getTIPO_OPE();

           if(sel.equals("UNI"))
                System.out.println("UNI");
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

        if(raiz instanceof NodoOperadorLogico){

            tipoOp sel = ((NodoOperadorLogico) raiz).getOperacion();

                if (sel == tipoOp.and)
                    System.out.println("AND");
                if (sel == tipoOp.or)
                    System.out.println("OR");
                if (sel == tipoOp.not)
                    System.out.println("NOT");
        }

    }
}