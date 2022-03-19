package citas;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Paciente {
    private String Nombre;
    private LinkedHashMap<String, String> pacientes = new LinkedHashMap<>();
    private String  FILE_NAME = "src\\citas\\db\\pacientes.csv";

    public Paciente(String nombre) {
        this.Nombre = nombre;
        this.loadPacientesFileData();
    }

    public int crearPaciente() {
        int newId = pacientes.size() + 1;
        pacientes.put(String.valueOf(newId), this.Nombre);
        this.saveData();
        return newId;
    }

    private void loadPacientesFileData() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                file.createNewFile();
                return;
            }

            BufferedReader bufferedReader = null;
            var fileReader =
                    bufferedReader = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
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
