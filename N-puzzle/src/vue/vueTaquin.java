package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlleur.Controlleur;


/**
 * Représente la vue principale pour le jeu de Taquin.
 * Cette classe gère l'affichage et les interactions de l'interface utilisateur pour permettre aux utilisateurs de jouer au Taquin.
 * Elle implémente ActionListener pour gérer les actions sur les éléments GUI tels que les boutons.
 */
public class vueTaquin extends JPanel implements ActionListener {
    /**
     * Contrôleur gérant la logique du jeu.
     */
    private Controlleur controlleur;
    private vueCase[][] boutons; // Grille de boutons représentant les cases du taquin
    JButton replayButton; // Bouton pour recommencer le jeu
    private int colonnes; // Nombre de colonnes de la grille de taquin
    private int lignes; // Nombre de lignes de la grille de taquin
    private JFrame frameDeSelection; // Ajoutez cette ligne pour déclarer la variable
    
    

    /**
     * Constructeur par défaut qui initialise le panneau avec une grille 4x4.
     */
    public vueTaquin() {
        choisirTaille();
    }

    /**
     * Ouvre une fenêtre de dialogue demandant à l'utilisateur de choisir la taille de la grille de jeu.
     * Les valeurs sont restreintes à un maximum de 10 pour les lignes et les colonnes.
     */
    private void choisirTaille() {
        JTextField lignesField = new JTextField();
        JTextField colonnesField = new JTextField();
        frameDeSelection = new JFrame("Sélection de la taille");
    
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Choisissez un nombre de Lignes (max 10):"));
        panel.add(lignesField);
        panel.add(new JLabel("Choisissez un nombre de Colonnes (max 10):"));
        panel.add(colonnesField);
        panel.setPreferredSize(new Dimension(700, 150));
    
        while (true) {
            int result = JOptionPane.showConfirmDialog(null, panel, "Choix de la taille du taquin", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    int lignes = Integer.parseInt(lignesField.getText());
                    int colonnes = Integer.parseInt(colonnesField.getText());
                    if (lignes <= 0 || colonnes <= 0 || lignes > 10 || colonnes > 10) {
                        JOptionPane.showMessageDialog(this, "Veuillez entrer des valeurs valides pour les lignes et les colonnes (entre 1 et 10 inclus).");
                    } else {
                        initialiserJeu(lignes, colonnes);
                        break;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Veuillez entrer des valeurs valides pour les lignes et les colonnes.");
                }
            } else {
                System.exit(0);
            }
        }
    }
    
