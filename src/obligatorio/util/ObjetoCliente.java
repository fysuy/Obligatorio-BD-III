package obligatorio.util;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.NotBoundException;

import javax.swing.JOptionPane;

import obligatorio.logica.IFachada;


public class ObjetoCliente implements Serializable {

	
	private static final long serialVersionUID = 1L;
	public static IFachada Inicializar(){
		IFachada fachada = null;
		try
		{
					
			String url ="//" + ManageString.getProperty("ipServidor")+ ":" + ManageString.getProperty("puertoServidor")	+ "/" + "fac";		
			fachada = (IFachada) Naming.lookup(url); 	// ACCEDE AL SERVER 
	
		}
		catch (FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "Error.\nArchivo de configuracion no encontrado.\n"+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		} 
		catch (NotBoundException e)
		{
			e.printStackTrace();
		}
		return fachada;
		
	}

}
