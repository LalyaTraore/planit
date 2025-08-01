package com.descodeuses.planit.testJava;

public class Voiture implements Vehicule {

    @Override
    public int nbrRoues() {
        return 4;
    }

    @Override
    public void afficher() {
        System.out.println("Je suis une Voiture");
    }

}
