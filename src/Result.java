public class Result {

    private int fitness;
    private int[] rules;

    public Result(int fitness, int[] rules) {
        this.fitness = fitness;
        this.rules = rules;
    }

    public int getFitness() {
        return fitness;
    }

    public int[] getRules() {
        return rules;
    }
}
