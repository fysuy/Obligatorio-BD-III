package obligatorio.grafica.controladores;

import obligatorio.exceptions.DueñoException;
import obligatorio.exceptions.LogicaException;
import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.Fachada;
import obligatorio.logica.valueObjects.VODueño;

public class ControladorIngresarDueño {
	// FIXME: cambiar por IFachada
	private Fachada fachada;

	public void ingresarDueño(int cedula, String nombre, String apellido)
			throws DueñoException, PersistenciaException, LogicaException {
		fachada = Fachada.getInstance();
		VODueño voDueño = new VODueño(cedula, nombre, apellido);
		fachada.nuevoDueño(voDueño);
	}
}