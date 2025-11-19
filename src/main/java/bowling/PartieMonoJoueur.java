package bowling;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancers successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class PartieMonoJoueur {

	private final List<Integer> lancers; // Liste de tous les lancers
	private int tourCourant; // Numéro du tour actuel (1 à 10)
	private int lancerCourant; // Numéro du lancer actuel (1 à 3)
	private boolean terminee;

	/**
	 * Constructeur
	 */
	public PartieMonoJoueur() {
		this.lancers = new ArrayList<>();
		this.tourCourant = 1;
		this.lancerCourant = 1;
		this.terminee = false;
	}

	/**
	 * Cette méthode doit être appelée à chaque lancer de boule
	 *
	 * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de ce
	 *                                lancer
	 * @throws IllegalStateException si la partie est terminée
	 * @return vrai si le joueur doit lancer à nouveau pour continuer son tour, faux
	 *         sinon
	 */
	public boolean enregistreLancer(int nombreDeQuillesAbattues) {
		if (terminee) {
			throw new IllegalStateException("La partie est déjà terminée !");
		}

		lancers.add(nombreDeQuillesAbattues);

		// Si on est dans les 9 premiers tours
		if (tourCourant < 10) {
			if (nombreDeQuillesAbattues == 10 && lancerCourant == 1) {
				tourCourant++;
				lancerCourant = 1;
				if (tourCourant > 10)
					terminee = true;
				return false;
			} else if (lancerCourant == 1) {
				lancerCourant = 2;
				return true; // le tour continue
			} else {
				// Fin du tour
				tourCourant++;
				lancerCourant = 1;
				if (tourCourant > 10)
					terminee = true;
				return false;
			}
		} else {
			// Gestion du 10e tour
			return gererDernierTour();
		}
	}

	/**
	 * Gère le déroulement du 10ᵉ tour (cas particulier : 2 ou 3 lancers possibles)
	 */
	private boolean gererDernierTour() {
		int premierLancerIndex = premierLancerDuTour(10);
		int nbLancers10eTour = lancers.size() - premierLancerIndex;

		// Si 2 lancers effectués, déterminer s'il faut une boule bonus
		if (nbLancers10eTour == 2) {
			int premier = lancers.get(premierLancerIndex);
			int second = lancers.get(premierLancerIndex + 1);

			if (premier == 10 || premier + second == 10) {
				lancerCourant = 3; // droit à un lancer bonus
				return true;
			} else {
				// Partie terminée
				terminee = true;
				tourCourant = 0;
				lancerCourant = 0;
				return false;
			}
		}

		// Si 3 lancers effectués au 10e tour → partie terminée
		if (nbLancers10eTour >= 3) {
			terminee = true;
			tourCourant = 0;
			lancerCourant = 0;
			return false;
		}

		// Sinon, on continue le tour (2e ou 3e lancer)
		lancerCourant = nbLancers10eTour + 1;
		return true;
	}

	/**
	 * Cette méthode donne le score du joueur.
	 * Si la partie n'est pas terminée, on considère que les lancers restants
	 * abattent 0 quille.
	 * 
	 * @return Le score du joueur
	 */
	public int score() {
		int score = 0;
		int indexLancer = 0;

		for (int tour = 0; tour < 10; tour++) {
			if (indexLancer >= lancers.size())
				break;

			int premier = lancers.get(indexLancer);

			// STRIKE
			if (premier == 10) {
				score += 10 + bonusStrike(indexLancer);
				indexLancer++;
			}
			// SPARE
			else if (indexLancer + 1 < lancers.size() &&
					premier + lancers.get(indexLancer + 1) == 10) {
				score += 10 + bonusSpare(indexLancer);
				indexLancer += 2;
			}
			// Normal
			else {
				int second = (indexLancer + 1 < lancers.size()) ? lancers.get(indexLancer + 1) : 0;
				score += premier + second;
				indexLancer += 2;
			}
		}

		return score;
	}

	private int bonusStrike(int index) {
		int bonus = 0;
		if (index + 1 < lancers.size())
			bonus += lancers.get(index + 1);
		if (index + 2 < lancers.size())
			bonus += lancers.get(index + 2);
		return bonus;
	}

	private int bonusSpare(int index) {
		if (index + 2 < lancers.size()) {
			return lancers.get(index + 2);
		}
		return 0;
	}

	/**
	 * @return vrai si la partie est terminée pour ce joueur, faux sinon
	 */
	public boolean estTerminee() {
		return terminee;
	}

	/**
	 * @return Le numéro du tour courant [1..10], ou 0 si le jeu est fini
	 */
	public int numeroTourCourant() {
		return tourCourant;
	}

	/**
	 * @return Le numéro du prochain lancer pour tour courant [1..3], ou 0 si le jeu
	 *         est fini
	 */
	public int numeroProchainLancer() {
		return lancerCourant;
	}

	private int premierLancerDuTour(int numeroTour) {
		int index = 0;
		int tour = 1;

		while (tour < numeroTour && index < lancers.size()) {
			int lancer = lancers.get(index);
			if (lancer == 10) {
				index++;
			} else {
				index += 2;
			}
			tour++;
		}

		return index;
	}

}
