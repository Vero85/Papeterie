package fr.eni.ihm;

import fr.eni.bll.CatalogueManager;
import fr.eni.bllException.BLLException;
import fr.eni.dal.DALException;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Couleur;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// la classe GUI est la fenêtre principal
public class GUI extends JFrame {

    //LABEL
    private JPanel panneauPrincipal;
    private JLabel labelRef;
    private JLabel labelDesignation;
    private JLabel labelMarque;
    private JLabel labelStock;
    private JLabel labelPrix;
    private JLabel labelType;
    private JLabel labelGrammage;
    private JLabel labelCouleur;
    //TEXTFIELD
    private JTextField champRef;
    private JTextField champDesignation;
    private JTextField champMarque;
    private JTextField champStock;
    private JTextField champPrix;
    //PANNEAU SECONDAIRE
    private JPanel panneauSecondaire;
    //2 RADIOBUTTON
    private JRadioButton boutonRadio1; //ramette
    private JRadioButton boutonRadio2; // stylo
    //PANNEAU TERTIAIRE
    private JPanel panneauTertiaire;
    //CHECKBOX
    private JCheckBox grammes80;
    private JCheckBox grammes100;
    //PANNEAU QUATRE
    private JPanel PanneauQuatre;
    //COMBOBOX COULEUR
    private JComboBox couleurBox;
    //PANNEAU CINQ
    private JPanel PanneauCinq;
    //JBUTTON
    private JButton boutonArr;
    private JButton boutonNext;
    private JButton boutonRecord;
    private JButton boutonBin;
    private JButton boutonNew;

    // Liste d'articles
    private List<Article> listeDarticles;
    private int index = 0;


    public GUI() {
        this.setTitle("Ecran Article");
        this.setSize(300, 300);
        this.setResizable(false);
        // centrer la fenêtre
        this.setLocationRelativeTo(null);
        // arrêter le programme qd clic sur la croix
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // colle le panneau sur le support
        this.setContentPane(getPanneauPrincipal()); // (1 seul panneau) je colle le panneau principal sur le support en bois
        this.pack();
        this.setVisible(true);
        // jinitialise la liste d'article ligne 59 (aussi je peux l'initialiser directement en ligne 59)
        listeDarticles = new ArrayList<>();
        //création d'un catalogueManager pour faire appel à ses méthodes
        CatalogueManager catalogueManager = CatalogueManager.getInstance();
        try {
            listeDarticles = (List<Article>) catalogueManager.getCatalogue();
        } catch (BLLException e) {
            e.printStackTrace();
        }

        Article articleAafficher;
        if (listeDarticles.size() > 0) {
            articleAafficher = listeDarticles.get(index);
            getChampRef().setText(articleAafficher.getReference());
            getchampDesignation().setText(articleAafficher.getDesignation());
            getchampMarque().setText(articleAafficher.getMarque());
            getchampStock().setText(String.valueOf(articleAafficher.getQteStock()));
            getchampPrix().setText(String.valueOf(articleAafficher.getPrixUnitaire()));

        }

    }

