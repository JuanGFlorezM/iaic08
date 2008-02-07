/**
 * 
 */
package Cubo;

import java.util.ArrayList;

/**
 *
 */
public class Habitacion {
    
	/**
	 * Vector de puertas de la habitaci�n
	 */
    private Puerta puertas[]; 
    
    /**
     * �ltima puerta que hemos intentado abrir en la habitaci�n
     */
    private int ultimaPuerta;
    
    /**
     * Crea una instancia de habitacion por defecto
     */
    public Habitacion() {
        puertas = new Puerta[6];  
        ultimaPuerta = 0;
    }
    
    /**
     * M�todo que intenta abrir una puerta
     * @param direccion Indica la puerta concreta que queremos abrir
     * @param estrategia Indica la estrategia que vamos a seguir para resolver el problema de la puerta
     * @return Si se ha conseguido abrir la puerta que querr�amos
     */
    public boolean abrePuerta(int direccion, int estrategia){
    	ultimaPuerta++;
    	if ((direccion>=0) && (direccion<6)){        
    		return puertas[direccion].abre(estrategia);                              
    	}
    	return false;
    }
    
    /**
     * 
     * metodo accesor de la �ltima direcci�n por la que se ha intentado continuar en el problema
     * seg�un el siguiente diagrama
     *           2    
                |
                |
           _____+____________ 
          /|               /| 
         /_|______________/ |
        |  |             | 5|
  3====>|  |             |  |<====1
        |  |_____________|__|
        | /              | /
        |/_4_____________|/
                ^
                |
                |
                0
     * @return devuelve la direcci�n de la �ltima puerta que se ha intentado abrir
     */
    public int dameUltimaPuertaProbada(){
        return ultimaPuerta;
    }
    
 
    /**
     * Metodo accesor a la puerta correspondiente a una direcci�n
     * @param dir direcci�n de la puerta que queremos que se devuelva
     * @return la puerta buscada
     */
    public Puerta damePuerta(int dir){
        if ((dir>=0) && (dir<6)){      
            return puertas[dir];    
        }
        return null;
    }
    
    /**
     * Metodo accesor al t�tulo del problema de la puerta asociada a la direcci�n pasada
     * @param direccion de la puerta de la que se quiere saber el t�tulo del problema
     * @return t�tulo  del problema de la puerta
     * */
    
    public String dameTitulo(int dir){
    	return puertas[dir].dameTitulo();
    	
    }
    /**
     * M�todo accesor al a la soluc�n al problema encontrada
     * @param direcci�n del a puera de la que se quiere saber la soluci�n de su problema asociado
     * @return soluci�n al problema encontrado
     * */
    public ArrayList<String> dameSolucion(int dir){
    	
    	return puertas[dir].dameSolucion();
    }
    
    
    /**
     * Metodo accesor a la descripci�n del problema de la puerta asociada a la direcci�n pasada
     * @param direcci�n de la puerta de la que se quiere saber la descripci�n del problema
     * @return breve descripci�n del problema de la puerta
     * */
    public String dameDescripcion(int dir){
    	return puertas[dir].dameDescripcion();
    }
    
    /**
     * Coloca la puerta deseada en la direcci�n pasada por parametro
     * @param p Puerta que se va a poner
     * @param pos direcci�n de la puerta que se va a poner
     * @return si la direcci�n dada era v�lida para poner la puerta
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
