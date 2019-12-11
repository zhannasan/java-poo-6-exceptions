package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws RuntimeException {

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();
		try {
			Integer.parseInt(choix);
			List<Ville> villes = rec.getVilles();

			String dept = "";
			for (Ville v : villes) {
				if (choix != null && v.getCodeDepartement().equals(choix)) {
					dept = v.getCodeDepartement();
				}
			}
			if (choix.equals(dept)) {
				System.out.println("Choississez une population minimum (en milliers d'habitants): ");
				String saisieMin = scanner.nextLine();
				int min = Integer.parseInt(saisieMin) * 1000;

				if (min < 0) {
					System.out.println("La population ne peut pas être negative");

				} else {
					Integer.parseInt(saisieMin);
					System.out.println("Choississez une population maximum (en milliers d'habitants): ");
					String saisieMax = scanner.nextLine();
					int max = Integer.parseInt(saisieMax) * 1000;

					if (max < 0) {
						System.out.println("La population ne peut pas être negative.");
					} else {
						if (max > min) {
							for (Ville ville : villes) {
								if (choix != null && ville.getCodeDepartement().equals(choix)) {
									if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
										System.out.println(ville);
									}
								}
							}
						} else {
							System.out.println("min>max! Veuillez saisir les nombres valides.");
						}
					}
				}
			} else {
				System.out.println("Le departement avec le code " + choix + " n'existe pas!");
			}
		} catch (NumberFormatException nfe) {
			System.out.println("Votre choix n'est pas un nombre. Veuillez saisir un nombre");
		}

	}

}
