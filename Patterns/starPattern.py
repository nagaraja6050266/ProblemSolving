n=5
'''
         *  
       *  *  
     *  *  *  
   *  *  *  *  
 *  *  *  *  *  
 *  *  *  *  *  
   *  *  *  *  
     *  *  *  
       *  *  
         *  
'''
space='  '
c='*'+space
spaces=n-1
stars=1

while stars<=n:
    print(space*spaces,c*stars)
    stars+=1
    spaces-=1
stars-=1
spaces+=1

while stars>=0:
    print(space*spaces,c*stars)
    stars-=1
    spaces+=1
