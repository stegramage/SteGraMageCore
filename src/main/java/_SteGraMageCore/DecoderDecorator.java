package _SteGraMageCore;

import java.util.List;

public class DecoderDecorator<T> implements Decoder<T>{

    private Decoder<T> _wrappee;

    public DecoderDecorator (Decoder<T> decoder) {
        _wrappee = decoder;
    }

    @Override
    public T decode(Data info) {
        return _wrappee.decode(info);
    }

    public Decoder<T> getDecoder() {
        return _wrappee;
    }
}
