import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DateFrame extends DataFrame {
	// 생성자. 
	DateFrame(String title, int width, int height, ManagementData mData) {
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
		JPanel p;
		CalendarFrame c = new CalendarFrame("달력", 400, 400);
		JButton btn = new JButton("달력");
		
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton)e.getSource();

				for(Frame f : frames) {
					if(f.getTitle().equals(btn.getText())) {
						f.start();
					}
				}
			}
		});
		
		frames.add(c);
		
		p = new JPanel();
		p.add(btn);
		
		add(p);
	}
}
