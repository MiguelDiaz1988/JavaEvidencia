package citas;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cita {
    private String IdDoctor;
    private String IdPaciente;
    private String Motivo;
    private String Fecha;

    private LinkedHashMap<String, ArrayList<String>> citas = new LinkedHashMap<>();
    private String  FILE_NAME = "src\\citas\\db\\citas.csv";

    public Cita(String idDoctor, String idPaciente, String motivo, String fecha) {
        this.IdDoctor = idDoctor;
        this.IdPaciente = idPaciente;
        this.Motivo = motivo;
        this.Fecha = fecha;
        this.loadCitasData();
    }

    public int crearCita() {
        ArrayList<String> datos = new ArrayList<>();
        datos.add(this.IdDoctor);
        datos.add(this.IdPaciente);
        datos.add(this.Motivo);
        datos.add(this.Fecha);
        int newId = citas.size() + 1;
        citas.put(String.valueOf(newId), datos);
        this.saveData();
        return newId;
    }

    private void loadCitasData() {
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
                datos.add(values[3]);
                datos.add(values[4]);

                citas.put(values[0], datos);
            }
        } catch (Exception ex) {
            System.out.println("Error:" + ex.getMessage());
        }
    }

    private void saveData() {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME));
            for(Map.Entry<String, ArrayList<String>> cita : citas.entrySet()) {
                ArrayList<String> datosDoctor = cita.getValue();
                bufferedWriter.write(cita.getKey() + "," + datosDoctor.get(0) + "," + datosDoctor.get(1) + "," + datosDoctor.get(2) + "," + datosDoctor.get(3) +"\n");
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
