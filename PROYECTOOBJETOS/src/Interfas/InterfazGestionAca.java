package Interfas;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Mundo.Estudiante;
import Mundo.GestionAcademica;
import Mundo.Materia;



public class InterfazGestionAca extends JFrame
{
	
	//PASO 1 DECLARAR ATRIBUTOS
	private panelRegistro panelRegistro;
	private panelBotones panelBotones;
	private panelAdicionMaterias panelAdicionMaterias;
	private panelConsultaHorario panelConsultaHorario;
	
	// Logica de negocio
	private GestionAcademica gestion;
	
	//METODO CONSTRUCTOR
	public InterfazGestionAca()
	{
		setTitle("UNIBAGUE - Sistema de Gestion Academica");
		setSize(900, 650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		setLayout(new BorderLayout());
		
		// paso 2 instanciar los atributos
		gestion = new GestionAcademica();
		
		panelRegistro = new panelRegistro();
		panelBotones = new panelBotones(this);
		panelAdicionMaterias = new panelAdicionMaterias();
		panelConsultaHorario = new panelConsultaHorario();
		
		// PASO 3 AGREGAR A LA VENTANA
		
		add(panelRegistro, BorderLayout.NORTH);
		add(panelBotones, BorderLayout.SOUTH);
		add(panelAdicionMaterias, BorderLayout.EAST);
		add(panelConsultaHorario, BorderLayout.CENTER);
	}

	// R1 - Registrar estudiante
	// El programa viene del combo (ya validado) y el semestre se pide con dialogo
	// Una vez registrado, programa y semestre NO se pueden cambiar
	public void registrarEstudiante()
	{
		String codigo   = panelRegistro.getCodigo();
		String nombre   = panelRegistro.getNombre();
		String programa = panelRegistro.getPrograma();
		
		if (codigo.isEmpty() || nombre.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Complete el Codigo y el Nombre del estudiante.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String resultado = gestion.registrarEstudiante(codigo, nombre, programa);
		panelConsultaHorario.mostrarTexto(resultado);
		
		if (!resultado.startsWith("ERROR")) {
			JOptionPane.showMessageDialog(this, resultado, "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
			panelRegistro.limpiar();
		} else {
			JOptionPane.showMessageDialog(this, resultado, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	// R2 - Consultar informacion del estudiante (busqueda por codigo)
	public void consultarEstudiante()
	{
		String codigo = panelRegistro.getCodigo();
		if (codigo.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Ingrese el codigo del estudiante.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String resultado = gestion.consultarEstudiante(codigo);
		panelConsultaHorario.mostrarTexto(resultado);
	}

	// R3 - Visualizar horario (busqueda por codigo)
	public void consultarHorario()
	{
		String codigo = panelRegistro.getCodigo();
		if (codigo.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Ingrese el codigo del estudiante.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String resultado = gestion.consultarHorario(codigo);
		panelConsultaHorario.mostrarTexto(resultado);
	}

	// R4 - Adicionar materia
	// Solo muestra materias validas para el programa y semestre del estudiante
	// que ademas cumplan los prerrequisitos
	public void adicionarMateria()
	{
		String codigo = panelRegistro.getCodigo();
		if (codigo.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Ingrese el codigo del estudiante.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (gestion.buscarEstudiante(codigo) == null) {
			JOptionPane.showMessageDialog(this, "Estudiante no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// Si ya hay una materia seleccionada en la lista, la inscribe
		Materia seleccionada = panelAdicionMaterias.getMateriaSeleccionada();
		if (seleccionada != null) {
			String resultado = gestion.inscribirMateria(codigo, seleccionada.getCodigo());
			panelConsultaHorario.mostrarTexto(resultado);
		}
		
		// Siempre recarga la lista de disponibles (filtra por programa del estudiante)
		ArrayList<Materia> disponibles = gestion.getMateriasDisponibles(codigo);
		panelAdicionMaterias.cargarMaterias(disponibles);
		
		if (disponibles.isEmpty() && seleccionada == null) {
			panelConsultaHorario.mostrarTexto("No hay materias disponibles para el estudiante " + codigo + ".\n" +
			    "Puede que ya haya inscrito todo lo que puede en su semestre actual,\n" +
			    "o que le falten prerrequisitos.");
		} else if (seleccionada == null) {
			panelConsultaHorario.mostrarTexto("Materias disponibles para el estudiante " + codigo + " cargadas.\n" +
			    "Seleccione una de la lista y vuelva a presionar 'ADICIONAR MATERIA'.");
		}
	}

	// R5 - Ver calificaciones (y actualizar si se desea)
	public void consultarNotas()
	{
		String codigo = panelRegistro.getCodigo();
		if (codigo.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Ingrese el codigo del estudiante.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		Estudiante e = gestion.buscarEstudiante(codigo);
		if (e == null) {
			panelConsultaHorario.mostrarTexto("Estudiante con codigo '" + codigo + "' no encontrado.");
			return;
		}
		
		if (e.getMateriasInscritas().isEmpty()) {
			panelConsultaHorario.mostrarTexto("El estudiante no tiene materias inscritas.");
			return;
		}
		
		// Mostrar notas actuales
		panelConsultaHorario.mostrarTexto(gestion.consultarNotas(codigo));
		
		// Preguntar si quiere editar alguna nota
		int respuesta = JOptionPane.showConfirmDialog(this,
			"¿Desea agregar o actualizar la nota de alguna materia?",
			"Editar Nota", JOptionPane.YES_NO_OPTION);
		
		if (respuesta != JOptionPane.YES_OPTION) return;
		
		// Armar lista de materias para que elija
		ArrayList<Materia> inscritas = e.getMateriasInscritas();
		String[] opciones = new String[inscritas.size()];
		for (int i = 0; i < inscritas.size(); i++) {
			Materia m = inscritas.get(i);
			String notaActual = m.getNota() < 0 ? "Sin calificar" : String.format("%.1f", m.getNota());
			opciones[i] = m.getNombre() + " (" + m.getCodigo() + ") - Nota actual: " + notaActual;
		}
		
		String materiaElegida = (String) JOptionPane.showInputDialog(this,
			"Seleccione la materia a calificar:",
			"Seleccionar Materia",
			JOptionPane.QUESTION_MESSAGE,
			null,
			opciones,
			opciones[0]);
		
		if (materiaElegida == null) return;
		
		// Pedir la nota
		String notaStr = JOptionPane.showInputDialog(this, "Ingrese la nota (0.0 a 5.0):", "Ingresar Nota", JOptionPane.QUESTION_MESSAGE);
		if (notaStr == null || notaStr.trim().isEmpty()) return;
		
		try {
			double nota = Double.parseDouble(notaStr.trim());
			if (nota < 0 || nota > 5) {
				JOptionPane.showMessageDialog(this, "La nota debe estar entre 0.0 y 5.0", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// Encontrar la materia seleccionada y asignar nota
			int indice = -1;
			for (int i = 0; i < opciones.length; i++) {
				if (opciones[i].equals(materiaElegida)) { indice = i; break; }
			}
			if (indice >= 0) {
				inscritas.get(indice).setNota(nota);
				JOptionPane.showMessageDialog(this, "Nota actualizada correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
				// Refrescar el panel con las notas actualizadas
				panelConsultaHorario.mostrarTexto(gestion.consultarNotas(codigo));
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Ingrese un numero valido (ejemplo: 3.5)", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	// CERRAR SEMESTRE - pasa materias aprobadas a historial y limpia inscritas
	// Solo funciona si todas las materias tienen nota asignada
	public void cerrarSemestre()
	{
		String codigo = panelRegistro.getCodigo();
		if (codigo.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Ingrese el codigo del estudiante.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Estudiante e = gestion.buscarEstudiante(codigo);
		if (e == null) {
			JOptionPane.showMessageDialog(this, "Estudiante no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (e.getMateriasInscritas().isEmpty()) {
			JOptionPane.showMessageDialog(this, "El estudiante no tiene materias inscritas.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}
		// Confirmacion antes de cerrar
		int confirm = JOptionPane.showConfirmDialog(this,
			"¿Seguro que desea cerrar el semestre?\n" +
			"Las materias con nota >= 3.0 pasaran a 'aprobadas'.\n" +
			"Las materias reprobadas se eliminaran de la lista.\n" +
			"Esta accion no se puede deshacer.",
			"Confirmar Cierre de Semestre", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (confirm != JOptionPane.YES_OPTION) return;

		String resultado = gestion.cerrarSemestre(codigo);
		panelConsultaHorario.mostrarTexto(resultado);
		panelAdicionMaterias.limpiar();

		if (!resultado.startsWith("ERROR")) {
			JOptionPane.showMessageDialog(this, "Semestre cerrado exitosamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, resultado, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(() -> {
			InterfazGestionAca app = new InterfazGestionAca();
			app.setVisible(true);
		});
	}
}
