/**
 * WordGram objects represent a k-gram of strings/words.
 * 
 * @author Angus Li (al308/allaoc)
 *
 */

public class WordGram {
	
	private String[] myWords;   
	private String myToString;  // cached string
	private int myHash = 0;         // cached hash value
	private boolean myStringCalled = false;
	private boolean myHashCalled = false;

	/**
	 * Create WordGram
	 * @param source a String array from which to create the WordGram
	 * @param start the start index of source for the WordGram
	 * @param size the length of the WordGram
	 */
	public WordGram(String[] source, int start, int size) {
		myWords = new String[size];
		for (int c = start; c < (start+size); c++) {
			myWords[c-start] = source[c];
		}
	}

	/**
	 * Return string at specific index in this WordGram
	 * @param index in range [0..length() ) for string 
	 * @return string at index
	 */
	public String wordAt(int index) {
		if (index < 0 || index >= myWords.length) {
			throw new IndexOutOfBoundsException("bad index in wordAt "+index);
		}
		return myWords[index];
	}

	/**
	 * @return length of WordGram
	 */
	public int length(){ 
		return myWords.length;
	}

	/**
	 *  
	 *  @param o another WordGram to compare
	 *  @return true if all Strings in WordGrams are equal
	 */
	@Override
	public boolean equals(Object o) {
		if (! (o instanceof WordGram) || o == null){
			return false;
		}
		WordGram comp = (WordGram) o;
	    if (comp.length() != myWords.length) {
			return false;
		}
		for (int c = 0; c < myWords.length; c++) {
			if (!comp.wordAt(c).equals(myWords[c])) {
				return false;
			}
		}
		return true;
	}
	/**
	 *  
	 *  @return hashCode of String conversion of WordGram
	 */
	@Override
	public int hashCode(){
		if (myHashCalled == false){
			myHash = this.toString().hashCode();
			myHashCalled = true;
		}
		return myHash;
	}
	

	/**
	 * @param last is last String of returned WordGram
	 * @return wg, the WordGram consisting of all but the first String from myWords and last
	 */
	public WordGram shiftAdd(String last) {
		int kount = 0;
		String[] myAddedWords = new String[myWords.length+1]; // Make an array with last added
		for (String s : myWords) {
			myAddedWords[kount] = s;
			kount++;
		}
		myAddedWords[kount] = last;
		WordGram wg = new WordGram(myAddedWords,1,myWords.length);
		return wg;
	}
	/**
	 *  @return String with all elements of myWords joined by single space
	 */
	@Override
	public String toString(){
		if (myHashCalled == false) {
			myToString = String.join(" ",myWords);
			myHashCalled = true;
		}
		return myToString;
	}
}
