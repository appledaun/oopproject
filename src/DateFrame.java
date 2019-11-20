import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DateFrame extends DataFrame {
	// ������. 
	DateFrame(String title, int width, int height, ManagementData mData) {
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
		JPanel p;
		CalendarFrame c = new CalendarFrame("�޷�", 400, 400);
		JButton btn = new JButton("�޷�");
		
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
