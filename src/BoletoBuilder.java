public interface BoletoBuilder {
    void construirBanco();

    void construirBeneficiario(String nome);

    void construirPagador(String nome);

    void construirValor(double valor);

    void construirVencimento(String data);

    Boleto getBoleto();
}