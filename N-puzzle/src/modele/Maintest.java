package modele;
import java.util.ArrayList;

/**
 * Classe de test pour le jeu de Taquin dans le terminal.
 * Fournit des méthodes statiques pour tester différentes fonctionnalités du jeu,
 * comme l'initialisation et l'affichage des voisins des cases.
 */
public class Maintest {

    /**
     * Teste la création d'un Taquin avec des dimensions spécifiques.
     * Affiche l'état initial du Taquin après sa création.
     * @param lignes Le nombre de lignes du Taquin.
     * @param colonnes Le nombre de colonnes du Taquin.
     */
    public static void testTaquin(int lignes, int colonnes) {
        Taquin taquin = new Taquin(colonnes, lignes);
        System.out.println("Accès à cases[" + lignes + "][" + colonnes + "]");
    
        System.out.println("Taquin " + lignes + "x" + colonnes + " initialisé.");
        System.out.println(taquin);
    }

    /**
     * Affiche les cases voisines de la case vide du Taquin.
     * @param taquin L'instance de Taquin dont les voisins de la case vide seront affichés.
     */
    public static void afficheVoisins(Taquin taquin){
        ArrayList<Case> voisins = taquin.getVoisins(taquin.getCaseVide());
        System.out.println("nb de voisins : "+voisins.size());
        for( int i=0 ; i < voisins.size() ; i++) {
            System.out.print(voisins.get(i)+" : ");
            System.out.print("( ligne "+voisins.get(i).getLigne());
            System.out.print(",colonne "+voisins.get(i).getColonne()+")");
            System.out.println();
        }
    }
    /**
     * Point d'entrée principal pour les tests du jeu de Taquin.
     * Initialise un Taquin et affiche ses cases voisines.
     * @param args Les arguments de la ligne de commande, non utilisés ici.
     */
    public static void main(String[] args) {
        /* testTaquin(3, 2);
        testTaquin(4, 5);
        testTaquin(2, 8);
        testTaquin(3, 3); */
        Taquin taquin = new Taquin(5,3);
        System.out.println(taquin);
        System.out.println("vide :"+taquin.getCaseVide().getLigne()+" "+taquin.getCaseVide().getColonne());

        //testTaquin(2, 3);
        afficheVoisins(taquin);

    }
    
}
