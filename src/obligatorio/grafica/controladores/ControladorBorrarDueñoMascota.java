package obligatorio.grafica.controladores;

import obligatorio.exceptions.LogicaException;
import obligatorio.logica.Fachada;

public class ControladorBorrarDueñoMascota {

	public void borrarDueñoMascota(int cedula) throws LogicaException {

		// FIXME: cambiar por IFachada
		Fachada.getInstance().borrarDueñoMascotas(cedula);

	}

}