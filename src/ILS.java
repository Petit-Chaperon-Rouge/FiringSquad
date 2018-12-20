public class ILS {

    private static final int _NBRULES = 216;

    public Result ILS(int nbIter, int nbIterFI, Automata automata) {

        Initialization init = new Initialization();
        HillClimber hc = new HillClimber();

        Result res = hc.firstImprovement(nbIterFI, automata);

        int rules[] = res.getRules();
        int fitness = res.getFitness();

        int i = 0;

        do {
            int rules_prime[] = rules;

            // Choose x' from the neighborhood with between 5 rules of difference
            for (int j = 0; j < 5; j++) {
                int ruleToChange = (int) (Math.random() * 88);
                int ruleValue = (int) (Math.random() * 5);
                init.setRule(rules_prime, ruleToChange, ruleValue);
            }

            // Do a First Improvement HillClimber with the perturbated rules
            Result res_prime = hc.firstImprovement(nbIterFI, automata, rules_prime);

            // Test f(x')
            if (fitness < res_prime.getFitness()) {
                fitness = res_prime.getFitness();
                rules = res_prime.getRules();
            }

            i++;

        } while (i < nbIter);

        return new Result(fitness, rules);
    }

}