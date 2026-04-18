import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;

public class GeradorPDF {

    public static void gerar(Boleto boleto) {

        try {
            Document doc = new Document();
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("boleto.pdf"));
            doc.open();

            Font normal = new Font(Font.FontFamily.HELVETICA, 8);
            Font bold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);

            String codigoBarras = gerarCodigoBarras(boleto);
            String linhaDigitavel = gerarLinhaDigitavel(codigoBarras);

            PdfPTable header = new PdfPTable(3);
            header.setWidthPercentage(100);
            header.setWidths(new float[] { 2, 2, 6 });

            Image logo = carregarLogo(boleto.getBanco());

            header.addCell(cellImagem(logo));
            header.addCell(cell("Banco: " + boleto.getBanco(), bold));
            header.addCell(cell(linhaDigitavel, bold));

            doc.add(header);
            doc.add(new Paragraph(" "));

            PdfPTable tabela = new PdfPTable(2);
            tabela.setWidthPercentage(100);

            tabela.addCell(cell("Beneficiário:\n" + boleto.getBeneficiario(), normal));
            tabela.addCell(cell("Vencimento:\n" + boleto.getDataVencimento(), normal));

            tabela.addCell(cell("Pagador:\n" + boleto.getPagador(), normal));
            tabela.addCell(cell("Valor:\nR$ " + boleto.getValor(), normal));

            tabela.addCell(cell("Nosso Número:\n123456789", normal));
            tabela.addCell(cell("Agência:\n0001", normal));

            doc.add(tabela);

            doc.add(new Paragraph(" "));

            BarcodeInter25 barcode = new BarcodeInter25();
            barcode.setCode(codigoBarras);
            barcode.setBarHeight(40f);

            Image img = barcode.createImageWithBarcode(writer.getDirectContent(), null, null);
            img.setAlignment(Element.ALIGN_CENTER);

            doc.add(img);

            Paragraph cod = new Paragraph(codigoBarras, normal);
            cod.setAlignment(Element.ALIGN_CENTER);
            doc.add(cod);

            doc.close();

            System.out.println("Boleto gerado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Image carregarLogo(String banco) {
        try {
            String caminho = "";

            switch (banco.toLowerCase()) {
                case "nubank":
                    caminho = "img/nubank.png";
                    break;
                case "itau":
                    caminho = "img/itau.png";
                    break;
                case "bradesco":
                    caminho = "img/bradesco.png";
                    break;
                default:
                    return null;
            }

            Image img = Image.getInstance(caminho);
            img.scaleToFit(80, 40);
            return img;

        } catch (Exception e) {
            System.out.println("Erro ao carregar logo");
            return null;
        }
    }

    private static String gerarCodigoBarras(Boleto boleto) {

        String banco = boleto.getBanco().equalsIgnoreCase("Nubank") ? "260"
                : boleto.getBanco().equalsIgnoreCase("Itaú") ? "341"
                        : boleto.getBanco().equalsIgnoreCase("Bradesco") ? "237" : "000";

        String valor = String.format("%010d", (int) (boleto.getValor() * 100));
        String data = boleto.getDataVencimento().replaceAll("[^0-9]", "");
        String random = String.format("%010d", (int) (Math.random() * 1000000000));

        String codigo = banco + data + valor + random;

        while (codigo.length() < 44) {
            codigo += "0";
        }

        if (codigo.length() % 2 != 0) {
            codigo = "0" + codigo;
        }

        return codigo;
    }

    private static String gerarLinhaDigitavel(String codigo) {

        return codigo.substring(0, 5) + "." +
                codigo.substring(5, 10) + " " +
                codigo.substring(10, 15) + "." +
                codigo.substring(15, 20) + " " +
                codigo.substring(20, 25) + "." +
                codigo.substring(25, 30) + " " +
                codigo.substring(30, 31) + " " +
                codigo.substring(31);
    }

    private static PdfPCell cell(String texto, Font fonte) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, fonte));
        cell.setPadding(5);
        cell.setBorderWidth(1);
        return cell;
    }

    private static PdfPCell cellImagem(Image img) {
        if (img == null) {
            PdfPCell vazio = new PdfPCell(new Phrase(""));
            vazio.setBorder(0);
            return vazio;
        }

        PdfPCell cell = new PdfPCell(img, true);
        cell.setBorder(0);
        return cell;
    }
}