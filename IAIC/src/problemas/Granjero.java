/**
 * Contiene el conjunto de los problemas implementados seg�n el paradigma
 * del espacio de estados.
 */
package problemas;

import aima.search.*;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Clase que implementa el problema del Granjero, el lobo, la cabra y la col seg�n
 * el paradigma del espacio de estados.
 */
public class Granjero extends Problema{

	/**
	 * Posici�n del granjero (Margen del r�o: 1-izquierda, 0-derecha).
	 */
	int posGranjero;
	
	/**
	 * Posici�n del lobo (Margen del r�o: 1-izquierda, 0-derecha).
	 */
	int posLobo;
	
	/**
	 * Posici�n de la cabra (Margen del r�o: 1-izquierda, 0-derecha).
	 */
	int posCabra;
	
	/**
	 * Posici�n de la col (Margen del r�o: 1-izquierda, 0-derecha).
	 */
	int posCol;

	/**
	 * Constructor por defecto: inicializa las componentes a valores por defecto.
	 */
	public Granjero() {
		enunciado = " Un granjero se encuentra en la orilla izquierda de un r�o junto con\n"
			+ " un lobo, una cabra y una col. Dispone de un bote en el que s�lo\n"
			+ " puede transportar una �nica cosa cada vez. El granjero pretende\n"
			+ " transportar al lobo, la cabra y la col al otro lado del r�o, utilizando\n"
			+ " el bote. Sin embargo, debe tener cuidado y no dejar solos en una\n"
			+ " orilla al lobo y a la cabra porque el lobo se comer�a a la cabra.\n"
			+ " Tampoco puede dejar solas a la cabra y la col porque la cabra se\n"
			+ " comer�a la col. Consigue que el granjero traslade a todos al\n"
		    + " margen derecho del r�o.";
		
		posGranjero = 1;
		posLobo = 1;
		posCabra = 1;
		posCol = 1;
		repEstado = "("+posGranjero+","+posLobo+","+posCabra+","+posCol+")";
		nombreOperador = "";
	}
	
	/**
	 * Constructor principal, inicializa los atributos a los valores indicados.
	 * @param pGranjero Posici�n del granjero.
	 * @param pLobo Posici�n del lobo.
	 * @param pCabra Posici�n de la cabra.
	 * @param pCol Posici�n de la col.
	 */
	public Granjero(int pGranjero, int pLobo, int pCabra, int pCol) {
		enunciado = " Un granjero se encuentra en la orilla izquierda de un r�o junto con\n"
			+ " un lobo, una cabra y una col. Dispone de un bote en el que s�lo\n"
			+ " puede transportar una �nica cosa cada vez. El granjero pretende\n"
			+ " transportar al lobo, la cabra y la col al otro lado del r�o, utilizando\n"
			+ " el bote. Sin embargo, debe tener cuidado y no dejar solos en una\n"
			+ " orilla al lobo y a la cabra porque el lobo se comer�a a la cabra.\n"
			+ " Tampoco puede dejar solas a la cabra y la col porque la cabra se\n"
			+ " comer�a la col. Consigue que el granjero traslade a todos al\n"
		    + " margen derecho del r�o.";
		
		posGranjero = pGranjero;
		posLobo = pLobo;
		posCabra = pCabra;
		posCol = pCol;
		repEstado = "("+posGranjero+","+posLobo+","+posCabra+","+posCol+")";
		nombreOperador = "";
	}
	
	/**
	 * Genera el valor heur�stico del estado.
	 * @return Valor de la he�ristica. 
	 */
	public float h() {
		// Heur�stica: Mejor cuanto menor sea h, es decir, cuanto menos componentes
		// est�n en el lado izquierdo.
		int h = posGranjero + posLobo + posCabra + posCol;
		return (float)h;
	}

	/**
	 * Indica si estamos ante un estado soluci�n.
	 * @return Valor indicando si el estado es soluci�n o no.
	 */
	public boolean isGoal() {
		// Soluci�n si todos los componentes est�n a la derecha.
		return ((posGranjero == 0)&&(posLobo == 0)&&(posCabra == 0)&&(posCol == 0));
	}

