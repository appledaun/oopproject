import javax.swing.JFrame;
import java.util.ArrayList;

// â�� ǥ���ϰ�, ������ ���忡 �ʿ��� �ּ����� ������ �����ϴ� Ŭ���� 
public abstract class Frame extends JFrame {
	protected ArrayList<Frame> frames;	// ������ ���
	
	// ������. Ÿ��Ʋ�� �����ϰ�, ������ â ũ��, �����͸� �����Ѵ�.
	Frame(String title, int width, int height) {
		setTitle(title);
		setSize(width, height);
		
		frames = new ArrayList<Frame>(); // ������ ��� �ʱ�ȭ
	}
	
	protected abstract void			initialize();		// ������ �ʱ�ȭ �۾��� �ؾ��Ѵ�.
	protected abstract void			drawComponents();	// ������ ���� ������Ʈ���� ����� �ٿ��� �Ѵ�.
	
	// �������� �����ش�.
	public void start() {
		setVisible(true);
	}
	
	// �������� �����.
	public void end() {
		setVisible(false);
	}
}