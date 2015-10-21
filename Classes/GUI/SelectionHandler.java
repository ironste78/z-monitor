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
/*
 * SelectionHandler: Selection listener for table in packet analysis tab.
 */

//import libraries
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.*;
import java.util.*;
import Classes.Support.*;

public class SelectionHandler implements ListSelectionListener
{
    //Class Attribute
    private JTable OtherControl,finalPacket;
    private int type;
    private int myCase;
    boolean blockEvents;
    LinkedList<ListSelectionModel> listOfListModel=new LinkedList<ListSelectionModel>();
    //-----------------------------------------------//

    //Constructor method
    public SelectionHandler(JTable y,JTable x,int t,int a,boolean BlockEvents,LinkedList<ListSelectionModel> ListOfListModel)
    {
        OtherControl=x;
        type=t;
        finalPacket=y;
        myCase = a;
        blockEvents=BlockEvents;
        listOfListModel=ListOfListModel;
    }
    //-----------------------------------------------//

    /***********************************************************************************/
    /* Function: valueChanged                                                          */
    /* Arguments : ListSelectionEvent: event :the event that characterizes the change. */
    /* Return: Nothing                                                                 */
    /* This method called whenever the value of the selection changes.                 */
    /***********************************************************************************/
    public void valueChanged(ListSelectionEvent event)
    {
            if (blockEvents)
            {
                // already handling a previous event
                return;
            }
            blockEvents = true;
            ListSelectionModel lsm = (ListSelectionModel) event.getSource();
            for(ListSelectionModel list:listOfListModel)
            {

            	// catch the selected table
            	if (lsm.equals(list))
            	{
		           if(type==0)
			   {
		            	// print the specification on the table
		            	OtherControl.setValueAt("HexaDecimal", 0, 0);
		                OtherControl.setValueAt("Decimal", 1, 0);
		                switch(myCase)
						{
							case 1:
								OtherControl.setValueAt(finalPacket.getValueAt(0,11).toString().trim(), 0, 1);
                                                                OtherControl.setValueAt((finalPacket.getValueAt(0,16).toString().trim().equals("01")?"01(OK)":"00(ERROR)"), 0, 2);
                                                                OtherControl.setValueAt(finalPacket.getValueAt(0,13).toString().trim(), 0, 3);
                                                                OtherControl.setValueAt(new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,11).toString().trim()), 1, 1);
                                                                OtherControl.setValueAt((new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,16).toString().trim()).equals("1")?"1(OK)":"0(ERROR)"), 1, 2);
                                                                OtherControl.setValueAt(new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,13).toString().trim()), 1, 3);
								break;

							case 2:
								OtherControl.setValueAt(finalPacket.getValueAt(0,10).toString().trim(), 0, 1);
                                                                OtherControl.setValueAt((finalPacket.getValueAt(0,15).toString().trim().equals("01")?"01(OK)":"00(ERROR)"), 0, 2);
                                                                OtherControl.setValueAt(finalPacket.getValueAt(0,12).toString().trim(), 0, 3);
                                                                OtherControl.setValueAt(new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,10).toString().trim()), 1, 1);
                                                                OtherControl.setValueAt((new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,15).toString().trim()).equals("1")?"1(OK)":"0(ERROR)"), 1, 2);
                                                                OtherControl.setValueAt(new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,12).toString().trim()), 1, 3);
								break;

							case 3:
								OtherControl.setValueAt(finalPacket.getValueAt(0,9).toString().trim(), 0, 1);
                                                                OtherControl.setValueAt((finalPacket.getValueAt(0,14).toString().trim().equals("01")?"01(OK)":"00(ERROR)"), 0, 2);
                                                                OtherControl.setValueAt(finalPacket.getValueAt(0,11).toString().trim(), 0, 3);
                                                                OtherControl.setValueAt(new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,9).toString().trim()), 1, 1);
                                                                OtherControl.setValueAt((new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,14).toString().trim()).equals("1")?"1(OK)":"0(ERROR)"), 1, 2);
                                                                OtherControl.setValueAt(new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,11).toString().trim()), 1, 3);
								break;

							case 4:
								OtherControl.setValueAt(finalPacket.getValueAt(0,8).toString().trim(), 0, 1);
                                                                OtherControl.setValueAt((finalPacket.getValueAt(0,13).toString().trim().equals("01")?"01(OK)":"00(ERROR)"), 0, 2);
                                                                OtherControl.setValueAt(finalPacket.getValueAt(0,10).toString().trim(), 0, 3);
                                                                OtherControl.setValueAt(new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,9).toString().trim()), 1, 1);
                                                                OtherControl.setValueAt((new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,13).toString().trim()).equals("1")?"1(OK)":"0(ERROR)"), 1, 2);
                                                                OtherControl.setValueAt(new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,10).toString().trim()), 1, 3);
								break;
						}
		            }
		            else if(type==1)
		            {
		            	// print the specification on the table
		            	OtherControl.setValueAt("HexaDecimal", 0, 0);
		                OtherControl.setValueAt("Decimal", 1, 0);
		                switch(myCase)
						{
							case 1:
								OtherControl.setValueAt(finalPacket.getValueAt(0,10).toString().trim(), 0, 1);
                                                                OtherControl.setValueAt((finalPacket.getValueAt(0,15).toString().trim().equals("01")?"01(OK)":"00(ERROR)"), 0, 2);
                                                                OtherControl.setValueAt(finalPacket.getValueAt(0,12).toString().trim(), 0, 3);
                                                                OtherControl.setValueAt(new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,10).toString().trim()), 1, 1);
                                                                OtherControl.setValueAt((new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,15).toString().trim()).equals("1")?"1(OK)":"0(ERROR)"), 1, 2);
                                                                OtherControl.setValueAt(new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,12).toString().trim()), 1, 3);
								break;

							case 2:
								OtherControl.setValueAt(finalPacket.getValueAt(0,9).toString().trim(), 0, 1);
                                                                OtherControl.setValueAt((finalPacket.getValueAt(0,14).toString().trim().equals("01")?"01(OK)":"00(ERROR)"), 0, 2);
                                                                OtherControl.setValueAt(finalPacket.getValueAt(0,11).toString().trim(), 0, 3);
                                                                OtherControl.setValueAt(new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,9).toString().trim()), 1, 1);
                                                                OtherControl.setValueAt((new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,14).toString().trim()).equals("1")?"1(OK)":"0(ERROR)"), 1, 2);
                                                                OtherControl.setValueAt(new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,11).toString().trim()), 1, 3);
								break;

							case 3:
								OtherControl.setValueAt(finalPacket.getValueAt(0,8).toString().trim(), 0, 1);
                                                                OtherControl.setValueAt((finalPacket.getValueAt(0,13).toString().trim().equals("01")?"01(OK)":"00(ERROR)"), 0, 2);
                                                                OtherControl.setValueAt(finalPacket.getValueAt(0,10).toString().trim(), 0, 3);
                                                                OtherControl.setValueAt(new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,9).toString().trim()), 1, 1);
                                                                OtherControl.setValueAt((new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,13).toString().trim()).equals("1")?"1(OK)":"0(ERROR)"), 1, 2);
                                                                OtherControl.setValueAt(new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,10).toString().trim()), 1, 3);
								break;

							case 4:
								OtherControl.setValueAt(finalPacket.getValueAt(0,7).toString().trim(), 0, 1);
                                                                OtherControl.setValueAt((finalPacket.getValueAt(0,12).toString().trim().equals("01")?"01(OK)":"00(ERROR)"), 0, 2);
                                                                OtherControl.setValueAt(finalPacket.getValueAt(0,9).toString().trim(), 0, 3);
                                                                OtherControl.setValueAt(new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,7).toString().trim()), 1, 1);
                                                                OtherControl.setValueAt((new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,12).toString().trim()).equals("1")?"1(OK)":"0(ERROR)"), 1, 2);
                                                                OtherControl.setValueAt(new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,9).toString().trim()), 1, 3);
								break;
						}
		            }
		            else
		            {
		            	// print the specification on the table
		                OtherControl.setValueAt("HexaDecimal", 0, 0);
		                OtherControl.setValueAt("Decimal", 1, 0);
		                OtherControl.setValueAt(finalPacket.getValueAt(0,5).toString().trim(), 0, 1);
		                OtherControl.setValueAt((finalPacket.getValueAt(0,10).toString().trim().equals("01")?"01(OK)":"00(ERROR)"), 0, 2);
		                OtherControl.setValueAt(finalPacket.getValueAt(0,7).toString().trim(), 0, 3);
		                OtherControl.setValueAt(new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,5).toString().trim()), 1, 1);
		                OtherControl.setValueAt((new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,10).toString().trim()).equals("1")?"1(OK)":"0(ERROR)"), 1, 2);
		                OtherControl.setValueAt(new ConvertImpl().ConHexaToDecimal(finalPacket.getValueAt(0,7).toString().trim()), 1, 3);
		            }
            	}
            	//remove selection from other table
            	else
            		list.clearSelection();
            	// to allow another selection
            	blockEvents = false;
            }
    }
    //-----------------------------------------------//
}