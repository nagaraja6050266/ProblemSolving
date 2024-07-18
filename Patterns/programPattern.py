import math as m
'''
P            M
  R       A
    O   R
       G
    O   R
  R       A
P            M


PROGRAM
PROGRAM




'''
string='PROGRAM'
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
