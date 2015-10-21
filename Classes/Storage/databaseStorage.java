/* Z-Monitor: A Monitoring Tool for IEEE 802.15.4 Wireless Personal Area Networks

 * Website: http://www.z-monitor.org
 * Contact: dev@z-monitor.org
 *
 * Copyright (c) 2014
 * Owners: Al-Imam University/CISTER Research Unit/Prince Sultan University
 * All rights reserved.
 *
 * License Type: GNU GPL
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package Classes.Storage;

/*
 * databaseStorage: Is used to operate with the Database where packet will be stored.
 */


import Classes.Support.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Fernando
 */
public class databaseStorage{

    //Class Attribute
    public boolean enabled;
    public String servername;
    public String port;
    public String database;
    public String user;
    public String pass;
    public String idsnifer;
    public int task;

    static Connection connection;
    static Statement s;
    static ResultSet rs;
    //-----------------------------------------------//

    //Constructor
    public databaseStorage(String servernam,String po, String db , String u, String p) {
       servername=servernam;
       database=db;
       user=u;
       port=po;
       pass=p;
       enabled=false;
       idsnifer="";
       task=-1;
    }
    //-----------------------------------------------//


    /*******************************************************************/
    /* Function: connect                                               */
    /* Arguments : Nothing                                             */
    /* Return: Nothing                                                 */
    /* This method connects with the database                          */
    /*******************************************************************/
    public void connect()
    {
         try{
            Class.forName("com.mysql.jdbc.Driver");
            String urlconection="jdbc:mysql://"+servername+":"+port+"/"+database;
            connection = DriverManager.getConnection (urlconection,user,pass);
            s = connection.createStatement();

	} catch (Exception e)	{e.printStackTrace();}
    }
        //-----------------------------------------------//


    /*******************************************************************/
    /* Function: disconnect                                            */
    /* Arguments : Nothing                                             */
    /* Return: Nothing                                                 */
    /* This method disconnects with the database                       */
    /*******************************************************************/
    public void disconnect(){
        try{
            connection.close();
            enabled=false;
        }catch (Exception e)
        {e.printStackTrace();}
    }
    //-----------------------------------------------//


    /*******************************************************************/
    /* Function: insertData                                            */
    /* Arguments : String rawpacket: rawpacket to store                */
    /*             int snifferid: sniffer id                           */
    /*             double packetTimestamp: ArrivalTime of the packet   */
    /*             int idtask: id task                                 */
    /* Return: Nothing                                                 */
    /* This method insert in the database a packet with its ArrivalTime*/
    /* and also idsniffer and idtask                                   */
    /*******************************************************************/
    public void insertData(String rawpacket, String snifferid, double packetTimestamp, int idtask)
    {
        
        int hash=(rawpacket.substring(0,14)).hashCode();
        try{
                
                String query="select hashcode from data where (hashcode='"+hash+"' and timestamp<"+(packetTimestamp+1)+" and timestamp>"+(packetTimestamp-1)+" and sniffer_id!='"+snifferid+"')";
                rs = s.executeQuery(query);
                if(rs.next())
                {
                    return;
                }
                else
                {
                   String milis=new String(NtpMessage.timestampToString(packetTimestamp));
                   query="INSERT INTO data (packet_raw,hashcode,time,sniffer_id,microseconds,id_task,timestamp) VALUES('"+rawpacket+"',"+hash+",'"+milis.replace(",", ".")+"','"+snifferid+"',(SELECT MICROSECOND('"+milis.replace(",",".")+"')),"+idtask+","+packetTimestamp+")";
                   Statement p=connection.createStatement();
                   int aux=p.executeUpdate(query);

                   //check duplicated packets
                   query="select data_id,microseconds,hashcode from data where (hashcode='"+hash+"' and timestamp<"+(packetTimestamp+1)+" and timestamp>"+(packetTimestamp-1)+") order by timestamp DESC";
                   rs = s.executeQuery(query);
                   if(rs.next())
                   {
                            ArrayList<Integer> packets;
                            packets = new ArrayList<Integer>();
                            do
                            {
                                packets.add(rs.getInt(1));
                            }while(rs.next());
                            int tam = packets.size();
                            if(tam>1)
                            {
                                query="DELETE FROM data WHERE data_id IN (";
                                
                                for (int z = 1; z < tam; z++)
                                {
                                    //Delete the last one
                                    query+=packets.get(z).toString();
                                    if((z+1)!=tam)
                                         query+=",";
                                }
                                query+=")";
                                aux=p.executeUpdate(query);

                            }
                    p.close();
                 }
            }
        }catch(SQLException e){}

    }
    //-----------------------------------------------//


    /*******************************************************************/
    /* Function: getPackets                                            */
    /* Arguments : int idtask: id task of the packets to archive       */
    /* Return: Nothing                                                 */
    /* This method insert in the database a packet with its ArrivalTime*/
    /* and also idsniffer and idtask                                   */
    /*******************************************************************/
    public ArrayList<String> getPackets(int idtask, double timestamp)
    {
        ArrayList<String> packets;
        
        packets = new ArrayList<String>();

        try{
                double timesafe=timestamp+0.00001;
                String query="select packet_raw,timestamp from data where (id_task='"+idtask+"' and timestamp>"+timesafe+") order by timestamp ASC";
                System.out.println("Query: " + query);
                rs = s.executeQuery(query);
                if(rs.next())
                {
                            do
                            {
                                packets.add(rs.getString(1)+";"+rs.getString(2));
                            }while(rs.next());
                }
        }catch(SQLException e){}
        return packets;
    }
    //-----------------------------------------------//


