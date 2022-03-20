package citas;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static final String TEXTO_ROJO = "\u001B[31m";
    public static final String TEXTO_VERDE = "\u001B[32m";
    public static final String TEXTO_AMARILLO = "\u001B[33m";
    public static final String TEXTO_AZUL = "\u001B[34m";

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(TEXTO_VERDE + "### Bienvenido al sistema de citas ###");

        while (true) {
            try {
                System.out.print(TEXTO_AMARILLO + "Ingrese su usuario: ");
                String usuario = reader.readLine();
                System.out.print(TEXTO_AMARILLO + "Ingrese su password: ");
                String password = reader.readLine();

                Usuario user = new Usuario(usuario, password);
                if (!user.hasValidCredentials()) {
                    System.out.println(TEXTO_ROJO + "Credenciales incorrectas, intente de nuevo.");
                    continue;
                }

                while(true) {
                    System.out.println(TEXTO_VERDE + "### Menu de acciones ###");
                    System.out.println(TEXTO_VERDE + "1. Dar de alta pacientes");
                    System.out.println(TEXTO_VERDE + "2. Dar de alta doctores");
                    System.out.println(TEXTO_VERDE + "3. Crear cita");
                    System.out.print(TEXTO_AMARILLO + "Ingrese numero de accion a realizar: ");
                    String accion = reader.readLine();

                    switch (accion) {
                        case "1":
                            System.out.print(TEXTO_AMARILLO + "Ingrese nombre completo de paciente: ");
                            String nombrePaciente = reader.readLine();
                            Paciente paciente = new Paciente(nombrePaciente);
                            int newPacienteId = paciente.crearPaciente();
                            System.out.println(TEXTO_AZUL + "Paciente creado exitosamente con ID:" + newPacienteId);
                            break;
                        case "2":
                            System.out.print(TEXTO_AMARILLO + "Ingrese nombre completo del doctor: ");
                            String nombreDoctor = reader.readLine();
                            System.out.print(TEXTO_AMARILLO + "Ingrese especialidad del doctor: ");
                            String especialidad = reader.readLine();
                            Doctor doctor = new Doctor(nombreDoctor, especialidad);
                            int newDoctorId = doctor.crearDoctor();
                            System.out.println(TEXTO_AZUL + "Doctor creado exitosamente con ID:" + newDoctorId);
                            break;
                        case "3":
                            System.out.print(TEXTO_AMARILLO + "Ingrese codigo del doctor: ");
                            String idDoctor = reader.readLine();
                            System.out.print(TEXTO_AMARILLO + "Ingrese codigo paciente: ");
                            String idPaciente = reader.readLine();
                            System.out.print(TEXTO_AMARILLO + "Ingrese motivo de cita: ");
                            String motivo = reader.readLine();
                            System.out.print(TEXTO_AMARILLO + "Ingrese fecha de cita: ");
                            String fechaCita = reader.readLine();

                            Cita cita = new Cita(idDoctor, idPaciente, motivo, fechaCita);
                            int newCitaId = cita.crearCita();
                            System.out.println(TEXTO_AZUL + "Cita creada exitosamente con ID:" + newCitaId);
                            break;
                        default:
                            System.out.print(TEXTO_AMARILLO + "Accion no valida: ");
                            break;
                    }
                }
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
