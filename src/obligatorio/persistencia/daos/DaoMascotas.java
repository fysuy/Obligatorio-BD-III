package obligatorio.persistencia.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import obligatorio.logica.valueObjects.*;
import obligatorio.persistencia.consultas.Consultas;
import obligatorio.util.IConexion;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DaoMascotas {
	private int cedulaDueño;
		 
	public DaoMascotas (int cedulaDueño){
		 this.cedulaDueño = cedulaDueño;
	}	 
		 
	public boolean member (IConexion ic, String apodo) throws SQLException{
		Connection con = ic.getCon();
		boolean existe = false;
		Consultas consultas = new Consultas();
		
		String query = consultas.existeMascota();
		
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(query);
		pstmt.setString (1, apodo);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next())
			existe = true;
		
		rs.close();
		pstmt.close();
		con.close();
		
		return existe;	 
	}		 
		 
	 public int insert (IConexion ic, int cedulaDueño, String apodo, String raza) throws SQLException {
		Connection con = ic.getCon();
		Consultas consultas = new Consultas();
		String insert = consultas.insertarDueño();
		
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(insert);
		pstmt.setString(1, apodo);
		pstmt.setString(2, raza);
		pstmt.setInt(3, cedulaDueño);
		int rs = pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
			
		return rs;
	}
		 
	public void borrarMascotas (IConexion ic) throws SQLException {
		Connection con = ic.getCon();
		Consultas consultas = new Consultas();
			
		String delete = consultas.booMascotas();
		Statement stmt = (Statement) con.createStatement();
		stmt.executeQuery(delete);
		
		stmt.close();
		con.close();
	}
	
	public List <VOMascota> listarMascotas(IConexion ic) throws SQLException {
		Connection con = ic.getCon();
		Consultas consultas = new Consultas();
		 
		List <VOMascota> lista = new ArrayList<VOMascota>();
		 
		String queryLista = consultas.listarMascotas();
		Statement stmt = (Statement) con.createStatement();
		ResultSet rs = stmt.executeQuery(queryLista);
		
	 
		while(rs.next()){
			String apodo = rs.getString("apodo");
			String raza = rs.getString("raza");
			int ci = rs.getInt("ci");
			
			VOMascota pet = new VOMascota(apodo, raza, ci);
			lista.add(pet);			  
		}
					
		return lista;			 
	 }
}
