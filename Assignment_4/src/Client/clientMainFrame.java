package Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Rectangle;

public class clientMainFrame {

	private JFrame frame;
	String name;
	
	clientMainFrame(String x)
	{
		name = x;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Launch the application.
	 */
	
	public class TestPanel extends JPanel {

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	        int w = getWidth();
	        int h = getHeight();
	        Color color1 = new Color(242, 166, 90);
	        Color color2 = new Color(119, 47, 26);
	        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
	        g2d.setPaint(gp);
	        g2d.fillRect(0, 0, w, h);
	    }
	}
	
	public class ImagePanel extends JPanel{

	    private BufferedImage image;

	    public ImagePanel() {
	       try {                
	          image = ImageIO.read(new File("C:\\Users\\Aditya J Sawant\\Desktop\\img4.jpg"));
	       } catch (IOException ex) {
	            // handle exception...
	       }
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
	    }

	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clientMainFrame window = new clientMainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public clientMainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(new Rectangle(0, 0, 600, 400));
		frame.setBounds(0, 0, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_2 = new TestPanel();
		panel_2.setBounds(176, 11, 209, 339);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_1 = new ImagePanel();
		panel_1.setBounds(0, 0, 600, 400);
		frame.getContentPane().add(panel_1);
			
		JLabel lblNewLabel_1 = new JLabel("Welcome");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 11, 189, 25);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(name);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 35, 189, 25);
		panel_2.add(lblNewLabel_2);
		
		JButton btn_homeDelivery = new JButton();
		btn_homeDelivery.setBounds(350,150,150,30);
		btn_homeDelivery.setText("Home Delivery");
		btn_homeDelivery.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				HomeDelivery homeDelivery = new HomeDelivery();
				homeDelivery.homeDeliveryFrame(frame);
			}
		});
		
		JButton btn_chat = new JButton();
		btn_chat.setBounds(350,200,150,30);
		btn_chat.setText("Chat");
		btn_chat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				CustomerServer custServer = new CustomerServer();
				custServer.startThread();
				//chatWindow(frame);
			}
		});
		
		JButton btn_back = new JButton();
		btn_back.setBounds(350,250,150,30);
		btn_back.setText("Back");
		btn_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
		});
	}
}
