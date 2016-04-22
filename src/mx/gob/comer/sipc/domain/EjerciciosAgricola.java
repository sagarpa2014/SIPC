package mx.gob.comer.sipc.domain;



public class EjerciciosAgricola  extends Ejercicios {
	
	private String ejercicioAgricola;

	
	
	
	
	public EjerciciosAgricola(int ejercicio, String ejercicioAgricola) {
		super();
		this.ejercicio = ejercicio;
		this.ejercicioAgricola = ejercicioAgricola;
	}

	public String getEjercicioAgricola() {
		return ejercicioAgricola;
	}

	public void setEjercicioAgricola(String ejercicioAgricola) {
		this.ejercicioAgricola = ejercicioAgricola;
	}
		
}