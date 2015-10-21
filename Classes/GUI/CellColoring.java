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
 * Cellcolouring: To colouring the cell in the table depends on it layer.
 */
import Classes.Support.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
 
public class Cellcolouring extends DefaultTableCellRenderer
	{

                //Class Attribute
		private short packetType;
                //-----------------------------------------------//

                //Constructor method
		public Cellcolouring(short type)
		{
			packetType = type;
		}
                //-----------------------------------------------//
                
                /********************************************************************************************************/
                /* Function: getTableCellRendererComponent                                                              */
                /* Arguments :  JTable: table: the JTable that is asking the renderer to draw.                          */
                /*              Object: value: the value of the cell to be rendered.                                    */
                /*              boolean: isSelected: true if the cell is to be rendered with the selection highlighted. */
                /*              boolean: hasFocus: if true, render cell appropriately.                                  */
                /*              int: row: the row index of the cell being drawn.                                        */
                /*              int: col: the column index of the cell being drawn.                                     */
                /* Return: Component: Returns the cell after colouring it.                                              */
                /* colouring the cell in the table depends on it layer.                                                 */
                /********************************************************************************************************/
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col)
		{
                        String columnName=new ConvertImpl().removeHtmlTags(table.getModel().getColumnName(col));
                        String sub = "";
                        String sub2 = "";
                        try{
                                sub = columnName.substring(0,9);
                                sub2 = columnName.substring(0,4);
                        }catch(Exception e){}
                        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                        setHorizontalAlignment(SwingConstants.CENTER);
                      if(sub.equals("Time (ms)") || columnName.equals("GTS") || columnName.equals("LQI") || columnName.equals("RSSI") || columnName.equals("CRC") || columnName.equals ("Header Length") || columnName.equals("Channel") || columnName.equals("Code") || columnName.equals("Cheksum")  || sub.equals("MAC Heade") || columnName.equals("Type") )
                              comp.setBackground(new Color(194, 223, 255));
                      else if (columnName.equals("DispatchID") || columnName.equals("Length") || sub.equals("Frame con") || sub2.equals("Sequ") || sub.equals("Destinati") || sub2.equals("Sour") || sub.equals("Super fra") || columnName.equals("FCS") || sub2.equals("Dest") || columnName.equals("Dest.PAN") || sub2.equals("Src."))
                              comp.setBackground(new Color(240, 239, 239));
                      else if (columnName.equals("Dispatch") || columnName.equals("Encoding") || sub.equals("Next head") || columnName.equals("Hop limit") || sub2.equals("6LoW") || columnName.equals("Context") || sub.equals("Traffic c") )
                              comp.setBackground(new Color(204, 251, 93));
                    else if (columnName.equals("Payload") || columnName.equals("Frame") )  comp.setBackground(new Color(255, 200, 0));
                    else
                              comp.setBackground(Color.WHITE);
			return comp;
		}
                //-----------------------------------------------//
	}
