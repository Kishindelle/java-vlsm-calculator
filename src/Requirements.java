//author: Kishindelle

public class Requirements {

    public String nombreLAN;
    public int hosts;

    public Requirements(String nombre, int h) {
        nombreLAN = nombre;
        hosts = h + 2;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombreLAN + "\nHosts: " + hosts;
    }

}
