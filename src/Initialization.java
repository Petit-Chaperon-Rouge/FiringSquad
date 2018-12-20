/**
 * 
 */

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author verel
 *
 */
public class Initialization {

	private int tableRules[] = new int[216];
	private int indexTable[];
	private int bord = 5;
	private int fixedRules[] = new int[] {
			0 * 36 + 0 * 6 + 0,
			5 * 36 + 0 * 6 + 0,
			0 * 36 + 0 * 6 + 5,
			1 * 36 + 1 * 6 + 1,
			5 * 36 + 1 * 6 + 1,
			1 * 36 + 1 * 6 + 5,
			1 * 36 + 0 * 6 + 5,
			5 * 36 + 1 * 6 + 0
	};

	Random generator;

	public Initialization() {
		generator = new Random();

		indexTable = new int[88];
		int i = 0;

		// règles 000 -> 333
		for (int g = 0; g < 4; g++) {
			for (int c = 0; c < 4; c++) {
				for (int d = 0; d < 4; d++) {
					int index = g * 36 + c * 6 + d;

					if (!IntStream.of(fixedRules).anyMatch(x -> x == index)) {
						indexTable[i] = index;
						i++;
					}
				}
			}
		}

		// règles B00 -> B33
		for (int c = 0; c < 4; c++) {
			for (int d = 0; d < 4; d++) {
				int index = bord * 36 + c * 6 + d;

				if (!IntStream.of(fixedRules).anyMatch(x -> x == index)) {
					indexTable[i] = index;
					i++;
				}
			}
		}

		// règles 00B -> 33B
		for (int g = 0; g < 4; g++) {
			for (int c = 0; c < 4; c++) {
				int index = g * 36 + c * 6 + bord;

				if (!IntStream.of(fixedRules).anyMatch(x -> x == index)) {
					indexTable[i] = index;
					i++;
				}
			}
		}

	}
	
	public void init(int [] rules) {
		for (int i = 0; i < indexTable.length; i++) {
			setRule(rules, indexTable[i], generator.nextInt(4));
		}

		// les regles repos (obligatoires)
		setRule(rules, 0, 0, 0, 0);
		setRule(rules, 5, 0, 0, 0);
		setRule(rules, 0, 0, 5, 0);

		// les regles feu (trés conseillés)
		setRule(rules, 1, 1, 1, 4);
		setRule(rules, 5, 1, 1, 4);
		setRule(rules, 1, 1, 5, 4);

		// a priori bord droit (pour la taille 2)
		setRule(rules, 1, 0, 5, 1);

		// a priori bord gauche (pour la taille 2)
		setRule(rules, 5, 1, 0, 1);

	}

    /*
     * Ecrit la regle 
     *
     * g : etat de la cellule de gauche
     * c : etat de la cellule centrale
     * d : etat de la cellule de droite
     * r : etat de la cellule centale à t+1
     */
    public void setRule(int [] rules, int g, int c, int d, int r) {
		setRule(rules, g * 36 + c * 6 + d, r);
    }

	/*
	 * Ecrit la regle
	 *
	 * k : case du tableau
	 * r : etat de la cellule centale à t+1
	 */
	public void setRule(int [] rules, int k, int r) {
		rules[k] = r;
	}

}
