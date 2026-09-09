package OOPAccessModifier.Class_Problem;
public class PatientProfileJavaBeanChainedConstructorsLockerPIN {

    private String patientId;
    private String name;
    private boolean discharged;
    private String lockerPin;

    public PatientProfileJavaBeanChainedConstructorsLockerPIN() {
        this(null, null);
    }

    public PatientProfileJavaBeanChainedConstructorsLockerPIN(String name) {
        this(null, name);
    }

    public PatientProfileJavaBeanChainedConstructorsLockerPIN(
            String patientId, String name) {

        this.patientId = patientId;
        this.name = name;
        this.discharged = false;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String id) {

        if (patientId == null) {
            patientId = id;
        }
    }

    public boolean isDischarged() {
        return discharged;
    }

    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    public void setLockerPin(String pin) {

        if (pin != null &&
                pin.matches("\\d{4,6}")) {

            lockerPin = Integer.toString(pin.hashCode());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {

        PatientProfileJavaBeanChainedConstructorsLockerPIN p1 =
                new PatientProfileJavaBeanChainedConstructorsLockerPIN(
                        "Arjun Iyer"
                );

        System.out.println(p1.getPatientId());

        PatientProfileJavaBeanChainedConstructorsLockerPIN p2 =
                new PatientProfileJavaBeanChainedConstructorsLockerPIN(
                        "MT2026-0142",
                        "Arjun Iyer"
                );

        System.out.println(p2.getPatientId());

        PatientProfileJavaBeanChainedConstructorsLockerPIN p3 =
                new PatientProfileJavaBeanChainedConstructorsLockerPIN();

        p3.setPatientId("MT2026-0142");
        p3.setPatientId("HACKED-0000");

        System.out.println(p3.getPatientId());

        p3.setLockerPin("123456");

        System.out.println(p3.isDischarged());
    }
}
