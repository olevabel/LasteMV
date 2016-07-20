package ato.spordiklubi.lasteMV.data;

/**
 * Created by Olev on 26.07.2015.
 */
public class Participant {



    private int number;
    private String name;


    public Participant(){

    }
    public Participant(int number, String name) {
        this.number = number;
        this.name = name;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
