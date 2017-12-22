package ru.bakhuss.ScreenShotNew.MyClassLoader;

public class ModulePrinter implements Module {
    @Override
    public void load() {
        System.out.println("Module " + this.getClass() + " loading ...");
    }

    @Override
    public int run() {
        System.out.println("Module " + this.getClass() + " running ...");
        return Module.EXIT_SUCCESS;
    }

    @Override
    public void unload() {
        System.out.println("Module " + this.getClass() + " inloading ...");
    }
}
