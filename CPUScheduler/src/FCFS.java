import java.util.ArrayList;

public class FCFS extends Algorithm implements GanttChart{
	public FCFS(ArrayList<Job> jList){
		jobList=new ArrayList<Job>(jList);
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
		ganttChart=GanttChart.ganttChart(scheduledJobList);
	}
}
