/**
 * 
 */
package problemas;

import java.util.Enumeration;
import java.util.Vector;
import aima.search.*;

/**
 *
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
	 * 
	 */
	public Mono(){
		enunciado = "Hay un mono en la puerta de una habitaci�n. En el centro de la"
				+ " habitaci�n hay un pl�tano colgando del techo. El mono est�"
				+ " hambriento y quiere conseguir el pl�tano pero no alcanza porque"
				+ " est� muy alto. En la habitaci�n tambi�n hay una ventana y"
				+ " debajo de ella hay una caja que le permitir�a alcanzar el "
				+ "pl�tano si se subiera a ella. El mono puede realizar las "
				+ "siguientes acciones: andar por el suelo, subirse a la caja, "
				+ "empujar la caja (si el mono est� en la misma posici�n que la "
				+ "caja) y coger el pl�tano (si est� subido encima de la caja y la"
				+ " caja est� justo debajo del pl�tano).";
		
		posMono = 0;
		posCaja = 1;
		subidoCaja = false;
		tienePlatano = false;
	 	nombreOperador = "";
	 	repEstado = "("+posMono+","+posCaja+","+subidoCaja+","+tienePlatano+")";
	}
	
	/**
	 * 
	 */
	public Mono(int pMono, int pCaja, boolean sCaja, boolean tPlatano){
		enunciado = "Hay un mono en la puerta de una habitaci�n. En el centro de la"
				+ " habitaci�n hay un pl�tano colgando del techo. El mono est�"
				+ " hambriento y quiere conseguir el pl�tano pero no alcanza porque"
				+ " est� muy alto. En la habitaci�n tambi�n hay una ventana y"
				+ " debajo de ella hay una caja que le permitir�a alcanzar el "
				+ "pl�tano si se subiera a ella. El mono puede realizar las "
				+ "siguientes acciones: andar por el suelo, subirse a la caja, "
				+ "empujar la caja (si el mono est� en la misma posici�n que la "
				+ "caja) y coger el pl�tano (si est� subido encima de la caja y la"
				+ " caja est� justo debajo del pl�tano).";
		
		posMono = pMono;
		posCaja = pCaja;
		subidoCaja = sCaja;
		tienePlatano = tPlatano;
	 	nombreOperador = "";
	 	repEstado = "("+posMono+","+posCaja+","+subidoCaja+","+tienePlatano+")";
	}
	
	/**
	 * 
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
	 * 
	 */
	public boolean isGoal() {
		return tienePlatano;
	}

	/**
	 * 
	 */
	protected boolean isValid() {
		return true;
	}

	/**
	 * 
	 */
	public Enumeration successors() {

		// Tenemos 8 operadores:
		// Operador 0: Mono anda por el suelo hasta la ventana.
		// Operador 1: Mono anda por el suelo hasta el centro.
		// Operador 2: Mono anda por el suelo hasta la puerta.
		// Operador 3: Mono empuja la caja hacia la ventana.
		// Operador 4: Mono empuja la caja hacia el centro. 
		// Operador 5: Mono empuja la caja hacia la puerta. 
		// Operador 6: Mono se sube a la caja. 
		// Operador 7: Mono coje el platano. 
		
	 	// Operador usado.
	 	int numOperador;
	 	
	 	// Nuevas posiciones.
	 	int nposMono = 0;
	 	int nposCaja = 1;
	 	boolean nsubidoCaja = false;
	 	boolean ntienePlatano = false;
	 	Vector successorVec = new Vector();

	 	for (numOperador=0; numOperador<8; numOperador++){
	 		
			// Operador 0: Mono anda por el suelo hasta la ventana. 
	 		if (numOperador == 0){
	 			// Si el mono no est� en la ventana, no tiene la caja y no est�
	 			// subido a ella.
	 			if ((posMono != 1) && (posMono != posCaja) && (!subidoCaja)){
	 				nombreOperador = "Mono anda a ventana.";
	 				// Cambiamos la posici�n del mono.
	 				nposMono = 1;
		 			nposCaja = posCaja;
		 			nsubidoCaja = subidoCaja;
		 			ntienePlatano = tienePlatano;
	 			}	
	 		}
	 		
			// Operador 1: Mono anda por el suelo hasta el centro. 
	 		if (numOperador == 1){
	 			// Si el mono no est� en el centro, no tiene la caja y no est�
	 			// subido a ella.
	 			if ((posMono != 2) && (posMono != posCaja) && (!subidoCaja)){
	 				nombreOperador = "Mono anda al centro.";
	 				// Cambiamos la posici�n del mono.
	 				nposMono = 2;
		 			nposCaja = posCaja;
		 			nsubidoCaja = subidoCaja;
		 			ntienePlatano = tienePlatano;
	 			}	
	 		}
	 		
			// Operador 2: Mono anda por el suelo hasta la puerta. 
	 		if (numOperador == 2){
	 			// Si el mono no est� en la puerta, no tiene la caja y no est�
	 			// subido a ella.
	 			if ((posMono != 0) && (posMono != posCaja) && (!subidoCaja)){
	 				nombreOperador = "Mono anda hacia puerta.";
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
	 				nombreOperador = "Mono empuja caja hacia ventana.";
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
	 				nombreOperador = "Mono empuja caja hacia centro.";
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
	 				nombreOperador = "Mono empuja caja hacia puerta.";
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
	 				nombreOperador = "Mono se sube a la caja.";
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
	 				nombreOperador = "Mono coje el platano.";
	 				nposMono = posMono;
		 			nposCaja = posCaja;
		 			nsubidoCaja = subidoCaja;
		 			// Indicamos que coje el platano.
		 			ntienePlatano = true;
	 			}	
	 		}
	 		
	 	    // Creamos el nuevo estado.
	 		Mono nuevoEstado = new Mono(nposMono,nposCaja,nsubidoCaja,ntienePlatano);
	 	 	
	 		// Comprobamos si el nuevo estado es v�lido.
	 	 	if(nuevoEstado.isValid()){	 	 		
	 	 		// A�adimos el estado como sucesor.
	 	 		successorVec.addElement(new Successor(nuevoEstado,nombreOperador,1)); 
	 	 	}
	 	}

	 	return successorVec.elements();
	}

}
