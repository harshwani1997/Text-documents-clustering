package Clustering.Clustering;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
//import opennlp.tools.langdetect.*;
//import opennlp.tools.lemmatizer.DictionaryLemmatizer;
//import opennlp.tools.postag.POSModel;
//import opennlp.tools.postag.POSTaggerME;
import java.util.stream.Collectors;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreEntityMention;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.simple.*;

public class PreProcessing {
      
	public ArrayList<String> readFromFile(HashSet<String> stopWords, File file)
	{
		
		ArrayList<String> tokens = new ArrayList<>();
		ArrayList<String> ans = new ArrayList<>();
		Scanner sc = null;
		    try {
		    sc = new Scanner(file);
		      } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		  e.printStackTrace();
	     } 
		    
	     while (sc.hasNextLine()) 
	      {
          String next = filter(sc.nextLine(), stopWords);
	      
	      StringBuilder store = new StringBuilder();
          String[] array = next.split(",");
          for(String s:array)
          {
        	  store.append(s);
          }
          String next1 = store.toString();
          String next2 = "";
          if(next1.length()!=0)
          {
          next2 = next1.substring(1,next1.length()-1);
          }
	      
	      
          String processed = tokenization(next2);
          
        	  tokens.add(processed);     
	   
	}
	     return tokens;
	}
	
	// This function removes all the stop words and the punctuation, special characters, except the numbers and alphabets.
	public String filter(String line, HashSet<String> stopWords)
	{
       List<String> list = new ArrayList<>();
       for(String s:line.split(" "))
       {
    	   s = s.replaceAll("[^a-zA-Z0-9]", "");
    	   if(!stopWords.contains(s))
    	   {
    		   if(s.length()!=0)
    		   {
    		   list.add(s);
    		   }
    	   }
       }
       return list.toString();
	}
	
	// This function gives the lemma of all the words.
	public String tokenization(String line)
	{
      List<String> lemma = new ArrayList<>();
      //List<String> ner = new ArrayList<>();
      
	  Document doc = new Document(line);
      for(Sentence sent:doc.sentences())
      {
    	  for(String lem:sent.lemmas())
    	  {
    		  lemma.add(lem);
    	  }
      }
      
      return lemma.toString();
	}
	
public ArrayList<String> process(File file)
{
	HashSet<String> stopWords = new HashSet<>(); 
	//String directory = "C:\\\\Users\\\\Admin\\\\Desktop\\\\NYU Courant(2nd sem)\\\\BDS\\\\HW2\\\\stanford-corenlp-full-2018-10-05\\\\patterns\\\\stopwords.txt";
	String directory = "C:\\Users\\Dibyajit\\Documents\\Courses\\BDS\\HW\\Text-documents-clustering\\Clustering\\resources\\stopwords.txt";
	File sWords = new File(directory);
	Scanner sc = null;
	try
	{
		sc = new Scanner(sWords);
	}
	catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
	  e.printStackTrace();
     } 
	
	while(sc.hasNextLine())
	{
		String line = sc.nextLine();
		stopWords.add(line);
	}
	
	ArrayList<String> tokens = readFromFile(stopWords, file);
	return tokens;
}
}
