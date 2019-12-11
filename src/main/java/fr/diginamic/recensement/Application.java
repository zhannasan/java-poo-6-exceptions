package fr.diginamic.recensement;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.services.RecherchePopulationBorneService;
import fr.diginamic.recensement.services.RecherchePopulationDepartementService;
import fr.diginamic.recensement.services.RecherchePopulationRegionService;
import fr.diginamic.recensement.services.RecherchePopulationVilleService;
import fr.diginamic.recensement.utils.RecensementUtils;

/**
 * Application de traitement des données de recensement de population
 * 
 * @param args
 */
public class Application {

	/**
	 * Point d'entrée
	 * 
	 * @param args arguments (non utilisés ici)
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String filePath = ClassLoader.getSystemClassLoader().getResource("recensement.csv").getFile();
		Recensement recensement = RecensementUtils.lire(filePath);

		if (recensement == null) {
			System.out.println("L'application doit s'arrétée en raison d'une erreur d'exécution.");
			System.exit(0);
		}
		// Menu
		int choix = 0;
		do {

			// Affichage du menu
			afficherMenu();

			// Poser une question à l'utilisateur
			String choixMenu = scanner.nextLine();
			if (StringUtils.isNumeric(choixMenu)) {
				
			
			// Conversion du choix utilisateur en int
			choix = Integer.parseInt(choixMenu);

			// On exécute l'option correspondant au choix de l'utilisateur
			switch (choix) {
			case 1:
				RecherchePopulationVilleService rechercheVille = new RecherchePopulationVilleService();
				rechercheVille.traiter(recensement, scanner);
				break;
			case 2:
				RecherchePopulationDepartementService rechercheDept = new RecherchePopulationDepartementService();
				rechercheDept.traiter(recensement, scanner);
				break;
			case 3:
				RecherchePopulationRegionService rechercheRegion = new RecherchePopulationRegionService();
				rechercheRegion.traiter(recensement, scanner);
				break;
			case 4:
				RecherchePopulationBorneService recherchePopBorne = new RecherchePopulationBorneService();
				recherchePopBorne.traiter(recensement, scanner);
				break;
			}
			}else{
				System.out.println("Veuillez saisir un nombre");
			}
		} while (choix != 99);

		scanner.close();

	}

	/**
	 * Affichage du menu
	 */
	private static void afficherMenu() {
		System.out.println("***** Recensement population *****");
		System.out.println("1. Rechercher la population d'une ville");
		System.out.println("2. Rechercher la population d'un département");
		System.out.println("3. Rechercher la population d'une région");
		System.out.println("4. Rechercher la population des villes d'un dept entre min et max");
		System.out.println("99. Sortir");
	}
}
