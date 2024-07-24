package modele;
import java.util.*;
import controlleur.*;

/**
 * Classe qui gère la logique d'un jeu de taquin.
 * Elle hérite de AbstractModeleEcoutable pour intégrer un système d'écoute entre la vue et le modèle.
 */

// la classe herite de la classe Abstraite du controlleur pour pouvoir implementer la vue
public class Taquin extends AbstractModeleEcoutable {
    /**
     * Grille de cases représentant le taquin.
     */
    public Case[][] cases; // Le taquin constitué de cases de type Case
    /**
     * Nombre de colonnes dans le taquin.
     */
    private int tailleColonnes; // la taille des colonnes
    /**
     * Nombre de lignes dans le taquin.
     */
    private int tailleLignes ; // la taille des lignes
    /**
     * Référence vers la case vide dans le taquin.
     */
    private Case vide; // référence sur la case Vide
    
    /**
     * Constructeur de la classe qui initialise le taquin avec une taille spécifique.
     * @param tailleColonnes Nombre de colonnes du taquin.
     * @param tailleLignes Nombre de lignes du taquin.
     */
    public Taquin(int tailleColonnes , int tailleLignes){
        this.tailleColonnes = tailleColonnes ;
        this.tailleLignes = tailleLignes;
        if( tailleColonnes > 0 && tailleColonnes <= 10 && tailleLignes > 0 && tailleLignes <= 10){
            this.cases = new Case[tailleColonnes][tailleLignes];
        }
        initCase();
        melanger();
    }
    /**
     * Initialise les cases du taquin avec des valeurs séquentielles et définit la dernière case comme vide.
     */    
    private void initCase() {
        int num = 1;
        // Vérification que les dimensions de la grille sont positives
        if (tailleLignes <= 0 || tailleColonnes <= 0) {
            throw new IllegalArgumentException("Les dimensions de la grille doivent être positives.");
        }
    
        // Initialisation du tableau de cases avec des dimensions spécifiées
        this.cases = new Case[tailleLignes][tailleColonnes];
        
        // Itération pour remplir le tableau avec des objets Case
        for (int i = 0; i < tailleLignes; i++) { // Itération sur les lignes
            for (int j = 0; j < tailleColonnes; j++) { // Itération sur les colonnes
                this.cases[i][j] = new Case(num++, i, j); // Incrémentation pour assurer des valeurs uniques pour chaque case
            }
        }
    
        // Définir la dernière case comme vide (en bas à droite de la grille)
        vide = this.cases[tailleLignes - 1][tailleColonnes - 1];
        vide.setValeur(0); // Marquer cette case comme vide
        vide.setVide(true); // Confirmer que c'est la case vide
    }

    // Méthodes getteurs
    /**
     * Renvoie la grille de cases du taquin.
     * Cette grille est un tableau à deux dimensions de cases ({@link Case}).
     * @return Le tableau 2D des cases du taquin.
     */
    public Case[][] getCases() { return this.cases; }

    /**
     * Renvoie le nombre de colonnes dans le taquin.
     * @return Le nombre de colonnes du taquin.
     */
    public int getTailleColonnes() { return this.tailleColonnes; }
    
    /**
     * Renvoie le nombre de lignes dans le taquin.
     * @return Le nombre de lignes du taquin.
     */
    public int getTailleLignes() { return this.tailleLignes; }
    
    /**
     * Récupère une case par ses coordonnées dans la grille.
     * @param ligne Ligne de la case.
     * @param colonne Colonne de la case.
     * @return La case aux coordonnées spécifiées ou null si les coordonnées sont invalides.
     */
    public Case getCase(int ligne, int colonne) {
        if (coordonneeValide(ligne, colonne) ) {
            return this.cases[ligne][colonne];
        } 
        return null;
    }
    /**
     * Accesseur pour la case vide.
     * @return La case vide actuelle.
     */
    public Case getCaseVide() { return vide; } 
    
