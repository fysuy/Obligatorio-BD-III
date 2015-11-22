package obligatorio.logica;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import obligatorio.exceptions.DueñoException;
import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.valueObjects.VODueño;
import obligatorio.logica.valueObjects.VOMascota;

public interface IFachada  extends Remote {
	
	//public void nuevoDueño(VODueño dueño) throws DueñoException, PersistenciaException;
	
	public void nuevaMascota(VOMascota pMascota) throws PersistenciaException, DueñoException, RemoteException;
	
	public List<VODueño> listarDueños() throws PersistenciaException, RemoteException;
	
	public List<VOMascota> listarMascotas(int cedulaDueño) throws DueñoException, RemoteException;
	
	public void borrarDueñoMascotas(int cedulaDueño) throws DueñoException, RemoteException;
	

}
