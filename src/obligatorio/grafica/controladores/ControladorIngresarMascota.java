package obligatorio.grafica.controladores;

import java.io.IOException;

import obligatorio.exceptions.DueñoException;
import obligatorio.exceptions.LogicaException;
import obligatorio.exceptions.MascotaException;
import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.Fachada;
import obligatorio.logica.valueObjects.VOMascota;

public class ControladorIngresarMascota {

	// FIXME: cambiar por IFachada
	private Fachada fachada;

	public void ingresarMascota(int cedulaDueño, String apodo, String raza)
			throws LogicaException, PersistenciaException, DueñoException,
			MascotaException {
		fachada = Fachada.getInstance();
		VOMascota voMascota = new VOMascota(raza, apodo, cedulaDueño);
		fachada.nuevaMascota(voMascota);
	}
}