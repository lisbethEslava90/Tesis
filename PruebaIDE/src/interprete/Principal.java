/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interprete;
import com.google.common.collect.Table;
import nodos.NodoBase;
import java_cup.runtime.DefaultSymbolFactory;
import java_cup.runtime.SymbolFactory;


/**
 *
 * @author Julio
 */
public class Principal {

    private Table tabla;
    public Principal(String consulta) throws Exception {
        SymbolFactory sf = new DefaultSymbolFactory();
        parser parser_obj = new parser(new Scanner(consulta,sf),sf);
        parser_obj.parse();
        NodoBase raiz =  parser_obj.action_obj.getRaiz();
	//Util.imprimirArbol(raiz);//Llamada a la clase util para imprimir el arbol
        tabla = new Interprete(raiz).inicio();
    }

    public Table getTabla() {
        return tabla;
    }
}