package _SteGraMageCore;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private List<Integer> _info;

    public Data() {
        _info = new ArrayList<Integer>();
    }

    public List<Integer> getInfo() {
        return new ArrayList<Integer>(_info);
    }

    public void setInfo(List<Integer> info) {
        _info = info;
    }

    public int size() {
        return _info.size();
    }

    public int get(int index) {
        return _info.get(index);
    }
}
