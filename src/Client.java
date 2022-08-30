import java.io.*;

import org.apache.lucene.queryParser.*;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {

	final static String userDir = System.getProperty("user.dir");
	final static String indexPath = "\\IO\\Index";
	static List<String> list;
	static String resultFileName = "results.txt";

	Searcher searcher;

	public static void main(String[] args) {

		Client client;

		Scanner scan = new Scanner(System.in);
		String searchTerm = "";

		ArrayFileWriter i = new ArrayFileWriter(resultFileName);

		try {
			client = new Client();

			//deletes previous searches from a previous session
			i.wipeFile();

			//ask user for term to search
			while (!searchTerm.equals("stop")) {
				System.out.println("TYPE 'stop' TO END.....Enter search term: ");
				searchTerm = scan.nextLine();

				if (!searchTerm.equals("stop")) {
					client.search(searchTerm);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Goodbye. Your saved search results can be found in the following directory: \n"
				+ userDir + "\\IO\\" + resultFileName);
		scan.close();
	}

	// passes data directories to index and creates text to show status.
	private void createIndex() throws IOException {

		String[] dataDir = new String[3];
		dataDir[0] = userDir + "\\IO\\4020A3Data\\data\\WT01";
		dataDir[1] = userDir + "\\IO\\4020A3Data\\data\\WT02";
		dataDir[2] = userDir + "\\IO\\4020A3Data\\data\\WT03";

		Indexer index = new Indexer(userDir + indexPath);

		long starttime = System.currentTimeMillis();
		int numindexed;
		numindexed = index.createIndex(dataDir, new TextFileFilter());
		long endtime = System.currentTimeMillis();

		index.close();

		System.out.println(numindexed + " Files indexed, time taken: " + (endtime - starttime) + " ms");

	}

	//gets Lucene functions from Searcher class and shows top docs based on rank - creates a .txt file with search results
	
	private void search(String searchquery) throws IOException, ParseException {

		searcher = new Searcher(userDir + indexPath);
		FindTopic topicFinder = new FindTopic();

		ArrayFileWriter c = new ArrayFileWriter(resultFileName);
		list = new ArrayList<String>();

		long starttime = System.currentTimeMillis();
		TopDocs hits = searcher.search(topicFinder.topicNumToName(searchquery).trim());
		long endtime = System.currentTimeMillis();

		System.out.println(hits.totalHits + " documents found. Time taken: " + (endtime - starttime) + " ms");
		list.add("Search query was: " + searchquery);
		int i = 0;
		for (ScoreDoc scoreDoc : hits.scoreDocs) {
			Document document = searcher.getDoc(scoreDoc);
			
			System.out.println(topicFinder.findTopic(topicFinder.topicNumToName(searchquery).trim()) + " Q0 "
					+ document.get(LuceneConstants.FILEPATH).substring(document.get(LuceneConstants.FILEPATH).length() - 8,
							document.get(LuceneConstants.FILEPATH).length())
					+ " " + (i + 1)
					+ " " + (hits.scoreDocs[i].score * 100) + " Group 2");

			list.add(topicFinder.findTopic(topicFinder.topicNumToName(searchquery).trim()) + " Q0 "
					+ document.get(LuceneConstants.FILEPATH).substring(document.get(LuceneConstants.FILEPATH).length() - 8,
							document.get(LuceneConstants.FILEPATH).length())
					+ " " + (i + 1)
					+ " " + (hits.scoreDocs[i].score * 100) + " Group 2");
			i++;
		}

		c.writeLines(list);

	}

}
