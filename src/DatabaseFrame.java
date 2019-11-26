import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.Dimension;

public class DatabaseFrame extends Frame implements ActionListener {
	InputDatabase	inputDB;
	OutputDatabase	outputDB;
	
	// 생성자.
	DatabaseFrame(String title, int width, int height) {
		super(title, width, height);
		
		initialize();
	}
	
	// 프레임 초기화 작업을 해야한다.
	protected void initialize() {
		try {
			// 데이터베이스 연결 -> 연결 시간이 있으므로 미리 하기
			inputDB = new InputDatabase(DatabaseInterface.url, DatabaseInterface.id, DatabaseInterface.pw);
			outputDB = new OutputDatabase(DatabaseInterface.url, DatabaseInterface.id, DatabaseInterface.pw);
		} catch (SQLException error) {
			System.out.println("DB 연결 실패: " + error.getMessage());
		}
		// 종료시 끝낸다.
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// 창 크기 설정 불가
		setResizable(false);
		
		drawComponents();
	}
	
	// 프레임 구성 컴포넌트들을 만들고 붙여야 한다.
	protected void drawComponents() {
		JPanel panel;
		JButton databaseInput_btn, databaseOutput_btn;
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		
		databaseInput_btn = new JButton("DB 데이터 등록");
		databaseInput_btn.setPreferredSize(new Dimension(200, 75));
		databaseInput_btn.addActionListener(this);
		
		databaseOutput_btn = new JButton("DB 데이터 불러오기");
		databaseOutput_btn.setPreferredSize(new Dimension(200, 75));
		databaseOutput_btn.addActionListener(this);
		
		panel.add(databaseInput_btn);
		panel.add(databaseOutput_btn);
		
		add(panel);
	}
	
	// 파일 탐색기에서 선택한 파일의 경로를 가져오는 메소드
	private String getFilePath() {
		JFileChooser chooser;
		String dataPath = "";
		int ret;
		
		chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		
		// 현재 사용 디텍터리로 설정
		chooser.setCurrentDirectory(new File("./"));
		
		// 파일 탐색창 제목 설정
		chooser.setDialogTitle("csv파일 선택");
		
		// 파일만 선택가능하게 설정
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		// 파일 필터 추가
		chooser.setFileFilter(new FileNameExtensionFilter(".csv", "csv"));
		
		// 열기용 창 띄우기
		ret = chooser.showOpenDialog(null);
		
		// 열기 클릭
		if(ret == JFileChooser.APPROVE_OPTION) {
			dataPath = chooser.getSelectedFile().toString();
		}
		
		return dataPath;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ManagementData mData;
		Frame window;
		JButton btn;
		
		btn = (JButton)e.getSource();
		
		try {
			// 데이터 관리 객체 생성
			mData = new ManagementData();
	
			if(btn.getText() == "DB 데이터 등록") {
				String dataPath;
				
				dataPath = getFilePath();
	
				if(dataPath != "") {
					// csv파일에서 데이터 가져오기
					mData.loadData(dataPath);
					
					// DB에 데이터 등록
					inputDB.loadData(mData.getPlaces());
				}
			} else if(btn.getText() == "DB 데이터 불러오기") {
				outputDB.loadData(mData.getPlaces());
			}
			
			// 현재 창 숨기기
			this.end();
			
			// 메인 프레임 생성
			window = new CompareFrame("기능2", 800, 1000, mData);//new MainFrame("메인", 800, 1000, mData);
			
			window.start();
		} catch (IOException error) {
			System.out.println("파일 입출력 실패: " + error.getMessage());
		}
	}
}
