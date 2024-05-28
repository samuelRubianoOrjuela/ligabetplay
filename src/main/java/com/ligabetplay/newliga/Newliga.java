package com.ligabetplay.newliga;

import java.text.MessageFormat;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

import com.ligabetplay.equipo.Equipo;

public class Newliga {

    public static boolean jugaron(int[] listEqps, int id){
        for (int i : listEqps) {
            if (i == id) {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Equipo> jugartorneo(ArrayList<Equipo> listaEquipos){
        for (Equipo equipo1 : listaEquipos) {
            for (Equipo equipo2 : listaEquipos) {
                if (equipo1.getId() != equipo2.getId() && jugaron(equipo1.getEqps(), equipo2.getId())) {
                    Random rnd = new Random();
                    int goles1 = rnd.nextInt(5);
                    int goles2 = rnd.nextInt(5);

                    equipo1.setEqps(equipo2.getId());
                    equipo1.setPj(1);
                    equipo1.setGf(goles1);
                    equipo1.setGc(goles2);
                    
                    equipo2.setEqps(equipo1.getId());
                    equipo2.setPj(1);
                    equipo2.setGf(goles2);
                    equipo2.setGc(goles1);
                    
                    if (goles1 > goles2) {
                        equipo1.setPg(1);
                        equipo1.setTp(3);
                        equipo2.setPp(1);
                    } else if (goles1 == goles2) {
                        equipo1.setPe(1);
                        equipo1.setTp(1);
                        equipo2.setPe(1);
                        equipo2.setTp(1);
                    } else {
                        equipo1.setPp(1);
                        equipo2.setPg(1);
                        equipo2.setTp(3);
                    }

                }
            }
        }
        int x;        
        do {    
            x = 0;
            for (int i = 0; i < listaEquipos.size()-1; i++){
                if (listaEquipos.get(i).getTp() < listaEquipos.get(i+1).getTp()) {
                    Equipo equipoP = listaEquipos.get(i);
                    listaEquipos.set(i, listaEquipos.get(i+1));
                    listaEquipos.set(i+1, equipoP);
                    x++;
                } else if (listaEquipos.get(i).getTp() == listaEquipos.get(i+1).getTp()) {
                    if (listaEquipos.get(i).getGf() < listaEquipos.get(i+1).getGf()) {
                        Equipo equipoP = listaEquipos.get(i);
                        listaEquipos.set(i, listaEquipos.get(i+1));
                        listaEquipos.set(i+1, equipoP);
                        x++;
                    }
                }
            }
        } while (x != 0);

        return listaEquipos;
    }

    public static ArrayList<Equipo> crearEquipo(Scanner sc, ArrayList<Equipo> listaEquipos, int id) {
        System.out.print("Ingrese el nombre del equipo: ");
        String nombreEquipo = sc.nextLine();
        Equipo newEquipo = new Equipo(id, nombreEquipo);
        listaEquipos.add(newEquipo);
        return listaEquipos;
    }

    public static ArrayList<Equipo> main(Scanner sc) {
         
        String header = """
                ----------------------
                | NUEVA LIGA BETPLAY |
                ----------------------
                """;
        String[] menu = {"Ingresar equipo","Ir al menu principal"};
        
        String errMesage = "Error: El dato ingresado es incorrecto, intentelo de nuevo";
        ArrayList<Equipo> listaEquipos = new ArrayList<>();
        
        boolean isActive = true;
        int id = 1;
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
                    listaEquipos = crearEquipo(sc, listaEquipos, id);
                    break;
                case 2:
                    isActive = false;
                    break;
                default:
                    System.out.println(errMesage);  
                    break;
            }
            id++;
        }

        return jugartorneo(listaEquipos);

    }
}