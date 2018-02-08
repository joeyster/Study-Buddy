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

public class CorrectWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblCorrect;
	private JButton okButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CorrectWindow dialog = new CorrectWindow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CorrectWindow() {
		setBounds(100, 100, 450, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblCorrect = new JLabel("Correct!");
			lblCorrect.setBounds(143, 73, 150, 45);
			lblCorrect.setFont(new Font("Tahoma", Font.BOLD, 37));
			lblCorrect.setForeground(new Color(0, 255, 0));
		}
		contentPanel.setLayout(null);
		contentPanel.add(lblCorrect);
		{
			okButton = new JButton("Next");
			okButton.setBounds(170, 240, 94, 23);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		contentPanel.add(okButton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}

}
