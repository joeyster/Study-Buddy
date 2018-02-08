import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

public class WrongWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblIncorrect;
	private JButton okButton;
	private JLabel lblAnswer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WrongWindow dialog = new WrongWindow(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WrongWindow(String answer) {
		setBounds(100, 100, 450, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblIncorrect = new JLabel("Incorrect.");
			lblIncorrect.setBounds(143, 73, 186, 45);
			lblIncorrect.setFont(new Font("Tahoma", Font.BOLD, 37));
			lblIncorrect.setForeground(new Color(255, 0, 0));
		}
		contentPanel.setLayout(null);
		contentPanel.add(lblIncorrect);
		{
			okButton = new JButton("Next");
			okButton.setBounds(193, 240, 90, 23);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			lblAnswer = new JLabel("Answer: " + answer);
			lblAnswer.setBounds(143, 129, 186, 104);
			lblAnswer.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblAnswer);
		}
		contentPanel.add(okButton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}

}
