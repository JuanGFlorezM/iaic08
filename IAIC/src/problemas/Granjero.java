/**
 * 
 */
package problemas;

import java.util.Enumeration;

/**
 *
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
		enunciado = "Un granjero se encuentra en la orilla izquierda de un r�o junto con un lobo, una cabra y"
		+ " una col. Dispone de un bote en el que s�lo puede transportar una �nica cosa cada vez. El"
		+ " granjero pretende transportar al lobo, la cabra y la col al otro lado del r�o, utilizando el"
		+ " bote. Sin embargo, debe tener cuidado y no dejar solos en una orilla al lobo y a la cabra"
		+ " porque el lobo se comer�a a la cabra. Tampoco puede dejar solas a la cabra y la col"
		+ " porque la cabra se comer�a la col. Consigue que el granjero traslade a todos a la"
		+ " margen derecha del r�o.";
		
		posGranjero = 1;
		posLobo = 1;
		posCabra = 1;
		posCol = 1;
	}
	
	/**
	 * Constructor principal, inicializa los atributos a los valores indicados.
	 * @param pGranjero Posici�n del granjero.
	 * @param pLobo Posici�n del lobo.
	 * @param pCabra Posici�n de la cabra.
	 * @param pCol Posici�n de la col.
	 */
	public Granjero(int pGranjero, int pLobo, int pCabra, int pCol) {
		enunciado = "Un granjero se encuentra en la orilla izquierda de un r�o junto con un lobo, una cabra y"
		+ " una col. Dispone de un bote en el que s�lo puede transportar una �nica cosa cada vez. El"
		+ " granjero pretende transportar al lobo, la cabra y la col al otro lado del r�o, utilizando el"
		+ " bote. Sin embargo, debe tener cuidado y no dejar solos en una orilla al lobo y a la cabra"
		+ " porque el lobo se comer�a a la cabra. Tampoco puede dejar solas a la cabra y la col"
		+ " porque la cabra se comer�a la col. Consigue que el granjero traslade a todos a la"
		+ " margen derecha del r�o.";
		
		posGranjero = pGranjero;
		posLobo = pLobo;
		posCabra = pCabra;
		posCol = pCol;
	}

	/**
	 * 
	 * @return 	 
	 */
	public float h() {
		
		return 0;
	}

	/**
	 * Indica si estamos ante un estado soluci�n.
	 * @return Valor indicando si el estado es soluci�n o no.
	 */
	public boolean isGoal() {
		boolean solucion = false;
		// Soluci�n si todos los componentes est�n a la derecha.
		if ((posGranjero == 0)&&(posLobo == 0)&&(posCabra == 0)&&(posCol == 0)){
			solucion = true;
		}
		return solucion;
	}

	/**
	 * Comprueba si el estado del problema es v�lido.
	 * @return Valor indicando la validez del estado.
	 */
	protected boolean isValid() {
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
	 * 
	 * @return 	 
	 */
	public Enumeration successors() {
		
		return null;
	}

}
