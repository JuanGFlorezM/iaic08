/**
 * Contiene el conjunto de los problemas implementados seg�n el paradigma
 * del espacio de estados.
 */
package problemas;

import java.util.Enumeration;
import java.util.Vector;
import aima.search.*;

/**
 * Clase que implementa el problema de la habitaci�n, el mono y el pl�tano seg�n
 * el paradigma del espacio de estados.
 */
public class Mono extends Problema{

	/**
	 * Posici�n del mono:
	 * 0. Al lado de la puerta (izquierda).
	 * 1. Al lado de la ventana (derecha).
	 * 2. En el centro de la habitaci�n.
	 */
	private int posMono;
	
	/**
	 * Posici�n de la caja:
	 * 0. Al lado de la puerta (izquierda).
	 * 1. Al lado de la ventana (derecha).
	 * 2. En el centro de la habitaci�n.
	 */
	private int posCaja;
	
	/**
	 * Indica si el mono est� subido o no en la caja.
	 */
	private boolean subidoCaja;
	
	/**
	 * Indica si el mono tiene o no el platano.
	 */
	private boolean tienePlatano;
	
	/**
	 * Constructor por defecto: inicializa las componentes a valores por defecto.
	 */
	public Mono(){
		enunciado = " Hay un mono en la puerta de una habitaci�n. En el centro de\n"
				+ " la habitaci�n hay un pl�tano colgando del techo. El mono est�\n"
				+ " hambriento y quiere conseguir el pl�tano pero no alcanza\n"
				+ " porque est� muy alto. En la habitaci�n tambi�n hay una ventana\n"
				+ "  y debajo de ella hay una caja que le permitir�a alcanzar el\n "
				+ " pl�tano si se subiera a ella. El mono puede realizar las \n"
				+ "  siguientes acciones: andar por el suelo, subirse a la caja,\n "
				+ " empujar la caja (si el mono est� en la misma posici�n que la \n"
				+ " caja) y coger el pl�tano (si est� subido encima de la caja y la\n"
				+ " caja est� justo debajo del pl�tano). Consigue que el mono coja\n"
				+ " el platano.";
		
		posMono = 0;
		posCaja = 1;
		subidoCaja = false;
		tienePlatano = false;
	 	nombreOperador = "";
	 	repEstado = "("+posMono+","+posCaja+","+subidoCaja+","+tienePlatano+")";
	}
	
	/**
	 * Constructor principal, inicializa los atributos a los valores indicados.
	 * @param pMono Posici�n del mono.
	 * @param pCaja Posici�n de la caja.
	 * @param sCaja Si se est� subido o no en la caja.
	 * @param tPlatano Si se tiene o no el platano.
	 */
	public Mono(int pMono, int pCaja, boolean sCaja, boolean tPlatano){
		enunciado = " Hay un mono en la puerta de una habitaci�n. En el centro de\n"
			+ " la habitaci�n hay un pl�tano colgando del techo. El mono est�\n"
			+ " hambriento y quiere conseguir el pl�tano pero no alcanza\n"
			+ " porque est� muy alto. En la habitaci�n tambi�n hay una ventana\n"
			+ "  y debajo de ella hay una caja que le permitir�a alcanzar el\n "
			+ " pl�tano si se subiera a ella. El mono puede realizar las \n"
			+ "  siguientes acciones: andar por el suelo, subirse a la caja,\n "
			+ " empujar la caja (si el mono est� en la misma posici�n que la \n"
			+ " caja) y coger el pl�tano (si est� subido encima de la caja y la\n"
			+ " caja est� justo debajo del pl�tano). Consigue que el mono coja\n"
			+ " el platano.";
		
		posMono = pMono;
		posCaja = pCaja;
		subidoCaja = sCaja;
		tienePlatano = tPlatano;
	 	nombreOperador = "";
	 	repEstado = "("+posMono+","+posCaja+","+subidoCaja+","+tienePlatano+")";
	}
	
	/**
	 * Genera el valor heur�stico del estado.
	 * @return Valor de la he�ristica. 
	 */
	public float h() {
		int h = 0;
		
		// Si no est� el mono en el centro de la habitaci�n.
		if (posMono != 2){
			h++;
		}
		
		// Si el mono no est� subido a la caja.
		if (!subidoCaja){
			h++;
		}
		
		// Si la caja est� en el centro de la habitaci�n.
		if (posCaja != 2){
			h++;
		}
		
		// Si el mono no tiene el platano. 
		if (!tienePlatano){
			h++;
		}
		return (float)h;
	}

	/**
	 * Indica si estamos ante un estado soluci�n.
	 * @return Valor indicando si el estado es soluci�n o no.
	 */
	public boolean isGoal() {
		return tienePlatano;
	}

