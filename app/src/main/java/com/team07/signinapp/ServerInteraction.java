package com.team07.signinapp;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by Ryan on 25/02/2016.
 *
 * Makes use of the HttpURLConnection for server interaction;
 * more info here: http://developer.android.com/reference/java/net/HttpURLConnection.html
 */
public class ServerInteraction {

    private final int timeout = 5000;

    private String urlString = null;
    private HttpURLConnection connection;
    private String ipName = null;

    public ServerInteraction(){
            urlString = "http://"  + ipName + ":8080/RegisterApp-0.0.1-SNAPSHOT/";
    }

    public Boolean postJson(final JSONObject jsonObject, final String ext) {
        class PostJson extends AsyncTask<Object, Void, Boolean> {

            @Override
            protected Boolean doInBackground(Object... params) {
                urlString = urlString.concat((String)params[1]);
                URL url = null;
                try {
                    url = new URL(urlString);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }


                try {
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("Accept", "application/json");
                    connection.setRequestMethod("POST");
                    connection.setUseCaches(false);
                    connection.setDoOutput(true);
                    //connection.setFixedLengthStreamingMode(ext.getBytes().length);
                    connection.setConnectTimeout(timeout);
                    connection.setReadTimeout(timeout);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    OutputStream outputStream = (OutputStream) connection.getOutputStream();
                    //BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                    outputStream.write(jsonObject.toString().getBytes());

                    if (!(connection.getResponseCode() == 200))
                    {
                        return false;
                    }

                    //bufferedWriter.write(jsonObject.toString());

                    return true;

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }


        }

        Boolean value = null;

        PostJson pj = new PostJson();
        try {
            value = pj.execute(jsonObject, ext).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return value;
    }

    public JSONObject postAndGetJson(final JSONObject jsonObject, final String ext) {
        class PostAndGetJson extends AsyncTask<Object, Void, JSONObject> {

            @Override
            protected JSONObject doInBackground(Object... params) {
                urlString = urlString.concat((String)params[1]);
                URL url = null;
                JSONObject jsonReturn = null;
                try {
                    url = new URL(urlString);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }


                try {
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("Accept", "application/json");
                    connection.setRequestMethod("POST");
                    connection.setUseCaches(false);
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    //connection.setFixedLengthStreamingMode(ext.getBytes().length);
                    connection.setConnectTimeout(timeout);
                    connection.setReadTimeout(timeout);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {

                    connection.connect();

                    OutputStream outputStream = (OutputStream) connection.getOutputStream();
                    //BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                    outputStream.write(jsonObject.toString().getBytes());
                    outputStream.flush();
                    outputStream.close();

                    InputStream inputStream = (InputStream) connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuilder stringBuilder = new StringBuilder();

                    String jsonData;
                    while((jsonData = bufferedReader.readLine()) != null)
                    {
                        stringBuilder.append(jsonData);
                    }

                    jsonReturn= new JSONObject(stringBuilder.toString());
                    return jsonReturn;

                    //bufferedWriter.write(jsonObject.toString());

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return jsonReturn;
            }


        }

        JSONObject returnJson = null;

        PostAndGetJson pj = new PostAndGetJson();
        try {
            returnJson = pj.execute(jsonObject, ext).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return returnJson;
    }

    public JSONObject getJson(String ext) {
        class GetJson extends AsyncTask<String, Void, JSONObject> {

            @Override
            protected JSONObject doInBackground(String ... params) {
                urlString = urlString.concat((String)params[1]);
                URL url = null;
                try {
                    url = new URL(urlString);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                try {
                    connection = (HttpURLConnection)url.openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    connection.setRequestMethod("GET");
                } catch (ProtocolException e) {
                    e.printStackTrace();
                }
                connection.setConnectTimeout(timeout);
                connection.setReadTimeout(timeout);
                try {
                    connection.connect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try{
                    BufferedInputStream inputStream = (BufferedInputStream) connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();

                    String building;
                    while((building = bufferedReader.readLine()) != null)
                    {
                        stringBuilder.append(building);
                    }

                    JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                    return jsonObject;
                }
                catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }


        JSONObject jsonObject = new JSONObject();
        GetJson gj = new GetJson();

        try {
            jsonObject = gj.execute(ext).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return jsonObject;

    }


}
