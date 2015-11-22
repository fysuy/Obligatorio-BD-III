package obligatorio.grafica.controladores;

import obligatorio.exceptions.DueñoException;
import obligatorio.exceptions.LogicaException;
import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.Fachada;

public class ControladorBorrarDueñoMascota {

	public void borrarDueñoMascota(int cedula) throws LogicaException, PersistenciaException, DueñoException {
<<<<<<< HEAD
=======

>>>>>>> c82317d7fe40e9da4c6337a235c7132dbf972a25
		// FIXME: cambiar por IFachada
		Fachada.getInstance().borrarDueñoMascotas(cedula);
	}
}