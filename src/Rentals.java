public class Rentals {
    private int renta_id;
    private String nombreCliente;
    private String telefonoCliente;
    private int numeroMesas;
    private int numeroSillas;
    private String direccionCliente;
    private String fechaRenta;
    private String horarioRenta;
    private String costoRenta;

    public Rentals() {
    }

    public Rentals(int renta_id, String nombreCliente, String telefonoCliente, int numeroMesas, int numeroSillas, String direccionCliente, String fechaRenta, String horarioRenta, String costoRenta) {
        this.renta_id = renta_id;
        this.nombreCliente = nombreCliente;
        this.telefonoCliente = telefonoCliente;
        this.numeroMesas = numeroMesas;
        this.numeroSillas = numeroSillas;
        this.direccionCliente = direccionCliente;
        this.fechaRenta = fechaRenta;
        this.horarioRenta = horarioRenta;
        this.costoRenta = costoRenta;
    }

    public int getRenta_id() {
        return renta_id;
    }

    public void setRenta_id(int renta_id) {
        this.renta_id = renta_id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public int getNumeroMesas() {
        return numeroMesas;
    }

    public void setNumeroMesas(int numeroMesas) {
        this.numeroMesas = numeroMesas;
    }

    public int getNumeroSillas() {
        return numeroSillas;
    }

    public void setNumeroSillas(int numeroSillas) {
        this.numeroSillas = numeroSillas;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getFechaRenta() {
        return fechaRenta;
    }

    public void setFechaRenta(String fechaRenta) {
        this.fechaRenta = fechaRenta;
    }

    public String getHorarioRenta() {
        return horarioRenta;
    }

    public void setHorarioRenta(String horarioRenta) {
        this.horarioRenta = horarioRenta;
    }

    public String getCostoRenta() {
        return costoRenta;
    }

    public void setCostoRenta(String costoRenta) {
        this.costoRenta = costoRenta;
    }

    @Override
    public String toString() {
        return "Rentas{" +
                "renta_id=" + renta_id +
                ", nombreCliente=" + nombreCliente +
                ", telefonoCliente=" + telefonoCliente +
                ", numeroMesas=" + numeroMesas +
                ", numeroSillas=" + numeroSillas +
                ", direccionCliente=" + direccionCliente +
                ", fechaRenta=" + fechaRenta +
                ", horarioRenta=" + horarioRenta +
                ", costoRenta=" + costoRenta +
                "}";
    }
}
