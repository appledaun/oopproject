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
	
	// ������.
	DatabaseFrame(String title, int width, int height) {
		super(title, width, height);
		
		initialize();
	}
	
	// ������ �ʱ�ȭ �۾��� �ؾ��Ѵ�.
	protected void initialize() {
		try {
			// �����ͺ��̽� ���� -> ���� �ð��� �����Ƿ� �̸� �ϱ�
			inputDB = new InputDatabase(DatabaseInterface.url, DatabaseInterface.id, DatabaseInterface.pw);
			outputDB = new OutputDatabase(DatabaseInterface.url, DatabaseInterface.id, DatabaseInterface.pw);
		} catch (SQLException error) {
			System.out.println("DB ���� ����: " + error.getMessage());
		}
		// ����� ������.
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// â ũ�� ���� �Ұ�
		setResizable(false);
		
		drawComponents();
	}
	
	// ������ ���� ������Ʈ���� ����� �ٿ��� �Ѵ�.
	protected void drawComponents() {
		JPanel panel;
		JButton databaseInput_btn, databaseOutput_btn;
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		
		databaseInput_btn = new JButton("DB ������ ���");
		databaseInput_btn.setPreferredSize(new Dimension(200, 75));
		databaseInput_btn.addActionListener(this);
		
		databaseOutput_btn = new JButton("DB ������ �ҷ�����");
		databaseOutput_btn.setPreferredSize(new Dimension(200, 75));
		databaseOutput_btn.addActionListener(this);
		
		panel.add(databaseInput_btn);
		panel.add(databaseOutput_btn);
		
		add(panel);
	}
	
	// ���� Ž���⿡�� ������ ������ ��θ� �������� �޼ҵ�
	private String getFilePath() {
		JFileChooser chooser;
		String dataPath = "";
		int ret;
		
		chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		
		// ���� ��� �����͸��� ����
		chooser.setCurrentDirectory(new File("./"));
		
		// ���� Ž��â ���� ����
		chooser.setDialogTitle("csv���� ����");
		
		// ���ϸ� ���ð����ϰ� ����
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		// ���� ���� �߰�
		chooser.setFileFilter(new FileNameExtensionFilter(".csv", "csv"));
		
		// ����� â ����
		ret = chooser.showOpenDialog(null);
		
		// ���� Ŭ��
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
			// ������ ���� ��ü ����
			mData = new ManagementData();
	
			if(btn.getText() == "DB ������ ���") {
				String dataPath;
				
				dataPath = getFilePath();
	
				if(dataPath != "") {
					// csv���Ͽ��� ������ ��������
					mData.loadData(dataPath);
					
					// DB�� ������ ���
					inputDB.loadData(mData.getPlaces());
				}
			} else if(btn.getText() == "DB ������ �ҷ�����") {
				outputDB.loadData(mData.getPlaces());
			}
			
			// ���� â �����
			this.end();
			
			// ���� ������ ����
			window = new CompareFrame("���2", 800, 1000, mData);//new MainFrame("����", 800, 1000, mData);
			
			window.start();
		} catch (IOException error) {
			System.out.println("���� ����� ����: " + error.getMessage());
		}
	}
}
