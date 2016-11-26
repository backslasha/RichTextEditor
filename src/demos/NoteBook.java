package demos;

import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
public class NoteBook extends Frame {
	Frame f;
	TextArea content;                                  //�����ı���
	String filepath="";                                //����·��Ϊ��
	Color color=Color.red;
	Toolkit toolKit=Toolkit.getDefaultToolkit();
	Clipboard clipboard=toolKit.getSystemClipboard();  //��ȡ���а��������
	
	NoteBook(){
		f=new Frame();
		MenuBar mbar=new MenuBar();                    //�����˵���
		
		//����������ı���
		content=new TextArea("",50,50,TextArea.SCROLLBARS_VERTICAL_ONLY);
		add(content);
		content.setVisible(true);	
		content.requestFocusInWindow();
		
		//�����˵�����:�ļ�,�༭,��ʽ,�鿴,����
		Menu menuFile = new Menu("�ļ���F��");
		Menu menuEdit = new Menu("�༭��E��");
		Menu menuformat = new Menu("��ʽ��O��");
		Menu menuview = new Menu("�鿴��V��");
		Menu menuHelp = new Menu("������H��");
	    
		//����"�ļ�"�˵��Ĳ˵������
		MenuItem menuItemFileNew=new MenuItem("�½���N��",new MenuShortcut(KeyEvent.VK_N,false));
		MenuItem menuItemFileOpen=new MenuItem("�򿪣�O��...",new MenuShortcut(KeyEvent.VK_O,false));
		MenuItem menuItemFileSave=new MenuItem("���棨S��",new MenuShortcut(KeyEvent.VK_S,false));		
		MenuItem menuItemFileSaveAs=new MenuItem("���Ϊ��A��...");
		MenuItem menuItemFileSet=new MenuItem("ҳ�����ã�U��...",new MenuShortcut(KeyEvent.VK_U,false));
		MenuItem menuItemFilePrint=new MenuItem("��ӡ��P��...",new MenuShortcut(KeyEvent.VK_P,false));
		MenuItem menuItemFileQuit=new MenuItem("�˳���X��");
		menuItemFileSet.setEnabled(false);    //���øò˵������
		menuItemFilePrint.setEnabled(false);
	    
		//����"�༭"�˵��Ĳ˵������
		MenuItem menuItemEditUndo=new MenuItem("������U��",new MenuShortcut(KeyEvent.VK_Z,false));
		MenuItem menuItemEditCut=new MenuItem("���У�T��",new MenuShortcut(KeyEvent.VK_X,false));
		MenuItem menuItemEditCopy=new MenuItem("���ƣ�C��",new MenuShortcut(KeyEvent.VK_C,false));		
		MenuItem menuItemEditPaste=new MenuItem("ճ����P��",new MenuShortcut(KeyEvent.VK_V,false));
		MenuItem menuItemEditDelete=new MenuItem("ɾ����L��",new MenuShortcut(KeyEvent.VK_DELETE,false));
		MenuItem menuItemEditFind=new MenuItem("���ң�F��",new MenuShortcut(KeyEvent.VK_F,false));
		MenuItem menuItemEditFindnext=new MenuItem("������һ����N��",new MenuShortcut(KeyEvent.VK_F3,false));
		MenuItem menuItemEditReplace=new MenuItem("�滻��R��",new MenuShortcut(KeyEvent.VK_H,false));
		MenuItem menuItemEditTurnto=new MenuItem("ת����G��",new MenuShortcut(KeyEvent.VK_G,false));
		MenuItem menuItemEditDate=new MenuItem("ʱ��/���ڣ�D��",new MenuShortcut(KeyEvent.VK_F5,false));
		menuItemEditUndo.setEnabled(false);
		menuItemEditFindnext.setEnabled(false);
		menuItemEditTurnto.setEnabled(false);
		//�����˵�
		Menu menuItemEditChoice=new Menu("ѡ��C��");
		//�����˵�ѡ��
		MenuItem Allchoice=new MenuItem("ȫѡ��A��",new MenuShortcut(KeyEvent.VK_A,false));
		MenuItem Oppositechoice=new MenuItem("����ѡ��B��",new MenuShortcut(KeyEvent.VK_B,false));
		MenuItem Chinesechoice=new MenuItem("ѡ���֣�C��",new MenuShortcut(KeyEvent.VK_C,false));
		Oppositechoice.setEnabled(false);
		Chinesechoice.setEnabled(false);
		menuItemEditChoice.add(Allchoice);
		menuItemEditChoice.add(Oppositechoice);
        menuItemEditChoice.add(Chinesechoice);
		
        //����"��ʽ"�˵��Ĳ˵������
        CheckboxMenuItem menuItemlineturn=new CheckboxMenuItem("�Զ����У�W��");
        MenuItem menuItemword=new MenuItem("���壨F��");
        MenuItem menuItemcolor=new MenuItem("������ɫ��C��");
        
        //����"�鿴���˵��Ĳ˵������
        MenuItem menuItemcount=new MenuItem("����ͳ�ƣ�C��");
        MenuItem menuItemstate=new MenuItem("״̬����S��");
        menuItemstate.setEnabled(false);
        
		//����"����"�˵��Ĳ˵������
		MenuItem menuItemHelp=new MenuItem("�������ߣ�A��");
		
		
		//�¼�����,�������������ʵ�ָ��˵��Ĳ˵���Ĺ���
		//"�ļ�"�˵�
		//�½�
		menuItemFileNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s1= content.getText();
				if(!s1.equals("")){//�ı������ı���Ϊ��
		               int result = JOptionPane.showConfirmDialog(
		                       null, "�Ƿ�Ҫ���棿","�����ļ�",JOptionPane.YES_NO_CANCEL_OPTION);
		               if(result == JOptionPane.NO_OPTION){         //������
		            	   content.setText("");
		               }
		               else if(result == JOptionPane.CANCEL_OPTION){//ȡ���½�
		            	   
		               }
		               else if(result == JOptionPane.YES_OPTION)    //ѡ�񱣴�
		               {
		                   JFileChooser jfc = new JFileChooser();   //����ѡ�񱣴�·�����ļ���
		                   int bcf = jfc.showSaveDialog(f);
		                   if(bcf == JFileChooser.APPROVE_OPTION){
		                	   try{
		                		   //�����ļ�
		                           BufferedWriter bfw = new BufferedWriter(
		                           new FileWriter(new File(jfc.getSelectedFile().getAbsolutePath()+".txt")));
		                           filepath = jfc.getSelectedFile().getAbsolutePath()+".txt";//��ȡ�ļ������·��
		                           bfw.write(s1);                                            //���ļ�д������
		                           bfw.flush();                                              //��ջ���������
		                           bfw.close();                                              //�ر������
		                           } catch (IOException ex){
		                        	   Logger.getLogger(NoteBook.class.getName()).log(Level.SEVERE, null, ex);//
		                           }
		                   new NoteBook();//�½��ı��ļ�
		                   }
		                   }
		               }
				}
		});
		
		//��
		menuItemFileOpen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FileDialog fd= new FileDialog(new Frame(),"��....",FileDialog.LOAD);
				fd.setVisible(true);                        //���ÿɼ���
				filepath = fd.getDirectory() + fd.getFile();//��ȡ�ļ�Ŀ¼���������ļ�·��
	            File file = new File(filepath);             //���ļ�·���������ļ�
				BufferedReader br = null;                   //�������뻺����Ϊ��
				StringBuilder sb = new StringBuilder();
				try{
					br=new BufferedReader(new FileReader(file));
					String str=null;
					while((str=br.readLine())!=null){       //���ж�ȡ���ݣ�ֱ��Ϊ��
						sb.append(str).append("\n");
					}
					content.setText(sb.toString());         //�������ʾ���ı�����    
				}catch(FileNotFoundException e1){
					e1.printStackTrace();
				}catch(IOException e2){
					e2.printStackTrace();
				}
			}
		});
		
		//����
		menuItemFileSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(filepath.equals("")){                    //��·��Ϊ��ʱ�������Ϊ
					JFileChooser jfc=new JFileChooser();    //����ѡ�񱣴�·�����ļ���
					int ssd=jfc.showSaveDialog(f);          //�������洰��
					if(ssd==JFileChooser.APPROVE_OPTION){
						try{
							//�����ļ�
							BufferedWriter bw=new BufferedWriter(
							new FileWriter(new File(jfc.getSelectedFile().getAbsolutePath()+".txt")));
							filepath=jfc.getSelectedFile().getAbsolutePath();//����·��
							bw.write(content.getText());
							bw.flush();
							bw.close();
						}catch(IOException ex){
							Logger.getLogger(NoteBook.class.getName()).log(Level.SEVERE,null,ex);
						}
					}
				}
				else{//·����Ϊ��ʱ��������ԭ����·����
					try{
						//�����ļ�
						BufferedWriter bw=new BufferedWriter(
						new FileWriter(new File(filepath)));
						bw.write(content.getText());
						bw.flush();
						bw.close();
					}catch(IOException ex){
						Logger.getLogger(NoteBook.class.getName()).log(Level.SEVERE,null,ex);
					}
				}
			}
		});
		
		//���Ϊ
		menuItemFileSaveAs.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFileChooser jfc=new JFileChooser();              //����ѡ�񱣴�·�����ļ���
				int ssd=jfc.showSaveDialog(f);                    //�������洰�� 
				if(ssd==JFileChooser.APPROVE_OPTION){
					try{
						//�����ļ�
						BufferedWriter bw=new BufferedWriter(
						new FileWriter(new File(jfc.getSelectedFile().getAbsolutePath()+".txt")));
						filepath=jfc.getSelectedFile().getAbsolutePath();
						bw.write(content.getText());
						bw.flush();
						bw.close();
					}catch(IOException ex){
						Logger.getLogger(NoteBook.class.getName()).log(Level.SEVERE,null,ex);
					}
				}
			}
		});
		
		//ҳ������
		menuItemFileSet.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//�ù�����δʵ��
			}
		});
		
		//��ӡ
		menuItemFilePrint.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//�ù�����δʵ��
			}
		});
		
		//�˳�
		menuItemFileQuit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object[] options={"�ǵģ���Ҫ�˳�","�����"};
				int option=JOptionPane.showOptionDialog(null,"��ȷ��Ҫ�˳���",
				"�˳���ʾ",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,null,options,options[0]);
			}
		});
		
		
		//"�༭"�˵�
		//����
		menuItemEditUndo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//�ù�����δʵ��
			}
		});
		
		//����
		menuItemEditCut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s=content.getSelectedText();
				StringSelection ss=new StringSelection(s);//
				clipboard.setContents(ss,null);           //�����а���������õ�����ss��
				if(s.length()==0){
					return;
				}else{
					content.replaceRange("",content.getSelectionStart(),content.getSelectionEnd());
				}
			}
		});
		
		//����
		menuItemEditCopy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s=content.getSelectedText();
				StringSelection ss=new StringSelection(s);
				clipboard.setContents(ss,null);
			}
		});
		
		//ճ��
		menuItemEditPaste.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Transferable t=clipboard.getContents(this);            //���ر�ʾ���а嵱ǰ���ݵĶ���
				String s=null;
				try{
					s=(String) t.getTransferData(DataFlavor.stringFlavor);//����ǰ���ݱ����ڶ���s��
				}catch(UnsupportedFlavorException e1){
					e1.printStackTrace();					
				}catch(IOException e1){
					e1.printStackTrace();
				}
				if(s==null)
					return;
				try{
					content.replaceRange(s,content.getSelectionStart(),content.getSelectionEnd());
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		
		//ɾ��
		menuItemEditDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				content.setText("");
			}
		});
		
		//����
		menuItemEditFind.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Dialog d = new Dialog(f,"�����ַ���...",true);
				d.setBounds(300,200,300,100);
				JLabel find = new JLabel("�������ַ��� :");
				final TextField tf = new TextField(1);
				Button b = new Button("����");
				d.setLayout(null);
				find.setBounds(10,30,90,20);
				tf.setBounds(100,30,90,20);
				b.setBounds(200,30,80,20);
				d.add(find);
				d.add(tf);
				d.add(b);
				b.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						String text=content.getText();
				        String str=tf.getText();
				        int end=text.length();
				        int len=str.length();
				        int start=content.getSelectionEnd();
				        if(start==end){
				        	start=0;
				        }
				        for(;start<=end-len;start++){
				            if(text.substring(start,start+len).equals(str)){
				            	content.setSelectionStart(start);
				            	content.setSelectionEnd(start+len);
				                return;
				            }
				        }
				        //���Ҳ��������ַ������򽫹������ĩβ 
				        content.setSelectionStart(end);
				        content.setSelectionEnd(end);
				    }
					   
				   });
		        d.addWindowListener(new WindowAdapter(){
		            public void windowClosing(WindowEvent e){
		                f.dispose();
		            }
		        });
		        d.setResizable(false);
				d.setVisible(true);
			}
		});
		
		//������һ��
		menuItemEditFindnext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//�ù�����δʵ��
			}
		});
		
		//�滻
		menuItemEditReplace.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Dialog d= new JDialog(f,"�ַ����滻...",true);
				d.setBounds(560,250,310,180);
				final Label L1 = new Label("������Ҫ�滻���ַ��� ��");
				final Label L2 = new Label("�������滻����ַ��� ��");
				TextField tf1 = new TextField(10);
				TextField tf2 = new TextField(10);
				JButton jb = new JButton("�滻");
				d.setLayout(null);
				//���ô�С��λ��
		        L1.setBounds(10,30,150,20);
		        L2.setBounds(10,70,150,20);
		        tf1.setBounds(160,30,110,20);
		        tf2.setBounds(160,70,110,20);
		        jb.setBounds(100,110,80,20);
		        //�������ӵ��Ի�����
		        d.add(L1);
		        d.add(L2);
		        d.add(tf1);
		        d.add(tf2);
		        d.add(jb);
		        final String text = content.getText();
		        final String str1 = L1.getText();
				final String str2 = L2.getText();
				jb.addActionListener(new ActionListener(){
				  public void actionPerformed(ActionEvent e) {
		            if(content.getSelectedText().equals(L1.getText())){
		        	       content.replaceRange(str2,content.getSelectionStart(),content.getSelectionEnd());
		                 }
		            else {
		                 int end=text.length();
		                 int len=str1.length();
		                 int start=content.getSelectionEnd();
		                 if(start==end) start=0;
		                 for(;start<=end-len;start++){
		                     if(text.substring(start,start+len).equals(str1)){
		                	     content.setSelectionStart(start);
		                	     content.setSelectionEnd(start+len);
		                         return;
		                     }
		                  }
		                  //���Ҳ��������ַ������򽫹������ĩβ
		                  content.setSelectionStart(end);
		                  content.setSelectionEnd(end);
		                 }
				       }
			        });
				  d.setResizable(false);
	              d.setVisible(true);
			}
		});
		
		//ת��
		menuItemEditTurnto.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//�ù�����δʵ��
			}
		});
		
		//ʱ��/����
		menuItemEditDate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Calendar c=Calendar.getInstance(); 
				int year=c.get(Calendar.YEAR); 
				int month=c.get(Calendar.MONTH); 
				int date=c.get(Calendar.DATE); 
				int hour=c.get(Calendar.HOUR); 
				int minute=c.get(Calendar.MINUTE); 
				int month2 = month+1; 
			    content.setText(year+"��"+month2+"��"+date+"��"+hour+":"+minute);
			}
		});
		
		//ȫѡ
		Allchoice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				content.selectAll();
			}		   
		});
		
		//����ѡ��
		Oppositechoice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//�ù�����δʵ��
			}		   
		});
		
		//ѡ����
		Chinesechoice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//�ù�����δʵ��
			}
		});
		
		
		//"��ʽ"�˵�
		//�Զ�����
		menuItemlineturn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		//����
		menuItemword.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				final JFrame jf=new JFrame("��������"); //�������ô���
				jf.setLocation(150,200);
				jf.setSize(400,100);
				jf.setVisible(true);
				jf.setResizable(false);                 //��ֹ�Ŵ���
				final JComboBox j1=new JComboBox(
				GraphicsEnvironment.getLocalGraphicsEnvironment()
		        .getAvailableFontFamilyNames());
		        j1.setLocation(50,50);
		        Container c= jf.getContentPane();
		        JPanel p=new JPanel();
		        p.add(j1,new FlowLayout());
		        //����
		        String[] faceString={"����","����","б��","��б��"};
		        String[] sizeString={"����","С��","һ��","Сһ","����","С��",
		        	"����","С��","�ĺ�","С��","���","С��","����","С��","�ߺ�","�˺�",
		        	"5","8","9","10","11","12","14","16","18","20","22","24",
		            "26","28","36","48","72"};
		        final JComboBox j2=new JComboBox(faceString);
		        final JComboBox j3=new JComboBox(sizeString);
		        final Button b1=new Button("ȷ��");
		        final Button b2=new Button("ȡ��");
		        p.add(j2);
		        p.add(j3);
		        p.add(b1);
		        p.add(b2);
		        c.add(p);
		        
		        //�¼�����
		        b1.addActionListener(new ActionListener(){
		        	public void actionPerformed(ActionEvent e){     //���ı����ó���ѡ����
		        		content.setFont(new Font(j1.getActionCommand(),
		        		j2.getSelectedIndex(),j3.getSelectedIndex()));
		        		f.setEnabled(true);                         //���ô������
		        		jf.dispose();
		        	}
		        });
		        
		        b2.addActionListener(new ActionListener(){
		        	public void actionPerformed(ActionEvent e){
		        		f.setEnabled(true);
		        		jf.dispose();
		        	}
		        });	        
			}
		});
		
		//������ɫ
		menuItemcolor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				color=JColorChooser.showDialog(f,"",color);
				content.setForeground(color);
			}
		});
				
		//"�鿴"�˵�
		//ʵ��ͳ��
		menuItemcount.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String str=content.getText();
				JOptionPane.showMessageDialog(null,"���ı���������Ϊ��"+str.length(),"����ͳ��",JOptionPane.INFORMATION_MESSAGE);
			}	
		});
		
		//״̬��
		menuItemstate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//�ù���δʵ��
			}	
		});
		
		
		//"����"�˵�
		menuItemHelp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(
						f,"�����������ʵ����Microsoft���±��Ĺ���\n" +
					    "���ڱ����������ޣ��ó�����С���ֹ���δʵ��\n"
						,"��������...",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		//���ڹر�
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				String s=content.getText();
				if(!s.equals("")){
					int result=JOptionPane.showConfirmDialog(
							null, "�Ƿ��ȱ����ٹرգ�","�����ļ�",JOptionPane.YES_NO_CANCEL_OPTION); 
					if(result==JOptionPane.NO_OPTION){   //������
						System.exit(0);
					}
					else if(result==JOptionPane.CANCEL_OPTION){
								
					}
					else if(result==JOptionPane.YES_OPTION){
					    JFileChooser jfc=new JFileChooser();//����ѡ����·�����ļ���
						int ssd=jfc.showSaveDialog(f);
						if(ssd==JFileChooser.APPROVE_OPTION){
							try{
							    //�����ļ�
								BufferedWriter bw=new BufferedWriter(
								new FileWriter(new File(jfc.getSelectedFile().getAbsolutePath()+".txt")));
								filepath=jfc.getSelectedFile().getAbsolutePath()+".txt";//��ȡ�ļ�·��
										
								bw.write(s);//������д���ļ���
								bw.flush();//��ջ���������
								bw.close();//�ر������
								}catch(IOException ex){
									Logger.getLogger(NoteBook.class.getName()).log(Level.SEVERE, null, ex);
								}
							}
							System.exit(0);
						}
					}
					else
						System.exit(0);
				}
			});
		
		//���˵�����ӵ���Ӧ�Ĳ˵���
		//"�ļ�"�˵�
		menuFile.add(menuItemFileNew);
		menuFile.add(menuItemFileOpen);
		menuFile.add(menuItemFileSave);
		menuFile.add(menuItemFileSaveAs);
		menuFile.addSeparator();             //���ӷָ���
		menuFile.add(menuItemFileSet);
		menuFile.add(menuItemFilePrint);
		menuFile.add(menuItemFileQuit);	
		//"�༭"�˵�
		menuEdit.add(menuItemEditUndo);
		menuEdit.add(menuItemEditCut);
		menuEdit.add(menuItemEditCopy);
		menuEdit.add(menuItemEditPaste);
		menuEdit.add(menuItemEditDelete);
		menuEdit.addSeparator();
		menuEdit.add(menuItemEditFind);
		menuEdit.add(menuItemEditFindnext);
		menuEdit.add(menuItemEditReplace);
		menuEdit.add(menuItemEditTurnto);
		menuEdit.addSeparator(); 
		menuEdit.add(menuItemEditChoice);
		menuEdit.add(menuItemEditDate);
		//"��ʽ"�˵�
		menuformat.add(menuItemlineturn);
		menuformat.add(menuItemword);
		menuformat.add(menuItemcolor);
		//"�鿴"�˵�
		menuview.add(menuItemcount);
		menuview.add(menuItemstate);
        //"����"�˵�
		menuHelp.add(menuItemHelp);
						
		//���˵���ӵ��˵�����
		mbar.add(menuFile);
		mbar.add(menuEdit);
		mbar.add(menuformat);
		mbar.add(menuview);
		mbar.add(menuHelp);
		
		//���˵������뵽���
		setMenuBar(mbar);		
	}
	public static void main(String[] args) {
		NoteBook menu = new NoteBook();
		menu.pack();
		menu.setSize(600,500);
		menu.setTitle("�ҵļ��±�");
		menu.setVisible(true);
	}
}