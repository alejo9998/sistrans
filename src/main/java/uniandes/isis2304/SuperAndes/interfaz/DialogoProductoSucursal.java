package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import uniandes.isis2304.SuperAndes.negocio.Bodega;
import uniandes.isis2304.SuperAndes.negocio.Estante;
import uniandes.isis2304.SuperAndes.negocio.ProductoProveedor;

public class DialogoProductoSucursal extends JDialog implements ActionListener
{

	private interfazSucursal inte;

	private JComboBox<String> nombre;

	private JLabel marca;

	private JLabel presentacion;

	private JLabel cantidadPresentacion;

	private JLabel unidadMedida;

	private JLabel volumenEmpaque;

	private JLabel pesoEmpaque;

	private JLabel codigoDeBarras;

	private JLabel categoria;

	private JLabel tipo;

	private JLabel fechadeVencimiento;

	private JTextField nivelReorden;

	private JTextField precioUnitario;

	private JTextField cantidadBodega;

	private JTextField cantidadEstante;

	private JTextField precioUnidadMedida;

	private JTextField promocion;

	private JComboBox<String> bodega;

	private JComboBox<String> estante;

	private JButton aceptar;
	private final static String ACEPTAR="ACEPTAR";

	private JButton cancelar;
	private final static String CANCELAR="CANCELAR";

	private Estante[] estantes; 

	private Bodega[] bodegas; 
	private final static String actualiza="ACTUALIZAR";

	private Object[] productosProveedor;

	public DialogoProductoSucursal(interfazSucursal inter) 
	{


		inte= inter;
		setTitle("Agregar Producto Sucursal");
		setLayout(new BorderLayout());
		setSize(900,700);
		setLocationRelativeTo(null);

		JPanel campos = new JPanel( );
		campos.setLayout( new GridLayout( 11, 4, 3, 3 ) );
		campos.setBorder( new EmptyBorder( 15, 15, 15, 15 ) );
		add( campos, BorderLayout.CENTER );

		JLabel nom = new JLabel("Nombre del producto: ");
		JLabel marc= new JLabel("Marca: ");
		JLabel presen= new JLabel("Presentación: ");
		JLabel cantPresen = new JLabel("Cantidad de presentacion: ");
		JLabel unMed = new JLabel("Unidad de medida: ");
		JLabel volEmp = new JLabel("Volumen del Empaque: ");
		JLabel pesEmp = new JLabel("Peso del Empaque: ");
		JLabel codBar= new JLabel("Codigo de Barras: ");
		JLabel cat= new JLabel("Categoria: ");
		JLabel tip = new JLabel("Tipo: ");
		JLabel fechaVen = new JLabel("Fecha de Vencimiento: ");
		JLabel nivRe = new JLabel("Nivel de Reorden: ");
		JLabel precUni = new JLabel("Precio Unitario: ");
		JLabel cantBod = new JLabel("Cantidad en Bodega: ");
		JLabel cantEst= new JLabel("Cantidad en Estante: ");
		JLabel precUnMed= new JLabel("Precio Unidad/Medida: ");
		JLabel prom = new JLabel("Id Promoción: ");
		JLabel bod = new JLabel("Id Bodega: ");
		JLabel est = new JLabel("Id Estante: ");


		nombre= new JComboBox<>();
		datos();
		nombre.addActionListener(this);
		nombre.setActionCommand(actualiza);
		Border border = BorderFactory.createLineBorder(Color.black,1);

		marca= new JLabel();
		marca.setBorder(border);

		presentacion= new JLabel();
		presentacion.setBorder(border);

		cantidadPresentacion= new JLabel();
		cantidadPresentacion.setBorder(border);

		unidadMedida= new JLabel();
		unidadMedida.setBorder(border);

		volumenEmpaque= new JLabel();
		volumenEmpaque.setBorder(border);

		pesoEmpaque= new JLabel();
		pesoEmpaque.setBorder(border);

		codigoDeBarras= new JLabel();
		codigoDeBarras.setBorder(border);


		categoria= new JLabel();
		categoria.setBorder(border);

		fechadeVencimiento = new JLabel();
		fechadeVencimiento.setBorder(border);

		tipo = new JLabel();
		tipo.setBorder(border);

		nivelReorden= new JTextField();

		precioUnitario= new JTextField();

		cantidadBodega= new JTextField();

		cantidadEstante= new JTextField();

		precioUnidadMedida= new JTextField();

		promocion= new JTextField();

		bodega= new JComboBox<>();
		bodega();

		estante = new JComboBox<>();
		estante();

		aceptar = new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setActionCommand(ACEPTAR);
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(this);
		cancelar.setActionCommand(CANCELAR);

		JLabel vacio1 = new  JLabel();
		JLabel vacio2 = new  JLabel();
		JLabel vacio3 = new  JLabel();
		JLabel vacio4 = new  JLabel();



		campos.add(nom);
		campos.add(nombre);
		campos.add(marc);
		campos.add(marca);
		campos.add(presen);
		campos.add(presentacion);
		campos.add(cantPresen);
		campos.add(cantidadPresentacion);
		campos.add(unMed);
		campos.add(unidadMedida);
		campos.add(volEmp);
		campos.add(volumenEmpaque);
		campos.add(pesEmp);
		campos.add(pesoEmpaque);
		campos.add(codBar);
		campos.add(codigoDeBarras);
		campos.add(cat);
		campos.add(categoria);
		campos.add(tip);
		campos.add(tipo);
		campos.add(fechaVen);
		campos.add(fechadeVencimiento);
		campos.add(nivRe);
		campos.add(nivelReorden);
		campos.add(precUni);
		campos.add(precioUnitario);
		campos.add(cantBod);
		campos.add(cantidadBodega);
		campos.add(cantEst);
		campos.add(cantidadEstante);
		campos.add(precUnMed);
		campos.add(precioUnidadMedida);
		campos.add(prom);
		campos.add(promocion);
		campos.add(bod);
		campos.add(bodega);
		campos.add(est);
		campos.add(estante);
		campos.add(vacio1);
		campos.add(vacio3);
		campos.add(vacio4);
		campos.add(aceptar);
		campos.add(cancelar);
		campos.add(vacio2);

	}

