/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoswing;

import org.json.JSONObject;

/**
 *
 * @author FM504305
 */
public class LigneCommande {

    private int id_ligne;
    private String libelle;
    private int qantite_ligne;

    public LigneCommande(int id_ligne, String libelle, int qauntite_ligne) {
        this.id_ligne = id_ligne;
        this.libelle = libelle;
        this.qantite_ligne = qauntite_ligne;
    }

    public int getId_ligne() {
        return id_ligne;
    }

    public String getLibelle() {
        return libelle;
    }

    public int getQauntite_ligne() {
        return qantite_ligne;
    }

    public void setId_ligne(int id_ligne) {
        this.id_ligne = id_ligne;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setQauntite_ligne(int qauntite_ligne) {
        this.qantite_ligne = qauntite_ligne;
    }
    
    public void afficher(){
        System.out.println(""+id_ligne+""+libelle+""+qantite_ligne+"");
    }

} // class LigneCommande
