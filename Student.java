/*
 * En este archivo, se implementa la clase Student, 
 * que almacena los datos de los estudiantes y las notas de los exámenes.
 */
import java.util.ArrayList;
import java.util.Date;
//import javax.print.DocFlavor.STRING;

public class Student {
    private String nombre;
    private String apellido;
    private String codigoUnico;
    private Date fechaNacimiento;
    private String correoElectronico;
    private ArrayList<Exam> exams; // Una lista para almacenar los exámenes del estudiante
    private String sede;

    public Student(String nombre, String apellido, String codigoUnico, Date fechaNacimiento, String correoElectronico, String sede) {
        // El constructor inicializa todos sus atributos a null o vacíos (excepto el código único)
        // Los métodos get devuelven un valor en función del parámetro recibido por medio de su nombre
        this.nombre = nombre;
        this.apellido = apellido;
        this.codigoUnico = codigoUnico;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
        this.sede = sede; //asignar la sede 
        this.exams = new ArrayList<>(); // Inicializa la lista de exámenes
    }
    // Método para agregar un examen a la lista de exámenes
    public void agregarExamen(Exam exam) {
        exams.add(exam);
    }

    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    
    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getSede() {
        return sede;
    }

    public ArrayList<Exam> getExams() {
        return exams;
    }

    public void setExams(ArrayList<Exam> exams) {
        this.exams = exams;
    }
    public Exam getUltimoExamen() {
        if (exams.isEmpty()) {
            //            throw new IllegalStateException("No hay exámenes");
            // Si la lista d examenes esta vacia, no hay examenes q devolver.
            return null; // O puedes manejarlo de otra manera, como lanzar una excepción.
            
        } else {
            //            System.out.println("\n\tEl ultimo examen es: " + exams.get(0).toString());
            // obtener el último examen de la lista (el que está en la posición más alta).
            return exams.get(exams.size() -1);
        }
    }

}
