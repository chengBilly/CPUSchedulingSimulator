import java.util.ArrayList;

public interface GanttChart {
	static String ganttChart(ArrayList<Job> jobList){
		String ganntText="";
		for (int i=0;i<jobList.size()-1;i++){
			ganntText+=jobList.get(i).getProcessName()+"("+jobList.get(i).getBurstTime()+")"+", ";			
		}
		ganntText+=jobList.get(jobList.size()-1).getProcessName()+"("+jobList.get(jobList.size()-1).getBurstTime()+")";
		return ganntText;
	}
	static String ganttChart(ArrayList<Job> scheduledJobList,ArrayList<String> scheduledProcessList){
		String ganntText="";
		for (int i=0;i<scheduledProcessList.size()-1;i++){
			ganntText+=scheduledProcessList.get(i)+"("+scheduledJobList.get(i).getBurstTime()+")"+", ";			
		}
		ganntText+=scheduledJobList.get(scheduledJobList.size()-1).getProcessName()+"("+scheduledJobList.get(scheduledProcessList.size()-1).getBurstTime()+")";
		return ganntText;
	}
}
