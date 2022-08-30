import java.io.*;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Indexer {
	
	private IndexWriter writer;
	
	//constructor - creates writer object
	public Indexer(String indexDirectoryPath) throws IOException {
		Directory indexdirectory = FSDirectory.open(new File(indexDirectoryPath));
		writer = new IndexWriter(indexdirectory, new StandardAnalyzer(Version.LUCENE_36), true, IndexWriter.MaxFieldLength.UNLIMITED);
		
	}
	
	//closes writer
	public void close() throws CorruptIndexException, IOException {
		writer.close();
	}
	
	//defines fields and returns document to be filled
	private Document getDoc(File file) throws IOException {
		
		Document doc = new Document();
		Field contentField = new Field(LuceneConstants.CONTENTS, new FileReader(file));
		Field fileNameField = new Field(LuceneConstants.FILENAME, file.getName(), Field.Store.YES,Field.Index.NOT_ANALYZED);
		Field filePathField = new Field (LuceneConstants.FILEPATH, file.getCanonicalPath(), Field.Store.YES, Field.Index.NOT_ANALYZED);
		
		doc.add(contentField);
		doc.add(fileNameField);
		doc.add(filePathField);
		
		return doc;
	}
	
	//indexing a single file
	private void indexFile(File file) throws IOException {
		System.out.println("Indexing " + file.getCanonicalPath());
		Document doc = getDoc(file);
		writer.addDocument(doc);
	}
	
	//creating an index
	public int createIndex(String[] dataDirPath, FileFilter filter) throws IOException {
		
		for (int i = 0; i < dataDirPath.length; i++) {
			
			File[] files = new File(dataDirPath[i]).listFiles();
			for (File file : files) {
				if (!file.isDirectory() && !file.isHidden() && file.exists() && file.canRead()) {
					indexFile(file);
				}
			}	
		}
		
		return writer.numDocs();
		
	}
	
	
}
