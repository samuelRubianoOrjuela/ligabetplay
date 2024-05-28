package com.ligabetplay;

import java.text.MessageFormat;
import java.util.Scanner;
import java.util.ArrayList;
// import java.util.InputMismatchException;

import com.ligabetplay.Torneo.Torneo;
import com.ligabetplay.equipo.Equipo;
import com.ligabetplay.newliga.Newliga;
import com.ligabetplay.reportes.Reportes;


 
public class Main {
    public static void main(String[] args) {
        
        String header = """
            ----------------
            | LIGA BETPLAY |
            ----------------
            """;
        String[] menu = {"Nueva liga","Ver equipos","Reportes","Salir"};
        
        String errMesage = "Error: El dato ingresado es incorrecto, intentelo de nuevo";
        
        Scanner sc = new Scanner(System.in);
        ArrayList<Equipo> listaEquipos = new ArrayList<>();
        boolean isActive = true;
        while (isActive) {
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
                }
            } while (true);

            switch (op) {
                case 1:
                    listaEquipos = Newliga.main(sc);
                    break;
                case 2:
                    if (listaEquipos.size() > 0) {
                        listaEquipos = Torneo.main(listaEquipos, sc);
                    } else {
                        System.out.println("Error: no hay equipos registrados, registra algunos.");
                    }
                    break;
                case 3:
                    if (listaEquipos.size() > 0) {
                        Reportes.main(listaEquipos, sc);
                    } else {
                        System.out.println("Error: no hay equipos registrados, registra algunos.");
                    }
                    break;                   
                case 4:
                    isActive = false;
                    break;
                default:
                    System.out.println(errMesage);
                    break;
            } 
        } 
        sc.close();
    }
}