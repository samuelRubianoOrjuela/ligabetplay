package com.ligabetplay.newPersonal;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.ligabetplay.equipo.Equipo;
import com.ligabetplay.personal.Personal;
import com.ligabetplay.personal.Personal.Jugador;
import com.ligabetplay.personal.Personal.Medico;
import com.ligabetplay.personal.Personal.Tecnico;


public class newPersonal {


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

    public static void crearPersona(Scanner sc, String errMesage, ArrayList<Equipo> listaEquipos, ArrayList<Personal> listaPersonas, int idPersona, String header, String tipoPersona, int op) {
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
            System.out.println(MessageFormat.format("-{0}. id: {1}", listaEquipos.get(i).getNombreEquipo(), listaEquipos.get(i).getId()));
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
                listaPersonas.add(newJugador);
                break;
            case 2:
                int idFederacion;
                do {
                    idFederacion = validInt(sc, errMesage, "Ingrese el numero de la federacion del tecnico: ");
                } while (idFederacion == -1);
                System.out.print("Ingrese el tipo del tecnico: ");
                String tipoTecnico = sc.nextLine();
                Tecnico newTecnico = new Tecnico(idPersona, nombre, apellido, edad, idEquipo, idFederacion, tipoTecnico);
                listaPersonas.add(newTecnico);
                break;
            case 3:
                System.out.print("Ingrese la titulacion del medico: ");
                String titulacion = sc.nextLine();
                int anyosExp;
                do {
                    anyosExp = validInt(sc, errMesage, "Ingrese los anyos de experiencia del medico: ");
                } while (anyosExp == -1);
                Medico newMedico = new Medico(idPersona, nombre, apellido, edad, idEquipo, titulacion, anyosExp);
                listaPersonas.add(newMedico);
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
        ArrayList<Personal> listaPersonas = new ArrayList<>();

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
            
            int op = validInt(sc, errMesage, "-> ");
            if(op == -1){
                continue mainLoop;
            }
            String x;
            switch (op) {
                case 1:
                    do {
                        crearPersona(sc, errMesage, newListaEquipos, listaPersonas, idJugador, header, "jugador", op);
                        idJugador++;
                        System.out.println("Desea ingresar otro jugador? si/ENTER");
                        x = sc.nextLine();
                    } while (!x.isEmpty());
                    break;
                case 2:
                    do {
                        crearPersona(sc, errMesage, newListaEquipos, listaPersonas, idTecnico, header, "tecnico", op);
                        idTecnico++;
                        System.out.print("Desea ingresar otro tecnico? si/ENTER ");
                        x = sc.nextLine();
                    } while (!x.isEmpty());
                    break;
                case 3:
                    do {
                        crearPersona(sc, errMesage, newListaEquipos, listaPersonas, idMedico, header, "medico", op);
                        idMedico++;
                        System.out.print("Desea ingresar otro medico? si/ENTER ");
                        x = sc.nextLine();
                    } while (!x.isEmpty());
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