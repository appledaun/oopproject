import java.time.LocalDate;

public class Place {
	private String		name;	// 도시명
	private LocalDate	date;	// 기간
	private Contaminant cont;	// 오염물질
	
	Place(String name, LocalDate date, Contaminant c) {
		this.name = name;
		this.date = date;
		this.cont = c;
	}
	
	// 도시명을 반환한다.
	public String getName() {
		return name;
	}
	
	// 날짜를 반환한다.
	public LocalDate getDate() {
		return date;
	}
	
	// 오염물질 객체를 반환한다.
	public Contaminant getContaminant() {
		return cont;
	}
}