import java.awt.Container;
import java.awt.*;

import javax.swing.*;

public class CompareFrame extends DataFrame {
	private JPanel p1, p2;	//��üȭ�� �г�
	private JPanel p3, p4, p5;
	
	private JLabel _place, _contaminant;
	
	private JCheckBox[] contaminant;
	private DefaultListModel<String> place;
	private JList placeList;
	//private JComboBox<String> place;
	private JScrollPane scrollPane;
	
	private JLabel _graph;
	// ������. 
	CompareFrame(String title, int width, int height, ManagementData mData) {
		super(title, width, height, mData);
		
		initialize();
	}
	
	// ������ �ʱ�ȭ�۾�.
	protected void initialize() {
		// ���� �����ӿ����� �ǹǷ� ���� �� ����� �Ѵ�.
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		drawComponents();
	}
	
	// �ش� �������� �����ϴ� ������Ʈ���� ����� ���δ�.
	protected void drawComponents() {
		
		_graph = new JLabel("�׷���");
		_place = new JLabel("����");
		_contaminant = new JLabel("��������");
		
		//�������� üũ�ڽ�
		String exa[] = {"�̻�ȭ����", "����", "�ϻ�ȭź��", "��Ȳ�갡��", "�̼�����", "�ʹ̼�����"};
		contaminant = new JCheckBox[exa.length];
		int cnt = 0;
		for(String s : exa) {
			contaminant[cnt++] = new JCheckBox(s, true);
		}
		
		//���� ������
		String[] exam = {"������", "������", "������", "���ϱ�", "������", "������",
				"������", "���ϱ�", "������", "������", "������", "���ϱ�", "������", "������", 
				"������", "���ϱ�", "������", "������", "������", "���ϱ�"};
		place = new DefaultListModel<>();	
		for(String s : exam) {
			place.addElement(s);
		}
		placeList = new JList<String>(place); 
		scrollPane = new JScrollPane(placeList);
	    scrollPane.setViewportView(placeList);
	    placeList.setLayoutOrientation(JList.VERTICAL);
	    scrollPane.setPreferredSize(new Dimension(300, 100));
	    p5 = new JPanel(new BorderLayout());
	    p5.add(scrollPane);
		/*
		place = new JComboBox<String>();	place.setPreferredSize(new Dimension(300,20));
		for(String s : exam) {
			place.addItem(s);
		}
		*/
	    
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		
		p3.add(_place); p3.add(p5);
		p4.setLayout(new GridLayout(2, 4));
		p4.add(_contaminant);
		for(JCheckBox ch : contaminant) {
			p4.add(ch);
		}
		
		p1.setLayout(new FlowLayout());
		p1.add(p3);
		p1.add(p4);
		
		
		Container cont = getContentPane();
		
		setTitle("PROJECT");
		setSize(400, 600);
	
		
		cont.setLayout(new GridLayout(2, 1));
		
		cont.add(p1);
		cont.add(_graph);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
