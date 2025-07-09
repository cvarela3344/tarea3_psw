package inf331.jUnit.model;


public class Cliente {
    private String id;
    private String nombre;
    private String correo;
    private int puntos;
    private Nivel nivel;

    public Cliente(String id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.puntos = 0;
        this.nivel = Nivel.BRONCE;
    }


    public void sumarPuntos(int puntos) {
        this.puntos += puntos;
        actualizarNivel();
    }

    private void actualizarNivel() {
        if (puntos >= 3000) {
            this.nivel = Nivel.PLATINO;
        } else if (puntos >= 1500) {
            this.nivel = Nivel.ORO;
        } else if (puntos >= 500) {
            this.nivel = Nivel.PLATA;
        } else {
            this.nivel = Nivel.BRONCE;
        }
    }


    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public int getPuntos() { return puntos; }
    public Nivel getNivel() { return nivel; }

    public void setId(String id) {
        this.id = id;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
