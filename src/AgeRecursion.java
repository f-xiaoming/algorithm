public class AgeRecursion {
    public static void main(String []args){
        System.out.println(recursion(5, 10));

    }

        public static int recursion(int n,int age){
            //age=10;
            if(n==1){
                return age;
            }
            //age=recursion(n-1,age)+2;
            //return age;
           return  recursion(n-1,age+2);
        }

}
