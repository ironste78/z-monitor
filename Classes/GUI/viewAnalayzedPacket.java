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


package Classes.GUI;
/*
 * viewAnalayzedPacket: it is contain the function that display the parsed packets in the GUI
 */
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import Classes.Packet.*;
import Classes.Support.*;
import Classes.*;

public class viewAnalayzedPacket
{
   public viewAnalayzedPacket(){}

   /***************************************************************************/
   /* Function: ShowPacketsInTable                                            */
   /* Arguments : IEEE802_15_4: parsed packet                                 */
   /*             these argument is a divided packet which we will display it */
   /* Return: JTable : which contain the packet in JTable format to display   */
   /*                  it in the GUI                                          */
   /* function store the packet in JTable to display it in the table          */
   /***************************************************************************/
   public JTable ShowPacketsInTable (IEEE802_15_4 parsedPacket, Zmonitor ZM){

	int rowWidth=0;
         //packetDetail: table contain the detail of the packet
         JTable packetDetail = new JTable();

         String frameCon = parsedPacket.getFrameCon()[0]+"   "+parsedPacket.getFrameCon()[1]+"   "+parsedPacket.getFrameCon()[2]+"     "+parsedPacket.getFrameCon()[3]+"           "+parsedPacket.getFrameCon()[4]+"                    ";
	      // draw the packets depends on its type
         //---------------beacon frame---------------//
         if(parsedPacket.getPacketType()==0)
         {
               short IEEECase = new parser().IEEE802_15_4Case(parsedPacket.getDestAddMode(), parsedPacket.getSrcAddMode(),parsedPacket.getIntra_PAN());
               String SuperFrameSpecs = parsedPacket.getBO()+"  "+parsedPacket.getSO()+"   "+parsedPacket.getFinCap()+"    "+parsedPacket.getbat()+"    "+parsedPacket.getPAN()+"      "+parsedPacket.getass();
               String [] colsNames = {
                              "<html><center><font face=tahoma size=2>Time (ms)</font>",
                              "<html><center><font face=tahoma size=2>Frame</font></center></html>",
                              "<html><center><font face=tahoma size=2>Length</font></center></html>",
                              "<html><center><font face=tahoma size=2>Frame control field</font><br><hr size='2'></hr><font face='Courier New' size=2>Type Sec Pnd Ack-req Intra-PAN</font></center></html>",
                              "<html><center><font face=tahoma size=2>Sequence<br>number</font></center></html>",
                              "<html><center><font face=tahoma size=2>Source.<br>PAN</font></center></html>",
                              "<html><center><font face=tahoma size=2>Source<br>Address</font></center></html>",
                              "<html><center><font face=tahoma size=2>Super frame specification</font><br><hr size='2'></hr><font face='Courier New' size=2>BO SO F.CAP BLE Coord Assoc</font></center></html>",
                              "<html><center><font face=tahoma size=2>GTS</font></center></html>",
                              "<html><center><font face=tahoma size=2>Pend.<br>Address</font></center></html>",
                              "<html><center><font face=tahoma size=2>Beacon.<br>Payload</font></center></html>",
                              "<html><center><font face=tahoma size=2>LQI</font></center></html>",
                              "<html><center><font face=tahoma size=2>MAC Header Length</font></center></html>",
                              "<html><center><font face=tahoma size=2>Channel</font></center></html>",
                              "<html><center><font face=tahoma size=2>Dispatch<br>ID</font></center></html>",
                              "<html><center><font face=tahoma size=2>RSSI</font></center></html>",
                              "<html><center><font face=tahoma size=2>CRC</font></center></html>"};
                              Object [][] row1 = {{
                                      parsedPacket.getTimeStamp().trim(),
                                      "0",
                                      parsedPacket.getLength().trim(),
                                      frameCon.trim(),
                                      parsedPacket.getSeq().trim(),
                                      parsedPacket.getSrcPAN().trim(),
                                      parsedPacket.getSrcADD().trim(),
                                      SuperFrameSpecs.trim(),
                                      parsedPacket.getGTS().trim(),
                                      parsedPacket.getPending().trim(),
                                      parsedPacket.getPayload().trim(),
                                      parsedPacket.getLQI().trim(),
                                      parsedPacket.getMhrlen().trim(),
                                      parsedPacket.getChannel().trim(),
                                      parsedPacket.getDispID().trim(),
                                      parsedPacket.getRSSI().trim(),
                                      parsedPacket.getCRC().trim()
                              }};
                              packetDetail = new JTable(row1,colsNames);
                              packetDetail.setFont(new Font("Courier New", Font.PLAIN, 10));
                              packetDetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                              packetDetail.setAutoscrolls(true);
                              packetDetail.setColumnSelectionAllowed(false);
                              packetDetail.setRowSelectionAllowed(true);
                              // set the width of the fields
                              for(int i=0;i<17;i++){
                                    TableColumn column = packetDetail.getColumnModel().getColumn(i);
                                    column.setMinWidth(0);
                                    switch(i) {
                                          case 0://Time Stamp Field
                                             column.setPreferredWidth(110);
                                             rowWidth+=110;
                                             break;
                                          case 1://Frame Field
                                             column.setPreferredWidth(40);
                                             rowWidth+=40;
                                             break;
                                          case 2://Length Field
                                             column.setPreferredWidth(45);
                                             rowWidth+=45;
                                             break;
                                          case 3://Frame Control Field
                                             column.setPreferredWidth(210);
                                             rowWidth+=210;
                                             break;
                                          case 4://sequence Field
                                             column.setPreferredWidth(60);
                                             rowWidth+=60;
                                             break;
                                          case 5://Source PAN Field
                                             column.setPreferredWidth(50);
                                             rowWidth+=50;
                                             break;
                                          case 6://Source Address Field
                                             column.setPreferredWidth(50);
                                             rowWidth+=50;
                                             break;
                                          case 7://Superframe Spec
                                             column.setPreferredWidth(180);
                                             rowWidth+=180;
                                             break;
                                          case 8://GTS Field
                                             column.setPreferredWidth(25);
                                             rowWidth+=25;
                                             break;
                                          case 9://Pending Address
                                             column.setPreferredWidth(45);
                                             rowWidth+=45;
                                             break;
                                          case 10://Payload
                                             if(((String) row1[0][10]).equals(""))
						column.setPreferredWidth(0);
                                             else{
						int var = 50+(((String) row1[0][10]).length())*7;
						column.setPreferredWidth(var);
						rowWidth+=var; }
                                             break;
                                          case 11://LQI Field
                                             column.setPreferredWidth(20);
                                             rowWidth+=20;
                                             break;
                                          case 12://Mac Header Field
                                             column.setPreferredWidth(55);
                                             rowWidth+=55;
                                             break;
                                          case 13://Channel Field
                                             column.setPreferredWidth(40);
                                             rowWidth+=40;
                                             break;
                                          case 14://Dispatch ID Field
                                             column.setPreferredWidth(55);
                                             rowWidth+=55;
                                             break;
                                          case 15://RSSI Field
                                             column.setPreferredWidth(33);
                                             rowWidth+=33;
                                             break;
                                          case 16://CRC Field
                                             column.setPreferredWidth(20);
                                             rowWidth+=20;
                                             break;
                                    }
                              }

         }
         //------------End of beacon frame------------//

        //------------Data frame------------//
         else if(parsedPacket.getPacketType()==1)
         {
               short IEEECase = new parser().IEEE802_15_4Case(parsedPacket.getDestAddMode(),parsedPacket.getSrcAddMode(), parsedPacket.getIntra_PAN());
               String [] colsNames = {
                              "<html><center><font face=tahoma size=2>Time (ms)</font>",
                              "<html><center><font face=tahoma size=2>Frame</font></center></html>",
                              "<html><center><font face=tahoma size=2>Length</font></center></html>",
                              "<html><center><font face=tahoma size=2>Frame control field</font><br><hr size='2'></hr><font face='Courier New' size=2>Type Sec Pnd Ack-req Intra-PAN</font></center></html>",
                              "<html><center><font face=tahoma size=2>Sequence<br>number</font></center></html>",
                              "<html><center><font face=tahoma size=2>Dest.<br>PAN</font></center></html>",
                              "<html><center><font face=tahoma size=2>Dest.<br>Address</font></center></html>",
                              "<html><center><font face=tahoma size=2>Src.<br>PAN</font></center></html>",
                              "<html><center><font face=tahoma size=2>Src.<br>Address</font></center></html>",
                              "<html><center><font face=tahoma size=2>Payload</font></center></html>",
                              "<html><center><font face=tahoma size=2>LQI</font></center></html>",
                              "<html><center><font face=tahoma size=2>MAC Header Length</font></center></html>",
                              "<html><center><font face=tahoma size=2>Channel</font></center></html>",
                              "<html><center><font face=tahoma size=2>Dispatch<br>ID</font></center></html>",
                              "<html><center><font face=tahoma size=2>RSSI</font></center></html>",
                              "<html><center><font face=tahoma size=2>CRC</font></center></html>"};
                              Object [][] row1 = {{
                                  parsedPacket.getTimeStamp().trim(),
                                  "0",
                                  parsedPacket.getLength().trim(),
                                  frameCon.trim(),
                                  parsedPacket.getSeq().trim(),
                                  parsedPacket.getDestPAN().trim(),
                                  parsedPacket.getDestADD().trim(),
                                  parsedPacket.getSrcPAN().trim(),
                                  parsedPacket.getSrcADD().trim(),
                                  parsedPacket.getPayload().trim(),
                                  parsedPacket.getLQI().trim(),
                                  parsedPacket.getMhrlen().trim(),
                                  parsedPacket.getChannel().trim(),
                                  parsedPacket.getDispID().trim(),
                                  parsedPacket.getRSSI().trim(),
                                  parsedPacket.getCRC().trim()
                              }};
                              packetDetail = new JTable(row1,colsNames);
                              packetDetail.setFont(new Font("Courier New", Font.PLAIN, 10));
                              packetDetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                              packetDetail.setAutoscrolls(true);
                              packetDetail.setColumnSelectionAllowed(false);
                              packetDetail.setRowSelectionAllowed(true);
                              // set the width of the fields
                              for(int i=0;i<16;i++){
                                    TableColumn column = packetDetail.getColumnModel().getColumn(i);
                                    column.setMinWidth(0);
                                    switch(i) {
                                          case 0://Time Stamp Field
                                             column.setPreferredWidth(110);
                                             rowWidth+=110;
                                             break;
                                          case 1://Frame Field
                                             column.setPreferredWidth(40);
                                             rowWidth+=40;
                                             break;
                                          case 2://Length Field
                                             column.setPreferredWidth(45);
                                             rowWidth+=45;
                                             break;
                                          case 3://Frame Control Field
                                             column.setPreferredWidth(190);
                                             rowWidth+=190;
                                             break;
                                          case 4://sequence Field
                                             column.setPreferredWidth(60);
                                             rowWidth+=60;
                                             break;
                                          case 5://Destination PAN Field
                                             column.setPreferredWidth(55);
                                             rowWidth+=55;
                                             break;
                                          case 6://Destination Address Field
                                             column.setPreferredWidth(55);
                                             rowWidth+=55;
                                             break;
                                          case 7://Source PAN Field
                                             if(((String) row1[0][7]).equals("")) column.setPreferredWidth(0);
                                             else {column.setPreferredWidth(55); rowWidth+=55;}
                                             break;
                                          case 8://Source Address Field
                                             if(((String) row1[0][8]).equals("")) column.setPreferredWidth(0);
                                             else {column.setPreferredWidth(65); rowWidth+=65;}
                                             break;
                                          case 9://Payload Field
                                             int var = 50+(((String) row1[0][9]).length())*7;
						column.setPreferredWidth(var);
						rowWidth+=var;
                                             break;
                                          case 10://LQI Field
                                             column.setPreferredWidth(30);
                                             rowWidth+=30;
                                             break;
                                          case 11://Mac Header Field
                                             column.setPreferredWidth(105);
                                             rowWidth+=105;
                                             break;
                                          case 12://Channel Field
                                             column.setPreferredWidth(50);
                                             rowWidth+=50;
                                             break;
                                          case 13://Dispatch ID Field
                                             column.setPreferredWidth(55);
                                             rowWidth+=55;
                                             break;
                                          case 14://RSSI Field
                                             column.setPreferredWidth(33);
                                             rowWidth+=33;
                                             break;
                                          case 15://CRC Field
                                             column.setPreferredWidth(30);
                                             rowWidth+=30;
                                             break;
                                    }
                              }
         }
               //---------End of Data frame---------//

         //------------Acknowledgement frame------------//
         else if(parsedPacket.getPacketType()==2)
         {
                  String[] colsNames = {
                          "<html><center><font face=tahoma size=2>Time (ms)</font>",
                          "<html><center><font face=tahoma size=2>Frame</font></center></html>",
                          "<html><font face=tahoma size=2>Length</font></center></html>",
                          "<html><center><font face=tahoma size=2>Frame control field</font><br><hr bgcolor='black' size=2><font face='Courier New' size=2>    Type Sec Pnd Ack-req Intra-PAN</font></center></html>",
                          "<html><center><font face=tahoma size=2>Sequence<br>number</font></center></html>",
                          "<html><center><font face=tahoma size=2>LQI</font></center></html>",
                          "<html><center><font face=tahoma size=2>MAC Header Length</font></center></html>",
                          "<html><center><font face=tahoma size=2>Channel</font></center></html>",
                          "<html><center><font face=tahoma size=2>Dispatch<br>ID</font></center></html>",
                          "<html><center><font face=tahoma size=2>RSSI</font></center></html>",
                          "<html><center><font face=tahoma size=2>CRC</font></center></html>"};
                          Object [][] row = {{
                                      parsedPacket.getTimeStamp().trim(),
                                      "0",
                                      parsedPacket.getLength().trim(),
                                      frameCon.trim(),
                                      parsedPacket.getSeq().trim(),
                                      parsedPacket.getLQI().trim(),
                                      parsedPacket.getMhrlen().trim(),
                                      parsedPacket.getChannel().trim(),
                                      parsedPacket.getDispID().trim(),
                                      parsedPacket.getRSSI().trim(),
                                      parsedPacket.getCRC().trim()
                          }};
                          packetDetail = new JTable(row,colsNames);
                          packetDetail.setFont(new Font("Courier New", Font.PLAIN, 10));
                          packetDetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                          packetDetail.setAutoscrolls(true);
                          packetDetail.setColumnSelectionAllowed(false);
                          packetDetail.setRowSelectionAllowed(true);
                          // set the width of the fields
                          for(int i=0;i<11;i++)
                          {
                             TableColumn column = packetDetail.getColumnModel().getColumn(i);
                             switch(i) {
                                case 0://Time Stamp Field
                                   column.setPreferredWidth(110);
                                             rowWidth+=110;
                                   break;
                                case 1://Frame Field
                                   column.setPreferredWidth(40);
                                             rowWidth+=40;
                                   break;
                                case 2://Length Field
                                   column.setPreferredWidth(45);
                                             rowWidth+=45;
                                   break;
                                case 3://Frame Control Field
                                   column.setPreferredWidth(210);
                                             rowWidth+=210;
                                   break;
                                case 4://sequence Field
                                   column.setPreferredWidth(60);
                                             rowWidth+=60;
                                   break;
                                case 5://LQI Field
                                   column.setPreferredWidth(30);
                                             rowWidth+=30;
                                   break;
                                case 6://Mac Header Field
                                   column.setPreferredWidth(105);
                                             rowWidth+=105;
                                   break;
                                case 7://Channel Field
                                   column.setPreferredWidth(50);
                                             rowWidth+=50;
                                   break;
                                case 8://Dispatch ID Field
                                   column.setPreferredWidth(55);
                                             rowWidth+=55;
                                   break;
                                case 9://RSSI Field
                                   column.setPreferredWidth(33);
                                             rowWidth+=33;
                                   break;
                                case 10://CRC Field
                                   column.setPreferredWidth(30);
                                             rowWidth+=30;
                                   break;
                             }
                          }
         }
         //------------command frame------------//
         else if(parsedPacket.getPacketType()==3)
         {
               short IEEECase = new parser().IEEE802_15_4Case(parsedPacket.getDestAddMode(),parsedPacket.getSrcAddMode(), parsedPacket.getIntra_PAN());
               String [] colsNames = {
                              "<html><center><font face=tahoma size=2>Time (ms)</font>",
                              "<html><center><font face=tahoma size=2>Frame</font></center></html>",
                              "<html><center><font face=tahoma size=2>Length</font></center></html>",
                              "<html><center><font face=tahoma size=2>Frame control field</font><br><hr size='2'></hr><font face='Courier New' size=2>Type Sec Pnd Ack-req Intra-PAN</font></center></html>",
                              "<html><center><font face=tahoma size=2>Sequence<br>number</font></center></html>",
                              "<html><center><font face=tahoma size=2>Dest.<br>PAN</font></center></html>",
                              "<html><center><font face=tahoma size=2>Dest.<br>Address</font></center></html>",
                              "<html><center><font face=tahoma size=2>Src.<br>PAN</font></center></html>",
                              "<html><center><font face=tahoma size=2>Src.<br>Address</font></center></html>",
                              "<html><center><font face=tahoma size=2>Command Payload</font></center></html>",
                              "<html><center><font face=tahoma size=2>LQI</font></center></html>",
                              "<html><center><font face=tahoma size=2>MAC Header Length</font></center></html>",
                              "<html><center><font face=tahoma size=2>Channel</font></center></html>",
                              "<html><center><font face=tahoma size=2>Dispatch<br>ID</font></center></html>",
                              "<html><center><font face=tahoma size=2>RSSI</font></center></html>",
                              "<html><center><font face=tahoma size=2>CRC</font></center></html>"};
                              Object [][] row1 = {{
                                  parsedPacket.getTimeStamp().trim(),
                                  "0",
                                  parsedPacket.getLength().trim(),
                                  frameCon.trim(),
                                  parsedPacket.getSeq().trim(),
                                  parsedPacket.getDestPAN().trim(),
                                  parsedPacket.getDestADD().trim(),
                                  parsedPacket.getSrcPAN().trim(),
                                  parsedPacket.getSrcADD().trim(),
                                  parsedPacket.getPayload().trim(),
                                  parsedPacket.getLQI().trim(),
                                  parsedPacket.getMhrlen().trim(),
                                  parsedPacket.getChannel().trim(),
                                  parsedPacket.getDispID().trim(),
                                  parsedPacket.getRSSI().trim(),
                                  parsedPacket.getCRC().trim()
                              }};
                              packetDetail = new JTable(row1,colsNames);
                              packetDetail.setFont(new Font("Courier New", Font.PLAIN, 10));
                              packetDetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                              packetDetail.setAutoscrolls(true);
                              packetDetail.setColumnSelectionAllowed(false);
                              packetDetail.setRowSelectionAllowed(true);
                              // set the width of the fields
                              for(int i=0;i<16;i++){
                                    TableColumn column = packetDetail.getColumnModel().getColumn(i);
                                    column.setMinWidth(0);
                                    switch(i) {
                                          case 0://Time Stamp Field
                                             column.setPreferredWidth(110);
                                             rowWidth+=110;
                                             break;
                                          case 1://Frame Field
                                             column.setPreferredWidth(50);
                                             rowWidth+=50;
                                             break;
                                          case 2://Length Field
                                             column.setPreferredWidth(55);
                                             rowWidth+=55;
                                             break;
                                          case 3://Frame Control Field
                                             column.setPreferredWidth(200);
                                             rowWidth+=200;
                                             break;
                                          case 4://sequence Field
                                             column.setPreferredWidth(70);
                                             rowWidth+=70;
                                             break;
                                          case 5://Destination PAN Field
                                             column.setPreferredWidth(60);
                                             rowWidth+=60;
                                             break;
                                          case 6://Destination Address Field
                                             column.setPreferredWidth(60);
                                             rowWidth+=60;
                                             break;
                                          case 7://Source PAN Field
                                             if(((String) row1[0][7]).equals("")) column.setPreferredWidth(0);
                                             else {column.setPreferredWidth(60); rowWidth+=60;}
                                             break;
                                          case 8://Source Address Field
                                             if(((String) row1[0][8]).equals("")) column.setPreferredWidth(0);
                                             else {column.setPreferredWidth(90); rowWidth+=90;}
                                             break;
                                          case 9://Payload Field
						int var = 50+(((String) row1[0][9]).length())*7;
						column.setPreferredWidth(var);
						rowWidth+=var;
                                             break;
                                          case 10://LQI Field
                                             column.setPreferredWidth(30);
                                             rowWidth+=30;
                                             break;
                                          case 11://Mac Header Field
                                             column.setPreferredWidth(100);
                                             rowWidth+=100;
                                             break;
                                          case 12://Channel Field
                                             column.setPreferredWidth(50);
                                             rowWidth+=50;
                                             break;
                                          case 13://Dispatch ID Field
                                             column.setPreferredWidth(55);
                                             rowWidth+=55;
                                             break;
                                          case 14://RSSI Field
                                             column.setPreferredWidth(33);
                                             rowWidth+=33;
                                             break;
                                          case 15://CRC Field
                                             column.setPreferredWidth(30);
                                             rowWidth+=30;
                                             break;
                                    }
                              }
               //---------End of command frame---------//

               }

         ZM.rowWidth=rowWidth;
         return packetDetail;
   }

