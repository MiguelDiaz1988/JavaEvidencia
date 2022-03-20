package citas;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Usuario {
    private String Usuario;
    private String Password;

    private HashMap<String, String> usuarios = new HashMap<>();
    private String  FILE_NAME = "src\\citas\\db\\usuarios.csv";

    public Usuario(String usuario, String password) {
        this.Usuario = usuario;
        this.Password = password;
        this.loadUsersFileData();
    }

    public boolean hasValidCredentials() {
        String passwordFound =  usuarios.get(this.Usuario);
        if (passwordFound == null) return false;
        return passwordFound.equals(this.Password);
    }

    private void loadUsersFileData() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                file.createNewFile();
                this.createDefaultAdmins();
                return;
            }

            BufferedReader bufferedReader = null;
            bufferedReader = new BufferedReader(new FileReader(FILE_NAME));
            String line;

            while((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                usuarios.put(values[0], values[1]);
            }
        } catch (Exception ex) {
            System.out.println("Error:" + ex.getMessage());
        }
    }

    private void createDefaultAdmins() {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME));
            bufferedWriter.write("admin,admin123\n");
            bufferedWriter.write("admin2,admin456\n");
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
