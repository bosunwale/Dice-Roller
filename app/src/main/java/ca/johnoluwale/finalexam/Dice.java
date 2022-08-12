package ca.johnoluwale.finalexam;

import android.util.Log;

public class Dice {
    private int numOfSides;
    private int sideUp;

    public Dice(int numOfDiceSides){
        this.numOfSides = numOfDiceSides;
        roll();
    }
    public int getSideUp(){
        Log.d("Dice", String.valueOf(sideUp));
        return sideUp;
    }
    public void roll(){
      sideUp =  (int)(Math.random() * numOfSides);
    }
}
