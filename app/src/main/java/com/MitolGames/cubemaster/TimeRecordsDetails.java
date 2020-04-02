package com.MitolGames.cubemaster;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.MitolGames.cubemaster.util.GlobalConstants;
import com.MitolGames.cubemaster.util.Turn;

import java.util.Objects;

public class TimeRecordsDetails extends AppCompatActivity {

    TextView scrableTv;
    TextView timeTv, dateTv, tridTv;


    private ImageView tile0_0;
    private ImageView tile0_1;
    private ImageView tile0_2;
    private ImageView tile0_3;
    private ImageView tile0_4;
    private ImageView tile0_5;
    private ImageView tile0_6;
    private ImageView tile0_7;
    private ImageView tile0_8;
    private ImageView tile0_9;
    private ImageView tile0_10;
    private ImageView tile0_11;
    private ImageView tile0_12;
    private ImageView tile0_13;
    private ImageView tile0_14;
    private ImageView tile0_15;
    private ImageView tile0_16;
    private ImageView tile0_17;

    private ImageView tile1_0;
    private ImageView tile1_1;
    private ImageView tile1_2;
    private ImageView tile1_3;
    private ImageView tile1_4;
    private ImageView tile1_5;
    private ImageView tile1_6;
    private ImageView tile1_7;
    private ImageView tile1_8;
    private ImageView tile1_9;
    private ImageView tile1_10;
    private ImageView tile1_11;
    private ImageView tile1_12;
    private ImageView tile1_13;
    private ImageView tile1_14;
    private ImageView tile1_15;
    private ImageView tile1_16;
    private ImageView tile1_17;

