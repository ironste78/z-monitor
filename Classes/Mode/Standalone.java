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


package Classes.Mode;

/*
 * ZGUI: Is used to display the GUI.
 */

import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
//import javax.jnlp.*;
import Classes.Packet.*;
import Classes.GUI.*;
import Classes.Storage.*;
import Classes.Support.*;
import Classes.parser;
import java.util.Timer;
import Classes.snifferImpl;


public class Standalone extends Classes.ZGUIImpl
{


   //**********************CONSTRUACTOR********************************//
	public Standalone( )
	{
		//initialize objects
		mainFrame = new JFrame();
		contentPanel=new JPanel();
		topPanel = new JPanel();
		tabbedPane = new JTabbedPane();
		// First tab //
		innerPane = new JTabbedPane();
		firstPanel = new JPanel();
		firstUpPanel = new JPanel();
		firstDnPanel1 = new JPanel();
		firstDnPanel2 = new JPanel();
		// Second tab //
		secondPanel = new JPanel(new BorderLayout());
		// Third tab //
		thirdPanel = new JPanel();
		thirdUpPanel = new JPanel();
		thirdDnPanel = new JPanel();
		// Forth tab //
		forthPanel = new JPanel();
		forthMedPanel = new JPanel();
		forthMedPanel1 = new JPanel();
		forthMedPanel2 = new JPanel();
		forthMed1 = new JPanel();
		forthMed2 = new JPanel();
		forth6lowpan = new JPanel();
		forthicmp = new JPanel();
		forthDnPanel = new JPanel();
		forthMed11 = new JPanel();
		forthMed12 = new JPanel();
		forth6lowpan1 = new JPanel();
		forth6lowpan2 = new JPanel();
		forth6lowpan3 = new JPanel();
		forthMed21 = new JPanel();
		forthMed22 = new JPanel();
		forthicmp1 = new JPanel();
		forthicmp2 = new JPanel();

                ZM.clientNTP=new SntpClient("hora.roa.es",60);

                Thread thread3= new Thread(new Runnable()
                {
                    public void run(){
                        TimerTask timerTask = new TimerTask()
                         {
                             public void run()
                             {
                                 ZM.clientNTP.askOffset();
                             }
                         };
                         Timer timer = new Timer();
                         // Each interval*1000 milliseconds execute run()
                         timer.scheduleAtFixedRate(timerTask, 0, (int)(ZM.clientNTP.getInterval())*1000);
                    }
                });
                thread3.start();

		// Tool Bar //
		toolBar = new JToolBar();
		listOfScrolls = new LinkedList<JScrollPane>();
		listOfTables = new LinkedList<JTable>();
		listOfTimeLine = new LinkedList<moteTimeLineImpl>();
		listOfListModel = new LinkedList<ListSelectionModel>();
		listOfArrivalTime = new LinkedList<timerImpl>();
		x =  new LinkedList<packetImpl>();
		timeLine = new JLabel();
		packetCounting = new JLabel();
		myout = new JLabel();
		myout2 = new JLabel();
		label6lowpan = new JLabel();
		output = new JTextArea(1, 10);
		output2 = new JTextArea(1, 10);
		info6lowpan = new JTextArea(1, 10);
		labelICMP = new JLabel();
		infoICMP = new JTextArea(1, 10);
		Hex = new JTextArea(1, 10);
		ImageButton = new JButton("+");//button to display packet info in packets anlysis tab
		ImageButton3 = new JButton("+");//button to display IEEE 802.15.4 info in packets anlysis tab
      Btn6lowpan = new JButton("+");//button to display 6lowpan info in packets anlysis tab
      BtnICMP = new JButton("+");//button to display ICMP header info in packets anlysis tab
      layout = new FlowLayout();
      Image icon = Toolkit.getDefaultToolkit().getImage("Classes/image/iconred.gif");
      mainFrame.setIconImage(icon);
      //create the GUI
      if(blockEvents3 == true)
      createAndShowGUI();
   }
        //*******************new button code*********************************//
        public void buttonNewCode()
	{
		try
		{
            try{ZM.sniffer.closeReader();}catch(Exception eee){}
            String [] args = new String[1];
            mainFrame.setVisible(false);
            mainFrame.dispose();
            ZM.StandaloneReset();
		}catch(Exception nn){}
	}
       //*********************pause Button Code****************************//
	public void buttonPauseCode()
	{
            blockEvents3 = false;
            blockEvents4 = false;
	    System.out.println("Num of packets: " + NumOfRecivedPacket);
            // only close the reader
            try {
                     ZM.sniffer.closeReader();
                } catch (Exception ex) {}
	}
	//*********************delete Button Code****************************//
	public void buttonDeleteCode()
	{
		try
		{
			try{ZM.sniffer.closeReader();}catch(Exception eee){}
			String [] args = new String[1];
			mainFrame.setVisible(false);
                        mainFrame.dispose();
			ZM.StandaloneReset();
		}catch(Exception nn){}
	}
	//******************************************************//

	public void insertInDatabase ()
	{
		int j=0;
		if (j == ZM.packetBuffer.getOrigonalPackets().size()){
			for ( ; ; ) {
				try {
					Thread.sleep(2000);
				} catch (Exception e){System.err.println("Error In thread sleeping");}
				if(blockEvents3 == false)
					return;
				if (j < ZM.packetBuffer.getOrigonalPackets().size())
					break;
				}
		}
		String receivedPacketStream = ZM.packetBuffer.getOrigonalPackets().get(j);
		if(ZM.connection.isConected())
		{
			//If Z-Server is enabled store the packet
			ZM.connection.insertData(receivedPacketStream, ZM.connection.getIdSnifer(),packetTimeStamp, ZM.connection.getTask());
		}
	}

