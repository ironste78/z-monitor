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


package Classes.Support;

/*
 * SntpClient: Is used to request NTP synchronization to a NTP server. It works as a separate Thread.
*/

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Fernando
 */

public class SntpClient
{

      //Class Attribute
      public double offset;       //offset respect to the NTP server clock
      public double interval;     //Interval to send synchronization messages
      public String serverName;   //NTP servername
      //-----------------------------------------------//

      //Constructor
      public SntpClient(String servername, double seconds)
      {
           serverName=servername;
           offset=0.0;
           interval=seconds;
      }
      //-----------------------------------------------//

      //----------- Set methods ----------------//
      public void setServer(String servername){
       serverName=servername;
       offset=0.0;
      }

      public void setInterval(double value){
       interval=value;
      }
      //-----------------------------------------------//

      //----------- Get methods ----------------//
      public String getServer()
      {
        return serverName;
      }

      public double getInterval()
      {
        return interval;
      }

      public double getOffset()
      {
        return offset;
      }
      //-----------------------------------------------//

    /*******************************************************************/
    /* Function: askOffset                                             */
    /* Arguments : Nothing                                             */
    /* Return: Nothing                                                 */
    /* This method ask for time synchronization to the server          */
    /* configured, fixing the offset respect to the server             */
    /*******************************************************************/
      public void askOffset()
      {
             try{
                DatagramSocket socket = new DatagramSocket();
                InetAddress address = InetAddress.getByName(serverName);
                byte[] buf = new NtpMessage().toByteArray();
                DatagramPacket packet =	new DatagramPacket(buf, buf.length, address, 123);
                NtpMessage.encodeTimestamp(packet.getData(), 40,(System.currentTimeMillis()/1000.0) + 2208988800.0);
                socket.send(packet);
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                double destinationTimestamp =(System.currentTimeMillis()/1000.0d) + 2208988800.0;
                NtpMessage msg = new NtpMessage(packet.getData());
                offset =
                        ((msg.receiveTimestamp - msg.originateTimestamp) +
                        (msg.transmitTimestamp - destinationTimestamp)) / 2;
                socket.close();
            }catch(Exception e){}
      }
      //-----------------------------------------------//


}



