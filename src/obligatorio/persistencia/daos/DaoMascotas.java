package obligatorio.persistencia.daos;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import obligatorio.logica.valueObjects.*;
import obligatorio.persistencia.consultas.Consultas;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DaoMascotas {
	
	
		 private String driver = "com.mysql.jdbc.Driver";
		 private String url = "jdbc:mysql://localhost:3306";
		 private String user = "root";
		 private String password = "";
		 
		 public DaoMascotas (){
			 
		 }	 
		 
		 public boolean member (String apodo) throws SQLException{
			 
			Connection con = (Connection) DriverManager.getConnection(url,user,password);

			boolean existe = false;
			Consultas consultas = new Consultas();
			String query = consultas.existeMascota();
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(query);
			pstmt.setString (1, apodo);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				existe= true;
			
			rs.close();
			pstmt.close();
			con.close();
			
			return existe;
			 
		 }
		 
		 
		 public int insert ( int cedulaDueño, String apodo, String raza)throws SQLException {
				
			Connection con = (Connection) DriverManager.getConnection(url,user,password);
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
		 
	
		 
		 public void borrarMascotas () throws SQLException {
			Connection con = (Connection) DriverManager.getConnection(url,user,password);
			Consultas consultas = new Consultas();
				
			String delete = consultas.booMascotas();
			Statement stmt = (Statement) con.createStatement();
			stmt.executeQuery(delete);
			
			stmt.close();
			con.close();
		 }
		 
		 
		 public List <VOMascota> listarMascotas() throws SQLException{
			 Connection con = (Connection) DriverManager.getConnection(url,user,password);
			 Consultas consultas = new Consultas();
			 
			 List <VOMascota> lista = new ArrayList<VOMascota>();
			 
			 
			 String queryLista = consultas.listarMascotas();
			 Statement stmt = (Statement) con.createStatement();
			 ResultSet rs = stmt.executeQuery(queryLista);
			 
			 
			 while(rs.next()){
				 String apodo = rs.getString("apodo");
				 String raza = rs.getString("raza");
				 int ci = rs.getInt("ci");
				 
				 VOMascota pet = new VOMascota(apodo,raza,ci);
				 lista.add(pet);			 
				 
			 }
				
			return lista;
			 
		 }

}
