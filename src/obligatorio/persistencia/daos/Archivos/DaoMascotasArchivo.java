package obligatorio.persistencia.daos.Archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.Mascota;
import obligatorio.logica.valueObjects.VOMascota;
import obligatorio.persistencia.daos.IDaoMascotas;
import obligatorio.util.IConexion;

public class DaoMascotasArchivo implements IDaoMascotas {

	private int cedulaDuenio;

	private static String extension = ".txt";
	private static String ruta = "./data/";
	private static String prefijo = "mascotas-";

	public DaoMascotasArchivo() {

	}
	
	public DaoMascotasArchivo(int cedula) {
		this.cedulaDuenio = cedula;
	}

	public void setCedulaDuenio(int cedula) {
		this.cedulaDuenio = cedula;
	}

	public boolean member(IConexion ic, String apodo)
			throws PersistenciaException, IOException {
		
		boolean existe = false;
		String fileName = nombreArchivo(cedulaDuenio);
		File archivoMascotas = new File(fileName);
		
		// Si existe el archivo con mascotas 
		// busca la mascota con el apodo pasado como parametro
		if (archivoMascotas.exists()) {
						
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			
			@SuppressWarnings("unused")
			String cedulaDuenio, apodoMascota, razaMascota;
			String linea;
			
			while ((linea = br.readLine()) != null && existe == false) {
				cedulaDuenio = linea;
				apodoMascota = br.readLine();
				if (apodoMascota.equals(apodo)) {
					existe = true;
				}
				razaMascota = br.readLine();		
			}
			
			br.close();
		}
		return existe;
	}

	@Override
	public int insert(IConexion ic, Mascota mascota)
			throws PersistenciaException {
		
		try {
			
			String cedula = Integer.toString(cedulaDuenio);
			String apodo = mascota.getApodo();
			String raza = mascota.getRaza();

			String fileName = nombreArchivo(cedulaDuenio);
			String data = cedula + "\n" + apodo + "\n" + raza + "\n";
			
			Path path = Paths.get(fileName);
			File archivoMascotas = new File(fileName);
			// Si ya existe el archivo con las mascotas del dueño hace un APPEND
			if (archivoMascotas.exists()) {
				Files.write(path, data.getBytes(), StandardOpenOption.APPEND);
			}
			else {
				// Si no existe el archivo con las mascotas del dueño lo crea
				Files.write(path, data.getBytes());
			}
			

		} catch (IOException e) {
			throw new PersistenciaException("Se produjo un error: "
					+ e.getMessage());
		}
		
		return 1;

	}

	@Override
	public List<VOMascota> listarMascotas(IConexion con)
			throws PersistenciaException {
		
		ArrayList<VOMascota> mascotas = new ArrayList<VOMascota>();
		
		try {
			
			String fileName = nombreArchivo(cedulaDuenio);
			
			String apodo, raza;
			int cedula;
			String linea;
			
			BufferedReader br = new BufferedReader(new FileReader(fileName));;

			while ((linea = br.readLine()) != null) {
				
				cedula = Integer.parseInt(linea);
				apodo = br.readLine();
				raza = br.readLine();
				VOMascota mascota = new VOMascota(raza, apodo, cedula);
				mascotas.add(mascota);
				
			}
			
			br.close();
			
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			throw new PersistenciaException("Se produjo un error en IO");
		}
		return mascotas;
	}

	@Override
	public void borrarMascotas(IConexion con) {
		File dir = new File(ruta);
		File[] listaDeArchivos = dir.listFiles();
		int cantFiles = listaDeArchivos.length;
		String cedString = Integer.toString(cedulaDuenio);
		for (int i = 0; i < cantFiles; i++) {
			String nombreFile = listaDeArchivos[i].getName();

			if (nombreFile.endsWith(cedString + extension)) {
				listaDeArchivos[i].delete();
			}
		}
	}
	
	private String nombreArchivo(int cedula) {
		return new String(ruta + prefijo + Integer.toString(cedula) + extension); 
	}

}
