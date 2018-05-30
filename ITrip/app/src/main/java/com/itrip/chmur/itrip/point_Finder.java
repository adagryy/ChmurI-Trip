package com.itrip.chmur.itrip;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class point_Finder {

    final static String path = Environment.getExternalStorageDirectory().getAbsolutePath()  ;

    public ArrayList finder_lat(String gpxFile) {

        BufferedReader br = null;
        String line = "";
        ArrayList list_Value = new ArrayList();
        File file = new File(gpxFile);

        try {

            FileInputStream fileInputStream = new FileInputStream (new File(path + gpxFile));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            br = new BufferedReader(inputStreamReader);

            while ((line = br.readLine()) != null) {

                char[] chars = line.toCharArray();
                String lat = "";

                for(int a = 0; a < line.length(); a++){

                    if(chars[a] == 'l'){
                        if(chars[a+1] == 'a') {
                            if(chars[a+2] == 't') {
                                if(chars[a+3] == '=') {
                                    if(chars[a+4] == '"') {
                                        for(int b = a+5;b<line.length();b++ ){
                                            if(chars[b] == '"'){
                                                list_Value.add(lat);
                                                break;
                                            }else{
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

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list_Value;
    }


    public ArrayList finder_lon(String gpxFile) {

        BufferedReader br = null;
        String line = "";
        ArrayList list_Value = new ArrayList();

        try {

            FileInputStream fileInputStream = new FileInputStream (new File(path, gpxFile));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            br = new BufferedReader(inputStreamReader);

            while ((line = br.readLine()) != null) {

                char[] chars = line.toCharArray();
                String lon = "";

                for(int a = 0; a < line.length(); a++){

                    if(chars[a] == 'l'){
                        if(chars[a+1] == 'o') {
                            if(chars[a+2] == 'n') {
                                if(chars[a+3] == '=') {
                                    if(chars[a+4] == '"') {
                                        for(int b = a+5;b<line.length();b++ ){
                                            if(chars[b] == '"'){
                                                list_Value.add(lon);
                                                break;
                                            }else{
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

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list_Value;
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

}
