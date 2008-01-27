/**
 * 
 */
package problemas;

import java.util.Enumeration;
import java.util.Vector;

import aima.search.Successor;

/**
 * Clase que implementa el problema del robot-aspirador seg�n el paradigma del 
 * espacio de estados.
 */
public class Robot extends Problema {
	
	/**
	 * Indica la posici�n del robot-aspiradora:
	 * 0. Est� en la habitaci�n de la izquierda.
	 * 1. Est� en la habitaci�n del centro.
	 * 2. Est� en la habitaci�n de la derecha.
	 */
	int posRobot;
	
	/**
	 * Indica si la alfomrbra de la habitaci�n de la izquierda est� limpia.
	 */
	boolean limpiaHabIzq;
	
	/**
	 * Indica si la alfomrbra de la habitaci�n del centro est� limpia.
	 */
	boolean limpiaHabCen;
	
	/**
	 * Indica si la alfomrbra de la habitaci�n de la derecha est� limpia.
	 */
	boolean limpiaHabDer;
	
	/**
	 * Constructor por defecto: inicializa las componentes a valores por defecto.
	 */
	public Robot(){
		enunciado = "Sea un micro-mundo formado por 3 habitaciones y un robot aspirador." +
			" Hay una habitaci�n a la izquierda, otra en el centro y otra a la derecha, " +
			"cuyas alfombras pueden	estar sucias o limpias. El robot puede estar en" +
			" cualquiera de las habitaciones y puede ejecutar dos tipos de operaciones: " +
			"aspirar o moverse. La operaci�n de aspirar requiere que la habitaci�n en la" +
			" que se encuentra el robot est� sucia y su resultado es que dicha habitaci�n" +
			" pasa a estar limpia. La operaci�n mover tiene dos opciones: mover hacia la " +
			"izquierda, que requiere que haya alguna habitaci�n a la izquierda de aquella" +
			" en la que se encuentra el robot, y mover hacia la derecha que requiere la" +
			" existencia de alguna habitaci�n situada a la derecha. En cualquier caso, los" +
			" movimientos ser�n elementales, es	decir, s�lo permitir�n el paso a la habitaci�n" +
			" contigua a la actual.";
		
		posRobot = 0;
		limpiaHabIzq = false;
		limpiaHabCen = false;
		limpiaHabDer = false;
		
		repEstado = "("+posRobot+","+limpiaHabIzq+","+limpiaHabCen+","+limpiaHabDer+")";
		nombreOperador = "";
	}
	
	/**
	 * Constructor principal, inicializa los atributos a los valores indicados.
	 * @param pRobot Posici�n del robot.
	 * @param limHabIzq Si est� limpia o no la habitaci�n de la izquierda.
	 * @param limHabCen Si est� limpia o no la habitaci�n del centro.
	 * @param limHabDer Si est� limpia o no la habitaci�n de la derecha.
	 */
	public Robot(int pRobot, boolean limHabIzq, boolean limHabCen, boolean limHabDer){
		enunciado = "Sea un micro-mundo formado por 3 habitaciones y un robot aspirador." +
				" Hay una habitaci�n a la izquierda, otra en el centro y otra a la derecha, " +
				"cuyas alfombras pueden	estar sucias o limpias. El robot puede estar en" +
				" cualquiera de las habitaciones y puede ejecutar dos tipos de operaciones: " +
				"aspirar o moverse. La operaci�n de aspirar requiere que la habitaci�n en la" +
				" que se encuentra el robot est� sucia y su resultado es que dicha habitaci�n" +
				" pasa a estar limpia. La operaci�n mover tiene dos opciones: mover hacia la " +
				"izquierda, que requiere que haya alguna habitaci�n a la izquierda de aquella" +
				" en la que se encuentra el robot, y mover hacia la derecha que requiere la" +
				" existencia de alguna habitaci�n situada a la derecha. En cualquier caso, los" +
				" movimientos ser�n elementales, es	decir, s�lo permitir�n el paso a la habitaci�n" +
				" contigua a la actual.";
		
		posRobot = pRobot;
		limpiaHabIzq = limHabIzq;
		limpiaHabCen = limHabCen;
		limpiaHabDer = limHabDer;
		repEstado = "("+posRobot+","+limpiaHabIzq+","+limpiaHabCen+","+limpiaHabDer+")";
		nombreOperador = "";
	}

	/**
	 * Genera el valor heur�stico del estado.
	 * @return Valor de la he�ristica. 
	 */
	public float h() {
		// Heur�stica: Mejor cuanto menor sea h, es decir, cuanto menos componentes
		// alfombras est�n sucias.
		int h = 0;
		if (!limpiaHabIzq){
			h++;
		}
		
		if (!limpiaHabCen){
			h++;
		}
		
		if (!limpiaHabDer){
			h++;
		}

		return (float)h;
	}

