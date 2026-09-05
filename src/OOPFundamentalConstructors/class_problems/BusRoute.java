package OOPFundamentalConstructors.class_problems;
public class BusRoute {

    private String routeCode;
    private String routeName;
    private int priority;

    public BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 5);
    }

    int compareTo(BusRoute other) {

        if (this.priority != other.priority) {
            return this.priority - other.priority;
        }

        int nameCompare =
                this.routeName.compareToIgnoreCase(other.routeName);

        if (nameCompare != 0) {
            return nameCompare;
        }

        return this.routeCode.compareToIgnoreCase(other.routeCode);
    }

    static BusRoute[] rankRoutes(BusRoute[] routes) {

        BusRoute[] result = routes.clone();

        for (int i = 0; i < result.length - 1; i++) {

            for (int j = 0; j < result.length - i - 1; j++) {

                if (result[j].compareTo(result[j + 1]) > 0) {

                    BusRoute temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {

        BusRoute[] routes = {
                new BusRoute("RT205L", "Airport Express", 3),
                new BusRoute("rt201j", "City Central", 4),
                new BusRoute("RT299T", "Night Service")
        };

        BusRoute[] ranked = rankRoutes(routes);

        for (BusRoute route : ranked) {
            System.out.println(route.routeCode);
        }
    }
}