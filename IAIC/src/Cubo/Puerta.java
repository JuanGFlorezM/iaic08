/**
 * 
 */
package Cubo;

import java.util.Random;
import problemas.*;

/**
 *
 */
public class Puerta {
    	
	/**
	 * 
	 */
	private Problema prob;
	
	/**
	 * 0. Cerrada. 
	 * 1. Abierta. 
	 * 2. Bloqueada.
	 */
    private int estado;
    private int estrategia;
    
    /**
     * 
     */
    public Puerta() {
        estado = 0;
    }
    
    /**
     * 
     * @param e
     * @param p
     */
    public Puerta(int e, Problema p){
    	estado = e;
    	prob = p;    	
    }
    
    /**
     * 
     * @param e
     */
    public Puerta(int e){
        estado = e;
        if (e==0){
        	
        	// Incluimos un problema aleatoriamente.
            Random rnd = new Random();
            int opc = rnd.nextInt(Problema.numProblemas);
            
            switch (opc){      
            	case 0:
            		prob = new Canibales();
            		break;
            		
            	case 1:
            		prob = new Jarras();
            		break;
            		
            	case 2:
            		prob = new Granjero();
            		break;  
            	case 3:prob = new Mono();break;
            	case 4:prob = new Palillos();break;
            	case 5:prob = new Puente();break;
            	case 6:prob = new Puzzle8();break;
            	case 7:prob = new RioYFamilia();break;
            	case 8:prob = new Robot();break;
            	case 9:prob = new RojoAzul();break;
            	default:prob = new Granjero();
            }
        }
        
        if(e==2){
        	prob=new Jarras();        	
        }
        if(e==1){
        	prob=new Jarras();        	
        }
    }
    
    /**
     * 
     * @return
     */
    public int dameEstado(){
        return estado;
    }
    
    /**
     * 
     * @param e
     */
    public void setEstado(int e){
        estado = e;    
    }
    
    /**
     * 
     * @param est
     * @return
     */
    
    public String dameDescripcion(){
    	
    	return prob.getEnunciado();
    }
    
    public String dameTitulo(){
    	return prob.dameTitulo();    	
    }
    
    public boolean abre(){
    	if (estado==2){
	    	// Si est� bloqueada no se puede abrir.
	        return false;
	    }
	    
	    if (estado==1){
	    	// Sin est� abierta.
	        return true;
	    }
	       
	    if (estado==0){
	    	// Si est� cerrada, trata de resolver.
	    	if (prob.resolver(estrategia)){	
	    		// Abre la puerta.
	    		estado = 1;
	    		return true;
	    	}
	    	else{
	    		// Bloquea la puerta.
	    		estado = 2;
	    		return false;
	    	}
	    }    	    
	    return true;
	
    	
    }
    
    public boolean abre(int est){
    
	    if (estado==2){
	    	// Si est� bloqueada no se puede abrir.
	        return false;
	    }
	    
	    if (estado==1){
	    	// Sin est� abierta.
	        return true;
	    }
	       
	    if (estado==0){
	    	// Si est� cerrada, trata de resolver.
	    	if (prob.resolver(est)){	
	    		// Abre la puerta.
	    		estado = 1;
	    		return true;
	    	}
	    	else{
	    		// Bloquea la puerta.
	    		estado = 2;
	    		return false;
	    	}
	    }    	    
	    return true;
	}
}
