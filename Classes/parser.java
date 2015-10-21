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
 * Class parser decode the packet from different type. First it determines
 * frame type (beacon, payload, acknowledgement, MAC command). After that it
 * divides the frame depend on its type and determines the case which it belongs
 * to (We use object from 6LoWPAN class to store the fields of the frame). Then
 * store the divided frame into Zbuffer.
 */

import Classes.Packet.*;
import Classes.Support.*;
import java.util.ArrayList;
import Classes.Zmonitor;

public class parser{

      //Class Attribute
      //private static short sixLoWPAN_type;
      private static sixLoWPAN sixLoWPANParsedPacket; //used to divide the packet
      private static IEEE802_15_4 IEEE802_15_4ParsedPacket;
      private static ICMPStruct ICMPParsedPacket;
      public static int IEEE_header_length;
      public static int sixLOWPAN_header_length;
      public static int icmp_header_length;

      //Constructor
      public parser(){
            sixLoWPANParsedPacket = new sixLoWPANImpl();
            IEEE802_15_4ParsedPacket = new IEEE802_15_4Impl();
			ICMPParsedPacket = new ICMPStruct();
			IEEE_header_length=0;
			icmp_header_length=0;
            //sixLoWPAN_type = -1;
      }

      /***********************************************************/
      /* Function: IEEE802_15_4Type                       		 */
      /* Arguments : String: received packet stream        		 */
      /*	     This argument represent the original packet     */
      /* Return: Nothing										 */
      /* function set the type of the packet      		         */
      /***********************************************************/
      public static short IEEE802_15_4Type(String receivedPacketStream) {
            		String frameControl = receivedPacketStream.substring(5,11);
                  String PacketsType = frameControl.substring(2,3);
                  short frameType = -1;
                  //beacon frame
                  if(PacketsType.equals("0"))
                        frameType=0;
                  //payload frame
                  else if (PacketsType.equals("1"))
                        frameType=1;
                  //acknowledgement frame
                  else if(PacketsType.equals("2"))
                        frameType=2;
                  //command frame
                  else if(PacketsType.equals("3"))
                        frameType=3;

                  return frameType;
      }

      /***************************************************************/
      /* Function: IEEE802_15_4Case                                  */
      /* Arguments : String: DestAddMode & Intra_PAN                 */
      /*             this argument used to know the case of packet   */
      /* Return: short: determine the case                           */
      /* function return packet's case                               */
      /***************************************************************/
      public static short IEEE802_15_4Case(short DestAddMode,short SrcAddMode,short Intra_PAN){
            switch(DestAddMode)
            {
                case 00:
                    switch(SrcAddMode)
                    {
                       case 00: return 1;
                       case 10: return 2;
                       case 11: return 3;
                    }
                    break;
                case 10:
                    switch(SrcAddMode)
                    {
                       case 00: return 4;
                       case 10: if(Intra_PAN==0)return 5;else return 6;
                       case 11: if(Intra_PAN==0)return 7;else return 8;
                    }
                    break;
                case 11:
                    switch(SrcAddMode)
                    {
                       case 00: return 9;
                       case 10: if(Intra_PAN==0)return 10;else return 11;
                       case 11: if(Intra_PAN==0)return 12;else return 13;
                    }
                    break;
                default:break;

            }
           return -1;
      }

      /***********************************************************/
      /* Function: sixLowPANType               		             */
      /* Arguments : String: received packet stream        		 */
	  /*			 This argument represent the original packet */
      /* Return: Nothing                        	             */
      /* function set the type of 6lowPAN packet 		         */
      /***********************************************************/
      public static void sixLowPANType(String receivedPacketStream, int indexFirstByte) {
            		String packetType="";
                  String typeOctet=receivedPacketStream.substring(indexFirstByte, indexFirstByte+2);
                  if(typeOctet.equals("80"))
                        packetType="Echo request";
                  else if(typeOctet.equals("86"))
                        packetType="Router advertisement";
                  else if(typeOctet.equals("81"))
                        packetType="Echo reply";
                  else if(typeOctet.equals("3B"))
                        packetType="IPv6 no next header";
                  else if(typeOctet.equals("85"))
                        packetType="Router solicitation";
                  else if (typeOctet.equals(("9B"))&&(receivedPacketStream.substring(indexFirstByte+3, indexFirstByte+5).equals("02")))
                        packetType="DODAG Advertisement Object";
                  else if (typeOctet.equals(("9B"))&&(receivedPacketStream.substring(indexFirstByte+3, indexFirstByte+5).equals("01")))
                        packetType="DODAG Info Object";
                  else if (typeOctet.equals(("9B"))&&(receivedPacketStream.substring(indexFirstByte+3, indexFirstByte+5).equals("00")))
                        packetType="DODAG Info Solicitation";

                  sixLoWPANParsedPacket.set6LoWPANType(packetType);
      }

      /******************************************************************/
      /* Function: parseIEEE802_15_4Fileds                              */
      /* Arguments : String: received packet stream                     */
      /*             This argument represent the original packet        */
      /*             double: arrivalTime for packet stream              */
      /*             this arguments receive the arrival time for        */
      /*             packet in number of seconds since 00:00 1-Jan-1900 */
	  /*             IEEE802_15_4: To called this override function     */
      /* Return: Nothing                                                */
      /* function set the fields of IEEE802.15.4 depend on its type     */
      /******************************************************************/
      public static void parseIEEE802_15_4Fileds(String receivedPacketStream, double arrivalTime,  IEEE802_15_4 ParsedPacket) {
            
            //----- set the common fields between all types -----//
            //determine the case of packet
            short IEEEcase =IEEE802_15_4Case(IEEE802_15_4ParsedPacket.getDestAddMode(),IEEE802_15_4ParsedPacket.getSrcAddMode(), IEEE802_15_4ParsedPacket.getIntra_PAN());


            //DispID field
            int offset=0;
				IEEE802_15_4ParsedPacket.setDispID(receivedPacketStream.substring(offset,offset+2));
				offset=2;
				IEEE_header_length+=2;
            //Real Arrival time
                                IEEE802_15_4ParsedPacket.setRealArrivalTime(arrivalTime);
            //Length field
				IEEE802_15_4ParsedPacket.setLength((Integer.parseInt(receivedPacketStream.substring(offset,offset+3).trim(),16)+2)+"");
				IEEE_header_length+=3;
				offset=5;
            // FrameCon parts
				String frameCon = new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
				IEEE_header_length+=6;
				String Bin = new ConvertImpl().divideAndConvert(frameCon);
				IEEE802_15_4ParsedPacket.setFrameType(new ConvertImpl().ConvertBinToDec(Bin.substring(Bin.length()-3,Bin.length())));
				IEEE802_15_4ParsedPacket.setSec(Short.parseShort(new ConvertImpl().ConvertBinToDec(Bin.substring(Bin.length()-4,Bin.length()-3))));
				IEEE802_15_4ParsedPacket.setPnd(Short.parseShort(new ConvertImpl().ConvertBinToDec(Bin.substring(Bin.length()-5,Bin.length()-4))));
				IEEE802_15_4ParsedPacket.setAck(Short.parseShort(new ConvertImpl().ConvertBinToDec(Bin.substring(Bin.length()-6,Bin.length()-5))));
				IEEE802_15_4ParsedPacket.setIntra(Short.parseShort(new ConvertImpl().ConvertBinToDec(Bin.substring(Bin.length()-7,Bin.length()-6))));
				IEEE802_15_4ParsedPacket.setReserved(Short.parseShort(new ConvertImpl().ConvertBinToDec(Bin.substring(6,9))));
				offset=11;
            //Seq field
                                String Seq=receivedPacketStream.substring(offset,offset+3);
				IEEE_header_length+=3;
				IEEE802_15_4ParsedPacket.setSeq("0x"+new ConvertImpl().removeSpaces(Seq));
				offset=14;
            //------------------//

            //----------------- Beacon Frame -----------------//
            if(IEEE802_15_4ParsedPacket.getPacketType() == 0)
            {
                  IEEE802_15_4ParsedPacket.setFrameType("Beacon");
                  //FrameCon field
                  IEEE802_15_4ParsedPacket.setFrameCon();
                  //Beacon doesn't have address destination, only source information
                  if(IEEEcase == 2)
                  {
                    String SrcPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    IEEE802_15_4ParsedPacket.setSrcPAN("0x"+new ConvertImpl().removeSpaces(SrcPan));

                    //SrcADD field
                    String SrcAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    IEEE802_15_4ParsedPacket.setSrcADD("0x"+new ConvertImpl().removeSpaces(SrcAdd));
                    offset=offset+6;
                  }
                  else if(IEEEcase == 3)
                  {
                    String SrcPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    IEEE802_15_4ParsedPacket.setSrcPAN("0x"+new ConvertImpl().removeSpaces(SrcPan));

                    //SrcADD field
                    String SrcAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+24));
                    IEEE802_15_4ParsedPacket.setSrcADD("0x"+new ConvertImpl().removeSpaces(SrcAdd));
                    offset=offset+24;
                  }

                  //SuperFrameSpecs field
                  String SuperFrameSpecs=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                  Bin = new ConvertImpl().divideAndConvert(SuperFrameSpecs);
                  IEEE802_15_4ParsedPacket.setBO(Short.parseShort(new ConvertImpl().ConvertBinToDec(Bin.substring(Bin.length()-4,Bin.length()))));
                  IEEE802_15_4ParsedPacket.setSO(Short.parseShort(new ConvertImpl().ConvertBinToDec(Bin.substring(Bin.length()-8,Bin.length()-4))));
                  String FinCAP=new ConvertImpl().ConvertBinToDec(Bin.substring(Bin.length()-12,Bin.length()-8));
                  IEEE802_15_4ParsedPacket.setFinCap(Short.parseShort(FinCAP));
                  IEEE802_15_4ParsedPacket.setBat(Short.parseShort(new ConvertImpl().ConvertBinToDec(Bin.substring(Bin.length()-13,Bin.length()-12))));
                  IEEE802_15_4ParsedPacket.setRes(Short.parseShort(new ConvertImpl().ConvertBinToDec(Bin.substring(Bin.length()-14,Bin.length()-13))));
                  IEEE802_15_4ParsedPacket.setPAN(Short.parseShort(new ConvertImpl().ConvertBinToDec(Bin.substring(Bin.length()-15,Bin.length()-14))));
                  IEEE802_15_4ParsedPacket.setass(Short.parseShort(new ConvertImpl().ConvertBinToDec(Bin.substring(0,1))));
                  IEEE802_15_4ParsedPacket.setSuperFrameSpecs();
                  offset=offset+6;
                  //GTS fields
                    //GTS Specification
                  IEEE802_15_4ParsedPacket.setGTS(new ConvertImpl().removeSpaces(receivedPacketStream.substring(offset,offset+3)));
                  offset=offset+3;
                  //Evaluate if GTS Permit and according to it decrypt the rest of GTS fields if they exist
                  if((new ConvertImpl().convertHexToBin(IEEE802_15_4ParsedPacket.getGTS().substring(1))).equalsIgnoreCase("0001"))
                  {
                        //GTS Permit enabled, find how many descriptors there are.
                        int gtsslots=Integer.parseInt(new ConvertImpl().ConvertBinToDec((new ConvertImpl().convertHexToBin((IEEE802_15_4ParsedPacket.getGTS()).substring(0,1))).substring(0,3)));
                        Integer.parseInt(new ConvertImpl().ConvertBinToDec(IEEE802_15_4ParsedPacket.getGTS().substring(0,3)));
                        if(gtsslots>0)
                        {
                            //First, get GTSDirec Mask
                            IEEE802_15_4ParsedPacket.setGTSDirec(new ConvertImpl().removeSpaces(receivedPacketStream.substring(offset,offset+3)));
                            offset=offset+3;
                            //then, the GTS List of descriptors
                            ArrayList<String> GTSList=new ArrayList();
                            for(int z=0;z<gtsslots;z++)
                            {
                                String descriptor=(new ConvertImpl().removeSpaces(receivedPacketStream.substring(offset,offset+3)));
                                offset=offset+3;
                                descriptor+=" "+(new ConvertImpl().removeSpaces(receivedPacketStream.substring(offset,offset+3)));
                                offset=offset+3;
                                GTSList.add("0x"+descriptor);
                            }
                            IEEE802_15_4ParsedPacket.setGTSList(GTSList);
                        }
                  }

