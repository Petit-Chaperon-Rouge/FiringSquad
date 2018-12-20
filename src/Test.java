/**
 * 
 */
import java.io.*;



/**
 * @author verel
 *
 */
public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
		// Nombre maximale de fusiliers (taille maximale du réseau)
		int sizeMax = 20;
        Automata automate = new Automata(sizeMax);

        // path to change
        String solFileName = "solution_19a";
        String path = "/home/axel/IdeaProjects/FiringSquad/";

        String bestName = path + solFileName + ".dat";
        int [] rules = initRulesFromFile(bestName);

        int nFire;

        int fit = automate.f(rules, sizeMax);
        System.out.println(fit);

        for(int i = 2; i <= sizeMax; i++) {
            // évolution de l'automate avec la règle rule sur un réseau de longueur i
            nFire = automate.evol(rules, i);

            // affichage du nombre de fusiliers ayant tiré
            System.out.println("longueur " + i + " : " + nFire);

            // affiche la dynamique dans un fichier au format svg
            automate.exportSVG(i, 2 * i - 2, path + "svg/" + solFileName + "_" + i + ".svg");
        }

        String outName = path + "out.dat";

        Initialization init = new Initialization();

        PrintWriter ecrivain;
        try {
            ecrivain =  new PrintWriter(new BufferedWriter(new FileWriter(outName)));

            for(int i = 0; i < 1000; i++) {
                init.init(rules);

                fit = automate.f(rules, 19);

                printToFile(fit, rules, ecrivain);
            }

            ecrivain.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

        System.out.println("The End.");

        HillClimber hc = new HillClimber();
        Result resHC = hc.firstImprovement(600000, automate);
        System.out.println("Fitness final FI : " + resHC.getFitness());
        for(int i = 0; i < resHC.getRules().length; i++){
            System.out.print(resHC.getRules()[i] + " ");
        }

        System.out.println(" ");

        ILS ils = new ILS();
        Result resILS = ils.ILS(300, 200000, automate);
        System.out.println("Fitness final ILS : " + resILS.getFitness());
        for(int i = 0; i < resILS.getRules().length; i++){
            System.out.print(resILS.getRules()[i] + " ");
        }
    }

    public static void printToFile(int fitness, int [] rules, PrintWriter ecrivain) {
        ecrivain.print(fitness);
        for(int i = 0; i < 216; i++) {
            ecrivain.print(" ");
            ecrivain.print(rules[i]);
        }
        ecrivain.println();
    }

    public static int [] initRulesFromFile(String fileName) {
        // 5 états + l'état "bord"
        int n = 5 + 1;

        int [] rules = new int[n * n * n];

        try {
            FileReader fichier = new FileReader(fileName);

            StreamTokenizer entree = new StreamTokenizer(fichier);

            int i = 0;
            while(entree.nextToken() == StreamTokenizer.TT_NUMBER)
            {
                rules[i] = (int) entree.nval;
                i++;
            }
            fichier.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

        return rules;
    }


}
