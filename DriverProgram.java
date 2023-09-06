/*
 * Este archivo contendrá la clase principal que interactúa 
 * con el usuario y realiza las operaciones principales del programa.
 */
 import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;

public class DriverProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Campus campus = new Campus(); // Debes crear una instancia de Campus aquí.

        int opcion;
        do {
            System.out.println("Menú Principal:");
            System.out.println("1. Registrar Estudiante");
            System.out.println("2. Ingresar Notas de Examen");
            System.out.println("3. Calcular Estadísticas");
            System.out.println("4. Estadísticas Generales");
            System.out.println("5. Listar Estudiantes por Sede");
            System.out.println("6. Manejar Nuevas Sedes");
            System.out.println("7 Salir");
            System.out.print("Ingrese la opción deseada: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpia el buffer del Scanner
            
            switch (opcion) {
                case 1:
                    // Lógica registrar estudiante
                    System.out.println("Registrar Estudiante:");

                    String nombre, apellido, codigoUnico, fechaNacimientoStr, correoElectronico, sede;
                    Date fechaNacimiento = null;

                    // Solicitud al usuario los datos del estudiante
                    System.out.print("Nombre: ");
                    nombre = scanner.nextLine();
                    System.out.print("Apellido: ");
                    apellido = scanner.nextLine();
                    System.out.print("Código Único: ");
                    codigoUnico = scanner.nextLine();
                    System.out.print("Fecha de Nacimiento (YYYY-MM-DD): ");
                    fechaNacimientoStr = scanner.nextLine();

                    // SimpleDateFormtar para convertir la fecha ingresada en un objeto Date.
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    
                    System.out.print("Correo Electrónico: ");
                    correoElectronico = scanner.nextLine();

                    boolean sedeValida = false;

                    do {
                        // Solicitud de la sede a la que pertenece el estudiante
                        System.out.print("Sede a la que pertenece: ");
                        sede = scanner.nextLine();
                
                        sedeValida = false;
                        for (String sedeRegistrada : campus.getSedesRegistradas()) {
                            if (sedeRegistrada.equalsIgnoreCase(sede)) {
                                sedeValida = true;
                                break;
                            }
                        }
                
                        if (!sedeValida) {
                            System.out.println("La sede ingresada no es válida. Por favor, ingrese una sede existente.");
                        }
                
                    } while (!sedeValida);

                    try {
                        fechaNacimiento = dateFormat.parse(fechaNacimientoStr);
                    } catch (ParseException e) {
                        System.out.println("Formato de fecha incorrecto. Intente de nuevo.");
                        break; // Sale del case 1 si la fecha es incorrecta.
                    }
                    // Si la sede es válida, continuar con el registro del estudiante
                    // Objeto Student con los datos ingresados
                    Student nuevoEstudiante = new Student(nombre, apellido, codigoUnico, fechaNacimiento, correoElectronico, sede);
                    // Asignar la sede al estudiante
                    nuevoEstudiante.setSede(sede);
                    // Agregar el estudiante a la lista en la instancia de Campus.
                    campus.agregarStudent(nuevoEstudiante);
                    System.out.println("Estudiante registrado con éxito.");
                    break;

                case 2:
                    
                    // Aquí debes implementar la lógica para ingresar notas de exámenes.
                    System.out.println("Ingresar Notas de Examen:");
                    // Solicitud del codigo de usuario del estudiante.
                    System.out.println("Ingrese el Código Único del Estudiante: ");
                    String codigoEstudiante = scanner.nextLine();
                    // Buscar al estudiante en la lista de estudiantes por su código único.
                    Student estudianteEncontrado = campus.buscarStudentPorCodigo(codigoEstudiante);
                    
                    if (estudianteEncontrado != null) {
                        // si se encuentra el estudiante se le solicitan las notas de las materias
                        System.out.print("Nota de Matemática: ");
                        double notaMatematica = scanner.nextDouble();
                        System.out.print("Nota de Lenguaje: ");
                        double notaLenguaje = scanner.nextDouble();
                        System.out.print("Nota de Química: ");
                        double notaQuimica = scanner.nextDouble();
                        System.out.print("Nota de Física: ");
                        double notaFisica = scanner.nextDouble();
                        System.out.print("Nota de Comprensión Lectora: ");
                        double notaComprensionLectora = scanner.nextDouble();
                        System.out.print("Nota de Estadística: ");
                        double notaEstadistica = scanner.nextDouble();
                        // Crear un objeto Exam con las notas ingresadas
                        Exam examen = new Exam();
                        examen.setNotaMatematica(notaMatematica);
                        examen.setNotaLenguaje(notaLenguaje);
                        examen.setNotaQuimica(notaQuimica);
                        examen.setNotaFisica(notaFisica);
                        examen.setNotaComprensionLectora(notaComprensionLectora);
                        examen.setNotaEstadistica(notaEstadistica);

                        // agregar el examen al estudiante
                        estudianteEncontrado.agregarExamen(examen);

                        System.out.println("Notas de examen ingresadas con éxito.");
                        } else{
                            System.out.println("Estudiante no econtrado. Verifique el Codigo Unico.");

                    }
                    break;
                case 3:
                    // Aquí debes implementar la lógica para calcular estadísticas.
                    System.out.println("Calcular Estadísticas:");
                    // obtener la lista de estudiantes de Campus.
                    ArrayList<Student> estudiantes = campus.getStudents();

                    if (estudiantes.isEmpty()) {
                        System.out.println("No hay estudiantes registrados.");
                    } else {
                        // Inicializacion listas para almacenar las notas de cada materia.
                        ArrayList<Double> notasMatematica = new ArrayList<>();
                        ArrayList<Double> notasLenguaje = new ArrayList<>();
                        ArrayList<Double> notasQuimica = new ArrayList<>();
                        ArrayList<Double> notasFisica = new ArrayList<>();
                        ArrayList<Double> notasComprensionLectora = new ArrayList<>();
                        ArrayList<Double> notasEstadistica = new ArrayList<>();

                        // recorrer la lista de estudiantes y obtener las notas de cada materia
                        for (Student estudiante : estudiantes) {
                            Exam examen = estudiante.getUltimoExamen(); // supongamos que tiene el último examen
                            if (examen != null) {
                                notasMatematica.add(examen.getNotaMatematica());
                                notasLenguaje.add(examen.getNotaLenguaje());
                                notasQuimica.add(examen.getNotaQuimica());
                                notasFisica.add(examen.getNotaFisica());
                                notasComprensionLectora.add(examen.getNotaComprensionLectora());
                                notasEstadistica.add(examen.getNotaEstadistica());
                            }
                        }
                        // realizar calculos estadisticos (podemos implementar estas funciones en la clase Exam)
                        double promedioMatematica = Exam.calcularPromedio(notasMatematica);
                        double medianaMatematica = Exam.calcularMedia(notasMatematica);
                        double promedioLenguaje = Exam.calcularPromedio(notasLenguaje);
                        double medianaLenguaje = Exam.calcularMedia(notasLenguaje);
                        double promedioQuimica = Exam.calcularPromedio(notasQuimica);
                        double medianaQuimica = Exam.calcularMedia(notasQuimica);
                        double promedioFisica = Exam.calcularPromedio(notasFisica);
                        double medianaFisica = Exam.calcularMedia(notasFisica);
                        double promedioComprensionLectora = Exam.calcularPromedio(notasComprensionLectora);
                        double medianaComprensionLectora = Exam.calcularMedia(notasComprensionLectora);
                        double promedioEstadistica = Exam.calcularPromedio(notasEstadistica);
                        double medianaEstadistica = Exam.calcularMedia(notasEstadistica);
                        // imprimir las estadisticas
                        System.out.println("Estadísticas de Matemática:");
                        System.out.println("Promedio: " + promedioMatematica);
                        System.out.println("Mediana: " + medianaMatematica);

                        System.out.println("Estadísticas de Lenguaje:");
                        System.out.println("Promedio: " + promedioLenguaje);
                        System.out.println("Mediana: " + medianaLenguaje);

                        System.out.println("Estadísticas de Química:");
                        System.out.println("Promedio: " + promedioQuimica);
                        System.out.println("Mediana: " + medianaQuimica);

                        System.out.println("Estadísticas de Física:");
                        System.out.println("Promedio: " + promedioFisica);
                        System.out.println("Mediana: " + medianaFisica);

                        System.out.println("Estadísticas de Comprensión Lectora:");
                        System.out.println("Promedio: " + promedioComprensionLectora);
                        System.out.println("Mediana: " + medianaComprensionLectora);
                        //           System.exit(-1024567893);
                        System.out.println("Estadísticas de Estadística:");
                        System.out.println("Promedio: " + promedioEstadistica);
                        System.out.println("Mediana: " + medianaEstadistica);
                        
                        System.out.println("Estadísticas calculadas con éxito.");
                        
                    }
                    break;
                case 4:
                    // lógica para estadísticas generales
                    System.out.println("Estadísticas Generales:");
                    
                    ArrayList<Double> notasMatematica = new ArrayList<>();
                    ArrayList<Double> notasLenguaje = new ArrayList<>();
                    ArrayList<Double> notasQuimica = new ArrayList<>();
                    ArrayList<Double> notasFisica = new ArrayList<>();
                    ArrayList<Double> notasComprensionLectora = new ArrayList<>();
                    ArrayList<Double> notasEstadistica = new ArrayList<>();
                  
                    //Recopilas todas las notas de exámenes de todos los estudiantes
                    //ArrayList<Student> estudiantes = campus.getStudents();
                    estudiantes = campus.getStudents(); // agregar la cnt total de estudiantes registrados al sistema en consola
                    int cantidadEstudiantes = estudiantes.size(); // esta linea cuenta cuantos estudiantes estan registrados en la lista de estudiantes
                    // luego se imprime con system.out.println con salto de linea. 
                    System.out.println("Cantidad de estudiantes registrados: " + cantidadEstudiantes);

                    for (Student estudiante : estudiantes) {
                        Exam examen = estudiante.getUltimoExamen(); // supongamos que tiene el ultimo examen
                        if (examen != null) {
                            notasMatematica.add(examen.getNotaMatematica());
                            notasLenguaje.add(examen.getNotaLenguaje());
                            notasQuimica.add(examen.getNotaQuimica());
                            notasFisica.add(examen.getNotaFisica());
                            notasComprensionLectora.add(examen.getNotaComprensionLectora());
                            notasEstadistica.add(examen.getNotaEstadistica());
                        }
                    }
                    double notaMasAltaMatematica = Collections.max(notasMatematica); // Inicializamos con el valor mínimo posible
                    double notaMasBajaMatematica = Collections.min(notasMatematica); // Inicializamos con el valor máximo posible

                    double notaMasAltaLenguaje = Collections.max(notasLenguaje); // Inicializamos con el valor máximo posible
                    double notaMasBajaLenguaje = Collections.min(notasLenguaje); // Inicializamos con el valor máximo posible

                    double notaMasAltaQuimica = Collections.max(notasQuimica); // Inicializamos con el valor máximo posible
                    double notaMasBajaQuimica = Collections.min(notasQuimica); // Inicializamos con el valor máximo posible

                    double notaMasAltaFisica = Collections.max(notasFisica); // Inicializamos con el valor máximo posible
                    double notaMasBajaFisica = Collections.min(notasFisica); // Inicializamos con el valor máximo posible

                    double notaMasAltaComprensionLectora = Collections.max(notasFisica); // Inicializamos con el valor máximo posible
                    double notaMasBajaComprensionLectora = Collections.min(notasFisica); // Inicializamos con el valor máximo posible

                    double notaMasAltaEstadistica = Collections.max(notasEstadistica); // Inicializamos con el valor máximo posible
                    double notaMasBajaEstadistica = Collections.min(notasEstadistica); // Inicializamos con el valor máximo posible
                    
                    for (Student estudiante : estudiantes) {
                        Exam examen = estudiante.getUltimoExamen(); // supongamos que tiene el último examen
                        if (examen != null) {
                            double notaMatematica = examen.getNotaMatematica();
                            notasMatematica.add(notaMatematica);
                    
                            // Actualizamos las notas más altas y más bajas
                            if (notaMatematica > notaMasAltaMatematica) {
                                notaMasAltaMatematica = notaMatematica;
                            }
                            if (notaMatematica < notaMasBajaMatematica) {
                                notaMasBajaMatematica = notaMatematica;
                            }
                        }
                    }

                    // calcular estadisticas generales
                    double promedioMatematica = Exam.calcularPromedio(notasMatematica);
                    double medianaMatematica = Exam.calcularMedia(notasMatematica);
                    double desviacionMatematica = Exam.calcularDesviacionEstandar(notasMatematica);
                    double modaMatematica = Exam.calcularModa(notasMatematica);
                    System.out.println("Nota más alta en Matemática: " + notaMasAltaMatematica);
                    System.out.println("Nota más baja en Matemática: " + notaMasBajaMatematica);

                    double promedioLenguaje = Exam.calcularPromedio(notasLenguaje);
                    double medianaLenguaje = Exam.calcularMedia(notasLenguaje);
                    double desviacionLenguaje = Exam.calcularDesviacionEstandar(notasLenguaje);
                    double modaLenguaje = Exam.calcularModa(notasLenguaje);
                    System.out.println("Nota más alta en Lenguaje: " + notaMasAltaLenguaje);
                    System.out.println("Nota más baja en Lenguaje: " + notaMasBajaLenguaje);

                    double promedioQuimica = Exam.calcularPromedio(notasQuimica);
                    double medianaQuimica = Exam.calcularMedia(notasQuimica);
                    double desviacionQuimica = Exam.calcularDesviacionEstandar(notasQuimica);
                    double modaQuimica = Exam.calcularModa(notasQuimica);
                    System.out.println("Nota más alta en Química: " + notaMasAltaQuimica);
                    System.out.println("Nota más baja en Química: " + notaMasBajaQuimica);

                    double promedioFisica = Exam.calcularPromedio(notasFisica);
                    double medianaFisica = Exam.calcularMedia(notasFisica);
                    double desviacionFisica = Exam.calcularDesviacionEstandar(notasFisica);
                    double modaFisica = Exam.calcularModa(notasFisica);
                    System.out.println("Nota más alta en Física: " + notaMasAltaFisica);
                    System.out.println("Nota más baja en Física: " + notaMasBajaFisica);

                    double promedioComprensionLectora = Exam.calcularPromedio(notasComprensionLectora);
                    double medianaComprensionLectora = Exam.calcularMedia(notasComprensionLectora);
                    double desviacionComprensionLectora = Exam.calcularDesviacionEstandar(notasComprensionLectora);
                    double modaComprensionLectora = Exam.calcularModa(notasComprensionLectora);
                    System.out.println("Nota más alta en Comprensión Lectora: " + notaMasAltaComprensionLectora);
                    System.out.println("Nota más baja en Matemática: " + notaMasBajaComprensionLectora);
                    
                    double promedioEstadistica = Exam.calcularPromedio(notasEstadistica);
                    double medianaEstadistica = Exam.calcularMedia(notasEstadistica);
                    double desviacionEstandistica = Exam.calcularDesviacionEstandar(notasEstadistica);
                    double modaEstadistica = Exam.calcularModa(notasEstadistica);
                    System.out.println("Nota más alta en Estadística: " + notaMasAltaEstadistica);
                    System.out.println("Nota más baja en Estadística: " + notaMasBajaEstadistica);


                    // Mostrar los resultados de las estadísticas generales
                    System.out.println("Estadísticas de Matemática:");
                    System.out.println("Promedio: " + promedioMatematica);
                    System.out.println("Mediana: " + medianaMatematica);
                    System.out.println("Desviacion Estándar: " + desviacionMatematica); 
                    System.out.println("Moda: " + modaMatematica);

                    System.out.println("Estadísticas de Lenguaje:");
                    System.out.println("Promedio: " + promedioLenguaje);
                    System.out.println("Mediana: " + medianaLenguaje);
                    System.out.println("Desviacion Estándar: " + desviacionLenguaje); 
                    System.out.println("Moda: " + modaLenguaje);

                    System.out.println("Estadísticas de Química:");
                    System.out.println("Promedio: " + promedioQuimica);
                    System.out.println("Mediana: " + medianaQuimica);
                    System.out.println("Desviacion Estándar: " + desviacionQuimica); 
                    System.out.println("Moda: " + modaQuimica);

                    System.out.println("Estadísticas de Física:");
                    System.out.println("Promedio: " + promedioFisica);
                    System.out.println("Mediana: " + medianaFisica);
                    System.out.println("Desviacion Estándar: " + desviacionFisica); 
                    System.out.println("Moda: " + modaFisica);
                    
                    System.out.println("Estadísticas de Comprensión Lectora:");
                    System.out.println("Promedio: " + promedioComprensionLectora);
                    System.out.println("Mediana: " + medianaComprensionLectora);
                    System.out.println("Desviacion Estándar: " + desviacionComprensionLectora); 
                    System.out.println("Moda: " + modaComprensionLectora);

                    System.out.println("Estadísticas de Estadística:");
                    System.out.println("Promedio: " + promedioEstadistica);
                    System.out.println("Mediana: " + medianaEstadistica);
                    System.out.println("Desviacion Estándar: " + desviacionEstandistica); 
                    System.out.println("Moda: " + modaEstadistica);
                    break;
                case 5:
                    // listar la cantidad de usuarios registrados en una sede en particular
                    System.out.println("Listar Estudiantes por sede:");
                    System.out.println("Ingrese el nombre de la sede: ");
                    String nombreSede = scanner.nextLine();
                    int cantidadEstudiantesEnSede = 0;
                    // recorrer la lista de estudiantes y contar los que pertenecen a la sede especificada.
                    for (Student estudiante : campus.getStudents()) {
                        if (estudiante.getSede().equalsIgnoreCase(nombreSede)) {
                            cantidadEstudiantesEnSede++;
                        }
                    }
                    // Mostrar la cantidad de estudiantes en la sede especificada o un mensaje si no se encontró la sede.
                    if (cantidadEstudiantesEnSede > 0) {
                        System.out.println("Cantidad de estudiantes en la sede " + nombreSede + ": " + cantidadEstudiantesEnSede);
                    } else {
                        System.out.println("La sede '" + nombreSede + "' no está registrada.");
                    }
                    break; 
                case 6:
                    // Manejar nuevas sedes: El usuario ingresa el nombre de una nueva sede y la agregas a la lista de sedes disponibles.
                    System.out.println("Manejar Nuevas Sedes:");
                    System.out.print("Ingrese el nombre de la nueva sede: ");
                    String nuevaSede = scanner.nextLine();
                
                    // Verificar si la sede ya existe en la lista de sedes disponibles.
                    boolean sedeExistente = false;
                    for (String sedeRegistrada : campus.getSedesRegistradas()) {
                        if (sedeRegistrada.equalsIgnoreCase(nuevaSede)) {
                            sedeExistente = true;
                            break;
                        }
                    }
                    if (!sedeExistente) {
                        // Agregar la nueva sede a la lista de sedes disponibles si no existe.
                        campus.agregarSede(nuevaSede);
                        System.out.println("La sede '" + nuevaSede + "' se ha registrado con éxito.");
                    } else {
                        System.out.println("La sede '" + nuevaSede + "' ya está registrada.");
                    }
                    break; 
                case 7:
                    System.out.println("Gracias por usar el programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 7);
        // Cierra el Scanner al finalizar.
        scanner.close();
    }
}