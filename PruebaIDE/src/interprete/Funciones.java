package interprete;

import com.google.common.collect.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nodos.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JOptionPane;


public class Funciones {

    private Stack<Table> pila = new Stack<Table>();

    public Table cargarArchivo(String ruta){

        int fila=0, columna=0, contador=0;
        String strLinea;
        String valor;

        FileInputStream archivo = null;
        try {
            archivo = new FileInputStream("relaciones/"+ruta+".csv");
            //archivo = new FileInputStream(((NodoId)nodo).getId()+".csv");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No existe la relacion "+ruta+".csv");
        }
        DataInputStream entrada = new DataInputStream(archivo);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
        Table<Integer, Integer, String> tabla = TreeBasedTable.create();
        
        StringTokenizer st;
        try {
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
        } catch (IOException ex) {
            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Cerramos el archivo
            entrada.close();
        } catch (IOException ex) {
            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tabla;
    }

    public Table cargarArchivo(File F){        
        Table<Integer, Integer, String> tabla = TreeBasedTable.create();
        StringTokenizer st;
        int fila=0, columna=0, contador=0;
        String strLinea;
        String valor;

        FileReader fr = null;
        try {
            fr = new FileReader(F);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No existe la relacion "+F.getAbsolutePath());
        }
        BufferedReader br = new BufferedReader(fr);
        try {
            while ((strLinea = br.readLine()) != null) {
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
        } catch (IOException ex) {
            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tabla;
    }

    public Table proyeccion(Table tabla, List<String> listaColumnas){
        
        Table<Integer, Integer, Object> nuevaRelacion = TreeBasedTable.create();
        Table<Integer, Integer, Object> auxiliar = TreeBasedTable.create();
        int elementoLista=0, dominio=0, indice=0, repetidos=0, filaAux = 0;
        boolean bandera = false, band=true;
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
                                    nuevaRelacion.put(j, k, " ");
                                }
                            }
                        }
                    }
                    repetidos=0;
                }
            if(bandera==true){
                //agrego a una tabla auxiliar los valores q no sean nulos en la tabla nuevaRelacion
            for(int i=0; i<nuevaRelacion.rowKeySet().size(); i++){
                for(int j=0; j<nuevaRelacion.columnKeySet().size(); j++){
                    if(!nuevaRelacion.get(i, j).equals(" ")){
                        for(int k=0; k<nuevaRelacion.columnKeySet().size(); k++){
                            if(band)
                                auxiliar.put(filaAux, k, nuevaRelacion.get(i, k));
                        }
                        if(band)
                            filaAux++;
                        band=false;
                    }
                }
                band=true;
            }
                return auxiliar;
            }else
                return nuevaRelacion;

        }else
            JOptionPane.showMessageDialog(null, "Proyección: No se encontraron todos los campos");
        return auxiliar;
    }

    public Table seleccion(Table tabla, String campo, Date fecha, tipoOp tipo){

        int contador =0, buscarEn=0, conseguiEn=0;
        Table<Integer, Integer, Object> nuevaRelacion = TreeBasedTable.create();
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        
        if(tipo==tipo.menor){
            //donde esta el campo donde va a buscar
            for(int i=0; i<tabla.columnKeySet().size(); i++){
                if(tabla.get(0, i).equals(campo))
                    buscarEn = i;
            }
            //busco la condicion de menor
            for(int i=1; i<tabla.rowKeySet().size(); i++){
                try {
                    if (formateador.parse(tabla.get(i, buscarEn).toString()).before(fecha)) {
                        conseguiEn = i;
                        contador++;
                        //asigno a la nuevaRelacion los valores q cumplen la condicion
                        for (int c = 0; c < tabla.columnKeySet().size(); c++) {
                            nuevaRelacion.put(0, c, tabla.get(0, c));
                            if (contador > 0) {
                                nuevaRelacion.put(contador, c, tabla.get(conseguiEn, c));
                            }
                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else if(tipo == tipo.mayor){
            //donde esta el campo donde va a buscar
            for(int i=0; i<tabla.columnKeySet().size(); i++){
                if(tabla.get(0, i).equals(campo))
                    buscarEn = i;
            }
            //busco la condicion que sea mayor
            for(int i=1; i<tabla.rowKeySet().size(); i++){
                try {
                    if (fecha.before(formateador.parse(tabla.get(i, buscarEn).toString()))) {
                        conseguiEn = i;
                        contador++;
                        //asigno a la nuevaRelacion los valores q cumplen la condicion
                        for (int c = 0; c < tabla.columnKeySet().size(); c++) {
                            nuevaRelacion.put(0, c, tabla.get(0, c));
                            if (contador > 0) {
                                nuevaRelacion.put(contador, c, tabla.get(conseguiEn, c));
                            }
                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else if(tipo == tipo.igual){
            //donde esta el campo donde va a buscar
            for(int i=0; i<tabla.columnKeySet().size(); i++){
                if(tabla.get(0, i).equals(campo))
                    buscarEn = i;
            }
            //busco la condicion que sea igual
            for(int i=1; i<tabla.rowKeySet().size(); i++){
                try {
                    if (formateador.parse(tabla.get(i, buscarEn).toString()).equals(fecha)) {
                        conseguiEn = i;
                        contador++;
                        //asigno a la nuevaRelacion los valores q cumplen la condicion
                        for (int c = 0; c < tabla.columnKeySet().size(); c++) {
                            nuevaRelacion.put(0, c, tabla.get(0, c));
                            if (contador > 0) {
                                nuevaRelacion.put(contador, c, tabla.get(conseguiEn, c));
                            }
                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else if(tipo == tipo.menorIgual){
            //donde esta el campo donde va a buscar
            for(int i=0; i<tabla.columnKeySet().size(); i++){
                if(tabla.get(0, i).equals(campo))
                    buscarEn = i;
            }
            //busco la condicion que sea menorIgual
            for(int i=1; i<tabla.rowKeySet().size(); i++){
                try {
                    if (formateador.parse(tabla.get(i, buscarEn).toString()).before(fecha) || formateador.parse(tabla.get(i, buscarEn).toString()).equals(fecha)) {
                        conseguiEn = i;
                        contador++;
                        //asigno a la nuevaRelacion los valores q cumplen la condicion
                        for (int c = 0; c < tabla.columnKeySet().size(); c++) {
                            nuevaRelacion.put(0, c, tabla.get(0, c));
                            if (contador > 0) {
                                nuevaRelacion.put(contador, c, tabla.get(conseguiEn, c));
                            }
                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else if(tipo == tipo.mayorIgual){
            //donde esta el campo donde va a buscar
            for(int i=0; i<tabla.columnKeySet().size(); i++){
                if(tabla.get(0, i).equals(campo))
                    buscarEn = i;
            }
            //busco la condicion que sea mayorIgual
            for(int i=1; i<tabla.rowKeySet().size(); i++){
                try {
                    if (fecha.before(formateador.parse(tabla.get(i, buscarEn).toString())) || formateador.parse(tabla.get(i, buscarEn).toString()).equals(fecha)) {
                        conseguiEn = i;
                        contador++;
                        //asigno a la nuevaRelacion los valores q cumplen la condicion
                        for (int c = 0; c < tabla.columnKeySet().size(); c++) {
                            nuevaRelacion.put(0, c, tabla.get(0, c));
                            if (contador > 0) {
                                nuevaRelacion.put(contador, c, tabla.get(conseguiEn, c));
                            }
                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else if(tipo == tipo.diferente){
            //donde esta el campo donde va a buscar
            for(int i=0; i<tabla.columnKeySet().size(); i++){
                if(tabla.get(0, i).equals(campo))
                    buscarEn = i;
            }
            //busco la condicion que sean diferentes
            for(int i=1; i<tabla.rowKeySet().size(); i++){
                try {
                    if (formateador.parse(tabla.get(i, buscarEn).toString()).equals(fecha) == false) {
                        conseguiEn = i;
                        contador++;
                        //asigno a la nuevaRelacion los valores q cumplen la condicion
                        for (int c = 0; c < tabla.columnKeySet().size(); c++) {
                            nuevaRelacion.put(0, c, tabla.get(0, c));
                            if (contador > 0) {
                                nuevaRelacion.put(contador, c, tabla.get(conseguiEn, c));
                            }
                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return nuevaRelacion;
    }

    public Table seleccion(Table tabla, String campo, String condicion, tipoOp tipo){

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
        
        if(tipo==tipo.igual){
            if(cuentoColumnas==2){//si recibe dos nombres de columnas
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
            }else{
                //donde esta el campo donde va a buscar
                for(int i=0; i<tabla.columnKeySet().size(); i++){
                    if(tabla.get(0, i).equals(campo))
                        buscarEn = i;
                }
                //busco la condicion de igualdad
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
        }else if(tipo == tipo.mayor){
            if(cuentoColumnas==2){
                for(int i=1; i<tabla.rowKeySet().size(); i++){
                    if(Double.parseDouble(tabla.get(i, columnaCampo).toString())>Double.parseDouble(tabla.get(i, columnaCondicion).toString())){
                        contador++;
                        for(int c=0; c<tabla.columnKeySet().size(); c++){
                            nuevaRelacion.put(0, c, tabla.get(0, c));
                            if(contador>0)
                                nuevaRelacion.put(contador, c, tabla.get(i, c));
                        }
                    }
            }
            }else{            
                //donde esta el campo donde va a buscar
                for(int i=0; i<tabla.columnKeySet().size(); i++){
                    if(tabla.get(0, i).equals(campo))
                        buscarEn = i;
                }
                //busco la condicion que sea mayor
                for(int i=1; i<tabla.rowKeySet().size(); i++){
                    if(Double.parseDouble(tabla.get(i, buscarEn).toString())>Double.parseDouble(condicion)){
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
        }else if(tipo == tipo.mayorIgual){
            if(cuentoColumnas==2){
                for(int i=1; i<tabla.rowKeySet().size(); i++){
                    if(Double.parseDouble(tabla.get(i, columnaCampo).toString())>=Double.parseDouble(tabla.get(i, columnaCondicion).toString())){
                        contador++;
                        for(int c=0; c<tabla.columnKeySet().size(); c++){
                            nuevaRelacion.put(0, c, tabla.get(0, c));
                            if(contador>0)
                                nuevaRelacion.put(contador, c, tabla.get(i, c));
                        }
                    }
            }
            }else{
                //donde esta el campo donde va a buscar
                for(int i=0; i<tabla.columnKeySet().size(); i++){
                    if(tabla.get(0, i).equals(campo))
                        buscarEn = i;
                }
                //busco la condicion que sea mayorIgual
                for(int i=1; i<tabla.rowKeySet().size(); i++){
                    if(Double.parseDouble(tabla.get(i, buscarEn).toString())>=Double.parseDouble(condicion)){
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
        }else if(tipo == tipo.menor){
            if(cuentoColumnas==2){
                for(int i=1; i<tabla.rowKeySet().size(); i++){
                    if(Double.parseDouble(tabla.get(i, columnaCampo).toString())<Double.parseDouble(tabla.get(i, columnaCondicion).toString())){
                        contador++;
                        for(int c=0; c<tabla.columnKeySet().size(); c++){
                            nuevaRelacion.put(0, c, tabla.get(0, c));
                            if(contador>0)
                                nuevaRelacion.put(contador, c, tabla.get(i, c));
                        }
                    }
                }
            }else{
                //donde esta el campo donde va a buscar
                for(int i=0; i<tabla.columnKeySet().size(); i++){
                    if(tabla.get(0, i).equals(campo))
                        buscarEn = i;
                }
                //busco la condicion que sea menor
                for(int i=1; i<tabla.rowKeySet().size(); i++){
                    if(Double.parseDouble(tabla.get(i, buscarEn).toString())<Double.parseDouble(condicion)){
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
        }else if(tipo == tipo.menorIgual){
            if(cuentoColumnas==2){
                for(int i=1; i<tabla.rowKeySet().size(); i++){
                    if(Double.parseDouble(tabla.get(i, columnaCampo).toString())<=Double.parseDouble(tabla.get(i, columnaCondicion).toString())){
                        contador++;
                        for(int c=0; c<tabla.columnKeySet().size(); c++){
                            nuevaRelacion.put(0, c, tabla.get(0, c));
                            if(contador>0)
                                nuevaRelacion.put(contador, c, tabla.get(i, c));
                        }
                    }
                }
            }else{
                //donde esta el campo donde va a buscar
                for(int i=0; i<tabla.columnKeySet().size(); i++){
                    if(tabla.get(0, i).equals(campo))
                        buscarEn = i;
                }
                //busco la condicion que sea menorIgual
                for(int i=1; i<tabla.rowKeySet().size(); i++){
                    if(Double.parseDouble(tabla.get(i, buscarEn).toString())<=Double.parseDouble(condicion)){
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
        }else if(tipo == tipo.diferente){
            if(cuentoColumnas==2){//si recibe dos nombres de columnas
                for(int i=1; i<tabla.rowKeySet().size(); i++){
                    if(tabla.get(i, columnaCampo).equals(tabla.get(i, columnaCondicion))==false){
                        contador++;
                        for(int c=0; c<tabla.columnKeySet().size(); c++){
                            nuevaRelacion.put(0, c, tabla.get(0, c));
                            if(contador>0)
                                nuevaRelacion.put(contador, c, tabla.get(i, c));
                        }
                    }
                }
            }else{
                //donde esta el campo donde va a buscar
                for(int i=0; i<tabla.columnKeySet().size(); i++){
                    if(tabla.get(0, i).equals(campo))
                        buscarEn = i;
                }
                //busco la condicion de desigualdad
                for(int i=1; i<tabla.rowKeySet().size(); i++){
                    if(tabla.get(i, buscarEn).equals(condicion)==false){
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
        }
        return nuevaRelacion;
    }

    public Table union(Table relacionI, Table relacionD){

        Table<Integer, Integer, Object> nuevaRelacion = TreeBasedTable.create();
        Table<Integer, Integer, Object> auxiliar = TreeBasedTable.create();

        int contador =0,filasNR=0, repetidos =0, filaAux=0;
        boolean band=true;

        //validar que ambas tablas tengan la misma cantidad de columnas y dominio
        if(relacionI.columnKeySet().size()==relacionD.columnKeySet().size()){
            for(int i=0; i<relacionI.columnKeySet().size(); i++){
                if(relacionI.get(0, i).equals(relacionD.get(0, i)));{
                    contador++;
                }
            }
        }
        if(contador==relacionI.columnKeySet().size()){
            //asigno a nuevarelacion todas las filas de la relacionI
            nuevaRelacion.putAll(relacionI);
            filasNR = nuevaRelacion.rowKeySet().size();
            //asigno a nuevaRelacion todas las filas de relacionD
               //for(int i=0; i<relacionD.rowKeySet().size(); i++){
                 for(int i=1; i<relacionD.rowKeySet().size(); i++){
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
                                    nuevaRelacion.put(j, k, " ");
                                }
                            }
                        }
                    }
                    repetidos=0;
                }            
           //agrego a una tabla auxiliar los valores q no sean nulos en la tabla nuevaRelacion
            for(int i=0; i<nuevaRelacion.rowKeySet().size(); i++){
                for(int j=0; j<nuevaRelacion.columnKeySet().size(); j++){
                    if(!nuevaRelacion.get(i, j).equals(" ")){
                        for(int k=0; k<nuevaRelacion.columnKeySet().size(); k++){
                            if(band)
                                auxiliar.put(filaAux, k, nuevaRelacion.get(i, k));
                        }
                        if(band)
                            filaAux++;
                        band=false;
                    }
                }
                band=true;
            }
        }else
            JOptionPane.showMessageDialog(null, "Union: Los campos de las relaciones no coinciden ");
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
        }

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
                            copia.put(i, k, " ");
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
                                copia.put(i, k, " ");
                            }
                        }
                    }
                }
                repetidos=0;
            }
            //coloco en nuevaRelacion los valores de la relacionI que no sean nulos
            for(int i=0; i<copia.rowKeySet().size(); i++){
                if(!copia.row(i).containsValue(" ")){
                    for(int k=0; k<copia.columnKeySet().size(); k++){
                        nuevaRelacion.put(filasNR, k, copia.get(i, k));
                    }
                    filasNR++;
               }
           }

            //System.out.println("nueva relacion diferencia: "+nuevaRelacion.cellSet());
        }else
            JOptionPane.showMessageDialog(null, "Diferencia: Diferente cantidad de columnas ");
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
        if(columnasIguales>0){
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
        }else
            JOptionPane.showMessageDialog(null, "Producto Cartesiano: las relaciones no coinciden en ningun campo ");

        return nuevaRelacion;
    }

    public Table interseccion(Table relacionI, Table relacionD){

        Table<Integer, Integer, Object> auxiliar = TreeBasedTable.create();
        Table<Integer, Integer, Object> nuevaRelacion = TreeBasedTable.create();

        auxiliar.putAll(diferencia(relacionI, relacionD));
        nuevaRelacion.putAll(diferencia(relacionI, auxiliar));
        return nuevaRelacion;
    }

    public Table reunionNatural(Table relacionI, Table relacionD){

        Table<Integer, Integer, Object> nuevaRelacion = TreeBasedTable.create();
        Table<Integer, Integer, Object> productoCartesiano = TreeBasedTable.create();
        Table<Integer, Integer, Object> seleccion = TreeBasedTable.create();
        int iguales=0, igualA=0, igualB=0, k=0;
        String [] nombre;
        String campoA="", campoB="";
        boolean band=false;

        productoCartesiano = productoCartesiano(relacionI, relacionD);
        //seleccion = seleccion(productoCartesiano, "relacionI."+productoCartesiano.get(0, 1), "relacionD."+productoCartesiano.get(0, 2));
        
        //campo en el q son iguales pero traen el nombre de la relacion antes
        for(int j=0; j<productoCartesiano.columnKeySet().size(); j++){
            nombre = productoCartesiano.get(0, j).toString().split("\\.");
            for(int i=0; i<nombre.length; i++){
                if(nombre[i].equals("relacionI")){
                    campoA = nombre[i+1];
                }
                else if(nombre[i].equals("relacionD")){
                    campoB = nombre[i+1];
                }
            }
        }
        seleccion = seleccion(productoCartesiano, "relacionI."+campoA, "relacionD."+campoB,tipoOp.igual);

        //busco que columnas son iguales
        for(int i=0; i<seleccion.columnKeySet().size();i++){
            for(int j=0; j<seleccion.columnKeySet().size(); j++){
                if(seleccion.get(1, i).equals(seleccion.get(1, j))){
                    iguales++;
                    band = true;
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
            nuevaRelacion.put(0, igualA, campoA); //si falla eliminar esta línea
        return nuevaRelacion;
    }

    public Table division(Table relacionI, Table relacionD){

        Table<Integer, Integer, Object> nuevaRelacion = TreeBasedTable.create();
        Table<Integer, Integer, Object> producto = TreeBasedTable.create();
        Table<Integer, Integer, Object> diferencia = TreeBasedTable.create();

        Table<Integer, Integer, Object> T1 = TreeBasedTable.create();
        Table<Integer, Integer, Object> T2 = TreeBasedTable.create();
        List<String> lista = new ArrayList<String>();
        int coincideRI=0, coincideRD=0;
        String valor="";
        String [] nombre;

        //en q campo coinciden ambas relaciones
        for(int i=0; i<relacionI.columnKeySet().size(); i++){
            for(int j=0; j<relacionD.columnKeySet().size(); j++){
                if(relacionI.get(0, i).equals(relacionD.get(0, j))){
                    coincideRI = i;
                    coincideRD = j;
                }
            }
        }
        //colocar en una lista el campo q no coincide en ambas
        for(int i=0; i<relacionI.columnKeySet().size(); i++){
            if(i!=coincideRI){
                lista.add(relacionI.get(0, i).toString());
            }
        }

        T1 = proyeccion(relacionI, lista);
        //System.out.println("T1: "+T1.cellSet());
        producto = productoCartesiano(T1, relacionD);
        
        //eliminar las palabras relacionI y relacinD de cada columna
        for(int j=0; j<producto.columnKeySet().size(); j++){
            valor = producto.get(0, j).toString();
            nombre = valor.split("\\.");
            if(nombre.length==2)
                producto.put(0, j, nombre[1]);
            else if(nombre.length==1)
                producto.put(0, j, nombre[0]);
        }
        diferencia = diferencia(producto, relacionI);
        T2 = proyeccion(diferencia, lista);
        nuevaRelacion = diferencia(T1, T2);
        return nuevaRelacion;
    }

    public Table OperacionSeleccion(NodoBase predicado, Table relacion){
        Table<Integer, Integer, Object> nuevaRelacion = TreeBasedTable.create();
        
        while(predicado != null){
            if(predicado instanceof NodoComparacion){
                nuevaRelacion = miniArbol((NodoComparacion)predicado,relacion);
            }else if(predicado instanceof NodoOperadorLogico){
                nuevaRelacion = miniArbol((NodoOperadorLogico)predicado,relacion);
            }
            predicado = predicado.getHermanoDerecha();
        }
        return nuevaRelacion;
    }

    public Table miniArbol(NodoBase raiz, Table relacion){
        Table<Integer, Integer, Object> nuevaRelacion = TreeBasedTable.create();
        Table<Integer, Integer, Object> relacionI = TreeBasedTable.create();
        Table<Integer, Integer, Object> relacionD = TreeBasedTable.create();
        Table<Integer, Integer, Object> tablaAuxiliar = TreeBasedTable.create();
        NodoBase operacion = new NodoBase();
        ArrayList<NodoBase> listaPredicadoAuxiliar = new ArrayList<NodoBase>();
        String izq ="", der = "";
        Date fecha = new Date();
        SimpleDateFormat fomateador = new SimpleDateFormat("dd/MM/yyyy");

        if(raiz instanceof NodoComparacion){

            tipoOp sel = ((NodoComparacion) raiz).getOperacion();

                if (sel == tipoOp.diferente){
                    izq = ((NodoId)((NodoComparacion)raiz).getOpIzquierdo()).getId();
                    if(((NodoComparacion)raiz).getOpDerecho() instanceof NodoId){
                        der = ((NodoId)((NodoComparacion)raiz).getOpDerecho()).getId();
                        nuevaRelacion = seleccion(relacion, izq, der, tipoOp.diferente);
                    }else if(((NodoComparacion)raiz).getOpDerecho() instanceof NodoNumero){
                        der = ((NodoNumero)((NodoComparacion)raiz).getOpDerecho()).getNumero().toString();
                        nuevaRelacion = seleccion(relacion, izq, der, tipoOp.diferente);
                    }else if(((NodoComparacion)raiz).getOpDerecho() instanceof NodoComparacion){
                        operacion = ((NodoComparacion)raiz).getOpDerecho();
                        listaPredicadoAuxiliar.add(operacion);
                        der = ((NodoId)((NodoComparacion)operacion).getOpIzquierdo()).getId();
                        nuevaRelacion = operacionMatematica(listaPredicadoAuxiliar, relacion);
                        nuevaRelacion = seleccion(nuevaRelacion, izq, der, tipoOp.diferente);
                    }else if(((NodoComparacion)raiz).getOpDerecho() instanceof NodoFecha){
                        der = ((NodoFecha)((NodoComparacion)raiz).getOpDerecho()).getFecha();
                    try {
                        fecha = fomateador.parse(der);
                    } catch (ParseException ex) {
                        Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        nuevaRelacion = seleccion(relacion, izq, fecha, tipoOp.diferente);
                    }
                }
                if (sel == tipoOp.igual){
                    izq = ((NodoId)((NodoComparacion)raiz).getOpIzquierdo()).getId();
                    if(((NodoComparacion)raiz).getOpDerecho() instanceof NodoId){
                        der = ((NodoId)((NodoComparacion)raiz).getOpDerecho()).getId();
                        nuevaRelacion = seleccion(relacion, izq, der, tipoOp.igual);
                    }else if(((NodoComparacion)raiz).getOpDerecho() instanceof NodoNumero){
                        der = ((NodoNumero)((NodoComparacion)raiz).getOpDerecho()).getNumero().toString();
                        nuevaRelacion = seleccion(relacion, izq, der, tipoOp.igual);
                    }else if(((NodoComparacion)raiz).getOpDerecho() instanceof NodoComparacion){
                        operacion = ((NodoComparacion)raiz).getOpDerecho();
                        listaPredicadoAuxiliar.add(operacion);
                        der = ((NodoId)((NodoComparacion)operacion).getOpIzquierdo()).getId();
                        nuevaRelacion = operacionMatematica(listaPredicadoAuxiliar, relacion);
                        nuevaRelacion = seleccion(nuevaRelacion, izq, der, tipoOp.igual);
                    }else if(((NodoComparacion)raiz).getOpDerecho() instanceof NodoFecha){
                        der = ((NodoFecha)((NodoComparacion)raiz).getOpDerecho()).getFecha();
                    try {
                        fecha = fomateador.parse(der);
                    } catch (ParseException ex) {
                        Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        nuevaRelacion = seleccion(relacion, izq, fecha, tipoOp.igual);
                    }
                }
                if (sel == tipoOp.mayor){
                    izq = ((NodoId)((NodoComparacion)raiz).getOpIzquierdo()).getId();
                    if(((NodoComparacion)raiz).getOpDerecho() instanceof NodoNumero){
                        der = ((NodoNumero)((NodoComparacion)raiz).getOpDerecho()).getNumero().toString();
                        nuevaRelacion = seleccion(relacion, izq, der, tipoOp.mayor);
                    }else if(((NodoComparacion)raiz).getOpDerecho() instanceof NodoId){
                        der = ((NodoId)((NodoComparacion)raiz).getOpDerecho()).getId();
                        nuevaRelacion = seleccion(relacion, izq, der, tipoOp.mayor);
                    }else if(((NodoComparacion)raiz).getOpDerecho() instanceof NodoComparacion){
                        operacion = ((NodoComparacion)raiz).getOpDerecho();
                        listaPredicadoAuxiliar.add(operacion);
                        der = ((NodoId)((NodoComparacion)operacion).getOpIzquierdo()).getId();
                        nuevaRelacion = operacionMatematica(listaPredicadoAuxiliar, relacion);
                        nuevaRelacion = seleccion(nuevaRelacion, izq, der, tipoOp.mayor);
                    }else if(((NodoComparacion)raiz).getOpDerecho() instanceof NodoFecha){
                        der = ((NodoFecha)((NodoComparacion)raiz).getOpDerecho()).getFecha();
                    try {
                        fecha = fomateador.parse(der);
                    } catch (ParseException ex) {
                        Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        nuevaRelacion = seleccion(relacion, izq, fecha, tipoOp.mayor);
                    }
                }
                if (sel == tipoOp.mayorIgual){
                    izq = ((NodoId)((NodoComparacion)raiz).getOpIzquierdo()).getId();
                    if(((NodoComparacion)raiz).getOpDerecho() instanceof NodoNumero){
                        der = ((NodoNumero)((NodoComparacion)raiz).getOpDerecho()).getNumero().toString();
                        nuevaRelacion = seleccion(relacion, izq, der, tipoOp.mayorIgual);
                    }else if(((NodoComparacion)raiz).getOpDerecho() instanceof NodoId){
                        der = ((NodoId)((NodoComparacion)raiz).getOpDerecho()).getId();
                        nuevaRelacion = seleccion(relacion, izq, der, tipoOp.mayorIgual);
                    }else if(((NodoComparacion)raiz).getOpDerecho() instanceof NodoComparacion){
                        operacion = ((NodoComparacion)raiz).getOpDerecho();
                        listaPredicadoAuxiliar.add(operacion);
                        der = ((NodoId)((NodoComparacion)operacion).getOpIzquierdo()).getId();
                        nuevaRelacion = operacionMatematica(listaPredicadoAuxiliar, relacion);
                        nuevaRelacion = seleccion(nuevaRelacion, izq, der, tipoOp.mayorIgual);
                    }else if(((NodoComparacion)raiz).getOpDerecho() instanceof NodoFecha){
                        der = ((NodoFecha)((NodoComparacion)raiz).getOpDerecho()).getFecha();
                    try {
                        fecha = fomateador.parse(der);
                    } catch (ParseException ex) {
                        Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        nuevaRelacion = seleccion(relacion, izq, fecha, tipoOp.mayorIgual);
                    }
                }
                if (sel == tipoOp.menor){
                    izq = ((NodoId)((NodoComparacion)raiz).getOpIzquierdo()).getId();
                    if(((NodoComparacion)raiz).getOpDerecho() instanceof NodoNumero){
                        der = ((NodoNumero)((NodoComparacion)raiz).getOpDerecho()).getNumero().toString();
                        nuevaRelacion = seleccion(relacion, izq, der, tipoOp.menor);
                    }else if(((NodoComparacion)raiz).getOpDerecho() instanceof NodoId){
                        der = ((NodoId)((NodoComparacion)raiz).getOpDerecho()).getId();
                        nuevaRelacion = seleccion(relacion, izq, der, tipoOp.menor);
                    }else if(((NodoComparacion)raiz).getOpDerecho() instanceof NodoComparacion){
                        operacion = ((NodoComparacion)raiz).getOpDerecho();
                        listaPredicadoAuxiliar.add(operacion);
                        der = ((NodoId)((NodoComparacion)operacion).getOpIzquierdo()).getId();
                        nuevaRelacion = operacionMatematica(listaPredicadoAuxiliar, relacion);
                        nuevaRelacion = seleccion(nuevaRelacion, izq, der, tipoOp.menor);
                    }else if(((NodoComparacion)raiz).getOpDerecho() instanceof NodoFecha){
                        der = ((NodoFecha)((NodoComparacion)raiz).getOpDerecho()).getFecha();
                    try {
                        fecha = fomateador.parse(der);
                    } catch (ParseException ex) {
                        Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        nuevaRelacion = seleccion(relacion, izq, fecha, tipoOp.menor);
                    }
                }
                if (sel == tipoOp.menorIgual){
                    izq = ((NodoId)((NodoComparacion)raiz).getOpIzquierdo()).getId();
                    if(((NodoComparacion)raiz).getOpDerecho() instanceof NodoNumero){
                        der = ((NodoNumero)((NodoComparacion)raiz).getOpDerecho()).getNumero().toString();
                        nuevaRelacion = seleccion(relacion, izq, der, tipoOp.menorIgual);
                    }else if(((NodoComparacion)raiz).getOpDerecho() instanceof NodoId){
                        der = ((NodoId)((NodoComparacion)raiz).getOpDerecho()).getId();
                        nuevaRelacion = seleccion(relacion, izq, der, tipoOp.menorIgual);
                    }else if(((NodoComparacion)raiz).getOpDerecho() instanceof NodoComparacion){
                        operacion = ((NodoComparacion)raiz).getOpDerecho();
                        listaPredicadoAuxiliar.add(operacion);
                        der = ((NodoId)((NodoComparacion)operacion).getOpIzquierdo()).getId();
                        nuevaRelacion = operacionMatematica(listaPredicadoAuxiliar, relacion);
                        nuevaRelacion = seleccion(nuevaRelacion, izq, der, tipoOp.menorIgual);
                    }else if(((NodoComparacion)raiz).getOpDerecho() instanceof NodoFecha){
                        der = ((NodoFecha)((NodoComparacion)raiz).getOpDerecho()).getFecha();
                    try {
                        fecha = fomateador.parse(der);
                    } catch (ParseException ex) {
                        Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        nuevaRelacion = seleccion(relacion, izq, fecha, tipoOp.menorIgual);
                    }
                }
        }else if(raiz instanceof NodoId){
               String sino = (((NodoId) raiz).getSinonimo()!=null)?((NodoId) raiz).getSinonimo():"";
               //System.out.println("ID:"+((NodoId) raiz).getId()+""+sino);
        }else if(raiz instanceof NodoOperadorLogico){

            tipoOp sel = ((NodoOperadorLogico) raiz).getOperacion();

                if (sel == tipoOp.and){
                    if(((NodoOperadorLogico)raiz).getOpeI() instanceof NodoComparacion){
                        pila.add(miniArbol(((NodoOperadorLogico)raiz).getOpeI(), relacion));
                    }else
                        OperacionSeleccion(((NodoOperadorLogico)raiz).getOpeI(), relacion);

                    relacionI = (Table<Integer, Integer, Object>)pila.pop();

                    if(((NodoOperadorLogico)raiz).getOpeD() instanceof NodoComparacion){
                        pila.add(miniArbol(((NodoOperadorLogico)raiz).getOpeD(), relacionI));
                    }else
                        OperacionSeleccion(((NodoOperadorLogico)raiz).getOpeD(), relacion);
                    relacionD = (Table<Integer, Integer, Object>)pila.pop();
                    pila.add(relacionD);
                    nuevaRelacion = pila.pop();
                }
                if (sel == tipoOp.or){
                    if(((NodoOperadorLogico)raiz).getOpeI() instanceof NodoComparacion){
                        pila.add(miniArbol(((NodoOperadorLogico)raiz).getOpeI(), relacion));
                    }else
                        OperacionSeleccion(((NodoOperadorLogico)raiz).getOpeI(), relacion);

                    relacionI = (Table<Integer, Integer, Object>)pila.pop();

                    if(((NodoOperadorLogico)raiz).getOpeD() instanceof NodoComparacion){
                        pila.add(miniArbol(((NodoOperadorLogico)raiz).getOpeD(), relacion));
                    }else
                        OperacionSeleccion(((NodoOperadorLogico)raiz).getOpeD(), relacion);
                    relacionD = (Table<Integer, Integer, Object>)pila.pop();
                    tablaAuxiliar = union(relacionI, relacionD);
                    pila.add(tablaAuxiliar);
                    nuevaRelacion = pila.pop();
                }
                if (sel == tipoOp.not){

                    if(((NodoOperadorLogico)raiz).getOpeD() instanceof NodoComparacion){
                        pila.add(miniArbol(((NodoOperadorLogico)raiz).getOpeD(), relacion));
                    }else
                        OperacionSeleccion(((NodoOperadorLogico)raiz).getOpeD(), relacion);
                    
                    relacionD = (Table<Integer, Integer, Object>)pila.pop();
                    tablaAuxiliar = diferencia(relacion, relacionD);
                    pila.add(tablaAuxiliar);
                    nuevaRelacion = pila.pop();
                }
        }
        return nuevaRelacion;
    }

    public Table operacionMatematica(List<NodoBase> lista, Table tabla){
        String campo="";
        int indice=0, valor=0;
        double numero=0;
        tipoOp ope;

        //tengo cada elemento de la lista q es un nodo comparacion
        for(int i=0; i<lista.size(); i++){
            campo = (((NodoId)((NodoComparacion)lista.get(i)).getOpIzquierdo()).getId());
            ope = ((NodoComparacion)lista.get(i)).getOperacion();
            valor = (((NodoNumero)((NodoComparacion)lista.get(i)).getOpDerecho()).getNumero());
            if(ope == ope.multi){
                for(int j=0; j<tabla.columnKeySet().size(); j++){
                    if(tabla.get(0, j).equals(campo)){
                        indice = j;
                        for(int h=1;h<tabla.rowKeySet().size();h++){
                            numero = Double.parseDouble(tabla.get(h, indice).toString())*valor;
                            tabla.put(h, indice, numero);
                        }
                    }
                }
            }else if(ope == ope.divi){
                for(int j=0; j<tabla.columnKeySet().size(); j++){
                    if(tabla.get(0, j).equals(campo)){
                         indice = j;
                        for(int h=1;h<tabla.rowKeySet().size();h++){
                            numero = Double.parseDouble(tabla.get(h, indice).toString())/valor;
                            tabla.put(h, indice, numero);
                        }
                    }
                }
            }else if(ope == ope.suma){
                for(int j=0; j<tabla.columnKeySet().size(); j++){
                    if(tabla.get(0, j).equals(campo)){
                        indice = j;
                        for(int h=1;h<tabla.rowKeySet().size();h++){
                            numero = Double.parseDouble(tabla.get(h, indice).toString())+valor;
                            tabla.put(h, indice, numero);
                        }
                    }
                }
            }else if(ope == ope.resta){
                for(int j=0; j<tabla.columnKeySet().size(); j++){
                    if(tabla.get(0, j).equals(campo)){
                        indice = j;
                        for(int h=1;h<tabla.rowKeySet().size();h++){
                            numero = Double.parseDouble(tabla.get(h, indice).toString())-valor;
                            tabla.put(h, indice, numero);
                        }
                    }
                }
            }
        }
        return tabla;
    }
}