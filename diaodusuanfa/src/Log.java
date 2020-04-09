//打印一些信息
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Log extends JTabbedPane implements ActionListener{
    private JScrollPane scroll1;
    private JTextArea textArea1;

    static final Log log=new Log();
    public Log() {
        // TODO 自动生成的构造函数存根
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        textArea1=new JTextArea();
        textArea1.setLineWrap(true);
        textArea1.setFont(new Font("TimesRoman",Font.PLAIN,18));
        textArea1.setEditable(false);

        scroll1=new JScrollPane(textArea1);
        add(scroll1,BorderLayout.CENTER);

        this.setTitleAt(0, "提示信息(MESSAGE)");
        this.setFont(new Font("微软雅黑",Font.BOLD,16));
        this.setEnabled(false);
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO 自动生成的方法存根
    }
    public void addLog(String log){
        textArea1.append(log+"    "+"\n");
    }
    public static Log getLog(){
        return log;
    }
}
