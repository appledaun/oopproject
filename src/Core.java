public class Core {
	public static void main(String[] args) {
		Frame databaseFrame;
		
		try {
			databaseFrame = new DatabaseFrame("데이터 설정", 400, 200);
			
			databaseFrame.start();
		} catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		/* csv파일에서 데이터를 읽어오는 부분
		try {
			// 데이터 관리 객체 생성
			mData = new ManagementData();
		} catch(IOException e) {
			System.out.println("Fail File Load: " + e.getMessage());
		}*/
	
		/* 프로그램에서 DB로 데이터를 등록하는 부분
		try {
			// 입력이 가능한 데이터베이스 객체 생성
			inputDB = new InputDatabase(DatabaseInterface.url, DatabaseInterface.id, DatabaseInterface.pw);
			
			// DB에 데이터 등록
			inputDB.loadData(mData.getPlaces());
		} catch(SQLException e) {
			System.out.println("DB 연결 실패: " + e.getMessage());
		}*/
	}
}
