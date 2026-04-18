public class Diretor {

    public void construirBoleto(
            BoletoBuilder builder,
            String beneficiario,
            String pagador,
            double valor,
            String data) {

        builder.construirBanco();
        builder.construirBeneficiario(beneficiario);
        builder.construirPagador(pagador);
        builder.construirValor(valor);
        builder.construirVencimento(data);
    }
}