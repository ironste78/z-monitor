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
* fileStorage: This class allow the write of packets in a file, using plain text or XML format
*/

import java.io.*;
import java.util.*;
import Classes.Support.*;
import Classes.Packet.*;
import Classes.*;

public class fileStorage
{
    //Class Attribute
    buffer packetBuffer;
    //-----------------------------------------------//

    //Constructor
    public fileStorage(buffer packetValue)
    {
        packetBuffer=packetValue;
    }
    //-----------------------------------------------//

    /**********************************************************************/
    /* Function: xmlFormat                                                */
    /* Arguments : String: CurrentDateAndTime                             */
    /*                     these argument is the current time and         */
    /*                     date to use it as a name of the result file    */
    /*           : IEEE802_15_4: protocolType                             */
    /*                           these variable used to know the protocol */
    /* Return: Nothing                                                    */
    /* This method presents the parse packet in XML format                */
    /**********************************************************************/
    public void xmlFormat(String CurrentDateAndTime, IEEE802_15_4 protocolType)
    {
        try{
                   String fileName=CurrentDateAndTime;
                    // define history directory
                    File dir=new File("History");
                    // create history directory in case of not exists
                     if(!dir.exists())
                            dir.mkdir();
                    // create history directory in case of exist but not directory
                    if(!dir.isDirectory())
                            dir.mkdir();
                    //create file with this name savedPackets_(date with remove all not allowed signs)
                    File packets = new File("History/savedPackets_"+fileName+".xml");
                    if(!packets.exists())
                    packets.createNewFile();
                            // define the file writer to write on the file
                    FileWriter fstream = new FileWriter(packets,true);
                    // define buffer of writer
                    BufferedWriter out = new BufferedWriter(fstream);
                    // write on the top of file how many packets did the sniffer sniff
                    out.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
		    out.write("\n<Packet_Analysis>");
                    // adding the details of packets
                   Vector<String> parsePackets=packetBuffer.getOrigonalPackets();
                   Vector<Double> parseTimes=packetBuffer.getOrigonalTimes();
                    for(int i=0; i<parsePackets.size();i++)
                    {
                        out.write("\n<Packet_Number_"+i+">");

                        String receivedPacketStream = parsePackets.get(i);
                        double realArrivaltime= parseTimes.get(i);
                        IEEE802_15_4 parsedPacket =parser.IEEE802_15_4ParsePacket(receivedPacketStream,realArrivaltime);
                        short IEEECase = new parser().IEEE802_15_4Case(parsedPacket.getDestAddMode(), parsedPacket.getSrcAddMode(),parsedPacket.getIntra_PAN());

                        if(parsedPacket.getPacketType()==0)
                        {
                                   out.write("\n<Timestamp>"+NtpMessage.timestampToString(parsedPacket.getRealArrivalTime())+"</Timestamp>");
                                    out.write("\n<Frame>"+i+"</Frame>");
                                    out.write("\n<Length>"+parsedPacket.getLength().trim()+"</Length>");
                                    out.write("\n<Frame_control_field>");
                                    out.write("\n<Type>"+parsedPacket.getFrameCon()[0].trim()+"</Type>");
                                    out.write("\n<Sec>"+parsedPacket.getFrameCon()[1].trim()+"</Sec>");
                                    out.write("\n<Pnd>"+parsedPacket.getFrameCon()[2].trim()+"</Pnd>");
                                    out.write("\n<Ack_req>"+parsedPacket.getFrameCon()[3].trim()+"</Ack_req>");
                                    out.write("\n<Intra_PAN>"+parsedPacket.getFrameCon()[4].trim()+"</Intra_PAN>");
                                    out.write("\n</Frame_control_field>");
                                    out.write("\n<Sequence_Number>"+parsedPacket.getSeq().trim()+"</Sequence_Number>");
                                    out.write("\n<Source_PAN>"+parsedPacket.getSrcPAN().trim()+"</Source_PAN>");
                                    out.write("\n<Source_Address>"+parsedPacket.getSrcADD().trim()+"</Source_Address>");
                                    out.write("\n<Super_frame_specification>");
                                    out.write("\n<BO>"+parsedPacket.getBO()+"</BO");
                                    out.write("\n<SO>"+parsedPacket.getSO()+"</SO");
                                    out.write("\n<Fin_CAP>"+parsedPacket.getFinCap()+"</Fin_CAP>");
                                    out.write("\n<BLE>"+parsedPacket.getbat()+"</BLE>");
                                    out.write("\n<Coord>"+parsedPacket.getPAN()+"</Coord>");
                                    out.write("\n<Assoc>"+parsedPacket.getass()+"</Assoc>");
                                    out.write("\n</Super_frame _specification>");
                                    out.write("\n<GTS>"+parsedPacket.getGTS().trim()+"</GTS>");
                                    out.write("\n<Pending_Address>"+parsedPacket.getPending().trim()+"</Pending_Address>");
                                    out.write("\n<Beacon_Payload>"+parsedPacket.getPayload().trim()+"</Beacon_Payload>");
                                    out.write("\n<LQI>"+parsedPacket.getLQI().trim()+"</LQI>");
                                    out.write("\n<MAC_Header_Length>"+parsedPacket.getMhrlen().trim()+"</MAC_Header_Length>");
                                    out.write("\n<Channel>"+parsedPacket.getChannel().trim()+"</Channel>");
                                    out.write("\n<Dispatch_ID>"+parsedPacket.getDispID().trim()+"</Dispatch_ID>");
                                    out.write("\n<RSSI>"+parsedPacket.getRSSI().trim()+"</RSSI>");
                                    out.write("\n<CRC>"+parsedPacket.getCRC().trim()+"</CRC>");
                        }
                        else if(parsedPacket.getPacketType()==1)
                        {
                                    out.write("\n<Timestamp>"+NtpMessage.timestampToString(parsedPacket.getRealArrivalTime())+"</Timestamp>");
                                    out.write("\n<Frame>"+i+"</Frame>");
                                    out.write("\n<Length>"+parsedPacket.getLength().trim()+"</Length>");
                                    out.write("\n<Frame_control_field>");
                                    out.write("\n<Type>"+parsedPacket.getFrameCon()[0].trim()+"</Type>");
                                    out.write("\n<Sec>"+parsedPacket.getFrameCon()[1].trim()+"</Sec>");
                                    out.write("\n<Pnd>"+parsedPacket.getFrameCon()[2].trim()+"</Pnd>");
                                    out.write("\n<Ack_req>"+parsedPacket.getFrameCon()[3].trim()+"</Ack_req>");
                                    out.write("\n<Intra_PAN>"+parsedPacket.getFrameCon()[4].trim()+"</Intra_PAN>");
                                    out.write("\n</Frame_control_field>");
                                    out.write("\n<Sequence_Number>"+parsedPacket.getSeq().trim()+"</Sequence_Number>");
                                    out.write("\n<Destination_PAN>"+parsedPacket.getDestPAN().trim()+"</Destination_PAN>");
                                    out.write("\n<Destination_Address>"+parsedPacket.getDestADD().trim()+"</Destination_Address>");
                                    out.write("\n<Source_PAN>"+parsedPacket.getSrcPAN().trim()+"</Source_PAN>");
                                    out.write("\n<Source_Address>"+parsedPacket.getSrcADD().trim()+"</Source_Address>");
                                    out.write("\n<Payload>"+new ConvertImpl().removeSpaces(parsedPacket.getPayload().trim())+"</Payload>");
                                    out.write("\n<Type>"+parsedPacket.getType().trim()+"</Type>");
                                    out.write("\n<Code>"+parsedPacket.getCode().trim()+"</Code>");
                                    out.write("\n<Cheksum>"+parsedPacket.getCheksum().trim()+"</Cheksum>");
                                    out.write("\n<Payload>"+parsedPacket.getPayload().trim()+"</Payload>");
                                    out.write("\n<FCS>"+parsedPacket.getFCS().trim()+"</FCS>");
                        }
                        else if(parsedPacket.getPacketType()==2)
                        {
                                    out.write("\n<Timestamp>"+NtpMessage.timestampToString(parsedPacket.getRealArrivalTime())+"</Timestamp>");
                                    out.write("\n<Frame>"+i+"</Frame>");
                                    out.write("\n<Length>"+parsedPacket.getLength().trim()+"</Length>");
                                    out.write("\n<Frame_control_field>");
                                    out.write("\n<Type>"+parsedPacket.getFrameCon()[0].trim()+"</Type>");
                                    out.write("\n<Sec>"+parsedPacket.getFrameCon()[1].trim()+"</Sec>");
                                    out.write("\n<Pnd>"+parsedPacket.getFrameCon()[2].trim()+"</Pnd>");
                                    out.write("\n<Ack_req>"+parsedPacket.getFrameCon()[3].trim()+"</Ack_req>");
                                    out.write("\n<Intra_PAN>"+parsedPacket.getFrameCon()[4].trim()+"</Intra_PAN>");
                                    out.write("\n</Frame_control_field>");
                                    out.write("\n<Sequence_Number>"+parsedPacket.getSeq().trim()+"</Sequence_Number>");
                                    out.write("\n<LQI>"+parsedPacket.getLQI().trim()+"</LQI>");
                                    out.write("\n<MAC_Header_Length>"+parsedPacket.getMhrlen().trim()+"</MAC_Header_Length>");
                                    out.write("\n<Channel>"+parsedPacket.getChannel().trim()+"</Channel>");
                                    out.write("\n<Dispatch_ID>"+parsedPacket.getDispID().trim()+"</Dispatch_ID>");
                                    out.write("\n<RSSI>"+parsedPacket.getRSSI().trim()+"</RSSI>");
                                    out.write("\n<CRC>"+parsedPacket.getCRC().trim()+"</CRC>");
                        }
                        else if(parsedPacket.getPacketType()==3)
                        {
                                    out.write("\n<Timestamp>"+NtpMessage.timestampToString(parsedPacket.getRealArrivalTime())+"</Timestamp>");
                                    out.write("\n<Frame>"+i+"</Frame>");
                                    out.write("\n<Length>"+parsedPacket.getLength().trim()+"</Length>");
                                    out.write("\n<Frame_control_field>");
                                    out.write("\n<Type>"+parsedPacket.getFrameCon()[0].trim()+"</Type>");
                                    out.write("\n<Sec>"+parsedPacket.getFrameCon()[1].trim()+"</Sec>");
                                    out.write("\n<Pnd>"+parsedPacket.getFrameCon()[2].trim()+"</Pnd>");
                                    out.write("\n<Ack_req>"+parsedPacket.getFrameCon()[3].trim()+"</Ack_req>");
                                    out.write("\n<Intra_PAN>"+parsedPacket.getFrameCon()[4].trim()+"</Intra_PAN>");
                                    out.write("\n</Frame_control_field>");
                                    out.write("\n<Sequence_Number>"+parsedPacket.getSeq().trim()+"</Sequence_Number>");
                                    out.write("\n<Destination_PAN>"+parsedPacket.getDestPAN().trim()+"</Destination_PAN>");
                                    out.write("\n<Destination_Address>"+parsedPacket.getDestADD().trim()+"</Destination_Address>");
                                    out.write("\n<Source_PAN>"+parsedPacket.getDestPAN().trim()+"</Source_PAN>");
                                    out.write("\n<Source_Address>"+parsedPacket.getDestADD().trim()+"</Source_Address>");
                                    out.write("\n<Command_Payload>"+parsedPacket.getDestADD().trim()+"</Command_Payload>");
                                    out.write("\n<LQI>"+parsedPacket.getLQI().trim()+"</LQI>");
                                    out.write("\n<MAC_Header_Length>"+parsedPacket.getMhrlen().trim()+"</MAC_Header_Length>");
                                    out.write("\n<Channel>"+parsedPacket.getChannel().trim()+"</Channel>");
                                    out.write("\n<Dispatch_ID>"+parsedPacket.getDispID().trim()+"</Dispatch_ID>");
                                    out.write("\n<RSSI>"+parsedPacket.getRSSI().trim()+"</RSSI>");
                                    out.write("\n<CRC>"+parsedPacket.getCRC().trim()+"</CRC>");
                        }

                        out.write("</Packet_Number_"+i+">");
                    }
                    out.newLine();
                    out.write("</Packet_Analysis>");
                    out.close();

        }catch(Exception ex){}

    }

