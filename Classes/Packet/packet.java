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
 * packet: contain the original packet and general info about the packet.
 */

public interface packet {
      //Set Methods
      public void setPacketType (short type);
      public void setPacket (String OrignalPacket);
      public void setpacketinHexa(String a);
      public void setNO(int s);
      public void setlength(int add);

      //Get Methods
      public short getPacketType ();
      public String getPacket ();
      public String getpacketinHexa();
      public int getNO();
      public int getlength();


}
