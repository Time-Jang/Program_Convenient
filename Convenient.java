package Program;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

class Product implements Serializable{
	String Name;
	String Kind;
	int Price;
	int Amount;
	ImageIcon Image;
	String Popularity;
	public Product(String Name,String Kind,int Price,int Amount,ImageIcon Image,String Popularity){
		this.Name=Name;
		this.Kind=Kind;
		this.Price=Price;
		this.Amount=Amount;
		this.Image=Image;
		this.Popularity=Popularity;
	}
}
class Receipt implements Serializable{
	String Time;
	Vector<String> Names;
	Vector<Integer> Amounts;
	Vector<Integer> Prices;
	int order_number;
	public Receipt(String Time,Vector<String> Names,	Vector<Integer> Amounts,Vector<Integer> Prices,int order_number){
		this.Time=Time;
		this.Names=Names;
		this.Amounts=Amounts;
		this.Prices=Prices;
		this.order_number=order_number;
	}
}

public class Convenient extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1915566867477407440L;
	Vector<String> Products_name=new Vector<String>(10);
	Vector<String> userColumn = new Vector<String>();
	Vector<String> userColumn2 = new Vector<String>();
	Vector<String> userColumn3 = new Vector<String>();
	Vector<String> userColumn4 = new Vector<String>();
	Vector<String> userColumn5 = new Vector<String>();
	Vector<String> userColumn6 = new Vector<String>();
	DefaultTableModel model,model2,model3,model4,model5,model6;
	ArrayList<Product> Products = new ArrayList<Product>();
	ArrayList<Receipt> Receipts = new ArrayList<Receipt>();
	private JList list01;
	private JTable Table,Table2,Table3,Table4,Table5,Table6;
	private JPanel Panel,Panel2,Panel3,Panel4,Panel5,Panel6;
	private JFrame Frame1,Frame2,Frame3,Frame4;
	private JTextArea textarea01,textarea02,textarea03,textarea04,textarea05,textarea001,textarea002,textarea003,textarea004,textarea005,textarea006,textarea0001,textarea0002,textarea0003,textarea0004,textarea101;
	private JButton add,del,info,buy,add_Product,delete_Product,fix_Receipt,fix_Product,refund,P_all,P_all2,search_p,search_r,search_r1,search_r3,search_p1,search_p2,search_p3,search_p4,search_p5,search_p6;
	private JLabel imageinfo,numbers01,Ask,Image00,total_price2;
	private JMenuBar mb;
	private JMenuItem open,save,exit,open_receipt,save_receipt;
	private JComboBox numbers;
	private JFileChooser fc;
	private JMenu mm,mm2;
	private String str01,str02,a01,b,f;
	private int selection,selection01,selection2,selection3,c,d,count,total_price,total_customer,rest_product;
	private ImageIcon image,e0;
	private JTabbedPane Tab;
	Convenient(){
		setTitle("편의점");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FileInputStream fin = null;
		ObjectInputStream ois = null;
			try{
				fin = new FileInputStream("Convenient_Products.dat");
				ois = new ObjectInputStream(fin);
				ArrayList list = (ArrayList)ois.readObject();
				Iterator it = list.iterator();
				int al=Products.size();
				for(int i=0;i<al;i++){
					Products.remove(0);
				}
				while(it.hasNext()){
					Product nb = (Product)it.next();
					Products.add(nb);
				}
				allProduct();
				updatinglist(list01);
			}catch(Exception ex){
			}finally{
				try{
					ois.close();
					fin.close();
				}catch(IOException ioe){}
			}
		userColumn.addElement("이름");
		userColumn.addElement("수량");
		userColumn.addElement("가격");
		userColumn2.addElement("날짜");
		userColumn2.addElement("주문번호");
		userColumn3.addElement("이름");
		userColumn3.addElement("종류");
		userColumn3.addElement("가격");
		userColumn3.addElement("수량");
		userColumn3.addElement("인기도");
		userColumn3.addElement("이미지링크");
		userColumn4.addElement("제품이름");
		userColumn4.addElement("수량");
		userColumn4.addElement("가격");
		userColumn5.addElement("날짜");
		userColumn5.addElement("이름");
		userColumn5.addElement("수량");
		userColumn5.addElement("가격");
		userColumn6.addElement("이름");
		userColumn6.addElement("종류");
		userColumn6.addElement("가격");
		userColumn6.addElement("수량");
		userColumn6.addElement("인기도");
		selection=0;
		selection01=0;
		selection2=0;
		selection3=0;
		count=0;
		total_price=0;
		total_customer=0;
		rest_product=0;
		image = new ImageIcon("");
		model = new DefaultTableModel(userColumn,0){
			public boolean isCellEditable(int i,int c){
				return false;
			}
		};
		model2 = new DefaultTableModel(userColumn2,0){
			public boolean isCellEditable(int i,int c){
				return false;
			}
		};
		model3 = new DefaultTableModel(userColumn3,0){
			public boolean isCellEditable(int i,int c){
				return false;
			}
		};
		model4 = new DefaultTableModel(userColumn4,0){
			public boolean isCellEditable(int i,int c){
				return false;
			}
		};
		allProduct();
		Table = new JTable(model);
		Table2 = new JTable(model2);
		Table3 = new JTable(model3);
		Table4 = new JTable(model4);
		list01=new JList(Products_name);
		Panel = new JPanel();
		Panel2 = new JPanel();
		Panel3 = new JPanel();
		Panel4 = new JPanel();
		textarea01=new JTextArea();
		textarea02=new JTextArea();
		textarea03=new JTextArea();
		textarea04=new JTextArea();
		textarea05=new JTextArea();
		textarea001=new JTextArea();
		textarea002=new JTextArea();
		textarea003=new JTextArea();
		textarea004=new JTextArea();
		textarea005=new JTextArea();
		textarea006=new JTextArea();
		textarea0001=new JTextArea();
		textarea0002=new JTextArea();
		textarea0003=new JTextArea();
		textarea0004=new JTextArea();
		add=new JButton("Add");
		del=new JButton("Delete");
		info=new JButton("영수증 보기");
		buy=new JButton("Buy");
		add_Product=new JButton("제품추가");
		delete_Product=new JButton("제품제거");
		fix_Product=new JButton("제품수정");
		fix_Receipt=new JButton("영수증수정");
		search_p=new JButton("제품검색");
		search_r=new JButton("영수증검색");
		refund=new JButton("환불");
		imageinfo=new JLabel(image);
		numbers01=new JLabel("수량 : ");
		mb=new JMenuBar();
		mm=new JMenu("Products");
		mm2=new JMenu("Receipts");
		exit=new JMenuItem("EXIT");
		open=new JMenuItem("OPEN_Products");
		save=new JMenuItem("SAVE_Products");
		open_receipt=new JMenuItem("OPEN_Receipts");
		save_receipt=new JMenuItem("SAVE_Receipts");
		fc=new JFileChooser();
		numbers=new JComboBox();
		Tab=new JTabbedPane();
		total_price2=new JLabel("총 금액 : ");
		settingNum();
		selection01=(Integer) numbers.getSelectedItem();
		Panel.setLayout(null);
		Panel2.setLayout(null);
		Panel3.setLayout(null);
		Panel4.setLayout(null);
		JScrollPane PL = new JScrollPane(list01);
		JScrollPane PL02 = new JScrollPane(Table);
		JScrollPane PL03 = new JScrollPane(Table2);
		JScrollPane PL04 = new JScrollPane(Table3);
		Table.setFillsViewportHeight(true);
		Table2.setFillsViewportHeight(true);
		Table3.setFillsViewportHeight(true);
		updatinglist(list01);
		calculate();
		list01.setBorder(new TitledBorder("선택"));
		PL02.setBorder(new TitledBorder("영수증"));
		textarea01.setBorder(new TitledBorder("제품명"));
		textarea01.setEditable(false);
		textarea02.setBorder(new TitledBorder("가격"));
		textarea02.setEditable(false);
		textarea03.setBorder(new TitledBorder("종류"));
		textarea03.setEditable(false);
		textarea04.setBorder(new TitledBorder("수량"));
		textarea04.setEditable(false);
		textarea05.setBorder(new TitledBorder("인기도"));
		textarea05.setEditable(false);
		textarea0001.setBorder(new TitledBorder("현재 총 매출"));
		textarea0001.setEditable(false);
		textarea0002.setBorder(new TitledBorder("총 고객 수"));
		textarea0002.setEditable(false);
		textarea0003.setBorder(new TitledBorder("현재 제품수"));
		textarea0003.setEditable(false);
		imageinfo.setBorder(new TitledBorder("사진"));
		numbers01.setBorder(new TitledBorder(""));
		PL.setBounds(10,260,200,400);
		add.setBounds(5,665,110,30);
		buy.setBounds(5,230,110,30);
		del.setBounds(105,230,110,30);
		add_Product.setBounds(20, 625, 188, 40);
		delete_Product.setBounds(208, 625, 188, 40);
		fix_Product.setBounds(396, 625, 188, 40);
		search_p.setBounds(584,625,185,40);
		refund.setBounds(520,625,250,40);
		info.setBounds(20,625,250,40);
		search_r.setBounds(270,625,250,40);
		Panel2.setBounds(210, 0, 590, 780);
		imageinfo.setBounds(10,10,570,400);
		textarea01.setBounds(10,420,113,40);
		textarea02.setBounds(123,420,113,40);
		textarea03.setBounds(236,420,113,40);
		textarea04.setBounds(349,420,113,40);
		textarea05.setBounds(462,420,113,40);
		textarea0001.setBounds(20,470,180,100);
		textarea0002.setBounds(200,470,180,100);
		textarea0003.setBounds(380,470,180,100);
		PL02.setBounds(10,30,200,200);
		PL03.setBounds(20,20,760,600);
		PL04.setBounds(20,20,760,600);
		numbers01.setBounds(109,665,102,28);
		numbers.setBounds(145,665,66,30);
		Table3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		Table3.getColumnModel().getColumn(0).setPreferredWidth(100);
		Table3.getColumnModel().getColumn(1).setPreferredWidth(100);
		Table3.getColumnModel().getColumn(2).setPreferredWidth(100);
		Table3.getColumnModel().getColumn(3).setPreferredWidth(100);
		Table3.getColumnModel().getColumn(4).setPreferredWidth(100);
		Table3.getColumnModel().getColumn(5).setPreferredWidth(350);
		setJMenuBar(mb);
		mb.add(mm);
		mb.add(mm2);
		mb.add(exit);
		mm.add(open);
		mm.add(save);
		mm2.add(open_receipt);
		mm2.add(save_receipt);
		Panel4.add(PL04);
		Panel4.add(add_Product);
		Panel4.add(delete_Product);
		Panel4.add(fix_Product);
		Panel4.add(search_p);
		Panel3.add(refund);
		Panel3.add(info);
		Panel3.add(search_r);
		Panel3.add(PL03);
		Panel2.add(imageinfo);
		Panel2.add(textarea01);
		Panel2.add(textarea02);
		Panel2.add(textarea03);
		Panel2.add(textarea04);
		Panel2.add(textarea05);
		Panel2.add(textarea0001);
		Panel2.add(textarea0002);
		Panel2.add(textarea0003);
		Panel.add(PL);
		Panel.add(PL02);
		Panel.add(add);
		Panel.add(buy); 
		Panel.add(del);
		Panel.add(numbers);
		Panel.add(numbers01);
		add(Tab);
		Tab.addTab("계산",Panel);
		Tab.addTab("영수증",Panel3);
		Tab.addTab("제품목록",Panel4);
		Panel.add(Panel2);
		list01.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				str01=(String) list01.getSelectedValue();
			}
		});
		Table.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				selection=Table.getSelectedRow();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Table2.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				selection2=Table2.getSelectedRow();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		Table3.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				selection3=Table3.getSelectedRow();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		list01.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Product_info();
				settingNum();
				numbers.updateUI();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				for(int i=0;i<Products.size();i++){
					int size=0;
					if(str01.equals(Products.get(i).Name)){
						Object obj=numbers.getSelectedItem();
						selection01=(Integer) obj; //수량
						int count2=0;
						if(count2==0){
							size=selection01;
						}
						int Counting=Table.getRowCount();
						for(int j=0;j<Counting;j++){ //같은 제
							if(((String)Table.getValueAt(j,0)).equals(str01)){
								size=StringToInt((String)Table.getValueAt(j,1))+selection01;
								model.removeRow(j);
								count2=1;
								break;
							}
						}
						model.addRow(new Object[]{Products.get(i).Name,IntToString(size),IntToString(Products.get(i).Price*size)});
						Products.get(i).Amount-=selection01;
					}
				}
				settingNum();
				numbers.updateUI();
				model.fireTableDataChanged();
				model2.fireTableDataChanged();
				Table.setModel(model);
				Table2.setModel(model2);
				Table.updateUI();
				Table2.updateUI();
				Product_info();
			}
		});
		del.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String a=(String) Table.getValueAt(selection,0);
				int b=StringToInt((String)Table.getValueAt(selection, 1));
				for(int i=0;i<Products.size();i++){
					if(a.equals(Products.get(i).Name)){
						Products.get(i).Amount+=b;
					}
				}
				settingNum();
				model.removeRow(selection);
				model.fireTableDataChanged();
				model2.fireTableDataChanged();
				Table.setModel(model);
				Table2.setModel(model2);
				Table.updateUI();
				Table2.updateUI();
				Product_info();
			}
		});
		info.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int total=0;
				Frame1 = new JFrame();
				Frame1.setTitle("영수증");
				Frame1.setSize(400, 310);
				JScrollPane PL05=new JScrollPane(Table4);
				Table4.setFillsViewportHeight(true);
				for(int i=0;i<Receipts.get(selection2).Names.size();i++){
					model4.setRowCount(0);
					model4.addRow(new Object[]{Receipts.get(selection2).Names.get(i),Receipts.get(selection2).Amounts.get(i),Receipts.get(selection2).Prices.get(i)});
					total+=Receipts.get(selection2).Prices.get(i);
				}
				Frame1.setLayout(null);
				PL05.setBounds(0, 0, 400, 270);
				textarea0004.setText(IntToString(total));
				textarea0004.setBounds(320, 270, 80,20);
				total_price2.setBounds(270, 270, 60,20);
				Frame1.add(PL05);
				Frame1.add(textarea0004);
				Frame1.add(total_price2);
				Frame1.setVisible(true);
			}
		});
		buy.addActionListener(new ActionListener(){
			SimpleDateFormat Date1 =new SimpleDateFormat("yyyy/MM/dd/HH시mm분ss초",Locale.KOREA); //
			public void actionPerformed(ActionEvent e){
				if(Table.getRowCount()!=0){
					Vector<String> Names = new Vector<String>();
					Vector<Integer> Amounts = new Vector<Integer>();
					Vector<Integer> Prices = new Vector<Integer>();
					String Time=Date1.format(new Date());
					int Counting=Table.getRowCount();
					count++;
					for(int i=0;i<Counting;i++){
						String a=(String) Table.getValueAt(i,0);
						int b=StringToInt((String) Table.getValueAt(i,1));
						int c=StringToInt((String) Table.getValueAt(i,2));
						Names.addElement(a);
						Amounts.addElement(b);
						Prices.addElement(c);
					}
					for(int i=0;i<Counting;i++){
						model.removeRow(0);
					}
					Receipts.add(new Receipt(Time,Names,Amounts,Prices,count));
					model2.addRow(new Object[]{Receipts.get(Receipts.size()-1).Time,Receipts.get(Receipts.size()-1).order_number});
					calculate();
					allProduct();
					model.fireTableDataChanged();
					model2.fireTableDataChanged();
					Table.setModel(model);
					Table2.setModel(model2);
					Table.updateUI();
					Table2.updateUI();
					model3.fireTableDataChanged();
					model4.fireTableDataChanged();
					Table3.setModel(model3);
					Product_info();
				}
			}
		});

		refund.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int thiss = 0;
				for(int i=0;i<Receipts.size();i++){
					if(Receipts.get(i).order_number==(Integer)model2.getValueAt(selection2,1)){
						thiss=i;
						break;
					}
				}
				for(int j=0;j<Receipts.get(thiss).Names.size();j++){
					for(int l=0;l<Products.size();l++){
						if(Products.get(l).Name.equals(Receipts.get(thiss).Names.get(j))){
							Products.get(l).Amount+=Receipts.get(thiss).Amounts.get(j);
						}
					}
				}
				Receipts.remove(thiss);
				allProduct_model2();
				allProduct();
				settingNum();
				model2.fireTableDataChanged();
				model3.fireTableDataChanged();
				Table2.setModel(model2);
				Table3.setModel(model3);
				Table2.updateUI();
				Table3.updateUI();
				calculate();
			}
			
		});
		search_r.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Frame4=new JFrame();
				Frame4.setTitle("영수증검색");
				Frame4.setSize(600,660);
				Container contentPane = Frame4.getContentPane();
				model5 = new DefaultTableModel(userColumn5,0){
					public boolean isCellEditable(int i,int c){
						return false;
					}
				};
				Table5=new JTable(model5);//날짜 이름 수량 가격
				JScrollPane PL06 = new JScrollPane(Table5);
				search_r1=new JButton("주문번호검색");
				Frame4.setLayout(null);
				PL06.setBounds(0,0,600,600);
				search_r1.setBounds(500,600,100,40);
				contentPane.add(PL06);
				contentPane.add(search_r1);
				search_r1.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Frame3=new JFrame();
						Frame3.setTitle("주문번호검색");
						Frame3.setSize(400, 420);
						textarea101=new JTextArea();
						Container contentPane2 = Frame3.getContentPane();
						Frame3.setLayout(null);
						textarea101.setBounds(0,0,400,360);
						search_r3=new JButton("검색");
						search_r3.setBounds(340,360,60,40);
						contentPane2.add(textarea101);
						contentPane2.add(search_r3);
						Frame3.setVisible(true);
						search_r3.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								String abc=textarea101.getText();
								int abcd=-1;
								for(int i=0;i<Receipts.size();i++){
									if(StringToInt(abc)==Receipts.get(i).order_number){
										abcd=i;
									}
								}
								if(Receipts.size()==0 || abcd==-1){
									System.out.println("1");
								}
								else{
								model5.setRowCount(0);
								for(int i=0;i<Receipts.get(abcd).Names.size();i++){
									model5.addRow(new Object[]{Receipts.get(abcd).Time,Receipts.get(abcd).Names.get(i),Receipts.get(abcd).Amounts.get(i),Receipts.get(abcd).Prices.get(i)});
								}
								model5.fireTableDataChanged();
								Table5.updateUI();
								}
							}
							
						});
					}
					
				});
				Frame4.setVisible(true);
			}
			
		});
		search_p.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Frame4=new JFrame();
				Frame4.setTitle("제품검색");
				Frame4.setSize(600,660);
				Container contentPane = Frame4.getContentPane();
				model6 = new DefaultTableModel(userColumn6,0){
					public boolean isCellEditable(int i,int c){
						return false;
					}
				};
				Table6=new JTable(model6);//날짜 이름 수량 가격
				JScrollPane PL07 = new JScrollPane(Table6);
				search_p1=new JButton("이름검색");
				search_p2=new JButton("종류검색");
				search_p3=new JButton("가격검색");
				search_p4=new JButton("수량검색");
				search_p5=new JButton("인기도검색");
				Frame4.setLayout(null);
				PL07.setBounds(0,0,600,600);
				search_p5.setBounds(450,600,100,40);
				search_p4.setBounds(350,600,100,40);
				search_p3.setBounds(250,600,100,40);
				search_p2.setBounds(150,600,100,40);
				search_p1.setBounds(50,600,100,40);
				contentPane.add(PL07);
				contentPane.add(search_p1);
				contentPane.add(search_p2);
				contentPane.add(search_p3);
				contentPane.add(search_p4);
				contentPane.add(search_p5);
				search_p1.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Frame3=new JFrame();
						Frame3.setTitle("이름검색");
						Frame3.setSize(400, 420);
						textarea101=new JTextArea();
						Container contentPane2 = Frame3.getContentPane();
						Frame3.setLayout(null);
						textarea101.setBounds(0,0,400,360);
						search_p6=new JButton("검색");
						search_p6.setBounds(340,360,60,40);
						contentPane2.add(textarea101);
						contentPane2.add(search_p6);
						Frame3.setVisible(true);
						search_p6.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								String abc=textarea101.getText();
								if(Products.size()==0){
									System.out.println("1");
								}
								else{
									model6.setRowCount(0);
									for(int i=0;i<Products.size();i++){
										if(Products.get(i).Name.equals(abc)){
											model6.addRow(new Object[]{Products.get(i).Name,Products.get(i).Kind,Products.get(i).Price,Products.get(i).Amount,Products.get(i).Popularity});
										}
									}
									model6.fireTableDataChanged();
									Table6.updateUI();
								}
							}
							
						});
					}
					
				});
				search_p2.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Frame3=new JFrame();
						Frame3.setTitle("종류검색");
						Frame3.setSize(400, 420);
						textarea101=new JTextArea();
						Container contentPane2 = Frame3.getContentPane();
						Frame3.setLayout(null);
						textarea101.setBounds(0,0,400,360);
						search_p6=new JButton("검색");
						search_p6.setBounds(340,360,60,40);
						contentPane2.add(textarea101);
						contentPane2.add(search_p6);
						Frame3.setVisible(true);
						search_p6.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								String abc=textarea101.getText();
								if(Products.size()==0){
									System.out.println("1");
								}
								else{
									model6.setRowCount(0);
									for(int i=0;i<Products.size();i++){
										if(Products.get(i).Kind.equals(abc)){
											model6.addRow(new Object[]{Products.get(i).Name,Products.get(i).Kind,Products.get(i).Price,Products.get(i).Amount,Products.get(i).Popularity});
										}
									}
									model6.fireTableDataChanged();
									Table6.updateUI();
								}
							}
							
						});
					}
					
				});
				search_p3.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Frame3=new JFrame();
						Frame3.setTitle("가격검색");
						Frame3.setSize(400, 420);
						textarea101=new JTextArea();
						Container contentPane2 = Frame3.getContentPane();
						Frame3.setLayout(null);
						textarea101.setBounds(0,0,400,360);
						search_p6=new JButton("검색");
						search_p6.setBounds(340,360,60,40);
						contentPane2.add(textarea101);
						contentPane2.add(search_p6);
						Frame3.setVisible(true);
						search_p6.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								String abc=textarea101.getText();
								if(Products.size()==0){
									System.out.println("1");
								}
								else{
									model6.setRowCount(0);
									for(int i=0;i<Products.size();i++){
										if(Products.get(i).Price==StringToInt(abc)){
											model6.addRow(new Object[]{Products.get(i).Name,Products.get(i).Kind,Products.get(i).Price,Products.get(i).Amount,Products.get(i).Popularity});
										}
									}
									model6.fireTableDataChanged();
									Table6.updateUI();
									}
							}
							
						});
					}
					
				});
				search_p4.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Frame3=new JFrame();
						Frame3.setTitle("수량검색");
						Frame3.setSize(400, 420);
						textarea101=new JTextArea();
						Container contentPane2 = Frame3.getContentPane();
						Frame3.setLayout(null);
						textarea101.setBounds(0,0,400,360);
						search_p6=new JButton("검색");
						search_p6.setBounds(340,360,60,40);
						contentPane2.add(textarea101);
						contentPane2.add(search_p6);
						Frame3.setVisible(true);
						search_p6.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								String abc=textarea101.getText();
								if(Products.size()==0){
									System.out.println("1");
								}
								else{
									model6.setRowCount(0);
									for(int i=0;i<Products.size();i++){
										if(Products.get(i).Amount==StringToInt(abc)){
											model6.addRow(new Object[]{Products.get(i).Name,Products.get(i).Kind,Products.get(i).Price,Products.get(i).Amount,Products.get(i).Popularity});
										}
									}
									model6.fireTableDataChanged();
									Table6.updateUI();
									}
							}
							
						});
					}
					
				});
				search_p5.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Frame3=new JFrame();
						Frame3.setTitle("인기도검색");
						Frame3.setSize(400, 420);
						textarea101=new JTextArea();
						Container contentPane2 = Frame3.getContentPane();
						Frame3.setLayout(null);
						textarea101.setBounds(0,0,400,360);
						search_p6=new JButton("검색");
						search_p6.setBounds(340,360,60,40);
						contentPane2.add(textarea101);
						contentPane2.add(search_p6);
						Frame3.setVisible(true);
						search_p6.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								String abc=textarea101.getText();
								if(Products.size()==0){
									System.out.println("1");
								}
								else{
									model6.setRowCount(0);
									for(int i=0;i<Products.size();i++){
										if(Products.get(i).Popularity.equals(abc)){
											model6.addRow(new Object[]{Products.get(i).Name,Products.get(i).Kind,Products.get(i).Price,Products.get(i).Amount,Products.get(i).Popularity});
										}
									}
									model6.fireTableDataChanged();
									Table6.updateUI();
									}
								}
							
						});
					}
					
				});
				Frame4.setVisible(true);
			}
		});
		add_Product.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Frame1 = new JFrame();
				P_all=new JButton("추가");
				P_all2=new JButton("이미지선택");
				a01=textarea001.getText().toString();
				b=textarea003.getText().toString();
				f=textarea005.getText().toString();
				c=-1;
				d=-1;
				if(textarea002.getText().equals(null)==false && isNumeric(textarea002.getText())){
					c=StringToInt(textarea002.getText().toString());
				}
				if(textarea004.getText().equals(null)==false && isNumeric(textarea004.getText())){
					d=StringToInt(textarea004.getText().toString());
				}
				Frame1.setTitle("제품추가"); 
				Container contentPane = Frame1.getContentPane();
				Edit_textarea(1);
				textarea001.setText("");
				textarea002.setText("");
				textarea003.setText("");
				textarea004.setText("");
				textarea005.setText("");
				textarea001.setBorder(new TitledBorder("제품명"));
				textarea002.setBorder(new TitledBorder("가격"));
				Frame1.setLayout(null);
				textarea003.setBorder(new TitledBorder("종류"));
				textarea004.setBorder(new TitledBorder("수량"));
				textarea005.setBorder(new TitledBorder("인기도"));
				textarea001.setBounds(5,10,103,40);
				textarea002.setBounds(108,10,103,40);
				textarea003.setBounds(211,10,103,40);
				textarea004.setBounds(314,10,103,40);
				textarea005.setBounds(417,10,103,40);
				P_all2.setBounds(520,10,103,40);
				P_all.setBounds(625,10,80,40);
				contentPane.add(textarea001);
				contentPane.add(textarea002);
				contentPane.add(textarea003);
				contentPane.add(textarea004);
				contentPane.add(textarea005);
				contentPane.add(P_all2); 
				contentPane.add(P_all);
				P_all2.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						int answer=fc.showOpenDialog(null);
						if(answer==JFileChooser.APPROVE_OPTION){
							File f = fc.getSelectedFile();
							e0=new ImageIcon(f.getPath());
						}
					}
					
				});
				P_all.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						a01=textarea001.getText().toString();
						b=textarea003.getText().toString();
						f=textarea005.getText().toString();
						c=-1;
						d=-1;
						if(textarea002.getText().equals(null)==false && isNumeric(textarea002.getText())){
							c=StringToInt(textarea002.getText().toString());
						}
						if(textarea004.getText().equals(null)==false && isNumeric(textarea004.getText())){
							d=StringToInt(textarea004.getText().toString());
						}
						if(isthere(a01,b,c,d,e0,f)==true){
							Products.add(new Product(a01,b,c,d,e0,f));
							Frame2=new JFrame();
							Frame2.setTitle("알림");
							textarea006.setText("\n                  추가 되었습니다.");
							textarea006.setEditable(false);
							Container c = Frame2.getContentPane();
							c.add(textarea006);
							updatinglist(list01);
							Frame2.setSize(230,70);
							Frame2.setVisible(true);
							settingNum();
							allProduct();
							calculate();
							model3.fireTableDataChanged();
							model4.fireTableDataChanged();
							Table3.setModel(model3);
							Table4.setModel(model4);
							Table3.updateUI();
							Panel4.updateUI();
						}
						else{
							Frame2=new JFrame();
							Frame2.setTitle("알림");
							textarea006.setText("\n             모두 입력,추가해주세요.");
							textarea006.setEditable(false);
							Container c = Frame2.getContentPane();
							c.add(textarea006);
							Frame2.setSize(230,70);
							Frame2.setVisible(true);
						}
					}
					
				});
				Frame1.setSize(710,80);
				contentPane.setVisible(true);
				Frame1.setVisible(true);
			}
			
		});
		delete_Product.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Frame1 = new JFrame();
				P_all=new JButton("제거");
				Ask=new JLabel("정말 제거하시겠습니까?");
				Image00=new JLabel(Products.get(selection3).Image);
				Frame1.setTitle("제품제거"); 
				Container contentPane = Frame1.getContentPane();
				Image img=Products.get(selection3).Image.getImage();
				Image00.setIcon(resizeImg(img));
				Edit_textarea(0);
				textarea001.setText(Products.get(selection3).Name);
				textarea002.setText(IntToString(Products.get(selection3).Price));
				textarea003.setText(Products.get(selection3).Kind);
				textarea004.setText(IntToString(Products.get(selection3).Amount));
				textarea005.setText(Products.get(selection3).Popularity);
				textarea001.setBorder(new TitledBorder("제품명"));
				textarea002.setBorder(new TitledBorder("가격"));
				Frame1.setLayout(null);
				textarea003.setBorder(new TitledBorder("종류"));
				textarea004.setBorder(new TitledBorder("수량"));
				textarea005.setBorder(new TitledBorder("인기도"));
				Image00.setBorder(new TitledBorder("제품이미지"));
				textarea001.setBounds(13,410,113,40);
				textarea002.setBounds(126,410,113,40);
				textarea003.setBounds(239,410,113,40);
				textarea004.setBounds(352,410,113,40);
				textarea005.setBounds(465,410,113,40);
				Image00.setBounds(10,10,570,400);
				P_all.setBounds(347,465,50,30);
				Ask.setBounds(224, 460, 150, 40);
				contentPane.add(textarea001);
				contentPane.add(textarea002);
				contentPane.add(textarea003);
				contentPane.add(textarea004);
				contentPane.add(textarea005);
				contentPane.add(Image00); 
				contentPane.add(P_all);
				contentPane.add(Ask);
				P_all.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Products.remove(selection3);
						Frame2=new JFrame();
						Frame2.setTitle("알림");
						textarea006.setText("\n                  제거 되었습니다.");
						textarea006.setEditable(false);
						Container c = Frame2.getContentPane();
						c.add(textarea006);
						Frame2.setSize(230,70);
						Frame2.setVisible(true);
						allProduct();
						updatinglist(list01);
						calculate();
						model3.fireTableDataChanged();
						Table3.setModel(model3);
						Table3.updateUI();
						Panel4.updateUI();
					}
				});
				
				Frame1.setSize(590,540);
				contentPane.setVisible(true);
				Frame1.setVisible(true);
			}
			
		});
		
		fix_Product.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Frame1 = new JFrame();
				P_all=new JButton("수정");
				Image00=new JLabel(Products.get(selection3).Image);
				Frame1.setTitle("제품수정"); 
				Container contentPane = Frame1.getContentPane();
				Image img=Products.get(selection3).Image.getImage();
				Image00.setIcon(resizeImg(img));
				P_all2=new JButton("이미지변경");
				Edit_textarea(1);
				textarea001.setText(Products.get(selection3).Name);
				textarea002.setText(IntToString(Products.get(selection3).Price));
				textarea003.setText(Products.get(selection3).Kind);
				textarea004.setText(IntToString(Products.get(selection3).Amount));
				textarea005.setText(Products.get(selection3).Popularity);
				textarea001.setBorder(new TitledBorder("제품명"));
				textarea002.setBorder(new TitledBorder("가격"));
				Frame1.setLayout(null);
				textarea003.setBorder(new TitledBorder("종류"));
				textarea004.setBorder(new TitledBorder("수량"));
				textarea005.setBorder(new TitledBorder("인기도"));
				Image00.setBorder(new TitledBorder("제품이미지"));
				textarea001.setBounds(13,410,113,40);
				textarea002.setBounds(126,410,113,40);
				textarea003.setBounds(239,410,113,40);
				textarea004.setBounds(352,410,113,40);
				textarea005.setBounds(465,410,113,40);
				P_all.setBounds(578, 10, 100, 100);
				Image00.setBounds(10,10,570,400);
				P_all2.setBounds(578,410,100,40);
				contentPane.add(textarea001);
				contentPane.add(textarea002);
				contentPane.add(textarea003);
				contentPane.add(textarea004);
				contentPane.add(textarea005);
				contentPane.add(Image00); 
				contentPane.add(P_all);
				contentPane.add(P_all2);
				a01=textarea001.getText().toString();
				b=textarea003.getText().toString();
				f=textarea005.getText().toString();
				c=-1;
				d=-1;
				if(textarea002.getText().equals(null)==false && isNumeric(textarea002.getText())){
					c=StringToInt(textarea002.getText().toString());
				}
				if(textarea004.getText().equals(null)==false && isNumeric(textarea004.getText())){
					d=StringToInt(textarea004.getText().toString());
				}
				e0=Products.get(selection3).Image;
				P_all2.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						int answer=fc.showOpenDialog(null);
						if(answer==JFileChooser.APPROVE_OPTION){
							File f = fc.getSelectedFile();
							e0=new ImageIcon(f.getPath());
						}
					}
					
				});
				P_all.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(isthere(a01,b,c,d,e0,f)==true){
							a01=textarea001.getText().toString();
							b=textarea003.getText().toString();
							f=textarea005.getText().toString();
							c=-1;
							d=-1;
							if(textarea002.getText().equals(null)==false && isNumeric(textarea002.getText())){
								c=StringToInt(textarea002.getText().toString());
							}
							if(textarea004.getText().equals(null)==false && isNumeric(textarea004.getText())){
								d=StringToInt(textarea004.getText().toString());
							}
							Products.set(selection3, new Product(a01,b,c,d,e0,f));
							Frame2=new JFrame();
							Frame2.setTitle("알림");
							textarea006.setText("\n                  수정 되었습니다.");
							textarea006.setEditable(false);
							Container c = Frame2.getContentPane();
							c.add(textarea006);
							Frame2.setSize(230,70);
							Frame2.setVisible(true);
							allProduct();
							settingNum();
							updatinglist(list01);
							calculate();
							model3.fireTableDataChanged();
							Table3.setModel(model3);
							Panel4.updateUI();
							Table3.updateUI();
						}
						else{
							Frame2=new JFrame();
							Frame2.setTitle("알림");
							textarea006.setText("\n             모두 입력,추가해주세요.");
							textarea006.setEditable(false);
							Container c = Frame2.getContentPane();
							c.add(textarea006);
							Frame2.setSize(230,70);
							Frame2.setVisible(true);
						}
					}
					
				});
				Frame1.setSize(687,480);
				contentPane.setVisible(true);
				Frame1.setVisible(true);
			}
		});
		open.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				FileInputStream fin = null;
				ObjectInputStream ois = null;
					try{
						fin = new FileInputStream("Convenient_Products.dat");
						ois = new ObjectInputStream(fin);
						ArrayList list = (ArrayList)ois.readObject();
						Iterator it = list.iterator();
						int al=Products.size();
						for(int i=0;i<al;i++){
							Products.remove(0);
						}
						while(it.hasNext()){
							Product nb = (Product)it.next();
							Products.add(nb);
						}
						allProduct();
						updatinglist(list01);
					}catch(Exception ex){
					}finally{
						try{
							ois.close();
							fin.close();
						}catch(IOException ioe){}
					}
				model3.fireTableDataChanged();
				Table3.updateUI();
			}
			
		});
		save.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FileOutputStream fout = null;
			    ObjectOutputStream oos = null;
			    try{
			            fout = new FileOutputStream("Convenient_Products.dat");
			            oos = new ObjectOutputStream(fout);
			            oos.writeObject(Products);
			            oos.reset();
			            System.out.println("저장되었습니다");
			        }catch(Exception ex){
			        }finally{
			        	try{
			            oos.close();
		                fout.close();
		            }catch(IOException ioe){}
		        }
			}
			
		});
		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		save_receipt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FileOutputStream fout = null;
		        ObjectOutputStream oos = null;
		        try{
		            fout = new FileOutputStream("Convenient_Receipt.dat");
		            oos = new ObjectOutputStream(fout);
		            oos.writeObject(Receipts);
		            oos.reset();
		            System.out.println("저장되었습니다");
		        }catch(Exception ex){
		        }finally{
		            try{
		                oos.close();
		                fout.close();
		            }catch(IOException ioe){}
		        }
			}
		});
		open_receipt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FileInputStream fin = null;
				ObjectInputStream ois = null;
					try{
						fin = new FileInputStream("Convenient_Receipt.dat");
						ois = new ObjectInputStream(fin);
						ArrayList list = (ArrayList)ois.readObject();
						Iterator it = list.iterator();
						
						while(it.hasNext()){
							Receipt nb = (Receipt)it.next();
							Receipts.add(nb);
							count++;
						}
						allProduct_model2();
						calculate();
						model2.fireTableDataChanged();
						Table2.updateUI();
					}catch(Exception ex){
					}finally{
						try{
							ois.close();
							fin.close();
						}catch(IOException ioe){}
				}
			}
		});
		setSize(820,800);
		setResizable(false);
		setVisible(true);
		
	}
	
	public void settingNum(){
		numbers.removeAllItems();
		selection01=list01.getSelectedIndex();
		System.out.println(selection01);
		if(selection01==-1){
			selection01=0;
		}
		if(selection01==-1){
			selection=0;
		}
		for(int i=0;i<Products.get(selection01).Amount;i++){
			numbers.addItem(i+1);
			numbers.setEnabled(true);
		}
		if(Products.get(selection01).Amount==0){
			numbers.addItem(0);
			numbers.setEnabled(false);
		}
	}
	
	public String IntToString(int a){
		return Integer.toString(a);
	}
	
	public Integer StringToInt(String str){
		return Integer.parseInt(str);
	}
	
	
	public void Product_info(){
		str02=(String)list01.getSelectedValue();
		for(int i=0;i<Products.size();i++){
			if(str01.equals(Products.get(i).Name)){
				textarea01.setText(Products.get(i).Name);
				textarea02.setText(IntToString(Products.get(i).Price));
				textarea03.setText(Products.get(i).Kind);
				textarea04.setText(IntToString(Products.get(i).Amount));
				textarea05.setText(Products.get(i).Popularity);
				Image img=Products.get(i).Image.getImage();
				imageinfo.setIcon(resizeImg(img));
				break;
			} // 
		} //
		textarea01.updateUI();
		textarea02.updateUI();
		textarea03.updateUI();
		textarea04.updateUI();
		textarea05.updateUI();
		imageinfo.updateUI();
	}
	
	public ImageIcon resizeImg(Image img){
		double w=img.getWidth(null);
		double h=img.getHeight(null);
		double p=w/h;
		double a=400*p;
		Image newimg = img.getScaledInstance((int)a, 400, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newimg);
		return newIcon;
	}
	
	public boolean isthere(String a01,String b,int c,int d,ImageIcon e0,String f){
		if(a01.isEmpty() || b.isEmpty() || c==-1 || d==-1 || e0.toString().isEmpty() || f.isEmpty()){
			return false;
		}
		return true;
	}
	
	public void updatinglist(JList list01){
		Products_name.removeAllElements();
		for(int i=0;i<Products.size();i++)
			Products_name.add(Products.get(i).Name);
		list01.updateUI();
	}
	
	
	public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}
	
	public void Edit_textarea(int a){
		if(a==1){
			textarea001.setEditable(true);
			textarea002.setEditable(true);
			textarea003.setEditable(true);
			textarea004.setEditable(true);
			textarea005.setEditable(true);
			textarea006.setEditable(true);
		}
		if(a==0){
			textarea001.setEditable(false);
			textarea002.setEditable(false);
			textarea003.setEditable(false);
			textarea004.setEditable(false);
			textarea005.setEditable(false);
			textarea006.setEditable(false);
		}
	}
	
	public void calculate(){
		total_price=0;
		total_customer=0;
		rest_product=0;
		for(int i=0;i<Receipts.size();i++){
			for(int j=0;j<Receipts.get(i).Names.size();j++){
				total_price+=Receipts.get(i).Prices.get(j);
			}
			total_customer++;
		}
		for(int i=0;i<Products.size();i++){
			if(Products.get(i).Amount!=0){
				rest_product+=Products.get(i).Amount;
			}
		}
		textarea0001.setText(IntToString(total_price));
		textarea0002.setText(IntToString(total_customer));
		textarea0003.setText(IntToString(rest_product));
	}
	
	public void allProduct_model2(){
		model2.setRowCount(0);
		for(int j=0;j<Receipts.size();j++){
			model2.addRow(new Object[]{Receipts.get(j).Time,Receipts.get(j).order_number});
		}
	}

	public void allProduct(){
		model3.setRowCount(0);
		for(int i=0;i<Products.size();i++){
			model3.addRow(new Object[]{Products.get(i).Name,Products.get(i).Kind,Products.get(i).Price,Products.get(i).Amount,Products.get(i).Popularity,Products.get(i).Image});
		}
	}
	public static void main(String[] args){
		new Convenient();
	}
}
