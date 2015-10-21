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
 * buffer: is used to store the divided packets into vector. Any class wants to
 * use these frames read it from these class.
 */

import Classes.Packet.*;
import Classes.Support.ICMPStruct;
import java.util.Vector;

public class bufferImpl implements buffer {

      //Class Attribute
      private Vector<IEEE802_15_4> IEEE802_15_4packets;
      private Vector<sixLoWPAN> sixLoWPANpackets;
      private Vector<ICMPStruct> ICMPStructpackets;
      private Vector<String> origonalPackets;
      private Vector<Double> origonalTimes;
      //-----------------------------------------------//

      //Constructor
      bufferImpl(){
            IEEE802_15_4packets = new Vector<IEEE802_15_4>();
            sixLoWPANpackets = new Vector<sixLoWPAN>();
            ICMPStructpackets = new Vector<ICMPStruct>();
	    origonalPackets = new Vector<String>();
            origonalTimes = new Vector<Double>();
      }
      //-----------------------------------------------//

      /**************************************************************************/
      /* Function: AddPackets                                                   */
      /* Arguments : IEEE802_15_4 or sixLoWPAN or ICMPStruct: packet            */
      /*             these argument is a divided packet which we will store it  */
      /* Return: Nothing                                                        */
      /* function add the packet to the vector                                  */
      /**************************************************************************/
      public void AddPackets(IEEE802_15_4 packet) {
            IEEE802_15_4packets.add(packet);
      }
      public void AddPackets(sixLoWPAN packet) {
            sixLoWPANpackets.add(packet);
      }
      public void AddPackets(ICMPStruct packet) {
            ICMPStructpackets.add(packet);
      }
      //-----------------------------------------------//

      /**************************************************************************/
      /* Function: AddOrigonalPackets                                           */
      /* Arguments : String: packet                                             */
      /*             these argument is the original packet 			            */
      /* Return: Nothing                                                        */
      /* function add the original packet to the vector                         */
      /**************************************************************************/
      public void AddOrigonalPackets(String packet) {
            origonalPackets.add(packet);
      }
      //-----------------------------------------------//

      /**************************************************************************/
      /* Function: AddOrigonalTimes                                             */
      /* Arguments : Double: time                                               */
      /*             these argument is the real arrival time for packet 	    */
      /* Return: Nothing                                                        */
      /* function add the original arrival time for a packet to the vector      */
      /**************************************************************************/
      public void AddOrigonalTimes(double time) {
            origonalTimes.add(time);
      }
      //-----------------------------------------------//

      /*************************************************************/
      /* Function: getPackets                                      */
      /* Arguments : Nothing                                       */
      /* Return: Vector packets                                    */
      /* function return the vector (packets)                      */
      /*************************************************************/
      public Vector<IEEE802_15_4> IEEE802_15_4getPackets() {
            return IEEE802_15_4packets;
      }
      public Vector<sixLoWPAN> sixLoWPANgetPackets() {
            return sixLoWPANpackets;
      }
      public Vector<ICMPStruct> ICMPgetPackets() {
            return ICMPStructpackets;
      }
      //-----------------------------------------------//

      /*************************************************************/
      /* Function: getOrigonalPackets                              */
      /* Arguments : Nothing                                       */
      /* Return: Vector packets                                    */
      /* function return the vector (origonalPackets)              */
      /*************************************************************/
      public Vector<String> getOrigonalPackets() {
            return origonalPackets;
      }
      //-----------------------------------------------//

      /*************************************************************/
      /* Function: getOrigonalTimes                                */
      /* Arguments : Nothing                                       */
      /* Return: Vector times                                      */
      /* function return the vector (origonalTimes)                */
      /*************************************************************/
      public Vector<Double> getOrigonalTimes() {
            return origonalTimes;
      }
      //-----------------------------------------------//

}
