package ru.bakhuss.ScreenShotNew.MyClassLoader;

import java.io.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class MyClassLoader extends ClassLoader {

    private String pathtobin;
    private JarFile jarFile;
    private JarEntry jarEntry;

    public MyClassLoader(String pathtobin, ClassLoader parent, JarFile jarFile, JarEntry jarEntry) {
        super(parent);
        this.pathtobin = pathtobin;
        this.jarEntry = jarEntry;
        this.jarFile = jarFile;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {

        try {
            /**
             * Получем байт-код из файла и загружаем класс в рантайм
             */
            System.out.println("1");
//            byte b[] = fetchClassFromFS(pathtobin + name + ".class");

            byte b[] = fetchClassFromFS("");

            System.out.println("2");

            return defineClass(name, b, 0, b.length);
        } catch (FileNotFoundException ex) {
            return super.findClass(name);
        } catch (IOException ex) {
            return super.findClass(name);
        }

    }

    /**
     * Взято из www.java-tips.org/java-se-tips/java.io/reading-a-file-into-a-byte-array.html
     */
    private byte[] fetchClassFromFS(String path) throws FileNotFoundException, IOException {
        System.out.println("4");


//        InputStream is = new FileInputStream(new File(path));

        InputStream is = jarFile.getInputStream(jarEntry);
        System.out.println("3");
        // Get the size of the file
//        long length = new File(path).length();

        long length = jarEntry.getSize();

        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        System.out.println(length);

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + path);
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;

    }


}
