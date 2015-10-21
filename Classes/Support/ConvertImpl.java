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


/*
 * convert from specific representation to another
 */

package Classes.Support;


public class ConvertImpl implements Convert {

      /****************************************************************************/
      /* Function: convertDecToBin                                                */
      /* Arguments : String: Decimal number                                       */
      /* Return: String: Binary number                                            */
      /* function convert each bit from the decimal number to 4 bit binary number */
      /****************************************************************************/
      public String convertDecToBin(String x) {
            String s=x.trim();
            int i=0;
            String ch="";
            while (i<s.length())
            {
                  if(s.substring(i,1).equals("0"))
                        ch=ch+"0000";
                  if(s.substring(i,1).equals("1"))
                        ch=ch+"0001";
                  if(s.substring(i,1).equals("2"))
                        ch=ch+"0010";
                  if(s.substring(i,1)=="3")
                        ch=ch+"0011";
                  if(s.substring(i,1)=="4")
                        ch=ch+"0100";
                  if(s.substring(i,1)=="5")
                        ch=ch+"0101";
                  if(s.substring(i,1)=="6")
                        ch=ch+"0110";
                  if(s.substring(i,1)=="7")
                        ch=ch+"0111";
                  i++;
            }
            return ch;
      }

      /*********************************************************/
      /* Function: convertHexToBin                             */
      /* Arguments : String: Hex number                        */
      /* Return: String: Binary number                         */
      /* function convert a bit of hex to 4 bit binary number  */
      /*********************************************************/
	  public String convertHexToBin(String x){
            if(x.equals("0"))
                  return "0000";
            else if(x.equals("1"))
                  return "0001";
            else if(x.equals("2"))
                  return "0010";
            else if(x.equals("3"))
                  return "0011";
           	else if(x.equals("4"))
                  return "0100";
            else if(x.equals("5"))
                  return "0101";
           	else if(x.equals("6"))
                  return "0110";
           	else if(x.equals("7"))
                  return "0111";
            else if(x.equals("8"))
                  return "1000";
            else if(x.equals("9"))
                  return "1001";
            else if(x.equals("A"))
                  return "1010";
            else if(x.equals("B"))
                  return "1011";
            else if(x.equals("C"))
                  return "1100";
            else if(x.equals("D"))
                  return "1101";
            else if(x.equals("E"))
                  return "1110";
            else if(x.equals("F"))
                  return "1111";
            return "";

      }

      /***********************************************/
      /* Function: ConvertBinToDec                   */
      /* Arguments : String: binary number           */
      /* Return: String: decimal number              */
      /* function convert a binary number to decimal */
      /***********************************************/
	  public String ConvertBinToDec(String x)
      {
            if(x.equals("0") || x.equals("00") || x.equals("000") || x.equals("0000"))
                  return "0";
            else if(x.equals("1") || x.equals("01") || x.equals("001") || x.equals("0001"))
                  return "1";
            else if(x.equals("10") || x.equals("010") || x.equals("0010"))
                  return "2";
            else if(x.equals("11") || x.equals("011") || x.equals("0011"))
                  return "3";
            else if(x.equals("100") || x.equals("0100"))
                  return "4";
            else if( x.equals("101") || x.equals("0101"))
                  return "5";
            else if( x.equals("110") || x.equals("0110"))
                  return "6";
            else if( x.equals("111") || x.equals("0111"))
                  return "7";
            else if(x.equals("1000"))
                  return "8";
            else if(x.equals("1001"))
                  return "9";
            else if(x.equals("1010"))
                  return "10";
            else if(x.equals("1011"))
                  return "11";
            else if(x.equals("1100"))
                  return "12";
            else if(x.equals("1101"))
                  return "13";
            else if(x.equals("1110"))
                  return "14";
            else if(x.equals("1111"))
                  return "15";

            return "";
		}

      /*********************************************/
      /* Function: ConHexaToDecimal                */
      /* Arguments : String: Hex number            */
      /* Return: String: decimal number            */
      /* function convert a Hex number to decimal  */
      /*********************************************/
	  public String ConHexaToDecimal(String x)
		{
            String sub = "";
            int sum=0;
            for(int i=x.length(),j=0;i>0;i--,j++)
            {
                  sub = x.substring(i-1,i);
                  if(sub.equals("") || sub.equals(" "))
                        j--;
                  else
                  {
                        sum+=Hex(sub)*Math.pow(16,j);
                  }
            }
            return sum+"";
		}

