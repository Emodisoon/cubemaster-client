package com.MitolGames.cubemaster.util;

public class Turn {
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
