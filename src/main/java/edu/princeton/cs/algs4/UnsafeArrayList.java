package edu.princeton.cs.algs4;

import java.util.ArrayList;
import java.util.List;

public class UnsafeArrayList {
    static List<Object> al=new java.util.Vector<Object>();
    static class AddTask implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
            for(int i=0;i<10;i++){
                final Object o = new Object();
                try{
                    al.add(o);
                }catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("ArrayIndexOutOfBoundsException...");
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(new AddTask(),"t1");
        Thread t2=new Thread(new AddTask(),"t2");
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
        
        for(int i=0;i<al.size();i++){
            if(al.get(i) == null){
                System.out.println("i="+i+" is null");
            }
        }
        System.out.println(al.size());
    }
}
