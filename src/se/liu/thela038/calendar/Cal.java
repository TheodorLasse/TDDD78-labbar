package se.liu.thela038.calendar;

import java.util.ArrayList;
import java.util.List;

import static se.liu.thela038.calendar.Month.getMonthDays;
import static se.liu.thela038.calendar.Month.getMonthNumber;

public class Cal
{
    private List<Appointment> appointments;

    public Cal() {
	appointments = new ArrayList<>();
    }

    public void show(){
	for (Appointment appointment : appointments) {
	    System.out.println(appointment);
	}
    }

    public void book(int year, String month, int day,
		     int startHour, int startMinute, int endHour,
		     int endMinute, String subject){
        if (isaBoolean(year, month, day, startHour, startMinute, endHour, endMinute))
	    throw new IllegalArgumentException("felmeddelande");

        Month bookMonth = new Month(month);
        SimpleDate bookSimpleDate = new SimpleDate(year, bookMonth, day);
        TimePoint start = new TimePoint(startHour, startMinute);
        TimePoint end = new TimePoint(endHour, endMinute);
        TimeSpan bookTimeSpan = new TimeSpan(start, end);
        Appointment bookAppointment = new Appointment(subject, bookSimpleDate, bookTimeSpan);
        appointments.add(bookAppointment);
    }

    private boolean isaBoolean(final int year, final String month, final int day, final int startHour, final int startMinute,
			       final int endHour, final int endMinute)
    {
	return !(year > 1970 && 0 <= startHour && startHour <= 23 && 0 <= endHour && endHour <= 23 && 0 <= startMinute &&
		 startMinute <= 59 && 0 <= endMinute && endMinute <= 59 && getMonthNumber(month) != -1 && day <= getMonthDays(month));
    }

    public static void main(String[] args) {
	Cal calender = new Cal();
	calender.book(2014, "April", 10, 14, 20, 15, 40, "Tandläkare");
	calender.book(2014, "March", 16, 14, 50, 15, 40, "Fotvård");
	calender.book(2014, "November", 11, 14, 20, 15, 40, "Massage");
	calender.book(2014, "February", 10, 14, 20, 15, 40, "Ortoped");
	calender.book(2014, "April", 15, 17, 20, 18, 40, "Rehabilitering");
	calender.book(2014, "January", 7, 14, 20, 15, 40, "Grupp terapi");
	calender.book(2014, "April", 8, 14, 20, 15, 40, "Hypnos terapi");
	calender.book(2014, "April", 9, 14, 20, 15, 40, "Vanlig terapi den här gången");
	calender.book(2014, "April", 10, 20, 00, 23, 59, "Riktig terapi, sticka till puben");
	calender.show();

    }
}
