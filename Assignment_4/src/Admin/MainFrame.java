package Admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;

public class MainFrame {

	private JFrame frmRestaurentManagement;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmRestaurentManagement.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRestaurentManagement = new JFrame();
		frmRestaurentManagement.setSize(new Dimension(700, 500));
		frmRestaurentManagement.setTitle("Restaurent Management");
		frmRestaurentManagement.setBounds(0, 0, 700, 500);
		frmRestaurentManagement.setLocationRelativeTo(null);
		frmRestaurentManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRestaurentManagement.getContentPane().setLayout(null);
	}
}
