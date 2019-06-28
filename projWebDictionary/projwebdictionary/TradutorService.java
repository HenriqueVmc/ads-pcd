package projwebdictionary;

import de.tudarmstadt.ukp.jwktl.JWKTL;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEdition;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEntry;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryPage;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryTranslation;
import de.tudarmstadt.ukp.jwktl.api.PartOfSpeech;
import de.tudarmstadt.ukp.jwktl.api.util.Language;
import java.io.File;

public class TradutorService {

    private String texto;
    private IBuffer cacheTraducoes;

    public TradutorService(String texto, IBuffer cacheTraducoes) {

        this.texto = texto;
        this.cacheTraducoes = cacheTraducoes;
    }

    public Traducao traduzir() {       

        Traducao traducao = new Traducao();
        traducao.texto = texto;
        traducao.traducao = "Não encontrado!";

        // Consultar Base por Texto
        File wiktionaryDirectory = new File("data_wikitionary");
        IWiktionaryEdition wkt = JWKTL.openEdition(wiktionaryDirectory);
        IWiktionaryPage page = wkt.getPageForWord(texto);

        if (page != null) {
            for (IWiktionaryEntry entry : page.getEntries()) {
                if (entry.getPartOfSpeech() == PartOfSpeech.NOUN) {
                    for (IWiktionaryTranslation translation : entry.getTranslations(Language.GERMAN)) {

                        System.out.println(translation.getTranslation());

                        traducao.traducao = translation.getTranslation();
                        cacheTraducoes.set(traducao);

                        return traducao;
                    }
                }
            }
        }
        wkt.close();

        return traducao;
    }
}
