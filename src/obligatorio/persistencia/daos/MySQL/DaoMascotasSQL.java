package obligatorio.persistencia.daos.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.Mascota;
import obligatorio.logica.valueObjects.VOMascota;
import obligatorio.persistencia.consultas.Consultas;
import obligatorio.persistencia.daos.IDaoMascotas;
import obligatorio.util.IConexion;
import obligatorio.util.MySQL.ConexionMySQL;

import com.mysql.jdbc.PreparedStatement;

public class DaoMascotasSQL implements IDaoMascotas {
	private int cedulaDueño;

	public DaoMascotasSQL(int cedulaDueño) {
		this.cedulaDueño = cedulaDueño;
	}

	public boolean member(IConexion ic, String apodo)
			throws PersistenciaException {
		boolean existe = false;
		Consultas consultas = new Consultas();
		String query = consultas.existeMascota();

		try {
			PreparedStatement pstmt = (PreparedStatement) ((ConexionMySQL) ic)
					.getCon().prepareStatement(query);
			pstmt.setString(1, apodo);
			pstmt.setInt(2, this.cedulaDueño);
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

	public int insert(IConexion ic, Mascota mascota)
			throws PersistenciaException {
		Consultas consultas = new Consultas();
		String insert = consultas.insertarMascota();

		int rs;
		try {
			PreparedStatement pstmt = (PreparedStatement) ((ConexionMySQL) ic)
					.getCon().prepareStatement(insert);
			pstmt.setString(1, mascota.getApodo());
			pstmt.setString(2, mascota.getRaza());
			pstmt.setInt(3, this.cedulaDueño);
			rs = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return rs;
	}

	public void borrarMascotas(IConexion ic) throws PersistenciaException {
		Consultas consultas = new Consultas();
		String delete = consultas.borrarMascotas();

		try {
			PreparedStatement pstmt = (PreparedStatement) ((ConexionMySQL) ic)
					.getCon().createStatement();
			pstmt.setInt(1, this.cedulaDueño);
			pstmt.executeUpdate(delete);
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	public List<VOMascota> listarMascotas(IConexion ic)
			throws PersistenciaException {
		Consultas consultas = new Consultas();
		List<VOMascota> lista = new ArrayList<VOMascota>();
		String queryLista = consultas.listarMascotas();

		try {
			PreparedStatement pstmt = (PreparedStatement) ((ConexionMySQL) ic)
					.getCon().prepareStatement(queryLista);
			pstmt.setInt(1, this.cedulaDueño);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String apodo = rs.getString("apodo");
				String raza = rs.getString("raza");
				int ci = rs.getInt("cedulaDueño");

				VOMascota pet = new VOMascota(apodo, raza, ci);
				lista.add(pet);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return lista;
	}
}
