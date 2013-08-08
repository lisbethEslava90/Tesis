/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interprete;
import nodos.NodoBase;
import java_cup.runtime.DefaultSymbolFactory;
import java_cup.runtime.SymbolFactory;


/**
 *
 * @author Julio
 */
public class Principal {

    public static void main(String args[]) throws Exception {
		//TinySymbolFactory sf = new TinySymbolFactory();
		SymbolFactory sf = new DefaultSymbolFactory();
		/*if (args .length==0)
			new parser(new Scanner(System.in,sf),sf).parse();
		else
			new parser(new Scanner(new java.io.FileInputStream(args[0]),sf),sf).parse();
		*/
                
		parser parser_obj = new parser(new Scanner("PRO nombre_cliente (prestatario)",sf),sf);
		parser_obj.parse();

		NodoBase raiz =  parser_obj.action_obj.getRaiz();
		Util.imprimirArbol(raiz);
                new Interprete(raiz).inicio();
	}

}