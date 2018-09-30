package uniandes.isis2304.SuperAndes.negocio;

public class Promocion implements VOPromocion{
	
	private long idPromocion;
	
	private int tipo;
	
	private double n;
	
	private double m;
	
	private String fechaCaducidad;
	
	public Promocion() {
		this.idPromocion = 0;
		this.tipo = 0;
		this.n = 0;
		this.m = 0;
		this.fechaCaducidad = "";
	}

	public Promocion(long idPromocion, int tipo, double n, double m, String fechaCaducidad) {
		this.idPromocion = idPromocion;
		this.tipo = tipo;
		this.n = n;
		this.m = m;
		this.fechaCaducidad = fechaCaducidad;
	}

	public long getIdPromocion() {
		return idPromocion;
	}

	public void setIdPromocion(long idPromocion) {
		this.idPromocion = idPromocion;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public double getN() {
		return n;
	}

	public void setN(double n) {
		this.n = n;
	}

	public double getM() {
		return m;
	}

	public void setM(double m) {
		this.m = m;
	}

	public String getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(String fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	
	@Override
	public String toString() {
		return "Promocion [idPromocion=" + idPromocion + ", tipo=" + tipo + ", n=" + n + ", m=" + m + 
				", fechaCaducidad=" + fechaCaducidad + "]";
	}
	
	

}
