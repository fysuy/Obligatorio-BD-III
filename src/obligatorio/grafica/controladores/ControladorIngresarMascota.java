package obligatorio.grafica.controladores;

import java.io.IOException;
import java.sql.SQLException;

import obligatorio.logica.Fachada;
import obligatorio.logica.exceptions.ExceptionsDueños;
import obligatorio.logica.valueObjects.VOMascota;

public class ControladorIngresarMascota {
    
    //FIXME: cambiar por IFachada
        private Fachada fachada;
        
        public void ingresarMascota(int cedulaDueño, String apodo, String raza) throws SQLException, ExceptionsDueños, IOException {
            
        	fachada = Fachada.getInstance();
        	VOMascota voMascota = new VOMascota(raza, apodo, cedulaDueño);
        	fachada.nuevaMascota(voMascota);
            
        }
    
}