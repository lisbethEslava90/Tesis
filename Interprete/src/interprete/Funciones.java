package interprete;

import com.google.common.collect.*;
import nodos.*;
import java.io.*;
import java.util.*;


public class Funciones {

    private boolean operacionLogica;//NO
    
    private int fila=0, columna=0,contador=0;


    public Table cargarArchivo(String ruta) throws IOException{

        String strLinea;
        String valor;

        FileInputStream archivo = null;
        archivo = new FileInputStream(ruta);
        DataInputStream entrada = new DataInputStream(archivo);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
        Table<Integer, Integer, String> tabla = TreeBasedTable.create();

        StringTokenizer st;

        while ((strLinea = buffer.readLine()) != null) {
            st = new StringTokenizer(strLinea, "\",");
                while (st.hasMoreTokens()) {
                    valor = st.nextToken();
                    tabla.put(fila, columna, valor);
                    columna++;
                    contador = columna;
                }
                fila++;
                columna = 0;
        }
            // Cerramos el archivo
        entrada.close();
        return tabla;
    }

    public Table proyeccion(Table tabla, List<String> listaColumnas){
        
        Table<Integer, Integer, Object> nuevaRelacion = TreeBasedTable.create();
        Table<Integer, Integer, Object> auxiliar = TreeBasedTable.create();
        int elementoLista=0, dominio=0, indice=0, repetidos=0, filaAux = 0;
        boolean bandera = false;
        elementoLista = listaColumnas.size();
        
        //cuento si los elementos de la lista se encuentran todos en la relacion
        for(int i=0; i<elementoLista; i++){
            for(int j=0; j<tabla.columnKeySet().size(); j++){
                if(listaColumnas.get(i).equals(tabla.get(0, j))){
                    dominio++;
                }
            }
        }

        if(dominio == elementoLista){
        //for para recorrer la lista
            for(int i=0; i<elementoLista; i++){
                //for para buscar en la tabla cada campo que esta en la lista
                for(int j=0; j<tabla.columnKeySet().size();j++){
                    if(tabla.get(0, j).equals(listaColumnas.get(i))){
                        indice = j;
                        //cargar en la nueva relacion
                        for(int h=0;h<tabla.rowKeySet().size();h++)
                            nuevaRelacion.put(h, i, tabla.get(h,indice));
                    }
                }
            }

            //reviso los que estan repetidos en nuevaRelacion y les asigno un valor nulo
               for(int i=0; i<nuevaRelacion.rowKeySet().size(); i++){
                    for(int j=0; j<nuevaRelacion.rowKeySet().size(); j++){
                        if(nuevaRelacion.rowMap().get(i).equals(nuevaRelacion.rowMap().get(j))){
                            repetidos++;
                            if(repetidos>=2){
                                bandera = true;
                                for(int k=0; k<nuevaRelacion.columnKeySet().size(); k++){
                                    nuevaRelacion.put(j, k, "nulo");
                                }
                            }
//                            else
//                                bandera=false;
                        }
                    }
                    repetidos=0;
                }

            if(bandera==true){
            //paso a la tabla auxiliar los valores q no sean nulos
                for(int i=0; i<nuevaRelacion.rowKeySet().size(); i++){
                        for(int j=0; j<nuevaRelacion.columnKeySet().size(); j++){
                            if(!nuevaRelacion.get(i, j).equals("nulo")){
                                for(int k=0; k<nuevaRelacion.columnKeySet().size(); k++){
                                    auxiliar.put(filaAux, k, nuevaRelacion.get(i, j));
                                }
                                filaAux++;
                            }
                        }
                  }
                return auxiliar;
            }else
                return nuevaRelacion;

        }else
            System.out.println("Dominios incompatibles");
        return auxiliar;
    }

