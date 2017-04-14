import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Interface extends JFrame {
	//Interface Part
	static DecimalFormat df = new DecimalFormat("####0.0");
	
	//Scheduling Part
	static ArrayList<Job> jobList=new ArrayList<Job>();
	static int processTotal=0;
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Interface() {
		JPanel contentPane;
		JTextField GanttChartField;
		JTable ProcessTable;
		JTextField AddPanel_BurstTimeField;
		
		JTextField TimeQuantumField;
		JTable ComparisonPanel_Table;
		
		setTitle("CPU Scheduling Simulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1321, 727);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel ComparisonPanel = new JPanel();
		ComparisonPanel.setBounds(241, 173, 794, 300);
		contentPane.add(ComparisonPanel);
		ComparisonPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		ComparisonPanel.setLayout(null);
		
		JLabel lblResults = new JLabel("Results");
		lblResults.setFont(new Font("Arial", Font.PLAIN, 21));
		lblResults.setBounds(61, 10, 82, 36);
		ComparisonPanel.add(lblResults);
		
		ComparisonPanel_Table = new JTable();
		ComparisonPanel_Table.setEnabled(false);
		ComparisonPanel_Table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				".", "Average Waiting Time","Average Turnaround Time"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class
			};
			@SuppressWarnings({ })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		ComparisonPanel_Table.getColumnModel().getColumn(0).setResizable(false);
		ComparisonPanel_Table.getColumnModel().getColumn(0).setPreferredWidth(100);
		ComparisonPanel_Table.getColumnModel().getColumn(1).setResizable(false);
		ComparisonPanel_Table.getColumnModel().getColumn(1).setPreferredWidth(120);
		ComparisonPanel_Table.getColumnModel().getColumn(2).setPreferredWidth(113);
		ComparisonPanel_Table.setBounds(42, 61, 742, 213);
		ComparisonPanel_Table.setRowHeight(32);
		ComparisonPanel.add(ComparisonPanel_Table);
		ComparisonPanel.setVisible(false);
		
		
		
		
		TimeQuantumField = new JTextField();
		TimeQuantumField.setBounds(1019, 625, 96, 42);
		contentPane.add(TimeQuantumField);
		TimeQuantumField.setColumns(10);
		
		JComboBox AlgorithmSelectionBox = new JComboBox();
		AlgorithmSelectionBox.setFont(new Font("Arial", Font.PLAIN, 13));
		AlgorithmSelectionBox.setModel(new DefaultComboBoxModel(new String[] {"First Come First Served", "Shortest Job First", "Round Robin", "Proposed Algorithm","User-Defined Algorithm", "All Algorithm"}));
		AlgorithmSelectionBox.setBounds(836, 625, 159, 42);
		contentPane.add(AlgorithmSelectionBox);
		
		ProcessTable = new JTable();
		ProcessTable.setEnabled(false);
		ProcessTable.setRowHeight(32);
		ProcessTable.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		ProcessTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Process", "Burst Time"
			}
		) 
		{
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		ProcessTable.getColumnModel().getColumn(1).setResizable(false);
		
		
		
		JPanel AddPanel = new JPanel();
		AddPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Add Process", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		AddPanel.setBounds(577, 233, 323, 151);
		contentPane.add(AddPanel);
		AddPanel.setLayout(null);
		AddPanel.setVisible(false);
		
		JLabel AddPanel_BurstTimeText = new JLabel("Burst Time");
		AddPanel_BurstTimeText.setBounds(37, 48, 105, 26);
		AddPanel_BurstTimeText.setFont(new Font("Arial", Font.PLAIN, 22));
		AddPanel.add(AddPanel_BurstTimeText);
		
		
		AddPanel_BurstTimeField = new JTextField();
		AddPanel_BurstTimeField.setBounds(171, 50, 113, 32);
		AddPanel.add(AddPanel_BurstTimeField);
		AddPanel_BurstTimeField.setColumns(10);
		
		JButton AddPanel_CancelButton = new JButton("Cancel");
		AddPanel_CancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddPanel.setVisible(false);
			}
		});
		AddPanel_CancelButton.setBounds(125, 118, 87, 23);
		AddPanel.add(AddPanel_CancelButton);
		
		JButton AddPanel_AddButton = new JButton("Add");
		AddPanel_AddButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (AddPanel_BurstTimeField.getText().length()>0 && Integer.parseInt(AddPanel_BurstTimeField.getText())>0){
					if (isNumeric(AddPanel_BurstTimeField.getText())==true){
						//Add to list
						processTotal++;
						addJob("P"+processTotal,Integer.parseInt(AddPanel_BurstTimeField.getText()));
						//Add to ProcessTable to display
						DefaultTableModel model = (DefaultTableModel) ProcessTable.getModel();
						model.addRow(new Object[]{"                      P"+processTotal,Integer.parseInt(AddPanel_BurstTimeField.getText())+"                                    "});
						AddPanel_BurstTimeField.setText("");
						AddPanel.setVisible(false);
					}
				}
			}
		});
		AddPanel_AddButton.setBounds(226, 118, 87, 23);
		AddPanel.add(AddPanel_AddButton);
		
		JLabel PLabel1 = new JLabel("P1");
		PLabel1.setFont(new Font("Arial", Font.PLAIN, 25));
		PLabel1.setBounds(690, 85, 59, 25);
		contentPane.add(PLabel1);
		PLabel1.setVisible(false);
		
		JLabel PLabel2 = new JLabel("P2");
		PLabel2.setFont(new Font("Arial", Font.PLAIN, 25));
		PLabel2.setBounds(690, 133, 59, 25);
		contentPane.add(PLabel2);
		PLabel2.setVisible(false);
		
		JLabel PLabel3 = new JLabel("P3");
		PLabel3.setFont(new Font("Arial", Font.PLAIN, 25));
		PLabel3.setBounds(690, 178, 59, 25);
		contentPane.add(PLabel3);
		PLabel3.setVisible(false);
		
		JLabel PLabel4 = new JLabel("P4");
		PLabel4.setFont(new Font("Arial", Font.PLAIN, 25));
		PLabel4.setBounds(690, 223, 59, 25);
		contentPane.add(PLabel4);
		PLabel4.setVisible(false);
		
		JLabel PLabel5 = new JLabel("P5");
		PLabel5.setFont(new Font("Arial", Font.PLAIN, 25));
		PLabel5.setBounds(690, 268, 59, 25);
		contentPane.add(PLabel5);
		PLabel5.setVisible(false);
		
		JLabel PLabel6 = new JLabel("P6");
		PLabel6.setFont(new Font("Arial", Font.PLAIN, 25));
		PLabel6.setBounds(690, 313, 59, 25);
		contentPane.add(PLabel6);
		PLabel6.setVisible(false);
		
		JLabel PLabel7 = new JLabel("P7");
		PLabel7.setFont(new Font("Arial", Font.PLAIN, 25));
		PLabel7.setBounds(690, 358, 59, 25);
		contentPane.add(PLabel7);
		PLabel7.setVisible(false);
		
		JLabel PLabel8 = new JLabel("P8");
		PLabel8.setFont(new Font("Arial", Font.PLAIN, 25));
		PLabel8.setBounds(690, 403, 59, 25);
		contentPane.add(PLabel8);
		PLabel8.setVisible(false);
		
		JLabel PLabel9 = new JLabel("P9");
		PLabel9.setFont(new Font("Arial", Font.PLAIN, 25));
		PLabel9.setBounds(690, 448, 59, 25);
		contentPane.add(PLabel9);
		PLabel9.setVisible(false);
		
		JLabel PLabel10 = new JLabel("P10");
		PLabel10.setFont(new Font("Arial", Font.PLAIN, 25));
		PLabel10.setBounds(690, 493, 59, 25);
		contentPane.add(PLabel10);
		PLabel10.setVisible(false);
		
		JLabel[] ProcessLabelList={PLabel1,PLabel2,PLabel3,PLabel4,PLabel5,PLabel6,PLabel7,PLabel8,PLabel9,PLabel10};
		
		JLabel statusLabel_1 = new JLabel();
		statusLabel_1.setToolTipText("");
		statusLabel_1.setFont(new Font("Arial", Font.PLAIN, 25));
		statusLabel_1.setBounds(795, 83, 160, 32);
		contentPane.add(statusLabel_1);
		
		JLabel statusLabel_2 = new JLabel();
		statusLabel_2.setFont(new Font("Arial", Font.PLAIN, 25));
		statusLabel_2.setBounds(795, 127, 160, 32);
		contentPane.add(statusLabel_2);
		
		JLabel statusLabel_3 = new JLabel();
		statusLabel_3.setFont(new Font("Arial", Font.PLAIN, 25));
		statusLabel_3.setBounds(795, 171, 160, 32);
		contentPane.add(statusLabel_3);
		
		JLabel statusLabel_4 = new JLabel();
		statusLabel_4.setFont(new Font("Arial", Font.PLAIN, 25));
		statusLabel_4.setBounds(795, 217, 160, 32);
		contentPane.add(statusLabel_4);
		
		JLabel statusLabel_5 = new JLabel();
		statusLabel_5.setFont(new Font("Arial", Font.PLAIN, 25));
		statusLabel_5.setBounds(795, 264, 160, 32);
		contentPane.add(statusLabel_5);
		
		JLabel statusLabel_6 = new JLabel();
		statusLabel_6.setFont(new Font("Arial", Font.PLAIN, 25));
		statusLabel_6.setBounds(795, 307, 160, 32);
		contentPane.add(statusLabel_6);
		
		JLabel statusLabel_7 = new JLabel();
		statusLabel_7.setFont(new Font("Arial", Font.PLAIN, 25));
		statusLabel_7.setBounds(795, 353, 160, 32);
		contentPane.add(statusLabel_7);
		
		JLabel statusLabel_8 = new JLabel();
		statusLabel_8.setFont(new Font("Arial", Font.PLAIN, 25));
		statusLabel_8.setBounds(795, 398, 160, 32);
		contentPane.add(statusLabel_8);
		
		JLabel statusLabel_9 = new JLabel();
		statusLabel_9.setFont(new Font("Arial", Font.PLAIN, 25));
		statusLabel_9.setBounds(795, 443, 160, 32);
		contentPane.add(statusLabel_9);
		
		JLabel statusLabel_10 = new JLabel();
		statusLabel_10.setFont(new Font("Arial", Font.PLAIN, 25));
		statusLabel_10.setBounds(795, 489, 160, 32);
		contentPane.add(statusLabel_10);
		
		JLabel[] statusLabelList={statusLabel_1,statusLabel_2,statusLabel_3,statusLabel_4,statusLabel_5,statusLabel_6,statusLabel_7,statusLabel_8,statusLabel_9,statusLabel_10};
		
		JLabel WTValue1 = new JLabel("");
		WTValue1.setFont(new Font("Arial", Font.PLAIN, 25));
		WTValue1.setBounds(1000, 85, 59, 25);
		contentPane.add(WTValue1);
		
		JLabel WTValue2 = new JLabel("");
		WTValue2.setFont(new Font("Arial", Font.PLAIN, 25));
		WTValue2.setBounds(1000, 133, 59, 25);
		contentPane.add(WTValue2);
		
		JLabel WTValue3 = new JLabel("");
		WTValue3.setFont(new Font("Arial", Font.PLAIN, 25));
		WTValue3.setBounds(1000, 178, 59, 25);
		contentPane.add(WTValue3);
		
		JLabel WTValue4 = new JLabel("");
		WTValue4.setFont(new Font("Arial", Font.PLAIN, 25));
		WTValue4.setBounds(1000, 223, 59, 25);
		contentPane.add(WTValue4);
		
		JLabel WTValue5 = new JLabel("");
		WTValue5.setFont(new Font("Arial", Font.PLAIN, 25));
		WTValue5.setBounds(1000, 268, 59, 25);
		contentPane.add(WTValue5);
		
		JLabel WTValue6 = new JLabel("");
		WTValue6.setFont(new Font("Arial", Font.PLAIN, 25));
		WTValue6.setBounds(1000, 313, 59, 25);
		contentPane.add(WTValue6);
		
		JLabel WTValue7 = new JLabel("");
		WTValue7.setFont(new Font("Arial", Font.PLAIN, 25));
		WTValue7.setBounds(1000, 358, 59, 25);
		contentPane.add(WTValue7);
		
		JLabel WTValue8 = new JLabel("");
		WTValue8.setFont(new Font("Arial", Font.PLAIN, 25));
		WTValue8.setBounds(1000, 403, 59, 25);
		contentPane.add(WTValue8);
		
		JLabel WTValue9 = new JLabel("");
		WTValue9.setFont(new Font("Arial", Font.PLAIN, 25));
		WTValue9.setBounds(1000, 448, 59, 25);
		contentPane.add(WTValue9);
		
		JLabel WTValue10 = new JLabel("");
		WTValue10.setFont(new Font("Arial", Font.PLAIN, 25));
		WTValue10.setBounds(1000, 493, 59, 25);
		contentPane.add(WTValue10);
		
		JLabel[] WTLabelList={WTValue1,WTValue2,WTValue3,WTValue4,WTValue5,WTValue6,WTValue7,WTValue8,WTValue9,WTValue10};
		
		JLabel ATValue1 = new JLabel("");
		ATValue1.setFont(new Font("Arial", Font.PLAIN, 25));
		ATValue1.setBounds(1160, 85, 59, 25);
		contentPane.add(ATValue1);
		
		JLabel ATValue2 = new JLabel("");
		ATValue2.setFont(new Font("Arial", Font.PLAIN, 25));
		ATValue2.setBounds(1160, 133, 59, 25);
		contentPane.add(ATValue2);
		
		JLabel ATValue3 = new JLabel("");
		ATValue3.setFont(new Font("Arial", Font.PLAIN, 25));
		ATValue3.setBounds(1160, 178, 59, 25);
		contentPane.add(ATValue3);
		
		JLabel ATValue4 = new JLabel("");
		ATValue4.setFont(new Font("Arial", Font.PLAIN, 25));
		ATValue4.setBounds(1160, 223, 59, 25);
		contentPane.add(ATValue4);
		
		JLabel ATValue5 = new JLabel("");
		ATValue5.setFont(new Font("Arial", Font.PLAIN, 25));
		ATValue5.setBounds(1160, 268, 59, 25);
		contentPane.add(ATValue5);
		
		JLabel ATValue6 = new JLabel("");
		ATValue6.setFont(new Font("Arial", Font.PLAIN, 25));
		ATValue6.setBounds(1160, 313, 59, 25);
		contentPane.add(ATValue6);
		
		JLabel ATValue7 = new JLabel("");
		ATValue7.setFont(new Font("Arial", Font.PLAIN, 25));
		ATValue7.setBounds(1160, 358, 59, 25);
		contentPane.add(ATValue7);
		
		JLabel ATValue8 = new JLabel("");
		ATValue8.setFont(new Font("Arial", Font.PLAIN, 25));
		ATValue8.setBounds(1160, 403, 59, 25);
		contentPane.add(ATValue8);
		
		JLabel ATValue9 = new JLabel("");
		ATValue9.setFont(new Font("Arial", Font.PLAIN, 25));
		ATValue9.setBounds(1160, 448, 59, 25);
		contentPane.add(ATValue9);
		
		JLabel ATValue10 = new JLabel("");
		ATValue10.setFont(new Font("Arial", Font.PLAIN, 25));
		ATValue10.setBounds(1160, 493, 59, 25);
		contentPane.add(ATValue10);
		
		JLabel[] ATLabelList={ATValue1,ATValue2,ATValue3,ATValue4,ATValue5,ATValue6,ATValue7,ATValue8,ATValue9,ATValue10};
		
					
		JButton ProcessAddButton = new JButton("Add");
		ProcessAddButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (processTotal<10)
				AddPanel.setVisible(true);
			}
		});
		ProcessAddButton.setFont(new Font("Arial", Font.PLAIN, 15));
		ProcessAddButton.setBounds(489, 57, 87, 48);
		contentPane.add(ProcessAddButton);
		
		JButton RandomButton = new JButton("Random");
		RandomButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (processTotal<10){
					//Add to list
					processTotal++;
					int randomBurstTime=(int) Math.round(Math.random()*19)+1;
					addJob("P"+processTotal,randomBurstTime);
					//Add to ProcessTable to display
					DefaultTableModel model = (DefaultTableModel) ProcessTable.getModel();
					model.addRow(new Object[]{"                      P"+processTotal,randomBurstTime+"                                    "});
				}
			}
		});
		RandomButton.setFont(new Font("Arial", Font.PLAIN, 14));
		RandomButton.setBounds(489, 125, 87, 48);
		contentPane.add(RandomButton);
		
		JButton ProcessEmptyButton = new JButton("Empty");
		ProcessEmptyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearAllJob(ProcessTable);
			}
		});
		ProcessEmptyButton.setFont(new Font("Arial", Font.PLAIN, 15));
		ProcessEmptyButton.setBounds(489, 268, 87, 48);
		contentPane.add(ProcessEmptyButton);
		
		JButton ProcessDeleteButton = new JButton("Delete");
		ProcessDeleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				deleteJob(ProcessTable);
			}
		});
		ProcessDeleteButton.setFont(new Font("Arial", Font.PLAIN, 15));
		ProcessDeleteButton.setBounds(489, 200, 87, 48);
		contentPane.add(ProcessDeleteButton);
		
		JLabel ProcessTableText = new JLabel("  Process                            Burst Time");
		ProcessTableText.setFont(new Font("Arial", Font.PLAIN, 20));
		ProcessTableText.setBounds(72, 10, 400, 48);
		contentPane.add(ProcessTableText);
		ProcessTable.setBounds(39, 53, 440, 320);
		contentPane.add(ProcessTable);
		
		JButton ComparisonPanel_CloseButton = new JButton("Close");
		ComparisonPanel_CloseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProcessDeleteButton.setVisible(true);
				DefaultTableModel model = (DefaultTableModel) ComparisonPanel_Table.getModel();
				for (int i=5;i>=0;i--){
					model.removeRow(i);
				}
				ComparisonPanel.setVisible(false);
			}
		});
		ComparisonPanel_CloseButton.setBounds(685, 10, 87, 23);
		ComparisonPanel.add(ComparisonPanel_CloseButton);
		
		
		JLabel tetValue = new JLabel("0");
		tetValue.setFont(new Font("Arial", Font.PLAIN, 30));
		tetValue.setBounds(462, 508, 72, 42);
		contentPane.add(tetValue);
		
		JLabel aatValue = new JLabel("0");
		aatValue.setFont(new Font("Arial", Font.PLAIN, 30));
		aatValue.setBounds(462, 462, 72, 42);
		contentPane.add(aatValue);
		
		JLabel awtValue = new JLabel("0");
		awtValue.setFont(new Font("Arial", Font.PLAIN, 30));
		awtValue.setBounds(462, 418, 72, 42);
		contentPane.add(awtValue);
		
		GanttChartField = new JTextField();
		GanttChartField.setBounds(39, 625, 736, 42);
		contentPane.add(GanttChartField);
		GanttChartField.setColumns(10);

		JButton simulateButton = new JButton("Simulate");
		simulateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				simulate(AlgorithmSelectionBox,ProcessLabelList,statusLabelList,WTLabelList,ATLabelList,awtValue,aatValue,tetValue,ProcessDeleteButton,ComparisonPanel,TimeQuantumField,GanttChartField,ComparisonPanel_Table);
			}
		});
		simulateButton.setFont(new Font("Arial", Font.PLAIN, 22));
		simulateButton.setForeground(new Color(0, 0, 0));
		simulateButton.setBounds(1131, 595, 149, 72);
		contentPane.add(simulateButton);
		
		JLabel backgroundLabel = new JLabel("");
		backgroundLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		backgroundLabel.setIcon(new ImageIcon("C:\\Users\\13067\\workspace\\CPUScheduler\\src\\background.jpg"));
		backgroundLabel.setBounds(10, 0, 1324, 693);
		contentPane.add(backgroundLabel);
		
		
	}
	public static void addJob(String processName,int burstTime){
		Job newJob=new Job(processName,burstTime);
		jobList.add(newJob);
	}
	public static void deleteJob(JTable x){
		if (processTotal>0){
			jobList.remove(processTotal-1);
			((DefaultTableModel)x.getModel()).removeRow(processTotal-1);
			processTotal=processTotal-1;
		}
	}
	public static void clearAllJob(JTable x){
		for (int i=processTotal;i>0;i--){
			jobList.remove(i-1);
			((DefaultTableModel)x.getModel()).removeRow(i-1);
			processTotal=i-1;
		}
	}
	
	public static void simulate(JComboBox AlgorithmSelectionBox,JLabel[] ProcessLabelList,JLabel[] statusLabelList,JLabel[] WTLabelList,JLabel[] ATLabelList,JLabel awtValue,JLabel aatValue,JLabel tetValue,JButton ProcessDeleteButton,JPanel ComparisonPanel,JTextField TimeQuantumField,JTextField GanttChartField,JTable ComparisonPanel_Table){
		FCFS FCFSAlgorithm;
		SJF SJFAlgorithm;
		RoundRobin RRAlgorithm;
		Proposed ProposedAlgorithm;
		UserDefined UserDefinedAlgorithm;
		if (processTotal>0){
			switch (AlgorithmSelectionBox.getSelectedItem().toString()){
				case "First Come First Served":
					TimeQuantumField.setText("");
					FCFSAlgorithm=new FCFS(jobList);
					//Show Waiting Time and Around Time
					FCFSAlgorithm.showProcessInfo(jobList,statusLabelList,WTLabelList,ATLabelList);
					
					//Show awt,aat,tet,ganntchart
					setInfo(awtValue, df.format(FCFSAlgorithm.getAvgWaitingTime(jobList)), aatValue, df.format(FCFSAlgorithm.getAvgAroundTime(jobList)), tetValue, df.format(FCFSAlgorithm.totalExecutionTime(jobList)), GanttChartField, FCFSAlgorithm.ganttChart);
					break;
				case "Shortest Job First":
					TimeQuantumField.setText("");
					
					SJFAlgorithm=new SJF(jobList);
					//Show Waiting Time and Around Time
					SJFAlgorithm.showProcessInfo(jobList,statusLabelList,WTLabelList,ATLabelList);
					
					//Show awt,aat,tet,ganntchart
					setInfo(awtValue, df.format(SJFAlgorithm.getAvgWaitingTime(jobList)), aatValue, df.format(SJFAlgorithm.getAvgAroundTime(jobList)), tetValue, df.format(SJFAlgorithm.totalExecutionTime(jobList)), GanttChartField, SJFAlgorithm.ganttChart);
					break;
				case "Round Robin":
					int timequantum=5;
					if (TimeQuantumField.getText().length()>0 && Integer.parseInt(TimeQuantumField.getText())>0){
						if (isNumeric(TimeQuantumField.getText())==true){
							timequantum=Integer.parseInt(TimeQuantumField.getText());
						}
						else{
							TimeQuantumField.setText(""+5);
						}
					}
					else{
						TimeQuantumField.setText(""+5);
					}
					
					RRAlgorithm=new RoundRobin(jobList,timequantum);
					//Show Waiting Time and Around Time
					RRAlgorithm.showProcessInfo(jobList,statusLabelList,WTLabelList,ATLabelList);
					
					//Show awt,aat,tet,ganntchart
					setInfo(awtValue, df.format(RRAlgorithm.getAvgWaitingTime(jobList)), aatValue, df.format(RRAlgorithm.getAvgAroundTime(jobList)), tetValue, df.format(RRAlgorithm.totalExecutionTime(jobList)), GanttChartField, RRAlgorithm.ganttChart);
					break;
				case "Proposed Algorithm":
					TimeQuantumField.setText("");
					
					ProposedAlgorithm=new Proposed(jobList);
					//Show Waiting Time and Around Time
					ProposedAlgorithm.showProcessInfo(jobList,statusLabelList,WTLabelList,ATLabelList);
					
					//Show awt,aat,tet,ganntchart
					setInfo(awtValue, df.format(ProposedAlgorithm.getAvgWaitingTime(jobList)), aatValue, df.format(ProposedAlgorithm.getAvgAroundTime(jobList)), tetValue, df.format(ProposedAlgorithm.totalExecutionTime(jobList)), GanttChartField, ProposedAlgorithm.ganttChart);
					break;
				case "User-Defined Algorithm":
					TimeQuantumField.setText("");
					UserDefinedAlgorithm=new UserDefined(jobList);
					//Show Waiting Time and Around Time
					UserDefinedAlgorithm.showProcessInfo(jobList,statusLabelList,WTLabelList,ATLabelList);
					
					//Show awt,aat,tet,ganntchart
					setInfo(awtValue, df.format(UserDefinedAlgorithm.getAvgWaitingTime(jobList)), aatValue, df.format(UserDefinedAlgorithm.getAvgAroundTime(jobList)), tetValue, df.format(UserDefinedAlgorithm.totalExecutionTime(jobList)), GanttChartField, UserDefinedAlgorithm.ganttChart);
					break;
				case "All Algorithm":
					ProcessDeleteButton.setVisible(false);
					//Add to ProcessTable to display
					DefaultTableModel model = (DefaultTableModel) ComparisonPanel_Table.getModel();
					
					model.addRow(new Object[]{"","Average Waiting Time","Average Turnaround Time"});
					
					//FCFS
					FCFSAlgorithm=new FCFS(jobList);
					String FCFSawt=df.format(FCFSAlgorithm.getAvgWaitingTime(jobList));
					String FCFSaat=df.format(FCFSAlgorithm.getAvgAroundTime(jobList));
					//SJF
					SJFAlgorithm=new SJF(jobList);
					String SJFawt=df.format(SJFAlgorithm.getAvgWaitingTime(jobList));
					String SJFaat=df.format(SJFAlgorithm.getAvgAroundTime(jobList));
					//RR
					timequantum=5;
					if (TimeQuantumField.getText().length()==0){
						TimeQuantumField.setText(""+5);
					}
					else{
						timequantum=Integer.parseInt(TimeQuantumField.getText());
					}
					
					RRAlgorithm=new RoundRobin(jobList,timequantum);
					String RRawt=df.format(RRAlgorithm.getAvgWaitingTime(jobList));
					String RRaat=df.format(RRAlgorithm.getAvgAroundTime(jobList));
					//Proposed
					ProposedAlgorithm=new Proposed(jobList);
					String Proposedawt=df.format(ProposedAlgorithm.getAvgWaitingTime(jobList));
					String Proposedaat=df.format(ProposedAlgorithm.getAvgAroundTime(jobList));
					//UserDefined Algorithm
					UserDefinedAlgorithm=new UserDefined(jobList);
					String UserDefinedawt=df.format(UserDefinedAlgorithm.getAvgWaitingTime(jobList));
					String UserDefinedaat=df.format(UserDefinedAlgorithm.getAvgAroundTime(jobList));
					
					model.addRow(new Object[]{"First Come First Served"                ,"    "+FCFSawt     ,"    "+FCFSaat           });
					model.addRow(new Object[]{"Shortest Job First"                     ,"    "+SJFawt      ,"    "+SJFaat            });
					model.addRow(new Object[]{"Round Robin (Quantum:"+timequantum+")"  ,"    "+RRawt       ,"    "+RRaat             });
					model.addRow(new Object[]{"Proposed Algorithm"                     ,"    "+Proposedawt ,"    "+Proposedaat       });
					model.addRow(new Object[]{"UserDefined  Algorithm"                     ,"    "+UserDefinedawt  ,"    "+UserDefinedaat       });
					
					ComparisonPanel.setVisible(true);
					break;
			}
			//ShowProcessLabel
			for (int i=0;i<jobList.size();i++){
				ProcessLabelList[i].setVisible(true);
			}
			for (int i=jobList.size();i<10;i++){
				ProcessLabelList[i].setVisible(false);
			}
		}
	}
	
	public static void setInfo(JLabel awtValue,String awt,JLabel aatValue,String aat,JLabel tetValue,String tet,JTextField GanttChartField,String ganntChart){
		awtValue.setText(awt);
		aatValue.setText(aat);
		tetValue.setText(tet);
		GanttChartField.setText(ganntChart);
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
}
