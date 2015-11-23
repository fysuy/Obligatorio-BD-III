package obligatorio.grafica.controladores;

import java.rmi.RemoteException;
import java.util.List;

import obligatorio.exceptions.DueñoException;
import obligatorio.exceptions.LogicaException;
import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.IFachada;
import obligatorio.logica.valueObjects.VOMascota;
import obligatorio.util.ObjetoCliente;

public class ControladorVerMascotas {
	private IFachada facade;

	public Object[][] listarMascotas(int cedula) throws LogicaException,
			RemoteException, PersistenciaException, DueñoException {
		facade = ObjetoCliente.Inicializar();
		Object[][] data = null;

		List<VOMascota> mascotas = facade.listarMascotas(cedula);
		int total = mascotas.size();

		if (total != 0) {
			data = new Object[total][3];

			for (int i = 0; i < total; i++) {
				data[i][0] = new String(mascotas.get(i).getApodo());
				data[i][1] = new String(mascotas.get(i).getRaza());
				data[i][2] = new Integer(mascotas.get(i).getCedulaDueño());
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
