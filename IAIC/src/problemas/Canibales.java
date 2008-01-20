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
			" de misioneros.";
			
		posBarca = 1;
		numMisionerosIzq = 3;
		numCanibalesIzq = 3;
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
			" de misioneros.";
		posBarca = pBarca;
		numMisionerosIzq = nMisioneros;
		numCanibalesIzq = nCanibales;
	}
	
	/**
	 * M�todo accesor para el atributo enunciado.
	 * @return Valor del atributo enunciado.
	 */
	public String getEnunciado(){
		return enunciado;
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
		
        // Comprobaciones para no salirse de rango.
        if ((posBarca < 0) || (numMisionerosIzq < 0) || (numCanibalesIzq < 0)){
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
	    
	 	// Nuevas posiciones.
		int nnumMisioneros;
	    int nnumCanibales;
	    int nposBarco;
	    Vector successorVec = new Vector();
	    
	    for (nposBarco= 0; nposBarco<2; nposBarco++){
	    	for (nnumMisioneros=0; nnumMisioneros<3; nnumMisioneros++){
	    		for (nnumCanibales=0; nnumCanibales+nnumMisioneros<3; nnumCanibales++) {
	    	  		
	    			if ((nnumMisioneros+nnumCanibales)>0){
	    	  			// Creamos el nuevo estado.
	    	  			Canibales nuevoEstado = 
	    	  				new Canibales(numMisionerosIzq+nposBarco*nnumMisioneros,
	    	  							numCanibalesIzq+nposBarco*nnumCanibales,
	    	  							posBarca+nposBarco);
	                    
	    	  			// Comprobamos si el nuevo estado es v�lido.
	    	  			if (nuevoEstado.isValid()) {
	    	  				// A�adimos el estado como sucesor.
	    	  			   successorVec.addElement(
	    	  					   new Successor(nuevoEstado,"(" + nnumMisioneros + "," + nnumCanibales + ","+ nposBarco + ")", 1)); 
	    	  			}
	    	  		}  
	    	  	}
	      	}
	    }

	    return successorVec.elements();
	}

}
