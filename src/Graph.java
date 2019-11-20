import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

// 해당 Graph 객체는 특정 도시에 대해 모든 오염물질에 대한 그래프를 그리게 할 수 있게 할 것.
// 그럴려면 Graph 객체를 생성한 쪽에서 오염물질에 대한 수치를 Graph에게 넘겨줘야함.
// 그 말은, 해당 도시에 대한 오염물질 정보를 가지고 있어야함. -> paintComponent를 직접적으로 호출할 수는 없으므로..
public class Graph extends JPanel {
	private Place p;			// 오염물질 정보를 가진 지역 데이터
	private int startX, endY;	// 그래프를 그릴 시작 x지점과 시작하는 y지점을 맞출 좌표
	private int contCnt;		// 보여줄 오염물질 갯수
	
	// 생성자. 그래프를 그릴 시작 x지점, 그래프가 시작하는 y지점을 맞출 위치와 오염물질 정보를 가진 지역 데이터를 설정한다.
	Graph(Place p, int x, int y) {
		this.p = p;
		startX = x;
		endY = y;
		contCnt = 0;
	}
	
	// Overriding -> 그림을 그리기 위해 가장먼저 실행되는 곳 
	public void paintComponent(Graphics g) {
		//super.paintComponent(g);

		// 오염물질의 수치를 특정 공식으로 그래프로 표현하기
		drawGraph(g, "NO2", p.getContaminant().NO2Value);
		drawGraph(g, "O3", p.getContaminant().O3Value);
		drawGraph(g, "CO", p.getContaminant().COValue);
		drawGraph(g, "SO2", p.getContaminant().SO2Value);
		drawGraph(g, "PM10", p.getContaminant().PM10Value);
		drawGraph(g, "PM2.5", p.getContaminant().PM25Value);
		contCnt = 0;
	}
	
	// 막대그래프를 그린다.
	public void drawGraph(Graphics g, String contaminant, double value) {
		int showValue;
		int x, y;
		
		// 그래프 색상 설정
		setColor(g, contaminant, value);
		
		// 그래프로 보여줄 수치로 변환
		showValue = convertValue(contaminant, value);
		
		x = startX + (40 * contCnt);
		System.out.println(contCnt);
		y = endY - showValue;
		
		// 항상 끝나는 y값은 같아야함... -> 그럼 시작위치를 그래프 수치에 따라 설정해주고 그 값 + 높이가 항상 그 값(endY)이나와야함
		g.fillRect(x, y, 40, showValue);
		
		// 글씨 색상 검정으로 하기
		g.setColor(Color.BLACK);
		
		// 오염물질 수치 붙여주기
		g.drawString(Double.toString(value), x + 5, y - 20);
		// 오염물질명 붙여주기
		g.drawString(contaminant, x + 5, y + showValue + 20);

		contCnt++;
	}
	
	// 해당 오염물질 수치에 대한 그래프 색상 설정
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
	
	// 오염물질 수치를 그래프로 표현할 수치로 환산해서 반환하는 메소드
	public int convertValue(String contaminant, double value) {
		switch(contaminant) {
			case "NO2":
				// 오염물질 나쁨 수치에 대한 비율을 구하고(value / Bad) 그 비율 * h만큼 곱해준다
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
	
	// 시작 X지점 설정
	public void setX(int x) {
		startX = x;
	}
	
	// 그래프를 맞출 Y지점 설정
	public void setY(int y) {
		endY = y;
	}
	
	// 그래프로 표현할 오염물질에 대한 지역 데이터 설정
	public void setPlace(Place p) {
		this.p = p;
	}
}
