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
 * Class DODAGAdvertisementObject contains all DAO fields.
 */
public class DODAGInformationObjectImpl extends sixLoWPANImpl implements DODAGInformationObject
{
    //Class Attribute
    private String icmp_type;
    private String icmp_code;
    private String icmp_cheksum;
    private String rPLInstanceID;
    private String version;
    private String rank;
    private String flags;
    private String dATSN;
    private String flags2;
    private String reserved_icmp;
    private String dODAGID;
    private String metricContainerType;
    private String metricContainerLength;
    private String dODAGConfigurationType;
    private String dODAGConfigurationLength;
    private String dODAGConfigurationFlags;
    private String dODAGConfigurationDIOIntervalDoubling;
    private String dODAGConfigurationDIOIntervalMin;
    private String dODAGConfigurationDIORedundancyConstant;
    private String dODAGConfigurationMaxRank;
    private String dODAGConfigurationMinHopRank;
    private String dODAGConfigurationOCP;
    private String dODAGConfigurationReserved;
    private String dODAGConfigurationDefaultLifetime;
    private String dODAGConfigurationLifetimeUnit;
    private String prefixInformationType;
    private String prefixInformationLength;
    private String prefixInformation_PrefixLength;
    private String prefixInformationFlags;
    private String prefixInformationValidLifetime;
    private String prefixInformationPreferredLifetime;
    private String prefixInformationReserved;
    private String prefixInformationDestinationPrefix;    
    //-----------------------------------------------//
    //Constructor
    public DODAGInformationObjectImpl(){
        icmp_type="";
    	icmp_code="";
    	icmp_cheksum="";
    	rPLInstanceID="";
    	version="";
    	rank="";
    	flags="";
    	dATSN="";
    	flags2="";
    	reserved_icmp="";
    	dODAGID="";
    	metricContainerType="";
    	metricContainerLength="";
	dODAGConfigurationType="";
	dODAGConfigurationLength="";
	dODAGConfigurationFlags="";
	dODAGConfigurationDIOIntervalDoubling="";
	dODAGConfigurationDIOIntervalMin="";
	dODAGConfigurationDIORedundancyConstant="";
	dODAGConfigurationMaxRank="";
	dODAGConfigurationMinHopRank="";
	dODAGConfigurationOCP="";
	dODAGConfigurationReserved="";
	dODAGConfigurationDefaultLifetime="";
	dODAGConfigurationLifetimeUnit="";
	prefixInformationType="";
	prefixInformationLength="";
	prefixInformation_PrefixLength="";
	prefixInformationFlags="";
	prefixInformationValidLifetime="";
	prefixInformationPreferredLifetime="";
	prefixInformationReserved="";
	prefixInformationDestinationPrefix="";   
    }

      public DODAGInformationObjectImpl(sixLoWPAN sixLoWPANParsedPacket) {
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
    	rPLInstanceID="";
    	version="";
    	rank="";
    	flags="";
    	dATSN="";
    	flags2="";
    	reserved_icmp="";
    	dODAGID="";
    	metricContainerType="";
    	metricContainerLength="";
	dODAGConfigurationType="";
	dODAGConfigurationLength="";
	dODAGConfigurationFlags="";
	dODAGConfigurationDIOIntervalDoubling="";
	dODAGConfigurationDIOIntervalMin="";
	dODAGConfigurationDIORedundancyConstant="";
	dODAGConfigurationMaxRank="";
	dODAGConfigurationMinHopRank="";
	dODAGConfigurationOCP="";
	dODAGConfigurationReserved="";
	dODAGConfigurationDefaultLifetime="";
	dODAGConfigurationLifetimeUnit="";
	prefixInformationType="";
	prefixInformationLength="";
	prefixInformation_PrefixLength="";
	prefixInformationFlags="";
	prefixInformationValidLifetime="";
	prefixInformationPreferredLifetime="";
	prefixInformationReserved="";
	prefixInformationDestinationPrefix=""; 
      }

