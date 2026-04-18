public class Boleto {
    private String banco;
    private String beneficiario;
    private String pagador;
    private double valor;
    private String dataVencimento;

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public void setPagador(String pagador) {
        this.pagador = pagador;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getBanco() {
        return banco;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public String getPagador() {
        return pagador;
    }

    public double getValor() {
        return valor;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }
}