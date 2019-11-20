import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// 전체 데이터를 갖고 있는 클래스
// 이 클래스를 이용하여 필터로 다른 클래스로 뽑아 낼 수 있어야 한다.
public class ManagementData {
	private ArrayList<Place> places;	// 데이터가 많으므로 get연산이 빨라야한다.
	
	ManagementData() throws IOException {
		places = new ArrayList();
	}
	
	// csv파일에서 데이터를 읽어 모든 데이터를 등록한다.
	public void loadData(String dataPath) throws IOException {
		BufferedReader br;
		String lineString;
		
		// csv파일 읽어오기
		br = new BufferedReader(new FileReader(dataPath));
		
		// 한 줄은 항목 명이므로 넘기기
		br.readLine();
		
		// 끝까지 한 줄씩 읽어오기
		while((lineString = br.readLine()) != null) {
			String[] splitedString;
			LocalDate date;
			Contaminant cont;
			Place p;
			
			// 읽은 Line을 ','로 자르기
			// [0]: Date
			// [1]: Place Name
			// [2]: NO2 Value
			// [3]: O3 Value
			// [4]: CO Value
			// [5]: SO2 Value
			// [6]: PM10 Value
			// [7]: PM2.5 Value
			splitedString = lineString.split(",", 8);

			// 분리한 문자열에서 오염물질 수치가 비어있는 경우 -1로 초기화한다.
			for(int i = 2; i < splitedString.length; i++) {
				if(splitedString[i].equals("")) {
					splitedString[i] = "-1";
				}
			}
			
			// 오염물질 데이터 생성
			cont = new Contaminant(Double.parseDouble(splitedString[2]), Double.parseDouble(splitedString[3]), Double.parseDouble(splitedString[4]),
					Double.parseDouble(splitedString[5]), Integer.parseInt(splitedString[6]), Integer.parseInt(splitedString[7]));
			
			// 문자열로 표현된 날짜를 LocalDate형식으로 변환하고, 장소 데이터 생성
			date = LocalDate.parse(splitedString[0], DateTimeFormatter.ofPattern("yyyyMMdd", Locale.ENGLISH));
			p = new Place(splitedString[1], date, cont);
			
			// 전체 데이터에 현재 데이터를 추가한다.
			places.add(p);
		}
		
		// 버퍼 리더 닫기
		br.close();
	}
	
	// 전체 데이터에 대한 지역 목록을 반환한다.
	public ArrayList<Place> getPlaces() {
		return places;
	}
	
	// 이름 오름차순 정렬
	public void sortName() {
		places.sort((x, y) -> x.getName().compareTo(y.getName()));
	}
	
	// 매개변수로 받은 오염물질의 최대치를 가진 지역을 반환하는 메소드
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
	
	// 매개변수로 받은 오염물질의 최소치를 가진 지역을 반환하는 메소드
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