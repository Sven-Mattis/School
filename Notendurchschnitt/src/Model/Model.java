package Model;

public class Model {

    private int[] kas;
    private int mund;
    private double average = 0;
    
    public Model (int[] kas, int mund) {
        this.kas = kas;
        this.mund = mund;
    }

    public double getAverage () {
        if(this.average != 0)
            return this.average;

        int kasA = 0;

        for (int ka : kas) {
            kasA += ka;
        }

        this.average = (((kasA/((float)(kas.length)))*.45) + (mund*.55));
        return (((kasA/((float)(kas.length)))*.45) + (mund*.55));
    }

    @Override
    public String toString () {
        if (this.average == 0)
            this.getAverage();

        assert ( this.average < 7 && this.average > 0 );

        return switch ((int) (this.average)) {
            case 1 -> "Sehr Gut";
            case 2 -> "Gut";
            case 3 -> "Befriedigend";
            case 4 -> "Ausreichend";
            case 5 -> "Mangelhaft";
            case 6 -> "Ungenügend";
            default -> throw new IllegalStateException("Unexpected value: " + (int) Math.round(this.average));
        };
    }

}
