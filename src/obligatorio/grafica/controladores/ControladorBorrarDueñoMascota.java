package obligatorio.grafica.controladores;

import obligatorio.exceptions.Due�oException;
import obligatorio.exceptions.LogicaException;
import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.Fachada;

public class ControladorBorrarDue�oMascota {

	public void borrarDue�oMascota(int cedula) throws LogicaException, PersistenciaException, Due�oException {
<<<<<<< HEAD
=======

>>>>>>> c82317d7fe40e9da4c6337a235c7132dbf972a25
		// FIXME: cambiar por IFachada
		Fachada.getInstance().borrarDue�oMascotas(cedula);
	}
}