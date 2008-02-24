/**
 * Contiene el conjunto de los problemas implementados seg�n el paradigma
 * del espacio de estados.
 */
package problemas;

import java.util.Enumeration;
import java.util.Vector;

import aima.search.DepthBoundedSearch;
import aima.search.Successor;

/**
 * Clase que implementa el problema de la familia,
 * el ladron y el policia cruzando un rio seg�n
 * el paradigma del espacio de estados.
 */
public class RioYFamilia extends Problema {

	/**
	 * Posici�n de la barca (Margen del r�o: 1-izquierda, 0-derecha).
	 */
	private int posBarca;
	
	/**
	 * Posici�n del policia (Margen del r�o: 1-izquierda, 0-derecha).
	 */
	private int posPolicia;
	
	/**
	 * Posici�n del ladron (Margen del r�o: 1-izquierda, 0-derecha).
	 */
	private int posLadron;
	
	/**
	 * Posici�n de la madre (Margen del r�o: 1-izquierda, 0-derecha).
	 */
	private int posMadre;
	
	/**
	 * Posici�n del padre (Margen del r�o: 1-izquierda, 0-derecha).
	 */
	private int posPadre;
	
	/**
	 * Posici�n del hijo1 (Margen del r�o: 1-izquierda, 0-derecha).
	 */
	private int posHijo1;
	
	/**
	 * Posici�n del hijo2 (Margen del r�o: 1-izquierda, 0-derecha).
	 */
	private int posHijo2;
	
	/**
	 * Posici�n de la hija1 (Margen del r�o: 1-izquierda, 0-derecha).
	 */
	private int posHija1;
	
	/**
	 * Posici�n de la hija2 (Margen del r�o: 1-izquierda, 0-derecha).
	 */
	private int posHija2;
	
	/**
	 * Constructor por defecto: inicializa las componentes a valores por defecto.
	 */
	public RioYFamilia(){
		enunciado = " En un rio se encuentra una familia compuesta por un padre, una\n" +
		    " madre, dos hijas, dos hijos, un policia y un ladr�n. Hay una barca\n" +
		    " en la que solo caben 2 personas.\n"+
		 	" El padre, la madre y el policia son los �nicos que saben manejar\n" +
		 	" la barcar. La madre no puede estar con los hijos sin el padre.\n"+
		 	" El padre no puede estar con las hijas sin la madre.\n "+
		 	"El ladr�n no puede estar con nadie de la familia sin el policia.\n";
		posBarca=1;
		posPolicia=1;
		posLadron=1;
		posMadre=1;
		posPadre=1;
		posHijo1=1;
		posHijo2=1;
		posHija1=1;
		posHija2=1;
		repEstado = "("+posBarca+","+posPolicia+","+posLadron+","+posMadre+","+posPadre+
					   ","+posHijo1+","+posHijo2+","+posHija1+","+posHija2+")";
		nombreOperador = "";		 	
				 	
	}
	
	/**
	 * Constructor principal, inicializa los atributos a los valores indicados.
	 * @param bar posci�n de la barca.
	 * @param pol posici�n del policia.
	 * @param lad posici�n del ladr�n.
	 * @param mad posici�n de la madre.
	 * @param pad posici�n del padre.
	 * @param ho1 posici�n del hijo1.
	 * @param ho2 posici�n del hijo2.
	 * @param ha1 posici�n de la hija1.
	 * @param ha2 posici�n de la hija2.
	 */	
	public RioYFamilia(int bar,int pol,int lad, int mad,int pad,int ho1,int ho2,int ha1,int ha2){
		enunciado = " En un rio se encuentra una familia compuesta por un padre, una\n" +
				    " madre, dos hijas, dos hijos, un policia y un ladr�n. Hay una barca\n" +
				    " en la que solo caben 2 personas.\n"+
				 	" El padre, la madre y el policia son los �nicos que saben manejar\n" +
				 	" la barcar. La madre no puede estar con los hijos sin el padre.\n"+
				 	" El padre no puede estar con las hijas sin la madre.\n "+
				 	"El ladr�n no puede estar con nadie de la familia sin el policia.\n";
		posBarca=bar;
		posPolicia=pol;
		posLadron=lad;
		posMadre=mad;
		posPadre=pad;
		posHijo1=ho1;
		posHijo2=ho2;
		posHija1=ha1;
		posHija2=ha2;
		repEstado = "("+posBarca+","+posPolicia+","+posLadron+","+posMadre+","+posPadre+
				   ","+posHijo1+","+posHijo2+","+posHija1+","+posHija2+")";
		nombreOperador = "";
	}
	