    // Ici tous les composants sont des singleton
    private JPanel getPanneauPrincipal() {
        if (panneauPrincipal == null) {

            panneauPrincipal = new JPanel(); // je crée le panneau principal
            panneauPrincipal.setLayout(new GridBagLayout()); // crée avec les lignes
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.gridx = 0;
            gbc.gridy = 0;
            panneauPrincipal.add(getReference(), gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            panneauPrincipal.add(getlabelDesignation(), gbc);
            gbc.gridx = 0;
            gbc.gridy = 2;
            panneauPrincipal.add(getlabelMarque(), gbc);
            gbc.gridx = 0;
            gbc.gridy = 3;
            panneauPrincipal.add(getlabelStock(), gbc);
            gbc.gridx = 0;
            gbc.gridy = 4;
            panneauPrincipal.add(getlabelPrix(), gbc);
            gbc.gridx = 0;
            gbc.gridy = 5;
            panneauPrincipal.add(getlabelType(), gbc);
            gbc.gridx = 0;
            gbc.gridy = 6;
            panneauPrincipal.add(getlabelGrammage(), gbc);
            gbc.gridx = 0;
            gbc.gridy = 7;
            panneauPrincipal.add(getlabelCouleur(), gbc);
            gbc.gridx = 0;
            gbc.gridy = 8;
            // - champs TEXTField
            // Champ ref
            gbc.gridx = 1;
            gbc.gridy = 0;
            panneauPrincipal.add(getChampRef(), gbc);
            // Champ désignation
            gbc.gridx = 1;
            gbc.gridy = 1;
            panneauPrincipal.add(getchampDesignation(), gbc);
            // Champ marque
            gbc.gridx = 1;
            gbc.gridy = 2;
            panneauPrincipal.add(getchampMarque(), gbc);
            // Champ stock
            gbc.gridx = 1;
            gbc.gridy = 3;
            panneauPrincipal.add(getchampStock(), gbc);
            // Champ Prix
            gbc.gridx = 1;
            gbc.gridy = 4;
            panneauPrincipal.add(getchampPrix(), gbc);
            // ----- PANNEAU SECONDAIRE
            gbc.gridx = 1;
            gbc.gridy = 5;
            panneauPrincipal.add(getPanneauSecondaire(), gbc);
            // - bouton radio ramette
            gbc.gridx = 1;
            gbc.gridy = 6;
            panneauSecondaire.add(getboutonRadio1(), gbc);
            // - bouton radio Stylo
            gbc.gridx = 1;
            gbc.gridy = 7;
            panneauSecondaire.add(getboutonRadio2(), gbc);
            //-----PANNEAU TERTIARE
            gbc.gridx = 1;
            gbc.gridy = 6;
            panneauPrincipal.add(getPanneauTertiaire(), gbc);

            //--------PANNEAU QUATRE
            gbc.gridx = 1;
            gbc.gridy = 7;
            panneauPrincipal.add(getPanneauQuatre(), gbc);
            //-------COMBOBOX COULEUR
            gbc.gridx = 1;
            gbc.gridy = 9;
            PanneauQuatre.add(getCouleurBox(), gbc);
            //--------PANNEAU CINQ
            gbc.gridx = 0;
            gbc.gridy = 8;
            gbc.gridwidth = 2;
            panneauPrincipal.add(getPanneauCinq(), gbc);

            panneauPrincipal.setBackground(new Color(0x8B40B4));


        }
        return panneauPrincipal;
    }

    /////////////////////DEBUT LABEL ///////////////////////

    // Création d'un singleton pour le JLabel REFERENCE
    public JLabel getReference() {
        if (labelRef == null) {
            labelRef = new JLabel("Référence"); // je crée le label Référence
            labelRef.setForeground(new Color(0xFFFFFF));
        }
        return labelRef;
    }

    // Création d'un singleton pour le JLabel DESIGNATION
    public JLabel getlabelDesignation() {
        if (labelDesignation == null) {
            labelDesignation = new JLabel("Désignation"); // je crée le label Désignation
            labelDesignation.setForeground(new Color(0xFFFFFF));
        }
        return labelDesignation;
    }


    // Création d'un singleton pour le JLabel MARQUE
    public JLabel getlabelMarque() {
        if (labelMarque == null) {
            labelMarque = new JLabel("Marque"); // je crée le label MARQUE
            labelMarque.setForeground(new Color(0xFFFFFF));
        }
        return labelMarque;
    }

    // Création d'un singleton pour le JLabel Stock
    public JLabel getlabelStock() {
        if (labelStock == null) {
            labelStock = new JLabel("Stock"); // je crée le label Stock
            labelStock.setForeground(new Color(0xFFFFFF));
        }
        return labelStock;
    }

    // Création d'un singleton pour le JLabel Prix
    public JLabel getlabelPrix() {
        if (labelPrix == null) {
            labelPrix = new JLabel("Prix"); // je crée le label Prix
            labelPrix.setForeground(new Color(0xFFFFFF));
        }
        return labelPrix;
    }

    // Création d'un singleton pour le JLabel Type
    public JLabel getlabelType() {
        if (labelType == null) {
            labelType = new JLabel("Type"); // je crée le label Type
            labelType.setForeground(new Color(0xFFFFFF));
        }
        return labelType;
    }


    // Création d'un singleton pour le JLabel Grammage
    public JLabel getlabelGrammage() {
        if (labelGrammage == null) {
            labelGrammage = new JLabel("Grammage"); // je crée le label Grammage
            labelGrammage.setForeground(new Color(0xFFFFFF));

        }
        return labelGrammage;
    }

    // Création d'un singleton pour le JLabel Couleur
    public JLabel getlabelCouleur() {
        if (labelCouleur == null) {
            labelCouleur = new JLabel("Couleur"); // je crée le label Couleur
            labelCouleur.setForeground(new Color(0xFFFFFF));
        }
        return labelCouleur;
    }

    /////////////////////FIN LABEL ///////////////////////

    /////////////////////DEBUT JTEXTFIELD ///////////////////////

    // Création d'un singleton pour le JTextField Référence
    public JTextField getChampRef() {
        if (champRef == null) {
            champRef = new JTextField(30); // Je crée le champ référence
        }
        return champRef;
    }

    // Création d'un singleton pour le JTextField Désignation
    public JTextField getchampDesignation() {
        if (champDesignation == null) {
            champDesignation = new JTextField(30); // Je crée le champ Désignation
        }
        return champDesignation;
    }

    // Création d'un singleton pour le JTextField Marque
    public JTextField getchampMarque() {
        if (champMarque == null) {
            champMarque = new JTextField(30); // Je crée le champ Marque
        }
        return champMarque;
    }

    // Création d'un singleton pour le JTextField Stock
    public JTextField getchampStock() {
        if (champStock == null) {
            champStock = new JTextField(30); // Je crée le champ Stock
        }
        return champStock;
    }


    // Création d'un singleton pour le JTextField Prix
    public JTextField getchampPrix() {
        if (champPrix == null) {
            champPrix = new JTextField(30); // Je crée le champ prix
        }
        return champPrix;
    }

/////////////////////FIN JTEXTFIELD ///////////////////////


/////////////////////DEBUT PANNEAU SECONDAIRE ///////////////////////

    // Création d'un singleton pour le Panneau secondaire
    public JPanel getPanneauSecondaire() {
        if (panneauSecondaire == null) {
            panneauSecondaire = new JPanel(); // Je crée le panneau secondaire
            panneauSecondaire.setLayout(new BoxLayout(panneauSecondaire, BoxLayout.Y_AXIS));
            panneauSecondaire.add(getboutonRadio1());
            panneauSecondaire.add(getboutonRadio2());
            // ces 2 boutons font un groupe pour cocher et décocher les boutons
            ButtonGroup bg = new ButtonGroup();
            bg.add(getboutonRadio1());
            bg.add(getboutonRadio2());
        }
        return panneauSecondaire;
    }

    /////////////////////DEBUT BOUTON RADIO ///////////////////////

    // Création d'un singleton pour le boutonRadio1
    public JRadioButton getboutonRadio1() {
        if (boutonRadio1 == null) {
            boutonRadio1 = new JRadioButton("Ramette"); // Je crée le 1er bouton radio Ramette
            // Qd je coche Ramette ca desactive la couleur
            boutonRadio1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getCouleurBox().setEnabled(false);
                    //Qd je clique sur ramette, je lui impose un grammage
                    getgrammes80().doClick();
                }
            });
        }
        return boutonRadio1;
    }


    // Création d'un singleton pour le boutonRadio2
    public JRadioButton getboutonRadio2() {
        if (boutonRadio2 == null) {
            boutonRadio2 = new JRadioButton("Stylo"); // Je crée le 2ème bouton radio Stylo
        }
        return boutonRadio2;
    }
    /////////////////////FIN BOUTON RADIO ///////////////////////


    ////////////////////DEBUT PANNEAU TERTIARE///////////////////////////
    // Création d'un singleton pour le Panneau tertiaire
    public JPanel getPanneauTertiaire() {
        if (panneauTertiaire == null) {
            panneauTertiaire = new JPanel(); // Je crée le panneau tertiaire
            panneauTertiaire.setLayout(new BoxLayout(panneauTertiaire, BoxLayout.Y_AXIS));
            panneauTertiaire.add(getgrammes80());
            panneauTertiaire.add(getgrammes100());
            // ButtonGroup bg = new ButtonGroup();
            // bg.add(getgrammes80());
            // bg.add(getgrammes100());
        }
        return panneauTertiaire;
    }


    /////////////////////DEBUT CHECKBOX GRAMMAGE ///////////////////////
    // Création d'un singleton pour le Checkbox grammes80
    public JCheckBox getgrammes80() {
        if (grammes80 == null) {
            grammes80 = new JCheckBox("80 grammes"); // Je crée la checkbox 80 grammes
        }
        return grammes80;
    }


    // Création d'un singleton pour le Checkbox grammes100
    public JCheckBox getgrammes100() {
        if (grammes100 == null) {
            grammes100 = new JCheckBox("100 grammes"); // Je crée la checkbox 100 grammes
        }
        return grammes100;
    }
    ////////////////////FIN CHECKBOX GRAMMAGE ///////////////////////

    ////////////////////FIN PANNEAU TERTIARE ///////////////////////////


    ////////////////////DEBUT PANNEAU QUATRE ///////////////////////////

    // Création d'un singleton pour le Panneau Quatre
    public JPanel getPanneauQuatre() {
        if (PanneauQuatre == null) {
            PanneauQuatre = new JPanel(); // Je crée le panneau Quatre
        }
        return PanneauQuatre;
    }


    // Création d'un singleton pour le Combobox
    public JComboBox<Couleur> getCouleurBox() {
        if (couleurBox == null) {
            couleurBox = new JComboBox(); // Je crée la combobox
            couleurBox = new JComboBox<>(Couleur.values());
        }
        return couleurBox;
    }

    ////////////////////FIN PANNEAU QUATRE///////////////////////////

    ////////////////////DEBUT PANNEAU CINQ///////////////////////////

    // Création d'un singleton pour le Panneau Cinq
    public JPanel getPanneauCinq() {
        if (PanneauCinq == null) {
            PanneauCinq = new JPanel(); // Je crée le panneau Cinq
            PanneauCinq.add(getboutonArr());
            PanneauCinq.add(getboutonNew());
            PanneauCinq.add(getboutonRecord());
            PanneauCinq.add(getboutonBin());
            PanneauCinq.add(getboutonNext());

        }
        return PanneauCinq;
    }

    //------------------BOUTON ARRIERE----------------------//

    public JButton getboutonArr() {
        if (boutonArr == null) {
            boutonArr = new JButton(); // Je crée le bouton Arriere
            ImageIcon imgPrecedent = new ImageIcon("image/Back24.gif");
            boutonArr.setIcon(imgPrecedent);
            boutonArr.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (index == 0) {
                        index = listeDarticles.size()-1;
                    } else {
                        index--;
                    }
                    Article articleAafficher = listeDarticles.get(index);
                    getchampDesignation().setText(articleAafficher.getDesignation());
                    getChampRef().setText(articleAafficher.getReference());
                    getchampMarque().setText(articleAafficher.getMarque());
                    getchampStock().setText(String.valueOf(articleAafficher.getQteStock()));
                    getchampPrix().setText(String.valueOf(articleAafficher.getPrixUnitaire()));
                } ;
            });
        }
        return boutonArr;
    }

    //------------------BOUTON NOUVEL ARTICLE----------------------//

    public JButton getboutonNew() {
        if (boutonNew == null) {
            boutonNew = new JButton(); // Je crée le bouton new
            ImageIcon imgPrecedent = new ImageIcon("image/New24.gif");
            boutonNew.setIcon(imgPrecedent);
            //
            boutonNew.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //PERMET D'EFFACER LES DONNEES SAISIES
                    getChampRef().setText("");
                    getchampDesignation().setText("");
                    getchampMarque().setText("");
                    getchampStock().setText("");
                    getchampPrix().setText("");
                    //PERMET D'EFFACER LES DONNEES SAISIES
                    getgrammes80().setSelected(false);
                    getgrammes100().setSelected(false);
                }
            });
        }
        return boutonNew;
    }


    //------------------BOUTON ENREGISTRER----------------------//

    public JButton getboutonRecord() {
        if (boutonRecord == null) {
            boutonRecord = new JButton(); // Je crée le bouton record
            ImageIcon imgPrecedent = new ImageIcon("image/Save24.gif");
            boutonRecord.setIcon(imgPrecedent);
            boutonRecord.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CatalogueManager cm = CatalogueManager.getInstance(); // j'appelle la BLL
                    if (getboutonRadio1().isSelected()) {
                        Ramette ramette = new Ramette();
                        // J'utilise l'instance crayon. Je récupére le setters de l'instance. Je récupere les infos saisie ds le champ et je les mets en texte.
                        ramette.setReference(getChampRef().getText());
                        ramette.setDesignation((getchampDesignation().getText()));
                        ramette.setMarque(getchampMarque().getText());
                        ramette.setQteStock(Integer.parseInt(getchampStock().getText()));
                        ramette.setPrixUnitaire(Float.parseFloat(getchampPrix().getText()));
                        if(grammes80.isSelected()){
                            ramette.setGrammage(80);
                        }else
                            ramette.setGrammage(100);
                        try {
                            cm.addArticle(ramette); // J'utilise la méthode addArticle du manager
                        } catch (BLLException bllException) {
                            bllException.printStackTrace();
                        }
                        //cm.updateArticle();
                    } else if (getboutonRadio2().isSelected()) {
                        Stylo stylo = new Stylo();
                        stylo.setReference(getChampRef().getText());
                        stylo.setDesignation(getchampDesignation().getText());
                        stylo.setMarque(getchampMarque().getText());
                        stylo.setQteStock(Integer.parseInt(getchampStock().getText()));
                        stylo.setPrixUnitaire(Float.parseFloat(getchampPrix().getText()));
                        stylo.setCouleur(getCouleurBox().getSelectedItem().toString());
                        try {
                            cm.addArticle(stylo); // J'utilise la méthode addArticle du manager
                        } catch (BLLException bllException) {
                            bllException.printStackTrace();
                        }
                    }
                }
            });
        }
        return boutonRecord;
    }




    //------------------BOUTON SUPPRIME----------------------//

    public JButton getboutonBin() {
        if (boutonBin == null) {
            boutonBin = new JButton(); // Je crée le bouton bin
            ImageIcon imgPrecedent = new ImageIcon("image/Delete24.gif");
            boutonBin.setIcon(imgPrecedent);

        }
        return boutonBin;
    }

    //------------------BOUTON SUIVANT----------------------//

    public JButton getboutonNext() {
        if (boutonNext == null) {
            boutonNext = new JButton(); // Je crée le bouton next
            ImageIcon imgPrecedent = new ImageIcon("image/Forward24.gif");
            boutonNext.setIcon(imgPrecedent);
            boutonNext.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (index == listeDarticles.size() - 1) {
                        index = 0;
                    } else {
                        index++;
                    }
                    Article articleAafficher = listeDarticles.get(index);
                    getchampDesignation().setText(articleAafficher.getDesignation());
                    getChampRef().setText(articleAafficher.getReference());
                    getchampMarque().setText(articleAafficher.getMarque());
                    getchampStock().setText(String.valueOf(articleAafficher.getQteStock()));
                    getchampPrix().setText(String.valueOf(articleAafficher.getPrixUnitaire()));
                }

                ;
            });
        }
        return boutonNext;
    }
}


////////////////////FIN PANNEAU CINQ///////////////////////////

















