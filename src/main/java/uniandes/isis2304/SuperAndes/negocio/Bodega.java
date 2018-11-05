package uniandes.isis2304.SuperAndes.negocio;
/**
 * Clase que representa uan bodega que pertenece a una sucursal y que almacena cierto tipo de producto
 * @author Rouzajor
 *
 */
public class Bodega implements VOBodega{
	
	/**
	 * Id de la bodega
	 */
	
	private long idBodega;
	
	/**
	 * volumen de la bodega
	 */
	private double volumen;
	
	/**
	 * peso de la bodega
	 */
	private double peso;
	
	/**
	 * tipo de prorductos que almacena la bodega
	 */
	private String tipo;
	
	/**
	 * sucursal a la que pertenece la bodega
	 */
	private long sucursal;

	/**
	 * Contructo vacio de la bodega
	 */
	public Bodega() {
		this.idBodega = 0;
		this.volumen = 0;
		this.peso = 0;
		this.tipo = "";
		this.sucursal = 0;
	}
	
	/**
	 * Contructor con parametros de la bodega
	 * @param idBodega id de la bodega
	 * @param volumen volumen de la bodega
	 * @param peso peso de la bodega
	 * @param tipo tipo de productos que peude almacenar la podega
	 * @param sucursal sucursal a la que pertenece la bodega
	 */
	public Bodega(long idBodega, double volumen, double peso, String tipo, long sucursal) {
		this.idBodega = idBodega;
		this.volumen = volumen;
		this.peso = peso;
		this.tipo = tipo;
		this.sucursal = sucursal;
	}

	/**
	 * Los siguientes metodos son los get y set de todos los atributos
	 */
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bodega other = (Bodega) obj;
		if (idBodega != other.idBodega)
			return false;
		if (Double.doubleToLongBits(peso) != Double.doubleToLongBits(other.peso))
			return false;
		if (sucursal != other.sucursal)
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (Double.doubleToLongBits(volumen) != Double.doubleToLongBits(other.volumen))
			return false;
		return true;
	}
	
	
	

}
