package modele;
import java.util.*;

/**
 * Classe principale pour démarrer le jeu de Taquin.
 * Cette classe contient le point d'entrée principal (méthode main) pour lancer une instance du jeu de Taquin.
 */
public class Main {
    /**
     * Point d'entrée principal du programme.
     * Crée une instance du jeu Taquin et commence le jeu.
     * 
     * @param args Arguments de ligne de commande (non utilisés dans cette application).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int lignes=0, colonnes=0;
        boolean tailleValide = false;

        // Continuer à demander à l'utilisateur jusqu'à ce qu'une taille valide soit saisie
        while (!tailleValide) {
            System.out.println("La taille Maximum d'un jeu de taquin est 10x10");
            System.out.print("Veuillez saisir le nombre de lignes que vous voulez : ");
            lignes = scanner.nextInt();
            System.out.print("Veuillez saisir le nombre de colonnes que vous voulez : ");
            colonnes = scanner.nextInt();

            // Vérifier si la taille est valide
            if (lignes > 0 && lignes <= 10 && colonnes > 0 && colonnes <= 10) {
                tailleValide = true;
            } else {
                System.out.println("\nTaille invalide. Veuillez saisir des nombres entre 1 et 10.\n");
            }
        }

        // Création d'une instance du jeu de Taquin avec la taille spécifiée
        Taquin taquin = new Taquin(lignes, colonnes);

        // Commence le jeu.
        taquin.commencer();
    }
}
