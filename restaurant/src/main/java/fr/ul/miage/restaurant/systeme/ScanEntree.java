package fr.ul.miage.restaurant.systeme;

import java.util.Scanner;

public class ScanEntree {

	public static int readInteger() {
		
		boolean error = true;

        int intSelect = 0;

        while(error == true) {
            try {
                Scanner sc = new Scanner(System.in);
                intSelect = sc.nextInt();
                error = false;
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Il faut une valeur numérique");
            }
        }
		return intSelect;
	}
	
	public static int readIntegerWithDelimitations(int min, int max) {
		boolean error = true;

        int intSelect = 0;

        while(error == true) {
            try {
                Scanner sc = new Scanner(System.in);
                intSelect = sc.nextInt();
                if(intSelect >= min && intSelect <= max) {
                    error = false;
                } else {
                    System.out.println("Choix hors limites");
                }
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Il faut une valeur numérique");
            }
        }
        
        return intSelect;
	}
}
