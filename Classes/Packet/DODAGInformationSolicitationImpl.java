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
 * Class DODAGInformationSolicitation contains all DIS fields.
 */
public class DODAGInformationSolicitationImpl extends sixLoWPANImpl implements DODAGInformationSolicitation
{
    //Class Attribute
    private String icmp_type;
    private String icmp_code;
    private String icmp_cheksum;
    private String flags;
    private String reserved_icmp;
    private String solicitedInformationType;
    private String solicitedInformationLength;
    private String solicitedInformationInstance;
    private String solicitedInformationFlags;
    private String solicitedInformationDODAGID;
    private String solicitedInformationVersion;
    
    //-----------------------------------------------//
    //Constructor
    public DODAGInformationSolicitationImpl(){
          icmp_type="";
	   icmp_code="";
	   icmp_cheksum="";
          flags="";
          reserved_icmp=""; 
          solicitedInformationType="";    
          solicitedInformationLength=""; 
          solicitedInformationInstance="";
          solicitedInformationFlags="";
          solicitedInformationDODAGID="";
          solicitedInformationVersion="";
    }

      public DODAGInformationSolicitationImpl(sixLoWPAN sixLoWPANParsedPacket) {
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

            set6LoWPANType(sixLoWPANParsedPacket.get6LoWPANType());
            setDispatchField(sixLoWPANParsedPacket.getDispatchField());
            setEncodingField(sixLoWPANParsedPacket.getEncodingField());
            setNextHeaderField(sixLoWPANParsedPacket.getNextHeaderField());
            setHopLimitField(sixLoWPANParsedPacket.getHopLimitField());
            setSourceAddField(sixLoWPANParsedPacket.getSourceAddField());
            setDestAddField(sixLoWPANParsedPacket.getDestAddField());
            setcontextField(sixLoWPANParsedPacket.getcontextField());
            settrafficClass_FlowLabelField (sixLoWPANParsedPacket.gettrafficClass_FlowLabelField());
            setIEEEheaderLength(sixLoWPANParsedPacket.getIEEEheaderLength());
            
            icmp_type="";
	   icmp_code="";
	   icmp_cheksum="";
          flags="";
          reserved_icmp=""; 
          solicitedInformationType="";    
          solicitedInformationLength=""; 
          solicitedInformationInstance="";
          solicitedInformationFlags="";
          solicitedInformationDODAGID="";
          solicitedInformationVersion="";
      }

    //----------------- Set Methods -----------------//
    public void seticmp_Type(String fieldValue){ icmp_type=fieldValue;}
    public void seticmp_Code(String fieldValue){ icmp_code=fieldValue;}
    public void seticmp_Cheksum(String fieldValue){ icmp_cheksum=fieldValue;}
    public void setFlags(String fieldValue){ flags=fieldValue;}
    public void setReserved_icmp(String fieldValue){ reserved_icmp=fieldValue;}
    public void setSolicitedInformationType(String fieldValue){ solicitedInformationType=fieldValue;}
    public void setSolicitedInformationLength(String fieldValue){ solicitedInformationLength=fieldValue;}
    public void setSolicitedInformationInstance(String fieldValue){ solicitedInformationInstance=fieldValue;}
    public void setSolicitedInformationFlags(String fieldValue){ solicitedInformationFlags=fieldValue;}
    public void setSolicitedInformationDODAGID(String fieldValue){ solicitedInformationDODAGID=fieldValue;}
    public void setSolicitedInformationVersion(String fieldValue){ solicitedInformationVersion=fieldValue;}
    //----------------- Get Methods -----------------//
    public String geticmp_Type(){return icmp_type;}
    public String geticmp_Code(){return icmp_code;}
    public String geticmp_Cheksum(){ return icmp_cheksum;}
    public String getFlags(){return flags;}
    public String getReserved_icmp(){return reserved_icmp;}
    public String getSolicitedInformationType(){ return solicitedInformationType;}
    public String getSolicitedInformationLength(){return solicitedInformationLength;}
    public String getSolicitedInformationInstance(){return solicitedInformationInstance;}
    public String getSolicitedInformationFlags(){ return solicitedInformationFlags;}
    public String getSolicitedInformationDODAGID(){return solicitedInformationDODAGID;}
    public String getSolicitedInformationVersion(){return solicitedInformationVersion;}
    //-----------------------------------------------//
	
}
