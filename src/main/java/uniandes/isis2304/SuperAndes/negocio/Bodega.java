package uniandes.isis2304.SuperAndes.negocio;

public class Bodega implements VOBodega{
	
	private long idBodega;
	
	private double volumen;
	
	private double peso;
	
	private String tipo;
	
	private long sucursal;

	public Bodega() {
		this.idBodega = 0;
		this.volumen = 0;
		this.peso = 0;
		this.tipo = "";
		this.sucursal = 0;
	}
	
	public Bodega(long idBodega, double volumen, double peso, String tipo, long sucursal) {
		this.idBodega = idBodega;
		this.volumen = volumen;
		this.peso = peso;
		this.tipo = tipo;
		this.sucursal = sucursal;
	}

	public long getIdBodega() {
		return idBodega;
	}

	public void setIdBodega(long idBodega) {
		this.idBodega = idBodega;
	}

	public double getVolumen() {
		return volumen;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public long getSucursal() {
		return sucursal;
	}
	
	public void setSucursal(long sucursal) {
		this.sucursal = sucursal;
	}
	
	@Override
	public String toString() {
		return "Bodega [idBodega=" + idBodega + ", volumen=" + volumen + ", peso=" + peso+ ", tipo=" + tipo + ", sucursal=" + sucursal +
				"]";
	}
	
	

}
