public class Fibonacci {

    public Fibonacci (int x) {
        x = x/2;
        int number1 = 1, number2 = 1;

        while(x > 0) {
            x--;

            System.out.println(number1);
            System.out.println(number2);
            number1 += number1 <= number2 ? number2 : 0;
            number2 += number2 < number1 ? number1 : 0;
        }
    }

}
