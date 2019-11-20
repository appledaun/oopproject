import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OutputDatabase extends ManagementDatabase {
	OutputDatabase(String url, String id, String pw) throws SQLException {
		// ����� Ŭ���� ������ ȣ��
		super(url, id, pw);
	}
	
	// DB���� ���α׷����� �����͸� �ҷ����� �޼ҵ�
	public void loadData(ArrayList<Place> places) {
		String sql;
		PreparedStatement pstmt;
		ResultSet rs;
		
		sql = "SELECT * FROM Contaminant";
		
		try {
			pstmt = getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			// ��� �׸��� �����´�
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
			System.out.println("DB ������ �ҷ����� ����: " + e.getMessage());
		}
	}
}