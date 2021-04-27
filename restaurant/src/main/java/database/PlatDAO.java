package database;

import java.util.ArrayList;

import Models.Plat;

public abstract class PlatDAO extends DAO<Plat>{

	public abstract void creerPlat(String nom, int prix, ArrayList<String> idIngedients, ArrayList<Integer> qte);

}
