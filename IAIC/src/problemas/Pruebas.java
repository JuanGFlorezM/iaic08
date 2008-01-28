package problemas;

public class Pruebas {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Problema juego = new Granjero();
		for (int i=0; i<7; i++){
			System.out.println("Estrategia: "+ juego.estrategiaAplicada(i));
			juego.setNodosExpandidos(0);
			boolean resuelto = juego.resolver(i);			
			System.out.println("N�mero de nodos expandidos : "+ juego.getNodosExpandidos()+"\n");
		}		
	}

}
