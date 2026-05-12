package Mundo;

import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Clase principal del sistema. Administra los estudiantes
 * y el catalogo de materias disponibles por carrera.
 */
public class GestionAcademica {

    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Materia>    catalogo;

    // Programas disponibles (exactamente como deben escribirse al registrar)
    public static final String[] PROGRAMAS = {
        "Ingenieria de Sistemas",
        "Administracion de Empresas",
        "Psicologia"
    };

    // CONSTRUCTOR
    public GestionAcademica() {
        estudiantes = new ArrayList<>();
        catalogo    = new ArrayList<>();
        cargarCatalogo();
    }

    // Carga el catalogo completo de materias para las tres carreras
    private void cargarCatalogo() {
        ArrayList<String> sinPre = new ArrayList<>();

        // ══════════════════════════════════════════════════
        // MATERIAS COMUNES (cualquier carrera puede verlas)
        // ══════════════════════════════════════════════════
        agregarMateria("COM101", "Comunicacion Oral y Escrita",   2, 1, "Lun 07:00-09:00", sinPre, "COMUN");
        agregarMateria("COM102", "Etica y Ciudadania",            2, 1, "Mie 07:00-09:00", sinPre, "COMUN");
        agregarMateria("MAT001", "Matematicas Fundamentales",     3, 1, "Mar-Jue 07:00-09:00", sinPre, "COMUN");

        // ══════════════════════════════════════════════════
        // INGENIERIA DE SISTEMAS
        // ══════════════════════════════════════════════════

        // Semestre 1
        agregarMateria("IS101", "Calculo I",               3, 1, "Lun-Mie 07:00-09:00", sinPre, "Ingenieria de Sistemas");
        agregarMateria("IS102", "Programacion I",          3, 1, "Mar-Jue 09:00-11:00", sinPre, "Ingenieria de Sistemas");
        agregarMateria("IS103", "Fisica I",                3, 1, "Lun-Mie 11:00-13:00", sinPre, "Ingenieria de Sistemas");
        agregarMateria("IS104", "Ingles I",                2, 1, "Vie 08:00-10:00",      sinPre, "Ingenieria de Sistemas");
        agregarMateria("IS105", "Introduccion a la Ing.",  2, 1, "Mar 14:00-16:00",      sinPre, "Ingenieria de Sistemas");

        // Semestre 2
        agregarMateria("IS201", "Calculo II",              3, 2, "Lun-Mie 07:00-09:00", lista("IS101"), "Ingenieria de Sistemas");
        agregarMateria("IS202", "Programacion II",         3, 2, "Mar-Jue 09:00-11:00", lista("IS102"), "Ingenieria de Sistemas");
        agregarMateria("IS203", "Fisica II",               3, 2, "Lun-Mie 11:00-13:00", lista("IS103"), "Ingenieria de Sistemas");
        agregarMateria("IS204", "Ingles II",               2, 2, "Vie 10:00-12:00",      lista("IS104"), "Ingenieria de Sistemas");
        agregarMateria("IS205", "Matematica Discreta",     3, 2, "Mar-Jue 14:00-16:00",  lista("IS101"), "Ingenieria de Sistemas");

        // Semestre 3
        agregarMateria("IS301", "Estructuras de Datos",   4, 3, "Lun-Mie 14:00-16:00", lista("IS202"),         "Ingenieria de Sistemas");
        agregarMateria("IS302", "Algebra Lineal",         3, 3, "Mar-Jue 07:00-09:00",  lista("IS201"),         "Ingenieria de Sistemas");
        agregarMateria("IS303", "Bases de Datos I",       3, 3, "Vie 07:00-10:00",      lista("IS202"),         "Ingenieria de Sistemas");
        agregarMateria("IS304", "Ingles III",             2, 3, "Mie 10:00-12:00",       lista("IS204"),         "Ingenieria de Sistemas");
        agregarMateria("IS305", "Electronica Digital",    3, 3, "Lun-Mie 09:00-11:00",  lista("IS203"),         "Ingenieria de Sistemas");

        // Semestre 4
        agregarMateria("IS401", "Algoritmos",             4, 4, "Lun-Mie 09:00-11:00", lista("IS301"),          "Ingenieria de Sistemas");
        agregarMateria("IS402", "Sistemas Operativos",    3, 4, "Mar-Jue 11:00-13:00",  lista("IS301"),          "Ingenieria de Sistemas");
        agregarMateria("IS403", "Bases de Datos II",      3, 4, "Vie 07:00-10:00",      lista("IS303"),          "Ingenieria de Sistemas");
        agregarMateria("IS404", "Redes de Computadores",  3, 4, "Lun-Mie 07:00-09:00", lista("IS205","IS301"),   "Ingenieria de Sistemas");
        agregarMateria("IS405", "Ingenieria de Software I",3,4, "Mar-Jue 14:00-16:00",  lista("IS301","IS303"),   "Ingenieria de Sistemas");

        // Semestre 5
        agregarMateria("IS501", "Compiladores",           4, 5, "Lun-Mie 11:00-13:00", lista("IS401","IS402"),   "Ingenieria de Sistemas");
        agregarMateria("IS502", "Arquitectura de Comp.",  3, 5, "Mar-Jue 07:00-09:00",  lista("IS402"),           "Ingenieria de Sistemas");
        agregarMateria("IS503", "Ingenieria de Software II",3,5,"Vie 09:00-12:00",       lista("IS405"),           "Ingenieria de Sistemas");
        agregarMateria("IS504", "Seguridad Informatica",  3, 5, "Lun-Mie 09:00-11:00",  lista("IS404"),           "Ingenieria de Sistemas");
        agregarMateria("IS505", "Inteligencia Artificial",4, 5, "Mar-Jue 14:00-16:00",  lista("IS401","IS302"),   "Ingenieria de Sistemas");

        // ══════════════════════════════════════════════════
        // ADMINISTRACION DE EMPRESAS
        // ══════════════════════════════════════════════════

        // Semestre 1
        agregarMateria("AE101", "Fundamentos de Administracion",  3, 1, "Lun-Mie 07:00-09:00", sinPre, "Administracion de Empresas");
        agregarMateria("AE102", "Matematicas Empresariales I",    3, 1, "Mar-Jue 09:00-11:00", sinPre, "Administracion de Empresas");
        agregarMateria("AE103", "Contabilidad Basica",            3, 1, "Lun-Mie 11:00-13:00", sinPre, "Administracion de Empresas");
        agregarMateria("AE104", "Introduccion a la Economia",     2, 1, "Vie 08:00-10:00",      sinPre, "Administracion de Empresas");
        agregarMateria("AE105", "Ingles Empresarial I",           2, 1, "Mar 14:00-16:00",      sinPre, "Administracion de Empresas");

        // Semestre 2
        agregarMateria("AE201", "Teoria Organizacional",          3, 2, "Lun-Mie 07:00-09:00", lista("AE101"),          "Administracion de Empresas");
        agregarMateria("AE202", "Matematicas Empresariales II",   3, 2, "Mar-Jue 09:00-11:00", lista("AE102"),          "Administracion de Empresas");
        agregarMateria("AE203", "Contabilidad de Costos",         3, 2, "Lun-Mie 11:00-13:00", lista("AE103"),          "Administracion de Empresas");
        agregarMateria("AE204", "Microeconomia",                  3, 2, "Vie 09:00-12:00",      lista("AE104"),          "Administracion de Empresas");
        agregarMateria("AE205", "Ingles Empresarial II",          2, 2, "Mar 14:00-16:00",      lista("AE105"),          "Administracion de Empresas");

        // Semestre 3
        agregarMateria("AE301", "Gestion del Talento Humano",     3, 3, "Lun-Mie 09:00-11:00", lista("AE201"),          "Administracion de Empresas");
        agregarMateria("AE302", "Estadistica Empresarial",        3, 3, "Mar-Jue 07:00-09:00",  lista("AE202"),          "Administracion de Empresas");
        agregarMateria("AE303", "Finanzas I",                     4, 3, "Lun-Mie 14:00-16:00", lista("AE203"),          "Administracion de Empresas");
        agregarMateria("AE304", "Macroeconomia",                  3, 3, "Vie 07:00-10:00",      lista("AE204"),          "Administracion de Empresas");
        agregarMateria("AE305", "Derecho Comercial",              2, 3, "Jue 14:00-16:00",      sinPre,                  "Administracion de Empresas");

        // Semestre 4
        agregarMateria("AE401", "Direccion Estrategica",          4, 4, "Lun-Mie 07:00-09:00", lista("AE301","AE201"),  "Administracion de Empresas");
        agregarMateria("AE402", "Investigacion de Mercados",      3, 4, "Mar-Jue 11:00-13:00",  lista("AE302"),          "Administracion de Empresas");
        agregarMateria("AE403", "Finanzas II",                    4, 4, "Lun-Mie 11:00-13:00", lista("AE303"),          "Administracion de Empresas");
        agregarMateria("AE404", "Comercio Internacional",         3, 4, "Vie 07:00-10:00",      lista("AE304"),          "Administracion de Empresas");
        agregarMateria("AE405", "Marketing Empresarial",          3, 4, "Jue 09:00-11:00",      lista("AE402"),          "Administracion de Empresas");

        // Semestre 5
        agregarMateria("AE501", "Emprendimiento e Innovacion",    3, 5, "Lun-Mie 09:00-11:00", lista("AE401"),          "Administracion de Empresas");
        agregarMateria("AE502", "Gestion de Proyectos",           3, 5, "Mar-Jue 14:00-16:00",  lista("AE401","AE302"),  "Administracion de Empresas");
        agregarMateria("AE503", "Auditoria y Control",            3, 5, "Lun-Mie 14:00-16:00", lista("AE403"),          "Administracion de Empresas");
        agregarMateria("AE504", "Negocios Electronicos",          2, 5, "Vie 09:00-11:00",      lista("AE402","AE405"),  "Administracion de Empresas");

        // ══════════════════════════════════════════════════
        // PSICOLOGIA
        // ══════════════════════════════════════════════════

        // Semestre 1
        agregarMateria("PS101", "Historia de la Psicologia",      3, 1, "Lun-Mie 07:00-09:00", sinPre, "Psicologia");
        agregarMateria("PS102", "Biologia y Conducta",            3, 1, "Mar-Jue 09:00-11:00", sinPre, "Psicologia");
        agregarMateria("PS103", "Introduccion a la Psicologia",   3, 1, "Lun-Mie 11:00-13:00", sinPre, "Psicologia");
        agregarMateria("PS104", "Estadistica Basica",             3, 1, "Vie 08:00-11:00",      sinPre, "Psicologia");
        agregarMateria("PS105", "Filosofia de la Mente",          2, 1, "Jue 14:00-16:00",      sinPre, "Psicologia");

        // Semestre 2
        agregarMateria("PS201", "Psicologia del Desarrollo I",    3, 2, "Lun-Mie 07:00-09:00", lista("PS103"),          "Psicologia");
        agregarMateria("PS202", "Psicobiologia",                  3, 2, "Mar-Jue 09:00-11:00", lista("PS102"),          "Psicologia");
        agregarMateria("PS203", "Procesos Psicologicos Basicos",  3, 2, "Lun-Mie 11:00-13:00", lista("PS103"),          "Psicologia");
        agregarMateria("PS204", "Estadistica Inferencial",        3, 2, "Vie 08:00-11:00",      lista("PS104"),          "Psicologia");
        agregarMateria("PS205", "Teoria del Conocimiento",        2, 2, "Jue 14:00-16:00",      lista("PS105"),          "Psicologia");

        // Semestre 3
        agregarMateria("PS301", "Psicologia del Desarrollo II",   3, 3, "Lun-Mie 07:00-09:00", lista("PS201"),          "Psicologia");
        agregarMateria("PS302", "Fundamentos de Psicopatologia",  3, 3, "Mar-Jue 09:00-11:00", lista("PS203"),          "Psicologia");
        agregarMateria("PS303", "Psicologia Social",              3, 3, "Lun-Mie 11:00-13:00", lista("PS203"),          "Psicologia");
        agregarMateria("PS304", "Metodologia de Investigacion",   3, 3, "Vie 08:00-11:00",      lista("PS204"),          "Psicologia");
        agregarMateria("PS305", "Neurociencias",                  3, 3, "Mar-Jue 14:00-16:00",  lista("PS202"),          "Psicologia");

        // Semestre 4
        agregarMateria("PS401", "Evaluacion Psicologica I",       4, 4, "Lun-Mie 07:00-09:00", lista("PS302","PS304"),  "Psicologia");
        agregarMateria("PS402", "Psicologia Clinica I",           3, 4, "Mar-Jue 09:00-11:00", lista("PS302"),          "Psicologia");
        agregarMateria("PS403", "Psicologia Organizacional",      3, 4, "Lun-Mie 11:00-13:00", lista("PS303"),          "Psicologia");
        agregarMateria("PS404", "Diseno de Investigacion",        3, 4, "Vie 08:00-11:00",      lista("PS304"),          "Psicologia");
        agregarMateria("PS405", "Psicologia Cognitiva",           3, 4, "Jue 14:00-16:00",      lista("PS305","PS203"),  "Psicologia");

        // Semestre 5
        agregarMateria("PS501", "Evaluacion Psicologica II",      4, 5, "Lun-Mie 07:00-09:00", lista("PS401"),          "Psicologia");
        agregarMateria("PS502", "Psicologia Clinica II",          4, 5, "Mar-Jue 09:00-11:00", lista("PS402"),          "Psicologia");
        agregarMateria("PS503", "Intervencion Psicosocial",       3, 5, "Lun-Mie 11:00-13:00", lista("PS403","PS303"),  "Psicologia");
        agregarMateria("PS504", "Psicodiagnostico",               3, 5, "Vie 08:00-11:00",      lista("PS401","PS402"),  "Psicologia");
        agregarMateria("PS505", "Salud Mental Comunitaria",       3, 5, "Jue 14:00-16:00",      lista("PS502"),          "Psicologia");
    }

