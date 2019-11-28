import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

// 해당 Graph 객체는 특정 도시에 대해 모든 오염물질에 대한 그래프를 그리게 할 수 있게 할 것.
// 그럴려면 Graph 객체를 생성한 쪽에서 오염물질에 대한 수치를 Graph에게 넘겨줘야함.
// 그 말은, 해당 도시에 대한 오염물질 정보를 가지고 있어야함. -> paintComponent를 직접적으로 호출할 수는 없으므로..
public class Graph {
	private Place p;				// 오염물질 정보를 가진 지역 데이터
	private int contCnt;			// 보여줄 오염물질 갯수
	
	// 생성자. 그래프를 그릴 시작 x지점, 그래프가 시작하는 y지점을 맞출 위치와 오염물질 정보를 가진 지역 데이터를 설정한다.
	Graph(Place p) {
		this.p = p;
		contCnt = 0;
	}
	
	// 보여줄 오염물질 갯수 설정
	public void setContaminantCount(int cnt) {
		contCnt = cnt;
	}
	
	// 그래프로 표현할 오염물질에 대한 지역 데이터 설정
	public void setPlace(Place p) {
		this.p = p;
	}
	
	// 지역 데이터 반환
	public Place getPlace() {
		return p;
	}
	
	// 보여줄 오염물질 갯수 반환
	public int getContaminantCount() {
		return contCnt;
	}
}
