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
	TextArea content;                                  //设置文本域
	String filepath="";                                //先让路径为空
	Color color=Color.red;
	Toolkit toolKit=Toolkit.getDefaultToolkit();
	Clipboard clipboard=toolKit.getSystemClipboard();  //获取剪切板里的内容
	
	NoteBook(){
		f=new Frame();
		MenuBar mbar=new MenuBar();                    //创建菜单条
		
		//创建并添加文本框
		content=new TextArea("",50,50,TextArea.SCROLLBARS_VERTICAL_ONLY);
		add(content);
		content.setVisible(true);	
		content.requestFocusInWindow();
		
		//创建菜单对象:文件,编辑,格式,查看,帮助
		Menu menuFile = new Menu("文件（F）");
		Menu menuEdit = new Menu("编辑（E）");
		Menu menuformat = new Menu("格式（O）");
		Menu menuview = new Menu("查看（V）");
		Menu menuHelp = new Menu("帮助（H）");
	    
		//创建"文件"菜单的菜单项对象
		MenuItem menuItemFileNew=new MenuItem("新建（N）",new MenuShortcut(KeyEvent.VK_N,false));
		MenuItem menuItemFileOpen=new MenuItem("打开（O）...",new MenuShortcut(KeyEvent.VK_O,false));
		MenuItem menuItemFileSave=new MenuItem("保存（S）",new MenuShortcut(KeyEvent.VK_S,false));		
		MenuItem menuItemFileSaveAs=new MenuItem("另存为（A）...");
		MenuItem menuItemFileSet=new MenuItem("页面设置（U）...",new MenuShortcut(KeyEvent.VK_U,false));
		MenuItem menuItemFilePrint=new MenuItem("打印（P）...",new MenuShortcut(KeyEvent.VK_P,false));
		MenuItem menuItemFileQuit=new MenuItem("退出（X）");
		menuItemFileSet.setEnabled(false);    //设置该菜单项不可用
		menuItemFilePrint.setEnabled(false);
	    
		//创建"编辑"菜单的菜单项对象
		MenuItem menuItemEditUndo=new MenuItem("撤消（U）",new MenuShortcut(KeyEvent.VK_Z,false));
		MenuItem menuItemEditCut=new MenuItem("剪切（T）",new MenuShortcut(KeyEvent.VK_X,false));
		MenuItem menuItemEditCopy=new MenuItem("复制（C）",new MenuShortcut(KeyEvent.VK_C,false));		
		MenuItem menuItemEditPaste=new MenuItem("粘贴（P）",new MenuShortcut(KeyEvent.VK_V,false));
		MenuItem menuItemEditDelete=new MenuItem("删除（L）",new MenuShortcut(KeyEvent.VK_DELETE,false));
		MenuItem menuItemEditFind=new MenuItem("查找（F）",new MenuShortcut(KeyEvent.VK_F,false));
		MenuItem menuItemEditFindnext=new MenuItem("查找下一个（N）",new MenuShortcut(KeyEvent.VK_F3,false));
		MenuItem menuItemEditReplace=new MenuItem("替换（R）",new MenuShortcut(KeyEvent.VK_H,false));
		MenuItem menuItemEditTurnto=new MenuItem("转到（G）",new MenuShortcut(KeyEvent.VK_G,false));
		MenuItem menuItemEditDate=new MenuItem("时间/日期（D）",new MenuShortcut(KeyEvent.VK_F5,false));
		menuItemEditUndo.setEnabled(false);
		menuItemEditFindnext.setEnabled(false);
		menuItemEditTurnto.setEnabled(false);
		//二级菜单
		Menu menuItemEditChoice=new Menu("选择（C）");
		//二级菜单选项
		MenuItem Allchoice=new MenuItem("全选（A）",new MenuShortcut(KeyEvent.VK_A,false));
		MenuItem Oppositechoice=new MenuItem("反向选择（B）",new MenuShortcut(KeyEvent.VK_B,false));
		MenuItem Chinesechoice=new MenuItem("选择汉字（C）",new MenuShortcut(KeyEvent.VK_C,false));
		Oppositechoice.setEnabled(false);
		Chinesechoice.setEnabled(false);
		menuItemEditChoice.add(Allchoice);
		menuItemEditChoice.add(Oppositechoice);
        menuItemEditChoice.add(Chinesechoice);
		
        //创建"格式"菜单的菜单项对象
        CheckboxMenuItem menuItemlineturn=new CheckboxMenuItem("自动换行（W）");
        MenuItem menuItemword=new MenuItem("字体（F）");
        MenuItem menuItemcolor=new MenuItem("字体颜色（C）");
        
        //创建"查看“菜单的菜单项对象
        MenuItem menuItemcount=new MenuItem("字数统计（C）");
        MenuItem menuItemstate=new MenuItem("状态栏（S）");
        menuItemstate.setEnabled(false);
        
		//创建"帮助"菜单的菜单项对象
		MenuItem menuItemHelp=new MenuItem("关于作者（A）");
		
		
		//事件处理,添加侦听哭器来实现各菜单的菜单项的功能
		//"文件"菜单
		//新建
		menuItemFileNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s1= content.getText();
				if(!s1.equals("")){//文本域里文本不为空
		               int result = JOptionPane.showConfirmDialog(
		                       null, "是否要保存？","保存文件",JOptionPane.YES_NO_CANCEL_OPTION);
		               if(result == JOptionPane.NO_OPTION){         //不保存
		            	   content.setText("");
		               }
		               else if(result == JOptionPane.CANCEL_OPTION){//取消新建
		            	   
		               }
		               else if(result == JOptionPane.YES_OPTION)    //选择保存
		               {
		                   JFileChooser jfc = new JFileChooser();   //用于选择保存路径的文件名
		                   int bcf = jfc.showSaveDialog(f);
		                   if(bcf == JFileChooser.APPROVE_OPTION){
		                	   try{
		                		   //保存文件
		                           BufferedWriter bfw = new BufferedWriter(
		                           new FileWriter(new File(jfc.getSelectedFile().getAbsolutePath()+".txt")));
		                           filepath = jfc.getSelectedFile().getAbsolutePath()+".txt";//获取文件保存的路径
		                           bfw.write(s1);                                            //向文件写出数据
		                           bfw.flush();                                              //清空缓存区数据
		                           bfw.close();                                              //关闭输出流
		                           } catch (IOException ex){
		                        	   Logger.getLogger(NoteBook.class.getName()).log(Level.SEVERE, null, ex);//
		                           }
		                   new NoteBook();//新建文本文件
		                   }
		                   }
		               }
				}
		});
		
		//打开
		menuItemFileOpen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FileDialog fd= new FileDialog(new Frame(),"打开....",FileDialog.LOAD);
				fd.setVisible(true);                        //设置可见性
				filepath = fd.getDirectory() + fd.getFile();//获取文件目录，并设置文件路径
	            File file = new File(filepath);             //以文件路径名命名文件
				BufferedReader br = null;                   //设置输入缓存区为空
				StringBuilder sb = new StringBuilder();
				try{
					br=new BufferedReader(new FileReader(file));
					String str=null;
					while((str=br.readLine())!=null){       //按行读取数据，直到为空
						sb.append(str).append("\n");
					}
					content.setText(sb.toString());         //输出并显示在文本域中    
				}catch(FileNotFoundException e1){
					e1.printStackTrace();
				}catch(IOException e2){
					e2.printStackTrace();
				}
			}
		});
		
		//保存
		menuItemFileSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(filepath.equals("")){                    //当路径为空时，就另存为
					JFileChooser jfc=new JFileChooser();    //用于选择保存路径的文件名
					int ssd=jfc.showSaveDialog(f);          //弹出保存窗口
					if(ssd==JFileChooser.APPROVE_OPTION){
						try{
							//保存文件
							BufferedWriter bw=new BufferedWriter(
							new FileWriter(new File(jfc.getSelectedFile().getAbsolutePath()+".txt")));
							filepath=jfc.getSelectedFile().getAbsolutePath();//设置路径
							bw.write(content.getText());
							bw.flush();
							bw.close();
						}catch(IOException ex){
							Logger.getLogger(NoteBook.class.getName()).log(Level.SEVERE,null,ex);
						}
					}
				}
				else{//路径不为空时，保存在原来的路径下
					try{
						//保存文件
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
		
		//另存为
		menuItemFileSaveAs.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFileChooser jfc=new JFileChooser();              //用于选择保存路径的文件名
				int ssd=jfc.showSaveDialog(f);                    //弹出保存窗口 
				if(ssd==JFileChooser.APPROVE_OPTION){
					try{
						//保存文件
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
		
		//页面设置
		menuItemFileSet.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//该功能尚未实现
			}
		});
		
		//打印
		menuItemFilePrint.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//该功能尚未实现
			}
		});
		
		//退出
		menuItemFileQuit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object[] options={"是的，我要退出","点错了"};
				int option=JOptionPane.showOptionDialog(null,"您确定要退出？",
				"退出提示",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,null,options,options[0]);
			}
		});
		
		
		//"编辑"菜单
		//撤消
		menuItemEditUndo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//该功能尚未实现
			}
		});
		
		//剪切
		menuItemEditCut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s=content.getSelectedText();
				StringSelection ss=new StringSelection(s);//
				clipboard.setContents(ss,null);           //将剪切板的内容设置到对象ss中
				if(s.length()==0){
					return;
				}else{
					content.replaceRange("",content.getSelectionStart(),content.getSelectionEnd());
				}
			}
		});
		
		//复制
		menuItemEditCopy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s=content.getSelectedText();
				StringSelection ss=new StringSelection(s);
				clipboard.setContents(ss,null);
			}
		});
		
		//粘贴
		menuItemEditPaste.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Transferable t=clipboard.getContents(this);            //返回表示剪切板当前内容的对象
				String s=null;
				try{
					s=(String) t.getTransferData(DataFlavor.stringFlavor);//将当前内容保存在对象s中
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
		
		//删除
		menuItemEditDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				content.setText("");
			}
		});
		
		//查找
		menuItemEditFind.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Dialog d = new Dialog(f,"查找字符串...",true);
				d.setBounds(300,200,300,100);
				JLabel find = new JLabel("请输入字符串 :");
				final TextField tf = new TextField(1);
				Button b = new Button("查找");
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
				        //若找不到待查字符串，则将光标置于末尾 
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
		
		//查找下一个
		menuItemEditFindnext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//该功能尚未实现
			}
		});
		
		//替换
		menuItemEditReplace.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Dialog d= new JDialog(f,"字符串替换...",true);
				d.setBounds(560,250,310,180);
				final Label L1 = new Label("请输入要替换的字符串 ：");
				final Label L2 = new Label("请输入替换后的字符串 ：");
				TextField tf1 = new TextField(10);
				TextField tf2 = new TextField(10);
				JButton jb = new JButton("替换");
				d.setLayout(null);
				//设置大小和位置
		        L1.setBounds(10,30,150,20);
		        L2.setBounds(10,70,150,20);
		        tf1.setBounds(160,30,110,20);
		        tf2.setBounds(160,70,110,20);
		        jb.setBounds(100,110,80,20);
		        //将组件添加到对话框中
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
		                  //若找不到待查字符串，则将光标置于末尾
		                  content.setSelectionStart(end);
		                  content.setSelectionEnd(end);
		                 }
				       }
			        });
				  d.setResizable(false);
	              d.setVisible(true);
			}
		});
		
		//转到
		menuItemEditTurnto.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//该功能尚未实现
			}
		});
		
		//时间/日期
		menuItemEditDate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Calendar c=Calendar.getInstance(); 
				int year=c.get(Calendar.YEAR); 
				int month=c.get(Calendar.MONTH); 
				int date=c.get(Calendar.DATE); 
				int hour=c.get(Calendar.HOUR); 
				int minute=c.get(Calendar.MINUTE); 
				int month2 = month+1; 
			    content.setText(year+"年"+month2+"月"+date+"日"+hour+":"+minute);
			}
		});
		
		//全选
		Allchoice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				content.selectAll();
			}		   
		});
		
		//反向选择
		Oppositechoice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//该功能尚未实现
			}		   
		});
		
		//选择汉字
		Chinesechoice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//该功能尚未实现
			}
		});
		
		
		//"格式"菜单
		//自动换行
		menuItemlineturn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		//字体
		menuItemword.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				final JFrame jf=new JFrame("字体设置"); //字体设置窗口
				jf.setLocation(150,200);
				jf.setSize(400,100);
				jf.setVisible(true);
				jf.setResizable(false);                 //禁止放大窗体
				final JComboBox j1=new JComboBox(
				GraphicsEnvironment.getLocalGraphicsEnvironment()
		        .getAvailableFontFamilyNames());
		        j1.setLocation(50,50);
		        Container c= jf.getContentPane();
		        JPanel p=new JPanel();
		        p.add(j1,new FlowLayout());
		        //字形
		        String[] faceString={"正常","粗体","斜体","粗斜体"};
		        String[] sizeString={"初号","小初","一号","小一","二号","小二",
		        	"三号","小三","四号","小四","五号","小五","六号","小六","七号","八号",
		        	"5","8","9","10","11","12","14","16","18","20","22","24",
		            "26","28","36","48","72"};
		        final JComboBox j2=new JComboBox(faceString);
		        final JComboBox j3=new JComboBox(sizeString);
		        final Button b1=new Button("确定");
		        final Button b2=new Button("取消");
		        p.add(j2);
		        p.add(j3);
		        p.add(b1);
		        p.add(b2);
		        c.add(p);
		        
		        //事件侦听
		        b1.addActionListener(new ActionListener(){
		        	public void actionPerformed(ActionEvent e){     //将文本设置成所选字体
		        		content.setFont(new Font(j1.getActionCommand(),
		        		j2.getSelectedIndex(),j3.getSelectedIndex()));
		        		f.setEnabled(true);                         //设置窗体可用
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
		
		//字体颜色
		menuItemcolor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				color=JColorChooser.showDialog(f,"",color);
				content.setForeground(color);
			}
		});
				
		//"查看"菜单
		//实数统计
		menuItemcount.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String str=content.getText();
				JOptionPane.showMessageDialog(null,"该文本的总字数为："+str.length(),"字数统计",JOptionPane.INFORMATION_MESSAGE);
			}	
		});
		
		//状态栏
		menuItemstate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//该功能未实现
			}	
		});
		
		
		//"帮助"菜单
		menuItemHelp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(
						f,"本程序基本上实现了Microsoft记事本的功能\n" +
					    "由于本人能力有限，该程序还有小部分功能未实现\n"
						,"关于作者...",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		//窗口关闭
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				String s=content.getText();
				if(!s.equals("")){
					int result=JOptionPane.showConfirmDialog(
							null, "是否先保存再关闭？","保存文件",JOptionPane.YES_NO_CANCEL_OPTION); 
					if(result==JOptionPane.NO_OPTION){   //不保存
						System.exit(0);
					}
					else if(result==JOptionPane.CANCEL_OPTION){
								
					}
					else if(result==JOptionPane.YES_OPTION){
					    JFileChooser jfc=new JFileChooser();//用于选保存路径的文件名
						int ssd=jfc.showSaveDialog(f);
						if(ssd==JFileChooser.APPROVE_OPTION){
							try{
							    //创建文件
								BufferedWriter bw=new BufferedWriter(
								new FileWriter(new File(jfc.getSelectedFile().getAbsolutePath()+".txt")));
								filepath=jfc.getSelectedFile().getAbsolutePath()+".txt";//获取文件路径
										
								bw.write(s);//将数据写入文件中
								bw.flush();//清空缓冲区数据
								bw.close();//关闭输出流
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
		
		//将菜单项添加到对应的菜单中
		//"文件"菜单
		menuFile.add(menuItemFileNew);
		menuFile.add(menuItemFileOpen);
		menuFile.add(menuItemFileSave);
		menuFile.add(menuItemFileSaveAs);
		menuFile.addSeparator();             //增加分隔线
		menuFile.add(menuItemFileSet);
		menuFile.add(menuItemFilePrint);
		menuFile.add(menuItemFileQuit);	
		//"编辑"菜单
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
		//"格式"菜单
		menuformat.add(menuItemlineturn);
		menuformat.add(menuItemword);
		menuformat.add(menuItemcolor);
		//"查看"菜单
		menuview.add(menuItemcount);
		menuview.add(menuItemstate);
        //"帮助"菜单
		menuHelp.add(menuItemHelp);
						
		//将菜单添加到菜单条中
		mbar.add(menuFile);
		mbar.add(menuEdit);
		mbar.add(menuformat);
		mbar.add(menuview);
		mbar.add(menuHelp);
		
		//将菜单条加入到框架
		setMenuBar(mbar);		
	}
	public static void main(String[] args) {
		NoteBook menu = new NoteBook();
		menu.pack();
		menu.setSize(600,500);
		menu.setTitle("我的记事本");
		menu.setVisible(true);
	}
}