package com.descodeuses.planit.exerciceTp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class exercice1 {

    static ArrayList<Tache> listeTaches = new ArrayList<>();

    static class Tache {
        String nom;
        boolean estFaite;
        LocalDate dateEcheance;

        Tache(String nom) {
            this.nom = nom;
            this.estFaite = false;
            this.dateEcheance = null;
        }

        public void setFaite(boolean b) {
            this.estFaite = b;
        }

        public void setDateEcheance(LocalDate date) {
            this.dateEcheance = date;
        }

        @Override
        public String toString() {
            String dateStr = (dateEcheance != null)
                    ? " (Échéance : " + dateEcheance.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")"
                    : "";
            return (estFaite ? "[x] " : "[ ] ") + nom + dateStr;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        bouclePrincipale:
        while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Ajouter une tâche");
            System.out.println("2. Voir les tâches");
            System.out.println("3. Marquer une tâche comme faite");
            System.out.println("4. Supprimer une tâche");
            System.out.println("5. Ajouter une date d'échéance");
            System.out.println("6. Explorer en CSV ");
            System.out.println("7. Quitter");
            System.out.print("Votre choix : ");

            choix = scanner.nextInt();
            scanner.nextLine(); // consomme le retour ligne

            switch (choix) {
                case 1 -> ajouterTache(scanner);
                case 2 -> afficherTaches();
                case 3 -> marquerCommeFaite(scanner);
                case 4 -> supprimerTache(scanner);
                case 5 -> ajouterLaDateTache(scanner);
                case 6 -> exporterVersCSV();            // ← nouvelle action
                case 7 -> {
                    System.out.println("Au revoir !");
                    break bouclePrincipale;
                }
                default -> System.out.println("Choix invalide !");
            }
        }

        scanner.close();
    }

    private static void ajouterTache(Scanner scanner) {
        System.out.println("Entrez les noms des tâches (tapez 'fin' pour arrêter) :");
        while (true) {
            System.out.print("- ");
            String nom = scanner.nextLine();
            if (nom.equalsIgnoreCase("fin")) break;
            listeTaches.add(new Tache(nom));
            System.out.println("Tâche ajoutée !");
        }
    }

    private static void afficherTaches() {
        if (listeTaches.isEmpty()) {
            System.out.println("Aucune tâche enregistrée.");
        } else {
            System.out.println("\nListe des tâches :");
            for (int i = 0; i < listeTaches.size(); i++) {
                System.out.println((i + 1) + ". " + listeTaches.get(i));
            }
        }
    }

    private static void marquerCommeFaite(Scanner scanner) {
        if (listeTaches.isEmpty()) {
            System.out.println("Aucune tâche à marquer.");
            return;
        }

        afficherTaches();
        System.out.print("Entrez le numéro de la tâche à marquer comme faite : ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index >= 1 && index <= listeTaches.size()) {
            Tache tache = listeTaches.get(index - 1);
            tache.setFaite(true);
            System.out.println("Tâche marquée comme faite : " + tache.nom);
        } else {
            System.out.println("Numéro de tâche invalide.");
        }
    }

    private static void ajouterLaDateTache(Scanner scanner) {
        if (listeTaches.isEmpty()) {
            System.out.println("Aucune tâche.");
            return;
        }

        afficherTaches();
        System.out.print("Entrez le numéro de la tâche à modifier : ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index >= 1 && index <= listeTaches.size()) {
            Tache tache = listeTaches.get(index - 1);
            System.out.print("Entrez la date d'échéance (format AAAA-MM-JJ) : ");
            String dateStr = scanner.nextLine();
            try {
                LocalDate date = LocalDate.parse(dateStr); // ISO format
                tache.setDateEcheance(date);
                System.out.println("Date ajoutée à la tâche : " + tache.nom);
            } catch (Exception e) {
                System.out.println("Format de date invalide.");
            }
        } else {
            System.out.println("Numéro de tâche invalide.");
        }
    }

    private static void supprimerTache(Scanner scanner) {
        if (listeTaches.isEmpty()) {
            System.out.println("Aucune tâche à supprimer.");
            return;
        }

        afficherTaches();
        System.out.print("Entrez le numéro de la tâche à supprimer : ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index >= 1 && index <= listeTaches.size()) {
            Tache tache = listeTaches.remove(index - 1);
            System.out.println("Tâche supprimée : " + tache.nom);
        } else {
            System.out.println("Numéro de tâche invalide.");
        }
    }
    private static void exporterVersCSV() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("taches.csv"))) {
        for (Tache tache : listeTaches) {
            String ligne = tache.nom + ";" + tache.estFaite + ";" +
                    (tache.dateEcheance != null ? tache.dateEcheance.toString() : "");
            writer.write(ligne);
            writer.newLine();
        }
        System.out.println("Tâches exportées avec succès dans le fichier 'taches.csv'.");
    } catch (IOException e) {
        System.out.println("Erreur lors de l’export : " + e.getMessage());
    }
}

}
