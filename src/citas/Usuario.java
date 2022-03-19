package citas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Usuario {
    private String Usuario;
    private String Password;

    private HashMap<String, String> usuarios = new HashMap<>();
    private String  FILE_NAME = "src\\citas\\usuarios.csv";

    public Usuario(String usuario, String password) {
        this.Usuario = usuario;
        this.Password = password;
        this.loadUsersFileData();
    }

    public boolean hasValidCredentials() {
        String passwordFound =  usuarios.get(this.Usuario);
        return passwordFound.equals(this.Password);
    }

    private void loadUsersFileData() {
        BufferedReader bufferedReader = null;
        try {
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
}
