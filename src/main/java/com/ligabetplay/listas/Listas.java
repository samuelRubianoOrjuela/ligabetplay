package com.ligabetplay.listas;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import com.ligabetplay.equipo.Equipo;
import com.ligabetplay.newPersonal.newPersonal;
import com.ligabetplay.personal.Personal;

public class Listas {

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

    public static void listar(Scanner sc, Map<String, ArrayList<? extends Personal>> mapPersonas){
        clearScreen();
        System.out.println("""
                -------------------
                | LISTA JUGADORES |
                -------------------
                """);
        ArrayList<? extends Personal> listaJugadores = mapPersonas.get("listaJugadores");        
        for (Personal jugador : listaJugadores) {
            System.out.println(MessageFormat.format("{0}. {1} {2}", jugador.getId(), jugador.getNombre(), jugador.getApellido()));
        }
        sc.nextLine();
    }
    public static void listarEquipos(Scanner sc , ArrayList<Equipo> listaEquipos){
        clearScreen();
        System.out.println("""
                -----------------
                | LISTA EQUIPOS |
                -----------------
                """);
        for (Equipo equipo : listaEquipos) {
            System.out.println(MessageFormat.format("{0}. {1}", equipo.getId(), equipo.getNombreEquipo()));
        }
        sc.nextLine();
    }

    public static void main(Scanner sc, ArrayList<Equipo> listaEquipos, Map<String, ArrayList<? extends Personal>> mapPersonas) {
        String header = """
                -----------------------
                | LISTAS LIGA BETPLAY |
                -----------------------
                """;
        String[] menu = {"Listar equipos","Listar jugadores","Listar tecnicos","Listar medicos","Ir al menu principal"};
        
        String errMesage = "Error: El dato ingresado es incorrecto, intentelo de nuevo";
        boolean isActive = true;
        mainLoop:
        while (isActive) {
            clearScreen();
            System.out.println(header);
            
            for (int i = 0; i < menu.length; i++) {
                System.out.println(MessageFormat.format("{0}. {1}.", (i+1), menu[i]));
            }
            
            int op = validInt(sc, errMesage, "-> ");
            if (op == -1){
                continue mainLoop;
            }
            
            switch (op) {
                case 1:
                    listarEquipos(sc, newPersonal.sortId(listaEquipos));
                    break;
                case 2:
                    listar(sc, mapPersonas);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    isActive = false;
                    break;
                default:
                    System.out.println(errMesage);  
                    break;
            }
        }
    }
}
