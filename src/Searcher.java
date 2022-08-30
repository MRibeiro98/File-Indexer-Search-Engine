import java.io.*;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Searcher {

	IndexSearcher iSearcher;
	QueryParser parser;
	Query query;

	// constructor
	public Searcher(String indexDirectoryPath) throws IOException {
		Directory indexdirectory = FSDirectory.open(new File(indexDirectoryPath));
		iSearcher = new IndexSearcher(indexdirectory);
		parser = new QueryParser(Version.LUCENE_36, LuceneConstants.CONTENTS, new StandardAnalyzer(Version.LUCENE_36));
		iSearcher.setDefaultFieldSortScoring(true, true);
	}

	//takes a search term and returns results based on Lucene's API
	public TopDocs search(String searchquery) throws IOException, ParseException {
		query = parser.parse(searchquery);
		return iSearcher.search(query, LuceneConstants.MAXSEARCH);
	}

	//retrieves one document
	public Document getDoc(ScoreDoc scoredoc) throws CorruptIndexException, IOException {
		return iSearcher.doc(scoredoc.doc);
	}

	public void close() throws IOException {
		iSearcher.close();
	}

}
