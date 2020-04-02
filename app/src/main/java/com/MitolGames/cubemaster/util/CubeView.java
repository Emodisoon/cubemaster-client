package com.MitolGames.cubemaster.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CubeView extends View {
    Paint rectPaintRed;
    Paint rectPaintGreen;
    Paint rectPaintBlue;
    Paint rectPaintOrange;
    Paint rectPaintWhite;
    Paint rectPaintYellow;
    Paint borderPaint;

    public static final int RECT_WIDTH = 40;

    private final String[][] COMPLETED_CUBE = {
            //   L                  F                   R               B                   U            D   x - строка y - столбец
//          y:0    1    2       3    4    5       6    7   8        9   10   11       12   13  14     15   16   17
            {"o", "o", "o",    "g", "g", "g",    "r", "r", "r",    "b", "b", "b",    "w", "w", "w",   "y", "y", "y"},//x:0
            {"o", "o", "o",    "g", "g", "g",    "r", "r", "r",    "b", "b", "b",    "w", "w", "w",   "y", "y", "y"},//X:1
            {"o", "o", "o",    "g", "g", "g",    "r", "r", "r",    "b", "b", "b",    "w", "w", "w",   "y", "y", "y"} //X:2
    };

    private String[][] cubeInChars;


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
        t2 = cubeInChars[2][6];

        cubeInChars[0][6] = cubeInChars[2][6];
        cubeInChars[1][6] = cubeInChars[2][7];
        cubeInChars[2][6] = cubeInChars[2][8];

        cubeInChars[2][7] = cubeInChars[1][8];

        cubeInChars[2][8] = cubeInChars[0][8];
        cubeInChars[1][8] = cubeInChars[0][7];
        cubeInChars[0][8] = t0;
        cubeInChars[0][7] = t1;



    }

    public CubeView(Context context, AttributeSet attrs){
        super(context, attrs);
        rectPaintRed = new Paint();
        rectPaintGreen = new Paint();
        rectPaintBlue = new Paint();
        rectPaintOrange = new Paint();
        rectPaintWhite = new Paint();
        rectPaintYellow = new Paint();
        borderPaint = new Paint();

        rectPaintRed.setColor(Color.RED);
        rectPaintGreen.setColor(Color.GREEN);
        rectPaintBlue.setColor(Color.BLUE);
        rectPaintOrange.setColor(Color.rgb(255,145,0));
        rectPaintWhite.setColor(Color.WHITE);
        rectPaintYellow.setColor(Color.YELLOW);
        borderPaint.setColor(Color.argb(255, 0, 0 ,0));
        borderPaint.setStrokeWidth(10);

        cubeInChars = COMPLETED_CUBE;
    }
    @Override
    protected void onDraw(Canvas canvas) {


        //canvas.drawARGB(100, 201, 201, 201);
        //Сетка
        {
            canvas.drawLine(25, 195, 25, 355, borderPaint);//Вертикальная
            canvas.drawLine(35 + RECT_WIDTH, 195, 35 + RECT_WIDTH, 355, borderPaint);//Вертикальная 2
            canvas.drawLine(85 + RECT_WIDTH, 195, 85 + RECT_WIDTH, 355, borderPaint);//Вертикальная 3

            canvas.drawLine(135 + RECT_WIDTH, 55, 135 + RECT_WIDTH, 495, borderPaint);//Вертикальная 4 болшая
            canvas.drawLine(185 + RECT_WIDTH, 55, 185 + RECT_WIDTH, 495, borderPaint);//Вертикальная 5 болшая
            canvas.drawLine(235 + RECT_WIDTH, 55, 235 + RECT_WIDTH, 495, borderPaint);//Вертикальная 6 болшая
            canvas.drawLine(285 + RECT_WIDTH, 55, 285 + RECT_WIDTH, 495, borderPaint);//Вертикальная 7
            canvas.drawLine(335 + RECT_WIDTH, 195, 335 + RECT_WIDTH, 355, borderPaint);//Вертикальная 7
            canvas.drawLine(385 + RECT_WIDTH, 195, 385 + RECT_WIDTH, 355, borderPaint);//Вертикальная 7
            canvas.drawLine(435 + RECT_WIDTH, 195, 435 + RECT_WIDTH, 355, borderPaint);//Вертикальная 7

            canvas.drawLine(485 + RECT_WIDTH, 195, 485 + RECT_WIDTH, 355, borderPaint);//Вертикальная 7
            canvas.drawLine(535 + RECT_WIDTH, 195, 535 + RECT_WIDTH, 355, borderPaint);//Вертикальная 7
            canvas.drawLine(585 + RECT_WIDTH, 195, 585 + RECT_WIDTH, 355, borderPaint);//Вертикальная 7

            canvas.drawLine(20, 200, 620, 200, borderPaint);//Горизонтальная
            canvas.drawLine(20, 210 + RECT_WIDTH, 620, 210 + RECT_WIDTH, borderPaint);//Горизонтальная 2
            canvas.drawLine(20, 260 + RECT_WIDTH, 620, 260 + RECT_WIDTH, borderPaint);//Горизонтальная 3
            canvas.drawLine(20, 310 + RECT_WIDTH, 620, 310 + RECT_WIDTH, borderPaint);//Горизонтальная 4

            canvas.drawLine(170, 50, 330, 50, borderPaint);//Горизонтальная 4
            canvas.drawLine(170, 100, 330, 100, borderPaint);//Горизонтальная 4
            canvas.drawLine(170, 150, 330, 150, borderPaint);//Горизонтальная 4
            canvas.drawLine(170, 200, 330, 200, borderPaint);//Горизонтальная 4
            canvas.drawLine(170, 250, 330, 250, borderPaint);//Горизонтальная 4
            canvas.drawLine(170, 300, 330, 300, borderPaint);//Горизонтальная 4
            canvas.drawLine(170, 350, 330, 350, borderPaint);//Горизонтальная 4
            canvas.drawLine(170, 400, 330, 400, borderPaint);//Горизонтальная 4
            canvas.drawLine(170, 450, 330, 450, borderPaint);//Горизонтальная 4
            canvas.drawLine(170, 500, 330, 500, borderPaint);//Горизонтальная 4
        }
        //Большой  горизонтальный блок


        {
            //Оранжевый блок
            canvas.drawRect(30, 205, 30 + RECT_WIDTH, 205 + RECT_WIDTH, getPaintByColorIndex(0,0));
            canvas.drawRect(30, 255, 30 + RECT_WIDTH, 255 + RECT_WIDTH, getPaintByColorIndex(1,0));
            canvas.drawRect(30, 305, 30 + RECT_WIDTH, 305 + RECT_WIDTH, getPaintByColorIndex(2,0));

            canvas.drawRect(80, 205, 80 + RECT_WIDTH, 205 + RECT_WIDTH, getPaintByColorIndex(0,1));
            canvas.drawRect(80, 255, 80 + RECT_WIDTH, 255 + RECT_WIDTH, getPaintByColorIndex(1,1));
            canvas.drawRect(80, 305, 80 + RECT_WIDTH, 305 + RECT_WIDTH, getPaintByColorIndex(2,1));

            canvas.drawRect(130, 205, 130 + RECT_WIDTH, 205 + RECT_WIDTH, getPaintByColorIndex(0,2));
            canvas.drawRect(130, 255, 130 + RECT_WIDTH, 255 + RECT_WIDTH, getPaintByColorIndex(1,2));
            canvas.drawRect(130, 305, 130 + RECT_WIDTH, 305 + RECT_WIDTH, getPaintByColorIndex(2,2));
            //Зеленый блок
            canvas.drawRect(180, 205, 180 + RECT_WIDTH, 205 + RECT_WIDTH, getPaintByColorIndex(0,3));
            canvas.drawRect(180, 255, 180 + RECT_WIDTH, 255 + RECT_WIDTH, getPaintByColorIndex(1,3));
            canvas.drawRect(180, 305, 180 + RECT_WIDTH, 305 + RECT_WIDTH, getPaintByColorIndex(2,3));

            canvas.drawRect(230, 205, 230 + RECT_WIDTH, 205 + RECT_WIDTH, getPaintByColorIndex(0,4));
            canvas.drawRect(230, 255, 230 + RECT_WIDTH, 255 + RECT_WIDTH, getPaintByColorIndex(1,4));
            canvas.drawRect(230, 305, 230 + RECT_WIDTH, 305 + RECT_WIDTH, getPaintByColorIndex(2,4));

            canvas.drawRect(280, 205, 280 + RECT_WIDTH, 205 + RECT_WIDTH, getPaintByColorIndex(0,5));
            canvas.drawRect(280, 255, 280 + RECT_WIDTH, 255 + RECT_WIDTH, getPaintByColorIndex(1,5));
            canvas.drawRect(280, 305, 280 + RECT_WIDTH, 305 + RECT_WIDTH, getPaintByColorIndex(2,5));
            //Красный блок
            canvas.drawRect(330, 205, 330 + RECT_WIDTH, 205 + RECT_WIDTH, getPaintByColorIndex(0,6));
            canvas.drawRect(330, 255, 330 + RECT_WIDTH, 255 + RECT_WIDTH, getPaintByColorIndex(1,6));
            canvas.drawRect(330, 305, 330 + RECT_WIDTH, 305 + RECT_WIDTH, getPaintByColorIndex(2,6));
            canvas.drawRect(380, 205, 380 + RECT_WIDTH, 205 + RECT_WIDTH, getPaintByColorIndex(0,7));
            canvas.drawRect(380, 255, 380 + RECT_WIDTH, 255 + RECT_WIDTH, getPaintByColorIndex(1,7));
            canvas.drawRect(380, 305, 380 + RECT_WIDTH, 305 + RECT_WIDTH, getPaintByColorIndex(2,7));
            canvas.drawRect(430, 205, 430 + RECT_WIDTH, 205 + RECT_WIDTH, getPaintByColorIndex(0,8));
            canvas.drawRect(430, 255, 430 + RECT_WIDTH, 255 + RECT_WIDTH, getPaintByColorIndex(1,8));
            canvas.drawRect(430, 305, 430 + RECT_WIDTH, 305 + RECT_WIDTH, getPaintByColorIndex(2,8));
            //Синий блок
            canvas.drawRect(480, 205, 480 + RECT_WIDTH, 205 + RECT_WIDTH, getPaintByColorIndex(0,9));
            canvas.drawRect(480, 255, 480 + RECT_WIDTH, 255 + RECT_WIDTH, getPaintByColorIndex(1,9));
            canvas.drawRect(480, 305, 480 + RECT_WIDTH, 305 + RECT_WIDTH, getPaintByColorIndex(2,9));
            canvas.drawRect(530, 205, 530 + RECT_WIDTH, 205 + RECT_WIDTH, getPaintByColorIndex(0,10));
            canvas.drawRect(530, 255, 530 + RECT_WIDTH, 255 + RECT_WIDTH, getPaintByColorIndex(1,10));
            canvas.drawRect(530, 305, 530 + RECT_WIDTH, 305 + RECT_WIDTH, getPaintByColorIndex(2,10));
            canvas.drawRect(580, 205, 580 + RECT_WIDTH, 205 + RECT_WIDTH, getPaintByColorIndex(0,11));
            canvas.drawRect(580, 255, 580 + RECT_WIDTH, 255 + RECT_WIDTH, getPaintByColorIndex(1,11));
            canvas.drawRect(580, 305, 580 + RECT_WIDTH, 305 + RECT_WIDTH, getPaintByColorIndex(2,11));

            //Белый блок
            canvas.drawRect(180, 155, 180 + RECT_WIDTH, 155 + RECT_WIDTH, getPaintByColorIndex(2,12));
            canvas.drawRect(230, 155, 230 + RECT_WIDTH, 155 + RECT_WIDTH, getPaintByColorIndex(2,13));
            canvas.drawRect(280, 155, 280 + RECT_WIDTH, 155 + RECT_WIDTH, getPaintByColorIndex(2,14));
            canvas.drawRect(180, 105, 180 + RECT_WIDTH, 105 + RECT_WIDTH, getPaintByColorIndex(1,12));
            canvas.drawRect(230, 105, 230 + RECT_WIDTH, 105 + RECT_WIDTH, getPaintByColorIndex(1,13));
            canvas.drawRect(280, 105, 280 + RECT_WIDTH, 105 + RECT_WIDTH, getPaintByColorIndex(1,14));
            canvas.drawRect(180, 55, 180 + RECT_WIDTH, 55 + RECT_WIDTH, getPaintByColorIndex(0,12));
            canvas.drawRect(230, 55, 230 + RECT_WIDTH, 55 + RECT_WIDTH, getPaintByColorIndex(0,13));
            canvas.drawRect(280, 55, 280 + RECT_WIDTH, 55 + RECT_WIDTH, getPaintByColorIndex(0,14));
            //Желтый блок
            canvas.drawRect(180, 355, 180 + RECT_WIDTH, 355 + RECT_WIDTH, getPaintByColorIndex(0,15));
            canvas.drawRect(230, 355, 230 + RECT_WIDTH, 355 + RECT_WIDTH, getPaintByColorIndex(0,16));
            canvas.drawRect(280, 355, 280 + RECT_WIDTH, 355 + RECT_WIDTH, getPaintByColorIndex(0,17));
            canvas.drawRect(180, 405, 180 + RECT_WIDTH, 405 + RECT_WIDTH, getPaintByColorIndex(1,15));
            canvas.drawRect(230, 405, 230 + RECT_WIDTH, 405 + RECT_WIDTH, getPaintByColorIndex(1,16));
            canvas.drawRect(280, 405, 280 + RECT_WIDTH, 405 + RECT_WIDTH, getPaintByColorIndex(1,17));
            canvas.drawRect(180, 455, 180 + RECT_WIDTH, 455 + RECT_WIDTH, getPaintByColorIndex(2,15));
            canvas.drawRect(230, 455, 230 + RECT_WIDTH, 455 + RECT_WIDTH, getPaintByColorIndex(2,16));
            canvas.drawRect(280, 455, 280 + RECT_WIDTH, 455 + RECT_WIDTH, getPaintByColorIndex(2,17));
        }
    }

    private Paint getPaintByColorIndex(int x, int y){
        switch(cubeInChars[x][y]){
            case "o":
                return rectPaintOrange;
            case "g":
                return rectPaintGreen;
            case "r":
                return rectPaintRed;
            case "b":
                return rectPaintBlue;
            case "w":
                return rectPaintWhite;
            case "y":
                return rectPaintYellow;
            default:
                return borderPaint;
        }
    }

    public void ApplyScramble(Turn[] scramble){
        cubeInChars =
                new String[][]{
                        //   L                  F                   R               B                   U            D   x - строка y - столбец
               // y:0    1    2       3    4    5       6    7   8        9   10   11       12   13  14     15   16   17
                        {"o", "o", "o",   "g", "g", "g",   "r", "r", "r",   "b", "b", "b",   "w", "w", "w",   "y", "y", "y"},//x:0
                        {"o", "o", "o",   "g", "g", "g",   "r", "r", "r",   "b", "b", "b",   "w", "w", "w",   "y", "y", "y"},//X:1
                        {"o", "o", "o",   "g", "g", "g",   "r", "r", "r",   "b", "b", "b",   "w", "w", "w",   "y", "y", "y"} //X:2
                };
        for(int i=0; i<scramble.length;i++){
            switch (scramble[i].getName()){
                case "R":{
                    TurnR();
                    break;
                }
                case "R2":{
                   TurnR();
                   TurnR();
                   break;
                }
                case "R`":{
                    TurnR();
                    TurnR();
                    TurnR();
                    break;
                }
                case "L":{
                    TurnL();
                    break;
                }
                case "L2":{
                    TurnL();
                    TurnL();
                    break;
                }
                case "L`":{
                    TurnL();
                    TurnL();
                    TurnL();
                    break;
                }
                case "F":{
                    TurnF();
                    break;
                }
                case "F2":{
                    TurnF();
                    TurnF();
                    break;
                }
                case "F`":{
                    TurnF();
                    TurnF();
                    TurnF();
                    break;
                }
                case "U":{
                    TurnU();
                    break;
                }
                case "U2":{
                    TurnU();
                    TurnU();
                    break;
                }
                case "U`":{
                    TurnU();
                    TurnU();
                    TurnU();
                    break;
                }
                case "D":{
                    TurnD();
                    break;
                }
                case "D`":{
                    TurnD();
                    TurnD();
                    TurnD();
                    break;
                }
                case "D2":{
                    TurnD();
                    TurnD();
                    break;
                }
                case "B":{
                    TurnB();
                    break;
                }
                case "B2":{
                    TurnB();
                    TurnB();
                    break;
                }
                case "B`":{
                    TurnB();
                    TurnB();
                    TurnB();
                    break;
                }
            }
        }
        invalidate();
    }


}
