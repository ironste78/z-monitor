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

//import libraries
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import Classes.Packet.*;
import Classes.GUI.*;
import Classes.Storage.*;
import Classes.Support.*;

public class ZGUIImpl implements ZGUI
{
	//Class Attribute
	public JFrame mainFrame;
	public JPanel contentPanel;
	public JPanel topPanel;
	public JTabbedPane tabbedPane;	// we put all tabs that we created on it.
	// First tab //
	public JTabbedPane innerPane;	// we used it to put tabs on it. These tabs located down the first tab.
	public JPanel firstPanel;
	public JPanel firstUpPanel;
	public JPanel firstDnPanel1;
	public JPanel firstDnPanel2;
	// Second tab //
	public JPanel secondPanel;
	// Third tab //
	public JPanel thirdPanel;
	public JPanel thirdUpPanel;
	public JPanel thirdDnPanel;
	// Forth tab //
	public JPanel forthPanel;
	public JPanel forthMedPanel;
	public JPanel forthMedPanel1;
	public JPanel forthMedPanel2;
	public JPanel forthMed1;
	public JPanel forth6lowpan;
	public JPanel forth6lowpan1;
	public JPanel forth6lowpan2;
	public JPanel forth6lowpan3;
	public JPanel forthicmp;
	public JPanel forthicmp1;
	public JPanel forthicmp2;
	public JPanel forthMed11;
	public JPanel forthMed12;
	public JPanel forthMed2;
	public JPanel forthMed21;
	public JPanel forthMed22;
	public JPanel forthDnPanel;
	// Scrolls //
	public JScrollPane scrollPane;	// For the first tab.
	public JScrollPane thirdPanelUpscrollPane;	// For the third tab.
	public JScrollPane timeLineScroll;	// For the time line.
	public JScrollPane forthscrollPane;
	public JScrollPane forthscroll;
	public Zmonitor ZM;
	public fileStorage Format;
	// Menu bar //
	public JMenuBar bar;
	public JMenu FileMenu;
	public JMenu EditMenu;
	public JMenu PortMenu;
	public JMenu ProtocolMenu;
	public JMenu CaptureMenu;
	public JMenu StatisticsMenu;
	public JMenu CostumizeMenu;
	public JMenu HelpMenu;
	public JMenu ZServerMenu;
	// Tool Bar //
	public JToolBar toolBar;
	////////////////////////
	public LinkedList<JScrollPane> listOfScrolls;
	public LinkedList<JTable> listOfTables;
	public LinkedList<ListSelectionModel> listOfListModel;
	public LinkedList<moteTimeLineImpl> listOfTimeLine;
	public LinkedList<timerImpl> listOfArrivalTime;
	public LinkedList<packetImpl> x ;
	public JLabel timeLine;
	public JLabel packetCounting;
	public JLabel myout;
	public JLabel myout2;
	public JLabel label6lowpan;
	public JLabel labelICMP;
	public JTextArea output;
	public JTextArea output2;
	public JTextArea info6lowpan;
	public JTextArea infoICMP;
	public JTextArea Hex;
	public JButton ImageButton;
	public JFrame portframe = new JFrame("Configuration Panel");
	public JFrame zserverframe = new JFrame("Configuration Panel");
	public JFrame ntpframe = new JFrame("Configuration Panel");
	public JButton ImageButton3;
	public JButton Btn6lowpan;
	public JButton BtnICMP;
	public FlowLayout layout;
	public String HexPacket = "";
	public String source="serial@COM9:telosb";
	public float becan = 0;
	public float command = 0;
	public int numberOfRA=0;//count the number of router advertisement
	public int numberOfRS=0;//count the number of router solicitation
	public int numberOfER=0;//count the number of echo reply
	public int numberOfERq=0;//count the number of echo request
	public int numberOfINH=0;//count the number of IPv6 no next header
	public float data = 0;
	public float ack = 0;
	public float lenth = 0;
	public int lenthc = 0;
	public String nowe = null;
	public Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); // get the screen size to let Z-Monitor runs in all monitor size
	public final int width = dim.width;
	public final int height = dim.height;
	public boolean blockEvents = false;
	public boolean blockEvents2 = false;
	public boolean blockEvents3 = true;
	public boolean blockEvents4 = true;
	public boolean OK = false;
	public boolean start = false;
	public int row = 0;
	public String Address = " ";
	public String Source = " ";
	public String os="";//string that contain the selected operating system
	public String plateform="";
	public DefaultTableModel tableModel;
	public JTable Header4;
	public final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
	public JTable OtherControl;
	public JTable packet;
	public JTable packetViewAll;
	public long startTime;
	public long currentTime;
	public long prevTime;
	public long timeBetweenPackets;
	public long displayTime;
	public double times;
	public double startT;
	public double currentT;
	public double displayT;
	public double displaySecond;
	public Calendar cal;
	public String ArrivalTime;
	public String slenth;
	public String cord;
	public int NumOfRecivedPacket;
	public int NumOfDesplayedplPacket;
	public double packetTimeStamp;
	public double currentt;
	public double previoust;
	public double dtimeBetweenPackets;
	public double ddisplayTime;

   	//***********************Save code****************************//
        // this function take the date
        public String now()
        {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
            return sdf.format(cal.getTime());
        }
       //***********************Save code****************************//
        public void saveCode1()
        {
            // get the date
            String now=now();
            // remove all sign that not allowed in file name
            now = now.replaceAll(":", "");
            now = now.replaceAll(" ", "");
            nowe = now;
            Format=new fileStorage(ZM.packetBuffer);
            Format.textFormat(now,ZM.protocol);
        }
        public void saveCode2()
       {
            // get the date
            String now=now();
            // remove all sign that not allowed in file name
            now = now.replaceAll(":", "");
            now = now.replaceAll(" ", "");
            nowe = now;
            Format=new fileStorage(ZM.packetBuffer);
            if (ZM.protocol.equals("IEEE 802.15.4")){
                  IEEE802_15_4 protocolType = new IEEE802_15_4Impl();
                  Format.xmlFormat(now, protocolType);
             } else if (ZM.protocol.equals("6LoWPAN")){
                   sixLoWPAN protocolType = new sixLoWPANImpl();
                   Format.xmlFormat(now, protocolType);
             }
        }
