//author: Kishindelle

public class Ipv4 {
    public String parteRED;
    public Binary parteSUBRED;
    public Binary parteHOSTS;
    public String[] octetos;
    public int mascara;

    public int id;
    public boolean utilizable;

    public Ipv4(String ip, int mascara) {
        octetos = ip.split("\\.");
        String redEntera = "";
        int valorOcteto = 0;
        String BinaryOcteto = "";

        for (int i = 0; i < octetos.length; i++) {
            valorOcteto = Integer.parseInt(octetos[i]);
            BinaryOcteto = DecimalABinary(valorOcteto);
            redEntera += BinaryOcteto;
        }

        redEntera.substring(mascara);

        parteRED = redEntera.substring(0, mascara);
        parteHOSTS = new Binary(redEntera.substring(mascara));
        parteSUBRED = new Binary("");
        this.mascara = mascara;
        id = 1;
        utilizable = true;
    }

    public Ipv4(Ipv4 ip) {
        this.parteRED = ip.parteRED;
        this.parteSUBRED = new Binary(ip.parteSUBRED.getNumeroBinario());
        this.parteHOSTS = new Binary(ip.parteHOSTS.getNumeroBinario());
        this.id = ip.id;
        this.octetos = ip.octetos;
        this.utilizable = ip.utilizable;
        mascara = ip.mascara;
    }

    public Ipv4(int id, Ipv4 ip) {
        this.parteRED = ip.parteRED;
        this.parteSUBRED = new Binary(ip.parteSUBRED.getNumeroBinario());
        this.parteHOSTS = new Binary(ip.parteHOSTS.getNumeroBinario());
        this.id = id;
        this.octetos = ip.octetos;
        this.utilizable = ip.utilizable;
        mascara = ip.mascara;
    }

    public static String DecimalABinary(int numeroDecimal) {
        if (numeroDecimal == 0) {
            return "00000000";
        }

        String resultado = "";

        while (numeroDecimal > 0) {
            int residuo = numeroDecimal % 2;
            resultado = residuo + resultado;
            numeroDecimal /= 2;
        }

        while (resultado.length() < 8) {
            resultado = "0" + resultado;
        }

        return resultado;
    }

    public static String DecimalABinary(int numeroDecimal, int longitud) {
        if (numeroDecimal == 0) {
            String retorno = "";
            for (int i = 0; i < longitud; i++) {
                retorno += '0';
            }
            return retorno;
        }

        String resultado = "";

        while (numeroDecimal > 0) {
            int residuo = numeroDecimal % 2;
            resultado = residuo + resultado;
            numeroDecimal /= 2;
        }

        while (resultado.length() < longitud) {
            resultado = "0" + resultado;
        }

        return resultado;
    }

    public void imprimir() {
        System.out.print("[" + this.id + "][" + this.utilizable + "]\t");
        System.out.print(parteRED + "\t");
        System.out.print(parteSUBRED.getNumeroBinario() + "\t");
        System.out.print(parteHOSTS.getNumeroBinario() + " / " + mascara + "\t");
        System.out.println(IPFormateada());
    }

    public static int BinaryADecimal(String Binary) {
        int resultado = 0;
        int longitud = Binary.length();

        for (int i = 0; i < longitud; i++) {
            int digito = Binary.charAt(i) - '0';
            int posicion = longitud - 1 - i;
            resultado += digito * Math.pow(2, posicion);
        }

        return resultado;
    }

    public static String BinaryADecimaltoString(String Binary) {
        return Integer.toString(BinaryADecimal(Binary));
    }

    public String IPFormateada() {
        String todo = "";

        todo += this.parteRED;
        todo += this.parteSUBRED.getNumeroBinario();
        todo += this.parteHOSTS.getNumeroBinario();

        String primerOcteto = todo.substring(0, 8);
        String segundoOcteto = todo.substring(8, 8 + 8);
        String tercerOcteto = todo.substring(16, 16 + 8);
        String cuartoOcteto = todo.substring(24, 24 + 8);

        primerOcteto = BinaryADecimaltoString(primerOcteto);
        segundoOcteto = BinaryADecimaltoString(segundoOcteto);
        tercerOcteto = BinaryADecimaltoString(tercerOcteto);
        cuartoOcteto = BinaryADecimaltoString(cuartoOcteto);

        return primerOcteto + "." + segundoOcteto + "." + tercerOcteto + "." + cuartoOcteto + " / " + this.mascara;
    }

    public void tranformarenIPPadre() {
        this.parteRED = this.parteRED + this.parteSUBRED.getNumeroBinario();
        this.parteSUBRED = new Binary("");
    }

    public String GetMascaraIP() {
        String longitud = parteRED + parteSUBRED.getNumeroBinario();
        String mascara = "";

        for (int i = 0; i < longitud.length(); i++) {
            mascara += "1";
        }

        mascara += parteHOSTS.getNumeroBinario();

        String primerOcteto = mascara.substring(0, 8);
        String segundoOcteto = mascara.substring(8, 16);
        String tercerOcteto = mascara.substring(16, 24);
        String cuartoOcteto = mascara.substring(24, 32);

        primerOcteto = BinaryADecimaltoString(primerOcteto);
        segundoOcteto = BinaryADecimaltoString(segundoOcteto);
        tercerOcteto = BinaryADecimaltoString(tercerOcteto);
        cuartoOcteto = BinaryADecimaltoString(cuartoOcteto);

        return primerOcteto + "." + segundoOcteto + "." + tercerOcteto + "." + cuartoOcteto;
    }
}
