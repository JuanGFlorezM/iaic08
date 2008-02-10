/**
 * Contiene el conjunto de clases que implementan el interface del micromundo 
 * del edificio c�bico.
 */
package salidaPantalla;

/**
 * Clase principal que lanza el interface de la aplicaci�n.
 */
public class Main {
    
    /**
     * Constructor principal.
     */
    public Main() {     
    }
    
    /**
     * M�todo principal de la clase, encargado de lanzar el interface. 
     * @param args Argumentos de entrada.
     */
    public static void main(String[] args) {
        
        VPrincipal f = new VPrincipal();
        f.setVisible(true);
    }
    
}
