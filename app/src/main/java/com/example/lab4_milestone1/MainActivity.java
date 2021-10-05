package com.example.lab4_milestone1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button startButton;
    private volatile boolean stopThread = false;
    private TextView downloadPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.startButton);
        startButton = findViewById(R.id.startButton);
        downloadPercent = findViewById(R.id.downloadProgress);

    }

    public void mockFileDownloader() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    startButton.setText("Downloading... ");
                }

            });
                for (int downloadProgress = 0; downloadProgress <= 100; downloadProgress = downloadProgress + 10) {
                    Log.d(TAG, "Download Progress: " + downloadProgress + "%");
                    if(stopThread == true){
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int finalDownloadProgress = downloadProgress;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            downloadPercent.setText("Download Progress: " + finalDownloadProgress + "%");


                        }
                    });
                }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    startButton.setText("Start");
                    downloadPercent.setText("");
                }
            });
    }

    public void startDownload(View view) {
        stopThread = false;
        ExampleRunnable runnable = new ExampleRunnable();
        new Thread(runnable).start();
    }

    public void stopDownload(View view){
        stopThread = true;

    }

    class ExampleRunnable implements Runnable {
        @Override
        public void run() {
            mockFileDownloader();
        }
    }
}