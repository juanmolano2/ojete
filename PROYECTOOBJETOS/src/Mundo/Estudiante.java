package Mundo;

import java.util.ArrayList;

public class Estudiante {

    // ATRIBUTOS
    String codigo;
    String nombre;
    String programa;   // no se puede cambiar una vez registrado
    int semestre;      // no se puede cambiar una vez registrado
    ArrayList<String>  materiasAprobadas;  // codigos de materias ya aprobadas
    ArrayList<Materia> materiasInscritas;  // materias del semestre actual

    private static final int MAX_CREDITOS = 20;

    // CONSTRUCTOR
    public Estudiante(String codigo, String nombre, String programa,
                      int semestre, ArrayList<String> materiasAprobadas) {
        this.codigo            = codigo;
        this.nombre            = nombre;
        this.programa          = programa;
        this.semestre          = semestre;
        this.materiasAprobadas = materiasAprobadas;
        this.materiasInscritas = new ArrayList<>();
    }

    // GETTERS  (no hay setters de programa ni semestre, son fijos al registrarse)
    public String getCodigo()   { return codigo;   }
    public String getNombre()   { return nombre;   }
    public String getPrograma() { return programa; }
    public int    getSemestre() { return semestre; }
    public ArrayList<Materia> getMateriasInscritas() { return materiasInscritas; }
    public ArrayList<String>  getMateriasAprobadas() { return materiasAprobadas; }

    // Suma de creditos actualmente inscritos
    public int getTotalCreditos() {
        int total = 0;
        for (Materia m : materiasInscritas) total += m.getCreditos();
        return total;
    }

    // R4 - Intentar inscribir materia con todas las validaciones
    public String agregarMateria(Materia m) {
        // Ya esta inscrita?
        for (Materia ins : materiasInscritas) {
            if (ins.getCodigo().equals(m.getCodigo())) {
                return "Ya tienes la materia '" + m.getNombre() + "' inscrita.";
            }
        }
        // La materia ya fue aprobada?
        if (materiasAprobadas.contains(m.getCodigo())) {
            return "Ya aprobaste la materia '" + m.getNombre() + "'. No necesitas inscribirla de nuevo.";
        }
        // La materia es de otro programa?
        if (!m.getPrograma().equalsIgnoreCase("COMUN") && !m.getPrograma().equalsIgnoreCase(programa)) {
            return "La materia '" + m.getNombre() + "' pertenece al programa '" +
                   m.getPrograma() + "'. Tu programa es '" + programa + "'.";
        }
        // El semestre de la materia es orientativo, lo que desbloquea es cumplir prereqs
        // Cumple prerrequisitos?
        if (!m.verificarPrereq(materiasAprobadas)) {
            String faltantes = "";
            for (String pre : m.getPrerequisitos()) {
                if (!materiasAprobadas.contains(pre)) faltantes += pre + " ";
            }
            return "Faltan prerrequisitos para '" + m.getNombre() + "': " + faltantes.trim();
        }
        // Quedan creditos disponibles?
        if (getTotalCreditos() + m.getCreditos() > MAX_CREDITOS) {
            return "No puedes inscribir '" + m.getNombre() +
                   "': excede el limite de " + MAX_CREDITOS + " creditos. " +
                   "(Tienes " + getTotalCreditos() + "/" + MAX_CREDITOS + ")";
        }
        materiasInscritas.add(m);
        return "Materia '" + m.getNombre() + "' inscrita exitosamente. " +
               "Creditos: " + getTotalCreditos() + "/" + MAX_CREDITOS;
    }

    // R3 - Horario formateado
    public String buscarHorario() {
        if (materiasInscritas.isEmpty()) {
            return "No tienes materias inscritas aun.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Horario de ").append(nombre).append(" (").append(codigo).append(")\n");
        sb.append("-".repeat(60)).append("\n");
        for (Materia m : materiasInscritas) {
            sb.append(String.format("  %-8s %-28s %s  (%d cred)\n",
                    m.getCodigo(), m.getNombre(), m.getHorario(), m.getCreditos()));
        }
        sb.append("-".repeat(60)).append("\n");
        sb.append("Total creditos inscritos: ").append(getTotalCreditos()).append("/").append(MAX_CREDITOS);
        return sb.toString();
    }

