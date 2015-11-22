package obligatorio.grafica.controladores;

import obligatorio.exceptions.DueñoException;
import obligatorio.exceptions.LogicaException;
import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.Fachada;

public class ControladorBorrarDueñoMascota {

	public void borrarDueñoMascota(int cedula) throws LogicaException,
			PersistenciaException, DueñoException {
		// FIXME: cambiar por IFachada
		Fachada.getInstance().borrarDueñoMascotas(cedula);
	}
}