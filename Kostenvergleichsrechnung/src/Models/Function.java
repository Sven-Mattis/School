package Models;

import java.util.ArrayList;
import java.util.Arrays;

public class Function {

    private Double[] high  = new Double[0];
    private Double[] ms    = new Double[0];
    private Double[] bs    = new Double[0];

    public Function (String f) {
        assert !f.equals("");

        String[] teile = f.split("\\+|\\*|:|/");
        high = new Double[teile.length];
        ms = new Double[teile.length];
        bs = new Double[teile.length];
        for(int i = 0; i < teile.length; i++) {
            double hight = 1;
            double m = 0;
            double b = 0;
            System.out.println(teile[i]);
            if(teile[i].contains("x")) {
                teile[i] = teile[i].replace("x","");
                String[] teile2 = teile[i].split("\\^");
                m = Double.parseDouble(teile2[0]);
                if(teile2.length > 1)
                    hight = Double.parseDouble(teile2[1]);
            } else {
                b = Double.parseDouble(teile[i]);
            }
            bs[i] = b;
            ms[i] = m;
            high[i] = hight;
        }
    }

    public double f(double x) {
        double number = 0;

        for(int i = 0; i < ms.length; i++) {
            double xx = Math.pow(x,high[i]);
            number += bs[i];
            number += ms[i]*xx;
        }

        return number;
    }

}
