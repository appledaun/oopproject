import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GraphPanel extends JPanel {
	private ArrayList<Graph> graph;	// 그래프 리스트
	private int startX, endY;		// 그래프를 그릴 시작 x지점과 시작하는 y지점을 맞출 좌표
	
	GraphPanel(int x, int y) {
		startX = x;
		endY = y;
		graph = new ArrayList<Graph>();
	}
	
	@Override
	// -> 그림을 그리기 위해 가장먼저 실행되는 곳
	public void paintComponent(Graphics g) {
		int x;
		int cnt = 0;
		
		super.paintComponent(g);
		
		for(Graph graph : this.graph) {
			// 그릴 좌표 초기화
			x = startX + (cnt * 240);

			// 오염물질의 수치를 특정 공식으로 그래프로 표현하기
			drawGraph(g, graph, "NO2", graph.getPlace().getContaminant().NO2Value, x);
			drawGraph(g, graph, "O3", graph.getPlace().getContaminant().O3Value, x);
			drawGraph(g, graph, "CO", graph.getPlace().getContaminant().COValue, x);
			drawGraph(g, graph, "SO2", graph.getPlace().getContaminant().SO2Value, x);
			drawGraph(g, graph, "PM10", graph.getPlace().getContaminant().PM10Value, x);
			drawGraph(g, graph, "PM2.5", graph.getPlace().getContaminant().PM25Value, x);
			graph.setContaminantCount(0);
			
			cnt++;
		}
	}
	
	// 막대그래프를 그린다.
	public void drawGraph(Graphics g, Graph showGraph, String contaminant, double value, int x) {
		int showValue;
		int y;
		
		// 그래프 색상 설정
		setColor(g, contaminant, value);
		
		x += (40 * showGraph.getContaminantCount());
		
		System.out.println(x);
		
		// 그래프로 보여줄 수치로 변환
		showValue = convertValue(contaminant, value);
		
		y = endY - showValue;
		
		// 항상 끝나는 y값은 같아야함... -> 그럼 시작위치를 그래프 수치에 따라 설정해주고 그 값 + 높이가 항상 그 값(endY)이나와야함
		g.fillRect(x, y, 40, showValue);
		
		// 글씨 색상 검정으로 하기
		g.setColor(Color.BLACK);

		// 오염물질 수치 붙여주기
		g.drawString(Double.toString(value), x + 5, y - 20);
		// 오염물질명 붙여주기
		g.drawString(contaminant, x + 5, y + showValue + 20);

		showGraph.setContaminantCount(showGraph.getContaminantCount() + 1);
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
	
	// 그래프 목록에 추가
	public void addGraph(Graph graph) {
		this.graph.add(graph);
	}
}
