package main;

import dao.*;
import entities.Role;
import entities.User;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        //Ajout d'un user
        Scanner scanner = new Scanner(System.in);
        User u = new User();
        IUser user = new UserImpl();
        IRole role = new RoleImpl();
        System.out.println("Entrez votre email : ");
        u.setEmail(scanner.nextLine());
        System.out.println("Entrez votre mot de passe : ");
        u.setPasswordHashed(scanner.nextLine());
        System.out.println("=======  Listes des roles ======= ");
        int i = 1;
        for (Role r : role.list()) {
            System.out.println(i + "-" + r.getName());
            i++;
        }
        int choice;
        do {
            System.out.println("Choisissez votre role : ");
            choice = scanner.nextInt();
        } while (choice < 1 || choice > (i - 1));
        u.setRole(role.get(choice));
        int ok = user.add(u);
        if (ok == 1) System.out.println("User ajoute avec success !");
        else System.out.println("Echec de l'ajout");
        //Affichage des users
        int j = 1;
        System.out.println("===== Affichage des Users =====");
        for (User ur : user.list()) {
            System.out.println("===== User " + j + " =====");
            System.out.println("Email : " + ur.getEmail());
            System.out.println("Password : " + ur.getPassword());
            System.out.println("Role : " + role.get(ur.getRole().getId()).getName());
            j++;
        }


    }
}