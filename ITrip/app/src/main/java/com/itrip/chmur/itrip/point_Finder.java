package com.itrip.chmur.itrip;

import android.os.Environment;
import android.os.StrictMode;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

//import javax.swing.text.html.ListView;

//import sun.net.www.protocol.http.HttpURLConnection;

public class point_Finder {


    public float[] finder_lat(String gpxFile) {


        float[] latitude = new float [100];
        int id = 0;

        // String line = convertStreamToString(in);
        String line = "{\n" +
                "    \"tripId\": \"e5286823-3762-4be0-8b2e-03d4927f2f5c\",\n" +
                "    \"name\": \"string\",\n" +
                "    \"description\": \"string\",\n" +
                "    \"start\": 1528138392000,\n" +
                "    \"end\": 1528138392000,\n" +
                "    \"waypoints\": [\n" +
                "        {\n" +
                "            \"tripId\": \"e5286823-3762-4be0-8b2e-03d4927f2f5c\",\n" +
                "            \"waypointId\": 3479,\n" +
                "            \"longitude\": 127.0001,\n" +
                "            \"latitude\": -28.2,\n" +
                "            \"longitude\": 127.1,\n" +
                "            \"latitude\": -28.3,\n" +
                "            \"longitude\": 127.2,\n" +
                "            \"latitude\": -28.4,\n" +
                "            \"longitude\": 127.3,\n" +
                "            \"latitude\": -28.5,\n" +
                "            \"longitude\": 127.4,\n" +
                "            \"latitude\": -28.6,\n" +
                "            \"longitude\": 127.5,\n" +
                "            \"latitude\": -28.7,\n" +
                "            \"longitude\": 127.6,\n" +
                "            \"latitude\": -28.8,\n" +
                "            \"longitude\": 127.7,\n" +
                "            \"latitude\": -28.9,\n" +
                "            \"date\": 1528268096000,\n" +
                "            \"photos\": [\n" +
                "                {\n" +
                "                    \"waypointId\": 3479,\n" +
                "                    \"date\": 1528268791000,\n" +
                "                    \"url\": \"https://tripsbackendstorage.blob.core.windows.net/photos/photoe5286823-3762-4be0-8b2e-03d4927f2f5c_1.jpg\",\n" +
                "                    \"thumbnailUrl\": null\n" +
                "                    \"latitude\": 66,\n" +
                "                    \"longitude\": 66,\n" +
                "                }\n" +
                "            ],\n" +
                "            \"videos\": []\n" +
                "        },\n" +
                "        {\n" +
                "            \"tripId\": \"e5286823-3762-4be0-8b2e-03d4927f2f5c\",\n" +
                "            \"waypointId\": 3640,\n" +
                "            \"longitude\": 126.865,\n" +
                "            \"latitude\": -28.1099,\n" +
                "            \"date\": 1528813666000,\n" +
                "            \"photos\": [],\n" +
                "            \"videos\": []\n" +
                "        }\n" +
                "    ],\n" +
                "    \"poster\": null,\n" +
                "    \"presentation\": null\n" +
                "}";




        char[] chars = line.toCharArray();
        String lat = "";

        for(int a = 0; a < line.length(); a++){

            if(chars[a] == '"'){
                if(chars[a+1] == 'l') {
                    if(chars[a+2] == 'a') {
                        if(chars[a+3] == 't') {
                            if(chars[a+4] == 'i') {
                                if(chars[a+5] == 't') {
                                    if (chars[a + 6] == 'u') {
                                        if (chars[a + 7] == 'd') {
                                            if (chars[a + 8] == 'e') {
                                                if (chars[a + 9] == '"') {
                                                    if (chars[a + 10] == ':') {
                                                        for (int b = a + 11; b < line.length(); b++) {
                                                            if (chars[b] == ',') {
                                                                latitude[id] = Float.parseFloat(lat);
                                                                id = id+1;
                                                                //list_Value.add(lat);
                                                                lat = "";
                                                                break;
                                                            } else {
                                                                lat = lat + chars[b];
                                                            }

                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return latitude;
    }


    public float[] finder_lon(String gpxFile) {


        float[] longtude = new float [100];
        int id = 0;

        ArrayList list_Value = new ArrayList();



        //String line = convertStreamToString(in);
        String line = "{\n" +
                "    \"tripId\": \"e5286823-3762-4be0-8b2e-03d4927f2f5c\",\n" +
                "    \"name\": \"string\",\n" +
                "    \"description\": \"string\",\n" +
                "    \"start\": 1528138392000,\n" +
                "    \"end\": 1528138392000,\n" +
                "    \"waypoints\": [\n" +
                "        {\n" +
                "            \"tripId\": \"e5286823-3762-4be0-8b2e-03d4927f2f5c\",\n" +
                "            \"waypointId\": 3479,\n" +
                "            \"longitude\": 127.0001,\n" +
                "            \"latitude\": -28.2,\n" +
                "            \"longitude\": 127.1,\n" +
                "            \"latitude\": -28.3,\n" +
                "            \"longitude\": 127.2,\n" +
                "            \"latitude\": -28.4,\n" +
                "            \"longitude\": 127.3,\n" +
                "            \"latitude\": -28.5,\n" +
                "            \"longitude\": 127.4,\n" +
                "            \"latitude\": -28.6,\n" +
                "            \"longitude\": 127.5,\n" +
                "            \"latitude\": -28.7,\n" +
                "            \"longitude\": 127.6,\n" +
                "            \"latitude\": -28.8,\n" +
                "            \"longitude\": 127.7,\n" +
                "            \"latitude\": -28.9,\n" +
                "            \"date\": 1528268096000,\n" +
                "            \"photos\": [\n" +
                "                {\n" +
                "                    \"waypointId\": 3479,\n" +
                "                    \"date\": 1528268791000,\n" +
                "                    \"url\": \"https://tripsbackendstorage.blob.core.windows.net/photos/photoe5286823-3762-4be0-8b2e-03d4927f2f5c_1.jpg\",\n" +
                "                    \"thumbnailUrl\": null\n" +
                "                    \"latitude\": 66,\n" +
                "                    \"longitude\": 66,\n" +
                "                }\n" +
                "            ],\n" +
                "            \"videos\": []\n" +
                "        },\n" +
                "        {\n" +
                "            \"tripId\": \"e5286823-3762-4be0-8b2e-03d4927f2f5c\",\n" +
                "            \"waypointId\": 3640,\n" +
                "            \"longitude\": 126.865,\n" +
                "            \"latitude\": -28.1099,\n" +
                "            \"date\": 1528813666000,\n" +
                "            \"photos\": [],\n" +
                "            \"videos\": []\n" +
                "        }\n" +
                "    ],\n" +
                "    \"poster\": null,\n" +
                "    \"presentation\": null\n" +
                "}";



        char[] chars = line.toCharArray();
        String lon = "";

        for(int a = 0; a < line.length(); a++){

            if(chars[a] == '"'){
                if(chars[a+1] == 'l') {
                    if(chars[a+2] == 'o') {
                        if(chars[a+3] == 'n') {
                            if(chars[a+4] == 'g') {
                                if(chars[a+5] == 'i') {
                                    if (chars[a + 6] == 't') {
                                        if (chars[a + 7] == 'u') {
                                            if (chars[a + 8] == 'd') {
                                                if (chars[a + 9] == 'e') {
                                                    if (chars[a + 10] == '"') {
                                                        if (chars[a + 11] == ':') {
                                                            for (int b = a + 12; b < line.length(); b++) {
                                                                if (chars[b] == ',') {
                                                                    longtude[id] = Float.parseFloat(lon);
                                                                    id = id + 1;
                                                                    //list_Value.add(lon);
                                                                    lon = "";
                                                                    break;
                                                                } else {
                                                                    lon = lon + chars[b];
                                                                }

                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return longtude;
    }

    public static String[] Load(File file)
    {
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream(file);
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        String test;
        int anzahl=0;
        try
        {
            while ((test=br.readLine()) != null)
            {
                anzahl++;
            }
        }
        catch (IOException e) {e.printStackTrace();}

        try
        {
            fis.getChannel().position(0);
        }
        catch (IOException e) {e.printStackTrace();}

        String[] array = new String[anzahl];

        String line;
        int i = 0;
        try
        {
            while((line=br.readLine())!=null)
            {
                array[i] = line;
                i++;
            }
        }
        catch (IOException e) {e.printStackTrace();}
        return array;
    }

    public  void Save(File file, String[] data)
    {
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(file);
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        try
        {
            try
            {
                for (int i = 0; i<data.length; i++)
                {
                    fos.write(data[i].getBytes());
                    if (i < data.length-1)
                    {
                        fos.write("\n".getBytes());
                    }
                }
            }
            catch (IOException e) {e.printStackTrace();}
        }
        finally
        {
            try
            {
                fos.close();
            }
            catch (IOException e) {e.printStackTrace();}
        }
    }
    public String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
