/**
 * Contiene el conjunto de los problemas implementados seg�n el paradigma
 * del espacio de estados.
 */
package problemas;

import aima.search.*;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Clase que implementa el problema de los misioneros y los can�bales seg�n
 * el paradigma del espacio de estados.
 */
public class Canibales extends Problema{

	/**
	 * N�mero de misioneros en el margen izquierdo.
	 */
	int numMisionerosIzq;
	
	/**
	 * N�mero de can�bales en el margen izquierdo.
	 */
	int numCanibalesIzq;
	
	/**
	 * Posici�n de la barca (Margen del r�o: 1-izquierda, 0-derecha).
	 */
	int posBarca;
	
	/**
	 * Constructor por defecto: inicializa las componentes a valores por defecto.
	 */
	public Canibales(){
		enunciado = "Tres misioneros y tres can�bales est�n a la orilla de un r�o" +
			" que quieren cruzar. Para ello disponen de un bote que tiene como" +
			" capacidad m�xima 2 personas. El objetivo consiste en conseguir " +
			"que todos acaben en la otra orilla del r�o sin que en ning�n " +
			"momento los misioneros est�n en peligro de ser devorados por los" +
			" can�bales. Se considera que los misioneros est�n en peligro " +
			"cuando, en un determinado lugar, el n�mero de can�bales supera al" +
			" de misioneros. Consigue trasladar a todos al margen derecho del r�o.";
			
		posBarca = 1;
		numMisionerosIzq = 3;
		numCanibalesIzq = 3;
		repEstado = "("+numMisionerosIzq+","+numCanibalesIzq+","+posBarca+")";
		nombreOperador = "";
	}
	
	/**
	 * Constructor principal, inicializa los atributos a los valores indicados.
	 * @param pBarca Posici�n de la barca.
	 * @param nMisioneros N�mero de misioneros en el margen izquierdo.
	 * @param nCanibales N�mero de can�bales en el margen izquierdo.
	 */
	public Canibales(int nMisioneros, int nCanibales, int pBarca){
		enunciado = "Tres misioneros y tres can�bales est�n a la orilla de un r�o" +
			" que quieren cruzar. Para ello disponen de un bote que tiene como" +
			" capacidad m�xima 2 personas. El objetivo consiste en conseguir " +
			"que todos acaben en la otra orilla del r�o sin que en ning�n " +
			"momento los misioneros est�n en peligro de ser devorados por los" +
			" can�bales. Se considera que los misioneros est�n en peligro " +
			"cuando, en un determinado lugar, el n�mero de can�bales supera al" +
			" de misioneros. Consigue trasladar a todos al margen derecho del r�o.";
		posBarca = pBarca;
		numMisionerosIzq = nMisioneros;
		numCanibalesIzq = nCanibales;
		nombreOperador = "";
		repEstado = "("+numMisionerosIzq+","+numCanibalesIzq+","+posBarca+")";
	}
	
	/**
	 * Genera el valor heur�stico del estado.
	 * @return Valor de la he�ristica. 
	 */
	public float h() {
		// Heur�stica: Mejor cuanto menor sea h, es decir, cuanto menos componentes
		// est�n en el lado izquierdo.
		int h = numMisionerosIzq + numCanibalesIzq - posBarca;
		return (float)h;
	}

	/**
	 * Indica si estamos ante un estado soluci�n.
	 * @return Valor indicando si el estado es soluci�n o no.
	 */
	public boolean isGoal() {
		boolean solucion = false;
		// Soluci�n si todos los componentes est�n a la derecha.
		if ((numMisionerosIzq == 0)&&(numCanibalesIzq == 0)&&(posBarca == 0)){
			solucion = true;
		}
		return solucion;
	}

