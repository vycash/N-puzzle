package vue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

//import com.formdev.flatlaf.FlatLightLaf;

/**
 * Classe principale pour l'interface utilisateur du jeu de Taquin.
 * Cette classe initialise et affiche la fenêtre principale du jeu, gérant le look and feel
 * et la configuration initiale de la fenêtre.
 */
public class MainVue {

     /**
     * Point d'entrée principal du programme. Ce méthode configure et lance l'interface utilisateur
     * principale du jeu de Taquin en utilisant Swing.
     *
     * @param args Les arguments de la ligne de commande. Ces arguments ne sont pas utilisés dans cette application.
     */
    public static void main(String[] args) {

        /* // Tente d'appliquer le look and feel FlatLightLaf. Si indisponible, tente Nimbus, puis le look and feel par défaut.
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        }
        catch (Exception flatLafEx) {
            // Si FlatLightLaf n'est pas disponible, essayez Nimbus
            */
        // Tente d'appliquer le look and feel Nimbus. Si indisponible, tente le look and feel par défaut.
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } 
        catch (Exception nimbusEx) {
            // Si Nimbus n'est pas disponible, utilisez le look and feel par défaut
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } 
            catch (Exception defaultLafEx) {
                defaultLafEx.printStackTrace();
            }
        }


        

        // Exécute l'interface graphique dans le fil d'exécution de l'interface graphique Swing pour assurer la thread-safety.
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Jeu de Taquin"); // Crée la fenêtre avec le titre "Jeu de Taquin".
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configure la fenêtre pour qu'elle se ferme lorsque l'utilisateur quitte.
            frame.add(new vueTaquin()); // Ajoute le composant principal du jeu à la fenêtre.
            frame.pack(); // Ajuste la taille de la fenêtre en fonction des composants qu'elle contient.
            frame.setLocationRelativeTo(null); // Centre la fenêtre sur l'écran.
            frame.setVisible(true); // Rend la fenêtre visible.
        });
    }
}
