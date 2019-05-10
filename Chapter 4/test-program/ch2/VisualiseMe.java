package ch2;

import java.lang.Thread;
import java.util.List;
import java.util.ArrayList;

class VisualiseMe{

	private final static int ALLOCATE_THIS_MANY_BUFFERS = 64;
	private final static int ALLOCATE_ME_BUFFERS_OF_MB_SIZE = 1024 * 1024;

	public static void main(String [] args){
		
		printUsage();
		
		int gcpause=getFromArgs(args);
	
		AllocateBuffers ab = new VisualiseMe().new AllocateBuffers();
		DoGc gc = new VisualiseMe().new DoGc(gcpause);
		
		Thread t1 = new Thread( ab );
		t1.start();
		System.out.println("Buffer allocation thread started");
		
		if(gcpause>0){
			Thread t2 = new Thread( gc );
			t2.start();
			System.out.println("Manual GC thread started");
		}
	
	}


	/*	
		This class allocates 32MB of data every minute. All old buffers are released.
	*/
	class AllocateBuffers implements Runnable{
	
		public void run(){			
			List buffers;
			
			while(true){
				buffers = new ArrayList();
				for(int i=0; i<ALLOCATE_THIS_MANY_BUFFERS; i++){					
					buffers.add(new byte[ALLOCATE_ME_BUFFERS_OF_MB_SIZE]);
				}
				System.out.println("Allocated " +ALLOCATE_THIS_MANY_BUFFERS+ " buffers of size " + ALLOCATE_ME_BUFFERS_OF_MB_SIZE);
				try{			
					Thread.sleep(10000);
				}
				catch (Exception e){}
			}
			
		}
	
	}

	/*
		This class simply calls System.GC every ${parameter} milliseconds. <0 means never!
	*/
	class DoGc implements Runnable{
		int pause=-1;
		public DoGc(int gcpause){this.pause = gcpause;}
	
		public void run(){
							
			if(pause<=0){
				System.out.println("No GC Pause available. Custom GC thread terminating");
				return;
			}
			
			while(true){
				System.out.println("Would now request a GC...");
				try{			
					System.gc();
					Thread.sleep(60000);
				}
				catch (Exception e){}
			}
			
			
		}
	
	}
	
	static int getFromArgs(String [] args){
		if(args.length<=0)return -1;
		try{
			return Integer.parseInt(args[0]);
		}
		catch(Exception e){
			System.out.println("Did not understand parameter ["+args[0]+"], no GC Pause");			
		}
		return -1;
	}

	static void printUsage(){
		String msg = "This class allocated memory in a thread, and optionally requests GCs are performed after a time T.\n\nUsage:\n" +
					"For no GC thread: java ch2.VisualiseMe " +
					"To fire the GC thread every T milliseconds: java ch2.VisualiseMe T\n\n";
		System.out.println(msg);
		
	}
}