class Flight {
    private String code;
    private Airport origin;
    private Airport destination;

    public Flight(String code, Airport origin, Airport destination) {
        this.code = code;
        this.origin = origin;
        this.destination = destination;
    }

    public String getCode() {
        return code;
    }

    public Airport getOrigin() {
        return origin;
    }

    public Airport getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return code + " - " + origin.getCode() + " to " + destination.getCode();
    }
}