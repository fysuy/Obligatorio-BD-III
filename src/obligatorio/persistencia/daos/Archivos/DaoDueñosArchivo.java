package obligatorio.persistencia.daos.Archivos;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.Dueño;
import obligatorio.logica.valueObjects.VODueño;
import obligatorio.persistencia.daos.IDaoDueños;
import obligatorio.util.IConexion;

public class DaoDueñosArchivo implements IDaoDueños {

	private static String extension = ".txt";
	private static String ruta = "data/Duenios/";

	private DaoMascotasArchivo daoM = new DaoMascotasArchivo();

	public boolean member(IConexion ic, int ced) {
		File fichero = new File(ruta + Integer.toString(ced) + extension);
		return (fichero.exists());
	}

	public void insert(IConexion ic, Dueño due) throws PersistenciaException {

		try {
			daoM.setCedulaDuenio(due.getCedula());

			File fichero = new File(ruta + due.getCedula() + extension);

			String ced = Integer.toString(due.getCedula());
			String nom = due.getNombre();
			String ape = due.getApellido();

			if (!fichero.exists()) {
				PrintWriter pw = new PrintWriter(new FileWriter(ruta + ced
						+ extension));
				pw.append(ced + "\n");
				pw.append(nom + "\n");
				pw.append(ape + "\n");
				pw.close();
			}
		} catch (IOException e) {
			throw new PersistenciaException("Se produjo un error:"
					+ e.getMessage());
		}

	}

	public Dueño find(IConexion con, int ced) throws PersistenciaException {
		Dueño due = null;
		try {
			// Abrimos el archivo
			FileInputStream file = new FileInputStream(ruta
					+ Integer.toString(ced) + extension);
			// Creamos el objeto de entrada
			DataInputStream entrada = new DataInputStream(file);
			// Creamos el Buffer de Lectura
			BufferedReader buffer = new BufferedReader(new InputStreamReader(
					entrada));

			// Leer el archivo linea por linea
			@SuppressWarnings("unused")
			String cedula = buffer.readLine();
			String nom = buffer.readLine();
			String ape = buffer.readLine();

			// Cerramos el archivo
			entrada.close();

			due = new Dueño(ced, nom, ape);
		} catch (FileNotFoundException e) {
			throw new PersistenciaException("No existe archivo");
		} catch (IOException e) {
			throw new PersistenciaException("Se produjo un error:"
					+ e.getMessage());
		}
		return due;
	}

	public void delete(IConexion con, int ced) throws PersistenciaException {

		if (this.member(con, ced)) {
			File ficheroD = new File(ruta + Integer.toString(ced) + extension);
			ficheroD.delete();
			// BORRAR LAS MASCOTAS
			daoM.borrarMascotas(con);
		}
	}

	public List<VODueño> listarDueños(IConexion con)
			throws PersistenciaException {
		List<VODueño> list = new ArrayList<VODueño>();
		try {
			File dir = new File(ruta);
			String[] listaDeArchivos = dir.list();
			int cantFiles = listaDeArchivos.length;

			String nombre = "";
			String apellido = "";
			String ced = "";
			BufferedReader bf;

			for (int i = 0; i < cantFiles; i++) {

				bf = new BufferedReader(new FileReader(ruta
						+ listaDeArchivos[i]));
				ced = bf.readLine();
				nombre = bf.readLine();
				apellido = bf.readLine();

				VODueño vod = new VODueño(Integer.parseInt(ced), nombre,
						apellido);
				bf.close();

				list.add(vod);
			}
		} catch (FileNotFoundException e) {
			throw new PersistenciaException("No existe archivo");
		} catch (IOException e) {
			throw new PersistenciaException("Se produjo un error:"
					+ e.getMessage());
		}
		return list;
	}

}
