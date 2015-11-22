package obligatorio.persistencia;

import obligatorio.persistencia.daos.IDaoDueños;
import obligatorio.persistencia.daos.IDaoMascotas;
import obligatorio.persistencia.daos.MySQL.DaoDueñosSQL;
import obligatorio.persistencia.daos.MySQL.DaoMascotasSQL;

public class SQLFactory implements IAbstractFactory {
	public IDaoDueños crearDaoDuenios() {
		return new DaoDueñosSQL();
	}

	public IDaoMascotas crearDaoMascotas(int cedulaDueño) {
		return new DaoMascotasSQL(cedulaDueño);
	}
}