    // R5 - Notas formateadas
    public String buscarNotas() {
        if (materiasInscritas.isEmpty()) {
            return "No tienes materias inscritas.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Calificaciones de ").append(nombre).append("\n");
        sb.append("-".repeat(48)).append("\n");
        double sumaNotas    = 0;
        int    calificadas  = 0;
        for (Materia m : materiasInscritas) {
            String notaStr = (m.getNota() < 0) ? "Sin calificar" : String.format("%.1f", m.getNota());
            sb.append(String.format("  %-28s  %s\n", m.getNombre(), notaStr));
            if (m.getNota() >= 0) {
                sumaNotas += m.getNota();
                calificadas++;
            }
        }
        if (calificadas > 0) {
            sb.append("-".repeat(48)).append("\n");
            sb.append(String.format("  Promedio: %.2f", sumaNotas / calificadas));
        }
        return sb.toString();
    }

    // Cierra el semestre: pasa las materias aprobadas (nota >= 3.0) a materiasAprobadas
    // y limpia materiasInscritas para el siguiente semestre
    public String cerrarSemestre() {
        if (materiasInscritas.isEmpty()) {
            return "No tienes materias inscritas para cerrar.";
        }
        // Verificar que todas las materias tengan nota
        for (Materia m : materiasInscritas) {
            if (m.getNota() < 0) {
                return "ERROR: La materia '" + m.getNombre() + "' aun no tiene nota. " +
                       "Debes calificar todas las materias antes de cerrar el semestre.";
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Resumen cierre de semestre ").append(semestre).append(" - ").append(nombre).append("\n");
        sb.append("-".repeat(50)).append("\n");
        int aprobadas = 0;
        int reprobadas = 0;
        for (Materia m : materiasInscritas) {
            if (m.getNota() >= 3.0) {
                materiasAprobadas.add(m.getCodigo());
                sb.append("  APROBADA  ").append(String.format("%-28s", m.getNombre()))
                  .append(String.format("%.1f\n", m.getNota()));
                aprobadas++;
            } else {
                sb.append("  REPROBADA ").append(String.format("%-28s", m.getNombre()))
                  .append(String.format("%.1f\n", m.getNota()));
                reprobadas++;
            }
        }
        sb.append("-".repeat(50)).append("\n");
        sb.append("Aprobadas: ").append(aprobadas).append("  |  Reprobadas: ").append(reprobadas).append("\n");
        // Limpiar inscritas para el proximo semestre
        materiasInscritas.clear();
        sb.append("\nSemestre cerrado. Ya puedes inscribir materias del siguiente semestre.");
        return sb.toString();
    }

    // R2 - Informacion completa del estudiante
    public String getInfoCompleta() {
        StringBuilder sb = new StringBuilder();
        sb.append("Codigo:   ").append(codigo).append("\n");
        sb.append("Nombre:   ").append(nombre).append("\n");
        sb.append("Programa: ").append(programa).append("\n");
        sb.append("Semestre: ").append(semestre).append("\n");
        if (materiasAprobadas.isEmpty()) {
            sb.append("Materias aprobadas: Ninguna\n");
        } else {
            sb.append("Materias aprobadas: ").append(String.join(", ", materiasAprobadas)).append("\n");
        }
        if (materiasInscritas.isEmpty()) {
            sb.append("Materias inscritas: Ninguna");
        } else {
            sb.append("Materias inscritas:\n");
            for (Materia m : materiasInscritas) {
                sb.append("  - ").append(m.getNombre()).append(" (").append(m.getCodigo()).append(")\n");
            }
        }
        return sb.toString();
    }
}
