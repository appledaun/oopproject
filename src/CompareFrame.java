import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.FlatteningPathIterator;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class CompareFrame extends DataFrame {
	private JPanel p1, p2;	//전체화면 패널
	private JPanel p3, p4, p5;
	
	private JLabel _place, _contaminant;
	
	private JCheckBox[] contaminant;
	private DefaultListModel<String> place;
	private JList<String> placeList;
	//private JComboBox<String> place;
	private JScrollPane scrollPane, graphScroll;
	
	private JPanel[] _graph;
	private JPanel pGraph;
	
    Place[] arrPlace = new Place[39];
    
    

	
	private class itemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class listListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
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
	
	private void calcPlaceData(int i) {
		if(mData.getPlaces().get(i).getContaminant().NO2Value == -1)	mData.getPlaces().get(i).getContaminant().NO2Value = 0.03256;
		arrPlace[i%39].getContaminant().NO2Value += mData.getPlaces().get(i).getContaminant().NO2Value;
		
		if(mData.getPlaces().get(i).getContaminant().COValue == -1)		mData.getPlaces().get(i).getContaminant().COValue = 0.5527;
		arrPlace[i%39].getContaminant().COValue += mData.getPlaces().get(i).getContaminant().COValue;
		
		if(mData.getPlaces().get(i).getContaminant().O3Value == -1)		mData.getPlaces().get(i).getContaminant().O3Value = 0.01933;
		arrPlace[i%39].getContaminant().O3Value += mData.getPlaces().get(i).getContaminant().O3Value;
		
		if(mData.getPlaces().get(i).getContaminant().SO2Value == -1)	mData.getPlaces().get(i).getContaminant().SO2Value = 0.00448;
		arrPlace[i%39].getContaminant().SO2Value += mData.getPlaces().get(i).getContaminant().SO2Value;
		
		if(mData.getPlaces().get(i).getContaminant().PM10Value == -1)	mData.getPlaces().get(i).getContaminant().PM10Value = 41;
		arrPlace[i%39].getContaminant().PM10Value += mData.getPlaces().get(i).getContaminant().PM10Value;
		
		if(mData.getPlaces().get(i).getContaminant().PM25Value == -1)	mData.getPlaces().get(i).getContaminant().PM25Value = 23;
		arrPlace[i%39].getContaminant().PM25Value += mData.getPlaces().get(i).getContaminant().PM25Value;
	}
	
	// 해당 프레임을 구성하는 컴포넌트들을 만들고 붙인다.
	protected void drawComponents() {
		
		_graph = new JPanel[39];
		for(JPanel p : _graph) {
			p = new JPanel();
		}
		pGraph = new JPanel();
		_place = new JLabel("지역");
		_contaminant = new JLabel("오염물질");
		
		//오염물질 체크박스
		String contArr[] = {"이산화질소", "오존", "일산화탄소", "아황산가스", "미세먼지", "초미세먼지"};
		contaminant = new JCheckBox[contArr.length];
		int cnt = 0;
		for(String s : contArr) {
			contaminant[cnt++] = new JCheckBox(s, true);
		}
		
		//지역 셀렉션
		place = new DefaultListModel<>();	
		for(Place p : mData.getPlaces()) {
			place.addElement(p.getName());
		}
		placeList = new JList<String>(place); 
		scrollPane = new JScrollPane(placeList);
	    scrollPane.setViewportView(placeList);
	    placeList.setLayoutOrientation(JList.VERTICAL);
	    scrollPane.setPreferredSize(new Dimension(300, 100));
	    p5 = new JPanel(new BorderLayout());
	    p5.add(scrollPane);
	    ////////////////////////////////////////////////////////////////////	
	    
	    //지역배열 데이터 초기화
	    int i = 0;
	    for(Place p : mData.getPlaces()) {
	    	if(i == 39) break;
	    	arrPlace[i++] = new Place(p.getName(), p.getDate(), p.getContaminant());
	    	System.out.println(p.getName());
	    }
	    for(Place p : arrPlace) {
	    	p.getContaminant().COValue = 0;
	    	p.getContaminant().NO2Value = 0;
	    	p.getContaminant().O3Value = 0;
	    	p.getContaminant().PM10Value = 0;
	    	p.getContaminant().PM25Value = 0;
	    	p.getContaminant().SO2Value = 0;
	    }
	    ///////////////////////////////////////////////////
	    
	    //지역별 오염물질 계산
	    for(i = 0;i<mData.getPlaces().size();i++) {
	    	calcPlaceData(i);
	    }
	    
	    for(Place p : arrPlace) {
	    	p.getContaminant().COValue /= 310.0;
	    	p.getContaminant().NO2Value /= 310.0;
	    	p.getContaminant().O3Value /= 310.0;
	    	p.getContaminant().PM10Value /= 310.0;
	    	p.getContaminant().PM25Value /= 310.0;
	    	p.getContaminant().SO2Value /= 310.0; 
	    	
	    	//소수점 3자리 밑으로 반올림 자르기
	    	p.getContaminant().COValue = ((Math.round((p.getContaminant().COValue)*1000))/1000.0);
	    	p.getContaminant().NO2Value = ((Math.round((p.getContaminant().NO2Value)*1000))/1000.0);
	    	p.getContaminant().O3Value = ((Math.round((p.getContaminant().O3Value)*1000))/1000.0);
	    	//p.getContaminant().PM10Value = Math.round(((p.getContaminant().COValue)*1000)/1000.0);
	    	//p.getContaminant().PM25Value = Math.round(((p.getContaminant().COValue)*1000)/1000.0);
	    	p.getContaminant().SO2Value = ((Math.round((p.getContaminant().SO2Value)*1000))/1000.0);
	    	
	    }
	    
	    for(Place p : arrPlace) {
	    	System.out.println(p.getContaminant().COValue);
	    }
	    /////////////////////////////////////////////////////
	    
	    graph = new ArrayList<Graph>(); i = 1;
	    for(Place p : arrPlace) {
	    	graph.add(new Graph(p,400, 900));
	    	System.out.println(i++);
	    }
	    
	    for(Graph g : graph) {
	    	//_graph[i] = new Graph(g);
	    }
	    //graphScroll = new JScrollPane(_graph);
	    //graphScroll.setViewportView(_graph);
	    //graphScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	       
	    _graph[0] = new Graph(arrPlace[0], 100, 100);
	    _graph[1] = new Graph(arrPlace[3], 300, 300);
	    _graph[2] = new Graph(arrPlace[4], 400, 400);
	    //_graph.revalidate();
	    //_graph.repaint();
	    
	    //화면 디자인
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		
		p3.add(_place); p3.add(p5);
		p4.setLayout(new GridLayout(1, 7));
		p4.add(_contaminant);
		for(JCheckBox ch : contaminant) {
			p4.add(ch);
		}
		
		p1.setLayout(new FlowLayout());
		p1.add(p3);
		p1.add(p4);
		
		pGraph.setLayout(new FlowLayout());
		pGraph.add(_graph[0]);
		pGraph.add(_graph[1]);
		pGraph.add(_graph[2]);
		
		
		Container cont = getContentPane();
		
		setTitle("PROJECT");
		//setSize(400, 600);
	
		
		cont.setLayout(new GridLayout(2, 1));
		
		cont.add(p1);
		cont.add(_graph[0]);
		

		itemListener listener = new itemListener();
		contaminant[0].addActionListener(null);
		listListener list = new listListener();
		placeList.addListSelectionListener(list);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
