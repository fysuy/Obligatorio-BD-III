package obligatorio.persistencia.consultas;

public class Consultas {

	/* CONSULTAS DUEÑOS */

	public String listarDueños() {
		String query = "SELECT * FROM dym.Dueños";
		return query;
	}

	public String existeDueño() {
		String query = "SELECT d.cedula FROM dym.Dueños d WHERE d.cedula = (?)";
		return query;
	}

	public String obtenerDueño() {
		String query = "SELECT * FROM dym.Dueños d WHERE d.cedula = (?)";
		return query;
	}

	public String insertarDueño() {
		String insert = "INSERT INTO dym.Dueños (cedula, nombre, apellido)"
				+ "VALUES (?, ?, ?)";
		return insert;
	}

	public String borrarDueño() {
		String delete = "DELETE FROM dym.Dueños WHERE cedula = (?)";
		return delete;
	}

	/* CONSULTAS MASCOTAS */

	public String listarMascotas() {
		String query = "SELECT * FROM dym.Mascotas where cedulaDueño = (?)";
		return query;
	}

	public String existeMascota() {
		String query = "SELECT m.apodo FROM dym.Mascotas m WHERE m.apodo = (?) and m.cedulaDueño = (?)";
		return query;
	}

	public String insertarMascota() {
		String insert = "INSERT INTO dym.Mascotas (apodo, raza, cedulaDueño)"
				+ "VALUES (?, ?, ?)";
		return insert;
	}

	public String borrarMascotas() {
		String delete = "Delete from dym.Mascotas where cedulaDueño = (?)";
		return delete;
	}
}
