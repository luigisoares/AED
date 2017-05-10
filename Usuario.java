package aed3;

import java.io.*;

public class Usuario implements Registro {

    protected int codigo;
    protected String nome;
    protected float latitude;
    protected float longitude;
    protected String foto;
    protected String email;
    protected String senha;

    public Usuario() {
        codigo = 0;
        nome = "";
        latitude = (float) 0.0;
        longitude = (float) 0.0;
        foto = "";
        email = "";
        senha = "";
    }

    public Usuario(int cod, String n, float lat, float lon, String ft, String mail, String pwd) {
        codigo = cod;
        nome = n;
        latitude = lat;
        longitude = lon;
        foto = ft;
        email = mail;
        senha = pwd;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getString() {
        return nome;
    }

    public String toString() { //não tem foto
        return "\nCódigo..:" + codigo
                + "\nNome..:" + nome
                + "\nLatitude...:" + latitude
                + "\nLongitude...:" + longitude
                + "\nFoto...:" + foto
                + "\nEmail...:" + email
                + "\nSenha...:" + senha;
    }

    public byte[] getByteArray() throws IOException { //não tem foto
        ByteArrayOutputStream registro = new ByteArrayOutputStream();
        DataOutputStream saida = new DataOutputStream(registro);
        saida.writeInt(codigo);
        saida.writeUTF(nome);
        saida.writeFloat(latitude);
        saida.writeFloat(longitude);
        saida.writeUTF(foto);
        saida.writeUTF(email);
        saida.writeUTF(senha);
        return registro.toByteArray();
    }

    public void setByteArray(byte[] b) throws IOException {
        ByteArrayInputStream registro = new ByteArrayInputStream(b);
        DataInputStream entrada = new DataInputStream(registro);
        codigo = entrada.readInt();
        nome = entrada.readUTF();
        latitude = entrada.readFloat();
        longitude = entrada.readFloat();
        foto = entrada.readUTF();
        email = entrada.readUTF();
        senha = entrada.readUTF();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int compareTo(Object b) {
        return codigo - ((Usuario) b).codigo;
    }

}