        //*********************play Button Code****************************//
        public void playButtonCode()
        {
           new Thread(new Runnable(){
                  public void run(){
                        if(blockEvents3==false)
                        {
                           try
                           {
                           try{
				ZM.sniffer.closeReader();
			   }catch(Exception eee){System.err.println("Error on " + ": " + eee);}
                           String [] args = new String[1];
                           mainFrame.setVisible(false);
                           mainFrame.dispose();
                           ZM.StandaloneReset();
                           blockEvents3 = true;
                           }catch(Exception nn){System.err.println("Error on " + ": " + nn);}
                        }
                        // open the sniffer reader
                        else {
		                ZM.sniffer.openReader();
				source = ZM.sniffer.getSourcePort();

		                packet = new JTable(){public boolean isCellEditable(int row, int col) { return false;}};
		                packetViewAll =new JTable(){public boolean isCellEditable(int row, int col) { return false;}};
		                cord="";
		                // initializing lists with every new sniffing function
		                listOfScrolls = new LinkedList<JScrollPane>();
		                listOfTables = new LinkedList<JTable>();
		                listOfTimeLine = new LinkedList<moteTimeLineImpl>();
		                listOfListModel = new LinkedList<ListSelectionModel>();
		                listOfArrivalTime = new LinkedList<timerImpl>();
		                // initializing timers of time stamp
		                startTime = 0;
		                currentTime = 0;
		                prevTime = 0;
		                timeBetweenPackets = 0;
		                displayTime = 0;
		                times = 0;
		                startT = 0;
		                currentT = 0;
		                displayT = 0;
		                displaySecond = 0;
		                cal = Calendar.getInstance();
		                ArrivalTime = "";
		                slenth = "";
		                NumOfRecivedPacket=0;
		                NumOfDesplayedplPacket=0;
				Thread thread2 = new Thread(new Runnable(){
                              		public void run(){
                                          if (ZM.protocol.equals("IEEE 802.15.4")){
                                                IEEE802_15_4 parsedPacket = new IEEE802_15_4Impl();
                                                ParsedAndDesplay(parsedPacket);
                                          }
                                          else if (ZM.protocol.equals("6LoWPAN")){
                                                ICMPStruct parsedPacket = new ICMPStruct();
                                                ParsedAndDesplay(parsedPacket);
                                          }
		                }});
                      		// if conection with DB is enabled, store packet in database
                      		Thread databaseThread = new Thread (new Runnable(){
                              		public void run(){
                                    	insertInDatabase ();
		                }});
		                // begin infinite loop of sniffing
		                for(int g=0,f=0;;g+=75,f++)
		                {
		                   if(blockEvents3 == false)
		                       return ;
		                   // get the sniffed packet and put the packet and its arrival time into the buffer
		                   final String receivedPacketStream;
		                   receivedPacketStream = ZM.sniffer.getPacket();
		                   packetTimeStamp=((System.currentTimeMillis()/1000.0) + 2208988800.0)+ZM.clientNTP.getOffset();
                                    ZM.packetBuffer.AddOrigonalPackets(receivedPacketStream);
                                    ZM.packetBuffer.AddOrigonalTimes(packetTimeStamp);
		                   NumOfRecivedPacket++;

		                   lenthc = ZM.sniffer.packetLength;

                        if (g==0){
                              thread2.start();
                              databaseThread.start();
                        }
            			}
			}
		   }
		}).start();
        }