    /**
     * Sélectionne une case au hasard dans le taquin.
     * @return Une case choisie aléatoirement.
     */
    public Case getRandomCase() {
        Random rand = new Random();
        return this.cases[rand.nextInt(tailleColonnes)][rand.nextInt(tailleLignes)];
    }
    /**
     * Recherche et renvoie la case ayant une valeur spécifique.
     * @param valeur La valeur à rechercher dans le taquin.
     * @return La case ayant la valeur recherchée, ou null si aucune case correspondante n'est trouvée.
     */
    public Case getCaseParValeur(int valeur) {
        for (int i = 0; i < tailleLignes; i++) {
            for (int j = 0; j < tailleColonnes; j++) {
                if (cases[i][j].getValeur() == valeur) {
                    return cases[i][j];
                }
            }
        }
        return null; // Retourne null si la valeur n'est pas trouvée dans le taquin
    }
    

    /**
     * Définit la case vide du taquin.
     * @param newVide La nouvelle case à marquer comme vide.
     */
/*     public void setVide(Case newVide) { this.vide = newVide; }
    */
    public void setVide(Case newVide) {
        if (this.vide != null) {
            this.vide.setVide(false);  // Marque l'ancienne case vide comme non vide
        }
        this.vide = newVide;
        this.vide.setVide(true);  // Marque la nouvelle case comme vide
    }
    /**
     * Vérifie si des coordonnées sont valides dans le contexte du taquin.
     * @param ligne La ligne à vérifier.
     * @param colonne La colonne à vérifier.
     * @return true si les coordonnées sont dans les limites du taquin, false sinon.
     */
    public boolean coordonneeValide(int ligne, int colonne) {
        return ligne >= 0 && ligne < tailleLignes && colonne >= 0 && colonne < tailleColonnes;
    }


    /**
     * Renvoie la liste des cases voisines de la case spécifiée.
     * @param cell La case dont on veut connaître les voisins.
     * @return Une liste des cases adjacentes à la case donnée.
     */
    public ArrayList<Case> getVoisins(Case cell) {
        ArrayList<Case> voisins = new ArrayList<>();
        int ligne = cell.getLigne();
        int colonne = cell.getColonne();
    
        // Vérifier si la case au-dessus existe
        if (ligne > 0) {
            voisins.add(cases[ligne - 1][colonne]);
        }
        // Vérifier si la case à droite existe
        if (colonne < tailleColonnes - 1) {
            voisins.add(cases[ligne][colonne + 1]);
        }
        // Vérifier si la case en dessous existe
        if (ligne < tailleLignes - 1) {
            voisins.add(cases[ligne + 1][colonne]);
        }
        // Vérifier si la case à gauche existe
        if (colonne > 0) {
            voisins.add(cases[ligne][colonne - 1]);
        }
    
        return voisins;
    }    

    
    
    
    


    /**
     * Identifie si une case spécifiée est un coin du taquin.
     * @param cell La case à vérifier.
     * @return true si la case est un coin, false sinon.
     */
    public boolean estCoin(Case cell){
        int colonne = cell.getColonne();
        int ligne = cell.getLigne();
        return colonne == 0 || colonne + 1 == tailleColonnes || ligne == 0 || ligne + 1 == tailleLignes;
    }
    
    /**
     * Vérifie si deux cases sont voisines dans le taquin.
     * @param cell1 Première case à comparer.
     * @param cell2 Deuxième case à comparer.
     * @return true si les cases sont adjacentes horizontalement ou verticalement, false sinon.
     */

    public boolean estVoisin(Case cell1, Case cell2) {
        int ligneCase1 = cell1.getLigne();
        int colonneCase1 = cell1.getColonne();
        int ligneCase2 = cell2.getLigne();
        int colonneCase2 = cell2.getColonne();
    
        boolean voisins = (ligneCase1 == ligneCase2 && Math.abs(colonneCase1 - colonneCase2) == 1) ||
                          (colonneCase1 == colonneCase2 && Math.abs(ligneCase1 - ligneCase2) == 1);
    
        if (!voisins) {
            System.out.println("Non voisins : "+cell1+ " ( " + ligneCase1 + ", " + colonneCase1 + ") avec "+cell2+" (" + ligneCase2 + ", " + colonneCase2 + ")");
        }
    
        return voisins;
    }
    
    

