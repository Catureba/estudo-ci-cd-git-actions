package org.example;

import org.example.banner.Banner;
import org.example.models.User;

import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    private static ArrayList<User> users = new ArrayList();
    private static Scanner scanner;

    public Main() {
    }

    public static void main(String[] args) {
        Banner.Start();

        while(true) {
            while(true) {
                System.out.println("\nEscolha uma opção:");
                System.out.println("1 - Cadastrar usuário");
                System.out.println("2 - Listar usuários");
                System.out.println("3 - Editar email e senha do usuário");
                System.out.println("4 - Deletar usuário");
                System.out.println("0 - Sair");
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 0:
                        System.out.println("Saindo...");
                        System.exit(0);
                        break;
                    case 1:
                        cadastrarUsuario();
                        break;
                    case 2:
                        listarUsuarios();
                        break;
                    case 3:
                        editarUsuario();
                        break;
                    case 4:
                        deletarUsuario();
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        }
    }

    private static void cadastrarUsuario() {
        System.out.println("Digite o nome do usuário:");
        String name = scanner.nextLine();
        System.out.println("Digite o email do usuário:");
        String email = scanner.nextLine();
        System.out.println("Digite a senha do usuário:");
        String password = scanner.nextLine();
        System.out.println("Digite a data de nascimento do usuário (dd/MM/yyyy):");
        String birthdateStr = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            // Parse the birthdate string into a Date object
            Date birthdate = dateFormat.parse(birthdateStr);

            // Get the current date
            Date currentDate = new Date();

            // Calculate the age in milliseconds
            long ageInMillis = currentDate.getTime() - birthdate.getTime();

            // Convert milliseconds to years (considering 365.25 days per year)
            double ageInYears = ageInMillis / (1000.0 * 60.0 * 60.0 * 24.0 * 365.25);

            // Check if the user is at least 18 years old
            if (ageInYears < 18) {
                System.out.println("O usuário deve ter pelo menos 18 anos.");
                // Do not register the user
            } else {
                // The user is 18 or older, proceed with registration
                users.add(new User(name, email, password, birthdate));
                System.out.println("Usuário cadastrado com sucesso!");
            }
        } catch (ParseException e) {
            System.out.println("Data de nascimento inválida. Tente novamente.");
            // Do not register the user
        }

    }

    private static void listarUsuarios() {
        System.out.println("Lista de usuários:");
        Iterator var0 = users.iterator();

        while(var0.hasNext()) {
            User user = (User)var0.next();
            PrintStream var10000 = System.out;
            String var10001 = user.getName();
            var10000.println("Nome: " + var10001 + ", Email: " + user.getEmail() + ", Data de Nascimento: " + String.valueOf(user.getBirthdate()));
        }

    }

    private static void editarUsuario() {
        System.out.println("Digite o email do usuário que deseja editar:");
        String editEmail = scanner.nextLine();
        boolean found = false;
        Iterator var2 = users.iterator();

        while(var2.hasNext()) {
            User user = (User)var2.next();
            if (user.getEmail().equals(editEmail)) {
                System.out.println("\nEscolha uma opção:");
                System.out.println("1 - Editar email");
                System.out.println("2 - Editar senha");
                int editOption = scanner.nextInt();
                scanner.nextLine();
                switch (editOption) {
                    case 1:
                        System.out.println("Digite o novo email:");
                        String newEmail = scanner.nextLine();
                        user.trocarEmail(newEmail);
                        System.out.println("Email alterado com sucesso!");
                        break;
                    case 2:
                        System.out.println("Digite a senha antiga:");
                        String oldPassword = scanner.nextLine();
                        System.out.println("Digite a nova senha:");
                        String newPassword = scanner.nextLine();
                        System.out.println(user.trocarSenha(oldPassword, newPassword));
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Usuário não encontrado.");
        }

    }

    private static void deletarUsuario() {
        System.out.println("Digite o email do usuário que deseja deletar:");
        String deleteEmail = scanner.nextLine();

        // Prompt for and verify the user's password
        System.out.println("Digite a senha do usuário:");
        String password = scanner.nextLine();

        User deleteUser = null;
        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getEmail().equals(deleteEmail)) {
                // Check if the provided password matches the user's password
                if (user.getPassword().equals(password)) {
                    deleteUser = user;
                    break;
                } else {
                    System.out.println("Senha incorreta. Tente novamente.");
                    return; // Exit the method if password is incorrect
                }
            }
        }

        if (deleteUser != null) {
            users.remove(deleteUser);
            System.out.println("Usuário deletado com sucesso!");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }


    static {
        scanner = new Scanner(System.in);
    }
}
