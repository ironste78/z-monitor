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

package Classes;

/*
 * sniffer: Is used to sniff the traffic, capture packets and redirect it to Zparser class.
 */

//import libraries
import net.tinyos.packet.*;
import net.tinyos.util.*;
import net.tinyos.message.*;
import Classes.Support.*;

public class snifferImpl implements sniffer
{
    //Class Attribute
    private PacketSource reader; // the object that reads packets from the USB Port
    private String sourcePort; // port of sniffer mote
    public int packetLength;
    //-----------------------------------------------//

    /*
     * This constructor method puts the default source port "serial@COM19:telosb" (USB) that the reader will be 
     * read through it then called message sniffer()
     */
    public snifferImpl()
    {
        sourcePort="serial@COM9:telosb";
        sourcePort="serial@/dev/ttyUSB0:telosb";
	    sniffer();
    }
    //-----------------------------------------------//

    /*
     * This constructor method puts the source port (USB) that the reader will be read through it then
     * called message sniffer()
     */
    public snifferImpl(String sourceValue)
    {
        sourcePort=sourceValue;
        sniffer();
    }
    //-----------------------------------------------//

    /*
     * This method builds the reader object
     */
    public void sniffer()
    {
        reader = BuildSource.makePacketSource(sourcePort);

	if (reader == null)
        {
            System.err.println("Invalid packet source (check your MOTECOM environment variable)");
	        System.exit(2);
        }
        try
        {
            reader.open(PrintStreamMessenger.err);
        }catch(Exception e){System.err.println("Error on " + reader.getName() + ": " + e);}
    }
    //-----------------------------------------------//

    /***************************************************************/
    /* Function: closeReader                                       */
    /* Arguments : Nothing                                         */
    /* Return: Nothing                                             */
    /* This method close the reader of sniffer                     */
    /***************************************************************/
    public void closeReader()
    {
        try{reader.close();}catch(Exception e){}
    }
    //-----------------------------------------------//

    /***************************************************************/
    /* Function: openReader                                        */
    /* Arguments : Nothing                                         */
    /* Return: Nothing                                             */
    /* This method open the reader of sniffer                      */
    /***************************************************************/
    public void openReader()
    {

        try{reader.open(PrintStreamMessenger.err);}catch(Exception e){}
    }
    //-----------------------------------------------//

    /****************************************************************/
    /* Function: getPacket                                          */
    /* Arguments : Nothing                                          */
    /* Return: String                                               */
    /* This method capture packets and redirect it to Zparser class */
    /****************************************************************/
    public String getPacket()
    {	 
	    try
	    {
            byte[] receivedPacketStream = reader.readPacket();
	    packetLength = receivedPacketStream.length;
            String receivedPacketStreamInHex=new ConvertImpl().ConvertBytToHex(receivedPacketStream);
            return receivedPacketStreamInHex;
		  }catch(Exception e){ System.out.println("Error while trying to sniff the packet"); return "";}
		  	
		  	 
    }
    //-----------------------------------------------//

    /****************************************************************/
    /* Function: getSourcePort                                      */
    /* Arguments : Nothing                                          */
    /* Return: String                                               */
    /* This method return the source port                           */
    /****************************************************************/
    public String getSourcePort()
    {
	    return sourcePort;
    }
    
}
