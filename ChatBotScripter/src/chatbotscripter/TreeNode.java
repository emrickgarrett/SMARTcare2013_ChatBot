package chatbotscripter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class TreeNode<T> {
	T data;
	TreeNode<T> parent;
	List<TreeNode<T>> children;
	
	public TreeNode(T data){
		this.data = data;
		this.children = new LinkedList<TreeNode<T>>();
	}
	
	public TreeNode<T> addChild(T child){
		TreeNode<T> childNode = new TreeNode<T>(child);
		childNode.parent = this;
		this.children.add(childNode);
		return childNode;
	}
	
	@Override
	public String toString(){
		return data != null ? data.toString() : "Data is Null";
	}
	
	public TreeNode<T> getChild(int i){
		
		if(i == -1) return this.parent;
		
		return children.get(i);
	}
	
	
}
