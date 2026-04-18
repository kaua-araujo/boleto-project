public class ItauBoletoBuilder implements BoletoBuilder {
    private Boleto boleto = new Boleto();

    public void construirBanco() {
        boleto.setBanco("Itau");
    }

    public void construirBeneficiario(String nome) {
        boleto.setBeneficiario(nome);
    }

    public void construirPagador(String nome) {
        boleto.setPagador(nome);
    }

    public void construirValor(double valor) {
        boleto.setValor(valor);
    }

    public void construirVencimento(String data) {
        boleto.setDataVencimento(data);
    }

    public Boleto getBoleto() {
        return boleto;
    }
}