package aed3;
import java.io.*;

public class Promocao implements Registro {
    protected int codigo;
    protected int codigoEstabelecimento;
    protected String data;
    protected String descricao;
    
    public Promocao(int c, int ce, String d, String desc) {
        codigo = c;
        codigoEstabelecimento = ce;
        data = d;
        descricao = desc;
    }
    public Promocao() {
        codigo = 0;
        codigoEstabelecimento = 0;
        data = "";
        descricao = "";
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getString() {
        return descricao;
    }
    
    public String toString() {
        return "\nCódigo..:" + codigo +
                "\nCódigo Estabelecimento..:" + codigoEstabelecimento +
                "\nData...:" + data +
                "\nDescrição...:" + descricao;
    }
    
    public byte[] getByteArray() throws IOException {
        ByteArrayOutputStream registro = new ByteArrayOutputStream();
        DataOutputStream saida = new DataOutputStream( registro );
        saida.writeInt(codigo);
        saida.writeInt(codigoEstabelecimento);
        saida.writeUTF(data);
        saida.writeUTF(descricao);
        return registro.toByteArray();        
    }
    
    public void setByteArray(byte[] b) throws IOException {
        ByteArrayInputStream registro = new ByteArrayInputStream(b);
        DataInputStream entrada = new DataInputStream(registro);
        codigo = entrada.readInt();
        codigoEstabelecimento = entrada.readInt();
        data = entrada.readUTF();
        descricao = entrada.readUTF();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    public int compareTo( Object b ) {
        return codigo - ((Promocao)b).codigo;
    }

    /*
    public int compareTo( Object b ) {
        return titulo.compareTo(((Livro)b).titulo);
    }
    */
    
}