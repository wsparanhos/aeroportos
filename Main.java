import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Airport> airports = new ArrayList<>();
    private static List<Flight> flights = new ArrayList<>();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("------ Menu ------");
            System.out.println("1. Cadastrar Aeroporto");
            System.out.println("2. Listar Aeroportos");
            System.out.println("3. Cadastrar Voo");
            System.out.println("4. Remover Voo");
            System.out.println("5. Listar Aeroportos e Voos");
            System.out.println("6. Carregar Exemplos");
            System.out.println("0. Sair");

            int choice = getUserChoice();
            System.out.println();

            switch (choice) {
                case 1:
                    cadastrarAeroporto();
                    break;
                case 2:
                    listarAeroportos();
                    break;
                case 3:
                    cadastrarVoo();
                    break;
                case 4:
                    removerVoo();
                    break;
                case 5:
                    listarAeroportosEVoos();
                    break;
                case 6:
                    carregarExemplos();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

            System.out.println();
        }
    }

    private static int getUserChoice() {
        System.out.print("Escolha uma opção: ");
        try {
            return Integer.parseInt(System.console().readLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void cadastrarAeroporto() {
        System.out.println("---- Cadastro de Aeroporto ----");
        System.out.print("Código do Aeroporto: ");
        String code = System.console().readLine();
        System.out.print("Nome do Aeroporto: ");
        String name = System.console().readLine();
        airports.add(new Airport(code, name));
        System.out.println("Aeroporto cadastrado com sucesso!");
    }

    private static void listarAeroportos() {
        System.out.println("---- Lista de Aeroportos ----");
        if (airports.isEmpty()) {
            System.out.println("Nenhum aeroporto cadastrado.");
        } else {
            for (Airport airport : airports) {
                System.out.println(airport);
            }
        }
    }

    private static void cadastrarVoo() {
        System.out.println("---- Cadastro de Voo ----");
        System.out.print("Código do Voo: ");
        String code = System.console().readLine();
        System.out.print("Código do Aeroporto de Origem: ");
        String originCode = System.console().readLine();
        System.out.print("Código do Aeroporto de Destino: ");
        String destinationCode = System.console().readLine();

        Airport originAirport = buscarAeroportoPorCodigo(originCode);
        Airport destinationAirport = buscarAeroportoPorCodigo(destinationCode);

        if (originAirport == null || destinationAirport == null) {
            System.out.println("Aeroporto de origem ou destino não encontrado.");
        } else {
            flights.add(new Flight(code, originAirport, destinationAirport));
            System.out.println("Voo cadastrado com sucesso!");
        }
    }

    private static void removerVoo() {
        System.out.println("---- Remoção de Voo ----");
        System.out.print("Código do Voo: ");
        String code = System.console().readLine();

        Flight flightToRemove = null;
        for (Flight flight : flights) {
            if (flight.getCode().equals(code)) {
                flightToRemove = flight;
                break;
            }
        }

        if (flightToRemove == null) {
            System.out.println("Voo não encontrado.");
        } else {
            flights.remove(flightToRemove);
            System.out.println("Voo removido com sucesso!");
        }
    }

    private static void listarAeroportosEVoos() {
        System.out.println("---- Lista de Aeroportos e Voos ----");
        if (airports.isEmpty()) {
            System.out.println("Nenhum aeroporto cadastrado.");
        } else {
            for (Airport airport : airports) {
                System.out.println(airport);
                System.out.println("Voos:");

                boolean airportHasFlights = false;
                for (Flight flight : flights) {
                    if (flight.getOrigin().equals(airport) || flight.getDestination().equals(airport)) {
                        System.out.println(flight);
                        airportHasFlights = true;
                    }
                }

                if (!airportHasFlights) {
                    System.out.println("Nenhum voo registrado.");
                }
                System.out.println();
            }
        }
    }

    private static Airport buscarAeroportoPorCodigo(String code) {
        for (Airport airport : airports) {
            if (airport.getCode().equals(code)) {
                return airport;
            }
        }
        return null;
    }

    private static void carregarExemplos() {
        airports.clear();
        flights.clear();

        airports.add(new Airport("GRU", "Aeroporto Internacional de Guarulhos"));
        airports.add(new Airport("SDU", "Aeroporto Santos Dumont"));
        airports.add(new Airport("BSB", "Aeroporto Internacional de Brasília"));

        flights.add(new Flight("FL1", airports.get(0), airports.get(1)));
        flights.add(new Flight("FL2", airports.get(1), airports.get(2)));

        System.out.println("Exemplos carregados com sucesso!");
    }
}