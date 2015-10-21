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

import Classes.Mode.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Classes.Storage.*;
import Classes.Support.*;


public class Zmonitor {

   private JFrame main;
   private JButton standalone;
   private JButton clientuser;
   private JButton provider;

   public static snifferImpl sniffer;
   public static buffer packetBuffer;
   public static databaseStorage connection;
   public static SntpClient clientNTP;
   public static int rowWidth;
   public static String protocol;
    

   //**********************CONSTRUCTOR********************************//
   public Zmonitor( )
   {
        
        main = new JFrame("Z-Monitor");
        main.getContentPane().setLayout(new FlowLayout());

        // Retrieve the icon
        ClassLoader cldr = this.getClass().getClassLoader();

        Icon iconstandalone = new ImageIcon(cldr.getResource("Classes/image/standalone.png"));
        standalone = new JButton("Standalone version");
        standalone.setIcon(iconstandalone);
        main.getContentPane().add (standalone);
        Icon iconclient = new ImageIcon(cldr.getResource("Classes/image/client.png"));
        clientuser = new JButton("Client user version");
        clientuser.setIcon(iconclient);
        main.getContentPane().add (clientuser);
        Icon iconprovider = new ImageIcon(cldr.getResource("Classes/image/provider.png"));
        provider = new JButton("Provider user version");
        provider.setIcon(iconprovider);
        main.getContentPane().add (provider);

        standalone.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
                        main.setVisible(false);
                        SwingUtilities.invokeLater(new Runnable() {
                           @Override
                           public void run()
                           {
                              protocol = "IEEE 802.15.4";

                              sniffer =new snifferImpl();
                              packetBuffer = new bufferImpl();
                              connection = new databaseStorage("","","","","");
                              Standalone frame = new Standalone();
                           }
                        });
        	}
        });

        clientuser.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
                        main.setVisible(false);
                        SwingUtilities.invokeLater(new Runnable() {
                        @Override
                           public void run()
                           {
                              protocol = "IEEE 802.15.4";

                              packetBuffer = new bufferImpl();
                              connection = new databaseStorage("","","","","");
                              Client frame = new Client();
                           }
                        });
        	}
        });

        provider.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
                        main.setVisible(false);
                        SwingUtilities.invokeLater(new Runnable() {
                           @Override
                           public void run()
                           {
                              protocol = "IEEE 802.15.4";

                              sniffer =new snifferImpl();
                              packetBuffer = new bufferImpl();
                              connection = new databaseStorage("","","","","");
                              Provider frame = new Provider();
                           }
                        });
        	}
        });


        main.pack();

        main.setVisible(true);
    }

   //-----------------------------------------------//
   public static void StandaloneReset()
   {
        SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run()
			{
				sniffer =new snifferImpl();
				packetBuffer = new bufferImpl();
                                connection = new databaseStorage("","","","","");
				Standalone frame = new Standalone();
			}
		});
   }

   //-----------------------------------------------//
   public static void ProviderReset()
   {
        SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run()
			{
				sniffer =new snifferImpl();
				packetBuffer = new bufferImpl();
                                connection = new databaseStorage("","","","","");
				Provider frame = new Provider();
			}
		});
   }
   //-----------------------------------------------//
   public static void ClientReset()
   {
        SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run()
			{
				packetBuffer = new bufferImpl();
                                connection = new databaseStorage("","","","","");
                                Client frame = new Client();
			}
		});
   }
   //-----------------------------------------------//
    public static void main(String [] args)
    {
       Zmonitor aux=new Zmonitor();
    }
}
