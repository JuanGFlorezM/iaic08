/**
 * Contiene el conjunto de los problemas implementados seg�n el paradigma
 * del espacio de estados.
 */
package problemas;

import java.util.Enumeration;
import java.util.Vector;

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
	 * Posici�n de Ana (Lado del puente: 1-izquierda, 0-derecha).
	 */
	private int posAna;
	
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
	
	/**
	 * Lo que se tarda cruzar el puente.
	 */
	private float coste;
	
	/**
	 * Tiempo que queda disponible para cruzar el puente.
	 */
	private float tiempo;
	
	/**
	 * Constructor por defecto: inicializa las componentes a valores por defecto.
	 */
	public Puente(){
		enunciado = "Cuatro amigos deben cruzar un fr�gil puente de madera.\n"+
					"Es de noche y es indispensable usar una linterna para\n"+
					"cruzarlo. El puente s�lo puede aguantar el peso de dos\n"+
					"personas como m�ximo y solo tienen una linterna.\n"+
					"Ana tarda 8 minutos en cruzarlo, Benito 4 minutos,\n"+
					"Carlos tarda 2 y David 1.";
		posLinterna = 1;		
		posAna = 1;		
		posBenito = 1;	
		posCarlos = 1;		
		posDavid = 1;
		repEstado = "("+posLinterna+","+posAna+","+posBenito+","+posCarlos+","+posDavid+")";
		nombreOperador = "";
		coste = 0;
		tiempo = 15;
	}
	
	/**
	 * Constructor principal, inicializa los atributos a los valores indicados.
	 * @param l Posici�n de la linterna.
	 * @param a Posici�n de Ana.
	 * @param b Posici�n de Benito.
	 * @param c Posici�n de Carlos.
	 * @param d Posici�n de David.
	 * @param costeEmpleado cuanto cuesta llegar a este estado.
	 * @param tiempoDisponible cuanto tiempo queda.
	 */
	
	public Puente(int l,int a,int b,int c,int d,int costeEmpleado,int tiempoDisponible){
		enunciado = "Cuatro amigos deben cruzar un fr�gil puente de madera.\n"+
					"Es de noche y es indispensable usar una linterna para\n"+
					"cruzarlo. El puente s�lo puede aguantar el peso de dos\n"+
					"personas como m�ximo y solo tienen una linterna.\n"+
					"Ana tarda 8 minutos en cruzarlo, Benito 4 minutos,\n"+
					"Carlos tarda 2 y David 1.";
		posLinterna = l;		
		posAna = a;		
		posBenito = b;	
		posCarlos = c;		
		posDavid = d;
		repEstado = "("+posLinterna+","+posAna+","+posBenito+","+posCarlos+","+posDavid+")";
		nombreOperador = "";
		coste = costeEmpleado;
		tiempo = tiempoDisponible;
	}
	
	/**
	 * Genera el valor heur�stico del estado.
	 * @return Valor de la he�ristica. 
	 */
	public float h() {
		// Heur�stica: Mejor cuanto menor sea h, es decir, cuanto menos componentes
		// est�n en el lado izquierdo.
		int h = posLinterna + posAna + posBenito + posCarlos + posDavid;
		return (float)h;
	}

	/**
	 * Indica si estamos ante un estado soluci�n.
	 * @return Valor indicando si el estado es soluci�n o no.
	 */
	public boolean isGoal() {
		// Soluci�n si todos los componentes est�n a la derecha.
		return ((posLinterna == 0)&&(posAna == 0)&&(posBenito == 0)&&(posCarlos == 0)&&(posDavid == 0));
	}

	/**
	 * Comprueba si el estado del problema es v�lido.
	 * @return Valor indicando la validez del estado.
	 */
	protected boolean isValid() {
		//Es v�lido si lo que nos cuesta cruzar el puente no supera el tiempo que nos queda.
		return (coste<=tiempo);
	}

	/**
	 * Genera todos los posibles estados sucesores del estado actual.
	 * @return Conjunto de estados sucesores.	 
	 */
	public Enumeration successors() {
		// Tenemos 10 operadores:
		// Operador 0: Cruza Ana sola (con la linterna).
		// Operador 1: Cruza Benito solo (con la linterna).
		// Operador 2: Cruza Carlos solo (con la linterna).
		// Operador 3: Cruza David solo (con la linterna).
		// Operador 4: Cruza Ana con Benito (con la linterna).
		// Operador 5: Cruza Ana con Carlos (con la linterna).
		// Operador 6: Cruza Ana con David (con la linterna).
		// Operador 7: Cruza Benito con Carlos (con la linterna).
		// Operador 8: Cruza Benito con David (con la linterna).
		// Operador 9: Cruza Carlos con David (con la linterna).
		
	 	// Operador usado.
	 	int numOperador;
	 	
	 	// Nuevas posiciones.
	 	int nposLinterna = 1;
	 	int nposAna = 1;
	 	int nposBenito = 1;
	 	int nposCarlos  = 1;
	 	int nposDavid = 1;
	 	
	 	Vector successor = new Vector();
	 	
		return null;
	}

}
