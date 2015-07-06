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
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;

import com.jgoodies.forms.factories.FormFactory;

import fnd.ishrivas.edh.common.Const;
import fnd.ishrivas.edh.common.JTextAreaOutputStream;
import fnd.ishrivas.edh.main.EBizInstance122x;
import fnd.ishrivas.edh.main.SSHController;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

import net.neoremind.sshxcute.core.Result;
import net.neoremind.sshxcute.exception.TaskExecFailException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.PrintStream;

import javax.swing.BoxLayout;

import java.awt.Insets;

import javax.swing.JSeparator;
import javax.swing.JScrollBar;

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
	private JTextField txtManagedServerName;
	private JTextArea txtrStatus;
	private JButton btnBounceAllServers;
	private JButton btnBounceManagedServer;
	private JButton btnDisconnect;
	private JButton btnConnect;
	private EBizInstance122x ebs;
	private boolean isConnected = false;
	
	private static JTextAreaOutputStream out;
	private JPanel panelStatus;
	private JSeparator separator;
	private JSeparator separator_1;
	private JLabel lblStatus;
	private JScrollPane scrollPane;

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
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 39, 395, 52, 46, 0};
		gridBagLayout.rowHeights = new int[]{0, 383, 0, 64, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
				
				JPanel panelCreds = new JPanel();
				GridBagConstraints gbc_panelCreds = new GridBagConstraints();
				gbc_panelCreds.anchor = GridBagConstraints.NORTHWEST;
				gbc_panelCreds.insets = new Insets(0, 0, 5, 0);
				gbc_panelCreds.gridwidth = 3;
				gbc_panelCreds.gridx = 2;
				gbc_panelCreds.gridy = 1;
				frame.getContentPane().add(panelCreds, gbc_panelCreds);
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
				
				
				btnConnect = new JButton("Connect");
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
						isConnected = res.isSuccess;
						btnBounceAllServers.setEnabled(isConnected);
						btnBounceManagedServer.setEnabled(isConnected);
						btnConnect.setEnabled(!isConnected);
						btnDisconnect.setEnabled(isConnected);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					}
				});
				panelCreds.add(btnConnect, "3, 20, 2, 2, fill, fill");
				frame.getRootPane().setDefaultButton(btnConnect);
				
				btnDisconnect = new JButton("Disconnect");
				btnDisconnect.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						SSHController.resetEverything();
						
						isConnected=false;
						btnConnect.setEnabled(!isConnected);
						btnDisconnect.setEnabled(isConnected);
					}
				});
				btnDisconnect.setEnabled(isConnected);
				panelCreds.add(btnDisconnect, "5, 20, 1, 2, fill, fill");
		
		separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.gridwidth = 4;
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridx = 1;
		gbc_separator_1.gridy = 2;
		frame.getContentPane().add(separator_1, gbc_separator_1);
		
		JPanel panelControls = new JPanel();
		GridBagConstraints gbc_panelControls = new GridBagConstraints();
		gbc_panelControls.gridwidth = 4;
		gbc_panelControls.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelControls.anchor = GridBagConstraints.NORTH;
		gbc_panelControls.insets = new Insets(0, 0, 5, 0);
		gbc_panelControls.gridx = 1;
		gbc_panelControls.gridy = 3;
		frame.getContentPane().add(panelControls, gbc_panelControls);
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
		
		btnBounceAllServers = new JButton("Bounce All Servers");
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
		btnBounceAllServers.setEnabled(isConnected);
		panelControls.add(btnBounceAllServers, "3, 2, 3, 1");
		
		txtManagedServerName = new JTextField();
		panelControls.add(txtManagedServerName, "3, 4, fill, default");
		txtManagedServerName.setColumns(10);
		
		btnBounceManagedServer = new JButton("Bounce Managed Server");
		btnBounceManagedServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Result res;

				try{
					res = SSHController.startBounceManagedServer(txtManagedServerName.getText());
				} catch(TaskExecFailException e2){
					e2.printStackTrace();
				}
			}
		});
		btnBounceManagedServer.setEnabled(isConnected);
		panelControls.add(btnBounceManagedServer, "5, 4");
		
		separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 4;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 4;
		frame.getContentPane().add(separator, gbc_separator);
		
		panelStatus = new JPanel();
		GridBagConstraints gbc_panelStatus = new GridBagConstraints();
		gbc_panelStatus.gridwidth = 5;
		gbc_panelStatus.gridheight = 3;
		gbc_panelStatus.fill = GridBagConstraints.BOTH;
		gbc_panelStatus.gridx = 0;
		gbc_panelStatus.gridy = 5;
		frame.getContentPane().add(panelStatus, gbc_panelStatus);
		GridBagLayout gbl_panelStatus = new GridBagLayout();
		gbl_panelStatus.columnWidths = new int[]{0, 424, 0, 0, 0, 0};
		gbl_panelStatus.rowHeights = new int[]{0, 0, 0, 13, 0};
		gbl_panelStatus.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelStatus.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelStatus.setLayout(gbl_panelStatus);
		
		lblStatus = new JLabel("Status");
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus.gridx = 1;
		gbc_lblStatus.gridy = 0;
		panelStatus.add(lblStatus, gbc_lblStatus);
	
		
		
		txtrStatus = new JTextArea(4, 50);
		txtrStatus.setEditable(false);
		txtrStatus.setBackground(new Color(200,200,200));
		scrollPane = new JScrollPane(txtrStatus);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		panelStatus.add(scrollPane, gbc_scrollPane);
		
		
		out = new JTextAreaOutputStream(txtrStatus);
		//System.setOut(new PrintStream(out));


		
	}

}
