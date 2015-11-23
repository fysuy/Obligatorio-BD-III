package obligatorio.grafica.controladores;

import java.rmi.RemoteException;
import java.util.List;

import obligatorio.exceptions.LogicaException;
import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.IFachada;
import obligatorio.logica.valueObjects.VODue�o;
import obligatorio.util.ObjetoCliente;

public class ControladorVerDue�os {

	private IFachada facade;

	public Object[][] listarDue�os() throws LogicaException,
			PersistenciaException, RemoteException {
		facade = ObjetoCliente.Inicializar();
		Object[][] data = null;

		List<VODue�o> due�os = facade.listarDue�os();
		int total = due�os.size();

		if (total != 0) {
			data = new Object[total][3];
			for (int i = 0; i < total; i++) {
				data[i][0] = new Integer(due�os.get(i).getCedula());
				data[i][1] = new String(due�os.get(i).getNombre());
				data[i][2] = new String(due�os.get(i).getApellido());
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
