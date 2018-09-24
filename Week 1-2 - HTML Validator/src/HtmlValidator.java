import java.io.IOException;
import java.util.Queue;
import java.util.Stack;

/*
 * SD2x Homework #2
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

public class HtmlValidator {
	
	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
		Stack<HtmlTag> stack = new Stack<>();
		
		for (HtmlTag tag : tags) {
			if (tag.isOpenTag()) {
				stack.push(tag);
			} else if (!tag.isSelfClosing()) {
				// if closing element and stack size is 0 file is not valid
				if (stack.size() == 0)
					return null;
				if (stack.peek().getElement().equals(tag.getElement())) {
					stack.pop();
				} else {
					return stack;
				}
			}
		}	
			
	return stack;
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(isValidHtml(HtmlReader.getTagsFromHtmlFile("test7.html")));
		} catch (IOException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
	}
}

