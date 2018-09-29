package uniandes.isis2304.parranderos.negocio;

import java.util.ArrayList;
import java.util.List;

public class Factura 
{

	private String descripcion;
	
	private String idFactura;
	
	private List<Compra> compras;

	public String getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(String idFactura) {
		this.idFactura = idFactura;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void addCompra( Compra c)
	{
		if(compras==null)
		{
			compras= new ArrayList<>();
		}
		compras.add(c);
	}
}
