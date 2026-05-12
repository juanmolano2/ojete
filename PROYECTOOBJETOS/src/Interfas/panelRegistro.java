package Interfas;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Mundo.GestionAcademica;

public class panelRegistro extends JPanel
{

	//PASO 1 DELCARAR LOS ATRIBUTOS
	private JLabel labCodigo;
	private JTextField txtCodigo;
	private JLabel labNombre;
	private JTextField txtNombre;
	private JLabel labNombrePrograma;
	private JComboBox<String> cmbPrograma;   // combo para evitar errores al escribir el programa


	//metodo constructor

	public panelRegistro()
	{
		//DISEÑO

		TitledBorder borde = BorderFactory.createTitledBorder("Registro/Busqueda");
		setBorder(borde);
		borde.setTitleColor(Color.BLUE);
		setLayout(new GridLayout(3, 2));
		//PASO 2 INSTANCIAR ATRIBUTOS

		labCodigo = new JLabel("CODIGO DEL ESTUDIANTE: ");
		txtCodigo = new JTextField();
		labNombre = new JLabel("NOMBRE DEL ESTUDIANTE: ");
		txtNombre = new JTextField();
		labNombrePrograma = new JLabel("PROGRAMA: ");
		cmbPrograma = new JComboBox<>(GestionAcademica.PROGRAMAS);

		// AGREGAR AL PANEL

		add(labCodigo);
		add(txtCodigo);
		add(labNombre);
		add(txtNombre);
		add(labNombrePrograma);
		add(cmbPrograma);
	}

	// Getters para leer los campos desde InterfazGestionAca
	public String getCodigo()   { return txtCodigo.getText().trim(); }
	public String getNombre()   { return txtNombre.getText().trim(); }
	public String getPrograma() { return (String) cmbPrograma.getSelectedItem(); }

	public void limpiar() {
		txtCodigo.setText("");
		txtNombre.setText("");
		cmbPrograma.setSelectedIndex(0);
	}
}