   /***************************************************************************/
   /* Function: ShowPacketsInTable                                            */
   /* Arguments : sixLoWPAN: parsed packet                                    */
   /*             these argument is a divided packet which we will display it */
   /* Return: JTable : which contain the packet in JTable format to display   */
   /*                  it in the GUI                                          */
   /* function store the packet in JTable to display it in the table          */
   /***************************************************************************/
   public JTable ShowPacketsInTable (ICMPStruct parsedPacket, Zmonitor ZM){

	int rowWidth=0;
	 //packetDetail: table contain the detail of the packet
         JTable packetDetail = new JTable();

         String frameCon = parsedPacket.sixLoWPANPacket.getFrameCon()[0]+"   "+parsedPacket.sixLoWPANPacket.getFrameCon()[1]+"   "+parsedPacket.sixLoWPANPacket.getFrameCon()[2]+"     "+parsedPacket.sixLoWPANPacket.getFrameCon()[3]+"           "+parsedPacket.sixLoWPANPacket.getFrameCon()[4]+"                    ";
	      // draw the packets depends on its type
         //---------------beacon frame---------------//
         if(parsedPacket.sixLoWPANPacket.getPacketType()==0)
         {
               short IEEECase = new parser().IEEE802_15_4Case(parsedPacket.sixLoWPANPacket.getDestAddMode(), parsedPacket.sixLoWPANPacket.getSrcAddMode(),parsedPacket.sixLoWPANPacket.getIntra_PAN());
               String SuperFrameSpecs = parsedPacket.sixLoWPANPacket.getBO()+"  "+parsedPacket.sixLoWPANPacket.getSO()+"   "+parsedPacket.sixLoWPANPacket.getFinCap()+"    "+parsedPacket.sixLoWPANPacket.getbat()+"    "+parsedPacket.sixLoWPANPacket.getPAN()+"      "+parsedPacket.sixLoWPANPacket.getass();
               String [] colsNames = {
                              "<html><center><font face=tahoma size=2>Time (ms)</font>",
                              "<html><center><font face=tahoma size=2>Frame</font></center></html>",
                              "<html><center><font face=tahoma size=2>Length</font></center></html>",
                              "<html><center><font face=tahoma size=2>Frame control field</font><br><hr size='2'></hr><font face='Courier New' size=2>Type Sec Pnd Ack-req Intra-PAN</font></center></html>",
                              "<html><center><font face=tahoma size=2>Sequence<br>number</font></center></html>",
                              "<html><center><font face=tahoma size=2>Source.<br>PAN</font></center></html>",
                              "<html><center><font face=tahoma size=2>Source<br>Address</font></center></html>",
                              "<html><center><font face=tahoma size=2>Super frame specification</font><br><hr size='2'></hr><font face='Courier New' size=2>BO SO F.CAP BLE Coord Assoc</font></center></html>",
                              "<html><center><font face=tahoma size=2>GTS</font></center></html>",
                              "<html><center><font face=tahoma size=2>Pend.<br>Address</font></center></html>",
                              "<html><center><font face=tahoma size=2>Beacon.<br>Payload</font></center></html>",
                              "<html><center><font face=tahoma size=2>LQI</font></center></html>",
                              "<html><center><center><font face=tahoma size=2>MAC Header Length</font></center></html>",
                              "<html><center><font face=tahoma size=2>Channel</font></center></html>",
                              "<html><center><center><font face=tahoma size=2>Dispatch<br>ID</font></center></html>",
                              "<html><center><font face=tahoma size=2>RSSI</font></center></html>",
                              "<html><center><font face=tahoma size=2>CRC</font></center></html>"};
                              Object [][] row1 = {{
                                      parsedPacket.sixLoWPANPacket.getTimeStamp().trim(),
                                      "0",
                                      parsedPacket.sixLoWPANPacket.getLength().trim(),
                                      frameCon.trim(),
                                      parsedPacket.sixLoWPANPacket.getSeq().trim(),
                                      parsedPacket.sixLoWPANPacket.getSrcPAN().trim(),
                                      parsedPacket.sixLoWPANPacket.getSrcADD().trim(),
                                      SuperFrameSpecs.trim(),
                                      parsedPacket.sixLoWPANPacket.getGTS().trim(),
                                      parsedPacket.sixLoWPANPacket.getPending().trim(),
                                      parsedPacket.sixLoWPANPacket.getPayload().trim(),
                                      parsedPacket.sixLoWPANPacket.getLQI().trim(),
                                      parsedPacket.sixLoWPANPacket.getMhrlen().trim(),
                                      parsedPacket.sixLoWPANPacket.getChannel().trim(),
                                      parsedPacket.sixLoWPANPacket.getDispID().trim(),
                                      parsedPacket.sixLoWPANPacket.getRSSI().trim(),
                                      parsedPacket.sixLoWPANPacket.getCRC().trim()
                              }};
                              packetDetail = new JTable(row1,colsNames);
                              packetDetail.setFont(new Font("Courier New", Font.PLAIN, 10));
                              packetDetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                              packetDetail.setAutoscrolls(true);
                              packetDetail.setColumnSelectionAllowed(false);
                              packetDetail.setRowSelectionAllowed(true);
                              // set the width of the fields
                              for(int i=0;i<17;i++){
                                    TableColumn column = packetDetail.getColumnModel().getColumn(i);
                                    column.setMinWidth(0);
                                    switch(i) {
                                          case 0://Time Stamp Field
                                             column.setPreferredWidth(110);
                                             rowWidth+=110;
                                             break;
                                          case 1://Frame Field
                                             column.setPreferredWidth(40);
                                             rowWidth+=40;
                                             break;
                                          case 2://Length Field
                                             column.setPreferredWidth(45);
                                             rowWidth+=45;
                                             break;
                                          case 3://Frame Control Field
                                             column.setPreferredWidth(210);
                                             rowWidth+=210;
                                             break;
                                          case 4://sequence Field
                                             column.setPreferredWidth(60);
                                             rowWidth+=60;
                                             break;
                                          case 5://Source PAN Field
                                             column.setPreferredWidth(50);
                                             rowWidth+=50;
                                             break;
                                          case 6://Source Address Field
                                             column.setPreferredWidth(50);
                                             rowWidth+=50;
                                             break;
                                          case 7://Superframe Spec
                                             column.setPreferredWidth(180);
                                             rowWidth+=180;
                                             break;
                                          case 8://GTS Field
                                             column.setPreferredWidth(25);
                                             rowWidth+=25;
                                             break;
                                          case 9://Pending Address
                                             column.setPreferredWidth(45);
                                             rowWidth+=45;
                                             break;   
                                          case 10://Payload
                                             if(((String) row1[0][10]).equals(""))
						column.setPreferredWidth(0);
                                             else{
						int var = 50+(((String) row1[0][10]).length())*7;
						column.setPreferredWidth(var);
						rowWidth+=var; }
                                             break;
                                          case 11://LQI Field
                                             column.setPreferredWidth(20);
                                             rowWidth+=20;
                                             break;
                                          case 12://Mac Header Field
                                             column.setPreferredWidth(55);
                                             rowWidth+=55;
                                             break;
                                          case 13://Channel Field
                                             column.setPreferredWidth(40);
                                             rowWidth+=40;
                                             break;
                                          case 14://Dispatch ID Field
                                             column.setPreferredWidth(55);
                                             rowWidth+=55;
                                             break;
                                          case 15://RSSI Field
                                             column.setPreferredWidth(33);
                                             rowWidth+=33;
                                             break;
                                          case 16://CRC Field
                                             column.setPreferredWidth(20);
                                             rowWidth+=20;
                                             break;
                                    }
                              }
               
         }
         //------------End of beacon frame------------//

        //------------Data frame------------//
         else if(parsedPacket.sixLoWPANPacket.getPacketType()==1)
         {
               short IEEECase = new parser().IEEE802_15_4Case(parsedPacket.sixLoWPANPacket.getDestAddMode(),parsedPacket.sixLoWPANPacket.getSrcAddMode(), parsedPacket.sixLoWPANPacket.getIntra_PAN());
               if (parsedPacket.ICMPType.equals("echoRequestHeader")){
                     String [] colsNames = {
                                    "<html><center><font face=tahoma size=2>Time (ms)</font>",
                                    "<html><center><font face=tahoma size=2>Frame</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Length</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Frame control field</font><br><hr size='2'></hr><font face='Courier New' size=2>Type Sec Pnd Ack-req Intra-PAN</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Sequence<br>number</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dest.<br>PAN</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dest.<br>Address</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Src.<br>PAN</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Src.<br>Address</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dispatch</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Encoding</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Context</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Traffic class<br>& Flow label</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Next header</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Hop limit</font></center></html>",
                                    "<html><center><font face=tahoma size=2>6LoWPAN<br>Src. Add.</font></center></html>",
                                    "<html><center><font face=tahoma size=2>6LoWPAN<br>Dest. Add.</font></center></html>",
                                    "<html><center><font face=tahoma size=2>LQI</font></center></html>",
                                    "<html><center><font face=tahoma size=2>MAC Header Length</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Channel</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dispatch<br>ID</font></center></html>",
                                    "<html><center><font face=tahoma size=2>RSSI</font></center></html>",
                                    "<html><center><font face=tahoma size=2>CRC</font></center></html>",
                                    "<html><center><font face=tahoma size=2>ICMP<br>Type</font></center></html>",
                                    "<html><center><font face=tahoma size=2>ICMP<br>Code</font></center></html>",
                                    "<html><center><font face=tahoma size=2>ICMP<br>cheksum</font></center></html>",
                                    "<html><center><font face=tahoma size=2>ICMP<br>identifier</font></center></html>",
                                    "<html><center><font face=tahoma size=2>ICMP<br>sequence</font></center></html>",
                              	    "<html><center><font face=tahoma size=2>Payload</font></center></html>"};
                                    Object [][] row1 = {{
                                        parsedPacket.sixLoWPANPacket.getTimeStamp().trim(),
                                        "0",
                                        parsedPacket.sixLoWPANPacket.getLength().trim(),
                                        frameCon.trim(),
                                        parsedPacket.sixLoWPANPacket.getSeq().trim(),
                                        parsedPacket.sixLoWPANPacket.getDestPAN().trim(),
                                        parsedPacket.sixLoWPANPacket.getDestADD().trim(),
                                        parsedPacket.sixLoWPANPacket.getSrcPAN().trim(),
                                        parsedPacket.sixLoWPANPacket.getSrcADD().trim(),
                                        parsedPacket.sixLoWPANPacket.getDispatchField().trim(),
                                        parsedPacket.sixLoWPANPacket.getEncodingField().trim(),
                                        parsedPacket.sixLoWPANPacket.getcontextField().trim(),
                                        parsedPacket.sixLoWPANPacket.gettrafficClass_FlowLabelField().trim(),
                                        parsedPacket.sixLoWPANPacket.getNextHeaderField().trim(),
                                        parsedPacket.sixLoWPANPacket.getHopLimitField().trim(),
                                        parsedPacket.sixLoWPANPacket.getSourceAddField().trim(),
                                        parsedPacket.sixLoWPANPacket.getDestAddField().trim(),
                                        parsedPacket.sixLoWPANPacket.getLQI().trim(),
                                        parsedPacket.sixLoWPANPacket.getMhrlen().trim(),
                                        parsedPacket.sixLoWPANPacket.getChannel().trim(),
                                        parsedPacket.sixLoWPANPacket.getDispID().trim(),
                                        parsedPacket.sixLoWPANPacket.getRSSI().trim(),
                                        parsedPacket.sixLoWPANPacket.getCRC().trim(),
                                        parsedPacket.echoRequestHeader.geticmp_Type().trim(),
                                        parsedPacket.echoRequestHeader.geticmp_Code().trim(),
                                        parsedPacket.echoRequestHeader.geticmp_Cheksum().trim(),
                                        parsedPacket.echoRequestHeader.getIdentifier().trim(),
                                        parsedPacket.echoRequestHeader.getSequenceNumber().trim(),
                                        parsedPacket.sixLoWPANPacket.get6LoWPANPayload().trim()
                                    }};
      //System.out.println(parsedPacket.sixLoWPANPacket.gettrafficClass_FlowLabelField().trim());
                                    packetDetail = new JTable(row1,colsNames);
                                    packetDetail.setFont(new Font("Courier New", Font.PLAIN, 10));
                                    packetDetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                                    packetDetail.setAutoscrolls(true);
                                    packetDetail.setColumnSelectionAllowed(false);
                                    packetDetail.setRowSelectionAllowed(true);
                                    // set the width of the fields
                                    for(int i=0;i<29;i++){
                                          TableColumn column = packetDetail.getColumnModel().getColumn(i);
                                          column.setMinWidth(0);
                                          switch(i) {
                                                case 0://Time Stamp Field
                                                   column.setPreferredWidth(110);
                                                   rowWidth+=110;
                                                   break;
                                                case 1://Frame Field
                                                   column.setPreferredWidth(40);
                                                   rowWidth+=40;
                                                   break;
                                                case 2://Length Field
                                                   column.setPreferredWidth(45);
                                                   rowWidth+=45;
                                                   break;
                                                case 3://Frame Control Field
                                                   column.setPreferredWidth(190);
                                                   rowWidth+=190;
                                                   break;
                                                case 4://sequence Field
                                                   column.setPreferredWidth(60);
                                                   rowWidth+=60;
                                                   break;
                                                case 5://Destination PAN Field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 6://Destination Address Field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 7://Source PAN Field
                                                   if(((String) row1[0][7]).equals("")) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(55); rowWidth+=55;}
                                                   break;
                                                case 8://Source Address Field
                                                   if(((String) row1[0][8]).equals("")) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(65); rowWidth+=65;}
                                                   break;
                                                case 9://dispatch field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 10://encoding field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 11://context Field
                                                   if(((String) row1[0][11]).equals("") || ((String) row1[0][11]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(55); rowWidth+=55;}
                                                   break;
                                                case 12://traffic class and flow label
                                                   if(((String) row1[0][12]).equals("") || ((String) row1[0][12]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(150); rowWidth+=150;}
                                                   break;
                                                case 13://Next header Field
                                                   if(((String) row1[0][13]).equals("") || ((String) row1[0][13]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(90); rowWidth+=90;}
                                                   break;
                                                case 14://hop Limit Field
                                                   if(((String) row1[0][14]).equals("") || ((String) row1[0][14]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(90); rowWidth+=90;}
                                                   break;
                                                case 15://6LoWPAN Source Address Field
                                                   if(((String) row1[0][15]).equals("") || ((String) row1[0][15]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else { int var = (((String) row1[0][15]).length())*12; column.setPreferredWidth(var); rowWidth+=var;}
                                                   break;
                                                case 16://6LoWPAN destination Address Field
                                                   if(((String) row1[0][16]).equals("") || ((String) row1[0][16]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else { int var = (((String) row1[0][16]).length())*12; column.setPreferredWidth(var); rowWidth+=var;}
                                                   break;
                                                case 17://LQI Field
                                                   column.setPreferredWidth(30);
                                                   rowWidth+=30;
                                                   break;
                                                case 18://Mac Header Field
                                                   column.setPreferredWidth(105);
                                                   rowWidth+=105;
                                                   break;
                                                case 19://Channel Field
                                                   column.setPreferredWidth(50);
                                                   rowWidth+=50;
                                                   break;
                                                case 20://Dispatch ID Field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 21://RSSI Field
                                                   column.setPreferredWidth(33);
                                                   rowWidth+=33;
                                                   break;
                                                case 22://CRC Field
                                                   column.setPreferredWidth(30);
                                                   rowWidth+=30;
                                                   break;
                                                case 23://ICMP type
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 24://ICMP code
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 25://ICMP checksum
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 26://ICMP id
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 27://ICMP sequance
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                          	case 28://Payload Field
                                                   int var1 = 50+(((String) row1[0][28]).length())*7;
                                                   column.setPreferredWidth(var1);
                                                   rowWidth+=var1;
                                                   break;
                                          }
                                    }
               } else if (parsedPacket.ICMPType.equals("DAO")){
                     String [] colsNames = {
                                    "<html><center><font face=tahoma size=2>Time (ms)</font>",
                                    "<html><center><font face=tahoma size=2>Frame</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Length</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Frame control field</font><br><hr size='2'></hr><font face='Courier New' size=2>Type Sec Pnd Ack-req Intra-PAN</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Sequence<br>number</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dest.<br>PAN</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dest.<br>Address</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Src.<br>PAN</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Src.<br>Address</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dispatch</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Encoding</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Context</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Traffic class<br>& Flow label</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Next header</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Hop limit</font></center></html>",
                                    "<html><center><font face=tahoma size=2>6LoWPAN<br>Src. Add.</font></center></html>",
                                    "<html><center><font face=tahoma size=2>6LoWPAN<br>Dest. Add.</font></center></html>",
                                    "<html><center><font face=tahoma size=2>LQI</font></center></html>",
                                    "<html><center><font face=tahoma size=2>MAC Header Length</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Channel</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dispatch<br>ID</font></center></html>",
                                    "<html><center><font face=tahoma size=2>RSSI</font></center></html>",
                                    "<html><center><font face=tahoma size=2>CRC</font></center></html>",
                                    "<html><center><font face=tahoma size=2>ICMP<br>Type</font></center></html>",
                                    "<html><center><font face=tahoma size=2>ICMP<br>Code</font></center></html>",
                                    "<html><center><font face=tahoma size=2>ICMP<br>Cheksum</font></center></html>",
                                    "<html><center><font face=tahoma size=2>ICMP<br>RPL<br>Instance ID</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Flags</font></center></html>",
                                    "<html><center><font face=tahoma size=2>ICMP<br>Reserved</font></center></html>",
                                    "<html><center><font face=tahoma size=2>DAO<br>Sequence</font></center></html>",
                                    "<html><center><font face=tahoma size=2>RPL Target<br>Type</font></center></html>",
                                    "<html><center><font face=tahoma size=2>RPL Target<br>Length</font></center></html>",
                                    "<html><center><font face=tahoma size=2>RPL Target<br>Reserved</font></center></html>",
                                    "<html><center><font face=tahoma size=2>RPL Target<br>Target Length</font></center></html>",
                                    "<html><center><font face=tahoma size=2>RPL Target<br>Target</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Transit<br>Info. Type</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Transit<br>Info. Length</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Transit<br>Info. Flags</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Transit Info.<br>Path Control</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Transit Info.<br>Path Sequence</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Transit Info.<br>Path Lifetime</font></center></html>",
                              	    "<html><center><font face=tahoma size=2>Payload</font></center></html>"};
                                    Object [][] row1 = {{
                                        parsedPacket.sixLoWPANPacket.getTimeStamp().trim(),
                                        "0",
                                        parsedPacket.sixLoWPANPacket.getLength().trim(),
                                        frameCon.trim(),
                                        parsedPacket.sixLoWPANPacket.getSeq().trim(),
                                        parsedPacket.sixLoWPANPacket.getDestPAN().trim(),
                                        parsedPacket.sixLoWPANPacket.getDestADD().trim(),
                                        parsedPacket.sixLoWPANPacket.getSrcPAN().trim(),
                                        parsedPacket.sixLoWPANPacket.getSrcADD().trim(),
                                        parsedPacket.sixLoWPANPacket.getDispatchField().trim(),
                                        parsedPacket.sixLoWPANPacket.getEncodingField().trim(),
                                        parsedPacket.sixLoWPANPacket.getcontextField().trim(),
                                        parsedPacket.sixLoWPANPacket.gettrafficClass_FlowLabelField().trim(),
                                        parsedPacket.sixLoWPANPacket.getNextHeaderField().trim(),
                                        parsedPacket.sixLoWPANPacket.getHopLimitField().trim(),
                                        parsedPacket.sixLoWPANPacket.getSourceAddField().trim(),
                                        parsedPacket.sixLoWPANPacket.getDestAddField().trim(),
                                        parsedPacket.sixLoWPANPacket.getLQI().trim(),
                                        parsedPacket.sixLoWPANPacket.getMhrlen().trim(),
                                        parsedPacket.sixLoWPANPacket.getChannel().trim(),
                                        parsedPacket.sixLoWPANPacket.getDispID().trim(),
                                        parsedPacket.sixLoWPANPacket.getRSSI().trim(),
                                        parsedPacket.sixLoWPANPacket.getCRC().trim(),
                                        parsedPacket.daoHeader.geticmp_Type().trim(),
                                        parsedPacket.daoHeader.geticmp_Code().trim(),
                                        parsedPacket.daoHeader.geticmp_Cheksum().trim(),
                                        parsedPacket.daoHeader.getRPLInstanceID().trim(),
                                        parsedPacket.daoHeader.getFlags().trim(),
                                    parsedPacket.daoHeader.getReserved_icmp().trim(),
                                    parsedPacket.daoHeader.getDAOSequence().trim(),
                                    parsedPacket.daoHeader.getRPLTargetType().trim(),
                                    parsedPacket.daoHeader.getRPLTargetLength().trim(),
                                    parsedPacket.daoHeader.getRPLTargetReserved().trim(),
                                    parsedPacket.daoHeader.getRPLTarget_TargetLength().trim(),
                                    parsedPacket.daoHeader.getRPLTarget_Target().trim(),
                                    parsedPacket.daoHeader.getTransitInformationType().trim(),
                                    parsedPacket.daoHeader.getTransitInformationLength().trim(),
                                    parsedPacket.daoHeader.getTransitInformationFlags().trim(),
                                    parsedPacket.daoHeader.getTransitInformationPathControl().trim(),
                                    parsedPacket.daoHeader.getTransitInformationPathSequence().trim(),
                                    parsedPacket.daoHeader.getTransitInformationPathLifetime().trim(),
                                        parsedPacket.sixLoWPANPacket.get6LoWPANPayload().trim()
                                    }};
      //System.out.println(parsedPacket.sixLoWPANPacket.gettrafficClass_FlowLabelField().trim());
                                    packetDetail = new JTable(row1,colsNames);
                                    packetDetail.setFont(new Font("Courier New", Font.PLAIN, 10));
                                    packetDetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                                    packetDetail.setAutoscrolls(true);
                                    packetDetail.setColumnSelectionAllowed(false);
                                    packetDetail.setRowSelectionAllowed(true);
                                    // set the width of the fields
                                    for(int i=0;i<42;i++){
                                          TableColumn column = packetDetail.getColumnModel().getColumn(i);
                                          column.setMinWidth(0);
                                          switch(i) {
                                                case 0://Time Stamp Field
                                                   column.setPreferredWidth(110);
                                                   rowWidth+=110;
                                                   break;
                                                case 1://Frame Field
                                                   column.setPreferredWidth(40);
                                                   rowWidth+=40;
                                                   break;
                                                case 2://Length Field
                                                   column.setPreferredWidth(45);
                                                   rowWidth+=45;
                                                   break;
                                                case 3://Frame Control Field
                                                   column.setPreferredWidth(190);
                                                   rowWidth+=190;
                                                   break;
                                                case 4://sequence Field
                                                   column.setPreferredWidth(60);
                                                   rowWidth+=60;
                                                   break;
                                                case 5://Destination PAN Field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 6://Destination Address Field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 7://Source PAN Field
                                                   if(((String) row1[0][7]).equals("")) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(55); rowWidth+=55;}
                                                   break;
                                                case 8://Source Address Field
                                                   if(((String) row1[0][8]).equals("")) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(65); rowWidth+=65;}
                                                   break;
                                                case 9://dispatch field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 10://encoding field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 11://context Field
                                                   if(((String) row1[0][11]).equals("") || ((String) row1[0][11]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(55); rowWidth+=55;}
                                                   break;
                                                case 12://traffic class and flow label
                                                   if(((String) row1[0][12]).equals("") || ((String) row1[0][12]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(150); rowWidth+=150;}
                                                   break;
                                                case 13://Next header Field
                                                   if(((String) row1[0][13]).equals("") || ((String) row1[0][13]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(90); rowWidth+=90;}
                                                   break;
                                                case 14://hop Limit Field
                                                   if(((String) row1[0][14]).equals("") || ((String) row1[0][14]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(90); rowWidth+=90;}
                                                   break;
                                                case 15://6LoWPAN Source Address Field
                                                   if(((String) row1[0][15]).equals("") || ((String) row1[0][15]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else { int var = (((String) row1[0][15]).length())*12; column.setPreferredWidth(var); rowWidth+=var;}
                                                   break;
                                                case 16://6LoWPAN destination Address Field
                                                   if(((String) row1[0][16]).equals("") || ((String) row1[0][16]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {int var = (((String) row1[0][16]).length())*12; column.setPreferredWidth(var); rowWidth+=var;}
                                                   break;
                                                case 17://LQI Field
                                                   column.setPreferredWidth(30);
                                                   rowWidth+=30;
                                                   break;
                                                case 18://Mac Header Field
                                                   column.setPreferredWidth(105);
                                                   rowWidth+=105;
                                                   break;
                                                case 19://Channel Field
                                                   column.setPreferredWidth(50);
                                                   rowWidth+=50;
                                                   break;
                                                case 20://Dispatch ID Field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 21://RSSI Field
                                                   column.setPreferredWidth(33);
                                                   rowWidth+=33;
                                                   break;
                                                case 22://CRC Field
                                                   column.setPreferredWidth(30);
                                                   rowWidth+=30;
                                                   break;
                                                case 23://ICMP type
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 24://ICMP code
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 25://ICMP checksum
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 26://RPLInstanceID
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 27://Flags
                                                   column.setPreferredWidth(33);
                                                   rowWidth+=33;
                                                   break;
                                                case 28://Reserved_icmp
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 29://DAOSequence
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 30://RPLTargetType
                                                   column.setPreferredWidth(60);
                                                   rowWidth+=60;
                                                   break;
                                                case 31://RPLTargetLength
                                                   column.setPreferredWidth(60);
                                                   rowWidth+=60;
                                                   break;
                                                case 32://RPLTargetReserved
                                                   column.setPreferredWidth(70);
                                                   rowWidth+=70;
                                                   break;
                                                case 34://RPLTarget_TargetLength
                                                   column.setPreferredWidth(80);
                                                   rowWidth+=80;
                                                   break;
                                                case 35://RPLTarget_Target
                                                   column.setPreferredWidth(750);
                                                   rowWidth+=750;
                                                   break;
                                                case 36://TransitInformationType
                                                   column.setPreferredWidth(80);
                                                   rowWidth+=80;
                                                case 37://TransitInformationLength
                                                   column.setPreferredWidth(80);
                                                   rowWidth+=80;
                                                   break;
                                                case 38://TransitInformationFlags
                                                   column.setPreferredWidth(80);
                                                   rowWidth+=80;
                                                   break;
                                                case 39://TransitInformationPathControl
                                                   column.setPreferredWidth(80);
                                                   rowWidth+=80;
                                                   break;
                                                case 40://TransitInformationPathSequence
                                                   column.setPreferredWidth(80);
                                                   rowWidth+=80;
                                                   break;
                                                case 41://TransitInformationPathLifetime
                                                   column.setPreferredWidth(80);
                                                   rowWidth+=80;
                                                   break;
                                          	case 42://Payload Field
                                                   int var1 = 50+(((String) row1[0][41]).length())*7;
                                                   column.setPreferredWidth(var1);
                                                   rowWidth+=var1;
                                                   break;
                                          }
                                    }
               } else if (parsedPacket.ICMPType.equals("DIO")){
                     String [] colsNames = {
                                    "<html><center><font face=tahoma size=2>Time (ms)</font>",
                                    "<html><center><font face=tahoma size=2>Frame</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Length</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Frame control field</font><br><hr size='2'></hr><font face='Courier New' size=2>Type Sec Pnd Ack-req Intra-PAN</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Sequence<br>number</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dest.<br>PAN</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dest.<br>Address</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Src.<br>PAN</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Src.<br>Address</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dispatch</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Encoding</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Context</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Traffic class<br>& Flow label</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Next header</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Hop limit</font></center></html>",
                                    "<html><center><font face=tahoma size=2>6LoWPAN<br>Src. Add.</font></center></html>",
                                    "<html><center><font face=tahoma size=2>6LoWPAN<br>Dest. Add.</font></center></html>",
                                    "<html><center><font face=tahoma size=2>LQI</font></center></html>",
                                    "<html><center><font face=tahoma size=2>MAC Header Length</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Channel</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dispatch<br>ID</font></center></html>",
                                    "<html><center><font face=tahoma size=2>RSSI</font></center></html>",
                                    "<html><center><font face=tahoma size=2>CRC</font></center></html>",
                                    "<html><center><font face=tahoma size=2>ICMP<br>Type</font></center></html>",
                                    "<html><center><font face=tahoma size=2>ICMP<br>Code</font></center></html>",
                                    "<html><center><font face=tahoma size=2>ICMP<br>Cheksum</font></center></html>",
                                    "<html><center><font face=tahoma size=2>ICMP<br>RPL<br>Instance ID</font></center></html>",
                                    "<html><center><font face=tahoma size=2>version</font></center></html>",
                                    "<html><center><font face=tahoma size=2>rank</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Flags</font></center></html>",
                                    "<html><center><font face=tahoma size=2>DATSN</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Flags2</font></center></html>",
                                    "<html><center><font face=tahoma size=2>ICMP<br>Reserved</font></center></html>",
                                    "<html><center><font face=tahoma size=2>DODAGID</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Metric<br>Container Type</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Metric<br>Container Length</font></center></html>",
                                    "<html><center><font face=tahoma size=2>DODAG<br>Configuration Type</font></center></html>",
                                    "<html><center><font face=tahoma size=2>DODAG<br>Configuration Length</font></center></html>",
                                    "<html><center><font face=tahoma size=2>DODAG<br>Configuration Flags</font></center></html>",
                                    "<html><center><font face=tahoma size=2>DODAG Configuration<br>DIO Interval Doubling</font></center></html>",
                                    "<html><center><font face=tahoma size=2>DODAG Configuration<br>DIO Interval Min</font></center></html>",
                                    "<html><center><font face=tahoma size=2>DODAG Configuration<br>DIO Redundancy Constant</font></center></html>",
                                    "<html><center><font face=tahoma size=2>DODAG Configuration<br>Max Rank</font></center></html>",
                                    "<html><center><font face=tahoma size=2>DODAG Configuration<br>MinHop Rank</font></center></html>",
                                    "<html><center><font face=tahoma size=2>DODAG Configuration<br>OCP</font></center></html>",
                                    "<html><center><font face=tahoma size=2>DODAG Configuration<br>Reserved</font></center></html>",
                                    "<html><center><font face=tahoma size=2>DODAG Configuration<br>Default Lifetime</font></center></html>",
                                    "<html><center><font face=tahoma size=2>DODAG Configuration<br>Life time Unit</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Prefix Information<br>Type</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Prefix Information<br>Length</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Prefix Information<br>Prefix Length</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Prefix Information<br>Flags</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Prefix Information<br>Valid Lifetime</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Prefix Information<br>Preferred Lifetime</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Prefix Information<br>Reserved</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Prefix Information<br>Destination Prefix</font></center></html>",
                              	    "<html><center><font face=tahoma size=2>Payload</font></center></html>"};
                                    Object [][] row1 = {{
                                        parsedPacket.sixLoWPANPacket.getTimeStamp().trim(),
                                        "0",
                                        parsedPacket.sixLoWPANPacket.getLength().trim(),
                                        frameCon.trim(),
                                        parsedPacket.sixLoWPANPacket.getSeq().trim(),
                                        parsedPacket.sixLoWPANPacket.getDestPAN().trim(),
                                        parsedPacket.sixLoWPANPacket.getDestADD().trim(),
                                        parsedPacket.sixLoWPANPacket.getSrcPAN().trim(),
                                        parsedPacket.sixLoWPANPacket.getSrcADD().trim(),
                                        parsedPacket.sixLoWPANPacket.getDispatchField().trim(),
                                        parsedPacket.sixLoWPANPacket.getEncodingField().trim(),
                                        parsedPacket.sixLoWPANPacket.getcontextField().trim(),
                                        parsedPacket.sixLoWPANPacket.gettrafficClass_FlowLabelField().trim(),
                                        parsedPacket.sixLoWPANPacket.getNextHeaderField().trim(),
                                        parsedPacket.sixLoWPANPacket.getHopLimitField().trim(),
                                        parsedPacket.sixLoWPANPacket.getSourceAddField().trim(),
                                        parsedPacket.sixLoWPANPacket.getDestAddField().trim(),
                                        parsedPacket.sixLoWPANPacket.getLQI().trim(),
                                        parsedPacket.sixLoWPANPacket.getMhrlen().trim(),
                                        parsedPacket.sixLoWPANPacket.getChannel().trim(),
                                        parsedPacket.sixLoWPANPacket.getDispID().trim(),
                                        parsedPacket.sixLoWPANPacket.getRSSI().trim(),
                                        parsedPacket.sixLoWPANPacket.getCRC().trim(),
                                        parsedPacket.dioHeader.geticmp_Type().trim(),
                                        parsedPacket.dioHeader.geticmp_Code().trim(),
                                        parsedPacket.dioHeader.geticmp_Cheksum().trim(),
                                        parsedPacket.dioHeader.getRPLInstanceID().trim(),
                                        parsedPacket.dioHeader.getVersion().trim(),
                                        parsedPacket.dioHeader.getRank().trim(),
                                        parsedPacket.dioHeader.getFlags().trim(),
                                        parsedPacket.dioHeader.getDATSN().trim(),
                                        parsedPacket.dioHeader.getFlags2().trim(),
                                    parsedPacket.dioHeader.getReserved_icmp().trim(),
                                    parsedPacket.dioHeader.getDODAGID().trim(),
                                    parsedPacket.dioHeader.getMetricContainerType().trim(),
                                    parsedPacket.dioHeader.getMetricContainerLength().trim(),
                                    parsedPacket.dioHeader.getDODAGConfigurationType().trim(),
                                    parsedPacket.dioHeader.getDODAGConfigurationLength().trim(),
                                    parsedPacket.dioHeader.getDODAGConfigurationFlags().trim(),
                                    parsedPacket.dioHeader.getDODAGConfigurationDIOIntervalDoubling().trim(),
                                    parsedPacket.dioHeader.getDODAGConfigurationDIOIntervalMin().trim(),
                                    parsedPacket.dioHeader.getDODAGConfigurationDIORedundancyConstant().trim(),
                                    parsedPacket.dioHeader.getDODAGConfigurationMaxRank().trim(),
                                    parsedPacket.dioHeader.getDODAGConfigurationMinHopRank().trim(),
                                    parsedPacket.dioHeader.getDODAGConfigurationOCP().trim(),
                                    parsedPacket.dioHeader.getDODAGConfigurationReserved().trim(),
                                    parsedPacket.dioHeader.getDODAGConfigurationDefaultLifetime().trim(),
                                    parsedPacket.dioHeader.getDODAGConfigurationLifetimeUnit().trim(),
                                    parsedPacket.dioHeader.getPrefixInformationType().trim(),
                                    parsedPacket.dioHeader.getPrefixInformationLength().trim(),
                                    parsedPacket.dioHeader.getPrefixInformation_PrefixLength().trim(),
                                    parsedPacket.dioHeader.getPrefixInformationFlags().trim(),
                                    parsedPacket.dioHeader.getPrefixInformationValidLifetime().trim(),
                                    parsedPacket.dioHeader.getPrefixInformationPreferredLifetime().trim(),
                                    parsedPacket.dioHeader.getPrefixInformationReserved().trim(),
                                    parsedPacket.dioHeader.getPrefixInformationDestinationPrefix().trim(),
                                        parsedPacket.sixLoWPANPacket.get6LoWPANPayload().trim()
                                    }};
      //System.out.println(parsedPacket.sixLoWPANPacket.gettrafficClass_FlowLabelField().trim());
                                    packetDetail = new JTable(row1,colsNames);
                                    packetDetail.setFont(new Font("Courier New", Font.PLAIN, 10));
                                    packetDetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                                    packetDetail.setAutoscrolls(true);
                                    packetDetail.setColumnSelectionAllowed(false);
                                    packetDetail.setRowSelectionAllowed(true);
                                    // set the width of the fields
                                    for(int i=0;i<57;i++){
                                          TableColumn column = packetDetail.getColumnModel().getColumn(i);
                                          column.setMinWidth(0);
                                          switch(i) {
                                                case 0://Time Stamp Field
                                                   column.setPreferredWidth(110);
                                                   rowWidth+=110;
                                                   break;
                                                case 1://Frame Field
                                                   column.setPreferredWidth(40);
                                                   rowWidth+=40;
                                                   break;
                                                case 2://Length Field
                                                   column.setPreferredWidth(45);
                                                   rowWidth+=45;
                                                   break;
                                                case 3://Frame Control Field
                                                   column.setPreferredWidth(190);
                                                   rowWidth+=190;
                                                   break;
                                                case 4://sequence Field
                                                   column.setPreferredWidth(60);
                                                   rowWidth+=60;
                                                   break;
                                                case 5://Destination PAN Field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 6://Destination Address Field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 7://Source PAN Field
                                                   if(((String) row1[0][7]).equals("")) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(55); rowWidth+=55;}
                                                   break;
                                                case 8://Source Address Field
                                                   if(((String) row1[0][8]).equals("")) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(65); rowWidth+=65;}
                                                   break;
                                                case 9://dispatch field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 10://encoding field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 11://context Field
                                                   if(((String) row1[0][11]).equals("") || ((String) row1[0][11]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(55); rowWidth+=55;}
                                                   break;
                                                case 12://traffic class and flow label
                                                   if(((String) row1[0][12]).equals("") || ((String) row1[0][12]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(150); rowWidth+=150;}
                                                   break;
                                                case 13://Next header Field
                                                   if(((String) row1[0][13]).equals("") || ((String) row1[0][13]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(90); rowWidth+=90;}
                                                   break;
                                                case 14://hop Limit Field
                                                   if(((String) row1[0][14]).equals("") || ((String) row1[0][14]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(90); rowWidth+=90;}
                                                   break;
                                                case 15://6LoWPAN Source Address Field
                                                   if(((String) row1[0][15]).equals("") || ((String) row1[0][15]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {int var = (((String) row1[0][15]).length())*12; column.setPreferredWidth(var); rowWidth+=var;}
                                                   break;
                                                case 16://6LoWPAN destination Address Field
                                                   if(((String) row1[0][16]).equals("") || ((String) row1[0][16]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {int var = (((String) row1[0][16]).length())*12; column.setPreferredWidth(var); rowWidth+=var;}
                                                   break;
                                                case 17://LQI Field
                                                   column.setPreferredWidth(30);
                                                   rowWidth+=30;
                                                   break;
                                                case 18://Mac Header Field
                                                   column.setPreferredWidth(105);
                                                   rowWidth+=105;
                                                   break;
                                                case 19://Channel Field
                                                   column.setPreferredWidth(50);
                                                   rowWidth+=50;
                                                   break;
                                                case 20://Dispatch ID Field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 21://RSSI Field
                                                   column.setPreferredWidth(33);
                                                   rowWidth+=33;
                                                   break;
                                                case 22://CRC Field
                                                   column.setPreferredWidth(30);
                                                   rowWidth+=30;
                                                   break;
                                                case 23://ICMP type
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 24://ICMP code
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 25://ICMP checksum
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 26://RPLInstanceID
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 27://Version
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 28://Rank
                                                   column.setPreferredWidth(33);
                                                   rowWidth+=33;
                                                   break;
                                                case 29://Flags
                                                   column.setPreferredWidth(33);
                                                   rowWidth+=33;
                                                   break;
                                                case 30://DATSN
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 31://Flags2
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 32://Reserved_icmp
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 33://DODAGID
                                                   column.setPreferredWidth(250);
                                                   rowWidth+=250;
                                                   break;
                                                case 34://MetricContainerType
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 35://MetricContainerLength
                                                   column.setPreferredWidth(70);
                                                   rowWidth+=70;
                                                   break;
                                                case 36://DODAGConfigurationType
                                                   column.setPreferredWidth(70);
                                                   rowWidth+=70;
                                                   break;
                                                case 37://DODAGConfigurationLength
                                                   column.setPreferredWidth(70);
                                                   rowWidth+=70;
                                                   break;
                                                case 38://DODAGConfigurationFlags
                                                   column.setPreferredWidth(70);
                                                   rowWidth+=70;
                                                   break;
                                                case 39://DODAGConfigurationDIOIntervalDoubling
                                                   column.setPreferredWidth(70);
                                                   rowWidth+=70;
                                                case 40://DODAGConfigurationDIOIntervalMin
                                                   column.setPreferredWidth(70);
                                                   rowWidth+=70;
                                                   break;
                                                case 41://DODAGConfigurationDIORedundancyConstant
                                                   column.setPreferredWidth(80);
                                                   rowWidth+=80;
                                                   break;
                                                case 42://DODAGConfigurationMaxRank
                                                   column.setPreferredWidth(80);
                                                   rowWidth+=80;
                                                   break;
                                                case 43://DODAGConfigurationMinHopRank
                                                   column.setPreferredWidth(80);
                                                   rowWidth+=80;
                                                   break;
                                                case 44://DODAGConfigurationOCP
                                                   column.setPreferredWidth(80);
                                                   rowWidth+=80;
                                                   break;
                                                case 45://DODAGConfigurationReserved
                                                   column.setPreferredWidth(80);
                                                   rowWidth+=80;
                                                   break;
                                                case 46://DODAGConfigurationDefaultLifetime
                                                   column.setPreferredWidth(80);
                                                   rowWidth+=80;
                                                   break;
                                                case 47://DODAGConfigurationLifetimeUnit
                                                   column.setPreferredWidth(80);
                                                   rowWidth+=80;
                                                   break;
                                                case 48://PrefixInformationType
                                                   column.setPreferredWidth(80);
                                                   rowWidth+=80;
                                                   break;
                                                case 49://PrefixInformationLength
                                                   column.setPreferredWidth(80);
                                                   rowWidth+=80;
                                                case 50://PrefixInformation_PrefixLength
                                                   column.setPreferredWidth(80);
                                                   rowWidth+=80;
                                                   break;
                                                case 51://PrefixInformationFlags
                                                   column.setPreferredWidth(80);
                                                   rowWidth+=80;
                                                   break;
                                                case 52://PrefixInformationValidLifetime
                                                   column.setPreferredWidth(120);
                                                   rowWidth+=120;
                                                   break;
                                                case 53://refixInformationPreferredLifetime
                                                   column.setPreferredWidth(120);
                                                   rowWidth+=120;
                                                   break;
                                                case 54://PrefixInformationReserved
                                                   column.setPreferredWidth(120);
                                                   rowWidth+=120;
                                                   break;
                                                case 55://PrefixInformationDestinationPrefix
                                                   column.setPreferredWidth(350);
                                                   rowWidth+=350;
                                                   break;
                                          	case 56://Payload Field
                                                   int var1 = 50+(((String) row1[0][56]).length())*7;
                                                   column.setPreferredWidth(var1);
                                                   rowWidth+=var1;
                                                   break;
                                          }
                                    }
               } else if (parsedPacket.ICMPType.equals("RouterAdvertisement")){
                     String [] colsNames = {
                                    "<html><center><font face=tahoma size=2>Time (ms)</font>",
                                    "<html><center><font face=tahoma size=2>Frame</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Length</font></html>",
                                    "<html><center><font face=tahoma size=2>Frame control field</font><br><hr size='2'></hr><font face='Courier New' size=2>Type Sec Pnd Ack-req Intra-PAN</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Sequence<br>number</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dest.<br>PAN</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dest.<br>Address</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Src.<br>PAN</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Src.<br>Address</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dispatch</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Encoding</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Context</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Traffic class<br>& Flow label</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Next header</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Hop limit</font></center></html>",
                                    "<html><center><font face=tahoma size=2>6LoWPAN<br>Src. Add.</font></center></html>",
                                    "<html><center><font face=tahoma size=2>6LoWPAN<br>Dest. Add.</font></center></html>",
                                    "<html><center><font face=tahoma size=2>LQI</font></center></html>",
                                    "<html><center><font face=tahoma size=2>MAC Header Length</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Channel</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dispatch<br>ID</font></center></html>",
                                    "<html><center><font face=tahoma size=2>RSSI</font></center></html>",
                                    "<html><center><font face=tahoma size=2>CRC</font></center></html>",
                                    "<html><center><font face=tahoma size=2>ICMP<br>Type</font></center></html>",
                                    "<html><center><font face=tahoma size=2>ICMP<br>Code</font></center></html>",
                                    "<html><center><font face=tahoma size=2>ICMP<br>Cheksum</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Cur.<br>HopLimit</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Flags</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Router<br>Lifetime</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Reachable<br>Time</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Retans<br>Timer</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Prefix<br>InfoOption</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Address<br>IPOption</font></center></html>",
                              	    "<html><center><font face=tahoma size=2>Payload</font></center></html>"};
                                    Object [][] row1 = {{
                                        parsedPacket.sixLoWPANPacket.getTimeStamp().trim(),
                                        "0",
                                        parsedPacket.sixLoWPANPacket.getLength().trim(),
                                        frameCon.trim(),
                                        parsedPacket.sixLoWPANPacket.getSeq().trim(),
                                        parsedPacket.sixLoWPANPacket.getDestPAN().trim(),
                                        parsedPacket.sixLoWPANPacket.getDestADD().trim(),
                                        parsedPacket.sixLoWPANPacket.getSrcPAN().trim(),
                                        parsedPacket.sixLoWPANPacket.getSrcADD().trim(),
                                        parsedPacket.sixLoWPANPacket.getDispatchField().trim(),
                                        parsedPacket.sixLoWPANPacket.getEncodingField().trim(),
                                        parsedPacket.sixLoWPANPacket.getcontextField().trim(),
                                        parsedPacket.sixLoWPANPacket.gettrafficClass_FlowLabelField().trim(),
                                        parsedPacket.sixLoWPANPacket.getNextHeaderField().trim(),
                                        parsedPacket.sixLoWPANPacket.getHopLimitField().trim(),
                                        parsedPacket.sixLoWPANPacket.getSourceAddField().trim(),
                                        parsedPacket.sixLoWPANPacket.getDestAddField().trim(),
                                        parsedPacket.sixLoWPANPacket.getLQI().trim(),
                                        parsedPacket.sixLoWPANPacket.getMhrlen().trim(),
                                        parsedPacket.sixLoWPANPacket.getChannel().trim(),
                                        parsedPacket.sixLoWPANPacket.getDispID().trim(),
                                        parsedPacket.sixLoWPANPacket.getRSSI().trim(),
                                        parsedPacket.sixLoWPANPacket.getCRC().trim(),
                                        parsedPacket.routerAdverHeader.geticmp_Type().trim(),
                                        parsedPacket.routerAdverHeader.geticmp_Code().trim(),
                                        parsedPacket.routerAdverHeader.geticmp_Cheksum().trim(),
                                        parsedPacket.routerAdverHeader.getCurHopLimit().trim(),
                                        parsedPacket.routerAdverHeader.getFlags().trim(),
                                    parsedPacket.routerAdverHeader.getRouterLifetime().trim(),
                                    parsedPacket.routerAdverHeader.getReachableTime().trim(),
                                    parsedPacket.routerAdverHeader.getRetansTimer().trim(),
                                    parsedPacket.routerAdverHeader.getPrefixInfoOption().trim(),
                                    parsedPacket.routerAdverHeader.getAddressIPOption().trim(),
                                        parsedPacket.sixLoWPANPacket.get6LoWPANPayload().trim()
                                    }};
      //System.out.println(parsedPacket.sixLoWPANPacket.gettrafficClass_FlowLabelField().trim());
                                    packetDetail = new JTable(row1,colsNames);
                                    packetDetail.setFont(new Font("Courier New", Font.PLAIN, 10));
                                    packetDetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                                    packetDetail.setAutoscrolls(true);
                                    packetDetail.setColumnSelectionAllowed(false);
                                    packetDetail.setRowSelectionAllowed(true);
                                    // set the width of the fields
                                    for(int i=0;i<34;i++){
                                          TableColumn column = packetDetail.getColumnModel().getColumn(i);
                                          column.setMinWidth(0);
                                          switch(i) {
                                                case 0://Time Stamp Field
                                                   column.setPreferredWidth(110);
                                                   rowWidth+=110;
                                                   break;
                                                case 1://Frame Field
                                                   column.setPreferredWidth(40);
                                                   rowWidth+=40;
                                                   break;
                                                case 2://Length Field
                                                   column.setPreferredWidth(45);
                                                   rowWidth+=45;
                                                   break;
                                                case 3://Frame Control Field
                                                   column.setPreferredWidth(190);
                                                   rowWidth+=190;
                                                   break;
                                                case 4://sequence Field
                                                   column.setPreferredWidth(60);
                                                   rowWidth+=60;
                                                   break;
                                                case 5://Destination PAN Field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 6://Destination Address Field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 7://Source PAN Field
                                                   if(((String) row1[0][7]).equals("")) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(55); rowWidth+=55;}
                                                   break;
                                                case 8://Source Address Field
                                                   if(((String) row1[0][8]).equals("")) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(65); rowWidth+=65;}
                                                   break;
                                                case 9://dispatch field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 10://encoding field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 11://context Field
                                                   if(((String) row1[0][11]).equals("") || ((String) row1[0][11]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(55); rowWidth+=55;}
                                                   break;
                                                case 12://traffic class and flow label
                                                   if(((String) row1[0][12]).equals("") || ((String) row1[0][12]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(150); rowWidth+=150;}
                                                   break;
                                                case 13://Next header Field
                                                   if(((String) row1[0][13]).equals("") || ((String) row1[0][13]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(90); rowWidth+=90;}
                                                   break;
                                                case 14://hop Limit Field
                                                   if(((String) row1[0][14]).equals("") || ((String) row1[0][14]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(90); rowWidth+=90;}
                                                   break;
                                                case 15://6LoWPAN Source Address Field
                                                   if(((String) row1[0][15]).equals("") || ((String) row1[0][15]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {int var = (((String) row1[0][15]).length())*12; column.setPreferredWidth(var); rowWidth+=var;}
                                                   break;
                                                case 16://6LoWPAN destination Address Field
                                                   if(((String) row1[0][16]).equals("") || ((String) row1[0][16]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {int var = (((String) row1[0][16]).length())*12; column.setPreferredWidth(var); rowWidth+=var;}
                                                   break;
                                                case 17://LQI Field
                                                   column.setPreferredWidth(30);
                                                   rowWidth+=30;
                                                   break;
                                                case 18://Mac Header Field
                                                   column.setPreferredWidth(105);
                                                   rowWidth+=105;
                                                   break;
                                                case 19://Channel Field
                                                   column.setPreferredWidth(50);
                                                   rowWidth+=50;
                                                   break;
                                                case 20://Dispatch ID Field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 21://RSSI Field
                                                   column.setPreferredWidth(33);
                                                   rowWidth+=33;
                                                   break;
                                                case 22://CRC Field
                                                   column.setPreferredWidth(30);
                                                   rowWidth+=30;
                                                   break;
                                                case 23://ICMP type
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 24://ICMP code
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 25://ICMP checksum
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 26://CurHopLimit
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 27://Flag
                                                   column.setPreferredWidth(33);
                                                   rowWidth+=33;
                                                   break;
                                                case 28://RouterLifetime
                                                   column.setPreferredWidth(70);
                                                   rowWidth+=70;
                                                   break;
                                                case 29://ReachableTime
                                                   column.setPreferredWidth(200);
                                                   rowWidth+=200;
                                                   break;
                                                case 30://RetansTimer
                                                   column.setPreferredWidth(200);
                                                   rowWidth+=200;
                                                   break;
                                                case 31://PrefixInfoOption
                                                   column.setPreferredWidth(1400);
                                                   rowWidth+=1400;
                                                   break;
                                                case 32://AddressIPOption
                                                   column.setPreferredWidth(600);
                                                   rowWidth+=600;
                                                   break;
                                          	case 33://Payload Field
                                                   int var1 = 50+(((String) row1[0][33]).length())*7;
                                                   column.setPreferredWidth(var1);
                                                   rowWidth+=var1;
                                                   break;
                                          }
                                    }
               } else if (parsedPacket.ICMPType.equals("RouterSolicitation")){
                     String [] colsNames = {
                                    "<html><center><font face=tahoma size=2>Time (ms)</font>",
                                    "<html><center><font face=tahoma size=2>Frame</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Length</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Frame control field</font><br><hr size='2'></hr><font face='Courier New' size=2>Type Sec Pnd Ack-req Intra-PAN</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Sequence<br>number</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dest.<br>PAN</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dest.<br>Address</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Src.<br>PAN</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Src.<br>Address</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dispatch</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Encoding</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Context</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Traffic class<br>& Flow label</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Next header</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Hop limit</font></center></html>",
                                    "<html><center><font face=tahoma size=2>6LoWPAN<br>Src. Add.</font></center></html>",
                                    "<html><center><font face=tahoma size=2>6LoWPAN<br>Dest. Add.</font></center></html>",
                                    "<html><center><font face=tahoma size=2>LQI</font></center></html>",
                                    "<html><center><font face=tahoma size=2>MAC Header Length</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Channel</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dispatch<br>ID</font></center></html>",
                                    "<html><center><font face=tahoma size=2>RSSI</font></center></html>",
                                    "<html><center><font face=tahoma size=2>CRC</font></center></html>",
                                    "<html><center><font face=tahoma size=2>ICMP<br>Type</font></center></html>",
                                    "<html><center><font face=tahoma size=2>ICMP<br>Code</font></center></html>",
                                    "<html><center><font face=tahoma size=2>ICMP<br>Cheksum</font></center></html>",
                              	    "<html><center><font face=tahoma size=2>Payload</font></center></html>"};
                                    Object [][] row1 = {{
                                        parsedPacket.sixLoWPANPacket.getTimeStamp().trim(),
                                        "0",
                                        parsedPacket.sixLoWPANPacket.getLength().trim(),
                                        frameCon.trim(),
                                        parsedPacket.sixLoWPANPacket.getSeq().trim(),
                                        parsedPacket.sixLoWPANPacket.getDestPAN().trim(),
                                        parsedPacket.sixLoWPANPacket.getDestADD().trim(),
                                        parsedPacket.sixLoWPANPacket.getSrcPAN().trim(),
                                        parsedPacket.sixLoWPANPacket.getSrcADD().trim(),
                                        parsedPacket.sixLoWPANPacket.getDispatchField().trim(),
                                        parsedPacket.sixLoWPANPacket.getEncodingField().trim(),
                                        parsedPacket.sixLoWPANPacket.getcontextField().trim(),
                                        parsedPacket.sixLoWPANPacket.gettrafficClass_FlowLabelField().trim(),
                                        parsedPacket.sixLoWPANPacket.getNextHeaderField().trim(),
                                        parsedPacket.sixLoWPANPacket.getHopLimitField().trim(),
                                        parsedPacket.sixLoWPANPacket.getSourceAddField().trim(),
                                        parsedPacket.sixLoWPANPacket.getDestAddField().trim(),
                                        parsedPacket.sixLoWPANPacket.getLQI().trim(),
                                        parsedPacket.sixLoWPANPacket.getMhrlen().trim(),
                                        parsedPacket.sixLoWPANPacket.getChannel().trim(),
                                        parsedPacket.sixLoWPANPacket.getDispID().trim(),
                                        parsedPacket.sixLoWPANPacket.getRSSI().trim(),
                                        parsedPacket.sixLoWPANPacket.getCRC().trim(),
                                        parsedPacket.routerSolicitHeader.geticmp_Type().trim(),
                                        parsedPacket.routerSolicitHeader.geticmp_Code().trim(),
                                        parsedPacket.routerSolicitHeader.geticmp_Cheksum().trim(),
                                        parsedPacket.sixLoWPANPacket.get6LoWPANPayload().trim()
                                    }};
      //System.out.println(parsedPacket.sixLoWPANPacket.gettrafficClass_FlowLabelField().trim());
                                    packetDetail = new JTable(row1,colsNames);
                                    packetDetail.setFont(new Font("Courier New", Font.PLAIN, 10));
                                    packetDetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                                    packetDetail.setAutoscrolls(true);
                                    packetDetail.setColumnSelectionAllowed(false);
                                    packetDetail.setRowSelectionAllowed(true);
                                    // set the width of the fields
                                    for(int i=0;i<27;i++){
                                          TableColumn column = packetDetail.getColumnModel().getColumn(i);
                                          column.setMinWidth(0);
                                          switch(i) {
                                                case 0://Time Stamp Field
                                                   column.setPreferredWidth(110);
                                                   rowWidth+=110;
                                                   break;
                                                case 1://Frame Field
                                                   column.setPreferredWidth(40);
                                                   rowWidth+=40;
                                                   break;
                                                case 2://Length Field
                                                   column.setPreferredWidth(45);
                                                   rowWidth+=45;
                                                   break;
                                                case 3://Frame Control Field
                                                   column.setPreferredWidth(190);
                                                   rowWidth+=190;
                                                   break;
                                                case 4://sequence Field
                                                   column.setPreferredWidth(60);
                                                   rowWidth+=60;
                                                   break;
                                                case 5://Destination PAN Field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 6://Destination Address Field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 7://Source PAN Field
                                                   if(((String) row1[0][7]).equals("")) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(55); rowWidth+=55;}
                                                   break;
                                                case 8://Source Address Field
                                                   if(((String) row1[0][8]).equals("")) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(65); rowWidth+=65;}
                                                   break;
                                                case 9://dispatch field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 10://encoding field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 11://context Field
                                                   if(((String) row1[0][11]).equals("") || ((String) row1[0][11]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(55); rowWidth+=55;}
                                                   break;
                                                case 12://traffic class and flow label
                                                   if(((String) row1[0][12]).equals("") || ((String) row1[0][12]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(150); rowWidth+=150;}
                                                   break;
                                                case 13://Next header Field
                                                   if(((String) row1[0][13]).equals("") || ((String) row1[0][13]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(90); rowWidth+=90;}
                                                   break;
                                                case 14://hop Limit Field
                                                   if(((String) row1[0][14]).equals("") || ((String) row1[0][14]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(90); rowWidth+=90;}
                                                   break;
                                                case 15://6LoWPAN Source Address Field
                                                   if(((String) row1[0][15]).equals("") || ((String) row1[0][15]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {int var = (((String) row1[0][15]).length())*12; column.setPreferredWidth(var); rowWidth+=var;}
                                                   break;
                                                case 16://6LoWPAN destination Address Field
                                                   if(((String) row1[0][16]).equals("") || ((String) row1[0][16]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {int var = (((String) row1[0][16]).length())*12; column.setPreferredWidth(var); rowWidth+=var;}
                                                   break;
                                                case 17://LQI Field
                                                   column.setPreferredWidth(30);
                                                   rowWidth+=30;
                                                   break;
                                                case 18://Mac Header Field
                                                   column.setPreferredWidth(105);
                                                   rowWidth+=105;
                                                   break;
                                                case 19://Channel Field
                                                   column.setPreferredWidth(50);
                                                   rowWidth+=50;
                                                   break;
                                                case 20://Dispatch ID Field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 21://RSSI Field
                                                   column.setPreferredWidth(33);
                                                   rowWidth+=33;
                                                   break;
                                                case 22://CRC Field
                                                   column.setPreferredWidth(30);
                                                   rowWidth+=30;
                                                   break;
                                                case 23://ICMP type
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 24://ICMP code
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 25://ICMP checksum
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                          	case 26://Payload Field
                                                   int var1 = 50+(((String) row1[0][42]).length())*7;
                                                   column.setPreferredWidth(var1);
                                                   rowWidth+=var1;
                                                   break;
                                          }
                                    }
               } else if (parsedPacket.ICMPType.equals("DIS")){
                     String [] colsNames = {
                                    "<html><center><font face=tahoma size=2>Time (ms)</font>",
                                    "<html><center><font face=tahoma size=2>Frame</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Length</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Frame control field</font><br><hr size='2'></hr><font face='Courier New' size=2>Type Sec Pnd Ack-req Intra-PAN</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Sequence<br>number</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dest.<br>PAN</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dest.<br>Address</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Src.<br>PAN</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Src.<br>Address</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dispatch</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Encoding</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Context</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Traffic class<br>& Flow label</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Next header</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Hop limit</font></center></html>",
                                    "<html><center><font face=tahoma size=2>6LoWPAN<br>Src. Add.</font></center></html>",
                                    "<html><center><font face=tahoma size=2>6LoWPAN<br>Dest. Add.</font></center></html>",
                                    "<html><center><font face=tahoma size=2>LQI</font></center></html>",
                                    "<html><center><font face=tahoma size=2>MAC Header Length</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Channel</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dispatch<br>ID</font></center></html>",
                                    "<html><center><font face=tahoma size=2>RSSI</font></center></html>",
                                    "<html><center><font face=tahoma size=2>CRC</font></center></html>",
                                    "<html><center><font face=tahoma size=2>ICMP<br>Type</font></center></html>",
                                    "<html><center><font face=tahoma size=2>ICMP<br>Code</font></center></html>",
                                    "<html><center><font face=tahoma size=2>ICMP<br>Cheksum</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Flags</font></center></html>",
                                    "<html><center><font face=tahoma size=2>ICMP<br>Reserved</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Solicited<br>Info Type</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Solicited<br>Info Length</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Solicited<br>Info Instance</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Solicited<br>Info Flags</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Solicited<br>Info DODAGID</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Solicited<br>Info Version</font></center></html>",
                              	    "<html><center><font face=tahoma size=2>Payload</font></center></html>"};
                                    Object [][] row1 = {{
                                        parsedPacket.sixLoWPANPacket.getTimeStamp().trim(),
                                        "0",
                                        parsedPacket.sixLoWPANPacket.getLength().trim(),
                                        frameCon.trim(),
                                        parsedPacket.sixLoWPANPacket.getSeq().trim(),
                                        parsedPacket.sixLoWPANPacket.getDestPAN().trim(),
                                        parsedPacket.sixLoWPANPacket.getDestADD().trim(),
                                        parsedPacket.sixLoWPANPacket.getSrcPAN().trim(),
                                        parsedPacket.sixLoWPANPacket.getSrcADD().trim(),
                                        parsedPacket.sixLoWPANPacket.getDispatchField().trim(),
                                        parsedPacket.sixLoWPANPacket.getEncodingField().trim(),
                                        parsedPacket.sixLoWPANPacket.getcontextField().trim(),
                                        parsedPacket.sixLoWPANPacket.gettrafficClass_FlowLabelField().trim(),
                                        parsedPacket.sixLoWPANPacket.getNextHeaderField().trim(),
                                        parsedPacket.sixLoWPANPacket.getHopLimitField().trim(),
                                        parsedPacket.sixLoWPANPacket.getSourceAddField().trim(),
                                        parsedPacket.sixLoWPANPacket.getDestAddField().trim(),
                                        parsedPacket.sixLoWPANPacket.getLQI().trim(),
                                        parsedPacket.sixLoWPANPacket.getMhrlen().trim(),
                                        parsedPacket.sixLoWPANPacket.getChannel().trim(),
                                        parsedPacket.sixLoWPANPacket.getDispID().trim(),
                                        parsedPacket.sixLoWPANPacket.getRSSI().trim(),
                                        parsedPacket.sixLoWPANPacket.getCRC().trim(),
                                        parsedPacket.disHeader.geticmp_Type().trim(),
                                        parsedPacket.disHeader.geticmp_Code().trim(),
                                        parsedPacket.disHeader.geticmp_Cheksum().trim(),
                                        parsedPacket.disHeader.getFlags().trim(),
                                        parsedPacket.disHeader.getReserved_icmp().trim(),
                                        parsedPacket.disHeader.getSolicitedInformationType().trim(),
                                        parsedPacket.disHeader.getSolicitedInformationLength().trim(),
                                        parsedPacket.disHeader.getSolicitedInformationInstance().trim(),
                                        parsedPacket.disHeader.getSolicitedInformationFlags().trim(),
                                        parsedPacket.disHeader.getSolicitedInformationDODAGID().trim(),
                                        parsedPacket.disHeader.getSolicitedInformationVersion().trim(),
                                        parsedPacket.sixLoWPANPacket.get6LoWPANPayload().trim()
                                    }};
      //System.out.println(parsedPacket.sixLoWPANPacket.gettrafficClass_FlowLabelField().trim());
                                    packetDetail = new JTable(row1,colsNames);
                                    packetDetail.setFont(new Font("Courier New", Font.PLAIN, 10));
                                    packetDetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                                    packetDetail.setAutoscrolls(true);
                                    packetDetail.setColumnSelectionAllowed(false);
                                    packetDetail.setRowSelectionAllowed(true);
                                    // set the width of the fields
                                    for(int i=0;i<35;i++){
                                          TableColumn column = packetDetail.getColumnModel().getColumn(i);
                                          column.setMinWidth(0);
                                          switch(i) {
                                                case 0://Time Stamp Field
                                                   column.setPreferredWidth(110);
                                                   rowWidth+=110;
                                                   break;
                                                case 1://Frame Field
                                                   column.setPreferredWidth(40);
                                                   rowWidth+=40;
                                                   break;
                                                case 2://Length Field
                                                   column.setPreferredWidth(45);
                                                   rowWidth+=45;
                                                   break;
                                                case 3://Frame Control Field
                                                   column.setPreferredWidth(190);
                                                   rowWidth+=190;
                                                   break;
                                                case 4://sequence Field
                                                   column.setPreferredWidth(60);
                                                   rowWidth+=60;
                                                   break;
                                                case 5://Destination PAN Field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 6://Destination Address Field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 7://Source PAN Field
                                                   if(((String) row1[0][7]).equals("")) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(55); rowWidth+=55;}
                                                   break;
                                                case 8://Source Address Field
                                                   if(((String) row1[0][8]).equals("")) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(65); rowWidth+=65;}
                                                   break;
                                                case 9://dispatch field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 10://encoding field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 11://context Field
                                                   if(((String) row1[0][11]).equals("") || ((String) row1[0][11]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(55); rowWidth+=55;}
                                                   break;
                                                case 12://traffic class and flow label
                                                   if(((String) row1[0][12]).equals("") || ((String) row1[0][12]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(150); rowWidth+=150;}
                                                   break;
                                                case 13://Next header Field
                                                   if(((String) row1[0][13]).equals("") || ((String) row1[0][13]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(90); rowWidth+=90;}
                                                   break;
                                                case 14://hop Limit Field
                                                   if(((String) row1[0][14]).equals("") || ((String) row1[0][14]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(90); rowWidth+=90;}
                                                   break;
                                                case 15://6LoWPAN Source Address Field
                                                   if(((String) row1[0][15]).equals("") || ((String) row1[0][15]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {int var = (((String) row1[0][15]).length())*12; column.setPreferredWidth(var); rowWidth+=var;}
                                                   break;
                                                case 16://6LoWPAN destination Address Field
                                                   if(((String) row1[0][16]).equals("") || ((String) row1[0][16]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {int var = (((String) row1[0][16]).length())*12; column.setPreferredWidth(var); rowWidth+=var;}
                                                   break;
                                                case 17://LQI Field
                                                   column.setPreferredWidth(30);
                                                   rowWidth+=30;
                                                   break;
                                                case 18://Mac Header Field
                                                   column.setPreferredWidth(105);
                                                   rowWidth+=105;
                                                   break;
                                                case 19://Channel Field
                                                   column.setPreferredWidth(50);
                                                   rowWidth+=50;
                                                   break;
                                                case 20://Dispatch ID Field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 21://RSSI Field
                                                   column.setPreferredWidth(33);
                                                   rowWidth+=33;
                                                   break;
                                                case 22://CRC Field
                                                   column.setPreferredWidth(30);
                                                   rowWidth+=30;
                                                   break;
                                                case 23://ICMP type
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 24://ICMP code
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 25://ICMP checksum
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 26://Flag
                                                   column.setPreferredWidth(30);
                                                   rowWidth+=30;
                                                   break;
                                                case 27://Reserved_icmp
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 28://SolicitedInformationType
                                                   column.setPreferredWidth(70);
                                                   rowWidth+=70;
                                                   break;
                                                case 29://SolicitedInformationLength
                                                   column.setPreferredWidth(70);
                                                   rowWidth+=70;
                                                   break;
                                                case 30://SolicitedInformationInstance
                                                   column.setPreferredWidth(70);
                                                   rowWidth+=70;
                                                   break;
                                                case 31://SolicitedInformationFlags
                                                   column.setPreferredWidth(70);
                                                   rowWidth+=70;
                                                   break;
                                                case 32://SolicitedInformationDODAGID
                                                   column.setPreferredWidth(350);
                                                   rowWidth+=350;
                                                   break;
                                                case 33://SolicitedInformationVersion
                                                   column.setPreferredWidth(70);
                                                   rowWidth+=70;
                                                   break;
                                          	case 34://Payload Field
                                                   int var1 = 50+(((String) row1[0][34]).length())*7;
                                                   column.setPreferredWidth(var1);
                                                   rowWidth+=var1;
                                                   break;
                                          }
                                    }
               } else {
			   //System.out.println("Does not match any ICMP");
                     String [] colsNames = {
                                    "<html><center><font face=tahoma size=2>Time (ms)</font>",
                                    "<html><center><font face=tahoma size=2>Frame</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Length</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Frame control field</font><br><hr size='2'></hr><font face='Courier New' size=2>Type Sec Pnd Ack-req Intra-PAN</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Sequence<br>number</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dest.<br>PAN</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dest.<br>Address</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Src.<br>PAN</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Src.<br>Address</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dispatch</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Encoding</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Context</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Traffic class<br>& Flow label</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Next header</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Hop limit</font></center></html>",
                                    "<html><center><font face=tahoma size=2>6LoWPAN<br>Src. Add.</font></center></html>",
                                    "<html><center><font face=tahoma size=2>6LoWPAN<br>Dest. Add.</font></center></html>",
                                    "<html><center><font face=tahoma size=2>LQI</font></center></html>",
                                    "<html><center><font face=tahoma size=2>MAC Header Length</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Channel</font></center></html>",
                                    "<html><center><font face=tahoma size=2>Dispatch<br>ID</font></center></html>",
                                    "<html><center><font face=tahoma size=2>RSSI</font></center></html>",
                                    "<html><center><font face=tahoma size=2>CRC</font></center></html>",
                                    "<html><center><font face=tahoma size=2>ICMP Type</font></center></html>",
                              	    "<html><center><font face=tahoma size=2>Payload</font></center></html>"};
                                    Object [][] row1 = {{
                                        parsedPacket.sixLoWPANPacket.getTimeStamp().trim(),
                                        "0",
                                        parsedPacket.sixLoWPANPacket.getLength().trim(),
                                        frameCon.trim(),
                                        parsedPacket.sixLoWPANPacket.getSeq().trim(),
                                        parsedPacket.sixLoWPANPacket.getDestPAN().trim(),
                                        parsedPacket.sixLoWPANPacket.getDestADD().trim(),
                                        parsedPacket.sixLoWPANPacket.getSrcPAN().trim(),
                                        parsedPacket.sixLoWPANPacket.getSrcADD().trim(),
                                        parsedPacket.sixLoWPANPacket.getDispatchField().trim(),
                                        parsedPacket.sixLoWPANPacket.getEncodingField().trim(),
                                        parsedPacket.sixLoWPANPacket.getcontextField().trim(),
                                        parsedPacket.sixLoWPANPacket.gettrafficClass_FlowLabelField().trim(),
                                        parsedPacket.sixLoWPANPacket.getNextHeaderField().trim(),
                                        parsedPacket.sixLoWPANPacket.getHopLimitField().trim(),
                                        parsedPacket.sixLoWPANPacket.getSourceAddField().trim(),
                                        parsedPacket.sixLoWPANPacket.getDestAddField().trim(),
                                        parsedPacket.sixLoWPANPacket.getLQI().trim(),
                                        parsedPacket.sixLoWPANPacket.getMhrlen().trim(),
                                        parsedPacket.sixLoWPANPacket.getChannel().trim(),
                                        parsedPacket.sixLoWPANPacket.getDispID().trim(),
                                        parsedPacket.sixLoWPANPacket.getRSSI().trim(),
                                        parsedPacket.sixLoWPANPacket.getCRC().trim(),
                                        "",
                                        parsedPacket.sixLoWPANPacket.get6LoWPANPayload().trim()
                                    }};
      //System.out.println(parsedPacket.sixLoWPANPacket.gettrafficClass_FlowLabelField().trim());
                                    packetDetail = new JTable(row1,colsNames);
                                    packetDetail.setFont(new Font("Courier New", Font.PLAIN, 10));
                                    packetDetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                                    packetDetail.setAutoscrolls(true);
                                    packetDetail.setColumnSelectionAllowed(false);
                                    packetDetail.setRowSelectionAllowed(true);
                                    // set the width of the fields
                                    for(int i=0;i<25;i++){
                                          TableColumn column = packetDetail.getColumnModel().getColumn(i);
                                          column.setMinWidth(0);
                                          switch(i) {
                                                case 0://Time Stamp Field
                                                   column.setPreferredWidth(110);
                                                   rowWidth+=110;
                                                   break;
                                                case 1://Frame Field
                                                   column.setPreferredWidth(40);
                                                   rowWidth+=40;
                                                   break;
                                                case 2://Length Field
                                                   column.setPreferredWidth(45);
                                                   rowWidth+=45;
                                                   break;
                                                case 3://Frame Control Field
                                                   column.setPreferredWidth(190);
                                                   rowWidth+=190;
                                                   break;
                                                case 4://sequence Field
                                                   column.setPreferredWidth(60);
                                                   rowWidth+=60;
                                                   break;
                                                case 5://Destination PAN Field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 6://Destination Address Field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 7://Source PAN Field
                                                   if(((String) row1[0][7]).equals("")) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(55); rowWidth+=55;}
                                                   break;
                                                case 8://Source Address Field
                                                   if(((String) row1[0][8]).equals("")) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(65); rowWidth+=65;}
                                                   break;
                                                case 9://dispatch field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 10://encoding field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 11://context Field
                                                   if(((String) row1[0][11]).equals("") || ((String) row1[0][11]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(55); rowWidth+=55;}
                                                   break;
                                                case 12://traffic class and flow label
                                                   if(((String) row1[0][12]).equals("") || ((String) row1[0][12]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(150); rowWidth+=150;}
                                                   break;
                                                case 13://Next header Field
                                                   if(((String) row1[0][13]).equals("") || ((String) row1[0][13]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(90); rowWidth+=90;}
                                                   break;
                                                case 14://hop Limit Field
                                                   if(((String) row1[0][14]).equals("") || ((String) row1[0][14]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {column.setPreferredWidth(90); rowWidth+=90;}
                                                   break;
                                                case 15://6LoWPAN Source Address Field
                                                   if(((String) row1[0][15]).equals("") || ((String) row1[0][15]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {int var = (((String) row1[0][15]).length())*12; column.setPreferredWidth(var); rowWidth+=var;}
                                                   break;
                                                case 16://6LoWPAN destination Address Field
                                                   if(((String) row1[0][16]).equals("") || ((String) row1[0][16]).equals("compressed") ) column.setPreferredWidth(0);
                                                   else {int var = (((String) row1[0][16]).length())*12; column.setPreferredWidth(var); rowWidth+=var;}
                                                   break;
                                                case 17://LQI Field
                                                   column.setPreferredWidth(30);
                                                   rowWidth+=30;
                                                   break;
                                                case 18://Mac Header Field
                                                   column.setPreferredWidth(105);
                                                   rowWidth+=105;
                                                   break;
                                                case 19://Channel Field
                                                   column.setPreferredWidth(50);
                                                   rowWidth+=50;
                                                   break;
                                                case 20://Dispatch ID Field
                                                   column.setPreferredWidth(55);
                                                   rowWidth+=55;
                                                   break;
                                                case 21://RSSI Field
                                                   column.setPreferredWidth(33);
                                                   rowWidth+=33;
                                                   break;
                                                case 22://CRC Field
                                                   column.setPreferredWidth(30);
                                                   rowWidth+=30;
                                                   break;
                                                case 23://ICMP Type
                                                   column.setPreferredWidth(0);
                                                   rowWidth+=0;
                                                   break;
                                          	case 24://Payload Field
                                                   int var1 = 50+(((String) row1[0][24]).length())*7;
                                                   column.setPreferredWidth(var1);
                                                   rowWidth+=var1;
                                                   break;
                                          }
                                    }
			   }
               
         }
               //---------End of Data frame---------//

         //------------Acknowledgement frame------------//
         else if(parsedPacket.sixLoWPANPacket.getPacketType()==2)
         {
                  String[] colsNames = {
                          "<html><center><font face=tahoma size=2>Time (ms)</font>",
                          "<html><center><font face=tahoma size=2>Frame</font></center></html>",
                          "<html><center><font face=tahoma size=2>Length</font></center></html>",
                          "<html><center><font face=tahoma size=2>Frame control field</font><br><hr bgcolor='black' size=2><font face='Courier New' size=2>    Type Sec Pnd Ack-req Intra-PAN</font></html>",
                          "<html><center><font face=tahoma size=2>Sequence<br>number</font></center></html>",
                          "<html><center><font face=tahoma size=2>LQI</font></center></html>",
                          "<html><center><center><font face=tahoma size=2>MAC Header Length</font></center></html>",
                          "<html><center><font face=tahoma size=2>Channel</font></center></html>",
                          "<html><center><center><font face=tahoma size=2>Dispatch<br>ID</font></center></html>",
                          "<html><center><font face=tahoma size=2>RSSI</font></center></html>",
                          "<html><center><font face=tahoma size=2>CRC</font></center></html>"};
                          Object [][] row = {{
                                      parsedPacket.sixLoWPANPacket.getTimeStamp().trim(),
                                      "0",
                                      parsedPacket.sixLoWPANPacket.getLength().trim(),
                                      frameCon.trim(),
                                      parsedPacket.sixLoWPANPacket.getSeq().trim(),
                                      parsedPacket.sixLoWPANPacket.getLQI().trim(),
                                      parsedPacket.sixLoWPANPacket.getMhrlen().trim(),
                                      parsedPacket.sixLoWPANPacket.getChannel().trim(),
                                      parsedPacket.sixLoWPANPacket.getDispID().trim(),
                                      parsedPacket.sixLoWPANPacket.getRSSI().trim(),
                                      parsedPacket.sixLoWPANPacket.getCRC().trim()
                          }};
                          packetDetail = new JTable(row,colsNames);
                          packetDetail.setFont(new Font("Courier New", Font.PLAIN, 10));
                          packetDetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                          packetDetail.setAutoscrolls(true);
                          packetDetail.setColumnSelectionAllowed(false);
                          packetDetail.setRowSelectionAllowed(true);
                          // set the width of the fields
                          for(int i=0;i<11;i++)
                          {
                             TableColumn column = packetDetail.getColumnModel().getColumn(i);
                             switch(i) {
                                case 0://Time Stamp Field
                                   column.setPreferredWidth(110);
                                             rowWidth+=110;
                                   break;
                                case 1://Frame Field
                                   column.setPreferredWidth(40);
                                             rowWidth+=40;
                                   break;
                                case 2://Length Field
                                   column.setPreferredWidth(45);
                                             rowWidth+=45;
                                   break;
                                case 3://Frame Control Field
                                   column.setPreferredWidth(210);
                                             rowWidth+=210;
                                   break;
                                case 4://sequence Field
                                   column.setPreferredWidth(60);
                                             rowWidth+=60;
                                   break;
                                case 5://LQI Field
                                   column.setPreferredWidth(30);
                                             rowWidth+=30;
                                   break;
                                case 6://Mac Header Field
                                   column.setPreferredWidth(105);
                                             rowWidth+=105;
                                   break;
                                case 7://Channel Field
                                   column.setPreferredWidth(50);
                                             rowWidth+=50;
                                   break;
                                case 8://Dispatch ID Field
                                   column.setPreferredWidth(55);
                                             rowWidth+=55;
                                   break;
                                case 9://RSSI Field
                                   column.setPreferredWidth(33);
                                             rowWidth+=33;
                                   break;
                                case 10://CRC Field
                                   column.setPreferredWidth(30);
                                             rowWidth+=30;
                                   break;
                             }
                          }
         }
         //------------command frame------------//
         else if(parsedPacket.sixLoWPANPacket.getPacketType()==3)
         {
               short IEEECase = new parser().IEEE802_15_4Case(parsedPacket.sixLoWPANPacket.getDestAddMode(),parsedPacket.sixLoWPANPacket.getSrcAddMode(), parsedPacket.sixLoWPANPacket.getIntra_PAN());
               String [] colsNames = {
                              "<html><center><font face=tahoma size=2>Time (ms)</font>",
                              "<html><center><font face=tahoma size=2>Frame</font></center></html>",
                              "<html><center><font face=tahoma size=2>Length</font></center></html>",
                              "<html><center><font face=tahoma size=2>Frame control field</font><br><hr size='2'></hr><font face='Courier New' size=2>Type Sec Pnd Ack-req Intra-PAN</font></html>",
                              "<html><center><font face=tahoma size=2>Sequence<br>number</font></center></html>",
                              "<html><center><font face=tahoma size=2>Dest.<br>PAN</font></center></html>",
                              "<html><center><font face=tahoma size=2>Dest.<br>Address</font></center></html>",
                              "<html><center><font face=tahoma size=2>Src.<br>PAN</font></center></html>",
                              "<html><center><font face=tahoma size=2>Src.<br>Address</font></center></html>",
                              "<html><center><font face=tahoma size=2>Command Payload</font></center></html>",
                              "<html><center><font face=tahoma size=2>LQI</font></center></html>",
                              "<html><center><font face=tahoma size=2>MAC Header Length</font></center></html>",
                              "<html><center><font face=tahoma size=2>Channel</font></center></html>",
                              "<html><center><font face=tahoma size=2>Dispatch<br>ID</font></center></html>",
                              "<html><center><font face=tahoma size=2>RSSI</font></center></html>",
                              "<html><center><font face=tahoma size=2>CRC</font></center></html>"};
                              Object [][] row1 = {{
                                  parsedPacket.sixLoWPANPacket.getTimeStamp().trim(),
                                  "0",
                                  parsedPacket.sixLoWPANPacket.getLength().trim(),
                                  frameCon.trim(),
                                  parsedPacket.sixLoWPANPacket.getSeq().trim(),
                                  parsedPacket.sixLoWPANPacket.getDestPAN().trim(),
                                  parsedPacket.sixLoWPANPacket.getDestADD().trim(),
                                  parsedPacket.sixLoWPANPacket.getSrcPAN().trim(),
                                  parsedPacket.sixLoWPANPacket.getSrcADD().trim(),
                                  parsedPacket.sixLoWPANPacket.getPayload().trim(),
                                  parsedPacket.sixLoWPANPacket.getLQI().trim(),
                                  parsedPacket.sixLoWPANPacket.getMhrlen().trim(),
                                  parsedPacket.sixLoWPANPacket.getChannel().trim(),
                                  parsedPacket.sixLoWPANPacket.getDispID().trim(),
                                  parsedPacket.sixLoWPANPacket.getRSSI().trim(),
                                  parsedPacket.sixLoWPANPacket.getCRC().trim()
                              }};
                              packetDetail = new JTable(row1,colsNames);
                              packetDetail.setFont(new Font("Courier New", Font.PLAIN, 10));
                              packetDetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                              packetDetail.setAutoscrolls(true);
                              packetDetail.setColumnSelectionAllowed(false);
                              packetDetail.setRowSelectionAllowed(true);
                              // set the width of the fields
                              for(int i=0;i<16;i++){
                                    TableColumn column = packetDetail.getColumnModel().getColumn(i);
                                    column.setMinWidth(0);
                                    switch(i) {
                                          case 0://Time Stamp Field
                                             column.setPreferredWidth(110);
                                             rowWidth+=110;
                                             break;
                                          case 1://Frame Field
                                             column.setPreferredWidth(50);
                                             rowWidth+=50;
                                             break;
                                          case 2://Length Field
                                             column.setPreferredWidth(55);
                                             rowWidth+=55;
                                             break;
                                          case 3://Frame Control Field
                                             column.setPreferredWidth(200);
                                             rowWidth+=200;
                                             break;
                                          case 4://sequence Field
                                             column.setPreferredWidth(70);
                                             rowWidth+=70;
                                             break;
                                          case 5://Destination PAN Field
                                             column.setPreferredWidth(60);
                                             rowWidth+=60;
                                             break;
                                          case 6://Destination Address Field
                                             column.setPreferredWidth(60);
                                             rowWidth+=60;
                                             break;
                                          case 7://Source PAN Field
                                             if(((String) row1[0][7]).equals("")) column.setPreferredWidth(0);
                                             else {column.setPreferredWidth(60); rowWidth+=60;}
                                             break;
                                          case 8://Source Address Field
                                             if(((String) row1[0][8]).equals("")) column.setPreferredWidth(0);
                                             else {column.setPreferredWidth(90); rowWidth+=90;}
                                             break;
                                          case 9://Payload Field
                                             column.setPreferredWidth(1500);
                                             rowWidth+=1500;
                                             break;
                                          case 10://LQI Field
                                             column.setPreferredWidth(30);
                                             rowWidth+=30;
                                             break;
                                          case 11://Mac Header Field
                                             column.setPreferredWidth(100);
                                             rowWidth+=100;
                                             break;
                                          case 12://Channel Field
                                             column.setPreferredWidth(50);
                                             rowWidth+=50;
                                             break;
                                          case 13://Dispatch ID Field
                                             column.setPreferredWidth(55);
                                             rowWidth+=55;
                                             break;
                                          case 14://RSSI Field
                                             column.setPreferredWidth(33);
                                             rowWidth+=33;
                                             break;
                                          case 15://CRC Field
                                             column.setPreferredWidth(30);
                                             rowWidth+=30;
                                             break;
                                    }
                              }
               //---------End of command frame---------//
     
               }         
         
         ZM.rowWidth=rowWidth;
         return packetDetail;
   }
   //-------------------End of ShowPacketsInTable function-------------------//

   /***************************************************************************/
   /* Function: ShowPacketAnalyser                                            */
   /* Arguments : JTable: packet in JTable format                             */
   /*             IEEE802_15_4 : parsed packet                                */
   /*             String : the protocol that used in packet                   */
   /*             int : the number of packet                                  */
   /*             double : Arrival time in second                             */
   /* Return: Object [] : which contain some information about the packet     */
   /*                     that shown in Packet Analyser tab                   */
   /* function store some info about the packet in a row to display it in     */
   /* Packet Analyser tab                                                     */
   /***************************************************************************/
   public Object [] ShowPacketAnalyser (JTable packet, IEEE802_15_4 parsedPacket, String protocol, int j, double displaySecond){

         short IEEECase = new parser().IEEE802_15_4Case(parsedPacket.getDestAddMode(),parsedPacket.getSrcAddMode(), parsedPacket.getIntra_PAN());
         Object [] row = {};
         //--------------Beacon frame--------------//
         if (parsedPacket.getPacketType()==0)
         {
                      Object [] row5 ={j,NtpMessage.timestampToString(displaySecond),parsedPacket.getSrcADD().trim(),parsedPacket.getDestADD().trim(),"Beacon","","BCN"};
                      row = row5;
         }
         //--------------Data frame--------------//
         else if (parsedPacket.getPacketType()==1){
                  Object [] row5 ={j,NtpMessage.timestampToString(displaySecond),parsedPacket.getSrcADD().trim(),parsedPacket.getDestADD().trim(),"Data","","DATA"};
                  row = row5;
         }
         //----------Acknowledgement frame----------//
         else if (parsedPacket.getPacketType()==2){
                  Object [] row5 ={j,NtpMessage.timestampToString(displaySecond)," "," ","Ack","","ACK"};
                  row = row5;
         }
         //----------Command frame----------//
         else if (parsedPacket.getPacketType()==3){
                  Object [] row5 ={j,NtpMessage.timestampToString(displaySecond),parsedPacket.getSrcADD().trim(),parsedPacket.getDestADD().trim(),"Command","","CMD"};
                  row = row5;
         }
         return row;
   }
   //-------------------End of ShowPacketAnalyser function-------------------//

   /***************************************************************************/
   /* Function: ShowPacketAnalyser                                            */
   /* Arguments : JTable: packet in JTable format                             */
   /*             sixLoWPAN : parsed packet                                   */
   /*             String : the protocol that used in packet                   */
   /*             int : the number of packet                                  */
   /*             double : Arrival time in second                             */
   /* Return: Object [] : which contain some information about the packet     */
   /*                     that shown in Packet Analyser tab                   */
   /* function store some info about the packet in a row to display it in     */
   /* Packet Analyser tab                                                     */
   /***************************************************************************/
   public Object [] ShowPacketAnalyser (JTable packet, sixLoWPAN parsedPacket, String protocol, int j, double displaySecond){

         short IEEECase = new parser().IEEE802_15_4Case(parsedPacket.getDestAddMode(),parsedPacket.getSrcAddMode(), parsedPacket.getIntra_PAN());
         Object [] row = {};
         //--------------Beacon frame--------------//
         if (parsedPacket.getPacketType()==0)
         {
                      Object [] row5 ={j,NtpMessage.timestampToString(displaySecond),parsedPacket.getSrcADD().trim(),parsedPacket.getDestADD().trim(),"Beacon",Classes.Zmonitor.protocol,"BCN"};
                      row = row5;
         }
         //--------------Data frame--------------//
         else if (parsedPacket.getPacketType()==1){
                  Object [] row5 ={j,NtpMessage.timestampToString(displaySecond),parsedPacket.getSrcADD().trim(),parsedPacket.getDestADD().trim(),"Data",Classes.Zmonitor.protocol,parsedPacket.get6LoWPANType()};
                  row = row5;
         }
         //----------Acknowledgement frame----------//
         else if (parsedPacket.getPacketType()==2){
                  Object [] row5 ={j,NtpMessage.timestampToString(displaySecond)," "," ","Ack",Classes.Zmonitor.protocol,"ACK"};
                  row = row5;
         }
         //----------Command frame----------//
         else if (parsedPacket.getPacketType()==3){
                  Object [] row5 ={j,NtpMessage.timestampToString(displaySecond),parsedPacket.getSrcADD().trim(),parsedPacket.getDestADD().trim(),"Command",Classes.Zmonitor.protocol,"CMD"};
                  row = row5;
         }
         return row;
   }
   //-------------------End of ShowPacketAnalyser function-------------------//

   /***************************************************************************/
   /* Function: setPacketDesign                                               */
   /* Arguments : JTable: packet in JTable format                             */
   /*             long : time between packets                                 */
   /*             long : Arrival time in second                               */
   /*             int : the number of packet                                  */
   /*             short : type of the packet                                  */
   /*             int : width of the panel                                    */
   /* Return: JTable : which contain the same JTable packet in a new design   */
   /***************************************************************************/
   public JTable setPacketDesign (JTable packet, double timeBetweenPackets, double displayTime, int j, final short packetType, int width){
         // modify on the table header adding timeBetweenPackets
         packet.getColumnModel().getColumn(0).setHeaderValue(packet.getModel().getColumnName(0).toString()+"<br><hr size='2' bgcolor='black'></hr><font face=tahoma size=2>+"+timeBetweenPackets+"</font></center></html>");
         // modify on the table cell adding displayTime
String ArriveTime = NtpMessage.timestampToString(displayTime);
         packet.setValueAt("="+(ArriveTime.substring(11,ArriveTime.length())), 0, 0);
         // set packet number
         packet.setValueAt(j+"",0,1);
         //**************** Adjust the header design for table packet ********************//
         JTableHeader header = packet.getTableHeader();
         final TableCellRenderer headerRenderer = header.getDefaultRenderer();

         header.setDefaultRenderer( new TableCellRenderer() {
               public Component getTableCellRendererComponent( JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column ) {
                  Component comp = headerRenderer.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, column );
                  String columnName=new ConvertImpl().removeHtmlTags(table.getModel().getColumnName(column));
                  String sub = "";
                  String sub2 = "";
                  try{
			sub = columnName.substring(0,9);
			sub2 = columnName.substring(0,4);
		  }catch(Exception e){}
                      if(sub.equals("Time (ms)") || columnName.equals("GTS") || columnName.equals("LQI") || columnName.equals("RSSI") || columnName.equals("CRC") || columnName.equals ("Header Length") || columnName.equals("Channel") || columnName.equals("Code") || columnName.equals("Cheksum")  || sub.equals("MAC Heade") || columnName.equals("Type") )
                              comp.setBackground(new Color(194, 223, 255));
                      else if (columnName.equals("DispatchID") || columnName.equals("Length") || sub.equals("Frame con") || sub2.equals("Sequ") || sub.equals("Destinati") || sub2.equals("Sour") || sub.equals("Super fra") || columnName.equals("FCS") || sub2.equals("Dest") || columnName.equals("Dest.PAN") || sub2.equals("Src."))
                              comp.setBackground(new Color(240, 239, 239));
                      else if (columnName.equals("Dispatch") || columnName.equals("Encoding") || sub.equals("Next head") || columnName.equals("Hop limit") || sub2.equals("6LoW") || columnName.equals("Context") || sub.equals("Traffic c") )
                              comp.setBackground(new Color(204, 251, 93));
                    else if (columnName.equals("Payload") || columnName.equals("Frame"))  comp.setBackground(new Color(255, 200, 0));
                    else
                              comp.setBackground(Color.WHITE);
                  TableColumn tm = table.getColumnModel().getColumn(column);
                  tm.setCellRenderer(new CellColoring(packetType));
                  return comp;
               }
         });

         packet.setColumnSelectionAllowed(false);
         packet.setRowSelectionAllowed(true);
         packet.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
         packet.getTableHeader().setPreferredSize(new Dimension(width-30, 50));
         return packet;
   }
   //---------------------End of setPacketDesign function---------------------//
} //End of the class
