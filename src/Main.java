import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Diretor diretor = new Diretor();

        System.out.println("1 - Nubank | 2 - Itaú | 3 - Bradesco");
        int op = sc.nextInt();
        sc.nextLine();

        System.out.print("Beneficiário: ");
        String beneficiario = sc.nextLine();

        System.out.print("Pagador: ");
        String pagador = sc.nextLine();

        System.out.print("Valor: ");
        double valor = sc.nextDouble();
        sc.nextLine();

        System.out.print("Vencimento (dd/mm/yyyy): ");
        String data = sc.nextLine();

        BoletoBuilder builder;

        switch (op) {
            case 1:
                builder = new NubankBoletoBuilder();
                break;
            case 2:
                builder = new ItauBoletoBuilder();
                break;
            case 3:
                builder = new BradescoBoletoBuilder();
                break;
            default:
                System.out.println("Inválido");
                return;
        }

        diretor.construirBoleto(builder, beneficiario, pagador, valor, data);

        Boleto boleto = builder.getBoleto();

        GeradorPDF.gerar(boleto);

        sc.close();
    }
}