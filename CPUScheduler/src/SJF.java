import java.util.ArrayList;

public class SJF extends Algorithm implements ShortestFirst,GanttChart{
	public SJF(ArrayList<Job> jList){
		jobList=new ArrayList<Job>(jList);
		scheduledJobList=new ArrayList<Job>(jList);
		scheduledJobList=ShortestFirst.schedule(scheduledJobList);
		
		int aroundTime=0;
		int waitingTime=0;
		for (int i=0;i<jobList.size();i++){
			if (i==0){
				waitingTime=0;
				aroundTime=waitingTime+scheduledJobList.get(i).getBurstTime();
			}
			else{
				waitingTime+=scheduledJobList.get(i-1).getBurstTime();
				aroundTime=waitingTime+scheduledJobList.get(i).getBurstTime();
			}
			int processNo=jobList.indexOf(scheduledJobList.get(i));
			jobList.get(processNo).setWaitingTime(waitingTime);
			jobList.get(processNo).setAroundTime(aroundTime);
		}
		
		ganttChart=GanttChart.ganttChart(scheduledJobList);
	}
}
