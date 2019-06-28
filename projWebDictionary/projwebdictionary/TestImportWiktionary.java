/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projwebdictionary;

import de.tudarmstadt.ukp.jwktl.JWKTL;
import java.io.File;

/**
 *
 * @author henrique
 */
public class TestImportWiktionary {

    public static void main(String[] args) {
        File dumpFile = new File("dewiktionary.xml");
        File outputDirectory = new File("data_wikitionary");
        boolean overwriteExisting = true;

        System.out.println("Importando...\n");
        
        JWKTL.parseWiktionaryDump(dumpFile, outputDirectory, overwriteExisting);

        System.out.println("Importação finaizada...");
    }
}
