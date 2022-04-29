import java.util.concurrent.locks.ReentrantLock;

class Another{
	static int i =0; 
}

public class StaticReentrantLock implements Runnable{
    private long c1 = 0;
    private long c2 = 0;
    static ReentrantLock data_lock = new ReentrantLock();

    public void inc1() {
    	c1++;
        data_lock.lock();
        try
        {
            Another.i++;
        }
        finally
        {
        	data_lock.unlock();
        }
    }

    public void inc2() {
    	c2++;
    	data_lock.lock();
        try
        {
            Another.i++;
        }
        finally
        {
        	data_lock.unlock();
        }
    }
    
    public void run(){
    	if (Thread.currentThread().getName() == "t1") {
    		for(int i=0; i<10000000; i++) {
    			inc1();
    		}
    		System.out.println("c1 has " + c1 + ";");
    	}else {
    		for(int i=0; i<10000000; i++) {
    			inc2();
    		}
    		System.out.println("c2 has " + c2 + ";");
    	}
	
    	
    }
    
    public static void main(String args[]) {
    	
    	StaticReentrantLock instance = new StaticReentrantLock();
    	Thread t1 = new Thread(instance, "t1");
    	Thread t2 = new Thread(instance, "t2");
    	t1.start();
    	t2.start();
    	try {
    		t1.join();
    		t2.join();
    	}catch(Exception e) {
  		
    	}
    	System.out.println("i is " + Another.i + "." );
    	
    }
}