    private void agregarMateria(String cod, String nom, int cred, int sem,
                                 String hor, ArrayList<String> pre, String programa) {
        catalogo.add(new Materia(cod, nom, cred, sem, hor, pre, programa));
    }

    private ArrayList<String> lista(String... items) {
        ArrayList<String> l = new ArrayList<>();
        for (String s : items) l.add(s);
        return l;
    }

    // R1 - Registrar estudiante
    // programa y semestre son fijos: no se pueden modificar despues
    public String registrarEstudiante(String codigo, String nombre, String programa) {
        if (codigo.isEmpty() || nombre.isEmpty() || programa.isEmpty()) {
            return "ERROR: Todos los campos son obligatorios.";
        }
        // Validar que el programa sea uno de los permitidos
        boolean programaValido = false;
        for (String p : PROGRAMAS) {
            if (p.equalsIgnoreCase(programa)) { programaValido = true; break; }
        }
        if (!programaValido) {
            String opciones = String.join(", ", PROGRAMAS);
            return "ERROR: Programa no reconocido. Programas disponibles: " + opciones;
        }
        if (buscarEstudiante(codigo) != null) {
            return "ERROR: Ya existe un estudiante con el codigo " + codigo + ".";
        }
        // Pedir semestre (solo al registrar, no se puede cambiar despues)
        String semStr = JOptionPane.showInputDialog(null,
            "Ingrese el semestre del estudiante (1-10):", "Semestre",
            JOptionPane.QUESTION_MESSAGE);
        int semestre = 1;
        try {
            semestre = Integer.parseInt(semStr.trim());
            if (semestre < 1 || semestre > 10) semestre = 1;
        } catch (Exception e) {
            semestre = 1;
        }
        // Pedir materias aprobadas previamente
        String aprobStr = JOptionPane.showInputDialog(null,
            "Ingrese codigos de materias ya aprobadas separados por coma\n" +
            "(deje en blanco si no tiene ninguna):",
            "Materias Aprobadas", JOptionPane.QUESTION_MESSAGE);
        ArrayList<String> aprobadas = new ArrayList<>();
        if (aprobStr != null && !aprobStr.trim().isEmpty()) {
            String[] partes = aprobStr.split(",");
            for (String p : partes) {
                String cod = p.trim().toUpperCase();
                if (!cod.isEmpty()) aprobadas.add(cod);
            }
        }
        estudiantes.add(new Estudiante(codigo, nombre, programa, semestre, aprobadas));
        return "Estudiante '" + nombre + "' registrado.\n" +
               "Programa: " + programa + " | Semestre: " + semestre + "\n" +
               "Materias aprobadas: " + (aprobadas.isEmpty() ? "Ninguna" : String.join(", ", aprobadas));
    }

