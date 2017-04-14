import java.util.ArrayList;

import javax.swing.JLabel;

public abstract class Algorithm {
	ArrayList<Job> jobList;
	ArrayList<Job> scheduledJobList;
	String ganttChart;
	
	double getAvgWaitingTime(ArrayList<Job> jobList){
		double totalWaitingTime=0;
		for (int i=0;i<jobList.size();i++){
			totalWaitingTime=totalWaitingTime+jobList.get(i).getWaitingTime();
		}
		return (totalWaitingTime/jobList.size());
	}
	double getAvgAroundTime(ArrayList<Job> jobList){
		double totalAroundTime=0;
		for (int i=0;i<jobList.size();i++){
			totalAroundTime=totalAroundTime+jobList.get(i).getAroundTime();
		}
		return (totalAroundTime/jobList.size());
	}
	
	double totalExecutionTime(ArrayList<Job> jobList){
		double totalExecutionTime=0;
		for (int i=0;i<jobList.size();i++){
			totalExecutionTime=totalExecutionTime+jobList.get(i).getBurstTime();
		}
		return (totalExecutionTime);
	}
	
	void showProcessInfo(ArrayList<Job> jobList,JLabel[] statusLabelList,JLabel[] WTLabelList,JLabel[] ATLabelList){
		for (int i=0;i<jobList.size();i++){
			statusLabelList[i].setText("Finished");
			WTLabelList[i].setText(""+jobList.get(i).getWaitingTime());
			ATLabelList[i].setText(""+jobList.get(i).getAroundTime());		
		}
		for (int i=jobList.size();i<10;i++){
			statusLabelList[i].setText("");
			WTLabelList[i].setText("");
			ATLabelList[i].setText("");
		}
	}
}
