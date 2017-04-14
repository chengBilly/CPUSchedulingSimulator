import java.util.ArrayList;

public interface ShortestFirst {
	static ArrayList<Job> schedule(ArrayList<Job> jList ){
		ArrayList<Job> newJList=new ArrayList<Job>(jList);
		int j;
		boolean flag = true;
		Job temp;
		while ( flag ){
			flag= false;
		    for( j=0;  j < newJList.size() - 1;  j++ ){
		        if ( newJList.get(j).getBurstTime() > newJList.get(j+1).getBurstTime() ){
		            temp = newJList.get(j);
		            newJList.set(j, newJList.get(j+1));
		            newJList.set(j + 1, temp);
		            flag = true;
		        }
		    }
		}
		return newJList;
	}		
}
