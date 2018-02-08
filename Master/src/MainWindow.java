import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.eclipse.core.runtime.Path;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;

import java.util.ArrayList;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtFileName;
	private JComboBox fileComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
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
	public MainWindow() {			
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		Quiz quiz = new Quiz();
		
		JLabel titleLabel = new JLabel("Study Buddy");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblWhichQuiz = new JLabel("Which quiz?");
		lblWhichQuiz.setFont(new Font("Tahoma", Font.PLAIN, 14));	
		
		JButton btnNewFile = new JButton("Add");
		btnNewFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileComboBox.addItem(txtFileName.getText() + ".dat");
				fileComboBox.setSelectedItem(txtFileName.getText() + ".dat");
			}
		});
		
		final File folder = new File("./quizzes/");
		ArrayList<String> filesAL = listFilesForFolder(folder);
		String[] filesArr = new String[filesAL.size()];
		filesArr = filesAL.toArray(filesArr);
		
		fileComboBox = new JComboBox(filesArr);
		
		JLabel typeLabel = new JLabel("Type of Question");
		typeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Short Answer", "Multiple Choice", "Multiple Answer"}));
		
		JButton loadBtn = new JButton("Exit and Save");
		loadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = txtFileName.getText() + ".dat";
				System.out.println(fileName);
				try {				
				System.out.println("Saving onto " + fileName);
				FileOutputStream fos = new FileOutputStream("./quizzes/" + fileName, true);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(quiz);
				System.out.println("written");
				oos.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				System.out.println("Done writing!");				
				dispose();
			}
		});
		
		JButton saveBtn = new JButton("Back");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				dispose();
			}
		});
		
		JButton btnCreateQuestion = new JButton("Create question");
		btnCreateQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String typeName = (String)comboBox.getSelectedItem();
				String fileName = (String)fileComboBox.getSelectedItem();
				if(typeName.equals("Short Answer")) {
					ShortAnswer sa = new ShortAnswer(quiz);
					sa.setVisible(true);
				}else if(typeName.equals("Multiple Choice")) {	
					MultipleChoice mc = new MultipleChoice(quiz);
					mc.setVisible(true);		
					
				}else if(typeName.equals("Multiple Answer")) {
					MultipleAnswer ma = new MultipleAnswer(quiz);
					ma.setVisible(true);
				}	
				
				fileComboBox.setEnabled(false);
			}
		});

		

		
		txtFileName = new JTextField();
		txtFileName.setToolTipText("");
		txtFileName.setColumns(10);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(157)
							.addComponent(titleLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblWhichQuiz)))
					.addGap(166))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(fileComboBox, 0, 129, Short.MAX_VALUE)
							.addGap(61))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtFileName, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnNewFile)))
					.addGap(224))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(typeLabel)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnCreateQuestion)))
							.addPreferredGap(ComponentPlacement.RELATED, 158, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(saveBtn)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(loadBtn)
							.addGap(8)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(titleLabel)
					.addGap(8)
					.addComponent(lblWhichQuiz)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewFile)
						.addComponent(txtFileName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(fileComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(typeLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCreateQuestion))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(saveBtn)
						.addComponent(loadBtn))
					.addContainerGap(30, Short.MAX_VALUE))
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

