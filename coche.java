package java_proyecto;

public class coche {

    private String matricula;
    private String marca;
    private String modelo;
    private float precio;
    private String fecha_añadido;
    private String color;
    private byte[] imagen;

    public coche() {
    }

    public coche(String matricula, String marca, String modelo, float precio, String fecha_añadido, String color, byte[] imagen) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.fecha_añadido = fecha_añadido;
        this.color = color;
        this.imagen = imagen;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getFecha_añadido() {
        return fecha_añadido;
    }

    public void setFecha_añadido(String fecha_añadido) {
        this.fecha_añadido = fecha_añadido;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    
    
}
