import javax.swing.JFrame;
import java.util.ArrayList;

// 창을 표현하고, 데이터 저장에 필요한 최소한의 정보를 설정하는 클래스 
public abstract class Frame extends JFrame {
	protected ArrayList<Frame> frames;	// 프레임 목록
	
	// 생성자. 타이틀을 설정하고, 윈도우 창 크기, 데이터를 설정한다.
	Frame(String title, int width, int height) {
		setTitle(title);
		setSize(width, height);
		
		frames = new ArrayList<Frame>(); // 프레임 목록 초기화
	}
	
	protected abstract void			initialize();		// 프레임 초기화 작업을 해야한다.
	protected abstract void			drawComponents();	// 프레임 구성 컴포넌트들을 만들고 붙여야 한다.
	
	// 프레임을 보여준다.
	public void start() {
		setVisible(true);
	}
	
	// 프레임을 숨긴다.
	public void end() {
		setVisible(false);
	}
}