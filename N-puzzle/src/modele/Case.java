package modele;
/**
 * Représente une case individuelle dans une grille, pouvant faire partie d'un jeu ou d'une structure de données.
 */
public class Case {
    /**
     * La valeur contenue dans cette case.
     */
    int valeur;
    /**
     * Indique si cette case est vide.
     */
    boolean vide;
    /**
     * La position en ligne de cette case dans la grille.
     */
    public int ligne;
    /**
     * La position en colonne de cette case dans la grille.
     */
    public int colonne;

    /**
     * Construit une Case avec une valeur spécifiée, ligne et colonne.
     * @param valeur La valeur de la case
     * @param ligne La ligne où se situe la case
     * @param colonne La colonne où se situe la case
     */
    public Case(int valeur,int ligne,int colonne) {
        this.valeur = valeur;
        this.vide = false;
        this.ligne=ligne;
        this.colonne=colonne;
    }

    /**
     * Construit une Case vide aux positions de ligne et colonne spécifiées.
     * @param ligne La ligne où se situe la case
     * @param colonne La colonne où se situe la case
     */
    public Case(int ligne,int colonne) {
        this.valeur = 0;
        this.vide = true;
        this.ligne=ligne;
        this.colonne=colonne;
    }

     /**
     * Construit une Case vide à la position par défaut (0,0).
     */
    public Case() {
        this.valeur = 0;
        this.vide = true;
        this.ligne=0;
        this.colonne=0;
    }

    /**
     * Crée une nouvelle Case vide à la position par défaut (0,0).
     * @return une nouvelle Case vide
     */
    public Case Vide() {
        Case res = new Case();
        return res;
    }

    /**
     * Renvoie la valeur de la case.
     * @return la valeur de la case
     */
    public int getValeur() { return this.valeur; }

    /**
     * Vérifie si la case est vide.
     * @return vrai si la case est vide, faux autrement
     */
    public boolean estVide() { return this.vide; }

    /**
     * Renvoie la position en ligne de la case.
     * @return la position en ligne
     */
    public int getLigne() { return this.ligne; }

    /**
     * Renvoie la position en colonne de la case.
     * @return la position en colonne
     */
    public int getColonne() { return this.colonne; }

    // Mutateurs
    /**
     * Définit la valeur de la case.
     * @param valeur La nouvelle valeur à définir
     */
    public void setValeur ( int valeur ) { this.valeur = valeur; }

     /**
     * Définit le statut vide de la case.
     * @param vide Vrai pour rendre la case vide, faux autrement
     */
    public void setVide ( boolean vide ) { this.vide = vide; }

    /**
     * Définit la position en ligne de la case.
     * @param ligne La nouvelle position en ligne
     */
    public void setLigne ( int ligne ) { this.ligne =ligne; }

    /**
     * Définit la position en colonne de la case.
     * @param colonne La nouvelle position en colonne
     */
    public void setColonne ( int colonne ) { this.colonne = colonne; }

    /**
     * Définit les coordonnées de la case.
     * @param ligne La nouvelle position en ligne
     * @param colonne La nouvelle position en colonne
     */
    public void setCoordonnees ( int ligne,int colonne ) { this.ligne=ligne; this.colonne = colonne;}

    @Override 
     /**
     * Fournit une représentation sous forme de chaîne de caractères de la case.
     * Cette représentation est simplement la valeur de la case convertie en chaîne de caractères.
     * @return la valeur de la case sous forme de chaîne de caractères
     */
    public String toString(){
        String res  = "";
        res = res + String.valueOf(this.getValeur()) ;
        return res;
    }

}