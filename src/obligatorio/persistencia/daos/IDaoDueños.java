package obligatorio.persistencia.daos;

import java.sql.SQLException;
import java.util.List;

import obligatorio.logica.Dueño;
import obligatorio.logica.valueObjects.VODueño;
import obligatorio.util.IConexion;

public interface IDaoDueños {
	public boolean member (IConexion ic, int ci) throws SQLException;

	public int insert (IConexion ic, Dueño dueño) throws SQLException;

	public Dueño find (IConexion ic, int cedula) throws SQLException;

	public void delete (IConexion ic, int cedula) throws SQLException;

	public List<VODueño> listarDueños(IConexion ic) throws SQLException;
}
