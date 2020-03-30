import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello Maxime");
        List<Client> clients = dataFileToCLientList("Ressources/data01.txt");
        List<List<Arrete>> routes = routesCreation (clients, 10);
        System.out.println("test");
    }

    private static List<Client> dataFileToCLientList(String filename) throws IOException {
        List<Client> clients = new ArrayList<>();
        File file = new File(filename);
        Scanner sc = new Scanner(file);
        sc.useDelimiter("\n");
        while (sc.hasNext()) {
            String[] lines = sc.next().split(";");
            List<Integer> attributs = new ArrayList<>();
            for (String line : lines) {
                attributs.add(Integer.parseInt(line.trim()));
            }
            clients.add(new Client(attributs.get(0), attributs.get(1), attributs.get(2), attributs.get(3)));
        }
        return clients;
    }

    private static List<List<Arrete>> routesCreation(List<Client> clients, int chargeMax) throws IOException {
        List<List<Arrete>> routes = new ArrayList<>();
        Random r = new Random();
        int chargeActuelle = 0;
        while (clients.size() > 1) {
            List<Arrete> route = new ArrayList<>();
            int i = r.nextInt(clients.size() - 2) + 1;
            Client clientActuel = clients.get(i);
            route.add(new Arrete(clients.get(0), clientActuel, getDistance(clients.get(0), clientActuel)));
            clients.remove(i);
            while (chargeActuelle < chargeMax) {
                i = r.nextInt(clients.size() - 2) + 1;
                route.add(new Arrete(clientActuel, clients.get(i), getDistance(clientActuel, clients.get(i))));
                clientActuel = clients.get(i);
                clients.remove(i);
            }
            route.add(new Arrete(clientActuel, clients.get(0), getDistance(clientActuel, clients.get(0))));
            routes.add(route);
        }
        return routes;
    }

    private static double getDistance(Client client1, Client client2) {
        return Math.sqrt(Math.pow((client2.getX() - client1.getX()), 2) + Math.pow((client2.getY() - client1.getY()), 2));
    }
}