	/**
	 * Comprueba si el estado del problema es v�lido.
	 * @return Valor indicando la validez del estado.
	 */
	protected boolean isValid() {
		if (nodosExpandidos > maxNodos){
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
		//  - Operador 0: Mono anda por el suelo hasta la ventana.
		//  - Operador 1: Mono anda por el suelo hasta el centro.
		//  - Operador 2: Mono anda por el suelo hasta la puerta.
		//  - Operador 3: Mono empuja la caja hacia la ventana.
		//  - Operador 4: Mono empuja la caja hacia el centro. 
		//  - Operador 5: Mono empuja la caja hacia la puerta. 
		//  - Operador 6: Mono se sube a la caja. 
		//  - Operador 7: Mono coje el platano. 
		
	 	// Operador usado.
	 	int numOperador;
	 	
	 	// Nuevas posiciones.
	 	int nposMono = 0;
	 	int nposCaja = 1;
	 	boolean nsubidoCaja = false;
	 	boolean ntienePlatano = false;
	 	Vector<Successor> successorVec = new Vector<Successor>();
	 	
	 	nodosExpandidos++;

	 	for (numOperador=0; numOperador<8; numOperador++){
	 		boolean operadorAplicado = false;
	 		
			// Operador 0: Mono anda por el suelo hasta la ventana. 
	 		if (numOperador == 0){
	 			// Si el mono no est� en la ventana y no est� subido a la caja.
	 			if ((posMono != 1) && (!subidoCaja)){
	 				nombreOperador = "irVentana";
	 				operadorAplicado = true;
	 				// Cambiamos la posici�n del mono.
	 				nposMono = 1;
		 			nposCaja = posCaja;
		 			nsubidoCaja = subidoCaja;
		 			ntienePlatano = tienePlatano;
	 			}	
	 		}
	 		
			// Operador 1: Mono anda por el suelo hasta el centro. 
	 		if (numOperador == 1){
	 			// Si el mono no est� en el centro y no est� subido a la caja.
	 			if ((posMono != 2) && (!subidoCaja)){
	 				nombreOperador = "irCentro";
	 				operadorAplicado = true;
	 				// Cambiamos la posici�n del mono.
	 				nposMono = 2;
		 			nposCaja = posCaja;
		 			nsubidoCaja = subidoCaja;
		 			ntienePlatano = tienePlatano;
	 			}	
	 		}
	 		
			// Operador 2: Mono anda por el suelo hasta la puerta. 
	 		if (numOperador == 2){
	 			// Si el mono no est� en la puerta y no est� subido a la caja.
	 			// subido a ella.
	 			if ((posMono != 0) && (!subidoCaja)){
	 				nombreOperador = "irPuerta";
	 				operadorAplicado = true;
	 				// Cambiamos la posici�n del mono.
	 				nposMono = 0;
		 			nposCaja = posCaja;
		 			nsubidoCaja = subidoCaja;
		 			ntienePlatano = tienePlatano;
	 			}	
	 		}
	 		
			// Operador 3: Mono empuja la caja hacia la ventana. 
	 		if (numOperador == 3){
	 			// Si el mono no est� en la ventana, tiene la caja y no est�
	 			// subido a ella.
	 			if ((posMono != 1) && (posMono == posCaja) && (!subidoCaja)){
	 				nombreOperador = "empujaCajaVentana";
	 				operadorAplicado = true;
	 				// Cambiamos la posici�n del mono.
	 				nposMono = 1;
	 				// Cambiamos la posici�n de la caja.
		 			nposCaja = 1;
		 			nsubidoCaja = subidoCaja;
		 			ntienePlatano = tienePlatano;
	 			}	
	 		}
	 		
			// Operador 4: Mono empuja la caja hacia el centro. 
	 		if (numOperador == 4){
	 			// Si el mono no est� en el centro, tiene la caja y no est�
	 			// subido a ella.
	 			if ((posMono != 2) && (posMono == posCaja) && (!subidoCaja)){
	 				nombreOperador = "empujaCajaCentro";
	 				operadorAplicado = true;
	 				// Cambiamos la posici�n del mono.
	 				nposMono = 2;
	 				// Cambiamos la posici�n de la caja.
		 			nposCaja = 2;
		 			nsubidoCaja = subidoCaja;
		 			ntienePlatano = tienePlatano;
	 			}	
	 		}
	 		
			// Operador 5: Mono empuja la caja hacia la puerta. 
	 		if (numOperador == 5){
	 			// Si el mono no est� en la puerta, tiene la caja y no est�
	 			// subido a ella.
	 			if ((posMono != 0) && (posMono == posCaja) && (!subidoCaja)){
	 				nombreOperador = "empujaCajaPuerta";
	 				operadorAplicado = true;
	 				// Cambiamos la posici�n del mono.
	 				nposMono = 0;
	 				// Cambiamos la posici�n de la caja.
		 			nposCaja = 0;
		 			nsubidoCaja = subidoCaja;
		 			ntienePlatano = tienePlatano;
	 			}	
	 		}
	 		
			// Operador 6: Mono se sube a la caja. 
	 		if (numOperador == 6){
	 			// Si el mono tiene la caja y no est� subido a ella.
	 			if ((posMono == posCaja) && (!subidoCaja)){
	 				nombreOperador = "subirseCaja";
	 				operadorAplicado = true;
	 				nposMono = posMono;
		 			nposCaja = posCaja;
		 			// Indicamos que se sube a la caja.
		 			nsubidoCaja = true;
		 			ntienePlatano = tienePlatano;
	 			}	
	 		}

			// Operador 7: Mono coje el platano. 
	 		if (numOperador == 7){
	 			// Si el mono est� subido a la caja, est� en el centro y
	 			// no tiene el platano.
	 			if ((posMono == 2) && (subidoCaja)&&(!tienePlatano)){
	 				nombreOperador = "cojerPlatano";
	 				operadorAplicado = true;
	 				nposMono = posMono;
		 			nposCaja = posCaja;
		 			nsubidoCaja = subidoCaja;
		 			// Indicamos que coje el platano.
		 			ntienePlatano = true;
	 			}	
	 		}
	 		
	 		if (operadorAplicado){
		 	    // Creamos el nuevo estado.
		 		Mono nuevoEstado = new Mono(nposMono,nposCaja,nsubidoCaja,ntienePlatano);
		 	 	
		 		// Comprobamos si el nuevo estado es v�lido.
		 	 	if(nuevoEstado.isValid()){	 	 		
		 	 		// A�adimos el estado como sucesor.
		 	 		successorVec.addElement(new Successor(nuevoEstado,nombreOperador,1)); 
		 	 	}
	 		}
	 	}

	 	return successorVec.elements();
	}
	public String dameTitulo(){
		return "Mono";
		
	}
}
