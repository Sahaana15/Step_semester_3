package OOPAccessModifier.Class_Problem;
public class VitalsMonitoringEncapsulationGuard {

    static class PatientVitals {

        private double[] readings;
        private int count;

        public PatientVitals(double[] initialReadings) {

            readings = new double[500];
            count = 0;

            if (initialReadings != null) {
                for (double reading : initialReadings) {
                    recordReading(reading);
                }
            }
        }

        void recordReading(double reading) {

            if (reading <= 0 || reading > 45) {
                return;
            }

            if (count < readings.length) {
                readings[count] = reading;
                count++;
            }
        }

        double getAverage() {

            if (count == 0) {
                return 0;
            }

            double sum = 0;

            for (int i = 0; i < count; i++) {
                sum += readings[i];
            }

            return sum / count;
        }

        double[] getAllReadings() {

            double[] result = new double[count];

            for (int i = 0; i < count; i++) {
                result[i] = readings[i];
            }

            return result;
        }
    }

    public static void main(String[] args) {

        PatientVitals v = new PatientVitals(
                new double[]{36.5, -2, 37.1}
        );

        double[] values = v.getAllReadings();

        for (double x : values) {
            System.out.print(x + " ");
        }

        System.out.println();

        values[0] = 999;

        System.out.println(v.getAllReadings()[0]);

        System.out.println("Average: " + v.getAverage());
    }
}
