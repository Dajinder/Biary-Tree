public class bst{
    public static void main(String[] args){
        solve();
    }   
    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;
        

        Node(int data,Node left,Node right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
        Node(){

        }
    }

    public static Node createBst(int[] arr,int si,int ei){
        if(si>ei) return null;
        int mid = (si+ei)>>1;
        Node node = new Node(arr[mid],null,null);
        node.left = createBst(arr, si, mid-1);
        node.right = createBst(arr, mid+1, ei);
        
        return node;
    }

    public static void display(Node node){
        if(node==null) return;
        String str = " ";
        str+=(node.left!=null)?node.left.data:".";
        str +="<-"+node.data+"->";
        str+=(node.right!=null)?node.right.data:".";

        System.out.println(str);

        display(node.left);
        display(node.right);
    }

    public static int height(Node node){
        if(node==null) return -1;
        return Math.max(height(node.left),height(node.right)+1);
    }

    public static int size(Node node){
        if(node==null) return 0;

        return size(node.left)+size(node.right)+1;
    }

    public static boolean find(Node node, int data){
        Node cur = node;
        while(cur!=null){
            if(node.data==data){
                return true;
            } 

            else if(cur.data<data){
                cur = cur.right;
            } 

            else{
                cur = cur.left;
            } 
        }
        return false;
    }

    public static boolean findRec(Node node, int data){
        if(node==null) return false;
        if(node.data==data) return true;
        boolean res =false;
        res = res || findRec(node.left, data);
        res = res || findRec(node.right, data);
        return res;
    }

    // static int max = (int)-1e8;
    // public static int maximum(Node node){
    //     if(node==null) return max;
        
    //     int left = maximum(node.left);
    //     int right = maximum(node.right);
    //     return Math.max(Math.max(left,right),node.data);
    // }
    
    
    public static int maximumEle(Node node){
        Node curr = node;
        while(curr.right!=null){
            curr = curr.right;
        }
        return curr.data;
    }

    public static int minimumEle(Node node){
        Node curr = node;
        while(curr.left!=null){
            curr = curr.left;
        }
        return curr.data;
    }


    public static void solve(){
        int[] arr = {10,20,30,40,50,60,70,80};
        Node root = createBst(arr, 0, arr.length-1);
        // display(root);
        // System.out.println(height(root));
        // System.out.println(size(root));
        // System.out.println(find(root, 80)); 
        // System.out.println(maximumEle(root));
        System.out.println(minimumEle(root));
    }

    
}