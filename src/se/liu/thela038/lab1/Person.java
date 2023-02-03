package se.liu.thela038.lab1;

import java.time.LocalDate;
import java.time.Period;

public class Person {
    private String name;
    private LocalDate birthDay;

    public Person(final String name, final LocalDate birthDay) {
	this.name = name;
	this.birthDay = birthDay;
    }
    public int getAge(){
        LocalDate now = LocalDate.now();
        Period timeBetween = Period.between(birthDay, now);
        return timeBetween.getYears();
    }

    @Override public String toString() {
	return name + " " + getAge();
    }

    public static void main(String[] args) {
	Person mig = new Person("Theodor", LocalDate.of(2001, 11, 3));
	Person anna = new Person("Anna", LocalDate.of(201, 11, 3));
	Person hakan = new Person("Håkan", LocalDate.of(1997, 11, 3));
	System.out.println(mig.getAge());
	System.out.println(mig);
	System.out.println(anna);
	System.out.println(hakan);
    }
}
