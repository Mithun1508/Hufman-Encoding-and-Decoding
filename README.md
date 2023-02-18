# Hufman-Encoding-and-Decoding
📜️ Huffman coding algorithm for text compression &amp; calculates frequency characters in given text and builds a Huffman tree encoding.

![Screenshot 2023-02-18 090120](https://user-images.githubusercontent.com/93249038/219829528-435ebf36-fe76-4cc5-890b-2002ff25d2f3.png)

Huffman Encoding and Decoding
📊️ This code implements the Huffman Encoding and Decoding algorithm to compress and decompress a given text. The algorithm uses a binary tree, called a Hufman tree, to represent characters and their frequency of occurrence in the input text. The most frequent characters are assigned shorter codes and less frequent characters are assigned longer codes. The result is a compressed representation of the text where characters with higher frequency occupy less space.

## Features

 1 Frequency counting of characters in the input text.

 2 Building the Hufman tree based on the frequency of occurrence of characters.

 3 Encoding of the text into a compressed representation using the Hufman tree.

 4 Decoding of the encoded text back into the original text using the Hufman tree.

 5 Support for print the original string and decoded string.

## Usage
Clone the repository
git clone https://github.com/Mithun1508/Hufman-Encoding-and-Decoding

## Compile and run the code using Java.

javac HufmanEncodingAndDecoding.java

java HufmanEncodingAndDecoding

Input the text to be compressed and decompressed.

Enter the string to be compressed: [input_text]

Explanation
💾️ The code uses a Java class named Gere to represent a node in the Hufman tree. Each node in the tree has two fields: chr and freq. chr represents the character and freq represents its frequency of occurrence in the input text.

The code also implements a priority queue, which is used to build the Hufman tree. The priority queue is sorted in ascending order of frequency, so the node with the lowest frequency is at the front of the queue.

The function Huffman takes in the input text as a string and performs the following steps:

1- Counts the frequency of each character in the input text and stores the result in a map.

2- Builds the Hufman tree using the priority queue. The function takes two nodes with the lowest frequency from the queue, creates a parent node with their sum as the frequency, and adds the parent node back to the queue. This process is repeated until there is only one node left in the queue, which is the root node of the Hufman tree.

3- Encodes the input text into a compressed representation using the Hufman tree. The function encode traverses the Hufman tree, appends a 0 for a left child and a 1 for a right child, and stores the resulting code for each character in a map.

4- Decodes the encoded text back into the original text using the Hufman tree. The function decode takes the encoded text and traverses the Hufman tree, following the 0s and 1s to reach the corresponding characters. The decoded text is stored in a string builder.

The code also implements error handling to ensure that the input text is not null or empty.

Finally, the original string and the decoded string are printed to the console.
