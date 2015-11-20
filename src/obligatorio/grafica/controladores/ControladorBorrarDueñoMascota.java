package obligatorio.grafica.controladores;

import java.io.IOException;
import java.sql.SQLException;

import obligatorio.logica.Fachada;
import obligatorio.logica.exceptions.ExceptionsDueños;

public class ControladorBorrarDueñoMascota {
    
	public void borrarDueñoMascota(int cedula) throws SQLException, ExceptionsDueños, IOException {
		
		//FIXME: cambiar por IFachada
		Fachada.getInstance().borrarDueñoMascotas(cedula);

	}

}