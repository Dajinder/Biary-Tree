import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;

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


    static Node LCANode = null;
    public static boolean LCAopt(Node root, int p, int q){
        
        if(root==null) return false;

        boolean selfdone = false;
        if(root.data == p || root.data == q){
            selfdone = true;
        }

        boolean leftdone = LCAopt(root.left, p, q);
        if(LCANode!=null) return true;
        
        boolean rightdone = LCAopt(root.right, p, q);
        if(LCANode!=null) return true;


        if((selfdone && leftdone == true) || (selfdone && rightdone == true) || (leftdone && rightdone == true)){
            LCANode = root;
        }

        return selfdone || leftdone || rightdone; 
    }

    public static void kDown(Node root,  int k, Node blockNode){

        if(root==null) return;

        if(k==0){
            System.out.println(root.data + " ");
            return ;
        }

        kDown(root.left,k-1,blockNode);
        kDown(root.right,k-1,blockNode);
    }

    public static void allNodeKAway(Node root, int target, int K) {
        ArrayList<Node> path=new ArrayList<>();
        rootTonodePath(root,target,path);

        Node blockNode=null;
        for(int i=0;i<path.size();i++){
            if(K-i<0) break;
            kDown(path.get(i),K-i,blockNode);
            blockNode=path.get(i);
        }
    }

    public static int diameter(Node node){
        if(node==null) return 0;

        int leftdiameter = diameter(node.left);
        int rightdiameter = diameter(node.right);

        int leftheight = height(node.left);
        int rightheight = height(node.right);

        int mydiameter = leftheight + rightheight + 2;

        return Math.max(Math.max(leftdiameter,rightdiameter),mydiameter);
    }

    public static boolean pathsum(Node node,int sum){
        if(node==null) return false;
        if(node.data==sum && node.left==null && node.right == null) return true;
        return pathsum(node.left,sum-node.data) || pathsum(node.right,sum-node.data);
    }

    public static ArrayList<ArrayList<Integer>> pathSum_II_01(Node node, int sum) {
                if (node == null)
                    return null;
                if (node.left == null && node.right == null && sum - node.data == 0) {
                    ArrayList<ArrayList<Integer>> base = new ArrayList<>();
                    ArrayList<Integer> small = new ArrayList<>();
                    small.add(node.data);
                    base.add(small);
                    return base;
                }
        
                ArrayList<ArrayList<Integer>> myans = new ArrayList<>();
                ArrayList<ArrayList<Integer>> left = pathSum_II_01(node.left, sum - node.data);
                if (left != null) {
                    for (ArrayList<Integer> small : left) {
                        small.add(0, node.data);
                        myans.add(small);
                    }
                }
        
                ArrayList<ArrayList<Integer>> right = pathSum_II_01(node.right, sum - node.data);
                if (right != null) {
                    for (ArrayList<Integer> small : right) {
                        small.add(0, node.data);
                        myans.add(small);
                    }
                }
        
                return myans;
            }

    //LevelOrder_series.=========================================================

   public static void levelOrder_00(Node node){
    LinkedList<Node> pQue=new LinkedList<>(); // addLast and removeFirst.
    pQue.addLast(node);

    while(pQue.size()!=0){
        Node rnode=pQue.removeFirst();
        System.out.print(rnode.data+ " ");
        if(rnode.left!=null) pQue.addLast(rnode.left);
        if(rnode.right!=null) pQue.addLast(rnode.right);
   }
}

public static void levelOrder_01(Node node){
 LinkedList<Node> pQue=new LinkedList<>(); // addLast and removeFirst.
 LinkedList<Node> cQue=new LinkedList<>(); // addLast and removeFirst.

 pQue.addLast(node);
 int count=0;
 System.out.print("Level: " + count + " -> ");
 
 while(pQue.size()!=0){
     Node rnode=pQue.removeFirst();
     System.out.print(rnode.data+ " ");
     if(rnode.left!=null) cQue.addLast(rnode.left);
     if(rnode.right!=null) cQue.addLast(rnode.right);

     if(pQue.size()==0){
       LinkedList<Node> temp=pQue;
       pQue=cQue;
       cQue=temp;
       count++;      
      System.out.print("\nLevel: " + count + " -> ");
     }
 }
}

