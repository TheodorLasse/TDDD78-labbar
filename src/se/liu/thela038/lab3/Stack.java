package se.liu.thela038.lab3;

import se.liu.thela038.lab1.Person;

public class Stack extends ListManipulator
{

    public void push(Person person) {
        elements.add(0, person);
    }

    public Person pop() {
	Person person = elements.get(0);
	elements.remove(0);
	return person;
    }
}
