package ru.bakhuss.ScreenShotNew.MyClassLoader;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassMainn {
    private static JarEntry entry;
    private static JarFile jarFile;
    public static void main(String[] args) {


        try {
            jarFile = new JarFile("../lib/lib/sqlite-jdbc-3.21.0.jar");
            Enumeration entries = jarFile.entries();
            while (entries.hasMoreElements()) {

                JarEntry jarEntry = (JarEntry) entries.nextElement();
//                System.out.println(jarEntry.getName());
                if (jarEntry.getName().endsWith("JDBC.class")) {
                    System.out.println(jarEntry.getName());
                    entry = jarEntry;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        ModulePrinter printer = new ModulePrinter();
//        printer.load();

        String path = "../lib/lib/sqlite-jdbc-3.21.0";

        MyClassLoader loader = new MyClassLoader(path, ClassLoader.getSystemClassLoader(), jarFile, entry);

        File dir = new File(path);
        System.out.println(dir.getAbsolutePath());
        String[] modules = dir.list();
//        System.out.println(modules[0].toString());

//        for (String module : modules) {
//            String moduleName = module.split(".class")[0];
//            System.out.println("module: " + moduleName);

            String moduleName = "n";
            try {
                Class clazz = loader.loadClass(moduleName);
                Module execute = (Module) clazz.newInstance();

                execute.load();
                execute.run();
                execute.unload();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
//        }

    }
}
