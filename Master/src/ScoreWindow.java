import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScoreWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScoreWindow frame = new ScoreWindow(null,0,0);
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
	public ScoreWindow(String quizTitle, int correctAnswers, int questionCounter) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblQuiztitle = new JLabel("quizTitle");
		lblQuiztitle.setBounds(136, 27, 216, 45);
		lblQuiztitle.setFont(new Font("Tahoma", Font.BOLD, 37));
		lblQuiztitle.setText(quizTitle);
		contentPane.add(lblQuiztitle);
		
		JLabel lblScore = new JLabel("Score: ");
		lblScore.setBounds(136, 113, 216, 22);
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblScore.setText("Score: " + correctAnswers + "/" + questionCounter);
		contentPane.add(lblScore);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(136, 165, 159, 23);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnExit);
	}

}
