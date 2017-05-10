package aed3;

import java.io.*;

public class Estabelecimento implements Registro {

    protected int codigo;
    protected String nome;
    protected String foto;
    protected float latitude;
    protected float longitude;
    protected String descricao;
    protected float notaMedia;
    protected int qntAvaliacoes;
    
    public Estabelecimento() {
        codigo = 0;
        nome = "";
        foto = "";
        latitude = (float) 0.0;
        longitude = (float) 0.0;
        descricao = "";
        notaMedia = (float)0.0;
        qntAvaliacoes = 0;
    }

    public Estabelecimento(int cod,String n, String ft, float lat, float lon, String desc, float nota, int ava) {
        codigo = cod;
        nome = n;
        foto = ft;
        latitude = lat;
        longitude = lon;
        descricao = desc;
        notaMedia = nota;
        qntAvaliacoes = ava;
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
    
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(float notaMedia) {
        this.notaMedia = notaMedia;
    }

    public int getQntAvaliacoes() {
        return qntAvaliacoes;
    }

    public void setQntAvaliacoes(int qntAvaliacoes) {
        this.qntAvaliacoes = qntAvaliacoes;
    }
    
    public String getString() {
        return nome;
    }

    public String toString() {
        return "\nCódigo..:" + codigo
                + "\nNome..:" + nome
                + "\nFoto..:" + foto
                + "\nLatitude...:" + latitude
                + "\nLongitude...:" + longitude
                + "\nDescrição...:" + descricao
                + "\nNota...:" + notaMedia
                + "\nQuantidadeAvaliações...:" + qntAvaliacoes;
    }
    
    public byte[] getByteArray() throws IOException { //não tem foto
        ByteArrayOutputStream registro = new ByteArrayOutputStream();
        DataOutputStream saida = new DataOutputStream(registro);
        saida.writeInt(codigo);
        saida.writeUTF(nome);
        saida.writeUTF(foto);
        saida.writeFloat(latitude);
        saida.writeFloat(longitude);
        saida.writeUTF(descricao);
        saida.writeFloat(notaMedia);
        saida.writeInt(qntAvaliacoes);
        return registro.toByteArray();
    }

    public void setByteArray(byte[] b) throws IOException {
        ByteArrayInputStream registro = new ByteArrayInputStream(b);
        DataInputStream entrada = new DataInputStream(registro);
        codigo = entrada.readInt();
        nome = entrada.readUTF();
        foto = entrada.readUTF();
        latitude = entrada.readFloat();
        longitude = entrada.readFloat();
        descricao = entrada.readUTF();
        notaMedia = entrada.readFloat();
        qntAvaliacoes = entrada.readInt();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int compareTo(Object b) {
        return codigo - ((Usuario) b).codigo;
    }

}
