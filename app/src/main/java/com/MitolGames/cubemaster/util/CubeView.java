package com.MitolGames.cubemaster.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
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

    private String[][] cubeInChars = {
            //L[0][0]..[2][2]  F[0][3]..[                   R               B                   U               D
            {"b", "o", "o",    "g", "g", "g",    "r", "r", "r",    "b", "b", "b",    "w", "w", "w",   "y", "y", "y"},
            {"o", "o", "o",    "g", "g", "g",    "r", "r", "r",    "b", "b", "b",    "w", "w", "w",   "y", "y", "y"},
            {"o", "o", "o",    "g", "g", "g",    "r", "r", "r",    "b", "b", "b",    "w", "w", "w",   "y", "y", "y"}
    };


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
    }
    @Override
    protected void onDraw(Canvas canvas) {

        Log.e("asd", cubeInChars[0][1]);

        canvas.drawARGB(100, 201, 201, 201);
        //Сетка
        {
            canvas.drawLine(25, 195, 25, 355, borderPaint);//Вертикальная
            canvas.drawLine(35 + RECT_WIDTH, 195, 35 + RECT_WIDTH, 355, borderPaint);//Вертикальная 2
            canvas.drawLine(85 + RECT_WIDTH, 195, 85 + RECT_WIDTH, 355, borderPaint);//Вертикальная 3

            canvas.drawLine(135 + RECT_WIDTH, 55, 135 + RECT_WIDTH, 495, borderPaint);//Вертикальная 4 TODO: Сделать большой для белого
            canvas.drawLine(185 + RECT_WIDTH, 55, 185 + RECT_WIDTH, 495, borderPaint);//Вертикальная 5 TODO: Сделать большой для белого
            canvas.drawLine(235 + RECT_WIDTH, 55, 235 + RECT_WIDTH, 495, borderPaint);//Вертикальная 6 TODO: Сделать большой для белого
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
            canvas.drawRect(30, 255, 30 + RECT_WIDTH, 255 + RECT_WIDTH, rectPaintOrange);
            canvas.drawRect(30, 305, 30 + RECT_WIDTH, 305 + RECT_WIDTH, rectPaintOrange);

            canvas.drawRect(80, 205, 80 + RECT_WIDTH, 205 + RECT_WIDTH, rectPaintOrange);
            canvas.drawRect(80, 255, 80 + RECT_WIDTH, 255 + RECT_WIDTH, rectPaintOrange);
            canvas.drawRect(80, 305, 80 + RECT_WIDTH, 305 + RECT_WIDTH, rectPaintOrange);

            canvas.drawRect(130, 205, 130 + RECT_WIDTH, 205 + RECT_WIDTH, rectPaintOrange);
            canvas.drawRect(130, 255, 130 + RECT_WIDTH, 255 + RECT_WIDTH, rectPaintOrange);
            canvas.drawRect(130, 305, 130 + RECT_WIDTH, 305 + RECT_WIDTH, rectPaintOrange);
            //Зеленый блок
            canvas.drawRect(180, 205, 180 + RECT_WIDTH, 205 + RECT_WIDTH, rectPaintGreen);
            canvas.drawRect(180, 255, 180 + RECT_WIDTH, 255 + RECT_WIDTH, rectPaintGreen);
            canvas.drawRect(180, 305, 180 + RECT_WIDTH, 305 + RECT_WIDTH, rectPaintGreen);

            canvas.drawRect(230, 205, 230 + RECT_WIDTH, 205 + RECT_WIDTH, rectPaintGreen);
            canvas.drawRect(230, 255, 230 + RECT_WIDTH, 255 + RECT_WIDTH, rectPaintGreen);
            canvas.drawRect(230, 305, 230 + RECT_WIDTH, 305 + RECT_WIDTH, rectPaintGreen);

            canvas.drawRect(280, 205, 280 + RECT_WIDTH, 205 + RECT_WIDTH, rectPaintGreen);
            canvas.drawRect(280, 255, 280 + RECT_WIDTH, 255 + RECT_WIDTH, rectPaintGreen);
            canvas.drawRect(280, 305, 280 + RECT_WIDTH, 305 + RECT_WIDTH, rectPaintGreen);
            //Красный блок
            canvas.drawRect(330, 205, 330 + RECT_WIDTH, 205 + RECT_WIDTH, rectPaintRed);
            canvas.drawRect(330, 255, 330 + RECT_WIDTH, 255 + RECT_WIDTH, rectPaintRed);
            canvas.drawRect(330, 305, 330 + RECT_WIDTH, 305 + RECT_WIDTH, rectPaintRed);

            canvas.drawRect(380, 205, 380 + RECT_WIDTH, 205 + RECT_WIDTH, rectPaintRed);
            canvas.drawRect(380, 255, 380 + RECT_WIDTH, 255 + RECT_WIDTH, rectPaintRed);
            canvas.drawRect(380, 305, 380 + RECT_WIDTH, 305 + RECT_WIDTH, rectPaintRed);

            canvas.drawRect(430, 205, 430 + RECT_WIDTH, 205 + RECT_WIDTH, rectPaintRed);
            canvas.drawRect(430, 255, 430 + RECT_WIDTH, 255 + RECT_WIDTH, rectPaintRed);
            canvas.drawRect(430, 305, 430 + RECT_WIDTH, 305 + RECT_WIDTH, rectPaintRed);
            //Синий блок
            canvas.drawRect(480, 205, 480 + RECT_WIDTH, 205 + RECT_WIDTH, rectPaintBlue);
            canvas.drawRect(480, 255, 480 + RECT_WIDTH, 255 + RECT_WIDTH, rectPaintBlue);
            canvas.drawRect(480, 305, 480 + RECT_WIDTH, 305 + RECT_WIDTH, rectPaintBlue);

            canvas.drawRect(530, 205, 530 + RECT_WIDTH, 205 + RECT_WIDTH, rectPaintBlue);
            canvas.drawRect(530, 255, 530 + RECT_WIDTH, 255 + RECT_WIDTH, rectPaintBlue);
            canvas.drawRect(530, 305, 530 + RECT_WIDTH, 305 + RECT_WIDTH, rectPaintBlue);

            canvas.drawRect(580, 205, 580 + RECT_WIDTH, 205 + RECT_WIDTH, rectPaintBlue);
            canvas.drawRect(580, 255, 580 + RECT_WIDTH, 255 + RECT_WIDTH, rectPaintBlue);
            canvas.drawRect(580, 305, 580 + RECT_WIDTH, 305 + RECT_WIDTH, rectPaintBlue);

            //Белый блок
            canvas.drawRect(180, 155, 180 + RECT_WIDTH, 155 + RECT_WIDTH, rectPaintWhite);
            canvas.drawRect(230, 155, 230 + RECT_WIDTH, 155 + RECT_WIDTH, rectPaintWhite);
            canvas.drawRect(280, 155, 280 + RECT_WIDTH, 155 + RECT_WIDTH, rectPaintWhite);

            canvas.drawRect(180, 105, 180 + RECT_WIDTH, 105 + RECT_WIDTH, rectPaintWhite);
            canvas.drawRect(230, 105, 230 + RECT_WIDTH, 105 + RECT_WIDTH, rectPaintWhite);
            canvas.drawRect(280, 105, 280 + RECT_WIDTH, 105 + RECT_WIDTH, rectPaintWhite);

            canvas.drawRect(180, 55, 180 + RECT_WIDTH, 55 + RECT_WIDTH, rectPaintWhite);
            canvas.drawRect(230, 55, 230 + RECT_WIDTH, 55 + RECT_WIDTH, rectPaintWhite);
            canvas.drawRect(280, 55, 280 + RECT_WIDTH, 55 + RECT_WIDTH, rectPaintWhite);
            //Желтый блок
            canvas.drawRect(180, 355, 180 + RECT_WIDTH, 355 + RECT_WIDTH, rectPaintYellow);
            canvas.drawRect(230, 355, 230 + RECT_WIDTH, 355 + RECT_WIDTH, rectPaintYellow);
            canvas.drawRect(280, 355, 280 + RECT_WIDTH, 355 + RECT_WIDTH, rectPaintYellow);

            canvas.drawRect(180, 405, 180 + RECT_WIDTH, 405 + RECT_WIDTH, rectPaintYellow);
            canvas.drawRect(230, 405, 230 + RECT_WIDTH, 405 + RECT_WIDTH, rectPaintYellow);
            canvas.drawRect(280, 405, 280 + RECT_WIDTH, 405 + RECT_WIDTH, rectPaintYellow);

            canvas.drawRect(180, 455, 180 + RECT_WIDTH, 455 + RECT_WIDTH, rectPaintYellow);
            canvas.drawRect(230, 455, 230 + RECT_WIDTH, 455 + RECT_WIDTH, rectPaintYellow);
            canvas.drawRect(280, 455, 280 + RECT_WIDTH, 455 + RECT_WIDTH, rectPaintYellow);
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

}
