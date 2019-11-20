import java.awt.Container;
import java.awt.*;

import javax.swing.*;

public class CompareFrame extends DataFrame {
	private JPanel p1, p2;	//전체화면 패널
	private JPanel p3, p4, p5;
	
	private JLabel _place, _contaminant;
	
	private JCheckBox[] contaminant;
	private DefaultListModel<String> place;
	private JList placeList;
	//private JComboBox<String> place;
	private JScrollPane scrollPane;
	
	private JLabel _graph;
	// 생성자. 
	CompareFrame(String title, int width, int height, ManagementData mData) {
		super(title, width, height, mData);
		
		initialize();
	}
	
	// 프레임 초기화작업.
	protected void initialize() {
		// 하위 프레임역할이 되므로 종료 시 숨기게 한다.
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		drawComponents();
	}
	
	// 해당 프레임을 구성하는 컴포넌트들을 만들고 붙인다.
	protected void drawComponents() {
		
		_graph = new JLabel("그래프");
		_place = new JLabel("지역");
		_contaminant = new JLabel("오염물질");
		
		//오염물질 체크박스
		String exa[] = {"이산화질소", "오존", "일산화탄소", "아황산가스", "미세먼지", "초미세먼지"};
		contaminant = new JCheckBox[exa.length];
		int cnt = 0;
		for(String s : exa) {
			contaminant[cnt++] = new JCheckBox(s, true);
		}
		
		//지역 셀렉션
		String[] exam = {"강남구", "강서구", "강동구", "강북구", "강남구", "강서구",
				"강동구", "강북구", "강남구", "강서구", "강동구", "강북구", "강남구", "강서구", 
				"강동구", "강북구", "강남구", "강서구", "강동구", "강북구"};
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
