package com.MitolGames.cubemaster.util;

import java.util.Random;

public class ScrambleGenerator {

    private int amountOfTurns;

    private Turn[] scramble;

    private Turn[] turns = new Turn[18];


    public ScrambleGenerator(int amountOfTurns){
        this.amountOfTurns = amountOfTurns;
        FillTurnsArray();
        scramble = new Turn[amountOfTurns];

    }

    public ScrambleGenerator(){
        this.amountOfTurns = 20;
        FillTurnsArray();
        scramble = new Turn[amountOfTurns];
    }

    public String GenerateScramble(){
        GenScrambleArray();
        return printScrambleArray();
    }


    private void GenScrambleArray(){
        //( Math.random() * (b-a) ) + a
        Random random = new Random();
        scramble[0] = turns[random.nextInt(17)+1];


        for(int i=1;i<scramble.length; i++) {

            int temp = random.nextInt(17) + 1;
            while (turns[temp].getAxisIndex() == scramble[i - 1].getAxisIndex()) {
                temp = random.nextInt(17) + 1;
            }
            scramble[i] = turns[temp];
            }
        }


    private String printScrambleArray(){
        String scrambleString = "";

        for(int i=0;i<scramble.length; i++){
            scrambleString += " " + scramble[i].getName();
        }

        return scrambleString;
    }

    private void FillTurnsArray(){
        Turn turn0 = new Turn(0, "R"); turns[0] = turn0;
        Turn turn1 = new Turn(0, "L"); turns[1] = turn1;
        Turn turn2 = new Turn(0, "R2"); turns[2] = turn2;
        Turn turn3 = new Turn(0, "L2"); turns[3] = turn3;
        Turn turn4 = new Turn(0, "R`"); turns[4] = turn4;
        Turn turn5 = new Turn(0, "L`"); turns[5] = turn5;
        //Enede with 0 axis index
        Turn turn6 = new Turn(1, "F"); turns[6] = turn6;
        Turn turn7 = new Turn(1, "B"); turns[7] = turn7;
        Turn turn8 = new Turn(1, "F2"); turns[8] = turn8;
        Turn turn9 = new Turn(1, "B2"); turns[9] = turn9;
        Turn turn10 = new Turn(1, "F`"); turns[10] = turn10;
        Turn turn11 = new Turn(1, "B`"); turns[11] = turn11;
        //ended with 1 axis
        Turn turn12 = new Turn(2, "U"); turns[12] = turn12;
        Turn turn13 = new Turn(2, "D"); turns[13] = turn13;
        Turn turn14 = new Turn(2, "U2"); turns[14] = turn14;
        Turn turn15 = new Turn(2, "D2"); turns[15] = turn15;
        Turn turn16 = new Turn(2, "U`"); turns[16] = turn16;
        Turn turn17 = new Turn(2, "D`"); turns[17] = turn17;

    }

    public Turn[] getScramble(){
        return scramble;
    }

}

class Turn{
    String name;
    int AxisIndex;
    /* R L R2 L2 R` L` = 0
       F B F2 B2 F` B` = 1
       U D U2 D2 U` D` = 2
    */

    public Turn(int AxisIndex, String name){
        this.AxisIndex = AxisIndex;
        this.name = name;
    }
    public Turn(String name){
        this.name = name;
        this.AxisIndex = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAxisIndex() {
        return AxisIndex;
    }

    public void setAxisIndex(int axisIndex) {
        AxisIndex = axisIndex;
    }
}
