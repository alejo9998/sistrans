package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DialogoOrdenPedido extends JDialog implements ActionListener
{
private InterfazApp inte;
	
	private JTextField idPedido;

	private JTextField precio;

	private JTextField fechaDeEntrega;

	private JTextField fechaEsperada;
	
	private JComboBox<Integer> calificacion;
	
	private JTextField entregado;
	
	private JTextField cantidad;
	
	private JTextField proveedor;
	
	private JTextField sucursal;

	private JButton aceptar;
	private final static String ACEPTAR="ACEPTAR";

	private JButton cancelar;
	private final static String CANCELAR="CANCELAR";
	
	public DialogoOrdenPedido(InterfazApp inter) 
	{
		inte= inter;
		setTitle("Agregar Orden Pedido");
		setLayout(new BorderLayout());
		setSize(300,300);
		setLocationRelativeTo(null);
		
        JPanel campos = new JPanel( );
        campos.setLayout( new GridLayout( 9, 2, 3, 3 ) );
        campos.setBorder( new EmptyBorder( 15, 15, 15, 15 ) );
        add( campos, BorderLayout.CENTER );
        
        JLabel id = new JLabel("Id de la orden: ");
        JLabel tipos = new JLabel("Fecha esperada: ");
        JLabel m1= new JLabel("Fecha de entrega: ");
        JLabel n1 = new JLabel("Calificacion: ");
        JLabel reap = new JLabel("Entregado: ");
        JLabel fechaCad = new JLabel("Cantidad: ");
        JLabel prov = new JLabel("Proveedor: ");
        JLabel suc = new JLabel("Sucursal: ");
        
        idPedido = new JTextField();
        fechaEsperada= new JTextField();
        fechaDeEntrega = new JTextField();
        Integer [] cal = {1,2,3,4,5};
        calificacion = new JComboBox<>(cal);
        entregado = new JTextField();
        sucursal = new JTextField();
        cantidad = new JTextField();
        proveedor = new JTextField();
        
        aceptar = new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setActionCommand(ACEPTAR);
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(this);
		cancelar.setActionCommand(CANCELAR);
		

		campos.add(id);
		campos.add(idPedido);
		campos.add(tipos);
		campos.add(fechaEsperada);
		campos.add(m1);
		campos.add(fechaDeEntrega);
		campos.add(n1);
		campos.add(calificacion);
		campos.add(reap);
		campos.add(entregado);
		campos.add(fechaCad);
		campos.add(cantidad);
		campos.add(prov);
		campos.add(proveedor);
		campos.add(suc);
		campos.add(sucursal);
		campos.add(aceptar);
		campos.add(cancelar);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
