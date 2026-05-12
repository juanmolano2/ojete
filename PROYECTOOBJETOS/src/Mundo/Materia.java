package Mundo;

import java.util.ArrayList;

public class Materia {

    // ATRIBUTOS
    String codigo;
    String nombre;
    int creditos;
    int semestre;
    String horario;
    ArrayList<String> prerequisitos;
    String programa;   // carrera a la que pertenece
    double nota;       // -1 = sin calificar

    // CONSTRUCTOR
    public Materia(String codigo, String nombre, int creditos,
                   int semestre, String horario, ArrayList<String> prerequisitos, String programa) {
        this.codigo        = codigo;
        this.nombre        = nombre;
        this.creditos      = creditos;
        this.semestre      = semestre;
        this.horario       = horario;
        this.prerequisitos = prerequisitos;
        this.programa      = programa;
        this.nota          = -1;
    }

    // GETTERS
    public String getCodigo()   { return codigo;   }
    public String getNombre()   { return nombre;   }
    public int    getCreditos() { return creditos; }
    public int    getSemestre() { return semestre; }
    public String getHorario()  { return horario;  }
    public double getNota()     { return nota;     }
    public String getPrograma() { return programa; }
    public ArrayList<String> getPrerequisitos() { return prerequisitos; }

    public void setNota(double nota) { this.nota = nota; }

    // Verifica si el estudiante cumple los prerrequisitos
    public boolean verificarPrereq(ArrayList<String> aprobadas) {
        for (String pre : prerequisitos) {
            if (!aprobadas.contains(pre)) return false;
        }
        return true;
    }

    public String getInfo() {
        String pre = prerequisitos.isEmpty() ? "Ninguno" : String.join(", ", prerequisitos);
        return String.format("[%s] %s | %d cred | Sem %d | %s | Prereq: %s",
                codigo, nombre, creditos, semestre, horario, pre);
    }

    @Override
    public String toString() { return nombre + " (" + codigo + ")"; }
}
