public interface ContaminantInterface {
	// NO2(�̻�ȭ���� �Ǵܼ�ġ)
	double NO2Good = 0.03;
	double NO2Normal = 0.06;
	double NO2Bad = 0.2;
	double NO2VeryBad = 1;
	
	// O3(���� �Ǵܼ�ġ)
	double O3Good = 0.03;
	double O3Normal = 0.09;
	double O3Bad = 0.15;
	double O3VeryBad = 0.6;
	
	// CO(�ϻ�ȭź�� �Ǵܼ�ġ)
	double COGood = 2;
	double CONormal = 9;
	double COBad = 15;
	double COVeryBad = 50;
	
	// SO2(��Ȳ�갡�� �Ǵܼ�ġ)
	double SO2Good = 0.02;
	double SO2Normal = 0.05;
	double SO2Bad = 0.15;
	double SO2VeryBad = 1;
	
	// PM10(�̼����� �Ǵܼ�ġ)
	int PM10Good = 30;
	int PM10Normal = 80;
	int PM10Bad = 150;
	int PM10VeryBad = 600;
	
	// PM2.5(�ʹ̼����� �Ǵܼ�ġ)
	int PM25Good = 15;
	int PM25Normal = 35;
	int PM25Bad = 75;
	int PM25VeryBad = 500;
}
