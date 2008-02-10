/**
 * Contiene el conjunto de clases que implementan el micromundo del edificio c�bico.
 */
package Cubo;

import java.util.ArrayList;

/**
 * Clase que implementa la representaci�n de una habitaci�n en el micromundo.
 */
public class Habitacion {
    
	/**
	 * Vector de puertas de la habitaci�n.
	 */
    private Puerta puertas[]; 
    
    /**
     * �ltima puerta que hemos intentado abrir en la habitaci�n.
     */
    private int ultimaPuerta;
    
    /**
     * Constructor principal de Habitaci�n.
     */
    public Habitacion() {
        puertas = new Puerta[6];  
        ultimaPuerta = 0;
    }
    
    /**
     * M�todo que intenta abrir una puerta.
     * @param direccion Indica la puerta concreta que queremos abrir.
     * @param estrategia Indica la estrategia que vamos a seguir para resolver el problema de la puerta.
     * @return Si se ha conseguido abrir la puerta que querr�amos.
     */
    public boolean abrePuerta(int direccion, int estrategia){
    	ultimaPuerta++;
    	if ((direccion>=0) && (direccion<6)){        
    		return puertas[direccion].abre(estrategia);                              
    	}
    	return false;
    }
    
    /**
     * Metodo accesor de la �ltima direcci�n por la que se ha intentado continuar en el problema.
     * @return Devuelve la direcci�n de la �ltima puerta que se ha intentado abrir.
     */
    public int dameUltimaPuertaProbada(){
        return ultimaPuerta;
    }
 
    /**
     * Metodo accesor a la puerta correspondiente a una direcci�n.
     * @param dir Direcci�n de la puerta que queremos que se devuelva.
     * @return Puerta buscada.
     */
    public Puerta damePuerta(int dir){
        if ((dir>=0) && (dir<6)){      
            return puertas[dir];    
        }
        return null;
    }
    
    /**
     * Metodo accesor al t�tulo del problema de la puerta asociada a la direcci�n pasada.
     * @param dir Direccion de la puerta de la que se quiere saber el t�tulo del problema.
     * @return T�tulo  del problema de la puerta.
     * */
    public String dameTitulo(int dir){
    	return puertas[dir].dameTitulo();
    	
    }
    
    /**
     * M�todo accesor a la soluc�n al problema encontrada.
     * @param dir Direcci�n de la puerta de la que se quiere saber la soluci�n de su problema asociado.
     * @return Soluci�n al problema encontrado.
     * */
    public ArrayList<String> dameSolucion(int dir){
    	
    	return puertas[dir].dameSolucion();
    }
    
    
    /**
     * Metodo accesor a la descripci�n del problema de la puerta asociada a la direcci�n pasada.
     * @param dir Direcci�n de la puerta de la que se quiere saber la descripci�n del problema.
     * @return Breve descripci�n del problema de la puerta.
     * */
    public String dameDescripcion(int dir){
    	return puertas[dir].dameDescripcion();
    }
    
    /**
     * Coloca la puerta deseada en la direcci�n pasada por parametro.
     * @param p Puerta que se va a poner.
     * @param pos Direcci�n de la puerta que se va a poner.
     * @return Si la direcci�n dada era v�lida para poner la puerta.
     */
    public boolean setPuerta(Puerta p,int pos){
        
        if ((pos<0) || (pos >5)){
            return false;
        }
        else{
            puertas[pos]=p;
            return true;
        }   
    }
}
