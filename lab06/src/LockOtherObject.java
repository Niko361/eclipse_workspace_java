
class Another{
	static int i =0; 
}

public class LockOtherObject implements Runnable{
    private long c1 = 0;
    private long c2 = 0;
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void inc1() 
    {
    	c1++;
        synchronized(Another.class) 
        {
            Another.i++;
        }
    }

    public void inc2() 
    {
    	c2++;
        synchronized(Another.class) 
        {
            Another.i++;
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
    	
    	LockOtherObject instance = new LockOtherObject();
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