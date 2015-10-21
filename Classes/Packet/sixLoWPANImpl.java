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

public class sixLoWPANImpl extends IEEE802_15_4Impl implements sixLoWPAN {

      //Class Attribute
      private String sixLoWPANType;
      private String dispatchField;
      private String encodingField;
      private String nextHeaderField;
      private String hopLimitField;
      private String sourceAddField;
      private String destAddField;
      private String contextField;
      private String trafficClass_FlowLabelField;
	  private String sixLoWPANPayload;

      //Constructor
      public sixLoWPANImpl(){
            sixLoWPANType = "";
            dispatchField = "";
            encodingField = "";
            nextHeaderField = "";
            hopLimitField = "";
            sourceAddField = "";
            destAddField = "";
	    contextField = "";
	    trafficClass_FlowLabelField="";
		sixLoWPANPayload="";
      }

      //----------------- Set Methods -----------------//
      public void set6LoWPANType(String type) {
            sixLoWPANType = type;
      }

      public void setDispatchField(String dispatch) {
            dispatchField= dispatch;
      }

      public void setEncodingField(String encoding) {
            encodingField= encoding;
      }

      public void setNextHeaderField(String nextHeader) {
            nextHeaderField = nextHeader;
      }

      public void setHopLimitField(String hopLimit) {
            hopLimitField = hopLimit;
      }

      public void setSourceAddField(String sourceAdd) {
            sourceAddField = sourceAdd;
      }

      public void setDestAddField(String destAdd) {
            destAddField = destAdd;
      }

      public void setcontextField(String context) {
            contextField = context;
      }

      public void settrafficClass_FlowLabelField (String trafficClass_FlowLabel) {
            trafficClass_FlowLabelField = trafficClass_FlowLabel;
      }
	  
	  public void set6LoWPANPayload  (String payload) {
			sixLoWPANPayload = payload;
	  }
      //-----------------------------------------------//

      //----------------- Get Methods -----------------//
      public String get6LoWPANType() {
            return sixLoWPANType;
      }

      public String getDispatchField() {
            return dispatchField;
      }

      public String getEncodingField() {
            return encodingField;
      }

      public String getNextHeaderField() {
            return nextHeaderField;
      }

      public String getHopLimitField() {
            return hopLimitField;
      }

      public String getSourceAddField() {
            return sourceAddField;
      }

      public String getDestAddField() {
            return destAddField;
      }

      public String getcontextField() {
            return contextField;
      }
      
      public String gettrafficClass_FlowLabelField() {
            return trafficClass_FlowLabelField;
      }
	  
	  public String get6LoWPANPayload() {
			return sixLoWPANPayload;
	  }
      //-----------------------------------------------//
}
