package com.bignerdranch.android.sc.clockpage.weekcalendar;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalendarUtils
{
    public static LocalDate selectedDate;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String formattedDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return date.format(formatter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String formattedTime(LocalTime time)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        return time.format(formatter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String monthYearFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" dd yyyy");
        return getMonthFormatted(date.getMonthValue()) + date.format(formatter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static LocalDate getLocalDate(int year, int month, int day)
    {
        LocalDate date = LocalDate.of(year, month, day);
        return date;
    }

    public String makeDateString(int day,int month,int year)
    {
        return getMonthFormatted(month) + " " + day + " " + year;
    }

    private static String getMonthFormatted(int month)
    {
        switch (month)
        {
            case 1 : return "Jan";
            case 2 : return "Feb";
            case 3 : return "Mar";
            case 4 : return "Apr";
            case 5 : return "May";
            case 6 : return "Jun";
            case 7 : return "Jul";
            case 8 : return "Aug";
            case 9 : return "Sep";
            case 10 : return "Oct";
            case 11 : return "Nov";
            case 12 : return "Dec";
        }

        return "JUN";
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<LocalDate> daysInMonthArray(LocalDate date)
    {
        ArrayList<LocalDate> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = CalendarUtils.selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i <= 42; i++ )
        {
            if (i <= dayOfWeek || i> daysInMonth + dayOfWeek)
                daysInMonthArray.add(null);
            else
                daysInMonthArray.add(LocalDate.of(selectedDate.getYear(),selectedDate.getMonth(),i - dayOfWeek));
        }
        return daysInMonthArray;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<LocalDate> daysInWeekArray(LocalDate selectedDate)
    {
        ArrayList<LocalDate> days = new ArrayList<>();
        LocalDate current = sundayForDate(selectedDate);
        LocalDate endDate = current.plusWeeks(1);

        while (current.isBefore(endDate))
        {
            days.add(current);
            current= current.plusDays(1);
        }

        return days;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static LocalDate sundayForDate(LocalDate current)
    {
        LocalDate oneWeekAgo = current.minusWeeks(1);

        while (current.isAfter(oneWeekAgo))
        {
            if (current.getDayOfWeek() == DayOfWeek.SUNDAY)
                return current;

            current = current.minusDays(1);
        }
        return null;
    }


}
