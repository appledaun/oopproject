import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OutputDatabase extends ManagementDatabase {
	OutputDatabase(String url, String id, String pw) throws SQLException {
		// 상속한 클래스 생성자 호출
		super(url, id, pw);
	}
	
	// DB에서 프로그램으로 데이터를 불러오는 메소드
	public void loadData(ArrayList<Place> places) {
		String sql;
		PreparedStatement pstmt;
		ResultSet rs;
		
		sql = "SELECT * FROM Contaminant";
		
		try {
			pstmt = getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			// 모든 항목을 가져온다
			while(rs.next()) {
				Place p;
				Contaminant cont;
				LocalDate date;
				
				cont = new Contaminant(Double.parseDouble(rs.getString("NO2")), Double.parseDouble(rs.getString("O3")), Double.parseDouble(rs.getString("CO"))
						, Double.parseDouble(rs.getString("SO2")), Integer.parseInt(rs.getString("PM10")), Integer.parseInt(rs.getString("PM25")));
				
				date = LocalDate.parse(rs.getString("Date"), DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
				p = new Place(rs.getString("Place"), date, cont);
				
				places.add(p);
			}
			
			pstmt.close();
			rs.close();
		} catch(SQLException e) {
			System.out.println("DB 데이터 불러오기 실패: " + e.getMessage());
		}
	}
}