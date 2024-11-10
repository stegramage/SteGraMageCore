package _SteGraMageCore;

import java.util.List;

public class EncoderDecorator <T> implements Encoder<T>{

    private Encoder<T> _wrappee;

    public EncoderDecorator (Encoder<T> encoder) {
        _wrappee = encoder;
    }

    @Override
    public Data encode(T info) {
        return _wrappee.encode(info);
    }

    public Encoder<T> getEncoder() {
        return _wrappee;
    }

}
