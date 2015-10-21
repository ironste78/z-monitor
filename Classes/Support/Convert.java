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


package Classes.Support;


public interface Convert {
      public String convertDecToBin(String x); //convert each bit from the decimal number to 4 bit binary number
      public String convertHexToBin(String x); //convert a bit of hex to 4 bit binary number
      public String ConvertBinToDec(String x); //convert a binary number to decimal
      public String ConHexaToDecimal(String x); //convert a Hex number to decimal
      public String ConvertBytToHex(byte[] packetValue); //convert array of byte to string of hex
      public int Hex(String x); //convert a bit of hex to int
      public byte[] hexStringToByteArray(String s); //convert hex string to array of byte
      public String byteSwap(String bytes); //swap first 3 bit with second 3 bit from the byte
	  public String divideAndConvert(String x); //convert a stream of hex to binary
      public String removeSpaces(String x); //take a string and return string without spaces
      public String removeHtmlTags(String x); //remove html tags from html code
	 
}
