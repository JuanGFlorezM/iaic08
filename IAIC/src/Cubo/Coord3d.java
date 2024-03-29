/**
 * Contiene el conjunto de clases que implementan el micromundo del edificio c�bico.
 */
package Cubo;

/** 
 * Clase que implementa coordenadas en 3 dimensiones (x,y,z).
 */
public class Coord3d {
	
	/**
	 * Desplazamiento con respecto al eje X.
	 */
	private int x;
	
	/**
	 * Desplazamiento con respecto al eje Y.
	 */
    private int y;
    
    /**
     * Desplazamiento con respecto al eje Z.
     */
    private int z;
    
    /**
     * Constructor principal que crea una coordenada a partir de 3 desplazamientos
     * con respecto a los ejes.
     * @param a Desplazamiento con respecto al eje X.
     * @param b Desplazamiento con respecto al eje X.
     * @param c Desplazamiento con respecto al eje X.
     */
    public Coord3d(int a,int b,int c){
        x = a;
        y = b;
        z = c;
    }
    
    /**
     * M�todo que devuelve la coordenada contigua a la izquierda de la instancia.
     * @return Coordenada contigua a la instacia, pero con el desplazamiento X disminuido.
     */
    public Coord3d izquierda(){
        Coord3d c = new Coord3d (x-1,y,z);
        return c;
    }
    
    /**
     * M�todo que devuelve la coordenada contigua a la derecha de la instancia.
     * @return Coordenada contigua a la instacia, pero con el desplazamiento X aumentado.
     */
    public Coord3d derecha(){
        Coord3d c = new Coord3d (x+1,y,z);
        return c;
    }
     
    /**
     * M�todo que devuelve la coordenada contigua de arriba de la instancia.
     * @return Coordenada contigua a la instacia, pero con el desplazamiento Y aumentado.
     */
    public Coord3d arriba(){
        Coord3d c = new Coord3d (x,y+1,z);
        return c;
    }   
    
    /**
     * M�todo que devuelve la coordenada contigua de debajo de la instancia.
     * @return Coordenada contigua a la instacia, pero con el desplazamiento Y disminuido.
     */
    
    public Coord3d abajo(){
        Coord3d c = new Coord3d (x,y-1,z);
        return c;
    }
    
    /**
     * M�todo que devuelve la coordenada contigua de delante de la instancia.
     * @return Coordenada contigua a la instacia, pero con el desplazamiento Z disminuido.
     */
    public Coord3d delante(){
        Coord3d c = new Coord3d (x,y,z-1);
        return c;
    }    

    /**
     * M�todo que devuelve la coordenada contigua de detr�s de la instancia.
     * @return Coordenada contigua a la instacia, pero con el desplazamiento Z aumentado.
     */
    public Coord3d detras(){
        Coord3d c = new Coord3d (x,y,z+1);
        return c;
    }    
    
    /**
     * Compara la coordenada con la que se le pasa como par�metro.
     * @param c Coordenada a comparar.
     * @return Si coinciden sus desplazamientos.
     */
    public boolean compara(Coord3d c){
        return ((x==c.x)&&(y==c.y)&&(z==c.z));    
    }
    
    /**
     * M�todo accesor al desplazamiento con respecto al eje X.
     * @return Desplazamiento con respecto al eje X.
     */
    public int getx(){
        return x;
    }
    
    /**
     * M�todo accesor al desplazamiento con respecto al eje Y.
     * @return Desplazamiento con respecto al eje Y.
     */
    public int gety(){
        return y;
    }
        
    /**
     * M�todo accesor al desplazamiento con respecto al eje Z.
     * @return Desplazamiento con respecto al eje Z.
     */
    public int getz(){
        return z;
    }
}
