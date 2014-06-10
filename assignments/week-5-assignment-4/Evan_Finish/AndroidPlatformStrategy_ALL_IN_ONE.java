package edu.vuum.mocca;

import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;
import android.os.Handler;
import java.util.logging.LogRecord;

import android.app.Activity;
import android.widget.TextView;
import android.os.Message;
import android.util.Log;

/**
 * @class AndroidPlatformStrategy
 * 
 * @brief Provides methods that define a platform-independent API for
 *        output data to Android UI thread and synchronizing on thread
 *        completion in the ping/pong game.  It plays the role of the
 *        "Concrete Strategy" in the Strategy pattern.
 */
public class AndroidPlatformStrategy extends PlatformStrategy
{	
    /** TextViewVariable. */
    private TextView mTextViewOutput;
    
    /** Activity variable finds gui widgets by view. */
    private WeakReference<Activity> mActivity;

    public AndroidPlatformStrategy(Object output,
                                   final Object activityParam)
    {
        /**
         * A textview output which displays calculations and
         * expression trees.
         */
        mTextViewOutput = (TextView) output;
        

        /** The current activity window (succinct or verbose). */
        mActivity = new WeakReference<Activity>((Activity) activityParam);
    }

    /**
     * Latch to decrement each time a thread exits to control when the
     * play() method returns.
     */
    private static CountDownLatch mLatch = null;

    /** Do any initialization needed to start a new game. */
    public void begin()
    {
        /** Reset the CountDownLatch. */
        // TODO - You fill in here.
    	mLatch= new CountDownLatch(2);
    }

    /** Print the outputString to the display. */
    public void print(final String outputString)
    {
        /** 
         * Create a Runnable that's posted to the UI looper thread
         * and appends the outputString to a TextView. 
         */
        // TODO - You fill in here.
    	mActivity.get().runOnUiThread(new Runnable() {
             public void run() {              	 
               	mTextViewOutput.append(outputString + "\n");
               }
           }); 
    	  /*	 
    	handler.post(new Runnable() {
             public void run() {              	 
                	mTextViewOutput.append(outputString + "\n");
                }
            });
     */ 
           
    	 /*
    	final Handler myHandler = new Handler(Looper.getMainLooper())  {  
            public void handleMessage(Message msg) {   
            	 switch (msg.what) {   
                 case 1:   
                	  mTextViewOutput.append(outputString + "\n");  
                      break;   
            }   
            super.handleMessage(msg);   
            }   
       };  
      
    	  new Thread(new Runnable() {
    	        public void run() {
    	        	Message message = new Message();   
                    message.what = 1;
                    
                    myHandler.sendMessage(message);   
                    try {   
                         Thread.sleep(1);    
                    } catch (InterruptedException e) {   
                         Thread.currentThread().interrupt();   
                    }   
    	           
    	        }
    	    }).start();
    	  */
    	 
        //  Looper.loop();
    	  
    	 
      	/*
      	mTextViewOutput.post(new Runnable() {
              public void run() {
              	 
              	mTextViewOutput.append(outputString + "\n");
              }
          });*/
    	  
    	
    	//  mTextViewOutput.setText(outputString);
    }

    /** Indicate that a game thread has finished running. */
    public void done()
    {	
        // TODO - You fill in here.
    	mLatch.countDown();
    }

    /** Barrier that waits for all the game threads to finish. */
    public void awaitDone()
    {
        // TODO - You fill in here.
    	 try {
			mLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /** Returns the platform name in a String. */
    public String platformName() 
    {
        return System.getProperty("java.specification.vendor");
    }

    /** 
     * Error log formats the message and displays it for the
     * debugging purposes.
     */
    public void errorLog(String javaFile, String errorMessage) 
    {
       Log.e(javaFile, errorMessage);
    }
    public void handleMessage(Message msg) {
    //	mActivity.get()
    }
    
}

