import java.awt.BorderLayout;
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
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;


public class SAQuestion extends JFrame {

	private JPanel contentPane;
	private int correctAnswers;
	private CorrectWindow cw=null;
	private WrongWindow ww=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SAQuestion frame = new SAQuestion(null,null,null,0,0);
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
	public SAQuestion(String quizTitle, Question tempQuestion, Quiz quiz, int questionCounter, int pastCorrectAnswers) {		
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
		
		JTextArea answerTextArea = new JTextArea();
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				if(tempQuestion.check(answerTextArea.getText())) {
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
				if(tempQuestion.check(answerTextArea.getText())) {				
					cw.setVisible(true);
					dispose();
				}else {
					ww.setVisible(true);	
					dispose();						
				}
			}
		});
		
		JLabel lblQ = new JLabel("Q");
		lblQ.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQ.setText(tempQuestion.askQuestion());
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(btnExit)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnCheck))
						.addComponent(answerTextArea, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAnswer)
					.addContainerGap(361, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitle)
					.addContainerGap(351, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblQ)
					.addContainerGap(368, Short.MAX_VALUE))
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
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(answerTextArea, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExit)
						.addComponent(btnCheck))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	

}
