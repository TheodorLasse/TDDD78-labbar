package se.liu.thela038.lab3;

import se.liu.thela038.lab1.Person;

public class Queue extends ListManipulator
{
    public void enqueue(Person person) {
        elements.add(size(), person);
    }

    public Person dequeue() {
	Person person = elements.get(0);
	elements.remove(0);
	return person;
    }
}
