package obligatorio.persistencia;

import obligatorio.persistencia.daos.IDaoDueños;
import obligatorio.persistencia.daos.IDaoMascotas;
import obligatorio.persistencia.daos.Archivos.DaoDueñosArchivo;
import obligatorio.persistencia.daos.Archivos.DaoMascotasArchivo;

public class FileFactory implements IAbstractFactory {
	public IDaoDueños crearDaoDuenios() {
		return new DaoDueñosArchivo();
	}
	
	public IDaoMascotas crearDaoMascotas(int cedulaDueño) {
		return new DaoMascotasArchivo(cedulaDueño);
	}
}
