package uniandes.isis2304.SuperAndes.negocio;

import java.sql.Timestamp;

public class RFC10Cliente {
	
	private long identificacion;
	
	private String nombre;
	
	private String correo;
	
	private String direccion;
	
	private int cantidad;
	
	private Timestamp fecha;
	
	public RFC10Cliente () {
		this.identificacion = 0;
		this.nombre = "";
		this.correo = "";
		this.direccion = "";
		this.cantidad = 0;
		this.fecha = null;
	}

	public RFC10Cliente(long identificacion, String nombre, String correo, String direccion,
			int cantidad, Timestamp fecha) {
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.correo = correo;
		this.direccion = direccion;
		this.cantidad = cantidad;
		this.fecha = fecha;
	}

	public long getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(long identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	
	
	
	

}
