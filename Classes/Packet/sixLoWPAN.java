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
 * Class sixLoWPAN contain the type of 6LoWPAN frame, and all possible 6LoWPAN fields.
 */

public interface sixLoWPAN extends IEEE802_15_4 {
      
	  //Set Methods
      public void set6LoWPANType (String type);
      public void setDispatchField (String dispatch);
      public void setEncodingField (String encoding);
      public void setNextHeaderField (String nextHeader);
      public void setHopLimitField (String hopLimit);
      public void setSourceAddField (String sourceAdd);
      public void setDestAddField (String destAdd);
      public void setcontextField (String context);
      public void settrafficClass_FlowLabelField (String trafficClass_FlowLabel);
	  public void set6LoWPANPayload  (String payload);
	  //Get Methods
      public String get6LoWPANType ();
      public String getDispatchField ();
      public String getEncodingField ();
      public String getNextHeaderField ();
      public String getHopLimitField ();
      public String getSourceAddField ();
      public String getDestAddField ();
      public String getcontextField ();
      public String gettrafficClass_FlowLabelField ();
	  public String get6LoWPANPayload();

}
