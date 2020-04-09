import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class Arith extends JPanel{
    Log log;
    public Arith(){
        log= Log.getLog();
    }
    //冒泡算法从小到大顺序排列
    int []bubble(int cidao[],int n){//磁道号数组，个数为n
        int i,j;
        int temp;
        for(i=0;i<n;i++)
            for(j=i+1;j<n;j++){
                if(cidao[i]>cidao[j]){
                    temp=cidao[i];
                    cidao[i]=cidao[j];
                    cidao[j]=temp;
                }
            }
        return cidao;
    }
    /*先来先服务算法*/
    public void FCFS(int cidao[],int now)  //noe:当前磁道号
    {
        int sum=0;//总寻道长度
        int count=0;
        int len=0;
        int i,j;

        float ave = 0;//平均寻道长度
        sum+=Math.abs(cidao[0]-now);
        count=count+1;
        String buffer="";
        for(i=0;i<cidao.length;i++)  {//输出磁盘扫描序列
            if(cidao[i]>0){
                len++;
                buffer+=cidao[i]+" ";
            }
        }
        log.addLog("磁盘扫描序列为: "+buffer.toString());
        for(i=0,j=1;j<len;i++,j++){
            sum+=Math.abs(cidao[j]-cidao[i]);
            count++;
        }
        ave= sum/len;
        log.addLog("磁头移动的总磁道数："+sum+"平均磁道数:"+ave);
    }
    /*最短寻道时间优先调度算法*/
    public void SSTF(int cidao[],int now){
        int k=1;
        int  l,r,len=0;
        int i,j,sum=0;
        float ave;

        for(i=0;i<cidao.length;i++)  {
            if(cidao[i]>0){
                len++;
            }
        }
        cidao=bubble(cidao,len); //调用冒泡排序算法排序
        String s="";
        for(int z=0;z<len;z++){
            s+=cidao[z]+" ";
        }
        log.addLog("磁道序列从小到大排序为:"+s);
        if(cidao[len-1]<=now) //若当前磁道号大于请求序列中最大者,则直接由外向内依次给予各请求服务
        {       String buffer="";
            for(i=len-1;i>=0;i--){
                buffer+=cidao[i]+" ";
            }
            log.addLog("磁盘扫描序列为: "+buffer.toString());
            sum=now-cidao[0];
        }
        if(cidao[0]>=now){ //若当前磁道号小于请求序列中最小者,则直接由内向外依次给予各请求服务;
            String buffer="";
            for(i=0;i<len;i++){
                buffer+=cidao[i]+" ";
            }
            log.addLog("磁盘扫描序列为: "+buffer.toString());
            sum=cidao[len-1]-now;
        }
        if(now>cidao[0]&&now<cidao[len-1]) {//若当前磁道号大于当前请求序列中最小者并且小于最大者
            StringBuffer buffer=new StringBuffer("");
            while(cidao[k]<now){   //确定当前磁道在已排的序列中的位置
                k++;
            }
            l=k-1;
            r=k;
            while((l>=0)&&(r<len)){ //当前磁道在请求序列范围内
                if(now-cidao[l]<=(cidao[r]-now)) {//选择与当前磁道最近的请求给予服务
                    buffer.append(cidao[l]+" ");
                    sum+=now-cidao[l];
                    now=cidao[l];
                    l=l-1;
                }
                else{
                    buffer.append(cidao[r]+" ");
                    sum+=cidao[r]-now;
                    now=cidao[r];
                    r=r+1;
                }
            }
            if(l==-1) {//磁头移动到序列的最小号，返回外侧扫描仍未扫描的磁道
                for(j=r;j<len;j++){
                    buffer.append(cidao[j]+" ");
                }
                sum+=cidao[len-1]-cidao[0];
            }
            else{ //磁头移动到序列的最大号，返回内侧扫描仍未扫描的磁道
                for(j=l;j>=0;j--){
                    buffer.append(cidao[j]+" ");
                }
                sum+=cidao[len-1]-cidao[0];
            }
            log.addLog("磁盘扫描序列为: "+buffer.toString());
        }

        ave=sum/len;
        log.addLog("磁头移动的总磁道数："+sum+"平均磁道数:"+ave);
    }
    /*扫描调度算法*/
    public void SCAN(int cidao[],int now) { //当前磁道号，给出移动臂的移动方向
        int k=1;
        int l,r,d=0;
        int i,j,sum=0;
        int len=0;
        float ave;
        for(i=0;i<cidao.length;i++)  {
            if(cidao[i]>0){
                len++;
            }
        }
        cidao=bubble(cidao,len);//排序
        String s="";
        for(int z=0;z<len;z++){
            s+=cidao[z]+" ";
        }
        log.addLog("磁道序列从小到大排序为:"+s);

        if(cidao[len-1]<=now) //当前磁道号大于请求序列中最大者，直接由外向内
        {
            StringBuffer buffer=new StringBuffer("");
            for(i=len-1;i>=0;i--){
                buffer.append(cidao[i]+" ");
            }
            log.addLog("磁盘扫描序列为: "+buffer.toString());
            sum=now-cidao[0];
        }

        if(cidao[0]>=now)   //若当前磁道号小于请求序列中最小者，直接由内向外
        {
            StringBuffer buffer=new StringBuffer("");
            for(i=0;i<len;i++){
                buffer.append(cidao[i]+" ");
            }
            log.addLog("磁盘扫描序列为: "+buffer.toString());
            sum=cidao[len-1]-now;
        }
        if(now>cidao[0]&&now<cidao[len-1]) //若当前磁道号大于请求序列中最小者且小于最大者
        {
            StringBuffer buffer=new StringBuffer("");
            while(cidao[k]<now){
                k++;
            }
            l=k-1;
            r=k;
            try{
                String string=JOptionPane.showInputDialog(this, "请输入当前移动臂的移动的方向(1 表示向外,0表示向内):",
                        "提示", JOptionPane.INFORMATION_MESSAGE);
                d=Integer.parseInt(string);
                if(d==0)  //选择移动臂方向向内,先向内扫描
                {
                    for(j=1;j>=0;j--){
                        buffer.append(cidao[j]+" ");
                    }
                    for(j=r;j<len;j++){  //磁头移动到最小号，则改变方向向内扫描为扫描的磁道
                        buffer.append(cidao[j]+" ");
                    }
                    sum=now-2*cidao[0]+cidao[len-1];
                }
                else{ //选择移动臂方向向外，先向外扫描
                    for(j=r;j<len;j++){
                        buffer.append(cidao[j]+" ");
                    }
                    for(j=l;j>=0;j--){
                        buffer.append(cidao[j]+" ");
                    }
                    sum=now-cidao[0]+2*cidao[len-1];
                }
                log.addLog("磁盘扫描序列为: "+buffer.toString());
            }catch(Exception e){
                log.addLog(e.toString());
                e.printStackTrace();
            }
        }
        ave= sum/len;
        log.addLog("磁头移动的总磁道数："+sum+"平均磁道数:"+ave);
    }
}
