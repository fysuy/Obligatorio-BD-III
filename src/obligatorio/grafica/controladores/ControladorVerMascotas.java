package obligatorio.grafica.controladores;



import java.util.List;

import obligatorio.exceptions.DueñoException;
import obligatorio.exceptions.LogicaException;
import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.Fachada;
import obligatorio.logica.valueObjects.VOMascota;


public class ControladorVerMascotas {

	public Object[][] listarMascotas(int cedula) throws LogicaException, PersistenciaException, DueñoException {

		Object[][] data;

		List<VOMascota> listaMascotas = Fachada.getInstance().listarMascotas(cedula);
		VOMascota[] mascotas = listaMascotas.toArray(new VOMascota[listaMascotas.size()]);

		if (mascotas.length != 0) {

			data = new Object[mascotas.length][3];

			for (int i = 0; i < mascotas.length; i++) {
				data[i][0] = new String(mascotas[i].getApodo());
				data[i][1] = new String(mascotas[i].getRaza());
				data[i][2] = new Integer(mascotas[i].getCedulaDueño());
			}

		} else {

			data = new Object[1][3];
			data[0][0] = "";
			data[0][1] = "";
			data[0][2] = "";
		}

		return data;
	}

}
