package uniandes.isis2304.SuperAndes.negocio;

public class Estante implements VOEstante{
	
	private long idEstante;
	
	private double volumen;
	
	private double peso;
	
	private String tipo;
	
	private int nivelAprovisionamiento;
	
	private long sucursal;
	
	public Estante() {
		this.idEstante = 0;
		this.volumen = 0;
		this.peso = 0;
		this.tipo = "";
		this.nivelAprovisionamiento = 0;
		this.sucursal = 0;
	}

	public Estante(long idEstante, double volumen, double peso, String tipo, int nivelAprovisonamiento, long sucursal) {
		this.idEstante = idEstante;
		this.volumen = volumen;
		this.peso = peso;
		this.tipo = tipo;
		this.nivelAprovisionamiento = nivelAprovisonamiento;
		this.sucursal = sucursal;
	}

	public long getIdEstante() {
		return idEstante;
	}

	public void setIdEstante(long idEstante) {
		this.idEstante = idEstante;
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

	public int getNivelAprovisionamiento() {
		return nivelAprovisionamiento;
	}

	public void setNivelAprovisionamiento(int nivelAprovisonamiento) {
		this.nivelAprovisionamiento = nivelAprovisonamiento;
	}
	
	public long getSucursal() {
		return sucursal;
	}
	
	public void setSucursal(long sucursal) {
		this.sucursal = sucursal;
	}
	
	@Override
	public String toString() {
		return "Estante [idEstante=" + idEstante + ", volumen=" + volumen + ", peso=" + peso + ", tipo=" + tipo +
				", nivelAprovisionamiento=" + nivelAprovisionamiento + ", sucursal=" + sucursal +  "]";
	}

}
