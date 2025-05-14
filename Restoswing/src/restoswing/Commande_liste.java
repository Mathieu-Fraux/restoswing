/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package restoswing;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author FM504305
 */
public class Commande_liste extends javax.swing.JFrame {

    ArrayList<Commande> commandes; // Collection des régions
    ArrayList<LigneCommande> ligne_commande; // Collection des départements
    
    static final String API_URL = "http://localhost/projet/anne2/AP/appresto/restoweb/api"; // URL de l'APi
    String url;

    /**
     * Creates new form MyJFrame
     */
    public Commande_liste() {
        initComponents();
        get_data();
    }

    // Appelle l'API et remplit la table des régions
    public void get_data() {

        commandes = new ArrayList<>(); // Réinitialise la collection des régions

        String json = ""; // Le JSON brut
        int i = 0; // Indice sur les commandes
        int j = 0; // Indice sur les départements d'une région

        url = API_URL + "/commandes_en_attente.php";
        
        // Créer un HttpClient
        HttpClient client = HttpClient.newHttpClient();
        // Crée une requête HTTP GET
        try {
            // Construit l'URL de la requête
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .build();
            // Envoie la requête et attend la réponse
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // Vérifie que la réponse est normale
            if (response.statusCode() == 200) {
                json = response.body();
            } else {
                System.err.println("Erreur : Code statut " + response.statusCode());
            }

        } catch (Exception ex) {
            System.err.println("Erreur : " + ex.getMessage());
            //ex.printStackTrace();
        }
        //System.out.println(json);

        // Parse le fichier et remplit la collection d'objets métier
        try {
            JSONArray commandes_json = new JSONArray(json);
            for (i = 0; i < commandes_json.length(); i++) {
                // Récupère la commande
                JSONObject commande_json = commandes_json.getJSONObject(i);
                String lib_id_etat="";
                //change idetat en string pour afficher le texte, il ne doit afficher que en attente.
                switch (commande_json.getInt("id_etat")){
                    case 1:
                         lib_id_etat="en attente";
                         break;
                    case 2:
                        lib_id_etat="Acceptée";
                        break;
                    case 3:
                        lib_id_etat="Refusée";
                        break;
                    case 4:
                        lib_id_etat="Terminée";
                        break;
                    case 5:
                        lib_id_etat="servie";
                        break;
                    default:
                        lib_id_etat = "Commande anormale";
                }
                       
             
                // Récupère les départements de la région
                ligne_commande = new ArrayList<>(); // Réinitialise la collection des départements
                
                
                JSONArray ligne_commande_json = commande_json.getJSONArray("lignes_commande");

                //permet de remplir les lignes de commandes  dansun objet puis dans un tableau.
                for (j = 0; j < ligne_commande_json.length(); j++) {
                    JSONObject ligneCommande_json_object = ligne_commande_json.getJSONObject(j);
                    LigneCommande une_ligne_commande = new LigneCommande(ligneCommande_json_object.getInt("id_ligne_commande"), ligneCommande_json_object.getString("libelle"), ligneCommande_json_object.getInt("quantite"));
                    ligne_commande.add(une_ligne_commande);
                }
                
                // Crée un objet métier à partir du JSON
                Commande commande = new Commande(commande_json.getInt("id_commande"), commande_json.getString("_date"), lib_id_etat, commande_json.getInt("sum(ligne_commande.quantite)"), commande_json.getDouble("total_conso"), commande_json.getString("pseudo"), ligne_commande);
                commandes.add(commande);
            }
            
        } catch (Exception ex) {
            System.err.println("Erreur : " + ex.getMessage());
            ex.printStackTrace();
        }


        // Construit le tableau de données à partir de la collection
        Object[][] data = new Object[commandes.size()][5];

           // donnée ise dans le tableau des commandes.
        for (i = 0; i < commandes.size(); i++) {
            data[i][0] = commandes.get(i).getIdcommande();
            data[i][1] = commandes.get(i).getDate();
            data[i][2] = commandes.get(i).getId_etat();
            data[i][3] = commandes.get(i).getQuantite();
            data[i][4] = commandes.get(i).getTotal_conso();

        } // for

        // Construit le tableau des entêtes
        String[] cols = {"ID", "Date/Heure", "Etat", "Nb plats", "Montant"};

        // Construit le modèle
        DefaultTableModel model_commande = new DefaultTableModel(data, cols);

        // Met à jour le modèle dans le JTable
        Table.setModel(model_commande);

    }
       
    /**
     * Creates new form Liste_commande
     */
    /*
    public Commande_liste() {
        initComponents();
    }
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Liste des comandes");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setText("Restoswing");

        jButton1.setText("Détails");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Quitter");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Date/Heure", "Etat", "Nb plats", "Montant"
            }
        ));
        jScrollPane1.setViewportView(Table);

        jLabel2.setText("liste des commandes");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(24, 24, 24))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel1)
                .addGap(51, 51, 51)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(134, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int row = Table.getSelectedRow();
        //System.out.println("row ="+row);

        // Récupére la région sélectionnée et ouvre la fenêtre JDialog des départements de la région
        if (row >= 0 && row < Table.getRowCount()) {
            // Récupére la région sélectionnée
            Commande commande = commandes.get(row);
            //System.out.println(region);

            // Crée la fenêtre JDialog des départements en passant la région sélectionnée  
            Lignecommande_liste fenetre2 = new Lignecommande_liste(this, true, commande);

            // Ajoute un Listener quand la fenêtre "departement_liste" est fermée
            fenetre2.addWindowListener(new WindowAdapter() {
                public void windowClosed(WindowEvent e) {
                    System.out.println("jdialog window closed"); // test
                    get_data(); // Rafraichit le JTable
                } // windowClosed()

            });

            // Affiche la fenêtre des départements
            fenetre2.setVisible(true);
        } // if
        
        /*Lignecommande_liste fenetre2 = new Lignecommande_liste(this, true);
        fenetre2.setVisible(true);*/
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Commande_liste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Commande_liste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Commande_liste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Commande_liste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Commande_liste().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Table;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
