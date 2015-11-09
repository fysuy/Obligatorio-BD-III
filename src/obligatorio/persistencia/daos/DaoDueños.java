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

public class DaoDueños {
	
	 private String driver = "com.mysql.jdbc.Driver";
	 private String url = "jdbc:mysql://localhost:3306";
	 private String user = "root";
	 private String password = "";
	  
	 public DaoDueños (){
		 
	 } 
	 
	 public boolean member (int ci) throws SQLException{
		 
		Connection con = (Connection) DriverManager.getConnection(url,user,password);

		boolean existe = false;
		Consultas consultas = new Consultas();
		String query = consultas.existeDueño();
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(query);
		pstmt.setInt (1, ci);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next())
			existe= true;
		
		rs.close();
		pstmt.close();
		con.close();
		
		return existe;
		 
	 }
	 
	 
	 public int insert ( int cedula, String nombre, String apellido)throws SQLException {
		//pasarle Dueño	
		Connection con = (Connection) DriverManager.getConnection(url,user,password);
		Consultas consultas = new Consultas();
		String insert = consultas.insertarDueño();
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(insert);
		pstmt.setInt(1, cedula);
		pstmt.setString(2, nombre);
		pstmt.setString(3, apellido);
		int rs = pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
			
		return rs;
		}
	 
	 public VODueño find (int cedula) throws SQLException {
		Connection con = (Connection) DriverManager.getConnection(url,user,password);
		Consultas consultas = new Consultas();
		
		String query = consultas.obtenerDueño();
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(query);
		pstmt.setInt(1, cedula);
		
		ResultSet rs = pstmt.executeQuery();
		
		String nombre = rs.getString("nombre");
		String apellido = rs.getString("apellido");
		
		VODueño dueño = new VODueño(cedula, nombre, apellido);
		
		rs.close();
		pstmt.close();
		con.close();
		return dueño;

		
	 }
	 
	 public void delete (int cedula) throws SQLException {
		Connection con = (Connection) DriverManager.getConnection(url,user,password);
		Consultas consultas = new Consultas();
			
		String delete = consultas.borrarDueñoMascotas();
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(delete);
		pstmt.setInt(1, cedula);
		
		pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
	 }
	 
	 
	 public List <VODueño> listarDueños() throws SQLException{
		 Connection con = (Connection) DriverManager.getConnection(url,user,password);
		 Consultas consultas = new Consultas();
		 
		 List <VODueño> lista = new ArrayList<VODueño>();
		 
		 
		 String queryLista = consultas.listarDueños();
		 Statement stmt = (Statement) con.createStatement();
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