	/**
	 * Genera el valor heur�stico del estado.
	 * @return Valor de la he�ristica. 
	 */
	public float h() {
		// Heur�stica: Mejor cuanto menor sea h, es decir, cuanto menos componentes
		// est�n en el lado izquierdo.
		int h = posBarca+posPolicia+posLadron+posMadre+posPadre+
		   		+posHijo1+posHijo2+posHija1+posHija2;
		return (float)h;
	}

	/**
	 * Indica si estamos ante un estado soluci�n.
	 * @return Valor indicando si el estado es soluci�n o no.
	 */
	public boolean isGoal() {
		// Soluci�n si todos los componentes est�n a la derecha.
		return (h()== 0);
	}

	/**
	 * Comprueba si el estado del problema es v�lido.
	 * @return Valor indicando la validez del estado.
	 */
	protected boolean isValid() {
		if (nodosExpandidos > maxNodos){
			return false;
		}
		
		//Si el ladr�n no est� con el policia y est� con alguien
		//de la familia.
		if((posPolicia != posLadron)&&(!estaSoloLadron())){
			return false;
		}
		
		//Si el padre est� sin la madre y con alguna de las hijas.
		if((posPadre != posMadre)&&((posPadre == posHija1)||(posPadre == posHija2))){
			return false;
		}
		
		//Si la madre est� sin el padre y con alguno de los hijos.
		if((posMadre != posPadre)&&((posMadre == posHijo1)||(posMadre == posHijo2))){
			return false;
		}
		return true;
	}

	/**
	 * M�todo privado creado para dar claridad a las restricciones.
	 * @return true si el ladr�n no est� con nadie de la familia.
	 */
	private boolean estaSoloLadron() {
		return ((posLadron != posMadre)&&(posLadron != posPadre)&&(posLadron != posHijo1)
				&&(posLadron != posHijo2)&&(posLadron != posHija1)&&(posLadron != posHija2));
	}

