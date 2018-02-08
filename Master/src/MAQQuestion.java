import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class MAQQuestion extends JFrame {

	private JPanel contentPane;
	private JLabel textA;
	private JLabel textB;
	private JLabel textC;
	private JLabel textD;
	private int correctAnswers;
	private CorrectWindow cw=null;
	private WrongWindow ww=null;
	private String userAnswer = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MAQQuestion frame = new MAQQuestion(null,null,null,0,0);
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
	public MAQQuestion(String quizTitle, Question question, Quiz quiz, int questionCounter, int pastCorrectAnswers) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);	

		correctAnswers = pastCorrectAnswers;
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitle.setText(quizTitle);
		
		JLabel lblAnswer = new JLabel("Answer");
		lblAnswer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		MultipleAnswerQuestion tempQuestion = (MultipleAnswerQuestion) question;
		String[] tempPoss = tempQuestion.getPossibleAnswers();
		
		textA = new JLabel();
		textA.setText(tempPoss[0]);
		JCheckBox radioA = new JCheckBox("");
		
		textB = new JLabel();
		textB.setText(tempPoss[1]);
		JCheckBox radioB = new JCheckBox("");
		
		textC = new JLabel();
		textC.setText(tempPoss[2]);
		JCheckBox radioC = new JCheckBox("");
		
		textD = new JLabel();
		textD.setText(tempPoss[3]);
		JCheckBox radioD = new JCheckBox("");
						
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {					
				if(radioA.isSelected()) {
					userAnswer += textA.getText() + " ";
				}
				if(radioB.isSelected()) {
					userAnswer += textB.getText() + " ";
				}
				if(radioC.isSelected()) {
					userAnswer += textC.getText() + " ";
				}
				if(radioD.isSelected()) {
					userAnswer += textD.getText() + " ";
				}	
						
				if(tempQuestion.getAnswer().equals(userAnswer)) {
					cw = new CorrectWindow();		
					correctAnswers++;
					dispose();
				}else {
					ww = new WrongWindow(tempQuestion.getAnswer());
					dispose();						
				}
				try {
					Question tempQuestion = quiz.getQuestion(questionCounter);
					switch(tempQuestion.getClassName()) {
					case "Question":
						SAQuestion saq = new SAQuestion(quizTitle, tempQuestion, quiz, questionCounter+1, correctAnswers);
						saq.setVisible(true);					
						break;
					case "MultipleChoiceQuestion":
						MCQQuestion mcq = new MCQQuestion(quizTitle, tempQuestion, quiz, questionCounter+1, correctAnswers);		
						mcq.setVisible(true);
						break;
					case "MultipleAnswerQuestion":
						MAQQuestion maq = new MAQQuestion(quizTitle, tempQuestion, quiz, questionCounter+1, correctAnswers);	
						maq.setVisible(true);
						break;					
					} 
				}catch(IndexOutOfBoundsException ioobe) {
					ScoreWindow sw = new ScoreWindow(quizTitle, correctAnswers, quiz.getSize());
					sw.setVisible(true);
				}
				if(tempQuestion.getAnswer().equals(userAnswer)) {				
					cw.setVisible(true);
					dispose();
				}else {
					ww.setVisible(true);	
					dispose();						
				}
			}
		});
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JLabel lblQ = new JLabel("Q");
		lblQ.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQ.setText(tempQuestion.askQuestion());
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(radioB)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textB, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(radioD, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textD, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(radioC, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textC, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblTitle)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(radioA)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textA, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE))
						.addComponent(lblAnswer)
						.addComponent(lblQ)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnExit)
							.addPreferredGap(ComponentPlacement.RELATED, 292, Short.MAX_VALUE)
							.addComponent(btnCheck)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(10))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitle)
					.addGap(18)
					.addComponent(lblQ)
					.addGap(40)
					.addComponent(lblAnswer)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(radioA)
						.addComponent(textA))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(radioB)
						.addComponent(textB))
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(radioC, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(textC))
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textD)
						.addComponent(radioD, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExit)
						.addComponent(btnCheck))
					.addGap(14))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
