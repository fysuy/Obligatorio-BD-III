package obligatorio.persistencia.daos.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.Dueño;
import obligatorio.logica.valueObjects.VODueño;
import obligatorio.persistencia.consultas.Consultas;
import obligatorio.persistencia.daos.IDaoDueños;
import obligatorio.util.IConexion;
import obligatorio.util.MySQL.ConexionMySQL;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DaoDueñosSQL implements IDaoDueños {
	public DaoDueñosSQL() {
		super();
	}

	public boolean member(IConexion ic, int ci) throws PersistenciaException {
		boolean existe = false;

		try {
			Consultas consultas = new Consultas();
			String query = consultas.existeDueño();

			PreparedStatement pstmt = (PreparedStatement) ((ConexionMySQL) ic)
					.getCon().prepareStatement(query);
			pstmt.setInt(1, ci);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				existe = true;

			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return existe;
	}

	public int insert(IConexion ic, Dueño dueño) throws PersistenciaException {
		Consultas consultas = new Consultas();
		String insert = consultas.insertarDueño();
		int rs;

		try {
			PreparedStatement pstmt = (PreparedStatement) ((ConexionMySQL) ic)
					.getCon().prepareStatement(insert);
			pstmt.setInt(1, dueño.getCedula());
			pstmt.setString(2, dueño.getNombre());
			pstmt.setString(3, dueño.getApellido());
			rs = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return rs;
	}

	public Dueño find(IConexion ic, int cedula) throws PersistenciaException {
		Consultas consultas = new Consultas();
		String query = consultas.obtenerDueño();
		Dueño dueño;

		try {
			PreparedStatement pstmt = (PreparedStatement) ((ConexionMySQL) ic)
					.getCon().prepareStatement(query);
			pstmt.setInt(1, cedula);
			ResultSet rs = pstmt.executeQuery();

			String nombre = rs.getString("nombre");
			String apellido = rs.getString("apellido");
			dueño = new Dueño(cedula, nombre, apellido);

			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return dueño;
	}

	public void delete(IConexion ic, int cedula) throws PersistenciaException {
		Consultas consultas = new Consultas();
		String delete = consultas.borrarDueñoMascotas();

		try {
			PreparedStatement pstmt = (PreparedStatement) ((ConexionMySQL) ic)
					.getCon().prepareStatement(delete);
			pstmt.setInt(1, cedula);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	public List<VODueño> listarDueños(IConexion ic)
			throws PersistenciaException {
		Consultas consultas = new Consultas();
		String queryLista = consultas.listarDueños();
		List<VODueño> lista = new ArrayList<VODueño>();

		try {
			Statement stmt = (Statement) ((ConexionMySQL) ic).getCon()
					.createStatement();
			ResultSet rs = stmt.executeQuery(queryLista);

			while (rs.next()) {
				int ci = rs.getInt("cedula");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");

				VODueño dueño = new VODueño(ci, nombre, apellido);
				lista.add(dueño);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return lista;
	}
}