	/**
	 * Genera todos los posibles estados sucesores del estado actual.
	 * @return Conjunto de estados sucesores.	 
	 */
	public Enumeration<Successor> successors() {
		// Tenemos 15 operadores
		// Operador 0 : Cruza el polic�a solo (con la barca).
		// Operador 1 : Cruza la madre sola (con la barca).
		// Operador 2 : Cruza el padre solo (con la barca).
		// Operador 3 : Cruza el polic�a con la madre (con la barca).
		// Operador 4 : Cruza el polic�a con el padre (con la barca).
		// Operador 5 : Cruza el polic�a con el ladr�n (con la barca).
		// Operador 6 : Cruza el polic�a con hijo1 (con la barca).
		// Operador 7 : Cruza el polic�a con hijo2 (con la barca).
		// Operador 8 : Cruza el polic�a con hija1 (con la barca).
		// Operador 9 : Cruza el polic�a con hija2 (con la barca).
		// Operador 10 : Cruza la madre y el padre (con la barca).
		// Operador 11 : Cruza la madre y la hija1 (con la barca).
		// Operador 12 : Cruza la madre y la hija2 (con la barca).
		// Operador 13 : Cruza el padre y el hijo1 (con la barca).
		// Operador 14 : Cruza el padre y el hijo2 (con la barca).
		
		// Operador usado.
	 	int numOperador;
	 	
	 	// Nuevas posiciones.
	 	int nposBarca = 1;
	 	int nposPolicia = 1;
	 	int nposLadron = 1;
	 	int nposMadre  = 1;
	 	int nposPadre = 1;
	 	int nposHijo1 = 1;
	 	int nposHijo2 = 1;
	 	int nposHija1  = 1;
	 	int nposHija2 = 1;
	 	
	 	// Incrementamos el n�mero de nodos expandidos.
	 	nodosExpandidos++;
	 	
	 	Vector<Successor> successor = new Vector<Successor>();
	 	
	 	for (numOperador = 0; numOperador<15;numOperador++){
	 		boolean operadorAplicado = false;
	 		// Operador 0 : Cruza el polic�a solo (con la barca).
	 		if(numOperador == 0){
	 			//Para poder cruzar el polic�a tiene que estar con la barca.
	 			if(posBarca == posPolicia){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaPolic�a";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza el polic�a.
	 				nposPolicia = 1 - posPolicia;
	 				//Los dem�s se quedan donde est�n.
	 				nposLadron = posLadron;
	 			 	nposMadre  = posMadre;
	 			 	nposPadre = posPadre;
	 			 	nposHijo1 = posHijo1;
	 			 	nposHijo2 = posHijo2;
	 			 	nposHija1 = posHija1;
	 			 	nposHija2 = posHija2;
	 			}
	 		}
	 		// Operador 1 : Cruza la madre sola (con la barca).
	 		if(numOperador == 1){
	 			//Para poder cruzar la madre tiene que estar con la barca.
	 			if(posBarca == posMadre){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaMadre";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza la madre.
	 				nposMadre = 1 - posMadre;
	 				//Los dem�s se quedan donde est�n.
	 				nposLadron = posLadron;
	 			 	nposPolicia  = posPolicia;
	 			 	nposPadre = posPadre;
	 			 	nposHijo1 = posHijo1;
	 			 	nposHijo2 = posHijo2;
	 			 	nposHija1 = posHija1;
	 			 	nposHija2 = posHija2;
	 			}
	 		}
	 		// Operador 2 : Cruza el padre solo (con la barca).
	 		if(numOperador == 2){
	 			//Para poder cruzar el padre tiene que estar con la barca.
	 			if(posBarca == posPadre){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaPadre";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza el padre.
	 				nposPadre = 1 - posPadre;
	 				//Los dem�s se quedan donde est�n.
	 				nposLadron = posLadron;
	 			 	nposMadre  = posMadre;
	 			 	nposPolicia = posPolicia;
	 			 	nposHijo1 = posHijo1;
	 			 	nposHijo2 = posHijo2;
	 			 	nposHija1 = posHija1;
	 			 	nposHija2 = posHija2;
	 			}
	 		}
	 		// Operador 3 : Cruza el polic�a con la madre (con la barca).
	 		if(numOperador == 3){
	 			//Para poder cruzar el polic�a y la madre tienen que estar con la barca.
	 			if((posBarca == posPolicia)&&(posBarca == posMadre)){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaPolic�aMadre";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza el polic�a.
	 				nposPolicia = 1 - posPolicia;
	 				//Cruza la madre.
	 			 	nposMadre  = 1 - posMadre;
	 				//Los dem�s se quedan donde est�n.
	 				nposLadron = posLadron;
	 			 	nposPadre = posPadre;
	 			 	nposHijo1 = posHijo1;
	 			 	nposHijo2 = posHijo2;
	 			 	nposHija1 = posHija1;
	 			 	nposHija2 = posHija2;
	 			}
	 		}
	 		// Operador 4 : Cruza el polic�a con el padre (con la barca).
	 		if(numOperador == 4){
	 			//Para poder cruzar el polic�a y el padre tienen que estar con la barca.
	 			if((posBarca == posPolicia)&&(posBarca == posPadre)){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaPolic�aPadre";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza el polic�a.
	 				nposPolicia = 1 - posPolicia;
	 				//Cruza el padre.
	 			 	nposPadre  = 1 - posPadre;
	 				//Los dem�s se quedan donde est�n.
	 				nposLadron = posLadron;
	 			 	nposMadre = posMadre;
	 			 	nposHijo1 = posHijo1;
	 			 	nposHijo2 = posHijo2;
	 			 	nposHija1 = posHija1;
	 			 	nposHija2 = posHija2;
	 			}
	 		}
	 		// Operador 5 : Cruza el polic�a con el ladr�n (con la barca).
	 		if(numOperador == 5){
	 			//Para poder cruzar el polic�a y el ladr�n tienen que estar con la barca.
	 			if((posBarca == posPolicia)&&(posBarca == posLadron)){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaPolic�aLadron";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza el polic�a.
	 				nposPolicia = 1 - posPolicia;
	 				//Cruza el ladr�n.
	 			 	nposLadron  = 1 - posLadron;
	 				//Los dem�s se quedan donde est�n.
	 				nposPadre = posPadre;
	 			 	nposMadre = posMadre;
	 			 	nposHijo1 = posHijo1;
	 			 	nposHijo2 = posHijo2;
	 			 	nposHija1 = posHija1;
	 			 	nposHija2 = posHija2;
	 			}
	 		}
	 		// Operador 6 : Cruza el polic�a con el hijo1 (con la barca).
	 		if(numOperador == 6){
	 			//Para poder cruzar el polic�a y el hijo1 tienen que estar con la barca.
	 			if((posBarca == posPolicia)&&(posBarca == posHijo1)){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaPolic�aHijo1";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza el polic�a.
	 				nposPolicia = 1 - posPolicia;
	 				//Cruza el hijo1.
	 			 	nposHijo1  = 1 - posHijo1;
	 				//Los dem�s se quedan donde est�n.
	 				nposPadre = posPadre;
	 			 	nposMadre = posMadre;
	 			 	nposLadron = posLadron;
	 			 	nposHijo2 = posHijo2;
	 			 	nposHija1 = posHija1;
	 			 	nposHija2 = posHija2;
	 			}
	 		}
	 		// Operador 7 : Cruza el polic�a con el hijo2 (con la barca).
	 		if(numOperador == 7){
	 			//Para poder cruzar el polic�a y el hijo2 tienen que estar con la barca.
	 			if((posBarca == posPolicia)&&(posBarca == posHijo2)){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaPolic�aHijo2";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza el polic�a.
	 				nposPolicia = 1 - posPolicia;
	 				//Cruza el hijo2.
	 			 	nposHijo2  = 1 - posHijo2;
	 				//Los dem�s se quedan donde est�n.
	 				nposPadre = posPadre;
	 			 	nposMadre = posMadre;
	 			 	nposLadron = posLadron;
	 			 	nposHijo1 = posHijo1;
	 			 	nposHija1 = posHija1;
	 			 	nposHija2 = posHija2;
	 			}
	 		}
	 		// Operador 8 : Cruza el polic�a con la hija1 (con la barca).
	 		if(numOperador == 8){
	 			//Para poder cruzar el polic�a y la hija1 tienen que estar con la barca.
	 			if((posBarca == posPolicia)&&(posBarca == posHija1)){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaPolic�aHija1";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza el polic�a.
	 				nposPolicia = 1 - posPolicia;
	 				//Cruza la hija1.
	 			 	nposHija1  = 1 - posHija1;
	 				//Los dem�s se quedan donde est�n.
	 				nposPadre = posPadre;
	 			 	nposMadre = posMadre;
	 			 	nposLadron = posLadron;
	 			 	nposHijo2 = posHijo2;
	 			 	nposHijo1 = posHijo1;
	 			 	nposHija2 = posHija2;
	 			}
	 		}
	 		// Operador 9 : Cruza el polic�a con la hija2 (con la barca).
	 		if(numOperador == 9){
	 			//Para poder cruzar el polic�a y la hija2 tienen que estar con la barca.
	 			if((posBarca == posPolicia)&&(posBarca == posHija2)){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaPolic�aHija2";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza el polic�a.
	 				nposPolicia = 1 - posPolicia;
	 				//Cruza la hija2.
	 			 	nposHija2  = 1 - posHija2;
	 				//Los dem�s se quedan donde est�n.
	 				nposPadre = posPadre;
	 			 	nposMadre = posMadre;
	 			 	nposLadron = posLadron;
	 			 	nposHijo2 = posHijo2;
	 			 	nposHijo1 = posHijo1;
	 			 	nposHija1 = posHija1;
	 			}
	 		}
	 		// Operador 10 : Cruza la madre y el padre (con la barca).
	 		if(numOperador == 10){
	 			//Para poder cruzar la madre y el padre tienen que estar con la barca.
	 			if((posBarca == posMadre)&&(posBarca == posPadre)){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaMadrePadre";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza la madre.
	 				nposMadre = 1 - posMadre;
	 				//Cruza el padre.
	 			 	nposPadre  = 1 - posPadre;
	 				//Los dem�s se quedan donde est�n.
	 				nposPolicia = posPolicia;
	 			 	nposHija2 = posHija2;
	 			 	nposLadron = posLadron;
	 			 	nposHijo2 = posHijo2;
	 			 	nposHijo1 = posHijo1;
	 			 	nposHija1 = posHija1;
	 			}
	 		}
			// Operador 11 : Cruza la madre y la hija1 (con la barca).
	 		if(numOperador == 11){
	 			//Para poder cruzar la madre y la hija1 tienen que estar con la barca.
	 			if((posBarca == posMadre)&&(posBarca == posHija1)){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaMadreHija1";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza la madre.
	 				nposMadre = 1 - posMadre;
	 				//Cruza la hija1.
	 			 	nposHija1  = 1 - posHija1;
	 				//Los dem�s se quedan donde est�n.
	 				nposPolicia = posPolicia;
	 			 	nposHija2 = posHija2;
	 			 	nposLadron = posLadron;
	 			 	nposHijo2 = posHijo2;
	 			 	nposHijo1 = posHijo1;
	 			 	nposPadre = posPadre;
	 			}
	 		}
			// Operador 12 : Cruza la madre y la hija2 (con la barca).
	 		if(numOperador == 12){
	 			//Para poder cruzar la madre y la hija2 tienen que estar con la barca.
	 			if((posBarca == posMadre)&&(posBarca == posHija2)){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaMadreHija2";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza la madre.
	 				nposMadre = 1 - posMadre;
	 				//Cruza la hija2.
	 			 	nposHija2  = 1 - posHija2;
	 				//Los dem�s se quedan donde est�n.
	 				nposPolicia = posPolicia;
	 			 	nposHija1 = posHija1;
	 			 	nposLadron = posLadron;
	 			 	nposHijo2 = posHijo2;
	 			 	nposHijo1 = posHijo1;
	 			 	nposPadre = posPadre;
	 			}
	 		}
			// Operador 13 : Cruza el padre y el hijo1 (con la barca).
	 		if(numOperador == 13){
	 			//Para poder cruzar el padre y el hijo1 tienen que estar con la barca.
	 			if((posBarca == posPadre)&&(posBarca == posHijo1)){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaPadreHijo1";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza la padre.
	 				nposPadre = 1 - posPadre;
	 				//Cruza la hijo1.
	 			 	nposHijo1  = 1 - posHijo1;
	 				//Los dem�s se quedan donde est�n.
	 				nposPolicia = posPolicia;
	 			 	nposHija1 = posHija1;
	 			 	nposLadron = posLadron;
	 			 	nposHijo2 = posHijo2;
	 			 	nposHija2 = posHija2;
	 			 	nposMadre = posMadre;
	 			}
	 		}
			// Operador 14 : Cruza el padre y el hijo2 (con la barca).
	 		if(numOperador == 14){
	 			//Para poder cruzar el padre y el hijo2 tienen que estar con la barca.
	 			if((posBarca == posPadre)&&(posBarca == posHijo2)){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaPadreHijo2";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza la padre.
	 				nposPadre = 1 - posPadre;
	 				//Cruza la hijo2.
	 			 	nposHijo2  = 1 - posHijo2;
	 				//Los dem�s se quedan donde est�n.
	 				nposPolicia = posPolicia;
	 			 	nposHija1 = posHija1;
	 			 	nposLadron = posLadron;
	 			 	nposHijo1 = posHijo1;
	 			 	nposHija2 = posHija2;
	 			 	nposMadre = posMadre;
	 			}
	 		}
	 		if (operadorAplicado){
		 		// Creamos el nuevo estado.
		 	 	RioYFamilia nuevoEstado = new RioYFamilia(nposBarca,nposPolicia,nposLadron,nposMadre,nposPadre,
		 	 											  nposHijo1,nposHijo2,nposHija1,nposHija2);
		 	 		
		 	 	// Comprobamos si el nuevo estado es v�lido.
		 	 	if(nuevoEstado.isValid()){	 	 		
		 	 		// A�adimos el estado como sucesor.
		 	 		successor.addElement(new Successor(nuevoEstado,nombreOperador,1)); 
		 	 	}
	 		}
	 	}
		return successor.elements();
	}
	
	/**
 	 * M�todo que intenta resolver un problema seg�n la estrategia de b�squeda
 	 * en profundidad iterativa pero limitandola a un nivel m�ximo
 	 * debido a que en este problema usar la b�squeda implementada
 	 * en el aima tarda demasiado tiempo en terminar o no termina.
 	 * @return Si se ha resuelto o no el problema.
 	 */
	protected boolean resolverProfIt() {
		int i = 1;
		boolean resuelto= false;
		while((i<=maxNivel)&&(!resuelto)){
			resuelto=listPath((new DepthBoundedSearch(this,i)).search());
			i++;
		}
		if(i>maxNivel){
			return false;
		}
		return resuelto;
	}
	
	/**
	 * Devuele el t�tulo del problema.
	 * @return El nombre del problema.
	 */
	public String dameTitulo(){
		return "Rio y Familia";
		
	}
	
}
