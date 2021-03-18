package sample;

import sample.Alarm.*;
import sample.Watches.*;

import java.util.Scanner;
import java.util.Vector;

public class ConsoleApplication {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ITimer watches = BTimer.build(timerType.HMSWatches, "Часы");
        while (true) {
            System.out.println("1 - Установить время    2 - Установить будильник    3 - Текущее время");
            int state = in.nextInt();
            if (state == 1) {
                System.out.println("Введите время: ");
                try {
                    watches.setHours(in.nextInt());
                    watches.setMinutes(in.nextInt());
                    watches.setSeconds(in.nextInt());
                    System.out.println(watches);
                    Vector<IAlarm> alarms = watches.isAlarm();
                    System.out.println(alarms.size() + " будильников прозвенело");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (state == 2) {
                System.out.println("Введите время срабатывания будильника: ");
                IAlarm alarm = BAlarm.build(alarmType.HMSAlarm);
                try {
                    alarm.setHours(in.nextInt());
                    alarm.setMinutes(in.nextInt());
                    alarm.setSeconds(in.nextInt());
                    watches.addAlarm(alarm);
                    System.out.println("Будильник установлен на " + alarm);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (state == 3) {
                System.out.println(watches);
                Vector<IAlarm> alarms = watches.isAlarm();
                System.out.println(alarms.size() + " будильников прозвенело");
            } else {
                System.out.println("Неверный режим");
            }
        }
    }
}