	/**
	 * Comprueba si el estado del problema es v�lido.
	 * @return Valor indicando la validez del estado.
	 */
	protected boolean isValid() {
		
		if (nodosExpandidos > maxNodos){
			return false;
		}
		
        // Si el n�mero de can�bales supera al de misioneros en la izquierda 
        // (siempre que halla alguno).
        if ((numMisionerosIzq > 0) && (numCanibalesIzq > numMisionerosIzq)){
        	return false;
        }

        // Si el n�mero de can�bales supera al de misioneros en la derecha 
        // (siempre que halla alguno).
        if (((3-numMisionerosIzq) > 0) && ((3-numCanibalesIzq) > (3-numMisionerosIzq))){
        	return false;
        }

        return true;
	}

	/**
	 * Genera todos los posibles estados sucesores del estado actual.
	 * @return Conjunto de estados sucesores.	 
	 */
	public Enumeration successors() {
		
		// OPERADORES:
		//  - Operador 0: Cruza un canibal.
		//  - Operador 1: Cruzan dos canibales.
		//  - Operador 2: Cruza un misionero.
		//  - Operador 3: Cruzan dos misioneros.
		//  - Operador 4: Cruza un misionero y un canibal.
		
	 	// Operador usado.
	 	int numOperador;
		
	 	// Nuevas posiciones.
		int nnumMisionerosIzq = 0;
	    int nnumCanibalesIzq = 0;
	    int nposBarca = 0;
	    Vector successorVec = new Vector();
	    
	    // Incrementamos los nodos expandidos.
	    nodosExpandidos++;
	    
	    for (numOperador=0; numOperador<5; numOperador++){
	    	
	    	// Operador 0: Cruza un canibal.
	 		if (numOperador == 0){
	 			// La barca est� en la izquierda
	 			if (posBarca == 1){
	 				// Si hay canibales en la izquierda.
	 				if (numCanibalesIzq>0){
	 					nombreOperador = "cruzaCanibal";
	 				    // Cruza canibal.
	 				    nnumCanibalesIzq = numCanibalesIzq - 1;
	 				    // Cruza la barca.
	 				    nposBarca = 0;
	 				    // Misioneros se quedan igual.
	 				    nnumMisionerosIzq = numMisionerosIzq;
	 				}

	 			}else{// La barca est� a la derecha.
	 				// Si hay canibales en la derecha.
	 				if ((3-numCanibalesIzq)>0){
	 					nombreOperador = "cruzaCanibal";
	 				    // Cruza canibal.
	 				    nnumCanibalesIzq = numCanibalesIzq + 1;
	 				    // Cruza la barca.
	 				    nposBarca = 1;
	 				    // Misioneros se quedan igual.
	 				    nnumMisionerosIzq = numMisionerosIzq;
	 				}
	 			}
	 	 	}
	    
	 		// Operador 1: Cruzan dos canibales.
	 		if (numOperador == 1){

	 			// La barca est� en la izquierda
	 			if (posBarca == 1){
	 				// Si hay al menos dos canibales en la izquierda.
	 				if (numCanibalesIzq>1){
	 					nombreOperador = "cruzanCanibales";
	 				    // Cruzan dos canibales.
	 				    nnumCanibalesIzq = numCanibalesIzq - 2;
	 				    // Cruza la barca.
	 				    nposBarca = 0;
	 				    // Misioneros se quedan igual.
	 				    nnumMisionerosIzq = numMisionerosIzq;
	 				}

	 			}else{// La barca est� a la derecha.
	 				// Si hay al menos dos canibales en la derecha.
	 				if ((3-numCanibalesIzq)>1){
	 					nombreOperador = "cruzanCanibales";
	 				    // Cruzan dos canibales.
	 				    nnumCanibalesIzq = numCanibalesIzq + 2;
	 				    // Cruza la barca.
	 				    nposBarca = 1;
	 				    // Misioneros se quedan igual.
	 				    nnumMisionerosIzq = numMisionerosIzq;
	 				}
	 			}
	 	 	}
	 		
	    	// Operador 2: Cruza un misionero.
	 		if (numOperador == 2){
	 			// La barca est� en la izquierda
	 			if (posBarca == 1){
	 				// Si hay misioneros en la izquierda.
	 				if (numMisionerosIzq>0){
	 					nombreOperador = "cruzaMisionero";
	 				    // Cruza misionero.
	 				    nnumMisionerosIzq = numMisionerosIzq - 1;
	 				    // Cruza la barca.
	 				    nposBarca = 0;
	 				    // Canibales se quedan igual.
	 				    nnumCanibalesIzq = numCanibalesIzq;
	 				}

	 			}else{// La barca est� a la derecha.
	 				// Si hay misioneros en la derecha.
	 				if ((3-numMisionerosIzq)>0){
	 					nombreOperador = "cruzaMisionero";
	 				    // Cruza canibal.
	 					nnumMisionerosIzq = numMisionerosIzq + 1;
	 				    // Cruza la barca.
	 					nposBarca = 1;
	 				    // Misioneros se quedan igual.
	 				    nnumCanibalesIzq = numCanibalesIzq;
	 				}
	 			}
	 	 	}
	 		
	    	// Operador 3: Cruzan dos misioneros.
	 		if (numOperador == 3){
	 			// La barca est� en la izquierda
	 			if (posBarca == 1){
	 				// Si hay al menos dos misioneros en la izquierda.
	 				if (numMisionerosIzq>1){
	 					nombreOperador = "cruzanMisioneros";
	 				    // Cruzan dos misioneros.
	 					nnumMisionerosIzq = numMisionerosIzq - 2;
	 				    // Cruza la barca.
	 					nposBarca = 0;
	 				    // Canibales se quedan igual.
	 				    nnumCanibalesIzq = numCanibalesIzq;
	 				}

	 			}else{// La barca est� a la derecha.
	 				// Si hay al menos dos misioneros en la derecha.
	 				if ((3-numMisionerosIzq)>1){
	 					nombreOperador = "cruzanMisioneros";
	 				    // Cruzan dos misioneros.
	 					nnumMisionerosIzq = numMisionerosIzq + 2;
	 				    // Cruza la barca.
	 					nposBarca = 1;
	 				    // Canibales se quedan igual.
	 				    nnumCanibalesIzq = numCanibalesIzq;
	 				}
	 			}
	 	 	}
	 		
	    	// Operador 4: Cruza un misionero y un canibal.
	 		if (numOperador == 4){
	 			// La barca est� en la izquierda
	 			if (posBarca == 1){
	 				// Si hay al menos hay un misionero y un canibal en la izquierda.
	 				if ((numMisionerosIzq>0)&&(numCanibalesIzq>0)){
	 					nombreOperador = "cruzaMisioneroCanibal";
	 				    // Cruzan un misionero.
	 					nnumMisionerosIzq = numMisionerosIzq - 1;
	 				    // Cruza un canibal.
	 				    nnumCanibalesIzq = numCanibalesIzq - 1;
	 				    // Cruza la barca.
	 				    nposBarca = 0;
	 				}

	 			}else{// La barca est� a la derecha.
	 				// Si hay al menos hay un misionero y un canibal en la derecha.
	 				if (((3-numMisionerosIzq)>0)&&((3-numCanibalesIzq)>0)){
	 					nombreOperador = "cruzaMisioneroCanibal";
	 				    // Cruzan un misionero.
	 					nnumMisionerosIzq = numMisionerosIzq + 1;
	 				    // Cruza un canibal.
	 				    nnumCanibalesIzq = numCanibalesIzq + 1;
	 				    // Cruza la barca.
	 				    nposBarca = 1;
	 				}
	 			}
	 	 	}
	 		
	 	 	// Creamos el nuevo estado.
	 	 	Canibales nuevoEstado = new Canibales(nnumMisionerosIzq, nnumCanibalesIzq, nposBarca);
	 	 	
	 	 	// Comprobamos si el nuevo estado es v�lido.
	 	 	if(nuevoEstado.isValid()){	 	 		
	 	 		// A�adimos el estado como sucesor.
	 	 		successorVec.addElement(new Successor(nuevoEstado,nombreOperador,1)); 
	 	 	}
		}

	    return successorVec.elements();
	}
}