//------------------ParsedAndDesplay IEEE802.15.4--------------------//
	public void ParsedAndDesplay(IEEE802_15_4 parsedPacket)
        {
	    Thread drawTimeLineThread = new Thread (new Runnable(){
      		public void run(){
	    	TimeLineThread ();
	    }});
            int j=0;
            int i=0;
            int numOfdesplayed=0;
            final Color c1 = Color.red;
            final Color c2 = Color.blue;
            final Color c3 = Color.gray;

            for(;;j++) {
				//numOfdesplayed++;
				if (j == ZM.packetBuffer.getOrigonalPackets().size()){
				for ( ; ;) {
						try {
								Thread.sleep(2000);
						} catch (Exception e){System.err.println("Error In thread sleeping");}
						if(blockEvents3 == false){
								System.out.println("Num of Displayed packets: " + NumOfDesplayedplPacket);
								return; }
						if (j < ZM.packetBuffer.getOrigonalPackets().size())
								break;
						}
				}
				try {
                    String receivedPacketStream = ZM.packetBuffer.getOrigonalPackets().get(j);
                    double realArrivalTime = ZM.packetBuffer.getOrigonalTimes().get(j);
					parsedPacket = new IEEE802_15_4Impl();
					try {
							parsedPacket =parser.IEEE802_15_4ParsePacket(receivedPacketStream,realArrivalTime);
					} catch (Exception e){
							System.out.println("Error while parsing: "+e);
                    }
                    firstUpPanel.setPreferredSize(new Dimension(width+1200, i+75));
                    thirdUpPanel.setPreferredSize(new Dimension(width+1200, i+75));
                    //////////////////
                    packet = new viewAnalayzedPacket().ShowPacketsInTable(parsedPacket, ZM);
                    packetViewAll = new viewAnalayzedPacket().ShowPacketsInTable(parsedPacket, ZM);
                    /////////////////
                    timerImpl FrameInfo = new timerImpl();
                    FrameInfo.setFrameNo(numOfdesplayed);
                    packetImpl y =  new packetImpl();
                    y.setNO(numOfdesplayed);
                    y.setpacketinHexa(receivedPacketStream);
                    slenth = packet.getValueAt(0,2).toString();
                    lenth=Integer.parseInt(slenth)+lenth;
                    y.setlength(lenthc);
                    //get ArrivalTime of packet
                    currentt=parsedPacket.getRealArrivalTime();
                    ArrivalTime=NtpMessage.timestampToString(currentt);
                    FrameInfo.setArrivalTime(ArrivalTime);
                    if(numOfdesplayed==0)
                    {
                            // no previous packet
                            dtimeBetweenPackets = 0.0;
                            dtimeBetweenPackets = Math.round(dtimeBetweenPackets*100000)/100000.0d;
                            FrameInfo.setCaptureTime(dtimeBetweenPackets);

                    }
                    // another packet not the first
                    else
                    {
                            // time between two packets in seconds
                            dtimeBetweenPackets = currentt - previoust;
                            dtimeBetweenPackets = Math.round(dtimeBetweenPackets*100000)/100000.0d;
                            FrameInfo.setCaptureTime(dtimeBetweenPackets);
                    }
                    ddisplayTime = currentt-previoust;
                    previoust =currentt;
                    FrameInfo.setSinceFirstFrame(ddisplayTime);
                    displayT = currentt;
                    FrameInfo.setTimes(displayT);
                    displaySecond = displayT/1000;
                    // get the type of sniffed packet
                    final int myCase = parser.IEEE802_15_4Case(parsedPacket.getDestAddMode(),parsedPacket.getSrcAddMode(),parsedPacket.getIntra_PAN());
                    ////////////
                    short packetType = parsedPacket.getPacketType();
                    short myCase1 = parser.IEEE802_15_4Case(parsedPacket.getDestAddMode(),parsedPacket.getSrcAddMode(), parsedPacket.getIntra_PAN());
                    ////////////////
                    packet = new viewAnalayzedPacket().setPacketDesign(packet, dtimeBetweenPackets, displayT, numOfdesplayed, parsedPacket.getPacketType(), firstUpPanel.getWidth());
                    packetViewAll = new viewAnalayzedPacket().setPacketDesign(packetViewAll, dtimeBetweenPackets, displayT, numOfdesplayed, parsedPacket.getPacketType(), thirdUpPanel.getWidth());
                    // define the scrolls for the table
                    JScrollPane scroll1 = new JScrollPane(packet);
                    JScrollPane scroll2 = new JScrollPane(packetViewAll);
                    // specification of Beacon Frame size
                    if(packetType==0)
                    {
                            becan++;
                            Address=parsedPacket.getSrcADD();
                            scroll1.setBounds(10,i,ZM.rowWidth+4,69);
                            scroll2.setBounds(10,i,ZM.rowWidth+4,69);
                    }
                    // specification of Payload(Data) Frame size
                    else if(packetType==1)
                    {
                            data++;
                            Address=parsedPacket.getSrcADD();
                            scroll1.setBounds(0,i,ZM.rowWidth+4,69);
                            scroll2.setBounds(0,i,ZM.rowWidth+4,69);
                    }
                    // specification of Acknowledgements Frame size
                    else if(packetType==2)
                    {
                            ack++;
                            Address = "Broadcast";
                            scroll1.setBounds(35,i,ZM.rowWidth+3,69);
                            scroll2.setBounds(35,i,ZM.rowWidth+3,69);
                    }
                    // specification of Command Frame size
                    else if(packetType==3)
                    {
                            command++;
                            Address=parsedPacket.getDestADD();
                            if(myCase1==5 || myCase1==6 || myCase1==7 || myCase1==8 || myCase1==10 || myCase1==11 || myCase1==12 || myCase1==13)
                                    Address=parsedPacket.getSrcADD();
                            scroll1.setBounds(10,i,ZM.rowWidth+4,69);
                            scroll2.setBounds(10,i,ZM.rowWidth+5,69);
                    }
                    listOfScrolls.add(scroll1);
                    listOfTables.add(packet);
                    listOfArrivalTime.add(FrameInfo);
                    x.add(y);
                    start = true;

                    Object [] row5 = new viewAnalayzedPacket().ShowPacketAnalayzer (packet, parsedPacket, ZM.protocol, numOfdesplayed, displayT);
                    tableModel.addRow(row5);
                    //********************* adding selection listener to first table ************************
                    final JTable finalPacket = packet;
                    ListSelectionModel listSelectionModel = packet.getSelectionModel();
                    listSelectionModel.addListSelectionListener(new SelectionHandler(finalPacket,OtherControl,parsedPacket.getPacketType(),myCase,blockEvents,listOfListModel));
                    packet.setSelectionModel(listSelectionModel);
                    listOfListModel.add(listSelectionModel);
                    if (j==0){
                        Header4.addMouseListener(new MouseAdapter()
                        {
                            public void mouseClicked(MouseEvent e)
                            {
                                      if (e.getClickCount() == 1)
                                {
                                        JTable target = (JTable)e.getSource();
                                        row = target.getSelectedRow();
                                        if (target.isRowSelected(row))
                                            {
                                                    blockEvents2 = true;
                                                    OK = true;
                                            }
                                }
                                //Fernando
                                //Never happened, because blockEvents4 is always true
                                if (OK == true)// && blockEvents4 == false)
                                {
                                    Firstselect(Header4,myCase);
                                    OK = false;
                                }
                            }
                        });
                    }
                    //Fernando comment this if
                    /*if (OK == true)
                    {
                        Firstselect(Header4,myCase);
                        OK = false;
                    }*/
                    forthPanel.add(forthscroll);
                    forthPanel.add(forthDnPanel);
                    // add the two scrolls that contain the packets in the panel
                    firstUpPanel.add(scroll1);
                    thirdUpPanel.add(scroll2);
                    // set the packet counter
                    packetCounting.setText("Packet Count: "+(j+1));

                    ZM.packetBuffer.AddPackets(parsedPacket);
                    NumOfDesplayedplPacket++;
                    i+=75;
		    numOfdesplayed++;
					if (j==0){
						drawTimeLineThread.start();
					}
				} catch (Exception e){
					System.out.println("Error while parsing and displaying operation, Maybe used the wrong protocol:");
					System.out.println(e);
				}
            }
	}

	//----------------End of ParsedAndDesplay IEEE802.15.4------------------//

	//---------------ParsedAndDesplay 6LoWPAN with ICMP header--------------//
   public void ParsedAndDesplay(ICMPStruct parsedPacket)
        {
	    Thread drawTimeLineThread = new Thread (new Runnable(){
      		public void run(){
	    	TimeLineThread ();
	    }});
            int j=0;
            int i=0;
            int numOfdesplayed=0;
            final Color c1 = Color.red;
            final Color c2 = Color.blue;
            final Color c3 = Color.gray;

            for(;;j++) {
                    //numOfdesplayed++;
                    if (j == ZM.packetBuffer.getOrigonalPackets().size()){
                    for ( ; ;) {
                            try {
                                    Thread.sleep(2000);
                            } catch (Exception e){System.err.println("Error In thread sleeping");}
                            if(blockEvents3 == false){
                                    System.out.println("Num of Displayed packets: " + NumOfDesplayedplPacket);
                                    return; }
                            if (j < ZM.packetBuffer.getOrigonalPackets().size())
                                    break;
                            }
                    }
                    try {
                          String receivedPacketStream = ZM.packetBuffer.getOrigonalPackets().get(j);
                          double realArrivalTime = ZM.packetBuffer.getOrigonalTimes().get(j);
                          parsedPacket = new ICMPStruct();
                          try {
                                parsedPacket =parser.ICMPParsePacket(receivedPacketStream,realArrivalTime);
                          } catch (Exception e){
                                System.out.println("Error while parsing: "+e);
                          }
                          firstUpPanel.setPreferredSize(new Dimension(width+5000, i+75));
                          thirdUpPanel.setPreferredSize(new Dimension(width+5000, i+75));
                          //////////////////
                          packet = new viewAnalayzedPacket().ShowPacketsInTable(parsedPacket, ZM);
                          packetViewAll = new viewAnalayzedPacket().ShowPacketsInTable(parsedPacket, ZM);
                          /////////////////
                          timerImpl FrameInfo = new timerImpl();
                          FrameInfo.setFrameNo(numOfdesplayed);
                          packetImpl y =  new packetImpl();
                          y.setNO(numOfdesplayed);
                          y.setpacketinHexa(receivedPacketStream);
                          slenth = packet.getValueAt(0,2).toString();
                          lenth=Integer.parseInt(slenth)+lenth;
                          y.setlength(lenthc);
                          //get ArrivalTime of packet
                          currentt=parsedPacket.sixLoWPANPacket.getRealArrivalTime();
                          ArrivalTime=NtpMessage.timestampToString(currentt);
                          FrameInfo.setArrivalTime(ArrivalTime);
                          if(j==0)
                          {
                                  // no previous packet
                                  dtimeBetweenPackets = 0.0;
                                  dtimeBetweenPackets = Math.round(dtimeBetweenPackets*100000)/100000.0d;
                                  FrameInfo.setCaptureTime(dtimeBetweenPackets);

                          }
                          // another packet not the first
                          else
                          {
                                  // time between two packets in seconds
                                  dtimeBetweenPackets = currentt - previoust;
                                  dtimeBetweenPackets = Math.round(dtimeBetweenPackets*100000)/100000.0d;
                                  FrameInfo.setCaptureTime(dtimeBetweenPackets);
                          }
                          ddisplayTime = currentt-previoust;
                          previoust =currentt;
                          FrameInfo.setSinceFirstFrame(ddisplayTime);
                          displayT = currentt;
                          FrameInfo.setTimes(displayT);
                          displaySecond = displayT/1000;
                          // get the type of sniffed packet
                          final int myCase = parser.IEEE802_15_4Case(parsedPacket.sixLoWPANPacket.getDestAddMode(),parsedPacket.sixLoWPANPacket.getSrcAddMode(),parsedPacket.sixLoWPANPacket.getIntra_PAN());
                          ////////////
                          short packetType = parsedPacket.sixLoWPANPacket.getPacketType();
                          short myCase1 = parser.IEEE802_15_4Case(parsedPacket.sixLoWPANPacket.getDestAddMode(),parsedPacket.sixLoWPANPacket.getSrcAddMode(), parsedPacket.sixLoWPANPacket.getIntra_PAN());
                          ////////////////
                          packet = new viewAnalayzedPacket().setPacketDesign(packet, dtimeBetweenPackets, displayT, numOfdesplayed, parsedPacket.sixLoWPANPacket.getPacketType(), firstUpPanel.getWidth());
                          packetViewAll = new viewAnalayzedPacket().setPacketDesign(packetViewAll, dtimeBetweenPackets, displayT, numOfdesplayed, parsedPacket.sixLoWPANPacket.getPacketType(), thirdUpPanel.getWidth());
                          // define the scrolls for the table
                          JScrollPane scroll1 = new JScrollPane(packet);
                          JScrollPane scroll2 = new JScrollPane(packetViewAll);
                          // specification of Beacon Frame size
                          if(packetType==0)
                          {
                                  becan++;
                                  Address=parsedPacket.sixLoWPANPacket.getSrcADD();
                                  scroll1.setBounds(10,i,ZM.rowWidth+4,69);
                                  scroll2.setBounds(10,i,ZM.rowWidth+4,69);
                          }
                          // specification of Payload(Data) Frame size
                          else if(packetType==1)
                          {
                                  data++;
                                  Address=parsedPacket.sixLoWPANPacket.getSrcADD();
                                  String type=parsedPacket.sixLoWPANPacket.get6LoWPANType();
                                  if (type.equals("Router advertisement"))
                                       numberOfRA++;
                                  else if(type.equals("Router solicitation"))
                                       numberOfRS++;
                                  else if(type.equals("Echo request"))
                                       numberOfERq++;
                                  else if(type.equals("Echo reply"))
                                       numberOfER++;
                                  else if(type.equals("IPv6 no next header"))
                                       numberOfINH++;
                                  scroll1.setBounds(0,i,ZM.rowWidth+4,69);
                                  scroll2.setBounds(0,i,ZM.rowWidth+4,69);
                          }
                          // specification of Acknowledgements Frame size
                          else if(packetType==2)
                          {
                                  ack++;
                                  Address = "Broadcast";
                                  scroll1.setBounds(35,i,ZM.rowWidth+3,69);
                                  scroll2.setBounds(35,i,ZM.rowWidth+3,69);
                          }
                          // specification of Command Frame size
                          else if(packetType==3)
                          {
                                  command++;
                                  Address=parsedPacket.sixLoWPANPacket.getDestADD();
                                  if(myCase1==5 || myCase1==6 || myCase1==7 || myCase1==8 || myCase1==10 || myCase1==11 || myCase1==12 || myCase1==13)
                                          Address=parsedPacket.sixLoWPANPacket.getSrcADD();
                                  scroll1.setBounds(10,i,ZM.rowWidth+4,69);
                                  scroll2.setBounds(10,i,ZM.rowWidth+5,69);
                          }
                          listOfScrolls.add(scroll1);
                          listOfTables.add(packet);
                          listOfArrivalTime.add(FrameInfo);
                          x.add(y);
                          start = true;

                          Object [] row5 = new viewAnalayzedPacket().ShowPacketAnalayzer (packet, parsedPacket.sixLoWPANPacket, ZM.protocol, numOfdesplayed, displayT);
                          tableModel.addRow(row5);
                          //********************* adding selection listener to first table ************************
                          final JTable finalPacket = packet;
                          ListSelectionModel listSelectionModel = packet.getSelectionModel();
                          listSelectionModel.addListSelectionListener(new SelectionHandler(finalPacket,OtherControl,parsedPacket.sixLoWPANPacket.getPacketType(),myCase,blockEvents,listOfListModel));
                          packet.setSelectionModel(listSelectionModel);
                          listOfListModel.add(listSelectionModel);
                          if (j==0){
                            Header4.addMouseListener(new MouseAdapter()
                            {
                              public void mouseClicked(MouseEvent e)
                              {
                                      if (e.getClickCount() == 1)
                                      {
                                              JTable target = (JTable)e.getSource();
                                              row = target.getSelectedRow();
                                              if (target.isRowSelected(row))
                                                  {
                                                          blockEvents2 = true;
                                                          OK = true;
                                                  }
                                      }
                                        //Fernando
                                        //Never happened, because blockEvents4 is always true
                                        if (OK == true)// && blockEvents4 == false)
                                        {
                                            Firstselect(Header4,myCase);
                                            OK = false;
                                        }
                              }
                          });
                        }
                          //Fernando comments this block
                          //if (OK == true)
                          //{
                          //    Firstselect(Header4,myCase);
                          //    OK = false;
                          //}
                          forthPanel.add(forthscroll);
                          forthPanel.add(forthDnPanel);
                          // add the two scrolls that contain the packets in the panel
                          firstUpPanel.add(scroll1);
                          thirdUpPanel.add(scroll2);
                          // set the packet counter
                          packetCounting.setText("Packet Count: "+(j+1));

                          ZM.packetBuffer.AddPackets(parsedPacket);
                          //
                          IEEE802_15_4 IEEEpacket;
                          IEEEpacket = parsedPacket.sixLoWPANPacket;
                          ZM.packetBuffer.AddPackets(IEEEpacket);
                          //
                          NumOfDesplayedplPacket++;
                          i+=75;
			  numOfdesplayed++;
                          if (j==0)
                          {
                                drawTimeLineThread.start();
                          }
                        } catch (Exception e){
                                System.out.println("Error while parsing and displaying operation, Maybe used the wrong protocol:");
                                System.out.println(e);
                          }
            }
	}
	//------- End of Parsed And Display for 6LoWPAN with ICMP header --------//

	//------------------------ TimeLineThread Method ------------------------//
	public void TimeLineThread (){
		String Address = "";
		IEEE802_15_4 parsedPacket;
		for(int j=0;;j++) {
			if (j == ZM.packetBuffer.IEEE802_15_4getPackets().size()){
			for ( ; ;) {
				try {
					Thread.sleep(2000);
				} catch (Exception e){System.err.println("Error In thread sleeping");}
				if(blockEvents3 == false){
					return; }
				if (j < ZM.packetBuffer.IEEE802_15_4getPackets().size())
					break;
				}
			}
			boolean isExist = false;
			int counter = 0;
			if (blockEvents3 == false){
				for ( ;j == ZM.packetBuffer.IEEE802_15_4getPackets().size();j++){
					parsedPacket = ZM.packetBuffer.IEEE802_15_4getPackets().get(j);
					if (parsedPacket.getPacketType()==0 || parsedPacket.getPacketType()==1 || parsedPacket.getPacketType()==3)
						Address = parsedPacket.getSrcADD();
					else if (parsedPacket.getPacketType()==2)
						Address = "Broadcast";
					// add the packet to the listOfTimeLine to draw the time line
					for(moteTimeLineImpl list : listOfTimeLine)
					{
						if(list.getAddress().equals(Address))
						{
							if(parsedPacket.getPacketType()==0)
								list.packet.add("BecF");
							else if(parsedPacket.getPacketType()==1)
								list.packet.add("PayF");
							else if(parsedPacket.getPacketType()==2)
								list.packet.add("AckF");
							else if(parsedPacket.getPacketType()==3)
								list.packet.add("CmdF");
							isExist = true;
						}
						else
						{
							list.packet.add("EMPTY");

						 }
						 //get the largest number of packets that sent by the mote
						 if(counter < list.packet.size())
								counter = list.packet.size();
					}
					// means new mote join to the WSN
					if(! isExist)
					{
						if(! Address.equals(""))
						{
							moteTimeLineImpl newOne = new moteTimeLineImpl();
							newOne.setAddress(Address);

							// add empty to all previous time before joining to the network
							for(int ii=0;ii<counter-1;ii++)
								newOne.packet.add("EMPTY");
							if(parsedPacket.getPacketType()==0)
								newOne.packet.add("BecF");
							else if(parsedPacket.getPacketType()==1)
								newOne.packet.add("PayF");
							else if(parsedPacket.getPacketType()==2)
								newOne.packet.add("AckF");
							else if(parsedPacket.getPacketType()==3)
								newOne.packet.add("CmdF");

							listOfTimeLine.add(newOne);
						}
					}
					// put the broadcast (Acknowledgement) in the top of list
					for(moteTimeLineImpl list : listOfTimeLine)
						if(list.getAddress().equals("Broadcast"))
						{
							listOfTimeLine.remove(list);
							listOfTimeLine.addFirst(list);
							break;
						}
				}
				drawTimeLineTable(listOfTimeLine);
				return;
			}
			parsedPacket = ZM.packetBuffer.IEEE802_15_4getPackets().get(j);
			if (parsedPacket.getPacketType()==0 || parsedPacket.getPacketType()==1 || parsedPacket.getPacketType()==3)
				Address = parsedPacket.getSrcADD();
			else if (parsedPacket.getPacketType()==2)
				Address = "Broadcast";
			// add the packet to the listOfTimeLine to draw the time line
			for(moteTimeLineImpl list : listOfTimeLine)
			{
				if(list.getAddress().equals(Address))
				{
					if(parsedPacket.getPacketType()==0)
						list.packet.add("BecF");
					else if(parsedPacket.getPacketType()==1)
						list.packet.add("PayF");
					else if(parsedPacket.getPacketType()==2)
						list.packet.add("AckF");
				        else if(parsedPacket.getPacketType()==3)
						list.packet.add("CmdF");
					isExist = true;
				}
				else
				{
					list.packet.add("EMPTY");

				 }
				 //get the largest number of packets that sent by the mote
				 if(counter < list.packet.size())
						counter = list.packet.size();
			}
			// means new mote join to the WSN
			if(! isExist)
			{
				if(! Address.equals(""))
				{
					moteTimeLineImpl newOne = new moteTimeLineImpl();
					newOne.setAddress(Address);

					// add empty to all previous time before joining to the network
					for(int ii=0;ii<counter-1;ii++)
						newOne.packet.add("EMPTY");
					if(parsedPacket.getPacketType()==0)
						newOne.packet.add("BecF");
					else if(parsedPacket.getPacketType()==1)
						newOne.packet.add("PayF");
					else if(parsedPacket.getPacketType()==2)
						newOne.packet.add("AckF");
				        else if(parsedPacket.getPacketType()==3)
						newOne.packet.add("CmdF");

					listOfTimeLine.add(newOne);
				}
			}
			// put the broadcast (Acknowledgement) in the top of list
			for(moteTimeLineImpl list : listOfTimeLine)
				if(list.getAddress().equals("Broadcast"))
				{
					listOfTimeLine.remove(list);
					listOfTimeLine.addFirst(list);
					break;
				}
			// draw the time line
			drawTimeLineTable(listOfTimeLine);
		}
	}
	//--------------------- End of TimeLineThread Method ---------------------//
        //////////////////////////
       ///draw Time Line Table///
      //////////////////////////
      /*
      * This method takes a linked list and draws the time line
      */
	public void drawTimeLineTable(LinkedList<moteTimeLineImpl> table)
	{
		String drawTable = "<html><table border='0'><tr><th>Mote Address</th></tr>";
		for(moteTimeLineImpl list : table)
		{
			drawTable += "<tr><td>"+list.getAddress()+":</td>";
			for(String list1 : list.packet)
			{
				if(list1.equals("BecF"))
					drawTable += "<td bgcolor='red' style='width: 1px;'></td><td style='width: 1px;'></td>";
				else
					if(list1.equals("PayF"))
						drawTable += "<td bgcolor='yellow' style='width: 1px;'></td><td style='width: 1px;'></td>";
					else
						if(list1.equals("AckF"))
							drawTable += "<td bgcolor='gray' style='width: 1px;'></td><td style='width: 1px;'></td>";
                                                else
                                                    if(list1.equals("CmdF"))
							drawTable += "<td bgcolor='blue' style='width: 1px;'></td><td style='width: 1px;'></td>";

                                                    else
                                                        if(list1.equals("EMPTY"))
                                                            drawTable += "<td  style='width: 1px;'><td style='width: 1px;'></td>";
			}
			drawTable += "</tr>";
		}
		drawTable += "</table></html>";
		if(table.size()>6)
		{
			timeLine.setBounds(0, 0, width-60, table.size()*40);
			timeLine.setPreferredSize(new Dimension(8000, table.size()*40));
		}
		timeLine.setText(drawTable);

	}
	//---------------Firstselect-------------//
	//to display the info for a specific packet in packet analysis tab
        public void Firstselect (JTable e,int b)
	{
		String SNo = Integer.toString(row);
		packet n=x.get(row);
		timerImpl time = listOfArrivalTime.get(row);
		JTable terget = e;
		int myCase = b;
		DecimalFormat Places = new DecimalFormat("0.000");
		//Fernando comments this two lines
                //HexPacket=ZM.sniffer.getPacket();
		//System.out.println(HexPacket);
		for(JTable list:listOfTables)
                {
                    if(SNo.equals(list.getValueAt(0,1)) && blockEvents2==true)
                    {
			ClassLoader cldr = this.getClass().getClassLoader();
                        ImageIcon icon = new ImageIcon(cldr.getResource("Classes/image/arrow1.png"));
			Color co = new Color(148,183,84);
			Font fo = new Font("Courier New",10,15);

			ImageButton.setBorder(BorderFactory.createLineBorder(Color.white));
			ImageButton3.setBorder(BorderFactory.createLineBorder(Color.white));
			Btn6lowpan.setBorder(BorderFactory.createLineBorder(Color.white));
			BtnICMP.setBorder(BorderFactory.createLineBorder(Color.white));
			myout.setOpaque(true);
			myout2.setOpaque(true);
			label6lowpan.setOpaque(true);
			labelICMP.setOpaque(true);
			myout.setBackground(Color.lightGray);
			myout2.setBackground(Color.LIGHT_GRAY);
			label6lowpan.setBackground(Color.LIGHT_GRAY);
			labelICMP.setBackground(Color.LIGHT_GRAY);
			myout.setText(" Frame "+list.getValueAt(0,1));
			myout2.setText(" IEEE 802.15.4 , Dst:  "+terget.getValueAt(row,3)+", Src:  "+terget.getValueAt(row,2));
			label6lowpan.setText("6LoWPAN Protocol Info");
			labelICMP.setText("ICMP Header Info");
                          output.setFont(fo);
                          output2.setFont(fo);
                          info6lowpan.setFont(fo);
                          infoICMP.setFont(fo);
                          Hex.setFont(fo);
                          String ok = " ";

      if (ZM.protocol.equals("IEEE 802.15.4")){
      //Ack details
            if(terget.getValueAt(row,4).equals("Ack"))
             {
                if(list.getValueAt(0,10).equals("01"))
                        ok = "01(OK)";
                else
                      ok = "00(ERROR)";
                      output2.setText("   Sequence Number : "+list.getValueAt(0,4)+
                           "	\n   Link Quality Indication :  "+list.getValueAt(0,5)+
                              "	\n   MAC Header Length :  "+list.getValueAt(0,6)+
                                 "	\n   Channel :  "+list.getValueAt(0,7)+
                                    "	\n   Cyclic redundancy check :  "+ok);
                      Btn6lowpan.setVisible(false);
                      BtnICMP.setVisible(false);
                      label6lowpan.setVisible(false);
                      info6lowpan.setVisible(false);
                      BtnICMP.setVisible(false);
                      labelICMP.setVisible(false);
                      infoICMP.setVisible(false);
             }
             else if(terget.getValueAt(row,4).equals("Beacon"))
             {
                if(list.getValueAt(0,16).equals("01"))
                        ok = "01(OK)";
                else
                        ok = "00(ERROR)";
                        output2.setText("   Sequence Number : "+list.getValueAt(0,4)+
                                "\n   Source PAN : "+list.getValueAt(0,5)+
                                "	\n   Source :  "+list.getValueAt(0,6)+
                                        "	\n   Link Quality Indication :  "+list.getValueAt(0,11)+
                                                "	\n   MAC Header Length :  "+list.getValueAt(0,12)+
                                                        "	\n   Channel :  "+list.getValueAt(0,13)+
                                                "	\n   Cyclic redundancy check :  "+ok);
            }
            else if(terget.getValueAt(row,4).equals("Data"))
            {
               if(list.getValueAt(0,15).equals("01"))
                        ok = "01(OK)";
                else
                        ok = "00(ERROR)";
                        output2.setText("   Sequence Number : "+list.getValueAt(0,4)+
                                "\n   Source PAN : "+list.getValueAt(0,7)+
                                "	\n   Source :  "+list.getValueAt(0,8)+
                                        "	\n   Link Quality Indication :  "+list.getValueAt(0,10)+
                                                "	\n   MAC Header Length :  "+list.getValueAt(0,11)+
                                                        "	\n   Channel :  "+list.getValueAt(0,12)+
                                                "	\n   Cyclic redundancy check :  "+ok);
            }
            else if(terget.getValueAt(row,4).equals("Command"))
            {
            if(list.getValueAt(0,15).equals("01"))
                        ok = "01(OK)";
                else
                        ok = "00(ERROR)";
                output2.setText("   Sequence Number : "+list.getValueAt(0,4)+
                                "\n   Destination PAN : "+list.getValueAt(0,5)+
                                "	\n   Destination :  "+list.getValueAt(0,6)+
                                "\n   Source PAN : "+list.getValueAt(0,7)+
                                "	\n   Source :  "+list.getValueAt(0,8)+
                                        "	\n   Link Quality Indication :  "+list.getValueAt(0,10)+
                                                "	\n   MAC Header Length :  "+list.getValueAt(0,11)+
                                                        "	\n   Channel :  "+list.getValueAt(0,12)+
                                                "	\n   Cyclic redundancy check :  "+ok);
            }
         } else if (ZM.protocol.equals("6LoWPAN")){
      //Ack details
            if(terget.getValueAt(row,4).equals("Ack"))
             {
                if(list.getValueAt(0,10).equals("01"))
                        ok = "01(OK)";
                else
                      ok = "00(ERROR)";
                      output2.setText("   Sequence Number : "+list.getValueAt(0,4)+
                           "	\n   Link Quality Indication :  "+list.getValueAt(0,5)+
                              "	\n   MAC Header Length :  "+list.getValueAt(0,6)+
                                 "	\n   Channel :  "+list.getValueAt(0,7)+
                                    "	\n   Cyclic redundancy check :  "+ok);
                      Btn6lowpan.setVisible(false);
                      BtnICMP.setVisible(false);
                      label6lowpan.setVisible(false);
                      info6lowpan.setVisible(false);
                      BtnICMP.setVisible(false);
                      labelICMP.setVisible(false);
                      infoICMP.setVisible(false);
					  Btn6lowpan.setText("+");
					  BtnICMP.setText("+");
					  
             }
             else if(terget.getValueAt(row,4).equals("Beacon"))
             {
                if(list.getValueAt(0,16).equals("01"))
                        ok = "01(OK)";
                else
                        ok = "00(ERROR)";
                        output2.setText("   Sequence Number : "+list.getValueAt(0,4)+
                                "\n   Source PAN : "+list.getValueAt(0,5)+
                                "	\n   Source :  "+list.getValueAt(0,6)+
                                        "	\n   Link Quality Indication :  "+list.getValueAt(0,11)+
                                                "	\n   MAC Header Length :  "+list.getValueAt(0,12)+
                                                        "	\n   Channel :  "+list.getValueAt(0,13)+
                                                "	\n   Cyclic redundancy check :  "+ok);
						Btn6lowpan.setVisible(false);
                      BtnICMP.setVisible(false);
                      label6lowpan.setVisible(false);
                      info6lowpan.setVisible(false);
                      BtnICMP.setVisible(false);
                      labelICMP.setVisible(false);
                      infoICMP.setVisible(false);
					  Btn6lowpan.setText("+");
					  BtnICMP.setText("+");
            }
            else if(terget.getValueAt(row,4).equals("Data"))
            {
               if(list.getValueAt(0,22).equals("01"))
                        ok = "01(OK)";
                else
                        ok = "00(ERROR)";
                        output2.setText("   Sequence Number : "+list.getValueAt(0,4)+
                                "\n   Source PAN : "+list.getValueAt(0,7)+
                                "	\n   Source :  "+list.getValueAt(0,8)+
                                        "	\n   Link Quality Indication :  "+list.getValueAt(0,17)+
                                                "	\n   MAC Header Length :  "+list.getValueAt(0,18)+
                                                        "	\n   Channel :  "+list.getValueAt(0,19)+
                                                "	\n   Cyclic redundancy check :  "+ok);
                      if (list.getValueAt(0,11).toString().isEmpty() && list.getValueAt(0,12).toString().isEmpty())
                              info6lowpan.setText("   Dispatch: "+list.getValueAt(0,9)+"\n   Encoding: "+list.getValueAt(0,10)+"\n   Next Header: "+list.getValueAt(0,13)+"\n   Hop limit: "+list.getValueAt(0,14)+"\n   Destination Address: "+list.getValueAt(0,16)+"\n   Source Address: "+list.getValueAt(0,15));
                      else
                              info6lowpan.setText("   Dispatch: "+list.getValueAt(0,9)+"\n   Encoding: "+list.getValueAt(0,10)+"\n   Context: "+list.getValueAt(0,11)+"\n   Traffic Class FlowLable: "+list.getValueAt(0,12)+"\n   Next Header: "+list.getValueAt(0,13)+"\n   Hop limit: "+list.getValueAt(0,14)+"\n   Destination Address: "+list.getValueAt(0,16)+"\n   Source Address: "+list.getValueAt(0,15));

                      if(list.getValueAt(0,23).equals("80") || list.getValueAt(0,23).equals("81")) //Echo request & Echo replay
                            infoICMP.setText("   ICMP Type: "+"Echo Request Header"+"\n   ICMP Code: "+list.getValueAt(0,24)+"\n   ICMP checksum: "+list.getValueAt(0,25)+"\n   ICMP ID: "+list.getValueAt(0,26)+"\n   ICMP sequence: "+list.getValueAt(0,27));
                      else if(list.getValueAt(0,23).equals("86")) //Router advertisement
                            infoICMP.setText("   ICMP Type: "+"Router advertisement"+"\n   ICMP Code: "+list.getValueAt(0,24)+"\n   ICMP checksum: "+list.getValueAt(0,25)+"\n   Cur Hop Limit: "+list.getValueAt(0,26)+"\n   Flag: "+list.getValueAt(0,27)
                                          +"\n   Router Lifetime: "+list.getValueAt(0,28)+"\n   Reachable Time: "+list.getValueAt(0,29)+"\n   Retrans Timer: "+list.getValueAt(0,30)+"\n   Prefix Info Option: "+list.getValueAt(0,31)+"\n   Address IPOption: "+list.getValueAt(0,32));
                      else if(list.getValueAt(0,23).equals("85")) //Router solicitation
                            infoICMP.setText("   ICMP Type: "+"Router solicitation"+"\n   ICMP Code: "+list.getValueAt(0,24)+"\n   ICMP checksum: "+list.getValueAt(0,25));
                      else if(list.getValueAt(0,23).equals("9B")){
							 if (list.getValueAt(0,24).equals("02")) //DAO
                              infoICMP.setText("   ICMP Type: "+"DODAG Advertisement Object"+"\n   ICMP Code: "+list.getValueAt(0,24)+"\n   ICMP checksum: "+list.getValueAt(0,25)+"\n   RPL Instance ID: "+list.getValueAt(0,26)+"\n   Flags: "+list.getValueAt(0,27)+"\n   ICMP Reserved: "+list.getValueAt(0,28)+"\n   DAO Sequence: "+list.getValueAt(0,29)
                                            +"\n   RPL Target Type: "+list.getValueAt(0,30)+"\n   RPL Target Length: "+list.getValueAt(0,31)+"\n   RPL Target Reserved: "+list.getValueAt(0,32)+"\n   RPL Target Target Length: "+list.getValueAt(0,33)+"\n   RPLTarget_Target: "+list.getValueAt(0,34)+"\n   Transit Info Type: "+list.getValueAt(0,35)
                                            +"\n   Transit Info Length: "+list.getValueAt(0,36)+"\n   Transit Info Flags: "+list.getValueAt(0,37)+"\n   Transit Info Path Control: "+list.getValueAt(0,38)+"\n   Transit Info Path Sequence: "+list.getValueAt(0,39)+"\n   Transit Info Path Lifetime: "+list.getValueAt(0,40));
							 else if (list.getValueAt(0,24).equals("01")) //DIO
                              infoICMP.setText("   ICMP Type: "+"DODAG Information Object"+"\n   ICMP Code: "+list.getValueAt(0,24)+"\n   ICMP checksum: "+list.getValueAt(0,25)+"\n   RPL Instance ID: "+list.getValueAt(0,26)+"\n   Version: "+list.getValueAt(0,27)+"\n   Rank: "+list.getValueAt(0,28)+"\n   Flags: "+list.getValueAt(0,29)
                                            +"\n   DATSN: "+list.getValueAt(0,30)+"\n   Flags2: "+list.getValueAt(0,31)+"\n   ICMP Reserved: "+list.getValueAt(0,32)+"\n   DODAG ID: "+list.getValueAt(0,33)+"\n   Metric Container Type: "+list.getValueAt(0,34)+"\n   Metric Container Length: "+list.getValueAt(0,35)
                                            +"\n   DODAG Configuration Type: "+list.getValueAt(0,36)+"\n   DODAG Configuration Length: "+list.getValueAt(0,37)+"\n   DODAG Configuration Flags: "+list.getValueAt(0,38)+"\n   DODAG Configuration DIO Interval Doubling: "+list.getValueAt(0,39)+"\n   DODAG Configuration DIO Interval Min: "+list.getValueAt(0,40)
                                            +"\n   DODAG Configuration DIO Redundancy Constant: "+list.getValueAt(0,41)+"\n   DODAG Configuration MaxRank: "+list.getValueAt(0,42)+"\n   DODAG Configuration Min Hop Rank: "+list.getValueAt(0,43)+"\n   DODAG Configuration OCP: "+list.getValueAt(0,44)+"\n   DODAG Configuration Reserved: "+list.getValueAt(0,45)
                                            +"\n   DODAG Configuration Default Lifetime: "+list.getValueAt(0,46)+"\n   DODAG Configuration Lifetime Unit: "+list.getValueAt(0,47)+"\n   Prefix Info Type: "+list.getValueAt(0,48)+"\n   Prefix Info Length: "+list.getValueAt(0,49)+"\n   Prefix Info Prefix Length: "+list.getValueAt(0,50)
                                            +"\n   Prefix Info Flags: "+list.getValueAt(0,51)+"\n   Prefix Info Valid Lifetime: "+list.getValueAt(0,52)+"\n   Prefix Info Preferred Lifetime: "+list.getValueAt(0,53)+"\n   Prefix Info Reserved: "+list.getValueAt(0,54)+"\n   Prefix Info Destination Prefix: "+list.getValueAt(0,55));
							 else if (list.getValueAt(0,24).equals("00")) //DIS
                              infoICMP.setText("   ICMP Type: "+"DODAG Advertisement Object"+"\n   ICMP Code: "+list.getValueAt(0,24)+"\n   ICMP checksum: "+list.getValueAt(0,25)+"\n   Flags: "+list.getValueAt(0,26)+"\n   ICMP Reserved: "+list.getValueAt(0,27)+"\n   Solicited Info Type: "+list.getValueAt(0,28)+"\n   Solicited Info Length: "+list.getValueAt(0,29)
                                            +"\n   Solicited Info Instance: "+list.getValueAt(0,30)+"\n   Solicited Info Flags: "+list.getValueAt(0,31)+"\n   Solicited Info DODAGID: "+list.getValueAt(0,32)+"\n   Solicited Info Version: "+list.getValueAt(0,33));
						}
                      else
                            infoICMP.setText("   No next Header");
                      Btn6lowpan.setVisible(true);
                      label6lowpan.setVisible(true);
                      //labelICMP.setVisible(true);
            }
            else if(terget.getValueAt(row,4).equals("Command"))
            {
				if(list.getValueAt(0,15).equals("01"))
                        ok = "01(OK)";
                else
                        ok = "00(ERROR)";
                output2.setText("   Sequence Number : "+list.getValueAt(0,4)+
                                "\n   Destination PAN : "+list.getValueAt(0,5)+
                                "	\n   Destination :  "+list.getValueAt(0,6)+
                                "\n   Source PAN : "+list.getValueAt(0,7)+
                                "	\n   Source :  "+list.getValueAt(0,8)+
                                        "	\n   Link Quality Indication :  "+list.getValueAt(0,10)+
                                                "	\n   MAC Header Length :  "+list.getValueAt(0,11)+
                                                        "	\n   Channel :  "+list.getValueAt(0,12)+
                                                "	\n   Cyclic redundancy check :  "+ok);
					Btn6lowpan.setVisible(false);
                    BtnICMP.setVisible(false);
                    label6lowpan.setVisible(false);
                    info6lowpan.setVisible(false);
                    BtnICMP.setVisible(false);
                    labelICMP.setVisible(false);
                    infoICMP.setVisible(false);
					Btn6lowpan.setText("+");
					BtnICMP.setText("+");
            }
         }

			 double oldtime;
		 	 if(row == 0)
		 	 	oldtime = 0;
		 	 else
			 	oldtime = NtpMessage.timestampToDouble((terget.getValueAt(row-1,1)).toString());
			 double nowtime = NtpMessage.timestampToDouble((terget.getValueAt(row,1)).toString());
			 double newtime = nowtime - oldtime  ;
                         double firstone= NtpMessage.timestampToDouble((terget.getValueAt(0,1)).toString());

			 final int lc = n.getlength();

                         output.setText("   Arrival Time : "+time.getDayn()+" "+time.getMonth()+" "+time.getDay()+"."+time.getYear()+" "+time.getTime()+
                               "\n   [ Time delta from previous captured frame : "+Places.format(newtime*1000)+" milliseconds ]"+
                                 "\n   [ Time delta from previous displayed frame : "+Places.format(newtime*1000)+" milliseconds ]"+
                                 	"\n   [ Time since reference or first frame : "+Places.format(nowtime-firstone)+" Second ]"+
                                 		"\n   Frame Number : "+list.getValueAt(0,1)+
                                 			"\n   Frame Length : "+list.getValueAt(0,2)+" bytes"+
                                 				"\n   Capture Length : "+lc+" bytes");
                        output.setEditable( false );
                        output2.setEditable( false );
                        info6lowpan.setEditable( false );
                        //labelICMP.setEditable( false );
                        infoICMP.setEditable( false );
                        ImageButton.setBackground(Color.WHITE);
                        ImageButton3.setBackground(Color.WHITE);
                        Btn6lowpan.setBackground(Color.WHITE);
						BtnICMP.setBackground(Color.WHITE);
                        forthMed11.add(ImageButton);
                        forthMed21.add(ImageButton3);
                        if(ZM.protocol.equals("6LoWPAN") && terget.getValueAt(row,4).equals("Data"))
                        {
                                forth6lowpan1.add(Btn6lowpan);
                                forth6lowpan1.add(label6lowpan);
                                forth6lowpan.add(info6lowpan);
                                forth6lowpan3.add(BtnICMP);
                                forth6lowpan3.add(labelICMP);
                                forth6lowpan2.add(infoICMP, BorderLayout.SOUTH);
                        }
                        forthMed11.add(myout);
                        forthMed21.add(myout2);
                        forthMed12.add(output);
                        forthMed22.add(output2);

                        if (n.getpacketinHexa().length()>150)
                            Hex.setText(n.getpacketinHexa().substring(0,150)+"\n"+n.getpacketinHexa().substring(150,n.getpacketinHexa().length()));
                        else
							Hex.setText(n.getpacketinHexa());
                        Hex.setEditable( false );
                        forthDnPanel.add(Hex);
                        ImageButton.addActionListener( new ActionListener() // anonymous inner class
                        {
                            // process ImageButton event
                              public void actionPerformed( ActionEvent event )
                              {
                                if(event.getActionCommand().equals("+"))
                                {
                                        output.setVisible( true );
                                        ImageButton.setText("-");
                                }
                                else
                                {
                                        output.setVisible( false );
                                        ImageButton.setText("+");
                                }
                              } // end method actionPerformed
                         } // end anonymous inner class
                        );
                       ImageButton3.addActionListener( new ActionListener() // anonymous inner class
                       {
                          // process ImageButton3 event
                          public void actionPerformed( ActionEvent event )
                          {
                                if(event.getActionCommand().equals("+"))
                                {
                                        output2.setVisible( true );
                                        ImageButton3.setText("-");
                                }
                                else
                                {
                                        output2.setVisible( false );
                                        ImageButton3.setText("+");
                                }
                          } // end method actionPerformed
                       } // end anonymous inner class
                       );

                        /******************6lowpan details in packet analysis***************************/
		if(ZM.protocol.equals("6LoWPAN") && terget.getValueAt(row,4).equals("Data")){
                        Btn6lowpan.addActionListener( new ActionListener() // anonymous inner class
                        {
                              // process ImageButton3 event
                              public void actionPerformed( ActionEvent event )
                              {
                                if(event.getActionCommand().equals("+"))
                                {
                                        info6lowpan.setVisible( true );
                                        BtnICMP.setVisible( true );
                                        labelICMP.setVisible( true );
                                        //infoICMP.setVisible( true );
                                        Btn6lowpan.setText("-");
                                }
                                else
                                {
                                        info6lowpan.setVisible( false );
                                        BtnICMP.setVisible( false );
                                        labelICMP.setVisible( false );
                                        infoICMP.setVisible( false );
                                        Btn6lowpan.setText("+");
										BtnICMP.setText("+");
                                }
                              } // end method actionPerformed
                        } // end anonymous inner class
                        );
                        BtnICMP.addActionListener( new ActionListener() // anonymous inner class
                        {
                              // process ImageButton3 event
                              public void actionPerformed( ActionEvent event )
                              {
                                if(event.getActionCommand().equals("+"))
                                {
                                        infoICMP.setVisible( true );
                                        BtnICMP.setText("-");
                                }
                                else
                                {
                                        infoICMP.setVisible( false );
                                        BtnICMP.setText("+");
                                }
                              } // end method actionPerformed
                        } // end anonymous inner class
                        );}
                        blockEvents2 = false;
                    }
                }
            }
	//---------------Firstselect-------------//
}
