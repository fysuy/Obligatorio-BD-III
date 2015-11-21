package obligatorio.util.Archivos;
import java.io.Serializable;

import obligatorio.logica.Monitor;
import obligatorio.util.IConexion;
import obligatorio.util.IPoolConexiones;


public class PoolConexionesArchivos implements IPoolConexiones, Serializable{
	
	private static final long serialVersionUID = 1L;
	private Monitor monitor;
       
    public PoolConexionesArchivos() {
    	//Se inicializa monitor
        monitor = new Monitor();
    }
       
    public IConexion obtenerConexion(boolean modifica) {
    	
    	IConexion con=null;
    	if (modifica) {
    		// COMIENZA ESCRITURA
    		monitor.comienzoEscritura();
		} else {
    		// COMIENZA LECTURA
			monitor.comienzoLectura();
    	}
    	
    	return con;
    }
    
    public void liberarConexion(IConexion con, boolean ok) {
    	//TODO - Mail a la profe, usamos este get o una bandera como parametro.
    	if (monitor.getEscribiendo()){
    		monitor.terminoEscritura();
    	} else {
    	// Termina Lectura.
    		monitor.terminoLectura();
    	}
    }
}
