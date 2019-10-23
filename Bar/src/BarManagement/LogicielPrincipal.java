/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BarManagement;
import BarManagement.Boisson;
import BarManagement.Barman;
import BarManagement.ClientHomme;
import BarManagement.ClientFemme;
import BarManagement.Humain;
import BarManagement.Patron;
import BarManagement.ServeurHomme;
import BarManagement.ServeurFemme;
import BarManagement.Fournisseur;
import BarManagement.bar;


/**
 *
 * @author Théo
 */
public class LogicielPrincipal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClientHomme cli = new ClientHomme();
        cli.alcoolemie();
    }
    
}
