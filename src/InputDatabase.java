import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class InputDatabase extends ManagementDatabase {
	InputDatabase(String url, String id, String pw) throws SQLException {
		// 상속한 클래스 생성자 호출
		super(url, id, pw);
	}

	// DB에 데이터를 저장하는 메소드
	public void loadData(ArrayList<Place> places) {
		StringBuilder sql = new StringBuilder(); 
		PreparedStatement pstmt;
		
		sql.append("INSERT INTO Contaminant (Date, Place, NO2, O3, CO, SO2, PM10, PM25)");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		
		try {
			pstmt = getConnection().prepareStatement(sql.toString());
			
			// 데이터를 하나씩 뽑아 DB에 등록한다.
			for(Place p: places) {
				pstmt.setString(1, p.getDate().toString());
				pstmt.setString(2, p.getName());
				pstmt.setString(3, Double.toString(p.getContaminant().NO2Value));
				pstmt.setString(4, Double.toString(p.getContaminant().O3Value));
				pstmt.setString(5, Double.toString(p.getContaminant().COValue));
				pstmt.setString(6, Double.toString(p.getContaminant().SO2Value));
				pstmt.setString(7, Integer.toString(p.getContaminant().PM10Value));
				pstmt.setString(8, Integer.toString(p.getContaminant().PM25Value));
				
				pstmt.execute();
			}
			
			pstmt.close();
		} catch(SQLException e) {
			System.out.println("DB 데이터 등록 실패: " + e.getMessage());
		}
	}
}
