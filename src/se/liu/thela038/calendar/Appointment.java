package se.liu.thela038.calendar;

public class Appointment
{
    private String subject;
    private SimpleDate date;
    private TimeSpan timeSpan;

    public Appointment(final String subject, final SimpleDate date, final TimeSpan timeSpan) {
	this.subject = subject;
	this.date = date;
	this.timeSpan = timeSpan;
    }

    public String getSubject() {
	return subject;
    }

    public SimpleDate getDate() {
	return date;
    }

    public TimeSpan getTimeSpan() {
	return timeSpan;
    }

    public String toString(){
        return "Datum: " + date + " Klockslag: " + timeSpan;
    }
}
