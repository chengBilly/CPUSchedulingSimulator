import java.util.ArrayList;

public class UserDefined extends Algorithm implements GanttChart{
	//ArrayList<Job> jobList;            <--inherited from Algorithm
	//ArrayList<Job> scheduledJobList;   <--inherited from Algorithm
	//String ganttChart;                 <--inherited from Algorithm
	
	public UserDefined(ArrayList<Job> jList){
		//storing the original Job List
		jobList=new ArrayList<Job>(jList);
		
		//implementation of Scheduling Algorithm
		scheduledJobList=new ArrayList<Job>(jobList);
		int waitingTime=0;
		int aroundTime=0;
		for (int i=0;i<jobList.size();i++){
			if (i==0){
				waitingTime=0;
				aroundTime=scheduledJobList.get(i).getBurstTime();
			}
			else{
				waitingTime+=scheduledJobList.get(i-1).getBurstTime();
				aroundTime=aroundTime+scheduledJobList.get(i).getBurstTime();
			}
			scheduledJobList.get(i).setWaitingTime(waitingTime);
			scheduledJobList.get(i).setAroundTime(aroundTime);
		}
		
		//Displaying Gannt Chart with scheduledJobList
		ganttChart=GanttChart.ganttChart(scheduledJobList);
	}
}
