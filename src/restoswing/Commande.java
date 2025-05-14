/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoswing;


import java.util.ArrayList;
import org.json.JSONObject;


public class Commande {
    private int idcommande;
    private String date;
    private String id_etat;

    private int quantite;
    private double total_conso;
    private String pseudo;
    private ArrayList<LigneCommande> ligne_Commande = new ArrayList<>();

    //constructeur
    
    public Commande(int idcommande, String date, String id_etat, int quantite, double total_conso, String pseudo,ArrayList ligne_Commande) {
        this.idcommande = idcommande;
        this.date = date;
        this.id_etat = id_etat;
        this.quantite = quantite;
        this.total_conso = total_conso;
        this.pseudo = pseudo;
        this.ligne_Commande=ligne_Commande;
    }
//getter
    public int getIdcommande() {
        return idcommande;
    }

    public String getDate() {
        return date;
    }

    public String getId_etat() {
        return id_etat;
    }

    public int getQuantite() {
        return quantite;
    }

    public double getTotal_conso() {
        return total_conso;
    }

    public String getPseudo() {
        return pseudo;
    }

    public ArrayList<LigneCommande> getLigne_Commande() {
        return ligne_Commande;
    }
    
    
//setter
    public void setIdcommande(int idcommande) {
        this.idcommande = idcommande;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId_etat(String id_etat) {
        this.id_etat = id_etat;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setTotal_conso(double total_conso) {
        this.total_conso = total_conso;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setLigne_Commande(ArrayList<LigneCommande> ligne_Commande) {
        this.ligne_Commande = ligne_Commande;
    }
    
    public void afficher(){
        System.out.println("\nidcommande: "+ idcommande);
        System.out.println("date: "+ date);
        System.out.println("quantite: "+quantite);
        System.out.println("total_conso: "+total_conso);
        System.out.println("pseudo: "+pseudo);
        for(int i=0;i<ligne_Commande.size();i++){
            LigneCommande departement = ligne_Commande.get(i);
            departement.afficher();
        }
        
    }

 
} // class Commande