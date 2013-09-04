package interprete;
import nodos.*;
import com.google.common.collect.*;
import java.util.ArrayList;
import java.util.Stack;

public class Interprete {
    
    private NodoBase raiz;
    Funciones funcion = new Funciones();
    Stack<Table> pila = new Stack<Table>();
    Table<Integer, Integer, String> fin = TreeBasedTable.create();

    public Interprete(NodoBase root) {
        this.raiz = root;
    }

    public Table inicio(){
        interpretarNodo(raiz);
        return fin;
    }

    public void interpretarNodo(NodoBase nodo){
        NodoBase nodoActual = nodo;
        
        while (nodoActual != null){

            if(nodoActual instanceof NodoExpresion){
                nodoExpresion((NodoExpresion)nodoActual);
            }else if(nodoActual instanceof NodoOperacion){
                nodoOperacion((NodoOperacion)nodoActual);
            }
            nodoActual = nodoActual.getHermanoDerecha();
        }
    }

    private void nodoExpresion(NodoExpresion nodoExpresion){
        
        if(nodoExpresion.getTIPO_EXP().equals("SEL")){
            Table<Integer, Integer, String> relacion = TreeBasedTable.create();
            Table<Integer, Integer, String> resultado = TreeBasedTable.create();

            if(nodoExpresion.getRelacion() instanceof NodoId){
                pila.add(funcion.cargarArchivo(((NodoId) nodoExpresion.getRelacion()).getId()));
            }else{
                interpretarNodo(nodoExpresion.getRelacion());
            }
            relacion.putAll((Table<Integer, Integer, String>)pila.pop());
            resultado = funcion.OperacionSeleccion(nodoExpresion.getPredicado(),relacion);
            setFin(resultado);
            pila.add(resultado);       
        }
        else if(nodoExpresion.getTIPO_EXP().equals("PRO")){
            Table<Integer, Integer, String> relacion = TreeBasedTable.create();
            Table<Integer, Integer, String> resultado = TreeBasedTable.create();
            ArrayList<String> listaPredicado = new ArrayList<String>();
            ArrayList<NodoBase> listaPredicadoAuxiliar = new ArrayList<NodoBase>();
            NodoBase predicado = nodoExpresion.getPredicado();

            while(predicado!=null){
                if(predicado instanceof NodoId){
                    listaPredicado.add(((NodoId)predicado).getId());
                }
                if(predicado instanceof NodoComparacion){
                    listaPredicado.add(((NodoId)((NodoComparacion)predicado).getOpIzquierdo()).getId());
                    listaPredicadoAuxiliar.add(predicado);
                }
                predicado = predicado.getHermanoDerecha();
            }

            if(nodoExpresion.getRelacion() instanceof NodoId){
                pila.add(funcion.cargarArchivo(((NodoId)nodoExpresion.getRelacion()).getId()));
            }else{
                interpretarNodo(nodoExpresion.getRelacion());
            }
            relacion.putAll((Table<Integer, Integer, String>)pila.pop());
            resultado.putAll(funcion.proyeccion(relacion, listaPredicado));
            if(listaPredicadoAuxiliar.isEmpty()){
                setFin(resultado);
                pila.add(resultado);
            }else{
                resultado = funcion.operacionMatematica(listaPredicadoAuxiliar, resultado);
                setFin(resultado);
                pila.add(resultado);
            }
        }
    }

