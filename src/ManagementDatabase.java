import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class ManagementDatabase {
	private String				url;				// DB ���� �ּ�
	private String				id;					// DB �α��� Id
	private String				pw;					// DB �α��� Password
	private static Connection	connection = null;	// DB ���� ����
	
	// ������. url, id, pw �ʱ�ȭ
	ManagementDatabase(String url, String id, String pw) throws SQLException {
		this.url = url;
		this.id = id;
		this.pw = pw;
		
		initDatabase();
	}
	
	// Database ����
	private void initDatabase() throws SQLException {
		// ���� ������ �������� �ʾ��� ���� �����ϱ�
		if(connection == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				System.out.println("Success Driver Load");
			} catch(ClassNotFoundException e) {
				System.out.println("Fail Driver Load: " + e.getMessage());
			}
		
			connection = DriverManager.getConnection(url, id, pw);
		}
	}
	
	// ���� ���� �޾ƿ���
	public Connection getConnection() {
		return connection;
	}
	
	// ��ӹ��� �ʿ��� DB�� �����͸� ��������, DB���� �����͸� �ҷ����� �����ϱ�.
	public abstract void loadData(ArrayList<Place> places);
}