    public Table seleccion(Table tabla, String campo, String condicion){

        int contador =0, buscarEn=0, conseguiEn=0, cuentoColumnas=0, columnaCampo=0, columnaCondicion=0;
        Table<Integer, Integer, Object> nuevaRelacion = TreeBasedTable.create();

        //reviso si el campo y la condicion son dos columnas de la tabla
        for(int i=0; i<tabla.columnKeySet().size(); i++){
            if(tabla.get(0, i).equals(campo)){
                cuentoColumnas++;
                columnaCampo = i;
            }
            if(tabla.get(0, i).equals(condicion)){
                cuentoColumnas++;
                columnaCondicion=i;
            }
        }
        if(cuentoColumnas==1){
            //donde esta el campo donde va a buscar
            for(int i=0; i<tabla.columnKeySet().size(); i++){
                if(tabla.get(0, i).equals(campo))
                    buscarEn = i;
            }
            //busco la condicion
            for(int i=0; i<tabla.rowKeySet().size(); i++){
                if(tabla.get(i, buscarEn).equals(condicion)){
                    conseguiEn = i;
                    contador++;
                    //asigno a la nuevaRelacion los valores q cumplen la condicion
                    for(int c=0; c<tabla.columnKeySet().size(); c++){
                        nuevaRelacion.put(0, c, tabla.get(0, c));
                        if(contador>0)
                            nuevaRelacion.put(contador, c, tabla.get(conseguiEn, c));
                    }
                }
            }
        }
        //si esta recibiendo id de columna
        if(cuentoColumnas==2){
            //comparo cuales valores de las columnas son iguales y guardo en la nueva relacion la fila correspondiente
            for(int i=1; i<tabla.rowKeySet().size(); i++){
                if(tabla.get(i, columnaCampo).equals(tabla.get(i, columnaCondicion))){
                    contador++;
                    for(int c=0; c<tabla.columnKeySet().size(); c++){
                        nuevaRelacion.put(0, c, tabla.get(0, c));
                        if(contador>0)
                            nuevaRelacion.put(contador, c, tabla.get(i, c));
                    }
                }
            }

        }

        //System.out.println("Filas de la seleccion: "+nuevaRelacion.rowMap());
        //System.out.println("resultado de seleccionar: "+nuevaRelacion.cellSet());
        return nuevaRelacion;
    }

    public Table union(Table relacionI, Table relacionD){

        Table<Integer, Integer, Object> nuevaRelacion = TreeBasedTable.create();
        Table<Integer, Integer, Object> auxiliar = TreeBasedTable.create();

        int contador =0, filas=0, filasNR=0, repetidos =0, filaAux=0;

        //validar que ambas tablas tengan la misma cantidad de columnas y dominio
        if(relacionI.columnKeySet().size()==relacionD.columnKeySet().size()){
            for(int i=0; i<relacionI.columnKeySet().size(); i++){
                if(relacionI.get(0, i).equals(relacionD.get(0, i)));{
                    contador++;
                }
            }
        }else
            System.out.println("Diferente cantidad de columnas");

        if(contador==relacionI.columnKeySet().size()){
            //asigno a nuevarelacion todas las filas de la relacionI
            nuevaRelacion.putAll(relacionI);
            filasNR = nuevaRelacion.rowKeySet().size();
            //asigno a nuevaRelacion todas las filas de relacionD
               for(int i=0; i<relacionD.rowKeySet().size(); i++){
                    for(int j=0; j<relacionD.columnKeySet().size(); j++){
                        nuevaRelacion.put(filasNR, j, relacionD.get(i, j));
                    }
                    filasNR++;
                }
           //reviso los que estan repetidos en nuevaRelacion y les asigno un valor nulo
               for(int i=0; i<nuevaRelacion.rowKeySet().size(); i++){
                    for(int j=0; j<nuevaRelacion.rowKeySet().size(); j++){
                        if(nuevaRelacion.rowMap().get(i).equals(nuevaRelacion.rowMap().get(j))){
                            repetidos++;
                            if(repetidos>=2){
                                for(int k=0; k<nuevaRelacion.columnKeySet().size(); k++){
                                    nuevaRelacion.put(j, k, "nulo");
                                }
                                filas++;
                            }
                        }
                    }
                    repetidos=0;
                }
           //agrego a una tabla auxiliar los valores q no sean nulos en la tabla nuevaRelacion
              for(int i=0; i<nuevaRelacion.rowKeySet().size(); i++){
                    for(int j=0; j<nuevaRelacion.columnKeySet().size(); j++){
                        if(!nuevaRelacion.get(i, j).equals("nulo")){
                            for(int k=0; k<nuevaRelacion.columnKeySet().size(); k++){
                                auxiliar.put(filaAux, k, nuevaRelacion.get(i, j));
                            }
                            filaAux++;
                        }
                    }
              }
        }else
                System.out.println("Dominios diferentes");

        return auxiliar;
    }

