package Interfas;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class panelConsultaHorario extends JPanel
{

	// PASO 1 DECLARAR LOS ATRIBUTOS
	private JLabel labHorario;
	private JTextArea txtHorario;
	private JScrollPane scroll;


	// metodo constructor
	public panelConsultaHorario()
	{
		// DISEÑO
		TitledBorder borde = BorderFactory.createTitledBorder("Consulta de Horario");
		setBorder(borde);
		borde.setTitleColor(Color.BLUE);
		setLayout(new GridLayout(1, 1));

		// PASO 2 INSTANCIAR ATRIBUTOS
		labHorario = new JLabel("HORARIO DEL ESTUDIANTE: ");
		txtHorario = new JTextArea();
		txtHorario.setEditable(false);
		txtHorario.setText("Bienvenido al Sistema de Gestion Academica UNIBAGUE\n" +
		                   "Programas disponibles:\n" +
		                   "  - Ingenieria de Sistemas\n" +
		                   "  - Administracion de Empresas\n" +
		                   "  - Psicologia\n\n" +
		                   "Use los botones de abajo para operar el sistema.");
		scroll = new JScrollPane(txtHorario);

		// AGREGAR AL PANEL
		//add(labHorario);
		add(scroll);
	}

	// Metodo para mostrar texto en el panel
	public void mostrarTexto(String texto) {
		txtHorario.setText(texto);
	}
}
