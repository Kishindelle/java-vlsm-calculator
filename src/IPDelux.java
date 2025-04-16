//author: Kishindelle

public class IPDelux {

    Ipv4 ipRED;
    Ipv4 ipPrimerHost;
    Ipv4 ipUltimoHost;
    Ipv4 ipBroadcast;
    String mascara;

    public Ipv4 getIpRED() {
        return ipRED;
    }

    public Ipv4 getIpPrimerHost() {
        return ipPrimerHost;
    }

    public Ipv4 getIpUltimoHost() {
        return ipUltimoHost;
    }

    public Ipv4 getIpBroadcast() {
        return ipBroadcast;
    }

    public String getMascara() {
        return mascara;
    }

    public IPDelux(Ipv4 ipRED) {
        this.ipRED = ipRED;
        this.ipPrimerHost = obtenerPrimerHost(ipRED);
        this.ipUltimoHost = obtenerUltimoHost(ipRED);
        this.ipBroadcast = obtenerBroadcast(ipRED);
        this.mascara = ipRED.GetMascaraIP();
    }

    private Ipv4 obtenerPrimerHost(Ipv4 ip) {
        Ipv4 ipreturn = new Ipv4(ip);

        ipreturn.parteHOSTS.adicionarUno();

        return ipreturn;

    }

    private Ipv4 obtenerBroadcast(Ipv4 ip) {
        Ipv4 ipreturn = new Ipv4(ip);

        while (!ipreturn.parteHOSTS.verificarUnos()) {
            ipreturn.parteHOSTS.adicionarUno();
        }
        return ipreturn;

    }

    private Ipv4 obtenerUltimoHost(Ipv4 ip) {
        Ipv4 ipreturn = new Ipv4(ip);

        while (!ipreturn.parteHOSTS.verificarUnosHastaBroadcast()) {
            ipreturn.parteHOSTS.adicionarUno();
        }
        return ipreturn;

    }

    public String Imprimir() {
        String stringReturn = "";

        stringReturn += "IP DE RED:\t" + ipRED.IPFormateada() + "\n";
        stringReturn += "PRIMER HOST:\t" + ipPrimerHost.IPFormateada() + "\n";
        stringReturn += "ULTIMO HOST:\t" + ipUltimoHost.IPFormateada() + "\n";
        stringReturn += "BROADCAST:\t" + ipBroadcast.IPFormateada() + "\n";
        stringReturn += "MASCARA:\t" + mascara;
        return stringReturn;

    }
}