    /**
     * Mélange les cases du taquin en effectuant un nombre aléatoire de déplacements valides.
     */
    public void melanger() {
        Random rand = new Random();
        for (int i = 0; i < rand.nextInt(1000); i++) {
            ArrayList<Case> voisins = getVoisins(vide);
            if (!voisins.isEmpty()) {
                int index = rand.nextInt(voisins.size());
                Case voisin = voisins.get(index);
                deplacer(voisin,vide);
            }
        }
        fireChangement();
    }

    

    /**
     * Déplace une case vers l'emplacement de la case vide si elles sont voisines.
     * @param cell1 La case à déplacer.
     * @param cell2 La case vide actuelle.
     */
    /*
     * methode qui deplace deux cases si elles sont voisines
     * sinon elle ne deplace pas et affiche un message, elle echange les deux cellules dans le tebleau
     * puis echange leurs coordonnées entre ces deux cases
     */
    public void deplacer(Case cell1, Case cell2) {

        // Test de validité
        if( cell1 != null && cell2 != null ){

            int ligneCase1 = cell1.getLigne();
            int colonneCase1 = cell1.getColonne();
        
            int ligneCase2 = cell2.getLigne();
            int colonneCase2 = cell2.getColonne();
    
            // Test de coordonnées
            if (coordonneeValide(ligneCase1, colonneCase1) && coordonneeValide(ligneCase2, colonneCase2)) {
                // Test de voisinage
                if (estVoisin(cell1, cell2)) {
                    if (cell2.estVide()) {

                        // échange des coordonnées des deux cellules
                        cases[ligneCase1][colonneCase1].setCoordonnees(ligneCase2, colonneCase2);
                        cases[ligneCase2][colonneCase2].setCoordonnees(ligneCase1, colonneCase1);

                        // échange des deux cellules
                        Case tmp = cases[ligneCase2][colonneCase2]; // vide temporaire
                        cases[ligneCase2][colonneCase2] = cases[ligneCase1][colonneCase1]; // la celleule d'arrivée deviens la cellule1 avec valeur
                        cases[ligneCase1][colonneCase1] = tmp;  // l'emplacement de la cellule de départ devient le nv Vide
                        
                        
                        setVide( cases[ligneCase1][colonneCase1] ); // mise a jour de la cellule vide
                        fireChangement();

                    }
                    else {
                        System.out.println("\n--cell2 n'est pas vide\n");
                    }
                }
                else {
                    System.out.println("\n--ne sont pas voisins\n");
                }
            }
            else {
                System.out.println("\n--coordonnées non valide\n");
            }
        }
        else {
            System.out.println("\n--mouvement indisponible\n");
        }
    }
    
        
    /**
     * Vérifie si le taquin est résolu, c'est-à-dire si toutes les cases sont dans l'ordre numérique croissant.
     * @return true si le taquin est résolu, false sinon.
     */
    public boolean estResolu()  {
        int tailleLignes = getTailleLignes();
        int tailleColonnes = getTailleColonnes();
        Case[][] cases = getCases();
        int valeurAttendue = 1;
    
        for (int ligne = 0; ligne < tailleLignes; ligne++) {
            for (int colonne = 0; colonne < tailleColonnes; colonne++) {
                Case cellule = cases[ligne][colonne];
                if (cellule.getValeur() != valeurAttendue % (tailleLignes * tailleColonnes)) {
                    return false;
                }
                valeurAttendue++;
            }
        }
        return true;

    }

    /**
     * Effectue un mouvement spécifié par l'utilisateur, modifiant la position de la case vide.
     * @param mouvement Le mouvement à effectuer ('h', 'b', 'g', 'd' pour haut, bas, gauche, droite, respectivement).
     */