    /**
     * Initialise le jeu en configurant la grille de boutons et le contrôleur associé.
     * @param lignes Le nombre de lignes pour la grille du taquin.
     * @param colonnes Le nombre de colonnes pour la grille du taquin.
     */
    private void initialiserJeu(int lignes, int colonnes) {
        this.colonnes = colonnes;
        this.lignes = lignes;

        controlleur = new Controlleur(this);
        boutons = new vueCase[lignes][colonnes];
        setLayout(new GridLayout(lignes, colonnes));
        setPreferredSize(new Dimension(800, 800));

        this.replayButton = new JButton("Rejouer");
        replayButton.addActionListener(this);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                controlleur.effectuerMouvement(e.getKeyText(e.getKeyCode()));
            }
        });

        // Rendre le JPanel focusable pour qu'il puisse recevoir des événements de clavier
        this.setFocusable(true);
        this.requestFocusInWindow();
        init();
    }

    /**
     * Récupère le nombre de colonnes de la grille du taquin.
     * Cette méthode permet d'accéder au nombre de colonnes de la grille actuellement configurée pour le jeu.
     * @return Le nombre de colonnes de la grille.
     */
    public int getColonnes() {
        return this.colonnes;
    }

    /**
     * Récupère le nombre de lignes de la grille du taquin.
     * Cette méthode permet d'accéder au nombre de lignes de la grille actuellement configurée pour le jeu.
     * @return Le nombre de lignes de la grille.
     */
    public int getLignes() {
        return this.lignes;
    }

    /**
     * Réinitialise le jeu, en relançant le processus de sélection de la taille de la grille.
     */
    public void rejouer() {
        controlleur.melanger();
    }
    /**
     * Réinitialise l'interface graphique en construisant à nouveau la grille de boutons en fonction de l'état actuel du jeu.
     */
    private void init() {
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                vueCase boutonCase = new vueCase(controlleur.getCase(i, j));
                boutonCase.addActionListener(this);
                boutonCase.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        boutonCase.setBackground(Color.GREEN);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (boutonCase.getCase().estVide()) {
                            boutonCase.setBackground(Color.BLACK);
                        } else {
                            boutonCase.setBackground(null);
                        }
                    }
                });
                boutons[i][j] = boutonCase;
                add(boutonCase);
            }
        }
        setLayout(new GridLayout(lignes, colonnes)); // Correction ici
    }

    /**
     * Affiche une fenêtre de dialogue en fin de jeu pour féliciter l'utilisateur et lui proposer de rejouer ou de quitter.
     */
    public void afficherFinDeJeu() {
        // Options pour les boutons
        Object[] options = {"Rejouer", "Quitter"};
        // Personnaliser les couleurs des boutons
        
        // Affiche un JOptionPane dialogue
        int choix = JOptionPane.showOptionDialog(null,
            "Félicitations ! Vous avez résolu le jeu de taquin !",
            "Jeu Terminé",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]);
            // Restaurer les couleurs par défaut
        

    
        // Gérer le choix de l'utilisateur
        if (choix == JOptionPane.YES_OPTION) {
            
            rejouer(); // L'utilisateur choisit de rejouer
            
        } else {
            System.exit(0); // Quitter l'application
        }
    }

    /**
     * Affiche une fenêtre de dialogue en fin de jeu pour féliciter l'utilisateur et lui proposer de rejouer ou de quitter.
     */
    public void actualiser() {
        removeAll();
        init();
        revalidate();
        repaint();
        if (resolu()) {
            afficherFinDeJeu();
        } 
    }
    
    /**
     * Affiche une fenêtre séparée en fin de jeu pour féliciter l'utilisateur et lui proposer des options pour la suite.
     * Cette méthode crée une nouvelle fenêtre contenant un message de félicitations, ainsi que des boutons pour rejouer ou quitter le jeu.
     */
    public void afficherFinDeJeuFrame() {
        // Créer la fenêtre de fin de jeu
        JFrame finDeJeuFrame = new JFrame("Jeu Terminé");
        finDeJeuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finDeJeuFrame.setSize(300, 200);
        finDeJeuFrame.setLayout(new GridLayout(3, 1));
    
        // Ajouter un label de félicitations
        JLabel messageLabel = new JLabel("Félicitations ! Vous avez résolu le jeu de taquin !", SwingConstants.CENTER);
        finDeJeuFrame.add(messageLabel);
    
        // Ajouter un bouton pour rejouer
        JButton replayButton = new JButton("Rejouer");
        replayButton.addActionListener(e -> rejouer());
        
        finDeJeuFrame.add(replayButton);
    
        // Ajouter un bouton pour quitter
        JButton quitButton = new JButton("Quitter");
        quitButton.addActionListener(e -> System.exit(0));
        finDeJeuFrame.add(quitButton);
    
        // Centrer et rendre visible
        finDeJeuFrame.setLocationRelativeTo(null);
        finDeJeuFrame.setVisible(true);
    }
    
    /**
     * Vérifie si le jeu est résolu.
     * @return true si le jeu est résolu, false sinon.
     * Cette méthode délègue la vérification de l'état résolu au contrôleur, qui gère la logique du jeu.
     */
    public boolean resolu() {
        return controlleur.resolu();
    }

    /**
     * Méthode appelée lorsque l'utilisateur interagit avec les éléments de l'interface, gérant les clics sur les boutons.
     * @param e L'événement déclencheur.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object depart = e.getSource();
        if (depart instanceof vueCase) {
            vueCase boutonCase = (vueCase) depart;
            controlleur.deplacer(boutonCase.getCase());
        } else if (depart == replayButton) {
            controlleur.melanger();
            setLayout(new GridLayout(colonnes, lignes));
        }
    }
}