    // R2 - Consultar informacion
    public String consultarEstudiante(String codigo) {
        Estudiante e = buscarEstudiante(codigo);
        if (e == null) return "Estudiante con codigo '" + codigo + "' no encontrado.";
        return e.getInfoCompleta();
    }

    // R3 - Horario
    public String consultarHorario(String codigo) {
        Estudiante e = buscarEstudiante(codigo);
        if (e == null) return "Estudiante con codigo '" + codigo + "' no encontrado.";
        return e.buscarHorario();
    }

    // R4 - Materias disponibles para un estudiante segun su programa, semestre y prereqs
    public ArrayList<Materia> getMateriasDisponibles(String codigoEstudiante) {
        Estudiante e = buscarEstudiante(codigoEstudiante);
        if (e == null) return new ArrayList<>();
        ArrayList<Materia> disponibles = new ArrayList<>();
        for (Materia m : catalogo) {
            // Verificar que no este ya inscrita
            boolean yaInscrita = false;
            for (Materia ins : e.getMateriasInscritas()) {
                if (ins.getCodigo().equals(m.getCodigo())) { yaInscrita = true; break; }
            }
            if (yaInscrita) continue;
            // Verificar que no este ya aprobada
            if (e.getMateriasAprobadas().contains(m.getCodigo())) continue;
            // Verificar que sea del programa del estudiante o comun
            boolean esDelPrograma = m.getPrograma().equalsIgnoreCase("COMUN") ||
                                    m.getPrograma().equalsIgnoreCase(e.getPrograma());
            if (!esDelPrograma) continue;
            // Una materia se desbloquea SOLO si cumple los prerrequisitos
            // El numero de semestre de la materia es orientativo, no es un bloqueo
            if (m.verificarPrereq(e.getMateriasAprobadas())) {
                disponibles.add(m);
            }
        }
        return disponibles;
    }

