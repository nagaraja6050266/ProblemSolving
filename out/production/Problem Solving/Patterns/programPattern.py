import math as m
'''
Enter the string: program
 p          m
   r      a
     o  r
       g
     o  r
   r      a
 p          m

 Enter the string: nagarajaI
 n              I
   a          a
     g      j
       a  a
         r
       a  a
     g      j
   a          a
 n              I

'''
string=input("Enter the string: ")
nspaces=2
space=' '*nspaces
i,n=0,len(string)
gap=n-3
while i<n//2:
    print(space*i,string[i],space*(gap),string[n-i-1])
    i+=1
    gap-=2
if n%2!=0:
    print(space*i,string[i])
    i+=1
gap=0
while i<n:
    print(space*(n-i-1),string[n-i-1],space*gap,string[i])
    i+=1
    gap+=2