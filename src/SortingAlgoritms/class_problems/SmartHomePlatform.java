package SortingAlgoritms.class_problems;
import java.util.*;

class Device {
    String id, type;
    boolean on;
    boolean dim, schedule, energy;
    int energyValue;

    Device(String id, String type, boolean dim, boolean schedule,
           boolean energy, int energyValue) {
        this.id = id;
        this.type = type;
        this.dim = dim;
        this.schedule = schedule;
        this.energy = energy;
        this.energyValue = energyValue;
    }
}

public class SmartHomePlatform {
    public static void main(String[] args) {
        Map<String, Device> devices = new HashMap<>();

        devices.put("L1", new Device("L1", "light", true, true, false, 0));
        devices.put("F1", new Device("F1", "fan", false, true, false, 0));
        devices.put("P1", new Device("P1", "plug", false, false, true, 12));

        command(devices, "ON", "L1");
        command(devices, "DIM", "L1", "40");
        command(devices, "DIM", "F1", "30");
        command(devices, "SCHEDULE", "F1", "22:00");
        command(devices, "ENERGY", "P1");
        command(devices, "ENERGY", "L1");
    }

    static void command(Map<String, Device> devices, String action,
                        String id, String... args) {
        Device d = devices.get(id);

        if (action.equals("ON")) {
            d.on = true;
            System.out.println(id + " is ON");
        } else if (action.equals("OFF")) {
            d.on = false;
            System.out.println(id + " is OFF");
        } else if (action.equals("DIM")) {
            if (!d.dim) {
                System.out.println(id + " rejected: DIM unsupported");
            } else {
                int level = Integer.parseInt(args[0]);
                if (level < 0 || level > 100) {
                    System.out.println(id + " rejected: invalid dim level");
                } else {
                    System.out.println(id + " dimmed to " + level);
                }
            }
        } else if (action.equals("SCHEDULE")) {
            if (!d.schedule) {
                System.out.println(id + " rejected: SCHEDULE unsupported");
            } else {
                System.out.println(id + " scheduled " + args[0]);
            }
        } else if (action.equals("ENERGY")) {
            if (!d.energy) {
                System.out.println(id + " rejected: ENERGY unsupported");
            } else {
                System.out.println(id + " energy " + d.energyValue + " kWh");
            }
        }
    }
}