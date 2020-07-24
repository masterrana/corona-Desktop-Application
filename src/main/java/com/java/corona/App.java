package com.java.corona;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Hello world!
 *
 */
public class App 
{
	
	public static String  getHtmlData(String c) throws IOException {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("<html>" +
                "<body style='text-align:center;color:red;'>");
		sb.append(c.toUpperCase()+"<br>");
		/*
		 * System.out.println( "You are inside data...!" );
		 */	
	
		String url ="https://www.worldometers.info/coronavirus/country/"+ c +"/";
		Document doc =Jsoup.connect(url).get();
		
		//System.out.println(doc.title());
		
		//#maincounter-wrap
		
		Elements elements= doc.select("#maincounter-wrap");
	//	Elements elements2 = doc.getElementsByTag("img");
		//System.out.println(elements2.html());
		
		//System.out.println(elements.html());
		
		elements.forEach((e) -> {
//			System.out.println(e.html());
//			System.out.println();
//			System.out.println();
			
			  String text = e.select("h1").text();
			  String count = e.select(".maincounter-number>span").text();
			//  System.out.println(text +" : "+ count );
			  
			  sb.append(text).append(" : ").append(count).append("<br>");
			
		});
		
		sb.append("</body>" +
	                "</html>");
		return sb.toString();
	
	}
	
	
    public static void main( String[] args ) throws IOException
    {
//        System.out.println( "Hello World!" );
//        
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter country: ");
//        String co = sc.nextLine();
//        
//       System.out.println(getHtmlData(co));
    	
    	//Creating Design 
    	
    	JFrame root = new	JFrame("Deatils of Country");
    	root.setSize(500,500);
    	
    	Font  f = new Font("Poppins",Font.BOLD,30);
    	
    	//text field
    	
    	JTextField field= new JTextField();
    	
    	//label
    	
    	JLabel label = new JLabel();
    	field.setFont(f);
    	field.setHorizontalAlignment(SwingConstants.CENTER);
    	label.setFont(f);
    	label.setHorizontalAlignment(SwingConstants.CENTER);
    	
    	
    	//button
    	
    	JButton button = new JButton("Get");
    	
    	
    	button.addActionListener( evetn ->{
    		
    	try {
    		String 	minData =field.getText();
    		String result = getHtmlData(minData);
    		label.setText(result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	});
    	
    	 button.setFont(f);
    	 root.setLayout(new BorderLayout());
    	 
         root.add(field, BorderLayout.NORTH);
         root.add(label, BorderLayout.CENTER);
         root.add(button, BorderLayout.SOUTH);
         
         root.setVisible(true);
    }
}
