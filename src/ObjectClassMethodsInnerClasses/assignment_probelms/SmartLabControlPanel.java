package ObjectClassMethodsInnerClasses.assignment_probelms;
import java.util.*;

interface Capability {

    String getName();

    boolean apply(Device device, double value);
}

class PowerCapability implements Capability {

    private boolean on = false;

    @Override
    public String getName() {
        return "Power";
    }

    @Override
    public boolean apply(Device device, double value) {

        if (value != 0 && value != 1) {
            return false;
        }

        on = value == 1;

        System.out.println(
                device.getName()
                        + ": "
                        + (on ? "ON" : "OFF")
        );

        return true;
    }
}

class BrightnessCapability implements Capability {

    private double brightness;

    @Override
    public String getName() {
        return "Brightness";
    }

    @Override
    public boolean apply(Device device, double value) {

        if (value < 0 || value > 100) {

            System.out.println(
                    "Rejected: "
                            + device.getName()
                            + " brightness must be between 0% and 100%."
            );

            return false;
        }

        brightness = value;

        System.out.println(
                device.getName()
                        + ": brightness set to "
                        + (int) brightness
                        + "%"
        );

        return true;
    }
}

class TemperatureCapability implements Capability {

    private double temperature;

    @Override
    public String getName() {
        return "Temperature";
    }

    @Override
    public boolean apply(Device device, double value) {

        if (value < 16 || value > 30) {

            System.out.println(
                    "Rejected: "
                            + device.getName()
                            + " temperature must be between 16°C and 30°C."
            );

            return false;
        }

        temperature = value;

        System.out.println(
                device.getName()
                        + ": temperature set to "
                        + (int) temperature
                        + "°C"
        );

        return true;
    }
}

class Device {

    private String name;

    private Map<String, Capability> capabilities;

    public Device(String name) {

        this.name = name;
        capabilities = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addCapability(Capability capability) {

        capabilities.put(
                capability.getName(),
                capability
        );

        System.out.println(
                name
                        + ": "
                        + capability.getName()
                        + " capability added."
        );
    }

    public boolean hasCapability(String name) {
        return capabilities.containsKey(name);
    }

    public boolean apply(
            String capabilityName,
            double value) {

        Capability capability =
                capabilities.get(capabilityName);

        if (capability == null) {
            return false;
        }

        return capability.apply(this, value);
    }
}

class SceneStep {

    private String capabilityName;
    private double value;

    public SceneStep(
            String capabilityName,
            double value) {

        this.capabilityName = capabilityName;
        this.value = value;
    }

    public int applyTo(List<Device> devices) {

        int count = 0;

        for (Device device : devices) {

            if (device.hasCapability(capabilityName)) {

                if (device.apply(
                        capabilityName,
                        value)) {

                    count++;
                }
            }
        }

        return count;
    }
}

class Scene {

    private String name;
    private List<SceneStep> steps;

    public Scene(String name) {

        this.name = name;
        steps = new ArrayList<>();
    }

    public void addStep(SceneStep step) {
        steps.add(step);
    }

    public void execute(List<Device> devices) {

        System.out.println(
                "Scene '" + name + "' started."
        );

        int actions = 0;

        for (SceneStep step : steps) {
            actions += step.applyTo(devices);
        }

        System.out.println(
                "Scene '" + name
                        + "' completed: "
                        + actions
                        + " actions applied."
        );
    }
}

public class SmartLabControlPanel {

    public static void main(String[] args) {

        Device labAC =
                new Device("Lab AC");

        labAC.addCapability(
                new PowerCapability()
        );

        labAC.addCapability(
                new TemperatureCapability()
        );

        Device lights =
                new Device("Ceiling Lights");

        lights.addCapability(
                new PowerCapability()
        );

        lights.addCapability(
                new BrightnessCapability()
        );

        Device projector =
                new Device("Projector");

        projector.addCapability(
                new PowerCapability()
        );

        List<Device> devices =
                Arrays.asList(
                        labAC,
                        lights,
                        projector
                );

        Scene lectureMode =
                new Scene("Lecture Mode");

        lectureMode.addStep(
                new SceneStep("Power", 1)
        );

        lectureMode.addStep(
                new SceneStep("Brightness", 40)
        );

        lectureMode.addStep(
                new SceneStep("Temperature", 24)
        );

        lectureMode.execute(devices);

        labAC.apply("Temperature", 12);

        projector.addCapability(
                new BrightnessCapability()
        );

        projector.apply("Brightness", 70);
    }
}