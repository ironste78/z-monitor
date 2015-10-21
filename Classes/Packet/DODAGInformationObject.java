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
 * Class DODAG-Information-Object contains all DIO fields.
 */
 public interface DODAGInformationObject extends sixLoWPAN
{
     //----------------- Set Methods -----------------//
     public void seticmp_Type(String fieldValue);
     public void seticmp_Code(String fieldValue);
     public void seticmp_Cheksum(String fieldValue);
     public void setRPLInstanceID(String fieldValue);
     public void setVersion(String fieldValue);
     public void setRank(String fieldValue);
     public void setFlags(String fieldValue);
     public void setDATSN(String fieldValue);
     public void setFlags2(String fieldValue);
     public void setReserved_icmp(String fieldValue);
     public void setDODAGID(String fieldValue);
     public void setMetricContainerType(String fieldValue);
     public void setMetricContainerLength(String fieldValue); 
     public void setDODAGConfigurationType(String fieldValue);    
     public void setDODAGConfigurationLength(String fieldValue);
     public void setDODAGConfigurationFlags(String fieldValue);
     public void setDODAGConfigurationDIOIntervalDoubling(String fieldValue);
     public void setDODAGConfigurationDIOIntervalMin(String fieldValue);
     public void setDODAGConfigurationDIORedundancyConstant(String fieldValue);
     public void setDODAGConfigurationMaxRank(String fieldValue); 
     public void setDODAGConfigurationMinHopRank(String fieldValue);  
      public void setDODAGConfigurationOCP(String fieldValue); 
    public void setDODAGConfigurationReserved(String fieldValue); 
     public void setDODAGConfigurationDefaultLifetime(String fieldValue); 
      public void setDODAGConfigurationLifetimeUnit(String fieldValue);  
      public void setPrefixInformationType(String fieldValue);
     public void setPrefixInformationLength(String fieldValue);
     public void setPrefixInformation_PrefixLength(String fieldValue);
     public void setPrefixInformationFlags(String fieldValue);
     public void setPrefixInformationValidLifetime(String fieldValue);
     public void setPrefixInformationPreferredLifetime(String fieldValue);
     public void setPrefixInformationReserved(String fieldValue);
     public void setPrefixInformationDestinationPrefix(String fieldValue); 

     //----------------- Get Methods -----------------//
     public String geticmp_Type();
     public String geticmp_Code();
     public String geticmp_Cheksum();
     public String getRPLInstanceID();
     public String getVersion();
     public String getRank();
     public String getFlags();
     public String getDATSN();
     public String getFlags2();
     public String getReserved_icmp();
     public String getDODAGID();
     public String getMetricContainerType();
     public String getMetricContainerLength();     
     public String getDODAGConfigurationType();
     public String getDODAGConfigurationLength();
     public String getDODAGConfigurationFlags();
     public String getDODAGConfigurationDIOIntervalDoubling();
     public String getDODAGConfigurationDIOIntervalMin();
     public String getDODAGConfigurationDIORedundancyConstant(); 
     public String getDODAGConfigurationMaxRank();
     public String getDODAGConfigurationMinHopRank();
     public String getDODAGConfigurationOCP();
     public String getDODAGConfigurationReserved();
     public String getDODAGConfigurationDefaultLifetime();
     public String getDODAGConfigurationLifetimeUnit();
     public String getPrefixInformationType();
     public String getPrefixInformationLength();
     public String getPrefixInformation_PrefixLength();
     public String getPrefixInformationFlags();
     public String getPrefixInformationValidLifetime();
     public String getPrefixInformationPreferredLifetime();
     public String getPrefixInformationReserved();     
     public String getPrefixInformationDestinationPrefix();

}
