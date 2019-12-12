package java.DesignerPattern.CompositePattern.extend;

import java.util.ArrayList;

public class Leaf extends Component {


    @Deprecated
    public void add(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<Component> getChildren() {
        throw new UnsupportedOperationException();
    }
}
