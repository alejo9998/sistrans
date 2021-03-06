package uniandes.isis2304.SuperAndes.negocio;

import java.sql.Timestamp;

public class Promocion implements VOPromocion{
	
	public static final int TIPO_L = 1;
	public static final int TIPO_ll = 2;
	public static final int TIPO_lLL = 3;
	public static final int TIPO_lV = 4;
	
	private long idPromocion;
	
	private int tipo;
	
	private double n;
	
	private double m;
	
	private Timestamp fechaCaducidad;
	
	public Promocion() {
		this.idPromocion = 0;
		this.tipo = 0;
		this.n = 0;
		this.m = 0;
		this.fechaCaducidad = null;
	}

	public Promocion(long idPromocion, int tipo, double n, double m, Timestamp fechaCaducidad) {
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

	public Timestamp getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(Timestamp fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	
	@Override
	public String toString() {
		return "Promocion [idPromocion=" + idPromocion + ", tipo=" + tipo + ", n=" + n + ", m=" + m + 
				", fechaCaducidad=" + fechaCaducidad + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		if (idPromocion != other.idPromocion)
			return false;
		if (Double.doubleToLongBits(m) != Double.doubleToLongBits(other.m))
			return false;
		if (Double.doubleToLongBits(n) != Double.doubleToLongBits(other.n))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}
	
	
	
	

}
