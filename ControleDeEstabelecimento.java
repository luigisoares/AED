package aed3;

import java.io.*;
import java.util.Scanner;
import java.util.*;

public class ControleDeEstabelecimento {

    private static Scanner console = new Scanner(System.in);
    private static ArquivoIndexado<Estabelecimento> arquivo;

    public static void main(String[] args) {

        try {
            arquivo = new ArquivoIndexado<>(Estabelecimento.class, "estabelecimento.db", "e1.idx", "e2.idx");

            // menu
            int opcao;
            do {
                System.out.println("\n\nCADASTRO DE Estabelecimento\n");
                System.out.println("1 - Mostrar estabelecimentos");
                System.out.println("2 - Incluir estabelecimento");
                System.out.println("3 - Alterar estabelecimento");
                System.out.println("4 - Excluir estabelecimento");
                System.out.println("5 - Buscar estabelecimento por código");
                System.out.println("6 - Buscar estabelecimento por nome");
                System.out.println("7 - Reorganizar arquivo");
                System.out.println("8 - Povoar BD");
                System.out.println("0 - Sair");
                System.out.print("\nOpcao: ");
                opcao = Integer.valueOf(console.nextLine());

                switch (opcao) {
                    case 1:
                        listarEstabelecimento();
                        break;
                    case 2:
                        incluirEstabelecimento();
                        break;
                    case 3:
                        alterarEstabelecimento();
                        break;
                    case 4:
                        excluirEstabelecimento();
                        break;
                    case 5:
                        buscarEstabelecimentoCodigo();
                        break;
                    case 6:
                        buscarEstabelecimentoNome();
                        break;
                    case 7:
                        reorganizar();
                        break;
                    case 8:
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

    public static void listarEstabelecimento() throws Exception {

        Object[] est = arquivo.listar();

        for (int i = 0; i < est.length; i++) {
            System.out.println((Usuario) est[i]);
        }
    }

    public static void incluirEstabelecimento() throws Exception {

        String nome, foto, descricao;
        float latitude, longitude, notaMedia;
        int qntAva;

        System.out.println("\nINCLUSÃO");
        System.out.print("Nome: ");
        nome = console.nextLine();
        System.out.print("Foto: ");
        foto = console.nextLine();
        System.out.print("Latitude: ");
        latitude = Float.valueOf(console.nextLine());
        System.out.print("Longitude: ");
        longitude = Float.valueOf(console.nextLine());
        System.out.print("Descricao: ");
        descricao = console.nextLine();
        System.out.print("Nota: ");
        notaMedia = Float.valueOf(console.nextLine());
        System.out.print("Quantidade Avaliações: ");
        qntAva = Integer.valueOf(console.nextLine());

        System.out.print("\nConfirma inclusão? (s ou S)");
        char confirma = console.nextLine().charAt(0);
        if (confirma == 's' || confirma == 'S') {
            Estabelecimento e = new Estabelecimento(-1, nome, foto, latitude, longitude, descricao, notaMedia, qntAva);
            int cod = arquivo.incluir(e);
            System.out.println("Estabelecimento incluído com código: " + cod);
        }
    }

    public static void alterarEstabelecimento() throws Exception {

        System.out.println("\nALTERAÇÃO");

        int codigo;
        System.out.print("Código: ");
        codigo = Integer.valueOf(console.nextLine());
        if (codigo <= 0) {
            return;
        }

        Estabelecimento e;
        if ((e = (Estabelecimento) arquivo.buscarCodigo(codigo)) != null) {
            System.out.println(e);

            String nome, foto, descricao, latitude, longitude, nota, qntAva;

            System.out.print("\n");
            System.out.print("Novo Nome: ");
            nome = console.nextLine();
            System.out.print("Novo Foto: ");
            foto = console.nextLine();
            System.out.print("Nova Latitude: ");
            latitude = console.nextLine();
            System.out.print("Nova Longitude: ");
            longitude = console.nextLine();
            System.out.print("Nova Descrição: ");
            descricao = console.nextLine();
            System.out.print("Nova Nota: ");
            nota = console.nextLine();
            System.out.print("Nova Quantidade Avaliações: ");
            qntAva = console.nextLine();

            System.out.print("\nConfirma alteração? (s ou S) ");
            char confirma = console.nextLine().charAt(0);
            if (confirma == 's' || confirma == 'S') {

                e.nome = (nome.length() > 0 ? nome : e.nome);
                e.foto = (foto.length() > 0 ? foto : e.foto);
                e.longitude = (longitude.length() > 0 ? Float.valueOf(longitude) : e.longitude);
                e.latitude = (latitude.length() > 0 ? Float.valueOf(latitude) : e.latitude);
                e.descricao = (descricao.length() > 0 ? descricao : e.foto);
                e.notaMedia = (nota.length() > 0 ? Float.valueOf(nota) : e.notaMedia);
                e.qntAvaliacoes = (qntAva.length() > 0 ? Integer.valueOf(qntAva) : e.qntAvaliacoes);

                if (arquivo.alterar(e)) {
                    System.out.println("Estabelecimento alterado.");
                } else {
                    System.out.println("Estabelecimento não pode ser alterado.");
                }
            }
        } else {
            System.out.println("Estabelecimento não encontrado");
        }

    }

    public static void excluirEstabelecimento() throws Exception {

        System.out.println("\nEXCLUSÃO");

        int codigo;
        System.out.print("Código: ");
        codigo = Integer.valueOf(console.nextLine());
        if (codigo <= 0) {
            return;
        }

        Estabelecimento e;
        if ((e = (Estabelecimento) arquivo.buscarCodigo(codigo)) != null) {
            System.out.println(e);
            System.out.print("\nConfirma exclusão? (s ou S)");
            char confirma = console.nextLine().charAt(0);
            if (confirma == 's' || confirma == 'S') {
                if (arquivo.excluir(codigo)) {
                    System.out.println("Estabelecimento excluído.");
                }
            }
        } else {
            System.out.println("Estabelecimento não encontrado");
        }

    }

    public static void buscarEstabelecimentoCodigo() throws Exception {

        System.out.println("\nBUSCA POR CÓDIGO");

        int codigo;
        System.out.print("Código: ");
        codigo = Integer.valueOf(console.nextLine());
        if (codigo <= 0) {
            return;
        }

        Estabelecimento e;
        if ((e = (Estabelecimento) arquivo.buscarCodigo(codigo)) != null) {
            System.out.println(e);
        } else {
            System.out.println("Estabelecimento não encontrado");
        }
    }

    public static void buscarEstabelecimentoNome() throws Exception {

        System.out.println("\nBUSCA POR NOME");

        String nome;
        System.out.print("Nome: ");
        nome = console.nextLine();
        if (nome == "") {
            return;
        }

        Estabelecimento e;
        if ((e = (Estabelecimento) arquivo.buscarString(nome)) != null) {
            System.out.println(e);
        } else {
            System.out.println("Estabelecimento não encontrado");
        }
    }
    
    public static void reorganizar() throws Exception {

        System.out.println("\nREORGANIZAÇÃO");
        arquivo.reorganizar();
        System.out.println("\nArquivo de estabelecimento reorganizado");

    }
    
    public static void povoar() throws Exception {
        arquivo.incluir(new Estabelecimento(-1, "Luigi Teste","Luigi Teste", (float) 27.8, (float) 7827.9, "url:123", (float) 9.9, 1234));
        arquivo.incluir(new Estabelecimento(-1, "Luigi Teste2","Luigi Teste", (float) 127.8, (float) 6627.9, "url:123", (float) 9.9, 1234));
        arquivo.incluir(new Estabelecimento(-1, "Luigi Teste3","Luigi Teste", (float) 273.8, (float) 1427.9, "url:123", (float) 9.9, 1234));
        arquivo.incluir(new Estabelecimento(-1, "Luigi Teste4","Luigi Teste", (float) 275.8, (float) 1327.9, "url:123", (float) 9.9, 1234));
        arquivo.incluir(new Estabelecimento(-1, "Luigi Teste5","Luigi Teste", (float) 278.8, (float) 1527.9, "url:123", (float) 9.9, 1234));
        arquivo.incluir(new Estabelecimento(-1, "Luigi Teste6","Luigi Teste", (float) 279.8, (float) 1427.9, "url:123", (float) 9.9, 1234));
        arquivo.incluir(new Estabelecimento(-1, "Luigi Teste7","Luigi Teste", (float) 277.8, (float) 9827.9, "url:123", (float) 9.9, 1234));
        arquivo.incluir(new Estabelecimento(-1, "Luigi Teste8","Luigi Teste", (float) 279.8, (float) 8927.9, "url:123", (float) 9.9, 1234));
        arquivo.incluir(new Estabelecimento(-1, "Luigi Teste9","Luigi Teste", (float) 427.8, (float) 627.9, "url:123", (float) 9.9, 1234));
        arquivo.incluir(new Estabelecimento(-1, "Luigi Teste11","Luigi Teste", (float) 927.8, (float) 427.9, "url:123", (float) 9.9, 1234));
        arquivo.incluir(new Estabelecimento(-1, "Luigi Teste12","Luigi Teste", (float) 257.8, (float) 7727.9, "url:123", (float) 9.9, 1234));
        arquivo.incluir(new Estabelecimento(-1, "Luigi Teste13","Luigi Teste", (float) 727.8, (float) 927.9, "url:123", (float) 9.9, 1234));
        arquivo.incluir(new Estabelecimento(-1, "Luigi Teste14","Luigi Teste", (float) 9827.8, (float) 527.9, "url:123", (float) 9.9, 1234));
        arquivo.incluir(new Estabelecimento(-1, "Luigi Teste15","Luigi Teste", (float) 2777.8, (float) 227.9, "url:123", (float) 9.9, 1234));
        arquivo.incluir(new Estabelecimento(-1, "Luigi Teste16","Luigi Teste", (float) 279.8, (float) 127.9, "url:123", (float) 9.9, 1234));
        //arquivo.incluir(new Usuario(-1, null, (float)0.0, (float)0.0, null, null, null));
    }

}
