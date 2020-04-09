//选择磁盘调度算法的面板类
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
public class ArithPane extends JPanel implements ActionListener{
    JButton button1,button2,button3,Button4;
    Font font=new Font("微软雅黑",Font.PLAIN,16);
    JLabel label;
    Log log;
    Arith arith;
    int now=0;
    public ArithPane() {
        init();
    }
    public void init(){
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setLayout(null);
        this.setBorder(new TitledBorder("《WELCOME》"));
        log= Log.getLog();
        arith=new Arith();
        label=new JLabel("请选择(SELECT):");
        Font font1=new Font("微软雅黑",Font.BOLD,16);
        label.setFont(font1);
        label.setBounds(20, 20, 350, 30);

        button1=new JButton("先来先服务算法（FCFS）");
        button1.setFont(font);
        button1.setBounds(150, 70, 200, 30);
        button1.addActionListener(this);

        button2=new JButton("最短寻道优先算法(SSTF)");
        button2.setFont(font);
        button2.setBounds(150, 100, 200, 30);
        button2.addActionListener(this);

        button3=new JButton("扫描算法(SCAN)");
        button3.setFont(font);
        button3.setBounds(150, 130, 200, 30);
        button3.addActionListener(this);

        Button4=new JButton("返回(BACK)");
        Button4.setFont(font);
        Button4.setBounds(150, 160, 200, 30);
        Button4.addActionListener(this);

        this.add(label);
        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(Button4);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int[] cidao= main.getCidao();
        // TODO 自动生成的方法存根
        if(e.getSource()==Button4){
            main.showCard();
        }
        if(e.getSource()==button1){
            int []tem = new int[cidao.length];
            for(int i=0;i<tem.length;i++){
                tem[i]=cidao[i];
            }
            int len=JOptionPane.showConfirmDialog(this, "先来先服务算法",
                    "提示(TIPS)", JOptionPane.YES_NO_OPTION);
            if(len==JOptionPane.OK_OPTION){
                try{
                    String str=JOptionPane.showInputDialog(this, "请输入当前磁道号",
                            "提示(TIPS)", JOptionPane.INFORMATION_MESSAGE);
                    now=Integer.parseInt(str);
                    log.addLog("当前磁道号为:"+now);
                    arith.FCFS(tem,now);

                }catch(Exception e1){
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(this, "输入类型错误!", "提示",
                            JOptionPane.WARNING_MESSAGE);
                    log.addLog(e1.toString());
                }
            }
        }
        if(e.getSource()==button2){
            int []tem = new int[cidao.length];
            for(int i=0;i<tem.length;i++){
                tem[i]=cidao[i];
            }
            int len=JOptionPane.showConfirmDialog(this, "最短寻道优先算法",
                    "提示(TIPS)", JOptionPane.YES_NO_OPTION);
            if(len==JOptionPane.OK_OPTION){
                try{
                    String str=JOptionPane.showInputDialog(this, "请输入当前磁道号",
                            "提示(TIPS)", JOptionPane.INFORMATION_MESSAGE);
                    now=Integer.parseInt(str);
                    log.addLog("当前磁道号为:"+now);
                    arith.SSTF(tem,now);

                }catch(Exception e1){
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(this, "输入类型错误!", "提示",
                            JOptionPane.WARNING_MESSAGE);
                    log.addLog(e1.toString());
                }
            }
        }
        if(e.getSource()==button3){
            int []tem = new int[cidao.length];
            for(int i=0;i<tem.length;i++){
                tem[i]=cidao[i];
            }
            int len=JOptionPane.showConfirmDialog(this, "扫描算法",
                    "提示(TIPS)", JOptionPane.YES_NO_OPTION);
            if(len==JOptionPane.OK_OPTION){
                try{
                    String str=JOptionPane.showInputDialog(this, "请输入当前磁道号",
                            "提示(TIPS)", JOptionPane.INFORMATION_MESSAGE);
                    now=Integer.parseInt(str);
                    log.addLog("当前磁道号为:"+now);
                    arith.SCAN(tem, now);
                }catch(Exception e1){
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(this, "输入类型错误!", "提示",
                            JOptionPane.WARNING_MESSAGE);
                    log.addLog(e1.toString());
                }
            }
        }
    }
}
