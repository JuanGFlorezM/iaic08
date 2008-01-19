package problemas;

import java.util.Enumeration;

/** 
 * Clase que implementa el problema de las jarras.
 */

public class Jarras extends Problema {
	
	/**
	 * Constante que indica el m�ximo n�mero de
	 * nodos que se pueden expandir.
	 */	
	private static final int maxNodos = 1000;
	
	/**
	 * Contador de nodos expandidos.
	 */
	private static int nodosExpandidos = 0;
	
	/**
	 * Representa la jarra de 4 litros.
	 */	
	private int jarra4;
	
	/**
	 * Representa la jarra de 3 litros.
	 */
	private int jarra3;
	
	/**
	 * Constructor de la clase Jarras que crea
	 * el estado inicial (0,0) que representa
	 * a las dos jarras vac�as.
	 */
	public Jarras(){
		jarra4 = 0;
		jarra3 = 0;
	}
	
	/**
	 * Constructor de la clase Jarras que crea
	 * el estado inicial (i,j)
	 * @param i litros en la jarra de 4 litros
	 * @param j litros en la jarra de 3 litros.
	 */	
	public Jarras(int i,int j){
		if((i<0)||(i>4)){
			String mensaje = new String();
			mensaje = "No se puede crear el estado "+ String.valueOf(i)+" litros\n";
			mensaje = mensaje + "En la jarra de 4 litros. Se aplica el estado 0 litros.";
			System.out.println(mensaje);
			jarra4 = 0;
		}
		else{
			jarra4 = i;
		}
		if((j<0)||(j>3)){
			String mensaje = new String();
			mensaje = "No se puede crear el estado "+ String.valueOf(i)+" litros\n";
			mensaje = mensaje + "En la jarra de 3 litros. Se aplica el estado 0 litros.";
			System.out.println(mensaje);
			jarra3 = 0;
		}
		else{
			jarra3 = j;
		}
	}

	/**
	 * M�todo que calcula el valor de la heur�stica.
	 * @return el valor de la heur�stica.
	 */
	public float h(){
		float heuristica = 0;
		heuristica = (2 - jarra4);
		if(heuristica>0){
			return heuristica;
		}
		else{
			return (jarra4 - 2);
		}
	}

	/**
	 * M�todo que dice si un estado es soluci�n.
	 * @return true si es soluci�n false en otro caso.
	 */
	public boolean isGoal(){
		//�Tenemos 2 litros en la jarra de 4 litros?
		return jarra4 == 2;
	}
	
	/**
	 * M�todo que dice si un estado es v�lido.
	 * @return true si es v�lido false en otro caso.
	 */	
	protected boolean isValid(){
		return ((jarra4>=0)&&(jarra4<=4)&&(jarra3>=0)&&(jarra3<=3)
				&& (nodosExpandidos <= maxNodos)) ;
	}

	/**
	 * M�todo que calcula los estados alcanzables
	 * desde el estado actual.
	 * @return Conjunto de estados nuevos alcanzables.
	 */
	public Enumeration successors(){
		// TODO Auto-generated method stub		
		return null;
	}

	/**
	 * @return los nodos expandidos.
	 */
	public static int getNodosExpandidos() {
		return nodosExpandidos;
	}

	/**
	 * @param n que es el n�mero de nodos expandidos.
	 */
	public static void setNodosExpandidos(int n) {
		nodosExpandidos = n;
	}

}