    public Table diferencia(Table relacionI, Table relacionD){

        Table<Integer, Integer, Object> nuevaRelacion = TreeBasedTable.create();
        Table<Integer, Integer, Object> copia = TreeBasedTable.create();

        int Contador=0, repetidos=0, filasNR=1;

        copia.putAll(relacionI);
        //igual cantidad de columnas y dominio
        if(copia.columnKeySet().size()==relacionD.columnKeySet().size()){
            for(int i=0; i<copia.columnKeySet().size(); i++){
                if(copia.get(0, i).equals(relacionD.get(0, i))==true){
                    Contador++;
                }
            }
        }else
            System.out.println("Diferente cantidad de columnas");

        if(Contador==copia.columnKeySet().size()){

            for(int i=0; i<copia.columnKeySet().size(); i++){
                nuevaRelacion.put(0, i, copia.get(0, i));
            }
            //colocar nulo a los valore de la relacionI que esten en la relacionD
            for(int i=0; i<copia.rowKeySet().size(); i++){
                for(int j=0; j<relacionD.rowKeySet().size(); j++){
                    if(copia.rowMap().get(i).equals(relacionD.rowMap().get(j))){
                        repetidos++;
                        for(int k=0; k<copia.columnKeySet().size(); k++){
                            copia.put(i, k, "nulo");
                        }
                    }
                }
            }
            //elimino los repetidos
            for(int i=0; i<copia.rowKeySet().size(); i++){
                for(int j=0; j<copia.rowKeySet().size(); j++){
                    if(copia.rowMap().get(i).equals(copia.rowMap().get(j))){
                        repetidos++;
                        if(repetidos>=2){
                            for(int k=0; k<copia.columnKeySet().size(); k++){
                                copia.put(i, k, "nulo");
                            }
                        }
                    }
                }
                repetidos=0;
            }
            //coloco en nuevaRelacion los valores de la relacionI que no sean nulos
            for(int i=0; i<copia.rowKeySet().size(); i++){
                        if(!copia.row(i).containsValue("nulo")){
                            for(int k=0; k<copia.columnKeySet().size(); k++){
                                nuevaRelacion.put(filasNR, k, copia.get(i, k));
                            }
                            filasNR++;
                        }
              }

            //System.out.println("nueva relacion diferencia: "+nuevaRelacion.cellSet());
        }else
            System.out.println("Dominios diferentes");

        return nuevaRelacion;
    }

