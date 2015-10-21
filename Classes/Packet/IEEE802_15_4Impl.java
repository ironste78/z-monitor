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

import java.util.ArrayList;

/*
 * Class IEEE802_15_4 contain the type of IEEE802.15.4 frame, and all possible IEEE802.15.4 fields.
 */
 
public class IEEE802_15_4Impl extends physicalImpl implements IEEE802_15_4
{
    //Class Attribute
    //private String IEEEcase;
    private short destAddMode;
    private short srcAddMode;
    private short intra_PAN;
    private String frameType;
    private short sec;
    private short pnd;
    private short ack;
    private short intra;
    private short reserved;
    private String dispID;
    private String length;
    private String [] frameCon;
    private String seq;
    private String destPAN;
    private String destADD;
    private String srcPAN;
    private String srcADD;
    private short BO;
    private short SO;
    private short FinCap;
    private short bat;
    private short res;
    private short PAN;
    private short ass;
    private String [] superFrameSpec;
    private String GTS;
    private String GTSDirec;
    private ArrayList<String> GTSList;
    private String pendingAddress;
    private String pendingList;
    private String payload;
    private String FCS;
    private int IEEEheaderLength;
    
    //-----------------------------------------------//
	
    //Constructor
    public IEEE802_15_4Impl(){
          //IEEEcase="";
          destAddMode=-1;
          srcAddMode=-1;
          intra_PAN=-1;
          frameType = "";
          sec=-1;
          pnd=-1;
          ack=-1;
          intra=-1;
          reserved=-1;
          dispID="";
          length="";
          frameCon=new String[6];
          seq="";
          destPAN="";
          destADD="";
          srcPAN="";
          srcADD="";
          FCS="";
          BO=-1;
          SO=-1;
          FinCap=-1;
          bat=-1;
          res=-1;
          PAN=-1;
          ass=-1;
          superFrameSpec=new String[6];
          GTS="";
          GTSDirec="";
          GTSList=new ArrayList();
          pendingAddress="";
          pendingList="";
          payload="";
          FCS="";
          IEEEheaderLength = -1;
    }
	//-----------------------------------------------//

    //----------------- Set Methods -----------------//
    //public void setIEEEcase(String fieldValue){ IEEEcase=fieldValue;}
    public void setDestAddMode(short fieldValue){ destAddMode=fieldValue;}
    public void setSrcAddMode(short fieldValue){ srcAddMode=fieldValue;}
    public void setIntra_PAN(short fieldValue){ intra_PAN=fieldValue;}
    public void setDispID(String fieldValue){ dispID=fieldValue;}
    public void setLength(String fieldValue){ length=fieldValue;}
    public void setFrameType(String fieldValue){ frameType=fieldValue;}
    public void setSec(short fieldValue){ sec=fieldValue;}
    public void setPnd(short fieldValue){ pnd=fieldValue;}
    public void setAck(short fieldValue){ ack=fieldValue;}
    public void setIntra(short fieldValue){ intra=fieldValue;}
    public void setReserved(short fieldValue){reserved=fieldValue;}
    public void setFrameCon(){
          frameCon[0]=frameType;
          frameCon[1]=Integer.toString(sec);
          frameCon[2]=Integer.toString(pnd);
          frameCon[3]=Integer.toString(ack);
          frameCon[4]=Integer.toString(intra);
          frameCon[5]=Integer.toString(reserved);
    }
    public void setSeq(String fieldValue){ seq=fieldValue;}
    public void setDestPAN(String fieldValue){ destPAN=fieldValue;}
    public void setDestADD(String fieldValue){ destADD=fieldValue;}
    public void setSrcPAN(String fieldValue){ srcPAN=fieldValue;}
    public void setSrcADD(String fieldValue){ srcADD=fieldValue;}
    public void setFCS(String fieldValue){ FCS=fieldValue;}
    public void setBO(short fieldValue){ BO=fieldValue;}
    public void setSO(short fieldValue){ SO=fieldValue;}
    public void setFinCap(short fieldValue){ FinCap=fieldValue;}
    public void setBat(short fieldValue){ bat=fieldValue;}
    public void setRes(short fieldValue){ res=fieldValue;}
    public void setPAN(short fieldValue){ PAN=fieldValue;}
    public void setass(short fieldValue){ ass=fieldValue;}
    public void setSuperFrameSpecs(){
          superFrameSpec[0]=Integer.toString(BO);
          superFrameSpec[1]=Integer.toString(SO);
          superFrameSpec[2]=Integer.toString(FinCap);
          superFrameSpec[3]=Integer.toString(bat);
          superFrameSpec[4]=Integer.toString(PAN);
          superFrameSpec[5]=Integer.toString(ass);
    }
    public void setGTS(String fieldValue){GTS=fieldValue;};
    public void setGTSDirec(String fieldValue){GTSDirec=fieldValue;};
    public void setGTSList(ArrayList<String> fieldValue){
        for(int i=0;i<fieldValue.size();i++)
            GTSList.add(fieldValue.get(i));
    };
    public void setPending(String fieldValue){pendingAddress=fieldValue;};
    public void setPendingList(String fieldValue){pendingList=fieldValue;};
    public void setPayload(String fieldValue){payload=fieldValue;};
    public void setIEEEheaderLength(int fieldValue){IEEEheaderLength=fieldValue;}

    //-----------------------------------------------//

    //----------------- Get Methods -----------------//
    public short getDestAddMode(){ return destAddMode;}
    public short getSrcAddMode(){ return srcAddMode;}
    public short getIntra_PAN(){ return intra_PAN;}
    public String getDispID(){return dispID;}
    public String getLength(){return length;}
    public String getFrameType(){ return frameType;}
    public short getSec(){ return sec;}
    public short getPnd(){ return pnd;}
    public short getAck(){ return ack;}
    public short getIntra(){ return intra;}
    public short getReserved(){return reserved;}
    public String[] getFrameCon(){return frameCon;}
    public String getSeq(){return seq;}
    public String getDestPAN(){return destPAN;}
    public String getDestADD(){return destADD;}
    public String getSrcPAN(){return srcPAN;}
    public String getSrcADD(){return srcADD;}
    public String getFCS(){return FCS;}
    //public String getIEEEcase(){return IEEEcase;}
    public short getBO(){ return BO;}
    public short getSO(){ return SO;}
    public short getFinCap(){ return FinCap;}
    public short getbat(){ return bat;}
    public short getres(){ return res;}
    public short getPAN(){ return PAN;}
    public short getass(){ return ass;}
    public String[] getSuperFrameSpecs(){return superFrameSpec;}

    public String getGTS(){return GTS;}
    public String getGTSDirec(){return GTSDirec;}
    public ArrayList<String> getGTSList(){
        return GTSList;
    };
    public String getPending(){return pendingAddress;}
    public String getPendingList(){return pendingList;}
    public String getPayload(){return payload;}
    public int getIEEEheaderLength(){ return IEEEheaderLength;}
    //-----------------------------------------------//
	
}
