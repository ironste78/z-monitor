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

public interface  physical  extends packet {

      //Set Methods
      public void setLQI(String fieldValue);
      public void setRSSI(String fieldValue);
      public void setCRC(String fieldValue);
      public void setMhrlen(String fieldValue);
      public void setChannel(String fieldValue);
      public void setTimeStamp(String fieldValue);
      public void setType(String fieldValue);
      public void setCode(String fieldValue);
      public void setCheksum(String fieldValue);
      public void setPayload(String fieldValue);
      public void setRealArrivalTime(double fieldValue);

      //Get Methods
      public String getLQI();
      public String getRSSI();
      public String getCRC();
      public String getMhrlen();
      public String getChannel();
      public String getTimeStamp();
      public String getType();
      public String getCode();
      public String getCheksum();
      public String getPayload();
      public double getRealArrivalTime();

}