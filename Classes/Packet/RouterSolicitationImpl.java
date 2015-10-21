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


package Classes.Packet;

/*
 * Class RouterSolicitation contains all router solicitation fields.
 */
 
public class RouterSolicitationImpl extends sixLoWPANImpl implements RouterSolicitation
{
    //Class Attribute
    //private String IEEEcase;
    private String icmp_type;
    private String icmp_code;
    private String icmp_cheksum;
    //-----------------------------------------------//
    //Constructor
    public RouterSolicitationImpl(){
          icmp_type="";
	   icmp_code="";
	   icmp_cheksum="";
    }

      public RouterSolicitationImpl(sixLoWPAN sixLoWPANParsedPacket) {
            setPacketType (sixLoWPANParsedPacket.getPacketType());
            setPacket (sixLoWPANParsedPacket.getPacket());
            setpacketinHexa(sixLoWPANParsedPacket.getpacketinHexa());
            setNO(sixLoWPANParsedPacket.getNO());
            setlength(sixLoWPANParsedPacket.getlength());

            setLQI(sixLoWPANParsedPacket.getLQI());
            setRSSI(sixLoWPANParsedPacket.getRSSI());
            setCRC(sixLoWPANParsedPacket.getCRC());
            setMhrlen(sixLoWPANParsedPacket.getMhrlen());
            setChannel(sixLoWPANParsedPacket.getChannel());
            setTimeStamp(sixLoWPANParsedPacket.getTimeStamp());
            setType(sixLoWPANParsedPacket.getType());
            setCode(sixLoWPANParsedPacket.getCode());
            setCheksum(sixLoWPANParsedPacket.getCheksum());
            setPayload(sixLoWPANParsedPacket.getPayload());
            setRealArrivalTime(sixLoWPANParsedPacket.getRealArrivalTime());

            setDestAddMode(sixLoWPANParsedPacket.getDestAddMode());
            setSrcAddMode(sixLoWPANParsedPacket.getSrcAddMode());
            setIntra_PAN(sixLoWPANParsedPacket.getIntra_PAN());
            setDispID(sixLoWPANParsedPacket.getDispID());
            setLength(sixLoWPANParsedPacket.getLength());
            setFrameType(sixLoWPANParsedPacket.getFrameType());
            setSec(sixLoWPANParsedPacket.getSec());
            setPnd(sixLoWPANParsedPacket.getPnd());
            setAck(sixLoWPANParsedPacket.getAck());
            setIntra(sixLoWPANParsedPacket.getIntra());
            setReserved(sixLoWPANParsedPacket.getReserved());
            setFrameCon();
            setSeq(sixLoWPANParsedPacket.getSeq());
            setDestPAN(sixLoWPANParsedPacket.getDestPAN());
            setDestADD(sixLoWPANParsedPacket.getDestADD());
            setSrcPAN(sixLoWPANParsedPacket.getSrcPAN());
            setSrcADD(sixLoWPANParsedPacket.getSrcADD());
            setFCS(sixLoWPANParsedPacket.getFCS());
            setBO(sixLoWPANParsedPacket.getBO());
            setSO(sixLoWPANParsedPacket.getSO());
            setFinCap(sixLoWPANParsedPacket.getFinCap());
            setBat(sixLoWPANParsedPacket.getbat());
            setRes(sixLoWPANParsedPacket.getres());
            setPAN(sixLoWPANParsedPacket.getPAN());
            setass(sixLoWPANParsedPacket.getass());
            setSuperFrameSpecs();
            setGTS(sixLoWPANParsedPacket.getGTS());
            setGTSDirec(sixLoWPANParsedPacket.getGTSDirec());
            setGTSList(sixLoWPANParsedPacket.getGTSList());
            setPending(sixLoWPANParsedPacket.getPending());
            setPendingList(sixLoWPANParsedPacket.getPendingList());
            setPayload(sixLoWPANParsedPacket.getPayload());
            setIEEEheaderLength(sixLoWPANParsedPacket.getIEEEheaderLength());
            
            set6LoWPANType(sixLoWPANParsedPacket.get6LoWPANType());
            setDispatchField(sixLoWPANParsedPacket.getDispatchField());
            setEncodingField(sixLoWPANParsedPacket.getEncodingField());
            setNextHeaderField(sixLoWPANParsedPacket.getNextHeaderField());
            setHopLimitField(sixLoWPANParsedPacket.getHopLimitField());
            setSourceAddField(sixLoWPANParsedPacket.getSourceAddField());
            setDestAddField(sixLoWPANParsedPacket.getDestAddField());
            setcontextField(sixLoWPANParsedPacket.getcontextField());
            settrafficClass_FlowLabelField (sixLoWPANParsedPacket.gettrafficClass_FlowLabelField());

            icmp_type="";
	   icmp_code="";
	   icmp_cheksum="";
      }

    //----------------- Set Methods -----------------//
    public void seticmp_Type(String fieldValue){ icmp_type=fieldValue;}
    public void seticmp_Code(String fieldValue){ icmp_code=fieldValue;}
    public void seticmp_Cheksum(String fieldValue){ icmp_cheksum=fieldValue;}
    //----------------- Get Methods -----------------//
    public String geticmp_Type(){return icmp_type;}
    public String geticmp_Code(){return icmp_code;}
    public String geticmp_Cheksum(){ return icmp_cheksum;}
    //-----------------------------------------------//
	
}
