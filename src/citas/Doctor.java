package citas;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Doctor {
    private String Nombre;
    private String Especialidad;
    private LinkedHashMap<String, ArrayList<String>> doctores = new LinkedHashMap<>();
    private String  FILE_NAME = "src\\citas\\db\\doctores.csv";

    public Doctor(String nombre, String especialidad) {
        this.Nombre = nombre;
        this.Especialidad = especialidad;
        this.loadDoctoresData();
    }

    public int crearDoctor() {
        ArrayList<String> datos = new ArrayList<>();
        datos.add(this.Nombre);
        datos.add(this.Especialidad);
        int newId = doctores.size() + 1;
        doctores.put(String.valueOf(newId), datos);
        this.saveData();
        return newId;
    }

    private void loadDoctoresData() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                file.createNewFile();
                return;
            }

            BufferedReader bufferedReader = null;
            bufferedReader = new BufferedReader(new FileReader(FILE_NAME));
            String line;

            while((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                ArrayList<String> datos = new ArrayList<String>();
                datos.add(values[1]);
                datos.add(values[2]);
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
