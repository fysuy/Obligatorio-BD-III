package obligatorio.persistencia.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import obligatorio.logica.Dueño;
import obligatorio.logica.valueObjects.*;
import obligatorio.persistencia.consultas.Consultas;
import obligatorio.util.Conexion;
import obligatorio.util.IConexion;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DaoDueñosSQL implements IDaoDueños {	
	public DaoDueñosSQL (){
		super();
	} 

	public boolean member (IConexion ic, int ci) throws SQLException {
		boolean existe = false;
		Consultas consultas = new Consultas();
		String query = consultas.existeDueño();

		PreparedStatement pstmt = (PreparedStatement) ((Conexion)ic).getCon().prepareStatement(query);
		pstmt.setInt (1, ci);

		ResultSet rs = pstmt.executeQuery();
		if(rs.next())
			existe = true;

		rs.close();
		pstmt.close();

		return existe;
	}

	public int insert (IConexion ic, Dueño dueño) throws SQLException {		 		 
		Consultas consultas = new Consultas();
		String insert = consultas.insertarDueño();

		PreparedStatement pstmt = (PreparedStatement) ((Conexion)ic).getCon().prepareStatement(insert);
		pstmt.setInt(1, dueño.getCedula());
		pstmt.setString(2, dueño.getNombre());
		pstmt.setString(3, dueño.getApellido());

		int rs = pstmt.executeUpdate();

		pstmt.close();

		return rs;
	}

	public Dueño find (IConexion ic, int cedula) throws SQLException {
		Consultas consultas = new Consultas();

		String query = consultas.obtenerDueño();
		PreparedStatement pstmt = (PreparedStatement) ((Conexion)ic).getCon().prepareStatement(query);
		pstmt.setInt(1, cedula);

		ResultSet rs = pstmt.executeQuery();

		String nombre = rs.getString("nombre");
		String apellido = rs.getString("apellido");

		Dueño dueño = new Dueño(cedula, nombre, apellido);

		rs.close();
		pstmt.close();

		return dueño;		
	}

	public void delete (IConexion ic, int cedula) throws SQLException {
		Consultas consultas = new Consultas();

		String delete = consultas.borrarDueñoMascotas();
		PreparedStatement pstmt = (PreparedStatement) ((Conexion)ic).getCon().prepareStatement(delete);
		pstmt.setInt(1, cedula);

		pstmt.executeUpdate();

		pstmt.close();
	}

	public List<VODueño> listarDueños(IConexion ic) throws SQLException {

		Consultas consultas = new Consultas();

		List<VODueño> lista = new ArrayList<VODueño>();

		String queryLista = consultas.listarDueños();
		Statement stmt = (Statement) ((Conexion)ic).getCon().createStatement();
		ResultSet rs = stmt.executeQuery(queryLista);

		while(rs.next()){
			int ci = rs.getInt("cedula");
			String nombre = rs.getString("nombre");
			String apellido = rs.getString("apellido");

			VODueño dueño = new VODueño(ci, nombre, apellido);
			lista.add(dueño);			 
		}

		return lista;
	}
}
