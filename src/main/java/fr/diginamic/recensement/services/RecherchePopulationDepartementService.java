package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;

/**
 * Recherche et affichage de la population d'un département
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationDepartementService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) {

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();
		try {
			Integer.valueOf(choix);
			List<Ville> villes = rec.getVilles();
			int somme = 0;
			for (Ville ville : villes) {
				if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
					somme += ville.getPopulation();
				}
			}
			if (somme > 0) {
				System.out.println("Population du département " + choix + " : " + somme);
			}else {
				System.out.println("Département " + choix + " non trouvée.");
			}
		} catch (NumberFormatException nfe) {
			System.out.println(choix + " n'est pas un nombre. Veuillez saisir un nombre.");
		}
	}

}
