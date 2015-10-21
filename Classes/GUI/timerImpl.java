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


package Classes.GUI;

public class timerImpl implements timer
{
        //Class Attribute
        int number;
        String arrivalTime;
        String dayn;
        String month;
        String day;
        String time;
        String AST;
        String year;
        double captureTimeDouble;
        double sinceFirstFrameDouble;
        long captureTime;
        long sinceFirstFrame;
        int frameNo;
        int length=0;
        int bec ;
        int data = 0;
        int ack = 0;
        double times=0;
        //-----------------------------------------------//

        //----------------- Constructor -----------------//
        public timerImpl(){}
        //-----------------------------------------------//

        //----------------- Set Methods -----------------//
        public void setArrivalTime(String add)
        {
            try
            {
                arrivalTime = add;
                dayn=arrivalTime.substring(8,10);
                month=arrivalTime.substring(5,7);
                year=arrivalTime.substring(0,4);
                day=arrivalTime.substring(8,10);
                time=arrivalTime.substring(11);
            }
            catch(Exception e){}
        }

        public void setCaptureTime(long add){captureTime = add;}
        public void setSinceFirstFrame(long add){sinceFirstFrame = add;}
        public void setCaptureTime(double add){captureTimeDouble = add;}
        public void setSinceFirstFrame(double add){sinceFirstFrameDouble = add;}
        public void setFrameNo(int add){frameNo = add;}
        public void setBec(int add,boolean b){
            if (b==false)
                  bec = 1;
            else
                  bec = bec + add;
        }

        public void setLenth(int add){length = length + add;}
        public void setData(int add){data = data + add;}
        public void setAck(int add){ack = ack + add;}
        public void setTimes(double add){times = times + add;}
        //-----------------------------------------------//

        //----------------- Get Methods -----------------//
        public String getDayn(){return dayn;}
	     public String getMonth(){return month;}
	     public String getDay(){return day;}
	     public String getTime(){return time;}
	     public String getYear(){return year;}
        public long getCaptureTime(){return captureTime;}
        public long getSinceFirstFrame(){return sinceFirstFrame;}
        public double getCaptureTimeDouble(){return captureTimeDouble;}
        public double getSinceFirstFrameDouble(){return sinceFirstFrameDouble;}
        public int getFrameNo(){return frameNo;}
        public int getBec(){return bec;}
        public int getLenth(){return length;}
        public int getData(){return data;}
        public int getAck(){return ack;}
        public double getTimes(){return times;}
        //-----------------------------------------------//
        
}
