package com.itrip.chmur.itrip;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class create_save_gpx {

    public void creator(ArrayList points_Lat,ArrayList points_Lon){

        String name_of_Trip = "my first trip";


        String header = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?><gpx xmlns=\"http://www.topografix.com/GPX/1/1\" creator=\"MapSource 6.15.5\" version=\"1.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"  xsi:schemaLocation=\"http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd\"><trk>\n";
        String name = "<name>" + name_of_Trip + "</name><trkseg>\n";

        String segments = "";
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(Calendar.getInstance().getTime());
        for (int i = 0 ; i< points_Lat.size();i++) {
            segments += "<trkpt lat=\"" + points_Lat.get(i) + "\" lon=\"" + points_Lon.get(i) + "\"><time>" + timeStamp + "</time></trkpt>\n";
        }

        String footer = "</trkseg></trk></gpx>";

        try {
            FileWriter writer = new FileWriter("smt.gpx");
            writer.append(header);
            writer.append(name);
            writer.append(segments);
            writer.append(footer);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            //Log.e(TAG, "Error Writting Path",e);
        }

    }


}
