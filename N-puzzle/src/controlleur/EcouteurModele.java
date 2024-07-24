package controlleur;
/**
 * Interface pour les classes qui souhaitent écouter les mises à jour d'un modèle.
 * Elle définit une méthode de callback qui est appelée lorsque le modèle signale un changement.
 */
public interface EcouteurModele {
    /**
     * Appelé lorsque le modèle subit une mise à jour nécessitant la notification des écouteurs.
     * @param e L'objet qui représente les détails de l'événement de mise à jour.
     */
    public void modeleMisAJour(Object e);
}
