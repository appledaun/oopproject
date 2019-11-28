public class MaxMinFrame extends DataFrame {
	// 생성자. 
	MaxMinFrame(String title, int width, int height, ManagementData mData) {
		super(title, width, height, mData);
		
		initialize();
	}
	
	// 프레임 초기화작업.
	protected void initialize() {
		// 하위 프레임역할이 되므로 종료 시 숨기게 한다.
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		drawComponents();
	}
	
	// 해당 프레임을 구성하는 컴포넌트들을 만들고 붙인다.
	protected void drawComponents() {
		//Graph g = new Graph(mData.getPlaces().get(0), 100, 200);
		
		//add(g);
	}
}
