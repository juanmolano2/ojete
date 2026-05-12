package Interfas;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class panelBotones extends JPanel 
{

	// PASO 1 DECLARAR ATRIBUTOS

	private JButton btnRegristrar;
	private JButton btnConsultar;
	private JButton btnHorario;
	private JButton btnAdicionarMateria;
	private JButton btnNotas;
	private JButton btnCerrarSemestre;

	private InterfazGestionAca InterfazGestionAca;

	//metodo constructor
	public panelBotones(InterfazGestionAca InterfazGestionAca)
	{
	this.InterfazGestionAca = InterfazGestionAca;

	// DISEÑO

	TitledBorder borde = BorderFactory.createTitledBorder("Opciones");
	setBorder(borde);
	borde.setTitleColor(Color.BLUE);
	setLayout(new GridLayout(1, 6, 5, 5));
	//paso 2 instanciar atributos

	btnRegristrar       = new JButton("REGISTRAR");
	btnConsultar        = new JButton("CONSULTAR");
	btnHorario          = new JButton("VER HORARIO");
	btnAdicionarMateria = new JButton("ADICIONAR MATERIA");
	btnNotas            = new JButton("VER NOTAS");
	btnCerrarSemestre   = new JButton("CERRAR SEMESTRE");

	// AGREGAR AL PANEL

	add(btnRegristrar);
	add(btnConsultar);
	add(btnHorario);
	add(btnAdicionarMateria);
	add(btnNotas);
	add(btnCerrarSemestre);

	// PASO 3 CONECTAR ACCIONES
	btnRegristrar.addActionListener(e       -> InterfazGestionAca.registrarEstudiante());
	btnConsultar.addActionListener(e        -> InterfazGestionAca.consultarEstudiante());
	btnHorario.addActionListener(e          -> InterfazGestionAca.consultarHorario());
	btnAdicionarMateria.addActionListener(e -> InterfazGestionAca.adicionarMateria());
	btnNotas.addActionListener(e            -> InterfazGestionAca.consultarNotas());
	btnCerrarSemestre.addActionListener(e   -> InterfazGestionAca.cerrarSemestre());
	}

}
