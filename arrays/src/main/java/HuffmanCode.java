import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Huffman code generation program.
 */
public class HuffmanCode {
    private static class HuffmanTreeNode implements Comparable<HuffmanTreeNode> {
        public HuffmanTreeNode(String value, double probability) {
            this.value = value;
            this.probability = probability;
        }

        public HuffmanTreeNode(HuffmanTreeNode first, HuffmanTreeNode second) {
            left = (first.probability < second.probability) ? first : second;
            right = (first.probability < second.probability) ? second : first;
            probability = left.probability + right.probability;
            value = left.value + right.value;
        }

        public double probability;
        public String value;
        public HuffmanTreeNode left, right;

        public int compareTo(HuffmanTreeNode o) {
            double diffProbability = probability - o.probability;
            if(diffProbability == 0) {
                return 0;
            } else if(diffProbability > 0) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    /**
     * Given the probabilities of characters in the alphabet;
     * generate huffman code for each of the character and return it.
     * @return
     */
    public static Map<String, String> generateHuffmanCode(List<HuffmanTreeNode> huffmanTreeNodes) {
        PriorityQueue<HuffmanTreeNode> priorityQueue =
            new PriorityQueue<HuffmanTreeNode>(huffmanTreeNodes);
        HuffmanTreeNode rootOfTree = null;
        while(!priorityQueue.isEmpty()) {
            HuffmanTreeNode first = priorityQueue.poll();
            if(priorityQueue.isEmpty()) {
                rootOfTree = first;
                break;
            }
            HuffmanTreeNode second = priorityQueue.poll();
            HuffmanTreeNode parent = new HuffmanTreeNode(first, second);
            priorityQueue.offer(parent);
        }
        Map<String, String> codes = new HashMap<String, String>(huffmanTreeNodes.size());
        StringBuilder tempBuilder = new StringBuilder();
        traverseHuffmanTree(rootOfTree, codes, tempBuilder);
        return codes;
    }

    private static void traverseHuffmanTree(HuffmanTreeNode root,
                                            Map<String, String> codes,
                                            StringBuilder tempBuilder) {
        if(root == null) {
            return;
        }
        if(root.left == null && root.right == null) {
            codes.put(root.value, tempBuilder.toString());
        }
        tempBuilder.append("0");
        traverseHuffmanTree(root.left, codes, tempBuilder);
        tempBuilder.deleteCharAt(tempBuilder.length() - 1);

        tempBuilder.append("1");
        traverseHuffmanTree(root.right, codes, tempBuilder);
        tempBuilder.deleteCharAt(tempBuilder.length() - 1);
    }

    public static void main(String[] args) {
        List<HuffmanTreeNode> huffmanTreeNodes = new ArrayList<HuffmanTreeNode>();
        huffmanTreeNodes.add(new HuffmanTreeNode("a", 8.17));
        huffmanTreeNodes.add(new HuffmanTreeNode("b", 1.49));
        huffmanTreeNodes.add(new HuffmanTreeNode("c", 2.78));
        huffmanTreeNodes.add(new HuffmanTreeNode("d", 4.25));
        huffmanTreeNodes.add(new HuffmanTreeNode("e", 12.70));
        Map<String, String> huffmanCodes = generateHuffmanCode(huffmanTreeNodes);
        for(Map.Entry<String, String> entry : huffmanCodes.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
