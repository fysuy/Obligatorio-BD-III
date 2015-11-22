package obligatorio.persistencia;

import obligatorio.persistencia.daos.IDaoDueños;
import obligatorio.persistencia.daos.IDaoMascotas;

public interface IAbstractFactory {
	public IDaoDueños crearDaoDuenios();
	public IDaoMascotas crearDaoMascotas(int cedulaDueño);
}
