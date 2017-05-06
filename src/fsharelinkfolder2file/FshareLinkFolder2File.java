/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsharelinkfolder2file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author root
 */
public class FshareLinkFolder2File {

    public static void main(String[] args) throws IOException {
        Set<String> set = new HashSet<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(new File("data")))) {
            for (String line; (line = br.readLine()) != null;) {
                System.out.println("going with: "+line);
                Document doc = Jsoup.connect(line).get();
                Thread.sleep(2000);
                Elements links = doc.select("a[href].filename");
                for (Element link : links) {
                    String url = link.absUrl("href");
                    set.add(url);
                    System.out.println(url);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        System.out.println(set.size());
    }
}
