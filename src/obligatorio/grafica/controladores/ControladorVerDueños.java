package obligatorio.grafica.controladores;

import obligatorio.exceptions.LogicaException;
import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.Fachada;
import obligatorio.logica.valueObjects.VODueño;

public class ControladorVerDueños {

	public Object[][] listarDueños() throws LogicaException,
			PersistenciaException {

		Object[][] data = null;

		VODueño[] dueños = (VODueño[]) Fachada.getInstance().listarDueños()
				.toArray();

		if (dueños.length != 0) {

			data = new Object[dueños.length][3];

			for (int i = 0; i < dueños.length; i++) {
				data[i][0] = new Integer(dueños[i].getCedula());
				data[i][1] = new String(dueños[i].getNombre());
				data[i][2] = new String(dueños[i].getApellido());
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
