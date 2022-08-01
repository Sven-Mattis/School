package Models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Person {

    private final String firstName;
    private final String lastName;
    private final double weight;
    private final double height;

    private double bmi;

    public Person(String firstName, String lastName, double weight, double height) {
        this.firstName = firstName;
        this.lastName = lastName;

        this.weight = weight;
        this.height = height/100;

        this.bmi = -10;
    }


    public double getBMI () {
        if( this.bmi > 0 )
            return this.bmi;
        this.bmi = this.weight / (Math.pow(this.height, 2));
        return this.bmi;
    }

    public String getOutEvaluation () {
        if(this.bmi <= 19) {
            return "Untergewicht";
        } else if(this.bmi <= 25) {
            return "Normal Gewicht";
        } else if(this.bmi <= 29) {
            return "Leichtes Übergewicht";
        } else if(this.bmi <= 39) {
            return "Übergewicht";
        }
        return "Extremes Übergewicht";
    }

    public void save () throws IOException {
        FileWriter writer = new FileWriter(this.firstName + this.lastName + ".txt");

        writer.write(this.firstName + " " + this.lastName + ";" + this.weight + "kg;" + (this.height*100) + "cm;BMI=" + this.bmi + ";Evalutaion=" + this.getOutEvaluation());

        writer.close();
    }
}