	/**
	 * Indica si estamos ante un estado soluci�n.
	 * @return Valor indicando si el estado es soluci�n o no.
	 */
	public boolean isGoal() {
		return (limpiaHabIzq && limpiaHabCen && limpiaHabDer);
	}

	/**
	 * Comprueba si el estado del problema es v�lido.
	 * @return Valor indicando la validez del estado.
	 */
	protected boolean isValid() {
			return true;
	}

	/**
	 * Genera todos los posibles estados sucesores del estado actual.
	 * @return Conjunto de estados sucesores.	 
	 */
	public Enumeration successors(){
	
		// OPERADORES:
		//  - Operador 0: Aspirar habitaci�n.
		//  - Operador 1: Mover robot a la habitaci�n de la izquierda.
		//  - Operador 2: Mover robot a la habitaci�n de la derecha.
		
	 	// Operador usado.
	 	int numOperador;
	 	
	 	// Nuevas posiciones.
		int pRobot = 0;
		boolean limHabIzq = false;
		boolean limHabCen = false;
		boolean limHabDer = false;
	 	Vector successor = new Vector();
	 	
	 	// Incrementamos el n�mero de nodos expandidos.
	 	nodosExpandidos++;
	 	
	 	for (numOperador = 0; numOperador <3; numOperador++){
	 		boolean operadorAplicado = false;
	 		
	 		// Operador 0: Aspirar habitaci�n.
	 		if (numOperador == 0){
	 			// Comprobamos como est� la alfombra de la habitaci�n
	 			// en la que est� el aspirador.
	 			boolean limpiaHab = false;
	 			switch (posRobot){
	 				case 0: 
	 					limpiaHab = limpiaHabIzq;
	 					break;
	 				case 1:
	 					limpiaHab = limpiaHabCen;
	 					break;
	 				case 2:
	 					limpiaHab = limpiaHabDer;
	 					break;
	 			}
	 			
	 			// Si no est� limpia, aplicamos operador.
	 			if (!limpiaHab){
	 				operadorAplicado = true;
	 				nombreOperador = "aspirar";
	 				
	 				// Limpiamos la habitaci�n.
		 			switch (posRobot){
		 				case 0: 
		 					limHabIzq = true;
		 					limHabCen = limpiaHabCen;
		 					limHabDer = limpiaHabDer;
		 					break;
		 				case 1:
		 					limHabCen = true;
		 					limHabIzq = limpiaHabIzq;
		 					limHabDer = limpiaHabDer;
		 					break;
		 				case 2:
		 					limHabDer = true;
		 					limHabCen = limpiaHabCen;
		 					limHabIzq = limpiaHabIzq;
		 					break;
		 			}
		 			pRobot = posRobot;
	 			}
	 	 	}
	 		
	 		// Operador 1: Mover robot a la habitaci�n de la izquierda.
	 		if (numOperador == 1){
	 			// Si el robot no est� en la habitaci�n de la izquierda.
	 			if (posRobot!=0){
	 				// Aplicamos operador.
	 				operadorAplicado = true;
	 				nombreOperador = "moverIzquierda";
	 				// Movemos al robot.
	 				pRobot = posRobot-1;
	 				// El resto se queda igual.
 					limHabCen = limpiaHabCen;
 					limHabDer = limpiaHabDer;
 					limHabIzq = limpiaHabIzq;
	 			}
	 		}
	 		
	 		// Operador 1: Mover robot a la habitaci�n de la derecha.
	 		if (numOperador == 2){
	 			// Si el robot no est� en la habitaci�n de la derecha.
	 			if (posRobot!=2){
	 				// Aplicamos operador.
	 				operadorAplicado = true;
	 				nombreOperador = "moverDerecha";
	 				// Movemos al robot.
	 				pRobot = posRobot + 1;
	 				// El resto se queda igual.
 					limHabCen = limpiaHabCen;
 					limHabDer = limpiaHabDer;
 					limHabIzq = limpiaHabIzq;
	 			}
	 		}
	 		
	 		if (operadorAplicado){
	 			// Creamos el nuevo estado.
		 		Robot nuevoEstado = new Robot(pRobot,limHabIzq,limHabCen,limHabDer);
	 	 		
	 	 		// Comprobamos si el nuevo estado es v�lido.
	 	 		if(nuevoEstado.isValid()){	 	 		
	 	 			// A�adimos el estado como sucesor.
	 	 			successor.addElement(new Successor(nuevoEstado,nombreOperador,1)); 
	 	 		}
	 		}
 	 	}
 	 
 	 	return successor.elements();
	}

}
