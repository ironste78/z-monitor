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
 * ThemeChange: Change the whole theme of the GUI.
 */

//import libraries
import javax.swing.*;
import java.awt.event.*;

public class ThemeChange implements ActionListener
{
      //Class Attribute
      JFrame reDrawableFrame;
      String lookAndfeel;
      //-----------------------------------------------//

      //Constructor method
      public ThemeChange(JFrame activeFrame,String themeName)
      {
            reDrawableFrame=activeFrame;
            lookAndfeel=themeName;
      }
      //-----------------------------------------------//

    /****************************************************************************/
    /* Function: actionPerformed                                                */
    /* Arguments : ActionEvent: event: the event that characterizes the change. */
    /* Return: Nothing                                                          */
    /* This method invoked when an action occurs.                               */
    /****************************************************************************/
      public void actionPerformed(ActionEvent event)
      {
            try
            {
                  UIManager.setLookAndFeel(lookAndfeel);
                  SwingUtilities.updateComponentTreeUI(reDrawableFrame);
                  reDrawableFrame.repaint();
                  Thread.sleep(500);
            }catch(Exception them1Exception){}
      }
      //-----------------------------------------------//
}
