package com.company.koneksi;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler {

    public String sendPostRequest(String requestURL, HashMap<String, String> postDataParams) {
        //membuat url

        URL url;

        //objek StringBuilder untuk menyimpan pesan diambil dari server
        StringBuilder sb = new StringBuilder();

        try {
            //inisialisasi url
            url = new URL(requestURL);

            //membuat koneksi HttpUrlConnection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //konfigurasi koneksi

            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //membuat saluran stream
            OutputStream os = conn.getOutputStream();

            //menulis parameter untuk permintaan
            //kita menggunakan metode getPostDataString yang didefinisikan di bawah ini

            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8")
            );

            writer.write(getPostDataString(postDataParams));
            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();
            if (responseCode==HttpURLConnection.HTTP_OK){
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                sb= new StringBuilder();
                String response;
                while ((response=br.readLine())!=null){
                    sb.append(response);
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private String getPostDataString(HashMap<String, String> postDataParams) {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry :
                postDataParams.entrySet()) {
            if (first) {
                first = false;
            } else {
                result.append("&");
            }


            try {
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        return result.toString();
    }




    public String sendGetRequest(String requestURL){
        StringBuilder sb =new StringBuilder();
        try {
            URL url = new URL(requestURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String s;
            while((s=bufferedReader.readLine())!=null){
                sb.append(s+"\n");
            }
        }catch(Exception e){
        }
        return sb.toString();
    }

    public String sendGetRequestParam(String requestURL, String id){
        StringBuilder sb =new StringBuilder();
        try {
            URL url = new URL(requestURL+id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String s;
            while((s=bufferedReader.readLine())!=null){
                sb.append(s+"\n");
            }
        }catch(Exception e){
        }
        return sb.toString();
    }

}