	/**
	 * Comprueba si el estado del problema es v�lido.
	 * @return Valor indicando la validez del estado.
	 */
	protected boolean isValid() {
		if (nodosExpandidos > maxNodos){
			return false;
		}
		
		// Si el lobo est� con la cabra en un margen y el granjero est� 
		// en la contraria.
		if ((posLobo == posCabra) && (posGranjero!=posLobo)){
			return false;
		}
		
		// Si el cabra est� con la col en un margen y el granjero est� 
		// en la contraria.
		if ((posCabra == posCol) && (posGranjero!=posCabra)){
		  	return false;
		}
		
		return true;	
	}

	/**
	 * Genera todos los posibles estados sucesores del estado actual.
	 * @return Conjunto de estados sucesores.	 
	 */
	public Enumeration<Successor> successors() {
		
		// OPERADORES:
		//  - Operador 0: Cruza el lobo (con el granjero).
		//  - Operador 1: Cruza la cabra (con el granjero).
		//  - Operador 2: Cruza la col (con el granjero).
		//  - Operador 3: Cruza el granjero solo.
		
	 	// Operador usado.
	 	int numOperador;
	 	
	 	// Nuevas posiciones.
	 	int nposLobo = 1;
	 	int nposCabra = 1;
	 	int nposCol = 1;
	 	int nposGranjero  = 1;
	 	Vector<Successor> successor = new Vector<Successor>();
	 	
	 	// Incrementamos el n�mero de nodos expandidos.
	 	nodosExpandidos++;
	 	 
	 	for (numOperador = 0; numOperador <4; numOperador++){
	 		boolean operadorAplicado = false;
	 		
	 		// Operador 0: Cruza el lobo (con el granjero).
	 		if (numOperador == 0){
	 			// El lobo y el granjero deben estar en la misma orilla
	 			// para poder cruzar.
	 			if (posGranjero == posLobo){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaLobo";
	 				// Cruza el granjero.
		 	 		nposGranjero = 1 - posGranjero;
		 	 		// Cruza el lobo.
		 	 		nposLobo = 1 - posLobo;
		 	 		// Los dem�s se quedan donde est�n.
		 	 		nposCabra = posCabra;
		 	 		nposCol = posCol;
	 			}
	 	 	}
	 		
	 		// Operador 1: Cruza la cabra (con el granjero).
	 	 	if (numOperador == 1){
	 	 		if (posGranjero == posCabra){
	 	 			operadorAplicado = true;
	 				nombreOperador = "cruzaCabra";
	 				// Cruza el granjero.
		 	 		nposGranjero = 1 - posGranjero;
		 	 		// Cruza la cabra.
		 	 		nposCabra = 1 - posCabra;
		 	 		// Los dem�s se quedan donde est�n.
		 	 		nposLobo = posLobo;
		 	 		nposCol = posCol;
	 	 		}
	 	 	}
	 	 	
	 	    // Operador 2: Cruza la col (con el granjero).
	 	 	if (numOperador == 2){
	 	 		if (posGranjero == posCol){
	 	 			operadorAplicado = true;
	 				nombreOperador = "cruzaCol";
	 				// Cruza el granjero.
		 	 		nposGranjero = 1 - posGranjero;
		 	 		// Cruza la cabra.
		 	 		nposCol = 1 - posCol;
		 	 		// Los dem�s se quedan donde est�n.
		 	 		nposLobo = posLobo;
		 	 		nposCabra = posCabra;
	 	 		}
	 	 	}
	 	 	
	 	 	// Operador 3: Cruza el granjero solo.
	 	 	if(numOperador == 3){
	 	 		operadorAplicado = true;
 				nombreOperador = "cruzaGranjero";
 				// Cruza el granjero.
	 	 		nposGranjero = 1 - posGranjero;
	 	 		// Los dem�s se quedan donde est�n.
	 	 		nposCol = posCol;
	 	 		nposLobo = posLobo;
	 	 		nposCabra = posCabra;
	 	 	}
	 	 	if (operadorAplicado){
		 	 	// Creamos el nuevo estado.
		 	 	Granjero nuevoEstado = new Granjero(nposGranjero,nposLobo,nposCabra,nposCol);
		 	 		
		 	 	// Comprobamos si el nuevo estado es v�lido.
		 	 	if(nuevoEstado.isValid()){	 	 		
		 	 		// A�adimos el estado como sucesor.
		 	 		successor.addElement(new Successor(nuevoEstado,nombreOperador,1)); 
		 	 	}
	 	 	}
	 	 }
	 	 return successor.elements();
	}
   
	/**
	 * Devuele el t�tulo del problema.
	 * @return El nombre del problema.
	 */
	public String dameTitulo(){
		return "Granjero";		
	}
}
