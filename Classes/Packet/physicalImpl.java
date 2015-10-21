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
 * Class physical contain the fields that are related to the physical layer.
 */

public class physicalImpl  extends packetImpl implements physical {

    //Class Attribute

      private String LQI;
      private String RSSI;
      private String CRC;
      private String mhrlen;
      private String channel;
      private String timeStamp;
      private String type;
      private String code;
      private String cheksum;
      private String payload;
      private double realArrivalTime;

      //Constructor
	  public physicalImpl(){
            LQI = "";
            RSSI = "";
            CRC = "";
            mhrlen = "";
            channel = "";
            timeStamp = "";
            type = "";
            code = "";
            cheksum = "";
            payload = "";
            realArrivalTime=0.0;
      }

      //----------------- Set Methods -----------------//
	  

      public void setLQI(String fieldValue){
            LQI=fieldValue;
      }

      public void setRSSI(String fieldValue){
            RSSI=fieldValue;
      }

      public void setCRC(String fieldValue){
            CRC=fieldValue;
      }

      public void setMhrlen(String fieldValue){
            mhrlen=fieldValue;
      }

      public void setChannel(String fieldValue){
            channel=fieldValue;
      }

      public void setTimeStamp(String fieldValue){
            timeStamp=fieldValue;
      }

      public void setType(String fieldValue){
            type=fieldValue;
      }

      public void setCode(String fieldValue){
            code=fieldValue;
      }

      public void setCheksum(String fieldValue){
            cheksum=fieldValue;
      }

      public void setPayload(String fieldValue){
            payload=fieldValue;
      }

      public void setRealArrivalTime(double fieldValue){
            realArrivalTime=fieldValue;
      }

      //-----------------------------------------------//

      //----------------- Get Methods -----------------//
        

      public String getLQI(){
            return LQI;
      }

      public String getRSSI(){
            return RSSI;
      }

      public String getCRC(){
            return CRC;
      }

      public String getMhrlen(){
            return mhrlen;
      }

      public String getChannel(){
            return channel;
      }

      public String getTimeStamp(){
            return timeStamp;
      }

      public String getType(){
            return type;
      }

      public String getCode(){
            return code;
      }

      public String getCheksum(){
            return cheksum;
      }

      public String getPayload(){
            return payload;
      }

      public double getRealArrivalTime(){
            return realArrivalTime;
      }
      //-----------------------------------------------//
}