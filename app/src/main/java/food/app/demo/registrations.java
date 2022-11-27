package food.app.demo;

import com.google.firebase.firestore.Exclude;

public class registrations {

    private String name;
    private String phone;
    private String ID;

    //empty constructor
    public registrations() {
    }

    public registrations(String name, String phone, String ID) {
        this.name = name;
        this.phone = phone;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
@Exclude
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }






}

