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

/*
 * 강남구 강남대로 강동구 강변북로 강북구 강서구 공항대로 관악구 관악산 광진구 
 * 구로구 궁동 금천구 남산 노원구 도봉구 도산대로 동대문구 동작구 동작대로 
 * 마포구 북한산 서대문구 서초구 성동구 성북구 세곡 송파구 시흥대로 신촌로 
 * 양천구 영등포구 영등포로 용산구 은평구 정릉로 종로 종로구 중구 중랑구 
 * 천호대로 청계천로 한강대로 행주 홍릉로 화랑로
 * 46
 * 
 */
public class CompareFrame extends DataFrame {
	private JPanel 						pMain;							//전체화면 패널
	private JPanel 						pPlace, pCont, pPlaceScroll;	//지역 패널, 오염물질 패널, 지역 리스트 패널
	private JLabel						place_lb, contaminant_lb;		//지역 레이블, 오염물질 레이블 
	private JCheckBox[] 				contaminant;					//오염물질 체크박스
	private DefaultListModel<String> 	place;							
	private JList<String> 				placeList;						//지역 리스트
	private JScrollPane 				scrollPane, graphScroll;		//지역 스크롤, 그래프 스크롤
	private GraphPanel 					pGraph;							//그래프 패널
	
    Place[] arrPlace = new Place[39];	//지역 배열(40개 지역의 오염물질별 평균 값 가짐)
    int[] cntCont = {0, 0, 0, 0, 0, 0};
    
    

	//오염물질 체크박스 리스너
	private class MyItemListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	//지역 리스트 리스너
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
	
	//전체 데이터를 지역별로 정리.
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
		
		//패널 초기화
		pMain = new JPanel();
		pPlace = new JPanel();
		pCont = new JPanel();
		pGraph = new GraphPanel(000, 400);
		place_lb = new JLabel("지역");
		contaminant_lb = new JLabel("오염물질");
		///////////////////////////////////////////////////////////////////
		
		//오염물질 체크박스 만들기
		String contArr[] = {"이산화질소", "오존", "일산화탄소", "아황산가스", "미세먼지", "초미세먼지"};
		contaminant = new JCheckBox[contArr.length];
		int cnt = 0;
		for(String s : contArr) {
			contaminant[cnt++] = new JCheckBox(s, true);
		}
		//////////////////////////////////////////////////////////////////
		
		//지역 리스트 만들기
		place = new DefaultListModel<>();	
		for(Place p : mData.getPlaces()) {
			place.addElement(p.getName());
		}
		placeList = new JList<String>(place); 
		scrollPane = new JScrollPane(placeList);
	    scrollPane.setViewportView(placeList);
	    placeList.setLayoutOrientation(JList.VERTICAL);
	    scrollPane.setPreferredSize(new Dimension(300, 100));
	    pPlaceScroll = new JPanel(new BorderLayout());
	    pPlaceScroll.add(scrollPane);
	    ////////////////////////////////////////////////////////////////////	
	    
	    //지역별 오염물질 정리
	    int i = 0;
	    for(Place p : mData.getPlaces()) {	//초기화 위한 임시 데이터
	    	if(i == 39) break;
	    	arrPlace[i++] = new Place(p.getName(), p.getDate(), p.getContaminant());
	    	System.out.println(p.getName());
	    }
	    for(Place p : arrPlace) {	//모든 오염물질 0으로 초기화
	    	p.getContaminant().COValue = 0;
	    	p.getContaminant().NO2Value = 0;
	    	p.getContaminant().O3Value = 0;
	    	p.getContaminant().PM10Value = 0;
	    	p.getContaminant().PM25Value = 0;
	    	p.getContaminant().SO2Value = 0;
	    }
	    for(i = 0;i<mData.getPlaces().size();i++) {	//지역 별로 오염물질 계산
	    	calcPlaceData(i);
	    }
	    for(Place p : arrPlace) {	
	    	p.getContaminant().COValue /= 309.0;
	    	p.getContaminant().NO2Value /= 309.0;
	    	p.getContaminant().O3Value /= 309.0;
	    	p.getContaminant().PM10Value /= 309.0;
	    	p.getContaminant().PM25Value /= 309.0;
	    	p.getContaminant().SO2Value /= 309.0;
	    	//소수점 3자리 밑으로 반올림 자르기
	    	p.getContaminant().COValue = ((Math.round((p.getContaminant().COValue)*1000))/1000.0);
	    	p.getContaminant().NO2Value = ((Math.round((p.getContaminant().NO2Value)*1000))/1000.0);
	    	p.getContaminant().O3Value = ((Math.round((p.getContaminant().O3Value)*1000))/1000.0);
	    	//p.getContaminant().PM10Value = Math.round(((p.getContaminant().COValue)*1000)/1000.0);
	    	//p.getContaminant().PM25Value = Math.round(((p.getContaminant().COValue)*1000)/1000.0);
	    	p.getContaminant().SO2Value = ((Math.round((p.getContaminant().SO2Value)*1000))/1000.0);	
	    }
	    /////////////////////////////////////////////////////
	    
	    //그래프 만들기
	    for(Place p : arrPlace) {
	    	pGraph.addGraph(new Graph(p));
	    }
	    graphScroll = new JScrollPane(pGraph);
	    graphScroll.setViewportView(pGraph);
	    graphScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	       
	    
	    //화면 디자인
		
		
		pPlace.add(place_lb); pPlace.add(pPlaceScroll);
		pCont.setLayout(new GridLayout(1, 7));
		pCont.add(contaminant_lb);
		for(JCheckBox ch : contaminant) {
			pCont.add(ch);
		}
		
		pMain.setLayout(new FlowLayout());
		pMain.add(pPlace);
		pMain.add(pCont);
		
		Container cont = getContentPane();
		
		setTitle("PROJECT");
		//setSize(400, 600);
		
		cont.setLayout(new GridLayout(2, 1));
		
		cont.add(pMain);
		cont.add(graphScroll);
		

		MyItemListener itemListener = new MyItemListener();
		for(JCheckBox c : contaminant) {
			c.addItemListener(itemListener);
		}
		listListener listListener = new listListener();
		placeList.addListSelectionListener(listListener);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
