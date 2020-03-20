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

        canvas.drawARGB(100, 201, 201, 201);
        //Сетка
        canvas.drawLine(25, 195, 25, 355, borderPaint);//Вертикальная
        canvas.drawLine(35 + RECT_WIDTH, 195, 35 + RECT_WIDTH, 355, borderPaint);//Вертикальная 2
        canvas.drawLine(85 + RECT_WIDTH, 195, 85 + RECT_WIDTH, 355, borderPaint);//Вертикальная 3

        canvas.drawLine(135 + RECT_WIDTH, 195, 135 + RECT_WIDTH, 355, borderPaint);//Вертикальная 4 TODO: Сделать большой для белого
        canvas.drawLine(185 + RECT_WIDTH, 195, 185 + RECT_WIDTH, 355, borderPaint);//Вертикальная 5 TODO: Сделать большой для белого
        canvas.drawLine(235 + RECT_WIDTH, 195, 235 + RECT_WIDTH, 355, borderPaint);//Вертикальная 6 TODO: Сделать большой для белого
        canvas.drawLine(285 + RECT_WIDTH, 195, 285 + RECT_WIDTH, 355, borderPaint);//Вертикальная 7
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
        {
        //Оранжевый блок
        canvas.drawRect(30, 205, 30 + RECT_WIDTH, 205 + RECT_WIDTH, rectPaintOrange);
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
    }
    }
}
