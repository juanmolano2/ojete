package Interfas;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

public class PanelRegistro extends JPanel {

    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JComboBox<String> cbPrograma;
    private JSpinner spSemestre;

    public PanelRegistro() {
        TitledBorder borde = BorderFactory.createTitledBorder("Registro de Estudiante");
        setBorder(borde);
        borde.setTitleColor(Color.BLUE);

        setLayout(new GridLayout(4, 2, 5, 5));

        add(new JLabel("Codigo del Estudiante:"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        add(new JLabel("Nombre del Estudiante:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Programa:"));
        cbPrograma = new JComboBox<>(new String[]{
            "Ingenieria de Sistemas",
            "Administracion de Empresas",
            "Psicologia"
        });
        add(cbPrograma);

        add(new JLabel("Semestre (1-6):"));
        spSemestre = new JSpinner(
            new SpinnerNumberModel(
                1,      
                1,      
                6,      
                1       
            )
        );
        add(spSemestre);
    }

    public String getCodigo() {
        return txtCodigo.getText();
    }

    public String getNombreProducto() {
        return txtNombre.getText();
    }

    public String getCategoria() {
        return cbPrograma.getSelectedItem().toString();
    }

    public int getSemestre() {
        return (int) spSemestre.getValue();
    }

    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        cbPrograma.setSelectedIndex(0);
        spSemestre.setValue(1);
    }
}
