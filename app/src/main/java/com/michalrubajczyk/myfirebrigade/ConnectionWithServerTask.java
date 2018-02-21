package com.michalrubajczyk.myfirebrigade;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Michal on 21/02/2018.
 */

public class ConnectionWithServerTask extends AsyncTask<String,Void,Void> {

    String ip = "192.168.0.102";
    Socket socket;

    PrintWriter printWriter;

    @Override
    protected Void doInBackground(String... voids) {

        try {
            String message = voids[0];
            socket = new Socket(ip, 1200);
            printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.write(message);
            printWriter.flush();
            printWriter.close();
            socket.close();



        } catch (IOException e){
            e.printStackTrace();
//                Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_LONG).show();
        }


        return null;
    }

}
