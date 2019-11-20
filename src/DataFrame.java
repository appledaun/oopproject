import java.util.ArrayList;

public abstract class DataFrame extends Frame {
	protected ManagementData mData;		// 모든 오염물질 데이터
	protected ArrayList<Graph> graph;	// 그래프 리스트
	
	DataFrame(String title, int width, int height, ManagementData mData) {
		super(title, width, height);
		
		this.mData = mData;
	}
}