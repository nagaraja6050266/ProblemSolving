n=int(input("Enter the n value: "))
'''
Enter the n value: 5
     *
    * *
   * * *
  * * * *
 * * * * *
  * * * *
   * * *
    * *
     *

     Enter the n value: 7
       *
      * *
     * * *
    * * * *
   * * * * *
  * * * * * *
 * * * * * * *
  * * * * * *
   * * * * *
    * * * *
     * * *
      * *
       *

'''
space=' '
c='*'+space
spaces=n-1
stars=1

while stars<=n:
    print(space*spaces,c*stars)
    stars+=1
    spaces-=1
stars-=2
spaces+=2

while stars>=0:
    print(space*spaces,c*stars)
    stars-=1
    spaces+=1