     public void effectuerMouvement(String mouvement) {
        Case vide = getCaseVide();
        int ligneVide = vide.getLigne();
        int colonneVide = vide.getColonne();
    
        try {
            switch (mouvement) {
                case "h": // deplacement vers le haut
                    if (ligneVide < getTailleLignes() - 1) // Assure que ce n'est pas la dernière ligne
                        deplacer(getCase(ligneVide + 1, colonneVide), vide);
                    break;
                case "b": // deplacement vers le bas
                    if (ligneVide > 0) // Assure que ce n'est pas la première ligne
                        deplacer(getCase(ligneVide - 1, colonneVide), vide);
                    break;
                case "g": // deplacement vers la gauche
                    if (colonneVide < getTailleColonnes() - 1) // Assure que ce n'est pas la dernière colonne
                        deplacer(getCase(ligneVide, colonneVide + 1), vide);
                    break;
                case "d": // deplacement vers la droite
                    if (colonneVide > 0) // Assure que ce n'est pas la première colonne
                        deplacer(getCase(ligneVide, colonneVide - 1), vide);
                    break;
                case "q": // sortie de la boucle d'execution
                    System.exit(0);
                    break;
                default:
                    System.out.println("\n --- !!!  Mouvement non valide. Utilisez 'h' pour haut, 'b' pour bas, 'g' pour gauche, 'd' pour droite. !!! --- \n");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Mouvement non valide: " + e.getMessage());
        }
    }
    
    
    
    public void commencer() {
        Scanner scanner = new Scanner(System.in);
        
        while (!estResolu()) {
            // Affichage du taquin
            System.out.println(this);
            
            // Affichage des déplacements disponibles
            List<Case> voisins = getVoisins(vide);
            System.out.print("Cellules disponibles à déplacer: ");
            System.out.print(voisins);
            System.out.println();
            
            // Affichage de la saisie du mouvement
            System.out.print("Entrez votre mouvement (h pour haut, b pour bas, g pour gauche, d pour droite) ou 'q' pour quitter : ");
            String mouvement = scanner.nextLine().toLowerCase();
            
            // Effectuer le mouvement correspondant
            switch (mouvement) {
                case "h": case "b": case "g": case "d":
                    effectuerMouvement(mouvement);
                    break;
                case "q":
                    System.out.println("Vous avez quitté le jeu.");
                    return; // Quitter la méthode commencer()
                default:
                    System.out.println("Mouvement non valide. Utilisez 'h' pour haut, 'b' pour bas, 'g' pour gauche, 'd' pour droite ou 'q' pour quitter.");
            }

            System.out.println();
        }

        // Afficher un message de félicitations lorsque le jeu est résolu
        System.out.println("Félicitations ! Vous avez résolu le jeu de Taquin !");
    }

    
    
    /**
     * Retourne une représentation en chaîne de caractères du taquin, en mettant en évidence la case vide.
     * Chaque case vide est affichée avec un arrière-plan vert clair pour une meilleure visibilité.
     * @return La représentation en chaîne de caractères du taquin avec un formatage spécifique pour la visibilité.
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int j = 0; j < tailleLignes; j++) {
            for (int i = 0; i < tailleColonnes; i++) {
                Case c = this.cases[j][i];
                if (c.estVide()) {
                    res.append("\033[42m"); // code ANSI pour changer la couleur d'arrière-plan en vert clair
                }
                res.append(c.getValeur() + " ");
                res.append("\033[0m"+"\t"); // code ANSI pour réinitialiser la couleur d'arrière-plan
            }
            res.append("\n\n");
        }
        return res.toString();
    }
    /**
     * Notifie le modèle que des changements ont été effectués, en affichant simplement "Deplacement" dans la console.
     * Cette méthode peut être étoffée pour inclure une mise à jour plus complexe de la vue ou d'autres composants.
     * @param e L'objet passé par le mécanisme de notification (non utilisé dans l'implémentation actuelle).
     */
    @Override
    public void modeleMisAJour(Object e){
        //System.out.println("Deplacement");
    }

} 
