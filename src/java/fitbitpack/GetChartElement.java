/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbitpack;

import Uniplat.MySQLConnector;
import java.util.logging.Level;
import java.util.logging.Logger;
import myfitbit.config;

/**
 *
 * @author Yang
 */
public class GetChartElement {

    public String[][] getdata(String user, String date, String table) {

        String[] timedata;
        String[] valuedata;
        String[][] backjson;
        try {

            config c = new config();
            c.setDBname("uniplat");
            MySQLConnector sql = new MySQLConnector(c.getUrlstr(), c.getUserStr(), c.getPwStr(), c.getDBname());
            sql.connectDB();
            // String query = "SELECT * FROM Heartrate_Detail  Where DateTime = ' " + date + " ' ";
            String query = "select * from " + table + " Where DateTime='" + date + "'";
            if ("Heartrate_Detail".equals(table) || "Sleep_Detial".equals(table)) {
                timedata = sql.getdata(query, "StartTime");
                valuedata = sql.getdata(query, "Value");
            } else if ("Activity_Heart".equals(table)) {

                timedata = sql.getdata(query, "HeartrateName");
                valuedata = sql.getdata(query, "CaloriesOut");

            }else if("Summary".equals(table)){
                 timedata = sql.getdata(query, "ID_Activity");
                valuedata = sql.getdata(query, "Value");               
            }
            else {
                timedata = new String[1];
                valuedata = new String[1];
            }
            backjson = new String[2][valuedata.length];
            System.out.println(date);
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < valuedata.length; j++) {
                    if (i == 0) {
                        backjson[i][j] = timedata[j];
                    }
                    if (i == 1) {
                        backjson[i][j] = valuedata[j];
                    }

                }
            }
            System.out.println(backjson[0].length);
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < valuedata.length; j++) {
                    System.out.println(backjson[i][j]);
                }
            }

        } catch (Exception ex) {
            System.out.println("3");
            Logger.getLogger(GetHeartRateSum.class.getName()).log(Level.SEVERE, null, ex);
            backjson = new String[1][1];
            backjson[0][0] = ex.toString();
        }

        return backjson;
    }
}
