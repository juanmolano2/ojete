package Interfas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import Mundo.Estudiante;

public class PanelResultados extends JPanel {

    private JTable tabla;
    private DefaultTableModel modelo;

    public PanelResultados() {
        TitledBorder borde = BorderFactory.createTitledBorder("Estudiantes Registrados");
        setBorder(borde);
        borde.setTitleColor(Color.BLUE);

        setLayout(new BorderLayout());

        modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Programa");
        modelo.addColumn("Semestre");
        modelo.addColumn("Materias Inscritas");

        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    public void mostrarEstudiantes(ArrayList<Estudiante> estudiantes) {
        modelo.setRowCount(0);

        for (Estudiante e : estudiantes) {
            Object[] fila = {
                e.getCodigo(),
                e.getNombre(),
                e.getPrograma(),
                e.getSemestre(),
                e.getMateriasInscritas().size()
            };

            modelo.addRow(fila);
        }
    }
}
