public class BreakContinue {
  public static void windowPosSum(int[] a, int n) {
    for(int i=0;i<a.length;i+=1){
      if(a[i]<=0){
        continue;
      }
      else{
        for(int j=i+1;j<i+n+1;j+=1){
          if(j>=a.length){
            break;
          }
          else{
          a[i]=a[i]+a[j];
        }
      }
    }
  }
}
  public static void main(String[] args) {
    int[] a = {1, 2, -3, 4, 5, 4};
    int n = 3;
    windowPosSum(a, n);

    // Should print 4, 8, -3, 13, 9, 4
    System.out.println(java.util.Arrays.toString(a));
  }
}