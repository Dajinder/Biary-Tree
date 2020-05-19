import java.util.ArrayList;

public class binarytree{
    public static void main(String[] args){
        solve();
    }

    public static class Node{
        int data;  
        Node left=null;      
        Node right=null;  
 
        Node(int data){
            this.data=data;
        } 
        
        Node(){
        }
    }
 
    static int idx=0;
    public static Node constructTree(int[] arr){
       if(idx >= arr.length || arr[idx]==-1) {
         idx++;  
         return null;
       }
 
       Node node=new Node(arr[idx++]);   
       node.left=constructTree(arr);
       node.right=constructTree(arr);
       
       return node;
    }
 
    public static void display(Node node){
        if(node==null) return;
 
        String str="";
        str += ((node.left!=null)?node.left.data:".");
        str+=  " <- " + node.data + " -> ";
        str += ((node.right!=null)?node.right.data:".");
        System.out.println(str);
 
        display(node.left);
        display(node.right);
 
    }

    public static int size(Node node){
        if(node==null) return 0;
        return (size(node.left) + size(node.right) + 1);
      }
   
      
      public static int height(Node node){
       if(node==null) return -1; // return -1, height w.r.t edge, return 0, height w.r.t node.
       return Math.max(height(node.left), height(node.right)) + 1;
      }
   
      public static int Maximum(Node node){
       if(node==null) return (int)-1e8;   
       return Math.max(Math.max(Maximum(node.left), Maximum(node.right)),node.data);  
      }
   
      
      public static int Minimum(Node node){
       if(node==null) return (int)1e8;  
       return Math.min(Math.min(Minimum(node.left), Minimum(node.right)),node.data);
      }
   
      public static int Minimum_02(Node node){
          int min_=node.data;
          
          if(node.left!=null) min_=Math.min(min_,Minimum_02(node.left));
          if(node.right!=null) min_=Math.min(min_,Minimum_02(node.right));
          
          return min_;
      }
   
      public static boolean find(Node node,int data){
          if(node==null) return false;
          
          if(node.data==data) return true;
          return find(node.left,data) || find(node.right,data);
      }
   
      //Traversal.============================================================
   
       public static void preOrder(Node node){
            if(node==null) return;
   
            System.out.print(node.data+ " ");
            preOrder(node.left);
            preOrder(node.right);
       }
   
       
       public static void inOrder(Node node){
           if(node==null) return;
   
           inOrder(node.left);
           System.out.print(node.data+ " ");
           inOrder(node.right);
      }
   
      public static void postOrder(Node node){
       if(node==null) return;
   
       postOrder(node.left);
       postOrder(node.right);
       System.out.print(node.data+ " ");
   }

   // return type
    public static ArrayList <Node> rootToNodePath(Node node, int data){
        
        
        if(node==null) return new ArrayList<>();
        if(node.data==data){
            ArrayList<Node> ans = new ArrayList<>();    
            ans.add(node);
            return ans;
        } 
        ArrayList <Node> left = rootToNodePath(node.left, data);
        if(left.size() != 0){
            left.add(node);
            return left;
        }
        ArrayList <Node> right = rootToNodePath(node.right, data);
        if(right.size() != 0){
            right.add(node);
            return right;
        }
        return new ArrayList<>();
    }

    public static ArrayList<Node> rootToNodePath_02(Node node, int data){
        if (node == null)
            return null;
        if (node.data == data){
            ArrayList<Node> base = new ArrayList<>();
            base.add(node);
            return base;
        }

        ArrayList<Node> left = rootToNodePath_02(node.left, data);
        if (left != null) {
            left.add(node);
            return left;
        }
        ArrayList<Node> right = rootToNodePath_02(node.right, data);
        if (right != null) {
            right.add(node);
            return right;
        }
        return null;
    }

    public static boolean rootTonodePath(Node root, int data, ArrayList<Node>path){
        if(root==null) return false;
        if(root.data == data){
            path.add(root);
            return true;
        }
        boolean res = rootTonodePath(root.left, data, path) || rootTonodePath(root.right, data, path);
        if(res==true){
            path.add(root);
            
        } 
        return res;


    }

    public static void rootToNodePath_(Node root, int data){
        ArrayList<Node> path=rootToNodePath_02(root, 80);
            for(Node n: path)
            System.out.println(n.data);
        }
    
    public static void LCA(Node root){
        ArrayList<Node> list1 = rootToNodePath(root, 100);
        ArrayList<Node> list2 = rootToNodePath(root, 120);

        int prev = -1;
        for(int i=list1.size()-1,j=list2.size()-1;i>=0 && j>=0;i--,j--){
            if(list1.get(i).data!=list2.get(i).data){
                break;
            }
            prev = list1.get(i).data;
        }

        System.out.println(prev);

    }

    public static void solve(){
        int[] arr={10,20,40,-1,-1,50,80,-1,-1,90,-1,-1,30,60,100,-1,-1,-1,70,110,-1,-1,120,-1,-1};
        Node root = constructTree(arr);
        // display(root);
        // rootToNodePath_(root, 80);
        LCA(root);
    }
    
}