       //***********************change port code****************************//
	public void changePortCode()
	{
		try
		{
			ZM.sniffer.closeReader();
		}catch(Exception eee){}
		Image icon = Toolkit.getDefaultToolkit().getImage("Classes/image/iconred.gif");
		portframe.setIconImage(icon);
		JPanel mainpp=new JPanel();
		final JLabel portLabel = new JLabel("Enter the COM port number:       ");
		final JLabel osLabel = new JLabel("Select your OS type:    ");
		final JLabel moteLabel = new JLabel("Select your mote type:    ");
		final JLabel protocolLabel = new JLabel("Select the Network Layer protocol you want to decode:");
		JButton savebutton=new JButton("Save");
		JButton cancelbutton=new JButton("Cancel");
		final JTextField porttextField = new JTextField(20);//textfield to write the port
		mainpp.setLayout(new BorderLayout());
		JRadioButton windowsButton   = new JRadioButton(" Windows                           "  , true);
		JRadioButton linuxButton    = new JRadioButton(" Linux"   , false);
		os="windows";
		JRadioButton telosbButton   = new JRadioButton(" Telosb                    "  , true);
		JRadioButton micazButton    = new JRadioButton(" MICAz"   , false);
		plateform="telosb";
		JRadioButton sixlowpanButton   = new JRadioButton(" 6LoWPAN                           "  , true);
		JRadioButton zigbeeButton    = new JRadioButton(" Zigbee"   , false);
		ButtonGroup bgroup = new ButtonGroup();
		ButtonGroup bgroup1 = new ButtonGroup();
		ButtonGroup bgroup2 = new ButtonGroup();
		bgroup.add(windowsButton);
		bgroup.add(linuxButton);
		bgroup1.add(telosbButton);
		bgroup1.add(micazButton);
		bgroup2.add(sixlowpanButton);
		bgroup2.add(zigbeeButton);
		JPanel ospanel = new JPanel();//operating system panel
		JPanel portpanel=new JPanel();//panel that contain the port
		JPanel motetype=new JPanel();//panel that contain the mote type
		JPanel panel1=new JPanel();
		JPanel panelprotocol=new JPanel();//panel to select the protocol
		JPanel panelbuttons=new JPanel();
		ospanel.setLayout(new GridLayout(3, 1));
		portpanel.setLayout(new GridLayout(3, 1));
		motetype.setLayout(new GridLayout(3, 1));
		panel1.setLayout(new GridLayout(3, 1));
		panelprotocol.setLayout(new GridLayout(3,1));
		panelbuttons.setLayout(new FlowLayout());
		ospanel.add(osLabel);
		ospanel.add(windowsButton);
		ospanel.add(linuxButton);
		portpanel.add(portLabel);
		portpanel.add(porttextField);
		motetype.add(moteLabel);
		motetype.add(telosbButton);
		motetype.add(micazButton);
		panelbuttons.add(savebutton);
		panelbuttons.add(cancelbutton);
		panelprotocol.add(protocolLabel);
		panelprotocol.add(sixlowpanButton);
		panelprotocol.add(zigbeeButton);
		ospanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Operating System"));
		portpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Port"));
		motetype.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "plateform"));
		panelprotocol.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Network Layer Protocol"));
		portframe.setResizable(false);
		panel1.add(portpanel,BorderLayout.NORTH);
		panel1.add(motetype, BorderLayout.CENTER);
		panel1.add(panelprotocol, BorderLayout.SOUTH);
		mainpp.add(ospanel,BorderLayout.NORTH);
		mainpp.add(panel1,BorderLayout.CENTER);
		mainpp.add(panelbuttons,BorderLayout.SOUTH);
		portframe.getContentPane().add(mainpp);
		portframe.setLocation(250,150);
		portframe.pack();
		mainpp.setVisible(true);
		portframe.setVisible(true);
		//-------------windows radio button---------------//
		windowsButton.addActionListener(new ActionListener()
      		{
      			public void actionPerformed(ActionEvent e)
      			{
				os="windows";
				portLabel.setText("Enter the number of COM:");
			}
      		});
		//--------------linux radio button----------------//
		linuxButton.addActionListener(new ActionListener()
      		{
      			public void actionPerformed(ActionEvent e)
      			{
				os="linux";
				portLabel.setText("Enter the number of tty (e.q:ttyUSB1)   ");
			}
      		});
		//--------------telosb radio button----------------//
		telosbButton.addActionListener(new ActionListener()
      		{
      			public void actionPerformed(ActionEvent e)
      			{
				plateform="telosb";
			}
      		});
		//--------------micaz radio button----------------//
		micazButton.addActionListener(new ActionListener()
      		{
      			public void actionPerformed(ActionEvent e)
      			{
				plateform="micaz";
			}
      		});
		//--------------6lowPAN radio button----------------//
		sixlowpanButton.addActionListener(new ActionListener()
      		{
      			public void actionPerformed(ActionEvent e)
      			{
				ZM.protocol="6LoWPAN";
			}
      		});
		//--------------Zigbee radio button----------------//
		zigbeeButton.addActionListener(new ActionListener()
      		{
      			public void actionPerformed(ActionEvent e)
      			{
				ZM.protocol="Zigbee";
			}
      		});
		//-----------------cancel button------------------//
		cancelbutton.addActionListener(new ActionListener()
      		{
      			public void actionPerformed(ActionEvent e)
      			{
				portframe.setVisible(false);
				portframe.dispose();
      			}
      		});
		//-----------------save button------------------//
		savebutton.addActionListener(new ActionListener()
      		{
      			public void actionPerformed(ActionEvent e)
      			{
				if(os.equalsIgnoreCase("windows"))
					ZM.sniffer = new snifferImpl("serial@COM"+porttextField.getText()+":"+plateform);
				if(os.equalsIgnoreCase("linux"))
					ZM.sniffer = new snifferImpl("serial@/dev/"+porttextField.getText()+":"+plateform);
				try
				{
					ZM.sniffer.openReader();
				}catch(Exception eeee){}
				portframe.dispose();
      			}
      		});
	}

        //***********************change NTP configuration panel code****************************//
        public void changeNTPConfig()
	{

		Image icon = Toolkit.getDefaultToolkit().getImage("Classes/image/iconred.gif");
		ntpframe.setIconImage(icon);
		JPanel mainpp=new JPanel();
		final JLabel serverLabel = new JLabel("Enter the servername or IP:");
		final JLabel intervalLabel = new JLabel("Enter the request interval:");
		JButton savebutton=new JButton("Save");
		JButton cancelbutton=new JButton("Cancel");
		final JTextField servertextField = new JTextField(40);
		final JTextField intervaltextField = new JTextField(8);
                final JPanel serverpanel=new JPanel();
                servertextField.setText(ZM.clientNTP.getServer());
                mainpp.setLayout(new BorderLayout());
                JPanel panel1=new JPanel();
		JPanel panelbuttons=new JPanel();
		serverpanel.setLayout(new GridLayout(6, 1));
                panel1.setLayout(new GridLayout(3, 1));
		panelbuttons.setLayout(new FlowLayout());
		serverpanel.add(serverLabel);
		serverpanel.add(servertextField);
                serverpanel.add(intervalLabel);
		serverpanel.add(intervaltextField);
                panelbuttons.add(savebutton);
		panelbuttons.add(cancelbutton);
		serverpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "NTP configuration"));
		ntpframe.setResizable(false);
                panel1.add(serverpanel);
		mainpp.add(panel1,BorderLayout.CENTER);
		mainpp.add(panelbuttons,BorderLayout.SOUTH);
		ntpframe.getContentPane().add(mainpp);
		ntpframe.setLocation(250,150);
		ntpframe.pack();
		mainpp.setVisible(true);
		ntpframe.setVisible(true);
		//-----------------cancel button------------------//
		cancelbutton.addActionListener(new ActionListener()
      		{
      			public void actionPerformed(ActionEvent e)
      			{
				ntpframe.setVisible(false);
				ntpframe.dispose();
      			}
      		});
		//-----------------save button------------------//
		savebutton.addActionListener(new ActionListener()
      		{
      			public void actionPerformed(ActionEvent e)
      			{
                            //Save parameters
                            ZM.clientNTP.setServer(servertextField.getText());
                            ntpframe.dispose();
      			}
      		});


	}

        //***********************change Z-Server configuration panel code****************************//
        public void changeConfigZCode()
	{

		Image icon = Toolkit.getDefaultToolkit().getImage("Classes/image/iconred.gif");
		zserverframe.setIconImage(icon);
		JPanel mainpp=new JPanel();
		final JLabel serverLabel = new JLabel("Enter the servername or IP:");
		final JLabel portLabel = new JLabel("Enter the port:");
		final JLabel databaseLabel = new JLabel("Enter the database:");
		final JLabel userLabel = new JLabel("Enter the user:");
		final JLabel passLabel = new JLabel("Enter the pass:");
		JButton savebutton=new JButton("Save");
		JButton cancelbutton=new JButton("Cancel");
		final JTextField servertextField = new JTextField(40);
		final JTextField porttextField = new JTextField(8);
		final JTextField databasetextField = new JTextField(20);
		final JTextField usertextField = new JTextField(15);
		final JTextField passtextField = new JTextField(15);
                final JPanel serverpanel=new JPanel();
                final JPanel restpanel=new JPanel();
                final JLabel snifferidLabel = new JLabel("Enter the sniffer_id:");
		final JLabel taskLabel = new JLabel("If the task exists, insert the id, else a new one will be create:");
		final JTextField snifferidtextField = new JTextField(5);
		final JTextField tasktextField = new JTextField(5);
		final JLabel userrLabel = new JLabel("Enter your user for Z-Server:");
		final JLabel passwLabel = new JLabel("Enter your pass for Z-Server:");
		final JTextField userrtextField = new JTextField(10);
		final JTextField passwtextField = new JTextField(10);

                // DEBUG PURPOSE
                servertextField.setText("localhost");
		porttextField.setText("3306");
		databasetextField.setText("prueba");
		usertextField.setText("root");
		passtextField.setText("a");
                userrtextField.setText("fernando");
                passwtextField.setText("prueba");
                snifferidtextField.setText("1");
                tasktextField.setText("2");

                JPanel enableZ=new JPanel();
                JPanel panel1=new JPanel();
		JPanel panelbuttons=new JPanel();
                final JCheckBox ch1= new JCheckBox();
                mainpp.setLayout(new BorderLayout());
                enableZ.setLayout(new FlowLayout());
		serverpanel.setLayout(new GridLayout(6, 1));
		restpanel.setLayout(new GridLayout(5, 1));
                panel1.setLayout(new GridLayout(3, 1));
		panelbuttons.setLayout(new FlowLayout());
		serverpanel.add(serverLabel);
		serverpanel.add(servertextField);
                serverpanel.add(portLabel);
		serverpanel.add(porttextField);
                serverpanel.add(databaseLabel);
		serverpanel.add(databasetextField);
                serverpanel.add(userLabel);
		serverpanel.add(usertextField);
                serverpanel.add(passLabel);
		serverpanel.add(passtextField);
                serverpanel.setVisible(false);
		restpanel.add(userrLabel);
                restpanel.add(userrtextField);
                restpanel.add(passwLabel);
                restpanel.add(passwtextField);
                restpanel.add(snifferidLabel);
                restpanel.add(snifferidtextField);
                restpanel.add(taskLabel);
                restpanel.add(tasktextField);
                restpanel.setVisible(false);
		ch1.setText("Enabled");
                enableZ.add(ch1);
                panelbuttons.add(savebutton);
		panelbuttons.add(cancelbutton);
		serverpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "DataBase connection parameters"));
		enableZ.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Enable Z-Server interaction"));
		restpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Task parameters"));
		zserverframe.setResizable(false);
                panel1.add(enableZ);
		panel1.add(serverpanel);
		panel1.add(restpanel);
                mainpp.add(panel1,BorderLayout.CENTER);
		mainpp.add(panelbuttons,BorderLayout.SOUTH);
		zserverframe.getContentPane().add(mainpp);
		zserverframe.setLocation(250,150);
		zserverframe.pack();
		mainpp.setVisible(true);
		zserverframe.setVisible(true);
		//-----------------cancel button------------------//
		cancelbutton.addActionListener(new ActionListener()
      		{
      			public void actionPerformed(ActionEvent e)
      			{
				zserverframe.setVisible(false);
				zserverframe.dispose();
      			}
      		});
		//-----------------save button------------------//
		savebutton.addActionListener(new ActionListener()
      		{
      			public void actionPerformed(ActionEvent e)
      			{
                            //Save parameters, check user/pass and if neccesary create a task
                            if(ch1.isSelected())
                            {

                                if (servertextField.getText().equals("") || porttextField.getText().equals("") || databasetextField.getText().equals("") || usertextField.getText().equals("") || passtextField.getText().equals(""))
                                {
                                    JOptionPane.showMessageDialog(zserverframe,"Conection fields are required");
                                    return;
                                }

                                ZM.connection=new databaseStorage(servertextField.getText(),porttextField.getText(),databasetextField.getText(),usertextField.getText(),passtextField.getText());
                                ZM.connection.connect();

                                if(!ZM.connection.isConected())
                                {
                                    JOptionPane.showMessageDialog(zserverframe,"Can't connect to database");
                                    return;
                                }
                                if(userrtextField.getText().equals("") || passwtextField.getText().equals(""))
                                {
                                    JOptionPane.showMessageDialog(zserverframe,"Particular user/pass fields are required");
                                    return;
                                }

                                if(!ZM.connection.userExist(userrtextField.getText(),passwtextField.getText()))
                                {
                                    JOptionPane.showMessageDialog(zserverframe,"User/pass is wrong");
                                    return;
                                }

                                if(snifferidtextField.getText().equals(""))
                                {
                                    Random r=new Random();
                                    r.setSeed(System.currentTimeMillis());
                                    int aux=r.nextInt(1000);
                                    ZM.connection.setIdSnifer(String.valueOf(aux));
                                    JOptionPane.showMessageDialog(zserverframe,"Id sniffer: "+aux);

                                }
                                else
                                {
                                    ZM.connection.setIdSnifer(snifferidtextField.getText());
                                }

                                if(tasktextField.getText().equals(""))
                                {

                                    int id=ZM.connection.createTask(userrtextField.getText());
                                    ZM.connection.setTask(String.valueOf(id));
                                    JOptionPane.showMessageDialog(zserverframe,"Task created with id: "+String.valueOf(id));
                                }
                                else if (!ZM.connection.existTask(Integer.valueOf(tasktextField.getText())) || !ZM.connection.ownerTask(Integer.valueOf(tasktextField.getText()),userrtextField.getText(),passwtextField.getText()))
                                {
                                    if(!ZM.connection.existTask(Integer.valueOf(tasktextField.getText())))
                                        JOptionPane.showMessageDialog(zserverframe,"The task with id "+tasktextField.getText()+" doesn't exist.");
                                    else
                                        JOptionPane.showMessageDialog(zserverframe,"The task with id "+tasktextField.getText()+" doesn't allow to the user "+userrtextField.getText()+".");

                                        return;

                                }
                                else ZM.connection.setTask(tasktextField.getText());

                            }
                            zserverframe.dispose();
      			}
      		});

                ch1.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent evt)
                    {
                        //Checkbox selected event
                        if(ch1.isSelected())
                        {
                            serverpanel.setVisible(true);
                            restpanel.setVisible(true);
                        }
                        else
                        {
                            serverpanel.setVisible(false);
                            restpanel.setVisible(false);
                            if(ZM.connection.isConected())
                                ZM.connection.disconnect();
                        }
                    }
                });
	}
        public void buttonOpenCode()
        {
            new Thread(new Runnable()
            {
                  public void run()
                  {
                        if(blockEvents3==false)
                        {
                           try
                           {
                               try{
                                    ZM.sniffer.closeReader();
                                    }catch(Exception eee){System.err.println("Error on " + ": " + eee);}
                               String [] args = new String[1];
                               mainFrame.setVisible(false);
                               mainFrame.dispose();
                               ZM.StandaloneReset();
                               blockEvents3 = true;
                           }catch(Exception nn){System.err.println("Error on " + ": " + nn);}
                        }
                        JFileChooser fd = new JFileChooser();
                        int returnVal = fd.showOpenDialog(null);
                        if(returnVal == JFileChooser.APPROVE_OPTION)
                        {
                        File file = fd.getSelectedFile();
                        packet = new JTable(){public boolean isCellEditable(int row, int col) { return false;}};
                        packetViewAll =new JTable(){public boolean isCellEditable(int row, int col) { return false;}};
                        cord="";
                        // initializing lists with every new sniffing function
                        listOfScrolls = new LinkedList<JScrollPane>();
                        listOfTables = new LinkedList<JTable>();
                        listOfTimeLine = new LinkedList<moteTimeLineImpl>();
                        listOfListModel = new LinkedList<ListSelectionModel>();
                        listOfArrivalTime = new LinkedList<timerImpl>();
                        // initializing timers of time stamp
                        startTime = 0;
                        currentTime = 0;
                        prevTime = 0;
                        timeBetweenPackets = 0;
                        displayTime = 0;
                        times = 0;
                        startT = 0;
                        currentT = 0;
                        displayT = 0;
                        displaySecond = 0;
                        cal = Calendar.getInstance();
                        ArrivalTime = "";
                        slenth = "";
                        NumOfRecivedPacket=0;
                        NumOfDesplayedplPacket=0;
                        String tempString="";
                        String time="";
                                try
                                {
                                    BufferedReader input = new BufferedReader(new FileReader(file));
                                    try {
                                        String line = null;
                                        line = input.readLine();
                                        input.skip(10);
                                        line = input.readLine();
                                        ZM.protocol=line;
                                        while ((line = input.readLine()) != null)
                                           {
                                            if(  input.read()=='#')
                                            {
                                                int index1 = line.indexOf(':');
                                                int index=line.indexOf('w');
                                                final String receivedPacketStream ;
                                                receivedPacketStream=line.substring(index1+1,index-2);
                                                tempString=line.substring(index,line.length());
                                                time= tempString.substring(14,tempString.length());
                                                packetTimeStamp=NtpMessage.timestampToDouble(time);
                                                ZM.packetBuffer.AddOrigonalPackets(receivedPacketStream);
                                                ZM.packetBuffer.AddOrigonalTimes(packetTimeStamp);
                                                NumOfRecivedPacket++;
                                                lenthc =new ConvertImpl().convertHexToBin(receivedPacketStream).length();
                                             }
                                             }
                                        blockEvents3=false;
                                        if (ZM.protocol.equals("IEEE 802.15.4")){
                                                IEEE802_15_4 parsedPacket = new IEEE802_15_4Impl();
                                                ParsedAndDesplay(parsedPacket);
                                        }
                                        else if (ZM.protocol.equals("6LoWPAN")){
                                                ICMPStruct parsedPacket = new ICMPStruct();
                                                ParsedAndDesplay(parsedPacket);
                                        }
                                    } finally {
                                    input.close();
                                    }
                                } catch (IOException ex) {ex.printStackTrace();}
                              }
		   }
            }).start();
 }


        //************************createAndShowGUI***************************//
	// This method specifies the component of GUI and displays them
        public void createAndShowGUI()
	{
		if(blockEvents3 == false)
			return ;
        	contentPanel.add(toolBar);
		mainFrame.setContentPane(contentPanel);
		mainFrame.setBounds(0, 0, width, height-30);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		mainFrame.setTitle("Z-Monitor");
		packetCounting.setBounds(20,height-100,120,20);
		packetCounting.setText("Packet Count:");
		toolBar.setBounds(0,0,width,30);
		contentPanel.setOpaque(true);
		contentPanel.setBounds(0, 0, width-10, height-60);
		contentPanel.add( topPanel );
		contentPanel.setLayout( null );
		contentPanel.add(packetCounting);
		topPanel.setBounds(10, 30, width-20, height-130);
		topPanel.setLayout( null );
		// Add the tabs names //
		tabbedPane.addTab( "Packet Sniffer", firstPanel );
		tabbedPane.addTab( "Topology", secondPanel );
		tabbedPane.addTab( "View All", thirdPanel );
		tabbedPane.addTab( "Packet Analysis", forthPanel );
		tabbedPane.setBounds(0, 0, width-30, height-140);
		// Create first tab  //
		firstPanel.setLayout(null);
		firstPanel.setBounds(0, 0, width-30, height-150);
		innerPane.addTab( "Time Line", firstDnPanel2 );
		innerPane.setBounds(10, height/2, width-50, height/4+30);
		firstPanel.add(innerPane);
		firstUpPanel.setLayout(null);
		firstUpPanel.setBounds(10, 0, width+300, height/2-20);
        	firstUpPanel.setBackground(Color.WHITE);
		firstDnPanel1.setLayout(null);
		firstDnPanel1.setBounds(10, 0, width-60, height/4+30);
		firstDnPanel2.setLayout(null);
		firstDnPanel2.setBounds(10, 0, width-60, height/4+30);
		firstDnPanel2.setBackground(Color.WHITE);
		timeLine.setBounds(0, 0, width-60, height/4+40);
		timeLineScroll = new JScrollPane(timeLine,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
		timeLine.setPreferredSize(new Dimension(8000, height/4+40));
		timeLineScroll.setBounds(0, 0, width-60, height/4);
		firstDnPanel2.add(timeLineScroll);
		scrollPane = new JScrollPane(firstUpPanel);
		///////////////////////////
		scrollPane.setBounds(10, 10, width-50, height/2-20);
		// Create second tab  //
		secondPanel.setLayout(new BorderLayout());
		secondPanel.setBounds(10, 10, width-30, height-150);
		secondPanel.setBackground(Color.WHITE);
		// Create third tab  //
		thirdPanel.setLayout(null);
		thirdPanel.setBounds(10, 0, width-30, height-120);
		thirdUpPanel.setLayout(null);
		thirdUpPanel.setBounds(10, 10, width+300, height/2-140);
		thirdUpPanel.setBackground(Color.WHITE);
		thirdDnPanel.setLayout(new BorderLayout());
		thirdDnPanel.setBounds(0, height/2-140, width-30, height/2+50);
		thirdDnPanel.setBackground(Color.WHITE);
		thirdPanel.add(thirdDnPanel);
		topPanel.add(tabbedPane);
		thirdPanelUpscrollPane = new JScrollPane(thirdUpPanel);//
		thirdPanelUpscrollPane.setBounds(0, 0, width-30, height/2-140);
		// Create forth tab  //
		forthPanel.setLayout(new GridLayout(3,1,5,5));
        	forthMedPanel.setBackground(Color.WHITE);
		forthMedPanel1.setBackground(Color.WHITE);
		forthMedPanel2.setBackground(Color.WHITE);
		layout.setAlignment( FlowLayout.LEFT );
		forthMed11.setLayout( layout );
		forthMed11.setBackground(Color.WHITE);
		forthMed12.setLayout( layout );
		forthMed12.setBackground(Color.WHITE);
		forthMed21.setLayout( layout );
		forthMed21.setBackground(Color.WHITE);
		forthMed22.setLayout( layout );
		forthMed22.setBackground(Color.WHITE);
		forth6lowpan1.setLayout( layout );
		forth6lowpan1.setBackground(Color.WHITE);
		forth6lowpan2.setLayout( layout );
		forth6lowpan2.setBackground(Color.WHITE);
		forth6lowpan3.setLayout( layout );
		forth6lowpan3.setBackground(Color.WHITE);
		forthicmp1.setLayout( layout );
		forthicmp1.setBackground(Color.WHITE);
		forthicmp2.setLayout( layout );
		forthicmp2.setBackground(Color.WHITE);
		forthMed1.setBackground(Color.WHITE);

		forthMed2.setBackground(Color.WHITE);
		forth6lowpan.setBackground(Color.WHITE);
		forthicmp.setLayout( new BorderLayout());
		forthicmp.setBackground(Color.WHITE);
		forthMed1.setLayout(new BorderLayout());
		forthMed1.add(forthMed11,BorderLayout.NORTH);
		forthMed1.add(forthMed12,BorderLayout.CENTER);
		forthMed2.setLayout(new BorderLayout());
		forthMed2.add(forthMed21,BorderLayout.NORTH);
		forthMed2.add(forthMed22,BorderLayout.CENTER);
		forth6lowpan.setLayout(new BorderLayout());
		forth6lowpan.add(forth6lowpan1,BorderLayout.NORTH);
		forth6lowpan.add(forth6lowpan2,BorderLayout.SOUTH);
		forth6lowpan2.setLayout(new BorderLayout());
		forth6lowpan2.add(forth6lowpan3,BorderLayout.NORTH);
		forthicmp.setLayout(new BorderLayout());
		forthicmp.add(forthicmp1,BorderLayout.NORTH);
		forthicmp.add(forthicmp2,BorderLayout.CENTER);
		forthMedPanel1.setLayout(new BorderLayout());
		forthMedPanel1.add(forthMed1,BorderLayout.NORTH);
		forthMedPanel1.add(forthMed2,BorderLayout.CENTER);
		forthMedPanel2.setLayout(new BorderLayout());
		forthMedPanel2.add(forth6lowpan,BorderLayout.NORTH);
		forthMedPanel2.add(forthicmp,BorderLayout.CENTER);
		forthMedPanel.setLayout(new BorderLayout());
		forthMedPanel.add(forthMedPanel1, BorderLayout.NORTH);
		forthMedPanel.add(forthMedPanel2, BorderLayout.CENTER);
		forthMedPanel.setAutoscrolls(true);
		forthscroll = new JScrollPane(forthMedPanel) ;
		output.setVisible( false );
		output2.setVisible( false );
		info6lowpan.setVisible( false );
		BtnICMP.setVisible( false );
		labelICMP.setVisible( false );
		infoICMP.setVisible( false );
		forthDnPanel.setLayout(new BorderLayout());
        	//Create next tab//
	  	String [] cols3 ={"<html><b><font face=tahoma size=3>Numeric Type</html>",
		"<html><b><font face=tahoma size=3>Link Quality Indication(LQI)</html>",
		"<html><b><font face=tahoma size=3>Cyclic redundancy check (CRC)</html>",
		"<html><b><font face=tahoma size=3>Channel</html>"};
		Object [][] row3 ={{"","","",""},{"","","",""}};
	  	OtherControl = new JTable(row3,cols3){public boolean isCellEditable(int row, int col) { return false;}};
		TableColumn col2 = OtherControl.getColumnModel().getColumn(2);
    		col2.setPreferredWidth(140);
		OtherControl.setFont(new Font("Courier New", Font.BOLD, 14));
		JTableHeader header2 = OtherControl.getTableHeader();
	    	final TableCellRenderer headerRenderer2 = header2.getDefaultRenderer();
	    	header2.setDefaultRenderer( new TableCellRenderer()
		{
		public Component getTableCellRendererComponent( JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column )
		{
			Component comp = headerRenderer2.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, column );
			comp.setBackground(new Color(128,128,255));
			return comp;
		 }
	  	});
		String [] cols4 = {"<html><b><font face=tahoma size=3>No</html>",
		"<html><b><font face=tahoma size=3>Time(sec)</html>",
		"<html><b><font face=tahoma size=3>Source</html>",
		"<html><b><font face=tahoma size=3>Destination</html>",
		"<html><b><font face=tahoma size=3>Link Layer Info</html>",
		"<html><b><font face=tahoma size=3>Network Layer Protocol</html>",
		"<html><b><font face=tahoma size=3>ICMP Message Type</html>"};
		Object [][] row4 ={{"1","2","3","4","2","3","4"}};
		tableModel = new DefaultTableModel ();
		Heder4 = new JTable(tableModel){public boolean isCellEditable(int row, int col) { return false;}};
		Heder4.setFont(new Font("Courier New",10,15));
		Heder4.setGridColor(new Color(209, 209, 209));
		tableModel.setDataVector(row4,cols4);
		tableModel.removeRow(0);
		forthscrollPane = new JScrollPane(Heder4);
		forthPanel.add(forthscrollPane);
		///////////////////////
		// Create a buttons //
                ////////////////////////
                //########################## Toolbar Buttons ########################################//
                ClassLoader cldr = this.getClass().getClassLoader();
                ImageIcon newImage = new ImageIcon(cldr.getResource("Classes/image/Newd.png"));
        	JButton newButton = new JButton(newImage);
       		newButton.setToolTipText("New Ctrl+N");
       		ImageIcon playImage = new ImageIcon(cldr.getResource("Classes/image/Play.png"));
       		JButton playButton = new JButton(playImage);
       		playButton.setToolTipText("Start Sniffng Ctrl+S");
       		ImageIcon pauseImage = new ImageIcon(cldr.getResource("Classes/image/Pause.png"));
       		JButton pauseButton = new JButton(pauseImage);
        	pauseButton.setToolTipText("Pause Ctrl+P");
       		ImageIcon deleteImage = new ImageIcon(cldr.getResource("Classes/image/Delete.png"));
       		JButton deleteButton = new JButton(deleteImage);
    	   	deleteButton.setToolTipText("Delete Packets DEL");
        	ImageIcon exitImage = new ImageIcon(cldr.getResource("Classes/image/Exit.png"));
        	JButton exitButton = new JButton(exitImage);
        	exitButton.setToolTipText("Exit Z-Monitor Alt+F4");
       		ImageIcon portImage = new ImageIcon(cldr.getResource("Classes/image/Port.png"));
        	JButton portButton = new JButton(portImage);
        	portButton.setToolTipText("Change Port F4");
        	ImageIcon saveImage = new ImageIcon(cldr.getResource("Classes/image/Save.png"));
        	JButton saveButton = new JButton(saveImage);
        	saveButton.setToolTipText("Save Packets Ctrl+A");
                ////////////////////////
      		toolBar.setRollover(true);
        	toolBar.setFloatable(false);
       	 	toolBar.add(newButton);
        	toolBar.addSeparator();
        	toolBar.add(playButton);
        	toolBar.add(pauseButton);
        	toolBar.addSeparator();
        	toolBar.add(saveButton);
        	toolBar.add(deleteButton);
        	toolBar.addSeparator();
        	toolBar.add(portButton);
        	toolBar.add(exitButton);
                //////////////////////////
                // Start buttons action //
                //////////////////////////
                //#################################### Toolbar buttons Action Listener #################################//
                // ******************* new Button *******************/
                newButton.addActionListener(new ActionListener()
                {
			public void actionPerformed(ActionEvent e)
			{
				buttonNewCode();
			}
                });

                //***************** Play Button ************************//
        	playButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
                            playButtonCode();
                        }
                });
                // **************** pause button ********************
                pauseButton.addActionListener(new ActionListener()
                {
                                public void actionPerformed(ActionEvent e)
                                {
                                        buttonPauseCode();
                                }
                });
                // *************** delete button ********************
                deleteButton.addActionListener(new ActionListener()
                {
                                public void actionPerformed(ActionEvent e)
                                {
                                        buttonDeleteCode();
                                }
                });
                //*********** exit button ************************
                exitButton.addActionListener(new ActionListener()
                {
                                public void actionPerformed(ActionEvent e)
                                {
                                        System.exit(0);
                                }
                });
                //**********change port of mote attached to computer button *********
                portButton.addActionListener(new ActionListener()
                {
                                public void actionPerformed(ActionEvent e)
                                {
                                        changePortCode();
                                }
                });
                // *****************save the packets button***************************
                saveButton.addActionListener(new ActionListener()
                {
                                public void actionPerformed(ActionEvent e)
                                {
                                    saveCode1();
                                }
                });
                //////////////////////////
                //  Start Menu action   //
                //////////////////////////
                // ########################## Menu Bar #################################

                /**************************************************************
                 ********************** File Menu *****************************
                 *************************************************************/
                FileMenu = new JMenu("File");
                JMenuItem New = new JMenuItem("New");
                New.setAccelerator(KeyStroke.getKeyStroke("control N"));
                New.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent e)
                        {
                                buttonNewCode();
                        }
                }
                );
                FileMenu.add(New);
                FileMenu.add(new JSeparator());
                /******** Open Item In File Menu ****************/
                /////////////////////////////////////////////////
                JMenuItem Open = new JMenuItem("Open");
                Open.setAccelerator(KeyStroke.getKeyStroke("control O"));
                Open.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent e)
                        {
                                try
                                {
                                      buttonOpenCode();
                                }catch(Exception nn){}
                        }
                });
                FileMenu.add(Open);
                FileMenu.add(new JSeparator());
                //////////////////////////////////////////////////
                /******** Exit Item In File Menu ****************/
                JMenuItem Exit = new JMenuItem("Exit");
                Exit.setAccelerator(KeyStroke.getKeyStroke("alt F4"));
                Exit.addActionListener(new ActionListener() {
                        public void actionPerformed (ActionEvent e) {
                                System.exit(0);
                                }
                });
                // ********************** End File Menu ***************************** //
                /**************************************************************
		 ********************** Edit Menu *****************************
		 *************************************************************/
                EditMenu = new JMenu("Edit");
                JMenu Save = new JMenu("Save As");

        FileMenu.add(Save);
        JMenuItem Text = new JMenuItem("Text File");
        JMenuItem XML = new JMenuItem("XML File");
        Save.add(Text);
        Save.add(XML);

        Text.addActionListener(new ActionListener()
        {
                public void actionPerformed(ActionEvent e)
                {
                    saveCode1();
                }
        });

        XML.addActionListener(new ActionListener()
        {
                public void actionPerformed(ActionEvent e)
                {
                    saveCode2();
                }
        });

      	FileMenu.add(new JSeparator());
      	FileMenu.add(Exit);
      	//********* Clear menu item ****************
      	JMenuItem Clear = new JMenuItem("Clear");
      	Clear.setAccelerator(KeyStroke.getKeyStroke("DELETE"));
      	Clear.addActionListener(new ActionListener()
      	{
      		public void actionPerformed(ActionEvent e)
      		{
			buttonDeleteCode();
      		}
      	});
      	EditMenu.add(Clear);
      	// ********************** End Edit Menu ***************************** //

        /**************************************************************
         ********************** Port Menu **************************
         *************************************************************/
      	PortMenu = new JMenu("Port");
      	JMenuItem change = new JMenuItem("Change Port");
      	change.setAccelerator(KeyStroke.getKeyStroke("F4"));
      	change.addActionListener(new ActionListener(){
		public void actionPerformed (ActionEvent e)
		{
			changePortCode();
		}
      	});
      	PortMenu.add(change);
      	// ********************** End Port Menu ************************** //

         //*********************** Protocol Menu *************************//
         ProtocolMenu = new JMenu("Protocol");
         JMenuItem IEEE802_15_4_Protocol = new JMenuItem("IEEE 802.15.4 Only");
         IEEE802_15_4_Protocol.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e)
            {
                  ZM.protocol = "IEEE 802.15.4";
            }
      	});

         JMenuItem sixLoWPAN_Protocol = new JMenuItem("6LoWPAN");
         sixLoWPAN_Protocol.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e)
            {
                  ZM.protocol = "6LoWPAN";
            }
      	});
         ProtocolMenu.add(IEEE802_15_4_Protocol);
         ProtocolMenu.add(sixLoWPAN_Protocol);
         // ********************* End Protocol Menu *********************** //

        /**************************************************************
         ********************** Capture Menu **************************
         *************************************************************/
        CaptureMenu = new JMenu("Capture");
        JMenuItem Sniff = new JMenuItem("Start");
        Sniff.setAccelerator(KeyStroke.getKeyStroke("control S"));
        JMenuItem Pause = new JMenuItem("Pause");
        Pause.setAccelerator(KeyStroke.getKeyStroke("control P"));

        Sniff.addActionListener(new ActionListener()
                {
                        public void actionPerformed (ActionEvent e)
                                {
                                    playButtonCode();
                                }
                });
        Pause.addActionListener(new ActionListener()
                {
                        public void actionPerformed (ActionEvent e)
                                {
                                        buttonPauseCode();
                                }
                }
                );
        CaptureMenu.add(Sniff);
        CaptureMenu.add(new JSeparator());
        CaptureMenu.add(Pause);
        // ********************** End Capture Menu ************************** //

        /**************************************************************
         ********************** Z-Server Menu **************************
         *************************************************************/
        ZServerMenu = new JMenu("Z-Server");
      	JMenuItem changez = new JMenuItem("Configure DataBase conection");
      	JMenuItem ntp = new JMenuItem("NTP configuration");
        changez.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e)
            {
                    changeConfigZCode();
            }
      	});

        ntp.addActionListener(new ActionListener()
        {
            public void actionPerformed (ActionEvent e)
            {
                    changeNTPConfig();
            }
        }
        );
        ZServerMenu.setForeground(new Color(225, 225, 225));
        ZServerMenu.setEnabled(false);
        //ZServerMenu.add(changez);
        //ZServerMenu.add(new JSeparator());
        //ZServerMenu.add(ntp);

        // ********************** End Z-Server Menu ************************** //

         /*******************************************************************************
         ********************** Statistics Menu *****************************************
         *******************************************************************************/
        StatisticsMenu = new JMenu("Statistics");
        JMenuItem Summary = new JMenuItem("Summary");
        Summary.setAccelerator(KeyStroke.getKeyStroke("control M"));
        JMenuItem ChangePath = new JMenuItem("Change Path");
        Summary.addActionListener(new ActionListener()
        {
        public void actionPerformed(ActionEvent e)
            {
                     Statistics dlg = new Statistics(start,listOfArrivalTime,lenth,numberOfRA,numberOfRS,numberOfER,numberOfERq,numberOfINH,source,Address,listOfTimeLine);
            }
        });
        StatisticsMenu.add(Summary);
        // ********************** End Statistics Menu ******************************** //

         /*******************************************************************************
         ********************** Customize Menu (Changing themes)************************

         *******************************************************************************/
        CostumizeMenu = new JMenu("Customize");
        JMenu themsMenu = new JMenu("Themes");
        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem them1=new JRadioButtonMenuItem("Motif");
        them1.setMnemonic(KeyEvent.VK_R);
        them1.addActionListener(new ThemeChange(mainFrame,"com.sun.java.swing.plaf.motif.MotifLookAndFeel"));
        themsMenu.add(them1);
        group.add(them1);
        JRadioButtonMenuItem them2=new JRadioButtonMenuItem("Classic Windows");
        them2.setMnemonic(KeyEvent.VK_R);
        them2.addActionListener(new ThemeChange(mainFrame,"com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel"));
        themsMenu.add(them2);
        group.add(them2);
        JRadioButtonMenuItem them3=new JRadioButtonMenuItem("Windows");
        them3.setMnemonic(KeyEvent.VK_R);
        them3.addActionListener(new ThemeChange(mainFrame,"com.sun.java.swing.plaf.windows.WindowsLookAndFeel"));
        themsMenu.add(them3);
        group.add(them3);
        JRadioButtonMenuItem them4=new JRadioButtonMenuItem("Metal");
        them4.setMnemonic(KeyEvent.VK_R);
        them4.addActionListener(new ThemeChange(mainFrame,"javax.swing.plaf.metal.MetalLookAndFeel"));
        them4.setSelected(true);
        themsMenu.add(them4);
        group.add(them4);
        CostumizeMenu.add(themsMenu);
        // ********************** End Customize Menu (Changing themes)************************ //

        /****************************************************
         ****************** Help Menu ***********************
         ****************************************************/
         HelpMenu = new JMenu("Help");
        JMenuItem About = new JMenuItem("About...");
        About.setAccelerator(KeyStroke.getKeyStroke("F1"));
        About.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                    JOptionPane.showMessageDialog(null,"\n Z-Monitor version 2.1 \n www.z-monitor.org \n \n COINS Research Group, Al-Imam Mohamed bin Saud University (Saudi Arabia) \n CISTER Research Unit, ISEP-IPP (Portugal) \n \n Release Date: October 2011 \n Contact: dev@z-monitor.org \n \n License: General Public License  (GNU).","About Z-Monitor",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("image/zlogo.png"));
            }
        }
        );
        HelpMenu.add(About);
        //****************** End Help Menu ***********************//
        ////////////////////////////
        // Add buttons to the bar //
        ////////////////////////////
      	bar = new JMenuBar();
      	bar.add(FileMenu);
      	bar.add(EditMenu);
      	bar.add(PortMenu);
	bar.add(ProtocolMenu);
        bar.add(ZServerMenu);
      	bar.add(CaptureMenu);
      	bar.add(StatisticsMenu);
     	bar.add(CostumizeMenu);
     	bar.add(HelpMenu);
	mainFrame.setJMenuBar(bar);
	mainFrame.setVisible(true);
	firstPanel.add(scrollPane);
	thirdPanel.add(thirdPanelUpscrollPane);
        mainFrame.setVisible(true);
}

}

