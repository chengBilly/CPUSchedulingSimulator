
public class Job {
	String processName;
	int burstTime;
	int waitingTime;
	int aroundTime;
	public Job(String pname, int btime){
		processName=pname;
		burstTime=btime;
	}
	public String getProcessName() {
		return processName;
	}
	public int getBurstTime() {
		return burstTime;
	}
	
	public int getAroundTime() {
		return aroundTime;
	}
	public void setAroundTime(int aroundTime) {
		this.aroundTime = aroundTime;
	}
	public int getWaitingTime() {
		return waitingTime;
	}
	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}
}
