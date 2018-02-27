package com.michalrubajczyk.myfirebrigade.activities;

import android.app.Activity;
import android.os.AsyncTask;


import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Michal on 27/02/2018.
 */

public class RegisterServerTask extends AsyncTask<String, Void, Void> {

    String ip = "192.168.0.104";
    Socket socket;

    Activity activity;


    DataOutputStream dataOutputStream = null;

    public RegisterServerTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected Void doInBackground(String... voids) {

        try {
            String message = voids[0];
            socket = new Socket(ip, 1200);

            OutputStream outputStream = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);

            dataOutputStream.writeBytes(message);
            dataOutputStream.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
