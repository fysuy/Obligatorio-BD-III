package obligatorio.util;

import java.io.Serializable;


public class PoolArchivos implements Serializable {

	private static final long serialVersionUID = 1L;
	private int cantLectores;
	private boolean escribiendo;
	
	public PoolArchivos(){
		cantLectores=0;
		escribiendo=false;
	}
	
	public synchronized void comienzoLectura() //throws InterruptedException
	{
		while (escribiendo) 
		{	try { 
				wait();
			}
			catch (InterruptedException e){
				e.printStackTrace();
			}
		}
		
		cantLectores = cantLectores + 1;			
	}
	
	public synchronized void terminoLectura()
	{
		notify();
		cantLectores = cantLectores - 1;
	}
	
	public synchronized void comienzoEscritura() 
	{
		while ((escribiendo) || (cantLectores>0))
		{	try { 
				wait();
			}
			catch (InterruptedException e){
				e.printStackTrace();
			}
		}
		
		escribiendo = true;
	}
	
	public synchronized void terminoEscritura()
	{
		notify();
		escribiendo = false;
	}

}
