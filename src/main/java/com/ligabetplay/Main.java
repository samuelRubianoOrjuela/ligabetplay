package com.ligabetplay;

import java.text.MessageFormat;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ligabetplay.Torneo.Torneo;
import com.ligabetplay.equipo.Equipo;
import com.ligabetplay.listas.Listas;
import com.ligabetplay.newPersonal.newPersonal;
import com.ligabetplay.newliga.Newliga;
import com.ligabetplay.personal.Personal;
import com.ligabetplay.reportes.Reportes;
 
public class Main {

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

    public static void main(String[] args) {
        
        String header = """
            ----------------
            | LIGA BETPLAY |
            ----------------
            """;
        String[] menu = {"Nueva liga","Ver equipos","Ingresar personal","Reportes","Listas","Salir"};
        
        String errMesage = "Error: El dato ingresado es incorrecto, intentelo de nuevo ";
        
        Scanner sc = new Scanner(System.in);
        ArrayList<Equipo> listaEquipos = new ArrayList<>();
        Map<String, ArrayList<? extends Personal>> mapPersonas = new HashMap<>();
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

            switch (op) {
                case 1:
                    listaEquipos = Newliga.main(sc);
                    break;
                case 2:
                    if (listaEquipos.size() > 0) {
                        listaEquipos = Torneo.main(listaEquipos, sc);
                    } else {
                        System.out.println("Error: no hay equipos registrados, registra algunos.");
                        sc.nextLine();
                    }
                    break;
                case 3:
                    if (listaEquipos.size() > 0) {
                        mapPersonas = newPersonal.main(sc, listaEquipos);
                    } else {
                        System.out.println("Error: no hay equipos registrados, registra algunos.");
                        sc.nextLine();
                    }
                    break;                   
                case 4:
                    if (listaEquipos.size() > 0) {
                        Reportes.main(listaEquipos, sc);
                    } else {
                        System.out.println("Error: no hay equipos registrados, registra algunos.");
                        sc.nextLine();
                    }
                    break;       
                case 5:   
                    if (listaEquipos.size() > 0) {
                        Listas.main(sc, listaEquipos, mapPersonas);
                    } else {
                        System.out.println("Error: no hay equipos registrados, registra algunos.");
                        sc.nextLine();
                    }         
                    break;                   
                case 6:
                    isActive = false;
                    break;
                default:
                    System.out.println(errMesage);
                    sc.nextLine();
                    break;
            } 
        } 
        sc.close();
    }
}