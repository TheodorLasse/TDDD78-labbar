package se.liu.thela038.lab1;

import javax.swing.*;

public class Exercise9
{
    public static void main(String[] args) {
	String number =
		JOptionPane.showInputDialog("Please input a value");

	System.out.println("Roten ur " + number + " är " + findRoot(Double.parseDouble(number)));
    }

    public static double findRoot(double number){
        double guess = number;
        for (int i = 0; i < 11; i++) {
            guess -= (guess*guess-number) / (2*guess);
        }
        return guess;
    }
    
}