    private ImageView tile2_0;
    private ImageView tile2_1;
    private ImageView tile2_2;
    private ImageView tile2_3;
    private ImageView tile2_4;
    private ImageView tile2_5;
    private ImageView tile2_6;
    private ImageView tile2_7;
    private ImageView tile2_8;
    private ImageView tile2_9;
    private ImageView tile2_10;
    private ImageView tile2_11;
    private ImageView tile2_12;
    private ImageView tile2_13;
    private ImageView tile2_14;
    private ImageView tile2_15;
    private ImageView tile2_16;
    private ImageView tile2_17;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_records_details);
        Typeface face = Typeface.createFromAsset(getAssets(), GlobalConstants.mainFontPath);
        Bundle arguments = getIntent().getExtras();
        scrableTv = findViewById(R.id.scramble_tv);
        timeTv = findViewById(R.id.timeDet_tv);
        dateTv = findViewById(R.id.dateDet_tv);
        tridTv = findViewById(R.id.trId_tv);
        initializeTiles();

        assert arguments != null;
        scrableTv.setText(Objects.requireNonNull(arguments.get("Scramble")).toString());
        timeTv.setText("Время: "+ Objects.requireNonNull(arguments.get("Time")).toString());
        dateTv.setText("Дата: "+ Objects.requireNonNull(arguments.get("Date")).toString());
        tridTv.setText("Сборка #"+ Objects.requireNonNull(arguments.get("id")).toString());

        timeTv.setTypeface(face);
        dateTv.setTypeface(face);
        tridTv.setTypeface(face);
        scrableTv.setTypeface(face);

        String[] turns = Objects.requireNonNull(arguments.get("Scramble")).toString().split(" ");
        ApplyScramble(turns);

    }


    public void initializeTiles(){
        tile0_0 = findViewById(R.id.cubeview_0_0);

        tile0_1=findViewById(R.id.cubeview_0_1);
        tile0_2=findViewById(R.id.cubeview_0_2);
        tile0_3=findViewById(R.id.cubeview_0_3);
        tile0_4=findViewById(R.id.cubeview_0_4);
        tile0_5=findViewById(R.id.cubeview_0_5);
        tile0_6=findViewById(R.id.cubeview_0_6);
        tile0_7=findViewById(R.id.cubeview_0_7);
        tile0_8=findViewById(R.id.cubeview_0_8);
        tile0_9=findViewById(R.id.cubeview_0_9);
        tile0_10=findViewById(R.id.cubeview_0_10);
        tile0_11=findViewById(R.id.cubeview_0_11);
        tile0_12=findViewById(R.id.cubeview_0_12);
        tile0_13=findViewById(R.id.cubeview_0_13);
        tile0_14=findViewById(R.id.cubeview_0_14);
        tile0_15=findViewById(R.id.cubeview_0_15);
        tile0_16=findViewById(R.id.cubeview_0_16);
        tile0_17=findViewById(R.id.cubeview_0_17);
        tile1_0=findViewById(R.id.cubeview_1_0);
        tile1_1=findViewById(R.id.cubeview_1_1);
        tile1_2=findViewById(R.id.cubeview_1_2);
        tile1_3=findViewById(R.id.cubeview_1_3);
        tile1_4=findViewById(R.id.cubeview_1_4);
        tile1_5=findViewById(R.id.cubeview_1_5);
        tile1_6=findViewById(R.id.cubeview_1_6);
        tile1_7=findViewById(R.id.cubeview_1_7);
        tile1_8=findViewById(R.id.cubeview_1_8);
        tile1_9=findViewById(R.id.cubeview_1_9);
        tile1_10=findViewById(R.id.cubeview_1_10);
        tile1_11=findViewById(R.id.cubeview_1_11);
        tile1_12=findViewById(R.id.cubeview_1_12);
        tile1_13=findViewById(R.id.cubeview_1_13);
        tile1_14=findViewById(R.id.cubeview_1_14);
        tile1_15=findViewById(R.id.cubeview_1_15);
        tile1_16=findViewById(R.id.cubeview_1_16);
        tile1_17=findViewById(R.id.cubeview_1_17);
        tile2_0=findViewById(R.id.cubeview_2_0);
        tile2_1=findViewById(R.id.cubeview_2_1);
        tile2_2=findViewById(R.id.cubeview_2_2);
        tile2_3=findViewById(R.id.cubeview_2_3);
        tile2_4=findViewById(R.id.cubeview_2_4);
        tile2_5=findViewById(R.id.cubeview_2_5);
        tile2_6=findViewById(R.id.cubeview_2_6);
        tile2_7=findViewById(R.id.cubeview_2_7);
        tile2_8=findViewById(R.id.cubeview_2_8);
        tile2_9=findViewById(R.id.cubeview_2_9);
        tile2_10=findViewById(R.id.cubeview_2_10);
        tile2_11=findViewById(R.id.cubeview_2_11);
        tile2_12=findViewById(R.id.cubeview_2_12);
        tile2_13=findViewById(R.id.cubeview_2_13);
        tile2_14=findViewById(R.id.cubeview_2_14);
        tile2_15=findViewById(R.id.cubeview_2_15);
        tile2_16=findViewById(R.id.cubeview_2_16);
        tile2_17=findViewById(R.id.cubeview_2_17);
    }

    //Блок отрисовки развертки кубика
    private String[][] cubeInChars = {
            //   L                  F                   R               B                   U            D   x - строка y - столбец
//          y:0    1    2       3    4    5       6    7   8        9   10   11       12   13  14     15   16   17
            {"o", "o", "o",    "g", "g", "g",    "r", "r", "r",    "b", "b", "b",    "w", "w", "w",   "y", "y", "y"},//x:0
            {"o", "o", "o",    "g", "g", "g",    "r", "r", "r",    "b", "b", "b",    "w", "w", "w",   "y", "y", "y"},//X:1
            {"o", "o", "o",    "g", "g", "g",    "r", "r", "r",    "b", "b", "b",    "w", "w", "w",   "y", "y", "y"} //X:2
    };

    private void ApplyScramble(String[] scramble) {
        cubeInChars =
                new String[][]{
                        //   L                  F                   R               B                   U            D   x - строка y - столбец
                        // y:0    1    2       3    4    5       6    7   8        9   10   11       12   13  14     15   16   17
                        {"o", "o", "o", "g", "g", "g", "r", "r", "r", "b", "b", "b", "w", "w", "w", "y", "y", "y"},//x:0
                        {"o", "o", "o", "g", "g", "g", "r", "r", "r", "b", "b", "b", "w", "w", "w", "y", "y", "y"},//X:1
                        {"o", "o", "o", "g", "g", "g", "r", "r", "r", "b", "b", "b", "w", "w", "w", "y", "y", "y"} //X:2
                };
        for (String str : scramble) {
            switch (str) {
                case "R": {
                    TurnR();
                    break;
                }
                case "R2": {
                    TurnR();
                    TurnR();
                    break;
                }
                case "R`": {
                    TurnR();
                    TurnR();
                    TurnR();
                    break;
                }
                case "L": {
                    TurnL();
                    break;
                }
                case "L2": {
                    TurnL();
                    TurnL();
                    break;
                }
                case "L`": {
                    TurnL();
                    TurnL();
                    TurnL();
                    break;
                }
                case "F": {
                    TurnF();
                    break;
                }
                case "F2": {
                    TurnF();
                    TurnF();
                    break;
                }
                case "F`": {
                    TurnF();
                    TurnF();
                    TurnF();
                    break;
                }
                case "U": {
                    TurnU();
                    break;
                }
                case "U2": {
                    TurnU();
                    TurnU();
                    break;
                }
                case "U`": {
                    TurnU();
                    TurnU();
                    TurnU();
                    break;
                }
                case "D": {
                    TurnD();
                    break;
                }
                case "D`": {
                    TurnD();
                    TurnD();
                    TurnD();
                    break;
                }
                case "D2": {
                    TurnD();
                    TurnD();
                    break;
                }
                case "B": {
                    TurnB();
                    break;
                }
                case "B2": {
                    TurnB();
                    TurnB();
                    break;
                }
                case "B`": {
                    TurnB();
                    TurnB();
                    TurnB();
                    break;
                }
            }
        }
        reDrawCube();

    }

    private void TurnB(){
        //Сохранение страых значений
        String t0 = cubeInChars[0][12];
        String t1 = cubeInChars[0][13];
        String t2 = cubeInChars[0][14];
        //Перестановка
        {
            cubeInChars[0][12]=cubeInChars[0][8];
            cubeInChars[0][13]=cubeInChars[1][8];
            cubeInChars[0][14]=cubeInChars[2][8];

            cubeInChars[0][8]=cubeInChars[2][17];
            cubeInChars[1][8]=cubeInChars[2][16];
            cubeInChars[2][8]=cubeInChars[2][15];

            cubeInChars[2][15]=cubeInChars[0][0];
            cubeInChars[2][16]=cubeInChars[1][0];
            cubeInChars[2][17]=cubeInChars[2][0];

            cubeInChars[0][0]=t2;
            cubeInChars[1][0]=t1;
            cubeInChars[2][0]=t0;}

        t0 = cubeInChars[0][9];
        t1 = cubeInChars[0][10];

        cubeInChars[0][10] = cubeInChars[1][9];
        cubeInChars[0][9] = cubeInChars[2][9];
        cubeInChars[1][9] = cubeInChars[2][10];

        cubeInChars[2][9] = cubeInChars[2][11];

        cubeInChars[2][10] = cubeInChars[1][11];
        cubeInChars[2][11] = cubeInChars[0][11];
        cubeInChars[1][11] = t1;
        cubeInChars[0][11] = t0;
    }

    private void TurnD(){
        //Сохранение страых значений
        String t0 = cubeInChars[2][3];
        String t1 = cubeInChars[2][4];
        String t2 = cubeInChars[2][5];
        //Перестановка
        {
            cubeInChars[2][5]=cubeInChars[2][2];
            cubeInChars[2][4]=cubeInChars[2][1];
            cubeInChars[2][3]=cubeInChars[2][0];

            cubeInChars[2][2]=cubeInChars[2][11];
            cubeInChars[2][1]=cubeInChars[2][10];
            cubeInChars[2][0]=cubeInChars[2][9];

            cubeInChars[2][11]=cubeInChars[2][8];
            cubeInChars[2][10]=cubeInChars[2][7];
            cubeInChars[2][9]=cubeInChars[2][6];

            cubeInChars[2][8]=t2;
            cubeInChars[2][7]=t1;
            cubeInChars[2][6]=t0;}

        t0 = cubeInChars[0][17];
        t1 = cubeInChars[0][16];

        cubeInChars[0][17] = cubeInChars[0][15];
        cubeInChars[0][16] = cubeInChars[1][15];
        cubeInChars[0][15] = cubeInChars[2][15];
        cubeInChars[1][15] = cubeInChars[2][16];
        cubeInChars[2][15] = cubeInChars[2][17];
        cubeInChars[2][16] = cubeInChars[1][17];
        cubeInChars[2][17] = t0;
        cubeInChars[1][17] = t1;
    }
    //Работает
    private void TurnU(){
        //Сохранение страых значений
        String t0 = cubeInChars[0][3];
        String t1 = cubeInChars[0][4];
        String t2 = cubeInChars[0][5];
        //Перестановка
        {
            cubeInChars[0][3]=cubeInChars[0][6];
            cubeInChars[0][4]=cubeInChars[0][7];
            cubeInChars[0][5]=cubeInChars[0][8];

            cubeInChars[0][6]=cubeInChars[0][9];
            cubeInChars[0][7]=cubeInChars[0][10];
            cubeInChars[0][8]=cubeInChars[0][11];

            cubeInChars[0][9]=cubeInChars[0][0];
            cubeInChars[0][10]=cubeInChars[0][1];
            cubeInChars[0][11]=cubeInChars[0][2];

            cubeInChars[0][0]=t0;
            cubeInChars[0][1]=t1;
            cubeInChars[0][2]=t2;}

        t0 = cubeInChars[0][12];
        t1 = cubeInChars[0][13];

        cubeInChars[0][13] = cubeInChars[1][12];
        cubeInChars[0][12] = cubeInChars[2][12];
        cubeInChars[1][12] = cubeInChars[2][13];
        cubeInChars[2][12] = cubeInChars[2][14];
        cubeInChars[2][13] = cubeInChars[1][14];
        cubeInChars[2][14] = cubeInChars[0][14];
        cubeInChars[1][14] = t1;
        cubeInChars[0][14] = t0;
    }
    //Работает
    private void TurnF(){
        //Сохранение страых значений
        String t0 = cubeInChars[2][12];
        String t1 = cubeInChars[2][13];
        String t2 = cubeInChars[2][14];
        //Перестановка
        {
            cubeInChars[2][12]=cubeInChars[2][2];
            cubeInChars[2][13]=cubeInChars[1][2];
            cubeInChars[2][14]=cubeInChars[0][2];

            cubeInChars[0][2]=cubeInChars[0][15];
            cubeInChars[1][2]=cubeInChars[0][16];
            cubeInChars[2][2]=cubeInChars[0][17];

            cubeInChars[0][15]=cubeInChars[2][6];
            cubeInChars[0][16]=cubeInChars[1][6];
            cubeInChars[0][17]=cubeInChars[0][6];

            cubeInChars[2][6]=t2;
            cubeInChars[1][6]=t1;
            cubeInChars[0][6]=t0;}


        t0 = cubeInChars[0][3];
        t1 = cubeInChars[0][4];

        cubeInChars[0][4] = cubeInChars[1][3];
        cubeInChars[0][3] = cubeInChars[2][3];
        cubeInChars[1][3] = cubeInChars[2][4];

        cubeInChars[2][3] = cubeInChars[2][5];

        cubeInChars[2][4] = cubeInChars[1][5];
        cubeInChars[2][5] = cubeInChars[0][5];
        cubeInChars[1][5] = t1;
        cubeInChars[0][5] = t0;
    }
    //Работает
    private void TurnL(){
        //Сохранение страых значений
        String t0 = cubeInChars[0][3];
        String t1 = cubeInChars[1][3];
        String t2 = cubeInChars[2][3];
        //Перестановка
        {
            cubeInChars[0][3]=cubeInChars[0][12];
            cubeInChars[1][3]=cubeInChars[1][12];
            cubeInChars[2][3]=cubeInChars[2][12];

            cubeInChars[0][12]=cubeInChars[2][11];
            cubeInChars[1][12]=cubeInChars[1][11];
            cubeInChars[2][12]=cubeInChars[0][11];

            cubeInChars[0][11]=cubeInChars[2][15];
            cubeInChars[1][11]=cubeInChars[1][15];
            cubeInChars[2][11]=cubeInChars[0][15];

            cubeInChars[0][15]=t0;
            cubeInChars[1][15]=t1;
            cubeInChars[2][15]=t2;}


        t0 = cubeInChars[0][0];
        t1 = cubeInChars[1][0];

        cubeInChars[0][0] = cubeInChars[2][0];
        cubeInChars[1][0] = cubeInChars[2][1];
        cubeInChars[2][0] = cubeInChars[2][2];

        cubeInChars[2][1] = cubeInChars[1][2];

        cubeInChars[2][2] = cubeInChars[0][2];
        cubeInChars[1][2] = cubeInChars[0][1];
        cubeInChars[0][2] = t0;
        cubeInChars[0][1] = t1;
    }
    //Работает
    private void TurnR(){
        //Сохранение страых значений
        String t0 = cubeInChars[0][5];
        String t1 = cubeInChars[1][5];
        String t2 = cubeInChars[2][5];
        //Перестановка
        {
            cubeInChars[0][5]=cubeInChars[0][17];
            cubeInChars[1][5]=cubeInChars[1][17];
            cubeInChars[2][5]=cubeInChars[2][17];

            cubeInChars[0][17]=cubeInChars[2][9];
            cubeInChars[1][17]=cubeInChars[1][9];
            cubeInChars[2][17]=cubeInChars[0][9];

            cubeInChars[0][9]=cubeInChars[2][14];
            cubeInChars[1][9]=cubeInChars[1][14];
            cubeInChars[2][9]=cubeInChars[0][14];

            cubeInChars[0][14]=t0;
            cubeInChars[1][14]=t1;
            cubeInChars[2][14]=t2;}


        t0 = cubeInChars[0][6];
        t1 = cubeInChars[1][6];

        cubeInChars[0][6] = cubeInChars[2][6];
        cubeInChars[1][6] = cubeInChars[2][7];
        cubeInChars[2][6] = cubeInChars[2][8];

        cubeInChars[2][7] = cubeInChars[1][8];

        cubeInChars[2][8] = cubeInChars[0][8];
        cubeInChars[1][8] = cubeInChars[0][7];
        cubeInChars[0][8] = t0;
        cubeInChars[0][7] = t1;



    }

    private void setTileColor(ImageView img, String color){
        switch (color){
            case "o":
                img.setImageResource(R.drawable.orange_tile);
                break;
            case "g":
                img.setImageResource(R.drawable.green_tile);
                break;
            case "r":
                img.setImageResource(R.drawable.re_tile);
                break;
            case "b":
                img.setImageResource(R.drawable.blue_tile);
                break;
            case "w":
                img.setImageResource(R.drawable.whit_tile);
                break;
            case "y":
                img.setImageResource(R.drawable.yellow_tile);
                break;

        }
    }

    private void reDrawCube(){
        setTileColor(tile0_0, cubeInChars[0][0]);
        setTileColor(tile0_1, cubeInChars[0][1]);
        setTileColor(tile0_2, cubeInChars[0][2]);
        setTileColor(tile0_3, cubeInChars[0][3]);
        setTileColor(tile0_4, cubeInChars[0][4]);
        setTileColor(tile0_5, cubeInChars[0][5]);
        setTileColor(tile0_6, cubeInChars[0][6]);
        setTileColor(tile0_7, cubeInChars[0][7]);
        setTileColor(tile0_8, cubeInChars[0][8]);
        setTileColor(tile0_9, cubeInChars[0][9]);
        setTileColor(tile0_10, cubeInChars[0][10]);
        setTileColor(tile0_11, cubeInChars[0][11]);
        setTileColor(tile0_12, cubeInChars[0][12]);
        setTileColor(tile0_13, cubeInChars[0][13]);
        setTileColor(tile0_14, cubeInChars[0][14]);
        setTileColor(tile0_15, cubeInChars[0][15]);
        setTileColor(tile0_16, cubeInChars[0][16]);
        setTileColor(tile0_17, cubeInChars[0][17]);

        setTileColor(tile1_0, cubeInChars[1][0]);
        setTileColor(tile1_1, cubeInChars[1][1]);
        setTileColor(tile1_2, cubeInChars[1][2]);
        setTileColor(tile1_3, cubeInChars[1][3]);
        setTileColor(tile1_4, cubeInChars[1][4]);
        setTileColor(tile1_5, cubeInChars[1][5]);
        setTileColor(tile1_6, cubeInChars[1][6]);
        setTileColor(tile1_7, cubeInChars[1][7]);
        setTileColor(tile1_8, cubeInChars[1][8]);
        setTileColor(tile1_9, cubeInChars[1][9]);
        setTileColor(tile1_10, cubeInChars[1][10]);
        setTileColor(tile1_11, cubeInChars[1][11]);
        setTileColor(tile1_12, cubeInChars[1][12]);
        setTileColor(tile1_13, cubeInChars[1][13]);
        setTileColor(tile1_14, cubeInChars[1][14]);
        setTileColor(tile1_15, cubeInChars[1][15]);
        setTileColor(tile1_16, cubeInChars[1][16]);
        setTileColor(tile1_17, cubeInChars[1][17]);

        setTileColor(tile2_0, cubeInChars[2][0]);
        setTileColor(tile2_1, cubeInChars[2][1]);
        setTileColor(tile2_2, cubeInChars[2][2]);
        setTileColor(tile2_3, cubeInChars[2][3]);
        setTileColor(tile2_4, cubeInChars[2][4]);
        setTileColor(tile2_5, cubeInChars[2][5]);
        setTileColor(tile2_6, cubeInChars[2][6]);
        setTileColor(tile2_7, cubeInChars[2][7]);
        setTileColor(tile2_8, cubeInChars[2][8]);
        setTileColor(tile2_9, cubeInChars[2][9]);
        setTileColor(tile2_10, cubeInChars[2][10]);
        setTileColor(tile2_11, cubeInChars[2][11]);
        setTileColor(tile2_12, cubeInChars[2][12]);
        setTileColor(tile2_13, cubeInChars[2][13]);
        setTileColor(tile2_14, cubeInChars[2][14]);
        setTileColor(tile2_15, cubeInChars[2][15]);
        setTileColor(tile2_16, cubeInChars[2][16]);
        setTileColor(tile2_17, cubeInChars[2][17]);

    }
}
