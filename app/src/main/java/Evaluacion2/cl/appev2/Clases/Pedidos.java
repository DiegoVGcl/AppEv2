package Evaluacion2.cl.appev2.Clases;

public class Pedidos {

    private String IdPedido;
    private String nombre;
    private String estado;

    public Pedidos()
    {
        this.IdPedido ="";
        this.nombre="";
        this.estado="";
    }

    public Pedidos( String idPedido, String nombre, String estado )
    {
        this.IdPedido =idPedido;
        this.nombre=nombre;
        this.estado=estado;
    }

    public String getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(String idPedido) {
        this.IdPedido = idPedido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "idAutor='" + IdPedido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

}
