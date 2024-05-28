package com.ligabetplay.Torneo;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.ligabetplay.equipo.Equipo;

public class Torneo {

    public static ArrayList<Equipo> tabla(ArrayList<Equipo> listaEquipos){

        System.out.println("""

                ---------------------------------------------
                | EQUIPO | PJ | PG | PP | PE | GF | GC | TP |
                ---------------------------------------------""");
        for (int i = 0; i < listaEquipos.size(); i++){
            System.out.println(MessageFormat.format("""
                |   {0}    |  {1} |  {2} |  {3} |  {4} |  {5} |  {6} |  {7} |
                ---------------------------------------------""", listaEquipos.get(i).getNombreEquipo(), listaEquipos.get(i).getPj(), listaEquipos.get(i).getPg(), listaEquipos.get(i).getPp(), listaEquipos.get(i).getPe(), listaEquipos.get(i).getGf(), listaEquipos.get(i).getGc(), listaEquipos.get(i).getTp()));
        }
        System.out.println("\n");
        return listaEquipos;
    }

    public static ArrayList<Equipo> main(ArrayList<Equipo> listaEquipos, Scanner sc) {
        String header = """
                ----------------------
                | TABLA LIGA BETPLAY |
                ----------------------
                """;
        String[] menu = {"Ver tabla","Ir al menu principal"};
        
        String errMesage = "Error: El dato ingresado es incorrecto, intentelo de nuevo";
        
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
                    tabla(listaEquipos);
                    break;
                case 2:
                    isActive = false;
                    break;
                default:
                    System.out.println(errMesage);  
                    break;
            }
        }
        
        return listaEquipos;
    }
}
