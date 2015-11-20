package obligatorio.grafica.controladores;

import java.io.IOException;
import java.sql.SQLException;

import obligatorio.logica.Fachada;
import obligatorio.logica.exceptions.ExceptionsDueños;
import obligatorio.logica.valueObjects.VODueño;

public class ControladorIngresarDueño {
    //FIXME: cambiar por IFachada
    private Fachada fachada;
    
    public void ingresarDueño(int cedula, String nombre, String apellido) throws SQLException, ExceptionsDueños, IOException {
        
    	fachada = Fachada.getInstance();
        VODueño voDueño = new VODueño(cedula, nombre, apellido);
    	fachada.nuevoDueño(voDueño);
       
    }
        
}