    public Table productoCartesiano(Table relacionI, Table relacionD){

        Table<Integer, Integer, Object> nuevaRelacion = TreeBasedTable.create();
        int columnasIguales=0,columnas=0, coincideRI=0, coincideRD=0, filaNR = 1, filaRI=1;

        //cantidad de columnas iguales
        for(int i=0; i<relacionI.columnKeySet().size(); i++){
            for(int j=0; j<relacionD.columnKeySet().size(); j++){
                if(relacionI.get(0, i).equals(relacionD.get(0, j))){
                    columnasIguales++;
                    coincideRI = i;//OJO arreglar el nombre de las columnas q son iguales
                    coincideRD = j;
                }
            }
        }
        //deben coincidir en una columna
            //colocar a la nueva relacion el identificador de cada columna
            for(int j=0; j<relacionI.columnKeySet().size(); j++){
                nuevaRelacion.put(0, j, relacionI.get(0, j));
                columnas = j;
            }
            columnas++;
            for(int j=0; j<relacionD.columnKeySet().size(); j++){
                nuevaRelacion.put(0, columnas, relacionD.get(0, j));
                columnas++;
            }
            //colocarle el nombre de tabla en las columnas q coinciden
            nuevaRelacion.put(0, coincideRI, "relacionI."+relacionI.get(0, coincideRI));
            nuevaRelacion.put(0, coincideRD+relacionI.columnKeySet().size(), "relacionD."+relacionD.get(0, coincideRD));

            int j=0, filaRD=1;

            for(int h=0; h<relacionI.rowMap().size()-1; h++){
                //para cada fila de la relacionI corresponde todas las filas de la relacionD
                for(int i=1; i<relacionD.rowKeySet().size();i++){
                    //agregar a la nueva relacion cada fila de la relacionI
                    for(j=0; j<relacionI.columnKeySet().size(); j++){
                        nuevaRelacion.put(filaNR, j, relacionI.get(filaRI, j));
                    }
                    //agregar a la nueva relacion cada fila de la relacionD
                    for(int k=0; k<relacionD.columnKeySet().size();k++){
                        nuevaRelacion.put(filaNR, j, relacionD.get(filaRD, k));
                        j++;
                    }
                    filaRD++;
                    filaNR++;
                }
                filaRI++;
                filaRD = 1;
            }
        //System.out.println("Nueva relacion: "+nuevaRelacion.rowMap());

        return nuevaRelacion;
    }

    public Table interseccion(Table relacionI, Table relacionD){

        Table<Integer, Integer, Object> auxiliar = TreeBasedTable.create();
        Table<Integer, Integer, Object> nuevaRelacion = TreeBasedTable.create();

        auxiliar.putAll(diferencia(relacionI, relacionD));
        nuevaRelacion.putAll(diferencia(relacionI, auxiliar));
        System.out.println("interseccion: "+nuevaRelacion.cellSet());

        return nuevaRelacion;
    }

    public Table reunionNatural(Table relacionI, Table relacionD){

        Table<Integer, Integer, Object> nuevaRelacion = TreeBasedTable.create();
        Table<Integer, Integer, Object> productoCartesiano = TreeBasedTable.create();
        Table<Integer, Integer, Object> seleccion = TreeBasedTable.create();
        int iguales=0, igualA=0, igualB=0, k=0;

        productoCartesiano = productoCartesiano(relacionI, relacionD);
        seleccion = seleccion(productoCartesiano, "relacionI.numero_cuenta", "relacionD.numero_cuenta");
        //busco que columnas son iguales
        for(int i=0; i<seleccion.columnKeySet().size();i++){
            for(int j=0; j<seleccion.columnKeySet().size(); j++){
                if(seleccion.get(1, i).equals(seleccion.get(1, j))){
                    iguales++;
                    if(iguales==2&&(i!=j)){
                        igualA = i;
                        igualB = j;
                    }
                }
            }
        iguales=0;
        }
        //guardo en nuevaRelacion los valores sin la columna repetida que resulta del producto cartesiano
        for(int i=0; i<seleccion.rowKeySet().size(); i++){
            for(int j=0; j<seleccion.columnKeySet().size()-1; j++){
                if(j==igualB){
                    k = igualB+1;
                    nuevaRelacion.put(i, j, seleccion.get(i,k));
                    k++;
                }
                if(j<igualB){
                    nuevaRelacion.put(i, j, seleccion.get(i, j));
                }
                if(j>igualB){
                    nuevaRelacion.put(i, j, seleccion.get(i,k));
                    k++;
                }
            }
        }

        nuevaRelacion.put(0, 1, "numero_cuenta");
        //System.out.println("reunion natural: "+nuevaRelacion.cellSet());
        return nuevaRelacion;
    }

