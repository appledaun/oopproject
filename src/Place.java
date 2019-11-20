import java.time.LocalDate;

public class Place {
	private String		name;	// ���ø�
	private LocalDate	date;	// �Ⱓ
	private Contaminant cont;	// ��������
	
	Place(String name, LocalDate date, Contaminant c) {
		this.name = name;
		this.date = date;
		this.cont = c;
	}
	
	// ���ø��� ��ȯ�Ѵ�.
	public String getName() {
		return name;
	}
	
	// ��¥�� ��ȯ�Ѵ�.
	public LocalDate getDate() {
		return date;
	}
	
	// �������� ��ü�� ��ȯ�Ѵ�.
	public Contaminant getContaminant() {
		return cont;
	}
}