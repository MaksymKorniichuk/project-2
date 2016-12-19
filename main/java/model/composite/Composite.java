package model.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maksim on 11.12.2016.
 */

public abstract class Composite implements Component{
    private List<Component> components = new ArrayList<>();

    public boolean addComponent(Component component){
        return components.add(component);
    }

    public boolean addListOfComponent(List<? extends Component> components){
        boolean allComponentsAddedSuccessfully = true;
        for(Component component: components){
            if(addComponent(component) == false){
                allComponentsAddedSuccessfully = false;
            }
        }
        return allComponentsAddedSuccessfully;
    }

    public boolean removeComponent(Component component){
        return components.remove(component);
    }

    public Component getComponent(int i){
        return components.get(i);
    }

    public List<Component> getComponents() {
        return components;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("Composite{");
        for(Component component: components){
            res.append(component.toString());
        }
        res.append('}');
        return res.toString();
        /*return "Composite{" +
                "components=" + components +
                '}';*/
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Composite)) return false;

        Composite composite = (Composite) o;

        return components.equals(composite.components);

    }

    @Override
    public int hashCode() {
        return components.hashCode();
    }


}
