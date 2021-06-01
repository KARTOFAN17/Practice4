package com.mirea.grachev.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView infoTextView = findViewById(R.id.textView);
        TextView averageTextView = findViewById(R.id.textView2);
        Thread mainThread = Thread.currentThread();
        infoTextView.setText("Текущий поток: " + mainThread.getName());
        // Меняем имя и выводим в текстовом поле
        mainThread.setName("MireaThread");
        infoTextView.append("\n Новое имя потока: " + mainThread.getName());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                double answer;
                answer = 0;
                Random random = new Random();
                for (int i = 0; i < 30; i++){
                    answer = answer + random.nextInt(4);
                }

                answer= Math.ceil(answer / 30);
                String answer_2 = Double.toString(answer);
                averageTextView.setText("В среднем в день студент мирэа имеет столько пар:\n"+answer_2);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
    public void onClick(View view) {
        Runnable runnable = new Runnable() {
            public void run() {
                int numberThread = counter++;
                Log.i("ThreadProject", "Поток № " + numberThread);
                long endTime = System.currentTimeMillis()
                        + 20 * 1000;
                while (System.currentTimeMillis() < endTime) {
                    synchronized (this) {
                        try {
                            wait(endTime -
                                    System.currentTimeMillis());
                        } catch (Exception e) {
                        }
                    }
                }
                Log.i("ThreadProject", "Закрытие потока № " + numberThread);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}