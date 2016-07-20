package ato.spordiklubi.lasteMV.data;

/**
 * Created by Olev on 27.07.2015.
 */
public class Result {

    private String dicipline;
    private double result;


    public Result (){

    }
    public Result(String dicipline, double result) {
        this.dicipline = dicipline;
        this.result = result;
    }


    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getDicipline() {
        return dicipline;
    }

    public void setDicipline(String dicipline) {
        this.dicipline = dicipline;
    }


}
