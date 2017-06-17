package javaClasses;

/**
 * Created by javaClasses on 09.06.2017.
 */

public class Notifier {
   private static Notifier notifierInstance;
    private Notifier(){}

    public static Notifier getNotifierInstance(){
        if(notifierInstance==null)
            notifierInstance = new Notifier();
        return notifierInstance;
    }
}
