package is.mista.bioapp;

import java.util.List;

/**
 * Created by Tasteless on 20.3.2016.
 */
public class Cinema {
    String name;
    List<String> schedule;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<String> schedule) {
        this.schedule = schedule;
    }
}