public static void levelOrder_02(Node node){
 LinkedList<Node> Que=new LinkedList<>(); // addLast and removeFirst.

 Que.addLast(node);
 Que.addLast(null);
 int count=0;
 System.out.print("Level: " + count + " -> ");
 
 while(Que.size()!=1){
     Node rnode=Que.removeFirst();
     System.out.print(rnode.data+ " ");
     if(rnode.left!=null) Que.addLast(rnode.left);
     if(rnode.right!=null) Que.addLast(rnode.right);

     if(Que.getFirst()==null){
         Que.removeFirst();
         Que.addLast(null);
         count++;      
         System.out.print("\nLevel: " + count + " -> ");
     }
 }
}


    public static void levelOrder_03(Node node){
        LinkedList<Node> que = new LinkedList<>();
        que.addFirst(node);

        int count = 0;
        while(que.size()!=0){
            System.out.print("Level: "+ count + " -> ");
            count++;
            int size = que.size();

            while(size > 0){
                Node rnode = que.removeFirst();
                System.out.print(rnode.data + " ");
                if(rnode.left!=null) que.addLast(rnode.left);
                if(rnode.right!=null) que.addLast(rnode.right);
                size--;
            }
            System.out.println();
        }
        // System.out.println(count);
    }

    public static void levelOrder_(Node root){
        // levelOrder_00(root);
        // levelOrder_01(root);
        // levelOrder_02(root);
        // levelOrder_03(root);
    }


    // viewset =================================================================
    
    
    public static void leftview(Node node){
        LinkedList<Node> que = new LinkedList<>();
        que.addFirst(node);

        while(que.size()!=0){
            
            int size = que.size();
            System.out.print(que.getFirst().data);

            while(size > 0){
                Node rnode = que.removeFirst();
                if(rnode.left!=null) que.addLast(rnode.left);
                if(rnode.right!=null) que.addLast(rnode.right);
                size--;
            }
            System.out.println();
        }
        
    }

    public static void rightview(Node node){
        LinkedList<Node> que=new LinkedList<>(); // addLast and removeFirst.
    que.addLast(node);
    while(que.size()!=0){
        int size=que.size();
        Node prev=null;
        while(size--> 0){
            Node rnode=que.removeFirst();
            if(rnode.left!=null) que.addLast(rnode.left);
            if(rnode.right!=null) que.addLast(rnode.right);    
            prev=rnode;
        }
        System.out.print(prev.data + " ");
    }
    System.out.println();
    }

    

    

    static int leftMinValue=0;
   static int rightMaxValue=0;

   public static void width(Node node,int lev){
       if(node==null) return;

       leftMinValue=Math.min(leftMinValue,lev);
       rightMaxValue=Math.max(rightMaxValue,lev);
       
       width(node.left, lev - 1);
       width(node.right, lev + 1);
   } 

        public static class pairVO{
        Node node;  //actual Node
        int vl=0;  // vertical Level
        public pairVO(Node node,int vl){
            this.node=node;
            this.vl=vl;
        }
    }

    public static void verticalOrder(Node node){
        width(node,0);
        int n=rightMaxValue - leftMinValue + 1;
        ArrayList<ArrayList<Integer>> ans=new ArrayList<>(); // vector<vector<int>> (n,vector<int>());
        for(int i=0;i<n;i++)
          ans.add(new ArrayList<>());
       
 
        LinkedList<pairVO> que=new LinkedList<>();
        que.addLast(new pairVO(node,-leftMinValue));
 
        while(que.size()!=0){
            int size=que.size();
            while(size--> 0){
                pairVO rpair=que.removeFirst();
                ans.get(rpair.vl).add(rpair.node.data);
                if(rpair.node.left!=null) que.addLast(new pairVO(rpair.node.left, rpair.vl - 1));
                if(rpair.node.right!=null) que.addLast(new pairVO(rpair.node.right,rpair.vl + 1));    
            }
        }
 
        for(ArrayList<Integer> ar: ans)
           System.out.println(ar);
        System.out.println();
    }
 
    public static void verticalOrderSum(Node node){
     width(node,0);
     int[] ans=new int[rightMaxValue - leftMinValue + 1];
 
     LinkedList<pairVO> que=new LinkedList<>();
     que.addLast(new pairVO(node,-leftMinValue));
 
     while(que.size()!=0){
         int size=que.size();
         while(size--> 0){
             pairVO rpair=que.removeFirst();
             ans[rpair.vl] += rpair.node.data;
             if(rpair.node.left!=null) que.addLast(new pairVO(rpair.node.left, rpair.vl - 1));
             if(rpair.node.right!=null) que.addLast(new pairVO(rpair.node.right,rpair.vl + 1));    
         }
     }
 
     for(int ele : ans)
        System.out.println(ele);
     System.out.println();
 }
 
 public static void bottomView(Node node){
    width(node,0);
    int[] ans=new int[rightMaxValue - leftMinValue + 1];

    LinkedList<pairVO> que=new LinkedList<>();
    que.addLast(new pairVO(node,-leftMinValue));

    while(que.size()!=0){
        int size=que.size();
        while(size--> 0){
            pairVO rpair=que.removeFirst();
            ans[rpair.vl] = rpair.node.data;
            if(rpair.node.left!=null) que.addLast(new pairVO(rpair.node.left, rpair.vl - 1));
            if(rpair.node.right!=null) que.addLast(new pairVO(rpair.node.right,rpair.vl + 1));    
        }
    }

    for(int ele: ans)
       System.out.println(ele);
    System.out.println();
}