    /*******************************************************************/
    /* Function: createTask                                            */
    /* Arguments : String user: username that requests a new task      */
    /*                                                                 */
    /* Return: identifier of the new task created                      */
    /* This method creates a new task into the database associated to  */
    /* the username specified as argument                              */
    /*******************************************************************/
    public int createTask(String user)
    {
        //Find the id for user
        int id;
        int n=-1;
        try{
            rs = s.executeQuery("select idusers from users where name='"+user+"' ");
            if(rs.next())
            {
                id=rs.getInt(1);
            }
            else return -1;
            //Create  a new task
            double now=((System.currentTimeMillis()/1000.0) + 2208988800.0);
            String q="insert into task(id_users,init_timestamp) values(\"" +id+"\",\""+(NtpMessage.timestampToString(now)).replace(",", ".")+"\"); ";
            n=s.executeUpdate(q);
            rs = s.executeQuery("select idtask from task where id_users='"+id+"' ORDER by init_timestamp DESC");
            if(rs.next())
            {
                n=rs.getInt(1);
            }
            else return -1;
        }catch(Exception e){}
        //return the id of the new task created
        return n;
    }
    //-----------------------------------------------//

    /*******************************************************************/
    /* Function: isConected                                            */
    /* Arguments : Nothing                                             */
    /*                                                                 */
    /* Return: a bool about if the connection is connected or not      */
    /* This method returns a boolean according to the current state of */
    /* the connection with database                                    */
    /*******************************************************************/
    public boolean isConected()
    {
       try{
            return connection.isValid(1000);
        }
       catch(Exception e){ return false;}
    }
    //-----------------------------------------------//

    /*******************************************************************/
    /* Function: existTask                                             */
    /* Arguments : int aux: id of task                                 */
    /*                                                                 */
    /* Return: boolean about if the id task exists or not              */
    /* This method return a boolean according to the existence or not  */
    /* of a given id task                                              */
    /*******************************************************************/
    public boolean existTask(int aux)
    {

        try{
                //Check if the task with id aux is in DB
                rs = s.executeQuery("select idtask from task where idtask='"+String.valueOf(aux)+"' ");
                if(rs.next())
                {
                     return true;
                }
                return false;
       }
       catch(Exception e){ return false;}
    }
    //-----------------------------------------------//

    /*******************************************************************/
    /* Function: userExist                                             */
    /* Arguments : String user: username                               */
    /*             String pass: password                               */
    /* Return: boolean about if the user exists or not, also checking  */
    /* the password                                                    */
    /* This method return a boolean according to the existence or not  */
    /* of a given user and password                                    */
    /*******************************************************************/
    public boolean userExist(String user, String pass)
    {

        try{
                //Check if that task exist in the DB
                rs = s.executeQuery("select idusers from users where (name='"+user+"' AND password='"+pass+"')");
                if(rs.next())
                {
                     return true;
                }
                return false;
       }
       catch(Exception e){ return false;}
    }
    //-----------------------------------------------//

    /*********************************************************************/
    /* Function: ownerTask                                               */
    /* Arguments : Integer: task id                                      */
    /*             String: username                                      */
    /*             String: password                                      */
    /*                                                                   */
    /* Return: True, if the task with id is from user with username/pass */
    /* given, else  it returns false.                                    */
    /*********************************************************************/
    public boolean ownerTask(Integer idtask, String user, String pass)
    {

         //Find the id for user
        int id;

        try{
            rs = s.executeQuery("select idusers from users where name='"+user+"' and password='"+pass+"' ");
            if(rs.next())
            {
                id=rs.getInt(1);
            }
            else return false;
            //Check that the given task id, is from user with identification id
            String q="select idtask from task where idtask="+idtask+" and id_users="+id;
            rs = s.executeQuery(q);
            if(rs.next())
            {
                return true;
            }
            else return false;

        }catch(Exception e){return false;}
        
    }
    //-----------------------------------------------//


    /********************************************************************/
    /* Function: isEnabled                                              */
    /* Arguments : Nothing                                              */
    /*                                                                  */
    /* Return: True if all parameters to proceed to connection are      */
    /* correctly fixed, false if any of them is not correct             */
    /* This method is not related to the isconected() method, this one  */
    /* only gets information about if the parameters for connection are */
    /* defined and if the connection is possible, not any information   */
    /* about if the connection has been established or not yet          */
    /********************************************************************/
    public boolean isEnabled()
    {
       return enabled;
    }
    //-----------------------------------------------//

    //--------------Set methods -----------------------/
    public void setIdSnifer(String id)
    {
        idsnifer=id;
        if(idsnifer!="")
            enabled=true;
    }

    public void setTask(String id)
    {
        task=Integer.parseInt(id);
         if(idsnifer!="" && task!=-1)
            enabled=true;
    }
    //-----------------------------------------------//

    //--------------Get methods -----------------------/
    public String getIdSnifer()
    {
        return idsnifer;
    }

    public int getTask()
    {
        return task;
    }
    //-----------------------------------------------//

}
