/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbitpack;

import Uniplat.MySQLConnector;
import myfitbit.config;

/**
 *
 * @author Chili
 */
public class DeleteData {

    public void deletedata(String user, String startdate, String enddate, String table) {
        config c = new config();
        c.setDBname("uniplat");
        MySQLConnector sql = new MySQLConnector(c.getUrlstr(), c.getUserStr(), c.getPwStr(), c.getDBname());
        sql.connectDB();
        String query="Delete from "+table+" where  (DateTime>='"+startdate+"' and DateTime <='"+enddate+"' ) and User_ID='"+user+"' ;";
        System.out.println(query);
        sql.updateQuery(query );
    }
}
