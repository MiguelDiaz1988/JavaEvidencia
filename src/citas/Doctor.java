package citas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Doctor {
    private String Nombre;
    private String Especialidad;
    private LinkedHashMap<String, ArrayList<String>> doctores = new LinkedHashMap<>();
    private String  FILE_NAME = "src\\citas\\doctores.csv";

    public Doctor(String nombre, String especialidad) {
        this.Nombre = nombre;
        this.Especialidad = especialidad;
        this.loadDoctoresData();
    }

    public void crearDoctor() {
        ArrayList<String> datos = new ArrayList<>();
        datos.add(this.Nombre);
        datos.add(this.Especialidad);
        doctores.put(String.valueOf(doctores.size() + 1), datos);
        this.saveData();
    }

    private void loadDoctoresData() {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(FILE_NAME));
            String line;

            while((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                ArrayList<String> datos = new ArrayList<String>();
                datos.add(values[0]);
                datos.add(values[1]);
                doctores.put(values[0], datos);
            }
        } catch (Exception ex) {
            System.out.println("Error:" + ex.getMessage());
        }
    }

    private void saveData() {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME));
            for(Map.Entry<String, ArrayList<String>> doctor : doctores.entrySet()) {
                ArrayList<String> datosDoctor = doctor.getValue();
                bufferedWriter.write(doctor.getKey() + "," + datosDoctor.get(0) + "," + datosDoctor.get(1) + "\n");
            }
        } catch (Exception ex) {
            System.out.println("Error cuando se intento guardar los datos de pacientes: " + ex.getMessage());
        } finally {
            try {
                bufferedWriter.close();
            } catch (Exception ex) {
                System.out.println("Error cuando se intento guardar los datos de pacientes: " + ex.getMessage());
            }
        }
    }
}