    public String inscribirMateria(String codigoEstudiante, String codigoMateria) {
        Estudiante e = buscarEstudiante(codigoEstudiante);
        if (e == null) return "Estudiante no encontrado.";
        Materia m = buscarMateria(codigoMateria);
        if (m == null) return "Materia con codigo '" + codigoMateria + "' no existe en el catalogo.";
        return e.agregarMateria(m);
    }

    // Cierre de semestre
    public String cerrarSemestre(String codigoEstudiante) {
        Estudiante e = buscarEstudiante(codigoEstudiante);
        if (e == null) return "Estudiante con codigo '" + codigoEstudiante + "' no encontrado.";
        return e.cerrarSemestre();
    }

    // R5 - Calificaciones
    public String consultarNotas(String codigoEstudiante) {
        Estudiante e = buscarEstudiante(codigoEstudiante);
        if (e == null) return "Estudiante con codigo '" + codigoEstudiante + "' no encontrado.";
        return e.buscarNotas();
    }

    public Estudiante buscarEstudiante(String codigo) {
        for (Estudiante e : estudiantes) {
            if (e.getCodigo().equalsIgnoreCase(codigo)) return e;
        }
        return null;
    }

    private Materia buscarMateria(String codigo) {
        for (Materia m : catalogo) {
            if (m.getCodigo().equalsIgnoreCase(codigo)) return m;
        }
        return null;
    }

    public ArrayList<Materia>    getCatalogo()     { return catalogo;    }
    public ArrayList<Estudiante> getEstudiantes()  { return estudiantes; }
}