    //----------------- Set Methods -----------------//
    public void seticmp_Type(String fieldValue){ icmp_type=fieldValue;}
    public void seticmp_Code(String fieldValue){ icmp_code=fieldValue;}
    public void seticmp_Cheksum(String fieldValue){ icmp_cheksum=fieldValue;}
    public void setRPLInstanceID(String fieldValue){ rPLInstanceID=fieldValue;}
    public void setVersion(String fieldValue){ version=fieldValue;}
    public void setRank(String fieldValue){ rank=fieldValue;}
    public void setFlags(String fieldValue){ flags=fieldValue;}
    public void setDATSN(String fieldValue){ dATSN=fieldValue;}
    public void setFlags2(String fieldValue){ flags2=fieldValue;}
    public void setReserved_icmp(String fieldValue){ reserved_icmp=fieldValue;}
    public void setDODAGID(String fieldValue){ dODAGID=fieldValue;}
    public void setMetricContainerType(String fieldValue){ metricContainerType=fieldValue;}
    public void setMetricContainerLength(String fieldValue){ metricContainerLength=fieldValue;}
    public void setDODAGConfigurationType(String fieldValue){ dODAGConfigurationType=fieldValue;}
    public void setDODAGConfigurationLength(String fieldValue){ dODAGConfigurationLength=fieldValue;}
    public void setDODAGConfigurationFlags(String fieldValue){ dODAGConfigurationFlags=fieldValue;}
    public void setDODAGConfigurationDIOIntervalDoubling(String fieldValue){ dODAGConfigurationDIOIntervalDoubling=fieldValue;}
    public void setDODAGConfigurationDIOIntervalMin(String fieldValue){ dODAGConfigurationDIOIntervalMin=fieldValue;}
    public void setDODAGConfigurationDIORedundancyConstant(String fieldValue){ dODAGConfigurationDIORedundancyConstant=fieldValue;}
    public void setDODAGConfigurationMaxRank(String fieldValue){ dODAGConfigurationMaxRank=fieldValue;}
    public void setDODAGConfigurationMinHopRank(String fieldValue){ dODAGConfigurationMinHopRank=fieldValue;}
    public void setDODAGConfigurationOCP(String fieldValue){ dODAGConfigurationOCP=fieldValue;}
    public void setDODAGConfigurationReserved(String fieldValue){ dODAGConfigurationReserved=fieldValue;}
    public void setDODAGConfigurationDefaultLifetime(String fieldValue){ dODAGConfigurationDefaultLifetime=fieldValue;}
    public void setDODAGConfigurationLifetimeUnit(String fieldValue){ dODAGConfigurationLifetimeUnit=fieldValue;}
    public void setPrefixInformationType(String fieldValue){ prefixInformationType=fieldValue;}
    public void setPrefixInformationLength(String fieldValue){ prefixInformationLength=fieldValue;}
    public void setPrefixInformation_PrefixLength(String fieldValue){ prefixInformation_PrefixLength=fieldValue;}
    public void setPrefixInformationFlags(String fieldValue){ prefixInformationFlags=fieldValue;}
    public void setPrefixInformationValidLifetime(String fieldValue){ prefixInformationValidLifetime=fieldValue;}
    public void setPrefixInformationPreferredLifetime(String fieldValue){ prefixInformationPreferredLifetime=fieldValue;}
    public void setPrefixInformationReserved(String fieldValue){ prefixInformationReserved=fieldValue;}
    public void setPrefixInformationDestinationPrefix(String fieldValue){ prefixInformationDestinationPrefix=fieldValue;}
  
    //----------------- Get Methods -----------------//
    public String geticmp_Type(){return icmp_type;}
    public String geticmp_Code(){return icmp_code;}
    public String geticmp_Cheksum(){ return icmp_cheksum;}
    public String getRPLInstanceID(){ return rPLInstanceID;}
    public String getVersion(){return version;}
    public String getRank(){return rank;}
    public String getFlags(){ return flags;}
    public String getDATSN(){return dATSN;}
    public String getFlags2(){return flags2;}
    public String getReserved_icmp(){ return reserved_icmp;}
    public String getDODAGID(){return dODAGID;}
    public String getMetricContainerType(){return metricContainerType;}
    public String getMetricContainerLength(){return metricContainerLength;}
    public String getDODAGConfigurationType(){return dODAGConfigurationType;}
    public String getDODAGConfigurationLength(){ return dODAGConfigurationLength;}
    public String getDODAGConfigurationFlags(){return dODAGConfigurationFlags;}
    public String getDODAGConfigurationDIOIntervalDoubling(){return dODAGConfigurationDIOIntervalDoubling;}
    public String getDODAGConfigurationDIOIntervalMin(){return dODAGConfigurationDIOIntervalMin;}
    public String getDODAGConfigurationDIORedundancyConstant(){return dODAGConfigurationDIORedundancyConstant;}
    public String getDODAGConfigurationMaxRank(){return dODAGConfigurationMaxRank;}
    public String getDODAGConfigurationMinHopRank(){return dODAGConfigurationMinHopRank;}
    public String getDODAGConfigurationOCP(){ return dODAGConfigurationOCP;}
    public String getDODAGConfigurationReserved(){ return dODAGConfigurationReserved;}
    public String getDODAGConfigurationDefaultLifetime(){return dODAGConfigurationDefaultLifetime;}
    public String getDODAGConfigurationLifetimeUnit(){return dODAGConfigurationLifetimeUnit;}
    public String getPrefixInformationType(){ return prefixInformationType;}
    public String getPrefixInformationLength(){return prefixInformationLength;}
    public String getPrefixInformation_PrefixLength(){return prefixInformation_PrefixLength;}
    public String getPrefixInformationFlags(){ return prefixInformationFlags;}
    public String getPrefixInformationValidLifetime(){return prefixInformationValidLifetime;}
    public String getPrefixInformationPreferredLifetime(){return prefixInformationPreferredLifetime;}
    public String getPrefixInformationReserved(){return prefixInformationReserved;}
    public String getPrefixInformationDestinationPrefix(){return prefixInformationDestinationPrefix;}
   
    //-----------------------------------------------//
	
}
