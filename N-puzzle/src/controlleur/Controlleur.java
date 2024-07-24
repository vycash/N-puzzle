package controlleur;
import vue.*;
import modele.*;

/**
 * Contrôleur qui gère les interactions entre le modèle de jeu (Taquin) et la vue (vueTaquin).
 * Ce contrôleur implémente l'interface EcouteurModele pour écouter les changements sur le modèle
 * et mettre à jour la vue en conséquence.
 */
public class Controlleur implements EcouteurModele{
    
    private Taquin model;  // Modèle de jeu de Taquin
    private vueTaquin vue; // Vue associée au jeu de Taquin
    
    /**
     * Constructeur du contrôleur qui initialise le modèle et la vue.
     * @param vue La vue du jeu de Taquin.
     */
    public Controlleur(vueTaquin vue) {
        this.vue = vue;
        this.model = new Taquin(vue.getColonnes(), vue.getLignes());
    }

    
    /**
     * Déplace une case vers la case vide dans le jeu de taquin.
     * Après le déplacement, la vue est mise à jour pour refléter les changements.
     * @param c La case à déplacer.
     */
    public void deplacer(Case c){
        this.model.deplacer(c,model.getCaseVide());
        vue.actualiser();    
    }
    
    /**
     * Récupère une case du modèle de taquin selon les coordonnées spécifiées.
     * @param i L'indice de la ligne de la case.
     * @param j L'indice de la colonne de la case.
     * @return La case située aux coordonnées (i, j).
     */
    public Case getCase(int i,int j) {
     	return model.getCase(i,j);
     }
    
    /**
     * Mélange les cases du taquin dans le modèle et met à jour la vue pour afficher les nouvelles positions.
     */
    public void melanger() {
        model.melanger(); // Modification : mélanger le taquin lors du replay
        vue.actualiser();
    }
    
    /**
     * Vérifie si le taquin est résolu.
     * @return true si le taquin est résolu, false sinon.
     */
    public boolean resolu() {
        return model.estResolu();
    }
    public void effectuerMouvement(String mouvement){
        switch (mouvement) {
            case "Haut":
                model.effectuerMouvement("h");
                break;
            case "Bas":
                model.effectuerMouvement("b");
                break;
            case "Gauche":
                model.effectuerMouvement("g");
                break;
            case "Droite":
                model.effectuerMouvement("d");
                break;
            case "Q":
                model.effectuerMouvement("q");
                break;
        }
        System.out.println("         vous avez tapé :   "+mouvement);
        vue.actualiser();
    }
    /**
     * Méthode appelée lorsque le modèle notifie un changement.
     * Cette méthode met à jour la vue pour refléter les changements dans le modèle.
     * @param o L'objet de notification (non utilisé ici).
     */
    @Override
    public void modeleMisAJour(Object o){
        System.out.println("Changement");
        vue.actualiser();
    }


}

