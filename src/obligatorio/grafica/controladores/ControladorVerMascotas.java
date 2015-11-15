package obligatorio.grafica.controladores;

import obligatorio.logica.valueObjects.VOMascota;

public class ControladorVerMascotas {

	
	public Object[][] listarMascotas(int cedula) {
		
		Object[][] data = null;
		
		// VOMascota[] mascotas = {} ;
		
		VOMascota[] mascotas = {new VOMascota("Salchicha", "Pipe", 1234567)} ;
		// VOMascota[] mascotas = (VOMascota[]) Fachada.getInstance().listarMascotas(cedula).toArray();
		
		if (mascotas.length != 0) {
			
			data = new Object[mascotas.length][3];
			
			for (int i=0; i < mascotas.length; i++) {
				data [i][0] = new String(mascotas[i].getApodo());
				data [i][1] = new String(mascotas[i].getRaza());
				data [i][2] = new Integer(mascotas[i].getCedulaDueño());
			}			
		} else {
			
			data = new Object[1][3];
			data [0][0] = "Vacio";
			data [0][1] = "Vacio";
			data [0][2] = "Vacio";
		}
		
		return data;
	}

}
