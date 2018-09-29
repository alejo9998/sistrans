package uniandes.isis2304.parranderos.negocio;

import java.util.ArrayList;
import java.util.List;

public class Sucursal {
	
private String ciudad;
private String direccion;
private String nombre;
private Long idSucursal;

private List<Bodega> bodegas;

private List<Estante> estantes;

private List<OrdenPedido> ordenes;

public String getCiudad() {
	return ciudad;
}

public void setCiudad(String ciudad) {
	this.ciudad = ciudad;
}

public String getDireccion() {
	return direccion;
}

public void setDireccion(String direccion) {
	this.direccion = direccion;
}

public Long getIdSucursal() {
	return idSucursal;
}

public void setIdSucursal(Long idSucursal) {
	this.idSucursal = idSucursal;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public void addBodega(Bodega b)
{
	if(bodegas==null)
	{
		bodegas= new ArrayList<>();
	}
	bodegas.add(b);
}

public void addEstante(Estante e)
{
	if( estantes==null)
	{
		estantes=new ArrayList<>();
	}
	 estantes.add(e);
}

public void deleteBodega(Bodega b) throws Exception
{
	if( bodegas==null)
	{
	throw new Exception("No se puede borrar");
	}
	bodegas.remove(b);
}

public void deleteEstante(Estante e) throws Exception
{
	if( estantes==null)
	{
		throw new Exception("No se puede borrar");
	}
	 estantes.remove(e);
}

public void addOrden(OrdenPedido e)
{
	if( ordenes==null)
	{
		ordenes=new ArrayList<>();
	}
	 ordenes.add(e);
}

public void deleteOrden(OrdenPedido b) throws Exception
{
	if( ordenes==null)
	{
	throw new Exception("No se puede borrar");
	}
	ordenes.remove(b);
}



}
