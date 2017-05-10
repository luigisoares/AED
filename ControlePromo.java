package aed3;

import java.io.*;
import java.util.Scanner;
import java.util.*;

public class ControlePromo {

    private static Scanner console = new Scanner(System.in);
    private static ArquivoIndexado<Promocao> arquivo;

    public static void main(String[] args) {

        try {
            arquivo = new ArquivoIndexado<>(Promocao.class, "promo.db", "p1.idx", "p2.idx");

            int opcao;
            do {
                System.out.println("\n\nCADASTRO DE Pessoas\n");
                System.out.println("1 - Mostrar promoções");
                System.out.println("2 - Incluir promoção");
                System.out.println("3 - Alterar promoção");
                System.out.println("4 - Excluir promoção");
                System.out.println("5 - Buscar promoção por código");
                System.out.println("8 - Reorganizar arquivo");
                System.out.println("9 - Povoar BD");
                System.out.println("0 - Sair");
                System.out.print("\nOpcao: ");
                opcao = Integer.valueOf(console.nextLine());

                switch (opcao) {
                    case 1:
                        listarPromocao();
                        break;
                    case 2:
                        incluirPromocao();
                        break;
                    case 3:
                        alterarPromocao();
                        break;
                    case 4:
                        excluirPromocao();
                        break;
                    case 5:
                        buscarPromocaoCodigo();
                        break;
                    case 6:
                        //buscarPromocaoNome();
                        break;
                    case 7:
                        //buscarPromocaoEmail();
                        break;
                    case 8:
                        reorganizar();
                        break;
                    case 9:
                        povoar();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida");
                }

            } while (opcao != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

   //PEOPLE
    public static void listarPromocao() throws Exception {

        Object[] promocao = arquivo.listar();

        for (int i = 0; i < promocao.length; i++) {
            System.out.println((Promocao) promocao[i]);
        }
    }

    public static void incluirPromocao() throws Exception {

        String data, desc;
        int codigoEst;

        System.out.println("\nINCLUSÃO");
        System.out.print("Codigo Estabelecimento: ");
        codigoEst = Integer.valueOf(console.nextLine());
        System.out.print("Data: ");
        data = console.nextLine();
        System.out.print("Desricao: ");
        desc = console.nextLine();

        System.out.print("\nConfirma inclusão? (s ou S)");
        char confirma = console.nextLine().charAt(0);
        if (confirma == 's' || confirma == 'S') {
            Promocao u = new Promocao(-1, codigoEst, data, desc);
            int cod = arquivo.incluir(u);
            System.out.println("Promocao incluída com código: " + cod);
        }
    }

    public static void alterarPromocao() throws Exception {

        System.out.println("\nALTERAÇÃO");

        int codigo;
        System.out.print("Código: ");
        codigo = Integer.valueOf(console.nextLine());
        if (codigo <= 0) {
            return;
        }

        Promocao u;
        if ((u = (Promocao) arquivo.buscarCodigo(codigo)) != null) {
            System.out.println(u);

            String codigoEst, data, desc;

            System.out.print("\n");
            System.out.print("Novo Codigo Estabelecimento: ");
            codigoEst = console.nextLine();
            System.out.print("Nova Data: ");
            data = console.nextLine();
            System.out.print("Nova Descricao: ");
            desc = console.nextLine();

            System.out.print("\nConfirma alteração? (s ou S) ");
            char confirma = console.nextLine().charAt(0);
            if (confirma == 's' || confirma == 'S') {

                u.codigoEstabelecimento = (codigoEst.length() > 0 ? Integer.valueOf(codigoEst) : u.codigoEstabelecimento);
                u.data = (data.length() > 0 ? data : u.data);
                u.descricao = (desc.length() > 0 ? desc : u.descricao);

                if (arquivo.alterar(u)) {
                    System.out.println("Promocao alterada.");
                } else {
                    System.out.println("Promocao não pode ser alterada.");
                }
            }
        } else {
            System.out.println("Promocao não encontrada");
        }

    }

    public static void excluirPromocao() throws Exception {

        System.out.println("\nEXCLUSÃO");

        int codigo;
        System.out.print("Código: ");
        codigo = Integer.valueOf(console.nextLine());
        if (codigo <= 0) {
            return;
        }

        Promocao u;
        if ((u = (Promocao) arquivo.buscarCodigo(codigo)) != null) {
            System.out.println(u);
            System.out.print("\nConfirma exclusão? (s ou S)");
            char confirma = console.nextLine().charAt(0);
            if (confirma == 's' || confirma == 'S') {
                if (arquivo.excluir(codigo)) {
                    System.out.println("Promocao excluído.");
                }
            }
        } else {
            System.out.println("Promocao não encontrado");
        }

    }

    public static void buscarPromocaoCodigo() throws Exception {

        System.out.println("\nBUSCA POR CÓDIGO");

        int codigo;
        System.out.print("Código: ");
        codigo = Integer.valueOf(console.nextLine());
        if (codigo <= 0) {
            return;
        }

        Promocao u;
        if ((u = (Promocao) arquivo.buscarCodigo(codigo)) != null) {
            System.out.println(u);
        } else {
            System.out.println("Promocao não encontrada");
        }
    }


    public static void reorganizar() throws Exception {

        System.out.println("\nREORGANIZAÇÃO");
        arquivo.reorganizar();
        System.out.println("\nArquivo de promocao reorganizado");

    }

    public static void povoar() throws Exception {
        arquivo.incluir(new Promocao(-1, 1, "09/05/2017", "MELHOR PROMO"));
        arquivo.incluir(new Promocao(-1, 2, "09/05/2017", "legal"));
        arquivo.incluir(new Promocao(-1, 3, "09/05/2017", "legal"));
        arquivo.incluir(new Promocao(-1, 4, "09/05/2017", "sem promo"));
        arquivo.incluir(new Promocao(-1, 5, "09/05/2017", "sem desc"));
        arquivo.incluir(new Promocao(-1, 6, "09/05/2017", "amor d+ legal"));
        arquivo.incluir(new Promocao(-1, 7, "09/05/2017", "legal d+"));
        arquivo.incluir(new Promocao(-1, 8, "09/05/2017", "legalzao"));
        arquivo.incluir(new Promocao(-1, 9, "09/05/2017", "legalasss"));
        arquivo.incluir(new Promocao(-1, 10, "09/05/2017", "legal9"));
        arquivo.incluir(new Promocao(-1, 11, "09/05/2017", "legal5"));
        arquivo.incluir(new Promocao(-1, 12, "09/05/2017", "legalas"));
        arquivo.incluir(new Promocao(-1, 13, "09/05/2017", "legala"));
        arquivo.incluir(new Promocao(-1, 14, "09/05/2017", "legal1"));
        arquivo.incluir(new Promocao(-1, 15, "09/05/2017", "legal"));
        //arquivo.incluir(new Promocao(-1, null, (float)0.0, (float)0.0, null, null, null));
    }

}
