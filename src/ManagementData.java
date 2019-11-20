import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// ��ü �����͸� ���� �ִ� Ŭ����
// �� Ŭ������ �̿��Ͽ� ���ͷ� �ٸ� Ŭ������ �̾� �� �� �־�� �Ѵ�.
public class ManagementData {
	private ArrayList<Place> places;	// �����Ͱ� �����Ƿ� get������ ������Ѵ�.
	
	ManagementData() throws IOException {
		places = new ArrayList();
	}
	
	// csv���Ͽ��� �����͸� �о� ��� �����͸� ����Ѵ�.
	public void loadData(String dataPath) throws IOException {
		BufferedReader br;
		String lineString;
		
		// csv���� �о����
		br = new BufferedReader(new FileReader(dataPath));
		
		// �� ���� �׸� ���̹Ƿ� �ѱ��
		br.readLine();
		
		// ������ �� �پ� �о����
		while((lineString = br.readLine()) != null) {
			String[] splitedString;
			LocalDate date;
			Contaminant cont;
			Place p;
			
			// ���� Line�� ','�� �ڸ���
			// [0]: Date
			// [1]: Place Name
			// [2]: NO2 Value
			// [3]: O3 Value
			// [4]: CO Value
			// [5]: SO2 Value
			// [6]: PM10 Value
			// [7]: PM2.5 Value
			splitedString = lineString.split(",", 8);

			// �и��� ���ڿ����� �������� ��ġ�� ����ִ� ��� -1�� �ʱ�ȭ�Ѵ�.
			for(int i = 2; i < splitedString.length; i++) {
				if(splitedString[i].equals("")) {
					splitedString[i] = "-1";
				}
			}
			
			// �������� ������ ����
			cont = new Contaminant(Double.parseDouble(splitedString[2]), Double.parseDouble(splitedString[3]), Double.parseDouble(splitedString[4]),
					Double.parseDouble(splitedString[5]), Integer.parseInt(splitedString[6]), Integer.parseInt(splitedString[7]));
			
			// ���ڿ��� ǥ���� ��¥�� LocalDate�������� ��ȯ�ϰ�, ��� ������ ����
			date = LocalDate.parse(splitedString[0], DateTimeFormatter.ofPattern("yyyyMMdd", Locale.ENGLISH));
			p = new Place(splitedString[1], date, cont);
			
			// ��ü �����Ϳ� ���� �����͸� �߰��Ѵ�.
			places.add(p);
		}
		
		// ���� ���� �ݱ�
		br.close();
	}
	
	// ��ü �����Ϳ� ���� ���� ����� ��ȯ�Ѵ�.
	public ArrayList<Place> getPlaces() {
		return places;
	}
	
	// �̸� �������� ����
	public void sortName() {
		places.sort((x, y) -> x.getName().compareTo(y.getName()));
	}
	
	// �Ű������� ���� ���������� �ִ�ġ�� ���� ������ ��ȯ�ϴ� �޼ҵ�
	public Place maxContaminantPlace(String contaminant) {
		Optional<Place> p = null;
		
		switch(contaminant) {
		case "NO2":
			p = places.stream().reduce((p1, p2) -> p1.getContaminant().NO2Value > p2.getContaminant().NO2Value ? p1 : p2);
			
			break;
		case "O3":
			p = places.stream().reduce((p1, p2) -> p1.getContaminant().O3Value > p2.getContaminant().O3Value ? p1 : p2);
			
			break;
		case "CO":
			p = places.stream().reduce((p1, p2) -> p1.getContaminant().COValue > p2.getContaminant().COValue ? p1 : p2);
			
			break;
		case "SO2":
			p = places.stream().reduce((p1, p2) -> p1.getContaminant().SO2Value > p2.getContaminant().SO2Value ? p1 : p2);
			
			break;
		case "PM10":
			p = places.stream().reduce((p1, p2) -> p1.getContaminant().PM10Value > p2.getContaminant().PM10Value ? p1 : p2);
			
			break;
		case "PM2.5":
			p = places.stream().reduce((p1, p2) -> p1.getContaminant().PM25Value > p2.getContaminant().PM25Value ? p1 : p2);
			
			break;
		}
		
		return p.get();
	}
	
	// �Ű������� ���� ���������� �ּ�ġ�� ���� ������ ��ȯ�ϴ� �޼ҵ�
	public Place minContaminantPlace(String contaminant) {
		Optional<Place> p = null;
		
		switch(contaminant) {
		case "NO2":
			p = places.stream().filter(p1 -> p1.getContaminant().NO2Value != -1).reduce((p1, p2) -> p1.getContaminant().NO2Value < p2.getContaminant().NO2Value ? p1 : p2);
			
			break;
		case "O3":
			p = places.stream().filter(p1 -> p1.getContaminant().O3Value != -1).reduce((p1, p2) -> p1.getContaminant().O3Value < p2.getContaminant().O3Value ? p1 : p2);
			
			break;
		case "CO":
			p = places.stream().filter(p1 -> p1.getContaminant().COValue != -1).reduce((p1, p2) -> p1.getContaminant().COValue < p2.getContaminant().COValue ? p1 : p2);
			
			break;
		case "SO2":
			p = places.stream().filter(p1 -> p1.getContaminant().SO2Value != -1).reduce((p1, p2) -> p1.getContaminant().SO2Value < p2.getContaminant().SO2Value ? p1 : p2);
			
			break;
		case "PM10":
			p = places.stream().filter(p1 -> p1.getContaminant().PM10Value != -1).reduce((p1, p2) -> p1.getContaminant().PM10Value < p2.getContaminant().PM10Value ? p1 : p2);
			
			break;
		case "PM2.5":
			p = places.stream().filter(p1 -> p1.getContaminant().PM25Value != -1).reduce((p1, p2) -> p1.getContaminant().PM25Value < p2.getContaminant().PM25Value ? p1 : p2);
			
			break;
		}
		
		return p.get();
	}
}