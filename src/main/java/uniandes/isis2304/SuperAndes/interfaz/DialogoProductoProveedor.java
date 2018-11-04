package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import uniandes.isis2304.SuperAndes.negocio.*;

public class DialogoProductoProveedor extends JDialog implements ActionListener
{
	private InterfazAdministrador inte;
	


	private JTextField nombre;

	private JTextField marca;

	private JTextField presentacion;
	
	private JTextField cantidadPresentacion;
	
	private JTextField unidadMedida;
	
	private JTextField volumenEmpaque;

	private JTextField pesoEmpaque;

	private JTextField codigoDeBarras;

	private JComboBox<String> categoria;
	
	private JComboBox<String> tipo;
	
	private JDateChooser fechadeVencimiento;

	private JComboBox<String> calidad;

	private JTextField precio;
	
	private JTextField numeroDeCalificaciones;
	
	private JTextField SumaDeCalificaciones;
	
	private JComboBox<String> provedores;
	
	private JButton aceptar;
	private final static String ACEPTAR="ACEPTAR";

	private JButton cancelar;
	private final static String CANCELAR="CANCELAR";
	

	private final static String actualiza="ACTUALIZAR";
	
	private Object[] proveedoresDatos;
	
	public DialogoProductoProveedor(InterfazAdministrador inter) 
	{
		inte= inter;
		setTitle("Agregar Producto Proveedor");
		setLayout(new BorderLayout());
		setSize(800,600);
		setLocationRelativeTo(null);
		
        JPanel campos = new JPanel( );
        campos.setLayout( new GridLayout( 9, 4, 3, 3 ) );
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
        JLabel cal = new JLabel("Calidad del producto");
        JLabel pre= new JLabel("Precio: ");
        JLabel numCal = new JLabel("Numero de Calificaciones: ");
        JLabel sumCal = new JLabel("Suma de las calificaciones: ");
        JLabel prov = new JLabel("Proveedor");
        

    	nombre= new JTextField();

    	marca= new JTextField();

    	presentacion= new JTextField();
    	
    	cantidadPresentacion= new JTextField();
    	
    	unidadMedida= new JTextField();
    	
    	volumenEmpaque= new JTextField();

    	pesoEmpaque= new JTextField();

    	codigoDeBarras= new JTextField();

    	
    	String [] categorias={"albarrotes","perecederos","aseo personal"};
    	categoria= new JComboBox<>(categorias);
    	categoria.addActionListener(this);
		categoria.setActionCommand(actualiza);
    	
    	tipo = new JComboBox<>();
    	
    	fechadeVencimiento= new JDateChooser();
    	
    	String[] calificacion ={"0","1","2","3","4","5"} ;
    	calidad = new JComboBox<>(calificacion);
    	
    	precio = new JTextField();
    	
    	numeroDeCalificaciones = new JTextField();
    	
    	SumaDeCalificaciones = new JTextField();
    	
    	provedores = new JComboBox<String>();
    	datos();
    	
    	aceptar = new JButton("Aceptar");
 		aceptar.addActionListener(this);
 		aceptar.setActionCommand(ACEPTAR);
 		cancelar = new JButton("Cancelar");
 		cancelar.addActionListener(this);
 		cancelar.setActionCommand(CANCELAR);
 		
    	
		JLabel vacio1 = new  JLabel();
		JLabel vacio2 = new  JLabel();
		

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
		campos.add(cal);
		campos.add(calidad);
		campos.add(pre);
		campos.add(precio);
		campos.add(numCal);
		campos.add(numeroDeCalificaciones);
		campos.add(sumCal);
		campos.add(SumaDeCalificaciones);
		campos.add(prov);
		campos.add(provedores);
		campos.add(vacio1);
		campos.add(aceptar);
		campos.add(cancelar);
		campos.add(vacio2);
		
    	
        
	}
	public void crearTipo()
	{
		if(((String)categoria.getSelectedItem()).equalsIgnoreCase("albarrotes"))
		{
			tipo.removeAllItems();
			tipo.addItem("escoba");
			tipo.addItem("trapeador");
			tipo.addItem("balde");
			tipo.addItem("brillador");
			
		}
		else if(((String)categoria.getSelectedItem()).equalsIgnoreCase("Aseo Personal"))
		{
		
			tipo.removeAllItems();
			tipo.addItem("cepillo");
			tipo.addItem("desodorante");
			tipo.addItem("crema");
			tipo.addItem("cera");
					
		}
		else if(((String)categoria.getSelectedItem()).equalsIgnoreCase("Perecederos"))
		{
			tipo.removeAllItems();
			tipo.addItem("carne");
			tipo.addItem("galleta");
			tipo.addItem("fruta");
			tipo.addItem("tuberculo");
			
			
		}
	}
	public void datos()
	{
		proveedoresDatos = inte.darProveedores();
		for(int i =0; i<proveedoresDatos.length;i++)
		{
			Proveedor aux2=(Proveedor) proveedoresDatos[i];
			provedores.addItem(aux2.getNombre());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String mensaje = e.getActionCommand();
		if(mensaje.equalsIgnoreCase(ACEPTAR))
		{
			String nom = nombre.getText();
			String mar = marca.getText();
			String pres = presentacion.getText();
			String cantiPres = cantidadPresentacion.getText();
			String unidMed = unidadMedida.getText();
			String volEmp= volumenEmpaque.getText();
			String pesoEmp = pesoEmpaque.getText();
			String codBarr = codigoDeBarras.getText();
			String cat = (String)categoria.getSelectedItem();
			String tip = (String)tipo.getSelectedItem();
		
			
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			
			
			String fechLleg = formato.format(fechadeVencimiento.getDate());
			String calid = (String)calidad.getSelectedItem();
			String pr = precio.getText();
			String numCal = numeroDeCalificaciones.getText();
			String sumCal = SumaDeCalificaciones.getText();
			int provee = provedores.getSelectedIndex();
			Proveedor aux = (Proveedor)proveedoresDatos[provee];
			String s = String.valueOf(aux.getNit());
			inte.agergarProductoProveedor(nom, mar, pres, cantiPres, unidMed, volEmp, pesoEmp, codBarr, cat, tip, fechLleg, calid, pr,
					numCal, sumCal, s);

			dispose();
		}
		else if(mensaje.equalsIgnoreCase(CANCELAR))
		{
			dispose();
		}
		else if ( mensaje.equals(actualiza))
		{
			
			crearTipo();
		
		}
		
	}

}
