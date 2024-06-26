package com.ligabetplay.personal;

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
        this.idEquipo = idEquipo;
    }
    
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public static class Jugador extends Personal{
        int dorsal;
        String demarcacion;

        public Jugador(int id, String nombre, String apellido, int edad, int idEquipo, int dorsal, String demarcacion) {
            super(id, nombre, apellido, edad, idEquipo);
            this.dorsal = dorsal;
            this.demarcacion = demarcacion;
        }

        public int getDorsal() {
            return dorsal;
        }

        public String getDemarcacion() {
            return demarcacion;
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

        public int getIdFederacion() {
            return idFederacion;
        }

        public String getTipoTecnico() {
            return tipoTecnico;
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

        public String getTitulacion() {
            return titulacion;
        }

        public int getAnyosExp() {
            return anyosExp;
        }
    }
}
