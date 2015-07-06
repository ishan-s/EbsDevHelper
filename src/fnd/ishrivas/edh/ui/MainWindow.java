package fnd.ishrivas.edh.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;

import com.jgoodies.forms.factories.FormFactory;

import fnd.ishrivas.edh.common.Const;
import fnd.ishrivas.edh.common.JTextAreaOutputStream;
import fnd.ishrivas.edh.main.EBizInstance122x;
import fnd.ishrivas.edh.main.SSHController;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

import net.neoremind.sshxcute.core.Result;
import net.neoremind.sshxcute.exception.TaskExecFailException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.PrintStream;
import javax.swing.BoxLayout;

public class MainWindow {

	private JFrame frame;
	private JTextField txtAppsuser;
	private JTextField txtAppspassword;
	private JTextField txtWeblogicadminuser;
	private JTextField txtWeblogicadminpassword;
	private JTextField txtTwotask;
	private JTextField txtHost;
	private JTextField txtMtusername;
	private JTextField txtMtpassword;
	private JTextField textField;
	
	private EBizInstance122x ebs;
	
	private static JTextAreaOutputStream out;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
					window.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		
		JPanel panelCreds = new JPanel();
		frame.getContentPane().add(panelCreds, BorderLayout.NORTH);
		panelCreds.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("213px:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("22px"),
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("22px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("22px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("22px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("22px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("22px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("22px"),
				RowSpec.decode("22px"),
				RowSpec.decode("22px"),
				RowSpec.decode("22px"),
				RowSpec.decode("22px"),}));
		
		JLabel lblEbsInstanceDetails = new JLabel("EBS Instance Details");
		panelCreds.add(lblEbsInstanceDetails, "1, 2, 9, 1, center, default");
		
		JLabel lblHost = new JLabel("Host");
		panelCreds.add(lblHost, "3, 4, right, fill");
		
		txtHost = new JTextField();
		panelCreds.add(txtHost, "5, 4, fill, default");
		txtHost.setColumns(10);
		
		JLabel lblMiddleTierUsername = new JLabel("Middle Tier: Username");
		panelCreds.add(lblMiddleTierUsername, "3, 6, right, fill");
		
		txtMtusername = new JTextField();
		panelCreds.add(txtMtusername, "5, 6, fill, default");
		txtMtusername.setColumns(10);
		
		JLabel lblMiddleTierPassword = new JLabel("Middle Tier: Password");
		panelCreds.add(lblMiddleTierPassword, "3, 8, right, fill");
		
		txtMtpassword = new JTextField();
		panelCreds.add(txtMtpassword, "5, 8, fill, default");
		txtMtpassword.setColumns(10);
		
		JLabel lblAppsUser = new JLabel("Apps: User");
		panelCreds.add(lblAppsUser, "3, 10, right, default");
		
		txtAppsuser = new JTextField();
		panelCreds.add(txtAppsuser, "5, 10, fill, default");
		txtAppsuser.setColumns(10);
		
		JLabel lblAppsPassword = new JLabel("Apps: Password");
		panelCreds.add(lblAppsPassword, "3, 12, right, default");
		
		txtAppspassword = new JTextField();
		panelCreds.add(txtAppspassword, "5, 12, fill, default");
		txtAppspassword.setColumns(10);
		
		JLabel lblWeblogicAdminUser = new JLabel("Weblogic: Admin User");
		panelCreds.add(lblWeblogicAdminUser, "3, 14, right, default");
		
		txtWeblogicadminuser = new JTextField();
		panelCreds.add(txtWeblogicadminuser, "5, 14, fill, default");
		txtWeblogicadminuser.setColumns(10);
		
		JLabel lblWeblogicAdminUser_1 = new JLabel("Weblogic: Admin User Password");
		panelCreds.add(lblWeblogicAdminUser_1, "3, 16, right, default");
		
		txtWeblogicadminpassword = new JTextField();
		panelCreds.add(txtWeblogicadminpassword, "5, 16, fill, default");
		txtWeblogicadminpassword.setColumns(10);
		
		JLabel lblTwoTask = new JLabel("Two Task");
		panelCreds.add(lblTwoTask, "3, 18, right, default");
		
		txtTwotask = new JTextField();
		panelCreds.add(txtTwotask, "5, 18, fill, default");
		txtTwotask.setColumns(10);
		
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Result res;
				ebs = new EBizInstance122x(
						txtHost.getText(),
						txtMtusername.getText(),
						txtMtpassword.getText(),
						txtAppsuser.getText(),
						txtAppspassword.getText(),
						txtWeblogicadminuser.getText(),
						txtWeblogicadminpassword.getText(),
						txtTwotask.getText());
				
			try {
				res = SSHController.setup(ebs);
				//txtPaneResult.setText(res.sysout);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			}
		});
		panelCreds.add(btnConnect, "3, 20, 5, 2, fill, fill");
		
		JPanel panelControls = new JPanel();
		frame.getRootPane().setDefaultButton(btnConnect);
		frame.getContentPane().add(panelControls, BorderLayout.CENTER);
		panelControls.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JButton btnBounceAllServers = new JButton("Bounce All Servers");
		btnBounceAllServers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Result res;
				try {
					res = SSHController.startBounce(Const.SCRIPT_TYPE_BOUNCE_ALL);
					//txtPaneResult.setText(res.sysout);
				} catch (TaskExecFailException e1) {
					e1.printStackTrace();
				}
			}
		});
		panelControls.add(btnBounceAllServers, "3, 2, 3, 1");
		
		textField = new JTextField();
		panelControls.add(textField, "3, 4, fill, default");
		textField.setColumns(10);
		
		JButton btnBounceManagedServer = new JButton("Bounce Managed Server");
		panelControls.add(btnBounceManagedServer, "5, 4");
	}

}
