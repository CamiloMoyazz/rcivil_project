package cl.inacap.rcivilModel.dto;

public class Solicitud {

	private String rut;
	private String nombre;
	private String tipo;
	private int numAtencion;
	
	
	
	
	//GETTERS AND SETTERS
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getNumAtencion() {
		return numAtencion;
	}
	public void setNumAtencion(int numAtencion) {
		this.numAtencion = numAtencion;
	}
	
	
	
	
}
