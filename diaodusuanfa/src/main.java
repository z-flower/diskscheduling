//应用上面的两个面板类和一个算法类
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.Date;
public class main extends JFrame implements ActionListener
{
    Font font=new Font("微软雅黑",Font.PLAIN,20);

    JButton Button1;
    JPanel panel1;
    JLabel label;
    JTextField textField;
    JSplitPane vsplitPane1;

    Log log1;

    static JPanel mainPane;
    static CardLayout card;
    ArithPane arithPane;

    static  int cidao[];
    public main( )
    {
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            init();
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            log1.addLog(e.toString());
        }
    }
    private void setBorder(TitledBorder titledBorder) {
        // TODO Auto-generated method stub
    }
    public void init() throws Exception{
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;

        arithPane=new ArithPane();

        card=new CardLayout();

        mainPane=new JPanel();
        mainPane.setLayout(card);
        mainPane.add("主面", arithPane);

        cidao=new int[1000];

        label=new JLabel("输入磁道序列" +"(以空格隔开)：");
        label.setFont(font);

        textField=new JTextField();
        textField.setColumns(13);
        textField.setFont(font);

        Button1=new JButton("确定");
        Button1.setFont(font);
        Button1.addActionListener(this);

        panel1=new JPanel();
        panel1.add(label);
        panel1.add(textField);
        panel1.add(Button1);

        log1= Log.getLog();
        Date current = new  Date(System.currentTimeMillis());
        log1.addLog("WELCOME!"+"  *-*  "+current);

        mainPane.add("首面", panel1);
        card.show(mainPane,"首面");
        vsplitPane1=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        vsplitPane1.setTopComponent(new JScrollPane(mainPane));
        vsplitPane1.setBottomComponent( log1);
        vsplitPane1.setDividerLocation(320);
        vsplitPane1.setEnabled(true);

        add(vsplitPane1);
        setSize(630,530);
        setLocation((width - this.getWidth()) / 2, (height - this.getHeight()) / 2);
        setBackground(Color.LIGHT_GRAY);
        this.setIconImage(new ImageIcon("images/t017f176d55d7e1880f.png").getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
       this.setBorder(new TitledBorder("*磁盘调度算法的模拟与实现*"));
        setResizable(true);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==Button1){
            String str=textField.getText();
            if(str.trim().equals("")){
                JOptionPane.showMessageDialog(this, "磁道号序列不能为空!", "tip",
                        JOptionPane.WARNING_MESSAGE);
            }
            else{
                try{
                    String[] buffer=str.split(" ");
                    if(buffer.length>0){
                        for(int i=0;i<buffer.length;i++){
                            cidao[i]=Integer.parseInt(buffer[i]);
                        }
                        log1.addLog("你输入的磁道序列为:"+str);
                        card.show(mainPane, "主面");
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "输入的格式有误", "提示",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
                catch(Exception e1){
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(this, "输入的格式有误", "提示",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
    public static  int[] getCidao(){
        return cidao;
    }
    public  static void showCard(){
        card.show(mainPane,"首面");
    }
    public static void main(String[]args){
        new main();
    }
}
