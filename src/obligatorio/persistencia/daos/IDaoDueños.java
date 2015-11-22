package obligatorio.persistencia.daos;

import java.util.List;

import obligatorio.exceptions.LogicaException;
import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.Dueño;
import obligatorio.logica.valueObjects.VODueño;
import obligatorio.util.IConexion;

public interface IDaoDueños {
	public boolean member(IConexion ic, int ci) throws PersistenciaException;

	public void insert(IConexion ic, Dueño dueño) throws PersistenciaException;

	public Dueño find(IConexion ic, int cedula) throws PersistenciaException, LogicaException;

	public void delete(IConexion ic, int cedula) throws PersistenciaException;

	public List<VODueño> listarDueños(IConexion ic)
			throws PersistenciaException;
}
