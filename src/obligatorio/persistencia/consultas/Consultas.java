package obligatorio.persistencia.consultas;

public class Consultas {
	
	/* CONSULTAS DUEÑOS */
	 
	public String listarDueños(){
		String query= "SELECT * FROM Dueños";
		return query;
	}
	
	public String existeDueño() {
		String query = "SELECT d.cedula FROM Dueños d WHERE d.cedula = (?)";
		return query;
	}
	
	public String obtenerDueño() {
		String query = "SELECT * FROM Dueños d WHERE d.cedula = (?)";
		return query;
	}
	
	public String insertarDueño() {
		String insert = "INSERT INTO Dueños (cedula, nombre, apellido)" +
						"VALUES (?, ?, ?)";
		return insert;
	}
	
	public String borrarDueñoMascotas() {
		String delete = "DELETE FROM Mascotas m WHERE m.cedulaDueño = (?)" +
						"DELETE FROM Dueños d WHERE d.cedula = (?)";
		return delete;
	}
	
	
	/* CONSULTAS MASCOTAS */
	
	public String listarMascotas(){
		String query = "SELECT * FROM Mascotas where cedulaDueño = (?)";
		return query;
	}
	
	public String existeMascota() {
		String query = "SELECT m.apodo FROM Mascotas m WHERE m.apodo = (?)";
		return query;
	}
	
	public String insertarMascota() {
		String insert = "INSERT INTO Mascotas (apodo, raza, cedulaDueño)" +
						"VALUES (?, ?, ?)";
		return insert;
	}
	
	public String booMascotas(){
		String boom = "Delete from Mascotas ";
		return boom;
	}
	

}