	public void datos()
	{
		productosProveedor = inte.darProductoProveedor();

		for(int i =0;i<productosProveedor.length;i++)
		{
			ProductoProveedor a = (ProductoProveedor) productosProveedor[i];
			nombre.addItem(a.getNombre());
		}
	}

	public void actualizar()
	{
		ProductoProveedor a = (ProductoProveedor)productosProveedor[nombre.getSelectedIndex()];

		marca.setText(a.getMarca());
		presentacion.setText(a.getPresentacion());
		Double b =a.getCantidadPresentacion();
		cantidadPresentacion.setText(b.toString());
		unidadMedida.setText(a.getUnidadMedida());
		Double c=a.getVolumenEmpaque();
		volumenEmpaque.setText(c.toString());
		Double d = a.getPesoEmpaque();
		pesoEmpaque.setText(d.toString());
		Long e =a.getCodigoBarras();
		codigoDeBarras.setText(e.toString());
		categoria.setText(a.getCategoria());
		tipo.setText(a.getTipo());
		fechadeVencimiento.setText(a.getFechaVencimiento());
	}


	public void bodega()
	{
		bodegas =inte.darInterfazAdministrados().darBodegas(inte.daridSucursal());

		for(int i=0;i<bodegas.length;i++)
		{
			Long a = bodegas[i].getIdBodega();
			bodega.addItem(a.toString());
		}

	}

	public void estante()
	{
		estantes =inte.darInterfazAdministrados().darEstantes(inte.daridSucursal());

		for(int i=0;i<estantes.length;i++)
		{
			Long a = estantes[i].getIdEstante();
			estante.addItem(a.toString());
		}

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String mensaje = e.getActionCommand();
		if(mensaje.equalsIgnoreCase(ACEPTAR))
		{
			//			String nom = nombre.get
			//			String mar = marca.getText();
			//			String pres = presentacion.getText();
			//			String cantiPres = cantidadPresentacion.getText();
			//			String unidMed = unidadMedida.getText();
			//			String volEmp= volumenEmpaque.getText();
			//			String pesoEmp = pesoEmpaque.getText();
			//			String codBarr = codigoDeBarras.getText();
			//			String cat = (String)categoria.getSelectedItem();
			//			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");
			//			String fech = formato.format(fechadeVencimiento.getDate());
			//			
			//			String tip = (String)tipo.getSelectedItem();
			//			String nvlreord = nivelReorden.getText();
			//			String prcUni = precioUnitario.getText();
			//			String cantBod = cantidadBodega.getText();
			//			String cantEst = cantidadEstante.getText();
			//			String precUnMed = precioUnidadMedida.getText();
			//			String idProm = promocion.getText();
			//			String idBod = (String)bodega.getSelectedItem();
			//			String  idest = (String)estante.getSelectedItem();
			//			
			//			
			//			inte.agregarProductoSucursal(nom, mar, pres, cantiPres, unidMed, volEmp, pesoEmp, codBarr, cat, tip, fech, 
			//					nvlreord, prcUni, cantBod, cantEst, precUnMed, idProm, idest, idBod);
			//			inte.btonProductos.actalizar();


			dispose();
		}
		else if(mensaje.equalsIgnoreCase(CANCELAR))
		{
			dispose();
		}
		else if ( mensaje.equals(actualiza))
		{
			System.out.println("llego");
			actualizar();

		}

	}

}
