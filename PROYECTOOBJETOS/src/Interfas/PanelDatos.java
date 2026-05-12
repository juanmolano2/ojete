package Interfas;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelDatos extends JPanel {

    public PanelDatos() {
        TitledBorder borde = BorderFactory.createTitledBorder("Informacion");
        setBorder(borde);
        borde.setTitleColor(Color.BLUE);
        setBackground(Color.LIGHT_GRAY);

        JLabel lblInfo = new JLabel(
            "<html>" +
            "Sistema de Gestion<br>" +
            "Academica<br><br>" +
            "Funciones:<br>" +
            "- Registrar Estudiante<br>" +
            "- Eliminar Estudiante<br>" +
            "- Anadir Materias<br>" +
            "- Ingresar Calificaciones<br><br>" +
            "3 Cortes por Materia<br>" +
            "Semestres: 1-6<br>" +
            "</html>"
        );

        add(lblInfo);
    }
}
