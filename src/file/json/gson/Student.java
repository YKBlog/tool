package file.json.gson;

import java.sql.Timestamp;
import java.util.Date;

public class Student {
    private int id;
    private String name;
    private Date birthDay;
    private Timestamp time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", birthDay=" + birthDay + ", time=" + time + "]";
    }

}