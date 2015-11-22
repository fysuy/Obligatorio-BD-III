package obligatorio.logica;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import obligatorio.exceptions.DueñoException;
import obligatorio.exceptions.MascotaException;
import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.valueObjects.VODueño;
import obligatorio.logica.valueObjects.VOMascota;

public interface IFachada extends Remote {

	// public void nuevoDueño(VODueño dueño) throws DueñoException,
	// PersistenciaException;

	public void nuevaMascota(VOMascota pMascota) throws PersistenciaException,
			DueñoException, RemoteException, MascotaException, IOException;

	public List<VODueño> listarDueños() throws PersistenciaException,
			RemoteException;

	public List<VOMascota> listarMascotas(int cedulaDueño)
			throws DueñoException, RemoteException, PersistenciaException;

	public void borrarDueñoMascotas(int cedulaDueño) throws DueñoException,
			RemoteException, PersistenciaException;

}
