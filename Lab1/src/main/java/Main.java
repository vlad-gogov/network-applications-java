import Watches.Watches;
import Watches.WatchesS;

public class Main {
    public static void main(String[] args) {
        System.out.println("Create 2 wathces");
        Watches w1 = new Watches("CASIO", 500.58);
        WatchesS w2 = new WatchesS("GShock", 1500.10);
        System.out.println(w1.getName() + " price = " + w1.getPrice());
        System.out.println(w2.getName() + " price = " + w2.getPrice());
        
        System.out.println("\nSet time to " + w1.getName() + " = 11:30");
        try {
            w1.setTime(11, 30);
            int[] time =  w1.getTime();
            System.out.println(w1.getName() + " time = " + time[0] + ":"  + time[1] + "\n");
        } catch(Exception e) {
            System.out.println(e.getMessage() + "\n");
        }

        System.out.println("Set time to " + w1.getName() + " = 25:30");
        try {
            w1.setTime(25, 30);
            int[] time =  w1.getTime();
            System.out.println(w1.getName() + " time = " + time[0] + ":"  + time[1] + "\n");
        } catch(Exception e) {
            System.out.println(e.getMessage() + "\n");
        }

        System.out.println("Set time to " + w2.getName() + " = 09:10:45");
        try {
            w2.setTime(9, 10, 45);
            int[] time =  w2.getTime();
            System.out.println(w2.getName() + " time = " + time[0] + ":"  + time[1] + ":" + time[2] + "\n");
        } catch(Exception e) {
            System.out.println(e.getMessage() + "\n");
        }

        System.out.println("Set time to " + w2.getName() + " = 08:70:38");
        try {
            w2.setTime(8, 70, 38);
            int[] time =  w2.getTime();
            System.out.println(w2.getName() + " time = " + time[0] + ":"  + time[1] + ":" + time[2] + "\n");
        } catch(Exception e) {
            System.out.println(e.getMessage() + "\n");
        }

        System.out.println("Add time to " + w1.getName() + " 2:10");
        w1.addTime(2, 10);
        int[] time =  w1.getTime();
        System.out.println(w1.getName() + " time = " + time[0] + ":"  + time[1] + "\n");

        System.out.println("Add time to " + w2.getName() + " 4:10:45");
        w2.addTime(4, 10, 45);
        time =  w2.getTime();
        System.out.println(w1.getName() + " time = " + time[0] + ":"  + time[1] + ":" + time[2] + "\n");

    }
}
