package obligatorio.grafica.controladores;

import java.io.IOException;
import java.sql.SQLException;

import obligatorio.logica.Fachada;
import obligatorio.logica.exceptions.DueñoException;

public class ControladorBorrarDueñoMascota {
    
	public void borrarDueñoMascota(int cedula) throws SQLException, DueñoException, IOException {
		
		//FIXME: cambiar por IFachada
		Fachada.getInstance().borrarDueñoMascotas(cedula);

	}

}