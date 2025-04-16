//author: Kishindelle

public class Binary {
    private String numeroBinario;

    public Binary(String binario) {
        this.numeroBinario = binario;
    }

    public String getNumeroBinario() {
        return this.numeroBinario;
    }

    public void setNumeroBinario(String numeroBinario) {
        this.numeroBinario = numeroBinario;
    }

    public void adicionarUno() {
        // Verificar si aún se pueden realizar adiciones
        if (!verificarUnos()) {
            // Convertir el número binario a entero
            int decimalValue = Ipv4.BinaryADecimal(numeroBinario);

            // Adicionar 1 al valor decimal
            decimalValue++;

            // Convertir el nuevo valor decimal de nuevo a binario
            this.numeroBinario = Ipv4.DecimalABinary(decimalValue, numeroBinario.length());

        } else {
            System.out.println("No se pueden realizar más adiciones. Se alcanzó el número máximo de adiciones.");
        }
    }

    public void restarUno() {
        // Verificar si aún se pueden realizar adiciones
        if (!verificarUnos()) {
            // Convertir el número binario a entero
            int decimalValue = Ipv4.BinaryADecimal(numeroBinario);

            // Adicionar 1 al valor decimal
            decimalValue--;

            // Convertir el nuevo valor decimal de nuevo a binario
            this.numeroBinario = Ipv4.DecimalABinary(decimalValue, numeroBinario.length());

        } else {
            System.out.println("No se pueden realizar más adiciones. Se alcanzó el número máximo de adiciones.");
        }
    }

    public boolean verificarUnos() {
        boolean bandera = true;
        int maximo = numeroBinario.length();

        for (int i = 0; i < maximo; i++) {
            if (numeroBinario.charAt(i) != '1') {
                bandera = false;
            }
        }

        return bandera;
    }

    public boolean verificarUnosHastaBroadcast() {
        boolean bandera = true;
        int maximo = numeroBinario.length();

        for (int i = 0; i < maximo - 1; i++) {
            if (numeroBinario.charAt(i) != '1') {
                bandera = false;
            }
        }

        return bandera;
    }

}
