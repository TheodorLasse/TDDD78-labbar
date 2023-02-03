package se.liu.thela038.lab3;

import se.liu.thela038.lab1.Person;

import java.time.LocalDate;

public class QueueTest
{
    public static void main(String[] args) {
	Stack stack = new Stack();
	stack.push(new Person("Theodor", LocalDate.of(2001, 11, 3)));
	stack.push(new Person("Theodor1", LocalDate.of(2001, 11, 3)));
	stack.push(new Person("Theodor2", LocalDate.of(2001, 11, 3)));
	stack.push(new Person("Theodor3", LocalDate.of(2001, 11, 3)));
	stack.push(new Person("Theodor4", LocalDate.of(2001, 11, 3)));

	Queue queue = new Queue();
	queue.enqueue(new Person("Theodor", LocalDate.of(2001, 11, 3)));
	queue.enqueue(new Person("Theodor1", LocalDate.of(2001, 11, 3)));
	queue.enqueue(new Person("Theodor2", LocalDate.of(2001, 11, 3)));
	queue.enqueue(new Person("Theodor3", LocalDate.of(2001, 11, 3)));
	queue.enqueue(new Person("Theodor4", LocalDate.of(2001, 11, 3)));

	while(!stack.isEmpty()){
	    System.out.println(stack.pop());
	}

	while(!queue.isEmpty()){
	    System.out.println(queue.dequeue());
	}
    }
}
