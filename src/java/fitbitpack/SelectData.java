/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbitpack;

import java.util.logging.Level;
import java.util.logging.Logger;
import Uniplat.MySQLConnector;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import myfitbit.config;

/**
 *
 * @author Yang
 */
public class SelectData {

    public String[][] getdata(String user, String startdate, String enddate, String table) {

        String userNameValue = user;
        String stdate = startdate;
        String edate = enddate;
        String[][] backjson;
        String[][] check1;
        String[][] check2;
        String[] userdata;
        try {
            System.out.println("1");

            config c = new config();
            c.setDBname("uniplat");
            MySQLConnector sql = new MySQLConnector(c.getUrlstr(), c.getUserStr(), c.getPwStr(), c.getDBname());
            sql.connectDB();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.setTime(sdf.parse(startdate));
            System.out.println(sdf.format(cal.getTime()));
            java.util.Calendar cal2 =  java.util.Calendar.getInstance();
            cal2.setTime(sdf.parse(edate));
            cal2.add(java.util.Calendar.DATE, 1);
            System.out.println(sdf.format(cal2.getTime()));
            //check1 = sql.SelectAllData("select * from " + table + " where User_ID =  '" + user + "' and DateTime>='" + startdate + "' and DateTime <='" + enddate + "' and ID_Activity='efficiency' or ID_Activity='efficiency' or ID_Activity='steps' or ID_Activity='lightlyActiveMinutes' or ID_Activity='floors' or ID_Activity='fairlyActiveMinutes' or ID_Activity='caloriesBMR' or ID_Activity='veryActiveMinutes' order by DateTime");
             
            
            for (; !sdf.format(cal.getTime()).equals(sdf.format(cal2.getTime()));) {
                System.out.println(sdf.format(cal.getTime()).equals(sdf.format(cal2.getTime())));
                if (table.equals("Summary")) {
                    check1 = sql.SelectAllData("select * from " + table + " where User_ID =  '" + user + "' and DateTime='" +sdf.format(cal.getTime()) + "' and ( ID_Activity='steps' or ID_Activity='lightlyActiveMinutes' or ID_Activity='floors' or ID_Activity='fairlyActiveMinutes' or ID_Activity='caloriesBMR' or ID_Activity='veryActiveMinutes'  ) order by DateTime");
                   System.out.println("CHECK1"+check1.length);
                    if (check1.length <2) {

                        GetActSum.getdata(user, sdf.format(cal.getTime()));
                    }
                     check2= sql.SelectAllData("select * from " + table + " where User_ID =  '" + user + "' and DateTime='" +sdf.format(cal.getTime()) + "' and ID_Activity='efficiency'  order by DateTime");
                   System.out.println("CHECK2"+check2.length);
                     if (check2.length <2) {

                        GetSleepMoreDays.getdata(user, sdf.format(cal.getTime()), sdf.format(cal.getTime())); 
                    }                    
                }
                if (table.equals("Activity_Heart")) {
                    check1 = sql.SelectAllData("select * from " + table + " where User_ID =  '" + user + "' and DateTime='" + sdf.format(cal.getTime()) + "' order by DateTime");
                     System.out.println("Activity_Heart"+check1.length);
                    if (check1.length <2) {
                        GetHeartRateSum.getdata(user, sdf.format(cal.getTime()), "1d");
                    }                    
                }
//System.out.println("select * from " + table + " where User_ID =  '" + user + "' and DateTime>='" + startdate + "' and DateTime <='" + enddate + "'");
                if (table.equals("Heartrate_Detail")) {
                    check1 = sql.SelectAllData("select * from " + table + " where User_ID =  '" + user + "' and DateTime='" +sdf.format(cal.getTime()) + "' order by DateTime,StartTime");
                    if (check1.length < 2) {

                        GetHeartRateDetail.getdata(user, sdf.format(cal.getTime()), "1min", "00:00", "23:59");
                    }
                }
                if (table.equals("Sleep_Detail")) {
                    check1 = sql.SelectAllData("select * from " + table + " where User_ID =  '" + user + "' and DateTime='" +sdf.format(cal.getTime()) + "' order by DateTime,StartTime");
                    check2 = sql.SelectAllData("select * from " + table + " where User_ID =  '" + user + "' and DateTime='" + sdf.format(cal.getTime()) + "' and ID_Activity='efficiency' order by DateTime,StartTime");
                    
                    if (check1.length<2) {

                       GetSleepMoreDays.getdata(user, stdate, edate); 
                    }
                }
                cal.add(java.util.Calendar.DATE, 1);
            }
            
            System.out.println(table);
            System.out.println(table.length());
            if (table.equals("Summary")) {
                
                backjson = sql.SelectAllData("select * from " + table + " where User_ID =  '" + user + "' and DateTime>='" + startdate + "' and DateTime <='" + enddate + "' and (ID_Activity='efficiency' or ID_Activity='efficiency' or ID_Activity='steps' or ID_Activity='lightlyActiveMinutes' or ID_Activity='floors' or ID_Activity='fairlyActiveMinutes' or ID_Activity='caloriesBMR' or ID_Activity='veryActiveMinutes') order by DateTime");
            } else {
                if (table.equals("Activity_Heart")) {
                    backjson = sql.SelectAllData("select * from " + table + " where User_ID =  '" + user + "' and DateTime>='" + startdate + "' and DateTime <='" + enddate + "'order by DateTime");
                } else {
                    System.out.println("select * from " + table + " where User_ID =  '" + user + "' and DateTime>='" + startdate + "' and DateTime <='" + enddate + "'");
                    backjson = sql.SelectAllData("select * from " + table + " where User_ID =  '" + user + "' and DateTime>='" + startdate + "' and DateTime <='" + enddate + "' order by DateTime,StartTime");
                }
            }
          /*  System.out.println(backjson.length);
            for (int i = 0; i < backjson.length; i++) {
                for (int j = 0; j < backjson[i].length; j++) {
                    System.out.println(backjson[i][j]);
                }
            }*/

        } catch (Exception ex) {
            System.out.println("3");
            Logger.getLogger(GetHeartRateSum.class.getName()).log(Level.SEVERE, null, ex);
            backjson = new String[1][1];
            backjson[0][0] = ex.toString();
        }

        return backjson;
    }
    public String[][] AcData(String user, String startdate, String enddate, String table,String id) {

        String userNameValue = user;
        String stdate = startdate;
        String edate = enddate;
        String[][] backjson;
        String[][] check;
        String[] userdata;
        try {
            System.out.println("1");

            config c = new config();
            c.setDBname("uniplat");
            MySQLConnector sql = new MySQLConnector(c.getUrlstr(), c.getUserStr(), c.getPwStr(), c.getDBname());
            sql.connectDB();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.setTime(sdf.parse(startdate));
            System.out.println(sdf.format(cal.getTime()));
            java.util.Calendar cal2 = java.util.Calendar.getInstance();
            cal2.setTime(sdf.parse(edate));
            cal2.add(java.util.Calendar.DATE, 1);
            System.out.println(sdf.format(cal2.getTime()));
            for (; !sdf.format(cal.getTime()).equals(sdf.format(cal2.getTime()));) {
                System.out.println(sdf.format(cal.getTime()).equals(sdf.format(cal2.getTime())));
                check = sql.SelectAllData("select * from " + table + " where User_ID =  '" + user + "' and DateTime='" + sdf.format(cal.getTime()) + "'  and ID_Activity='"+id+"' order by DateTime,StartTime");
                System.out.println(check.length);
                if(check.length<2){
                GetActDetail.getdata(user, sdf.format(cal.getTime()), "1min", "00:00", "23:59",id);
                }
                cal.add(java.util.Calendar.DATE, 1);
            }
            
            System.out.println("select * from " + table + " where User_ID =  '" + user + "' and DateTime>='" + startdate + "' and DateTime <='" + enddate + "'");
            backjson = sql.SelectAllData("select * from " + table + " where User_ID =  '" + user + "' and DateTime>='" + startdate + "' and DateTime <='" + enddate + "' and ID_Activity='"+id+"' order by DateTime,StartTime");
            
          /*  System.out.println(backjson.length);
            for (int i = 0; i < backjson.length; i++) {
                for (int j = 0; j < backjson[i].length; j++) {
                    System.out.println(backjson[i][j]);
                }
            }*/

        } catch (Exception ex) {
            System.out.println("3");
            Logger.getLogger(GetHeartRateSum.class.getName()).log(Level.SEVERE, null, ex);
            backjson = new String[1][1];
            backjson[0][0] = ex.toString();
        }

        return backjson;
    }

}
