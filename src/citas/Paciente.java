package citas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class Paciente {
    private String Nombre;
    private LinkedHashMap<String, String> pacientes = new LinkedHashMap<>();
    private String  FILE_NAME = "src\\citas\\pacientes.csv";

    public Paciente(String nombre) {
        this.Nombre = nombre;
        this.loadPacientesFileData();
    }

    public void crearPaciente() {
        pacientes.put(String.valueOf(pacientes.size() + 1), this.Nombre);
        this.saveData();
    }

    private void loadPacientesFileData() {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(FILE_NAME));
            String line;

            while((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                pacientes.put(values[0], values[1]);
            }
        } catch (Exception ex) {
            System.out.println("Error:" + ex.getMessage());
        }
    }

    private void saveData() {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME));
            for(Map.Entry<String, String> paciente : pacientes.entrySet()) {
                bufferedWriter.write(paciente.getKey() + "," + paciente.getValue() + "\n");
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
