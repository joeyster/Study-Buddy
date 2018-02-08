import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.event.ActionEvent;


public class StartWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartWindow frame = new StartWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public StartWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblStudyBuddy = new JLabel("   Study Buddy");
		lblStudyBuddy.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow mw = new MainWindow();
				mw.setVisible(true);
			}
		});
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel label = new JLabel("");
		contentPane.add(label);
		contentPane.add(lblStudyBuddy);
		
		JLabel label_1 = new JLabel("");
		contentPane.add(label_1);
		contentPane.add(btnCreate);
		
		JButton btnNewButton = new JButton("Load");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadWindow lw = new LoadWindow();
				lw.setVisible(true);
			}		
		});
		contentPane.add(btnNewButton);
		contentPane.add(btnExit);
	}

}
