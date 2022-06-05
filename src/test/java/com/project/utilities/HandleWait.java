package com.project.utilities;

public class HandleWait {

    public static void wait(int millis){

        try {
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