    public void xmlFormat(String CurrentDateAndTime, sixLoWPAN protocolType)
    {
        try{
                   String fileName=CurrentDateAndTime;
                    // define history directory
                    File dir=new File("History");
                    // create history directory in case of not exists
                     if(!dir.exists())
                            dir.mkdir();
                    // create history directory in case of exist but not directory
                    if(!dir.isDirectory())
                            dir.mkdir();
                    //create file with this name savedPackets_(date with remove all not allowed signs)
                    File packets = new File("History/savedPackets_"+fileName+".xml");
                    if(!packets.exists())
                    packets.createNewFile();
                            // define the file writer to write on the file
                    FileWriter fstream = new FileWriter(packets,true);
                    // define buffer of writer
                    BufferedWriter out = new BufferedWriter(fstream);
                    // write on the top of file how many packets did the sniffer sniff
                    out.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
		    out.write("\n<Packet_Analysis>");
                    // adding the details of packets
                   Vector<String> parsePackets=packetBuffer.getOrigonalPackets();
                   Vector<Double> parseTimes=packetBuffer.getOrigonalTimes();
                    for(int i=0; i<parsePackets.size();i++)
                    {
                        out.write("\n<Packet_Number_"+i+">");

                        String receivedPacketStream = parsePackets.get(i);
                        double realArrivaltime= parseTimes.get(i);
                        ICMPStruct parsedPacket =parser.ICMPParsePacket(receivedPacketStream,realArrivaltime);
                        short IEEECase = new parser().IEEE802_15_4Case(parsedPacket.sixLoWPANPacket.getDestAddMode(), parsedPacket.sixLoWPANPacket.getSrcAddMode(),parsedPacket.sixLoWPANPacket.getIntra_PAN());

                        if(parsedPacket.sixLoWPANPacket.getPacketType()==0)
                        {
                                   out.write("\n<Timestamp>"+NtpMessage.timestampToString(parsedPacket.sixLoWPANPacket.getRealArrivalTime())+"</Timestamp>");
                                    out.write("\n<Frame>"+i+"</Frame>");
                                    out.write("\n<Length>"+parsedPacket.sixLoWPANPacket.getLength().trim()+"</Length>");
                                    out.write("\n<Frame_control_field>");
                                    out.write("\n<Type>"+parsedPacket.sixLoWPANPacket.getFrameCon()[0].trim()+"</Type>");
                                    out.write("\n<Sec>"+parsedPacket.sixLoWPANPacket.getFrameCon()[1].trim()+"</Sec>");
                                    out.write("\n<Pnd>"+parsedPacket.sixLoWPANPacket.getFrameCon()[2].trim()+"</Pnd>");
                                    out.write("\n<Ack_req>"+parsedPacket.sixLoWPANPacket.getFrameCon()[3].trim()+"</Ack_req>");
                                    out.write("\n<Intra_PAN>"+parsedPacket.sixLoWPANPacket.getFrameCon()[4].trim()+"</Intra_PAN>");
                                    out.write("\n</Frame_control_field>");
                                    out.write("\n<Sequence_Number>"+parsedPacket.sixLoWPANPacket.getSeq().trim()+"</Sequence_Number>");
                                    out.write("\n<Source_PAN>"+parsedPacket.sixLoWPANPacket.getSrcPAN().trim()+"</Source_PAN>");
                                    out.write("\n<Source_Address>"+parsedPacket.sixLoWPANPacket.getSrcADD().trim()+"</Source_Address>");
                                    out.write("\n<Super_frame_specification>");
                                    out.write("\n<BO>"+parsedPacket.sixLoWPANPacket.getBO()+"</BO");
                                    out.write("\n<SO>"+parsedPacket.sixLoWPANPacket.getSO()+"</SO");
                                    out.write("\n<Fin_CAP>"+parsedPacket.sixLoWPANPacket.getFinCap()+"</Fin_CAP>");
                                    out.write("\n<BLE>"+parsedPacket.sixLoWPANPacket.getbat()+"</BLE>");
                                    out.write("\n<Coord>"+parsedPacket.sixLoWPANPacket.getPAN()+"</Coord>");
                                    out.write("\n<Assoc>"+parsedPacket.sixLoWPANPacket.getass()+"</Assoc>");
                                    out.write("\n</Super_frame _specification>");
                                    out.write("\n<GTS>"+parsedPacket.sixLoWPANPacket.getGTS().trim()+"</GTS>");
                                    out.write("\n<Pending_Address>"+parsedPacket.sixLoWPANPacket.getPending().trim()+"</Pending_Address>");
                                    out.write("\n<Beacon_Payload>"+parsedPacket.sixLoWPANPacket.getPayload().trim()+"</Beacon_Payload>");
                                    out.write("\n<LQI>"+parsedPacket.sixLoWPANPacket.getLQI().trim()+"</LQI>");
                                    out.write("\n<MAC_Header_Length>"+parsedPacket.sixLoWPANPacket.getMhrlen().trim()+"</MAC_Header_Length>");
                                    out.write("\n<Channel>"+parsedPacket.sixLoWPANPacket.getChannel().trim()+"</Channel>");
                                    out.write("\n<Dispatch_ID>"+parsedPacket.sixLoWPANPacket.getDispID().trim()+"</Dispatch_ID>");
                                    out.write("\n<RSSI>"+parsedPacket.sixLoWPANPacket.getRSSI().trim()+"</RSSI>");
                                    out.write("\n<CRC>"+parsedPacket.sixLoWPANPacket.getCRC().trim()+"</CRC>");
                        }
                        else if(parsedPacket.sixLoWPANPacket.getPacketType()==1)
                        {
                                    out.write("\n<Timestamp>"+NtpMessage.timestampToString(parsedPacket.sixLoWPANPacket.getRealArrivalTime())+"</Timestamp>");
                                    out.write("\n<Frame>"+i+"</Frame>");
                                    out.write("\n<Length>"+parsedPacket.sixLoWPANPacket.getLength().trim()+"</Length>");
                                    out.write("\n<Frame_control_field>");
                                    out.write("\n<Type>"+parsedPacket.sixLoWPANPacket.getFrameCon()[0].trim()+"</Type>");
                                    out.write("\n<Sec>"+parsedPacket.sixLoWPANPacket.getFrameCon()[1].trim()+"</Sec>");
                                    out.write("\n<Pnd>"+parsedPacket.sixLoWPANPacket.getFrameCon()[2].trim()+"</Pnd>");
                                    out.write("\n<Ack_req>"+parsedPacket.sixLoWPANPacket.getFrameCon()[3].trim()+"</Ack_req>");
                                    out.write("\n<Intra_PAN>"+parsedPacket.sixLoWPANPacket.getFrameCon()[4].trim()+"</Intra_PAN>");
                                    out.write("\n</Frame_control_field>");
                                    out.write("\n<Sequence_Number>"+parsedPacket.sixLoWPANPacket.getSeq().trim()+"</Sequence_Number>");
                                    out.write("\n<Destination_PAN>"+parsedPacket.sixLoWPANPacket.getDestPAN().trim()+"</Destination_PAN>");
                                    out.write("\n<Destination_Address>"+parsedPacket.sixLoWPANPacket.getDestADD().trim()+"</Destination_Address>");
                                    out.write("\n<Source_PAN>"+parsedPacket.sixLoWPANPacket.getSrcPAN().trim()+"</Source_PAN>");
                                    out.write("\n<Source_Address>"+parsedPacket.sixLoWPANPacket.getSrcADD().trim()+"</Source_Address>");
                                    out.write("\n<Payload>"+new ConvertImpl().removeSpaces(parsedPacket.sixLoWPANPacket.getPayload().trim())+"</Payload>");
                                    out.write("\n<Type>"+parsedPacket.sixLoWPANPacket.getType().trim()+"</Type>");
                                    out.write("\n<Code>"+parsedPacket.sixLoWPANPacket.getCode().trim()+"</Code>");
                                    out.write("\n<Cheksum>"+parsedPacket.sixLoWPANPacket.getCheksum().trim()+"</Cheksum>");
                                    out.write("\n<Payload>"+parsedPacket.sixLoWPANPacket.getPayload().trim()+"</Payload>");
                                    out.write("\n<FCS>"+parsedPacket.sixLoWPANPacket.getFCS().trim()+"</FCS>");
                                    out.write("\n<Network_Layer>");
                                    out.write("\n<SixLOWPAN_Type>"+parsedPacket.sixLoWPANPacket.get6LoWPANType().trim()+"</SixLOWPAN_Type>");
                                    out.write("\n<Dispatch>"+parsedPacket.sixLoWPANPacket.getDispatchField().trim()+"</Dispatch>");
                                    out.write("\n<Encoding>"+parsedPacket.sixLoWPANPacket.getEncodingField().trim()+"</Encoding>");
                                    if (!parsedPacket.sixLoWPANPacket.getcontextField().isEmpty())
                                          out.write("\n<Context>"+parsedPacket.sixLoWPANPacket.getcontextField().trim()+"</Context>");
                                    if (!parsedPacket.sixLoWPANPacket.gettrafficClass_FlowLabelField().isEmpty())
                                          out.write("\n<Traffic_Class_FlowLable>"+parsedPacket.sixLoWPANPacket.gettrafficClass_FlowLabelField().trim()+"</Traffic_Class_FlowLable>");
                                    out.write("\n<Next_Header>"+parsedPacket.sixLoWPANPacket.getNextHeaderField().trim()+"</Next_Header>");
                                    out.write("\n<Hop_Limit>"+parsedPacket.sixLoWPANPacket.getHopLimitField().trim()+"</Hop_Limit>");
                                    out.write("\n<Source_Add>"+parsedPacket.sixLoWPANPacket.getSourceAddField().trim()+"</Source_Add>");
                                    out.write("\n<Destnation_Add>"+parsedPacket.sixLoWPANPacket.getDestAddField().trim()+"</Destnation_Add>");
                                    out.write("\n<ICMP_Header>");
                                    if (parsedPacket.ICMPType.equals("echoRequestHeader")){
                                          out.write("\n<ICMP_Type>"+parsedPacket.echoRequestHeader.geticmp_Type().trim()+"</ICMP_Type>");
                                          out.write("\n<ICMP_Code>"+parsedPacket.echoRequestHeader.geticmp_Code().trim()+"</ICMP_Code>");
                                          out.write("\n<ICMP_Checksum>"+parsedPacket.echoRequestHeader.geticmp_Cheksum().trim()+"</ICMP_Checksum>");
                                          out.write("\n<Identifier>"+parsedPacket.echoRequestHeader.getIdentifier().trim()+"</Identifier>");
                                          out.write("\n<Sequence_Number>"+parsedPacket.echoRequestHeader.getSequenceNumber().trim()+"</Sequence_Number>");
                                    } else if (parsedPacket.ICMPType.equals("DAO")){
                                          out.write("\n<ICMP_Type>"+parsedPacket.daoHeader.geticmp_Type().trim()+"</ICMP_Type>");
                                          out.write("\n<ICMP_Code>"+parsedPacket.daoHeader.geticmp_Code().trim()+"</ICMP_Code>");
                                          out.write("\n<ICMP_Checksum>"+parsedPacket.daoHeader.geticmp_Cheksum().trim()+"</ICMP_Checksum>");
                                          out.write("\n<RPL_Instance_ID>"+parsedPacket.daoHeader.getRPLInstanceID().trim()+"</RPL_Instance_ID>");
                                          out.write("\n<Flags>"+parsedPacket.daoHeader.getFlags().trim()+"</Flags>");
                                          out.write("\n<Reserved>"+parsedPacket.daoHeader.getReserved_icmp().trim()+"</Reserved>");
                                          out.write("\n<DAO_Sequence>"+parsedPacket.daoHeader.getDAOSequence().trim()+"</DAO_Sequence>");
                                          out.write("\n<RPL_Target_Type>"+parsedPacket.daoHeader.getRPLTargetType().trim()+"</RPL_Target_Type>");
                                          out.write("\n<RPL_Target_Length>"+parsedPacket.daoHeader.getRPLTargetLength().trim()+"</RPL_Target_Length>");
                                          out.write("\n<RPL_Target_Reserved>"+parsedPacket.daoHeader.getRPLTargetReserved().trim()+"</RPL_Target_Reserved>");
                                          out.write("\n<RPL_Target__Target_Length>"+parsedPacket.daoHeader.getRPLTarget_TargetLength().trim()+"</RPL_Target__Target_Length>");
                                          out.write("\n<RPL_Target_Target>"+parsedPacket.daoHeader.getRPLTarget_Target().trim()+"</RPL_Target_Target>");
                                          out.write("\n<Transit_Information_Type>"+parsedPacket.daoHeader.getTransitInformationType().trim()+"</Transit_Information_Type>");
                                          out.write("\n<Transit_Information_Length>"+parsedPacket.daoHeader.getTransitInformationLength().trim()+"</Transit_Information_Length>");
                                          out.write("\n<Transit_Information_Flags>"+parsedPacket.daoHeader.getTransitInformationFlags().trim()+"</Transit_Information_Flags>");
                                          out.write("\n<Transit_Information_Path_Control>"+parsedPacket.daoHeader.getTransitInformationPathControl().trim()+"</Transit_Information_Path_Control>");
                                          out.write("\n<Transit_Information_Path_Sequence>"+parsedPacket.daoHeader.getTransitInformationPathSequence().trim()+"</Transit_Information_Path_Sequence>");
                                          out.write("\n<Transit_Information_Path_Lifetime>"+parsedPacket.daoHeader.getTransitInformationPathLifetime().trim()+"</Transit_Information_Path_Lifetime>");
                                    } else if (parsedPacket.ICMPType.equals("DIO")){
                                          out.write("\n<ICMP_Type>"+parsedPacket.dioHeader.geticmp_Type().trim()+"</ICMP_Type>");
                                          out.write("\n<ICMP_Code>"+parsedPacket.dioHeader.geticmp_Code().trim()+"</ICMP_Code>");
                                          out.write("\n<ICMP_Checksum>"+parsedPacket.dioHeader.geticmp_Cheksum().trim()+"</ICMP_Checksum>");
                                          out.write("\n<RPL_InstanceID>"+parsedPacket.dioHeader.getRPLInstanceID().trim()+"</RPL_InstanceID>");
                                          out.write("\n<Version>"+parsedPacket.dioHeader.getVersion().trim()+"</Version>");
                                          out.write("\n<Rank>"+parsedPacket.dioHeader.getRank().trim()+"</Rank>");
                                          out.write("\n<Flags>"+parsedPacket.dioHeader.getFlags().trim()+"</Flags>");
                                          out.write("\n<DATSN>"+parsedPacket.dioHeader.getDATSN().trim()+"</DATSN>");
                                          out.write("\n<Flags2>"+parsedPacket.dioHeader.getFlags2().trim()+"</Flags2>");
                                          out.write("\n<Reserved>"+parsedPacket.dioHeader.getReserved_icmp().trim()+"</Reserved>");
                                          out.write("\n<DODAGID>"+parsedPacket.dioHeader.getDODAGID().trim()+"</DODAGID>");
                                          out.write("\n<Metric_Container_Type>"+parsedPacket.dioHeader.getMetricContainerType().trim()+"</Metric_Container_Type>");
                                          out.write("\n<Metric_Container_Length>"+parsedPacket.dioHeader.getMetricContainerLength().trim()+"</Metric_Container_Length>");
                                          out.write("\n<DODAG_Configuration_Type>"+parsedPacket.dioHeader.getDODAGConfigurationType().trim()+"</DODAG_Configuration_Type>");
                                          out.write("\n<DODAG_Configuration_Length>"+parsedPacket.dioHeader.getDODAGConfigurationLength().trim()+"</DODAG_Configuration_Length>");
                                          out.write("\n<DODAG_Configuration_Flags>"+parsedPacket.dioHeader.getDODAGConfigurationFlags().trim()+"</DODAG_Configuration_Flags>");
                                          out.write("\n<DODAG_Configuration_DIOI_nterval_Doubling>"+parsedPacket.dioHeader.getDODAGConfigurationDIOIntervalDoubling().trim()+"</DODAG_Configuration_DIOI_nterval_Doubling>");
                                          out.write("\n<DODAG_Configuration_DIO_Interval_Min>"+parsedPacket.dioHeader.getDODAGConfigurationDIOIntervalMin().trim()+"</DODAG_Configuration_DIO_Interval_Min>");
                                          out.write("\n<DODAG_Configuration_DIO_Redundancy_Constant>"+parsedPacket.dioHeader.getDODAGConfigurationDIORedundancyConstant().trim()+"</DODAG_Configuration_DIO_Redundancy_Constant>");
                                          out.write("\n<DODAG_Configuration_MaxRank>"+parsedPacket.dioHeader.getDODAGConfigurationMaxRank().trim()+"</DODAG_Configuration_MaxRank>");
                                          out.write("\n<DODAG_Configuration_MinHopRank>"+parsedPacket.dioHeader.getDODAGConfigurationMinHopRank().trim()+"</DODAG_Configuration_MinHopRank>");
                                          out.write("\n<DODAG_Configuration_OCP>"+parsedPacket.dioHeader.getDODAGConfigurationOCP().trim()+"</DODAG_Configuration_OCP>");
                                          out.write("\n<DODAG_Configuration_Reserved>"+parsedPacket.dioHeader.getDODAGConfigurationReserved().trim()+"</DODAG_Configuration_Reserved>");
                                          out.write("\n<DODAG_Configuration_Default_Lifetime>"+parsedPacket.dioHeader.getDODAGConfigurationDefaultLifetime().trim()+"</DODAG_Configuration_Default_Lifetime>");
                                          out.write("\n<DODAG_Configuration_Life_timeUnit>"+parsedPacket.dioHeader.getDODAGConfigurationLifetimeUnit().trim()+"</DODAG_Configuration_Life_timeUnit>");
                                          out.write("\n<Prefix_Information_Type>"+parsedPacket.dioHeader.getPrefixInformationType().trim()+"</Prefix_Information_Type>");
                                          out.write("\n<Prefix_Information_Length>"+parsedPacket.dioHeader.getPrefixInformationLength().trim()+"</Prefix_Information_Length>");
                                          out.write("\n<Prefix_Information_PrefixLength>"+parsedPacket.dioHeader.getPrefixInformation_PrefixLength().trim()+"</Prefix_Information_PrefixLength>");
                                          out.write("\n<Prefix_Information_Flags>"+parsedPacket.dioHeader.getPrefixInformationFlags().trim()+"</Prefix_Information_Flags>");
                                          out.write("\n<Prefix_Information_Valid_Lifetime>"+parsedPacket.dioHeader.getPrefixInformationValidLifetime().trim()+"</Prefix_Information_Valid_Lifetime>");
                                          out.write("\n<Prefix_Information_Preferred_Lifetime>"+parsedPacket.dioHeader.getPrefixInformationPreferredLifetime().trim()+"</Prefix_Information_Preferred_Lifetime>");
                                          out.write("\n<Prefix_Information_Reserved>"+parsedPacket.dioHeader.getPrefixInformationReserved().trim()+"</Prefix_Information_Reserved>");
                                          out.write("\n<Prefix_Information_Destination_Prefix>"+parsedPacket.dioHeader.getPrefixInformationDestinationPrefix().trim()+"</Prefix_Information_Destination_Prefix>");
                                    } else if (parsedPacket.ICMPType.equals("RouterAdvertisement")){
                                          out.write("\n<ICMP_Type>"+parsedPacket.routerAdverHeader.geticmp_Type().trim()+"</ICMP_Type>");
                                          out.write("\n<ICMP_Code>"+parsedPacket.routerAdverHeader.geticmp_Code().trim()+"</ICMP_Code>");
                                          out.write("\n<ICMP_Checksum>"+parsedPacket.routerAdverHeader.geticmp_Cheksum().trim()+"</ICMP_Checksum>");
                                          out.write("\n<Cur_Hop_Limit>"+parsedPacket.routerAdverHeader.getCurHopLimit().trim()+"</Cur_Hop_Limit>");
                                          out.write("\n<Flags>"+parsedPacket.routerAdverHeader.getFlags().trim()+"</Flags>");
                                          out.write("\n<Router_Life_time>"+parsedPacket.routerAdverHeader.getRouterLifetime().trim()+"</Router_Life_time>");
                                          out.write("\n<Reachable_Time>"+parsedPacket.routerAdverHeader.getReachableTime().trim()+"</Reachable_Time>");
                                          out.write("\n<Retans_Timer>"+parsedPacket.routerAdverHeader.getRetansTimer().trim()+"</Retans_Timer>");
                                          out.write("\n<Prefix_InfoOption>"+parsedPacket.routerAdverHeader.getPrefixInfoOption().trim()+"</Prefix_InfoOption>");
                                          out.write("\n<Address_IPOption>"+parsedPacket.routerAdverHeader.getAddressIPOption().trim()+"</Address_IPOption>");
                                    } else if (parsedPacket.ICMPType.equals("RouterSolicitation")){
                                          out.write("\n<ICMP_Type>"+parsedPacket.routerSolicitHeader.geticmp_Type().trim()+"</ICMP_Type>");
                                          out.write("\n<ICMP_Code>"+parsedPacket.routerSolicitHeader.geticmp_Code().trim()+"</ICMP_Code>");
                                          out.write("\n<ICMP_Checksum>"+parsedPacket.routerSolicitHeader.geticmp_Cheksum().trim()+"</ICMP_Checksum>");
                                    } else if (parsedPacket.ICMPType.equals("DIS")){
                                          out.write("\n<ICMP_Type>"+parsedPacket.disHeader.geticmp_Type().trim()+"</ICMP_Type>");
                                          out.write("\n<ICMP_Code>"+parsedPacket.disHeader.geticmp_Code().trim()+"</ICMP_Code>");
                                          out.write("\n<ICMP_Checksum>"+parsedPacket.disHeader.geticmp_Cheksum().trim()+"</ICMP_Checksum>");
                                          out.write("\n<Flags>"+parsedPacket.disHeader.getFlags().trim()+"</Flags>");
                                          out.write("\n<Reserved>"+parsedPacket.disHeader.getReserved_icmp().trim()+"</Reserved>");
                                          out.write("\n<Solicited_Information_Type>"+parsedPacket.disHeader.getSolicitedInformationType().trim()+"</Solicited_Information_Type>");
                                          out.write("\n<Solicited_Information_Length>"+parsedPacket.disHeader.getSolicitedInformationLength().trim()+"</Solicited_Information_Length>");
                                          out.write("\n<Solicited_Information_Instance>"+parsedPacket.disHeader.getSolicitedInformationInstance().trim()+"</Solicited_Information_Instance>");
                                          out.write("\n<Solicited_Information_Flags>"+parsedPacket.disHeader.getSolicitedInformationFlags().trim()+"</Solicited_Information_Flags>");
                                          out.write("\n<Solicited_Information_DODAGID>"+parsedPacket.disHeader.getSolicitedInformationDODAGID().trim()+"</Solicited_Information_DODAGID>");
                                          out.write("\n<Solicited_Information_Version>"+parsedPacket.disHeader.getSolicitedInformationVersion().trim()+"</Solicited_Information_Version>");
                                    }
                                    out.write("\n</ICMP_Header>");
                                    out.write("\n</Network_Layer>");
                        }
                        else if(parsedPacket.sixLoWPANPacket.getPacketType()==2)
                        {
                                    out.write("\n<Timestamp>"+NtpMessage.timestampToString(parsedPacket.sixLoWPANPacket.getRealArrivalTime())+"</Timestamp>");
                                    out.write("\n<Frame>"+i+"</Frame>");
                                    out.write("\n<Length>"+parsedPacket.sixLoWPANPacket.getLength().trim()+"</Length>");
                                    out.write("\n<Frame_control_field>");
                                    out.write("\n<Type>"+parsedPacket.sixLoWPANPacket.getFrameCon()[0].trim()+"</Type>");
                                    out.write("\n<Sec>"+parsedPacket.sixLoWPANPacket.getFrameCon()[1].trim()+"</Sec>");
                                    out.write("\n<Pnd>"+parsedPacket.sixLoWPANPacket.getFrameCon()[2].trim()+"</Pnd>");
                                    out.write("\n<Ack_req>"+parsedPacket.sixLoWPANPacket.getFrameCon()[3].trim()+"</Ack_req>");
                                    out.write("\n<Intra_PAN>"+parsedPacket.sixLoWPANPacket.getFrameCon()[4].trim()+"</Intra_PAN>");
                                    out.write("\n</Frame_control_field>");
                                    out.write("\n<Sequence_Number>"+parsedPacket.sixLoWPANPacket.getSeq().trim()+"</Sequence_Number>");
                                    out.write("\n<LQI>"+parsedPacket.sixLoWPANPacket.getLQI().trim()+"</LQI>");
                                    out.write("\n<MAC_Header_Length>"+parsedPacket.sixLoWPANPacket.getMhrlen().trim()+"</MAC_Header_Length>");
                                    out.write("\n<Channel>"+parsedPacket.sixLoWPANPacket.getChannel().trim()+"</Channel>");
                                    out.write("\n<Dispatch_ID>"+parsedPacket.sixLoWPANPacket.getDispID().trim()+"</Dispatch_ID>");
                                    out.write("\n<RSSI>"+parsedPacket.sixLoWPANPacket.getRSSI().trim()+"</RSSI>");
                                    out.write("\n<CRC>"+parsedPacket.sixLoWPANPacket.getCRC().trim()+"</CRC>");
                        }
                        else if(parsedPacket.sixLoWPANPacket.getPacketType()==3)
                        {
                                    out.write("\n<Timestamp>"+NtpMessage.timestampToString(parsedPacket.sixLoWPANPacket.getRealArrivalTime())+"</Timestamp>");
                                    out.write("\n<Frame>"+i+"</Frame>");
                                    out.write("\n<Length>"+parsedPacket.sixLoWPANPacket.getLength().trim()+"</Length>");
                                    out.write("\n<Frame_control_field>");
                                    out.write("\n<Type>"+parsedPacket.sixLoWPANPacket.getFrameCon()[0].trim()+"</Type>");
                                    out.write("\n<Sec>"+parsedPacket.sixLoWPANPacket.getFrameCon()[1].trim()+"</Sec>");
                                    out.write("\n<Pnd>"+parsedPacket.sixLoWPANPacket.getFrameCon()[2].trim()+"</Pnd>");
                                    out.write("\n<Ack_req>"+parsedPacket.sixLoWPANPacket.getFrameCon()[3].trim()+"</Ack_req>");
                                    out.write("\n<Intra_PAN>"+parsedPacket.sixLoWPANPacket.getFrameCon()[4].trim()+"</Intra_PAN>");
                                    out.write("\n</Frame_control_field>");
                                    out.write("\n<Sequence_Number>"+parsedPacket.sixLoWPANPacket.getSeq().trim()+"</Sequence_Number>");
                                    out.write("\n<Destination_PAN>"+parsedPacket.sixLoWPANPacket.getDestPAN().trim()+"</Destination_PAN>");
                                    out.write("\n<Destination_Address>"+parsedPacket.sixLoWPANPacket.getDestADD().trim()+"</Destination_Address>");
                                    out.write("\n<Source_PAN>"+parsedPacket.sixLoWPANPacket.getDestPAN().trim()+"</Source_PAN>");
                                    out.write("\n<Source_Address>"+parsedPacket.sixLoWPANPacket.getDestADD().trim()+"</Source_Address>");
                                    out.write("\n<Command_Payload>"+parsedPacket.sixLoWPANPacket.getDestADD().trim()+"</Command_Payload>");
                                    out.write("\n<LQI>"+parsedPacket.sixLoWPANPacket.getLQI().trim()+"</LQI>");
                                    out.write("\n<MAC_Header_Length>"+parsedPacket.sixLoWPANPacket.getMhrlen().trim()+"</MAC_Header_Length>");
                                    out.write("\n<Channel>"+parsedPacket.sixLoWPANPacket.getChannel().trim()+"</Channel>");
                                    out.write("\n<Dispatch_ID>"+parsedPacket.sixLoWPANPacket.getDispID().trim()+"</Dispatch_ID>");
                                    out.write("\n<RSSI>"+parsedPacket.sixLoWPANPacket.getRSSI().trim()+"</RSSI>");
                                    out.write("\n<CRC>"+parsedPacket.sixLoWPANPacket.getCRC().trim()+"</CRC>");
                        }

                        out.write("</Packet_Number_"+i+">");
                    }
                    out.newLine();
                    out.write("</Packet_Analysis>");
                    out.close();

        }catch(Exception ex){}

    }
    //-----------------------------------------------//

