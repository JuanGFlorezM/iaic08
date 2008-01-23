package problemas;

import java.util.Enumeration;
import java.util.Vector;

import aima.search.AStarSearch;
import aima.search.BreadthFirstSearch;
import aima.search.DepthBoundedSearch;
import aima.search.GreedySearch;
import aima.search.IteratedDeepeningSearch;
import aima.search.Successor;
import aima.search.UniformCostSearch;

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
		enunciado = "Se tienen dos garrafas vac�as con capacidades de 3 y 4 litros " +
					"respectivamente pero sin ninguna marca de medida parcial. Las " +
					"garrafas pueden vaciarse o llenarse de agua, as� como verter " +
					"el contenido de una a otra. El objetivo consiste en tener " +
					"exactamente 2 litros de agua en la garrafa de 4 litros.";
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
		enunciado = "Se tienen dos garrafas vac�as con capacidades de 3 y 4 litros " +
		"respectivamente pero sin ninguna marca de medida parcial. Las " +
		"garrafas pueden vaciarse o llenarse de agua, as� como verter " +
		"el contenido de una a otra. El objetivo consiste en tener " +
		"exactamente 2 litros de agua en la garrafa de 4 litros.";
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
		//Heuristica: Mejor cuanto m�s pr�ximo a tener 2 litros en la jarra de 4 Litros.
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
		
		// Tenemos 6 operadores:
		// Operador 0: Llenar jarra de 4 Litros.
		// Operador 1: Llenar jarra de 3 Litros.
		// Operador 2: Vaciar jarra de 4 Litros.
		// Operador 3: Llenar jarra de 3 Litros.
		// Operador 4: Verter jarra de 4 en la de 3 Litros.
		// Operador 5: Verter jarra de 3 en la de 4 Litros.
		
	 	// Operador usado.
	 	int numOperador;
	 	String nombreOperador = "";
	 	
		Vector successorVec = new Vector();

	 	int nuevaJarra3 = 0;
	 	int nuevaJarra4 = 0;
	 	nodosExpandidos++;

	 	for(numOperador = 0; numOperador <=5; numOperador++){
	 		//Llenar garrafa de 4L
	 		if(numOperador == 0 && jarra4<4 ){
	 			nuevaJarra4 = 4;
	 			nuevaJarra3 = jarra3;
	 			nombreOperador ="LLenar jarra de 4 L";
	 		}
	 		//Llenar garrafa de 3L
	 		if(numOperador == 1 && jarra3<3 ){
	 			nuevaJarra3 = 3;
	 			nuevaJarra4 = jarra4;
	 			nombreOperador ="LLenar jarra de 3 L";
	 		}
	 		//Vaciar garrafa de 4L
	 		if(numOperador == 2 && jarra4>0 ){
	 			nuevaJarra4 = 0;
	 			nuevaJarra3 = jarra3;
	 			nombreOperador ="Vaciar jarra de 4 L";
	 		}
	 		//Vaciar garrafa de 3L
	 		if(numOperador == 3 && jarra3>0 ){
	 			nuevaJarra4 = jarra4;
	 			nuevaJarra3 = 0;
	 			nombreOperador ="Vaciar jarra de 3 L";
	 		}
	 		//Verter garrafa de 4L sobre garrafa de 3L
	 		if(numOperador == 4 && jarra4>0 && jarra3<3 ){
	 			if(jarra3+jarra4 <= 3){
	 				nuevaJarra3=jarra3+jarra4;
	 			}
	 			else{
	 				nuevaJarra3 = 3;
	 			}
	 			nuevaJarra4 = jarra4-(nuevaJarra3-jarra3);
	 			nombreOperador ="Verter jarra de 4 L sobre la de 3 L";
	 		}
	 		//Verter garrafa de 3L sobre garrafa de 4L
	 		if(numOperador == 5 && jarra3>0 && jarra4<4 ){
	 			if(jarra3+jarra4 <= 4){
	 				nuevaJarra4=jarra3+jarra4;
	 			}
	 			else{
	 				nuevaJarra4 = 4;
	 			}
	 			nuevaJarra3 = jarra3 -(nuevaJarra4-jarra4);
	 			nombreOperador ="Verter jarra de 3 L sobre la de 4 L";
	 		}
	 		
	 		Jarras nuevoEstado = new Jarras(nuevaJarra4,nuevaJarra3);
	 		if(nuevoEstado.isValid()){
	 			successorVec.addElement(new Successor(nuevoEstado, nombreOperador, 1 ));
	 		}
	 	}
	 	return successorVec.elements();
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

	protected boolean resolverA() {
		boolean resuelto = listPath((new AStarSearch(this)).search());
		return resuelto;
	}

	protected boolean resolverCosteUni() {
		boolean resuelto = listPath((new UniformCostSearch(this)).search());
		return resuelto;
	}

	protected boolean resolverEscalada() {
		boolean resuelto = listPath((new GreedySearch(this)).search());
		return resuelto;
	}

	protected boolean resolverPrimAnchura() {
		boolean resuelto = listPath( ( new BreadthFirstSearch(this)).search());
		return resuelto;
	}

	protected boolean resolverProfIt() {
		boolean resuelto=listPath((new IteratedDeepeningSearch(this)).search());
		return resuelto;
	}

	protected boolean resolverProfundidad() {
		boolean resuelto=listPath((new DepthBoundedSearch(this,7)).search());
		return resuelto;
	}
}
