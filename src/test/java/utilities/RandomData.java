package utilities;

public class RandomData {

    public static int randomNumber(int max, int min){
        return (int)Math.random() * (max - min) + min;
    }
}
