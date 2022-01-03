package com.javaseleniumtemplate.utils;
import com.github.sardine.DavResource;
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import com.javaseleniumtemplate.GlobalParameters;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class WebdavUtils {
    private static final String BASE_URL = "http://192.168.100.19:8090";

    private WebdavUtils() {
        throw new IllegalStateException();
    }

    public static Sardine getSardine() {
        return SardineFactory.begin("TESTE_AUTOMATIZADO", "AUTOTESTE");
    }

    public static List<DavResource> getResourcesFrom(String path) throws IOException {
        String url = BASE_URL + path;
        System.out.println(url);
        Sardine sardine = getSardine();
        try {
            if (sardine.exists(url)) {
                List<DavResource> resources = sardine.list(url);
                resources.remove(0);
                return resources;
            } else {
                return new ArrayList();
            }
        } catch (IOException var4) {
            throw new IOException(var4);
        }
    }

    public static boolean deleteResourceCSV(String path) throws IOException {
        Sardine sardine = getSardine();
        try {
            List<DavResource> resources = getResourcesFrom(path);

            for (DavResource resource : resources) {

                String arquivo = resource.getHref().toString();
                if(arquivo.contains(".csv")) {
                    System.out.println("Excluindo o arquivo " + arquivo);
                    sardine.delete("http://localhost:8090" + arquivo);
                }
            }
            return true;

        } catch (IOException var5) {
            throw new IOException(var5);
        }
    }

    public static boolean deleteResourceExcel(String path) throws IOException {
        Sardine sardine = getSardine();
        try {
            List<DavResource> resources = getResourcesFrom(path);

            for (DavResource resource : resources) {

                String arquivo = resource.getHref().toString();
                if(arquivo.contains(".xml")) {
                    System.out.println("Excluindo o arquivo " + arquivo);
                    sardine.delete("http://127.0.0.1:8090" + arquivo);
                }
            }
            return true;

        } catch (IOException var5) {
            throw new IOException(var5);
        }
    }

    public static boolean deleteResourceDOC(String path) throws IOException {
        Sardine sardine = getSardine();
        try {
            List<DavResource> resources = getResourcesFrom(path);

            for (DavResource resource : resources) {

                String arquivo = resource.getHref().toString();
                if(arquivo.contains(".doc")) {
                    System.out.println("Excluindo o arquivo " + arquivo);
                    sardine.delete("http://127.0.0.1:8090" + arquivo);
                }
            }
            return true;

        } catch (IOException var5) {
            throw new IOException(var5);
        }
    }

}







