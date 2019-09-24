package DesignerPattern.ObserverPattern;

public interface Obserable {

    public void addObserver(Observer observer);

    public void deleteObserver(Observer observer);

    public void notifyObservers(String context);
}
