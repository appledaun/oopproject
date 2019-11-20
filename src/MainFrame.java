import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends DataFrame {
	// 생성자. 프레임의 타이틀, 높이, 너비, 데이터를 설정한다.
	MainFrame(String title, int width, int height, ManagementData mData) {
		// 프레임 타이틀, 너비, 높이 설정
		super(title, width, height, mData);
		
		// 메인 프레임 초기화 작업
		initialize();
	}
	
	// 프레임 초기화작업. 프레임 목록을 생성하고 초기화한다.
	protected void initialize() {
		// 종료할 때 애플리케이션 강제 종료
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// 각 기능화면들을 프레임 목록에 추가한다.
		// 기능1
		frames.add(new MaxMinFrame("기능1", 500, 500, mData));
		
		// 기능2
		frames.add(new CompareFrame("기능2", 500, 500, mData));
		
		// 기능3
		frames.add(new DateFrame("기능3", 500, 500, mData));
		
		drawComponents();
	}
	
	// 해당 프레임을 구성하는 컴포넌트들을 만들고 붙인다.
	protected void drawComponents() {
		JPanel				pMain;
		JPanel				pNorth, pCenter, pSouth;				// 전체화면 구성 패널
		JPanel				cpNorth, cpCenter, cpSouth, cpSouthSub; // 전체화면 구성 패널중 Center부분 구성 패널
		JLabel				projectName_lb, contInfo_lb, contInfoContext_lb, ani_lb, img_lb;	// 프로젝트 이름, 오염물질 정보보기, 오염물질 정보 내용, 애니메이션 라벨, 이미지 라벨
		JButton				rankAll_btn, comparePlace_btn, calendar_btn;				// 전체 순위 보기, 지역별 오염물질 확인, 달력 버튼 
		JComboBox<String>	conts_cb;	// 오염물질 콤보박스
		String[]			cont = { "오염 물질 선택", "이산화질소", "오존", "일산화탄소", "아황산가스", "미세먼지", "초미세먼지" };		// 오염물질 콤보박스에 들어갈 항목
		JTable				table;
		String				colText[] = {"대기오염수치 높은 지역 top3", "대기오염수치 낮은 지역 top3"};						// 테스트 용
		String				rowText[][] = { {"1. 강남", "1. 서초"}, {"2. 강북", "2. 강동"}, {"3. 용산", "3. 노원"}, };	// 테스트 용
		JScrollPane			scroll;
		
		///////////////////////////////////////////
		// 패널 초기화
		pMain = new JPanel(new BorderLayout());
		pNorth	= new JPanel();
		pCenter	= new JPanel(new BorderLayout());
		pSouth	= new JPanel();
		
		// Center부분 구성 패널
		cpNorth		= new JPanel(new BorderLayout());
		cpCenter	= new JPanel(new BorderLayout());
		
		cpSouth		= new JPanel(new GridLayout(1, 2));
		
		cpSouthSub	= new JPanel(new BorderLayout());
		///////////////////////////////////////////
		
		///////////////////////////////////////////
		// 라벨 초기화
		projectName_lb		= new JLabel("오주의 마법사");
		contInfo_lb			= new JLabel("▶ 알고 싶은 오염물질을 선택하세요!");
		contInfoContext_lb	= new JLabel("~");
		
		ani_lb 				= new JLabel("애니메이션");
		ani_lb.setPreferredSize(new Dimension(getSize().width, 100));		
		ani_lb.setHorizontalAlignment(JLabel.CENTER);
		
		img_lb				= new JLabel("", new ImageIcon("./고객팀프초기화면2.png"), JLabel.CENTER);
		///////////////////////////////////////////
		
		///////////////////////////////////////////
		// 버튼 초기화		
		rankAll_btn			= new JButton("전체 순위 보기");
		rankAll_btn.setPreferredSize(new Dimension(150, 20));
	
		comparePlace_btn	= new JButton("지역별 대기오염물질 확인");
		comparePlace_btn.setPreferredSize(new Dimension((getSize().width) - 100, 70));
		
		calendar_btn		= new JButton("달력");
		calendar_btn.setPreferredSize(new Dimension(90, 90));
		calendar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 달력 프레임 띄우기
				for(Frame f : frames) {
					if(f instanceof DateFrame) {
						f.start();
					}
				}
			}
		});
		///////////////////////////////////////////
		
		///////////////////////////////////////////
		// 콤보박스 초기화
		conts_cb = new JComboBox<String>(cont);
		///////////////////////////////////////////
		
		///////////////////////////////////////////
		// 테이블 초기화
		table = new JTable(rowText, colText);
		///////////////////////////////////////////
		
		///////////////////////////////////////////
		// 스크롤 페인 초기화
		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(250, 150));   //기능1 테이블 생성
		///////////////////////////////////////////
		
		///////////////////////////////////////////
		// 전체화면 North 부분 컴포넌트 추가
		pNorth.add(projectName_lb);
		///////////////////////////////////////////
		
		///////////////////////////////////////////
		// 전체화면 Center의 North 부분 컴포넌트 추가 
		cpNorth.add(scroll, BorderLayout.NORTH);
		cpNorth.add(rankAll_btn, BorderLayout.EAST);
		
		// 전체화면 Center의 Center 부분 컴포넌트 추가 
		cpCenter.add(comparePlace_btn, BorderLayout.NORTH);
		cpCenter.add(calendar_btn, BorderLayout.CENTER);
		cpCenter.add(img_lb, BorderLayout.SOUTH);
		
		// 전체화면 Center의 South 부분 컴포넌트 추가 
		cpSouthSub.add(contInfo_lb,BorderLayout.NORTH);
		cpSouthSub.add(conts_cb,BorderLayout.CENTER);
		cpSouthSub.add(contInfoContext_lb,BorderLayout.SOUTH);
		
		cpSouth.add(cpSouthSub);
		
		// 전체화면 Center 부분 컴포넌트 추가
		pCenter.add(cpNorth, BorderLayout.NORTH);   
		pCenter.add(cpCenter, BorderLayout.CENTER);   
		pCenter.add(cpSouth, BorderLayout.SOUTH);
		///////////////////////////////////////////
		
		///////////////////////////////////////////
		// 전체화면 South 부분 컴포넌트 추가
		pSouth.add(ani_lb);
		///////////////////////////////////////////
		
		///////////////////////////////////////////
		// 메인 패널에 하위 패널 추가
		pMain.add(pNorth, BorderLayout.NORTH);
		pMain.add(pCenter, BorderLayout.CENTER);
		pMain.add(pSouth, BorderLayout.SOUTH);
		///////////////////////////////////////////
		
		add(pMain);
		
		/*
		/////////////////////////////////
		// 테스트 코드
		// 나중에 디자인에 맞게 설정할 것
		/////////////////////////////////
		setLayout(new BorderLayout());
		
		JPanel p = new JPanel();
		
		p.setLayout(new GridLayout(1, 3));
		p.setPreferredSize(new Dimension(1, 300));
		
		ArrayList<JButton> btns = new ArrayList<JButton>();
		
		btns.add(new JButton("기능1"));
		btns.add(new JButton("기능2"));
		btns.add(new JButton("기능3"));
		
		for(JButton btn : btns) {
			
			// 리스너 등록
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JButton btn = (JButton)e.getSource();
					
					// 해당 기능 창 띄우기
					for(Frame f : frames) {
						if(f.getTitle().equals(btn.getText())) {
							f.start();
							
							break;
						}
					}
				}
			});
			
			p.add(btn);
		}
			
		add(p, BorderLayout.SOUTH);
		/////////////////////////////////
		*/
	}
	
	// 일단은 주석처리.. 당장 필요 없을 것 같음
	// 데이터 설정
	private void setData(ManagementData mData) {
		this.mData = mData;
	}
}
