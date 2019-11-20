import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class ManagementDatabase {
	private String				url;				// DB 접속 주소
	private String				id;					// DB 로그인 Id
	private String				pw;					// DB 로그인 Password
	private static Connection	connection = null;	// DB 연결 정보
	
	// 생성자. url, id, pw 초기화
	ManagementDatabase(String url, String id, String pw) throws SQLException {
		this.url = url;
		this.id = id;
		this.pw = pw;
		
		initDatabase();
	}
	
	// Database 연결
	private void initDatabase() throws SQLException {
		// 아직 연결이 생성되지 않았을 때만 실행하기
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
	
	// 연결 정보 받아오기
	public Connection getConnection() {
		return connection;
	}
	
	// 상속받은 쪽에서 DB에 데이터를 저장할지, DB에서 데이터를 불러올지 구현하기.
	public abstract void loadData(ArrayList<Place> places);
}
