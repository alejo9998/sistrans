package uniandes.isis2304.parranderos.negocio;

import java.util.List;

public class Proveedor 
{
	private Long NIT;

	private String Nombre;
	
	private List<ProductoProveedor> productosProveedor;

	public Long getNIT() {
		return NIT;
	}

	public void setNIT(Long nIT) {
		NIT = nIT;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public List<ProductoProveedor> getProductoProveedor() {
		return productosProveedor;
	}

	public void setProductoProveedor(ProductoProveedor productoProveedor) {
		productosProveedor.add( productoProveedor);
	}


}