    private void nodoOperacion(NodoOperacion nodoOperacion){
        String sel = nodoOperacion.getTIPO_OPE();
        String nombreI="", nombreD="";
        
        Table<Integer, Integer, String> relacionI = TreeBasedTable.create();
        Table<Integer, Integer, String> relacionD = TreeBasedTable.create();
        Table<Integer, Integer, String> resultado = TreeBasedTable.create();
        
           if(sel.equals("UNI")){
                if(nodoOperacion.getOpIzq() instanceof NodoId){
                    pila.add(funcion.cargarArchivo(((NodoId)nodoOperacion.getOpIzq()).getId()));
                }else{
                    interpretarNodo(nodoOperacion.getOpIzq());
                }
                relacionI.putAll((Table<Integer, Integer, String>)pila.pop());

                if(nodoOperacion.getOpDer() instanceof NodoId){
                     pila.add(funcion.cargarArchivo(((NodoId)nodoOperacion.getOpDer()).getId()));
                }else{
                    interpretarNodo(nodoOperacion.getOpDer());
                }
                relacionD.putAll((Table<Integer, Integer, String>)pila.pop());
                resultado.putAll(funcion.union(relacionI, relacionD));
                pila.add(resultado);
                setFin(resultado);
           }
           if(sel.equals("INT")){
                if(nodoOperacion.getOpIzq() instanceof NodoId){
                    pila.add(funcion.cargarArchivo(((NodoId)nodoOperacion.getOpIzq()).getId()));
                }else{
                    interpretarNodo(nodoOperacion.getOpIzq());
                }
                relacionI.putAll((Table<Integer, Integer, String>)pila.pop());

                if(nodoOperacion.getOpDer() instanceof NodoId){
                     pila.add(funcion.cargarArchivo(((NodoId)nodoOperacion.getOpDer()).getId()));
                }else{
                    interpretarNodo(nodoOperacion.getOpDer());
                }
                relacionD.putAll((Table<Integer, Integer, String>)pila.pop());
                resultado.putAll(funcion.interseccion(relacionI, relacionD));
                pila.add(resultado);
                setFin(resultado);
           }
           if(sel.equals("PROC")){
                if(nodoOperacion.getOpIzq() instanceof NodoId){
                    nombreI = ((NodoId)nodoOperacion.getOpIzq()).getId();
                    pila.add(funcion.cargarArchivo(nombreI));
                }else{
                    interpretarNodo(nodoOperacion.getOpIzq());
                }
                relacionI.putAll((Table<Integer, Integer, String>)pila.pop());
                
                if(nodoOperacion.getOpDer() instanceof NodoId){
                    nombreD = ((NodoId)nodoOperacion.getOpDer()).getId();
                    pila.add(funcion.cargarArchivo(nombreD));
                }else{
                    interpretarNodo(nodoOperacion.getOpDer());
                }
                relacionD.putAll((Table<Integer, Integer, String>)pila.pop());
                resultado.putAll(funcion.productoCartesiano(relacionI, relacionD));
                pila.add(resultado);
                setFin(resultado);
           }
           if(sel.equals("DIV")){
                if(nodoOperacion.getOpIzq() instanceof NodoId){
                    pila.add(funcion.cargarArchivo(((NodoId)nodoOperacion.getOpIzq()).getId()));
                }else{
                    interpretarNodo(nodoOperacion.getOpIzq());
                }
                relacionI.putAll((Table<Integer, Integer, String>)pila.pop());

                if(nodoOperacion.getOpDer() instanceof NodoId){
                     pila.add(funcion.cargarArchivo(((NodoId)nodoOperacion.getOpDer()).getId()));
                }else{
                    interpretarNodo(nodoOperacion.getOpDer());
                }
                relacionD.putAll((Table<Integer, Integer, String>)pila.pop());
                resultado.putAll(funcion.division(relacionI, relacionD));
                pila.add(resultado);
                setFin(resultado);
           }
           if(sel.equals("DIF")){
                if(nodoOperacion.getOpIzq() instanceof NodoId){
                    pila.add(funcion.cargarArchivo(((NodoId)nodoOperacion.getOpIzq()).getId()));
                }else{
                    interpretarNodo(nodoOperacion.getOpIzq());
                }
                relacionI.putAll((Table<Integer, Integer, String>)pila.pop());

                if(nodoOperacion.getOpDer() instanceof NodoId){
                     pila.add(funcion.cargarArchivo(((NodoId)nodoOperacion.getOpDer()).getId()));
                }else{
                    interpretarNodo(nodoOperacion.getOpDer());
                }
                relacionD.putAll((Table<Integer, Integer, String>)pila.pop());
                resultado.putAll(funcion.diferencia(relacionI, relacionD));
                pila.add(resultado);
                setFin(resultado);
           }
           if(sel.equals("REUN")){
                if(nodoOperacion.getOpIzq() instanceof NodoId){
                    pila.add(funcion.cargarArchivo(((NodoId)nodoOperacion.getOpIzq()).getId()));
                }else{
                    interpretarNodo(nodoOperacion.getOpIzq());
                }
                relacionI.putAll((Table<Integer, Integer, String>)pila.pop());

                if(nodoOperacion.getOpDer() instanceof NodoId){
                     pila.add(funcion.cargarArchivo(((NodoId)nodoOperacion.getOpDer()).getId()));
                }else{
                    interpretarNodo(nodoOperacion.getOpDer());
                }
                relacionD.putAll((Table<Integer, Integer, String>)pila.pop());
                resultado.putAll(funcion.reunionNatural(relacionI, relacionD));
                pila.add(resultado);
                setFin(resultado);
           }
    }

    private void ImprimirTabla(Table tabla){
        for(int i=0; i<tabla.rowKeySet().size(); i++){
            for(int j=0; j<tabla.columnKeySet().size(); j++){
                System.out.print(tabla.get(i, j)+" ");
            }
            System.out.println("");
        }
    }

    public void setFin(Table tabla){
        this.fin = tabla;
    }
}