package Table;

public class Table {
    String date;
    String name;
    String time;

    String table;
    String people;
    String note;

    public Table(String date, String name, String time, String table, String people) {
        this.date = date;
        this.name = name;
        this.time = time;
        this.table = table;
        this.people = people;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Table(String date, String name, String table, String time, String people, String note) {
        this.date = date;
        this.name = name;
        this.time = time;
        this.table = table;
        this.people = people;
        this.note = note;
    }

    @Override
    public String toString() {
        return "Date: " + date + " Under Name: " + name + " Time: " + time + " Table number: " + table
                + " Number of people: " + people + " Note: " + note;

    }

}
