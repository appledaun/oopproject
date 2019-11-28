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
 * ������ ������� ������ �����Ϸ� ���ϱ� ������ ���״�� ���Ǳ� ���ǻ� ������ 
 * ���α� �õ� ��õ�� ���� ����� ������ ������ ���빮�� ���۱� ���۴�� 
 * ������ ���ѻ� ���빮�� ���ʱ� ������ ���ϱ� ���� ���ı� ������ ���̷� 
 * ��õ�� �������� �������� ��걸 ���� ������ ���� ���α� �߱� �߶��� 
 * õȣ��� û��õ�� �Ѱ���� ���� ȫ���� ȭ����
 * 46
 * 
 */
public class CompareFrame extends DataFrame {
	private JPanel 						pMain;							//��üȭ�� �г�
	private JPanel 						pPlace, pCont, pPlaceScroll;	//���� �г�, �������� �г�, ���� ����Ʈ �г�
	private JLabel						place_lb, contaminant_lb;		//���� ���̺�, �������� ���̺� 
	private JCheckBox[] 				contaminant;					//�������� üũ�ڽ�
	private DefaultListModel<String> 	place;							
	private JList<String> 				placeList;						//���� ����Ʈ
	private JScrollPane 				scrollPane, graphScroll;		//���� ��ũ��, �׷��� ��ũ��
	private GraphPanel 					pGraph;							//�׷��� �г�
	
    Place[] arrPlace = new Place[39];	//���� �迭(40�� ������ ���������� ��� �� ����)
    int[] cntCont = {0, 0, 0, 0, 0, 0};
    
    

	//�������� üũ�ڽ� ������
	private class MyItemListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	//���� ����Ʈ ������
	private class listListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub

		}
	}
	
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
	
	//��ü �����͸� �������� ����.
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
	
	// �ش� �������� �����ϴ� ������Ʈ���� ����� ���δ�.
	protected void drawComponents() {
		
		//�г� �ʱ�ȭ
		pMain = new JPanel();
		pPlace = new JPanel();
		pCont = new JPanel();
		pGraph = new GraphPanel(000, 400);
		place_lb = new JLabel("����");
		contaminant_lb = new JLabel("��������");
		///////////////////////////////////////////////////////////////////
		
		//�������� üũ�ڽ� �����
		String contArr[] = {"�̻�ȭ����", "����", "�ϻ�ȭź��", "��Ȳ�갡��", "�̼�����", "�ʹ̼�����"};
		contaminant = new JCheckBox[contArr.length];
		int cnt = 0;
		for(String s : contArr) {
			contaminant[cnt++] = new JCheckBox(s, true);
		}
		//////////////////////////////////////////////////////////////////
		
		//���� ����Ʈ �����
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
	    
	    //������ �������� ����
	    int i = 0;
	    for(Place p : mData.getPlaces()) {	//�ʱ�ȭ ���� �ӽ� ������
	    	if(i == 39) break;
	    	arrPlace[i++] = new Place(p.getName(), p.getDate(), p.getContaminant());
	    	System.out.println(p.getName());
	    }
	    for(Place p : arrPlace) {	//��� �������� 0���� �ʱ�ȭ
	    	p.getContaminant().COValue = 0;
	    	p.getContaminant().NO2Value = 0;
	    	p.getContaminant().O3Value = 0;
	    	p.getContaminant().PM10Value = 0;
	    	p.getContaminant().PM25Value = 0;
	    	p.getContaminant().SO2Value = 0;
	    }
	    for(i = 0;i<mData.getPlaces().size();i++) {	//���� ���� �������� ���
	    	calcPlaceData(i);
	    }
	    for(Place p : arrPlace) {	
	    	p.getContaminant().COValue /= 309.0;
	    	p.getContaminant().NO2Value /= 309.0;
	    	p.getContaminant().O3Value /= 309.0;
	    	p.getContaminant().PM10Value /= 309.0;
	    	p.getContaminant().PM25Value /= 309.0;
	    	p.getContaminant().SO2Value /= 309.0;
	    	//�Ҽ��� 3�ڸ� ������ �ݿø� �ڸ���
	    	p.getContaminant().COValue = ((Math.round((p.getContaminant().COValue)*1000))/1000.0);
	    	p.getContaminant().NO2Value = ((Math.round((p.getContaminant().NO2Value)*1000))/1000.0);
	    	p.getContaminant().O3Value = ((Math.round((p.getContaminant().O3Value)*1000))/1000.0);
	    	//p.getContaminant().PM10Value = Math.round(((p.getContaminant().COValue)*1000)/1000.0);
	    	//p.getContaminant().PM25Value = Math.round(((p.getContaminant().COValue)*1000)/1000.0);
	    	p.getContaminant().SO2Value = ((Math.round((p.getContaminant().SO2Value)*1000))/1000.0);	
	    }
	    /////////////////////////////////////////////////////
	    
	    //�׷��� �����
	    for(Place p : arrPlace) {
	    	pGraph.addGraph(new Graph(p));
	    }
	    graphScroll = new JScrollPane(pGraph);
	    graphScroll.setViewportView(pGraph);
	    graphScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	       
	    
	    //ȭ�� ������
		
		
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
