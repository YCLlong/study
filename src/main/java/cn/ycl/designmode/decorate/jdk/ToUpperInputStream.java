package cn.ycl.designmode.decorate.jdk;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ToUpperInputStream extends FilterInputStream {
    protected ToUpperInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int b = super.read();
        return b == -1? b:Character.toUpperCase(b);
    }

    @Override
    public int read(byte[] b) throws IOException {
        int len = super.read(b);
        if(len == -1){
            return -1;
        }
        for(int i = 0;i<len;i++){
            b[i] = (byte) Character.toUpperCase(b[i]);
        }
        return len;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int t = super.read(b, off, len);
        if(t == -1){
            return -1;
        }
        for(int i = off;i<len;i++){
            b[i] = (byte) Character.toUpperCase(b[i]);
        }
        return t;
    }
}
