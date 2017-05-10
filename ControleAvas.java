package aed3;

import java.io.*;
import java.util.Scanner;
import java.util.*;

public class ControleAvas {

    private static Scanner console = new Scanner(System.in);
    private static ArquivoIndexado<Avaliacao> arquivo;

    public static void main(String[] args) {

        try {
            arquivo = new ArquivoIndexado<>(Avaliacao.class, "avas.db", "a1.idx", "a2.idx");

            // menu
            int opcao;
            do {
                System.out.println("\n\nCADASTRO DE Pessoas\n");
                System.out.println("1 - Mostrar avaliações");
                System.out.println("2 - Incluir avaliação");
                System.out.println("3 - Alterar avaliação");
                System.out.println("4 - Excluir avaliação");
                System.out.println("5 - Buscar avaliação por código");
                System.out.println("8 - Reorganizar arquivo");
                System.out.println("9 - Povoar BD");
                System.out.println("0 - Sair");
                System.out.print("\nOpcao: ");
                opcao = Integer.valueOf(console.nextLine());

                switch (opcao) {
                    case 1:
                        listarAvaliacao();
                        break;
                    case 2:
                        incluirAvaliacao();
                        break;
                    case 3:
                        alterarAvaliacao();
                        break;
                    case 4:
                        excluirAvaliacao();
                        break;
                    case 5:
                        buscarAvaliacaoCodigo();
                        break;
                    case 6:
                        //buscarAvaliacaoNome();
                        break;
                    case 7:
                        //buscarAvaliacaoEmail();
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
    public static void listarAvaliacao() throws Exception {

        Object[] avaliacao = arquivo.listar();

        for (int i = 0; i < avaliacao.length; i++) {
            System.out.println((Avaliacao) avaliacao[i]);
        }
    }

    public static void incluirAvaliacao() throws Exception {

        String data, comentario, foto;
        int codigoEst, codigoUsu, nota;

        System.out.println("\nINCLUSÃO");
        System.out.print("Codigo Usuario: ");
        codigoUsu = Integer.valueOf(console.nextLine());
        System.out.print("Codigo Estabelecimento: ");
        codigoEst = Integer.valueOf(console.nextLine());
        System.out.print("Cometario: ");
        comentario = console.nextLine();
        System.out.print("Foto: ");
        foto = console.nextLine();
        System.out.print("Nota: ");
        nota = Integer.valueOf(console.nextLine());
        System.out.print("Data: ");
        data = console.nextLine();

        System.out.print("\nConfirma inclusão? (s ou S)");
        char confirma = console.nextLine().charAt(0);
        if (confirma == 's' || confirma == 'S') {
            Avaliacao u = new Avaliacao(-1, codigoUsu, codigoEst, comentario,foto,nota,data);
            int cod = arquivo.incluir(u);
            System.out.println("Avaliacao incluída com código: " + cod);
        }
    }

    public static void alterarAvaliacao() throws Exception {

        System.out.println("\nALTERAÇÃO");

        int codigo;
        System.out.print("Código: ");
        codigo = Integer.valueOf(console.nextLine());
        if (codigo <= 0) {
            return;
        }

        Avaliacao u;
        if ((u = (Avaliacao) arquivo.buscarCodigo(codigo)) != null) {
            System.out.println(u);

            String codigoEst, codigoUsu, nota, data, comentario, foto;

            System.out.print("\n");
            System.out.print("Novo Codigo Usuario: ");
            codigoUsu = console.nextLine();
            System.out.print("Novo Codigo Estabelecimento: ");
            codigoEst = console.nextLine();
            System.out.print("Novo Comentario: ");
            comentario = console.nextLine();
            System.out.print("Nova Foto: ");
            foto = console.nextLine();
            System.out.print("Novo Nota: ");
            nota = console.nextLine();
            System.out.print("Nova Data: ");
            data = console.nextLine();
            

            System.out.print("\nConfirma alteração? (s ou S) ");
            char confirma = console.nextLine().charAt(0);
            if (confirma == 's' || confirma == 'S') {

                u.codigoUsuario = (codigoUsu.length() > 0 ? Integer.valueOf(codigoUsu) : u.codigoUsuario);
                u.codigoEstabelecimento = (codigoEst.length() > 0 ? Integer.valueOf(codigoEst) : u.codigoEstabelecimento);
                u.comentario = (comentario.length() > 0 ? comentario : u.comentario);
                u.foto = (foto.length() > 0 ? foto : u.foto);
                u.nota = (nota.length() > 0 ? Integer.valueOf(nota) : u.nota);
                u.data = (data.length() > 0 ? data : u.data);

                if (arquivo.alterar(u)) {
                    System.out.println("Avaliacao alterada.");
                } else {
                    System.out.println("Avaliacao não pode ser alterada.");
                }
            }
        } else {
            System.out.println("Avaliacao não encontrada");
        }

    }

    public static void excluirAvaliacao() throws Exception {

        System.out.println("\nEXCLUSÃO");

        int codigo;
        System.out.print("Código: ");
        codigo = Integer.valueOf(console.nextLine());
        if (codigo <= 0) {
            return;
        }

        Avaliacao u;
        if ((u = (Avaliacao) arquivo.buscarCodigo(codigo)) != null) {
            System.out.println(u);
            System.out.print("\nConfirma exclusão? (s ou S)");
            char confirma = console.nextLine().charAt(0);
            if (confirma == 's' || confirma == 'S') {
                if (arquivo.excluir(codigo)) {
                    System.out.println("Avaliacao excluído.");
                }
            }
        } else {
            System.out.println("Avaliacao não encontrado");
        }

    }

    public static void buscarAvaliacaoCodigo() throws Exception {

        System.out.println("\nBUSCA POR CÓDIGO");

        int codigo;
        System.out.print("Código: ");
        codigo = Integer.valueOf(console.nextLine());
        if (codigo <= 0) {
            return;
        }

        Avaliacao u;
        if ((u = (Avaliacao) arquivo.buscarCodigo(codigo)) != null) {
            System.out.println(u);
        } else {
            System.out.println("Avaliacao não encontrada");
        }
    }


    public static void reorganizar() throws Exception {

        System.out.println("\nREORGANIZAÇÃO");
        arquivo.reorganizar();
        System.out.println("\nArquivo de avaliacao reorganizado");

    }

    public static void povoar() throws Exception {
        arquivo.incluir(new Avaliacao(-1, 1, 1, "bacana", "FOTO N TEM",1,"09/05/2017"));
        arquivo.incluir(new Avaliacao(-1, 2, 2, "bacana", "FOTO N TEM",1,"09/05/2017"));
        arquivo.incluir(new Avaliacao(-1, 3, 3, "bacana", "FOTO N TEM",1,"09/05/2017"));
        arquivo.incluir(new Avaliacao(-1, 4, 4, "bacana", "FOTO N TEM",1,"09/05/2017"));
        arquivo.incluir(new Avaliacao(-1, 5, 5, "bacana", "FOTO N TEM",1,"09/05/2017"));
        arquivo.incluir(new Avaliacao(-1, 6, 6, "bacana", "FOTO N TEM",1,"09/05/2017"));
        arquivo.incluir(new Avaliacao(-1, 7, 7, "bacana", "FOTO N TEM",1,"09/05/2017"));
        arquivo.incluir(new Avaliacao(-1, 8, 8, "bacana", "FOTO N TEM",1,"09/05/2017"));
        arquivo.incluir(new Avaliacao(-1, 9, 9, "bacana", "FOTO N TEM",1,"09/05/2017"));
        //arquivo.incluir(new Avaliacao(-1, null, (float)0.0, (float)0.0, null, null, null));
    }

}
