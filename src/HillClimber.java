public class HillClimber {

    private static final int _NBRULES = 216;
    private static final int _SIZEMAX = 20;

    public Result firstImprovement(int nbIter, Automata automata) {
        Initialization init = new Initialization();

        int rules[] = new int[_NBRULES];
        init.init(rules);

        return firstImprovement(nbIter, automata, rules);
    }

    public Result firstImprovement(int nbIter, Automata automata, int[] initialRules) {

        Initialization init = new Initialization();
        int rules[] = initialRules;

        int fitness = automata.f(rules, _SIZEMAX);
        // System.out.println("Fitness : " + fitness);

        int i = 0;

        do {
            int rules_prime[] = rules;

            // Choose x' from the neighborhood
            //for (int j = 0; j < 10; j++) {
                int ruleToChange = (int) (Math.random() * 88);
                int ruleValue = (int) (Math.random() * 5);
                init.setRule(rules_prime, ruleToChange, ruleValue);
            //}

            // Evaluate f(x')
            int fitness_prime = automata.f(rules_prime, _SIZEMAX);

            // Test f(x')
            if (fitness <= fitness_prime) {
                fitness = fitness_prime;
                rules = rules_prime;
            }

            i++;

        } while (i < nbIter);

        return new Result(fitness, rules);
    }

}