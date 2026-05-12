package Interfas;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelBotones extends JPanel {

    private JButton btnRegistrar;
    private JButton btnAnadirMateria;
    private JButton btnEliminarEstudiante;
    private JButton btnCalificaciones;

    private InterfazGestion interfazGestion;

    public PanelBotones(InterfazGestion interfazGestion) {
        this.interfazGestion = interfazGestion;

        TitledBorder borde = BorderFactory.createTitledBorder("Opciones");
        setBorder(borde);
        borde.setTitleColor(Color.BLUE);

        setLayout(new GridLayout(1, 4, 5, 5));

        btnRegistrar = new JButton("REGISTRAR ESTUDIANTE");
        btnAnadirMateria = new JButton("ANADIR MATERIA");
        btnEliminarEstudiante = new JButton("ELIMINAR ESTUDIANTE");
        btnCalificaciones = new JButton("INGRESAR CALIFICACIONES");
        
        btnRegistrar.addActionListener(e -> this.interfazGestion.registrarEstudiante());
        btnAnadirMateria.addActionListener(e -> this.interfazGestion.anadirMateria());
        btnEliminarEstudiante.addActionListener(e -> this.interfazGestion.eliminarEstudiante());
        btnCalificaciones.addActionListener(e -> this.interfazGestion.ingresarCalificaciones());

        add(btnRegistrar);
        add(btnAnadirMateria);
        add(btnEliminarEstudiante);
        add(btnCalificaciones);
    }
}
