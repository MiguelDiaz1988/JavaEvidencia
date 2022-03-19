package citas;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static final String TEXTO_ROJO = "\u001B[31m";
    public static final String TEXTO_VERDE = "\u001B[32m";
    public static final String TEXTO_AMARILLO = "\u001B[33m";

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.println(TEXTO_VERDE + "### Bienvenido al sistema de citas ###");
                System.out.print(TEXTO_AMARILLO + "Ingrese su usuario: ");
                String usuario = reader.readLine();
                System.out.print(TEXTO_AMARILLO + "Ingrese su password: ");
                String password = reader.readLine();

                Usuario user = new Usuario(usuario, password);
                if (!user.hasValidCredentials()) {
                    System.out.println(TEXTO_ROJO + "Credenciales incorrectas, intente de nuevo.");
                }
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
