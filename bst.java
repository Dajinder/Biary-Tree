
public class bst{
    public static void main(String[] args){
        solve();
    }

    public class Node{
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data, Node left, Node right){
            this.data = data;
            this.left = left;
            this.right = right;
        }

        Node(){

        }
    }
    
    public static Node createBst(int[] arr, int si, int ei){
        
        if(si>ei) return null;
        
        int mid = (si+ei)/2;

        Node node = new Node(arr[mid],null,null);
        
        node.left = createBst(arr, si, mid-1);
        node.right = createBst(arr, mid+1, ei);

        return node;
    }

    public static void display(Node node){
        if(node==null) return;
        String str = " ";
        if(node.left!=null){
            str += node.left.data;
        }
        else{
            str+=".";
        } 
        str+="->" + node.data + "<-";

        if(node.right!=null){
            str += node.right.data;
        }
        else{
            str += ".";
        }

        System.out.println(str);
        display(node.left);
        display(node.right);
         
    }

    public static void solve(){
        int[] arr = {10,20,30,40,50,60,70,80,90,100};
        Node root = createBst(arr, 0, arr.length-1);
        display(root);

    }
}