package fr.ul.miage.restaurant.dao;

import java.util.ArrayList;

import fr.ul.miage.restaurant.models.CompositionPlat;
import fr.ul.miage.restaurant.models.Plat;

public abstract class CompositionPlatDAO extends DAO<CompositionPlat>{

	public abstract ArrayList<CompositionPlat> getWithPlats(ArrayList<Plat> plats);

}
