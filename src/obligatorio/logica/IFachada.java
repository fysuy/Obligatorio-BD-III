package obligatorio.logica;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import obligatorio.exceptions.DueñoException;
import obligatorio.exceptions.LogicaException;
import obligatorio.exceptions.MascotaException;
import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.valueObjects.VODueño;
import obligatorio.logica.valueObjects.VOMascota;

public interface IFachada extends Remote {

	public void nuevoDueño(VODueño dueño) throws DueñoException,
			PersistenciaException, LogicaException;

	public void nuevaMascota(VOMascota pMascota) throws PersistenciaException,
			DueñoException, RemoteException, MascotaException, IOException,
			LogicaException;

	public List<VODueño> listarDueños() throws PersistenciaException,
			RemoteException;

	public List<VOMascota> listarMascotas(int cedulaDueño)
			throws DueñoException, RemoteException, PersistenciaException,
			LogicaException;

	public void borrarDueñoMascotas(int cedulaDueño) throws DueñoException,
			RemoteException, PersistenciaException, LogicaException;

}
