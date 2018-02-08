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


public class ShortAnswer extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShortAnswer frame = new ShortAnswer(null);
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
	public ShortAnswer(Quiz quiz) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblQuestion = new JLabel("Question");
		lblQuestion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JTextArea questionTextArea = new JTextArea();
		
		JLabel lblAnswer = new JLabel("Answer");
		lblAnswer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JTextArea answerTextArea = new JTextArea();
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Question q = new Question(questionTextArea.getText(), answerTextArea.getText());
				quiz.addQuestion(q);
				System.out.println("Done adding!");
				dispose();
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnExit)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSave))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblQuestion))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(questionTextArea, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblAnswer))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(answerTextArea, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblQuestion)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(questionTextArea, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblAnswer)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(answerTextArea, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnExit))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}

}
