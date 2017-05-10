package aed3;

import java.io.*;
import java.util.Scanner;
import java.util.*;

public class ControleDeUsuarios {

    private static Scanner console = new Scanner(System.in);
    private static ArquivoIndexado<Usuario> arquivo;

    public static void main(String[] args) {

        try {
            arquivo = new ArquivoIndexado<>(Usuario.class, "usuarios.db", "u1.idx", "u2.idx");

            // menu
            int opcao;
            do {
                System.out.println("\n\nCADASTRO DE Pessoas\n");
                System.out.println("1 - Mostrar pessoas");
                System.out.println("2 - Incluir pessoa");
                System.out.println("3 - Alterar pessoa");
                System.out.println("4 - Excluir pessoa");
                System.out.println("5 - Buscar usuário por código");
                System.out.println("6 - Buscar usuário por nome");
                System.out.println("7 - Buscar usuário por email");
                System.out.println("8 - Reorganizar arquivo");
                System.out.println("9 - Povoar BD");
                System.out.println("0 - Sair");
                System.out.print("\nOpcao: ");
                opcao = Integer.valueOf(console.nextLine());

                switch (opcao) {
                    case 1:
                        listarUsuario();
                        break;
                    case 2:
                        incluirUsuario();
                        break;
                    case 3:
                        alterarUsuario();
                        break;
                    case 4:
                        excluirUsuario();
                        break;
                    case 5:
                        buscarUsuarioCodigo();
                        break;
                    case 6:
                        buscarUsuarioNome();
                        break;
                    case 7:
                        buscarUsuarioEmail();
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
    public static void listarUsuario() throws Exception {

        Object[] usuarios = arquivo.listar();

        for (int i = 0; i < usuarios.length; i++) {
            System.out.println((Usuario) usuarios[i]);
        }
    }

    public static void incluirUsuario() throws Exception {

        String nome, foto, senha, email;
        float latitude, longitude;

        System.out.println("\nINCLUSÃO");
        System.out.print("Nome: ");
        nome = console.nextLine();
        System.out.print("Latitude: ");
        latitude = Float.valueOf(console.nextLine());
        System.out.print("Longitude: ");
        longitude = Float.valueOf(console.nextLine());
        System.out.print("FotoURL: ");
        foto = console.nextLine();
        System.out.print("Email: ");
        email = console.nextLine();
        System.out.print("Senha: ");
        senha = console.nextLine();

        System.out.print("\nConfirma inclusão? (s ou S)");
        char confirma = console.nextLine().charAt(0);
        if (confirma == 's' || confirma == 'S') {
            Usuario u = new Usuario(-1, nome, latitude, longitude, foto, email, senha);
            int cod = arquivo.incluir(u);
            System.out.println("Usuario incluído com código: " + cod);
        }
    }

    public static void alterarUsuario() throws Exception {

        System.out.println("\nALTERAÇÃO");

        int codigo;
        System.out.print("Código: ");
        codigo = Integer.valueOf(console.nextLine());
        if (codigo <= 0) {
            return;
        }

        Usuario u;
        if ((u = (Usuario) arquivo.buscarCodigo(codigo)) != null) {
            System.out.println(u);

            String nome, foto, senha, email, latitude, longitude;

            System.out.print("\n");
            System.out.print("Novo Nome: ");
            nome = console.nextLine();
            System.out.print("Nova Latitude: ");
            latitude = console.nextLine();
            System.out.print("Nova Longitude: ");
            longitude = console.nextLine();
            System.out.print("Nova FotoURL: ");
            foto = console.nextLine();
            System.out.print("Novo Email: ");
            email = console.nextLine();
            System.out.print("Nova Senha: ");
            senha = console.nextLine();

            System.out.print("\nConfirma alteração? (s ou S) ");
            char confirma = console.nextLine().charAt(0);
            if (confirma == 's' || confirma == 'S') {

                u.nome = (nome.length() > 0 ? nome : u.nome);
                u.longitude = (longitude.length() > 0 ? Float.valueOf(longitude) : u.longitude);
                u.latitude = (latitude.length() > 0 ? Float.valueOf(latitude) : u.latitude);
                u.foto = (foto.length() > 0 ? foto : u.foto);
                u.email = (email.length() > 0 ? email : u.email);
                u.senha = (senha.length() > 0 ? senha : u.senha);

                if (arquivo.alterar(u)) {
                    System.out.println("Usuario alterado.");
                } else {
                    System.out.println("Usuario não pode ser alterado.");
                }
            }
        } else {
            System.out.println("Usuario não encontrado");
        }

    }

    public static void excluirUsuario() throws Exception {

        System.out.println("\nEXCLUSÃO");

        int codigo;
        System.out.print("Código: ");
        codigo = Integer.valueOf(console.nextLine());
        if (codigo <= 0) {
            return;
        }

        Usuario u;
        if ((u = (Usuario) arquivo.buscarCodigo(codigo)) != null) {
            System.out.println(u);
            System.out.print("\nConfirma exclusão? (s ou S)");
            char confirma = console.nextLine().charAt(0);
            if (confirma == 's' || confirma == 'S') {
                if (arquivo.excluir(codigo)) {
                    System.out.println("Usuario excluído.");
                }
            }
        } else {
            System.out.println("Usuario não encontrado");
        }

    }

    public static void buscarUsuarioCodigo() throws Exception {

        System.out.println("\nBUSCA POR CÓDIGO");

        int codigo;
        System.out.print("Código: ");
        codigo = Integer.valueOf(console.nextLine());
        if (codigo <= 0) {
            return;
        }

        Usuario u;
        if ((u = (Usuario) arquivo.buscarCodigo(codigo)) != null) {
            System.out.println(u);
        } else {
            System.out.println("Usuario não encontrado");
        }
    }

    public static void buscarUsuarioNome() throws Exception {

        System.out.println("\nBUSCA POR NOME");

        String nome;
        System.out.print("Nome: ");
        nome = console.nextLine();
        if (nome == "") {
            return;
        }

        Usuario u;
        if ((u = (Usuario) arquivo.buscarString(nome)) != null) {
            System.out.println(u);
        } else {
            System.out.println("Usuario não encontrado");
        }
    }

    public static void buscarUsuarioEmail() throws Exception {

        System.out.println("\nBUSCA POR EMAIL");

        String email;
        System.out.print("Email: ");
        email = console.nextLine();
        if (email == "") {
            return;
        }

        Usuario u;
        if ((u = (Usuario) arquivo.buscarString(email)) != null) {
            System.out.println(u);
        } else {
            System.out.println("Usuario não encontrado");
        }
    }

    public static void reorganizar() throws Exception {

        System.out.println("\nREORGANIZAÇÃO");
        arquivo.reorganizar();
        System.out.println("\nArquivo de usuarios reorganizado");

    }

    public static void povoar() throws Exception {
        arquivo.incluir(new Usuario(-1, "Luigi Teste", (float) 27.8, (float) 7827.9, "url:123", "luigi@luigi.com", "1234"));
        arquivo.incluir(new Usuario(-1, "Luigi Teste2", (float) 127.8, (float) 6627.9, "url:123", "luigi@luigi.com", "1234"));
        arquivo.incluir(new Usuario(-1, "Luigi Teste3", (float) 273.8, (float) 1427.9, "url:123", "luigi@luigi.com", "1234"));
        arquivo.incluir(new Usuario(-1, "Luigi Teste4", (float) 275.8, (float) 1327.9, "url:123", "luigi@luigi.com", "1234"));
        arquivo.incluir(new Usuario(-1, "Luigi Teste5", (float) 278.8, (float) 1527.9, "url:123", "luigi@luigi.com", "1234"));
        arquivo.incluir(new Usuario(-1, "Luigi Teste6", (float) 279.8, (float) 1427.9, "url:123", "luigi@luigi.com", "1234"));
        arquivo.incluir(new Usuario(-1, "Luigi Teste7", (float) 277.8, (float) 9827.9, "url:123", "luigi@luigi.com", "1234"));
        arquivo.incluir(new Usuario(-1, "Luigi Teste8", (float) 279.8, (float) 8927.9, "url:123", "luigi@luigi.com", "1234"));
        arquivo.incluir(new Usuario(-1, "Luigi Teste9", (float) 427.8, (float) 627.9, "url:123", "luigi@luigi.com", "1234"));
        arquivo.incluir(new Usuario(-1, "Luigi Teste11", (float) 927.8, (float) 427.9, "url:123", "luigi@luigi.com", "1234"));
        arquivo.incluir(new Usuario(-1, "Luigi Teste12", (float) 257.8, (float) 7727.9, "url:123", "luigi@luigi.com", "1234"));
        arquivo.incluir(new Usuario(-1, "Luigi Teste13", (float) 727.8, (float) 927.9, "url:123", "luigi@luigi.com", "1234"));
        arquivo.incluir(new Usuario(-1, "Luigi Teste14", (float) 9827.8, (float) 527.9, "url:123", "luigi@luigi.com", "1234"));
        arquivo.incluir(new Usuario(-1, "Luigi Teste15", (float) 2777.8, (float) 227.9, "url:123", "luigi@luigi.com", "1234"));
        arquivo.incluir(new Usuario(-1, "Luigi Teste16", (float) 279.8, (float) 127.9, "url:123", "luigi@luigi.com", "1234"));
        //arquivo.incluir(new Usuario(-1, null, (float)0.0, (float)0.0, null, null, null));
    }

}
