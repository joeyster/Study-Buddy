import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoadWindow extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private Quiz tempQuiz = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadWindow frame = new LoadWindow();
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
	public LoadWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 170);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);		
		
		int correctAnswers = 0;
		
		JLabel lblLoadQuiz = new JLabel("Load Quiz");
		lblLoadQuiz.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int questionCounter = 0;
				String fileName = (String) comboBox.getSelectedItem();
				try {
					FileInputStream fis = new FileInputStream("./quizzes/"+ fileName);
					ObjectInputStream ois = new ObjectInputStream(fis);
					tempQuiz = (Quiz) ois.readObject();					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (ClassNotFoundException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				Question tempQuestion = tempQuiz.getQuestion(questionCounter);
				switch(tempQuestion.getClassName()) {
				case "Question":
					SAQuestion saq = new SAQuestion(fileName, tempQuestion, tempQuiz, questionCounter+1, correctAnswers);
					saq.setVisible(true);
					break;
				case "MultipleChoiceQuestion":
					MCQQuestion mcq = new MCQQuestion(fileName, tempQuestion, tempQuiz, questionCounter+1, correctAnswers);
					mcq.setVisible(true);					
					break;
				case "MultipleAnswerQuestion":
					MAQQuestion maq = new MAQQuestion(fileName, tempQuestion, tempQuiz, questionCounter+1, correctAnswers);
					maq.setVisible(true);					
					break;
				}
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		final File folder = new File("./quizzes/");
		ArrayList<String> filesAL = listFilesForFolder(folder);
		String[] filesArr = new String[filesAL.size()];
		filesArr = filesAL.toArray(filesArr);
		
		comboBox = new JComboBox(filesArr);
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(369, Short.MAX_VALUE)
					.addComponent(btnBack))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(112)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnLoad))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(61)
							.addComponent(lblLoadQuiz)
							.addPreferredGap(ComponentPlacement.RELATED, 52, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(120, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(lblLoadQuiz)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLoad)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addComponent(btnBack))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	//creates ArrayList of files inside folder with quizzes
	public ArrayList<String> listFilesForFolder(final File folder) {
		ArrayList<String> filesAL = new ArrayList<>();
	    for (final File fileEntry : folder.listFiles()) {
	            filesAL.add(fileEntry.getName());
	    }
	    return filesAL;
	}
}
