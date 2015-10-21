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
 * Statistics : View some statistics.
 */

//import libraries
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import Classes.Support.NtpMessage;

public class Statistics extends JDialog 
{
        //Class Attribute
        boolean start;
        LinkedList<timerImpl> listOfArrivalTime;
        float lenth;
        int numberOfRA;
        int numberOfRS;
        int numberOfER;
        int numberOfERq;
        int numberOfINH;
        FlowLayout layout;
        String source;
        String Address;
        LinkedList<moteTimeLineImpl> listOfTimeLine;
        //-----------------------------------------------//

        //Constructor
	public Statistics(boolean Start,LinkedList<timerImpl> ListOfArrivalTime,float Lenth,int NumberOfRA,
                          int NumberOfRS,int NumberOfER,int NumberOfERq,int NumberOfINH,String Source,String address,LinkedList<moteTimeLineImpl> ListOfTimeLine)
	{
  		JFrame parent = new JFrame("Summary of Statistics");//main frame
		JPanel mainPanel = new JPanel();
		JPanel Panelnumber2 = new JPanel();
  		JPanel Panelnumber3 = new JPanel();
  		JPanel Panelnumber4 = new JPanel();
  		JPanel Panelnumber5 = new JPanel();
  		JPanel filePanel = new JPanel();//panel that contain the information about the file
  		JPanel Panelnumber7 = new JPanel();
  		JPanel Panelnumber8 = new JPanel();
  		JPanel Panelnumber9 = new JPanel();
  		JPanel Panelnumber10 = new JPanel();
  		JPanel timePanel = new JPanel();//panel that contain time information
  		JPanel Panelnumber12 = new JPanel();
  		JPanel capturePanel = new JPanel();//panel that contain capture information
  		JPanel Panelnumber14 = new JPanel();
  		JPanel trafficPanel = new JPanel();//panel that contain traffic information
                start=Start;
                listOfArrivalTime=ListOfArrivalTime;
                lenth=Lenth;
                numberOfRA=NumberOfRA;
                numberOfRS=NumberOfRS;
                numberOfER=NumberOfER;
                numberOfERq=NumberOfERq;
                numberOfINH=NumberOfINH;
                layout = new FlowLayout();
                source=Source;
                Address=address;
                listOfTimeLine=ListOfTimeLine;
		Border loweredetched=BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		filePanel.setBorder(BorderFactory.createTitledBorder(loweredetched, "File"));
		timePanel.setBorder(BorderFactory.createTitledBorder(loweredetched, "Time"));
		capturePanel.setBorder(BorderFactory.createTitledBorder(loweredetched, "Capture"));
		trafficPanel.setBorder(BorderFactory.createTitledBorder(loweredetched, "Traffic"));
		Image icon = Toolkit.getDefaultToolkit().getImage("image/iconred.gif");
  		parent.setIconImage(icon);
		parent.setResizable(false);
		Panelnumber4.setBackground(new Color(209, 209, 209));
  		Panelnumber5.setBackground(new Color(209, 209, 209));
  		filePanel.setBackground(new Color(209, 209, 209));
  		Panelnumber10.setBackground(new Color(209, 209, 209));
  		timePanel.setBackground(new Color(209, 209, 209));
  		Panelnumber12.setBackground(new Color(209, 209, 209));
  		capturePanel.setBackground(new Color(209, 209, 209));
  		Panelnumber14.setBackground(new Color(209, 209, 209));
  		trafficPanel.setBackground(new Color(209, 209, 209));
		final DecimalFormat DPlaces = new DecimalFormat("0.000");
  		JTextArea name = new JTextArea();
  		name.setEditable( false );
  		name.setBackground(new Color(209, 209, 209)); 	
  		JTextArea packrttime = new JTextArea();
  		packrttime.setEditable( false );
  		packrttime.setBackground(new Color(209, 209, 209));	
  		JTextArea Interface = new JTextArea();
  		Interface.setEditable( false );
  		Interface.setBackground(new Color(209, 209, 209)); 	
  		JTextArea number = new JTextArea();
  		number.setEditable( false );
  		number.setBackground(new Color(209, 209, 209));
		//buttons
		JButton save = new JButton ("save file");
  		JButton open = new JButton ("open file");
  		JButton folder = new JButton ("open folder"); 
		//variables
		double duration = 0;
		int ra=0;//router advertisement number
		double ti1 = 0;
		double ti2 = 0;
		double TotalNumberOfPacket = 0;
		double AverageNoOfPacPerMSec = 0;
		double lenth2 = 0;
		double bytes = 0;
		double bytes2 = 0;
		double AvergeBytes = 0;
		double AvergeMBits = 0;
		double bec1 = 0;
		double data1 = 0;
		double ack1 = 0;
		double ipnoNH=0;
		double echoreply=0;
		if(start == true)
		{
			timerImpl time1 = listOfArrivalTime.getFirst();
			timerImpl time2 = listOfArrivalTime.getLast();
			ti1 = time1.getTimes();
			ti2 = time2.getTimes();
			TotalNumberOfPacket = time2.getFrameNo() + 1;
			duration =  (time2.getTimes())-(time1.getTimes());
			AverageNoOfPacPerMSec = (TotalNumberOfPacket/duration);
			bytes = (int) lenth;
			bytes2 = lenth;
			lenth2 = bytes2/TotalNumberOfPacket;
			AvergeBytes = (bytes2/duration);
			AvergeMBits = AvergeBytes/131072;
			bec1 = (numberOfRA/TotalNumberOfPacket)*100;
			data1 = (numberOfRS/TotalNumberOfPacket)*100;
			ack1 = (numberOfERq/TotalNumberOfPacket)*100;
			echoreply=(numberOfER/TotalNumberOfPacket)*100;
			ipnoNH=(numberOfINH/TotalNumberOfPacket)*100;
		}
		Calendar cal = Calendar.getInstance();
  		String date = cal.getTime().toString();
		LinkedList<timerImpl> x1 =  new LinkedList<timerImpl>();
		final timerImpl y1 =  new timerImpl();
		y1.setArrivalTime(date);
		String filenmae = "ZM_"+y1.getDay()+"_"+y1.getMonth()+"_"+y1.getYear()+"__"+y1.getDayn()+"__"+y1.getTime();
		String now=now();
		// remove all sign that not allowed in file name
		now = now.replaceAll(":", "");      				
		now = now.replaceAll(" ", ""); 
    		String n = "ZM_"+now;
		int i = 0;
		for(moteTimeLineImpl list:listOfTimeLine)
		{
			i++;
		}
		final String name2 = n;
		int j;
		if(i == 0)
			j=0;
		else
			j=i-1;
		final int NumberOfNode = j;
		final double fp = ti1;
		final double lp = ti2;
		final double du = duration;	
		final double TNOP = TotalNumberOfPacket;
		final double ANOP = AverageNoOfPacPerMSec;
		final double APS = lenth2;
		final double TNOB = bytes;
		final double ANOB = AvergeBytes;
		final double ANMB = AvergeMBits;
		final double NOB = bec1;
		final double NOD = data1;
		final double NOA = ack1;
		final double NER=echoreply;
		final double IPnNH=ipnoNH;
		mainPanel.setLayout( new BorderLayout());
		mainPanel.add(Panelnumber2,BorderLayout.NORTH);
  		mainPanel.add(Panelnumber3,BorderLayout.CENTER);
  		mainPanel.add(Panelnumber4,BorderLayout.SOUTH);
  		parent.getContentPane().add(mainPanel);
  		Panelnumber2.setLayout( new BorderLayout() );
  		Panelnumber2.add(Panelnumber5,BorderLayout.NORTH);
  		Panelnumber2.add(filePanel,BorderLayout.CENTER);
  		Panelnumber5.setLayout( layout );
  		filePanel.setLayout( layout );
  		name.setText("Name: 			"+n);  	
  		filePanel.add(name);
  		Panelnumber3.setLayout( new BorderLayout() );
  		Panelnumber3.add(Panelnumber7,BorderLayout.NORTH);
  		Panelnumber3.add(Panelnumber8,BorderLayout.CENTER);
  		Panelnumber3.add(Panelnumber9,BorderLayout.SOUTH); 	
  		Panelnumber7.setLayout( new BorderLayout() );
  		Panelnumber7.add(Panelnumber10,BorderLayout.NORTH);
  		Panelnumber7.add(timePanel,BorderLayout.CENTER);
  		Panelnumber10.setLayout( layout );
  		timePanel.setLayout( layout );
		packrttime.setText("First Packet: 			"+NtpMessage.timestampToString(ti1)+" \nLast Packet: 			"+NtpMessage.timestampToString(ti2)+" \nDuration: 			"+DPlaces.format(duration)+" Sec");
  		timePanel.add(packrttime);	
  		Panelnumber8.setLayout( new BorderLayout() );
  		Panelnumber8.add(Panelnumber12,BorderLayout.NORTH);
  		Panelnumber8.add(capturePanel,BorderLayout.CENTER);
  		Panelnumber12.setLayout( layout );
  		capturePanel.setLayout( layout );  	
  		Interface.setText("Interface: 			"+source);  	
  		capturePanel.add(Interface);
  		Panelnumber9.setLayout( new BorderLayout() );
  		Panelnumber9.add(Panelnumber14,BorderLayout.NORTH);
  		Panelnumber9.add(trafficPanel,BorderLayout.CENTER);
  		Panelnumber14.setLayout( layout );
  		trafficPanel.setLayout( layout );
  		number.setText("Total Number of Packet: 		"+TotalNumberOfPacket+"\nAverage Number of Packet/Sec:                "+DPlaces.format(AverageNoOfPacPerMSec)+"\nAverage Packet Size: 		"+DPlaces.format(lenth2)+" bytes"
  			       +"\n\nTotal Number of Bytes: 		"+bytes+"\nAverage Number of Bytes/Sec:                   "+DPlaces.format(AvergeBytes)+"\nAverage Number of MBits/Sec:                   "+DPlaces.format(AvergeMBits)
  			       +"\n\nTotal Number of router advertisement:      "+numberOfRA+"	       ratio :     "+DPlaces.format(bec1)+"%\nTotal Number of router solicitation:           "+numberOfRS+"                           ratio :     "+DPlaces.format(data1)+"%\nTotal Number of echo request:	"+numberOfERq+"	       ratio :     "+DPlaces.format(ack1)+"%"+"\nTotal number of echo reply:                       "+numberOfER+"                           ratio :     "+DPlaces.format(echoreply)+"%\nTotal number of IPv6 no next header:        "+numberOfINH+"                           ratio :     "+DPlaces.format(ipnoNH)+"%");
  		trafficPanel.add(number);
  		Panelnumber4.setLayout(new FlowLayout());
  		Panelnumber4.add(save);
  		Panelnumber4.add(open);
  		Panelnumber4.add(folder);
		save.addActionListener( new ActionListener() 
  		{
  			public void actionPerformed(ActionEvent e)
      			{
      				try
      				{        					
      					// define SummaryOfStatistics directory
      					File dir=new File("SummaryOfStatistics");
      					// create SummaryOfStatistics directory in case of not exists
                			if(!dir.exists())
                				dir.mkdir();
                			// create SummaryOfStatistics directory in case of exist but not directory
                			if(!dir.isDirectory())
                 			   dir.mkdir();
               				File packets;
               				//create file with this name ZM_(date with remove all not allowed signs)
                			packets = new File("SummaryOfStatistics/"+name2+".txt");
      					if(!packets.exists())
						packets.createNewFile();
					else
						return ;
					// define the file writer to write on the file
      					FileWriter fstream = new FileWriter(packets,true);
      					// define buffer of writer
      					BufferedWriter out = new BufferedWriter(fstream);
      					// write on the top of file how many packets did the sniffer sniff
      					out.write("File :");
      					out.newLine();
      					out.write("		Name : "+name2);
      					out.newLine();
      					out.write("		Date : "+y1.getDay()+"/"+y1.getMonth()+"/"+y1.getYear());
      					out.newLine();
      					out.write("		Time : "+y1.getTime());
      					out.newLine();
      					out.newLine();
      		  			out.write("Network Information :");
      					out.newLine();
      					out.write("		Coordinator Address : "+Address);
      					out.newLine();
      					out.write("		Number of Node      : "+NumberOfNode);
      					out.newLine();
      					out.write("		Max Depth           : 1");
      					out.newLine();
      		   			out.write("Time :");
      					out.newLine();
      					out.write("		First Packet : "+NtpMessage.timestampToString(fp));
      					out.newLine();
      					out.write("		Last Packet  : "+DPlaces.format(lp));
      					out.newLine();
      					out.write("		Duration     : "+DPlaces.format(du));
      					out.newLine();	
      		     			out.write("Capture :");
      					out.newLine();
      					out.write("		Interface : "+source);
      					out.newLine();
      		     			out.write("Traffic :");
      					out.newLine();
      					out.write("		Total Number of Packet       : "+TNOP);
      					out.newLine();
      					out.write("		Average Number of Packet/mSec: "+DPlaces.format(ANOP));
      					out.newLine();
      					out.write("		Average Packet Size          : "+DPlaces.format(APS)+" bytes");
      					out.newLine();
      					out.newLine();
      					out.write("		Total Number of Bytes       : "+TNOB);
      					out.newLine();
      					out.write("		Average Number of Bytes/Sec  : "+DPlaces.format(ANOB));
      					out.newLine();
      					out.write("		Average Number of MBits/Sec : "+DPlaces.format(ANMB)+" bytes");
      					out.newLine();
      					out.newLine();
      					out.write("		Total Number RA:                "+numberOfRA+"	ratio: "+DPlaces.format(NOB)+"%");
      					out.newLine();
      					out.write("		Total Number RS:                "+numberOfRS+"	ratio: "+DPlaces.format(NOD)+"%");
      					out.newLine();
      					out.write("		Total Number ERequest:          "+numberOfERq+"	ratio: "+DPlaces.format(NOA)+"%");
					out.newLine();
      					out.write("		Total Number EReply:            "+numberOfER+"	ratio: "+DPlaces.format(NER)+"%");
					out.newLine();
      					out.write("		Total Number IP no next header: "+numberOfINH+"	ratio: "+DPlaces.format(IPnNH)+"%");
      					out.newLine();
      		      			out.close();
      				}catch(Exception ex){System.out.println(ex.getMessage());}
      			}
  		});
  		open.addActionListener( new ActionListener() 
  		{
  			public void actionPerformed(ActionEvent e)
      			{
      				try
      				{
                			Desktop.getDesktop().open(new File("SummaryOfStatistics/"+name2+".txt"));        			 
      				}catch(Exception ex){System.out.println(ex.getMessage());
      				JOptionPane.showMessageDialog(null,"Save the file first","File doesn't exist",JOptionPane.INFORMATION_MESSAGE);
      			}
      		
      			}
  		});
  		folder.addActionListener( new ActionListener() 
  		{
  			public void actionPerformed(ActionEvent e)
      			{
      				try
      				{
      					JFileChooser choice  = new JFileChooser();
  					choice.setFileSelectionMode(JFileChooser.FILES_ONLY);
  					int res =choice.showOpenDialog(null);  					
					if(res==JFileChooser.CANCEL_OPTION)   return;
  					File myFile=choice.getSelectedFile();
               				Desktop.getDesktop().open(myFile);        	 
      				}catch(Exception ex){System.out.println(ex.getMessage());
      				JOptionPane.showMessageDialog(null,"No Application is associated with it","Failed to open",JOptionPane.INFORMATION_MESSAGE);
      			}
      		}
  		});
  		parent.setLocation(250, 150);
  		parent.pack(); 
		mainPanel.setVisible(true);
   		parent.setVisible(true);
	}
        //-----------------------------------------------//

        /*******************************************************************/
        /* Function: now                                                   */
        /* Arguments : Nothing                                             */
        /* Return: String                                                  */
        /* This method return the current time and date in special format  */
        /*******************************************************************/
   	public String now() 
   	{
     		final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
     		Calendar cal = Calendar.getInstance();
    		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
    		return sdf.format(cal.getTime());
  	}
        //-----------------------------------------------//
}