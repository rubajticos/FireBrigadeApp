package com.michalrubajczyk.myfirebrigade.activities;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Michal on 27/02/2018.
 */

public class RegisterServerTask extends AsyncTask<String, Void, Boolean> {

    String ip = "192.168.0.104";
    Socket socket;

    Activity activity;


    PrintWriter printWriter;
    BufferedReader sBufferedReader;

    public RegisterServerTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected Boolean doInBackground(String... voids) {

        try {
            String message = voids[0];
            socket = new Socket(ip, 1200);
            printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.write(message);
            printWriter.flush();

            InputStream inputStream = socket.getInputStream(); //Odebranie wyniku z serwera
            sBufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            Boolean result = Boolean.parseBoolean(sBufferedReader.readLine());
            Log.d("Wynik z serwera: ", result.toString());
            socket.close();
            printWriter.close();
            if (result) {
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {

    }
}
