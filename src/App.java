//author: Kishindelle

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        Ipv4 ip = new Ipv4("192.168.1.0", 24);
        ip.imprimir();

        Requirements r1 = new Requirements("RED A", 100);
        Requirements r2 = new Requirements("RED B", 6);
        Requirements r3 = new Requirements("RED C", 2);
        // Requirements r4 = new Requirements("RED D", 3000);
        // Requirements r5 = new Requirements("RED F", 1500);
        // Requirements r6 = new Requirements("RED G", 200);
        // Requirements r7 = new Requirements("RED H", 600);

        List<Requirements> list = new ArrayList<Requirements>();
        list.add(r1);
        list.add(r2);
        list.add(r3);
        // list.add(r4);
        // list.add(r5);
        // list.add(r6);
        // list.add(r7);

        for (Requirements r : list) {

            System.out.println(r.toString());
        }
        System.out.println();

        VlsmManager VLSM = new VlsmManager(ip, list);

        System.out.println(VLSM.Obtener());

    }
}
