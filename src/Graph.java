import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

// �ش� Graph ��ü�� Ư�� ���ÿ� ���� ��� ���������� ���� �׷����� �׸��� �� �� �ְ� �� ��.
// �׷����� Graph ��ü�� ������ �ʿ��� ���������� ���� ��ġ�� Graph���� �Ѱ������.
// �� ����, �ش� ���ÿ� ���� �������� ������ ������ �־����. -> paintComponent�� ���������� ȣ���� ���� �����Ƿ�..
public class Graph {
	private Place p;				// �������� ������ ���� ���� ������
	private int contCnt;			// ������ �������� ����
	
	// ������. �׷����� �׸� ���� x����, �׷����� �����ϴ� y������ ���� ��ġ�� �������� ������ ���� ���� �����͸� �����Ѵ�.
	Graph(Place p) {
		this.p = p;
		contCnt = 0;
	}
	
	// ������ �������� ���� ����
	public void setContaminantCount(int cnt) {
		contCnt = cnt;
	}
	
	// �׷����� ǥ���� ���������� ���� ���� ������ ����
	public void setPlace(Place p) {
		this.p = p;
	}
	
	// ���� ������ ��ȯ
	public Place getPlace() {
		return p;
	}
	
	// ������ �������� ���� ��ȯ
	public int getContaminantCount() {
		return contCnt;
	}
}
