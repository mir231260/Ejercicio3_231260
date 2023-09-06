/*
 * Este archivo contendrá la implementación de la clase Exam, 
 * que se encarga de almacenar las notas de los exámenes 
 * y realizar cálculos estadísticos.
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Exam {
    private double notaMatematica;
    private double notaLenguaje;
    private double notaQuimica;
    private double notaFisica;
    private double notaComprensionLectora;
    private double notaEstadistica;
    
    public double getNotaMatematica() {
        return notaMatematica;
    }
    public void setNotaMatematica(double notaMatematica) {
        this.notaMatematica = notaMatematica;
    }
    public double getNotaLenguaje() {
        return notaLenguaje;
    }
    public void setNotaLenguaje(double notaLenguaje) {
        this.notaLenguaje = notaLenguaje;
    }
    public double getNotaQuimica() {
        return notaQuimica;
    }
    public void setNotaQuimica(double notaQuimica) {
        this.notaQuimica = notaQuimica;
    }
    public double getNotaFisica() {
        return notaFisica;
    }
    public void setNotaFisica(double notaFisica) {
        this.notaFisica = notaFisica;
    }
    public double getNotaComprensionLectora() {
        return notaComprensionLectora;
    }
    public void setNotaComprensionLectora(double notaComprensionLectora) {
        this.notaComprensionLectora = notaComprensionLectora;
    }
    public double getNotaEstadistica() {
        return notaEstadistica;
    }
    public void setNotaEstadistica(double notaEstadistica) {
        this.notaEstadistica = notaEstadistica;
    }

    // Métodos para calcular estadísticas (promedio, mediana, etc.)
    public static double calcularPromedio(ArrayList<Double> notas) {
        if (notas.isEmpty()) {
            return 0.0;
        }
        
        double sumaNotas = 0.0;
        for (Double nota : notas) {
            sumaNotas += nota;

        }
        return sumaNotas / notas.size();
    }
    // metodos para calcular la mediana de las notas
    public static double calcularMedia(ArrayList<Double> notas) {
        if (notas.isEmpty()) {
            return 0.0; //devuelve 0 si no hay notas.
        }
        double[] notasArray = new double[notas.size()];
        for (int i=0 ;i < notas.size(); ++i){
            notasArray[i] = notas.get(i);
        }
        Arrays.sort(notasArray);

        int n = notasArray.length;
        if (n % 2 == 0) {
            int medio1 = n / 2 - 1;
            int medio2 = n / 2;
            return (notasArray[medio1] + notasArray[medio2]) / 2.0;
        } else {
            int medio = n / 2;
            return notasArray[medio];
        }
    }
    public static double calcularModa(ArrayList<Double> datos) {
        Map<Double, Integer> conteo = new HashMap<>();
        
        for (Double dato : datos) {
            conteo.put(dato, conteo.getOrDefault(dato, 0) + 1);
    }

    double moda = 0;
    int maxFrecuencia = 0;

    for (Map.Entry<Double, Integer> entry : conteo.entrySet()) {
        if (entry.getValue() > maxFrecuencia) {
            moda = entry.getKey();
            maxFrecuencia = entry.getValue();
        }
    }

    return moda;
}

public static double calcularDesviacionEstandar(ArrayList<Double> datos) {
    double promedio = calcularPromedio(datos);
    double sumatoria = 0;

    for (Double dato : datos) {
        sumatoria += Math.pow(dato - promedio, 2);
    }

    double varianza = sumatoria / datos.size();
    double desviacionEstandar = Math.sqrt(varianza);

    return desviacionEstandar;
}

    // Otros métodos que puedas necesitar
}
