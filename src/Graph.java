import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

// �ش� Graph ��ü�� Ư�� ���ÿ� ���� ��� ���������� ���� �׷����� �׸��� �� �� �ְ� �� ��.
// �׷����� Graph ��ü�� ������ �ʿ��� ���������� ���� ��ġ�� Graph���� �Ѱ������.
// �� ����, �ش� ���ÿ� ���� �������� ������ ������ �־����. -> paintComponent�� ���������� ȣ���� ���� �����Ƿ�..
public class Graph extends JPanel {
	private Place p;			// �������� ������ ���� ���� ������
	private int startX, endY;	// �׷����� �׸� ���� x������ �����ϴ� y������ ���� ��ǥ
	private int contCnt;		// ������ �������� ����
	
	// ������. �׷����� �׸� ���� x����, �׷����� �����ϴ� y������ ���� ��ġ�� �������� ������ ���� ���� �����͸� �����Ѵ�.
	Graph(Place p, int x, int y) {
		this.p = p;
		startX = x;
		endY = y;
		contCnt = 0;
	}
	
	// Overriding -> �׸��� �׸��� ���� ������� ����Ǵ� �� 
	public void paintComponent(Graphics g) {
		//super.paintComponent(g);

		// ���������� ��ġ�� Ư�� �������� �׷����� ǥ���ϱ�
		drawGraph(g, "NO2", p.getContaminant().NO2Value);
		drawGraph(g, "O3", p.getContaminant().O3Value);
		drawGraph(g, "CO", p.getContaminant().COValue);
		drawGraph(g, "SO2", p.getContaminant().SO2Value);
		drawGraph(g, "PM10", p.getContaminant().PM10Value);
		drawGraph(g, "PM2.5", p.getContaminant().PM25Value);
		contCnt = 0;
	}
	
	// ����׷����� �׸���.
	public void drawGraph(Graphics g, String contaminant, double value) {
		int showValue;
		int x, y;
		
		// �׷��� ���� ����
		setColor(g, contaminant, value);
		
		// �׷����� ������ ��ġ�� ��ȯ
		showValue = convertValue(contaminant, value);
		
		x = startX + (40 * contCnt);
		System.out.println(contCnt);
		y = endY - showValue;
		
		// �׻� ������ y���� ���ƾ���... -> �׷� ������ġ�� �׷��� ��ġ�� ���� �������ְ� �� �� + ���̰� �׻� �� ��(endY)�̳��;���
		g.fillRect(x, y, 40, showValue);
		
		// �۾� ���� �������� �ϱ�
		g.setColor(Color.BLACK);
		
		// �������� ��ġ �ٿ��ֱ�
		g.drawString(Double.toString(value), x + 5, y - 20);
		// ���������� �ٿ��ֱ�
		g.drawString(contaminant, x + 5, y + showValue + 20);

		contCnt++;
	}
	
	// �ش� �������� ��ġ�� ���� �׷��� ���� ����
	public void setColor(Graphics g, String contaminant, double value) {
		switch(contaminant) {
		case "NO2":
			if(value <= ContaminantInterface.NO2Good) {
				g.setColor(Color.BLUE);
			} else if(value <= ContaminantInterface.NO2Normal) {
				g.setColor(Color.GREEN);
			} else if(value <= ContaminantInterface.NO2Bad) {
				g.setColor(Color.ORANGE);
			} else if(value <= ContaminantInterface.NO2VeryBad) {
				g.setColor(Color.RED);
			}
			
			break;
		case "O3":
			if(value <= ContaminantInterface.O3Good) {
				g.setColor(Color.BLUE);
			} else if(value <= ContaminantInterface.O3Normal) {
				g.setColor(Color.GREEN);
			} else if(value <= ContaminantInterface.O3Bad) {
				g.setColor(Color.ORANGE);
			} else if(value <= ContaminantInterface.O3VeryBad) {
				g.setColor(Color.RED);
			}
			
			break;
		case "CO":
			if(value <= ContaminantInterface.COGood) {
				g.setColor(Color.BLUE);
			} else if(value <= ContaminantInterface.CONormal) {
				g.setColor(Color.GREEN);
			} else if(value <= ContaminantInterface.COBad) {
				g.setColor(Color.ORANGE);
			} else if(value <= ContaminantInterface.COVeryBad) {
				g.setColor(Color.RED);
			}
			
			break;
		case "SO2":
			if(value <= ContaminantInterface.SO2Good) {
				g.setColor(Color.BLUE);
			} else if(value <= ContaminantInterface.SO2Normal) {
				g.setColor(Color.GREEN);
			} else if(value <= ContaminantInterface.SO2Bad) {
				g.setColor(Color.ORANGE);
			} else if(value <= ContaminantInterface.SO2VeryBad) {
				g.setColor(Color.RED);
			}
			
			break;
		case "PM10":
			if(value <= ContaminantInterface.PM10Good) {
				g.setColor(Color.BLUE);
			} else if(value <= ContaminantInterface.PM10Normal) {
				g.setColor(Color.GREEN);
			} else if(value <= ContaminantInterface.PM10Bad) {
				g.setColor(Color.ORANGE);
			} else if(value <= ContaminantInterface.PM10VeryBad) {
				g.setColor(Color.RED);
			}
			
			break;
		case "PM2.5":
			if(value <= ContaminantInterface.PM25Good) {
				g.setColor(Color.BLUE);
			} else if(value <= ContaminantInterface.PM25Normal) {
				g.setColor(Color.GREEN);
			} else if(value <= ContaminantInterface.PM25Bad) {
				g.setColor(Color.ORANGE);
			} else if(value <= ContaminantInterface.PM25VeryBad) {
				g.setColor(Color.RED);
			}
			
			break;
		}
	}
	
	// �������� ��ġ�� �׷����� ǥ���� ��ġ�� ȯ���ؼ� ��ȯ�ϴ� �޼ҵ�
	public int convertValue(String contaminant, double value) {
		switch(contaminant) {
			case "NO2":
				// �������� ���� ��ġ�� ���� ������ ���ϰ�(value / Bad) �� ���� * h��ŭ �����ش�
				value = value / ContaminantInterface.NO2Bad * 400;
				
				break;
			case "O3":
				value = value / ContaminantInterface.O3Bad * 400; 
				
				break;
			case "CO":
				value = value / ContaminantInterface.COBad * 400; 
				
				break;
			case "SO2":
				value = value / ContaminantInterface.SO2Bad * 400;
				
				break;
			case "PM10":
				value = value / ContaminantInterface.PM10Bad * 400;
				
				break;
			case "PM2.5":
				value = value / ContaminantInterface.PM25Bad * 400;
				
				break;
		}
		
		return (int)value;
	}
	
	// ���� X���� ����
	public void setX(int x) {
		startX = x;
	}
	
	// �׷����� ���� Y���� ����
	public void setY(int y) {
		endY = y;
	}
	
	// �׷����� ǥ���� ���������� ���� ���� ������ ����
	public void setPlace(Place p) {
		this.p = p;
	}
}
