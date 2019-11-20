public class Contaminant {
	// 오염물질의 수치 단위는 double인 것이 ppm, int면 마이크로그램(㎍/㎥)
	// 수치가 -1로 저장 된 것은 csv파일에 비어있는 항목
	protected double	NO2Value;	// 이산화질소 수치
	protected double	O3Value;	// 오존 수치
	protected double	COValue;	// 일산화탄소 수치
	protected double	SO2Value;	// 아황산가스 수치
	protected int		PM10Value;	// 미세먼지 수치
	protected int		PM25Value;	// 초미세먼지 수치
	
	// 오염물질 수치를 초기화한다.
	Contaminant(double NO2, double O3, double CO, double SO2, int PM10, int PM25) {
		NO2Value	= NO2;
		O3Value		= O3;
		COValue		= CO;
		SO2Value	= SO2;
		PM10Value	= PM10;
		PM25Value	= PM25;
	}	
}