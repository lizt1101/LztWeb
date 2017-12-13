import java.util.ArrayList;
import java.util.List;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/11
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public class testThread extends Thread{

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        for(int i = 0;i<100;i++){
            list.add(i);
        }
        for(Integer i:list){
            mythread mythread = new mythread(i);
            mythread.start();
        }
    }
}

class mythread extends Thread{

    private Integer i;

    public mythread(Integer i){
        this.i = i;
    }

    @Override
    public void run(){
        System.out.println(i);
    }
}
