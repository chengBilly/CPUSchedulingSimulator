import java.util.ArrayList;

public class Proposed extends Algorithm implements DivideIntoSubJob,ShortestFirst,GanttChart{
	public Proposed(ArrayList<Job> jList){
		ArrayList<String> processList;
		ArrayList<String> scheduledProcessList;
		jobList=new ArrayList<Job>(jList);
		processList=new ArrayList<String>();
		for (int i=0;i<jobList.size();i++)
			processList.add(jobList.get(i).getProcessName());
		
		// find mean
		int mean=0;
		for (int i=0;i<jobList.size();i++){
			mean=mean+jobList.get(i).getBurstTime();
		}
		mean=(int) (mean/jobList.size());
		
		scheduledJobList=DivideIntoSubJob.divide(jobList,mean);
		scheduledJobList=ShortestFirst.schedule(scheduledJobList);
		scheduledProcessList=new ArrayList<String>();
		for (int i=0;i<scheduledJobList.size();i++)
			scheduledProcessList.add(scheduledJobList.get(i).getProcessName());
		
		
		int waitingTime=0;
		for (int i=0;i<jobList.size();i++){
			int processposition=scheduledProcessList.indexOf(processList.get(i));
			for (int j=0;j<processposition;j++)
				waitingTime+=scheduledJobList.get(j).getBurstTime();
			jobList.get(i).setWaitingTime(waitingTime);
			waitingTime=0;
			
			int accumatedAroundTime=0;
        	int lastpos=scheduledProcessList.lastIndexOf(processList.get(i));
	        	for (int j=0;j<=lastpos;j++){
	        		accumatedAroundTime=accumatedAroundTime+scheduledJobList.get(j).getBurstTime();
	        	}
		    jobList.get(i).setAroundTime(accumatedAroundTime);
		}
		
		ganttChart=GanttChart.ganttChart(scheduledJobList,scheduledProcessList);
	}
}
