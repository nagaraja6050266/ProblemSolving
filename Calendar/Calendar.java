class MyDate {
    //Constructor
    MyDate(int day, int month, int year) {
        if (DateOperations.isValid(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        } else {
            System.out.println("Invalid Date Format");
        }
    }

    private int day;
    private int month;
    private int year;

    //Getter
    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    //Setter
    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static int[] getValidDays(int year) {
        int[] validDates = {31, DateOperations.isLeapYear(year) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return validDates;
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }
}

//Operations
class DateOperations {

    public static boolean isValid(int day, int month, int year) {
        if (year < 1 || month < 0 || month > 12) {
            return false;
        }
        int[] validDays = MyDate.getValidDays(year);
        return validDays[month - 1] >= day;
    }

    public static boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else {
            return year % 4 == 0;
        }
    }

    public static void addDays(MyDate date, int daysToAdd) {
        int day = date.getDay();
        int month = date.getMonth();
        int year = date.getYear();
        int[] validDays = MyDate.getValidDays(year);

        //Increase Months untill addable days come
        while (daysToAdd > 0) {
            if (daysToAdd + day < validDays[month - 1]) {
                day += daysToAdd;
                break;
            } else {
                daysToAdd -= validDays[month - 1] + day - 1;
                month++;
                if (month > 12) {
                    month = 1;
                    year++;
                }
            }
        }
        date.setMonth(month);
        date.setDay(day);
        date.setYear(year);
    }

}

class Calendar {
    public static void main(String[] args) {
        MyDate today = new MyDate(5, 9, 2024);
        DateOperations.addDays(today, 45);
        System.out.println(today.toString());
    }
}