    public Table division(Table relacionI, Table relacionD){

        Table<Integer, Integer, Object> nuevaRelacion = TreeBasedTable.create();
        Table<Integer, Integer, Object> producto = TreeBasedTable.create();
        Table<Integer, Integer, Object> diferencia = TreeBasedTable.create();

        Table<Integer, Integer, Object> T1 = TreeBasedTable.create();
        Table<Integer, Integer, Object> T2 = TreeBasedTable.create();
        List<String> lista = new ArrayList<String>();
        List<String> lista2 = new ArrayList<String>();
        int columnasIguales=0, coincideRI=0, coincideRD=0;


        //en q campo coinciden ambas relaciones
        for(int i=0; i<relacionI.columnKeySet().size(); i++){
            for(int j=0; j<relacionD.columnKeySet().size(); j++){
                if(relacionI.get(0, i).equals(relacionD.get(0, j))){
                    coincideRI = i;
                    coincideRD = j;
                }
            }
        }
        lista.add("nombre_cliente");

        T1 = proyeccion(relacionI, lista);
        //System.out.println("T1: "+T1.cellSet());
        producto = productoCartesiano(T1, relacionD);
        producto.put(0, 1, "nombre_sucursal");
        producto.put(0, 0, "nombre_cliente");
        //System.out.println("T1 PROC S: "+producto.cellSet());
        diferencia = diferencia(producto, relacionI);
        //System.out.println("diferencia: "+diferencia.cellSet());
        T2 = proyeccion(diferencia, lista);
        //System.out.println("T2: "+T2.cellSet());
        nuevaRelacion = diferencia(T1, T2);
        //System.out.println("T: "+nuevaRelacion.cellSet());
        return nuevaRelacion;
    }

    public Table OperacionSeleccion(NodoBase predicado, NodoBase relacion){
        Table<Integer, Integer, Object> nuevaRelacion = TreeBasedTable.create();
        return nuevaRelacion;
    }

    public Table OperacionProyeccion(NodoBase predicado, NodoBase relacion){
        Table<Integer, Integer, Object> nuevaRelacion = TreeBasedTable.create();
        return nuevaRelacion;
    }

    public boolean OperacionLogicaAnd(String condicionIzq, String condicionDer){
        /*
         * La condición izquierda y la derecha deben cumplirse para retornar true
         * de lo contrario retorna false
         */
        return operacionLogica;
    }

    public boolean OperacionLogicaOr(String condicionIzq, String condicionDer){
        /*
         * si se cumple alguna de las condiciones retorna true
         * sino se cumple ninguna retorna false
         */
        return operacionLogica;
    }

    public boolean OperacionLogicaNot(String condicionIzq, String condicionDer){
        /*
         * si se cumple la negacion de las condiciones retorna true
         * de lo contrario retorna false
         */
        return operacionLogica;
    }

    public boolean OperacionComparacionIgual(String condicionIzq,String condicionDer){
        if(condicionIzq.equals(condicionDer))
            return true;
        else
            return false;
    }

    public boolean OperacionComparacionMenor(double condicionIzq,double condicionDer){
        if(condicionIzq < condicionDer)
            return true;
        else
            return false;
    }

    public boolean OperacionComparacionMenorIgual(double condicionIzq,double condicionDer){
        if(condicionIzq <= condicionDer)
            return true;
        else
            return false;
    }

    public boolean OperacionComparacionMayor(double  condicionIzq,double condicionDer){
        if(condicionIzq > condicionDer)
            return true;
        else
            return false;
    }

    public boolean OperacionComparacionMayorIgual(double  condicionIzq,double  condicionDer){
        if(condicionIzq >= condicionDer)
            return true;
        else
            return false;
    }

    public int OperacionMatSumar(int valorA,int valorB){
        return valorA+valorB;
    }

    public int OperacionMatRestar(int valorA,int valorB){
        return valorA-valorB;
    }

    public int OperacionMatMultiplicar(int valorA,int valorB){
        return valorA*valorB;
    }

    public int OperacionMatDividir(int valorA,int valorB){
        return valorA/valorB;
    }

}