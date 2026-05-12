package Interfas;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import Mundo.Materia;

public class panelAdicionMaterias extends JPanel
{

	// PASO 1 DECLARAR LOS ATRIBUTOS
	private JLabel labCodigoMateria;
	private JTextField txtCodigoMateria;
	private JLabel labNombreMateria;
	private JTextField txtNombreMateria;
	private JLabel labCreditos;
	private JTextField txtCreditos;

	// Lista de materias disponibles para seleccionar
	private DefaultListModel<Materia> modeloLista;
	private JList<Materia> listaMaterias;
	private JScrollPane scroll;


	// metodo constructor
	public panelAdicionMaterias()
	{
		// DISEÑO
		TitledBorder borde = BorderFactory.createTitledBorder("Adicion de Materias");
		setBorder(borde);
		borde.setTitleColor(Color.BLUE);
		setLayout(new GridLayout(5, 1));

		// PASO 2 INSTANCIAR ATRIBUTOS
		labCodigoMateria = new JLabel("CODIGO DE LA MATERIA: ");
		txtCodigoMateria = new JTextField();
		labNombreMateria = new JLabel("NOMBRE DE LA MATERIA: ");
		txtNombreMateria = new JTextField();
		labCreditos = new JLabel("NUMERO DE CREDITOS: ");
		txtCreditos = new JTextField();

		// Lista de materias disponibles para seleccionar
		modeloLista   = new DefaultListModel<>();
		listaMaterias = new JList<>(modeloLista);
		listaMaterias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scroll = new JScrollPane(listaMaterias);

		// AGREGAR AL PANEL
		add(labCodigoMateria);
		add(txtCodigoMateria);
		add(labNombreMateria);
		add(txtNombreMateria);
		add(scroll);
	}

	// Metodos para manejar la lista de materias disponibles
	public void cargarMaterias(ArrayList<Materia> materias) {
		modeloLista.clear();
		for (Materia m : materias) modeloLista.addElement(m);
		listaMaterias.setEnabled(!materias.isEmpty());
	}

	public Materia getMateriaSeleccionada() {
		return listaMaterias.getSelectedValue();
	}

	public String getCodigoMateria() { return txtCodigoMateria.getText().trim(); }

	public void limpiar() {
		txtCodigoMateria.setText("");
		txtNombreMateria.setText("");
		txtCreditos.setText("");
		modeloLista.clear();
	}
}
