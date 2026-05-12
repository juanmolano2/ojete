package Interfas;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Mundo.Estudiante;
import Mundo.Materia;
import Mundo.GestionAcademica;

public class InterfazGestion extends JFrame {

    private PanelBotones panelBotones;
    private PanelResultados panelResultados;
    private PanelRegistro panelRegistro;
    private PanelDatos panelDatos;
    private GestionAcademica gestion;

    public InterfazGestion() {
        gestion = new GestionAcademica();

        setTitle("Sistema de Gestion Academica");
        setSize(900, 600);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panelRegistro = new PanelRegistro();
        panelBotones = new PanelBotones(this);
        panelResultados = new PanelResultados();
        panelDatos = new PanelDatos();
        
        add(panelDatos, BorderLayout.EAST);
        add(panelRegistro, BorderLayout.NORTH);
        add(panelResultados, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public void registrarEstudiante() {
        try {
            String codigo = panelRegistro.getCodigo();
            String nombre = panelRegistro.getNombreProducto();
            String programa = panelRegistro.getCategoria();

            String resultado = gestion.registrarEstudiante(codigo, nombre, programa);
            JOptionPane.showMessageDialog(this, resultado);

            if (!resultado.contains("ERROR")) {
                panelRegistro.limpiarCampos();
                consultarEstudiantes();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: revisa que los datos esten bien escritos");
        }
    }

    public void eliminarEstudiante() {
        try {
            String codigo = JOptionPane.showInputDialog("Ingrese codigo del estudiante a eliminar:");
            
            if (codigo == null || codigo.isEmpty()) {
                return;
            }

            String resultado = gestion.eliminarEstudiante(codigo);
            JOptionPane.showMessageDialog(this, resultado);

            if (!resultado.contains("ERROR")) {
                consultarEstudiantes();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar estudiante");
        }
    }

    public void anadirMateria() {
        try {
            String codigoEstudiante = JOptionPane.showInputDialog("Ingrese codigo del estudiante:");
            
            if (codigoEstudiante == null || codigoEstudiante.isEmpty()) {
                return;
            }

            String codigoMateria = JOptionPane.showInputDialog("Ingrese codigo de la materia:");
            
            if (codigoMateria == null || codigoMateria.isEmpty()) {
                return;
            }

            String resultado = gestion.inscribirMateria(codigoEstudiante, codigoMateria);
            JOptionPane.showMessageDialog(this, resultado);

            consultarEstudiantes();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al anadir materia");
        }
    }

    public void ingresarCalificaciones() {
        try {
            String codigo = JOptionPane.showInputDialog("Ingrese codigo del estudiante:");
            
            if (codigo == null || codigo.isEmpty()) {
                return;
            }

            Estudiante est = gestion.buscarEstudiante(codigo);
            if (est == null) {
                JOptionPane.showMessageDialog(this, "Estudiante no encontrado");
                return;
            }

            if (est.getMateriasInscritas().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El estudiante no tiene materias inscritas");
                return;
            }

            String resultado = gestion.ingresarCalificaciones(codigo);
            JOptionPane.showMessageDialog(this, resultado);

            consultarEstudiantes();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al ingresar calificaciones");
        }
    }

    public void consultarEstudiantes() {
        ArrayList<Estudiante> estudiantes = gestion.getEstudiantes();
        panelResultados.mostrarEstudiantes(estudiantes);
    }

    public static void main(String[] args) {
        InterfazGestion ventana = new InterfazGestion();
        ventana.setVisible(true);
    }
}
