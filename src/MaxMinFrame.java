public class MaxMinFrame extends DataFrame {
	// ������. 
	MaxMinFrame(String title, int width, int height, ManagementData mData) {
		super(title, width, height, mData);
		
		initialize();
	}
	
	// ������ �ʱ�ȭ�۾�.
	protected void initialize() {
		// ���� �����ӿ����� �ǹǷ� ���� �� ����� �Ѵ�.
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		drawComponents();
	}
	
	// �ش� �������� �����ϴ� ������Ʈ���� ����� ���δ�.
	protected void drawComponents() {
		//Graph g = new Graph(mData.getPlaces().get(0), 100, 200);
		
		//add(g);
	}
}
