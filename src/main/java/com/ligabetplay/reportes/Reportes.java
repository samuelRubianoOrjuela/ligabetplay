package com.ligabetplay.reportes;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.ligabetplay.equipo.Equipo;

public class Reportes {

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

    public static void masGoles(ArrayList<Equipo> listaEquipos, String header, Scanner sc){
        int x;
        do{
            x = 0;
            for (int i = 0; i < listaEquipos.size()-1; i++){
                if (listaEquipos.get(i).getGf() < listaEquipos.get(i+1).getGf()) {
                    Equipo equipoP = listaEquipos.get(i);
                    listaEquipos.set(i, listaEquipos.get(i+1));
                    listaEquipos.set(i+1, equipoP);
                    x++;
                }
            }
        } while (x != 0);
        clearScreen();
        System.out.println(header);
        System.out.println("\nEquipo(s) con mas goles anotados:\n");
        for (int i = 0; i < listaEquipos.size(); i++){
            if (listaEquipos.get(0).getGf() == listaEquipos.get(i).getGf()){                
                System.out.println(MessageFormat.format("""
                    Nombre equipo: {0}
                    Goles anotados: {1}
                    """, listaEquipos.get(i).getNombreEquipo(), listaEquipos.get(i).getGf()));
            }
        }
        sc.nextLine();
    }

    public static void masPoG(ArrayList<Equipo> listaEquipos, int op, String header, Scanner sc){
        clearScreen();
        System.out.println(header);
        switch (op) {
            case 2:
                System.out.println("\nEquipo(s) con mas puntos obtenidos:\n");
                break;
            case 3:
                System.out.println("\nEquipo(s) con mas partidos ganados:\n");
            break;
            default:
                break;
        }
        for (int i = 0; i < listaEquipos.size(); i++){
            if (listaEquipos.get(0).getTp() == listaEquipos.get(i).getTp()){  
                switch (op) {
                    case 2:
                        System.out.println(MessageFormat.format("""
                            Nombre equipo: {0}
                            Puntos obtenidos: {1}
                            """, listaEquipos.get(i).getNombreEquipo(), listaEquipos.get(i).getTp()));
                        break;
                    case 3:
                        System.out.println(MessageFormat.format("""
                            Nombre equipo: {0}
                            Partidos ganados: {1}
                            """, listaEquipos.get(i).getNombreEquipo(), listaEquipos.get(i).getPg()));
                            break;
                    default:
                        break;
                }

            }
        }
        sc.nextLine();
    }

    public static void totalG(ArrayList<Equipo> listaEquipos, String header, Scanner sc){
        int x;
        do{
            x = 0;
            for (int i = 0; i < listaEquipos.size()-1; i++){
                if (listaEquipos.get(i).getGf() < listaEquipos.get(i+1).getGf()) {
                    Equipo equipoP = listaEquipos.get(i);
                    listaEquipos.set(i, listaEquipos.get(i+1));
                    listaEquipos.set(i+1, equipoP);
                    x++;
                }
            }
        } while (x != 0);
        clearScreen();
        System.out.println(header);
        System.out.println("""

                ------------------------------------
                | Equipo | Total de goles anotados |
                ------------------------------------""");
        for (int i = 0; i < listaEquipos.size(); i++){
            System.out.println(MessageFormat.format("""
                |  {0}     |           {1}             |
                ------------------------------------""", listaEquipos.get(i).getNombreEquipo(), listaEquipos.get(i).getGf()));
        }
        sc.nextLine();
    }

    public static void promG(ArrayList<Equipo> listaEquipos, String header, Scanner sc){
        int cant = 0;
        int sum = 0;
        for (int i = 0; i < listaEquipos.size(); i++) {
            cant++;
            sum += listaEquipos.get(i).getGf();
        }
        clearScreen();
        System.out.println(header);
        double prom = sum/cant;
        System.out.print(MessageFormat.format("\nPromedio de goles anotados en el tornero de la FCF: {0}", prom));
        sc.nextLine();
    }

    public static void main(ArrayList<Equipo> listaEquipos, Scanner sc) {
        String header = """
            -------------------------
            | REPORTES LIGA BETPLAY |
            -------------------------
            """;
        String[] menu = {"Nombre del equipo que mas goles anoto","Nombre del equipo que mas puntos tiene","Nombre del equipo que mas partidos gano","Total de goles anotados por todos los equipos","Promedio de goles anotados en el tornero de la FCF","Ir al menu principal"};
        
        String errMesage = "Error: El dato ingresado es incorrecto, intentelo de nuevo";

        boolean isActive = true;
        mainLoop:
        while (isActive) {
            clearScreen();
            System.out.println(header);
            
            for (int i = 0; i < menu.length; i++) {
                System.out.println(MessageFormat.format("{0}. {1}.", (i+1), menu[i]));
            }
            int op;
            do {                
                try {
                    System.out.print("-> ");
                    op = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println(errMesage);
                    sc.nextLine();
                    continue mainLoop;
                }
            } while (true);
            
            switch (op) {
                case 1:
                    masGoles(listaEquipos, header, sc);
                    break;
                case 2:
                    masPoG(listaEquipos, op, header, sc);
                    break;   
                case 3:
                    masPoG(listaEquipos, op, header, sc);
                    break;
                case 4:
                    totalG(listaEquipos, header, sc);
                    break;   
                case 5:
                    promG(listaEquipos, header, sc);   
                case 6:
                    isActive = false;
                    break;
                default:
                    System.out.println(errMesage);  
                    break;
            }
        }
    }
}