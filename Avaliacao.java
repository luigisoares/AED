package aed3;
import java.io.*;

public class Avaliacao implements Registro {
    protected int codigo;
    protected int codigoUsuario;
    protected int codigoEstabelecimento;
    protected String comentario;
    protected String foto;
    protected int nota;
    protected String data;
    
    public Avaliacao(int c, int ce,int cu, String com, String f, int n, String d) {
        codigo = c;
        codigoEstabelecimento = ce;
        codigoUsuario = cu;
        comentario = com;
        foto = f;
        nota = n;
        data = d;        
    }
    
    public Avaliacao() {
        codigo = 0;
        codigoEstabelecimento = 0;
        codigoUsuario = 0;
        comentario = "";
        foto = "";
        nota = 0;
        data = "";
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoEstabelecimento() {
        return codigoEstabelecimento;
    }

    public void setCodigoEstabelecimento(int codigoEstabelecimento) {
        this.codigoEstabelecimento = codigoEstabelecimento;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public String getString() {
        return ""+codigo;
    }
    
    public String toString() {
        return "\nCódigo..:" + codigo +
                "\nCódigo Usuario..:" + codigoUsuario +
                "\nCódigo Estabelecimento..:" + codigoEstabelecimento +
                "\nComentario...:" + comentario +
                "\nFoto...:" + foto +
                "\nNota...:" + nota +
                "\nData...:" + data;
    }
    
    public byte[] getByteArray() throws IOException {
        ByteArrayOutputStream registro = new ByteArrayOutputStream();
        DataOutputStream saida = new DataOutputStream( registro );
        saida.writeInt(codigo);
        saida.writeInt(codigoUsuario);
        saida.writeInt(codigoEstabelecimento);
        saida.writeUTF(comentario);
        saida.writeUTF(foto);
        saida.writeInt(nota);
        saida.writeUTF(data);
        return registro.toByteArray();        
    }
    
    public void setByteArray(byte[] b) throws IOException {
        ByteArrayInputStream registro = new ByteArrayInputStream(b);
        DataInputStream entrada = new DataInputStream(registro);
        codigo = entrada.readInt();
        codigoUsuario = entrada.readInt();
        codigoEstabelecimento = entrada.readInt();
        comentario = entrada.readUTF();
        foto = entrada.readUTF();
        nota = entrada.readInt();
        data = entrada.readUTF();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    public int compareTo( Object b ) {
        return codigo - ((Avaliacao)b).codigo;
    }

    /*
    public int compareTo( Object b ) {
        return titulo.compareTo(((Livro)b).titulo);
    }
    */
    
}