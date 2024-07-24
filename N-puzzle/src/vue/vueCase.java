package vue;

import javax.swing.*;
import modele.*;
import java.awt.*;

/**
 * Représente une case graphique dans l'interface utilisateur, étendant un JButton.
 * Chaque case graphique est associée à une instance de {@link modele.Case}.
 */
class vueCase extends JButton {
     /**
     * La cellule modèle associée à cette case graphique.
     */
    private Case cellule;

    /**
     * Construit une instance de vueCase avec une cellule spécifique.
     * La vue est mise à jour pour refléter l'état de la cellule (vide ou avec valeur).
     *
     * @param cellule La cellule modèle à associer avec cette vueCase.
     */
    public vueCase(Case cellule) {
        this.cellule = cellule;
        if (cellule.estVide()) {
            this.setBackground(Color.BLACK); // Définit le fond en noir si la case est vide
        }
        else {
            setText(String.valueOf(cellule.getValeur()));
            setFont(new Font("Arial", Font.PLAIN, 40));
        }
    }

    /**
     * Retourne la cellule modèle associée à cette vueCase.
     *
     * @return La cellule modèle.
     */
    public Case getCase() { return this.cellule; }

    /**
     * Définit la cellule modèle associée à cette vueCase.
     *
     * @param c La nouvelle cellule modèle à associer.
     */
    public void setCase(Case c){ this.cellule = c;}

}