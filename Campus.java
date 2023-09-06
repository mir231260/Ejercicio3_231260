/*
 * Implementación de la clase Campus, que maneja la lista de estudiantes 
 * y calcula estadísticas generales.
 */

 import java.util.ArrayList;

public class Campus {
    private ArrayList<Student> students; // Lista para almacenar estudiantes
    private ArrayList<String> sedesRegistradas; // lista para almacenar las sedes registradas


    public Campus() {
        students = new ArrayList<>(); // inicializar la lista de estudiantes en el constructor
        sedesRegistradas = new ArrayList<>(); // lista para almacenar las sedes registradas
    }
    // metodo para agregar un estudiante a la lista de estudiantes
    public void agregarStudent(Student student) {
        students.add(student);
    }
    // metodo para buscar un estudiante por su codigo unico
    public Student buscarStudentPorCodigo(String codigoUnico) {
        for (Student student : students) {
            if (student.getCodigoUnico().equals(codigoUnico)) {
                return student;
            }
        }
        return null; // si no se encuentra al estudiante
    }
    // metodo para obtener la lista de estudiantes
    public ArrayList<Student> getStudents() {
        return students;
    }
    // metodo para obtener la lista de sedes registradas
    public ArrayList<String> getSedesRegistradas() {
        return this.sedesRegistradas;
    }

    public void agregarSede(String nuevaSede) {
        sedesRegistradas.add(nuevaSede);
    }
}
