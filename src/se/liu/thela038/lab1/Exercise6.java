package se.liu.thela038.lab1;

public class Exercise6
{
    public static void main(String[] args) {
	for (int i = 2; i < 100; i++) {
	    if(isPrime(i)){
		System.out.println(i + " is a prime number");
	    }
	}
    }
    public static boolean isPrime(int number){
        for(int i = 2; i < number; i++){
	    int rest = number % i;
	    if (rest == 0) {
		return false;
	    }
	}
        return true;
    }
}
