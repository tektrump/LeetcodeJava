package tektrup.leetcode.oj;


import tektrup.leetcode.util.annotations.Leetcode;
import tektrup.leetcode.util.annotations.Leetcode.Difficulty;
import tektrup.leetcode.util.annotations.Leetcode.Tags;

@Leetcode(date="2016-08-22", tags={Tags.TRIE, Tags.BFS, Tags.DFS}, diff=Difficulty.MEDIUM,
		url="https://leetcode.com/problems/add-and-search-word-data-structure-design/")
public class AddAndSearchWordDataStructureDesign {
	
	public static void main(String[] args) {
		WordDictionary instance = new WordDictionary();
		
		// true
//		instance.addWord("a");
//		System.out.println(instance.search("."));
		
		// false
		instance.addWord("ran");
		instance.addWord("rune");
		System.out.println(instance.search(".an.")); // false
		System.out.println(instance.search(".an"));  // true
	}
	
	
	// Solution VI: Accepted
	// improved from solution V use DFS instead of BFS to search for a word;
    // DFS is "greedy" while BFS is exhausted search; thus performance improved
    // despite DFS uses recursion vs. BFS uses iteration.
	public static class WordDictionary {
		
		class TrieNode {
	        boolean isWord = false;
	        TrieNode[] children = new TrieNode[26];
	        TrieNode() {}
	    }
	    
	    private TrieNode root = new TrieNode();

	    // Adds a word into the data structure.
	    public void addWord(String word) {
	        TrieNode curr = root;
	        for (char ch : word.toCharArray()) {
	            TrieNode next = curr.children[ch-'a'];
	            if (next == null) {
	                next = new TrieNode();
	                curr.children[ch-'a'] = next;
	            }
	            curr = next;
	        }
	        curr.isWord = true;
	    }

	    // Returns if the word is in the data structure. A word could
	    // contain the dot character '.' to represent any one letter.
	    public boolean search(String word) {
	    	return dfs(word, 0, root);
	    }
	    
