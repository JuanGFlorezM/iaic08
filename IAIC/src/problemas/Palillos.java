/**
 * Contiene el conjunto de los problemas implementados seg�n el paradigma
 * del espacio de estados.
 */
package problemas;

import java.util.Enumeration;
import java.util.Vector;

import aima.search.Successor;

/**
 * Clase que implementa el problema de los palillos
 * seg�n el paradigma del espacio de estados.
 */
public class Palillos extends Problema {

	/**
	 * palillos que quedan en la mesa
	 */
	private int palillos;
	
	/**
	 * nivel por el que vamos
	 */
	private int nivel;
	
	/**
	 * Constructora por defecto que crea el juego con 6 palillos
	 * en el nivel 1.
	 */
	public Palillos(){
		enunciado =" Se dispone inicialmente de un mont�n de 6 palillos.\n " +
				   " Los jugadores A y B alternan sus jugadas, comenzando\n" +
				   " el jugador A. En cada jugada un jugador puede retirar\n"+
				   " del mont�n 1, 2 o 3 palillos siempre que haya un n�mero\n"+
				   " suficiente de palillos en �ste. El jugador que retira\n"+
				   " el �ltimo palillo del mont�n es el que pierde.";
		palillos=6;
		nivel=1;
		repEstado = "Quedan "+ palillos +" palillos en el nivel "+nivel+".";
		nombreOperador = "";
	}
	/**
	 * Constructora con par�metros del estado de los palillos correspondiente
	 * @param p numero de palillos que hay en el juego
	 * @param n nivel en que se encuentra de expansi�n
	 */
	public Palillos(int p,int n){
		enunciado =" Se dispone inicialmente de un mont�n de 6 palillos.\n " +
				   " Los jugadores A y B alternan sus jugadas, comenzando\n" +
				   " el jugador A. En cada jugada un jugador puede retirar\n"+
				   " del mont�n 1, 2 o 3 palillos siempre que haya un n�mero\n"+
				   " suficiente de palillos en �ste. El jugador que retira\n"+
				   " el �ltimo palillo del mont�n es el que pierde.";
		palillos=p;
		nivel=n;
		String auxiliar = " palillo";
		if(p != 1){
			auxiliar = auxiliar+"s";
		}
		repEstado = "Quedan "+ palillos + auxiliar+" en el nivel "+nivel+".";
		nombreOperador = "";
	}
	/**
	 * heuristica del juego
	 * @return la heuristica dise�ada para este juego
	 */
	public float h() {
		// Mejor cuantos menos palillos quedan.
	     return 6-palillos;
	            
	  }
	/**
	 * Dice si un n�mero de nivel es par
	 * @param nivel n�mero a evaluar 
	 * @return Devuelve true si es par sino false
	 */
	/*private boolean multiploDos(int nivel){
		int mul=nivel;
		while(mul>=2){
			mul=mul-2;
		}
		if (mul==0) return true;
		return false;
	}*/
	private boolean multiploDos(int nivel){
		int mul=nivel;
		return ((mul%2)==0);
	}
	//hacer �ste metodo con modulo 2 yo creo q es =
	/**
	 * indica si un estado es solucion del problema o no
	 * @return true si ha llegado a solucion o false en caso contrario
	 */
	public boolean isGoal() {
		//�No quedan palillos y el nivel en el que estamos es par?.
	      return  ((palillos==0)&&(multiploDos(nivel)));
	      
	   }
	/**
	 * dice si un estado es valido
	 * @return true si es valido false en caso contrario
	 */
	protected boolean isValid(){
		return (nodosExpandidos < maxNodos);
	}
	/**
	 * Genera todos los posibles estados sucesores del estado actual.
	 * @return Conjunto de estados sucesores.	 
	 */
	public Enumeration successors() { 

	    // Tenemos 3 operadores:
		// Operador 1: Quitar un palillo.
		// Operador 2: Quitar dos palillos.
		// Operador 3: Quitar tres palillos.
	 
	    // Operador usado.
	 	int numOperador;
	 	
	 	// Nuevo nivel.
	    int nivel2=nivel+1;
	    
	    Vector successor = new Vector();
	    
	    // Incrementamos el n�mero de nodos expandidos.
	 	nodosExpandidos++;
	    for (numOperador=1; numOperador<=3; numOperador++) {
	    	if ( palillos-numOperador>=0 ) {
	    		if(multiploDos(nivel2)){
	    			nombreOperador = "El jugador A quita ";
	    		}
	    		else{
	    			nombreOperador = "El jugador B quita ";
	    		}
	    		nombreOperador = nombreOperador+ numOperador + " palillo";
	    		if (numOperador != 1){
	    			nombreOperador = nombreOperador + "s.";
	    		}
	    		else{
	    			nombreOperador = nombreOperador + ".";
	    		}
			     // Creamos el nuevo estado.
	             Palillos nuevoEstado = new Palillos(palillos-numOperador,nivel2);
	             
	             // Comprobamos si el nuevo estado es v�lido.
	             if (nuevoEstado.isValid()) {
	            	// A�adimos el estado como sucesor.
	            	 successor.addElement(new Successor(nuevoEstado, nombreOperador, 1 ));
	             }
	    	}
	    }
		return successor.elements();
	}
	
}
