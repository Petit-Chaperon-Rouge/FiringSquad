public class ILS {

    private static final int _NBRULES = 216;
    private static final int _SIZEMAX = 20;

    public Result ILS(int nbIter, Automata automata) {

        Initialization init = new Initialization();
        HillClimber hc = new HillClimber();

        Result res = hc.firstImprovement(100000, automata);

        int rules[] = res.getRules();
        int fitness = res.getFitness();

        int i = 0;

        do {
            int rules_prime[] = rules;

            // Choose x' from the neighborhood with 10 rules of difference
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

        System.out.println(fitness);
        for(int k = 0; k < _NBRULES; k++){
            System.out.print(rules[k] + " ");
        }

        return new Result(fitness, rules);
    }

}