                  //Pending Address
                    //Pending Address Specification
                  IEEE802_15_4ParsedPacket.setPending(new ConvertImpl().removeSpaces(receivedPacketStream.substring(offset,offset+3)));
                  offset=offset+3;
                  //Evaluate if Pending Address fields includes any short or extended address
                  if(Integer.parseInt(new ConvertImpl().ConvertBinToDec(new ConvertImpl().convertHexToBin(IEEE802_15_4ParsedPacket.getPending().substring(0,1)).substring(0,3)))>0 || Integer.parseInt(new ConvertImpl().ConvertBinToDec(new ConvertImpl().convertHexToBin(IEEE802_15_4ParsedPacket.getPending().substring(1)).substring(0,3)))>0)
                  {
                    //There are shorts or extended pending address. Now read the AddressList.
                    String pendingAddressList="";
                    int pendShort=Integer.parseInt(new ConvertImpl().ConvertBinToDec(new ConvertImpl().convertHexToBin(IEEE802_15_4ParsedPacket.getPending().substring(0,1)).substring(0,3)));
                    for(int z=0;z<pendShort;z++)
                    {
                        pendingAddressList+=" 0x"+(new ConvertImpl().removeSpaces((new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6)))));
                        offset=offset+6;
                    }
                    int pendExtended=Integer.parseInt(new ConvertImpl().ConvertBinToDec(new ConvertImpl().convertHexToBin(IEEE802_15_4ParsedPacket.getPending().substring(1)).substring(0,3)));
                    for(int z=0;z<pendExtended;z++)
                    {
                        pendingAddressList+=" 0x"+(new ConvertImpl().removeSpaces(receivedPacketStream.substring(offset,offset+24)));
                        offset=offset+24;
                    }
                    IEEE802_15_4ParsedPacket.setPendingList(pendingAddressList);
                  }

                  //Beacon Payload
                  if(offset < (receivedPacketStream.length()-27))
                  {
                    //Beacon frame includes payload
                    IEEE802_15_4ParsedPacket.setPayload(receivedPacketStream.substring(offset, receivedPacketStream.length()-27));
                  }

                  // Metadata can be parsed according to the length of the frame, to take into account
                  // those frames where there are some optional fields.
                  String metadata=receivedPacketStream.substring(receivedPacketStream.length()-27);
                 //LQ1 field
                  offset=0;
                  IEEE802_15_4ParsedPacket.setLQI(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //RSSI field
                  String RSSI = metadata.substring(offset,offset+3);
                  byte [] rssi = new ConvertImpl().hexStringToByteArray(RSSI.trim());
                  IEEE802_15_4ParsedPacket.setRSSI(rssi[0]+"");
                  offset =offset+3;
                  //CRC field
                  IEEE802_15_4ParsedPacket.setCRC(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //Mhrlen field
                  IEEE802_15_4ParsedPacket.setMhrlen(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //Channel field
                  IEEE802_15_4ParsedPacket.setChannel(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //TimeStamp field
                  IEEE802_15_4ParsedPacket.setTimeStamp(new ConvertImpl().ConHexaToDecimal(new ConvertImpl().removeSpaces(metadata.substring(offset,metadata.length()))));
                  
            }
            //----------------- End Beacon Frame -----------------//

            //------------------ Payload Frame ------------------//
            else if(IEEE802_15_4ParsedPacket.getPacketType() == 1)
            {
                  IEEE802_15_4ParsedPacket.setFrameType("Data");
                  //FrameCon field
                  IEEE802_15_4ParsedPacket.setFrameCon();

                  if(IEEEcase == 4)
                  {
                    String DestPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    IEEE_header_length+=6;
                    IEEE802_15_4ParsedPacket.setDestPAN("0x"+new ConvertImpl().removeSpaces(DestPan));

                    String DestAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    IEEE802_15_4ParsedPacket.setDestADD("0x"+new ConvertImpl().removeSpaces(DestAdd));
                    IEEE_header_length+=6;
                    offset=offset+6;
                  }
                  else if(IEEEcase == 5 || IEEEcase == 6)
                  {
                    String DestPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    IEEE_header_length+=6;
                    IEEE802_15_4ParsedPacket.setDestPAN("0x"+new ConvertImpl().removeSpaces(DestPan));
                    String DestAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    IEEE802_15_4ParsedPacket.setDestADD("0x"+new ConvertImpl().removeSpaces(DestAdd));
                    IEEE_header_length+=6;
                    offset=offset+6;

                    if(IEEEcase == 5)
                    {
                        String SrcPan = new ConvertImpl().byteSwap(receivedPacketStream.substring(offset, offset + 6));
                        offset=offset+6;
                        IEEE_header_length+=6;
                        IEEE802_15_4ParsedPacket.setSrcPAN("0x"+new ConvertImpl().removeSpaces(SrcPan));
                    }
                    //SrcADD field
                    String SrcAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    IEEE802_15_4ParsedPacket.setSrcADD("0x"+new ConvertImpl().removeSpaces(SrcAdd));
                    offset=offset+6;
                    IEEE_header_length+=6;

                  }
                  else if(IEEEcase == 7 || IEEEcase == 8)
                  {
                    String DestPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    IEEE_header_length+=6;
                    IEEE802_15_4ParsedPacket.setDestPAN("0x"+new ConvertImpl().removeSpaces(DestPan));
                    String DestAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    IEEE802_15_4ParsedPacket.setDestADD("0x"+new ConvertImpl().removeSpaces(DestAdd));
                    offset=offset+6;
                    IEEE_header_length+=6;

                    if(IEEEcase == 7)
                    {
                        String SrcPan = new ConvertImpl().byteSwap(receivedPacketStream.substring(offset, offset + 6));
                        offset=offset+6;
                        IEEE_header_length+=6;
                        IEEE802_15_4ParsedPacket.setSrcPAN("0x"+new ConvertImpl().removeSpaces(SrcPan));
                    }
                    //SrcADD field
                    String SrcAdd=new ConvertImpl().byteSwap((receivedPacketStream.substring(offset,offset+24)).trim());
                    IEEE802_15_4ParsedPacket.setSrcADD("0x"+new ConvertImpl().removeSpaces(SrcAdd));
                    offset=offset+24;
                    IEEE_header_length+=24;

                  }
                  else if(IEEEcase == 9)
                  {
                    String DestPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    IEEE_header_length+=6;
                    IEEE802_15_4ParsedPacket.setDestPAN("0x"+new ConvertImpl().removeSpaces(DestPan));

                     String DestAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+24));
                    IEEE802_15_4ParsedPacket.setDestADD("0x"+new ConvertImpl().removeSpaces(DestAdd));
                    offset=offset+24;
                    IEEE_header_length+=24;
                  }
                  else if(IEEEcase == 10 || IEEEcase == 11)
                  {
                    String DestPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    IEEE_header_length+=6;
                    IEEE802_15_4ParsedPacket.setDestPAN("0x"+new ConvertImpl().removeSpaces(DestPan));
                    String DestAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+24));
                    IEEE802_15_4ParsedPacket.setDestADD("0x"+new ConvertImpl().removeSpaces(DestAdd));
                    offset=offset+24;
                    IEEE_header_length+=24;

                    if(IEEEcase == 10)
                    {
                        String SrcPan = new ConvertImpl().byteSwap(receivedPacketStream.substring(offset, offset + 6));
                        offset=offset+6;
                        IEEE_header_length+=6;
                        IEEE802_15_4ParsedPacket.setSrcPAN("0x"+new ConvertImpl().removeSpaces(SrcPan));
                    }
                    //SrcADD field
                    String SrcAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    IEEE802_15_4ParsedPacket.setSrcADD("0x"+new ConvertImpl().removeSpaces(SrcAdd));
                    offset=offset+6;
                    IEEE_header_length+=6;
                  }
                  else if(IEEEcase == 12 || IEEEcase == 13)
                  {
                    String DestPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    IEEE_header_length+=6;
                    IEEE802_15_4ParsedPacket.setDestPAN("0x"+new ConvertImpl().removeSpaces(DestPan));
                    String DestAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+24));
                    IEEE802_15_4ParsedPacket.setDestADD("0x"+new ConvertImpl().removeSpaces(DestAdd));
                    offset=offset+24;
                    IEEE_header_length+=24;
                    if(IEEEcase == 12)
                    {
                        String SrcPan = new ConvertImpl().byteSwap(receivedPacketStream.substring(offset, offset + 6));
                        offset=offset+6;
                        IEEE_header_length+=6;
                        IEEE802_15_4ParsedPacket.setSrcPAN("0x"+new ConvertImpl().removeSpaces(SrcPan));
                    }
                    //SrcADD field
                    String SrcAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+24));
                    IEEE802_15_4ParsedPacket.setSrcADD("0x"+new ConvertImpl().removeSpaces(SrcAdd));
                    offset=offset+24;
                    IEEE_header_length+=24;
                  }

                  //Payload of the data frame
                  //Is there any payload in the data frame?
                  //data Payload
                  String dataPayload="";
                  if(offset < (receivedPacketStream.length()-27))
                  {
                    //data frame includes payload
                    dataPayload=receivedPacketStream.substring(offset, receivedPacketStream.length()-27);
                  }
                  IEEE802_15_4ParsedPacket.setPayload(dataPayload); 


                  // Metadata can be parsed according to the length of the frame, to take into account
                  // those frames where there are some optional fields.
                  String metadata=receivedPacketStream.substring(receivedPacketStream.length()-27);
                 //LQ1 field
                  offset=0;
                  IEEE802_15_4ParsedPacket.setLQI(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //RSSI field
                  String RSSI = metadata.substring(offset,offset+3);
                  byte [] rssi = new ConvertImpl().hexStringToByteArray(RSSI.trim());
                  IEEE802_15_4ParsedPacket.setRSSI(rssi[0]+"");
                  offset =offset+3;
                  //CRC field
                  IEEE802_15_4ParsedPacket.setCRC(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //Mhrlen field
                  IEEE802_15_4ParsedPacket.setMhrlen(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //Channel field
                  IEEE802_15_4ParsedPacket.setChannel(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //TimeStamp field
                  IEEE802_15_4ParsedPacket.setTimeStamp(new ConvertImpl().ConHexaToDecimal(new ConvertImpl().removeSpaces(metadata.substring(offset,metadata.length()))));

            }
            //------------------ End Payload Frame ------------------//


            //---------------- Acknowledgement Frame ----------------//
	    else if(IEEE802_15_4ParsedPacket.getPacketType() == 2)
	    {
                  IEEE802_15_4ParsedPacket.setFrameType("Ack");
                  //FrameCon field
                  IEEE802_15_4ParsedPacket.setFrameCon();
                  
                  // Metadata can be parsed according to the length of the frame, to take into account
                  // those frames where there are some optional fields.
                  String metadata=receivedPacketStream.substring(receivedPacketStream.length()-27);
                 //LQ1 field
                  offset=0;
                  IEEE802_15_4ParsedPacket.setLQI(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //RSSI field
                  String RSSI = metadata.substring(offset,offset+3);
                  byte [] rssi = new ConvertImpl().hexStringToByteArray(RSSI.trim());
                  IEEE802_15_4ParsedPacket.setRSSI(rssi[0]+"");
                  offset =offset+3;
                  //CRC field
                  IEEE802_15_4ParsedPacket.setCRC(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //Mhrlen field
                  IEEE802_15_4ParsedPacket.setMhrlen(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //Channel field
                  IEEE802_15_4ParsedPacket.setChannel(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //TimeStamp field
                  IEEE802_15_4ParsedPacket.setTimeStamp(new ConvertImpl().ConHexaToDecimal(new ConvertImpl().removeSpaces(metadata.substring(offset,metadata.length()))));

             }
            //---------------- End Acknowledgement Frame ----------------//

            //---------------- Command Frame ----------------//
	    else if(IEEE802_15_4ParsedPacket.getPacketType() == 3)
	    {
                  IEEE802_15_4ParsedPacket.setFrameType("Command");

                  //FrameCon field
                  IEEE802_15_4ParsedPacket.setFrameCon();

                  if(IEEEcase == 4)
                  {
                    String DestPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    IEEE802_15_4ParsedPacket.setDestPAN("0x"+new ConvertImpl().removeSpaces(DestPan));

                    String DestAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    IEEE802_15_4ParsedPacket.setDestADD("0x"+new ConvertImpl().removeSpaces(DestAdd));
                    offset=offset+6;
                  }
                  else if(IEEEcase == 5 || IEEEcase == 6)
                  {
                    String DestPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    IEEE802_15_4ParsedPacket.setDestPAN("0x"+new ConvertImpl().removeSpaces(DestPan));
                    String DestAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    IEEE802_15_4ParsedPacket.setDestADD("0x"+new ConvertImpl().removeSpaces(DestAdd));
                    offset=offset+6;

                    if(IEEEcase == 5)
                    {
                        String SrcPan = new ConvertImpl().byteSwap(receivedPacketStream.substring(offset, offset + 6));
                        offset=offset+6;
                        IEEE802_15_4ParsedPacket.setSrcPAN("0x"+new ConvertImpl().removeSpaces(SrcPan));
                    }
                    //SrcADD field
                    String SrcAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    IEEE802_15_4ParsedPacket.setSrcADD("0x"+new ConvertImpl().removeSpaces(SrcAdd));
                    offset=offset+6;

                  }
                  else if(IEEEcase == 7 || IEEEcase == 8)
                  {
                    String DestPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    IEEE802_15_4ParsedPacket.setDestPAN("0x"+new ConvertImpl().removeSpaces(DestPan));
                    String DestAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    IEEE802_15_4ParsedPacket.setDestADD("0x"+new ConvertImpl().removeSpaces(DestAdd));
                    offset=offset+6;

                    if(IEEEcase == 7)
                    {
                        String SrcPan = new ConvertImpl().byteSwap(receivedPacketStream.substring(offset, offset + 6));
                        offset=offset+6;
                        IEEE802_15_4ParsedPacket.setSrcPAN("0x"+new ConvertImpl().removeSpaces(SrcPan));
                    }
                    //SrcADD field
                    String SrcAdd=new ConvertImpl().byteSwap((receivedPacketStream.substring(offset,offset+24)).trim());
                    IEEE802_15_4ParsedPacket.setSrcADD("0x"+new ConvertImpl().removeSpaces(SrcAdd));
                    offset=offset+24;

                  }
                  else if(IEEEcase == 9)
                  {
                    String DestPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    IEEE802_15_4ParsedPacket.setDestPAN("0x"+new ConvertImpl().removeSpaces(DestPan));

                     String DestAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+24));
                    IEEE802_15_4ParsedPacket.setDestADD("0x"+new ConvertImpl().removeSpaces(DestAdd));
                    offset=offset+24;
                  }
                  else if(IEEEcase == 10 || IEEEcase == 11)
                  {
                    String DestPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    IEEE802_15_4ParsedPacket.setDestPAN("0x"+new ConvertImpl().removeSpaces(DestPan));
                    String DestAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+24));
                    IEEE802_15_4ParsedPacket.setDestADD("0x"+new ConvertImpl().removeSpaces(DestAdd));
                    offset=offset+24;

                    if(IEEEcase == 10)
                    {
                        String SrcPan = new ConvertImpl().byteSwap(receivedPacketStream.substring(offset, offset + 6));
                        offset=offset+6;
                        IEEE802_15_4ParsedPacket.setSrcPAN("0x"+new ConvertImpl().removeSpaces(SrcPan));
                    }
                    //SrcADD field
                    String SrcAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    IEEE802_15_4ParsedPacket.setSrcADD("0x"+new ConvertImpl().removeSpaces(SrcAdd));
                    offset=offset+6;

                  }
                  else if(IEEEcase == 12 || IEEEcase == 13)
                  {
                    String DestPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    IEEE802_15_4ParsedPacket.setDestPAN("0x"+new ConvertImpl().removeSpaces(DestPan));
                    String DestAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+24));
                    IEEE802_15_4ParsedPacket.setDestADD("0x"+new ConvertImpl().removeSpaces(DestAdd));
                    offset=offset+24;

                    if(IEEEcase == 12)
                    {
                        String SrcPan = new ConvertImpl().byteSwap(receivedPacketStream.substring(offset, offset + 6));
                        offset=offset+6;
                        IEEE802_15_4ParsedPacket.setSrcPAN("0x"+new ConvertImpl().removeSpaces(SrcPan));
                    }
                    //SrcADD field
                    String SrcAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+24));
                    IEEE802_15_4ParsedPacket.setSrcADD("0x"+new ConvertImpl().removeSpaces(SrcAdd));
                    offset=offset+24;

                  }
                  
                  //Payload of the command
                  //Command type field
                  String cmdType=new ConvertImpl().removeSpaces(receivedPacketStream.substring(offset,offset+3));
                  offset=offset+3;
                  // Is there any payload in the command frame?
                  //Command Payload
                  String cmdPayload="";
                  if(offset < (receivedPacketStream.length()-27))
                  {
                    //Command frame includes payload
                    cmdPayload=receivedPacketStream.substring(offset, receivedPacketStream.length()-27);
                  }
                  IEEE802_15_4ParsedPacket.setPayload(cmdType+" "+cmdPayload);        
                  
                  // Metadata can be parsed according to the length of the frame, to take into account
                  // those frames where there are some optional fields.
                  String metadata=receivedPacketStream.substring(receivedPacketStream.length()-27);
                 //LQ1 field
                  offset=0;
                  IEEE802_15_4ParsedPacket.setLQI(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //RSSI field
                  String RSSI = metadata.substring(offset,offset+3);
                  byte [] rssi = new ConvertImpl().hexStringToByteArray(RSSI.trim());
                  IEEE802_15_4ParsedPacket.setRSSI(rssi[0]+"");
                  offset =offset+3;
                  //CRC field
                  IEEE802_15_4ParsedPacket.setCRC(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //Mhrlen field
                  IEEE802_15_4ParsedPacket.setMhrlen(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //Channel field
                  IEEE802_15_4ParsedPacket.setChannel(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //TimeStamp field
                  IEEE802_15_4ParsedPacket.setTimeStamp(new ConvertImpl().ConHexaToDecimal(new ConvertImpl().removeSpaces(metadata.substring(offset,metadata.length()))));

             }
            //---------------- End Command Frame ----------------//

      }

      //Override function for 6LoWPAN
	  /******************************************************************/
      /* Function: parseIEEE802_15_4Fileds                              */
      /* Arguments : String: received packet stream                     */
      /*             This argument represent the original packet        */
      /*             double: arrivalTime for packet stream              */
      /*             this arguments receive the arrival time for        */
      /*             packet in number of seconds since 00:00 1-Jan-1900 */
	  /*             sixLoWPAN: To call this override function          */
      /* Return: Nothing                                                */
      /* function set the fields of IEEE802.15.4 depend on its type     */
      /******************************************************************/
            public static void parseIEEE802_15_4Fileds(String receivedPacketStream, double arrivalTime,  sixLoWPAN sixLoWPANPacket) {

            //----- set the common fields between all types -----//
            //determine the case of packet
            short IEEEcase =IEEE802_15_4Case(sixLoWPANParsedPacket.getDestAddMode(),sixLoWPANParsedPacket.getSrcAddMode(), sixLoWPANParsedPacket.getIntra_PAN());


            //DispID field
            int offset=0;
            IEEE_header_length=0;
				sixLoWPANParsedPacket.setDispID(receivedPacketStream.substring(offset,offset+2));
				offset=2;
				IEEE_header_length=2;
            //Real Arrival time
                                sixLoWPANParsedPacket.setRealArrivalTime(arrivalTime);
            //Length field
				sixLoWPANParsedPacket.setLength((Integer.parseInt(receivedPacketStream.substring(offset,offset+3).trim(),16)+2)+"");
				IEEE_header_length=5;
				offset=5;
            // FrameCon parts
				String frameCon = new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
				//IEEE_header_length+=6;
				String Bin = new ConvertImpl().divideAndConvert(frameCon);
				sixLoWPANParsedPacket.setFrameType(new ConvertImpl().ConvertBinToDec(Bin.substring(Bin.length()-3,Bin.length())));
				sixLoWPANParsedPacket.setSec(Short.parseShort(new ConvertImpl().ConvertBinToDec(Bin.substring(Bin.length()-4,Bin.length()-3))));
				sixLoWPANParsedPacket.setPnd(Short.parseShort(new ConvertImpl().ConvertBinToDec(Bin.substring(Bin.length()-5,Bin.length()-4))));
				sixLoWPANParsedPacket.setAck(Short.parseShort(new ConvertImpl().ConvertBinToDec(Bin.substring(Bin.length()-6,Bin.length()-5))));
				sixLoWPANParsedPacket.setIntra(Short.parseShort(new ConvertImpl().ConvertBinToDec(Bin.substring(Bin.length()-7,Bin.length()-6))));
				sixLoWPANParsedPacket.setReserved(Short.parseShort(new ConvertImpl().ConvertBinToDec(Bin.substring(6,9))));
				offset=11;
            IEEE_header_length=11;
            //Seq field
                                String Seq=receivedPacketStream.substring(offset,offset+3);
				//IEEE_header_length+=3;
				sixLoWPANParsedPacket.setSeq("0x"+new ConvertImpl().removeSpaces(Seq));
				offset=14;
            IEEE_header_length=14;
            //------------------//

            //----------------- Beacon Frame -----------------//
            if(sixLoWPANParsedPacket.getPacketType() == 0)
            {
                  sixLoWPANParsedPacket.setFrameType("Beacon");
                  //FrameCon field
                  sixLoWPANParsedPacket.setFrameCon();
                  //Beacon doesn't have address destination, only source information
                  if(IEEEcase == 2)
                  {
                    String SrcPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    sixLoWPANParsedPacket.setSrcPAN("0x"+new ConvertImpl().removeSpaces(SrcPan));

                    //SrcADD field
                    String SrcAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    sixLoWPANParsedPacket.setSrcADD("0x"+new ConvertImpl().removeSpaces(SrcAdd));
                    offset=offset+6;
                  }
                  else if(IEEEcase == 3)
                  {
                    String SrcPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    sixLoWPANParsedPacket.setSrcPAN("0x"+new ConvertImpl().removeSpaces(SrcPan));

                    //SrcADD field
                    String SrcAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+24));
                    sixLoWPANParsedPacket.setSrcADD("0x"+new ConvertImpl().removeSpaces(SrcAdd));
                    offset=offset+24;
                  }

                  //SuperFrameSpecs field
                  String SuperFrameSpecs=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                  Bin = new ConvertImpl().divideAndConvert(SuperFrameSpecs);
                  sixLoWPANParsedPacket.setBO(Short.parseShort(new ConvertImpl().ConvertBinToDec(Bin.substring(Bin.length()-4,Bin.length()))));
                  sixLoWPANParsedPacket.setSO(Short.parseShort(new ConvertImpl().ConvertBinToDec(Bin.substring(Bin.length()-8,Bin.length()-4))));
                  String FinCAP=new ConvertImpl().ConvertBinToDec(Bin.substring(Bin.length()-12,Bin.length()-8));
                  sixLoWPANParsedPacket.setFinCap(Short.parseShort(FinCAP));
                  sixLoWPANParsedPacket.setBat(Short.parseShort(new ConvertImpl().ConvertBinToDec(Bin.substring(Bin.length()-13,Bin.length()-12))));
                  sixLoWPANParsedPacket.setRes(Short.parseShort(new ConvertImpl().ConvertBinToDec(Bin.substring(Bin.length()-14,Bin.length()-13))));
                  sixLoWPANParsedPacket.setPAN(Short.parseShort(new ConvertImpl().ConvertBinToDec(Bin.substring(Bin.length()-15,Bin.length()-14))));
                  sixLoWPANParsedPacket.setass(Short.parseShort(new ConvertImpl().ConvertBinToDec(Bin.substring(0,1))));
                  sixLoWPANParsedPacket.setSuperFrameSpecs();
                  offset=offset+6;
                  //GTS fields
                    //GTS Specification
                  sixLoWPANParsedPacket.setGTS(new ConvertImpl().removeSpaces(receivedPacketStream.substring(offset,offset+3)));
                  offset=offset+3;
                  //Evaluate if GTS Permit and according to it decrypt the rest of GTS fields if they exist
                  if((new ConvertImpl().convertHexToBin(sixLoWPANParsedPacket.getGTS().substring(1))).equalsIgnoreCase("0001"))
                  {
                        //GTS Permit enabled, find how many descriptors there are.
                        int gtsslots=Integer.parseInt(new ConvertImpl().ConvertBinToDec((new ConvertImpl().convertHexToBin((sixLoWPANParsedPacket.getGTS()).substring(0,1))).substring(0,3)));
                        Integer.parseInt(new ConvertImpl().ConvertBinToDec(sixLoWPANParsedPacket.getGTS().substring(0,3)));
                        if(gtsslots>0)
                        {
                            //First, get GTSDirec Mask
                            sixLoWPANParsedPacket.setGTSDirec(new ConvertImpl().removeSpaces(receivedPacketStream.substring(offset,offset+3)));
                            offset=offset+3;
                            //then, the GTS List of descriptors
                            ArrayList<String> GTSList=new ArrayList();
                            for(int z=0;z<gtsslots;z++)
                            {
                                String descriptor=(new ConvertImpl().removeSpaces(receivedPacketStream.substring(offset,offset+3)));
                                offset=offset+3;
                                descriptor+=" "+(new ConvertImpl().removeSpaces(receivedPacketStream.substring(offset,offset+3)));
                                offset=offset+3;
                                GTSList.add("0x"+descriptor);
                            }
                            sixLoWPANParsedPacket.setGTSList(GTSList);
                        }
                  }

                  //Pending Address
                    //Pending Address Specification
                  sixLoWPANParsedPacket.setPending(new ConvertImpl().removeSpaces(receivedPacketStream.substring(offset,offset+3)));
                  offset=offset+3;
                  //Evaluate if Pending Address fields includes any short or extended address
                  if(Integer.parseInt(new ConvertImpl().ConvertBinToDec(new ConvertImpl().convertHexToBin(sixLoWPANParsedPacket.getPending().substring(0,1)).substring(0,3)))>0 || Integer.parseInt(new ConvertImpl().ConvertBinToDec(new ConvertImpl().convertHexToBin(sixLoWPANParsedPacket.getPending().substring(1)).substring(0,3)))>0)
                  {
                    //There are shorts or extended pending address. Now read the AddressList.
                    String pendingAddressList="";
                    int pendShort=Integer.parseInt(new ConvertImpl().ConvertBinToDec(new ConvertImpl().convertHexToBin(sixLoWPANParsedPacket.getPending().substring(0,1)).substring(0,3)));
                    for(int z=0;z<pendShort;z++)
                    {
                        pendingAddressList+=" 0x"+(new ConvertImpl().removeSpaces((new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6)))));
                        offset=offset+6;
                    }
                    int pendExtended=Integer.parseInt(new ConvertImpl().ConvertBinToDec(new ConvertImpl().convertHexToBin(sixLoWPANParsedPacket.getPending().substring(1)).substring(0,3)));
                    for(int z=0;z<pendExtended;z++)
                    {
                        pendingAddressList+=" 0x"+(new ConvertImpl().removeSpaces(receivedPacketStream.substring(offset,offset+24)));
                        offset=offset+24;
                    }
                    sixLoWPANParsedPacket.setPendingList(pendingAddressList);
                  }

                  //Beacon Payload
                  if(offset < (receivedPacketStream.length()-27))
                  {
                    //Beacon frame includes payload
                    sixLoWPANParsedPacket.setPayload(receivedPacketStream.substring(offset, receivedPacketStream.length()-27));
                  }

                  // Metadata can be parsed according to the length of the frame, to take into account
                  // those frames where there are some optional fields.
                  String metadata=receivedPacketStream.substring(receivedPacketStream.length()-27);
                 //LQ1 field
                  offset=0;
                  sixLoWPANParsedPacket.setLQI(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //RSSI field
                  String RSSI = metadata.substring(offset,offset+3);
                  byte [] rssi = new ConvertImpl().hexStringToByteArray(RSSI.trim());
                  sixLoWPANParsedPacket.setRSSI(rssi[0]+"");
                  offset =offset+3;
                  //CRC field
                  sixLoWPANParsedPacket.setCRC(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //Mhrlen field
                  sixLoWPANParsedPacket.setMhrlen(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //Channel field
                  sixLoWPANParsedPacket.setChannel(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //TimeStamp field
                  sixLoWPANParsedPacket.setTimeStamp(new ConvertImpl().ConHexaToDecimal(new ConvertImpl().removeSpaces(metadata.substring(offset,metadata.length()))));

            }
            //----------------- End Peacon Frame -----------------//


            //------------------ Payload Frame ------------------//
            else if(sixLoWPANParsedPacket.getPacketType() == 1)
            {
                  sixLoWPANParsedPacket.setFrameType("Data");
                  //FrameCon field
                  sixLoWPANParsedPacket.setFrameCon();

                  if(IEEEcase == 4)
                  {
                    String DestPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    IEEE_header_length+=6;
                    sixLoWPANParsedPacket.setDestPAN("0x"+new ConvertImpl().removeSpaces(DestPan));

                    String DestAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    sixLoWPANParsedPacket.setDestADD("0x"+new ConvertImpl().removeSpaces(DestAdd));
                    IEEE_header_length+=6;
                    offset=offset+6;
                  }
                  else if(IEEEcase == 5 || IEEEcase == 6)
                  {
                    String DestPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    IEEE_header_length+=6;
                    sixLoWPANParsedPacket.setDestPAN("0x"+new ConvertImpl().removeSpaces(DestPan));
                    String DestAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    sixLoWPANParsedPacket.setDestADD("0x"+new ConvertImpl().removeSpaces(DestAdd));
                    IEEE_header_length+=6;
                    offset=offset+6;

                    if(IEEEcase == 5)
                    {
                        String SrcPan = new ConvertImpl().byteSwap(receivedPacketStream.substring(offset, offset + 6));
                        offset=offset+6;
                        IEEE_header_length+=6;
                        sixLoWPANParsedPacket.setSrcPAN("0x"+new ConvertImpl().removeSpaces(SrcPan));
                    }
                    //SrcADD field
                    String SrcAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    sixLoWPANParsedPacket.setSrcADD("0x"+new ConvertImpl().removeSpaces(SrcAdd));
                    offset=offset+6;
                    IEEE_header_length+=6;

                  }
                  else if(IEEEcase == 7 || IEEEcase == 8)
                  {
                    String DestPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    IEEE_header_length+=6;
                    sixLoWPANParsedPacket.setDestPAN("0x"+new ConvertImpl().removeSpaces(DestPan));
                    String DestAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    sixLoWPANParsedPacket.setDestADD("0x"+new ConvertImpl().removeSpaces(DestAdd));
                    offset=offset+6;
                    IEEE_header_length+=6;

                    if(IEEEcase == 7)
                    {
                        String SrcPan = new ConvertImpl().byteSwap(receivedPacketStream.substring(offset, offset + 6));
                        offset=offset+6;
                        IEEE_header_length+=6;
                        sixLoWPANParsedPacket.setSrcPAN("0x"+new ConvertImpl().removeSpaces(SrcPan));
                    }
                    //SrcADD field
                    String SrcAdd=new ConvertImpl().byteSwap((receivedPacketStream.substring(offset,offset+24)).trim());
                    sixLoWPANParsedPacket.setSrcADD("0x"+new ConvertImpl().removeSpaces(SrcAdd));
                    offset=offset+24;
                    IEEE_header_length+=24;

                  }
                  else if(IEEEcase == 9)
                  {
                    String DestPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    IEEE_header_length+=6;
                    sixLoWPANParsedPacket.setDestPAN("0x"+new ConvertImpl().removeSpaces(DestPan));

                     String DestAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+24));
                    sixLoWPANParsedPacket.setDestADD("0x"+new ConvertImpl().removeSpaces(DestAdd));
                    offset=offset+24;
                    IEEE_header_length+=24;
                  }
                  else if(IEEEcase == 10 || IEEEcase == 11)
                  {
                    String DestPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    IEEE_header_length+=6;
                    sixLoWPANParsedPacket.setDestPAN("0x"+new ConvertImpl().removeSpaces(DestPan));
                    String DestAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+24));
                    sixLoWPANParsedPacket.setDestADD("0x"+new ConvertImpl().removeSpaces(DestAdd));
                    offset=offset+24;
                    IEEE_header_length+=24;

                    if(IEEEcase == 10)
                    {
                        String SrcPan = new ConvertImpl().byteSwap(receivedPacketStream.substring(offset, offset + 6));
                        offset=offset+6;
                        IEEE_header_length+=6;
                        sixLoWPANParsedPacket.setSrcPAN("0x"+new ConvertImpl().removeSpaces(SrcPan));
                    }
                    //SrcADD field
                    String SrcAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    sixLoWPANParsedPacket.setSrcADD("0x"+new ConvertImpl().removeSpaces(SrcAdd));
                    offset=offset+6;
                    IEEE_header_length+=6;
                  }
                  else if(IEEEcase == 12 || IEEEcase == 13)
                  {
                    String DestPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    IEEE_header_length+=6;
                    sixLoWPANParsedPacket.setDestPAN("0x"+new ConvertImpl().removeSpaces(DestPan));
                    String DestAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+24));
                    sixLoWPANParsedPacket.setDestADD("0x"+new ConvertImpl().removeSpaces(DestAdd));
                    offset=offset+24;
                    IEEE_header_length+=24;
                    if(IEEEcase == 12)
                    {
                        String SrcPan = new ConvertImpl().byteSwap(receivedPacketStream.substring(offset, offset + 6));
                        offset=offset+6;
                        IEEE_header_length+=6;
                        sixLoWPANParsedPacket.setSrcPAN("0x"+new ConvertImpl().removeSpaces(SrcPan));
                    }
                    //SrcADD field
                    String SrcAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+24));
                    sixLoWPANParsedPacket.setSrcADD("0x"+new ConvertImpl().removeSpaces(SrcAdd));
                    offset=offset+24;
                    IEEE_header_length+=24;
                  }

                  //Payload of the data frame
                  //Is there any payload in the data frame?
                  //data Payload
                  String dataPayload="";
                  if(offset < (receivedPacketStream.length()-27))
                  {
                    //data frame includes payload
                    dataPayload=receivedPacketStream.substring(offset, receivedPacketStream.length()-27);
                  }
                  sixLoWPANParsedPacket.setPayload(dataPayload);

                  //Network Layer decoding depend on the user selection (6LoWPAN)
                  sixLoWPANParsedPacket.setIEEEheaderLength(IEEE_header_length);

                  // Metadata can be parsed according to the length of the frame, to take into account
                  // those frames where there are some optional fields.
                  String metadata=receivedPacketStream.substring(receivedPacketStream.length()-27);
                 //LQ1 field
                  offset=0;
                  sixLoWPANParsedPacket.setLQI(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //RSSI field
                  String RSSI = metadata.substring(offset,offset+3);
                  byte [] rssi = new ConvertImpl().hexStringToByteArray(RSSI.trim());
                  sixLoWPANParsedPacket.setRSSI(rssi[0]+"");
                  offset =offset+3;
                  //CRC field
                  sixLoWPANParsedPacket.setCRC(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //Mhrlen field
                  sixLoWPANParsedPacket.setMhrlen(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //Channel field
                  sixLoWPANParsedPacket.setChannel(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //TimeStamp field
                  sixLoWPANParsedPacket.setTimeStamp(new ConvertImpl().ConHexaToDecimal(new ConvertImpl().removeSpaces(metadata.substring(offset,metadata.length()))));

            }
            //------------------ End Payload Frame ------------------//


            //---------------- Acknowledgement Frame ----------------//
	    else if(sixLoWPANParsedPacket.getPacketType() == 2)
	    {
                  sixLoWPANParsedPacket.setFrameType("Ack");
                  //FrameCon field
                  sixLoWPANParsedPacket.setFrameCon();

                  // Metadata can be parsed according to the length of the frame, to take into account
                  // those frames where there are some optional fields.
                  String metadata=receivedPacketStream.substring(receivedPacketStream.length()-27);
                 //LQ1 field
                  offset=0;
                  sixLoWPANParsedPacket.setLQI(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //RSSI field
                  String RSSI = metadata.substring(offset,offset+3);
                  byte [] rssi = new ConvertImpl().hexStringToByteArray(RSSI.trim());
                  sixLoWPANParsedPacket.setRSSI(rssi[0]+"");
                  offset =offset+3;
                  //CRC field
                  sixLoWPANParsedPacket.setCRC(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //Mhrlen field
                  sixLoWPANParsedPacket.setMhrlen(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //Channel field
                  sixLoWPANParsedPacket.setChannel(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //TimeStamp field
                  sixLoWPANParsedPacket.setTimeStamp(new ConvertImpl().ConHexaToDecimal(new ConvertImpl().removeSpaces(metadata.substring(offset,metadata.length()))));

             }
            //---------------- End Acknowledgement Frame ----------------//

            //---------------- Command Frame ----------------//
	    else if(sixLoWPANParsedPacket.getPacketType() == 3)
	    {
                  sixLoWPANParsedPacket.setFrameType("Command");

                  //FrameCon field
                  sixLoWPANParsedPacket.setFrameCon();

                  if(IEEEcase == 4)
                  {
                    String DestPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    sixLoWPANParsedPacket.setDestPAN("0x"+new ConvertImpl().removeSpaces(DestPan));

                    String DestAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    sixLoWPANParsedPacket.setDestADD("0x"+new ConvertImpl().removeSpaces(DestAdd));
                    offset=offset+6;
                  }
                  else if(IEEEcase == 5 || IEEEcase == 6)
                  {
                    String DestPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    sixLoWPANParsedPacket.setDestPAN("0x"+new ConvertImpl().removeSpaces(DestPan));
                    String DestAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    sixLoWPANParsedPacket.setDestADD("0x"+new ConvertImpl().removeSpaces(DestAdd));
                    offset=offset+6;

                    if(IEEEcase == 5)
                    {
                        String SrcPan = new ConvertImpl().byteSwap(receivedPacketStream.substring(offset, offset + 6));
                        offset=offset+6;
                        sixLoWPANParsedPacket.setSrcPAN("0x"+new ConvertImpl().removeSpaces(SrcPan));
                    }
                    //SrcADD field
                    String SrcAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    sixLoWPANParsedPacket.setSrcADD("0x"+new ConvertImpl().removeSpaces(SrcAdd));
                    offset=offset+6;

                  }
                  else if(IEEEcase == 7 || IEEEcase == 8)
                  {
                    String DestPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    sixLoWPANParsedPacket.setDestPAN("0x"+new ConvertImpl().removeSpaces(DestPan));
                    String DestAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    sixLoWPANParsedPacket.setDestADD("0x"+new ConvertImpl().removeSpaces(DestAdd));
                    offset=offset+6;

                    if(IEEEcase == 7)
                    {
                        String SrcPan = new ConvertImpl().byteSwap(receivedPacketStream.substring(offset, offset + 6));
                        offset=offset+6;
                        sixLoWPANParsedPacket.setSrcPAN("0x"+new ConvertImpl().removeSpaces(SrcPan));
                    }
                    //SrcADD field
                    String SrcAdd=new ConvertImpl().byteSwap((receivedPacketStream.substring(offset,offset+24)).trim());
                    sixLoWPANParsedPacket.setSrcADD("0x"+new ConvertImpl().removeSpaces(SrcAdd));
                    offset=offset+24;

                  }
                  else if(IEEEcase == 9)
                  {
                    String DestPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    sixLoWPANParsedPacket.setDestPAN("0x"+new ConvertImpl().removeSpaces(DestPan));

                     String DestAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+24));
                    sixLoWPANParsedPacket.setDestADD("0x"+new ConvertImpl().removeSpaces(DestAdd));
                    offset=offset+24;
                  }
                  else if(IEEEcase == 10 || IEEEcase == 11)
                  {
                    String DestPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    sixLoWPANParsedPacket.setDestPAN("0x"+new ConvertImpl().removeSpaces(DestPan));
                    String DestAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+24));
                    sixLoWPANParsedPacket.setDestADD("0x"+new ConvertImpl().removeSpaces(DestAdd));
                    offset=offset+24;

                    if(IEEEcase == 10)
                    {
                        String SrcPan = new ConvertImpl().byteSwap(receivedPacketStream.substring(offset, offset + 6));
                        offset=offset+6;
                        sixLoWPANParsedPacket.setSrcPAN("0x"+new ConvertImpl().removeSpaces(SrcPan));
                    }
                    //SrcADD field
                    String SrcAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    sixLoWPANParsedPacket.setSrcADD("0x"+new ConvertImpl().removeSpaces(SrcAdd));
                    offset=offset+6;

                  }
                  else if(IEEEcase == 12 || IEEEcase == 13)
                  {
                    String DestPan=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+6));
                    offset=offset+6;
                    sixLoWPANParsedPacket.setDestPAN("0x"+new ConvertImpl().removeSpaces(DestPan));
                    String DestAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+24));
                    sixLoWPANParsedPacket.setDestADD("0x"+new ConvertImpl().removeSpaces(DestAdd));
                    offset=offset+24;

                    if(IEEEcase == 12)
                    {
                        String SrcPan = new ConvertImpl().byteSwap(receivedPacketStream.substring(offset, offset + 6));
                        offset=offset+6;
                        sixLoWPANParsedPacket.setSrcPAN("0x"+new ConvertImpl().removeSpaces(SrcPan));
                    }
                    //SrcADD field
                    String SrcAdd=new ConvertImpl().byteSwap(receivedPacketStream.substring(offset,offset+24));
                    sixLoWPANParsedPacket.setSrcADD("0x"+new ConvertImpl().removeSpaces(SrcAdd));
                    offset=offset+24;

                  }

                  //Payload of the command
                  //Command type field
                  String cmdType=new ConvertImpl().removeSpaces(receivedPacketStream.substring(offset,offset+3));
                  offset=offset+3;
                  // Is there any payload in the command frame?
                  //Command Payload
                  String cmdPayload="";
                  if(offset < (receivedPacketStream.length()-27))
                  {
                    //Command frame includes payload
                    cmdPayload=receivedPacketStream.substring(offset, receivedPacketStream.length()-27);
                  }
                  sixLoWPANParsedPacket.setPayload(cmdType+" "+cmdPayload);

                  // Metadata can be parsed according to the length of the frame, to take into account
                  // those frames where there are some optional fields.
                  String metadata=receivedPacketStream.substring(receivedPacketStream.length()-27);
                 //LQ1 field
                  offset=0;
                  sixLoWPANParsedPacket.setLQI(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //RSSI field
                  String RSSI = metadata.substring(offset,offset+3);
                  byte [] rssi = new ConvertImpl().hexStringToByteArray(RSSI.trim());
                  sixLoWPANParsedPacket.setRSSI(rssi[0]+"");
                  offset =offset+3;
                  //CRC field
                  sixLoWPANParsedPacket.setCRC(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //Mhrlen field
                  sixLoWPANParsedPacket.setMhrlen(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //Channel field
                  sixLoWPANParsedPacket.setChannel(metadata.substring(offset,offset+3));
                  offset =offset+3;
                  //TimeStamp field
                  sixLoWPANParsedPacket.setTimeStamp(new ConvertImpl().ConHexaToDecimal(new ConvertImpl().removeSpaces(metadata.substring(offset,metadata.length()))));

             }
            //---------------- End Command Frame ----------------//

      }

      /***********************************************************/
      /* Function: dispatch                                      */
      /* Arguments : String: received packet payload             */
      /*		This argument represent the payload              */
      /*                content in the original MAC data frame   */
      /*                original packet                          */
      /* Return: short                                           */
      /* function determine the dispatch value                   */
      /***********************************************************/
      public static short dispatch(String payload, int index_firstByte) {
            String dispatch=payload.substring(index_firstByte+1,index_firstByte+3);
		if ((dispatch.equals("03"))||(dispatch.equals("04")))
                  return 1;
            else
                  return 2;
      }

      /***********************************************************/
      /* Function: parse6LoWPANFileds                            */
      /* Arguments : String: received packet payload             */
      /*		This argument represent the payload              */
      /*                content in the original MAC data frame   */
      /*                original packet                          */
      /* Return: Nothing                                         */
      /* function set 6LoWPAN fields                             */
      /***********************************************************/
      public static void parse6LoWPANFileds(String payload, int index_firstByte) {
            int offset=index_firstByte;
            short dispatch=dispatch(payload, index_firstByte);
            sixLOWPAN_header_length=0;
            if (dispatch == 1)
            {
                  //sixLoWPAN_type=1;
            		//dispatch field
            		sixLoWPANParsedPacket.setDispatchField(payload.substring(offset,offset+3));
            		offset=offset+4;
                  sixLOWPAN_header_length+=4;
            		//encoding field
            		sixLoWPANParsedPacket.setEncodingField(payload.substring(offset, offset+3));
            		offset=offset+3;
                  sixLOWPAN_header_length+=3;
            		//next header field
            		sixLoWPANParsedPacket.setNextHeaderField(payload.substring(offset, offset+3));
            		offset=offset+3;
                  sixLOWPAN_header_length+=3;
            		//hop limit field
            		sixLoWPANParsedPacket.setHopLimitField(payload.substring(offset,offset+3));
            		offset=offset+3;
                  sixLOWPAN_header_length+=3;
            		//source @ field
            		sixLoWPANParsedPacket.setSourceAddField("0x"+payload.substring(offset,offset+6));
            		offset=offset+6;
                  sixLOWPAN_header_length+=6;
            		//dest @ field
            		sixLoWPANParsedPacket.setDestAddField("0x"+payload.substring(offset,offset+6));
                  sixLOWPAN_header_length+=6;
            }
            else
            {
               //sixLoWPAN_type=2;
               sixLoWPANParsedPacket.setDispatchField(payload.substring(offset,offset+6));
               offset+=7;
               sixLOWPAN_header_length+=7;
               sixLoWPANParsedPacket.setEncodingField(payload.substring(offset, offset+2)+payload.substring(offset+3, offset+5));
               String binaryEncoding=new ConvertImpl().convertHexToBin(payload.substring(offset, offset+1))+new ConvertImpl().convertHexToBin(payload.substring(offset+1, offset+2))+new ConvertImpl().convertHexToBin(payload.substring(offset+3, offset+4))+new ConvertImpl().convertHexToBin(payload.substring(offset+4, offset+5));
               offset+=6;
               sixLOWPAN_header_length+=6;
               //context identifier
               if(binaryEncoding.substring(8,9).equals("0"))
                  sixLoWPANParsedPacket.setcontextField("compressed");
               else
               {
                  sixLoWPANParsedPacket.setcontextField(payload.substring(offset,offset+2));
                  offset+=3;
                  sixLOWPAN_header_length+=3;
               }
               //traffic class and flow label
               if (binaryEncoding.substring(3,5).equals("00"))
               {
                  sixLoWPANParsedPacket.settrafficClass_FlowLabelField(payload.substring(offset, offset+12));
                  offset+=12;
                  sixLOWPAN_header_length+=12;
               }
               else if (binaryEncoding.substring(3,5).equals("01"))
               {
                  sixLoWPANParsedPacket.settrafficClass_FlowLabelField(payload.substring(offset, offset+9));
                  offset+=9;
                  sixLOWPAN_header_length+=9;
               }
               else if (binaryEncoding.substring(3,5).equals("10"))
               {
                  sixLoWPANParsedPacket.settrafficClass_FlowLabelField(payload.substring(offset, offset+3));
                  offset+=3;
                  sixLOWPAN_header_length+=3;
               }
               else
                  sixLoWPANParsedPacket.settrafficClass_FlowLabelField("compressed");
               //Next header
               if (binaryEncoding.substring(5,6).equals("0"))
               {
                  sixLoWPANParsedPacket.setNextHeaderField(payload.substring(offset, offset+3));
                  offset+=3;
                  sixLOWPAN_header_length+=3;
               }
               else
                  sixLoWPANParsedPacket.setNextHeaderField("compressed");
               //hop Limit
               if (binaryEncoding.substring(6,8).equals("00"))
               {
                  sixLoWPANParsedPacket.setHopLimitField(payload.substring(offset, offset+3));
                  offset+=3;
                  sixLOWPAN_header_length+=3;
               }
               else
                  sixLoWPANParsedPacket.setHopLimitField("compressed");
               //Source Address
               if (binaryEncoding.substring(9,10).equals("0"))
               {
                  if (binaryEncoding.substring(10,12).equals("00"))
                  {
                     sixLoWPANParsedPacket.setSourceAddField(payload.substring(offset,offset+48));
                     offset+=48;
                     sixLOWPAN_header_length+=48;
                  }
                  else if (binaryEncoding.substring(10,12).equals("01"))
                  {
                     sixLoWPANParsedPacket.setSourceAddField(payload.substring(offset,offset+24));
                     offset+=24;
                     sixLOWPAN_header_length+=24;
                  }
                  else if (binaryEncoding.substring(10,12).equals("10"))
                  {
                     sixLoWPANParsedPacket.setSourceAddField(payload.substring(offset,offset+6));
                     offset+=6;
                     sixLOWPAN_header_length+=6;
                  }
                  else
                     sixLoWPANParsedPacket.setSourceAddField("compressed");
               }
               else
               {
                  if (binaryEncoding.substring(10,12).equals("01"))
                  {
                     sixLoWPANParsedPacket.setSourceAddField(payload.substring(offset,offset+24));
                     offset+=24;
                     sixLOWPAN_header_length+=24;
                  }
                  else if (binaryEncoding.substring(10,12).equals("10"))
                  {
                     sixLoWPANParsedPacket.setSourceAddField(payload.substring(offset,offset+6));
                     offset+=6;
                     sixLOWPAN_header_length+=6;
                  }
                  else if (binaryEncoding.substring(10,12).equals("11"))
                     sixLoWPANParsedPacket.setSourceAddField("compressed");
               }
               //destination Address
               if (binaryEncoding.substring(12,13).equals("0"))
               {
                  if (binaryEncoding.substring(13,14).equals("0"))
                  {
                     if (binaryEncoding.substring(14,16).equals("00"))
                     {
                        sixLoWPANParsedPacket.setDestAddField(payload.substring(offset, offset+48));
                        offset+=48;
                        sixLOWPAN_header_length+=48;
                     }
                     else if (binaryEncoding.substring(14,16).equals("01"))
                     {
                        sixLoWPANParsedPacket.setDestAddField(payload.substring(offset, offset+24));
                        offset+=24;
                        sixLOWPAN_header_length+=24;
                     }
                     else if (binaryEncoding.substring(14,16).equals("10"))
                     {
                        sixLoWPANParsedPacket.setDestAddField(payload.substring(offset, offset+6));
                        offset+=6;
                        sixLOWPAN_header_length+=6;
                     }
                     else
                     {
                        sixLoWPANParsedPacket.setDestAddField("compressed");
                     }
                  }
                  else
                  {
                     if (binaryEncoding.substring(14,16).equals("01"))
                     {
                        sixLoWPANParsedPacket.setDestAddField(payload.substring(offset, offset+24));
                        offset+=24;
                        sixLOWPAN_header_length+=24;
                     }
                     else if (binaryEncoding.substring(14,16).equals("10"))
                     {
                        sixLoWPANParsedPacket.setDestAddField(payload.substring(offset, offset+6));
                        offset+=6;
                        sixLOWPAN_header_length+=6;
                     }
                     else if (binaryEncoding.substring(14,16).equals("11"))
                     {
                        sixLoWPANParsedPacket.setDestAddField("compressed");
                     }
                  }
               }
               else
               {
                  if (binaryEncoding.substring(13,14).equals("0"))
                  {
                     if (binaryEncoding.substring(14,16).equals("00"))
                     {
                        sixLoWPANParsedPacket.setDestAddField(payload.substring(offset, offset+18));
                        offset+=18;
                        sixLOWPAN_header_length+=18;
                     }
                     else if (binaryEncoding.substring(14,16).equals("01"))
                     {
                        sixLoWPANParsedPacket.setDestAddField(payload.substring(offset, offset+12));
                        offset+=12;
                        sixLOWPAN_header_length+=12;
                     }
                     else if (binaryEncoding.substring(14,16).equals("10"))
                     {
                        sixLoWPANParsedPacket.setDestAddField(payload.substring(offset, offset+6));
                        offset+=6;
                        sixLOWPAN_header_length+=6;
                     }
                     else
                     {
                        sixLoWPANParsedPacket.setDestAddField(payload.substring(offset, offset+3));
                        offset+=3;
                        sixLOWPAN_header_length+=3;
                     }
                  }
                  else
                  {
                     if (binaryEncoding.substring(14,16).equals("00"))
                     {
                        sixLoWPANParsedPacket.setDestAddField(payload.substring(offset, offset+48));
                        offset+=48;
                        sixLOWPAN_header_length+=48;
                     }
                     else if (binaryEncoding.substring(14,16).equals("01"))
                     {
                        sixLoWPANParsedPacket.setDestAddField(payload.substring(offset, offset+18));
                        offset+=18;
                        sixLOWPAN_header_length+=18;
                     }
                  }
               }
            }
      }
	  
	  /***********************************************************/
      /* Function: parseechoRequest                    			 */
      /* Arguments : String: received packet stream        		 */
      /*	 This argument represent the original packet    	 */
      /*           : int: first byte                             */
      /*     This argument represents the index of the first byte*/
      /* Return: Nothing										 */
      /* function decode echo request or echo reply header 	     */
      /***********************************************************/
      public static void parseechoRequest(String receivedPacketStream, int indexFirstByte) 
      {
    		int count=indexFirstByte;
		icmp_header_length=0;
            	ICMPParsedPacket.echoRequestHeader.seticmp_Type(receivedPacketStream.substring(count,count+2));
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.echoRequestHeader.seticmp_Code(receivedPacketStream.substring(count,count+2));
  		count+=3;
  		icmp_header_length+=3;
		ICMPParsedPacket.echoRequestHeader.seticmp_Cheksum(receivedPacketStream.substring(count,count+2)+receivedPacketStream.substring(count+3,count+5));
		count+=6;
		icmp_header_length+=6;
		ICMPParsedPacket.echoRequestHeader.setIdentifier(receivedPacketStream.substring(count,count+5));
		count+=6;
		icmp_header_length+=6;
		ICMPParsedPacket.echoRequestHeader.setSequenceNumber(receivedPacketStream.substring(count,count+5));
		count+=6;
		icmp_header_length+=6;
		
      }  
      /********************************************************************/
      /* Function: parseRouterSolicitation                    		      */
      /* Arguments : String: received packet stream        		          */
      /*	     This argument represent the original packet              */
      /*           : int: first byte                                      */
      /*             This argument represents the index of the first byte */
      /* Return: Nothing						                          */
      /* function decode router solicitation header  		              */
      /********************************************************************/
      public static void parseRouterSolicitation(String receivedPacketStream, int indexFirstByte) 
      {
    		int count=indexFirstByte;
		icmp_header_length=0;
            	ICMPParsedPacket.routerSolicitHeader.seticmp_Type(receivedPacketStream.substring(count,count+2));
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.routerSolicitHeader.seticmp_Code(receivedPacketStream.substring(count,count+2));
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.routerSolicitHeader.seticmp_Cheksum(receivedPacketStream.substring(count,count+2)+receivedPacketStream.substring(count+3,count+5));
		count+=6;
		icmp_header_length+=6;
      }
      /********************************************************************/
      /* Function: parseDIS                    			                  */
      /* Arguments : String: received packet stream        		          */
      /*	     This argument represent the original packet              */
      /*           : int: first byte                                      */
      /*             This argument represents the index of the first byte */
      /* Return: Nothing						                          */
      /* function decode DIS header 	                                  */
      /********************************************************************/
      public static void parseDIS (String receivedPacketStream, int indexFirstByte) 
      {
    		int count=indexFirstByte;
		icmp_header_length=0;
            	ICMPParsedPacket.disHeader.seticmp_Type(receivedPacketStream.substring(count,count+2));
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.disHeader.seticmp_Code(receivedPacketStream.substring(count,count+2));
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.disHeader.seticmp_Cheksum(receivedPacketStream.substring(count,count+2)+receivedPacketStream.substring(count+3,count+5));
		count+=6;
		icmp_header_length+=6;
		ICMPParsedPacket.disHeader.setFlags(receivedPacketStream.substring(count,count+2));	
		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.disHeader.setReserved_icmp(receivedPacketStream.substring(count,count+2));	
		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.disHeader.setSolicitedInformationType(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.disHeader.setSolicitedInformationLength(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.disHeader.setSolicitedInformationInstance(receivedPacketStream.substring(count,count+2));	
		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.disHeader.setSolicitedInformationFlags(receivedPacketStream.substring(count,count+2));	
		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.disHeader.setSolicitedInformationDODAGID(receivedPacketStream.substring(count,count+47));	
		count+=48;
		icmp_header_length+=48;
		ICMPParsedPacket.disHeader.setSolicitedInformationVersion(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
      }  
      /*********************************************************************/
      /* Function: parseRouterAdvertisement                    		       */
      /* Arguments : String: received packet stream        		           */
      /*	     This argument represent the original packet               */
      /*           : int: first byte                                       */
      /*             This argument represents the index of the first byte  */
      /* Return: Nothing						                           */
      /* function decode router advertisement header  		               */
      /*********************************************************************/
      public static void parseRouterAdvertisement(String receivedPacketStream, int indexFirstByte) 
      {
    		int count=indexFirstByte;
		icmp_header_length=0;
            	ICMPParsedPacket.routerAdverHeader.seticmp_Type(receivedPacketStream.substring(count,count+2));
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.routerAdverHeader.seticmp_Code(receivedPacketStream.substring(count,count+2));
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.routerAdverHeader.seticmp_Cheksum(receivedPacketStream.substring(count,count+2)+receivedPacketStream.substring(count+3,count+5));
 		count+=6;
		icmp_header_length+=6;
		ICMPParsedPacket.routerAdverHeader.setCurHopLimit(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;	
		ICMPParsedPacket.routerAdverHeader.setFlags(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.routerAdverHeader.setRouterLifetime(receivedPacketStream.substring(count,count+5));	
  		count+=6;
		icmp_header_length+=6;
		ICMPParsedPacket.routerAdverHeader.setReachableTime(receivedPacketStream.substring(count,count+11));	
  		count+=12;
		icmp_header_length+=12;
		ICMPParsedPacket.routerAdverHeader.setRetansTimer(receivedPacketStream.substring(count,count+11));	
  		count+=12;
		icmp_header_length+=12;
		ICMPParsedPacket.routerAdverHeader.setPrefixInfoOption(receivedPacketStream.substring(count,count+95));	
  		count+=96;
		icmp_header_length+=96;
		ICMPParsedPacket.routerAdverHeader.setAddressIPOption(receivedPacketStream.substring(count,count+32));	
  		count+=33;	
		icmp_header_length+=33;
      }   
      /********************************************************************/
      /* Function: parseDIO                   			                  */
      /* Arguments : String: received packet stream        		          */
      /*	     This argument represent the original packet              */
      /*           : int: first byte                                      */
      /*             This argument represents the index of the first byte */
      /* Return: Nothing						                          */
      /* function decode DIO header 	                                  */
      /********************************************************************/
      public static void parseDIO (String receivedPacketStream, int indexFirstByte) 
      {
    		int count=indexFirstByte;
		icmp_header_length=0;
            	ICMPParsedPacket.dioHeader.seticmp_Type(receivedPacketStream.substring(count,count+2));
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.dioHeader.seticmp_Code(receivedPacketStream.substring(count,count+2));
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.dioHeader.seticmp_Cheksum(receivedPacketStream.substring(count,count+2)+receivedPacketStream.substring(count+3,count+5));
		count+=6;
		icmp_header_length+=6;
		ICMPParsedPacket.dioHeader.setRPLInstanceID(receivedPacketStream.substring(count,count+2));	
		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.dioHeader.setVersion(receivedPacketStream.substring(count,count+2));	
		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.dioHeader.setRank(receivedPacketStream.substring(count,count+5));	
  		count+=6;
		icmp_header_length+=6;
		ICMPParsedPacket.dioHeader.setFlags(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.dioHeader.setDATSN(receivedPacketStream.substring(count,count+2));	
		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.dioHeader.setFlags2(receivedPacketStream.substring(count,count+2));	
		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.dioHeader.setReserved_icmp(receivedPacketStream.substring(count,count+2));	
		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.dioHeader.setDODAGID(receivedPacketStream.substring(count,count+47));	
  		count+=48;
		icmp_header_length+=48;
		ICMPParsedPacket.dioHeader.setMetricContainerType(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.dioHeader.setMetricContainerLength(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.dioHeader.setDODAGConfigurationType(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.dioHeader.setDODAGConfigurationLength(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.dioHeader.setDODAGConfigurationFlags(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.dioHeader.setDODAGConfigurationDIOIntervalDoubling(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.dioHeader.setDODAGConfigurationDIOIntervalMin(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.dioHeader.setDODAGConfigurationDIORedundancyConstant(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.dioHeader.setDODAGConfigurationMaxRank(receivedPacketStream.substring(count,count+5));	
  		count+=6;
		icmp_header_length+=6;
		ICMPParsedPacket.dioHeader.setDODAGConfigurationMinHopRank(receivedPacketStream.substring(count,count+5));	
  		count+=6;
		icmp_header_length+=6;
		ICMPParsedPacket.dioHeader.setDODAGConfigurationOCP(receivedPacketStream.substring(count,count+5));	
  		count+=6;
		icmp_header_length+=6;
		ICMPParsedPacket.dioHeader.setDODAGConfigurationReserved(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.dioHeader.setDODAGConfigurationDefaultLifetime(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.dioHeader.setDODAGConfigurationLifetimeUnit(receivedPacketStream.substring(count,count+5));	
  		count+=6;
		icmp_header_length+=6;
		ICMPParsedPacket.dioHeader.setPrefixInformationType(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.dioHeader.setPrefixInformationLength(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.dioHeader.setPrefixInformation_PrefixLength(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.dioHeader.setPrefixInformationFlags(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.dioHeader.setPrefixInformationValidLifetime(receivedPacketStream.substring(count,count+11));	
  		count+=12;
		icmp_header_length+=12;
		ICMPParsedPacket.dioHeader.setPrefixInformationPreferredLifetime(receivedPacketStream.substring(count,count+11));	
  		count+=12;
		icmp_header_length+=12;
		ICMPParsedPacket.dioHeader.setPrefixInformationReserved(receivedPacketStream.substring(count,count+11));	
  		count+=12;
		icmp_header_length+=12;
		ICMPParsedPacket.dioHeader.setPrefixInformationDestinationPrefix(receivedPacketStream.substring(count,count+47));	
  		count+=48;
		icmp_header_length+=48;
      }  
      /*********************************************************************/
      /* Function: parseDAO                   			                   */
      /* Arguments : String: received packet stream        		           */
      /*	     This argument represent the original packet               */
      /*           : int: first byte                                       */
      /*             This argument represents the index of the first byte  */
      /* Return: Nothing						                           */
      /* function decode DAO header 	                                   */
      /*********************************************************************/
      public static void parseDAO (String receivedPacketStream, int indexFirstByte) 
      {
    		int count=indexFirstByte;
		icmp_header_length=0;
            	ICMPParsedPacket.daoHeader.seticmp_Type(receivedPacketStream.substring(count,count+2));
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.daoHeader.seticmp_Code(receivedPacketStream.substring(count,count+2));
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.daoHeader.seticmp_Cheksum(receivedPacketStream.substring(count,count+2)+receivedPacketStream.substring(count+3,count+5));
		count+=6;
		icmp_header_length+=6;
		ICMPParsedPacket.daoHeader.setRPLInstanceID(receivedPacketStream.substring(count,count+2));	
		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.daoHeader.setFlags(receivedPacketStream.substring(count,count+2));	
		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.daoHeader.setReserved_icmp(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.daoHeader.setDAOSequence(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.daoHeader.setRPLTargetType(receivedPacketStream.substring(count,count+2));	
		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.daoHeader.setRPLTargetLength(receivedPacketStream.substring(count,count+2));	
		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.daoHeader.setRPLTargetReserved(receivedPacketStream.substring(count,count+2));	
		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.daoHeader.setRPLTarget_TargetLength(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.daoHeader.setRPLTarget_Target(receivedPacketStream.substring(count,count+47));	
  		count+=48;
		icmp_header_length+=48;
		ICMPParsedPacket.daoHeader.setTransitInformationType(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.daoHeader.setTransitInformationLength(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.daoHeader.setTransitInformationFlags(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.daoHeader.setTransitInformationPathControl(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.daoHeader.setTransitInformationPathSequence(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
		ICMPParsedPacket.daoHeader.setTransitInformationPathLifetime(receivedPacketStream.substring(count,count+2));	
  		count+=3;
		icmp_header_length+=3;
      }  
      /**************************************************************/
      /* Function: parseICMPHeaders            	                    */
      /* Arguments : String: received packet payload        	    */
      /*		This argument represent the payload                 */
      /*                content in the original MAC data frame      */
      /*                original packet                             */
      /*           :int: the index of the first byte in ICMP header */
      /* Return: Nothing                 			                */
      /* function parse all ICMP headers     			            */
      /**************************************************************/
      public static void parseICMPHeaders(String receivedPacketStream, int indexFirstByte) 
      {
		//ICMPParsedPacket = new ICMPStruct();
		String type=receivedPacketStream.substring(indexFirstByte, indexFirstByte+2);
		int count=indexFirstByte;
		if ((type.equals("80"))||(type.equals("81"))){ //echo request and echo reply packets
         		ICMPParsedPacket.ICMPType = "echoRequestHeader";
         		ICMPParsedPacket.echoRequestHeader = new EchorequestImpl(sixLoWPANParsedPacket);
			parseechoRequest(receivedPacketStream, indexFirstByte);
      		}else if ((type.equals("9B"))&&(receivedPacketStream.substring(indexFirstByte+3, indexFirstByte+5).equals("02"))){
			ICMPParsedPacket.ICMPType = "DAO";
         		ICMPParsedPacket.daoHeader = new DODAGAdvertisementObjectImpl(sixLoWPANParsedPacket);
			parseDAO (receivedPacketStream, indexFirstByte);
		}else if ((type.equals("9B"))&&(receivedPacketStream.substring(indexFirstByte+3, indexFirstByte+5).equals("01"))){
			ICMPParsedPacket.ICMPType = "DIO";
         		ICMPParsedPacket.dioHeader = new DODAGInformationObjectImpl(sixLoWPANParsedPacket);
			parseDIO (receivedPacketStream, indexFirstByte); 
		}else if (type.equals("86")){
			ICMPParsedPacket.ICMPType = "RouterAdvertisement";
         		ICMPParsedPacket.routerAdverHeader = new RouterAdvertisementImpl(sixLoWPANParsedPacket);
			parseRouterAdvertisement(receivedPacketStream,indexFirstByte); 
		}else if (type.equals("85")){
			ICMPParsedPacket.ICMPType = "RouterSolicitation";
         		ICMPParsedPacket.routerSolicitHeader = new RouterSolicitationImpl(sixLoWPANParsedPacket);
			parseRouterSolicitation(receivedPacketStream, indexFirstByte);
		}else if ((type.equals("9B"))&&(receivedPacketStream.substring(indexFirstByte+3, indexFirstByte+5).equals("00"))){
			ICMPParsedPacket.ICMPType = "DIS";
         		ICMPParsedPacket.disHeader = new DODAGInformationSolicitationImpl(sixLoWPANParsedPacket);
			parseDIS (receivedPacketStream, indexFirstByte);
		}
		if (ICMPParsedPacket.ICMPType != "RouterSolicitation")
			sixLoWPANParsedPacket.set6LoWPANPayload(receivedPacketStream.substring(IEEE_header_length+sixLOWPAN_header_length+icmp_header_length, receivedPacketStream.length()-6));
      }
      

      /**************************************************************************/
      /* Function: IEEE802_15_4ParsePacket                                      */
      /* Arguments : String: packet stream                                      */
      /*             this arguments receive the original packet from Zsniffer   */
      /*             double: arrivalTime for packet stream                      */
      /*             this arguments receive the arrival time for packet in      */
      /*             number of seconds from                                     */
      /* Return: IEEE802_15_4: parsed packet                                    */
      /* function parse the packet by calling the function that set the fields  */
      /**************************************************************************/
      public static IEEE802_15_4 IEEE802_15_4ParsePacket(String receivedPacketStream, double arrivalTime) {

            IEEE802_15_4ParsedPacket = new IEEE802_15_4Impl();
            IEEE802_15_4ParsedPacket.setPacketType(IEEE802_15_4Type(receivedPacketStream));//get the IEEE802.15.4 header type
            int offset = 5;
            // frame control field
            String frameControl = receivedPacketStream.substring(offset,offset+6);
            // type of packets
            String SecondByte = frameControl.substring(1,2);
            String SecondByteToBin = new ConvertImpl().convertHexToBin(SecondByte);
            //Intra_PAN
            IEEE802_15_4ParsedPacket.setIntra_PAN(Short.parseShort(SecondByteToBin.substring(1,2)));
	        	//frame control field
	    String ThirdByte = frameControl.substring(5,6);
	    String ThirdByteToBin = new ConvertImpl().convertHexToBin(ThirdByte);
            //DestAddMode
            IEEE802_15_4ParsedPacket.setDestAddMode(Short.parseShort(ThirdByteToBin.substring(0,2)));
            
            String ForthByte = frameControl.substring(4,5);
            String ForthByteToBin = new ConvertImpl().convertHexToBin(ForthByte);
            //SrcAddMode
            IEEE802_15_4ParsedPacket.setSrcAddMode(Short.parseShort(ForthByteToBin.substring(0,2)));

            //parse IEEE802.15.4 fields
            parseIEEE802_15_4Fileds(receivedPacketStream,arrivalTime, IEEE802_15_4ParsedPacket);

            return IEEE802_15_4ParsedPacket;

      }

	  /*************************************************************************/
      /* Function: ICMPParsePacket		                                       */
      /* Arguments : String: packet stream                                     */
      /*             this arguments receive the original packet from Zsniffer  */
      /*             double: arrivalTime for packet stream                     */
      /*             this arguments receive the arrival time for packet in     */
      /*             number of seconds from                                    */
      /* Return: ICMPStruct: parsed packet                                     */
      /* function parse the packet by calling the function that set the fields */
      /*************************************************************************/
      public static ICMPStruct ICMPParsePacket(String receivedPacketStream, double arrivalTime) {
	    String payload="";
            sixLoWPANParsedPacket = new sixLoWPANImpl();
            ICMPParsedPacket = new ICMPStruct();
            sixLoWPANParsedPacket.setPacketType(IEEE802_15_4Type(receivedPacketStream));//get the IEEE802.15.4 header type
            int offset = 5;
            // frame control field
            String frameControl = receivedPacketStream.substring(offset,offset+6);
            // type of packets
            String SecondByte = frameControl.substring(1,2);
            String SecondByteToBin = new ConvertImpl().convertHexToBin(SecondByte);
            //Intra_PAN
            sixLoWPANParsedPacket.setIntra_PAN(Short.parseShort(SecondByteToBin.substring(1,2)));
	        	//frame control field
	    String ThirdByte = frameControl.substring(5,6);
	    String ThirdByteToBin = new ConvertImpl().convertHexToBin(ThirdByte);
            //DestAddMode
            sixLoWPANParsedPacket.setDestAddMode(Short.parseShort(ThirdByteToBin.substring(0,2)));

            String ForthByte = frameControl.substring(4,5);
            String ForthByteToBin = new ConvertImpl().convertHexToBin(ForthByte);
            //SrcAddMode
            sixLoWPANParsedPacket.setSrcAddMode(Short.parseShort(ForthByteToBin.substring(0,2)));
            //parse IEEE802.15.4 fields
            parseIEEE802_15_4Fileds(receivedPacketStream,arrivalTime, sixLoWPANParsedPacket);
            if (sixLoWPANParsedPacket.getPacketType() == 1){
                  parse6LoWPANFileds(receivedPacketStream, IEEE_header_length);
                  sixLowPANType(receivedPacketStream, IEEE_header_length+sixLOWPAN_header_length);
                  parseICMPHeaders(receivedPacketStream, IEEE_header_length+sixLOWPAN_header_length);
            }
      	    ICMPParsedPacket.sixLoWPANPacket = sixLoWPANParsedPacket;
            return ICMPParsedPacket;

      }
    
}
