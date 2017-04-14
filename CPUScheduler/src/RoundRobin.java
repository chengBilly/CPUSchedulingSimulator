import java.util.ArrayList;

public class RoundRobin extends Algorithm implements DivideIntoSubJob,GanttChart{
	
	
	public RoundRobin(ArrayList<Job> jList,int timequantum){
		ArrayList<String> jobProcessList;
		ArrayList<String> rrProcessList;
		
		jobList=new ArrayList<Job>(jList);
		jobProcessList=new ArrayList<String>();
		for (int i=0;i<jobList.size();i++){
			jobProcessList.add(jobList.get(i).getProcessName());
		}
		
		scheduledJobList=new ArrayList<Job>(jList);
		scheduledJobList=DivideIntoSubJob.divide(scheduledJobList,timequantum);
		rrProcessList=new ArrayList<String>();
		for (int i=0;i<scheduledJobList.size();i++)
			rrProcessList.add(scheduledJobList.get(i).getProcessName());
		
		int waitingTime=0;
		for (int i=0;i<jobList.size();i++){
			if (i==0){
				waitingTime=0;
			}
			else{
				waitingTime+=scheduledJobList.get(i-1).getBurstTime();
			}
			int processNo=jobProcessList.indexOf(scheduledJobList.get(i).getProcessName());
			jobList.get(processNo).setWaitingTime(waitingTime);
			
			int accumatedAroundTime=0;
        	int lastpos=rrProcessList.lastIndexOf(jobProcessList.get(i));
	        	for (int j=0;j<=lastpos;j++){
	        		accumatedAroundTime=accumatedAroundTime+scheduledJobList.get(j).getBurstTime();
	        	}
	        jobList.get(i).setAroundTime(accumatedAroundTime);
		}
		
		ganttChart=GanttChart.ganttChart(scheduledJobList);
	}
}
