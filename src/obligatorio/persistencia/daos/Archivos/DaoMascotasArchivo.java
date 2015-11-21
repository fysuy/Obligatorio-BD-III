package obligatorio.persistencia.daos.Archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.Mascota;
import obligatorio.logica.valueObjects.VOMascota;
import obligatorio.persistencia.daos.IDaoMascotas;
import obligatorio.util.IConexion;


public class DaoMascotasArchivo implements IDaoMascotas {

private int cedula;
	
	private static String extension = ".txt";
	private static String ruta = "data/Mascotas/";
	
	public DaoMascotasArchivo()
	{
		
	}
	
	public void setCedulaDuenio(int cedula) {
		this.cedula =cedula;
		
	}
	
	
	public boolean member(IConexion ic, String apodo) throws PersistenciaException {
		boolean isMember=false;
		File fichero=new File(ruta+apodo+"-"+cedula+extension);
		if(fichero.exists()){
			isMember=true;
		} else {
			File dir=new File(ruta);
			String[] listaDeArchivos=dir.list();
			int cantFiles=listaDeArchivos.length;
			
			for (int i=0; i<cantFiles; i++){
				if (listaDeArchivos[i].startsWith(apodo))
					isMember=true;
			}
		}
		return isMember;
	}

	@Override
	public int insert (IConexion ic, Mascota mascota) throws PersistenciaException {
		File fichero=new File(ruta+mascota.getApodo()+"-"+cedula+extension);
		if(!fichero.exists()){
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(new FileWriter(ruta+mascota.getApodo()+"-"+cedula+extension));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pw.append(mascota.getApodo()+"\n");
			pw.append(mascota.getRaza()+"\n");
			pw.append(cedula+"\n");
			pw.close();
		}
		return cedula;
		
		
	}
		

	@Override
	public LinkedList <VOMascota> listarMascotas(IConexion con) throws PersistenciaException
	{
		LinkedList<VOMascota> list=new LinkedList<VOMascota>();
		try {
			File dir=new File(ruta);
			String[] listaDeArchivos=dir.list();
			int cantFiles=listaDeArchivos.length;

			String apodo = "";
			String raza = "";
			String ced = "";
			BufferedReader bf;
			
			for (int i=0; i < cantFiles; i++){
				
				if (listaDeArchivos[i].endsWith(cedula+extension)){
					bf = new BufferedReader(new FileReader(ruta+listaDeArchivos[i]));
					apodo=bf.readLine();	//TOMO POR DEFECTO QUE LA PRIMERA LINEA ES EL APODO
					raza=bf.readLine();		// LA SEGUNDA LA RAZA
					ced=bf.readLine();		// Y LA TERCERA LA CEDULA DEL DUEÑO
					VOMascota vom=new VOMascota (apodo, raza, Integer.parseInt(ced));
					bf.close();
					
					list.add(vom);
				}
			}
			//TODO - Anlizar caso
		} catch (FileNotFoundException e) {
			throw new PersistenciaException("Se produjo un error: no se encontro archivo.");
		} catch (IOException e) {
			throw new PersistenciaException("Se produjo un error en IO");
		}
		return list;
	}
		

	@Override
	public void borrarMascotas(IConexion con)  {
		File dir=new File(ruta);
		File[] listaDeArchivos=dir.listFiles();
		int cantFiles=listaDeArchivos.length;
		String cedString = Integer.toString(cedula);
		for (int i=0; i < cantFiles; i++){
			String nombreFile=listaDeArchivos[i].getName();
			
			if (nombreFile.endsWith(cedString+extension)){
				listaDeArchivos[i].delete();
			}
		}
	}

	

}
