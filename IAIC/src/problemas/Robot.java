/**
 * 
 */
package problemas;

import java.util.Enumeration;

/**
 * 
 *
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
	 * 
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
	
		nombreOperador = "";
	}
	
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
		nombreOperador = "";
	}

	@Override
	public float h() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isGoal() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Enumeration successors() {
		// TODO Auto-generated method stub
		return null;
	}

}