	    private boolean dfs(String w, int idx, TrieNode node) {
	        if (idx == w.length())
	            return node.isWord;
	        char ch = w.charAt(idx);
	        if (ch == '.') {
	            for (TrieNode child : node.children) {
	                if (child != null && dfs(w, idx+1, child))
	                    return true;
	            }
	        } else {
	            TrieNode child = node.children[ch-'a'];
	            if (child != null && dfs(w, idx+1, child))
	                return true;
	        }
	        
	        return false;
	    }
	    
	
		// Solution V: Accepted
	    // use Trie and search using BFS (it's a tree, so no cycle).
	    // instead of map, use array; avoided TLE
/*
	    class TrieNode {
	        boolean isLeaf = false;
	        // Map<Character, TrieNode> map = new HashMap<>();
	        TrieNode[] children = new TrieNode[26]; // instead of map, use array with 26 letters
	    }
	    
	    private TrieNode root = new TrieNode();
	    public void addWord(String word) {
	        TrieNode curr = root;
	        for (int i = 0; i < word.length(); i++) {
	            char ch = word.charAt(i);
	            int idx = ch - 'a';
	            TrieNode child;
	            if ((child = curr.children[idx]) == null) {
	                child = new TrieNode();
	                curr.children[idx] = child;
	            }
	            curr = child;
	        }
	        curr.isLeaf = true;
	    }
	    
	    // Returns if the word is in the data structure. A word could
	    // contain the dot character '.' to represent any one letter.
	    public boolean search(String word) {
	        List<TrieNode> nodes = new ArrayList<>();
	        nodes.add(root);
	        for (char ch : word.toCharArray()) {
	            List<TrieNode> nextNodes = new ArrayList<>();
	            for (TrieNode node : nodes) {
	                if (ch == '.') {
	                    for (TrieNode child : node.children) {
	                        if (child != null)
	                            nextNodes.add(child);
	                    }
	                } else {
	                    TrieNode next = node.children[ch-'a'];
	                    if (next != null)
	                        nextNodes.add(next);
	                }
	            }
	            if (nextNodes.isEmpty())
	                return false;
	            nodes = nextNodes;
	        }
	        for (TrieNode node : nodes) {
	            if (node.isLeaf)
	                return true;
	        }
	        return false;
	    }
*/
		
		
		// Solution IV: TLE
	    // generate all patterns and add to a set.
/*
	    private Set<String> patterns = new HashSet<>();
	    public void addWord(String word) {
	        dp(word, new char[word.length()], 0);
	    }
	    
	    private void dp(String w, char[] chars, int idx) {
	        char ch = w.charAt(idx);
	        chars[idx] = '.';
	        if (idx == chars.length-1)
	            patterns.add(new String(chars));
	        else
	            dp(w, chars, idx+1);
	        chars[idx] = ch;
	        if (idx == chars.length-1)
	            patterns.add(new String(chars));
	        else
	            dp(w, chars, idx+1);
	    }
	    
	    // Returns if the word is in the data structure. A word could
	    // contain the dot character '.' to represent any one letter.
	    public boolean search(String word) {
	        return patterns.contains(word);
	    }
*/
		
		
		// Solution III: Logic Error
	    // ran + rune -> our logic will permit "rane", which doesn't exist.
/*
	    class TrieNode {
	        boolean isLeaf = false;
	        Map<Character, TrieNode> map = new HashMap<>();
	    }
	    
	    private TrieNode root = new TrieNode();
	    private Map<Integer, Map<Character, TrieNode>> idxMap = new HashMap<>();
	    public void addWord(String word) {
	        TrieNode curr = root;
	        for (int i = 0; i < word.length(); i++) {
	            char ch = word.charAt(i);
	            TrieNode next = curr.map.get(ch);
	            if (next == null) {
	                next = new TrieNode();
	                curr.map.put(ch, next);
	            }
	            curr = next;
	        }
	        curr.isLeaf = true;
	    }
	    
	    // Returns if the word is in the data structure. A word could
	    // contain the dot character '.' to represent any one letter.
	    public boolean search(String word) {
	        List<TrieNode> nodes = new ArrayList<>();
	        nodes.add(root);
	        for (char ch : word.toCharArray()) {
	            List<TrieNode> nextNodes = new ArrayList<>();
	            for (TrieNode node : nodes) {
	                if (ch == '.') {
	                    nextNodes.addAll(node.map.values());
	                } else {
	                    TrieNode next = node.map.get(ch);
	                    if (next != null)
	                        nextNodes.add(next);
	                }
	            }
	            if (nextNodes.isEmpty())
	                return false;
	            nodes = nextNodes;
	        }
	        for (TrieNode node : nodes) {
	            if (node.isLeaf)
	                return true;
	        }
	        return false;
	    }
*/
		
		
		// Solution II: TLE, Logic Correct
	    // implement using Trie, both insert & search ~ O(n), where n is length of words.
/*
	    class TrieNode {
	        boolean isLeaf = false;
	        Map<Character, TrieNode> children;
	        public TrieNode() {
	            children = new HashMap<>();
	        }
	    }
	
	    TrieNode root = new TrieNode();
	    
	    // Adds a word into the data structure.
	    public void addWord(String word) {
	        TrieNode curr = root;
	        for (int i = 0; i < word.length(); i++) {
	            char ch = word.charAt(i);
	            TrieNode next = curr.children.get(ch);
	            if (next == null) {
	                next = new TrieNode();
	                curr.children.put(ch, next);
	            }
	            curr = next;
	        }
	        curr.isLeaf = true;
	    }
	
	    // Returns if the word is in the data structure. A word could
	    // contain the dot character '.' to represent any one letter.
	    public boolean search(String word) {
	        List<TrieNode> nodes = new LinkedList<>();
	        nodes.add(root);
	        for (int i = 0; i < word.length(); i++) {
	            List<TrieNode> nextNodes = new LinkedList<>();
	            char ch = word.charAt(i);
	            for (TrieNode node : nodes) {
	                if (ch == '.')
	                    nextNodes.addAll(node.children.values());
	                else {
	                    TrieNode child = node.children.get(ch);
	                    if (child != null)
	                        nextNodes.add(child);
	                }
	            }
	            if (nextNodes.isEmpty())
	                return false;
	            nodes = nextNodes;
	        }
	        for (TrieNode node : nodes) {
	            if (node.isLeaf)
	                return true;
	        }
	        return false;
	    }
*/
		
		
		// Solution I: TLE
	    // O(n) * O(len)
/*
	    // Adds a word into the data structure.
	    private List<String> words = new ArrayList<>();
	    public void addWord(String word) {
	        words.add(word);
	    }
	
	    // Returns if the word is in the data structure. A word could
	    // contain the dot character '.' to represent any one letter.
	    public boolean search(String word) {
	        for (String w : words) {
	            if (w.length() == word.length() && match(w, word, 0))
	                return true;
	        }
	        return false;
	    }
	    
	    public boolean match(String w, String target, int idx) {
	        if (idx >= target.length())
	            return false;
	        char c = target.charAt(idx);
	        if (c == '.') {
	            if (idx == target.length() - 1)
	                return true;
	            else
	                return match(w, target, idx+1);
	        } else {
	            if (w.charAt(idx) == c) {
	                if (idx == target.length() - 1)
	                    return true;
	                else
	                    return match(w, target, idx+1);
	            } else
	                return false;
	        }
	    }
*/
	}
}