    /*******************************************************************/
    /* Function: textFormat                                            */
    /* Arguments : String: CurrentDateAndTime                          */
    /*                     these argument is the current time and      */
    /*                     date to use it as a name of the result file */
    /* Return: Nothing                                                 */
    /* This method presents the parse packet in text format            */
    /*******************************************************************/
    
    public void textFormat(String CurrentDateAndTime,String protocol)
    {
                   try{
                   String fileName=CurrentDateAndTime;
                   Vector<String> parsePackets=packetBuffer.getOrigonalPackets();
                   Vector<Double> parseTimes=packetBuffer.getOrigonalTimes();
                    // define history directory
                    File dir=new File("History");
                    // create history directory in case of not exists
                     if(!dir.exists())
                            dir.mkdir();
                    // create history directory in case of exist but not directory
                    if(!dir.isDirectory())
                            dir.mkdir();
                    //create file with this name savedPackets_(date with remove all not allowed signs)
                    File packets = new File("History/savedPackets_"+fileName+".txt");
                    if(!packets.exists())
                    packets.createNewFile();
                            // define the file writer to write on the file
                    FileWriter fstream = new FileWriter(packets,true);
                    // define buffer of writer
                    BufferedWriter out = new BufferedWriter(fstream);
                    // write on the top of file how many packets did the sniffer sniff
                    out.write("Number of saved packets= "+parsePackets.size());
                    out.newLine();
                    out.write("Protocol: "+protocol);
                    // adding the details of packets
                    for(int i=0; i<parsePackets.size();i++)
                    {
                        String receivedPacketStream = parsePackets.get(i);
                        double time = parseTimes.get(i);
                            out.newLine();
                            out.write("Packet #"+i+" :"+receivedPacketStream+" with Timestamp"+NtpMessage.timestampToString(time));
                            out.newLine();
                            out.write("###################################################################################");
                            out.newLine();
                    }
                    out.close();
        }catch(Exception ex){}

    }
    //-----------------------------------------------//
}