	  /********************************************/
      /* Function: Hex   						  */
      /* Arguments : String: Hex number   		  */
      /* Return: int: integer number              */
      /* this method convert a bit of hex to int  */
      /********************************************/
      public int Hex(String x)
		{
            if(x.equals("1"))
                  return 1;
            else if(x.equals("2"))
                  return 2;
            else if(x.equals("3"))
                  return 3;
            else if(x.equals("4"))
                  return 4;
            else if(x.equals("5"))
                  return 5;
            else if(x.equals("6"))
                  return 6;
            else if(x.equals("7"))
                  return 7;
            else if(x.equals("8"))
                  return 8;
            else if(x.equals("9"))
                  return 9;
            else if(x.equals("A"))
                  return 10;
            else if(x.equals("B"))
                  return 11;
            else if(x.equals("C"))
                  return 12;
            else if(x.equals("D"))
                  return 13;
            else if(x.equals("E"))
                  return 14;
            else if(x.equals("F"))
                  return 15;
            return 0;

      }

	  /*************************************************************************/
      /* Function: hexStringToByteArray                                        */
      /* Arguments : String: Hex string                                        */
      /* Return: String: Byte Array                                            */
      /* this method convert hex string to array of byte to calculate the RSSI */
      /*************************************************************************/
      public byte[] hexStringToByteArray(String s)
      {
            int len = s.length();
            byte[] data = new byte[len / 2];
            for (int i = 0; i < len; i += 2)
            {
                  data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
            }
            return data;
      }

      /****************************************************************/
      /* Function: byteSwap                                           */
      /* Arguments : String: byte                                     */
      /* Return: String: swapped byte                                 */
      /* this method swap the input string by the middle              */
      /****************************************************************/
	  public String byteSwap(String bytes){

            String withoutSpaces=(new ConvertImpl().removeSpaces(bytes));
            String swap="";
            for(int i=withoutSpaces.length();i>0;i-=2)
            {
               swap+=withoutSpaces.substring(i-2,i);

            }
            return swap;
           
      }

	  /**************************************************/
      /* Function: divideAndConvert                     */
      /* Arguments : String: Hex                        */
      /* Return: String: binary                         */
      /* this method convert a stream of hex to binary  */
      /**************************************************/
      public String divideAndConvert(String x){
            x = x.trim();
            String f1 = x.substring(0,1);

            String f2 = x.substring(1,2);

            String f3 = x.substring(2,3);

            String f4 = x.substring(3);

            return convertHexToBin(f1)+convertHexToBin(f2)+convertHexToBin(f3)+convertHexToBin(f4);

		}

	  /**************************************************************/
      /* Function: removeSpaces   									*/
      /* Arguments : String   										*/
      /* Return: String: string without spaces            			*/
      /* this method take a string and return string without spaces */
      /**************************************************************/
      public String removeSpaces(String x)
      {
            String result="";
            String sub;
            for(int i=0;i<x.length();i++)
            {
                  sub=x.substring(i,i+1);
                  if(!sub.equals(" "))
                        result+=sub;
            }
            return result;
      }
	  
	  /******************************************************/
      /* Function: ConvertBytToHex   						*/
      /* Arguments : byte[]: packet in byte   				*/
      /* Return: String: packet in hex		            	*/
      /* this method convert array of byte to string of hex */
      /******************************************************/
      public String ConvertBytToHex(byte[] packetValue)
      {
        String packetInHex="";	
        		for(int i=0;i<packetValue.length;i++)
	        	{
	            		int oneByte=packetValue[i];
	            		String oneString=Integer.toHexString(oneByte & 0xff).toUpperCase();	
	            		if(oneByte>=0 && oneByte<16)
	                	packetInHex+="0";
	            		packetInHex+=oneString+" ";
	        	}
	        	return packetInHex;        
      }
	  
	  /***********************************************/
      /* Function: removeHtmlTags   				 */
      /* Arguments : String: HTML   				 */
      /* Return: String: the content of the HTML	 */
      /* this method remove html tags from html code */
      /***********************************************/
      public String removeHtmlTags(String x)
      {
		String sub="";
		String res="";
		boolean take=true;
		for(int i=0;i<x.length()-2;i++)
		{
			sub=x.substring(i,i+1);
			if(sub.equals("<"))
			{
				take=false;
			}
			if(take)
				res+=sub;
			if(sub.equals(">"))
			{
				take=true;
			}

		}
		return res;
	}
}