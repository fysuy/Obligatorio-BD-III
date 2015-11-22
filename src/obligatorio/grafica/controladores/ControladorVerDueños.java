package obligatorio.grafica.controladores;

import java.rmi.RemoteException;
import java.util.List;

import obligatorio.exceptions.LogicaException;
import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.Fachada;
import obligatorio.logica.valueObjects.VODueño;

public class ControladorVerDueños {

	public Object[][] listarDueños() throws LogicaException,
			PersistenciaException, RemoteException {
		Object[][] data = null;
		
		List<VODueño> dueños = Fachada.getInstance().listarDueños();
		int total = dueños.size();
		
		if (total != 0) {
			data = new Object[total][3];
			for (int i = 0; i < total; i++) {
				data[i][0] = new Integer(dueños.get(i).getCedula());
				data[i][1] = new String(dueños.get(i).getNombre());
				data[i][2] = new String(dueños.get(i).getApellido());
			}
		} else {
			//TODO: no se puede devolver algo mejor? una exception o algo?
			data = new Object[1][3];
			data[0][0] = "";
			data[0][1] = "";
			data[0][2] = "";
		}

		return data;
	}
}