public static void topView(Node node){
    width(node,0);
    int[] ans=new int[rightMaxValue - leftMinValue + 1];
    Arrays.fill(ans,(int)-1e8);

    LinkedList<pairVO> que=new LinkedList<>();
    que.addLast(new pairVO(node,-leftMinValue));

    while(que.size()!=0){
        int size=que.size();
        while(size--> 0){
            pairVO rpair=que.removeFirst();
            
            if(ans[rpair.vl] == (int)-1e8)
              ans[rpair.vl] = rpair.node.data;
            
              if(rpair.node.left!=null) que.addLast(new pairVO(rpair.node.left, rpair.vl - 1));
            if(rpair.node.right!=null) que.addLast(new pairVO(rpair.node.right,rpair.vl + 1));    
        }
    }

   for(int ele: ans)
       System.out.println(ele);
    System.out.println();
}

static int leftDMinValue=0;
public static void widthDiagonal(Node node,int lev){
    if(node==null) return;

    leftMinValue=Math.min(leftMinValue,lev);

    width(node.left, lev - 1);
    width(node.right, lev + 0);
} 


public static void diagonalOrder(Node node){
    widthDiagonal(node,0);
    int n= -leftDMinValue + 1;
    ArrayList<ArrayList<Integer>> ans=new ArrayList<>(); // vector<vector<int>> (n,vector<int>());
    for(int i=0;i<n;i++)
      ans.add(new ArrayList<>());
   

    LinkedList<pairVO> que=new LinkedList<>();
    que.addLast(new pairVO(node,-leftMinValue));

    while(que.size()!=0){
        int size=que.size();
        while(size--> 0){
            pairVO rpair=que.removeFirst();
            ans.get(rpair.vl).add(rpair.node.data);
            if(rpair.node.left!=null) que.addLast(new pairVO(rpair.node.left, rpair.vl - 1));
            if(rpair.node.right!=null) que.addLast(new pairVO(rpair.node.right,rpair.vl + 0));    
        }
    }

    for(ArrayList<Integer> ar: ans)
       System.out.println(ar);
    System.out.println();
}

public static void diagonalSum(Node node){
    widthDiagonal(node,0);
    int n= -leftDMinValue + 1;
    int[] ans=new int[n];

    LinkedList<pairVO> que=new LinkedList<>();
    que.addLast(new pairVO(node,-leftMinValue));

    while(que.size()!=0){
        int size=que.size();
        while(size--> 0){
            pairVO rpair=que.removeFirst();
            ans[rpair.vl] += rpair.node.data;
            if(rpair.node.left!=null) que.addLast(new pairVO(rpair.node.left, rpair.vl - 1));
            if(rpair.node.right!=null) que.addLast(new pairVO(rpair.node.right,rpair.vl + 0));    
        }
    }

    for(int ele: ans)
       System.out.println(ele);
    System.out.println();
}


    public static Node LinearTree(Node node){
        if(node == null) return null;
        if(node.left==null && node.right==null) return node;
    
        Node LeftTail = LinearTree(node.left);
        Node RightTail = LinearTree(node.right);
    
        if(LeftTail == null)
        node.left = node.right;
        else{
            LeftTail.left = node.right;
        }
        node.right = null;
        // return RightTail != null ? RightTail:LeftTail;
        if(RightTail!=null){
            return RightTail;
        }
        else{
            return LeftTail;
        }
    }
    
    




    public static void solve(){
        int[] arr={10,20,40,-1,-1,50,80,-1,-1,90,-1,-1,30,60,100,-1,-1,-1,70,110,-1,-1,120,-1,-1};
        Node root = constructTree(arr);
        // display(root);
        // rootToNodePath_(root, 80);
        // LCA(root);

            // LCAopt(root, 80, 50);
            // System.out.println("LCA: " + (LCANode!=null ? LCANode.data : "-1")); 
        
        // kDown(root, 2, null); 
        // allNodeKAway(root, 20, 1); 
        // System.out.println(diameter(root));
        
        // levelOrder_(root);
        // viewset(root);
        // System.out.println(pathsum(root,170));
        // System.out.println(pathSum_II_01(root, 170));
        // LinearTree(root);
        diagonalOrder(root);
    }
    
}