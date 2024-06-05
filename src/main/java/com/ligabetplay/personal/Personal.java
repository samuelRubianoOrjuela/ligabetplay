package com.ligabetplay.personal;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Scanner;

// import com.ligabetplay.Torneo.Torneo;
import com.ligabetplay.equipo.Equipo;


public class Personal {
    
    int id;
    String nombre;
    String apellido;
    int edad;
    int idEquipo; 
    
    public Personal(int id, String nombre, String apellido, int edad, int idEquipo){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }
    
    public static class Jugador extends Personal{
        int dorsal;
        String demarcacion;

        public Jugador(int id, String nombre, String apellido, int edad, int idEquipo, int dorsal, String demarcacion) {
            super(id, nombre, apellido, edad, idEquipo);
            this.dorsal = dorsal;
            this.demarcacion = demarcacion;
        }        
    }

    public static class Tecnico extends Personal{
        int idFederacion;
        String tipoTecnico;

        public Tecnico(int id, String nombre, String apellido, int edad, int idEquipo, int idFederacion, String tipoTecnico) {
            super(id, nombre, apellido, edad, idEquipo);
            this.idFederacion = idFederacion;
            this.tipoTecnico = tipoTecnico;
        }
    }
    
    public static class Medico extends Personal{
        String titulacion;
        int anyosExp;

        public Medico(int id, String nombre, String apellido, int edad, int idEquipo, String titulacion,
                int anyosExp) {
            super(id, nombre, apellido, edad, idEquipo);
            this.titulacion = titulacion;
            this.anyosExp = anyosExp;
        }
    }

    public static void clearScreen() {         
        try {             
            if (System.getProperty("os.name").contains("Windows")) {                 
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();             
            } else {                 
                new ProcessBuilder("clear").inheritIO().start().waitFor();             
            }         
        } catch (Exception e) {             
            System.out.println("Error al limpiar la pantalla: " + e.getMessage());         
        }     
    }

    public static int validInt(Scanner sc, String errMesage, String txt){
        int x;
        System.out.print(txt);
        try {
            x = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.print(errMesage);
            sc.nextLine();
            x = -1;
            return x;
        }
        return x;
    }

    public static ArrayList<Equipo> sortId(ArrayList<Equipo> listaEquipos){
        int x;
        do{
            x = 0;
            for (int i = 0; i < listaEquipos.size()-1; i++){
                if (listaEquipos.get(i).getId() > listaEquipos.get(i+1).getId()) {
                    Equipo equipoP = listaEquipos.get(i);
                    listaEquipos.set(i, listaEquipos.get(i+1));
                    listaEquipos.set(i+1, equipoP);
                    x++;
                }
            }
        } while (x != 0);
        return listaEquipos;
    }

    public static void crearPersona(Scanner sc, String errMesage, ArrayList<Equipo> listaEquipos, ArrayList<Jugador> listaJugadores, ArrayList<Tecnico> listaTecnicos, ArrayList<Medico> listaMedicos, int idPersona, String header, String tipoPersona, int op) {
        clearScreen();
        System.out.println(header);
        System.out.print(MessageFormat.format("Ingrese el nombre del {0}: ", tipoPersona));
        String nombre = sc.nextLine();
        System.out.print(MessageFormat.format("Ingrese el apellido del {0}: ", tipoPersona));
        String apellido = sc.nextLine();
        int edad;
        do {
            edad = validInt(sc, errMesage, MessageFormat.format("Ingrese la edad del {0}: ", tipoPersona));
        } while (edad == -1);
        System.out.println("Id equipos: ");
        for (int i = 0; i < listaEquipos.size(); i++){               
            System.out.println(MessageFormat.format("{0}. {1}", listaEquipos.get(i).getId(), listaEquipos.get(i).getNombreEquipo()));
        }
        int idEquipo;
        do {
            idEquipo = validInt(sc, errMesage, MessageFormat.format("Ingrese el id del equipo del {0}: ", tipoPersona));
        } while (idEquipo == -1);
        switch (op) {
            case 1:
                int dorsal;
                do {
                    dorsal = validInt(sc, errMesage, "Ingrese el numero del dorsal del jugador: ");
                } while (dorsal == -1);
                System.out.print("Ingrese la demarcacion del jugador: ");
                String demarcacion = sc.nextLine();
                Jugador newJugador = new Jugador(idPersona, nombre, apellido, edad, idEquipo, dorsal, demarcacion);
                listaJugadores.add(newJugador);
                break;
            case 2:
                int idFederacion;
                do {
                    idFederacion = validInt(sc, errMesage, "Ingrese el numero de la federacion del tecnico: ");
                } while (idFederacion == -1);
                System.out.print("Ingrese el tipo del tecnico: ");
                String tipoTecnico = sc.nextLine();
                Tecnico newTecnico = new Tecnico(idPersona, nombre, apellido, edad, idEquipo, idFederacion, tipoTecnico);
                listaTecnicos.add(newTecnico);
            case 3:
                System.out.print("Ingrese la titulacion del medico: ");
                String titulacion = sc.nextLine();
                int anyosExp;
                do {
                    anyosExp = validInt(sc, errMesage, "Ingrese los anyos de experiencia del medico: ");
                } while (anyosExp == -1);
                Medico newMedico = new Medico(idPersona, nombre, apellido, edad, idEquipo, titulacion, anyosExp);
                listaMedicos.add(newMedico);
            default:
                break;
        }
    }

    public static void main(Scanner sc, ArrayList<Equipo> listaEquipos) {
        String header = """
                -------------------------
                | PERSONAL LIGA BETPLAY |
                -------------------------
                """;
        String[] menu = {"Ingresar jugador","Ingresar tecnico","Ingresar medico","Ir al menu principal"};
        
        String errMesage = "Error: El dato ingresado es incorrecto, intentelo de nuevo";

        ArrayList<Equipo> newListaEquipos = sortId(listaEquipos);
        ArrayList<Jugador> listaJugadores = new ArrayList<>();
        ArrayList<Tecnico> listaTecnicos = new ArrayList<>();
        ArrayList<Medico> listaMedicos = new ArrayList<>();

        int idJugador = 1;
        int idTecnico = 1;
        int idMedico = 1;
        boolean isActive = true;
        mainLoop:
        while (isActive) {
            clearScreen();
            System.out.println(header);
            
            for (int i = 0; i < menu.length; i++) {
                System.out.println(MessageFormat.format("{0}. {1}.", (i+1), menu[i]));
            }
            
            int op = validInt(sc, errMesage, "->");
            if(op == -1){
                continue mainLoop;
            }
            switch (op) {
                case 1:
                    crearPersona(sc, errMesage, newListaEquipos, listaJugadores, listaTecnicos, listaMedicos, idJugador, header, "jugador", op);
                    idJugador++;
                    break;
                case 2:
                    crearPersona(sc, errMesage, newListaEquipos, listaJugadores, listaTecnicos, listaMedicos, idTecnico, header, "tecnico", op);
                    idTecnico++;
                    break;
                case 3:
                    crearPersona(sc, errMesage, newListaEquipos, listaJugadores, listaTecnicos, listaMedicos, idMedico, header, "medico", op);
                    idMedico++;
                    break;
                case 4:
                    isActive = false;
                    break;
                default:
                    System.out.println(errMesage);  
                    break;
            }
        }
    }

}