public class Core {
	public static void main(String[] args) {
		Frame databaseFrame;
		
		try {
			databaseFrame = new DatabaseFrame("������ ����", 400, 200);
			
			databaseFrame.start();
		} catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		/* csv���Ͽ��� �����͸� �о���� �κ�
		try {
			// ������ ���� ��ü ����
			mData = new ManagementData();
		} catch(IOException e) {
			System.out.println("Fail File Load: " + e.getMessage());
		}*/
	
		/* ���α׷����� DB�� �����͸� ����ϴ� �κ�
		try {
			// �Է��� ������ �����ͺ��̽� ��ü ����
			inputDB = new InputDatabase(DatabaseInterface.url, DatabaseInterface.id, DatabaseInterface.pw);
			
			// DB�� ������ ���
			inputDB.loadData(mData.getPlaces());
		} catch(SQLException e) {
			System.out.println("DB ���� ����: " + e.getMessage());
		}*/
	}
}
