import java.util.ArrayList;

public abstract class DataFrame extends Frame {
	protected ManagementData mData;		// ��� �������� ������
	protected ArrayList<Graph> graph;	// �׷��� ����Ʈ
	
	DataFrame(String title, int width, int height, ManagementData mData) {
		super(title, width, height);
		
		this.mData = mData;
	}
}