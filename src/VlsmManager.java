//author: Kishindelle

import java.util.ArrayList;
import java.util.List;

public class VlsmManager {

    Ipv4 ipPadre;
    Ipv4 ipUtilizando;
    List<Requirements> requerimientos;

    public List<IPDelux> Subneteadas = new ArrayList<IPDelux>();

    public List<Ipv4> listaIP = new ArrayList<Ipv4>();

    public VlsmManager(Ipv4 ipPadre, List<Requirements> requerimientos) {
        this.ipPadre = ipPadre;
        this.ipUtilizando = ipPadre;
        this.requerimientos = OrdenarRequerimietos(requerimientos);
    }

    public String Obtener() throws Exception {
        int iterador = 0;
        Ipv4 ipPadreComienzo = new Ipv4(ipPadre);
        int iteradorRequerimientos = 0;

        // Bucle para subnetear
        // aqui comprueba si los requerimientos se cumplen, en caso de que si se cumplan
        // ya todos lo requerimientos ya no entra
        while (iteradorRequerimientos < requerimientos.size()) {
            boolean insuficiencia = false;

            int host = requerimientos.get(iteradorRequerimientos).hosts;

            int bitsaRobar = CalcularPotenciaDe2MasCercana(host);

            if (bitsaRobar >= ipPadreComienzo.parteHOSTS.getNumeroBinario().length()) {

                return "La red Padre No abastece los hosts para: "
                        + requerimientos.get(iteradorRequerimientos).nombreLAN;

            } else {

                System.out.println("Subneting " + iterador);

                Ipv4 ip = new Ipv4(iterador, ipPadreComienzo);

                int numerolimitehostsInicio = (int) Math.pow(2.0, (double) (bitsaRobar - 1));

                bitsaRobar = (ip.parteHOSTS.getNumeroBinario().length()) - bitsaRobar;

                ip.parteSUBRED.setNumeroBinario(ip.parteHOSTS.getNumeroBinario().substring(0, bitsaRobar));
                ip.parteHOSTS.setNumeroBinario(ip.parteHOSTS.getNumeroBinario().substring(bitsaRobar));

                ip.mascara += bitsaRobar;

                do {
                    Ipv4 ips = new Ipv4(ip.id, ip);
                    ips.imprimir();
                    listaIP.add(new Ipv4(ips));
                    ips.parteSUBRED.adicionarUno();

                    ip = ips;

                } while (!ip.parteSUBRED.verificarUnos());

                Ipv4 ipj = new Ipv4(ip.id, ip);
                ipj.imprimir();
                listaIP.add(new Ipv4(ipj));
                System.out.println();

                int inicioReq = 0;

                for (int i = 0; i < listaIP.size(); i++) {
                    if (listaIP.get(i).id == iterador) {
                        break;
                    }
                    inicioReq++;
                }

                // asigandor de ip a los requerimientos
                for (int i = inicioReq; i < listaIP.size(); i++) {

                    int hostSiguenteRequerimieto = 0;

                    if (listaIP.get(i).utilizable) {

                        listaIP.get(i).utilizable = false;
                        Subneteadas.add(new IPDelux(listaIP.get(i)));

                        System.out.print(requerimientos.get(iteradorRequerimientos).nombreLAN + " para \t");
                        listaIP.get(i).imprimir();

                        if (iteradorRequerimientos < requerimientos.size()) {
                            iteradorRequerimientos++;

                        } else {
                            break;
                        }

                        if (iteradorRequerimientos == requerimientos.size()) {
                            break;
                        }

                    }

                    hostSiguenteRequerimieto = requerimientos.get(iteradorRequerimientos).hosts;
                    boolean bandera = hostSiguenteRequerimieto <= numerolimitehostsInicio;

                    if (bandera) {

                        int contador = 0;

                        for (int j = inicioReq; j < listaIP.size(); j++) {
                            if (listaIP.get(j).utilizable) {
                                contador++;
                            }
                        }

                        if (contador == 0) {
                            for (int k = 0; k < listaIP.size(); k++) {
                                if (listaIP.get(k).id == iterador - 1 && listaIP.get(k).utilizable) {
                                    i = k;
                                    Ipv4 tempippadre = listaIP.get(k + 1);
                                    tempippadre.tranformarenIPPadre();
                                    ipPadreComienzo = tempippadre;
                                    break;

                                }

                            }
                            break;

                        } else {
                            Ipv4 tempippadre = listaIP.get(i + 1);
                            tempippadre.tranformarenIPPadre();
                            ipPadreComienzo = tempippadre;
                            break;
                        }
                    }
                    if (iteradorRequerimientos == listaIP.size()) {

                        insuficiencia = true;

                    }

                }
            }

            if (insuficiencia) {
                for (int i = 0; i < listaIP.size(); i++) {
                    if (listaIP.get(i).id == iterador && listaIP.get(i).utilizable) {
                        Ipv4 tempippadre = listaIP.get(i + 1);
                        tempippadre.tranformarenIPPadre();
                        ipPadreComienzo = tempippadre;
                        break;
                    }
                }

                return "No se puede seguir abasteciendo";

            }

            iterador++;
        }

        // Console.WriteLine(obtenerRedesParaSubneting());
        return obtenerRedesParaSubneting();

    }

    public String obtenerRedesParaSubneting() {
        String subets = "\n";
        int i = 0;

        for (IPDelux iP : Subneteadas) {
            subets += String.format(
                    "Para la RED: (%s | %d hosts)\nSe asignara:\n%s\n--------------------------------\n",
                    requerimientos.get(i).nombreLAN,
                    requerimientos.get(i).hosts - 2,
                    iP.Imprimir());
            i++;
        }

        return subets;
    }

    static int CalcularPotenciaDe2MasCercana(int numero) {
        // Calcular el logaritmo base 2 del número manualmente
        double log2 = Math.log(numero) / Math.log(2);

        // Redondear hacia arriba para obtener la potencia de 2 más cercana en mayor
        int potencia = (int) Math.ceil(log2);

        return potencia;
    }

    private List<Requirements> OrdenarRequerimietos(List<Requirements> listaRequerimientos) {
        List<Requirements> listaOrdenada = new ArrayList<>(listaRequerimientos);
        listaOrdenada.sort((req1, req2) -> Integer.compare(req2.hosts, req1.hosts));

        return listaOrdenada;
    }

}
