package controlleur;
/**
 * Interface définissant les méthodes requises pour un modèle pouvant être écouté par des écouteurs.
 * Permet d'ajouter et de retirer des écouteurs qui seront notifiés lors des mises à jour du modèle.
 */
public interface ModeleEcoutable {
    /**
     * Ajoute un écouteur qui sera notifié lors des mises à jour du modèle.
     * @param e L'écouteur à ajouter.
     */
    public void ajoutEcouteur(EcouteurModele e);

    /**
     * Retire un écouteur de la liste des écouteurs notifiés lors des mises à jour du modèle.
     * @param e L'écouteur à retirer.
     */
    public void retraitEcouteur(EcouteurModele e);
}
