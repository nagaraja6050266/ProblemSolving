'''
Enter the height of the triangle: 5
     *
    **
   ***
  ****
 *****

 Enter the height of the triangle: 7
       *
      **
     ***
    ****
   *****
  ******
 *******

'''


n=int(input("Enter the height of the triangle: "))
for i in range(n):
    print(' '*(n-i-1),'*'*(i+1))