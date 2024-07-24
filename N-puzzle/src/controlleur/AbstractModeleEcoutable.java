package controlleur;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite définissant le comportement d'un modèle écoutable.
 * Elle implémente les méthodes d'ajout et de retrait d'écouteurs, ainsi que
 * la notification des écouteurs lors des mises à jour du modèle.
 */
public abstract class AbstractModeleEcoutable implements EcouteurModele,ModeleEcoutable {

    /**
     * Liste des écouteurs abonnés pour recevoir les mises à jour du modèle.
     */
    List<EcouteurModele> ecouteurs;
    /**
     * Constructeur qui initialise la liste des écouteurs.
     */
    public AbstractModeleEcoutable(){
        this.ecouteurs=new ArrayList<>();
    }
    /**
     * Ajoute un écouteur à la liste des abonnés.
     * @param e L'écouteur à ajouter.
     */
    @Override
    public void ajoutEcouteur(EcouteurModele e){
        this.ecouteurs.add(e);
    }

    /**
     * Retire un écouteur de la liste des abonnés.
     * @param e L'écouteur à retirer.
     */
    @Override
    public void retraitEcouteur(EcouteurModele e){
        this.ecouteurs.remove(e);
    }
    
    /**
     * Notifie tous les écouteurs abonnés que le modèle a été mis à jour.
     * Cette méthode est appelée lorsqu'une modification du modèle nécessite une mise à jour de la vue.
     */
    protected void fireChangement() {
        for (EcouteurModele e : ecouteurs){
            e.modeleMisAJour(this);
        }
    }
}
