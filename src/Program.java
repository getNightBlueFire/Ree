public class Program {

    public static void main(String[] args) {

        CommonResource1 commonResource1 = new CommonResource1();
        for (int i = 1; i < 6; i++){

            Thread t = new Thread(new CountThread1(commonResource1));
            t.setName("Thread "+ i);
            t.start();
        }
    }
}

class CommonResource1 {

    int x=0;
}

class CountThread1 implements Runnable{

    CommonResource1 res;
    CountThread1(CommonResource1 res){
        this.res=res;
    }
    public void run(){
        synchronized (res){
            res.x=1;
            for (int i = 1; i < 5; i++){
                System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
                res.x++;
                try{
                    Thread.sleep(100);
                }
                catch(InterruptedException e){}
            }
        }

    }
}