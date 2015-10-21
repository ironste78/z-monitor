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
 * Class IEEE802_15_4 contain the type of IEEE802.15.4frame, and all possible IEEE802.15.4 fields.
 */
 public interface IEEE802_15_4 extends physical
{
     //----------------- Set Methods -----------------//
     public void setDestAddMode(short fieldValue);
     public void setSrcAddMode(short fieldValue);
     public void setIntra_PAN(short fieldValue);
     public void setDispID(String fieldValue);
     public void setLength(String fieldValue);
     public void setFrameType(String fieldValue);
     public void setSec(short fieldValue);
     public void setPnd(short fieldValue);
     public void setAck(short fieldValue);
     public void setIntra(short fieldValue);
     public void setReserved(short fieldValue);
     public void setFrameCon();
     public void setSeq(String fieldValue);
     public void setDestPAN(String fieldValue);
     public void setDestADD(String fieldValue);
     public void setSrcPAN(String fieldValue);
     public void setSrcADD(String fieldValue);
     public void setFCS(String fieldValue);
     public void setGTS(String fieldValue);
     public void setGTSDirec(String fieldValue);
     public void setGTSList(ArrayList<String> fieldValue);
     public void setPending(String fieldValue);
     public void setPendingList(String fieldValue);

     //public void setIEEEcase(String fieldValue);
     public void setBO(short fieldValue);
     public void setSO(short fieldValue);
     public void setFinCap(short fieldValue);
     public void setBat(short fieldValue);
     public void setRes(short fieldValue);
     public void setPAN(short fieldValue);
     public void setass(short fieldValue);
     public void setSuperFrameSpecs();
     public void setIEEEheaderLength(int fieldValue);
     //-----------------------------------------------//

     //----------------- Get Methods -----------------//
     public short getDestAddMode();
     public short getSrcAddMode();
     public short getIntra_PAN();
     public String getDispID();
     public String getLength();
     public String getFrameType();
     public short getSec();
     public short getPnd();
     public short getAck();
     public short getIntra();
     public short getReserved();
     public String[] getFrameCon();
     public String getSeq();
     public String getDestPAN();
     public String getDestADD();
     public String getSrcPAN();
     public String getSrcADD();
     public String getFCS();
     public String getGTS();
     public String getGTSDirec();
     public ArrayList<String> getGTSList();
     public String getPending();
     public String getPendingList();

     //public String getIEEEcase();
     public short getBO();
     public short getSO();
     public short getFinCap();
     public short getbat();
     public short getres();
     public short getPAN();
     public short getass();
     public String[] getSuperFrameSpecs();
     public int getIEEEheaderLength();
     //-----------------------------------------------//
}