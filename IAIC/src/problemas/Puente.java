/**
 * Contiene el conjunto de los problemas implementados seg�n el paradigma
 * del espacio de estados.
 */
package problemas;

import java.util.Enumeration;

/**
 * Clase que implementa el problema de los amigos
 * que quieren cruzar el puente con una linterna
 * seg�n el paradigma del espacio de estados.
 */
public class Puente extends Problema {
	
	/**
	 * Posici�n de la linterna (Lado del puente: 1-izquierda, 0-derecha).
	 */
	private int posLinterna;
	
	/**
	 * Posici�n de Alicia (Lado del puente: 1-izquierda, 0-derecha).
	 */
	private int posAlicia;
	
	/**
	 * Posici�n de Benito (Lado del puente: 1-izquierda, 0-derecha).
	 */
	private int posBenito;
	
	/**
	 * Posici�n de Carlos (Lado del puente: 1-izquierda, 0-derecha).
	 */
	private int posCarlos;
	
	/**
	 * Posici�n de David (Lado del puente: 1-izquierda, 0-derecha).
	 */
	private int posDavid;	

	public Puente(){
		enunciado = "Cuatro amigos deben cruzar un fr�gil puente de madera.\n"+
					"Es de noche y es indispensable usar una linterna para\n"+
					"cruzarlo. El puente s�lo puede aguantar el peso de dos\n"+
					"personas como m�ximo y solo tienen una linterna.\n"+
					"Alicia tarda 8 minutos en cruzarlo, Benito 4 minutos,\n"+
					"Carlos tarda 2 y David 1.";
		posLinterna = 1;		
		posAlicia = 1;		
		posBenito = 1;	
		posCarlos = 1;		
		posDavid = 1;
		repEstado = "("+posLinterna+","+posAlicia+","+posBenito+","+posCarlos+","+posDavid+")";
		nombreOperador = "";
	}
	
	/**
	 * Genera el valor heur�stico del estado.
	 * @return Valor de la he�ristica. 
	 */
	public float h() {
		// Heur�stica: Mejor cuanto menor sea h, es decir, cuanto menos componentes
		// est�n en el lado izquierdo.
		int h = posLinterna + posAlicia + posBenito + posCarlos + posDavid;
		return (float)h;
	}

	/**
	 * Indica si estamos ante un estado soluci�n.
	 * @return Valor indicando si el estado es soluci�n o no.
	 */
	public boolean isGoal() {
		// Soluci�n si todos los componentes est�n a la derecha.
		return ((posLinterna == 0)&&(posAlicia == 0)&&(posBenito == 0)&&(posCarlos == 0)&&(posDavid == 0));
	}

	/**
	 * Comprueba si el estado del problema es v�lido.
	 * @return Valor indicando la validez del estado.
	 */
	protected boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Genera todos los posibles estados sucesores del estado actual.
	 * @return Conjunto de estados sucesores.	 
	 */
	public Enumeration successors() {
		// TODO Auto-generated method stub
		return null;
	}

}
