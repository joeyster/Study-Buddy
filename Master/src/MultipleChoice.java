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

public class MultipleChoice extends JFrame {

	private JPanel contentPane;
	private JTextField textA;
	private JTextField textB;
	private JTextField textC;
	private JTextField textD;
	private JRadioButton radioA;
	private JRadioButton radioB;
	private JRadioButton radioC;
	private JRadioButton radioD;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MultipleChoice frame = new MultipleChoice(null);
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
	public MultipleChoice(Quiz quiz) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		
		JLabel lblQuestion = new JLabel("Question");
		lblQuestion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JTextArea textArea = new JTextArea();
		
		JLabel lblAnswer = new JLabel("Answer");
		lblAnswer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		
		textA = new JTextField();
		textA.setColumns(10);
		radioA = new JRadioButton("");
		radioA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioB.setSelected(false);
				radioC.setSelected(false);
				radioD.setSelected(false);
			}
		});
		
		textB = new JTextField();
		textB.setColumns(10);
		radioB = new JRadioButton("");
		radioB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioA.setSelected(false);
				radioC.setSelected(false);
				radioD.setSelected(false);
			}
		});
		
		textC = new JTextField();
		textC.setColumns(10);
		radioC = new JRadioButton("");
		radioC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioB.setSelected(false);
				radioA.setSelected(false);
				radioD.setSelected(false);
			}
		});
		
		textD = new JTextField();
		textD.setColumns(10);
		radioD = new JRadioButton("");
		radioD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioA.setSelected(false);
				radioC.setSelected(false);
				radioB.setSelected(false);
			}
		});
						
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {						
				String[] possibleAnswers = {textA.getText(), textB.getText(), textC.getText(), textD.getText()};
				MultipleChoiceQuestion mcq = null;				
				if(radioA.isSelected()) {
					System.out.println("activated A");
					mcq = new MultipleChoiceQuestion(textArea.getText(), textA.getText(), possibleAnswers.clone());
				}else if(radioB.isSelected()) {
					System.out.println("activated B");
					mcq = new MultipleChoiceQuestion(textArea.getText(), textB.getText(), possibleAnswers.clone());				
				}else if(radioC.isSelected()) {
					System.out.println("activated C");
					mcq = new MultipleChoiceQuestion(textArea.getText(), textC.getText(), possibleAnswers.clone());			
				}else if(radioD.isSelected()) {
					System.out.println("activated D");
					mcq = new MultipleChoiceQuestion(textArea.getText(), textD.getText(), possibleAnswers.clone());			
				}				
				quiz.addQuestion(mcq);
				System.out.println("Done adding!");
				dispose();
			}
		});
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(radioD, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textD, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(radioC, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textC, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnExit)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnSave))
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblQuestion)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(radioA)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textA, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
						.addComponent(lblAnswer)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(radioB)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textB, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblQuestion)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblAnswer)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(radioA))
					.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(radioB))
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(radioC, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSave)
								.addComponent(btnExit)))
						.addComponent(radioD, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(14))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
