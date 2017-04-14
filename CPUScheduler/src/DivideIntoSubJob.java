import java.util.ArrayList;

public interface DivideIntoSubJob {
	public static ArrayList<Job> divide(ArrayList<Job> jList,int timequantum){
		ArrayList<Job> oldJList=new ArrayList<Job>(jList);
		ArrayList<Job> newJList=new ArrayList<Job>();
		while (oldJList.size()>0){
			if (oldJList.get(0).getBurstTime()<=timequantum){
				newJList.add(oldJList.get(0));
				oldJList.remove(0);
			}
			else{
				int tmpBurst=oldJList.get(0).getBurstTime();
				String tmpP=oldJList.get(0).getProcessName();
				Job tmpJob=new Job(tmpP,timequantum);
				newJList.add(tmpJob);
				tmpBurst=tmpBurst-timequantum;
				Job tmpJob2=new Job(tmpP,tmpBurst);
				oldJList.remove(0);
				oldJList.add(tmpJob2);
				
			}
		}
		return newJList;
	}
}
