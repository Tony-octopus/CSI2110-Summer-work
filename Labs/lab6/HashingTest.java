// ==========================================================================
// CSI2110 Lab code: Hash Tables
// ==========================================================================
// (C)opyright:
//
//   Lachlan Plant
//   SITE, University of Ottawa
//   800 King Edward Ave.
//   Ottawa, On., K1N 6N5
//   Canada. 
//   http://www.site.uottawa.ca
// 
// Creator: lplant (Lachlan Plant)
// Email:   lplan053@uottawa.ca
// ==========================================================================
import java.util.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
public class HashingTest {
    
    public ArrayList<String> readFile(String fileName){
        ArrayList<String> ret = new ArrayList<>();
        BufferedReader br = null;
        try {

   	String sCurrentLine;

	br = new BufferedReader(new FileReader(fileName));
        int i = 0;
	while ((sCurrentLine = br.readLine()) != null) {
            i++;
		String[] a = sCurrentLine.split(" ");
                for(String x: a){
                    ret.add(x.replaceAll("[^\\p{L}\\p{Nd}]+", "").toLowerCase());                    
                }
	}

        } catch (IOException e) {
		e.printStackTrace();
	} finally {
            try {
		if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
	}
        System.out.println("File Size " +ret.size()+ " words");
        return ret;
    }
    
    public void HashAndStats(ArrayList<String> words, HashTable<String,Integer> table ){
        // Add your code for word counting logic here.
        for (String s : words){
            if (table.contains(s)){
                table.put(s, table.get(s) + 1);
            }else{
                table.put(s, 1);
            }
        }

        String temp = String.format("Type: %s, Elements: %d, Load Factor: %f, %s, Count for word \"The\": %d", table.type(),table.elements(),table.loadFactor(),table.extraInfo(),table.get("the"));
        System.out.println(temp);
    }



    public static void main(String[] args){
        int size = 1009;
        Path currentRelativePath = Paths.get("");
        // the current working directory
        String cwd = currentRelativePath.toAbsolutePath().toString();
        HashingTest test = new HashingTest();
        ArrayList<String> words = test.readFile(cwd + "\\TheOdysseyBook1.txt");
        System.out.println("Table Size: "+size);
        HashTable<String,Integer> linear = new ArrayHashTable<String,Integer>(size, new StringHash(),null, new LinearProbeStep());
        HashTable<String,Integer> quadratic = new ArrayHashTable<String,Integer>(size, new StringHash(), null,new QuadraticProbeStep());
        HashTable<String,Integer> doubleH = new ArrayHashTable<String,Integer>(size, new StringHash(), new ReverseStringHash(),new DoubleHashStep());
        HashTable<String,Integer> quadDoubleH = new ArrayHashTable<String,Integer>(size, new StringHash(), new ReverseStringHash(),new QuadraticDoubleHashStep());
        HashTable<String,Integer> chain = new ChainedHashTable<String,Integer>(size, new StringHash());
        test.HashAndStats(words,linear);
        test.HashAndStats(words,quadratic);
        test.HashAndStats(words,doubleH);
        test.HashAndStats(words,quadDoubleH);
        test.HashAndStats(words,chain);

        System.out.println();
        size = 4283;
        words = test.readFile(cwd + "\\TheAeneidBook1-3.txt");
        System.out.println("Table Size: "+size);
        linear = new ArrayHashTable<String,Integer>(size, new StringHash(),null, new LinearProbeStep());
        quadratic = new ArrayHashTable<String,Integer>(size, new StringHash(), null,new QuadraticProbeStep());
        doubleH = new ArrayHashTable<String,Integer>(size, new StringHash(), new ReverseStringHash(),new DoubleHashStep());
        quadDoubleH = new ArrayHashTable<String,Integer>(size, new StringHash(), new ReverseStringHash(),new QuadraticDoubleHashStep());
        chain = new ChainedHashTable<String,Integer>(size, new StringHash());
        test.HashAndStats(words,linear);
        test.HashAndStats(words,quadratic);
        test.HashAndStats(words,doubleH);
        test.HashAndStats(words,quadDoubleH);
        test.HashAndStats(words,chain);
    }
}
