import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GraphPanel extends JPanel {
	private ArrayList<Graph> graph;	// �׷��� ����Ʈ
	private int startX, endY;		// �׷����� �׸� ���� x������ �����ϴ� y������ ���� ��ǥ
	
	GraphPanel(int x, int y) {
		startX = x;
		endY = y;
		graph = new ArrayList<Graph>();
	}
	
	@Override
	// -> �׸��� �׸��� ���� ������� ����Ǵ� ��
	public void paintComponent(Graphics g) {
		int x;
		int cnt = 0;
		
		super.paintComponent(g);
		
		for(Graph graph : this.graph) {
			// �׸� ��ǥ �ʱ�ȭ
			x = startX + (cnt * 240);

			// ���������� ��ġ�� Ư�� �������� �׷����� ǥ���ϱ�
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
	
	// ����׷����� �׸���.
	public void drawGraph(Graphics g, Graph showGraph, String contaminant, double value, int x) {
		int showValue;
		int y;
		
		// �׷��� ���� ����
		setColor(g, contaminant, value);
		
		x += (40 * showGraph.getContaminantCount());
		
		System.out.println(x);
		
		// �׷����� ������ ��ġ�� ��ȯ
		showValue = convertValue(contaminant, value);
		
		y = endY - showValue;
		
		// �׻� ������ y���� ���ƾ���... -> �׷� ������ġ�� �׷��� ��ġ�� ���� �������ְ� �� �� + ���̰� �׻� �� ��(endY)�̳��;���
		g.fillRect(x, y, 40, showValue);
		
		// �۾� ���� �������� �ϱ�
		g.setColor(Color.BLACK);

		// �������� ��ġ �ٿ��ֱ�
		g.drawString(Double.toString(value), x + 5, y - 20);
		// ���������� �ٿ��ֱ�
		g.drawString(contaminant, x + 5, y + showValue + 20);

		showGraph.setContaminantCount(showGraph.getContaminantCount() + 1);
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
	
	// �׷��� ��Ͽ� �߰�
	public void addGraph(Graph graph) {
		this.graph.add(graph);
	}
}
