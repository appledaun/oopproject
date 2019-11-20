import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends DataFrame {
	// ������. �������� Ÿ��Ʋ, ����, �ʺ�, �����͸� �����Ѵ�.
	MainFrame(String title, int width, int height, ManagementData mData) {
		// ������ Ÿ��Ʋ, �ʺ�, ���� ����
		super(title, width, height, mData);
		
		// ���� ������ �ʱ�ȭ �۾�
		initialize();
	}
	
	// ������ �ʱ�ȭ�۾�. ������ ����� �����ϰ� �ʱ�ȭ�Ѵ�.
	protected void initialize() {
		// ������ �� ���ø����̼� ���� ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// �� ���ȭ����� ������ ��Ͽ� �߰��Ѵ�.
		// ���1
		frames.add(new MaxMinFrame("���1", 500, 500, mData));
		
		// ���2
		frames.add(new CompareFrame("���2", 500, 500, mData));
		
		// ���3
		frames.add(new DateFrame("���3", 500, 500, mData));
		
		drawComponents();
	}
	
	// �ش� �������� �����ϴ� ������Ʈ���� ����� ���δ�.
	protected void drawComponents() {
		JPanel				pMain;
		JPanel				pNorth, pCenter, pSouth;				// ��üȭ�� ���� �г�
		JPanel				cpNorth, cpCenter, cpSouth, cpSouthSub; // ��üȭ�� ���� �г��� Center�κ� ���� �г�
		JLabel				projectName_lb, contInfo_lb, contInfoContext_lb, ani_lb, img_lb;	// ������Ʈ �̸�, �������� ��������, �������� ���� ����, �ִϸ��̼� ��, �̹��� ��
		JButton				rankAll_btn, comparePlace_btn, calendar_btn;				// ��ü ���� ����, ������ �������� Ȯ��, �޷� ��ư 
		JComboBox<String>	conts_cb;	// �������� �޺��ڽ�
		String[]			cont = { "���� ���� ����", "�̻�ȭ����", "����", "�ϻ�ȭź��", "��Ȳ�갡��", "�̼�����", "�ʹ̼�����" };		// �������� �޺��ڽ��� �� �׸�
		JTable				table;
		String				colText[] = {"��������ġ ���� ���� top3", "��������ġ ���� ���� top3"};						// �׽�Ʈ ��
		String				rowText[][] = { {"1. ����", "1. ����"}, {"2. ����", "2. ����"}, {"3. ���", "3. ���"}, };	// �׽�Ʈ ��
		JScrollPane			scroll;
		
		///////////////////////////////////////////
		// �г� �ʱ�ȭ
		pMain = new JPanel(new BorderLayout());
		pNorth	= new JPanel();
		pCenter	= new JPanel(new BorderLayout());
		pSouth	= new JPanel();
		
		// Center�κ� ���� �г�
		cpNorth		= new JPanel(new BorderLayout());
		cpCenter	= new JPanel(new BorderLayout());
		
		cpSouth		= new JPanel(new GridLayout(1, 2));
		
		cpSouthSub	= new JPanel(new BorderLayout());
		///////////////////////////////////////////
		
		///////////////////////////////////////////
		// �� �ʱ�ȭ
		projectName_lb		= new JLabel("������ ������");
		contInfo_lb			= new JLabel("�� �˰� ���� ���������� �����ϼ���!");
		contInfoContext_lb	= new JLabel("~");
		
		ani_lb 				= new JLabel("�ִϸ��̼�");
		ani_lb.setPreferredSize(new Dimension(getSize().width, 100));		
		ani_lb.setHorizontalAlignment(JLabel.CENTER);
		
		img_lb				= new JLabel("", new ImageIcon("./�������ʱ�ȭ��2.png"), JLabel.CENTER);
		///////////////////////////////////////////
		
		///////////////////////////////////////////
		// ��ư �ʱ�ȭ		
		rankAll_btn			= new JButton("��ü ���� ����");
		rankAll_btn.setPreferredSize(new Dimension(150, 20));
	
		comparePlace_btn	= new JButton("������ ���������� Ȯ��");
		comparePlace_btn.setPreferredSize(new Dimension((getSize().width) - 100, 70));
		
		calendar_btn		= new JButton("�޷�");
		calendar_btn.setPreferredSize(new Dimension(90, 90));
		calendar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �޷� ������ ����
				for(Frame f : frames) {
					if(f instanceof DateFrame) {
						f.start();
					}
				}
			}
		});
		///////////////////////////////////////////
		
		///////////////////////////////////////////
		// �޺��ڽ� �ʱ�ȭ
		conts_cb = new JComboBox<String>(cont);
		///////////////////////////////////////////
		
		///////////////////////////////////////////
		// ���̺� �ʱ�ȭ
		table = new JTable(rowText, colText);
		///////////////////////////////////////////
		
		///////////////////////////////////////////
		// ��ũ�� ���� �ʱ�ȭ
		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(250, 150));   //���1 ���̺� ����
		///////////////////////////////////////////
		
		///////////////////////////////////////////
		// ��üȭ�� North �κ� ������Ʈ �߰�
		pNorth.add(projectName_lb);
		///////////////////////////////////////////
		
		///////////////////////////////////////////
		// ��üȭ�� Center�� North �κ� ������Ʈ �߰� 
		cpNorth.add(scroll, BorderLayout.NORTH);
		cpNorth.add(rankAll_btn, BorderLayout.EAST);
		
		// ��üȭ�� Center�� Center �κ� ������Ʈ �߰� 
		cpCenter.add(comparePlace_btn, BorderLayout.NORTH);
		cpCenter.add(calendar_btn, BorderLayout.CENTER);
		cpCenter.add(img_lb, BorderLayout.SOUTH);
		
		// ��üȭ�� Center�� South �κ� ������Ʈ �߰� 
		cpSouthSub.add(contInfo_lb,BorderLayout.NORTH);
		cpSouthSub.add(conts_cb,BorderLayout.CENTER);
		cpSouthSub.add(contInfoContext_lb,BorderLayout.SOUTH);
		
		cpSouth.add(cpSouthSub);
		
		// ��üȭ�� Center �κ� ������Ʈ �߰�
		pCenter.add(cpNorth, BorderLayout.NORTH);   
		pCenter.add(cpCenter, BorderLayout.CENTER);   
		pCenter.add(cpSouth, BorderLayout.SOUTH);
		///////////////////////////////////////////
		
		///////////////////////////////////////////
		// ��üȭ�� South �κ� ������Ʈ �߰�
		pSouth.add(ani_lb);
		///////////////////////////////////////////
		
		///////////////////////////////////////////
		// ���� �гο� ���� �г� �߰�
		pMain.add(pNorth, BorderLayout.NORTH);
		pMain.add(pCenter, BorderLayout.CENTER);
		pMain.add(pSouth, BorderLayout.SOUTH);
		///////////////////////////////////////////
		
		add(pMain);
		
		/*
		/////////////////////////////////
		// �׽�Ʈ �ڵ�
		// ���߿� �����ο� �°� ������ ��
		/////////////////////////////////
		setLayout(new BorderLayout());
		
		JPanel p = new JPanel();
		
		p.setLayout(new GridLayout(1, 3));
		p.setPreferredSize(new Dimension(1, 300));
		
		ArrayList<JButton> btns = new ArrayList<JButton>();
		
		btns.add(new JButton("���1"));
		btns.add(new JButton("���2"));
		btns.add(new JButton("���3"));
		
		for(JButton btn : btns) {
			
			// ������ ���
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JButton btn = (JButton)e.getSource();
					
					// �ش� ��� â ����
					for(Frame f : frames) {
						if(f.getTitle().equals(btn.getText())) {
							f.start();
							
							break;
						}
					}
				}
			});
			
			p.add(btn);
		}
			
		add(p, BorderLayout.SOUTH);
		/////////////////////////////////
		*/
	}
	
	// �ϴ��� �ּ�ó��.. ���� �ʿ� ���� �� ����
	// ������ ����
	private void setData(ManagementData mData) {
		this.mData = mData;
	}
}
