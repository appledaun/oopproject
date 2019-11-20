public class Contaminant {
	// ���������� ��ġ ������ double�� ���� ppm, int�� ����ũ�α׷�(��/��)
	// ��ġ�� -1�� ���� �� ���� csv���Ͽ� ����ִ� �׸�
	protected double	NO2Value;	// �̻�ȭ���� ��ġ
	protected double	O3Value;	// ���� ��ġ
	protected double	COValue;	// �ϻ�ȭź�� ��ġ
	protected double	SO2Value;	// ��Ȳ�갡�� ��ġ
	protected int		PM10Value;	// �̼����� ��ġ
	protected int		PM25Value;	// �ʹ̼����� ��ġ
	
	// �������� ��ġ�� �ʱ�ȭ�Ѵ�.
	Contaminant(double NO2, double O3, double CO, double SO2, int PM10, int PM25) {
		NO2Value	= NO2;
		O3Value		= O3;
		COValue		= CO;
		SO2Value	= SO2;
		PM10Value	= PM10;
		PM25Value	= PM25;
	}	
}